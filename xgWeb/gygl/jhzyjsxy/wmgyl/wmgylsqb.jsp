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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
</head>

<body>
<div class=Section1 style='layout-grid:15.6pt'>
  <div align="center">金华职业技术<bean:message key="lable.xsgzyxpzxy" /><bean:write name="rs" property="xn"/>学年文明公寓楼申请表</div>
  <div align=center>
    <table border=1 cellspacing=0 cellpadding=0 width=600>
      <tr>
        <td width=96 class="Normal">楼幢名</td>
        <td width=204 colspan=2 class="Normal"><bean:write name="rs" property="lzm"/>&nbsp;</td>
        <td width=120 class="Normal">寝室数</td>
        <td width=180 colspan=2 class="Normal"><bean:write name="rs" property="qss"/>&nbsp;</td>
      </tr>
      <tr>
        <td width=96 class="Normal">学生数</td>
        <td width=204 colspan=2 class="Normal"><bean:write name="rs" property="xss"/>&nbsp;</td>
        <td width=120 class="Normal">所属<bean:message key="lable.xsgzyxpzxy" /></td>
        <td width=180 colspan=2 class="Normal"><bean:write name="rs" property="xydm"/>&nbsp;</td>
      </tr>
      <tr>
        <td width=96 class="Normal">卫生合格率</td>
        <td width=96 class="Normal"><bean:write name="rs" property="wshgl"/>&nbsp;</td>
        <td width=108 class="Normal">卫生优秀率</td>
        <td width=120 class="Normal"><bean:write name="rs" property="wsyxl"/>&nbsp;</td>
        <td width=96 class="Normal">星级寝室率</td>
        <td width=84 class="Normal"><bean:write name="rs" property="xjqsl"/>&nbsp;</td>
      </tr>
      <tr>
        <td width=96 class="Normal">公寓 文明 建设 情况 概述</td>
        <td width=504 height="150" colspan=5 valign=bottom class="Normal"><bean:write name="rs" property="gywmgs"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;楼长签名：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</td>
      </tr>
      <tr>
        <td width=96 class="Normal">楼幢辅导员意见</td>
        <td width=504 height="150" colspan=5 valign=bottom class="Normal"><bean:write name="rs" property="fdyyj"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辅导员签名：<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日 </td>
      </tr>
      <tr>
        <td width=96 class="Normal">所属<bean:message key="lable.xsgzyxpzxy" />意见</td>
        <td width=504 height="150" colspan=5 valign=bottom class="Normal"><bean:write name="rs" property="xyyj"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（盖 章）<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</td>
      </tr>
      <tr>
        <td width=96 class="Normal">学生处意见</td>
        <td width=504 height="150" colspan=5 valign=bottom class="Normal"><bean:write name="rs" property="xxyj"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（盖 章）<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</td>
      </tr>
      <tr height=0>
        <td width=96 class="Normal"></td>
        <td width=96 class="Normal"></td>
        <td width=108 class="Normal"></td>
        <td width=120 class="Normal"></td>
        <td width=96 class="Normal"></td>
        <td width=84 class="Normal"></td>
      </tr>
    </table>
  </div>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：本表格一式三份。
  </div>
					<br>
				<div class="buttontool noprint" align="center">
					<input type="button" class="button2" value="页面设置"
						onclick="WebBrowser.ExecWB(8,1);">
					<input type="button" class="button2" value="打印预览"
						onclick="WebBrowser.ExecWB(7,1)">
					<input type="button" class="button2" value="直接打印"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
	
</body>

</html:html>
