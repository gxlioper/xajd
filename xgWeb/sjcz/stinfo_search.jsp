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
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link rel="stylesheet" rev="stylesheet" href="style/calendar.css"
		type="text/css" media="all" />
	<script language="javascript">
//function sendTestStInfo(){
//	window.dialogArguments.document.forms[0].yxstlbV.value = curr_row.getElementsByTagName("input")[0].value;
//	window.dialogArguments.createStList();
//}
</script>
	<script language="javascript" src="js/function.js"></script>
	<base target="_self">
	<body>
		<html:form action="/data_search" method="post">
			<div id="title">
				<div id="title_m" class="title_img">
					当前所在位置：心理健康 - 心理测试 - 组卷试题查询
				</div>
			</div>
			<div id="main" style="heigth:100px;">
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 试题类型:
								<html:select property="stlxdm" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="stlxdm"
										labelProperty="stlxmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试题难度级别：
								<html:select property="stndjbdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="stndjbdm"
										labelProperty="stndjbmc" />
								</html:select>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/stinfo_search.do?act=testQuestion')">
									查 询
								</button>
							</td>
						</tr>
					</thead>
				</table>
				<div id="result">
					<div class="searchcontent">
						<logic:empty name="rs">
							<br />
							<br />
							<p align="center">
								未找到任何记录！
							</p>
						</logic:empty>
						<logic:notEmpty name="rs">
    当前试题数：<bean:write name="rsNum" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
								color="blue">提示：双击将该题加入到已选试题列表</font>
							<table width="99%" id="rsTable" class="tbstyle">
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
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="window.dialogArguments.createStList(curr_row.getElementsByTagName('input')[0].value)">
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
							<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</logic:notEmpty>
						<br />
					</div>
				</div>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" />
				</div>
				<div id="button" class="buttontool">
					<button type="button" class="button2" onclick="viewMore('view')"
						style="width:80px">
						查 看 详 情
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px">
						&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;
					</button>
				</div>
			</div>
		</html:form>
	</body>
</html>
