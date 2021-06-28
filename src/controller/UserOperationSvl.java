package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.User;

/**
 * Servlet implementation class UserOperationSvl
 */
@WebServlet("/UserOperationSvl")
public class UserOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperationSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		//User user=(User)request.getAttribute("userInfo");
		HttpSession session = request.getSession();
		int mode=Integer.parseInt(request.getParameter("mode"));
		if(mode!=11&&session==null){
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}

		String usrName=request.getParameter("usrName");
		int gender=3;
		if(request.getParameter("gender")!=null){
			gender=Integer.parseInt(request.getParameter("gender"));
		}
		String birthday=request.getParameter("birthday");
		String postcode=request.getParameter("postcode");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		String password=request.getParameter("password");

		User user=new User();
		user.setUsr_name(usrName);
		user.setGender_id(gender);
		user.setBirthday(birthday);
		user.setPostcode(postcode);
		user.setAddress(address);
		user.setPhone(phone);
		user.setMail(mail);
		user.setPassword(password);


		switch(mode){
		  case 11:
			  try{
				  user=User.insert(user);
				  /*String ma=user.getMail();
				  user=User.getUser(ma);*/
				  ///上の記述ないとID保持できない？20210517
				  session.setAttribute("userInfo", user);

			  }catch(IdealException e){
				  //エラー処理から20210519
				  e.printStackTrace();
				  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				  request.setAttribute("msg", ie);
				  request.setAttribute("userInfo", user);
				  RequestDispatcher rd=request.getRequestDispatcher("userInsert.jsp");
				  rd.forward(request, response);
			  }
			  RequestDispatcher rd=request.getRequestDispatcher("userInsertCompletion.jsp");
			  rd.forward(request, response);
			  break;

		  case 12:
			  try{

				  //updateから20210512
				  String ma=user.getMail();
				  user=User.getUser(ma);
				  postcode=request.getParameter("postcode");
				  address=request.getParameter("address");
				  phone=request.getParameter("phone");
				  mail=request.getParameter("mail");
				  password=request.getParameter("newpassword");

				  if(password.equals("")){
					  user.setPostcode(postcode);
					  user.setAddress(address);
					  user.setPhone(phone);
					  user.setMail(mail);

					  user=User.update(user);
					  ma=user.getMail();
					  user=User.getUser(ma);
					  session.setAttribute("userInfo", user);
				  }else{
					  user.setPostcode(postcode);
					  user.setAddress(address);
					  user.setPhone(phone);
					  user.setMail(mail);
					  user.setPassword(password);
					  System.out.println(password);

					  user=User.update(user);
					  ma=user.getMail();
					  user=User.getUser(ma);
					  session.setAttribute("userInfo", user);
				  }

			  }catch(Exception e){
				  e.printStackTrace();
				  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				  request.setAttribute("msg", ie);
				  request.setAttribute("userInfo", user);
				  rd=request.getRequestDispatcher("UserUpdateSvl");
				  rd.forward(request, response);
			  }
			  rd=request.getRequestDispatcher("userIndex.jsp");
			  rd.forward(request, response);
			  break;

		  case 13:
			  try{
				  String ma=request.getParameter("mail");
				  user=User.getUser(ma);
				  User.deleteReserve(user);
				  User.delete(user);
				  session.invalidate();

			  }catch(Exception e){
				  e.printStackTrace();
				  IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

				  request.setAttribute("msg", ie);
				  request.setAttribute("userInfo", user);
				  rd=request.getRequestDispatcher("UserDeleteSvl");
				  rd.forward(request, response);
			  }
			  rd=request.getRequestDispatcher("home.jsp");
			  rd.forward(request, response);
			  break;
		}

	}

}
