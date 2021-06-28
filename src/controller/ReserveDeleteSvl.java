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
import model.Reserve;
import model.User;

/**
 * Servlet implementation class ReserveDeleteSvl
 */
@WebServlet("/ReserveDeleteSvl")
public class ReserveDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveDeleteSvl() {
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
		RequestDispatcher rd = null;
		String url = "";
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if (User.userCheck(user)) {
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			try {
				int rsvId = Integer.parseInt(request.getParameter("rsvId"));
				request.setAttribute("reserve", Reserve.getReserve(rsvId));
				url = "reserveDelete.jsp";
			} catch (IdealException e) {
				request.setAttribute("msg", e.getErrmsg());
				url = "ReserveListSvl";
			}
		}
		rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
