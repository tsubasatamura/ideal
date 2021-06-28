package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;
import model.Reserve;

/**
 * Servlet implementation class AdminReserveListSvl
 */
@WebServlet("/AdminReserveListSvl")
public class AdminReserveListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReserveListSvl() {
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

		ArrayList<Reserve> rl = new ArrayList<Reserve>();
		String rsvYyMmDd = "";
		String minYyMmDd = "";
		String maxYyMmDd = "";
		int rsvYy;
		int rsvMm;
		int rsvDd;

		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("adminInfo");

		if(admin==null){
			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			try {
				LocalDateTime nowDate = LocalDateTime.now();
				DateTimeFormatter fmt01 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String now_date_time = fmt01.format(nowDate);
				// 予約可能期間の設定
				String p1y_date_time = fmt01.format(nowDate.plusYears(1));
				minYyMmDd = now_date_time.substring(0, 10);
				maxYyMmDd = p1y_date_time.substring(0, 10);
				try {
					rsvYyMmDd = request.getParameter("rsvYyMmDd");
					rsvYy = Integer.parseInt(rsvYyMmDd.substring(0, 4));
					rsvMm = Integer.parseInt(rsvYyMmDd.substring(5, 7));
					rsvDd = Integer.parseInt(rsvYyMmDd.substring(8, 10));
				} catch (NumberFormatException | NullPointerException e) {
					rsvYy = Integer.parseInt(now_date_time.substring(0, 4));
					rsvMm = Integer.parseInt(now_date_time.substring(5, 7));
					rsvDd = Integer.parseInt(now_date_time.substring(8, 10));
					rsvYyMmDd = String.format("%02d", rsvYy) + "-" + String.format("%02d", rsvMm) + "-" + String.format("%02d", rsvDd);
				}
				//rl = Reserve.getReserveList();
				rl = Reserve.getDaylyReserveList(rsvYyMmDd);
				request.setAttribute("reserveList", rl);

				HashMap<String, Reserve> hm = new HashMap<String, Reserve>();
				for (Reserve r : rl) {
					int tableId = r.getTableId();
					int rsvHh = r.getRsvHh();
					int rsvMi = r.getRsvMi();
					hm.put( tableId + "/" +rsvHh+ ":" +rsvMi, r);
				}
				request.setAttribute("reserveMap", hm);
				request.setAttribute("rsvYyMmDd", rsvYyMmDd);
				request.setAttribute("minYyMmDd", minYyMmDd);
				request.setAttribute("maxYyMmDd", maxYyMmDd);
				rd = request.getRequestDispatcher("/adminReserveList.jsp");
				rd.forward(request, response);
			} catch (IdealException e) {
				request.setAttribute("msg", e.getErrmsg());
				e.printStackTrace();
				rd = request.getRequestDispatcher("/adminIndex.jsp");
				rd.forward(request, response);
			}
		}

	}

}
