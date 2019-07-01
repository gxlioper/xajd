<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  
  <body>
    <html:form action="">
      <div align="center" style="font-family:黑体;font-size:20px ">校级＃＃＃登记表</div>
		<br>
		_____________学年                        ________________班级
		
		__________系（院）
		<table width="90%"  border="1">
		  <tr>
		    <th colspan="2" scope="col">姓名</th>
		    <th width="15%" scope="col">&nbsp;</th>
		    <th width="7%" scope="col">性别</th>
		    <th width="17%" scope="col">&nbsp;</th>
		    <th width="16%" scope="col">职务</th>
		    <th colspan="2" scope="col">&nbsp;</th>
		  </tr>
		  <tr>
		    <th colspan="2" scope="row">政治面貌</th>
		    <td>&nbsp;</td>
		    <td><div align="center">民族</div></td>
		    <td>&nbsp;</td>
		    <td colspan="2"><div align="center">综合评分名次/总分成绩名次</div></td>
		    <td width="12%">&nbsp;</td>
		  </tr>
		  <tr>
		    <th colspan="2" scope="row">家庭详细地址</th>
		    <td colspan="4">&nbsp;</td>
		    <td width="22%"><div align="center">电话</div></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <th width="5%" scope="row"><p>主</p>
		    <p>要</p>
		    <p>事</p>
		    <p>迹</p></th>
		    <th colspan="7" scope="row">&nbsp;</th>
		  </tr>
		  <tr>
		    <th height="114" colspan="5" scope="row">
		      <div style="left:2px;top:1px;position:inherit">
		        <p>班主任意见：</p>
		        <p>签名：</p>
		        <p>年 月 日 </p>
		    </div>	</th>
		    <td colspan="3"><blockquote>
		      <blockquote>
		        <p>系（院）意见：</p>
		        <p>签名：</p>
		        <p>年 月 日 </p>
		      </blockquote>
		    </blockquote></td>
		  </tr>
		  <tr>
		    <th height="127" colspan="8" scope="row"><p><bean:message key="lable.xsgzyxpzxy" />意见：</p>
		    <p>盖章：</p>
		    <p>年 月 日 </p></th>
		  </tr>
		</table>
    </html:form>
  </body>
</html:html>
