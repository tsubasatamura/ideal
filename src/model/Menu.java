package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import controller.MenuOperationSvl;


public class Menu implements Serializable{

	private static final long serialVersionUID=1L;

	private int menuId;
	private String menuName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;
	private String menuPhotoName;

	public Menu() {
		super();
	}
	public int getMenuId() {
	    return menuId;
	}
	public String getMenuName() {
	    return menuName;
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
	public String getMenuPhotoName() {
	    return menuPhotoName;
	}

	public void setMenuId(int menuId) {
	    this.menuId = menuId;
	}
	public void setMenuName(String menuName) {
	    this.menuName = menuName;
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
	public void setMenuPhotoName(String menuPhotoName) {
	    this.menuPhotoName = menuPhotoName;
	}


	public static Menu getOneMenu(int menuID, int typeID) throws IdealException {

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;

		Menu m=null;
		String sql="";
		String table="menu";
		String keyColumn="m_id";
		String name="m_Name";

		if(typeID == 100){
			table= "course";
			keyColumn= "c_id";
			name= "c_name";
		}
		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();


			sql += "SELECT * FROM " + table;
			sql += " JOIN";
			sql += "   menutype";
			sql += "     USING(t_id)";
			sql += " WHERE ";
			sql +=        keyColumn + " = ?";
			sql += " ORDER BY " + keyColumn;

			pst=con.prepareStatement(sql);
			pst.setInt(1,menuID);
			rs=pst.executeQuery();

			if(rs.next()){
				m=new Menu();
				m.setMenuId(rs.getInt(keyColumn));
				m.setMenuName(rs.getString(name));
				m.setDetail(rs.getString("detail"));
				m.setOrderFlg(rs.getInt("orderFlg"));
				m.setPrice(rs.getInt("price"));
				m.setTypeId(rs.getInt("t_id"));
				m.setTypeName(rs.getString("t_name"));
				m.setMenuPhotoName(rs.getString("mp_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return m;
	}

	public static ArrayList<Menu>getMenuList() throws IdealException {
		ArrayList<Menu>al=new ArrayList<Menu>();

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;

		String sql="";
		String table="menu";

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();

			sql += "SELECT * FROM " + table;
			sql += " LEFT OUTER JOIN menutype USING(t_id) WHERE ";
			sql +=        "orderFlg = 1";


			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();

			while(rs.next()){
				Menu m=new Menu();
				m.setTypeId(rs.getInt(1));
				m.setMenuId(rs.getInt(2));
				m.setMenuName(rs.getString(3));
				m.setDetail(rs.getString(4));
				m.setOrderFlg(rs.getInt(5));
				m.setPrice(rs.getInt(6));
				m.setTypeName(rs.getString(8));
				m.setMenuPhotoName(rs.getString("mp_name"));
				al.add(m);

			}
		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}

	public static ArrayList<Menu>getMenu(int typeId) throws IdealException{
		ArrayList<Menu>al=new ArrayList<Menu>();

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;

		String sql="";
		String table="menu";
		if(typeId==100)table="course";
		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();

			sql += "SELECT * FROM " + table;
			sql += " JOIN";
			sql += "   menutype";
			sql += " USING(t_id)";
			sql += " WHERE ";
			sql +=        table + ".t_id = ?";
			sql += " ORDER BY 2";


			pst=con.prepareStatement(sql);
			pst.setInt(1,typeId);
			rs=pst.executeQuery();

			while(rs.next()){
				Menu m=new Menu();
				m.setMenuId(rs.getInt(2));
				m.setMenuName(rs.getString(3));
				m.setDetail(rs.getString(4));
				m.setOrderFlg(rs.getInt(5));
				m.setPrice(rs.getInt(6));
				m.setMenuPhotoName(rs.getString(7));
				m.setTypeName(rs.getString(8));
				al.add(m);

			}
		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{
				if(rs !=null)rs.close();
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return al;
	}

	public static int updateMenu(Menu m,int mode) throws IdealException{

		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		PreparedStatement pst=null;

		String sql="";
		String table="";
		String keyColumn="";
		String name="";
		int num=0;
		if(m.getTypeId ()==100){
			table="course";
			keyColumn="c_id";
			name="c_name";
		}else{
			table="menu";
			keyColumn="m_id";
			name="m_name";
		}

		try{
			ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/mysql");
			con=ds.getConnection();
			if(mode==MenuOperationSvl.INSERT){
				sql += "INSERT INTO ";
				sql +=      table;
				sql += " VALUES(";
				sql += " DEFAULT,?,?,?,?,?,?)";

			pst=con.prepareStatement(sql);
			pst.setString(1,m.getMenuName());
			pst.setString(2,m.getDetail());
			pst.setInt(3, m.getOrderFlg());
			pst.setInt(4, m.getPrice());
			pst.setInt(5, m.getTypeId());

			pst.setString(6, m.getMenuPhotoName());


		}else if(mode==MenuOperationSvl.UPDATE){
			sql += "UPDATE ";
			sql += table ;
			sql += " SET ";
			sql +=      name + " =?,";
			sql += " detail =?,";
			sql += " orderFlg =?,";
			sql += " price =?,";
			sql += " t_id =?,";
			sql += " mp_name =?";
			sql += " WHERE ";
			sql +=   keyColumn + " =?";

			pst=con.prepareStatement(sql);
			pst.setString(1, m.getMenuName());
			pst.setString(2, m.getDetail());
			pst.setInt(3, m.getOrderFlg());
			pst.setInt(4, m.getPrice());
			pst.setInt(5, m.getTypeId());
			pst.setString(6, m.getMenuPhotoName());
			pst.setInt(7, m.getMenuId());


		}else if(mode==MenuOperationSvl.DELETE){
			sql+="DELETE FROM ";
			sql+=table;
			sql+=" WHERE ";
			sql+= keyColumn + " =?";

			pst=con.prepareStatement(sql);
			pst.setInt(1, m.getMenuId());
		}
			num=pst.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}finally{
			try{

				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}


return num;

}

}