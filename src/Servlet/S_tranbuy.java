package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DS_operation.Confirm_Tickets;
import DS_operation.Train_leftickets_info;

/**
 * Servlet implementation class S_tranbuy
 */
@WebServlet("/S_tranbuy")
public class S_tranbuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_tranbuy() {
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
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		sub_start_arrive
		String custom_name=request.getParameter("custom_name");
		String custom_id=request.getParameter("custom_id");
		
		int atrainid=Integer.parseInt(request.getParameter("atrainid"));
		int btrainid=Integer.parseInt(request.getParameter("btrainid"));
		int traina_startid=Integer.parseInt(request.getParameter("traina_startid"));
		int traina_middleid=Integer.parseInt(request.getParameter("traina_middleid"));
		
		int trainb_middleid=Integer.parseInt(request.getParameter("trainb_middleid"));
		
		int train_arriveid=Integer.parseInt(request.getParameter("train_arriveid"));
		
		int counta=Integer.parseInt(request.getParameter("counta"));
		
		int countb=Integer.parseInt(request.getParameter("countb"));
		
		String traina_name=request.getParameter("traina_name");
		String trainb_name=request.getParameter("trainb_name");
		String start_middle=request.getParameter("start_middle");
		String middle_arrive=request.getParameter("middle_arrive");
		String start_middle_t=request.getParameter("start_middle_t");
		String middle_arrive_t=request.getParameter("middle_arrive_t");
		
		//从数据库获取train_name,start_station,arrive_station对应的余票；
		System.out.println(custom_name);
		System.out.println(custom_id);
		System.out.println(atrainid);
		System.out.println(btrainid);
		System.out.println(traina_startid);
		System.out.println(traina_middleid);
		System.out.println(trainb_middleid);
		System.out.println(train_arriveid);
		
		
		System.out.println(traina_name);
		System.out.println(trainb_name);
		System.out.println(start_middle);
		System.out.println(middle_arrive);
		System.out.println(start_middle_t);
		System.out.println(middle_arrive_t);
		
		Confirm_Tickets confirm_Ticketsa=new Confirm_Tickets(atrainid, 1, counta, traina_startid, traina_middleid);
		
		Confirm_Tickets confirm_Ticketsb=new Confirm_Tickets(btrainid, 1, countb, trainb_middleid, train_arriveid);
		
		String arundate=confirm_Ticketsa.getRun_date();
		String brundate=confirm_Ticketsb.getRun_date();
		
		Train_leftickets_info infoa=confirm_Ticketsa.getTrain_leftickets_info();
		Train_leftickets_info infob=confirm_Ticketsb.getTrain_leftickets_info();
		
		
		double[] atickets_cost=confirm_Ticketsa.getTickets_cost();
		double[] btickets_cost=confirm_Ticketsb.getTickets_cost();
		
		
		
		request.getSession().setAttribute("custom_id", custom_id);
		request.getSession().setAttribute("custom_name", custom_name);
		request.getSession().setAttribute("atrainid", atrainid);
		request.getSession().setAttribute("btrainid", btrainid);
		request.getSession().setAttribute("traina_startid", traina_startid);
		request.getSession().setAttribute("traina_middleid", traina_middleid);
		request.getSession().setAttribute("trainb_middleid", trainb_middleid);
		request.getSession().setAttribute("train_arriveid", train_arriveid);
		
		request.getSession().setAttribute("traina_name", traina_name);
		request.getSession().setAttribute("trainb_name", trainb_name);
		request.getSession().setAttribute("start_middle", start_middle);
		request.getSession().setAttribute("middle_arrive", middle_arrive);
		request.getSession().setAttribute("start_middle_t", start_middle_t);
		request.getSession().setAttribute("middle_arrive_t", middle_arrive_t);
		
		request.getSession().setAttribute("ASW", atickets_cost[0]);
		request.getSession().setAttribute("AYD", atickets_cost[1]);
		request.getSession().setAttribute("AED", atickets_cost[2]);
		request.getSession().setAttribute("AOT", atickets_cost[3]);
		
		request.getSession().setAttribute("BSW", btickets_cost[0]);
		request.getSession().setAttribute("BYD", btickets_cost[1]);
		request.getSession().setAttribute("BED", btickets_cost[2]);
		request.getSession().setAttribute("BOT", btickets_cost[3]);
		
		request.getSession().setAttribute("counta", counta);
		request.getSession().setAttribute("counta", countb);
		request.getSession().setAttribute("infoa", infoa);
		request.getSession().setAttribute("infob", infob);
		request.getSession().setAttribute("arundate", arundate);
		request.getSession().setAttribute("brundate", brundate);
//		
		System.out.append("dd");
		response.sendRedirect("../Tickets/buyTickets/confirm_tran.jsp");
	
		
		
	}

}
