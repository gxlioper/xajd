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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
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
			
		}
		changeColor(iRow);
	}

	//选中行变色
	function changeColor(E){
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
		
		var userType = val('userType');
		var msg = "确定要删除所选记录？";
		if(userType == "xy"){
			msg = "<bean:message key="lable.xsgzyxpzxy" />用户不能删除已通过学校审核的数据，" + msg;
		}
		if (!confirm(msg)){
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
			url += "&xh=";
			url += curr_row.getElementsByTagName("input")[2].value;
			url += "&queryPK=";
			url += curr_row.getElementsByTagName("input")[3].value;
			url += "&tName=";
			url += document.getElementById("realTable").value;
			url += "&lx="+$("titName").value;
			if(url.search('assisChkOne')){
				url += "&xydm="+document.getElementById("xy").value;
			}
			showTopWin(url, 750, 550);
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
		url += "&xh=";
		url += curr_row.getElementsByTagName("input")[2].value;
		url += "&queryPK=";
		url += curr_row.getElementsByTagName("input")[3].value;
		return showTopWin(url,750,550);
	}
	
	function dataExport2() {
		document.forms[0].action = "/xgxt/expData2.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	</script>
	<body onload="xyDisabled('xy');initNdList();initNjList();initOnLoadXyList();">		
		<html:form action="/xszz_ynys.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<input type="hidden" id="rsExpString" name="rsExpString"
				value="<bean:write name="rsExpString" scope="request"/>" />
			<input type="hidden" id="colListS" name="colListS"
				value="<bean:write name="colListS" scope="request"/>" />
			<input type="hidden" id="titName" name=titName
				value="<bean:write name="titName" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
			<input type="hidden" id="isBzr" name="isBzr" value="${isBzr}"/>
			<input type="hidden" name="njV" id="njV" value="${form.nj}"/>
			<input type="hidden" name="xyV" id="xyV" value="${form.xydm}"/>
			<input type="hidden" name="zyV" id="zyV" value="${form.zydm}"/>			
			<input type="hidden" name="bjV" id="bjV" value="${form.bjdm}"/>
			<input type="hidden" name="ndV" id="ndV" value="${form.nd}"/>
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								<logic:equal name="xxdm" value="10690">
								年级：
								<html:select property="nj" onchange="initOnLoadXyList();" 
								styleId="nj" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								年度：
								<html:select property="nd" styleId="nd" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								学号：
								<html:text property="xh" styleId="xh" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>&nbsp;&nbsp;
								姓名：
								<html:text property="xm" styleId="xm" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>			
								<logic:notEqual value="is" name="isQuery">
									审核类别：
									<html:select property="shlb" styleId="shlb" style="width:90px;padding-left:100px">
										<html:option value=""></html:option>
										<html:options collection="checkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:notEqual>						
								</logic:equal>
								<logic:notEqual name="xxdm" value="10690">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" style="width:180px"
									styleId="xy" onchange="refreshForm('/xgxt/auditing_gjjzxj.do')">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
								<html:select name="rs1" property="zydm" style="width:180px"
									styleId="zy" onchange="refreshForm('/xgxt/auditing_gjjzxj.do')">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级：
								<html:select name="rs1" property="bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</logic:notEqual>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/xszz_ynys.do?method=jzxjsh&lx='+$('titName').value)">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initOnLoadZyList()"
								 style="width:180px" styleId="xy">
								</html:select>&nbsp;&nbsp;
								专业：
								<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								</html:select>&nbsp;&nbsp;
								班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
								</html:select>
							</td>
						</tr>
						<logic:equal name="titName" value="xy">
						<tr>
							<td colspan="2">
								级别：
								<html:select property="jb" styleId="jb">
									<html:option value=""></html:option>
									<html:options collection="jbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						</logic:equal>
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
						<font color="blue">提示：双击一行可以查看详细信息<logic:notEqual value="is" name="isQuery">，并可以改变审核状态</logic:notEqual>；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="4">
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
									ondblclick="chkAssisOne('/xgxt/xszz_ynys.do?method=jzxjshOne&lx='+$('titName').value,'edit')">
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
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
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
									ondblclick="chkAssisOne('/xgxt/xszz_ynys.do?method=jzxjshOne','view')">
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
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
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
						<button type="button" class="button2" onclick="add('/xgxt/xszz_ynys.do?method=jzxjsq&lx='+$('titName').value);"
							style="width:80px">
							增 加
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modi('/xgxt/xszz_ynys.do?method=jzxjsq&lx='+$('titName').value);"
							style="width:80px">
							修 改
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
					<button type="button" class="button2"
						onclick="del('/xgxt/xszz_ynys.do?method=jzxjshOne&actDo=del&lx='+$('titName').value+'&isQuery='+$('isQuery').value);"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport2()" style="width:80px">
						导出数据
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
