<%@page import="DS_operation.Train_leftickets_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		
 	 String custom_id=request.getSession().getAttribute("custom_id").toString();
	String custom_name=request.getSession().getAttribute("custom_name").toString();
	String traina_name=request.getSession().getAttribute("traina_name").toString();
	String trainb_name=request.getSession().getAttribute("trainb_name").toString();
	
	String start_middle=request.getSession().getAttribute("start_middle").toString();
	String middle_arrive=request.getSession().getAttribute("middle_arrive").toString();
	
	String start_middle_t=request.getSession().getAttribute("start_middle_t").toString();
	String middle_arrive_t=request.getSession().getAttribute("middle_arrive_t").toString();
	String arundate=request.getSession().getAttribute("arundate").toString();
	String brundate=request.getSession().getAttribute("brundate").toString();
	
	int atrainid=Integer.parseInt(request.getSession().getAttribute("atrainid").toString());
	int btrainid=Integer.parseInt(request.getSession().getAttribute("btrainid").toString());
	
	int traina_startid=Integer.parseInt(request.getSession().getAttribute("traina_startid").toString());
	int traina_middleid=Integer.parseInt(request.getSession().getAttribute("traina_middleid").toString());
	int trainb_middleid=Integer.parseInt(request.getSession().getAttribute("trainb_middleid").toString());
	int train_arriveid=Integer.parseInt(request.getSession().getAttribute("train_arriveid").toString());
	
	
	double ASW=(double)(request.getSession().getAttribute("ASW"));
	double AYD=(double)(request.getSession().getAttribute("AYD"));
	double AED=(double)(request.getSession().getAttribute("AED"));
	double AOT=(double)(request.getSession().getAttribute("AOT"));
	
	double BSW=(double)(request.getSession().getAttribute("BSW"));
	double BYD=(double)(request.getSession().getAttribute("BYD"));
	double BED=(double)(request.getSession().getAttribute("BED"));
	double BOT=(double)(request.getSession().getAttribute("BOT"));
	
	Train_leftickets_info infoa=(Train_leftickets_info)request.getSession().getAttribute("infoa"); 
	Train_leftickets_info infob=(Train_leftickets_info)request.getSession().getAttribute("infob");  
	
	
