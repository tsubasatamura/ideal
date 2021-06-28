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

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/AdminLoginSvl")
public class AdminLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginSvl() {
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

		//User user=(User)request.getAttribute("userInfo");
		HttpSession session = request.getSession();


		String admName=request.getParameter("id");
		String password=request.getParameter("pass");
		Admin admin=new Admin();

		try{
			admin=Admin.login(admName,password);
			//System.out.println(admName);
			//System.out.println(admin.getAdmName());
			if(admName.equals(admin.getAdmName())&&password.equals(admin.getPassword())){
				session.setAttribute("adminInfo", admin);
				RequestDispatcher rd=request.getRequestDispatcher("adminIndex.jsp");
				  rd.forward(request, response);
			}
			}catch(Exception e){
				IdealException ie = new IdealException(
						IdealException.ERR_NO_DB_EXCEPTION);
				request.setAttribute("msg", ie);
				RequestDispatcher rd=request.getRequestDispatcher("adminLogin.jsp");
				  rd.forward(request, response);



		}
    }
}