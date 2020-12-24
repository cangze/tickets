<%@page import="DS_operation.Train_info_new"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String order_id=request.getSession().getAttribute("order_id").toString();

String start_station=request.getSession().getAttribute("start_station").toString();
String arrive_staion=request.getSession().getAttribute("arrive_station").toString();
if(!start_station.contains("站")){
	start_station+="站";
}
if(!arrive_staion.contains("站")){
	arrive_staion+="站";
}
String taker_name=request.getSession().getAttribute("taker_name").toString();
String taker_id=request.getSession().getAttribute("taker_id").toString();
String taker_phone=request.getSession().getAttribute("taker_phone").toString();
 double cost=Double.parseDouble(request.getSession().getAttribute("cost").toString()); 

/* String order_id="Wdaih2564";
String start_station="北京南";
String arrive_staion="天津";

String taker_name="dbq";
String taker_id="2968489";
String taker_phone="19884948";
/* double cost=20.0; */

String result="董冰清";
String result_id="15589359936";  

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户页面</title>
    <link rel="stylesheet" type="text/css" href="../css/leftTicket.css" />
	<link rel="stylesheet" type="text/css" href="../css/cityselect.css"/>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/cityselect2.js"></script>
	<script type="text/javascript">
		
		function submitFun(){
			var td=document.getElementById("dd");
			td.innerHTML="16:53-12:22";
			document.myFrom.action="../servlet/Buy"
			document.myFrom.submit();
		}
		function Page1(){
			window.location.href="../index.html";
//		window.location.href="http://blog.sina.com.cn/mleavs";
		}
		function Page2(){
			window.location.href="../leftTicket/transTickets.jsp";
//		window.location.href="http://blog.sina.com.cn/mleavs";
		}
		function myorder(){
			var sub_custom_id=document.getElementById("custom_id").value;
			var sub_custom_name=document.getElementById("custom_name").value;
			
			
			
			var temp=document.createElement("form");
			temp.action="../Servlet/S_myorder";
			temp.method="post";
			temp.style.display="none";
			
			var custom_id=document.createElement("input");
			custom_id.name="custom_id";
			custom_id.value=sub_custom_id;
			temp.appendChild(custom_id);
			
			var custom_id=document.createElement("input");
			custom_id.name="custom_id";
			custom_id.value=sub_custom_id;
			temp.appendChild(custom_id);
		
			
			document.body.appendChild(temp);
			temp.submit();
			
			return temp;
		}
		function buy(a){
			
			var id=a;
			var sub_custom_name=document.getElementById("custom_name").value;
			var sub_custom_id=document.getElementById("custom_id").value;
			var sub_train_name=document.getElementById("train_name_input"+id).value;
			var sub_train_id=document.getElementById("trainidinput"+id).value;
			
			var sub_start_station_id=document.getElementById("train_startidinput"+id).value;
			var sub_arrive_station_id=document.getElementById("train_arriveidinput"+id).value;
			
			var sub_start_arrive=document.getElementById("start_arriveinput"+id).value;
			
			var sub_start_station='<%=start_station%>';
			var sub_arrive_station='<%=arrive_staion%>';
			
			var sub_train_start=document.getElementById("train_startidinput"+id).value;
			var sub_train_arrive=document.getElementById("train_arriveidinput"+id).value;
			var sub_start_arrive=document.getElementById("start_arriveinput"+id).value;
			var sub_startt_arrivet=document.getElementById("startt_arrivetinput"+id).value;
			var taker_name='<%=taker_name%>';
			var taker_id='<%=taker_id%>';
			var taker_phone='<%=taker_phone%>';
			var cost='<%=cost%>';
			alert(sub_start_station+" "+sub_arrive_station);
			
			var temp=document.createElement("form");
			temp.action="../Servlet/S_gaiqianbuy";
			temp.method="post";
			temp.style.display="none";
			
		/* 	var trainid=document.createElement("input");
			trainid.name="train_id";
			trainid.value=id;
			temp.appendChild(trainid); */ 
			
			
			var custom_name=document.createElement("input");
			custom_name.name="custom_name";
			custom_name.value=sub_custom_name;
			temp.appendChild(custom_name);
			
			var custom_id=document.createElement("input");
			custom_id.name="custom_id";
			custom_id.value=sub_custom_id;
			temp.appendChild(custom_id);
			
			var trainid=document.createElement("input");
			trainid.name="train_id";
			trainid.value=sub_train_id;
			temp.appendChild(trainid);
			
			var trainname=document.createElement("input");
			trainname.name="train_name";
			trainname.value=sub_train_name;
			temp.appendChild(trainname);
			
			var trainstation=document.createElement("input");
			trainstation.name="start_station";
			trainstation.value=sub_start_station;
			temp.appendChild(trainstation);
			
			var trainstation2=document.createElement("input");
			trainstation2.name="arrive_station";
			trainstation2.value=sub_arrive_station;
			temp.appendChild(trainstation2);
			
			var start_station_id=document.createElement("input");
			start_station_id.name="start_station_id";
			start_station_id.value=sub_start_station_id;
			temp.appendChild(start_station_id);
			
			var arrive_station_id=document.createElement("input");
			arrive_station_id.name="arrive_station_id";
			arrive_station_id.value=sub_arrive_station_id;
			temp.appendChild(arrive_station_id);
			
			var trainstation3=document.createElement("input");
			trainstation3.name="train_start_station";
			trainstation3.value=sub_train_start;
			temp.appendChild(trainstation3);
			
			var trainstation4=document.createElement("input");
			trainstation4.name="train_arrive_station";
			trainstation4.value=sub_train_arrive;
			temp.appendChild(trainstation4);
			
			var trainstation5=document.createElement("input");
			trainstation5.name="start_arrive";
			trainstation5.value=sub_start_arrive;
			temp.appendChild(trainstation5);
			
			var trainstation6=document.createElement("input");
			trainstation6.name="startt_arrivet";
			trainstation6.value=sub_startt_arrivet;
			temp.appendChild(trainstation6);

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
			
			return temp;
		}
		function sub_tickets(){
			//alert("请输入目的地、出发地、出发时间");
			var tick_s=document.getElementById("citySelect1").value;
			var tick_a=document.getElementById("citySelect2").value;
			var tick_t=document.getElementById("ticket_time").value;
			if(tick_s.length<=0||tick_a.length<=0||tick_t<=0){
				alert("请输入目的地、出发地、出发时间");
			}
			else{
				//alert("目的地、出发地、出发时间"+tick_s+"::"+tick_a+"::"+tick_t);
				var req;
				if(window.XMLHttpRequest){
				req=new XMLHttpRequest();
				//alert("目的地、出发地、出发时间1"+tick_s+"::"+tick_a+"::"+tick_t);
				}else{//不直接支持ajax
			      req=new ActiveObject('Microsoft.XMLHTTP');
			    // alert("目的地、出发地、出发时间2"+tick_s+"::"+tick_a+"::"+tick_t);
		   		 }
					req.onreadystatechange=function(){
			  		if (req.readyState==4&&req.status==200) {
			   			 //获取服务器的响应值
			 		/* var result=req.responseText;
			   		alert(result);  */
			   			 //后续操作
			      	var obj = eval("(" + req.responseText + ")");
			       	//alert(obj);
			       	/* var trObj=document.getElementById("tb"); */
			      	var trObj=document.getElementById("tb");
			       	trObj.innerHTML="";
			       	for (var i = 0; i < obj.length; i++) {	
			       		
			       		/*  var tail="<form name=\"myform"+i+"\" action=\"../Servlet/S_buy\" method=\"post\">"; */
			       		var tail="";
			       		var id=obj[i].train_id;
			       		tail+="<input class=\"inputtxt\" id=\"train_startidinput"+id+"\" type=\"text\" value="+obj[i].train_start_id+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"train_arriveidinput"+id+"\" type=\"text\" value="+obj[i].train_arrive_id+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"trainidinput"+id+"\" type=\"text\" value="+obj[i].train_id+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"start_idinput"+id+"\" type=\"text\" value="+obj[i].start_station_id+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"arrive_idinput"+id+"\" type=\"text\" value="+obj[i].arrive_station_id+" style=\" display:none\">";
			       		tail+="<tr><td>"+"<input class=\"inputtxt\" id=\"train_name_input"+id+"\" type=\"text\" value="+obj[i].train_name+">"+"</td>";
			       		tail+="<td>"+"<input class=\"inputtxt\" id=\"start_arriveinput"+id+"\" type=\"text\" value="+obj[i].start_arrive+">"+"</td>";
			       		tail+="<td>"+"<input class=\"inputtxt\" id=\"startt_arrivetinput"+id+"\" type=\"text\" value="+obj[i].startt_arrivet+">"+"</td>";
			       		tail+="<td>"+obj[i].SW+"</td>";
			       		tail+="<td>"+obj[i].YD+"</td>";
			       		tail+="<td>"+obj[i].ED+"</td>";
			       		tail+="<td>"+obj[i].OT+"</td>";
			       		tail+="<td>"+"<input class=\"inputbut\" type=\"button\" name=\"train"+id+"\" onClick=\"buy("+id+")\" value=\"预订\"></td></tr>";
			       		
			     		trObj.innerHTML+=tail;
					}
					/* var oFragment = document.createDocumentFragment();
					for (var i=0; i<obj.length; i++) {
						var trObj = document.createElement("tr");
						trObj.innerHTML = "<td>" + obj[i].train_name + "</td><td>" + obj[i].id + "</td>";
						oFragment.appendChild(trObj);
					}
					document.getElementById("tb").appendChild(oFragment);
			   		} */
					}
				};
			/* alert(tick_s+"::"+tick_a+"::"+tick_t); */
        	//设置传送方式，对应的servlet或action路径，是否异步处理
			req.open("POST", "../Servlet/S_select", true);
	        //alert("目的地、出发地、出发时间3"+tick_s+"::"+tick_a+"::"+tick_t);
        	//如果设置数据传送方式为post，则必须设置请求头信息
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			//alert("目的地、出发地、出发时间4"+tick_s+"::"+tick_a+"::"+tick_t);
        	//Ajax请求发送的数据不是表单，需要拼接数据，格式和get方式一样
			var reqData ="start=" + tick_s;
			reqData += "&arrive=" + tick_a;
			reqData += "&time=" + tick_t;
			//alert(reqData);
	        //发送请求
        	req.send(reqData);
        	//alert(reqData);
		  }
		}
