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

import model.Course;
import model.Holiday;
import model.IdealException;
import model.Reserve;
import model.User;

/**
 * Servlet implementation class ReserveInsertSvl
 */
@WebServlet("/ReserveInsertSvl")
public class ReserveInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveInsertSvl() {
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
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = null;

		String url = "";
		String rsvYyMmDd = "";
		String minYyMmDd = "";
		String maxYyMmDd = "";
		String rsvHhMi = "";
		int rsvYy;
		int rsvMm;
		int rsvDd;
		int rsvHh;
		int rsvMi;
		int person;
		int courseId;
		//int courseName;
		int charter;
		int tel;
		int mail;
		String exp;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if (User.userCheck(user)) {
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", request.getAttribute("msg"));
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
				try {
					rsvHhMi = request.getParameter("rsvHhMi");
					rsvHh = Integer.parseInt(rsvHhMi.substring(0, 2));
					rsvMi = Integer.parseInt(rsvHhMi.substring(3, 5));
				} catch (NumberFormatException | NullPointerException e) {
					rsvHhMi = now_date_time.substring(11, 16);
					rsvHh = Integer.parseInt(rsvHhMi.substring(0, 2));
					rsvMi = Integer.parseInt(rsvHhMi.substring(3, 5));
				}
				try {
					person = Integer.parseInt(request.getParameter("person"));
				} catch (NumberFormatException | NullPointerException e) {
					person = 2;
				}
				try {
					courseId = Integer.parseInt(request.getParameter("courseId"));
				} catch (NumberFormatException | NullPointerException e) {
					courseId = 1;
				}
				try {
					charter = Integer.parseInt(request.getParameter("charter"));
				} catch (NumberFormatException | NullPointerException e) {
					charter = 0;
				}
				try {
					tel = Integer.parseInt(request.getParameter("tel"));
				} catch (NumberFormatException | NullPointerException e) {
					tel = 0;
				}
				try {
					mail = Integer.parseInt(request.getParameter("mail"));
				} catch (NumberFormatException | NullPointerException e) {
					mail = 0;
				}
				try {
					exp = request.getParameter("exp");
				} catch (NumberFormatException | NullPointerException e) {
					exp = "";
				}

				// 予約可能な時間一覧取得
				request.setAttribute("reservableList", Reserve.getReservableNumList(rsvYyMmDd, person, charter));
				// オーダー可能なコース一覧取得
				request.setAttribute("courseList", Course.getOneCourseList());

				Reserve rsv = new Reserve();
				rsv.setUsrId(user.getUsr_id());
				rsv.setRsvYy(rsvYy);
				rsv.setRsvMm(rsvMm);
				rsv.setRsvDd(rsvDd);
				rsv.setRsvHh(rsvHh);
				rsv.setRsvMi(rsvMi);
				rsv.setPerson(person);
				rsv.setCourseId(courseId);
				rsv.setCharter(charter);
				rsv.setTel(tel);
				rsv.setMail(mail);
				rsv.setExp(exp);

				// 定休日のチェック
				if(Holiday.checkHoliday(rsvYyMmDd)){
					request.setAttribute("holidayMsg", "定休日なので予約できません。");
				}

				// 一日の予約状況チェック
				int status = Reserve.getReservationStatus(rsvYyMmDd);
				request.setAttribute("status", status);

				request.setAttribute("reserve", rsv);
				request.setAttribute("minYyMmDd", minYyMmDd);
				request.setAttribute("maxYyMmDd", maxYyMmDd);
				url = "reserveInsert.jsp";

			} catch (IdealException e) {
				request.setAttribute("msg", e.getErrmsg());
				url = "ReserveInsertSvl";
			}
			rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
