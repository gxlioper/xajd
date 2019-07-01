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


function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function xszzdataExport() {
	if(document.getElementById('xmdm').value==''){
		alert('审核项目不能为空！');
		return false;
	}
	document.forms[0].action = "/xgxt/xcxyXszz.do?method=xszzExpData";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function impAndChkData2(){
	if(document.getElementById('xmdm').value==''){
		alert('审核项目不能为空！');
		return false;
	}
	impAndChkData();
}
	function query(){
		if(document.getElementById('xmdm').value==''){
			alert('审核项目不能为空！');
			return false;
		}else{
			refreshForm('/xgxt/xcxyXszz.do?method=getXmsh&doType=query');
		}
	}
	function plsh(){
		var array = document.getElementsByName('pk');
		var xmdm = document.getElementById('xmdm').value;
		var flag = 0;
		var pks = '!@!';
		for(var i = 0;i < array.length;i++){
			if(array[i].checked==true){
				flag = 1;
				pks += array[i].value+'!@!';
			}
		}
		if(flag == 0){
			alert('请勾选要审核的记录！');
			return false;
		}else{
			showTopWin('/xgxt/xcxyXszz.do?method=excutePlsh&xmdm='+xmdm+'&temp='+pks,450,350);
		}
	}
	function checkAndView(obj){
		var xmdm = document.getElementById('xmdm').value;
		var array = obj.getElementsByTagName('input');
		showTopWin('/xgxt/xcxyXszz.do?method=excuteDgsh&temp='+array[0].value+'&xmdm='+xmdm+'&xh='+array[1].value,750,650);
	}
	</script>
		<html:form action="xcxyXszz.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:equal value="xy" name="userType">
				<input type="hidden" id="condsql" name="condsql" value="no"/>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<input type="hidden" id="condsql" name="condsql" value="yes"/>
			</logic:notEqual>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:100px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;审核项目：
								<html:select property="xmdm" style="width:120px"
									onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh')">
									<html:option value=""></html:option>
									<html:options collection="zzxmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:60px" styleId="nj"
									onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh')">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="xydm" value="<bean:write name="xydm"/>">
									<html:select property="xy" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh&xysx=yes')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="query()">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh')">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal value="xy" name="userType">
									<bean:message key="lable.xsgzyxpzxy" />审核：
									<html:select property="xysh" style="width:80px" styleId="xysh"
										onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh')">							
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									学校审核：
									<html:select property="xxsh" style="width:80px" styleId="xxsh"
										onchange="refreshForm('/xgxt/xcxyXszz.do?method=getXmsh')">							
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</logic:notEqual>
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
						<font color="blue">提示：双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序</font>
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
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								ondblclick="checkAndView(this);">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk" value="<bean:write name="v"/>">
									</logic:iterate>
									<logic:iterate id="v1" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v1"/>">
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
					<button type="button" class="button2" onclick="plsh();" style="width:80px">
						批量审核
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData2();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="xszzdataExport()" style="width:80px">
						导出数据
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
