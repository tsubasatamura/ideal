package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import controller.CourseOperationSvl;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private int courseId;
	private String courseName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;
	private int menuId;
	private String menuName;
	private String coursePhotoName;







	public static final int[] COURSE_MENU_TYPE_ID = { 200, 210, 220, 300, 310, 400 };

	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDetail() {
		return detail;
	}

	public int getOrderFlg() {
		return orderFlg;
	}

	public int getPrice() {
		return price;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setOrderFlg(int orderFlg) {
		this.orderFlg = orderFlg;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getCoursePhotoName() {
		return coursePhotoName;
	}

	public void setCoursePhotoName(String coursePhotoName) {
		this.coursePhotoName = coursePhotoName;
	}

	public static Course getCourse(int courseId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Course crs = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM course WHERE c_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, courseId);
			rs = pst.executeQuery();

			if (rs.next()) {
				crs = new Course();
				crs.setCourseId(rs.getInt(courseId));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeId(rs.getInt("t_id"));
				crs.setCoursePhotoName(rs.getString("cp_name"));
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

		return crs;
	}

	public static ArrayList<Course> getOneCourse(int courseId) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Course> alCrs = new ArrayList<Course>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM course INNER JOIN coursectl USING (c_id) LEFT OUTER JOIN menu USING (m_id) WHERE c_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, courseId);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeId(rs.getInt("course.t_id"));
				crs.setTypeId(rs.getInt("menu.t_id"));
				crs.setMenuId(rs.getInt("m_id"));
				crs.setMenuName(rs.getString("m_Name"));
				crs.setCoursePhotoName(rs.getString("cp_name"));
				alCrs.add(crs);
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

		return alCrs;

	}

	public static ArrayList<Course> getCourseList() throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Course> alCrs = new ArrayList<Course>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM course INNER JOIN coursectl USING (c_id) LEFT OUTER JOIN menu USING (m_id) WHERE course.orderFlg = 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				// crs.setTypeId(rs.getInt("course.t_id"));
				crs.setTypeId(rs.getInt("menu.t_id"));
				crs.setMenuId(rs.getInt("m_id"));
				crs.setMenuName(rs.getString("m_Name"));
				crs.setCoursePhotoName(rs.getString("cp_name"));
				alCrs.add(crs);
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

		return alCrs;

	}

	public static ArrayList<Course> getOneCourseList() throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Course> alCrs = new ArrayList<Course>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM course WHERE orderFlg = 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeId(rs.getInt("t_id"));
				crs.setCoursePhotoName(rs.getString("cp_name"));
				alCrs.add(crs);
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

		return alCrs;

	}

	public static ArrayList<Course> getTypeCourseList(int t_id) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Course> alCrs = new ArrayList<Course>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM course LEFT OUTER JOIN menuType USING (t_id) ORDER BY c_id ASC";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseId(rs.getInt("c_id"));
				crs.setCourseName(rs.getString("c_name"));
				crs.setDetail(rs.getString("detail"));
				crs.setOrderFlg(rs.getInt("orderFlg"));
				crs.setPrice(rs.getInt("price"));
				crs.setTypeName(rs.getString("t_name"));
				crs.setCoursePhotoName(rs.getString("cp_name"));
				alCrs.add(crs);
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

		return alCrs;

	}

	// 未実装
	public static int updateCourse(Course c, int mode, ArrayList<Coursectl> coursectl) throws IdealException {
		CourseOperationSvl cal=new CourseOperationSvl();



		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;


		String table="";
		String keyColumn="";
		String name="";

		int num = 0;

		try {

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			if (mode == 11) { // 登録

				sql="INSERT INTO course (t_id,c_name,detail,orderFlg,price,cp_name) VALUES (?,?,?,?,?,?)";
				pst=con.prepareStatement(sql);

				pst.setString(2,c.getCourseName());
				pst.setString(3,c.getDetail());
				pst.setInt(4,c.getOrderFlg());
				pst.setInt(5,c.getPrice());
				pst.setInt(1, c.getTypeId());
				pst.setString(6,c.getCoursePhotoName());


				pst.executeUpdate();

				sql="SELECT LAST_INSERT_ID()";
				pst=con.prepareStatement(sql);
				rs=pst.executeQuery();

				if(rs.next()){
					int cid=rs.getInt("LAST_INSERT_ID()");

				for(int i=0 ; i < coursectl.size() ; i++){


				sql="INSERT INTO coursectl (c_id,m_id) VALUES (?,?)";

				pst=con.prepareStatement(sql);
				pst.setInt(1,cid);
				pst.setInt(2,coursectl.get(i).getM_id());


				pst.executeUpdate();
				}
				}

			} else if (mode == 12) { // 変更

				sql="UPDATE course SET t_id=?, c_name=?, detail=?, orderFlg=?, price=?, cp_name=? WHERE c_id=?";
				pst=con.prepareStatement(sql);
				pst.setInt(1,c.getTypeId());
				pst.setString(2,c.getCourseName());
				pst.setString(3,c.getDetail());
				pst.setInt(4,c.getOrderFlg());
				pst.setInt(5,c.getPrice());
				pst.setString(6,c.getCoursePhotoName());

				pst.setInt(7, c.getCourseId());
				pst.executeUpdate();

				sql="DELETE FROM  coursectl WHERE c_id=?";
				pst=con.prepareStatement(sql);
				pst.setInt(1,c.getCourseId());

				pst.executeUpdate();

				for(int i=0 ; i < coursectl.size() ; i++){
				sql="INSERT INTO coursectl (c_id,m_id) VALUES (?,?)";
				pst=con.prepareStatement(sql);

				pst.setInt(2,coursectl.get(i).getM_id());
				pst.setInt(1, c.getCourseId());
				pst.executeUpdate();
				}



			} else if (mode == 13) { // 抹消
				System.out.println(13);
				System.out.println(c.getCourseId());

				sql="DELETE FROM  coursectl WHERE c_id=?";
				pst=con.prepareStatement(sql);
				pst.setInt(1,c.getCourseId());

				pst.executeUpdate();

				sql="DELETE FROM  course WHERE c_id=?";
				pst=con.prepareStatement(sql);
				pst.setInt(1,c.getCourseId());

				pst.executeUpdate();

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

		return num;

	}



}
