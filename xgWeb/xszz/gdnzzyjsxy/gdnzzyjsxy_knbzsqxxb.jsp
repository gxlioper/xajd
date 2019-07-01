<%@ page language="java" contentType="text/html;charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<p align="center" style="font-size:24px">
						<bean:write name='rs' property="nd" />
						年度广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />
						<bean:write name='rs' property="knbzmc" />
						申请表
					</p>
					<div align="right">
						申请时间:
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="16%">
								学号
							</td>
							<td align="left" width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<td width="16%" scope="col">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="34%" scope="col">
								<bean:write name='rs' property="xm" />
							</td>
						</tr>
						<tr>
							<td width="16%" scope="row">
								<div align="center">
									性别
								</div>
							</td>
							<td width="34%">
								<bean:write name='rs' property="xb" />
							</td>
							<td>
								<div align="center">
									年级
								</div>
							</td>
							<td>
								<bean:write name='rs' property="nj" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									身份证号
								</div>
							</td>
							<td>
								<bean:write name='rs' property="sfzh" />
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xymc" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									专业
								</div>
							</td>
							<td>
								<bean:write name='rs' property="zymc" />
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td>
								<bean:write name='rs' property="bjmc" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									生源地
								</div>
							</td>
							<td>
								<bean:write name='rs' property="syd" />
							</td>
							<td>
								<div align="center">
									户口性质
								</div>
							</td>
							<td>
								<bean:write name='rs' property="hkxz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭居住地址
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtjzdz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<bean:write name='rs' property="yzbm" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td>
								<bean:write name='rs' property="lxdh" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭人口数
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtrks" />
							</td>
							<td>
								<div align="center">
									家庭人均月收入
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtrjysr" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭年总收入
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtnzsr" />
							</td>
							<td>
								<div align="center">
									学生本人月实际消费额度
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xsbrysjxfed" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭收入来源
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="srly" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭情况
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtqk" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									家庭经济情况说明
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtjjqksm" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									上学期成绩排名
								</div>
							</td>
							<td>
								<bean:write name="rs" property="sxqcjpm" />
							</td>
							<td>
								<div align="center">
									综合测评排名
								</div>
							</td>
							<td>
								<bean:write name='rs' property="zhcppm" />
							</td>
						</tr>
						<tr>
							<td scope="row" colspan="4">
								在校期间历年获得奖、助学金及困难补助和贷款记录：
								<div align="left">
									<%
												ArrayList list = (ArrayList) request
												.getAttribute("zzjl");
												for (Iterator it = list.iterator(); it.hasNext();) {
											String temp = (String)it.next();
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<%=temp%>
									<br />
									<%
									}
									%>
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									是否已参加勤工助学
								</div>
							</td>
							<td>
								<bean:write name="rs" property="sfycjqgzx" />
							</td>
							<td>
								<div align="center">
									欠学费金额
								</div>
							</td>
							<td>
								<bean:write name='rs' property="qxfje" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									申请奖学金等级
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jxjdj" />
							</td>
							<td>
								<div align="center">
									申请金额
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jlgrje" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									困难程度排名
								</div>
							</td>
							<td>
								<bean:write name="rs" property="kncdpm" />
							</td>
							<td scope="row">
								<div align="center">
									备注
								</div>
							</td>
							<td>
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									申请补助原因
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="sqbzyy" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />审核意见
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xyshyj" />
								<br />
								<div align="right">
									签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									学校审核意见
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xxshyj" />
								<br />
								<div align="right">
									签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
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
