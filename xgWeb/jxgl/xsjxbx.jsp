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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript" src="js/jxgl.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<base target="_self">
	<body>
		<html:form action="/Army">
			<input type="hidden" name="url" id="url"
				value="/jxgl/xsjxbx.jsp">
			<input type="hidden" name="variable" id="variable" value="variable">
			<input type="hidden" name="oper" id="oper" value="<bean:write name="doType"/>">	
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />	
			<input type="hidden" name="notnull" id="notnull" value="xh-jxrq-bxqk">
			<logic:equal value="modi" name="doType">
			    <input type="hidden" name="pk" id="pk" value="<bean:write name="pk" scope="request"/>"/>
			    <input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue" scope="request"/>"/>
			</logic:equal>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:军训管理-学生军训表现-信息维护
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<div class="buttontool" id="btn" width="100%">
					<b>军训学生表现</b>
				</div>
				<tr>
					<td height="22" align="right">
						<div align="right">
							<font color="red">*</font>学号：
						</div>
					</td>
					<td height="22" width="33%">
						<logic:notEqual value="add" name="doType">
							<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
						</logic:notEqual>
						<logic:equal value="add" name="doType">
							<html:text name='rs' property="xh" styleId="xh"								
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
					</td>
					<td>
						<div align="right">
							姓名：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td>
						<div align="right">
							性别：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="right">
							年级：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="xymc" />
					</td>
					<td height="22">
						<div align="right">
							专业名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td height="22">
						<div align="right">
							班级名称：
						</div>
					</td>
					<td height="22">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td height="22">
						<div align="right">
							<font color="red">*</font>军训日期：
						</div>
					</td>
					<td height="22">
						<html:text name="rs" property="jxrq" styleId="jxrq" 
						onclick="return showCalendar('jxrq','y-mm-dd');"/>
					</td>
				</tr>
				<tr style="height:30px">
					<td height="22">
						<div align="right">
							<font color="red">*</font>表现情况：
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="bxqk" onblur="chLeng(this,320);" style="height:100px;width:300px"/>				
					</td>
				</tr>
			</table>
			<logic:equal value="yes" name="writeAble"></logic:equal>
			<center>
				<div class="buttontool" id="btn" width="100%">
					<button type="button" class="button2" onclick="SaveArmy('/xgxt/modiArmy.do?doType=save&tableName=view_xsbx&realTable=xsjxbxb&act=xsjxrcbx&pk=jxrq-xh')" style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.close();return false;"
						style="width:80px">
						关 闭
					</button>
				</div>
			</center>
		</html:form>
	</body>
</html>
