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
			var xmdm = document.getElementById('xmdm').value;
			var xmmc = document.getElementById('xmmc').value;
			var xmlc = document.getElementById('xmlc').value;
			if(xmdm == null || xmdm == "" ||
			   xmmc == null || xmmc == '' ||
			   xmlc == null || xmlc == '' ){
				alert("所有录入均不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=zzxmEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 资助项目
		</div>
	</div>
		<html:form action="xcxyXszz.do" method="post">

			<logic:present name="save">
				<logic:match value="yes" name="save">
					<script language="javascript">
	         			alert("保存成功！");
	         		</script>
				</logic:match>
				<logic:match value="no" name="save">
					<script language="javascript">
	         			alert("保存失败！");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="exist">
				<logic:match value="yes" name="exist">
					<script language="javascript">
	         			alert("项目代码已存在！");
	         		</script>
				</logic:match>
			</logic:present>
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<table class="tbstyle" width="100%">
				<tr>
					<td width="30%">
						<div align="right">
							资助项目代码:
						</div>
					</td>
					<td width="70%">
						<logic:equal name="doType" value="add">
						<input type="text" id="xmdm" name="xmdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmdm"/>" maxlength="8" onkeyup="checkInputData(this)">
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<input type="text" id="xmdm" name="xmdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmdm"/>" readonly="readonly">
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							奖学金项目名称:
						</div>
					</td>
					<td>
						<input type="text" id="xmmc" name="xmmc" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							指定项目流程:
						</div>
					</td>
					<td>
						<html:select name="rs" property="xmlc">
							<html:option value="国家奖学金">国家奖学金</html:option>
							<html:option value="国家助学金">国家助学金</html:option>
							<html:option value="国家励志奖学金">国家励志奖学金</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							指定报表样式:
						</div>
					</td>
					<td>
						<html:select name="rs" property="bbys">
							<html:option value="国家奖学金">国家奖学金</html:option>
							<html:option value="国家助学金">国家助学金</html:option>
							<html:option value="国家励志奖学金">国家励志奖学金</html:option>
							<html:option value="广州助学金">广州助学金</html:option>
							<html:option value="加拿大福慧基金会励志奖学金">加拿大福慧基金会励志奖学金</html:option>
						</html:select>
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
		</html:form>
</body>
</html:html>
