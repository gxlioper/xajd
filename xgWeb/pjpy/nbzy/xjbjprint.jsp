<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
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
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'黑体' "><bean:write name='rs' property="title"/></div>
<br/>

<table width="98%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">院   系</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th height="30" width="11%" scope="col">班  级</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="bjmc"/></td>
    <th height="30" width="10%" colspan="2" scope="col">学  年</th>
    <td height="30" colspan="" scope="col" align="center">&nbsp;<bean:write name='rs' property="xn"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">班主任</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th><div align="center">班  长</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzxm"/></td>
    <th colspan="2"><div align="center">团支书</div></th>
    <td colspan="" height="30" width="13%" align="center">&nbsp;<bean:write name='rs' property="tzs"/></td>
  </tr>
  <tr>
    <th height="30" colspan="5" scope="row" align="left">班内有无不合格寝室</th>
    <td colspan="5" align="center">&nbsp;有(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;) 无(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)</td>
  </tr>
  <tr>
  	<th colspan="5" height="30"><div align="left">有无同学受过行政,党,团纪处分</div></th>
    <td colspan="5" align="center">&nbsp;有(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;) 无(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)</td>
  </tr>
  <tr>
    <th scope="row" style="width:14%">
    <p>&nbsp;</p>
       班级<br/>集体<br/>荣誉
    <p>&nbsp;</p>
    </th>
    <th colspan="9" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  <tr>
    <th scope="row" colspan="10" >
    <p align="left">
      主要成绩简介
    </p>
    <br/><br/><br/><br/><br/>
    <p align="right">(总结另附页 1500-2000 字)</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="10" >
    <p align="left">
     <bean:message key="lable.xsgzyxpzxy" />党总支意见
    </p>
    <br/><br/><br/>
    <p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="10" >
    <p align="left">
     <bean:message key="lable.xsgzyxpzxy" />学工部意见
    </p>
    <br/><br/>
    <p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="10" >
    <p align="left">
     <bean:message key="lable.xsgzyxpzxy" />意见
    </p>
    <br/><br/>
    <p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
	</table>
    </html:form>
  </body>
</html:html>
