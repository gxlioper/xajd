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


	<title><bean:message key="lable.title" /></title>
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
		function yz(titName){
			var zzkssj = document.getElementById('zzkssj').value;
			var zzjssj = document.getElementById('zzjssj').value;
			if(zzkssj == null || zzkssj == "" ||
			   zzjssj == null || zzjssj == ''  ){
				alert("所有录入均不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=zzxmSjEdit";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 资助项目时间
		</div>
	</div>
		<html:form action="xcxyXszz.do" method="post">

			
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="savepk" name="savepk"
				value="<bean:write name="savepk" scope="request"/>" />
			<table class="tbstyle" width="100%">
				<logic:equal value="oneSave" name="act">
				<tr>
					<td width="30%">
						<div align="right">
							资助项目名称:
						</div>
					</td>
					<td width="70%">
						<input type="hidden" name="temp" value="<bean:write name="rs" property="xmdm"/>">
						<html:select name="rs" property="xmdm" style="width:180px" disabled="true" styleId="xmdm">
							<html:options collection="zzxmList" property="xmdm" labelProperty="xmmc" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
			<tr>
				<td>
					<div align="right">
						 申请开始时间:
					</div>
				</td>
				<td align="left">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('zzkssj','y-mm-dd');"
								value="<bean:write name='rs' property="zzkssj" />"
								name="zzkssj" id="zzkssj" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						申请结束时间:
					</div>
				</td>
				<td align="left">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('zzjssj','y-mm-dd');"
								value="<bean:write name='rs' property="zzjssj" />"
								name="zzjssj" id="zzjssj" />
				</td>
			</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2"
					onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button type="button" class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
			<logic:present name="save">
				<logic:match value="yes" name="save">
					<script language="javascript">
	         			alert("保存成功！");
	         			Close();
	         			window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="no" name="save">
					<script language="javascript">
	         			alert("保存失败！");
	         		</script>
				</logic:match>
			</logic:present>
		</html:form>
</body>
</html:html>
