<%@ page language="java" contentType="text/html; charset=GBK"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	
	</script>
	<body>
		<html:form action="/jycxzmgl" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name='xxdm'/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：日常事务 - 教育储蓄证明管理- 教育储蓄证明查看
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="tableName" name="tableName" value="czxx_jycyzmb" />
				<input type="hidden" id="url" name="url" value="/jycxzmgl.do?method=addJycczm" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>填写申请表</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>修改申请表</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
						</td>
						<td align="right">
							申请时间：						
						</td>
						<td align="left">
							<html:text name="rs" property="zqsj" readonly="true"></html:text>						
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
<%--							<html:text name="rs" property="nd" readonly="true" styleId="nd"></html:text>--%>
							<html:select name="rs" property="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xn" readonly="true" styleId="xn"></html:text>--%>
							<html:select name="rs" property="xn" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xq" readonly="true" styleId="xq"></html:text>--%>
							<html:select name="rs" property="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							储蓄银行：
						</td>
						<td align="left">
							<html:select name="rs" property="yzyh">
								<html:option value=""></html:option>
								<html:options collection="yhList" property="yhdmid"
									labelProperty="yhdmmc"/>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
						<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							储蓄支行：
						</td>
						<td>
							<html:text name="rs" property="yzzh" readonly="true" maxlength="40"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							身份证：
						</td>
						<td>
							<html:text name="rs" property="sfzh" readonly="true" maxlength="30"></html:text>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" id="buttonSave" class="button2" onclick="Close();return false;">
<%--						onclick="if(checkFiledSuccess()){chkisDataExist('xh-xmdm-lxdh-kcjqgzxsj-sqly');}">--%>
						关闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
