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
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" /><br>
			国家助学贷款申请意向表
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="7">
					<div align="center">
						系 &nbsp;<u>&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; 班级 &nbsp;<u>&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; 姓名 &nbsp;<u>&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; 性别 &nbsp;<u>&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; 出生日期 &nbsp;<u>&nbsp;
						<bean:write name='rs' property="csrq" /></u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						家庭住址
					</div>
				</td>
				<td colspan="3" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtzz" />
					</div>
				</td>
				<td scope="col" width="20%">
					<div align="center">
						学生联系电话
					</div>
				</td>
				<td scope="col" width="20%">
					<div align="center">
						<bean:write name='rs' property="xslxdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
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
						<bean:message key="lable.xsgzyxpzxy" />统编学号码
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						家庭成员
					</div>
				</td>
				<td width="15%">
					<div align="center">
						称谓
					</div>
				</td>
				<td width="15%">
					<div align="center">
						年龄
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<p>
							工作单位及职务
						</p>
					</div>
				</td>
				<td>
					<div align="center">
						年收入
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学习情况
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						入学成绩 <u>&nbsp;
						<bean:write name='rs' property="rxcj" />
						&nbsp;</u>分，上学期班内排名第 <u>&nbsp;
						<bean:write name='rs' property="sxqpm" />
						&nbsp;</u>名，课程成绩平均分 <u>&nbsp;
						<bean:write name='rs' property="pjcj" />
						&nbsp;</u>分。
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						日常表现
					</div>
				</td>
				<td colspan="5" height="30">
					<bean:write name='rs' property="rcbx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						奖惩情况
					</div>
				</td>
				<td colspan="5" height="30">
					<bean:write name='rs' property="jcqk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭经济困难情况
					</div>
				</td>
				<td colspan="5" height="50">
					<bean:write name='rs' property="jtjjknzk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						缴费情况
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						全学年学费 <u>&nbsp;
						<bean:write name='rs' property="xnxf" />
						&nbsp;</u>元， 家庭提供培养费生活费不足数：<u> &nbsp;
						<bean:write name='rs' property="jtfybzs" />
						&nbsp;</u>元， 缓缴学费：<u> &nbsp;
						<bean:write name='rs' property="hjxf" />
						&nbsp;</u>元
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						贷款意向
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						申请贷款种类：江苏省信用社农村生源地助学贷款(高等学校国家助学贷款)
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<div align="left">
						本学期拟贷款数 <u>&nbsp;
						<bean:write name='rs' property="bxqdks" />
						&nbsp;</u>元， 计划 <u>&nbsp;
						<bean:write name='rs' property="jhhkkssj" />
						&nbsp;</u>年—— <u>&nbsp;
						<bean:write name='rs' property="jhhkjssj" />
						&nbsp;</u>年归还
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						班主任意见
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						(同意贷生源地农村信用社贷款还是高等国家助学贷款)
					</div>
					<div align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						系部意见
					</div>
				</td>
				<td colspan="5" height="20">
					<bean:write name='rs' property="xyshyj" />
					<br>
					<br>
					<div align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="5">
					<bean:write name='rs' property="bz" />
					<br>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div align="center">
						此表留存各系部(管理区).系部同意后，给学生出具生源地贷款证明或填写高校贷款审批表
					</div>
					<div align="right">
						填表日期:&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','江苏信息职业技术<bean:message key="lable.xsgzyxpzxy" />国家助学贷款申请意向表')" />
	</div>
</body>
</html:html>
