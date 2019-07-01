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
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />家庭经济困难学生认定申请表
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="4%">
								<div align="center">
									<strong>学<br /> 生<br /> 本<br /> 人<br /> 基<br /> 本<br />
										情<br /> 况</strong>
								</div>
							</td>
							<td width="8%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="6%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									学号
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									出生
									<br />
									年月
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="csny" />
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
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治
									<br />
									面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									宿舍及
									<br />
									宿舍电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="ssjssdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									分院
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									手机
									<br />
									号码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="sjhm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									家庭
									<br />
									住址
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtzz" />
							</td>
							<td>
								<div align="center">
									家庭
									<br />
									电话
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtdh" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭人均
									<br />
									月收入
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtrjysr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>学<br />生<br />陈<br />述<br />申<br />请<br />认<br />定<br />理<br />由</strong>
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
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									学生签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______年____月____日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="left">
									(注：可另附详细情况说明)
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									<strong>民<br />主<br />评<br />议</strong>
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
							<td colspan="2">
								A.家庭经济困难&nbsp;
								<logic:equal name="rs" property="mzpy" value="困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="困难">
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
							<td colspan="4" rowspan="3">
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
									班导师签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______年____月____日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								B.家庭经济特别困难&nbsp;
								<logic:equal name="rs" property="mzpy" value="特别困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="特别困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								C.家庭经济不困难&nbsp;
								<logic:equal name="rs" property="mzpy" value="不困难">
								√
								</logic:equal>
								<logic:notEqual name="rs" property="mzpy" value="不困难">
								□
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<strong>认<br />定<br />决<br />定</strong>
								</div>
							</td>
							<td>
								<div align="center">
									分
									<br />
									院
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="3">
								经班级推荐、本分院认真审核后，
								<br />
								<logic:equal name="rs" property="xysh" value="未审核">
								□&nbsp;同意班级小组意见。
								<br />
								□&nbsp;不同意班级小组意见。
								<br />
								调整为______________。
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="未审核">
									<logic:equal name="rs" property="tj_xy" value="1">
										√&nbsp;同意班级小组意见。
										<br />
										□&nbsp;不同意班级小组意见。
										<br />
										调整为_______________。
									</logic:equal>
									<logic:equal name="rs" property="tj_xy" value="0">
										□&nbsp;同意班级小组意见。
										<br />
										√&nbsp;不同意班级小组意见。
										<br />
										调整为<u>&nbsp;<bean:write name="rs" property="xysh" />&nbsp;</u>。
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								签字：
								<br />
								<div align="right">
									______年____月____日
								</div>
							</td>
							<td>
								<div align="center">
									学
									<br />
									院
									<br />
									审
									<br />
									定
									<br />
									意
									<br />
									见
								</div>
							</td>
							<td colspan="3">
								经审核，
								<br />
								<logic:equal name="rs" property="xxsh" value="未审核">
								□&nbsp;同意分院意见。
								<br />
								□&nbsp;不同意分院意见。
								<br />
								调整为：_______________。
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="未审核">
									<logic:equal name="rs" property="xy_xx" value="1">
										√&nbsp;同意分院意见。
										<br />
										□&nbsp;不同意分院意见。
										<br />
										调整为：_______________。
									</logic:equal>
									<logic:equal name="rs" property="xy_xx" value="0">
										□&nbsp;同意分院意见。
										<br />
										√&nbsp;不同意分院意见。
										<br />
										调整为：<u>&nbsp;<bean:write name="rs" property="xxsh" />&nbsp;</u>。
									</logic:equal>
								</logic:notEqual>
								<br />
								<br />
								<br />
								<div align="right">
									______年____月____日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="center">
									(加盖部门公章)
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
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
