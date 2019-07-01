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
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
		}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
		
	iRow=window.event.srcElement;
	do{
		iRow=iRow.parentElement;
	}while(iRow.tagName!='TR')

	//Ctrl多选
	if(event.ctrlKey){
		var j=-1;
		for(i=0;i<Rows.length;i++){
			if(iRow==Rows[i]){
				j=i;
				break;
			}
		}
		if(j!=-1){
			for(i=j;i<Rows.length-1;i++){
				Rows[i]=Rows[i+1];
			}
			Rows.length=Rows.length-1;
			iRow.style.backgroundColor = "#FFFFFF";
		}else{
			Rows[Rows.length]=iRow;
		}
//		ShiftStartRow=iRow;
	}
	else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
		
//		ShiftStartRow=iRow;
	}
	changeColor(iRow);
}

//选中行变色
function changeColor(E){
	
/*	for(var i=1;i<E.parentElement.rows.length;i++){
		E.parentElement.rows(i).style.backgroundColor=cur_bgc;
	}
*/
	for(i=0;i<Rows.length;i++){
		Rows[i].style.backgroundColor=cur_bgc;	
	}
}

function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量删除的记录！");
		return false;
	}
	
	if (!confirm("辅导员用户不能删除已通过<bean:message key="lable.xsgzyxpzxy" />审核的数据,<bean:message key="lable.xsgzyxpzxy" />用户不能删除已通过学校审核的数据，确定要删除所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function notPass(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为不通过的记录！");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function pass(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为通过的记录！");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function chkAssisOne(url, act) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&actDo=";
		url += act;
		url +="&act=";
		url += document.getElementById("act").value;
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&tName=";
		url += document.getElementById("realTable").value;
		if(url.search('assisChkOne')){
			url += "&xydm="+document.getElementById("xy").value;
		}
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要修改的记录！");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=gjzxdkshExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

/*
数据导入检测
*/	
function impAndChkData1(){
	var realTable = "";
	var tableName = "";
	var sty = "toolbar=no,location=no,directories=no,status=yes";
	sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=500,top=100";
	sty += ",left=200";
	if($("realTable1")) realTable1 = document.getElementById("realTable1").value;
	if($("tableName")) tableName = document.getElementById("tableName").value;
	url = '/xgxt/impAndChkData.do?method=importData';
	url += "&realTable=" + realTable1;
	url += "&tableName=" + tableName;
	//showTopWin(url,600,500);
	//refreshForm(url);
	window.open(url,'',sty);
}
		</script>
		<html:form action="/bjlhdx_xszz.do?method=gjzxdksh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="realTable1" name="realTable1"
				value="xszz_bjlhdx_gjzxgk_xsjbxx" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<logic:equal name="isQuery" value="is">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级：
									<html:select name="rs1" property="bjdm" style="width:180px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10" align="center" valign="middle" rowspan="2">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;年级：
									<html:select name="rs1" property="nj" style="width:80px"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;学年：
									<html:select name="rs1" property="xn" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;学号：
									<input type="text" id="xh" name="xh" maxlength="20"
										style="width:120px;heigh:100%"
										value="<bean:write name="rs1" property="xh"/>">
									&nbsp;&nbsp;申请状态：
									<html:select name="rs1" property="sqzt" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="sqztAllList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;系别：
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级：
									<html:select name="rs1" property="bjdm" style="width:180px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10" align="center" valign="middle" rowspan="2">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;年级：
									<html:select name="rs1" property="nj" style="width:180px"
										onchange="refreshForm('/xgxt/bjlhdx_xszz.do?method=gjzxdksh')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;学号：
									<input type="text" id="xh" name="xh" maxlength="20"
										style="width:180px;heigh:100%"
										value="<bean:write name="rs1" property="xh"/>">
									&nbsp;&nbsp;申请状态：
									<html:select name="rs1" property="sqzt" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="sqztAllList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</logic:notEqual>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
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
						<font color="blue">提示：双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal name="isQuery" value="no">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="chkAssisOne('/xgxt/bjlhdx_xszz.do?method=gjzxdkshXxxx','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:equal name="isQuery" value="is">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="chkAssisOne('/xgxt/bjlhdx_xszz.do?method=gjzxdksq&isQuery=is','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal name="isQuery" value="is">
						<button class="button2"
							onclick="add('/xgxt/bjlhdx_xszz.do?method=gjzxdksq&isQuery=is');"
							style="width:80px">
							增 加
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
							onclick="modi('/xgxt/bjlhdx_xszz.do?method=gjzxdksq&isQuery=is');"
							style="width:80px">
							修 改
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
					<logic:notEqual name="isQuery" value="is">
						<button class="button2"
							onclick="pass('/xgxt/bjlhdx_xszz.do?method=gjzxdkshXxxx&actDo=pass');"
							style="width:80px">
							通 过
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
							onclick="notPass('/xgxt/bjlhdx_xszz.do?method=gjzxdkshXxxx&actDo=notPass');"
							style="width:80px">
							不通过
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2"
						onclick="del('/xgxt/bjlhdx_xszz.do?method=gjzxdkshXxxx&actDo=del');"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="impAndChkData1();"
						style="width:80px">
						导入数据1
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据2
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport2()" style="width:80px">
						导出数据
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
