<%@page import="DS_operation.Train_leftickets_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String custom_id=request.getSession().getAttribute("custom_id").toString();
	String custom_name=request.getSession().getAttribute("custom_name").toString();
	int train_id=Integer.parseInt(request.getSession().getAttribute("train_id").toString());
	String train_name=request.getSession().getAttribute("train_name").toString();
	String start_arrive=request.getSession().getAttribute("start_arrive").toString();
	int start_station_id=Integer.parseInt(request.getSession().getAttribute("start_station_id").toString());
	int arrive_station_id=Integer.parseInt(request.getSession().getAttribute("arrive_station_id").toString());
	double SW=(double)(request.getSession().getAttribute("SW"));
	double YD=(double)(request.getSession().getAttribute("YD"));
	double ED=(double)(request.getSession().getAttribute("ED"));
	double OT=(double)(request.getSession().getAttribute("OT"));
	String startt_arrivet=request.getSession().getAttribute("startt_arrivet").toString();
	String run_date=request.getSession().getAttribute("run_date").toString();
	Train_leftickets_info info=(Train_leftickets_info)request.getSession().getAttribute("lefticket"); 
	
	/* String custom_id="15589359936".toString();
	String custom_name="董冰清";
	int train_id=6666;
	String train_name="G985".toString();
	String start_arrive="我的现在一直在-我的未来也会来".toString();
	int start_station_id=0;
	int arrive_station_id=0;
	double SW=35.5;
	double YD=25.5;
	double ED=15.5;
	double OT=15.5;
	String startt_arrivet="00:00:00-24:00:00".toString();
	String run_date="anytime";
	Train_leftickets_info info=new Train_leftickets_info();
	info.setTrain_id(6666);
	info.setStart_staion_id(0);
	info.setArrive_station_id(0);
	info.setSW(100);info.setYD(0);info.setED(0);info.setOT(100); */

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="../css/confirm.css">
  <link rel="stylesheet" href="../jquery-ui-1.12.1/jquery-ui.css">
  <script src="../jquery-ui-1.12.1/external/jquery/jquery.js"></script>
  <script src="../jquery-ui-1.12.1/jquery-ui.js"></script>



<script type="text/javascript">

var counttt=0;
function dele(e){	
		$(e).parent().parent().remove();
		var para=$("#buy_detail tr").length;//获取tbody删除一行后tr的个数
		//alert("PARA="+para);
	    for(let i=0;i<para;i++){ //循环遍历
	    	//将遍历后的i给到每行的序号中
	        var j=i+1;
	        $("#buy_detail").find("tr").eq(i).find("td").eq(0).html(j);
	    }
		counttt=para;
		//alert(counttt);
}

var opt={
		autoOpen: false,
    	title:"乘车人信息",
        resizable: false,
        height: "auto",
        width: 500,
        modal: true,
        buttons: {
            "确认添加": function() {
           	
            //这里可以加入一些操作
            //给下面的赋值
             var taker_name=$("#takername").val();
             var taker_idcard=$("#takeridcard").val();
             var takertel=$("#takertel").val();
             var ticket_type=$('#ticket_type_age').val();
             var seat_type=$('#seat_type').val();
             var ticket_cost=0.00;
             var ticket_type_string="";
             switch (seat_type) {
				case "SW":
					ticket_cost='<%=SW%>';
					ticket_type_string="商务座";
					break;
				case "YD":
					ticket_cost='<%=YD%>';
					ticket_type_string="一等座";
					break;
				case "ED":
					ticket_cost='<%=ED%>';
					ticket_type_string="二等座";
					break;
				case "OT":
					ticket_cost='<%=OT%>';
					ticket_type_string="其他座次类型";
					break;
			}
             switch (ticket_type) {
				case "学生票":
					ticket_cost=ticket_cost*0.75;
					break;
				case "成人票":
					ticket_cost=ticket_cost;
					break;
			}
             //判断是否与上面的相同
             if(taker_name.length<=0||taker_idcard.length<=0||takertel.length<=0){
                  alert("输入完整信息");
			}
             else{
            	 //考虑一下删除的情况吧
            	//获取tbody  tr的个数
            	var para=$("#buy_detail tr").length;
            	var dd=0;
            
            	for(var j=0;j<para;j++){
            		var t1=$("#buy_detail").find("tr").eq(j).find("td").eq(4).find("input").val();
           
            		if(t1==taker_idcard){
            		dd=1;
            		alert("身份证号重复，请检查信息是否正确");
            		}
            	}
                 
            	
            	
            	if(dd==0){
            		counttt++;
              		$("#buy_detail").append('<tr><td>'+counttt+'</td>'
             		+'<td><input class="taker_input" type="text" value="'+ticket_type+'"disabled></td>'
             		+'<td><input class="taker_input" type="text" value="'+ticket_type_string+'"disabled></td>'
             		+'<td><input class="taker_input" type="text" value="'+taker_name+'"disabled></td>'
             		+'<td><input class="taker_input" type="text" value="'+taker_idcard+'" disabled></td>'
             		+'<td><input class="taker_input" type="text" value="'+takertel+'" disabled></td>'
             		+'<td><input class="taker_input" type="text" value="'+ticket_cost+'" disabled></td>'
             		+'<td><button class="dele_but" id="delebut" onclick="dele(this)" >删除</button></td>'
             		+'</tr>'); 
            		$("#takername").val("");
             		$("#takeridcard").val("");
            				 $("#takertel").val("");
            	}
             }
             $(this).dialog("close");
             },
              "取消": function() {
            	  $("#takername").val(0);
                  $("#takeridcard").val(0);
                  $("#takertel").val(0);
                  $(this).dialog("close");
              }
       }   	
}
	
