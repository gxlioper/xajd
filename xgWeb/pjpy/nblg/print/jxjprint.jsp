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
    <html:form action="/pjpynblgwh">
      <div align="center" style="font-size:20px;font:'黑体' "><b>${xxmc }20<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;-&nbsp;20<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年<br/>奖学金评审表</b></div>
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
    <th height="30" colspan="2" scope="row">分   &nbsp;&nbsp;&nbsp; 院</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="xymc"/></td>
    <th colspan="2"><div align="center">专业班级</div></th>
    <td colspan="5" width="13%" align="center">&nbsp;<bean:write name='rs' property="zymc"/><br/><bean:write name='rs' property="bjmc"/></td>
  </tr>
  <tr>
  	<th height="30" colspan="2" width="10%" scope="col">智育成绩<br/>专业排名</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;</td>
    <th height="30" colspan="2" width="14%" scope="col">综合测评<br/>班级排名</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;</td>
    <th height="30" width="11%" scope="col">学年有无<br/>违纪处分</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;</td>
    <th height="30" width="10%" scope="col">体育<br/>是否达标</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;</td>
  </tr>
  <tr>
    <th height="30" colspan="2" rowspan="2" scope="row">外   &nbsp;&nbsp;&nbsp; 语</th>
    <td colspan="2" align="center">等   &nbsp;&nbsp;&nbsp; 级</td>
    <td colspan="3" align="center">&nbsp;</td>
    <th colspan="2" rowspan="2"><div align="center" >学年其他<br/>获奖情况</div></th>
    <td colspan="5" width="13%" rowspan="2" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="center">成   &nbsp;&nbsp;&nbsp; 绩</td>
    <td colspan="3" align="center">&nbsp;</td>
  </tr>
  <tr>
    <th colspan="4" align="center">申请奖学金类别</th>
    <td colspan="10" align="center">&nbsp;<bean:write name='rs' property="jxjmc"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>个</p>
    <p>人</p>
    <p>学</p>
    <p>年</p>
    <p>小</p>
    <p>结</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    	<p align="right">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>所在</p>
    <p>分院</p>
    <p>意见</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p><bean:message key="lable.xsgzyxpzxy" /></p>
    <p>奖学</p>
    <p>金评</p>
    <p>审委</p>
    <p>员分</p>
    <p>意见</p>
    </th>
    <th colspan="13" scope="row">
    	<br/><br/><br/><br/><br/><br/>
    	<p align="right">(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  		<p align="right">年  &nbsp;&nbsp;&nbsp;月  &nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
   </th>
  </tr>
</table>
<div>
	填表注意事项：<br/>
1、本表需用钢笔或黑色水笔填写，要求字迹清晰端正。<br/>
2、要求智育专业排名和综合测评班级排名的书写格式如1/120和1/30。<br/>
3、个人学年小结可附页。<br/>
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
