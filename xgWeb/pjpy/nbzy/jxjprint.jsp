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
      <div align="center" style="font-size:18px;font:'黑体' ">${title }</div>
<br/>

<table width="98%" class="printstyle">
  <tr>
    <th height="35" colspan="2" width="15%" scope="col">学年度</th>
    <td height="30" colspan="2" style="width:10%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th height="30" width="11%" scope="col">班  级</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="bjmc"/></td>
    <th height="30" width="10%" colspan="2" scope="col">姓名</th>
    <td height="30" colspan="" scope="col" align="center">&nbsp;<bean:write name='rs' property="xn"/></td>
    <td rowspan="3" scope="col" width="15%" align="center">照片</td>
  </tr>
  <tr>
    <th height="35" colspan="2" scope="row">申报奖学金等级</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th><div align="center">学  号</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bzxm"/></td>
    <th colspan="2"><div align="center">寝室号</div></th>
    <td colspan="" height="30" width="13%" align="center">&nbsp;<bean:write name='rs' property="tzs"/></td>
    
  </tr>
  <tr>
  	<th height="35" colspan="4" scope="row">职业素养测评分</th>
    <td colspan="1" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th colspan="2" align="center">名  次</th>
    <th height="30" colspan="3" scope="row">全班共&nbsp;&nbsp;&nbsp;人排第&nbsp;&nbsp;&nbsp;名</th>
  </tr>
  <tr>
  	<th height="30" colspan="4" scope="row">职业技能素养测评分</th>
    <td colspan="1" align="center">&nbsp;<bean:write name='rs' property="bzr"/></td>
    <th colspan="4" align="center">可持续发展素质测评分</th>
    <th height="30" colspan="2" scope="row">&nbsp;</th>
  </tr>
  <tr>
  	<th height="30" colspan="4" scope="row">综合测评总分</th>
    <td colspan="1" align="center">&nbsp;</td>
    <th colspan="2" align="center">名  次</th>
    <th height="30" colspan="4" scope="row">全班共&nbsp;&nbsp;&nbsp;人排第&nbsp;&nbsp;&nbsp;名</th>
  </tr>
    <tr>
    <th scope="row" colspan="2" style="width:15%">
    <p align="center">
     申请理<br/>由
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  <tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
      班级<br/>测评<br/>小组<br/>意见
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">班主任签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     <bean:message key="lable.xsgzyxpzxy" /><br/>综合<br/>测评<br/>小组<br/>意见
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(<bean:message key="lable.xsgzyxpzxy" />党总支盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     学工部<br/>意见
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
  	<tr>
    <th scope="row" colspan="2" style="width:14%">
    <p align="center">
     <bean:message key="lable.xsgzyxpzxy" /><br/>意见
    </p>
    </th>
    <th scope="row" colspan="9" >
    <br/><br/><br/><br/><br/>
    <p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  	</tr>
	</table>
    </html:form>
  </body>
</html:html>