$(document).ready(function (){
	var eds=<%=info.getED()%>;
	if(eds==0){ $("#edchose").attr("disabled",""); $("#seat_type").val("YD");}
	
	var yds=<%=info.getYD()%>;
	if(yds==0) {$("#ydchose").attr("disabled",""); $("#seat_type").val("SW");}
	
	var sws=<%=info.getSW()%>;
	if(sws==0) {$("#swchose").attr("disabled",""); $("#seat_type").val("OT");}

	var ots=<%=info.getOT()%>;
	if(ots==0) {$("#otchose").attr("disabled","");}
	
		
    /*     $("#dialog_confirm").dialog(opt); */
		$("#button_taker").click(function(e){
			$("#dialog_confirm").dialog(opt);
			$("#dialog_confirm").dialog("open");
		
		});
    
    	$("#submit_but").click(function(e){
    		//检查是否有值
    		//获取tbody tr的个数
    		var dataall=[];
    		var total_cost=0.0;
    		var para=$("#buy_detail tr").length;
    		if(para==0){
    			alert("请添加乘车人信息");
    		}
    		else{
    		for(var j=0;j<para;j++){
    			var person=new Object();
    			var customid='<%=custom_id%>';
    			var trainid='<%=train_id%>';
    			var start_arrive_s='<%=start_arrive%>';
       			var start_stationid='<%=start_station_id%>';
    			var arrive_stationid='<%=arrive_station_id%>';
    			var start_arrive_t='<%=startt_arrivet%>'
        		var t2=$("#buy_detail").find("tr").eq(j).find("td").eq(1).find("input").val();
        		var t3=$("#buy_detail").find("tr").eq(j).find("td").eq(2).find("input").val();
        		var t4=$("#buy_detail").find("tr").eq(j).find("td").eq(3).find("input").val();
        		var t5=$("#buy_detail").find("tr").eq(j).find("td").eq(4).find("input").val();
        		var t6=$("#buy_detail").find("tr").eq(j).find("td").eq(5).find("input").val();
        		var t7=$("#buy_detail").find("tr").eq(j).find("td").eq(6).find("input").val();
        		alert(""+t2+" "+t3+" "+t4+" "+t5+" "+t6+" "+t7)
        		person.customid=customid;
        		person.trainid=trainid;
        		person.start_arrive=start_arrive_s;
        		person.start_arrive_t=start_arrive_t;
        		person.start_stationid=start_stationid;
        		person.arrive_stationid=arrive_stationid;
        		person.ticket_type_age=t2;
        		person.ticket_type=t3;
        		person.taker_name=t4;
        		person.taker_idcard=t5;
        		person.taker_tel=t6;
        		person.cost=t7;
        		dataall.push(person);
        		total_cost+=parseFloat(t7);
        	}
    	
    		var json_str=JSON.stringify(dataall); 
    		//alert("确认提交"+json_str);
    		alert("价格为:"+total_cost);
    		 $.ajax({
                type: "POST",
                url: "../Servlet/S_order",
                data: json_str,
                contentType: "application/json;charset=UTF-8",
                success: function(result){
                    alert(result);
                  //跳转到指定页面
                },
                error:function(){
                	alert("error");
                }
            	}); 
    		}
    	});
    	
    	 
    	
  	});        
       
  
	function Page1(){
			window.location.href="../index.html";
//		window.location.href="http://blog.sina.com.cn/mleavs";
	}
	function Page2(){
		window.location.href="../leftTicket/leftTicket.jsp";
//	window.location.href="http://blog.sina.com.cn/mleavs";
}
	function Page3(){
		window.location.href="../leftTicket/transTickets.jsp";
//	window.location.href="http://blog.sina.com.cn/mleavs";
}
	function myorder(){
		window.location.href="../Myorder/myorder.jsp";
//	window.location.href="http://blog.sina.com.cn/mleavs";
}
	
