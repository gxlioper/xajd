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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 信息维护 - 公寓表现分维护
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					无记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/pjpy/zjjd/gybxf/gybxfOne.jsp" />
				<fieldset>
					<legend>
						公寓表现分维护
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right" width="15%" >
								<font color="red">*</font>学号：
							</td>
							<td align="left">
							<logic:equal value="edit" name="doType">
								<html:text name="rs" property="xh" styleId="xh" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="edit" name="doType">
								<html:text name="rs" property="xh" styleId="xh" readonly="true"></html:text>
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?jq=gy',750,550);"
									class="btn_01" id="buttonFindStu" >选择
								</button>
							</logic:notEqual>
							</td>
							<td align="right" width="15%">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>							
							</td>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>添加时间：
							</td>
							<td align="left">
								<logic:notEqual name="doType" value="add">
								<html:text  name="rs" property="rq" styleId="rq" readonly="true"/>
								</logic:notEqual>
								<logic:equal name="doType" value="add">
								<html:text  name="rs" property="rq" styleId="rq" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rq','y-mm-dd');"/>
								</logic:equal>
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>学年：
							</td>
							<td align="left">
								<input type="hidden" id="xnV" value="<bean:write name="rs" property="xn"/>" />
								<logic:notEqual name="doType" value="add">
								<html:select name="rs" property="xn" styleId="xn" disabled="true">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								</logic:notEqual>
								<logic:equal name="doType" value="add">
								<html:select name="rs" property="xn" styleId="xn">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								</logic:equal>
							</td>
							<td align="right">
								<font color="red">*</font>学期：
							</td>
							<td align="left">
								<input type="hidden" id="xqV" value="<bean:write name="rs" property="xq"/>" />
								<logic:notEqual name="doType" value="add">
								<html:select name="rs" property="xq" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
								</logic:notEqual>
								<logic:equal name="doType" value="add">
								<html:select name="rs" property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td align="right">
								加分：
							</td>
							<td align="left">
								<html:text name='rs' property="jf" styleId="jf" style="width:30%"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="5"/>
							</td>
							<td align="right">
								扣分：
							</td>
							<td align="left">
								<html:text name='rs' property="kf" styleId="kf" style="width:30%"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="5"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								事项：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property="sx" styleId="sx" style="width:100%" rows="5"
								onblur="chLeng(this,'300')"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2"
						onclick="Savedata('xh-xn-xq-rq','pjpyzjjdwh.do?method=gybxfOne&type=save&xnV='+$('xnV').value+'&xqV='+$('xqV').value);"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
</html>
