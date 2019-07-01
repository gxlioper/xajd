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
	<style type="text/css" >
		.td{
			font-size: 15px;	
		}
	</style>
	<style media='print'>
		.noPrin{display:none;}
	</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
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
    <html:form action="/wjcfhhgxywh">
      <div align="center" ><font style="font-size:22px;font:'黑体' "><b>${xxmc}学生违纪处分决定书</b></font><font style="font-size:17px; ">(<b>存根</b>)</font></div>
      <br/>
      <div align="right">No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;</div>
<br/>
<div>
<table width="95%" class="" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr align="">
    <td height="40px" align="center" class="td" >
    	拟处分学生的姓名<u style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	性别<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	学号<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    </td>
  </tr>
  <tr>
    <td height="40px" align="center" class="td">
    	学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	违纪情况<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
   	 	处分决定<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>处分。
	</td>
  </tr>
  <tr>
  	<td height="50px" align="center" class="td">
  		<br/>
  		<div align="right">
  			淮海工<bean:message key="lable.xsgzyxpzxy" />学生工作委员会（学生处代章）
  		</div>
  		<br/>
  		<div align="right">
  			年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</div>
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td">
  	  		<br/>
  		领取人签名<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	</td>
  </tr>
</table>
<br/>
<hr>
<br/>
<div align="center" style="font-size:22px;font:'华文楷体' "><b>${xxmc}学生违纪处分决定书</b></div>
      <br/>
      <div align="right">No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;</div>
<br/>
<div>
<table width="95%" class="" align="center" border="0" cellpadding="0" cellspacing="0">
  <tr align="">
    <td height="40px" align="center" class="td" >
    	拟处分学生的姓名<u style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	性别<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xb }&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	学号<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    </td>
  </tr>
  <tr>
    <td height="40px" align="center" class="td">
    	学&nbsp;&nbsp;&nbsp;&nbsp;院<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
    	班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级<u style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	处分原由和依据<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	根据《淮海工<bean:message key="lable.xsgzyxpzxy" />学生违纪处分实施细则(试行)》第<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>条
   	 	
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>款的规定，经<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>研究决定给予你<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>处分。
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如对以上的处分有异议，可在接到本通知后五个工作日内向院学生申&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td"> 
   	 	诉处理委员会（办公室设在学生处）提出申诉。
   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
  <tr>
  	<td height="50px" align="center" class="td">
  		<br/>
  		<div align="right">
  			淮海工<bean:message key="lable.xsgzyxpzxy" />学生工作委员会（学生处代章）
  		</div>
  		<br/>
  		<div align="right">
  			年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</div>
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" class="td">
  		<br/>
  		当事人签名<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	</td>
  </tr>
  <tr>
  	<td height="40px" align="center" style="font: 楷体_GB2312 ;font-size: 13px">
  	  		<br/>说明：1.当事人签名领取阅读后须将告知书交回其所在<bean:message key="lable.xsgzyxpzxy" />。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;<br/>
		     2.当事人所在<bean:message key="lable.xsgzyxpzxy" />须将收回的告知书保存备查。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		
  	</td>
  </tr>
</table>
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
    </div>
<!-- 注：此表一式两份，系（院）、院学生处各一份 -->
</div></div>
    </html:form>
  </body>
</html:html>
