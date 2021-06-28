package model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MenuType implements Serializable{
	private static final long serialVersionUID=1L;

	private int typeID;
	private String typeName;


	public MenuType(){
		super();
	}

	public int getTypeID() {
	    return typeID;
	}
	public String getTypeName() {
	    return typeName;
	}

	public void setTypeID(int typeID) {
	    this.typeID = typeID;
	}
	public void setTypeName(String typeName) {
	    this.typeName = typeName;
	}

	public static ArrayList<MenuType> getAllType() throws IdealException{
		ArrayList<MenuType> al = new ArrayList<MenuType>();

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="";

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();

			sql += "SELECT * FROM menutype ORDER BY t_id";

			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				MenuType mt=new MenuType();
				mt.setTypeID(rs.getInt("t_id"));
				mt.setTypeName(rs.getString("t_name"));
				al.add(mt);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs != null)rs.close();
				if(pst != null)pst.close();
				if(con != null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}
}
