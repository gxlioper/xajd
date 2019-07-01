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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/pjpyahjgwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 荣誉称号审核 - 单个荣誉称号审核
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue"/>" />
			<input type="hidden" name="failInfo" id="failInfo" value="${failInfo }"/>
			<input type="hidden" name="oldyesNo" id="oldyesNo" value="<bean:write name="ag" property="yesNo"/>"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个荣誉称号审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left"  id="selxh">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" id="oldxh" name="oldxh" value="<bean:write name="rs" property="xh"/>"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						荣誉称号：
					</td>
					<td align="left">
						<bean:write name="rs" property="rychmc"/>
						<input type="hidden" id="rychdm" name="rychdm" value="<bean:write name="rs" property="rychdm"/>"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						德成绩：
					</td>
					<td align="left">
						<bean:write name='rs' property="dcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						智成绩：
					</td>
					<td align="left">
						<bean:write name="rs" property="zcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						体成绩：
					</td>
					<td align="left">
						<bean:write name="rs" property="tcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
					课程平均分:
					</td>
					<td align="left">
						<bean:write name="rs" property="pjf"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						所在班级:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjrychmc"/>
					</td>
					<td align="right">
						班级名次:
					</td>
					<td align="left">
					<bean:write name="rs" property="mc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						所在宿舍:
					</td>
					<td align="left">
						<bean:write name="rs" property="wmss"/>
					</td>
					<td align="right">
						计算机二级:
					</td>
					<td align="left">
						<bean:write name="rs" property="jsjej"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						担任职务:
					</td>
					<td align="left">
						<bean:write name="rs" property="drzw"/>
					</td>
					<td align="right">
						英语四级:
					</td>
					<td align="left">
						<bean:write name="rs" property="cet4"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						十佳大学生得分:
					</td>
					<td align="left">
						<bean:write name="rs" property="df"/>
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select name="ag" property="yesNo" styleId="yesNo" style="width:120px">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						十佳大学生得分全校排名：
					</td>
					<td align="left">
						<bean:write name="rs" property="pm"/>
					</td>
					<td align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="if (document.getElementById('oldyesNo').value==document.getElementById('yesNo').value) {alert('您未作任何修改！');return;} else refreshForm('xjgrshbyone.do');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" style="width:90px"
					id="buttonQuery">
					查看学生信息
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！'+document.getElementById('failInfo').value);
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