</script>
	<script type="text/javascript">
	$(function() {
		var date_now=new Date();
		var year=date_now.getFullYear();
		//alert(date_now);
		var month= date_now.getMonth()+1;
		//alert(month);
		if(month+1<10){
			month="0"+(date_now.getMonth()+1)
		}
		var date=date_now.getDate(); 
		//alert(date);
		if(date<10){
			date="0"+date_now.getDate();
		}
		$("#ticket_time").attr("min",year+"-"+month+"-"+date);
	})
	
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
		<div class="headticket">
			<!--这是我的车票按钮-->
			<button class="ticket_but" style="width: 80px;height: 30px;">退出</button>
			<button class="ticket_but" style="width: 80px;height: 30px;"><%=result%></button>
			<button class="ticket_but" style="width: 80px;height: 30px; color: blue;" onclick="myorder()"> 我的车票</button>
			
		</div>		
	</div>
	<div class="guider">
		<ul>
			<!--用jQuery实现页面的切换-->
			<li class="btns"><button class="li_button" onClick="Page2()">换乘查询</button></li>
			<li class="btns"><button class="li_button" onClick="Page1()">回到首页</button></li>
		</ul>
	</div>
	  
	  
	<div class="search">
		<div class="search_info">
			<form action="../Servlet/S_select" method="post" name="ticketsform" class="info_form">
			<ul>
			<li class="info_start">
				<p class="info_table" style="display: inline-block">出发地</p>
				<input type="text" class="cityinput" id="citySelect1" name="start_station" value="<%=start_station %>" disabled="disabled">
			</li>
			<li class="info_start">
				<p class="info_table" style="display: inline-block">目的地</p>
				<input type="text" class="cityinput" id="citySelect2" name="arrive_station" value="<%=arrive_staion %>" disabled="disabled">
			</li>
		
			<script type="text/javascript">
				var test=new Vcity.CitySelector({input:'citySelect1'});
				var test=new Vcity.CitySelector({input:'citySelect2'});
			</script>
			<li class="info_start">
				<p class="info_table"  style="display: inline-block">出发日期</p>
				<input class="cityinput" type="date" id="ticket_time" name="start_time" min="2020-11-09">
			</li>
			<li class="info_start">
				<input type="button" onclick="sub_tickets()" class="select_but" value="查询">
			</li>
			</ul>
			</form>
		
		</div>
		 
	</div>
	<div id="webPage4">
   		<table class="table" width="1450px" cellspacing="0" cellpadding="0">
        <thead class="table_head">
        <tr>
            <th width="120px">车次</th>
            <th width="120px">出发站-到达站</th>
            <th width="120px">出发时间-到达时间</th>

			<th width="120px">商务座</th>
			<th width="120px">一等座</th>
			<th width="120px">二等座</th>
			<th width="120px">其他</th>
			<th width="120px">购买</th>
        </tr>
        </thead>
        
        <tbody id="tb">
			
			
        </tbody>
        
    	</table> 
	</div>
	

	</body>
</html>
		