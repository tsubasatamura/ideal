package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Admin implements Serializable {

	private String admName;
	private String password;
	private String exp;

	public Admin(){
		super();
	}

	public String getAdmName() {
		return admName;
	}

	public String getPassword() {
		return password;
	}

	public String getExp() {
		return exp;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public static Admin login(String admName,String password){
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql=null;
		Admin ad=null;

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			sql="SELECT * FROM admin WHERE adm_name=? AND password=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,admName);
			pst.setString(2,password);
			rs=pst.executeQuery();
			ad=new Admin();

			if(rs.next()){
				ad.setAdmName(rs.getString("adm_name"));
				ad.setPassword(rs.getString("password"));
				ad.setExp(rs.getString("exp"));
			}else{
				ad=null;
			}

		}catch(Exception e){

		}finally{
			try{
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			}catch(Exception e){

			}
		}
		return ad;
	}
}