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
	<style type="text/css">
	<!--
	.style1 {font-family: "华文行楷"}
	-->
	</style>
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
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								国家助学贷款申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="right">
						填表日期:&nbsp;&nbsp;&nbsp;&nbsp;
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
				<td scope="row">
					<table width="100%" class="tbstyle">
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
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="xsxz" value="本科">
											本科
										</logic:equal>
										<logic:equal name="rs" property="xsxz" value="专科">
											专科
										</logic:equal>
										<logic:equal name="rs" property="xsxz" value="专接本">
											专接本
										</logic:equal>
									</logic:notEmpty>
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
									出生日期
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="csrq" />
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
								<bean:write name='rs' property="yxjzdz" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
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
								<logic:empty name="rs" property="jtjjqk">
									<br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqk">
									<bean:write name='rs' property="jtjjqk" />
								</logic:notEmpty>
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
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="xfdk" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									应交学费
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="yjxf" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									生活费贷款
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="shfdk" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									合计
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="hj" />
									</logic:notEmpty>
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
						<tr>
							<td colspan="11">
								<div align="center">
									上学年学习成绩
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									课程名称
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									成绩
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									课程名称
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									成绩
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj1_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj1_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj2_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj2_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj3_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj3_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj4_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj4_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj5_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj5_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj6_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj6_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj7_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj7_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj8_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj8_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj9_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj9_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj10_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj10_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									入学以来不及格科目
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxlbjgkm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									入学以来补考通过科目
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="rxlbktgkm" />
								</div>
							</td>
						</tr>
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
									<bean:message key="lable.xsgzyxpzxy" />意见
								</div>
							</td>
							<td colspan="9">
								<br />
								<br />
								<br />
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />主管领导签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
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
								<br />
								<br />
								<div align="right">
									领导签字（盖章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="left">
					&nbsp;&nbsp;填表说明:　1.学生必须保证以上信息真实无误，不得涂改。<br />
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.“家庭经济情况”要注明家庭人口情况、收入来源、月人均收入。
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
