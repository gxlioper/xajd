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
		<p align="center">
			中国银行国家助学贷款还款协议
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td>
					<p align="left">
						借款人（甲方）：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="xm" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						有效证件号码：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="sfzh" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						住所：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="dwdz" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						工作单位：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="gzdw" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						邮政编码：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="dwyzbm" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						联系电话：<u>&nbsp;&nbsp;
						<bean:write name="rs" property="lxdh" />&nbsp;&nbsp;</u>
					</p>
					<p align="left">
						贷款人（乙方）：_______________________________________
					</p>
					<p align="left">
						地址：_______________________________________
					</p>
					<p align="left">
						邮政编码：_______________________________________
					</p>
					<p align="left">
						联系电话：_______________________________________
					</p>
					<br />
					<br />
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;本协议为甲方和乙方签订的《借款合同》（合同编号：&nbsp;
						<bean:write name="rs" property="hth" />&nbsp;
						）约定的从属协议，用以明确甲方向乙方归还国家助学贷款计划。经甲、乙两方协商同意后，订立如下还款协议：
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;一、截止&nbsp;
						<bean:write name="rs" property="zhfkrqYear" />&nbsp;
						年&nbsp;
						<bean:write name="rs" property="zhfkrqMon" />&nbsp;
						月&nbsp;
						<bean:write name="rs" property="zhfkrqDay" />&nbsp;
						日，甲方从乙方获得国家助学贷款共计人民币&nbsp;
						<bean:write name="rs" property="fkzje" />&nbsp;
						；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;二、甲方于&nbsp;
						<bean:write name="rs" property="lxsjYear" />&nbsp;
						年&nbsp;
						<bean:write name="rs" property="lxsjMon" />&nbsp;
						月&nbsp;
						<bean:write name="rs" property="lxsjDay" />&nbsp;
						日因&nbsp;
						<bean:write name="rs" property="lxyy" />&nbsp;
						原因，正式离开&nbsp;
						<bean:write name="rs" property="xxmc" />&nbsp;
						（所在学校）。甲方承诺于&nbsp;
						<bean:write name="rs" property="hkkssjYear" />&nbsp;
						年&nbsp;
						<bean:write name="rs" property="hkkssjMon" />&nbsp;
						月&nbsp;
						<bean:write name="rs" property="hkkssjDay" />&nbsp;
						日开始归还贷款本金；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;三、甲方采用以下第&nbsp;
						<bean:write name="rs" property="hkfs1" />&nbsp;
						方式按&nbsp;
						<bean:write name="rs" property="hkfs2" />&nbsp;
						（月 /季）分&nbsp;
						<bean:write name="rs" property="hkcs" />&nbsp;
						期归还贷款本息，还款期限共&nbsp;
						<bean:write name="rs" property="hkqx" />&nbsp;
						月，从&nbsp;
						<bean:write name="rs" property="hkkssjYear" />&nbsp;
						年&nbsp;
						<bean:write name="rs" property="hkkssjMon" />&nbsp;
						月&nbsp;
						<bean:write name="rs" property="hkkssjDay" />&nbsp;
						日至&nbsp;
						<bean:write name="rs" property="hkjssjYear" />&nbsp;
						年&nbsp;
						<bean:write name="rs" property="hkjssjMon" />&nbsp;
						月&nbsp;
						<bean:write name="rs" property="hkjssjDay" />&nbsp;
						日止：
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（一）等额本息还款法
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（二）等额本金还款法
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;四、经双方确认的借款，在本还款协议履行期间，按贷款期限执行中国人民银行同档次法定利率。如遇法定利率调整，乙方将执行调整后的利率，无须另行通知甲方；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;五、甲方授权乙方直接从甲方在乙方开立的账户中扣款，用于归还借款本息，账户户名为：&nbsp;
						<bean:write name="rs" property="zffm" />&nbsp;
						，账户号为：&nbsp;
						<bean:write name="rs" property="zfh" />&nbsp;
						；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;六、甲方承诺在离校手续办妥后一个月内将《中国银行国家助学贷款联系方式确认函》寄送回乙方；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;七、本协议所有条款甲方已经与乙方进行了充分的协商；
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;八、本协议作为《国家助学贷款借款合同》的组成部分，与《国家助学贷款借款合同》具有同等法律效力。
					</p>
					<br />
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;甲方：（签字或盖章）
					</p>
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
					</p>
					<br />
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;乙方：（公章）
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;授权签字人：（签字）
					</p>
					<br />
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','中国银行国家助学贷款还款协议')" />
	</div>
</body>
</html:html>
