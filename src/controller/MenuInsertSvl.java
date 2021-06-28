package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Admin;
import model.Course;
import model.IdealException;
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class MenuInsertSvl
 */
@WebServlet("/MenuInsertSvl")
public class MenuInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuInsertSvl() {
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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		int mode=Integer.parseInt(request.getParameter("mode"));
		Admin admin=(Admin)session.getAttribute("adminInfo");
		String url=null;
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			  try{

				  request.setAttribute("mType", MenuType.getAllType());
			  int typeID=Integer.parseInt(request.getParameter("typeID"));


			if(typeID==100){

				 ArrayList<MenuType> typeMenuList=MenuType.getAllType();
				  request.setAttribute("typeMenuList",typeMenuList);



				  url="courseInsert.jsp";
				/*
					ArrayList<Menu>al=Menu.getMenu(typeID);
						request.setAttribute("Menu",al);
						rd=request.getRequestDispatcher("courseInsert.jsp");
							rd.forward(request, response);
							*/

				}else{

					 ArrayList<MenuType> mType=MenuType.getAllType();
					  request.setAttribute("mType",mType);
					  request.setAttribute("typeID",typeID);

					 
					  url="insertMenu.jsp";

/*
					ArrayList<MenuType>al=MenuType.getAllType();
					ArrayList<Menu>menu=Menu.getMenu(typeID);

					 request.setAttribute("mType", al);
					 request.setAttribute("Menu",menu);
					 */




}

			}catch (IdealException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

			  request.setAttribute("msg", ie);
			  request.setAttribute("adminInfo", admin);
			  RequestDispatcher rd=request.getRequestDispatcher("MenuMaintenanceSvl");
			  rd.forward(request, response);
	}
			  session.setAttribute("adminInfo", admin);
			  RequestDispatcher rd=request.getRequestDispatcher(url);
			  rd.forward(request, response);
	}

}
}
