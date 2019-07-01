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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var operFlag = document.getElementById('zt').value;
			if(operFlag == true || operFlag=="true"){
				alert("审核已提交，不能再修改审核结果!");
				return false;
			}
			refreshForm('/xgxt/n05_xszz.do?method=gjzxj3shdmOne&type=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家助学金审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<input type="hidden" id="shmodel" name="shmodel" value="${shmodel}" />
			<input type="hidden" id="zt" name="zt" value="${operFlag}" />
			<fieldset>
				<legend>
					学生信息
				</legend>
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<logic:iterate id="tit" name="topTr">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)" nowrap>
								<bean:write name="tit" property="cn"/>
							</td>
						</logic:iterate>
					</tr>
				</thead>
				<logic:iterate name="rs" id="s">
					<tr>
						<logic:iterate id="v" name="s">
							<td align=center>
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</table>
			</fieldset>

			<fieldset>
				<legend>
					审核
				</legend>
			<table width="98%" align="center" class="tbstyle">
			<tr>
				<td align="right" width="20%">
					审核结果：
				</td>
				<td>
					<html:select name="map" property="shjg">
						<html:options collection="chkList" property="en"
							labelProperty="cn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					审核意见：
				</td>
				<td>
					<html:textarea name="map" property="shyj" rows='3' style="width:100%" onblur="chLeng(this,300)"/>
				</td>
			</tr>
			</table>
			</fieldset>
			<div class="buttontool" align="center">
				<logic:notEqual value="view" name="doType">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
