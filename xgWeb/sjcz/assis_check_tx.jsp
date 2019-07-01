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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
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
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}else if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("学校审核意见不能大于60个字符");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/assisChkOne.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName"
				value="<bean:write name="tName" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">	
						</td>
					</tr>
				</thead>
				<tr>
							     <td colspan="2"><div align="center">学号</div></td>
						         <td colspan="2"><bean:write name="xh" /></td>
							     <td width="16%"><div align="center">姓名</div></td>
						         <td width="34%"><bean:write name="xm" /></td>
							 </tr>
						     <tr>
							     <td colspan="2"><div align="center">身份证号</div></td>
						         <td colspan="2"><bean:write name="sfzh" /></td>
							     <td><div align="center">性别</div></td>
						         <td><bean:write name="xb" /></td>
							 </tr>
						     <tr>
							     <td colspan="2"><div align="center">大学</div></td>
						         <td colspan="2"><bean:write name="xxmc" /></td>
							     <td><div align="center">贷款银行</div></td>
						         <td><bean:write name="dkyh" /></td>
							 </tr>
						     <tr>
							     <td colspan="2"><div align="center">应付利息</div></td>
						         <td colspan="2"><bean:write name="yflx" /></td>
							     <td><div align="center">贷款总金额</div></td>
						         <td><bean:write name="dkzje" /></td>
							 </tr>
						     <tr>
							     <td colspan="2"><div align="center">贷款期限</div></td>
							     <td colspan="2"><bean:write name="dkqx" /></td>
							     <td><div align="center">审核结果</div></td>
							     <td>
							     <html:select property="yesNo">
							          <html:options collection="chkList" property="en"
								         labelProperty="cn" />
						              </html:select>
							     </td>
						     </tr>
						     <tr>
						       <td colspan="2"><div align="center">帮困贴息</div></td>
							   <td colspan="4"><bean:write name="bktx" /></td>
						     </tr>
						     <tr>
						       <td colspan="2"><div align="center">奖优贴息</div></td>
							   <td colspan="4"><bean:write name="jytx" /></td>
						     </tr>
						     <tr>
						       <td colspan="2"><div align="center">政策贴息</div></td>
							   <td colspan="4"><bean:write name="zctx" /></td>
						     </tr>
						     <tr>
							     <td colspan="2"><div align="left"><bean:message key="lable.xsgzyxpzxy" />审核意见</div></td>
							     <td colspan="4"><textarea id="xyshyj" name="xyshyj" style="width:100%;" value="" rows="5"><bean:write name='xyshyj'/></textarea></td>
						     </tr>	 
						     <tr>
							     <td colspan="2"><div align="left">学校审核意见</div></td>
							     <td colspan="4"><textarea id="xxshyj" name="xxshyj" style="width:100%;" value="" rows="5"><bean:write name='xxshyj'/></textarea></td>
						     </tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="yz();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
