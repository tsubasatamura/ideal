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

import model.IdealException;
import model.Reserve;
import model.User;

/**
 * Servlet implementation class ReserveListSvl
 */
@WebServlet("/ReserveListSvl")
public class ReserveListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveListSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("trxt/html; charset=utf-8");
		RequestDispatcher rd = null;

		ArrayList<Reserve> rl = new ArrayList<Reserve>();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if (User.userCheck(user)) {
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {

			try {
				rl = Reserve.getReserveList(user.getUsr_id());
				request.setAttribute("reserveList", rl);
				rd = request.getRequestDispatcher("/reserveList.jsp");
				rd.forward(request, response);
			} catch (IdealException e) {
				request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
				rd = request.getRequestDispatcher("/userIndex.jsp");
				rd.forward(request, response);
			}
		}
	}

}
