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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript">
function chkStuNum(){
	var valTmp = document.getElementsByName("stuNum");
	var val = 0;
	for(i = 0;i<valTmp.length;i++){
		if(valTmp[i].value != "NaN"){
			val -= -valTmp[i].value;
		}else{
			alert("数字不合法！");
		}
	}
	document.forms[0].totRS.value=val;
}
</script>
	<body onload="checkWinType();chkStuNum();">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xySetBysStuNum.do?act=save" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td height="25" align="center" colspan="2">
							毕业生人数核对
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="35%">
						学年：
					</td>
					<td align="left">
						<input type="text" name="xn" id="xn"
							value="<bean:write name="jxjsqxn" scope="request" />"
							style="color:#666666" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<input type="text" name="nd" id="nd"
							value="<bean:write name="jxjsqnd" scope="request" />"
							style="color:#666666" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						部门：
					</td>
					<td align="left">
						<input type="text" name="bmT" id="bmT"
							value="<bean:write name="bmmc" scope="request" />"
							style="color:#666666" readonly />
						<input type="hidden" name="bm" id="bm" value="" />
					</td>
				</tr>
				<logic:iterate id="lst" name="rs">
					<tr>
						<td align="right" nowrap>
							<bean:write name="lst" property="xymc" />
							：
						</td>
						<td align="left">
							<input type="hidden" name="njL"
								value="<bean:write name="lst" property="bmdm"/>" />
							<input type="text" name="stuNum"
								value="<bean:write name="lst" property="bysrs"/>"
								style="width:150px" onchange="chkStuNum()"
								onkeypress="return numInputValue(this,4,event)" />
							人
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td align="right">
						总人数：
					</td>
					<td align="left">
						<input type="text" name="totRS" value="" style="width:150px"
							readonly />
						人
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="if(confirm('确定要保存吗？')){document.forms[0].submit();alert('保存成功！');Close();window.dialogArguments.document.getElementById('search_go').click();return true;}return false;">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
