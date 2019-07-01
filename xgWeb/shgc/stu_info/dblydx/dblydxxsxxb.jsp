<%@ page language="java" pageEncoding="GB2312"
	contentType="text/html;charset=GBK"%>

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
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="/style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
<body>
	<html:form action="stu_info_query.do" method="post">
		<center>
			<h3>
				东北林业大学学生基本信息登记表
			</h3>
			<table 　 width="100%" class="tbstyle" id="rsTable">
				<tr>
					<td align="center" width="80">
						<bean:message key="lable.xsgzyxpzxy" />（部）：
					</td>
					<td width="120">
						<bean:write name="rs" property="XYMC" />
					</td>
					<td align="center" width="80">
						专业：
					</td>
					<td width="120">
						<bean:write name="rs" property="ZYMC" />
					</td>
					<td align="center" width="80">
						班级：
					</td>
					<td width="120">
						<bean:write name="rs" property="BJMC" />
					</td>
					<td align="center" width="80">
						学号：<bean:write name="rs" property="XH" />
					</td>
				</tr>
				<tr height="30">
					<td align="center" width="80">
						姓名
					</td>
					<td width="120">
						<bean:write name="rs" property="XM" />
					</td>
					<td align="center" width="80">
						出生日期
					</td>
					<td width="120">
						<bean:write name="rs" property="CSRQ" />
					</td>
					<td align="center" width="80">
						性别:
					</td>
					<td width="120">
						<bean:write name="rs" property="XB" />
					</td>
					<td rowspan="4" width="120">
						<img src="123.jpg" style="width:100%;height:100%">
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						政治面貌
					</td>
					<td>
						<bean:write name="rs" property="ZZMMMC" />
					</td>
					<td align="center">
						民族
					</td>
					<td>
						<bean:write name="rs" property="MZMC" />
					</td>
					<td align="center">
						入党入团时间:
					</td>
					<td>
						<bean:write name="rs" property="JRGCDSJ" />
					</td>
				</tr>
				<tr height="40">
					<td align="center">
						身份证号码
					</td>
					<td colspan="3">
						<bean:write name="rs" property="SFZHM" />
					</td>
					<td align="center">
						是否贷款
					</td>
					<td>
						<bean:write name="rs" property="SFDK" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						手机号码
					</td>
					<td>
						<bean:write name="rs" property="SJHM" />
					</td>
					<td align="center">
						E-mail
					</td>
					<td colspan="3">
						<bean:write name="rs" property="DZYX" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						兴趣爱好
					</td>
					<td colspan="3">
						<bean:write name="rs" property="AH" />
					</td>
					<td align="center">
						特长
					</td>
					<td colspan="2">
						<bean:write name="rs" property="TC" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						寝 室
					</td>
					<td colspan="3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="qslh" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; 号楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="qssh" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;室
					</td>
					<td align="center">
						寝室电话
					</td>
					<td colspan="2">
						<bean:write name="rs" property="QSDH" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						家庭详细地址
					</td>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						省
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						市
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						县（区）
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						镇（乡）
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;村（街道）
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;小区
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;单元
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;室
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						邮政编码
					</td>
					<td>
						<bean:write name="rs" property="JTYB" />
					</td>
					<td align="center">
						家庭电话
					</td>
					<td>
						<bean:write name="rs" property="JTDH" />
					</td>
					<td align="center">
						家庭经济情况
					</td>
					<td colspan="2">
						□ 宽 裕&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; □ 一 般
						<br/>
						□ 困 难 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□ 特 困
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						家庭主要成员姓名
					</td>
					<td align="center">
						与本人关系
					</td>
					<td colspan="2" align="center">
						工作单位
					</td>
					<td align="center">
						职务
					</td>
					<td align="center">
						单位电话
					</td>
					<td align="center">
						手机号码
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY1_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY1_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY1_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY2_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY2_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY2_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="JTCY3_XM" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_CH" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="JTCY3_GZDZ" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_ZW" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_LXDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="JTCY3_LXDH1" />
					</td>
				</tr>
				<tr height="30">
					<td align="center">
						哈尔滨市主要
						<br/>
						社会关系
						<br/>
						姓 名
					</td>
					<td align="center">
						与本人关系
					</td>
					<td colspan="2" align="center">
						工作单位
					</td>
					<td align="center">
						职务
					</td>
					<td align="center">
						单位电话
					</td>
					<td align="center">
						手机号码
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="SHGXXM1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXGX1" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="SHGXGZDW1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXZW1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXDWDH1" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXSJHM1" />
					</td>
				</tr>
				<tr height="30">
					<td height="30">
						<bean:write name="rs" property="SHGXXM2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXGX2" />
					</td>
					<td height="30" colspan="2">
						<bean:write name="rs" property="SHGXGZDW2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXZW2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXDWDH2" />
					</td>
					<td height="30">
						<bean:write name="rs" property="SHGXSJHM2" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						个人及家庭
						<br/>
						情况简介
						<br/>
						（100字）
					</td>
					<td colspan="6">
						<bean:write name="rs" property="JTQKJJ" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						曾获得奖励
						<br/>
						（市级以上）
					</td>
					<td colspan="6">
						<bean:write name="rs" property="JLCFJL" />
					</td>
				</tr>
				<tr height="80">
					<td align="center">
						备 注
					</td>
					<td colspan="6">
						<bean:write name="rs" property="BZ" />
					</td>
				</tr>
				<tr height="40">
					<td colspan="7" align="center">
						本 人 保 证 以 上 信 息 如 有 任 何 弄 虚 作 假 ，本 人 将 承 担 一 切 责 任 ．
						<br/>
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						填表人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日
					</td>
				</tr>
			</table>
		</center>
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;width:100%">
				<button class="button2"
					onclick="expTab('rsTable','东北林业大学学生基本信息登记表','')"
					style="width:80px">
					打印
				</button>
			</div>
		</center>
	</html:form>
</body>
</html:html>
