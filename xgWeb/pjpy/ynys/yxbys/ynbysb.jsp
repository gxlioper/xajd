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
    <html:form action="/pjpyynyswh" method="post">
      <div align="center" style="font-size:18px;font:'黑体' ">${tit }</div>
<br/>
<div>
<table width="98%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="10%" scope="col">姓  名</th>
    <td height="30" colspan="2" style="width:15%" scope="col" align="center">&nbsp;<bean:write name="rs" property="xm"/></td>
    <th height="30" width="11%" scope="col">性  别</th>
    <td height="30" colspan="2" scope="col" style="width:10%" align="center">&nbsp;<bean:write name="rs" property="xb"/></td>
    <th height="30" width="10%" scope="col">民  族</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;<bean:write name="rs" property="mz"/></td>
    <th height="30" width="10%" scope="col">出生年月</th>
    <td height="30" colspan="2" style="width:15%" scope="col" align="center">&nbsp;<bean:write name="rs" property="csrq"/></td>
  </tr>
  <tr>
  	<th height="30" colspan="2" width="14%" scope="col">生源地</th>
    <td height="30" colspan="3" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="syd"/></td>
    <th height="30" colspan="2" scope="row">政治面貌</th>
    <td colspan="3" align="center">&nbsp;<bean:write name="rs" property="zzmm"/></td>
    <th><div align="center">健康状况</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name="rs" property="jkzk"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">所学专业</th>
    <td colspan="5" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    <th height="30" colspan="2" scope="row">入学时间</th>
    <td colspan="4" align="center">&nbsp;<bean:write name="rs" property="rxrq"/></td>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">家庭详细地址</th>
    <td colspan="10" align="center">&nbsp;<bean:write name="rs" property="jtdz"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>&nbsp;</p>
    <p>优</p>
    <p>秀</p>
    <p>事</p>
    <p>迹</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="12" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxsj"/></p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%" >
    <p>班</p>
    <p>级</p>
    <p>意</p>
    <p>见</p>
    </th>
    <th colspan="12" scope="row">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjyj"/></div>
    <p>&nbsp;</p>
    <p  align="right">班主任（签名）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
  	<th scope="row" style="width:6%">
  		<p>院</p>
    	<p>系</p>
    	<p>推</p>
    	<p>荐</p>
    	<p>意</p>
    	<p>见</p>
  	</th>
  	<th colspan="6">
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxyj"/></div>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
    	<p align="right">负责人签字（公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    	<p align="right">年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
  	</th>
  	<th scope="row" style="width:6%">
  		<p>学</p>
    	<p>校</p>
    	<p>审</p>
    	<p>核</p>
    	<p>意</p>
    	<p>见</p>
  	</th>
  	<th colspan="5">
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/></div>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
    	<p align="right">学校（公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    	<p align="right">年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
  	</th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>省</p>
    <p>教</p>
    <p>育</p>
    <p>厅</p>
    <p>意</p>
    <p>见</p>
    </th>
    <th colspan="12" scope="row">
    <p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jytyj"/></div>
  		<p>&nbsp;</p>
    <p  align="right">公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
    
   </th>
  </tr>
</table>
 	&nbsp;&nbsp;云南省教育厅学生处制表    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
 	<logic:notEmpty name="rs">
 		填表日期: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="tbrq"/>
 	</logic:notEmpty>
 	<logic:empty name="rs">
 		填表日期: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;
 	</logic:empty>
 	</div>
    </html:form>
  </body>
</html:html>
