<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">


	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">
			<fieldset>
				<legend>
					个人简历详细信息
				</legend>
				<table width="100%" class="tbstyle" id="grjl">
					<thead>
						<tr>
							<td colspan="8" align="center">
								身份证号：
								<html:text name="rs" property="id" readonly="true"
									style="width:130px" />
								<html:checkbox name="rs" property="idsee" value="no" />
								(保密) &nbsp;&nbsp; 所属<bean:message key="lable.xsgzyxpzxy" />：
								<html:text name="rs" property="xymc" readonly="true"
									style="width:100px" />
								&nbsp;&nbsp; 学号：
								<html:text name="rs" property="xsxh" readonly="true"
									style="width:80px" />
								&nbsp;&nbsp; 发布时间：
								<html:text name="rs" property="fbsj" style="width:130px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr>
						<td rowspan="3" align="center" width="3%">
							<b>个<br>人<br>资<br>料</b>
						</td>
						<td align="right">
							姓名：
						</td>
						<td width="100">
							<bean:write name="rs" property="name" />
						</td>
						<td align="right">
							性别：
						</td>
						<td width="15%">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							出生年月：
						</td>
						<td width="15%">
							<bean:write name="rs" property="csny" />
						</td>
						<td rowspan="4" align="center" width="9%">
							照片
						</td>
					</tr>
					<tr>
						<td align="right">
							民族：
						</td>
						<td>
							<bean:write name="rs" property="mz" />
						</td>
						<td align="right">
							学历：
						</td>
						<td>
							<bean:write name="rs" property="xl" />
						</td>
						<td align="right">
							政治面貌：
						</td>
						<td>
							<bean:write name="rs" property="zzmm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							专业名称：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							辅修专业：
						</td>
						<td>
							<bean:write name="rs" property="fxzymc" />
						</td>
						<td align="right">
							生源地区：
						</td>
						<td>
							<bean:write name="rs" property="sydq" />
						</td>
					</tr>
					<tr>
						<td rowspan="2" align="center">
							<b>联<br>系<br>方<br>法</b>
						</td>
						<td align="right">
							联系地址：
						</td>
						<td colspan="2">
							<bean:write name="rs" property="lxdz" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td colspan="2">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							邮政编码：
						</td>
						<td colspan="2">
							<bean:write name="rs" property="yzbm" />
						</td>
						<td align="right">
							电子邮箱：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">
							<b>学<br>生<br>综<br>合<br>情<br>况</b>
						</td>
						<td align="center">
							获奖情况
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="hjqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							学习情况
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="xxqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							校级以
							<br>
							上奖励
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="xjysjl" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							社会实
							<br>
							践情况
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="shsjqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							工作经历
						</td>
						<td colspan="2">
							<html:textarea name="rs" property="gzjl" rows="4"
								style="width=100%" readonly="true" />
						</td>
						<td align="center">
							个人特长
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="grtc" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>自<br>我<br>推<br>荐</b>
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="zwtj" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>学<br>校<br>推<br>荐</b>
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xxtj" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
				</table>
			</fieldset>
		</html:form>
		<logic:notEmpty name="notice">
			<logic:equal name="notice" value="no">
				<script>
                       alert("该学生简历未通过学校审核，为保证简历的真实性暂时无法查看！");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
