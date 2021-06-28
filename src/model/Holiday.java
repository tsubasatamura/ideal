package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Holiday implements Serializable{
	private int h_id;
	private String h_date;

	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public String getH_date() {
		return h_date;
	}
	public void setH_date(String h_date) {
		this.h_date = h_date;
	}

	public Holiday(){
		super();
	}

	public static ArrayList<Holiday> getHolidayList()throws IdealException{
		ArrayList<Holiday>al=new ArrayList<>();

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			String sql="SELECT * FROM holiday";

			st=con.createStatement();
			rs=st.executeQuery(sql);

			while(rs.next()){
				Holiday h=new Holiday();
				h.setH_id(rs.getInt(1));
				h.setH_date(rs.getString(2));
				al.add(h);
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(st !=null)st.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}

	public static void insertHoliday(String holiday) throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();



			sql="INSERT INTO holiday(h_date) VALUES(?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,holiday);

			pst.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}

	}

	public static void deleteHoliday(String holiday) throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();



			sql="DELETE FROM holiday WHERE h_date=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,holiday);

			pst.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}

	}

	public static boolean checkHoliday(String holiday)throws IdealException{
		boolean check=false;
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			String sql="SELECT * FROM holiday";

			st=con.createStatement();
			rs=st.executeQuery(sql);

			while(rs.next()){
				if(holiday.equals(rs.getString("h_date").substring(0, 10))){
				check=true;
				break;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(st !=null)st.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return check;
	}

	public static boolean checkReserve(String holiday)throws IdealException{
		boolean check=false;
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			String sql="SELECT * FROM reserve";

			st=con.createStatement();
			rs=st.executeQuery(sql);

			while(rs.next()){
				if(holiday.equals(rs.getString("rsv_date").substring(0, 10))){
				check=true;
				break;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(st !=null)st.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return check;
	}


}
