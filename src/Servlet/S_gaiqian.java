package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DS_operation.Tuipiao;

/**
 * Servlet implementation class S_gaiqian
 */
@WebServlet("/S_gaiqian")
public class S_gaiqian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_gaiqian() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		sub_start_arrive
		String orderid=request.getParameter("order_id");
		
		
		
		String start_station=request.getParameter("start_station");
		String arrive_station=request.getParameter("arrive_station");
		String taker_name=request.getParameter("taker_name");
		String taker_id=request.getParameter("taker_id");
		String taker_phone=request.getParameter("taker_phone");
		double cost=Double.parseDouble(request.getParameter("cost"));
				
		request.getSession().setAttribute("order_id", orderid);
		request.getSession().setAttribute("start_station", start_station);
		request.getSession().setAttribute("arrive_station", arrive_station);
		request.getSession().setAttribute("taker_name", taker_name);
		request.getSession().setAttribute("taker_id", taker_id);
		request.getSession().setAttribute("taker_phone", taker_phone);
		request.getSession().setAttribute("cost", cost);
		
		System.out.println(cost);
		
		System.out.println("start_station"+start_station+" "+arrive_station+" "+taker_name+" "+taker_id+" "+taker_phone+" "+cost);
		Tuipiao tuipiao=new Tuipiao(orderid);
		response.sendRedirect("../leftTicket/gaiqian.jsp");
		
		
	}

}