/*
	String custom_id="15589359936";
	String custom_name="董冰清";
	String traina_name="G985";
	String trainb_name="G211";
	String start_middle="过去-现在";
	String middle_arrive="现在-未来";
	String start_middle_t="00:00:00-24:00:00";
	String middle_arrive_t="00:00:00-24:00:00";
	
	int atrainid=1;
	int btrainid=2;
	int traina_startid=3;
	int traina_middleid=4;
	int trainb_middleid=5;
	int train_arriveid=6;
	
	
	double ASW=20.2;
	double AYD=20.2;
	double AED=20.2;
	double AOT=20.2;
	
	double BSW=20.2;
	double BYD=20.2;
	double BED=20.2;
	double BOT=20.2;
	
	Train_leftickets_info infoa=new Train_leftickets_info(); 
	infoa.setSW(5);infoa.setYD(5);infoa.setED(5);infoa.setOT(5);
	Train_leftickets_info infob=new Train_leftickets_info();
	infob.setSW(5);infob.setYD(5);infob.setED(5);infob.setOT(5);
	
	String arundate="2020-11-20";
	String brundate="2020-11-20";*/
	
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
		var para=$("#detail tr").length;//获取tbody删除一行后tr的个数
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
             var aticket_type=$('#aticket_type_age').val();
             var aseat_type=$('#aseat_type').val();
             
             var bticket_type=$('#bticket_type_age').val();
             var bseat_type=$('#bseat_type').val();
             
             var aticket_cost=0.00;
             var aticket_type_string="";
             switch (aseat_type) {
				case "SW":
					aticket_cost='<%=ASW%>';
					aticket_type_string="商务座";
					break;
				case "YD":
					aticket_cost='<%=AYD%>';
					aticket_type_string="一等座";
					break;
				case "ED":
					aticket_cost='<%=AED%>';
					aticket_type_string="二等座";
					break;
				case "OT":
					aticket_cost='<%=AOT%>';
					aticket_type_string="其他座次类型";
					break;
			}
             switch (aticket_type) {
				case "学生票":
					aticket_cost=aticket_cost*0.75;
					break;
				case "成人票":
					aticket_cost=aticket_cost;
					break;
			}
             
             var bticket_cost=0.00;
             var bticket_type_string="";
             switch (bseat_type) {
				case "SW":
					bticket_cost='<%=BSW%>';
					bticket_type_string="商务座";
					break;
				case "YD":
					bticket_cost='<%=BYD%>';
					bticket_type_string="一等座";
					break;
				case "ED":
					bticket_cost='<%=BED%>';
					bticket_type_string="二等座";
					break;
				case "OT":
					bticket_cost='<%=BOT%>';
					bticket_type_string="其他座次类型";
					break;
			}
          switch (bticket_type) {
				case "学生票":
					bticket_cost=bticket_cost*0.75;
					break;
				case "成人票":
					bticket_cost=bticket_cost;
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
             		+'<td><input class="taker_input2" type="text" value="'+aticket_type+'"disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+aticket_type_string+'"disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+bticket_type+'"disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+bticket_type_string+'"disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+taker_name+'"disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+taker_idcard+'" disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+takertel+'" disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+aticket_cost+'" disabled></td>'
             		+'<td><input class="taker_input2" type="text" value="'+bticket_cost+'" disabled></td>'
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
	var aeds=<%=infoa.getED()%>;
	if(aeds==0){ $("#aedchose").attr("disabled",""); $("#aseat_type").val("YD");}
	
	var ayds=<%=infoa.getYD()%>;
	if(ayds==0) {$("#aydchose").attr("disabled",""); $("#aseat_type").val("SW");}
	
	var asws=<%=infoa.getSW()%>;
	if(asws==0) {("#aswchose").attr("disabled",""); $("#aseat_type").val("OT");}

	var aots=<%=infoa.getOT()%>;
	if(aots==0) {$("#aotchose").attr("disabled","");}
	
	var beds=<%=infob.getED()%>;
	if(beds==0){ $("#bedchose").attr("disabled",""); $("#bseat_type").val("YD");}
	
	var byds=<%=infob.getYD()%>;
	if(byds==0) {$("#bydchose").attr("disabled",""); $("#bseat_type").val("SW");}
	
	var bsws=<%=infob.getSW()%>;
	if(bsws==0) {("#bswchose").attr("disabled",""); $("#bseat_type").val("OT");}

	var bots=<%=infob.getOT()%>;
	if(bots==0) {$("#botchose").attr("disabled","");}
	
		
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
    			var atrainid='<%=atrainid%>';
    			var btrainid='<%=btrainid%>';
    			var start_middle='<%=start_middle%>';
       			var middle_arrive='<%=middle_arrive%>';
       			var start_middle_t='<%=start_middle_t%>';
       			var middle_arrive_t='<%=middle_arrive_t%>';
    			var traina_startid='<%=traina_startid%>';
    			var traina_middleid='<%=traina_middleid%>';
    			var trainb_middleid='<%=trainb_middleid%>';
    			var train_arriveid='<%=train_arriveid%>'
        		var t2=$("#buy_detail").find("tr").eq(j).find("td").eq(1).find("input").val();
        		var t3=$("#buy_detail").find("tr").eq(j).find("td").eq(2).find("input").val();
        		var t4=$("#buy_detail").find("tr").eq(j).find("td").eq(3).find("input").val();
        		var t5=$("#buy_detail").find("tr").eq(j).find("td").eq(4).find("input").val();
        		var t6=$("#buy_detail").find("tr").eq(j).find("td").eq(5).find("input").val();
        		var t7=$("#buy_detail").find("tr").eq(j).find("td").eq(6).find("input").val();
        		var t8=$("#buy_detail").find("tr").eq(j).find("td").eq(7).find("input").val();
        		var t9=$("#buy_detail").find("tr").eq(j).find("td").eq(8).find("input").val();
        		var t10=$("#buy_detail").find("tr").eq(j).find("td").eq(9).find("input").val();
        		
        	
        		alert(""+t2+" "+t3+" "+t4+" "+t5+" "+t6+" "+t7+" "+t8+" "+t9+" "+t10);
        		
        		person.customid=customid;
        		person.atrainid=atrainid;
        		person.btrainid=btrainid;
        		person.start_middle=start_middle;
        		person.middle_arrive=middle_arrive;
        		person.start_middle_t=start_middle_t;
        		person.middle_arrive_t=middle_arrive_t;
        		person.traina_startid=traina_startid;
        		person.traina_middleid=traina_middleid;
        		person.trainb_middleid=trainb_middleid;
        		person.train_arriveid=train_arriveid;
        		person.aticket_type_age=t2;
        		person.aticket_type=t3;
        		person.bticket_type_age=t4;
        		person.bticket_type=t5;
        		person.taker_name=t6;
        		person.taker_idcard=t7;
        		person.taker_tel=t8;
        		person.costa=t9;
        		person.costb=t10;
        		dataall.push(person);
        		total_cost+=parseFloat(t9)+parseFloat(t10);
        	}
    	
    		var json_str=JSON.stringify(dataall); 
    		//alert("确认提交"+json_str);
    		alert("价格为:"+total_cost);
    		 $.ajax({
                type: "POST",
                url: "../Servlet/S_tranorder",
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
	function myorder(){
		window.location.href="../Myorder/myorder.jsp";
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
		</ul>
	</div>
	
		<div id="dialog_confirm" style="display: none">
		
		<p style="width: 100px; display: inline-block"><%=traina_name %>票据类型</p>
		<select class="taker_input" id="aticket_type_age"><option value="成人票">成人票</option><option value="学生票">学生票</option></select>
		
		<p style="width: 100px; display: inline-block">座位类型</p>
		<select class="taker_input" id="aseat_type">
     		<option id="aedchose" value="ED">二等座</option>
     		<option id="aydchose" value="YD">一等座</option>
     		<option id="aswchose" value="SW">商务座</option>
     		<option id="aotchose" value="OT">其他<option>
     	</select>
		<br>
		<p style="width: 100px; display: inline-block"><%=trainb_name %>票据类型</p>
		<select class="taker_input" id="bticket_type_age"><option value="成人票">成人票</option><option value="学生票">学生票</option></select>
		
		<p style="width: 100px; display: inline-block">座位类型</p>
		<select class="taker_input" id="bseat_type">
     		<option id="bedchose" value="ED">二等座</option>
     		<option id="bydchose" value="YD">一等座</option>
     		<option id="bswchose" value="SW">商务座</option>
     		<option id="botchose" value="OT">其他<option>
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
					<input class="train_info_input" type="text" disabled value=<%=arundate %>>
					<input class="train_info_input" type="text" disabled value=<%=brundate %>> 
					<input class="train_info_input" type="text" disabled value=<%=traina_name %>> 
					<input class="train_info_input" type="text" disabled value=<%=trainb_name %>> 
					<br>
					<input class="train_info_input" type="text" disabled value=<%=start_middle %>> 
					<p style="display: inline-block">-</p>
					<input class="train_info_input" type="text" disabled value=<%=middle_arrive %>> 
					<br>
					<input class="train_info_input" type="text" disabled value=<%=start_middle_t %>>
					<p style="display: inline-block">-</p>
					<input class="train_info_input" type="text" disabled value=<%=middle_arrive_t %>>
					
				</div>
				<div class="train_ticket">
					<!--显示所有有票的座次-->
					
					<p style="display: inline-block; margin-left: 30px">商务座(￥<%=ASW %>)  <%=infoa.getSW() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">一等座(￥<%=AYD %>)  <%=infoa.getYD() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">二等座(￥<%=AED %>)  <%=infoa.getED() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">其他(￥<%=AOT %>)  <%=infoa.getOT() %>张票</p>
					<br>
					<p style="display: inline-block; margin-left: 30px">商务座(￥<%=BSW %>)  <%=infob.getSW() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">一等座(￥<%=BYD %>)  <%=infob.getYD() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">二等座(￥<%=BED %>)  <%=infob.getED() %>张票</p>
					<p style="display: inline-block; margin-left: 30px">其他(￥<%=BOT %>)  <%=infob.getOT() %>张票</p>
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
								<th style="width: 120px">第一票种</th>
								<th style="width: 120px">第一席别</th>
								<th style="width: 120px">第二票种</th>
								<th style="width: 120px">第二席别</th>
								<th style="width: 120px">姓名</th>
								<th style="width: 120px">身份证号</th>
								<th style="width: 120px">手机号</th>
								<th style="width: 120px">第一张价格</th>
								<th style="width: 120px">第二张价格</th>
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
