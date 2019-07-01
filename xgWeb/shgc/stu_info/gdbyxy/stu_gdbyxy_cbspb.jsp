<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/xgxt/skin1/style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
		<html:form action="/leaveSchool.do">
		<center>
 <h1 align="center"><strong>学 生 插 班 审 批 表</strong></h1>
</center>
<table class="tbstyle" width="100%">
  <tr>
    <td width="45" height="145" rowspan="5">&nbsp;</td>
    <td width="" height="40">姓名</td>
    <td width="">&nbsp;</td>
    <td width="">性别</td>
    <td width="">&nbsp;</td>
    <td width="">出生日期</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="22" height="45">家庭地址</td>
    <td  colspan="5" height="45">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" height="45">原读学校</td>
    <td >&nbsp;</td>
    <td height="45">原读专业</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td height="25">进行过何种<br/>实习或取得<br/>何种技能证书</td>
    <td  colspan="5">&nbsp;</td>
  </tr>
  <tr>
    <td height="15" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本人经过认真考虑,征得家长同意,决定从原来的学校转学到广州白云工商高级技工
      <p>学校 _______________________________专业(高技、中技)学习。并同意推迟一年获取毕业证书。</p>
    <p> 手机/家庭电话：</p>
    <p>家长签名：__________ 学生签名：___________</p>
    <p> 年 月 日 </p></td>
  </tr>
  <tr>
    <td ><p>系学</p>
    <p>生管</p>
    <p>理副</p>
    <p>主任</p>
    <p>意见</p></td>
    <td width="409" colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td><p>系主</p>
    <p>任意</p>
    <p>见</p></td>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td><p>学生</p>
    <p>处备</p>
    <p>案审</p>
    <p>签</p></td>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td>备注</td>
    <td  colspan="6">&nbsp;</td>
  </tr>
</table>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html:html>
