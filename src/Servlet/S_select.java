package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;

import DS_operation.LeftTickets_info;
import DS_operation.Select_train_final;
import DS_operation.Select_train_newq;
import DS_operation.Train_info_final;
import DS_operation.Train_info_new;
import DS_operation.Train_leftickets_info;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class S_select
 */
@WebServlet("/S_select")
public class S_select extends HttpServlet {
	private static final long serialVersionUID = 148965L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_select() {
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
		
		String start_station=request.getParameter("start");
		String arrive_station=request.getParameter("arrive");
		String date=request.getParameter("time");
		List<String> list=new ArrayList<String>();
		
		System.out.append("dd");
		
//		if(start_station.contains("市")||start_station.contains("站")) {
//			start_station=start_station.substring(0,start_station.length()-1);
//		}
//		if(arrive_station.contains("市")||arrive_station.contains("站")) {
//			arrive_station=arrive_station.substring(0,arrive_station.length()-1);
//		}
		System.out.print(start_station+":"+arrive_station);
		Select_train_final select_train_final=new Select_train_final(start_station, arrive_station, date);
		ArrayList<Train_info_final> finals=select_train_final.getTrain_info_finals();
		ArrayList<Train_leftickets_info> leftickets_infos=select_train_final.getTrain_leftickets_infos();
		//		Select_train_newq s=new Select_train_newq(start_station,arrive_station, date);
//		ArrayList<Train_info_new> tsArrayList=s.getTrain_through();
//		Iterator<Train_info_new> trainIterator2=tsArrayList.iterator();
//		
//		int count=0;
//		while(trainIterator2.hasNext()) {
//			Train_info_new tInfo_new=trainIterator2.next();
//			System.out.println("wefindit:"+tInfo_new);
//			count++;
//		}
		
		JSONArray jsonArray=new JSONArray();
		Iterator<Train_info_final> tinIterator=finals.iterator();
//		Iterator<Train_info_new> trainIterator=tsArrayList.iterator();
		//Iterator<Train_leftickets_info> tliIterator=leftickets_infos.iterator();
		while (tinIterator.hasNext()) {
			Train_info_final tInfo_new=tinIterator.next();
			JSONObject json1=new JSONObject();
			json1.accumulate("train_id", tInfo_new.getTrain_id());
			System.out.print(tInfo_new.getTrain_id());
			json1.accumulate("train_name", tInfo_new.getTrain_name());
			json1.accumulate("train_start_id", tInfo_new.getTrain_start_id());
			json1.accumulate("train_arrive_id", tInfo_new.getTrain_arrive_id());
			json1.accumulate("start_arrive", tInfo_new.getStart_station_name()+"-"+tInfo_new.getArrive_station_name());
			json1.accumulate("start_station_id", tInfo_new.getStart_station_id());
			json1.accumulate("arrive_station_id", tInfo_new.getArrive_station_id());
			json1.accumulate("startt_arrivet", tInfo_new.getStart_time()+"-"+tInfo_new.getArrive_time());
//			int nextday=tInfo_new.getNextday();
//			String aString="未知";
//			if(nextday==0) {
//				aString="当日到达";
//			}else if (nextday==1) {
//				aString="次日到达";
//			}
//			json1.accumulate("timespand", tInfo_new.getTime_cost());
//			json1.accumulate("nextday", aString);
			Iterator<Train_leftickets_info> linIterator=leftickets_infos.iterator();
			while (linIterator.hasNext()) {
				Train_leftickets_info lInfo=(Train_leftickets_info)linIterator.next();	
				if(lInfo.getTrain_id()==tInfo_new.getTrain_id()) {
					 json1.accumulate("SW", lInfo.getSW());
					 json1.accumulate("YD", lInfo.getYD());
					 json1.accumulate("ED", lInfo.getED());
					 json1.accumulate("OT", lInfo.getOT());
					 break;
				}
			}
			jsonArray.add(json1);
		}
		
		
		System.out.println(jsonArray.toString());
		response.setContentType("application/json; charset=utf-8");
		PrintWriter wiWriter=response.getWriter();
		wiWriter.print(jsonArray);
		wiWriter.close();
		
}
}
