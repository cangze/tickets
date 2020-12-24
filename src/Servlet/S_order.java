package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.org.apache.xml.internal.utils.ObjectPool;

import DS_operation.CheckBeforeOrder;
import DS_operation.Check_current_left;
import DS_operation.Order_tickets;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.java2d.SunGraphics2D;

/**
 * Servlet implementation class S_order
 */
@WebServlet("/S_order")
public class S_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
       

		request.setCharacterEncoding("UTF-8"); 
		
//		System.out.print("sss");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb);
		String resultString="";
		int result=0;
		String sbString=sb.toString();
		response.setCharacterEncoding("UTF-8");
//		xmlhttp2.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
//		response.setContentType("application/json; charset=utf-8");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//			/* 星号表示所有的异域请求都可以接受， */
//		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		JSONArray jsonArray=JSONArray.fromObject(sbString);
		if(jsonArray.size()>0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				//获取数据，插入数据库orders,返回交易成功。
				String customid=jsonObject.getString("customid");
				int trainid=jsonObject.getInt("trainid");
				String start_arrive=jsonObject.getString("start_arrive");
				String[] s1=start_arrive.split("-");
				String start_station=s1[0];
				String arrive_station=s1[1];
				String start_arrive_t=jsonObject.getString("start_arrive_t");
				String[] s2=start_arrive_t.split("-");
				String start_time=s2[0];
				String arrive_time=s2[1];
				int start_station_id=jsonObject.getInt("start_stationid");
				int arrive_station_id=jsonObject.getInt("arrive_stationid");
				String ticket_type_age=jsonObject.getString("ticket_type_age");
				String ticket_type=jsonObject.getString("ticket_type");
				String taker_name=jsonObject.getString("taker_name");
				String taker_idcard=jsonObject.getString("taker_idcard");
				String taker_tel=jsonObject.getString("taker_tel");
				double cost=jsonObject.getDouble("cost");
				String final_seat_type="";
				switch (ticket_type) {
				case "商务座":
					final_seat_type="SW";
					break;
				case "一等座":
					final_seat_type="YD";
					break;
				case "二等座":
					final_seat_type="ED";
					break;
				case "其他座位类型":
					final_seat_type="OT";
					break;
				}
				String final_seat_type_age="";
				switch (ticket_type_age) {
				case "成人票":
					final_seat_type_age="CR";
					break;
				case "学生票":
					final_seat_type_age="XS";
					break;
				
				}
				Date dateDate=new Date();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String date=simpleDateFormat.format(dateDate);
				CheckBeforeOrder checkBeforeOrder=new CheckBeforeOrder(trainid, taker_idcard, date);
				if(checkBeforeOrder.getResult()==0) {
					System.out.print(ticket_type);
					System.out.print(final_seat_type_age);
				Order_tickets order_tickets=new Order_tickets(customid, trainid, start_station_id, arrive_station_id, start_station, arrive_station, start_time, arrive_time, taker_name, taker_idcard, final_seat_type, taker_tel, final_seat_type_age, cost);
				result++;
				}
				else {
					resultString="一天内同一身份不能购买同一趟列车";
					break;
				}
			}
		}
		if(result==jsonArray.size()) {
			resultString="支付成功";
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.accumulate("message", resultString);
		PrintWriter out=response.getWriter();
//		String message="购买成功";
        try {
        	out.write(resultString);
        	System.out.print("");
        	
        	out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
	}

}
