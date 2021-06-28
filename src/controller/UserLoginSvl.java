package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.User;

/**
 * Servlet implementation class UserLoginSvl
 */
@WebServlet("/UserLoginSvl")
public class UserLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");

			// User user=(User)request.getAttribute("userInfo");
			HttpSession session = request.getSession();

			String mail = request.getParameter("name");
			String password = request.getParameter("pass");
			User user = new User();

			user = User.login(mail, password);
			// System.out.println(mail);
			// System.out.println(user.getMail());
			if (mail.equals(user.getMail())
					&& password.equals(user.getPassword())) {

				session.setAttribute("userInfo", user);
				RequestDispatcher rd = request
						.getRequestDispatcher("userIndex.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			IdealException ie = new IdealException(
					IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie);
			RequestDispatcher rd = request
					.getRequestDispatcher("userLogin.jsp");
			rd.forward(request, response);
		}
	}
}