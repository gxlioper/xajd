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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=knsrd1sq";
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
							<strong> 高等学校家庭经济困难学生认定申请表 </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br /><br />
  					<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学校:<u>&nbsp;&nbsp;<bean:write name='rs' property="xxmc" />&nbsp;&nbsp;</u></div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="8%">
								<div align="center">
									学生
									<br />
									本人
									<br />
									基本
									<br />
									情况
								</div>
							</td>
							<td width="12%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="22%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治
									<br />
									面貌
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
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
									<bean:write name='rs' property="jtrjnsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									年级
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									在校联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zxlxdh" />
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
								<logic:empty name="rs" property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									学生签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;&nbsp;
								</div>
								<div align="left">
									(注:可另附详细情况说明。)
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
								<div align="left">
									<logic:equal name="rs" property="fdysh" value="一般困难">
									A.家庭经济一般困难&nbsp;&nbsp;√
									</logic:equal>
									<logic:notEqual name="rs" property="fdysh" value="一般困难">
									A.家庭经济一般困难&nbsp;&nbsp;□
									</logic:notEqual>
								</div>
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
								<logic:empty name="rs" property="fdyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="left">
									评议小组组长签字：
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="left">
									<logic:equal name="rs" property="fdysh" value="困难">
									B.家庭经济困难&nbsp;&nbsp;√
									</logic:equal>
									<logic:notEqual name="rs" property="fdysh" value="困难">
									B.家庭经济困难&nbsp;&nbsp;□
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="left">
									<logic:equal name="rs" property="mzrd" value="特殊困难">
									C.家庭经济特殊困难&nbsp;&nbsp;√
									</logic:equal>
									<logic:notEqual name="rs" property="mzrd" value="特殊困难">
									C.家庭经济特殊困难&nbsp;&nbsp;□
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="left">
									<logic:equal name="rs" property="mzrd" value="不困难">
									D.家庭经济不困难&nbsp;&nbsp;√
									</logic:equal>
									<logic:notEqual name="rs" property="mzrd" value="不困难">
									D.家庭经济不困难&nbsp;&nbsp;□
									</logic:notEqual>
								</div>
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
									院
									<br />
									(系)
									<br />
									意
									<br />
									见
								</div>
							</td>
							<logic:empty name="rs" property="xh">
								<td colspan="2">
									<div align="left">
										&nbsp;经评议小组推荐、本院(系)认真审核后，
										<br />
										&nbsp;□&nbsp;&nbsp;同意评议小组意见。
										<br />
										&nbsp;□&nbsp;&nbsp;不同意评议小组意见。调整为
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;工作组组长签字：
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										学
										<br />
										校
										<br />
										学
										<br />
										生
										<br />
										资
										<br />
										助
										<br />
										管
										<br />
										理
										<br />
										机
										<br />
										构
										<br />
										意
										<br />
										见
									</div>
								</td>
								<td colspan="4">
									<div align="left">
										&nbsp;经学生所在院(系)提请，经本机构认真核实，
										<br />
										&nbsp;□&nbsp;&nbsp;同意工作小组和评议小组意见。
										<br />
										&nbsp;□&nbsp;&nbsp;不同意工作小组和评议小组意见。
										<br />
										&nbsp;调整为
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;负责人签字：
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;
									</div>
									<div align="left">
										&nbsp;(加盖部门公章)
									</div>
								</td>
							</logic:empty>
							<logic:notEmpty name="rs" property="xh">
								<td colspan="2">
									<div align="left">
										&nbsp;经评议小组推荐、院(系)认真审核后，
										<br />
										<logic:equal name="rs" property="xysh" value="未审核">
									&nbsp;□&nbsp;&nbsp;同意评议小组意见。
									<br />
									&nbsp;□&nbsp;&nbsp;不同意评议小组意见。调整为
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
									</logic:equal>
										<logic:notEqual name="rs" property="xysh" value="未审核">
											<logic:equal name="xt1" value="is">
											&nbsp;√&nbsp;&nbsp;同意评议小组意见。
											<br />
											&nbsp;□&nbsp;&nbsp;不同意评议小组意见。调整为
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
										</logic:equal>
											<logic:equal name="xt1" value="no">
											&nbsp;□&nbsp;&nbsp;同意评议小组意见。
											<br />
											&nbsp;√&nbsp;&nbsp;不同意评议小组意见。调整为
											<u>&nbsp;<bean:write name='rs' property="xysh" />&nbsp;</u>。
										</logic:equal>
										</logic:notEqual>
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;工作组组长签字：
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;
									</div>
								</td>
								<td>
									<div align="center">
										学
										<br />
										校
										<br />
										学
										<br />
										生
										<br />
										资
										<br />
										助
										<br />
										管
										<br />
										理
										<br />
										机
										<br />
										构
										<br />
										意
										<br />
										见
									</div>
								</td>
								<td colspan="4">
									<div align="left">
										&nbsp;经学生所在院(系)提请，经本机构认真核实，
										<br />
										<logic:equal name="rs" property="xxsh" value="未审核">
									&nbsp;□&nbsp;&nbsp;同意工作小组和评议小组意见。
									<br />
									&nbsp;□&nbsp;&nbsp;不同意工作小组和评议小组意见。
									<br />
									&nbsp;调整为
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
									</logic:equal>
										<logic:notEqual name="rs" property="xxsh" value="未审核">
											<logic:equal name="xt2" value="is">
											&nbsp;√&nbsp;&nbsp;同意工作小组和评议小组意见。
											<br />
											&nbsp;□&nbsp;&nbsp;不同意工作小组和评议小组意见。调整为
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
										</logic:equal>
											<logic:equal name="xt2" value="no">
											&nbsp;□&nbsp;&nbsp;同意工作小组和评议小组意见。
											<br />
											&nbsp;√&nbsp;&nbsp;不同意工作小组和评议小组意见。调整为
											<u>&nbsp;<bean:write name='rs' property="xxsh" />&nbsp;</u>。
										</logic:equal>
										</logic:notEqual>
									</div>
									<br />
									<br />
									<div align="left">
										&nbsp;负责人签字：
									</div>
									<br />
									<div align="right">
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>月
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;
									</div>
									<div align="left">
										&nbsp;(加盖部门公章)
									</div>
								</td>
							</logic:notEmpty>
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
