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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
function plsh(){
	var userType = document.getElementById('userType').value;
	var shType = document.getElementById('shType').value;
	var shjg = document.getElementById('shjg').value;
	
	if (shType == null || shType == ""){
		alert("请选择审核类型!");
		return false;
	}
	if (shType == null || shType == ""){
		alert("请选择审核结果!");
		return false;
	}
	
	
	if (userType != "xx" && userType != "admin"){
		if (!confirm("下级用户不能修改已通过上级审核的数据，确定要修改所选记录？")){
			return false;
		}
	}
	refreshForm('/xgxt/zgmsxy_xszz.do?method=knsrdplsh&doType=save');
}

		</script>
		<html:form action="/zgmsxy_xszz.do?method=knsrdplsh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:notEqual name="isQuery" value="is">
					当前所在位置：困难生 - 审核 - 困难生批量审核
					</logic:notEqual>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="cbVal" name="cbVal"
				value="<bean:write name="rs" property="cbVal"/>" />
			<logic:present name="over">
				<logic:match value="over" name="over">
					<script language="javascript">
	         			alert("批量审核完成！");
	         		</script>
				</logic:match>
			</logic:present>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="2" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="50%">
						审核类型
					</td>
					<td align="left" width="50%">
						<logic:equal name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">评议结果</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />审核</html:option>
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="3">学校审核</html:option>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						审核结果
					</td>
					<td align="left" width="50%">
						<html:select name="rs" property="shjg">
							<html:option value="困难">困难</html:option>
							<html:option value="特殊困难">特殊困难</html:option>
							<html:option value="不困难">不困难</html:option>
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="plsh();" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
