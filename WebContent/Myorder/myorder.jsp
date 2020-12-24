<%@page import="DS_operation.GetOrders"%>
<%@page import="java.util.Iterator"%>
<%@page import="DS_operation.Orderinfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
String result="董冰清";
String result_id="15589359936"; 
ArrayList<Orderinfo> orderinfos=new GetOrders(result_id).getOrderinfos();
Iterator<Orderinfo> it_order2 =orderinfos.iterator();
Iterator<Orderinfo> it_order =orderinfos.iterator();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="../css/leftTicket.css" />
  <script src="../jquery-ui-1.12.1/external/jquery/jquery.js"></script>
   <script src="../js/jquery-form.js"></script>
 <script type="text/javascript">
 <% 
	while (it_order2.hasNext()) {
		Orderinfo pro2=it_order2.next();
	int count=0;
	String id=pro2.getOrder_id();
	String start_station=pro2.getStart_station_name();
	String arrive_station=pro2.getArrive_station_name();
	String taker_name=pro2.getTaker_name();
	String taker_id=pro2.getTaker_card_id();
	String taker_phone=pro2.getTaker_phone();
	double cost=pro2.getCost();
	%>
	function tuipiao<%=id%>(){
		var order_id='<%=id%>';
		alert("退票"+order_id);
		var temp=document.createElement("form");
		temp.target="nm_iframe";
		temp.action="../Servlet/S_tuipiao";
		temp.method="post";
		temp.style.display="none";
		
		var order_idd=document.createElement("input");
		order_idd.name="order_id";
		order_idd.value=order_id;
		temp.appendChild(order_idd);
		
		document.body.appendChild(temp);
		
		var tempp=document.createElement("iframe");
		tempp.name="nm_iframe";
		tempp.style.display="none";
		document.body.appendChild(tempp);
		
		temp.submit();	
	}
	
	function changeticket<%=id%>(){
		var order_id='<%=id%>';
		var start_station='<%=start_station%>';
		var arrive_station='<%=arrive_station%>';
		
		var taker_name='<%=taker_name%>';
		var taker_id='<%=taker_id%>';
		var taker_phone='<%=taker_phone%>';
		var cost='<%=cost%>';
		
		alert(""+order_id+" "+start_station+"  "+arrive_station+" "+taker_name+" "+taker_id+" "+taker_phone+" "+cost);
		
		var temp=document.createElement("form");
		temp.target="nm_iframe";
		temp.action="../Servlet/S_gaiqian";
		temp.method="post";
		temp.style.display="none";
		
		var order_idd=document.createElement("input");
		order_idd.name="order_id";
		order_idd.value=order_id;
		temp.appendChild(order_idd);
		
		var start_stations=document.createElement("input");
		start_stations.name="start_station";
		start_stations.value=start_station;
		temp.appendChild(start_stations);
		
		var arrive_stations=document.createElement("input");
		arrive_stations.name="arrive_station";
		arrive_stations.value=arrive_station;
		temp.appendChild(arrive_stations);
	
		var taker_names=document.createElement("input");
		taker_names.name="taker_name";
		taker_names.value=taker_name;
		temp.appendChild(taker_names);
		
		var taker_ids=document.createElement("input");
		taker_ids.name="taker_id";
		taker_ids.value=taker_id;
		temp.appendChild(taker_ids);
		
		var taker_phones=document.createElement("input");
		taker_phones.name="taker_phone";
		taker_phones.value=taker_phone;
		temp.appendChild(taker_phones);
	
		var costs=document.createElement("input");
		costs.name="cost";
		costs.value=cost;
		temp.appendChild(costs);
		
		
		document.body.appendChild(temp);
		temp.submit();
	}
	
<%}%>
 	
function Page1(){
	window.location.href="../index.html";
//window.location.href="http://blog.sina.com.cn/mleavs";
}
function Page3(){
	window.location.href="../leftTicket/leftTicket.jsp";
//window.location.href="http://blog.sina.com.cn/mleavs";
}
function Page2(){
	window.location.href="../leftTicket/transTickets.jsp";
//window.location.href="http://blog.sina.com.cn/mleavs";
}
function myorder(){
	window.location.href="../Myorder/myorder.jsp";
//window.location.href="http://blog.sina.com.cn/mleavs";
}
 </script>
 
 
 
</head>

<body>
<input id="custom_name" type="text" style="display: none;" value='<%=result%>'>
<input id="custom_id" type="text" style="display: none;" value='<%=result_id%>'>
 <div class="head" >
		<!--设置背景图片-->
		<div class="head_logo">
		<!--这是logo-->
		</div>		
	</div>
	<div class="guider">
		<ul>
			<!--用jQuery实现页面的切换-->
			<li class="btns"><button class="li_button" onClick="Page3()">单程查询</button></li>
			<li class="btns"><button class="li_button" onClick="Page2()">换乘查询</button></li>
			<li class="btns"><button class="li_button" onClick="Page1()">回到首页</button></li>
		</ul>
	</div>
	<div id="webPage4">
		
		 <h3>订单</h3>
		<hr>
   		<table class="table" width="1450px" cellspacing="0" cellpadding="0">
        <thead class="table_head">
        <tr>
            <th width="250px">车次</th>
            <th width="250px">起止站点</th>
            <th width="250px">乘客信息</th>
			<th width="250px">席位信息th>
			<th width="250px">操作</th>
        </tr>
        </thead>
        <tbody>
         <%
       		
			while (it_order.hasNext()) {
				Orderinfo pro2=it_order.next();
			int count=0;
			String id=pro2.getOrder_id();
			%>
			<tr>
			<input style="display: none" id="orderid+<%=pro2.getOrder_id() %>" value="<%=pro2.getOrder_id() %>">
			<input style="display: none" id="start_station<%=id %>" value="<%=pro2.getStart_station_name() %>">
			<input style="display: none" id="arrive_station+<%=id %>" value="<%=pro2.getArrive_station_name() %>">
            <td><p><%=pro2.getStart_station_name() %>--><%=pro2.getArrive_station_name() %>: <%=pro2.getTrain_name() %></p></td>
            <td><p>乘客姓名:<%=pro2.getTaker_name() %></p><p>乘客身份证号:<%=pro2.getTaker_card_id() %></p><p>乘客电话:<%=pro2.getTaker_phone() %></p></td>
            <td><p>席位信息:<%=pro2.getSeat_type() %> </p><p><%=pro2.getCarriage_id() %>车   <%=pro2.getTrain_seat() %>号</p></td>
            <td><p><%=pro2.getSeat_type_age() %> </p><p><%=pro2.getCost()%></p></td>
			<td><button id="changeticket<%=id%>" onclick="changeticket<%=id%>()">改签</button><button id="tuipiao<%=id%>" onclick="tuipiao<%=id%>()">退票</button></td>
			</tr>
			<% 
			}
			%>
			
        </tbody>
    	</table> 
		 </div>
		
		</div>
		

</body>
</html>