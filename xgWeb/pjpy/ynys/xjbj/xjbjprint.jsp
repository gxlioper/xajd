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
<logic:notEmpty name="rs"><div align="right"><bean:write name="rs" property="tbrq"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></logic:notEmpty>
<logic:empty name="rs"><div align="right">填表日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></logic:empty>
<table width="98%" class="printstyle">
  <tr>
    <th height="25" colspan="2" style="width:8%" scope="col">院 (系)</th>
    <td height="25" colspan="3" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="xymc"/></td>
    <th height="25" width="8%" scope="col">年  级</th>
    <td height="25" colspan="4" scope="col" style="width:18%" align="center">&nbsp;<bean:write name="rs" property="nj"/></td>
    <th height="25" width="10%" colspan="2" scope="col">班&nbsp;&nbsp;&nbsp;级</th>
    <td height="25" colspan="3" scope="col" align="center">&nbsp;<bean:write name="rs" property="bjmc"/></td>
  </tr>
  <tr>
  	<th height="25" colspan="2" style="width:8%" scope="col">专 &nbsp;&nbsp; 业</th>
    <td height="25" colspan="4" style="width:20%" scope="col" align="center">&nbsp;<bean:write name="rs" property="zymc"/></td>
    <th height="25" colspan="2" scope="row" width="9%" >班级人数</th>
    <td colspan="2"  align="center" style="width:5%">&nbsp;<bean:write name="rs" property="bjrs"/></td>
    <th colspan="2"><div align="center">班主任</div></th>
    <td colspan="3" align="center">&nbsp;<bean:write name="rs" property="bzr"/></td>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%">
    <p>班</p>
    <p>级</p>
    <p>达</p>
    <p>标</p>
    <p>情</p>
    <p>况</p>
    </th>
    <th colspan="13" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjdbqk"/></p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>班主任</p>
    <p>意&nbsp;&nbsp;&nbsp;见</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bzryj"/></div><p>&nbsp;</p>
    <p align="right">班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>辅导员</p>
    <p>意&nbsp;&nbsp;&nbsp;见</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/></div><p>&nbsp;</p>
    <p align="right">辅导员签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>院(系)</p>
    <p>意&nbsp;&nbsp;&nbsp;见</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxyj"/></div>
<p>&nbsp;</p>
    <p align="right">负责人签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    <p>学生处</p>
    <p>意&nbsp;&nbsp;&nbsp;见</p>
    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/></div>
    <p>&nbsp;</p>
    <p align="right">负责人签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >

    <p>学&nbsp;&nbsp;&nbsp;院</p>
    <p>意&nbsp;&nbsp;&nbsp;见</p>

    </th>
    <th colspan="13" scope="row">
    <p>&nbsp;</p><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/></div>
    <p>&nbsp;</p>
    <p align="right">负责人签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           年 &nbsp;&nbsp;  月  &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th colspan="2" scope="row" style="width:8%" >
    	<p>说&nbsp;&nbsp;&nbsp;明</p>
    </th>
    <th colspan="13" scope="row" align="center">
    <p align="left">班级达标情况一栏按《云南艺术<bean:message key="lable.xsgzyxpzxy" />学生奖学金评定办法》第六章第二十一条逐一填写。</p>
   </th>
  </tr>
</table>
 	<div align="right">云南艺术<bean:message key="lable.xsgzyxpzxy" />学生处制表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </html:form>
  </body>
</html:html>
