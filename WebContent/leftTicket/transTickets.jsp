<%@page import="DS_operation.Train_info_new"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*  String name=request.getSession().getAttribute("name").toString();
String result_id=request.getSession().getAttribute("custom_id").toString();
	String result="登录";
	if(name!=null){
	result=name;		
}  */
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
			window.location.href="../leftTicket/leftTicket.jsp";
//		window.location.href="http://blog.sina.com.cn/mleavs";
		}
		function myorder(){
			window.location.href="../Myorder/myorder.jsp";
//		window.location.href="http://blog.sina.com.cn/mleavs";
		}
		function buy(a){
			
			var id=a;
			
			var sub_custom_name=document.getElementById("custom_name").value;
			var sub_custom_id=document.getElementById("custom_id").value;
			var traina_startidinput=document.getElementById("traina_startidinput"+id).value;
			var traina_middleidinput=document.getElementById("traina_middleidinput"+id).value;
			var trainb_middleidinput=document.getElementById("trainb_middleidinput"+id).value;
			
			alert("传输的trainb_middleinput"+trainb_middleidinput);
			
			var train_arriveidinput=document.getElementById("train_arriveidinput"+id).value;
			var trainaidinput=document.getElementById("trainaidinput"+id).value;
			var trainbidinput=document.getElementById("trainbidinput"+id).value;
			
			var traina_name_input=document.getElementById("traina_name_input"+id).value;
			var trainb_name_input=document.getElementById("trainb_name_input"+id).value;
			var start_middleinput=document.getElementById("start_middleinput"+id).value;
			var middle_arrive=document.getElementById("middle_arrive"+id).value;
			var start_middle_tinput=document.getElementById("start_middle_tinput"+id).value;
			var middle_arrive_tinput=document.getElementById("middle_arrive_tinput"+id).value;
			
			var counta=document.getElementById("counta"+id).value;
			var countb=document.getElementById("countb"+id).value;
			
			var temp=document.createElement("form");
			temp.action="../S_tranbuy";
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
			
			var atrainid=document.createElement("input");
			atrainid.name="atrainid";
			atrainid.value=trainaidinput;
			temp.appendChild(atrainid);
			
			var btrainid=document.createElement("input");
			btrainid.name="btrainid";
			btrainid.value=trainbidinput;
			temp.appendChild(btrainid);
			
			var traina_startid=document.createElement("input");
			traina_startid.name="traina_startid";
			traina_startid.value=traina_startidinput;
			temp.appendChild(traina_startid);
			
			var traina_middleid=document.createElement("input");
			traina_middleid.name="traina_middleid";
			traina_middleid.value=traina_middleidinput;
			temp.appendChild(traina_middleid);
			
			var trainb_middleid=document.createElement("input");
			trainb_middleid.name="trainb_middleid";
			trainb_middleid.value=trainb_middleidinput;
			temp.appendChild(trainb_middleid);
			
			
			
			var train_arriveid=document.createElement("input");
			train_arriveid.name="train_arriveid";
			train_arriveid.value=train_arriveidinput;
			temp.appendChild(train_arriveid);
			
			
			var traina_name=document.createElement("input");
			traina_name.name="traina_name";
			traina_name.value=traina_name_input;
			temp.appendChild(traina_name);
		
			var trainb_name=document.createElement("input");
			trainb_name.name="trainb_name";
			trainb_name.value=trainb_name_input;
			temp.appendChild(trainb_name);
			
			var start_middle=document.createElement("input");
			start_middle.name="start_middle";
			start_middle.value=start_middleinput;
			temp.appendChild(start_middle);
			
			var middle_arrivei=document.createElement("input");
			middle_arrivei.name="middle_arrive";
			middle_arrivei.value=start_middleinput;
			temp.appendChild(middle_arrivei);
			
			var start_middle_t=document.createElement("input");
			start_middle_t.name="start_middle_t";
			start_middle_t.value=start_middle_tinput;
			temp.appendChild(start_middle_t);
			
			var middle_arrive_t=document.createElement("input");
			middle_arrive_t.name="middle_arrive_t";
			middle_arrive_t.value=middle_arrive_tinput;
			temp.appendChild(middle_arrive_t);
			
			var countas=document.createElement("input");
			countas.name="counta";
			countas.value=counta;
			temp.appendChild(countas);
			alert(counta);
			var countbs=document.createElement("input");
			countbs.name="countb";
			countbs.value=countb;
			temp.appendChild(countbs);
			
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
				
				var req;
				if(window.XMLHttpRequest){
				req=new XMLHttpRequest();
				
				}else{//不直接支持ajax
			      req=new ActiveObject('Microsoft.XMLHTTP');
			    
		   		 }
					req.onreadystatechange=function(){
			  		if (req.readyState==4&&req.status==200) {
			   			 //获取服务器的响应值
			 		/* var result=req.responseText;
			   		alert(result);  */
			   			 //后续操作
			      	var obj = eval("(" + req.responseText + ")");
			       	/* alert(obj);  */
			       	/* var trObj=document.getElementById("tb"); */
			      	var trObj=document.getElementById("tb");
			       	trObj.innerHTML="";
			       	if(obj.length!=0){
			       	for (var i = 0; i < obj.length; i++) {	
			       		/* alert(obj[i].middieidb);
			       		alert(obj[i].counta);
			       		alert(obj[i].countb); */
			       		/*  var tail="<form name=\"myform"+i+"\" action=\"../Servlet/S_buy\" method=\"post\">"; */
			       		var tail="";
			       		var id=obj[i].trainida+""+obj[i].trainidb+""+obj[i].middieida+""+obj[i].middieidb;
			       		
			       		tail+="<input class=\"inputtxt\" id=\"counta"+id+"\" type=\"text\" value="+obj[i].counta+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"countb"+id+"\" type=\"text\" value="+obj[i].countb+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"traina_startidinput"+id+"\" type=\"text\" value="+obj[i].startid+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"traina_middleidinput"+id+"\" type=\"text\" value="+obj[i].middieida+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"trainb_middleidinput"+id+"\" type=\"text\" value="+obj[i].middieidb+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"train_arriveidinput"+id+"\" type=\"text\" value="+obj[i].arriveid+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"trainaidinput"+id+"\" type=\"text\" value="+obj[i].trainida+" style=\" display:none\">";
			       		tail+="<input class=\"inputtxt\" id=\"trainbidinput"+id+"\" type=\"text\" value="+obj[i].trainidb+" style=\" display:none\">";
			       		
			       		tail+="<tr><td>"+"<input class=\"inputtxt\" id=\"traina_name_input"+id+"\" type=\"text\" value="+obj[i].atrain_name+">"+"<br>"+
			       		"<input class=\"inputtxt\" id=\"trainb_name_input"+id+"\" type=\"text\" value="+obj[i].btrain_name+"></td>";
			       		tail+="<td>"+"<input class=\"inputtxt\" id=\"start_middleinput"+id+"\" type=\"text\" value="+obj[i].start_middle+">"+"<br>"+
			       		"<input class=\"inputtxt\" id=\"middle_arrive"+id+"\" type=\"text\" value="+obj[i].middle_arrive+"></td>";
			       		tail+="<td>"+"<input class=\"inputtxt\" id=\"start_middle_tinput"+id+"\" type=\"text\" value="+obj[i].start_middle_t+">"+"<br>"+
			       		"<input class=\"inputtxt\" id=\"middle_arrive_tinput"+id+"\" type=\"text\" value="+obj[i].middle_arrive_t+"></td>";
			       		tail+="<td>"+obj[i].asw+"<br>"+obj[i].bsw+"</td>";
			       		tail+="<td>"+obj[i].ayd+"<br>"+obj[i].byd+"</td>";
			       		tail+="<td>"+obj[i].aed+"<br>"+obj[i].bed+"</td>";
			       		tail+="<td>"+obj[i].aot+"<br>"+obj[i].bot+"</td>";
			       		tail+="<td>"+"<input class=\"inputbut\" type=\"button\" name=\"train"+id+"\" onClick=\"buy("+id+")\" value=\"预订\"></td></tr>";
			       		
			     		trObj.innerHTML+=tail;
					}
			      	     
				}
			       	else {
						alert("请等待");
					}
				}
					};
			/* alert(tick_s+"::"+tick_a+"::"+tick_t); */
        	//设置传送方式，对应的servlet或action路径，是否异步处理
			req.open("POST", "../Servlet/S_trans", true);
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
			month="0"+(date_now.getMonth()+1);
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
			<button class="ticket_but" style="width: 80px;height: 30px; color: blue" onclick="myorder()"> 我的车票</button>
			
		</div>		
	</div>
	<div class="guider">
		<ul>
			<!--用jQuery实现页面的切换-->
			<li class="btns"><button class="li_button" onClick="Page2()">回到单程</button></li>
			<li class="btns"><button class="li_button" onClick="Page1()">回到首页</button></li>
		</ul>
	</div>
	  
	  
	<div class="search">
		<div class="search_info">
			<form action="../Servlet/S_select" method="post" name="ticketsform" class="info_form">
			<ul>
			<li class="info_start">
				<p class="info_table" style="display: inline-block">出发地</p>
				<input type="text" class="cityinput" id="citySelect1" name="start_station" placeholder="请输入出发地">
			</li>
			<li class="info_start">
				<p class="info_table" style="display: inline-block">目的地</p>
				<input type="text" class="cityinput" id="citySelect2" name="arrive_station" placeholder="请输入目的地">
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
		