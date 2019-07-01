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
	for(i=0;i<Rows.length;i++){
		Rows[i].style.backgroundColor=cur_bgc;	
	}
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function init(){
	var url = "/xgxt/xcxyXszz.do?method=zzxmSjEdit";
	url += "&act=clear";
	if(confirm('确定要初始化所有资助项目时间吗？')){
		refreshForm(url);
	}
}

function setOne(){
	if((curr_row == null) || (curr_row == "")){
		alert("请单击要修改的记录！");
		return false;
	}
	var url = "/xgxt/xcxyXszz.do?method=zzxmSjEdit";
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	url += "&doType=one";
	return showTopWin(url,400,360);
}
function setMany(){
	var RowsStr="!@!";	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!@!";
    	}
	}
	if (RowsStr=="!@!"){
		alert("请勾选要批量设置的记录！");
		return false;
	}
	var url = "/xgxt/xcxyXszz.do?method=zzxmSjEdit";
	url += "&doType=many&savepk="+RowsStr;
	return showTopWin(url,400,360);
}
		</script>
		<html:form action="/xcxyXszz.do?method=zzxmInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									精确资助项目：
									<html:select name="rs1" property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zzxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;模糊资助项目名称：
									<html:text name="rs1" property="xmmc" style="width:180px"></html:text>
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<button type="button" class="button2" id="search_go"
										onclick="refreshForm('xcxyXszz.do?method=zzxmSjInfo&doType=query');">
										查询
									</button>
								</td>
							</tr>
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
						<font color="blue">提示：双击一行可以查看并修改详细信息；单击表头可以排序；</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="setOne();">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pk" value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">								
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
						<button type="button" class="button2" onclick="init();"
							style="width:80px">
							全部初始化
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="setOne();"
							style="width:80px">
							单个设置
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="setMany();"
						style="width:80px">
						批量设置
					</button>
				<br />&nbsp;
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:equal value="yes" name="result">
			<script language="javascript">
	         	alert("删除成功！");
	        </script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script language="javascript">
	         	alert("删除失败！");
	        </script>
		</logic:equal>
		
	</body>
</html>
