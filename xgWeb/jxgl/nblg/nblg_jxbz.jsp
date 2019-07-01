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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script language="javascript">
		function initYjList(){
			var nj = document.getElementById('nj').value;
			var query = "";
			GetListData.getNblgJxYjbzList(nj,function initTjList(data){
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
		}
		
		function initLjList(){
			var nj = document.getElementById('nj').value;
			var yjdm = "";
			var query = "";
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
		}
		
		function initPjList(){
			var nj = document.getElementById('nj').value;
			var yjdm = "";
			var ljdm = "";
			var query = "";
			if($("yjdm")){yjdm = $("yjdm").value};
			if($("ljdm")){ljdm = $("ljdm").value};	
			GetListData.getNblgJxPjbzList(yjdm,ljdm,nj,function initTjList(data){
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
		}
		
function initBjListT(){
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var yjdm = "";
	var ljdm = "";
	var pjdm = "";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("yjdm")){yjdm = $("yjdm").value};
	if($("ljdm")){ljdm = $("ljdm").value};	
	if($("pjdm")){pjdm = $("pjdm").value};	
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;
		GetListData.getNblgJxBjList(query,yjdm,ljdm,pjdm,userName,function initTjList(data){
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
}
</script>
		<html:form action="/ArmyStuInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训考核 - 学生军训表彰
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" name="xyV" id="xyV" />
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="yjdmV" id="yjdmV" />
					<input type="hidden" name="ljdmV" id="ljdmV" />
					<input type="hidden" name="pjdmV" id="pjdmV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td>
								&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
								</html:select>
							</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/nblg_showArmy.do?')">
										查询
									</button>
								</td>
							</tr>
							<tr>
							<td align="left" nowrap>
								<input type="hidden" name="zyV" value=""/>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:120px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xhV" maxlength="10"/>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" />
							</td>
						</tr>
							<tr>
								<td align="left" nowrap>
									&nbsp;&nbsp;营级：
									<html:select property="yjdm" onchange="initLjList();"
										 style="width:150px" styleId="yjdm">
										<html:option value=""></html:option>
										<html:options collection="yjList" property="yjdm"
											labelProperty="yjmc" />
									</html:select>
									连级：
									<html:select property="ljdm"
										style="width:180px" styleId="ljdm">
										<html:option value=""></html:option>
										<html:options collection="ljList" property="ljdm"
											labelProperty="ljmc" />
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="viewMore('modi')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>				
					<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="viewMore('add')" style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="viewMore('modi')" style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="viewMore('del')" style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="dataExport('/xgxt/expData.do')"
							style="width:80px">
							导出数据
						</button>											
					</div>
				<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
			   </script>
			    <logic:equal value="yes" name="done">
					<script language="javascript">
      						//alert("操作成功！");
	  				　　　　 document.getElementById("search_go").click();
	  				</script>
				</logic:equal>
				<logic:equal  value="no" name="done">
					<script language="javascript">
	  						//alert("操作失败! ");
	  						document.getElementById("search_go").click();
	  					</script>
				</logic:equal>
			</html:form>
	</body>
</html>
