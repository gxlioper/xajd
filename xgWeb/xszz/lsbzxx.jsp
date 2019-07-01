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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
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

function pass(url){
	var RowsStr="!!#!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
		if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	}
	}
	document.forms[0].sbbm.value = RowsStr;
	
	if (RowsStr=="!!#!!"){
		alert("请选择要批量审核的记录！");
		return false;
	}
	
	if (!confirm("确定要通过所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function notpass(url){
	var RowsStr="!!#!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	}
	}
	document.forms[0].sbbm.value = RowsStr;
	
	if (RowsStr=="!!#!!"){
		alert("请选择要批量审核的记录！");
		return false;
	}
	
	if (!confirm("确定要不通过所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function del(url){
	var RowsStr="!!#!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	}
	}
	document.forms[0].sbbm.value = RowsStr;
	
	if (RowsStr=="!!#!!"){
		alert("请选择要批量删除的记录！");
		return false;
	}
	
	if (!confirm("确定要删除所选记录？")){
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
		</script>
		<html:form action="/stu_temp_subsidy.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
					<input type="hidden" name="sbbm" value="" />
					<input type="hidden" name="xyV" value=""/>
					<input type="hidden" name="zyV" value=""/>
					<input type="hidden" name="bjV" value=""/>
						
					<logic:present name="isSh">
						<logic:match value="is" name="isSh">
							<script language="javascript">
	         					alert("已通过学校审核，不能删除！");
	         				</script>
						</logic:match>
					</logic:present>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
								<html:select name="rs1" property="nj" onchange="initBjList()" style="width:70px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();initBjList()" style="width:140px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select name="rs1" property="zydm" onchange="initBjList()" style="width:160px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select name="rs1" property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="allNotEmpThenGo('/xgxt/stu_temp_subsidy.do?go=go')">
									查询
								</button>
							</td>
							</tr>
							<tr>
								<td align="left">
									补助日期：
									<input type="text" readonly style="cursor:hand;width:50px"
										onclick="return showCalendar('bzffny','y-mm');"
										value="<bean:write name='rs1' property="bzffny" />"
										name="bzffny" id="bzffny" />
									学号：
									<html:text name="rs1" property="xh" style="width:120px"></html:text>
									卡号：
									<html:text name="rs1" property="kh" style="width:120px"></html:text>
									审核结果：
								<html:select name="rs1" property="xxsh" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="xxshList" property="xxsh"
										labelProperty="xxsh" />
								</html:select>
								补助项目：
								<html:select name="rs1" property="lsbzdm" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="lsbzList" property="lsbzdm"
										labelProperty="lsbzmc" />
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
							<font color="red">提示：学校用户可批量审核或删除；通过审核的数据不能删除；</font>
							<font color="blue">双击一行可以查看详细信息；单击表头可以排序！</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="qbxz" value="all" onclick="chec('qbxz')">
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
										ondblclick="viewMore('view')">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pk" value="<bean:write name="v"/>">
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
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
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2"  onclick="viewInfo('add','/xgxt/stu_lsbz_info.do')"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="viewInfo('modi','/xgxt/stu_lsbz_info.do')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal name="isXX" value="is">
									<button class="button2" onclick="pass('/xgxt/stu_lsbz_info.do?doType=pass');"
										style="width:80px">
										批量通过
									</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="button2" onclick="notpass('/xgxt/stu_lsbz_info.do?doType=notpass')"
										style="width:80px">
										批量不通过
									</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
							<button class="button2" onclick="del('/xgxt/stu_lsbz_info.do?doType=del');"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<a href="xlsDown/<bean:write name="realTable" scope="request"/>.xls" target="_blank">模板下载</a>--%>
						</div>
					</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

