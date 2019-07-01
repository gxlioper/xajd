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
		<base target="_self"/>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	
	</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
			
			function yz(){
				var userType = document.getElementById('userType').value;
				var shyj='';
				if(userType=='xy'){
					shyj = document.getElementById('xyshyj').value;
				}else{
					shyj = document.getElementById('xxshyj').value;
				}
				if(shyj.length > 500){
					alert('审核意见不能超过500字！');
					return  false;
				}
				document.forms[0].action = '/xgxt/xcxyXszz.do?method=excutePlsh&doType=sh';
				document.forms[0].submit();
			}
		</script>
		<html:form action="xcxyXszz.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="temp" name="temp"
				value="<bean:write name="pks" scope="request"/>" />
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<fieldset>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="right">
							项目名称：
						</td>
						<td align="left">
							<bean:write name="xmmc" />
						</td>
					</tr>
					<tr>
						<td align="right">
							审核状态：
						</td>
						<td align="left">
							<logic:equal value="xy" name="userType">
								<html:select property="xysh" style="width:100px" styleId="xysh">
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<html:select property="xxsh" style="width:100px" styleId="xxsh">
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td align="right">
							项目金额：
						</td>
						<td align="left">
							<html:select property="zzje" style="width:120px" styleId="zzje">
								<html:options collection="zzjeList" property="zzje"
									labelProperty="zzje" />
							</html:select>
							&nbsp;元
						</td>
					</tr>
					<tr>
						<td align="right">
							审核意见：
						</td>
						<td align="left">
							<logic:equal value="xy" name="userType">
								<textarea rows="4" cols="40" name="xyshyj"></textarea>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<textarea rows="4" cols="40" name="xxshyj"></textarea>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<button type="button" class="button2" onClick="yz();">
								保&nbsp;&nbsp;存
							</button>
							<button type="button" class="button2"
								onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
								关&nbsp;&nbsp;闭
							</button>
						</td>
					</tr>
				</table>
			</fieldset>
		</html:form>
		<logic:equal value="yes" name="result">
			<script language="javascript">
				alert('审核成功！');
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script language="javascript">
				alert('审核失败！');
			</script>
		</logic:equal>
	</body>
</html>
