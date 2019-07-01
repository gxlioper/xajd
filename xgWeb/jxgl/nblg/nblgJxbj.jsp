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
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/jxglFunction.js"></script>
	<body>
		<script language="javascript">
function saveNblgBj(){
	setEleDisable("button");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdmList";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("jxgljz_nblg.do?method=nblgJxbj&act=save");
}

function addLdBj(){
	var fromIndx = $("bjFlist").selectedIndex;
	if(fromIndx < 0){
		return false;
	}
	$("bjTlist").options[$("bjTlist").options.length] = new Option($("bjFlist").options[fromIndx].text,$("bjFlist").options[fromIndx].value);
	$("bjFlist").options[fromIndx] = null;
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
		$("delLdBjB").disabled = false;
	}else{		
		$("addLdBjB").disabled = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
}	
function delLdBj(){
	var toIndx = $("bjTlist").selectedIndex;
	if(toIndx < 0){
		return false;
	}
	$("bjFlist").options[$("bjFlist").options.length] = new Option($("bjTlist").options[toIndx].text,$("bjTlist").options[toIndx].value);
	$("bjTlist").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
		$("addLdBjB").disabled = false;
	}else{		
		$("delLdBjB").disabled = true;
	}	
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
}
		</script>
		<html:form action="/jxgljz_nblg" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训编制 - 建制维护 - 班级分配
				</div>
			</div>
			<fieldset>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>">
				<input type="hidden" id="nj" name="nj"
					value="<bean:write name="nj"/>">
				<input type="hidden" id="sjdm" name="sjdm"
					value="<bean:write name="sjdm"/>">
				<input type="hidden" id="ssjz" name="ssjz"
					value="<bean:write name="ssjz"/>">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4">
								年级:
								<bean:write name="nj" />
								&nbsp;&nbsp;&nbsp;&nbsp;所属建制:
								<bean:write name="ssjz" />
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<bean:message key="lable.xsgzyxpzxy" />:
								<html:select property="xydm" styleId="xy"
									onchange="refreshForm('jxgljz_nblg.do?method=nblgJxbj')">
									<option value=""></option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm"
									onchange="refreshForm('jxgljz_nblg.do?method=nblgJxbj')">
									<option value=""></option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</td>
						</tr>
						<tr>
							<td colspan="3">						
								指导员
								<html:select property="zdy"  styleId="zdy" name="rs">
								<html:option value=""></html:option>
								<html:options collection="lsList" property="jsdm"
									labelProperty="xm" />
								</html:select>
								教官:
								<html:select property="jgmc"  styleId="jgmc">
								<html:option value=""></html:option>
								<html:options collection="jgList" property="jgbh"
									labelProperty="xm" />
								</html:select>
							</td>
							<td><font color="red">更改专业之前请确认保存班级编制</font>	</td>
						</tr>
					</thead>
					<tr>
						<td align="center" width="6%" valign="middle">
							<p>
								班
							</p>
							<p>
								级
							</p>
						</td>
						<td width="40%">
							<html:select name="LdInfo" property="bjmc" styleId="bjFlist"
								ondblclick="addLdBj()" size="12" style="width:100% ">
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						<td nowrap width="14%">
							<button type="button" class="button2" style="width:100%" id="addLdBjB"
								onclick="addLdBj()">
								&gt;&nbsp;&gt;
							</button>
							<br />
							<br />
							<br />
							<button type="button" class="button2" style="width:100%" id="delLdBjB"
								onclick="delLdBj()">
								&lt;&nbsp;&lt;
							</button>
						</td>
						<td width="40%">
							负责班级：
							<br />
							<html:select name="LdInfo" property="bjlist" size="11"
								ondblclick="delLdBj()" styleId="bjTlist" style="width:100% ">
								<html:options collection="ldBjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>

			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="saveNblgBj();" style="width:80px">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px">
					关 闭
				</button>
			</div>
			<logic:present name="ok" scope="request">
				<logic:equal value="ok" name="ok" scope="request">
					<script>alert("保存成功！");Close();window.dialogArguments.document.getElementById('search_go').click();</script>
				</logic:equal>
				<logic:equal value="no" name="ok" scope="request">
					<script>alert("保存失败，请重试！");</script>
				</logic:equal>
			</logic:present>
			<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
