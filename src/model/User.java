package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class User implements Serializable{
	private static final long serialVersionUID=1L;
	private int usr_id;
	private String usr_name;
	private String password;
	private String postcode;
	private String address;
	private String phone;
	private String mail;
	private int gender_id;
	private String birthday;
	private String exp;


	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address!=null){
		address=address.replace("<", "&lt;");
		address=address.replace(">", "&gt;");
		address=address.replace("\"", "&quot;");
		}
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone!=null){
		phone=phone.replace("-", "");
		}
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getGender_id() {
		return gender_id;
	}
	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		if(birthday==""){
			this.birthday=null;
		}else{
		this.birthday = birthday;
		}
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public User(){
		super();
	}

	public static User login(String mail,String password)throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		User u=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			sql="SELECT * FROM user1 WHERE mail=? AND password=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,mail);
			pst.setString(2,password);
			rs=pst.executeQuery();
			u=new User();

			if(rs.next()){
				u.setUsr_id(rs.getInt("usr_id"));
				u.setUsr_name(rs.getString("usr_name"));
				u.setPassword(rs.getString("password"));
				u.setPostcode(rs.getString("postcode"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setMail(rs.getString("mail"));
				u.setGender_id(rs.getInt("gender_id"));
				u.setBirthday(rs.getString("birthday"));
				u.setExp(rs.getString("exp"));
			}else{
				u=null;
			}

		}catch(Exception e){
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

		}finally{
			try{
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}
		return u;
	}

	public static User getUser(String mail) throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		User u=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			sql="SELECT * FROM user1 WHERE mail=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,mail);
			rs=pst.executeQuery();
			u=new User();

			if(rs.next()){
				u.setUsr_id(rs.getInt("usr_id"));
				u.setUsr_name(rs.getString("usr_name"));
				u.setPassword(rs.getString("password"));
				u.setPostcode(rs.getString("postcode"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setMail(rs.getString("mail"));
				u.setGender_id(rs.getInt("gender_id"));
				u.setBirthday(rs.getString("birthday"));
				u.setExp(rs.getString("exp"));
			}else{
				u=null;
			}

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
		return u;
	}

	public static User insert(User user) throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		Statement st=null;
		ResultSet rs=null;
		String sql=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();

			String name=user.getUsr_name();
			String pass=user.getPassword();
			String post=user.getPostcode();
			String add=user.getAddress();
			String pho=user.getPhone();
			String ma=user.getMail();
			int gender=user.getGender_id();
			String birth=user.getBirthday();
			String ex=user.getExp();



			sql="INSERT INTO user1(usr_name,password,postcode,address,phone,mail,gender_id,birthday,exp) VALUES(?,?,?,?,?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2,pass);
			pst.setString(3,post);
			pst.setString(4,add);
			pst.setString(5,pho);
			pst.setString(6,ma);
			pst.setInt(7,gender);
			pst.setString(8,birth);
			pst.setString(9,ex);

			pst.executeUpdate();

			sql="SELECT LAST_INSERT_ID()";

			st=con.createStatement();
			rs=st.executeQuery(sql);


			if(rs.next()){
				user.setUsr_id(rs.getInt("LAST_INSERT_ID()"));

			}

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
		return user;
	}

	public static User update(User user) throws IdealException{
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
			//String name=user.getUsr_name();
			String pass=user.getPassword();
			String post=user.getPostcode();
			String add=user.getAddress();
			String pho=user.getPhone();
			String ma=user.getMail();
			//int gender=user.getGender_id();
			//String birth=user.getBirthday();
			//String ex=user.getExp();


			sql="UPDATE user1 SET password=?, postcode=?, address=?, phone=?, mail=? WHERE usr_id=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,pass);
			pst.setString(2,post);
			pst.setString(3,add);
			pst.setString(4,pho);
			pst.setString(5,ma);
			pst.setInt(6,id);

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
		return user;
	}

	public static void delete(User user) throws IdealException{
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

			sql="DELETE FROM  user1 WHERE usr_id=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,id);

			pst.executeUpdate();
		}catch(Exception e){
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

	public static ArrayList<User> getUserList() throws IdealException{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<User> al=new ArrayList<>();

		try{
		ic=new InitialContext();
		ds=(DataSource)ic.lookup("java:comp/env/mysql");
		con=ds.getConnection();

		st=con.createStatement();
		String sql="SELECT * FROM user1";
		st=con.createStatement();
		rs=st.executeQuery(sql);

		while(rs.next()){
			User user=new User();
			user.setUsr_id(rs.getInt("usr_id"));
			user.setUsr_name(rs.getString("usr_name"));
			user.setPostcode(rs.getString("postcode"));
			user.setAddress(rs.getString("address"));
			user.setPhone(rs.getString("phone"));
			user.setMail(rs.getString("mail"));
			user.setGender_id(rs.getInt("gender_id"));
			user.setBirthday(rs.getString("birthday"));
			al.add(user);
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

	public static boolean userCheck(User user){
		boolean check=false;

		try{
			if(Objects.equals(user.getUsr_name(),null)){
				check=true;
			}
		}catch(Exception e){
			check=true;
		}

		return check;
	}

	public static void deleteReserve(User user) throws IdealException{
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
			sql = "DELETE FROM reserve WHERE usr_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, user.getUsr_id());
			pst.executeUpdate();

		} catch (Exception e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pst != null)pst.close();
				if (con != null)con.close();
			} catch (Exception e) {
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

	}

}
