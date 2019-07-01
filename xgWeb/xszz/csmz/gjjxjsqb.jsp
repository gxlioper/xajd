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
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>普通高等学校国家奖学金评审表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						(
						<logic:empty name="rs" property="xn">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xn">
						<bean:write name='rs' property="xn" />
						</logic:notEmpty>
						&nbsp;学年)
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学校：<bean:write name="xxmc" scope="session"/>&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
						<logic:empty name="rs" property="xymc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<bean:write name='rs' property="xymc" />
						</logic:notEmpty>
						&nbsp;&nbsp;专业：
						<logic:empty name="rs" property="zymc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<bean:write name='rs' property="zymc" />
						</logic:notEmpty>
						&nbsp;&nbsp;班级：
						<logic:empty name="rs" property="bjmc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="bjmc">
							<bean:write name='rs' property="bjmc" />
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="1500px">
						<tr>
							<td rowspan="4" width="4%">
								<div align="center">
									基
									<br />
									本
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="24%">
								<div align="center">
									学生姓名
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									民族
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									入学年月
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name="rs" property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学
									<br />
									生
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td colspan="19">
								<br />
								该学年必修课程数
								<logic:empty name="rs" property="gxnbxkcs">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="gxnbxkcs">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="gxnbxkcs" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								门,其中，优秀
								<logic:empty name="rs" property="yxkc">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="yxkc">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxkc" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								门，良好
								<logic:empty name="rs" property="lhkc">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="lhkc">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="lhkc" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								门
								<br />
								<br />
								成绩排名(专业或年级)：
								<logic:empty name="rs" property="cjpm">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="cjpm">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="cjpm" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								(名次/人数)
								<br />
								<br />
								综合考评成绩
								<logic:empty name="rs" property="zhkpcj">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="zhkpcj">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zhkpcj" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								分，排名
								<logic:empty name="rs" property="zhkppm">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="zhkppm">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zhkppm" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								(名次/人数)(如无此项，填写无)
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									获
									<br />
									奖
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td colspan="19">
								<br />
								主要奖项：
								<br />
								<logic:empty name="rs" property="zyjx">
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="zyjx">
								<bean:write name="rs" property="zyjx" />
								</logic:notEmpty>
								<br />
								其中，院级奖励
								<logic:empty name="rs" property="yjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="yjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								项；校级奖励
								<logic:empty name="rs" property="xjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="xjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								项；省级以上奖励
								<logic:empty name="rs" property="sjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="sjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								项
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									申
									<br />
									请
									<br />
									理
									<br />
									由
									<br />
									︵
									<br />
									全
									<br />
									面
									<br />
									反
									<br />
									映
									<br />
									德
									<br />
									智
									<br />
									体
									<br />
									美
									<br />
									情
									<br />
									况
									<br />
									︶
								</div>
							</td>
							<td colspan="19">
								<br />
								<logic:empty name="rs" property="sqly">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
									年
									<br />
									级
									<br />
									︵
									<br />
									专
									<br />
									业
									<br />
									︶
									<br />
									推
									<br />
									荐
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="19">
								<br />
								<br />
								<br />
								<div align="right">
									推荐人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行政职务：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
									院
									<br />
									︵
									<br />
									系
									<br />
									︶
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="19">
								<br />
								<br />
								<br />
								<div align="right">
									(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
									学
									<br />
									校意
									<br />
									见
								</div>
							</td>
							<td colspan="19">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在___________范围内公示______天，无异议，现报请同意该同学获得_______学年度国家奖学金。
								<br />
								<div align="right">
									(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
