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

import model.*;

/**
 * Servlet implementation class AdminReserveOperationSvl
 */
@WebServlet("/AdminReserveOperationSvl")
public class AdminReserveOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ファイナル変数宣言
	public static final int TO_TOP = 0;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReserveOperationSvl() {
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

		int mode;
		String url = "";
		String rsvYyMmDd = "";
		int userId;
		int rsvId;
		int rsvYy;
		int rsvMm;
		String rsvHhMi = "";
		int rsvDd;
		int rsvHh;
		int rsvMi;
		int person;
		int courseId;
		int tableId;
		int charter;
		int tel;
		int mail;
		String exp = "";

		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("adminInfo");

		if(admin==null){

			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);

		} else {

			try {
				mode = Integer.parseInt(request.getParameter("mode"));
			} catch (NumberFormatException | NullPointerException e) {
				mode = TO_TOP;
			}

			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (NumberFormatException | NullPointerException e) {
				userId = 1;
			}

			try {
				rsvId = Integer.parseInt(request.getParameter("rsvId"));
			} catch (NumberFormatException | NullPointerException e) {
				rsvId = 0;
			}

			DateTimeFormatter fmt01 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String now_date_time = fmt01.format(LocalDateTime.now());

			try {
				rsvYyMmDd = request.getParameter("rsvYyMmDd");
				rsvYy = Integer.parseInt(rsvYyMmDd.substring(0, 4));
				rsvMm = Integer.parseInt(rsvYyMmDd.substring(5, 7));
				rsvDd = Integer.parseInt(rsvYyMmDd.substring(8, 10));
			} catch (NumberFormatException | NullPointerException e) {
				rsvYy = Integer.parseInt(now_date_time.substring(0, 4));
				rsvMm = Integer.parseInt(now_date_time.substring(5, 7));
				rsvDd = Integer.parseInt(now_date_time.substring(8, 10));
			}

			try {
				rsvHhMi = request.getParameter("rsvHhMi");
				rsvHh = Integer.parseInt(rsvHhMi.substring(0, 2));
				rsvMi = Integer.parseInt(rsvHhMi.substring(3, 5));
			} catch (NumberFormatException | NullPointerException e) {
				rsvHh = Integer.parseInt(now_date_time.substring(11, 13));
				rsvMi = Integer.parseInt(now_date_time.substring(14, 16));
			}

			try {
				person = Integer.parseInt(request.getParameter("person"));
			} catch (NumberFormatException | NullPointerException e) {
				person = 0;
			}

			try {
				courseId = Integer.parseInt(request.getParameter("courseId"));
			} catch (NumberFormatException | NullPointerException e) {
				courseId = 0;
			}

			try {
				tableId = Integer.parseInt(request.getParameter("tableId"));
			} catch (NumberFormatException | NullPointerException e) {
				tableId = 0;
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

			try {
				exp = request.getParameter("exp").replace(System.lineSeparator(), "<br/>");
			} catch (NumberFormatException | NullPointerException e) {
				exp = "";
			}

			if (mode == TO_TOP) {

				url = "/home.jsp";

			} else if (mode == INSERT) {
				try {

					Reserve rsv = new Reserve();
					// rsv.setRsvId(rsvId);
					rsv.setUsrId(userId);
					rsv.setRsvYy(rsvYy);
					rsv.setRsvMm(rsvMm);
					rsv.setRsvDd(rsvDd);
					rsv.setRsvHh(rsvHh);
					rsv.setRsvMi(rsvMi);
					rsv.setPerson(person);
					rsv.setCourseId(courseId);
					rsv.setTableId(tableId);
					rsv.setCharter(charter);
					rsv.setTel(tel);
					rsv.setMail(mail);
					rsv.setExp(exp);

					TableLoc tl = null;

					String rsvDate = rsvYyMmDd + " " + rsvHhMi;
					if ((tl = Reserve.insertChk(rsvDate, person, charter)) != null) {
						int table_id = tl.getTable_id();
						String table_name = tl.getTableName();
						rsv.setTableId(table_id);
						rsv.setTableName(table_name);
						rsv = Reserve.insert(rsv);
						request.setAttribute("reserve", Reserve.getReserve(rsv.getRsvId()));
						url = "reserveCompletion.jsp";

					} else {
						request.setAttribute("reserve", rsv);
						throw new IdealException(IdealException.ERR_NO_NOT_VACANCY_EXCEPTION);
					}
				} catch (IdealException e) {
					request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
					url = "ReserveInsertSvl";
				}

			} else if (mode == UPDATE) {

				try {
					Reserve rsv = new Reserve();
					rsv.setRsvId(rsvId);
					//rsv.setUsrId(userId);
					rsv.setRsvYy(rsvYy);
					rsv.setRsvMm(rsvMm);
					rsv.setRsvDd(rsvDd);
					rsv.setRsvHh(rsvHh);
					rsv.setRsvMi(rsvMi);
					rsv.setPerson(person);
					rsv.setCourseId(courseId);
					rsv.setTableId(tableId);
					rsv.setCharter(charter);
					rsv.setTel(tel);
					rsv.setMail(mail);
					rsv.setExp(exp);

					TableLoc tl = null;

					String rsvDate = rsvYyMmDd + " " + rsvHhMi;
					if ((tl = Reserve.updateChk(rsvId, rsvDate, person, charter)) != null) {
						int table_id = tl.getTable_id();
						String table_name = tl.getTableName();
						rsv.setTableId(table_id);
						rsv.setTableName(table_name);
						rsv = Reserve.update(rsv);
						request.setAttribute("rserve", rsv);
						url = "AdminReserveListSvl";

					} else {
						request.setAttribute("reserve", rsv);
						throw new IdealException(IdealException.ERR_NO_NOT_VACANCY_EXCEPTION);
					}
				} catch (IdealException e) {
					request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
					url = "AdminReserveUpdateSvl";
				}

			} else if (mode == DELETE) {

				try {
					rsvId = Integer.parseInt(request.getParameter("rsvId"));
					Reserve rsv = Reserve.getReserve(rsvId);
					Reserve.delete(rsv);
					request.setAttribute("reserve", rsvId);
					url = "AdminReserveListSvl";
				} catch (IdealException e) {
					request.setAttribute("msg", e.getErrmsg());// エラーメッセージを入れる
					url = "AdminReserveDeleteSvl";
				}
			}

			rd = request.getRequestDispatcher(url);
			rd.forward(request, response);

		}
	}

}
