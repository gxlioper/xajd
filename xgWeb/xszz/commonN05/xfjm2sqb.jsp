<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=xfjm2sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong>特困学生减免学费申请表</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;系别：<bean:write name='rs' property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%">
								<div align="center">
									姓&nbsp;&nbsp;名
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									班&nbsp;&nbsp;级
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									学&nbsp;&nbsp;号
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									欠费情况
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="qfqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									获奖情况
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="hjqk" />
							</td>
							<td>
								<div align="center">
									获助情况
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="hzqk" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtdz" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="lxdh" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									申&nbsp;&nbsp;请
									<br />
									<br />
									理&nbsp;&nbsp;由
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									本人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									推&nbsp;&nbsp;荐
									<br /><br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="3">
								&nbsp;班级推荐意见：
								<br /><br /><br />
								<div align="right">
									班长签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="4">
								&nbsp;班主任意见：
								<br />
								<logic:empty name='rs' property="fdyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="fdyshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									消&nbsp;&nbsp;费
									<br />
									情&nbsp;&nbsp;况
									<br />
									核&nbsp;&nbsp;查
								</div>
							</td>
							<td colspan="7">
								<br /><br /><br />
								<div align="right">
									辅导员签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									系&nbsp;&nbsp;部
									<br />
									意&nbsp;&nbsp;见
									<br />
									(拟等第)
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									系部签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学工处
									<br /><br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									学工处签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学&nbsp;&nbsp;院
									<br /><br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="7">
								<br /><br /><br />
								<div align="right">
									院领导签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：1、将获奖情况、在校各学年的成绩、相关贫困证明附于表后。<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、请将曾经获得过的资助类别和金额填写在表格上。
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
