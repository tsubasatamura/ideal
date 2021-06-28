package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Course;
import model.Holiday;
import model.IdealException;
import model.Reserve;

/**
 * Servlet implementation class AdminReserveUpdateSvl
 */
@WebServlet("/AdminReserveUpdateSvl")
public class AdminReserveUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReserveUpdateSvl() {
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

		int rsvId;
		String url = "";
		String rsvYyMmDd = "";
		String minYyMmDd = "";
		String maxYyMmDd = "";
		String rsvHhMi = "";

		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("adminInfo");

		if(admin==null){

			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);

		}else{

			request.setAttribute("msg", request.getAttribute("msg"));

			try {

				rsvId = Integer.parseInt(request.getParameter("rsvId"));
				Reserve rsv = new Reserve();
				rsv = Reserve.getReserve(rsvId);

				// 予約可能期間の設定
				LocalDateTime nowDate = LocalDateTime.now();
				DateTimeFormatter fmt01 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String now_date_time = fmt01.format(nowDate);
				String p1y_date_time = fmt01.format(nowDate.plusYears(1));
				minYyMmDd = now_date_time.substring(0, 10);
				maxYyMmDd = p1y_date_time.substring(0, 10);

				if(request.getParameter("rsvYyMmDd") != null){
					rsvYyMmDd = request.getParameter("rsvYyMmDd");
					rsv.setRsvYy(Integer.parseInt(rsvYyMmDd.substring(0, 4)));
					rsv.setRsvMm(Integer.parseInt(rsvYyMmDd.substring(5, 7)));
					rsv.setRsvDd(Integer.parseInt(rsvYyMmDd.substring(8, 10)));
				}else{
					rsvYyMmDd = String.format("%02d", rsv.getRsvYy()) + "-" + String.format("%02d", rsv.getRsvMm()) + "-" + String.format("%02d", rsv.getRsvDd());
				}
				if(request.getParameter("rsvHhMi") != null){
					rsvHhMi = request.getParameter("rsvHhMi");
					rsv.setRsvHh(Integer.parseInt(rsvHhMi.substring(0, 2)));
					rsv.setRsvMi(Integer.parseInt(rsvHhMi.substring(3, 5)));
				}
				if(request.getParameter("person") != null){
					rsv.setPerson(Integer.parseInt(request.getParameter("person")));
				}
				if(request.getParameter("courseId") != null){
					rsv.setCourseId(Integer.parseInt(request.getParameter("courseId")));
				}
				if(request.getParameter("charter") != null){
					rsv.setCharter(Integer.parseInt(request.getParameter("charter")));
				}
				if(request.getParameter("tel") != null){
					rsv.setTel(Integer.parseInt(request.getParameter("tel")));
				}
				if(request.getParameter("mail") != null){
					rsv.setMail(Integer.parseInt(request.getParameter("mail")));
				}
				if(request.getParameter("exp") != null){
					rsv.setExp(request.getParameter("exp").replace(System.lineSeparator(), "<br/>"));
				}
				// 予約可能な時間一覧取得
				request.setAttribute("reservableList", Reserve.getUpdateReservableNumList(rsvId, rsvYyMmDd, rsv.getPerson(), rsv.getCharter()));
				// オーダー可能なコース一覧取得
				request.setAttribute("courseList", Course.getOneCourseList());

				// 定休日のチェック
				if(Holiday.checkHoliday(rsvYyMmDd)){
					request.setAttribute("holidayMsg", "定休日なので予約できません。");
				}

				request.setAttribute("reserve", rsv);
				request.setAttribute("minYyMmDd", minYyMmDd);
				request.setAttribute("maxYyMmDd", maxYyMmDd);
				url = "adminReserveUpdate.jsp";

			} catch (IdealException e) {
				request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
				url = "AdminReserveListSvl";
			}
			rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}

}
