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
  <!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'黑体' "><b>中国美术<bean:message key="lable.xsgzyxpzxy" />优秀毕业生评选推荐表</b></div>
<br/>
<div>

<table width="600px" align="center" class="printstyle">
   &nbsp;&nbsp;&nbsp;院（系）：&nbsp;${rs.xymc }&nbsp;专业：&nbsp;${rs.zymc }&nbsp; 学历：&nbsp;${rs.pycc }&nbsp; &nbsp;${rs.y }&nbsp;年 &nbsp;${rs.m }&nbsp; 月 &nbsp;${rs.r }&nbsp; 日
   <tr>
    <td width="74">  <div align="center"> 姓&nbsp;&nbsp;&nbsp;名</div></td>
    <td width="75"><div align="center">${rs.xm }</div></td>
    <td width="43"><div align="center">性别</div></td>
    <td width="55"><div align="center">${rs.xb }</div></td>
    <td width="55"><div align="center">民族</div></td>
    <td colspan="2" width="200"><div align="center">${rs.mzmc }</div><div align="center"></div></td>
    <td width="69"><div align="center">出生年月</div></td>
    <td width="100"><div align="center">${rs.csrq }</div></td>
  </tr>
  <tr>
    <td height="26"><div align="center">生&nbsp;源&nbsp;地</div></td>
    <td><div align="center">${rs.syd }</div></td>
    <td><div align="center">职务</div></td>
    <td colspan="2"><div align="center">${rs.drzw }</div>      <div align="center"></div></td>
    <td width="90"><div align="center">政治面貌</div></td>
    <td colspan="3"><div align="center">${rs.zzmmmc }</div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  <tr>
    <td height="25"><div align="center">家庭地址</div></td>
    <td colspan="3"><div align="center">${rs.jtdz }</div>      <div align="center"></div>      <div align="center"></div></td>
    <td colspan="2"><div align="center">联系电话</div>      <div align="center"></div></td>
    <td colspan="3"><div align="center">${rs.lxdh }</div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  <tr>
    <td><p align="center">民<br/>主<br/>评<br/>议<br/>情<br/>况<br/>说<br/>明</p> <p align="center">&nbsp;</p></td>
    <td colspan="8"><p>&nbsp;</p><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.mzpyqksm }</div> 
    <p align="center">&nbsp;</p><p>&nbsp;</p><p align="center">&nbsp;</p>
    <p align="right">班长：   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  年 &nbsp;&nbsp;  月 &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
    
    </td>
  </tr>
  <tr>
    <td><div align="center"><p align="center">&nbsp;</p>奖<br/>
      惩<br/>
      情<br/>
    况<p align="center">&nbsp;</p><p align="center">&nbsp;</p></div></td>
    <td colspan="8"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.jcqk }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  </table>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<%--  <p>&nbsp;</p>--%>
<div style='page-break-before:always;'>&nbsp;</div>
  <table width="600px" align="center" class="printstyle">
  <tr>
    <td td width="74"><div align="center">在<br/>
      校<br/>
      期<br/>
      间<br/>
      表<br/>
      现<br/>
      及<br/>
      院<br/>
      系<br/>
      推<br/>
      荐<br/>
      意<br/>
    见</div></td>
    <td colspan="8"><p>&nbsp;</p>
    <p>&nbsp;</p><p>&nbsp;</p>
    <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>
   	<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
   	<p align="right">负责人：   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  （章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  年 &nbsp;&nbsp;  月 &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
    </td>
  </tr>
  <tr>
    <td><div align="center">学<br/>
      校<br/>
      审<br/>
      批<br/>
      意<br/>
    见</div></td>
    <td colspan="8"><p>&nbsp;</p><p>&nbsp;</p>
    <p>&nbsp;</p><p>&nbsp;</p>
    <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>
    <p>&nbsp;</p><p>&nbsp;</p>
    
    <p align="right">负责人：   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  （章） &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;  年 &nbsp;&nbsp;  月 &nbsp;&nbsp;  日&nbsp;&nbsp;</p>
    </td>
  </tr>
</table>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
		<p align="center">
注：1．此表一式两份：学生本人档案、学校各一份。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
2．本表内容可打印或用钢笔填写，字迹清楚，经院（系）、学校盖章，领导签字方有效。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
    </html:form>
  </body>
</html:html>
