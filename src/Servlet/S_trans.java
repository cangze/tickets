package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DS_operation.Select_train_final;
import DS_operation.Select_trans_train;
import DS_operation.Train_info_final;
import DS_operation.Train_leftickets_info;
import DS_operation.Trans_info_finals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class S_trans
 */
@WebServlet("/S_trans")
public class S_trans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_trans() {
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
		
		String start_station=request.getParameter("start");
		String arrive_station=request.getParameter("arrive");
		String date=request.getParameter("time");
		List<String> list=new ArrayList<String>();
		
		System.out.append("dd");
		
		System.out.print(start_station+":"+arrive_station);
		
		Select_trans_train select_trans_train=new Select_trans_train(start_station, arrive_station, date);
//		Select_train_final select_train_final=new Select_train_final(start_station, arrive_station, date);
		ArrayList<Trans_info_finals> finals=select_trans_train.getTransInfoFinals();
		
		
		JSONArray jsonArray=new JSONArray();
		Iterator<Trans_info_finals> tinIterator=finals.iterator();
//		Iterator<Train_info_new> trainIterator=tsArrayList.iterator();
		//Iterator<Train_leftickets_info> tliIterator=leftickets_infos.iterator();
		while (tinIterator.hasNext()) {
			
			Trans_info_finals tInfo_new=tinIterator.next();
			System.out.print(tInfo_new);
			System.out.println("woshi counra"+tInfo_new.getCounta());
			
			JSONObject json1=new JSONObject();
			json1.accumulate("trainida", tInfo_new.getTrainida());
			json1.accumulate("trainidb", tInfo_new.getTrainidb());
			json1.accumulate("startid", tInfo_new.getStartid());
			json1.accumulate("middieida", tInfo_new.getMiddleida());
			
			System.out.println("我是middleidb"+tInfo_new.getMiddleidb());
			
			json1.accumulate("middieidb", tInfo_new.getMiddleidb());	
			
			json1.accumulate("arriveid", tInfo_new.getArriveid());
			json1.accumulate("asw", tInfo_new.getASW());
			json1.accumulate("ayd", tInfo_new.getAYD());
			json1.accumulate("aed", tInfo_new.getAED());
			json1.accumulate("aot", tInfo_new.getAOT());
			json1.accumulate("bsw", tInfo_new.getBSW());
			json1.accumulate("byd", tInfo_new.getBYD());
			json1.accumulate("bed", tInfo_new.getBED());
			json1.accumulate("bot", tInfo_new.getBOT());
			
			json1.accumulate("atrain_name", tInfo_new.getAtrainname());
			json1.accumulate("btrain_name", tInfo_new.getBtrainname());
			json1.accumulate("start_middle", tInfo_new.getStartname()+"-"+tInfo_new.getMiddlename());
			json1.accumulate("middle_arrive", tInfo_new.getMiddlename()+"-"+tInfo_new.getArrivename());
			json1.accumulate("start_middle_t", tInfo_new.getStarttime()+"-"+tInfo_new.getMiddletimea());
			json1.accumulate("middle_arrive_t", tInfo_new.getMiddletimeb()+"-"+tInfo_new.getArrivetime());
			json1.accumulate("counta", tInfo_new.getCounta());
			json1.accumulate("countb", tInfo_new.getCountb());
					
			
			jsonArray.add(json1);
		}
		
		
		System.out.println(jsonArray.toString());
		response.setContentType("application/json; charset=utf-8");
		PrintWriter wiWriter=response.getWriter();
		wiWriter.print(jsonArray);
		wiWriter.close();
	}

}
