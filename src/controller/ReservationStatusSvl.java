package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HCalendar;
import model.IdealException;
import model.Reserve;

/**
 * Servlet implementation class ReservationStatus
 */
@WebServlet("/ReservationStatusSvl")
public class ReservationStatusSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationStatusSvl() {
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
		response.setContentType("trxt/html; charset=utf-8");
		RequestDispatcher rd = null;
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
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

		String date = String.format("%02d", mc.getYear()) + "-" + String.format("%02d", mc.getMonth()) + "-" + String.format("%02d", 1);

		HashMap<String, Integer> statusMap = new HashMap<String, Integer>();
		try {
			statusMap = Reserve.getMonthlyReservationStatus(date);

		} catch (IdealException e) {
			request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}

		//リクエストスコープに格納
		request.setAttribute("mc", mc);
		request.setAttribute("statusMap", statusMap);
		rd=request.getRequestDispatcher("reservationStatus.jsp");
	    rd.forward(request, response);
	}

}
