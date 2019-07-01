<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
		function setPk(){
			var len=document.forms[0].mappingList.options.length;			
			var pk="";
			for(var i=0;i<len;i++){
				var optionV=document.forms[0].mappingList.options[i].text;
				if(document.forms[0].mappingList.options[i].selected){					
					if(optionV.indexOf("￥")<0){
						document.forms[0].mappingList.options[i].text="￥"+document.forms[0].mappingList.options[i].text;						
					}else{
						document.forms[0].mappingList.options[i].text=optionV.substring(1,optionV.length);						
					}					
				}
			}		
			//alert(document.getElementById("pk").value);
		}
	</script>
	<script language="javascript">
	function getPk(){
			var len=document.forms[0].mappingList.options.length;
			document.getElementById("pk").value="";
			for(var i=0;i<len;i++){
				var txt=document.forms[0].mappingList.options[i].text;
				if(txt.indexOf("￥")>=0){
					document.getElementById("pk").value=document.getElementById("pk").value+document.forms[0].mappingList.options[i].value+"@@";
				}
			}			
			//alert(document.getElementById("pk").value);
			if(document.getElementById("pk").value==""){
				alert("主键为空,请设置主键");
				return false;
				}else{
					return true;
				}
		}
	</script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();title_m.innerHTML = window.dialogArguments.document.all('title_m').innerHTML+' - 字段对照';">
		<html:form action="/saveUpData" method="post">
			<div class="title">
				<div class="title_img" id="title_m"></div>
			</div>
			<html:hidden property="itemStr" styleId="itemList" />
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" id="pk" name="pk" value=""/>
			<input type="hidden" name="filepath"
				value="<%=request.getAttribute("filepath")%>" />
			<input type="hidden" name="tabName"
				value="<%=request.getAttribute("tabName")%>" />
			<input type="hidden" name="tabName"
				value="<%=request.getAttribute("tabName")%>" />	
			<input type="hidden" name="moditag"　id="moditag"
				value="<%=request.getAttribute("moditag")%>" />				
			<fieldset>
				<legend>
					字段匹配
				</legend>
				<table width="98%" align="center" class="tbstyle">
					<thead>
						<tr align="center">
							<td>
								源数据列表
							</td>
							<td>
								目标表字段列表
							</td>
							<td>
								对应字段列表
							</td>
						</tr>
					</thead>
					<tr align="center">
					<td></td>
					<td></td>
					<td><font color="red">选中某行单击右键设置主键</font></td>
					</tr>
					<tr align="center">
						<td>
							<select name="excelItem" style="width:160px;" size="18"
								id="excelList"></select>
						</td>
						<td>
							<html:select property="oracleItem" style="width:160px;" size="18"
								styleId="oracleList"
								ondblclick="if(document.forms[0].excelList.selectedIndex>=0) addItemList();">
								<html:options collection="orcleItemList"
									labelProperty="comments" property="column_name" />
							</html:select>
						</td>
						<td rowspan="2" id="oContextMenu">
							<select name="mappingItem" style="width:200px;cursor:hand;"
								size="20" id="mappingList" ondblclick="deleteItemList()"
								onmousedown="if(event.button==2)setPk();"></select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="button2"  name="button1"
								style="width:70px" value=" + " onclick="addItemList()" />
							<input class="button2"  name="button2"
								style="width:70px" value=" - " onclick="deleteItemList()" />
							<input  name="button3" style="width:70px"
								class="button2" value="默 认" onclick="defaultItemList()" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input class="button2"  name="button1"
								style="width:100px" value="确 定" onclick="if(getPk())subList()" />
							<input  class="button2" name="button2"
								style="width:100px" value="重 置" onclick="clearList()" />
							<input  class="button2" name="button2"
								style="width:100px" value="关 闭" onclick="Close();return false;" />
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
var powerChanged = false;
var i = 1;
var j = 1;
var dpd = new Array();
var txt = document.forms[0].itemList.value;
var SplitSignOne = "!!SplitSignOne!!";
var SplitSignTwo = "!!SplitSignTwo!!";
var initStrToSplit = txt.split(SplitSignOne);

document.forms[0].excelList.options.length = 0;
for(i = 1;i<initStrToSplit.length;i++){
	dpd[i] = initStrToSplit[i].split(SplitSignTwo);
    document.forms[0].excelList.options[document.forms[0].excelList.options.length] = new Option(dpd[i][2],dpd[i][1]);
	
}
</script>
	</body>
</html>
