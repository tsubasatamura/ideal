package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private int newsId;
	private String newsTitle;
	private String newsText;
	private String newsTime;

	public News() {
		super();
	}

	public int getNewsId() {
		return newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public String getNewsText() {
		return newsText;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public void setNewsText(String newsText) {
		if(newsText!=null){
			newsText=newsText.replace("<", "&lt;");
			newsText=newsText.replace(">", "&gt;");
			newsText=newsText.replace("\"", "&quot;");
			newsText=newsText.replace(System.lineSeparator(), "<br/>");
			}
		this.newsText = newsText;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public static News getNews() throws IdealException {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		News n = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM news";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			n = new News();

			while (rs.next()) {
				n.setNewsId(rs.getInt("n_id"));
				n.setNewsTitle(rs.getString("title"));
				n.setNewsText(rs.getString("n_message"));
				n.setNewsTime(rs.getString("n_date"));
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
			} catch (Exception e) {

			}
		}
		return n;
	}

	public static News insertNews(String newsTitle, String newsText)
			throws IdealException {

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		News n = new News();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "INSERT INTO news(title,n_message) VALUES(?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, newsTitle);
			pst.setString(2, newsText);

			pst.executeUpdate();

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
		return n;
	}

	public static News update(News news) throws IdealException {
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

			int id = news.getNewsId();
			String title = news.getNewsTitle();
			String text = news.getNewsText();

			sql = "UPDATE news SET n_id=?, title=?, n_message=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, title);
			pst.setString(3, text);

			pst.executeUpdate();
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
		return news;
	}

	public static void delete(int newsId) throws IdealException {
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

			sql = "DELETE FROM news WHERE n_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, newsId);

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
			} catch (Exception e) {

			}
		}

	}

	public static ArrayList<News> getAllNews() {
		ArrayList<News> al = new ArrayList<News>();

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

			sql = "SELECT * FROM news";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				News n = new News();
				n.setNewsId(rs.getInt("n_id"));
				n.setNewsTitle(rs.getString("title"));
				n.setNewsText(rs.getString("n_message"));
				n.setNewsTime(rs.getString("n_date"));
				al.add(n);
			}
		} catch (Exception e) {

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
		return al;
	}
}