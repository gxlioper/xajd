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
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			申请表
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td width="104" height="35">
					<p align="center">
						借款人姓名
					</p>
				</td>
				<td colspan="2" valign="middle">
				<p align="center">
					<bean:write name='rs' property="xm" />
				</p>
				</td>
				<td colspan="2">
					<p align="center">
						性 别
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xb" />
					</p>
				</td>
				<td width="103">
					<p align="center">
						出生日期
					</p>
				</td>
				<td width="225">
					<p align="center">
						<bean:write name='rs' property="csny" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						就读学校
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="xxmc" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						院 系
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xymc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="sfzh" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						专 业
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xmc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						学 号
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="xh" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						宿舍电话
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="ssdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						学 制
					</p>
				</td>
				<td width="157">
					<p align="center">
						<bean:write name='rs' property="xz" />
						&nbsp;年
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						学 历
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						&nbsp;□ 专科&nbsp;&nbsp; □ 本科&nbsp;&nbsp; □ 硕士&nbsp;&nbsp; □ 博士
						&nbsp;&nbsp;□ 其他 ______________ &nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						申请金额
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						总金额：&nbsp;
						<bean:write name='rs' property="dkze" />
						&nbsp;元、 学费&nbsp;
						<bean:write name='rs' property="xfdks" />
						&nbsp;元、 住宿费&nbsp;
						<bean:write name='rs' property="zsfdks" />
						&nbsp;元、 生活费&nbsp;
						<bean:write name='rs' property="shfdks" />
						&nbsp;元
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						贷款期限
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						自&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="kssjYear" />&nbsp;年&nbsp;
						<bean:write name='rs' property="kssjMonth" />&nbsp;月&nbsp;
						<bean:write name='rs' property="kssjDay" />&nbsp;日&nbsp;&nbsp;&nbsp;
						至&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="jssjYear" />&nbsp;年&nbsp;
						<bean:write name='rs' property="jssjMonth" />&nbsp;月&nbsp;
						<bean:write name='rs' property="jssjDay" />&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						家庭住址
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="jtzz" />
					</p>
				</td>
				<td colspan="2" rowspan="5" align="center" valign="middle">
					<p>
						本人保证以上填写内容真实无误，并予以认可。
					</p>
					<p align="right">
						借款申请人（签字）：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						邮 编
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="yzbm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						电 话
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="dh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						父亲姓名
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqxm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						职 业
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqzy" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqsfzh" />
					</p>
				</td>
				<td colspan="2" rowspan="5" valign="middle" align="center">
					<p align="center">
						借款申请人系我校就读学生，表内所填资料属实，特此证明。
					</p>
					
					<p align="right">
						贷款介绍人 / 学校（签章）：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						母亲姓名
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqxm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						职 业
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqzy" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqsfzh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						家庭收入
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="jtsr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						客户经理
					</p>
					<p align="center">
						意见
					</p>
				</td>
				<td colspan="8" valign="middle">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						借款人向我行申请国家助学贷款贷款。根据国家相关贷款政策，已委托借款人所在高校向借款申请人收取贷款申请资料，并在现场见证借款申请人本人签字，经初步审核，拟同意其借款申请，并提请部门主管及有权审批人审核。
					</p>
					<p align="right">
						客户经理:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						部门主管
					</p>
					<p align="center">
						意见
					</p>
				</td>
				<td colspan="8" valign="bottom">
					<p align="center">
						□ 同意客户经理意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; □ 不同意客户经理意见
					</p>
					<p align="right">
						部门主管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						有权审批人意见
					</p>
				</td>
				<td colspan="8" valign="bottom">
					<p align="center">
						□ 同意发放贷款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; □ 不同意发放贷款
					</p>
					<p align="right">
						有权审批人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','中国银行上海市分行国家助学贷款申请审批表')" />
	</div>
</body>
</html:html>
