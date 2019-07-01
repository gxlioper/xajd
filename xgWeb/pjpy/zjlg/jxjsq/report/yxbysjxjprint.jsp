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
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
	<script language="javascript" src="js/function.js"></script>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
<body>
	<div colspan="8"/>
			<center>浙江理工大学<bean:write name="LoginXn"/>学年优秀学生奖学金登记表</center>
	</div>
		<html:form action="/zjlgPjpy" method="post">
			
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jxjmc"/>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jxjlbmc"/>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1" style="width: 80px">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh"/>
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="4" scope="col">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							银行卡号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhkh"/>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="yhlx"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xn"/>
					</td>
					<td colspan="1">
						<div align="center">
							学期
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td colspan="1">
						<div align="center">
							班级
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmm"/>
					</td>
					<td>
						<div align="center">
							职务
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="zw"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							家 庭 地 址 
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="syd"/>
					</td>
					<td>
						<div align="center">
							联系电话 
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							民族 
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mz"/>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							生源地 
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jg"/>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							本<br>人<br>简<br>历
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xxjl"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>主<br>要<br>事<br>迹<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="zysj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>获<br>奖<br>情<br>况<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jfqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>学<br>院<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>学<br>校<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xxshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>教<br>育<br>厅<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jytyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>毕<br>业<br>去<br>向<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="byqx"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>备<br>注<br>
						</div>
					</td>
					<td colspan="8">
					<bean:write name="rs" property="bz"/>
					</td>
				</tr>
			</table>
<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
</body>
</html:html>
