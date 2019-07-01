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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdsq";
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
								中国地质大学（武汉）<br />
								<bean:write name="rs" property="xn" />
								学年家庭经济困难学生认定申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />:
						<logic:empty name="rs" property="xymc">
						________________________________
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<u> &nbsp;<bean:write name="rs" property="xymc" />&nbsp; </u>
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="4">
								<div align="center">
									学
									<br />
									生
									<br />
									本
									<br />
									人
									<br />
									基
									<br />
									本
									<br />
									情
									<br />
									况
								</div>
							</td>
							<td width="10%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xh" />
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
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="xb" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="zfsh" />
								</div>
							</td>
							<td>
								<div align="center">
									班号
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭人均
									<br />
									年收入
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtrjnsr" />
									&nbsp;元
								</div>
							</td>
							<td>
								<div align="center">
									在校联系
									<br />
									电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="zxlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtdz" />
								</div>
							</td>
							<td>
								<div align="center">
									所属县市
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="ssxs" />
								</div>
							</td>
							<td>
								<div align="center">
									是否地震
									<br />
									重灾区
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name="rs" property="sfdzzzq" />
									</logic:notEmpty>
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
									陈
									<br />
									述
									<br />
									申
									<br />
									请
									<br />
									认
									<br />
									定
									<br />
									理
									<br />
									由
								</div>
							</td>
							<td colspan="8">
								<br />
								<br />
								<logic:empty name="rs" property="xscssqly">
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
								<logic:notEmpty name="rs" property="xscssqly">
									<bean:write name="rs" property="xscssqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									学生签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______年___月___日&nbsp;&nbsp;
								</div>
								<div align="left">
									注:可另附详细情况说明。
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="4">
								<div align="center">
									民
									<br />
									主
									<br />
									评
									<br />
									议
								</div>
							</td>
							<td rowspan="4">
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
								A.家庭经济一般困难&nbsp;
								<logic:equal name="rs" property="tjdc" value="一般困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="tjdc" value="一般困难">
								□
								</logic:notEqual>
							</td>
							<td rowspan="4">
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
							<td colspan="5" rowspan="4">
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
								<div align="right">
									评议小组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______年___月___日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								B.家庭经济困难&nbsp;
								<logic:equal name="rs" property="tjdc" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="tjdc" value="困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								C.家庭经济特殊困难&nbsp;
								<logic:equal name="rs" property="tjdc" value="特殊困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="tjdc" value="特殊困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								D.家庭经济不困难&nbsp;
								<logic:equal name="rs" property="tjdc" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="tjdc" value="不困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									认
									<br />
									定
									<br />
									决
									<br />
									定
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									(课部)
									<br />
									意见
								</div>
							</td>
							<td colspan="2">
								经评议小组推荐、本<bean:message key="lable.xsgzyxpzxy" />(课部)认真审核后，
								<br />
								<logic:equal name="rs" property="xysh" value="未审核">
								□&nbsp;同意评议小组意见。
								<br />
								□&nbsp;不同意评议小组意见。
								<br />
								调整为______________。
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="未审核">
									<logic:equal name="rs" property="tj_xy" value="1">
										√&nbsp;同意评议小组意见。
										<br />
										□&nbsp;不同意评议小组意见。
										<br />
										调整为_______________。
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
								工作组组长签字：
								<div align="right">
									_____年___月___日&nbsp;
								</div>
								<br />
							</td>
							<td>
								<div align="center">
									学生工作处
									<br />
									大学生资助
									<br />
									中心意见
								</div>
							</td>
							<td colspan="4">
								经学生所在院(系)提请，本中心认真核实，
								<br />
								<logic:equal name="rs" property="xxsh" value="未审核">
								□&nbsp;同意工作组和评议小组意见。
								<br />
								□&nbsp;不同意工作组和评议小组意见。
								<br />
								调整为：___________________。
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="未审核">
									<logic:equal name="rs" property="xy_xx" value="1">
										√&nbsp;同意工作组和评议小组意见。
										<br />
										□&nbsp;不同意工作组和评议小组意见。
										<br />
										调整为___________________________。
									</logic:equal>
									<logic:equal name="rs" property="xy_xx" value="0">
										□&nbsp;同意工作组和评议小组意见。
										<br />
										√&nbsp;不同意工作组和评议小组意见。
										<br />
										调整为<u>&nbsp;<bean:write name="rs" property="xxsh" />&nbsp;</u>。
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								负责人签字：
								<br />
								<br />
								<div align="right">
									______年___月___日&nbsp;&nbsp;
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
