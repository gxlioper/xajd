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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
	function change(){
		var type = document.getElementById("bmxm").value;
		if(type=="考研"){
			document.getElementById("bybmqx").style.display="none";
			document.getElementById("bybmky").style.display="inline";
			document.getElementById("bybmsq").style.display="none";
		}else if(type=="社区助理"){
			document.getElementById("bybmqx").style.display="none";
			document.getElementById("bybmky").style.display="none";
			document.getElementById("bybmsq").style.display="inline";
		}else{
			document.getElementById("bybmqx").style.display="inline";
			document.getElementById("bybmky").style.display="none";
			document.getElementById("bybmsq").style.display="none";
		}
	}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="change();">
<html:form action="/yxjzyjs.do" method="post">
		
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>报名信息查看</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						报名项目：
					</td>
					<td>  
						<html:text name="rs1" property="bmxm" readonly="true"/>
					</td>
					<td align="right">
						学号：
					</td>
					<td>
						<html:text name="rs1" property="xh" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td>  
						<html:text name="rs1" property="xm" style="width: 70%" readonly="true"/>
					</td>
					<td align="right">
						性别：
					</td>
					<td>
						<html:text name="rs1" property="xb" style="width: 70%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						系(院)名称：
					</td>
					<td>
						<html:text name="rs1" property="xydm" style="width: 70%" readonly="true"/>
					</td>
					<td align="right">
						专业名称：
					</td>
					<td>
						<html:text name="rs1" property="zydm" style="width: 70%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						生源地：
					</td>
					<td>  
						<html:text name="rs1" property="syd" style="width: 70%" readonly="true"/>
					</td>
					<td align="right" id="bybmqx" style="display: none;">
						报考区县：
					</td>
						<td align="right" id="bybmky">
						报考学校：
					</td>
						<td align="right" id="bybmsq" style="display: none;">
						报考社区：
					</td>
					<td>
						<html:text name="rs1" property="bkqx" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						联系方式：
					</td>
					<td>  
						<html:text name="rs1" property="lxfs" style="width: 70%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="bz" style="width: 100%" rows="8" readonly="true"></html:textarea>
					</td>
				</tr>
			</table>
		</logic:iterate>
		</html:form>
	</body>
</html>
