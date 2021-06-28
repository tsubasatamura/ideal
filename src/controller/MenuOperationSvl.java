package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Admin;
import model.IdealException;
import model.Menu;

/**
 * Servlet implementation class MenuOperationSvl
 */
@WebServlet("/MenuOperationSvl")
@MultipartConfig(
maxFileSize=5000000,
maxRequestSize=5000000
)
public class MenuOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int INSERT=11;
	public static final int UPDATE=12;
	public static final int DELETE=13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuOperationSvl() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		RequestDispatcher rd=null;
		int mode=Integer.parseInt(request.getParameter("mode"));
		Admin admin=(Admin)request.getAttribute("adminInfo");
		if(session==null){
			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}

		int menuId;
		int orderFlg;
		int price;
		int typeID;

		if(request.getParameter("menuID")==null){
			menuId=0;
		}else{
		menuId=Integer.parseInt(request.getParameter("menuID"));
		}
		String menuName=request.getParameter("menuName");
		String detail=request.getParameter("detail");
		try{
		orderFlg=Integer.parseInt(request.getParameter("orderFlg"));
		}catch(NumberFormatException | NullPointerException e){
			orderFlg = 0;
		}
		try{
		price=Integer.parseInt(request.getParameter("price"));
		}catch(NumberFormatException | NullPointerException e){
			price = 0;
		}
		try{
		typeID = Integer.parseInt(request.getParameter("typeID"));
		}catch(NumberFormatException | NullPointerException e){
			typeID = 0;
		}
		Menu menu=new Menu();
		menu.setMenuId(menuId);
		menu.setMenuName(menuName);
		menu.setDetail(detail);
		menu.setOrderFlg(orderFlg);
		menu.setPrice(price);
		menu.setTypeId(typeID);


		switch(mode){
		case 11:

			try{
				Part part=request.getPart("menuPhotoName");
			    String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();

			    if(filename.equals("")){
			    	filename=null;
			    	menu.setMenuPhotoName(filename);
			    }else{
			    	String path=getServletContext().getRealPath("/upload");
			    	part.write(path+File.separator+filename);
			    	menu.setMenuPhotoName(filename);
			    }
					Menu.updateMenu(menu, mode);


			}catch(IdealException e){
				e.printStackTrace();
				IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				request.setAttribute("oneMenu", menu);
				rd=request.getRequestDispatcher("MenuInsertSvl");
				rd.forward(request, response);
			}
			request.setAttribute("typeID", typeID);
			rd=request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);
			break;

		case 12:
			try{
				Part part=request.getPart("menuPhotoName");
			    String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();

			    if(filename.equals("")){
			    	filename=null;
			    	menu.setMenuPhotoName(filename);
			    }else{
			    	String path=getServletContext().getRealPath("/upload");
			    	part.write(path+File.separator+filename);
			    	menu.setMenuPhotoName(filename);
			    }
				Menu.updateMenu(menu, mode);
			}catch(IdealException e){
				e.printStackTrace();
				IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				request.setAttribute("oneMenu", menu);
				rd=request.getRequestDispatcher("MenuUpdateSvl");
				rd.forward(request, response);
			}
			request.setAttribute("typeID", typeID);
			rd=request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);
			break;

		case 13:
			try{
				Menu.updateMenu(menu, mode);
			}catch(IdealException e){
				e.printStackTrace();
				IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				request.setAttribute("msg", ie);
				request.setAttribute("oneMenu", menu);
				rd=request.getRequestDispatcher("MenuDeleteSvl");
				rd.forward(request, response);
			}
			request.setAttribute("typeID", typeID);
			rd=request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);
				break;
		}
	}
}