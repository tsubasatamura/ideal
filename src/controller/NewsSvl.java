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
import model.News;

/**
 * Servlet implementation class NewsSvl
 */
@WebServlet("/NewsSvl")
public class NewsSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsSvl() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("adminInfo");
		if(admin==null){
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher rd = request.getRequestDispatcher("newsCtl.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// User user=(User)request.getAttribute("userInfo");
		int mode = Integer.parseInt(request.getParameter("mode"));
		if (!(mode == 14 || mode == 15 || mode == 16)) {
			RequestDispatcher rd = request.getRequestDispatcher("newsCtl.jsp");
			rd.forward(request, response);
		}

		switch (mode) {
		case 14:
			try {
				String title = request.getParameter("title");
				String text = request.getParameter("messages");
				//System.out.println(title);
				//System.out.println(text);
				News news=new News();
				news.setNewsTitle(title);
				news.setNewsText(text);
				String setTitle=news.getNewsTitle();
				String setText=news.getNewsText();
				news=News.insertNews(setTitle, setText);
			} catch (IdealException e) {
				e.printStackTrace();
				IdealException ie = new IdealException(
						IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher("newsCtl.jsp");
			rd.forward(request, response);
			break;

		case 15:
			try {
				String title = request.getParameter("title");
				String text = request.getParameter("message");
				News news = new News();
				// updateから20210512
				news = News.getNews();

				news.setNewsTitle(title);
				news.setNewsText(text);

				news = News.update(news);

			} catch (Exception e) {
				e.printStackTrace();
				IdealException ie = new IdealException(
						IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				rd = request.getRequestDispatcher("NewsSvl");
				rd.forward(request, response);
			}
			rd = request.getRequestDispatcher("newsCtl.jsp");
			rd.forward(request, response);
			break;

		case 16:
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				News news = new News();
				System.out.println(id);
				news.setNewsId(id);
				News.delete(id);

			} catch (Exception e) {
				e.printStackTrace();
				IdealException ie = new IdealException(
						IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				rd = request.getRequestDispatcher("adminIndex.jsp");
				rd.forward(request, response);
			}
			rd = request.getRequestDispatcher("newsCtl.jsp");
			rd.forward(request, response);
			break;
		}

	}

}
