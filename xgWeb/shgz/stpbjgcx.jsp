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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function ljsdaUpdate(url,w,h){	
	var pk="";	
	if(curr_row == null ){
			alert("请选择一行记录！\n单击一行即可!");
			return false;
		} 		
	pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
	url+=pk;
	
	if(w==""||w==null){
		w = 800;
}
	if(h==""||h==null){
		h = 700;
	}
	showTopWin(url,w,h);		
}
	
	function addInfo(){
		if(curr_row == null){
			alert("此操作需要有选中的行，请点击要添加备注的行！");
			return false;
		}
		showTopWin("addStuInfo.do?xh=" + curr_row.cells[1].innerText,400,300,false);
	}
	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
      }
    }
    
    function del(url){
		var RowsStr="";
		if(curr_row == null || typeof(curr_row) == undefined){
		alert("请选择要删除的记录！！");
		return false;
	}
	    RowsStr=curr_row.cells[0].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
	    alert(RowsStr);
		document.forms[0].delPk.value = RowsStr;
		document.forms[0].action=url;
	    document.forms[0].submit();
}
function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('xydm').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('xydm').disabled=false;
		}
	}
	function xy_dataExport1()
	{
		var url='/xgxt/expData.do?tableName=view_xsjbxx';
		var xydm=document.getElementById('xydm').value;
		url=url+'&xydm='+xydm;
		dataExport1(url);
	}
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/stgl" method="post">
			<input type="hidden" id="xxdm" name="xxdm" 
				value="<bean:write name="xxdm" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="method" name="method"
				value="sthpjgcx" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：社会工作 - 社团管理 - 社团管理结果查询
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
							<td align="left" nowrap>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<html:select property="xmdm" style="width:80px" styleId="xmdm">
									<html:option value="sum">结算总分</html:option>
									<html:options collection="pbxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="ffjebj" style="width:120px">
									<option value=""></option>
									<option value="&gt;=">
										大于或等于
									</option>
									<option value="&gt;">
										大于
									</option>
									<option value="=">
										等于
									</option>
									<option value="&lt;">
										小于
									</option>
									<option value="&lt;=">
										小于或等于
									</option>
									</select>
									&nbsp;&nbsp;&nbsp;
								<input type="text" name="bjfz" value="" style="width:60px" />
								</td>
								<td width="10" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stgl.do');">
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/stgl.do?method=stpbjgjs');"
								style="width:80px">
								重新计算
							</button>
						</div>
				</logic:equal>
				<logic:notEmpty name="result">
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
