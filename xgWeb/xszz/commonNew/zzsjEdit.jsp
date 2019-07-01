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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var xydm = document.getElementById('xydm').value;
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((xmdm == null) || (xmdm == "")){
				alert("请选择奖学金项目!");
				return false;
			}
			if((xydm == null) || (xydm == "")){
				alert("请选择<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			}
			if((kssj == null) || (kssj == "")){
				alert("申请开始时间不能为空!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("申请结束时间不能为空!");
				return false;
			}
			if (kssj > jssj){
				alert("申请开始时间不能大于申请结束时间！");
				return false;
			}
			document.forms[0].action = "/xgxt/new_common_xszz.do?method=zzsjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 资助申请时间维护
		</div>
	</div>
	<html:form action="shgc_kns.do" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%">
			<tr>
				<td width="50%">
					<div align="center">
						资助项目
					</div>
				</td>
				<td width="50%">
						<input type="hidden" id="xmdm" name="xmdm"
							value="<bean:write name="rs" property="xmdm"/>" />
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
						<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						是否必须是困难生
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="sfkns">
						<logic:match value="是" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="是" checked>&nbsp;&nbsp;是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sfkns" value="否">&nbsp;&nbsp;否
						</logic:match>
						<logic:match value="否" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="是">&nbsp;&nbsp;是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sfkns" value="否" checked>&nbsp;&nbsp;否
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfkns">
						<input type="radio" name="sfkns" value="是">&nbsp;&nbsp;是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="sfkns" value="否" checked>&nbsp;&nbsp;否
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						 申请开始时间
					</div>
				</td>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						申请结束时间
					</div>
				</td>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
			</tr>
		</table>
		<logic:equal name="writeAble" value="yes">
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<button class="button2" onClick="yz();">
				保&nbsp;&nbsp;&nbsp;&nbsp;存
			</button>
			<button class="button2"
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				关&nbsp;&nbsp;&nbsp;&nbsp;闭
			</button>
		</div>
		</logic:equal>
	</html:form>
</body>
</html:html>
