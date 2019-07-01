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
		<script>   
var mb = 0;
function colorOn(){
	for(i = 0;i<mbT.rows.length;i++){
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
function printZS(mod){
	if(mb >2 || mb <0){
		mb = 0;
		colorOn();
	}
	var url = "/xgxt/print/auto_card_print_";
	url += (mb-(-1));
	url += "_1.htm";
	document.getElementById("nj").value = mod;
	chgRight(url,'_blank');
	document.forms[0].target = "_self";
}
</script>
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body onload="colorOn()">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/log_search" method="post">
			<div class="title noprint">
				<div class="title_img">
					当前所在位置：评奖评优 - 审核 - 证书打印
				</div>
			</div>
			<div class="noprint">
				<fieldset>
					<legend align="center">
						非打印区
					</legend>
					<fieldset>
						<legend>
							查 询 &amp; 操 作
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										<input type="hidden" value="a" id="jxjdm" name="xmdm">
										<input type="hidden" value="a" id="nj" name="nj">
										<input type="hidden" value="s" id="sj" name="sj">
										<input type="checkbox" style="display:none" id="chgMode">
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:230px" styleId="xy"
											onchange="refreshForm('auto_card_print.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="listPriseConf('/xgxt/auto_card_print.do')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										专业：
										<html:select property="zydm" style="width:300px" styleId="zy"
											onchange="refreshForm('auto_card_print.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:230px"
											styleId="bjdm">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</fieldset>
			</div>

			<div class="noprint">
				<fieldset>
					<legend align="center">
						打印区----模板选择
					</legend>
					<table class="tbstyle" align="center" width="100%" height="150px"
						id="mbT">
						<tr>
							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=0;colorOn();"> </td> -->
							<td bgcolor="#FFFFFF" width="45%" align="center"
								style="cursor:hand" onclick="mb=0;colorOn();"></td>
							<td bgcolor="#FFFFFF" width="45%" align="center"
								style="cursor:hand" onclick="mb=1;colorOn();"></td>
							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=2;colorOn();"> </td> -->
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
								onclick="mb=0;colorOn();">
								模板一
							</td>
							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
								onclick="mb=1;colorOn();">
								模板二
							</td>
						</tr>
					</table>
				</fieldset>
			</div>

			<div class="noprint">
				<fieldset>
					<legend align="center">
						非打印区
					</legend>
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
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;">
										<logic:iterate id="v" name="s">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div class="buttontool noprint"
						style="position: absolute;left:1%;top:100px" id="btn">
						<input type='button' class='button2' value='打印测试页'
							onclick="printZS(0)">
						<input type='button' class='button2' value='单个打印'
							onclick="printZS(1)">
						<input type='button' class='button2' value='证书连打'
							onclick="printZS(2)">
					</div>
				</fieldset>
			</div>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
</html>
