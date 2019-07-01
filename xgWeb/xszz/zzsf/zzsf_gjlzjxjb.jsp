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
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								漳州师范<bean:message key="lable.xsgzyxpzxy" />国家励志奖学金申请表
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" scope="col" width="4%">
								<div align="center">
									本人情况
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									姓名
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									性别
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="16%" rowspan="4" scope="col">
								<div align="center">
									照片
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									民族
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									入学时间
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxrq" />
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
									联系电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<logic:empty name="rs" property="xymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="xymc">
								<bean:write name='rs' property="xymc" />
								</logic:notEmpty>
								&nbsp;系&nbsp;&nbsp;
								<logic:empty name="rs" property="zymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="zymc">
								<bean:write name='rs' property="zymc" />
								</logic:notEmpty>
								&nbsp;专业&nbsp;&nbsp;
								<logic:empty name="rs" property="bjmc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="bjmc">
								<bean:write name='rs' property="bjmc" />
								</logic:notEmpty>
								&nbsp;班级&nbsp;&nbsp;
								<logic:empty name="rs" property="xh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
								<bean:write name='rs' property="xh" />
								</logic:notEmpty>
								&nbsp;学号
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									曾
									<br />
									获
									<br />
									何
									<br />
									种
									<br />
									奖
									<br />
									励
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="chhzjl" />
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td>
								<div align="center">
									家庭户口
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jthk" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭人口总数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭月总收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									人均月收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									收入来源
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭住址
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
							</td>
							<td>
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
							<td rowspan="2" scope="row">
								<div align="center">
									学习成绩
								</div>
							</td>
							<td>
								<div align="center">
									上学年综合测评总排名
								</div>
							</td>
							<td colspan="6">
								<logic:empty name='rs' property="sxnzhcpzpm" >
								<div align="right">
									（名次 /本专业年级总人数）
								</div>
								</logic:empty>
								<logic:notEmpty name='rs' property="sxnzhcpzpm" >
								<bean:write name='rs' property="sxnzhcpzpm" />
								&nbsp;&nbsp;（名次 /本专业年级总人数）
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									上学年学习成绩总排名
								</div>
							</td>
							<td colspan="6">
								<logic:empty name='rs' property="sxnxxcjzpm" >
								<div align="right">
									（名次 /本专业年级总人数）
								</div>
								</logic:empty>
								<logic:notEmpty name='rs' property="sxnxxcjzpm" >
								<bean:write name='rs' property="sxnxxcjzpm" />
								&nbsp;&nbsp;（名次 /本专业年级总人数）
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td colspan="8" scope="row">
								申请理由
								<br />
								<bean:write name='rs' property="sqly" />
								<br />
								<div align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8" scope="row">
								系审核意见
								<br />
								<bean:write name='rs' property="xysh" />
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<br />
								<div align="right">
									(公章)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td colspan="8" scope="row">--%>
<%--								系党总支意见--%>
<%--								<br />--%>
<%--								<bean:write name='rs' property="xyzzfzryj" />--%>
<%--								<br />--%>
<%--								<br />--%>
<%--								<div align="right">--%>
<%--									(公章)--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td colspan="8" scope="row">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
								<br />
								<bean:write name='rs' property="xxsh" />
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<br />
								<div align="right">
									(公章)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
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
