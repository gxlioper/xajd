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
	<html:form action="bjlhdx_xszz.do?method=gjzxdksqb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>国&nbsp;家&nbsp;助&nbsp;学&nbsp;贷&nbsp;款&nbsp;申&nbsp;请&nbsp;表</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						填表日期:&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%"  class="tbstyle">
						<tr>
							<td width="9%">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="11%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									姓名
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									本/专/专接本
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="pycc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									学制
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									性别
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									入学年月
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									毕业年月
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									有效居住
									<br />
									地址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtzz" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
								<br />
								<div align="center">
									<bean:write name='rs' property="gddh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭经济情况
									<br />
									(请详细填写)
								</div>
							</td>
							<td colspan="10">
								<bean:write name='rs' property="jtjjqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									申请贷款项目
									<br />
									及额度(元)
								</div>
							</td>
							<td>
								<div align="center">
									学费贷款
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xfdkze" />
								</div>
							</td>
							<td>
								<div align="center">
									应交学费
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yjxf" />
								</div>
							</td>
							<td>
								<div align="center">
									生活费贷款
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="shfdkze" />
								</div>
							</td>
							<td>
								<div align="center">
									合计
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="dkze" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家长意见
								</div>
							</td>
							<td colspan="10">
								<br />
								<br />
								<div align="right">
									家长签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td colspan="11">--%>
<%--								<div align="center">--%>
<%--									上学年学习成绩--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									课程名称--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									成绩--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									课程名称--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									成绩--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj1_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj1_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj2_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj2_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj3_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj3_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj4_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj4_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj5_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj5_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj6_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj6_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj7_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj7_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj8_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj8_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj9_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj9_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj10_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj10_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									入学以来不及格科目--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="rxlbjgkm" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									入学以来补考通过科目--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="rxlbktgkm" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td colspan="3">
								<div align="center">
									学生所在<bean:message key="lable.xsgzyxpzxy" />教学
									<br />
									办公室核实（盖章）
								</div>
							</td>
							<td colspan="8">
								<br />
								<br />
								<div align="right">
									盖章人签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									班主任及
									<br />
									班委会意见
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="fdyshyj" />
								<br />
								<div align="right">
									班主任签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="fdshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="fdshsj">
										<bean:write name='rs' property="fdshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />意见
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />主管领导签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="xyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">
										<bean:write name='rs' property="xyshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									学校意见
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									领导签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="xxshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">
										<bean:write name='rs' property="xxshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;填表说明: 1.学生必须保证以上信息真实无误，不得涂改，一式两份。
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.“家庭经济情况”要注明家庭人口情况、收入来源、月人均收入。
					</div>
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
