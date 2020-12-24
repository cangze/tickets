package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DS_operation.Confirm_Tickets;
import DS_operation.Train_info_new;
import DS_operation.Train_leftickets_info;

/**
 * Servlet implementation class S_buy
 */
@WebServlet("/S_buy")
public class S_buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_buy() {
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
		String custom_name=request.getParameter("custom_name");
		String custom_id=request.getParameter("custom_id");
		String startt_arrivetString=request.getParameter("startt_arrivet");
		String start_arrive=request.getParameter("start_arrive");
		String train_name=request.getParameter("train_name");
		int train_id=Integer.parseInt(request.getParameter("train_id"));
		int start_station_id=Integer.parseInt(request.getParameter("start_station"));
		int arrive_station_id=Integer.parseInt(request.getParameter("arrive_station"));
		int train_start_id=Integer.parseInt(request.getParameter("train_start_station"));
		int train_arrive_id=Integer.parseInt(request.getParameter("train_arrive_station"));
		
		//从数据库获取train_name,start_station,arrive_station对应的余票；
		System.out.print( ""+train_id+"-"+train_name+" "+start_arrive+" :"+start_station_id+"-"+arrive_station_id+" "+train_start_id+"-"+train_arrive_id+" "+startt_arrivetString);
		
		Confirm_Tickets confirm_Tickets=new Confirm_Tickets(train_id, train_start_id, train_arrive_id, start_station_id, arrive_station_id);
		Train_leftickets_info info=confirm_Tickets.getTrain_leftickets_info();
		double[] ticket_cost=confirm_Tickets.getTickets_cost();
		String rundate=confirm_Tickets.getRun_date();
		
		request.getSession().setAttribute("custom_id", custom_id);
		request.getSession().setAttribute("custom_name", custom_name);
		request.getSession().setAttribute("train_id", train_id);
		request.getSession().setAttribute("train_name", train_name);
		request.getSession().setAttribute("start_arrive", start_arrive);
		request.getSession().setAttribute("startt_arrivet", startt_arrivetString);
		request.getSession().setAttribute("start_station_id", start_station_id);
		request.getSession().setAttribute("arrive_station_id", arrive_station_id);
		request.getSession().setAttribute("SW", ticket_cost[0]);
		request.getSession().setAttribute("YD", ticket_cost[1]);
		request.getSession().setAttribute("ED", ticket_cost[2]);
		request.getSession().setAttribute("OT", ticket_cost[3]);
		request.getSession().setAttribute("run_date", rundate);
		request.getSession().setAttribute("lefticket", info);
		
		System.out.append("dd");
		response.sendRedirect("../buyTickets/confirm.jsp");
	}

}
