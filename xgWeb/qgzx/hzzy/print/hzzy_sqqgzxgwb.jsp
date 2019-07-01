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
    
    <title>hzzy_sqqgzxgwb.jsp</title>

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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
	<html:form action="/qgzx_bb_gwsqb">
    	<div align="center"><br><strong><font size="2">
  			杭州职业技术<bean:message key="lable.xsgzyxpzxy" />用工部门<bean:write name="rs" property="xn" scope="request"/>学年</font></strong><br/>
  			<br><strong><font size="2">
  			第<bean:write name="rs" property="xq" scope="request"/>学期申请勤工助学岗位表</font></strong><br/>
		</div>
  		<table width="646" align="center">
  			<tr>
    			<td height="29" colspan="4" align="left">部门: <bean:write name="rs" property="xymc" scope="request"/></td>
  			</tr>
  		</table>
		<table width="646" height="487" align="center" class="tbstyle">
  			<tr>
    			<td width="126" height="25"><div align="center">岗 位 名 称</div></td>
    			<td width="210"><bean:write name="rs" property="gwdm" scope="request"/></td>
    			<td width="100"><div align="center">所 需 人 数</div></td>
    			<td width="210"><bean:write name="rs" property="xyrs" scope="request"/></td>
  			</tr>
  			<tr>
    			<td height="108" colspan="4"><p>岗位工作内容(包括月工作时间):</p>
    			<p>&nbsp;</p>
   				<p>&nbsp;</p>
    			<p>&nbsp;</p></td>
  			</tr>
  			<tr>
    			<td height="119" colspan="4"><p>对勤工助学同学的要求:</p>
    			<p>&nbsp;</p>
    			<p>&nbsp;</p>
    			<p>&nbsp;</p></td>
  			</tr>
  			<tr>
    			<td height="26"><div align="center">用工部门负责人</div></td>
    			<td><bean:write name="rs" property="lxr" scope="request"/></td>
    			<td><div align="center">填 报 日 期</div></td>
    			<td>&nbsp;</td>
  			</tr>
  			<tr>
    			<td height="91" colspan="4"><p>人事处意见:</p>
    			<p>&nbsp;</p>      
    			<blockquote>
      				<blockquote>
        				<div align="right">
          					<br>签名:<br>
          					日期:<br/>
        				</div>
      					</blockquote>
    			</blockquote>
    			</td>
  			</tr>
  			<tr>
    			<td height="29" colspan="4"><div align="center">学 生 资 助 中 心 意 见</div></td>
  			</tr>
  			<tr>
    			<td height="71" colspan="4">&nbsp;</td>
  			</tr>
		</table>
		<br/>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	</html:form>
</body>
</html:html>
