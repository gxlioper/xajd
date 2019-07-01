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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								高等学校家庭经济困难学生认定申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						<br /><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;教学单位:
						<u> &nbsp;中国美术<bean:message key="lable.xsgzyxpzxy" />&nbsp; </u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="4">
								<div align="center">
									<strong>学<br />生<br />本<br />人<br />基<br />本<br />情<br />况</strong>
								</div>
							</td>
							<td width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									出生
									<br />
									年月
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证
									<br />
									号码
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭人均
									<br />
									年收入
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtrjnsr">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjnsr">
									<bean:write name="rs" property="jtrjnsr" />&nbsp;
									</logic:notEmpty>
									元
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									系别
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									年级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="nj" />
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
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									寝室
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="qs" />
								</div>
							</td>
							<td>
								<div align="center">
									联系
									<br />
									电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xslxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>学<br /> 生<br /> 陈<br /> 述<br /> 申<br /> 请<br />
										认<br /> 定<br /> 理<br /> 由</strong>
								</div>
							</td>
							<td colspan="8">
								<br />
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
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
								<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									学生签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______年____月____日&nbsp;&nbsp;
								</div>
								<div align="left">
									&nbsp;&nbsp;(注:可另附详细情况说明。)
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									<strong>民<br /> 主<br /> 评<br /> 议</strong>
								</div>
							</td>
							<td rowspan="3">
								<div align="center">
									推
									<br />
									荐
									<br />
									档
									<br />
									次
								</div>
							</td>
							<td>
								A.家庭经济困难&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="困难">
								□
								</logic:notEqual>
							</td>
							<td rowspan="3">
								<div align="center">
									陈
									<br />
									述
									<br />
									理
									<br />
									由
								</div>
							</td>
							<td colspan="5" rowspan="3">
								<br />
								<logic:empty name="rs" property="csly">
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="csly">
								<bean:write name="rs" property="csly" />
								</logic:notEmpty>
								<br />
								<div align="left">
									评议小组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______年____月____日
								</div>
							</td>
						</tr>
						<tr>
							<td>
								B.家庭经济特殊困难&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="特殊困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								C.家庭经济不困难&nbsp;
								<logic:equal name="rs" property="mzpyjg" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpyjg" value="不困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>认<br /> 定<br /> 决<br /> 定</strong>
								</div>
							</td>
							<td>
								<div align="center">
									院(系)
									<br />
									意见
								</div>
							</td>
							<td colspan="2">
								经评议小组推荐、本院(系)认真审核后，
								<br />
								<logic:equal name="rs" property="xysh" value="未审核">
								□&nbsp;同意评议小组意见。
								<br />
								□&nbsp;不同意评议小组意见。
								<br />
								调整为_____________。
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="未审核">
									<logic:equal name="rs" property="tj_xy" value="1">
										√&nbsp;同意评议小组意见。
										<br />
										□&nbsp;不同意评议小组意见。
										<br />
										调整为_____________。
									</logic:equal>
									<logic:equal name="rs" property="tj_xy" value="0">
										□&nbsp;同意评议小组意见。
										<br />
										√&nbsp;不同意评议小组意见。
										<br />
										调整为<u>&nbsp;<bean:write name="rs" property="xysh" />&nbsp;</u>。
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								认定小组组长签字：
								<br />
								<br />
								<div align="right">
									______年____月____日
								</div>
								<br />
							</td>
							<td>
								<div align="center">
									学校学生
									<br />
									资助管理
									<br />
									机构意见
								</div>
							</td>
							<td colspan="4">
								经学生所在院(系)提请，本机构认真核实，
								<br />
								<logic:equal name="rs" property="xxsh" value="未审核">
								□&nbsp;同意认定小组和评议小组意见。
								<br />
								□&nbsp;不同意认定小组和评议小组意见。
								<br />
								调整为：_____________________。
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="未审核">	
									<logic:equal name="rs" property="xy_xx" value="1">
										√&nbsp;同意认定小组和评议小组意见。
										<br />
										□&nbsp;不同意认定小组和评议小组意见。
										<br />
										调整为：_____________________。
									</logic:equal>
									<logic:equal name="rs" property="xy_xx" value="0">
										□&nbsp;同意认定小组和评议小组意见。
										<br />
										√&nbsp;不同意认定小组和评议小组意见。
										<br />
										调整为：<u>&nbsp;<bean:write name="rs" property="xxsh" />&nbsp;</u>。
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								负责人签字：
								<br />
								<br />
								<div align="right">
									______年____月____日&nbsp;&nbsp;
								</div>
								<br />
								<div align="center">
									(加盖部门公章)
								</div>
								<br />
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
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
