<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>	
	<script>
		function yz(){
       		var url = document.getElementById("toPage").value;
       		if((url == null) || (url=="")){
       			alert("请选择要统计查询的项目!");
       			return false;
       		}
       		refreshForm(url);
		}
	</script>
	<body>		
		<html:form action="/pjpyxfjs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 学风建设 - 统计查询
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr valign="middle" >
						<td align="center">
							<b style="font-size:14">请选择统计查询项目</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select property="dyym" styleId="toPage">
								<html:option value="">--------请选择--------</html:option>
								<html:option value="pjpyxfjs.do?method=bjccqkSearch">抽查情况</html:option>
								<html:option value="pjpyxfjs.do?method=xskqqkSearch">学生考勤</html:option>
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input  class="button2" onclick='yz()' style="width:80px" value="确  定" />
					</td>
				</tr>
			</table>			
		</html:form>
	</body>
</html>
