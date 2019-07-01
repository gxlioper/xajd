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
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td colspan="19">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>学校：</strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xxmc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>院（系）：</strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xymc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>专业： </strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xmc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>年级： </strong>&nbsp;&nbsp;
						<bean:write name="rs" property="nj" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td rowspan="4" scope="col" width="5%">
					<div align="center">
						<strong>学生本人基本情况</strong>
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						姓 名
					</div>
				</td>
				<td colspan="2" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						性 别
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="xb" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						出生年月
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="csny" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						民 族
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="mzmc" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						身份证号码
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmm" />
				</td>
				<td>
					<p align="center">
						入学前户口
					</p>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="rxqhk" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭人口数
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtrks" />
				</td>
				<td>
					<p align="center">
						毕业学校
					</p>
				</td>
				<td>
					<bean:write name="rs" property="byxx" />
				</td>
				<td>
					<div align="center">
						个人特长
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="grtc" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						孤 残
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sfgc" />
				</td>
				<td>
					<div align="center">
						单 亲
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sfdq" />
				</td>
				<td>
					<div align="center">
						烈士子女
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sflszn" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						<strong>家庭通讯信息</strong>
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						详细通讯地址
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="jtxxtxdz" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtyzbm" />
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtlxdh" />
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						<strong>家庭成员情况</strong>
					</div>
				</td>
				<td>
					<div align="center">
						姓名
					</div>
				</td>
				<td width="10%">
					<div align="center">
						年龄
					</div>
				</td>
				<td width="10%">
					<p align="center">
						与学生关系
					</p>
				</td>
				<td colspan="3">
					<div align="center">
						工作（学习）单位
					</div>
				</td>
				<td>
					<div align="center">
						职业
					</div>
				</td>
				<td>
					<div align="center">
						年收入(元)
					</div>
				</td>
				<td>
					<div align="center">
						健康状况
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_nl" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_nl" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_nl" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_nl" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_nl" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row" height="200">
					<p align="center">
						<strong>影响家庭经济状况有关信息</strong>
					</p>
				</td>
				<td colspan="9">
					家庭人均年收入&nbsp;
					<bean:write name="rs" property="jtrjnsr" />
					（元）。学生本学年已获资助情况&nbsp;
					<bean:write name="rs" property="xsyhhjqk" />
					。
					<br />
					家庭遭受自然灾害情况：&nbsp;
					<bean:write name="rs" property="jtzszrzhqk" />
					。
					<br />
					家庭遭受突发意外事件：&nbsp;
					<bean:write name="rs" property="jtzstfywsj" />
					。
					<br />
					家庭成员因残疾、年迈而劳动能力弱情况：&nbsp;
					<bean:write name="rs" property="jtcyndlrqk" />
					。
					<br />
					家庭成员失业情况：&nbsp;
					<bean:write name="rs" property="jtcysyqk" />
					。家庭欠债情况：&nbsp;
					<bean:write name="rs" property="jtqzqk" />
					。
					<br />
					其他情况：&nbsp;
					<bean:write name="rs" property="qtqk" />
					。
				</td>
			</tr>
			<tr>
				<td scope="row" height="100">
					<div align="center">
						<strong>签章</strong>
					</div>
				</td>
				<td>
					<div align="center">
						学生本人
					</div>
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					<div align="center">
						学生家长或监护人
					</div>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
				<td>
					<div align="center">
						学生家庭所在地乡镇或街道民政部门
					</div>
				</td>
				<td colspan="3">
					<div align="left">
						<p>
							经办人签字：
						</p>
						<br />
						<p>
							单位名称：
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;（加盖公章）
						</p>
					</div>
					<div align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						<strong>民政部门信息</strong>
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						详细通讯地址
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="mzbmxxtxdz" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						邮政编码
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbmyzbm" />
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbmlxdh" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','高等学校学生及家庭情况调查表')" />
	</div>
</body>
</html:html>
