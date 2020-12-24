<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/cityselect.css">
<style type="text/css">
	.demo{width:220px;margin:50px auto;}
	.demo input{padding: 12px 18px;}
</style>

</head>
<body>
<h3 style="text-align: center;margin-top: 15px;">在下面的输入框中输入城市的名称：</h3>
<div class="demo">
	<!-- 在输入框加入id -->
	<p style="display: inline-block;">出发地</p><input type="text" class="cityinput" id="citySelect1" placeholder="请输入出发地">
	<p style="display: inline-block;">目的地</p><input type="text" class="cityinput" id="citySelect2" placeholder="请输入目的地">
	<script type="text/javascript" src="js/cityselect.js"></script>
	<script type="text/javascript">
	var test=new Vcity.CitySelector({input:'citySelect1'});
	var test=new Vcity.CitySelector({input:'citySelect2'});
</script>
</div>
</html>