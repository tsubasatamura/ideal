package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

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
import model.Course;
import model.Coursectl;
import model.IdealException;

/**
 * Servlet implementation class CourseOperationSvl
 */
@WebServlet("/CourseOperationSvl")
@MultipartConfig(
maxFileSize=5000000,
maxRequestSize=5000000
)
public class CourseOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int INSERT=11;
	public static final int UPDATE=12;
	public static final int DELETE=13;

	public static final int[] COURSE_MENU_TYPE_ID={200,210,220,300,310,400};

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseOperationSvl() {
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

		RequestDispatcher rd=null;


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();

		Admin admin=(Admin)session.getAttribute("adminInfo");

		int typeID=Integer.parseInt(request.getParameter("typeID"));
		ArrayList<Coursectl>cal=new ArrayList<Coursectl>();


		int[] dishes=new int[6];

		if(request.getParameter("appetizerID")!=null){
		if(!request.getParameter("appetizerID").equals("")){
		dishes[0]=Integer.parseInt(request.getParameter("appetizerID"));
		}else {
			dishes[0]=0;
		}
		if(!request.getParameter("soupID").equals("")){
		dishes[1]=Integer.parseInt(request.getParameter("soupID"));
		}else {
			dishes[1]=0;
		}
		if(!request.getParameter("pastaID").equals("")){
		dishes[2]=Integer.parseInt(request.getParameter("pastaID"));
		}else {
			dishes[2]=0;
		}
		if(!request.getParameter("meatID").equals("")){
		dishes[3]=Integer.parseInt(request.getParameter("meatID"));
		}else {
			dishes[3]=0;
		}
		if(!request.getParameter("fishID").equals("")){
		dishes[4]=Integer.parseInt(request.getParameter("fishID"));
		}else {
			dishes[4]=0;
		}
		if(!request.getParameter("dessertID").equals("")){
		dishes[5]=Integer.parseInt(request.getParameter("dessertID"));
		}else {
			dishes[5]=0;
		}

		for(int i=0;i<dishes.length;i++){
			if(dishes[i]!=0){

			Coursectl ctl=new Coursectl();
			ctl.setM_id(dishes[i]);
			cal.add(ctl);
			}

		}
		}

		if(admin==null){

			rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		try{

			Course crs = new Course();
			int mode;
			mode=Integer.parseInt(request.getParameter("mode"));
			crs.setCourseName(request.getParameter("courseName"));


			if(request.getParameter("courseID")!=null){
			crs.setCourseId(Integer.parseInt(request.getParameter("courseID")));
			}

			if(request.getParameter("price")!=null){
			crs.setPrice(Integer.parseInt(request.getParameter("price")));
			crs.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
			crs.setDetail(request.getParameter("detail"));
			crs.setTypeId(typeID);
			}



			if((Object)mode instanceof String){

				mode=100;
			}else{

				if(mode == INSERT){
					Part part=request.getPart("coursePhotoName");
					String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();

					if(filename.equals("")){
						filename=null;
					crs.setCoursePhotoName(filename);
					}else{
					String path=getServletContext().getRealPath("/upload");
					part.write(path+File.separator+filename);
					crs.setCoursePhotoName(filename);
					}
					Course.updateCourse(crs,mode,cal);


					typeID=Integer.parseInt(request.getParameter("typeID"));
					rd=request.getRequestDispatcher("MenuMaintenanceSvl");
					rd.forward(request, response);
				return;

				}else if(mode == DELETE){
				Course.updateCourse(crs,mode,cal);


					typeID=Integer.parseInt(request.getParameter("typeID"));
					request.setAttribute("typeID",typeID);
					rd=request.getRequestDispatcher("MenuMaintenanceSvl");
					rd.forward(request, response);

				}else if(mode == UPDATE){
					Part part=request.getPart("coursePhotoName");
					String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();

					if(filename.equals("")){
						filename=null;
					crs.setCoursePhotoName(filename);
					}else{
					String path=getServletContext().getRealPath("/upload");
					part.write(path+File.separator+filename);
					crs.setCoursePhotoName(filename);
					}

					Course.updateCourse(crs,mode,cal);


					typeID=Integer.parseInt(request.getParameter("typeID"));
					rd=request.getRequestDispatcher("MenuMaintenanceSvl");
					rd.forward(request, response);
				return;

				}else{

				}

			}



        }catch (IdealException e) {
	// TODO 自動生成された catch ブロック
	e.printStackTrace();

	  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

	  request.setAttribute("msg", ie);
	  request.setAttribute("adminInfo", admin);
	  rd=request.getRequestDispatcher("MenuMaintenanceSvl");
	  rd.forward(request, response);

	}

}


	}

