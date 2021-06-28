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
import model.Contact;
import model.IdealException;
import model.User;

/**
 * Servlet implementation class AdminInsertSvl
 */
@WebServlet("/AdminInsertSvl")
public class AdminInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInsertSvl() {
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
		ArrayList<Contact>al=null;
		User user=new User();
		String userID=request.getParameter("userID");

		Admin admin=(Admin)session.getAttribute("adminInfo");
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			 try {
					user.setUsr_id(Integer.parseInt(userID));
					al=Contact.userContactList(user);
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
			  request.setAttribute("userID", user);

			  RequestDispatcher rd=request.getRequestDispatcher("adminContact.jsp");
				rd.forward(request, response);
		}

	}

}
