package controller;

import java.io.IOException;

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
 * Servlet implementation class UserInfoSvl
 */
@WebServlet("/UserInfoSvl")
public class UserInfoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoSvl() {
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
		User user=null;
		String mail=null;

		Admin admin=(Admin)session.getAttribute("adminInfo");
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			 try {
				 	mail=request.getParameter("usermail");
					user=User.getUser(mail);
				} catch (IdealException e) {
					e.printStackTrace();
					IdealException ie = new IdealException(
							IdealException.ERR_NO_DB_EXCEPTION);
					request.setAttribute("msg", ie);
					RequestDispatcher rd = request
							.getRequestDispatcher("userIndex.jsp");
					rd.forward(request, response);
				}
		      request.setAttribute("user", user);
			  RequestDispatcher rd=request.getRequestDispatcher("userInfo.jsp");
			  rd.forward(request, response);
		}
	}

}