//	}
</script>

</head>

<body>
		<!--设置背景图片-->
		<div class="head_logo">
			<!--这是logo-->
		</div>
		<div class="headticket">
			<!--这是我的车票按钮-->
			<button class="ticket_but" style="width: 80px; height: 30px;">退出</button>
			<button class="ticket_but" style="width: 80px; height: 30px;"><%=custom_name %></button>
			<button class="ticket_but"
				style="width: 80px; height: 30px; color: blue" onclick="myorder()">我的车票</button>

		</div>
		<div class="guider">
		<ul>
			<!--用jQuery实现页面的切换-->
			<li class="btns"><button class="li_button" onClick="Page1()">回到首页</button></li>
			<li class="btns"><button class="li_button" onClick="Page2()">直达</button></li>
			<li class="btns"><button class="li_button" onClick="Page3()">转乘</button></li>
		</ul>
	</div>
	
		<div id="dialog_confirm" style="display: none">
		<p style="width: 100px; display: inline-block">票据类型</p>
		<select class="taker_input" id="ticket_type_age"><option value="成人票">成人票</option><option value="学生票">学生票</option></select>
		<br>
		<p style="width: 100px; display: inline-block">座位类型</p>
		<select class="taker_input" id="seat_type">
     		<option id="edchose" value="ED">二等座</option>
     		<option id="ydchose" value="YD">一等座</option>
     		<option id="swchose" value="SW">商务座</option>
     		<option id="otchose" value="OT">其他<option>
     	</select>
		<br>
		
		<p style="width: 100px; display: inline-block">乘客名</p>
		<input style="width: 400px;" class="tanchuinput" id="takername"
			type="text" placeholder="请输入乘客名"> 
		<br>
		<p style="width: 100px; display: inline-block">乘客证件号码</p>
		<input style="width: 400px;" class="tanchuinput" id="takeridcard"
			type="text" placeholder="请输入正确的证件号码">
		<br>
		<p style="width: 100px; display: inline-block">乘客手机号</p>
		<input style="width: 400px;" class="tanchuinput" id="takertel"
			type="text" placeholder="请输入手机号"> <br></br>
		
		</div>
		<div class="main">
			
			<div class="traininfo">
				<div class="tain_info_head">
					<p>列车信息（以下余票信息仅供参考）</p>
				</div>
				<div class="train_info_main">
					<input class="train_info_input" type="text" disabled value=<%=run_date %>> 
					<input class="train_info_input" type="text" disabled value=<%=train_name %>> 
					<input class="train_info_input" type="text" disabled value=<%=start_arrive %>> 
					<p style="display: inline-block">-</p>
					<input class="train_info_input" type="text" disabled value=<%=startt_arrivet %>>
				</div>
				<div class="train_ticket">
					<!--显示所有有票的座次-->
					<p style="display: inline-block; margin-left: 30px">商务座(￥<%=SW %>)  <%=info.getSW() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">一等座(￥<%=YD %>)  <%=info.getYD() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">二等座(￥<%=ED %>)  <%=info.getED() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">其他(￥<%=OT %>)  <%=info.getOT() %>张票</p>
				</div>
			</div>
			<div class="taker_info">

				<div class="tain_info_head">
					<p>乘客信息</p>
				</div>
				<div class="taker_info_head">
					<div class="taker_head1">
						<img src="../images/background.jpg" width="30px">
						<p style="display: inline-block">乘车人:</p>
						<button class="button_taker" id="button_taker">添加乘车人</button>
					</div>
					
				</div>
				<div class="taker_info_main" id="webPage4">
				<form>
					<table id="table_taker">
						<thead>
							<tr>
								<th style="width: 120px">序号</th>
								<th style="width: 120px">票种</th>
								<th style="width: 120px">席别</th>
								<th style="width: 120px">姓名</th>
								<th style="width: 120px">身份证号</th>
								<th style="width: 120px">手机号</th>
								<th style="width: 120px">价格</th>
								<th style="width: 120px">操作</th>
							</tr>
						</thead>
						<tbody id="buy_detail">
						
						</tbody>
					</table>
				</form>
				</div>
				<div class="taker_info_submit">
					<input id="submit_but" class="submit_button" type="button" 	value="购买">
				</div>

			</div>
		</div>
		
	
</body>
</html>
