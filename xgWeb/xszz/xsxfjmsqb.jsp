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
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			申请表
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td width="54" height="35">
					<p align="center">
						姓 名
					</p>
				</td>
				<td width="147">
					<p align="center">
						<bean:write name="rs" property="xm" />
					</p>
				</td>
				<td width="53">
					<p align="center">
						性别
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xb" />
					</p>
				</td>
				<td colspan="2" width="12%">
					<p align="center">
						出生年月
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="csny" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						民族
					</p>
				</td>
				<td colspan="2" width="8%">
					<p align="center">
						<bean:write name="rs" property="mzmc" />
					</p>
				</td>
				<td>
					<p align="center">
						政治面貌
					</p>
				</td>
				<td width="74">
					<p align="center">
						<bean:write name="rs" property="zzmmmc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="35">
					<p align="center">
						学 院
					</p>
				</td>
				<td width="147">
					<p align="center">
						<bean:write name="rs" property="xy" />
					</p>
				</td>
				<td width="53">
					<p align="center">
						班级
					</p>
				</td>
				<td colspan="2" width="10%">
					<p align="center">
						<bean:write name="rs" property="bjmc" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						学号
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="xh" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						现任职务
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xrzw" />
					</p>
				</td>
				<td width="66">
					<p align="center">
						寝室
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="qs" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="35">
					<p align="center">
						家庭详细地址
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name="rs" property="jtzz" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						邮编
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="yzbm" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						联系电话
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="70">
					<p align="center">
						已享受困难补助、减免学费及贷款情况
					</p>
				</td>
				<td colspan="13" valign="top">
					<p>
						<bean:write name="rs" property="yxsbzdkqk" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="70">
					<p align="center">
						在校参加勤工助学情况
					</p>
				</td>
				<td colspan="13" valign="top">
					<p>
						<bean:write name="rs" property="zxcjqgzxqk" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" valign="middle" height="35">
					<p align="center">
						家庭经济来源
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" rowspan="6">
					<p align="center">
						家 庭 成 员
					</p>
				</td>
				<td width="147" height="35">
					<p align="center">
						称 谓
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						姓 名
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						出生年月
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						工作单位
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						月收入
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" height="35">
					<p align="center">
						申请学费减免理由（在第二页） 申请日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" valign="top" height="70">
					<p>
						父亲单位（或乡政府）意见
					</p>
					<br><br>
					<p align="right">
						签名（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td colspan="8" valign="top">
					<p>
						母亲单位（或乡政府）意见
					</p>
					<br><br>
					<p align="right">
						签名（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="14">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						拟减免金额：
						<bean:write name="rs" property="njmjedx" />
						&nbsp;&nbsp;&nbsp;&nbsp;签名（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
			</tr>
			<tr>
				<td height="89" colspan="14">
					<p>
						学生处审核意见：
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						减免金额：
						<bean:write name="rs" property="jmjedx" />
						&nbsp;&nbsp;&nbsp;&nbsp;签名（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
			</tr>
			<tr>
				<td colspan="14" height="80" valign="top">
					<p align="right">
					本表一式两份，一份留<bean:message key="lable.xsgzyxpzxy" />，一份交学生处。
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" height="200" valign="top">
					<p>
						申请理由：
					</p>
					<bean:write name="rs" property="sqly" />
				</td>
			</tr>
			</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','学生学费减免申请表')" />
	</div>
</body>
</html:html>
