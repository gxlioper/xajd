<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
function rowOver(objTr) {//
	curr_row = objTr;
}

function rowOut(objTr) {//
	curr_row = null;
}
		
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

function selectAll(){
	var checkBoxArr = document.getElementsByName("pk");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量删除的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能删除已通过学校审核的数据，确定要删除所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function ad(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为A档的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function bd(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为B档的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function cd(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为C档的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function bkn(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为不困难的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
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
	document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=knsExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

		</script>
	<body onload="xyDisabled('xy');">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<html:form action="/zgkydx_xszz.do?method=knssh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal name="isQuery" value="is">
					当前所在位置：学生资助 - 申请结果查询 - 困难生
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					当前所在位置：学生资助 - 审核 - 困难生
					</logic:notEqual>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年度：
								<logic:equal name="isQuery" value="is">
									<html:select property="nd" style="width:120px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="isQuery" value="is">
									<html:select property="nd" style="width:120px" styleId="nd"
										disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:notEqual>
								年级：
								<html:select property="nj" onchange="initBjList()"
									style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								学号：
								<html:text property="xh" styleId="xh"
									style="width:90px;inputtext" styleClass="inputtext"></html:text>
								系部审核：
								<html:select property="xbshjg">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="A档">A档</html:option>
									<html:option value="B档">B档</html:option>
									<html:option value="C档">C档</html:option>
									<html:option value="不困难档">不困难</html:option>
								</html:select>
								学校审核：
								<html:select property="xxshjg">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="A档">A档</html:option>
									<html:option value="B档">B档</html:option>
									<html:option value="C档">C档</html:option>
									<html:option value="不困难档">不困难</html:option>
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zgkydx_xszz.do?method=knssh&go=go')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm"
									onchange="initZyList();initBjList()" style="width:180px"
									styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select property="zydm" onchange="initBjList()"
									style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数： ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="cb" name="cb"
												onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3"
											scope="request">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
										<tr onmouseover="rowOver(this);" onmouseout="rowOut();"
											onclick="rowOnClick(this)"
											ondblclick="chkAssisOne('/xgxt/zgkydx_xszz.do?method=knssq')"
											style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
											<td align=center>
												<input type="checkbox" id="pk" name="pk"
													value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
										<tr onclick="rowOnClick(this)"
											ondblclick="chkAssisOne('/xgxt/zgkydx_xszz.do?method=knsshOne')"
											style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
											<td align=center>
												<input type="checkbox" id="pk" name="pk"
													value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:notEqual>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xszzZgkydxActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
						<div class="buttontool" align="center" id="btn"
							style="position:absolute;width:95%;top:100px">
							<logic:equal name="isQuery" value="is">
								<button type="button" class="button2"
									onclick="add('/xgxt/zgkydx_xszz.do?method=knssq');"
									style="width:80px">
									增 加
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="modi('/xgxt/zgkydx_xszz.do?method=knssq');"
									style="width:80px">
									修 改
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<logic:notEqual name="isQuery" value="is">
								<button type="button" class="button2"
									onclick="ad('/xgxt/zgkydx_xszz.do?method=knssh&go=ad');"
									style="width:80px">
									A 档
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="bd('/xgxt/zgkydx_xszz.do?method=knssh&go=bd');"
									style="width:80px">
									B 档
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="cd('/xgxt/zgkydx_xszz.do?method=knssh&go=cd');"
									style="width:80px">
									C 档
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="bkn('/xgxt/zgkydx_xszz.do?method=knssh&go=bkn');"
									style="width:80px">
									不困难
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" class="button2"
								onclick="del('/xgxt/zgkydx_xszz.do?method=knssh&go=del');"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport2()"
								style="width:80px">
								导出数据
							</button>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="result">
			<logic:equal value="yes" name="result">
				<script>
		 			document.getElementById('search_go').click();
		 		</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script language="javascript">
var b = false;;
if(document.getElementById('rsTable')){
	for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
  			b = true;
  			break;
  		}
  	}
  	if (b) {
  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
  		     var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
  			xhTd.innerHTML = html_content;
  		}
  	}
}
</script>
</html>
