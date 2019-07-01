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
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/jxglNblg.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getList.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<body>
		<script language="javascript">
function saveXbmzBj(){
	setEleDisable("button");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdmList";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("jxgljz_xbmz.do?method=xbmzJxbj&act=save");
}

function addLdBj(){
	var fromIndx = $("bj").selectedIndex;
	if(fromIndx < 0){
		return false;
	}
	$("bjTlist").options[$("bjTlist").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
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
	var xb = document.getElementById("xb").value;
	if(toIndx < 0){
		return false;
	}
	
	$("bj").options[$("bj").options.length] = new Option($("bjTlist").options[toIndx].text,$("bjTlist").options[toIndx].value);
	jxglNblg.setXbmzBjList($("bjTlist").options[toIndx].value,xb,function(data){
			if (data != null){

			}
		});
	$("bjTlist").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
		$("addLdBjB").disabled = false;
	}else{		
		$("delLdBjB").disabled = true;
	}	
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
}

function chbj(){
	var nj = document.getElementById("nj").value;
	var xb = document.getElementById("xb").value;
	var xydm = document.getElementById("xy").value;
	var zydm = document.getElementById("zy").value;
	jxglNblg.getXbmzBjList(nj,xydm,zydm,xb,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
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
			}
			if ($("bj").options.length>0){
			for(var i = 0;i < $("bj").options.length; i++){
			if ( $("bjTlist").options.length>0){
			for(var j=0;j< $("bjTlist").options.length; j++){
				if($("bj").options[i].value == $("bjTlist").options[j].value){
					$("bj").options[i]=null;
					break;
				}
			}
			}
		}
		}
		});
		var sjdm = document.getElementById("sjdm").value;
		jxglNblg.getNblgBjTList(sjdm,nj,function(data){
			if (data != null){
				var bj = document.getElementById("bj");
				for(var i=0;i<data.length;i++){
					for(var j=0;j<bj.options.length;j++){
						if(bj.options[j].value == data[i].dm){
							bj.options[j]=null;
						}
					}
				}
			}
		});
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
				<input type="hidden" id="xb" name="xb"
					value="<bean:write name="xb"/>">
				<input type="hidden" id="msg" name="msg" value="${msg}"/>
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
						<td align="left" nowrap colspan="4">
								<input type="hidden" name="zyV" value=""/>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();chbj();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
								专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="chbj();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
								<input type="hidden" name="bjTlistV" value=""/>
							</td>
						</tr>
						<tr>
							<td colspan="4">						
								指导员
								<input type="text" id="zdy" name="zdy"
								style="width:30%;heigh:100%" maxlength="50"
								value="<bean:write name="rs" property="zdy"/>">
								教官:
								<input type="text" id="jgmc" name="jgmc"
								style="width:30%;heigh:100%" maxlength="50"
								value="<bean:write name="rs" property="jgmc"/>">
							</td>
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
							<html:select name="LdInfo" property="bjmc" styleId="bj"
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
				<button type="button" class="button2" onclick="saveXbmzBj();" style="width:80px">
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
			<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
				</script>
			</logic:present>
			<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
