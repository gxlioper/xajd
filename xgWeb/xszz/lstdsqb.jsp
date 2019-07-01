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
				<td width="65" height="35" nowrap>
					<p align="center">
						班 级
					</p>
				</td>
				<td width="93">
					<p align="center">
						<bean:write name="rs" property="bjmc" />
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						学 号
					</p>
				</td>
				<td width="120">
					<p align="center">
						<bean:write name="rs" property="xh" />
					</p>
				</td>
				<td width="97">
					<p align="center">
						姓 名
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xm" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						性 别
					</p>
				</td>
				<td width="79">
					<p align="center">
						<bean:write name="rs" property="xb" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						专 业
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="xmc" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						联系电话
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						身份证号
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="sfzh" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						家庭联系电话
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="jtlxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						家庭住址
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="jtzz" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						联系电话
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" valign="middle">
					<p>
						本人申请理由：
					</p>
					<bean:write name="rs" property="sqly" />
					
					<p>
						<strong>本人保证以上所填内容真实无误。如被批准绿色通道，我将以申请助学贷款方式支付本学期学费。 </strong>
					</p>
					<p align="right" >
						申请人（签名）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						担保人（签名）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" height="35">
					<p align="center">
						家庭成员情况
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<p align="center">
						成 员
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						姓 名
					</p>
				</td>
				<td colspan="7">
					<p align="center">
						工 作 单 位
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						月 收 入
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						学生处审核意见：
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						学校领导审批意见：
					</p>
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" valign="top" align="right" height="150">
					<p><font color="red">
						注：父母收入情况请出具单位或街道证明。
					</font></p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>通 </strong><strong></strong><strong>知 </strong><strong></strong><strong>书
						</strong><strong></strong>
					</p>
					<p>
						教务处：
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;班&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; 同学，因&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; 原因申请绿色通道，现已批准，请你处给予该生办理入学注册手续。
					</p>
					<p align="right">
						特此通知&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						学 生 处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>通 </strong><strong></strong><strong>知 </strong><strong></strong><strong>书
						</strong><strong></strong>
					</p>
					<p>
						财务处：
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;班&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; 同学，因&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; 原因申请绿色通道，现已批准，请你处给予该生办理入学注册手续。
					</p>
					<p align="right">
						特此通知&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						学 生 处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>通 </strong><strong></strong><strong>知 </strong><strong></strong><strong>书
						</strong><strong></strong>
					</p>
					<p>
						&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;班&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; 同学，因&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; 原因申请绿色通道，现已批准，请你处给予该生办理入学注册手续。
					</p>
					<p align="right">
						特此通知&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						学 生 处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','绿色通道申请表')" />
	</div>
</body>
</html:html>
