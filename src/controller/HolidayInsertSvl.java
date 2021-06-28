package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Holiday;
import model.IdealException;

/**
 * Servlet implementation class HolidayInsertSvl
 */
@WebServlet("/HolidayInsertSvl")
public class HolidayInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayInsertSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();



		Admin admin=(Admin)session.getAttribute("adminInfo");
		if(admin==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}else{
			String[] holiday=request.getParameterValues("holiday");
			String[] delete=request.getParameterValues("delete");
			ArrayList<String> al=new ArrayList<>();

			if(holiday==null){
				holiday=new String[0];
			}
			if(delete==null){
				delete=new String[0];
			}

			for(int i=0;i<delete.length;i++){
				boolean judge=true;
				for(int j=0;j<holiday.length;j++){
					if(delete[i].equals(holiday[j])){
						judge=false;
					}
				}
				if(judge){
					al.add(delete[i]);
				}
			}

			  try {
				 for(int i=0;i<al.size();i++){
						Holiday.deleteHoliday(al.get(i));
				 }

			      for (int i = 0 ; i < holiday.length ; i++){
			    	  if(!Holiday.checkHoliday(holiday[i])){
			    		  Holiday.insertHoliday(holiday[i]);
			    	  }
			      }
			  } catch (IdealException e) {
    			  e.printStackTrace();
    			  IdealException ie = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
    			  request.setAttribute("msg", ie);
    			  RequestDispatcher rd = request.getRequestDispatcher("adminHoliday.jsp");
    			  rd.forward(request, response);
    		  }

			  RequestDispatcher rd=request.getRequestDispatcher("HolidaySvl");
			  rd.forward(request, response);
		}
	}

}
