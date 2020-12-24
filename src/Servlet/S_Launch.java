package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DS_operation.Launch;
import DS_operation.Launch_account;

/**
 * Servlet implementation class Launch
 */
@WebServlet("/S_Launch")
public class S_Launch extends HttpServlet {
	private static final long serialVersionUID = 84515L;
       
    /**
     * @see HttpServlet#HttpServlet()
    */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name=request.getParameter("account_name");
		String password=request.getParameter("account_password");
		System.out.print(name+":"+password);
		Launch_account launch_account=new Launch_account(name, password);
		Launch launch=new Launch(launch_account);
		int result=launch.getResult();
		if(result==1) {
			System.out.println("successfully launched");
			request.getSession().setAttribute("name",launch.getCustom_nameString());
			request.getSession().setAttribute("custom_id", name);
			response.sendRedirect("../leftTicket/leftTicket.jsp");
		}else {
			System.out.println("launch Failed");
			//返回到Error页面
			request.getSession().setAttribute("error", "用户名与密码不匹配");
			response.sendRedirect("../Error/Error.jsp");
		}
		//
		
	}

}
