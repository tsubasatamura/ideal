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
import model.Contact;
import model.IdealException;

/**
 * Servlet implementation class AdminContactOperationSvl
 */
@WebServlet("/AdminContactOperationSvl")
public class AdminContactOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminContactOperationSvl() {
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

		Admin admin=(Admin)session.getAttribute("adminInfo");
		String text=request.getParameter("admcontact");
		int userID=Integer.parseInt(request.getParameter("userID"));

		try {
			Contact.adminsertContact(userID, text);
		} catch (IdealException e) {
			e.printStackTrace();
			IdealException ie = new IdealException(
					IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie);
			RequestDispatcher rd = request
					.getRequestDispatcher("adminContact.jsp");
			rd.forward(request, response);
		}
			session.setAttribute("adminInfo", admin);

		  RequestDispatcher rd=request.getRequestDispatcher("AdminInsertSvl");
			rd.forward(request, response);
	}

}
