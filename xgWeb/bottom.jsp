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
<%@ page import = "xgxt.DAO.JspDAO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy="document.selection.empty()" onselect="document.selection.empty()">
		<div class="bottomframe">
			<div style="float:left;margin:2px 0px 0px 5px;">
				登录号：
				${userName }
				, 姓名：
				${userNameReal }
				<% JspDAO dao = new JspDAO();
				String userDep = null != session.getAttribute("userDep") ? session.getAttribute("userDep").toString() : "";
			  	String bmmc = dao.getBmmcByDmForJsp(userDep);%>
			  , 所属部门：
				<%=bmmc %>
				<%--长沙民政--%>
				<logic:equal value="10827" name="xxdm">
				<logic:equal value="student" name="userOnLine">
				, 所属班级：
				${bjmc }
				</logic:equal>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<script language="javascript">
dayName = new Array("", "周一", "周二", "周三", "周四", "周五", "周六", "周日")
monName = new Array("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月")
now = new Date();
document.write(now.getFullYear() + "年 " + monName[now.getMonth()] + " " + now.getDate() + "日 " + dayName[now.getDay()]);
</script> 
			
			<logic:equal value="11417" name="xxdm">
				<logic:notEqual value="放假" name="LoginZc">
				(第<b>${LoginZc }</b>教学周)
				</logic:notEqual>
				<logic:equal value="放假" name="LoginZc">
				<b>放假</b>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b>${LoginXn }</b>学年度&nbsp;第<b>${LoginXq }</b>学期
			</logic:equal>
			</div>
			<a href="http://www.zfsoft.com" target="_blank"><div class="zflogo"></div>
			</a>
		</div>
	</body>
</html>
