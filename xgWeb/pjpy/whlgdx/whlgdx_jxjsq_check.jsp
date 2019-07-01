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
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 奖学金审核 - 单个奖学金审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="pk"/>" />
				<input type="hidden" name="tg" id="tg" value="${tgres }"/>
				<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个奖学金审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left" id="selxh">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh"/>"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
						<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd"/>"/>
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
						<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn"/>"/>
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
						奖学金：
					</td>
					<td align="left">
						<bean:write name="rs" property="jxjmc"/>
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
						思想道德素质分数：
					</td>
					<td align="left">
						<bean:write name="rs" property="dcj"/>
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
						学习平均成绩：
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcj"/>
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
						学习平均成绩排名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcjpm"/>
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
						学习平均成绩排名比例：
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcjpmbl"/>
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						身体素质分数：
					</td>
					<td align="left">
						<bean:write name="rs" property="stszzf"/>
					</td>
					<td align="right">
						拓展素质分数：
					</td>
					<td align="left">
						<bean:write name="rs" property="sztzzf"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						综测总分：
					</td>
					<td align="left">
						<bean:write name="rs" property="stszzf"/>
					</td>
					<td align="right">
						综测总分排名：
					</td>
					<td align="left">
						<bean:write name="rs" property="sztzzf"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						综测总分排名比例：
					</td>
					<td align="left">
						<bean:write name="rs" property="zhszcpcjpmbl"/>
					</td>
					<td align="right">
						单科最低分数：
					</td>
					<td align="left">
						<bean:write name="rs" property="dkzdfs"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						外语过级情况：
					</td>
					<td align="left">
						<bean:write name="rs" property="wygjqk"/>
					</td>
					<td align="right">
						是否达标：
					</td>
					<td align="left">
						<bean:write name="rs" property="tjflag"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select property="yesNo" styleId="yesNo" name="rs">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<logic:equal value="xy" scope="request" name="userType">
				<tr>
					<td align="right">
						辅导员意见：
					</td>
					<td align="left" colspan="3">
						<textarea name="fdyyj" style="width:100%" rows="3"><bean:write name="rs" property="fdyyj" /></textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</td>
					<td align="left" colspan="3">
						<textarea name="xyyj" style="width:100%" rows="3"><bean:write name="rs" property="xyshyj"/></textarea>
					</td>
				</tr>
				</logic:equal>
				
				<logic:equal value="xx" scope="request" name="userType">
				<tr>
					<td align="right">
						辅导员意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>				
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						学校审核意见：
					</td>
					<td align="left" colspan="3">
						<textarea name="xxyj" style="width:100%" rows="3"><bean:write name="rs" property="xxshyj"/></textarea>
					</td>
				</tr>				
				</logic:equal>
			</table>				
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('pjpy_whlgdx.do?method=checkPriseSave');"
					style="width:90px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					关 闭
				</button>
				<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('/xgxt/dxjxjsp.do?method=dxjxjsp&pk=<bean:write name="xn||nd||xh||jxjdm"/>')" style="width:90px"
					id="buttonQuery">
					打印报表
				</button>
				</logic:present>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert(""+document.getElementById('tg').value);
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
