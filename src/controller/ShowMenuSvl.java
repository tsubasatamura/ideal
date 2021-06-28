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

import model.Course;
import model.IdealException;
import model.Menu;
import model.User;

/**
 * Servlet implementation class showMenuSvl
 */
@WebServlet("/ShowMenuSvl")
public class ShowMenuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMenuSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		ArrayList<Course> courseList=null;
		ArrayList<Menu> menuList=null;

		User user=(User)session.getAttribute("userInfo");
		try{
			courseList=Course.getCourseList();
			menuList=Menu.getMenuList();


		}catch(Exception e){
			e.printStackTrace();
			IdealException ie = new IdealException(
					IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie);
			if(user!=null){
			RequestDispatcher rd = request
					.getRequestDispatcher("userIndex.jsp");
			rd.forward(request, response);
			}else{
				RequestDispatcher rd = request
						.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}
		}

		request.setAttribute("courseList", courseList);
		request.setAttribute("menuList", menuList);
		RequestDispatcher rd=request.getRequestDispatcher("showMenu.jsp");
		rd.forward(request, response);

	}

}
