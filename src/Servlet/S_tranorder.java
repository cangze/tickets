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

import DS_operation.CheckBeforeOrder;
import DS_operation.Order_tickets;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class S_tranorder
 */
@WebServlet("/S_tranorder")
public class S_tranorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_tranorder() {
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
				int atrainid=jsonObject.getInt("atrainid");
				int btrainid=jsonObject.getInt("btrainid");
				
				String start_arrive_t=jsonObject.getString("start_middle_t");
				String[] s1=start_arrive_t.split("-");
				String start_t=s1[0];
				String middlea_t=s1[1];
				System.out.print(start_arrive_t);
				String middle_arrive_t=jsonObject.getString("middle_arrive_t");
				String[] s2=middle_arrive_t.split("-");
				String middleb_t=s2[0];
				String arrive_t=s2[1];
				System.out.print(middle_arrive_t);
				
				String start_arrive=jsonObject.getString("start_middle");
				String[] s3=start_arrive.split("-");
				String start=s3[0];
				String middlea=s3[1];
				
				String middle_arrive=jsonObject.getString("middle_arrive");
				String[] s4=middle_arrive.split("-");
				String middleb=s4[0];
				String arrive=s4[1];
				
				
				int traina_startid=jsonObject.getInt("traina_startid");
				int traina_middleid=jsonObject.getInt("traina_middleid");
				
				int trainb_middleid=jsonObject.getInt("trainb_middleid");
				int train_arriveid=jsonObject.getInt("train_arriveid");
				
				String aticket_type_age=jsonObject.getString("aticket_type_age");
				String aticket_type=jsonObject.getString("aticket_type");
				
				String bticket_type_age=jsonObject.getString("bticket_type_age");
				String bticket_type=jsonObject.getString("bticket_type");
				
				String taker_name=jsonObject.getString("taker_name");
				String taker_idcard=jsonObject.getString("taker_idcard");
				String taker_tel=jsonObject.getString("taker_tel");
				
				double costa=jsonObject.getDouble("costa");
				double costb=jsonObject.getDouble("costa");
				
				String afinal_seat_type="";
				switch (aticket_type) {
				case "商务座":
					afinal_seat_type="SW";
					break;
				case "一等座":
					afinal_seat_type="YD";
					break;
				case "二等座":
					afinal_seat_type="ED";
					break;
				case "其他座位类型":
					afinal_seat_type="OT";
					break;
				}
				String afinal_seat_type_age="";
				switch (aticket_type_age) {
				case "成人票":
					afinal_seat_type_age="CR";
					break;
				case "学生票":
					afinal_seat_type_age="XS";
					break;
				
				}
				
				String bfinal_seat_type="";
				switch (bticket_type) {
				case "商务座":
					bfinal_seat_type="SW";
					break;
				case "一等座":
					bfinal_seat_type="YD";
					break;
				case "二等座":
					bfinal_seat_type="ED";
					break;
				case "其他座位类型":
					bfinal_seat_type="OT";
					break;
				}
				String bfinal_seat_type_age="";
				switch (bticket_type_age) {
				case "成人票":
					bfinal_seat_type_age="CR";
					break;
				case "学生票":
					bfinal_seat_type_age="XS";
					break;
				
				}
				
				Date dateDate=new Date();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String date=simpleDateFormat.format(dateDate);
				CheckBeforeOrder checkBeforeOrdera=new CheckBeforeOrder(atrainid, taker_idcard, date);
				CheckBeforeOrder checkBeforeOrderb=new CheckBeforeOrder(btrainid, taker_idcard, date);
				if(checkBeforeOrdera.getResult()==0&&checkBeforeOrderb.getResult()==0) {
					System.out.println(aticket_type);
					System.out.println(afinal_seat_type_age);
					System.out.println(bticket_type);
					System.out.println(bfinal_seat_type_age);
				
				Order_tickets aorder_tickets=new Order_tickets(customid, atrainid, traina_startid, traina_middleid, start, middlea, start_t, middlea_t, taker_name, taker_idcard, afinal_seat_type, taker_tel, afinal_seat_type_age, costa);
				Order_tickets border_tickets=new Order_tickets(customid, btrainid, trainb_middleid, train_arriveid, middleb, arrive, middlea_t,arrive_t, taker_name, taker_idcard, bfinal_seat_type, taker_tel, bfinal_seat_type_age, costb);
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
