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

<%
		response.setHeader("prama", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);
%>


<body>
	<html:form action="/txsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			申请表
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td width="77">
					<p align="center">
						姓名
					</p>
				</td>
				<td width="112">
					<p align="center">
						<bean:write name='rs' property="xm" />
					</p>
				</td>
				<td width="149">
					<p align="center">
						性别
					</p>
				</td>
				<td width="141">
					<p align="center">
						<bean:write name='rs' property="xb" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						身份证号码
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="sfzh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						所在院校名称
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xxmc" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						学号
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="xh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						贷款银行（支行)
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="dkyh" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						贷款总金额
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="dkzje" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						贷款期限
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="dkqx" />
					</p>
				</td>
				<td width="125">
					<p align="center">
						应付利息
					</p>
				</td>
				<td width="355">
					<p align="center">
						<bean:write name='rs' property="yflx" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p align="center">
						贷款银行签章
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<br><br><br><br><br><br>
					</p>
				</td>
			</tr>
			<tr>
				<td width="77" rowspan="3">
					<p align="center">
						申请理由
					</p>
					<p align="center">
						（由申请人填写并附有关材料）
					</p>
				</td>
				<td width="112">
					<p align="center">
						帮 困
					</p>
					<p align="center">
						贴 息
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="bktx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="112">
					<p align="center">
						奖 优
					</p>
					<p align="center">
						贴 息
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="jytx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="112">
					<p align="center">
						政 策
					</p>
					<p align="center">
						贴 息
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name='rs' property="zctx" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="89">
					<p align="center">
						学校意见
					</p>
				</td>
				<td colspan="5">
					<bean:write name="rs" property="xyshyj" />
					<br><br>
					<p align="right">
						学校：（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="89">
					<p align="center">
						学校意见
					</p>
				</td>
				<td colspan="5">
					<bean:write name="rs" property="xxshyj" />
					<br><br>
					<p align="right">
						<bean:message key="lable.xsgzyxpzxy" />：（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
				</td>
			</tr>
			<tr>
				<td width="77">
					<p align="center">
						教委意见
					</p>
				</td>
				<td colspan="5">
					<p>&nbsp;
					</p>
					<p align="center">
						同意贴息 ＿＿＿＿ ％
					</p>
					<p align="center">
						合计人民币（大写） ＿＿＿＿＿＿ 元（小写） ＿＿＿＿ 元
					</p>
					<p align="right">
						教委：（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						负责人：（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
					</p>
					
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<p align="right">
					 注：本表一式三份，教委、学校、申请人各一份。
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印"
			onclick="expTab('theTable','助 学 贷 款 贴 息 申 请 审 批 表')" />
	</div>
</body>
</html:html>
