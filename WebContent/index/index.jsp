<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* String name=request.getSession().getAttribute("name").toString();
	String result="登录";
	if(name!=null){
		result=name;		
	} */
	String result="董冰清";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的12306</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/cityselect.css">

<script type="text/javascript">
	var test=new Vcity.CitySelectoy({input:'citySelect'});
	
	function Page1(){
		var div=document.getElementById("ticket_select");
		div.style.display="";
		var div=document.getElementById("ticket_trans");
		div.style.display="none";
	}
	function Page2(){
		var div=document.getElementById("ticket_select");
		div.style.display="none";
		var div=document.getElementById("ticket_trans");
		div.style.display="";
	}
	function Login(){
		var but=document.getElementById("mubutton").value;
		alert(but);
	}
	
	function sub_tickets(){
		var tick_s=document.getElementById("citySelect1").value;
		var tick_a=document.getElementById("citySelect2").value;
		var tick_t=document.getElementById("ticket_time").value;
		if(tick_s.length<=0||tick_a.length<=0||tick_t<=0){
			alert("请输入目的地、出发地、出发时间");
		}
		else{
			alert(tick_s+"::"+tick_a+"::"+tick_t);
			document.ticketsform.submit();
		}
		
	}
	function sub_trans() {
		
	}
	
</script>

</head>

<body>

	<div class="head" >
		<!--设置背景图片-->
		<div class="head_logo">
		<!--这是logo-->
		</div>
		<div class="headticket">
			<!--这是我的车票按钮-->
			<button class="ticket_but" style="width: 80px;height: 30px;">注册</button>
			<input type="button" id="mubutton" class="ticket_but" style="width: 80px;height: 30px;" onClick="my_but()" value=<%=result%>>
			<button class="ticket_but" style="width: 80px;height: 30px; color: blue"> 我的车票</button>
			
		</div>		
	</div>
	<div class="guider">
		<ul>
			<!--用jQuery实现页面的切换-->
			<li class="btns"><button class="li_button" onClick="Page1()">直达</button></li>
			<li class="btns"><button class="li_button" onClick="Page2()">换乘</button></li>
		</ul>
	</div>
	<div class="main">
		<div class="ticket_select" id="ticket_select">
			<form action="../Servlet/S_select" method="post" name="ticketsform">
			<ul>
			<li class="ticket_li"><p class="ticket_label">出发地</p><input type="text" class="cityinput" id="citySelect1" name="start_station" placeholder="请输入出发地"></li>
			<li class="ticket_li"><p class="ticket_label">目的地</p><input type="text" class="cityinput" id="citySelect2" name="arrive_station" placeholder="请输入目的地"></li>
			<script type="text/javascript" src="../js/cityselect.js"></script>
			<script type="text/javascript">
				var test=new Vcity.CitySelector({input:'citySelect1'});
				var test=new Vcity.CitySelector({input:'citySelect2'});
			</script>
			<li class="ticket_li"><p class="ticket_label">出发日期</p><input id="ticket_time" type="date" name="start_time" ></li>
			<li class="ticket_li"><input type="button" onclick="sub_tickets()" class="input_but" value="查询"></li>
			</ul>
			</form>
		</div>
		<div class="ticket_trans" id="ticket_trans" style="display: none">
			<form>
				<ul>
					<li class="ticket_li"><p style="color:red; display: inline-block;">*</p><p class="ticket_label">出发地</p><input type="text" class="cityinput" id="citySelect3" placeholder="请输入出发地"></li>
					<li class="ticket_li"><p style="color:transparent; display: inline-block;">*</p><p class="ticket_label">换乘城市</p><input type="text" class="cityinput" id="citySelect4" placeholder="请输入转乘城市"></li>
					<li class="ticket_li"><p style="color:red; display: inline-block;">*</p><p class="ticket_label">目的地</p><input type="text" class="cityinput" id="citySelect5" placeholder="请输入目的地"></li>
					<li class="ticket_li"><p style="color:red; display: inline-block;">*</p><p class="ticket_label">出发日期</p><input type="date"></li>
					<li class="ticket_li"><input type="button" onClick="sub_tarns()" class="input_but" value="查询"></li>
				</ul>
			</form>
	<script type="text/javascript">
		var test=new Vcity.CitySelector({input:'citySelect3'});
		var test=new Vcity.CitySelector({input:'citySelect4'});
		var test=new Vcity.CitySelector({input:'citySelect5'});
</script>	
		</div>
	</div>

</body>
</html>
