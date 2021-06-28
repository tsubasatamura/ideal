package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Admin;
import model.IdealException;
import model.Menu;
import model.MenuType;


/**
 * Servlet implementation class MenuMaintenanceSvl
 */
@WebServlet("/MenuMaintenanceSvl")
public class MenuMaintenanceSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int TO_TOP=0;
	public static final int TO_INSERT = 1;
	public static final int TO_UPDATE=2;
	public static final int TO_DELETE=3;
	public static final int INSERT=11;
	public static final int UPDATE=12;
	public static final int DELETE=13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuMaintenanceSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=null;
		String url="";

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();

		Admin admin=(Admin)session.getAttribute("adminInfo");

		if(admin==null){
			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			int typeID;


				if(request.getParameter("typeID")==null){
					typeID=100;
				}else{
				typeID=Integer.parseInt(request.getParameter("typeID"));
				}


			try{

				ArrayList<MenuType>mType=MenuType.getAllType();
				ArrayList<Menu>menu=Menu.getMenu(typeID);



				 request.setAttribute("mType", mType);
				 request.setAttribute("menu",menu);




				rd=request.getRequestDispatcher("menuMaintenance.jsp");
				rd.forward(request, response);



				}catch(Exception e){
					 e.printStackTrace();
					  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

					  request.setAttribute("msg", ie);
					  request.setAttribute("adminInfo", admin);
					  rd=request.getRequestDispatcher("adminIndex.jsp");
					  rd.forward(request, response);
				}
				}


		}



}
