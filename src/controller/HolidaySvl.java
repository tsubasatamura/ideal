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
import model.HCalendar;

/**
 * Servlet implementation class HolidaySvl
 */
@WebServlet("/HolidaySvl")
public class HolidaySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidaySvl() {
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
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			String s_year=request.getParameter("year");
			String s_month=request.getParameter("month");
			HCalendar mc=null;
			if(s_year != null && s_month != null) {
				int year =Integer.parseInt(s_year);
				int month=Integer.parseInt(s_month);
				if(month==0) {
					month=12;
					year--;
				}
				if(month==13) {
					month=1;
					year++;
				}
				//年と月のクエリパラメーターが来ている場合にはその年月でカレンダーを生成する
				mc=HCalendar.createCalendar(year,month);
			}else {
				//クエリパラメータが来ていないときは実行日時のカレンダーを生成する。
				mc=HCalendar.createCalendar();
			}
			//リクエストスコープに格納
			request.setAttribute("mc", mc);
			session.setAttribute("adminInfo", admin);

			RequestDispatcher rd=request.getRequestDispatcher("adminHoliday.jsp");
		    rd.forward(request, response);
		}
	}

}
