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
			<center>国家奖学金申请审批表</center>
	</div>
		<html:form action="/zjlgPjpy" method="post">
			
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1">
						奖学金名称
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
					<td colspan="1" scope="row" rowspan="5">
						<div align="center">
							<b>基<br>本<br>情<br>况</b>
						</div>
					</td>
						<td align="center" colspan="1">
							学号
						</td>
						<td align="left" colspan="1">
							<bean:write name="rs" property="xh"/>
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="1" scope="col">
						<bean:write name="rs" property="xm"/>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="xb"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							民族 
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="mz"/>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="csrq"/>
					</td>
					<td colspan="1">
						<div align="center">
							入学时间
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="rxrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="sfzh"/>
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
							政治面貌
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="zzmm"/>
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
					<td colspan="1" rowspan="1">
						<div align="center">
							<b>学<br>习<br>等<br>情<br>况</b>
						</div>
					</td>
					<td colspan="8">
<%--						本学年必修课程     门，其中，优秀     门，良好     门<br>--%>
<%----%>
<%--						成绩排名（专业或年级）：              （名次/总人数）<br>--%>
<%----%>
<%--						综合考评成绩     分，排名            （名次/总人数）<br>--%>
						<bean:write name="rs" property="xxjl"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>获<br>奖<br>情<br>况<br></b>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jfqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							<b><br>申<br>请<br>理<br>由<br></b>
						</div>
					</td>
					<td colspan="8">
						（全面反映德智体美情况）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>年级<br>（专业）<br>推荐意见<br></b>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>院<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>校<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xxshyj"/>
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
