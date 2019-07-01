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
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong><bean:write name='rs' property="nd" />年度国家开发银行国家助学贷款申请审查表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						编号：10827<bean:write name='rs' property="nd" /><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;学校名称：长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="85%">
						<tr>
							<td width="4%" rowspan="10">
								<div align="center">
									借
									<br />
									款
									<br />
									学
									<br />
									生
									<br />
									及
									<br />
									家
									<br />
									庭
									<br />
									基
									<br />
									本
									<br />
									信
									<br />
									息
								</div>
							</td>
							<td width="13%">
								<div align="center">
									学生姓名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									性别
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									出生年月
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									入学时间
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />名称
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									专业
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td width="11%">
								<div align="center">
									班级
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									学制
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									电子邮箱
									<br />
									或QQ号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="dzyxhqqh" />
								</div>
							</td>
							<td>
								<div align="center">
									类别(在□打√)
								</div>
							</td>
							<td>
								<div align="center">
									□&nbsp;专&nbsp;&nbsp;□&nbsp;本
									<br />
									□&nbsp;硕&nbsp;&nbsp;□&nbsp;博
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学生电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xsdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									所在宿舍
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="szss" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭户口
									<br />
									详细地址
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtfk" />
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtfkyb" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭现在
									<br />
									详细地址
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="jtxxxzz" />
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtxxxzzyb" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									父母或监护人
								</div>
							</td>
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									称谓
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									身份证号
								</div>
							</td>
							<td>
								<div align="center">
									固定电话
								</div>
							</td>
							<td>
								<div align="center">
									手机电话
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_cw" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_gddh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr1_sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_cw" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_gddh" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmhjhr2_sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="5">
								<div align="center">
									申
									<br />
									请
									<br />
									贷
									<br />
									款
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									以往实际
									<br />
									贷款金额
									<br />
									(单位:元)
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									入学第一年
								</div>
							</td>
							<td>
								<div align="center">
									第二年
								</div>
							</td>
							<td>
								<div align="center">
									第三年
								</div>
							</td>
							<td>
								<div align="center">
									第四年
								</div>
							</td>
							<td>
								<div align="center">
									第五年
								</div>
							</td>
							<td>
								<div align="center">
									第六年
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="ywsjje1" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje2" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje3" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje4" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje5" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywsjje6" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									本次申请
									<br />
									贷款金额
									<br />
									(单位:元)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									(大写)
									&nbsp;<bean:write name='rs' property="sqjedx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									小写&nbsp;&nbsp;
									<bean:write name='rs' property="sqje" />
									&nbsp;元
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									申请贷款
									<br />
									期限
									<br />
									(单位:年)
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									<u>&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="sqdknx" />&nbsp;&nbsp;&nbsp;</u>年
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									其中
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									学费
								</div>
							</td>
							<td>
								<div align="center">
									住宿费
								</div>
							</td>
							<td>
								<div align="center">
									生活费
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sqje" />
								</div>
							</td>
							<td>
								<div align="center">
									0
								</div>
							</td>
							<td>
								<div align="center">
									0
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<div align="left">
									贷款学生申明：本人保证本表所填内容真实无误，本项申请属本人真实意思表示，如有不实，愿承担法律责任。
								</div>
								<div align="right">
									学生签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="sqsj">
										&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="sqsj">
										<bean:write name='rs' property="sqsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<div align="center">
									<strong>学&nbsp;校&nbsp;审&nbsp;核&nbsp;意&nbsp;见</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div align="center">
									辅导员审查意见
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审查意见
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									校资助中心审核意见
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;经与招生信息、学籍信息核实，该生基本信息和家庭经济困难情况属实，表内填写内容核对无误，资料齐全，符合申请贷款的条件。
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									签名：&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="fdyshsj">
										年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="fdyshsj">
										<bean:write name='rs' property="fdyshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									辅导员电话：
									<logic:empty name="rs" property="fdydh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="fdydh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="fdydh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
								<br />
							</td>
							<td colspan="3">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;借款申请人是我<bean:message key="lable.xsgzyxpzxy" />就度学生，表内所填内容属实，同意将学生申请贷款资料报送资助中心。
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />领导签名：
								</div>
								<div align="center">
									（<bean:message key="lable.xsgzyxpzxy" />盖章）&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="xyshsj">
										年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">
										<bean:write name='rs' property="xyshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />电话：
									<logic:empty name="rs" property="xydh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="xydh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="xydh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
								<br />
							</td>
							<td colspan="2">
								<br />
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;经审核，该生符合申请国家助学贷款条件，同意该学生申请国家助学贷款。
								</div>
								<br />
								<br />
								<br />
								<div align="center">
									经办人签名：
								</div>
								<div align="center">
									（中心盖章）&nbsp;
								</div>
								<br />
								<div align="right">
									<logic:empty name="rs" property="xxshsj">
										年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">
										<bean:write name='rs' property="xxshsj" />
									</logic:notEmpty>
									&nbsp;
								</div>
								<div align="right">
									校资助中心电话：
									<logic:empty name="rs" property="xxdh">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									</logic:empty>
									<logic:notEmpty name="rs" property="xxdh">
									<u>&nbsp;&nbsp;<bean:write name='rs' property="xxdh" />&nbsp;&nbsp;</u>
									</logic:notEmpty>
									&nbsp;&nbsp;
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
