package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Contact implements Serializable{
	private static final long serialVersionUID=1L;
	private int con_id;
	private String message;
	private int usr_id;
	private String date;
	private int adminFlag;
	private String usr_name;

	public Contact(){
		super();
	}

	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		if(message!=null){
			message=message.replace("<", "&lt;");
			message=message.replace(">", "&gt;");
			message=message.replace(System.lineSeparator(), "<br/>");
		}
		this.message = message;
	}
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public static void insertContact(User user,String text) throws IdealException{
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

			int id=user.getUsr_id();


			sql="INSERT INTO contact(message,usr_id,adminFlag) VALUES(?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,text);
			pst.setInt(2,id);
			pst.setInt(3,0);

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

	public static void adminsertContact(int userID,String text) throws IdealException{
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

			int id=userID;


			sql="INSERT INTO contact(message,usr_id,adminFlag) VALUES(?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,text);
			pst.setInt(2,id);
			pst.setInt(3,1);

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

	public static ArrayList<Contact> userContactList(User user) throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Contact> al=new ArrayList<>();

		try{
		ic=new InitialContext();
		ds=(DataSource)ic.lookup("java:comp/env/mysql");
		con=ds.getConnection();

		int id=user.getUsr_id();
		st=con.createStatement();
		String sql="SELECT * FROM contact LEFT OUTER JOIN user1 USING(usr_id) WHERE usr_id="+id+" ORDER BY date DESC";
		st=con.createStatement();
		rs=st.executeQuery(sql);

		while(rs.next()){
			Contact cont=new Contact();
			cont.setCon_id(rs.getInt("con_id"));
			cont.setMessage(rs.getString("message"));
			cont.setUsr_id(rs.getInt("usr_id"));
			cont.setDate(rs.getString("date"));
			cont.setAdminFlag(rs.getInt("adminFlag"));
			cont.setUsr_name(rs.getString("usr_name"));
			al.add(cont);
		}

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}
		return al;
	}

	public static ArrayList<Contact> adminContactList() throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Contact> al=new ArrayList<>();

		try{
		ic=new InitialContext();
		ds=(DataSource)ic.lookup("java:comp/env/mysql");
		con=ds.getConnection();

		st=con.createStatement();
		String sql="SELECT * FROM contact INNER JOIN(SELECT usr_id,MAX(date) AS saidai FROM contact GROUP BY usr_id) AS ta ON contact.usr_id=ta.usr_id AND contact.date=ta.saidai LEFT OUTER JOIN user1 ON contact.usr_id=user1.usr_id WHERE adminFlag=0 ORDER BY date DESC";
		st=con.createStatement();
		rs=st.executeQuery(sql);

		while(rs.next()){
			Contact cont=new Contact();
			cont.setCon_id(rs.getInt("con_id"));
			cont.setMessage(rs.getString("message"));
			cont.setUsr_id(rs.getInt("usr_id"));
			cont.setDate(rs.getString("date"));
			cont.setAdminFlag(rs.getInt("adminFlag"));
			cont.setUsr_name(rs.getString("usr_name"));
			al.add(cont);
		}

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}
		return al;
	}


}
