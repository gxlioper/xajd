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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src='/xgxt/dwr/interface/jxglNblg.js'></script>
	<script language="javascript">
	function yz(){
			var bzdm = document.getElementById('bzdm').value;
			var bzmc = document.getElementById('bzmc').value;
			var bz = document.getElementById('bz').value;
			
			if((bzdm == null) || (bzdm == "")){
				alert("建制代码不能为空!");
				return false;
			}
			if((bzmc == null) || (bzmc == "")){
				alert("建制名称不能为空!");
				return false;
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("备注不能超过200个字符！");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/jxgljz_xbmz.do?method=addQtjz&act=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="jxgljz_nblg.do?method=addQtjz" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：
						<bean:write name="title" />
					</div>
				</div>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<logic:present name="ok">
					<logic:match value="ok" name="ok">
						<script language="javascript">
	         				alert("保存成功！");
	         				dialogArgumentsQueryChick();
	         				Close();
	         			</script>
					</logic:match>
					<logic:match value="no" name="ok">
						<logic:present name="have">
							<logic:match value="have" name="have">
								<script language="javascript">
	         						alert("建制代码已存在！");
	         					</script>
							</logic:match>
						</logic:present>
						<logic:notPresent name="have">
							<script language="javascript">
	         					alert("保存失败！");
	         				</script>
	         			</logic:notPresent>
					</logic:match>
				</logic:present>
				<table width="100%" id="rsTable" class="tbstyle">
					<tr>
						<td width="35%">
							<div align="center">
								年级
							</div>
						</td>
						<td width="65%">
							<input type="text" readonly="readonly" id="nj" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="nj"/>"
								readonly="readonly">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								<font color="red">*</font>建制代码
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzdm" name="bzdm" readOnly ="true"
								style="width:100%;heigh:100%" maxlength="20"
								value="<bean:write name="rs" property="bzdm"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								<font color="red">*</font>建制名称
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzmc" name="bzmc"
								style="width:100%;heigh:100%" maxlength="100"
								value="<bean:write name="rs" property="bzmc"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								建制等级
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzdjmc" name="bzdjmc"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="bzdjmc"/>">
							<input type="hidden" id="bzdj" name="bzdj"
								value="<bean:write name="rs" property="bzdj"/>" />
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								所属
							</div>
						</td>
						<td width="65%">
							<input type="text" id="ssjz" name="ssjz"
								style="width:100%;heigh:100%" readonly="readonly"
								value="<bean:write name="rs" property="ssjz"/>">
							<input type="hidden" id="sjdm" name="sjdm"
								value="<bean:write name="rs" property="sjdm"/>" />
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								指导员
							</div>
						</td>
						<td width="65%">
							<input type="text" id="zdy" name="zdy"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name="rs" property="zdy"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								教官
							</div>
						</td>
						<td width="65%">
							<input type="text" id="jgmc" name="jgmc"
								style="width:100%;heigh:100%" maxlength="50"
								value="<bean:write name="rs" property="jgmc"/>">
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								连队性别
							</div>
						</td>
						<td width="65%">
							<html:select property="xb" style="width:50px">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="35%">
							<div align="center">
								备注
							</div>
						</td>
						<td width="65%">
							<html:textarea name="rs" property="bz" rows='3'
								style='width:100%' onblur="yzdx(this,'bz')" />
							<input type="hidden" id="bz" name="bz" value="">
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onClick="yz();">
						提&nbsp;&nbsp;交
					</button>
					<button type="button" class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关&nbsp;&nbsp;闭
					</button>
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
