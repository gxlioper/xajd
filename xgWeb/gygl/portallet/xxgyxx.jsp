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
		<%@include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/XsGyXxGl.do?method=xsGyXxQuerry" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					
				</div>
			</div>
				<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tabform">
					<tbody>
						<tr>
							<td align="left">
								学号：
								<html:text property="xh" styleId="xh" ></html:text>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyXxGl.do?method=xsGyXxQuerry')">
									查 询
								</button>
							</td>
						</tr>
					</tbody>
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
					<table width="99%" id="rsTable" class="tabform">
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
							<tr onclick="rowOnClick(this)"
								 style="cursor:hand" ondblclick="viewXx()">
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
		</html:form>
<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
</body>
<script type="text/javascript">
<!--
function viewXx(){
 pkValue = curr_row.getElementsByTagName("input")[0].value;  
 var url = "/xgxt/XsGyXxGl.do?method=xsGyXxView&pkValue=";
 url +=pkValue;
 showTopWin(url,'600','400');
}
//-->
</script>
</html>		
		
