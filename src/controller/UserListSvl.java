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
import model.IdealException;
import model.User;

/**
 * Servlet implementation class UserListSvl
 */
@WebServlet("/UserListSvl")
public class UserListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		ArrayList<User>al=null;

		Admin admin=(Admin)session.getAttribute("adminInfo");
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			 try {
					al=User.getUserList();
				} catch (IdealException e) {
					e.printStackTrace();
					IdealException ie = new IdealException(
							IdealException.ERR_NO_DB_EXCEPTION);
					request.setAttribute("msg", ie);
					RequestDispatcher rd = request
							.getRequestDispatcher("userIndex.jsp");
					rd.forward(request, response);
				}
		      request.setAttribute("list", al);
			  session.setAttribute("adminInfo", admin);

			  RequestDispatcher rd=request.getRequestDispatcher("userList.jsp");
				rd.forward(request, response);
		}

	}

}
