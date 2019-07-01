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
	<html:form action="gjjzxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			国家和省政府助学奖学金申请表
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td scope="col" width="4%">
					<div align="center">
						申
						<br>
						请
						<br>
						类
						<br>
						型
					</div>
				</td>
				<td colspan="8" scope="col">
					<div align="center">
						<bean:write name="rs" property="jxjmc" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="4" scope="row" width="4%">
					<div align="center">
						学
						<br>
						生
						<br>
						本
						<br>
						人
						<br>
						情
						<br>
						况
					</div>
				</td>
				<td width="12%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="xm" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						性别
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="xb" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						出生年月
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="csny" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						民族
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="mzmc" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<p align="center">
						&nbsp;
						<bean:write name="rs" property="xxmc" />
						&nbsp;大学&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xsgzyxpzxy" />&nbsp;
						<bean:write name="rs" property="xmc" />
						&nbsp;系&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;班
					</p>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						入学时间
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="rxny" />
					</div>
				</td>
				<td>
					<div align="center">
						高考总分
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="gkfs" />
					</div>
				</td>
				<td>
					<div align="center">
						毕业高中
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="bygz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						曾获何种奖励
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="jlxx" />
				</td>
			</tr>
			<tr>
				<td rowspan="3" scope="row">
					<div align="center">
						家庭经济情况
					</div>
				</td>
				<td>
					<div align="center">
						家庭住址
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtzz" />
					</div>
				</td>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="yzbm" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭户口
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="radjthk" />
					</div>
				</td>
				<td>
					<div align="center">
						家庭总人数
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="hkrs" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						家庭月总收入
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtyzsr" />
					</div>
				</td>
				<td>
					<div align="center">
						收入来源
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtsrly" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						人均收入来源
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtrjsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						家庭成员情况
					</div>
				</td>
				<td>
					<div align="center">
						姓名
					</div>
				</td>
				<td>
					<div align="center">
						年龄
					</div>
				</td>
				<td>
					<div align="center">
						与本人关系
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						工作或学习单位
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="7" scope="row">
					<div align="center">
						上学年成绩或高考成绩
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						课程名称
					</div>
				</td>
				<td>
					<div align="center">
						分数或等第
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						课程名称
					</div>
				</td>
				<td>
					<div align="center">
						分数或等第
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc1_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc1_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc2_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc2_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc3_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc3_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc4_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc4_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc5_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc5_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc6_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc6_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc7_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc7_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc8_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc8_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc9_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc9_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc10_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc10_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc11_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc11_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc12_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc12_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td height="60" colspan="9" valign="top">
					<p>
						申请理由：
					</p>
					<bean:write name="rs" property="sqly" />
					<p>
					<br>
					<br>
					<p align="right">
					(申请人保证以上所填写情况全部属实)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						申请人签名：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="9" valign="top">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
						<br>
						<br>
					<p align="right">
						<bean:message key="lable.xsgzyxpzxy" />领导签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="9" valign="top">
					<p>
						学校审核及公示意见：
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p align="right">
						校领导签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（学校公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','国家和省政府助学奖学金申请表')" />
	</div>
</body>
</html:html>
