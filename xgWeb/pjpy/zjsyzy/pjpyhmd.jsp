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
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />	

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/pjpy_zjsyzy.do" method="post">
			<input type="hidden" name="url" id="url" value="/pjpy_zjsyzy.do?method=addPjpyhmd"/>
			<input type="hidden" name="getStuInfo" id="getStuInfo" value="xh-xm"/>
			<input type="hidden" name="doType" id="doType" value="${type}"/>
			<input type="hidden" name="xn" id="xn" value="${rs.xn}"/>
			<input type="hidden" name="nd" id="nd" value="${rs.nd}"/>
			<input type="hidden" name="xmmcO" id="xmmcO" value="${rs.xmmc}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 信息维护 - 评优黑名单维护
				</div>
			</div>
			<fieldset>
				<legend>
					评优黑名单维护
				</legend>

				<table width="100%" class="tbstyle">
					<tr style="height:23px">
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<logic:present name="showstu">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="s" />
							</logic:present>
							<logic:notPresent name="showstu">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:notPresent name="type">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notPresent>
							</logic:notPresent>
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr style="height:23px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>					
					</tr>
					<tr style="height:23px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:23px">						
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							
						</td>
						<td align="left">
							
						</td>
						
					</tr>									
					<tr style="height:23px">
						<td align="right">
							<font color="red">*</font>项目名称：
						</td>
						<td align="left">
							<html:select property="xmmc" name="rs" styleId="xmmc">
							<html:options collection="xmList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<td align="right">
							原因：
						</td>
						<td align="left">
							<html:textarea property="yy" name="rs" styleId="yy" style="width:100%">							
							</html:textarea>
						</td>
					</tr>
					<tr style="height:23px">
						<td align="right">
							<font color="red">*</font>开始时间：
						</td>
						<td align="left">
							<html:text property="kssj" name="rs" styleId="kssj" onclick="return showCalendar('kssj','y-mm-dd');" readonly="true"/>
						</td>
						<td align="right" rowspan="2">
							备注：
						</td>
						<td align="left" rowspan="2" width="40%">
							<html:textarea property="bz" name="rs" styleId="bz" style="width:100%;height:100%">							
							</html:textarea>
						</td>
					</tr>
					<tr style="height:23px">
						<td align="right">
							<font color="red">*</font>结束时间：
						</td>
						<td align="left">
							<html:text property="jssj" name="rs" styleId="jssj" onclick="return showCalendar('jssj','y-mm-dd');" readonly="true"/>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">
				<button type="button" class="button2" onclick="commit('pjpy_zjsyzy.do?method=savePjpyhmd','xh-xmmc-kssj-jssj')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('操作成功!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert('操作失败！');
					Close();
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
