package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Reserve implements Serializable {

	private static final long serialVersionUID = 1L;
	private int rsvId;
	private int usrId;
	private String usrName;
	private String usrPhone;
	private String usrMail;
	private int usrGenderId;
	private int rsvYy;
	private int rsvMm;
	private int rsvDd;
	private int rsvHh;
	private int rsvMi;
	private int person;
	private int tableId;
	private String tableName;
	private int courseId;
	private String courseName;
	private String appDate;
	private int appYy;
	private int appMm;
	private int appDd;
	private int appHh;
	private int appMi;
	private int charter;
	private String exp;
	private int tel;
	private int mail;
	private int coursePrice;
	private int totalPrice;

	public static final int CHARTER_FEE = 30000;// 貸切料金

	public Reserve() {
		super();
	}

	public static ArrayList<Reserve> getReserveList() throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Reserve> rl = new ArrayList<Reserve>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve"
					+ " INNER JOIN user1 USING (usr_id)"
					+ " INNER JOIN table_loc USING (table_id)"
					+ " INNER JOIN course USING (c_id)"
					//+ " WHERE usr_id = ? ORDER BY rsv_id";
					+ " ORDER BY rsv_id";
			pst = con.prepareStatement(sql);
			//pst.setInt(1, usrId);
			//System.out.println("<<<getReserveList01>>>" + pst.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				Reserve rsv = new Reserve();
				rsv.setRsvId(rs.getInt("rsv_id"));
				rsv.setUsrId(rs.getInt("usr_id"));
				rsv.setUsrName(rs.getString("usr_name"));
				rsv.setUsrPhone(rs.getString("phone"));
				rsv.setUsrMail(rs.getString("user1.mail"));
				rsv.setUsrGenderId(rs.getInt("gender_id"));
				rsv.setRsvYy(Integer.parseInt(rs.getString("rsv_date").substring(0, 4)));
				rsv.setRsvMm(Integer.parseInt(rs.getString("rsv_date").substring(5, 7)));
				rsv.setRsvDd(Integer.parseInt(rs.getString("rsv_date").substring(8, 10)));
				rsv.setRsvHh(Integer.parseInt(rs.getString("rsv_date").substring(11, 13)));
				rsv.setRsvMi(Integer.parseInt(rs.getString("rsv_date").substring(14, 16)));
				rsv.setPerson(rs.getInt("person"));
				rsv.setTableId(rs.getInt("table_id"));
				rsv.setTableName(rs.getString("table_name"));
				rsv.setCourseId(rs.getInt("c_id"));
				rsv.setCourseName(rs.getString("c_name"));
				rsv.setAppDate(rs.getString("app_date"));
				rsv.setAppYy(Integer.parseInt(rs.getString("app_date").substring(0, 4)));
				rsv.setAppMm(Integer.parseInt(rs.getString("app_date").substring(5, 7)));
				rsv.setAppDd(Integer.parseInt(rs.getString("app_date").substring(8, 10)));
				rsv.setAppHh(Integer.parseInt(rs.getString("app_date").substring(11, 13)));
				rsv.setAppMi(Integer.parseInt(rs.getString("app_date").substring(14, 16)));
				rsv.setCharter(rs.getInt("charter"));
				rsv.setExp(rs.getString("exp"));
				rsv.setTel(rs.getInt("tel"));
				rsv.setMail(rs.getInt("reserve.mail"));
				rsv.setCoursePrice(rs.getInt("price"));
				rsv.setTotalPrice(rs.getInt("price"));

				rl.add(rsv);
			}
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return rl;
	}

	// 各ユーザーの予約一覧をリストで返す
	public static ArrayList<Reserve> getReserveList(int usrId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Reserve> rl = new ArrayList<Reserve>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve"
					+ " INNER JOIN user1 USING (usr_id)"
					+ " INNER JOIN table_loc USING (table_id)"
					+ " INNER JOIN course USING (c_id)"
					+ " WHERE usr_id = ? ORDER BY rsv_id";
			pst = con.prepareStatement(sql);
			pst.setInt(1, usrId);
			//System.out.println("<<<getReserveList02>>>" + pst.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				Reserve rsv = new Reserve();
				rsv.setRsvId(rs.getInt("rsv_id"));
				rsv.setUsrId(rs.getInt("usr_id"));
				rsv.setUsrName(rs.getString("usr_name"));
				rsv.setUsrPhone(rs.getString("phone"));
				rsv.setUsrMail(rs.getString("user1.mail"));
				rsv.setUsrGenderId(rs.getInt("gender_id"));
				rsv.setRsvYy(Integer.parseInt(rs.getString("rsv_date").substring(0, 4)));
				rsv.setRsvMm(Integer.parseInt(rs.getString("rsv_date").substring(5, 7)));
				rsv.setRsvDd(Integer.parseInt(rs.getString("rsv_date").substring(8, 10)));
				rsv.setRsvHh(Integer.parseInt(rs.getString("rsv_date").substring(11, 13)));
				rsv.setRsvMi(Integer.parseInt(rs.getString("rsv_date").substring(14, 16)));
				rsv.setPerson(rs.getInt("person"));
				rsv.setTableId(rs.getInt("table_id"));
				rsv.setTableName(rs.getString("table_name"));
				rsv.setCourseId(rs.getInt("c_id"));
				rsv.setCourseName(rs.getString("c_name"));
				rsv.setAppDate(rs.getString("app_date"));
				rsv.setAppYy(Integer.parseInt(rs.getString("app_date").substring(0, 4)));
				rsv.setAppMm(Integer.parseInt(rs.getString("app_date").substring(5, 7)));
				rsv.setAppDd(Integer.parseInt(rs.getString("app_date").substring(8, 10)));
				rsv.setAppHh(Integer.parseInt(rs.getString("app_date").substring(11, 13)));
				rsv.setAppMi(Integer.parseInt(rs.getString("app_date").substring(14, 16)));
				rsv.setCharter(rs.getInt("charter"));
				rsv.setExp(rs.getString("exp"));
				rsv.setTel(rs.getInt("tel"));
				rsv.setMail(rs.getInt("reserve.mail"));
				rsv.setCoursePrice(rs.getInt("price"));
				rsv.setTotalPrice(rs.getInt("price"));

				rl.add(rsv);
			}
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return rl;
	}

	//その日の予約リストを返す
	public static ArrayList<Reserve> getDaylyReserveList(String day) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Reserve> rl = new ArrayList<Reserve>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve"
					+ " INNER JOIN user1 USING (usr_id)"
					+ " INNER JOIN table_loc USING (table_id)"
					+ " INNER JOIN course USING (c_id)"
					+ " WHERE rsv_date BETWEEN ? AND ? ORDER BY table_id,rsv_date";
			pst = con.prepareStatement(sql);
			pst.setString(1, day + " 00:00:00");
			pst.setString(2, day + " 23:59:59");
			//System.out.println("<<<getDaylyReserveList>>>" + pst.toString());
			rs = pst.executeQuery();

			while (rs.next()) {
				Reserve rsv = new Reserve();
				rsv.setRsvId(rs.getInt("rsv_id"));
				rsv.setUsrId(rs.getInt("usr_id"));
				rsv.setUsrName(rs.getString("usr_name"));
				rsv.setUsrPhone(rs.getString("phone"));
				rsv.setUsrMail(rs.getString("user1.mail"));
				rsv.setUsrGenderId(rs.getInt("gender_id"));
				rsv.setRsvYy(Integer.parseInt(rs.getString("rsv_date").substring(0, 4)));
				rsv.setRsvMm(Integer.parseInt(rs.getString("rsv_date").substring(5, 7)));
				rsv.setRsvDd(Integer.parseInt(rs.getString("rsv_date").substring(8, 10)));
				rsv.setRsvHh(Integer.parseInt(rs.getString("rsv_date").substring(11, 13)));
				rsv.setRsvMi(Integer.parseInt(rs.getString("rsv_date").substring(14, 16)));
				rsv.setPerson(rs.getInt("person"));
				rsv.setTableId(rs.getInt("table_id"));
				rsv.setTableName(rs.getString("table_name"));
				rsv.setCourseId(rs.getInt("c_id"));
				rsv.setCourseName(rs.getString("c_name"));
				rsv.setAppDate(rs.getString("app_date"));
				rsv.setAppYy(Integer.parseInt(rs.getString("app_date").substring(0, 4)));
				rsv.setAppMm(Integer.parseInt(rs.getString("app_date").substring(5, 7)));
				rsv.setAppDd(Integer.parseInt(rs.getString("app_date").substring(8, 10)));
				rsv.setAppHh(Integer.parseInt(rs.getString("app_date").substring(11, 13)));
				rsv.setAppMi(Integer.parseInt(rs.getString("app_date").substring(14, 16)));
				rsv.setCharter(rs.getInt("charter"));
				rsv.setExp(rs.getString("exp"));
				rsv.setTel(rs.getInt("tel"));
				rsv.setMail(rs.getInt("reserve.mail"));
				rsv.setCoursePrice(rs.getInt("price"));
				rsv.setTotalPrice(rs.getInt("price"));

				rl.add(rsv);
			}
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return rl;
	}

	public static Reserve getReserve(int rsvId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Reserve rsv = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve"
					+ " INNER JOIN user1 USING (usr_id)"
					+ " INNER JOIN table_loc USING (table_id)"
					+ " INNER JOIN course USING (c_id)"
					+ " WHERE rsv_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, rsvId);
			//System.out.println("<<<getReserve>>" + pst.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				rsv = new Reserve();
				rsv.setRsvId(rs.getInt("rsv_id"));
				rsv.setUsrId(rs.getInt("usr_id"));
				rsv.setUsrName(rs.getString("usr_name"));
				rsv.setUsrPhone(rs.getString("phone"));
				rsv.setUsrMail(rs.getString("user1.mail"));
				rsv.setUsrGenderId(rs.getInt("gender_id"));
				rsv.setRsvYy(Integer.parseInt(rs.getString("rsv_date").substring(0, 4)));
				rsv.setRsvMm(Integer.parseInt(rs.getString("rsv_date").substring(5, 7)));
				rsv.setRsvDd(Integer.parseInt(rs.getString("rsv_date").substring(8, 10)));
				rsv.setRsvHh(Integer.parseInt(rs.getString("rsv_date").substring(11, 13)));
				rsv.setRsvMi(Integer.parseInt(rs.getString("rsv_date").substring(14, 16)));
				rsv.setPerson(rs.getInt("person"));
				rsv.setTableId(rs.getInt("table_id"));
				rsv.setTableName(rs.getString("table_name"));
				rsv.setCourseId(rs.getInt("c_id"));
				rsv.setCourseName(rs.getString("c_name"));
				rsv.setAppYy(Integer.parseInt(rs.getString("app_date").substring(0, 4)));
				rsv.setAppMm(Integer.parseInt(rs.getString("app_date").substring(5, 7)));
				rsv.setAppDd(Integer.parseInt(rs.getString("app_date").substring(8, 10)));
				rsv.setAppHh(Integer.parseInt(rs.getString("app_date").substring(11, 13)));
				rsv.setAppMi(Integer.parseInt(rs.getString("app_date").substring(14, 16)));
				rsv.setCharter(rs.getInt("charter"));
				rsv.setExp(rs.getString("exp"));
				rsv.setTel(rs.getInt("tel"));
				rsv.setMail(rs.getInt("reserve.mail"));
				rsv.setCoursePrice(rs.getInt("price"));
				rsv.setTotalPrice(rs.getInt("price"));
			}

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return rsv;
	}

	// 指定時間の予約可能件数を返す
	public static int getReservableNum(String dateTime, int person, int charter) throws IdealException {
		int num = 0;// 予約可能数
		int totalSeatNum = 7;//テーブルの数

		if (Holiday.checkHoliday(dateTime.substring(0, 10))) {// 定休日かチェック
			num = 0;
		} else {
			InitialContext ic=null;
			DataSource ds=null;
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql=null;

			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();

				if (reserveCharterChk(dateTime)) {// 貸し切りがあるかチェック
					num = 0;
				} else {
					sql = "SELECT COUNT(*) cnt FROM table_loc WHERE (table_id NOT IN "
							+ "(SELECT table_id FROM reserve INNER JOIN table_loc USING (table_id) "
							+ "WHERE DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
							+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?)) "
							+ "AND max_capacity >= ?";
					pst = con.prepareStatement(sql);
					// dateTime = sdFormat.format(calendar.getTime());
					pst.setString(1, dateTime);
					pst.setString(2, dateTime);
					int capacity = charter == 1 ? 0 : person; // 貸切の場合はmax_capacityは無視する
					pst.setInt(3, capacity);
					//System.out.println("<<<getReservableNum>>>" + pst.toString());
					rs = pst.executeQuery();


					if (rs.next()) {
						num = rs.getInt("cnt");// 空いている席数
					}

					if (charter == 1 && num < totalSeatNum) {
						num = 0;
					}
				}
			} catch (Exception e) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pst != null)
						pst.close();
					if (con != null)
						con.close();
				} catch (Exception e2) {
					throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
				}
			}
		}

		return num;

	}

	// 指定日の予約可能な件数をリストで返す
	public static ArrayList<Integer> getReservableNumList(String dateStr, int person, int charter) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Integer> numl = new ArrayList<Integer>();
		int totalSeatNum = 7;
		int num = 0;// ある時間の予約可能件数を入れる

		try {
			// String day = dateStr.substring(0, 10);
			String iniStr = dateStr + " 17:00:00";
			String endStr = dateStr + " 21:00:00";

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date iniDate = sdFormat.parse(iniStr);
			// Date endDate = sdFormat.parse(endStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(iniDate);
			String dateTime = "";

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			while (true) {

				dateTime = sdFormat.format(calendar.getTime());

				num = getReservableNum(dateTime, person, charter);

				if (charter == 1) {
					if (num == totalSeatNum) {
						numl.add(1);
					} else {
						numl.add(0);
					}
				} else {
					numl.add(num);
				}

				calendar.add(Calendar.MINUTE, 15);
				if (dateTime.equals(endStr)) {
					break;
				}
			}
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return numl;
	}

	// 指定予約IDを除いた指定時間の予約可能件数を返す
	public static int getUpdateReservableNum(int rsvId, String dateTime, int person, int charter) throws IdealException {
		int num = 0;// 予約可能数

		if (Holiday.checkHoliday(dateTime.substring(0, 10))) {// 定休日かチェック
			num = 0;
		} else {
			InitialContext ic=null;
			DataSource ds=null;
			Connection con=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql=null;

			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();

				if (reserveCharterChk(dateTime)) {// 貸し切りがあるかチェック
					num = 0;
				} else {
					sql = "SELECT COUNT(*) cnt FROM table_loc WHERE (table_id NOT IN "
							+ "(SELECT table_id FROM reserve INNER JOIN table_loc USING (table_id) "
							+ "WHERE rsv_id != ? AND DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
							+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?)) "
							+ "AND max_capacity >= ?";
					pst = con.prepareStatement(sql);
					// dateTime = sdFormat.format(calendar.getTime());
					pst.setInt(1, rsvId);
					pst.setString(2, dateTime);
					pst.setString(3, dateTime);
					int capacity = charter == 1 ? 0 : person; // 貸切の場合はmax_capacityは無視する
					pst.setInt(4, capacity);
					//System.out.println("<<<getUpdateReservableNum>>>" + pst.toString());
					rs = pst.executeQuery();

					if (rs.next()) {
						num = rs.getInt("cnt");// 空いている席数
					}
				}
			} catch (Exception e) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pst != null)
						pst.close();
					if (con != null)
						con.close();
				} catch (Exception e2) {
					throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
				}
			}
		}

		return num;

	}

	// 指定予約IDを除いた指定日の予約可能な件数をリストで返す
	public static ArrayList<Integer> getUpdateReservableNumList(int rsvId, String dateStr, int person, int charter) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Integer> numl = new ArrayList<Integer>();
		int totalSeatNum = 7;
		int num;

		try {
			// String day = dateStr.substring(0, 10);
			String iniStr = dateStr + " 17:00:00";
			String endStr = dateStr + " 21:00:00";

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date iniDate = sdFormat.parse(iniStr);
			// Date endDate = sdFormat.parse(endStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(iniDate);
			String dateTime = "";

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			while (true) {

				dateTime = sdFormat.format(calendar.getTime());

				num = getUpdateReservableNum(rsvId, dateTime, person, charter);

				if (charter == 1) {
					if (num == totalSeatNum) {
						numl.add(1);
					} else {
						numl.add(0);
					}
				} else {
					numl.add(num);
				}

				calendar.add(Calendar.MINUTE, 15);
				if (dateTime.equals(endStr)) {
					break;
				}
			}

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return numl;
	}

	// 指定日一日の予約状況を数値で返す
	public static int getReservationStatus(String date) throws IdealException {
		int status = 0;// 予約状況を表す数値（0：空き無または過去日 1:4名までなら予約可 2:6名予約可 3:貸切も可）
		int num;

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String iniStr;
		String endStr;

		//System.out.println("<<<iniDate01>>>" + date);

		if (Holiday.checkHoliday(date)) {// まずは定休日かチェック

			status = 0;

		} else {

			try {

				iniStr = date + " 17:00:00";
				endStr = date + " 21:00:00";
				Date iniDateTime = sdFormat.parse(iniStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(iniDateTime);
				String dateTime = "";

				while (true) {
					SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
			        Calendar nowCal= Calendar.getInstance();
			        String nowDate = dtf.format(nowCal.getTime());

			        dateTime = sdFormat.format(calendar.getTime());

			        if(dateTime.substring(0, 10).compareTo(nowDate) < 0){//過去日なら
						status = 0;
						break;
			        }
					// 	貸し切りできるか
					num = getReservableNum(dateTime, 2,1);
					//System.out.println("貸切可能数 :: " + num);
					if (num > 0) { // 貸し切り可能な時間があればbreak
						status = 3;
						break;
					}
					if (status < 1) {
						// 	4人の予約ができる席があるか
						num = getReservableNum(dateTime, 4, 0);
						if (num > 0) {
							status = 1;
						}
					}
					if (status < 2){
						// 	6人の予約ができる席があるか
						num = getReservableNum(dateTime, 6, 0);
						if (num > 0) { // 6人予約が可能な席があれば
							status = 2;
						}
					}
					calendar.add(Calendar.MINUTE, 15);
					if (dateTime.equals(endStr)) {
						break;
					}
				}

			} catch (Exception e) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}
		//System.out.println("<<<getReservationStatus>>>" + status);
		return status;
	}

	// 指定月の予約状況をHashMapで返す
	public static HashMap<String, Integer> getMonthlyReservationStatus(String date) throws IdealException {

		HashMap<String, Integer> statusMap = new HashMap<String, Integer>();
		int status = 0;

		try {
			String iniDate = date;// yyyy-MM-dd
			String iniY = iniDate.substring(0, 4);// yyyy
			String iniM = iniDate.substring(5, 7);// MM
			String iniD = iniDate.substring(8, 10);// dd

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(iniY),Integer.parseInt(iniM)-1,Integer.parseInt(iniD));

			int lastDay = calendar.getActualMaximum(Calendar.DATE);

			for (int i = 1; i <= lastDay; i++) {

				String dateTime = sdFormat.format(calendar.getTime());
				status = getReservationStatus(dateTime);
				//System.out.println("<<<status>>>" + i +"日 :: "+ status);
				statusMap.put(dateTime, status);

				calendar.add(Calendar.DATE, 1);

			}
		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}

		return statusMap;
	}

	public static boolean reserveCharterChk(String dateTime) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		boolean ret = false; //貸切があればtrue

		try {

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			// 貸し切りがあるかチェック
			sql = "SELECT COUNT(*) cnt FROM reserve "
					+ "WHERE DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
					+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ? "
					+ "AND charter = 1";
			pst = con.prepareStatement(sql);
			pst.setString(1, dateTime);
			pst.setString(2, dateTime);
			//System.out.println("<<<reserveCharterChk01>>>" + pst.toString());
			rs = pst.executeQuery();
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt >= 1) {
				ret = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {

			}
		}

		return ret;

	}

	public static boolean reserveCharterChk(String dateTime, int rsvId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		boolean ret = false; //貸切があればtrue

		try {

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT COUNT(*) cnt FROM reserve "
					+ "WHERE rsv_id != ? AND DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
					+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ? "
					+ "AND charter = 1";
			pst = con.prepareStatement(sql);
			pst.setInt(1, rsvId);
			pst.setString(2, dateTime);
			pst.setString(3, dateTime);
			//System.out.println("<<<reserveCharterChk02>>>" + pst.toString());
			rs = pst.executeQuery();
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt >= 1) {
				ret = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {

			}
		}

		return ret;

	}

	public static void reserveCourseChk(int cId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve INNER JOIN user1 USING (usr_id) "
					+ "INNER JOIN table_loc USING (table_id) "
					+ "WHERE c_id = ? "
					+ "AND DATE_FORMAT(rsv_date, '%Y-%m-%d') > DATE_FORMAT(now(), '%Y-%m-%d')";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cId);
			rs = pst.executeQuery();

			if (rs.next()) {
				throw new IdealException(IdealException.ERR_NO_NOT_RESERV_DELETE);
			}

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}
	}

	public static TableLoc insertChk(String dateTime, int personNum, int charter) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		TableLoc tl = null;


		int cnt = 0;
		try {
			//ic = new InitialContext();
			//ds = (DataSource) ic.lookup("java:comp/env/mysql");
			//con = ds.getConnection();
			if (reserveCharterChk(dateTime)) {// まず予約時間に貸切がないかチェック
				throw new IdealException(IdealException.ERR_NO_NOT_VACANCY_EXCEPTION);
			}

			if (charter == 0) { // 貸切しない場合、空きがあるかチェック
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				sql = "SELECT * FROM table_loc WHERE max_capacity >= ? AND "
						+ "table_id NOT IN "
						+ "(SELECT table_id FROM reserve INNER JOIN table_loc USING (table_id) "
						+ "WHERE DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
						+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?) "
						+ "ORDER BY table_id";
				pst = con.prepareStatement(sql);
				pst.setInt(1, personNum);
				pst.setString(2, dateTime);
				pst.setString(3, dateTime);
				//System.out.println("<<<table_loc>>>" + pst.toString());
				rs = pst.executeQuery();

				if (rs.next()) {
					tl = new TableLoc();
					tl.setTable_id(rs.getInt("table_id"));
					tl.setTableName(rs.getString("table_name"));
					tl.setMaxCapacity(Integer.parseInt(rs.getString("max_capacity")));
				}
			} else { // 貸し切りする場合、空きがあるかチェック
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				sql = "SELECT COUNT(*) cnt FROM reserve "
						+ "WHERE DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
						+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?";
				pst = con.prepareStatement(sql);
				// pst.setInt(1, personNum);
				pst.setString(1, dateTime);
				pst.setString(2, dateTime);
				//System.out.println("<<<table_loc>>>" + pst.toString());
				rs = pst.executeQuery();

				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}
				if (cnt == 0) {
					sql = "SELECT * FROM table_loc";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						tl = new TableLoc();
						tl.setTable_id(rs.getInt("table_id"));
						tl.setTableName(rs.getString("table_name"));
						tl.setMaxCapacity(Integer.parseInt(rs.getString("max_capacity")));
					}
				}
			}

		} catch (SQLException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return tl;
	}

	public static TableLoc updateChk(int rsvId, String dateTime, int personNum, int charter) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		TableLoc tl = null;


		int cnt = 0;
		try {
			//ic = new InitialContext();
			//ds = (DataSource) ic.lookup("java:comp/env/mysql");
			//con = ds.getConnection();
			if (reserveCharterChk(dateTime, rsvId)) {// まず予約日に貸切がないかチェック
				throw new IdealException(IdealException.ERR_NO_NOT_VACANCY_EXCEPTION);
			}

			if (charter == 0) { // 貸切しない場合、空きがあるかチェック
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				sql = "SELECT * FROM table_loc WHERE max_capacity >= ? AND "
						+ "table_id NOT IN "
						+ "(SELECT table_id FROM reserve INNER JOIN table_loc USING (table_id) "
						+ "WHERE rsv_id != ? AND DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
						+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?) "
						+ "ORDER BY table_id";
				pst = con.prepareStatement(sql);
				pst.setInt(1, personNum);
				pst.setInt(2, rsvId);
				pst.setString(3, dateTime);
				pst.setString(4, dateTime);
				//System.out.println("<<<table_loc>>>" + pst.toString());
				rs = pst.executeQuery();

				if (rs.next()) {
					tl = new TableLoc();
					tl.setTable_id(rs.getInt("table_id"));
					tl.setTableName(rs.getString("table_name"));
					tl.setMaxCapacity(Integer.parseInt(rs.getString("max_capacity")));
				}
			} else { // 貸し切りする場合、空きがあるかチェック
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				sql = "SELECT COUNT(*) cnt FROM reserve "
						+ "WHERE rsv_id != ? AND DATE_ADD(rsv_date, INTERVAL 179 MINUTE) >= ? "
						+ "AND DATE_ADD(rsv_date, INTERVAL -179 MINUTE) <= ?";
				pst = con.prepareStatement(sql);
				// pst.setInt(1, personNum);
				pst.setInt(1, rsvId);
				pst.setString(2, dateTime);
				pst.setString(3, dateTime);
				//System.out.println("<<<table_loc>>>" + pst.toString());
				rs = pst.executeQuery();

				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}
				if (cnt == 0) {
					sql = "SELECT * FROM table_loc";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						tl = new TableLoc();
						tl.setTable_id(rs.getInt("table_id"));
						tl.setTableName(rs.getString("table_name"));
						tl.setMaxCapacity(Integer.parseInt(rs.getString("max_capacity")));
					}
				}
			}

		} catch (SQLException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return tl;
	}

	// 席のリストを返す
	public static ArrayList<TableLoc> getTableLoc() throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		TableLoc tl = null;
		ArrayList<TableLoc> tlList = new ArrayList<TableLoc>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
				//ic = new InitialContext();
				//ds = (DataSource) ic.lookup("java:comp/env/mysql");
				//con = ds.getConnection();
				sql = "SELECT * FROM table_loc ORDER BY table_id";
				pst = con.prepareStatement(sql);
				//System.out.println("<<<getTableLoc>>>" + pst.toString());
				rs = pst.executeQuery();

				while (rs.next()) {
					tl = new TableLoc();
					tl.setTable_id(rs.getInt("table_id"));
					tl.setTableName(rs.getString("table_name"));
					tl.setMaxCapacity(Integer.parseInt(rs.getString("max_capacity")));
					tlList.add(tl);
				}

		} catch (SQLException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return tlList;
	}

	public static Reserve insert(Reserve reserve) throws IdealException {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		DecimalFormat df = new DecimalFormat("00");

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "INSERT INTO reserve(usr_id, rsv_date, person, table_id, c_id, charter, tel, mail, exp)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, reserve.getUsrId());
			String date = reserve.getRsvYy() + "-" + df.format(reserve.getRsvMm()) + "-" + df.format(reserve.getRsvDd()) + " " + df.format(reserve.getRsvHh()) + ":" + df.format(reserve.getRsvMi()) + ":00";
			pst.setString(2, date);
			pst.setInt(3, reserve.getPerson());
			pst.setInt(4, reserve.getTableId());
			pst.setInt(5, reserve.getCourseId());
			pst.setInt(6, reserve.getCharter());
			pst.setInt(7, reserve.getTel());
			pst.setInt(8, reserve.getMail());
			pst.setString(9, reserve.getExp());
			//System.out.println("<<<Reserve insert>>>" + pst.toString());
			pst.executeUpdate();

			ResultSet res = pst.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
			if (res.next()) {
				//System.out.println("<<<check>>>" + res.getInt("LAST"));
				reserve.setRsvId(res.getInt("LAST"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return reserve;
	}

	public static Reserve update(Reserve reserve) throws IdealException {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		DecimalFormat df = new DecimalFormat("00");

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "UPDATE reserve SET rsv_date = ?, person = ?, table_id = ?, c_id = ?, charter = ?, mail = ?, tel = ?, exp = ? WHERE rsv_id = ?";
			pst = con.prepareStatement(sql);
			//pst.setInt(1, reserve.getUsrId());
			String date = reserve.getRsvYy() + "-" + df.format(reserve.getRsvMm()) + "-" + df.format(reserve.getRsvDd()) + " " + df.format(reserve.getRsvHh()) + ":" + df.format(reserve.getRsvMi()) + ":00";
			pst.setString(1, date);
			pst.setInt(2, reserve.getPerson());
			pst.setInt(3, reserve.getTableId());
			pst.setInt(4, reserve.getCourseId());
			pst.setInt(5, reserve.getCharter());
			pst.setInt(6, reserve.getTel());
			pst.setInt(7, reserve.getMail());
			pst.setString(8, reserve.getExp());
			pst.setInt(9, reserve.getRsvId());
			//System.out.println("<<<update>>>" + pst.toString());
			pst.executeUpdate();

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

		return reserve;
	}

	public static void delete(Reserve reserve) throws IdealException {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "DELETE FROM reserve WHERE rsv_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, reserve.getRsvId());
			pst.executeUpdate();

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}
	}

	public int getRsvId() {
		return rsvId;
	}

	public int getUsrId() {
		return usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	/**
	 * usrPhoneを取得します。
	 * @return usrPhone
	 */
	public String getUsrPhone() {
	    return usrPhone;
	}

	/**
	 * usrPhoneを設定します。
	 * @param usrPhone usrPhone
	 */
	public void setUsrPhone(String usrPhone) {
	    this.usrPhone = usrPhone;
	}

	/**
	 * usrMailを取得します。
	 * @return usrMail
	 */
	public String getUsrMail() {
	    return usrMail;
	}

	/**
	 * usrMailを設定します。
	 * @param usrMail usrMail
	 */
	public void setUsrMail(String usrMail) {
	    this.usrMail = usrMail;
	}

	/**
	 * usrGenderIdを取得します。
	 * @return usrGenderId
	 */
	public int getUsrGenderId() {
	    return usrGenderId;
	}

	/**
	 * usrGenderIdを設定します。
	 * @param usrGenderId usrGenderId
	 */
	public void setUsrGenderId(int usrGenderId) {
	    this.usrGenderId = usrGenderId;
	}

	public int getRsvYy() {
		return rsvYy;
	}

	public int getRsvMm() {
		return rsvMm;
	}

	public int getRsvDd() {
		return rsvDd;
	}

	public int getRsvHh() {
		return rsvHh;
	}

	public int getRsvMi() {
		return rsvMi;
	}

	public int getPerson() {
		return person;
	}

	public int getTableId() {
		return tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getAppDate() {
		return appDate;
	}

	public int getAppYy() {
		return appYy;
	}

	public int getAppMm() {
		return appMm;
	}

	public int getAppDd() {
		return appDd;
	}

	public int getAppHh() {
		return appHh;
	}

	public int getAppMi() {
		return appMi;
	}

	public int getCharter() {
		return charter;
	}

	public String getExp() {
		return exp;
	}

	public int getTel() {
		return tel;
	}

	public int getMail() {
		return mail;
	}

	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public void setRsvYy(int rsvYy) {
		this.rsvYy = rsvYy;
	}

	public void setRsvMm(int rsvMm) {
		this.rsvMm = rsvMm;
	}

	public void setRsvDd(int rsvDd) {
		this.rsvDd = rsvDd;
	}

	public void setRsvHh(int rsvHh) {
		this.rsvHh = rsvHh;
	}

	public void setRsvMi(int rsvMi) {
		this.rsvMi = rsvMi;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public void setAppYy(int appYy) {
		this.appYy = appYy;
	}

	public void setAppMm(int appMm) {
		this.appMm = appMm;
	}

	public void setAppDd(int appDd) {
		this.appDd = appDd;
	}

	public void setAppHh(int appHh) {
		this.appHh = appHh;
	}

	public void setAppMi(int appMi) {
		this.appMi = appMi;
	}

	public void setCharter(int charter) {
		this.charter = charter;
	}

	public void setExp(String exp) {
		if (exp != null) {
			exp = exp.replace("<", "&lt;");
			exp = exp.replace(">", "&gt;");
			exp = exp.replace("\"", "&quot;");
		}
		this.exp = exp;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public void setMail(int mail) {
		this.mail = mail;
	}

	public int getCoursePrice() {
	    return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
	    this.coursePrice = coursePrice;
	}

	public int getTotalPrice() {
	    return totalPrice;
	}

	public void setTotalPrice(int price) {
		int totalPrice = 0;
		if(this.getCharter() == 1){ // 貸切の場合
		    totalPrice = price * this.getPerson() + CHARTER_FEE ;
		}else{
		    totalPrice = price * this.getPerson();
		}
	    this.totalPrice = totalPrice;
	}

}
