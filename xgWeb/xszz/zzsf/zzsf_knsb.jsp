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
	<title><bean:message key="lable.title" />
	</title>
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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								漳州师范<bean:message key="lable.xsgzyxpzxy" />家庭经济困难学生认定申请表
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" scope="col" width="4%">
								<div align="center">
									学生本人基本情况
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									姓名
								</div>
							</td>
							<td scope="col" width="16%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="14%" scope="col">
								<div align="center">
									性别
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									<bean:write name='rs' property="xsrq" />
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									民族
								</div>
							</td>
							<td width="11%" scope="col">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭人均年收入
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrjnsr" />
									&nbsp;元
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									系
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									类别
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xslb" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									年级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									在校联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									学生陈述申请认定理由
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="sqly" />
								<br />
								<div align="right">
									学生签字：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;
								</div>
								<div align="tight">
									注：可另附详细情况说明。
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" rowspan="4">
								<div align="center">
									民主评议
								</div>
							</td>
							<td width="4%" rowspan="4">
								<div align="center">
									推荐档次
								</div>
							</td>
							<td colspan="2">
								A.家庭经济一般困难&nbsp;
								<logic:equal name="rs" property="fdysh" value="一般困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="一般困难">
								□
								</logic:notEqual>
							</td>
							<td rowspan="4">
								<div align="center">
									陈
									<br />
									述
									<br />
									理
									<br />
									由
								</div>
							</td>
							<td colspan="5" rowspan="4">
								<br />
								<logic:empty name="rs" property="fdyshyj">
								<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
								<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									评议小组组长签字签字：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								B.家庭经济困难&nbsp;
								<logic:equal name="rs" property="fdysh" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								C.家庭经济特殊困难&nbsp;
								<logic:equal name="rs" property="fdysh" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="特殊困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								D.家庭经济不困难&nbsp;
								<logic:equal name="rs" property="fdysh" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="不困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									认定决定
								</div>
							</td>
							<td>
								<div align="center">
									系意见
								</div>
							</td>
							<td colspan="3">
								经评议小组推荐，本系认真审核后，<br />
								<logic:equal name="rs" property="xysh" value="">
								□&nbsp;同意评议小组意见。<br />
								□&nbsp;不同意评议小组意见。<br />
								调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="">
								<logic:equal name="fdy_xy" value="same">
								√&nbsp;同意评议小组意见。<br />
								□&nbsp;不同意评议小组意见。<br />
								调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:equal name="fdy_xy" value="notSame">
								□&nbsp;同意评议小组意见。<br />
								√&nbsp;不同意评议小组意见。<br />
								调整为<u>&nbsp;<bean:write name='rs' property="xysh" />&nbsp;</u>。
								</logic:equal>
								</logic:notEqual>
								<br />
								<div align="left">
									工作组组长签字：
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="center">
									(加盖系党总支公章)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									学生
									<br />
									资助
									<br />
									管理
									<br />
									资助
									<br />
									中心
									<br />
									意见
								</div>
							</td>
							<td colspan="4">
								经学生所在系提请，本机构认真核实，<br />
								<logic:equal name="rs" property="xxsh" value="">
								□&nbsp;同意工作组和评议小组意见。<br />
								□&nbsp;不同意工作组和评议小组意见。<br />
								调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="">
								<logic:equal name="xy_xx" value="same">
								√&nbsp;同意工作组和评议小组意见。<br />
								□&nbsp;不同意工作组和评议小组意见。<br />
								调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
								</logic:equal>
								<logic:equal name="xy_xx" value="notSame">
								□&nbsp;同意工作组和评议小组意见。<br />
								√&nbsp;不同意工作组和评议小组意见。<br />
								调整为<u>&nbsp;<bean:write name='rs' property="xxsh" />&nbsp;</u>。
								</logic:equal>
								</logic:notEqual>
								<br />
								<div align="left">
									负责人签字：
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日
									&nbsp;&nbsp;
								</div>
								<div align="center">
									(加盖部门公章)
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
