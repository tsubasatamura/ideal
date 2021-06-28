package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Course;
import model.IdealException;
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class MenuUpdateSvl
 */
@WebServlet("/MenuUpdateSvl")
public class MenuUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuUpdateSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();

		Admin admin=(Admin)session.getAttribute("adminInfo");
		String url=null;
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			  int typeID=Integer.parseInt(request.getParameter("typeID"));
			  int menuID=Integer.parseInt(request.getParameter("menuID"));
			  try{
				  if(typeID==100){
					  ArrayList<MenuType> typeMenuList=MenuType.getAllType();
					  request.setAttribute("typeMenuList",typeMenuList);
					  ArrayList<Course> oneCourse=Course.getOneCourse(menuID);

					  request.setAttribute("oneCourse",oneCourse);
					  url="courseUpdate.jsp";
				  }else{
					  ArrayList<MenuType> mType=MenuType.getAllType();
					  request.setAttribute("mType",mType);
					  request.setAttribute("typeID",typeID);
					  request.setAttribute("oneMenu",Menu.getOneMenu(menuID, typeID));
					  url="updateMenu.jsp";
				  }
			  }catch(Exception e){
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
