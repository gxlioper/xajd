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
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
	function yz(){
			var bzdm = document.getElementById('bzdm').value;
			var bzmc = document.getElementById('bzmc').value;
			var sjdm = document.getElementById('sjdm').value;
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

			document.forms[0].action = "/xgxt/jxgljz_xbmz.do?method=modXbmzJxjz&act=save";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function yjC(){
			var yjdm = document.getElementById('yjdm').value;
			document.getElementById('sjdm').value = yjdm;
		}
		function ljC(){
			var ljdm = document.getElementById('ljdm').value;
			document.getElementById('sjdm').value = ljdm;
		}
		
		function initLjList(){
			var nj = document.getElementById('nj').value;
			var yjdm = "";
			var query = "";
			var userName = "";
			if($("yjdm")){yjdm = $("yjdm").value};	
			GetListData.getNblgJxLjbzList(yjdm,nj,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId + "V"){
							if($(objId +"V").value != "" && $(objId + "V").value != null){
								for(var i = 0;i < $(objId).options.length; i++){
									if($(objId).options[i].value == $(objId +"V").value){
										$(objId).options[i].selected = true;
										return true;
									}
								}
							}
						}
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			});
			
			document.getElementById('sjdm').value = "";
		}
</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="jxgljz_xbmz.do?method=addQtjz" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：
						<bean:write name="title" />
					</div>
				</div>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" name="ljdmV" id="ljdmV" />
				<input type="hidden" name="pjdmV" id="pjdmV" />
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
								建制代码
							</div>
						</td>
						<td width="65%">
							<input type="text" id="bzdm" name="bzdm" readonly="readonly"
								style="width:100%;heigh:100%"
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
							<input type="hidden" id="sjdm" name="sjdm"
									value="<bean:write name="rs" property="sjdm"/>" />
						</td>
					</tr>
					<logic:equal name="rs" property="bzdj" value="2">
						<tr>
							<td width="35%">
								<div align="center">
									<font color="red">*</font>所属营级
								</div>
							</td>
							<td width="65%">
								<html:select property="yjdm" style="width:100%" onchange="yjC();"
									styleId="yjdm">
									<html:options collection="getNblgYjList" property="yjdm"
										labelProperty="yjmc" />
								</html:select>
								<input type="hidden" id="sjdm" name="sjdm"
									value="<bean:write name="rs" property="sjdm"/>" />
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="rs" property="bzdj" value="3">
						<tr>
							<td width="35%">
								<div align="center">
									所属营级
								</div>
							</td>
							<td width="65%">
								<html:select property="yjdm" style="width:100%" onchange="initLjList();"
									styleId="yjdm">
									<html:options collection="getNblgYjList" property="yjdm"
										labelProperty="yjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="35%">
								<div align="center">
									<font color="red">*</font>所属连级
								</div>
							</td>
							<td width="65%">
								<html:select property="ljdm" style="width:100%" onchange="ljC();"
									styleId="ljdm">
									<html:options collection="getNblgLjList" property="ljdm"
										labelProperty="ljmc" />
								</html:select>
								<input type="hidden" id="sjdm" name="sjdm"
									value="<bean:write name="rs" property="sjdm"/>" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td width="35%">
							<div align="center">
								指导员
							</div>
						</td>
						<td width="65%">
							<input type="text" id="jgmc" name="zdy"
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
					<button type="button" class="button2" onClick="yz();Close();
						window.dialogArguments.document.getElementById('search_go').click();"">
						提&nbsp;&nbsp;交
					</button>
					<button type="button" class="button2"
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关&nbsp;&nbsp;闭
					</button>
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
