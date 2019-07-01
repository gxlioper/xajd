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
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<html:form action="/pjpycsmzwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="wjcf_csmz_shjxjsh" bundle="pjpycsmz"/>
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="pkValue"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 20%">
						学号：
					</td>
					<td align="left" style="width: 30%">
						<bean:write name="rs"  property="xh"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs"  property="nd"/>
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
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						奖学金名称：
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
						德成绩：
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
						<bean:write name="rs"  property="zymc"/>
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
					审核：
					</td>
					<td align="left">
					<html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						成绩名次：
					</td>
					<td align="left">
						<bean:write name="rs" property="cjmc"/>
					</td>
						<td align="right">
					综合评分名次：
					</td>
					<td align="left">
					<bean:write name="rs" property="zhpfmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						担任职务：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="drzw"/>
					</td>
					</tr>
				<tr>
					<td align="right">
						审核意见：
					</td>
					<td colspan="3" align="left"><html:textarea rows="4"  style="width:98%" property="shyj"  styleId="shyj" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="savexxsh_shgc('shjxjpriseone.do?pkValue=');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>	
			</div>
		</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="view" name="result">
			<script>
				document.getElementById('shyj').value='';
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
			<logic:equal value="noview" name="result">
			<script>
			document.getElementById('shyj').value='';
				alert("操作失败");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
