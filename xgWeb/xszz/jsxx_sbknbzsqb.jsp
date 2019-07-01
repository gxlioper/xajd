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
			<br>
			伤、病困难补助申请表
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td colspan="9">
					<div align="center">
						系 <u>&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;</u>&nbsp; 班级 <u>&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;</u>&nbsp; 姓名 <u>&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;</u>&nbsp; 性别 <u>&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;</u>&nbsp; 出生日期 <u>&nbsp;
						<bean:write name='rs' property="csrq" /></u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						家庭地址
					</div>
				</td>
				<td colspan="5" scope="col">
					<div align="left">
						<bean:write name='rs' property="jtdz" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="lxdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						家庭成员
					</div>
				</td>
				<td width="10%">
					<div align="center">
						称谓
					</div>
				</td>
				<td width="10%">
					<div align="center">
						年龄
					</div>
				</td>
				<td width="10%">
					<div align="center">
						身体状况
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						工作单位及职务
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						年收入
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="9" scope="row">
					<div align="center">
						是否是烈军属:&nbsp;
						<bean:write name='rs' property="sfljs" />
						&nbsp;&nbsp;&nbsp;&nbsp; 是否是单亲:&nbsp;
						<bean:write name='rs' property="sfdq" />
						&nbsp;&nbsp;&nbsp;&nbsp; 父母是否是双下岗:&nbsp;
						<bean:write name='rs' property="sfsxg" />
						&nbsp;&nbsp;&nbsp;&nbsp; 父母是否有残疾:&nbsp;
						<bean:write name='rs' property="sfcj" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学习情况
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxqk" />
					<br />
					<div align="right">
						(补考后有无不及格课程&nbsp;
						<bean:write name='rs' property="bkhywbjgkc" />
						&nbsp;)
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						操行等第
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="cxdd" />
					</div>
				</td>
				<td>
					<div align="center">
						平时表现
					</div>
				</td>
				<td colspan="6">
					<bean:write name='rs' property="psbx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						奖惩情况
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jcqk" />
				</td>
			</tr>
			<tr>
				<td colspan="4" scope="row">
					每年家庭提供费用&nbsp;&nbsp;<u>&nbsp;
					<bean:write name='rs' property="mnjttgfy" />
					&nbsp;</u>元
				</td>
				<td colspan="2">
					亲朋好友等提供<u>&nbsp;
					<bean:write name='rs' property="mnqphytgfy" />
					&nbsp;</u>元
				</td>
				<td colspan="3">
					合计每年提供<u>&nbsp;
					<bean:write name='rs' property="mnhjtgfy" />
					&nbsp;</u>元
				</td>
			</tr>
			<tr>
				<td colspan="4" scope="row">
					每年应缴纳各种费用<u>&nbsp;
					<bean:write name='rs' property="mnyjgzfy" />
					&nbsp;</u>元
				</td>
				<td colspan="2">
					每月平均生活费<u>&nbsp;
					<bean:write name='rs' property="mypjshf" />
					&nbsp;</u>元
				</td>
				<td colspan="3">
					合计每年费用<u>&nbsp;
					<bean:write name='rs' property="mnhjfy" />
					&nbsp;</u>元
				</td>
			</tr>
			<tr>
				<td colspan="9" scope="row">
					每年参加院内勤工助学获补贴<u>&nbsp;
					<bean:write name='rs' property="mncjynqgzxbt" />
					&nbsp;</u>元, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 参加院外勤工助学活动获报酬<u>&nbsp;
					<bean:write name='rs' property="cjywqgzxbc" />
					&nbsp;</u>元
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						意外伤害和疾病概况
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="ywshhjbgk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						伤、病时间
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sbsj" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						诊治医院
					</div>
				</td>
				<td width="20%">
					<div align="center">
						<bean:write name='rs' property="zzyy" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						医药费
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="yyf" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						其他费用
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="qtfy" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						是否欠费
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sfqf" />
					</div>
				</td>
				<td colspan="3">
					家庭保险理赔款<u>&nbsp;
					<bean:write name='rs' property="jtbxlpk" />
					&nbsp;</u>元
				</td>
				<td colspan="3">
					<bean:message key="lable.xsgzyxpzxy" />保险理赔款<u>&nbsp;
					<bean:write name='rs' property="xybxlpk" />
					&nbsp;</u>元
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭经济状况及申请伤、病困难补助的理由
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jtjjzkjsqsbbzly" />
				</td>
			</tr>
			<tr>
				<td colspan="5" scope="row">
					<div align="right">
						有无家庭所在地村、镇(居委、街办)出具的有关证明
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="ywzm" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						拟申请补助金额
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="nsqbzje" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						班主任意见
					</div>
				</td>
				<td colspan="8">
					<br />
					<div align="right">
						签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xyshyj" />
					<br />
					<div align="right">
						签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学校意见
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxshyj" />
					<br />
					<div align="right">
						签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						院领导审批
					</div>
				</td>
				<td colspan="8">
					<br />
					<div align="right">
						签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','江苏信息职业技术<bean:message key="lable.xsgzyxpzxy" /> 伤、病困难补助申请表')" />
	</div>
</body>
</html:html>
