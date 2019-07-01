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
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->	
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/xmlgpjpy">
      <div align="center" style="font-size:22px;font:'黑体' "><b>${xxmc }&nbsp;${rs.xn }学年<br/>奖学金评审表</b></div>
<br/>
<div>
<table width="100%" class="printstyle">
  <tr>
  	<th height="30" colspan="2" width="12%" scope="col">学   &nbsp;&nbsp;&nbsp; 号</th>
    <td height="30" colspan="2" style="width:14%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xh"/></td>
    <th height="30" colspan="2" width="14%" scope="col">姓   &nbsp;&nbsp;&nbsp; 名</th>
    <td height="30" colspan="2" style="width:14%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xm"/></td>
    <th height="30" width="11%" scope="col">性  别</th>
    <td height="30" colspan="2" scope="col" style="width:12%" align="center">&nbsp;<bean:write name='rs' property="xb"/></td>
    <th height="30" width="12%" scope="col">出生年月</th>
    <td height="30" colspan="2" scope="col" width="12%" align="center">&nbsp;<bean:write name='rs' property="csrq"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">学   &nbsp;&nbsp;&nbsp; 院</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th colspan="2"><div align="center">专业班级</div></th>
    <td colspan="5" width="13%" align="center">&nbsp;<bean:write name='rs' property="zymc"/><br/><bean:write name='rs' property="bjmc"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">综合测评<br/>学年排名</th>
    <td height="30" colspan="5" style="width:20%" scope="col" align="center">&nbsp;${rs.xnpm }</td>
    <th height="30" width="11%" colspan="2" scope="col">学年有无<br/>违纪处分</th>
    <td height="30" colspan="5" scope="col" style="width:15%" align="center">&nbsp;${rs.cf }</td>
  </tr>
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">外语水平</th>
    <td height="30" colspan="5" style="width:20%" scope="col" align="center">&nbsp;${rs.wysp }</td>
    <th height="30" width="11%" colspan="2" scope="col">计算机水平</th>
    <td height="30" colspan="5" scope="col" style="width:15%" align="center">&nbsp;${rs.jsjsp }</td>
  </tr>
  <tr>
    <th colspan="4" align="center">申请奖学金等级</th>
    <td colspan="10" align="center">&nbsp;<bean:write name='rs' property="jxjmc"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>奖</p>
    <p>励</p>
    <p>情</p>
    <p>况</p>
    </th>
    <th colspan="13" scope="row">
    	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jlqk }</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>申</p>
    <p>请</p>
    <p>理</p>
    <p>由</p>
    </th>
    <th colspan="13" scope="row">
    	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>所在</p>
    <p><bean:message key="lable.xsgzyxpzxy" /></p>
    <p>意见</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/>
    	<p align="center">${rs.xyshyj }</p>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>奖学</p>
    <p>金评</p>
    <p>审委</p>
    <p>员分</p>
    <p>意见</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/>
    	<p align="center">${rs.xxshyj }</p>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
</table>
<div>
</div>
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
    </div>
    </div>
   </html:form>
  </body>
</html:html>
