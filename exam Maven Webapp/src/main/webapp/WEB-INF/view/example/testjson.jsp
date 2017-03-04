<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="renderer" content="webkit">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
	<input type="button" id="aa" value="获取表单数据">
		<script id="tableData" type="text/html">
			<tr>
				<td>name</td>
				<td>email</td>
				<td>phone</td>
			</tr>
			{{# for(var i=0;i<d.list.length;i++ ){
					var data = d.list[i];
			}}
				<tr>
					<td>{{data.username}}</td>
					<td>{{data.userEmail}}</td>
					<td>{{data.userPhone}}</td>
				</tr>
			{{# } }}
		</script>
		<table id="table" border="1"> </table>
		
	</body>
	<script type="text/javascript" src="/exam/static/js/common/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="/exam/static/js/common/laytpl.js"></script>
	<script>
	$(function() {
	$("#aa").click(function(){
		$.getJSON("/exam/bg/example",{"to":"json"},function(data){
			table(data);
		})
	})
	});
	//数据写入表格
	function table(data){
		var tableData=document.getElementById("tableData").innerHTML;
		laytpl(tableData).render(data,function(tableData){
			document.getElementById("table").innerHTML=tableData;
		});
	}
	</script>
</html>