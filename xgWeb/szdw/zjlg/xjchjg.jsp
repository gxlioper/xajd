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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
function chkView(){
	var xjchdm = $("xjchdm").value; 	
    var url = "/xgxt/szdw_zjlg.do?method=xjchChek&doType=view&xjchdm="+xjchdm+"&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk;
    showTopWin(url,"850","600");              		                  
 }
</script>
	<body>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>	
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
			
			<html:form action="/szdw_zjlg" method="post">
<%--				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />--%>
<%--				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />--%>
				<input type="hidden" id="userType" name="userType"
					value="${userType}" />
				<input type="hidden" id="userName" name=" userName "
					value="${userName}" />
				<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>		
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
				<input type="hidden" id="xjchdm" name="xjchdm"  value="<bean:write name="xjchdm"/>" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置： 思政队伍 - 工作队伍建设 - 先进称号审核 - <bean:write name = "xjchmc"/>
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
									学年：
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;部门：
									<logic:equal value="admin" name = "userType" scope="session">
									<html:select property="bmdm" styleId="bmdm">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>	
									</logic:equal>
									<logic:notEqual value="admin" name = "userType" scope="session">
										<html:select property="bmdm" styleId="bmdm" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc" />
										</html:select>	
									</logic:notEqual>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" />			
									&nbsp;&nbsp;审核状态：
									<html:select property="shzt" styleId="shzt">
										<html:option value="">全部</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>															
								</td>
								<td width="10" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/szdw_zjlg.do?method=xjchQuery&go=go');">
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
							<font color="blue">提示：双击一行可以查看详细信息并进行审核；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()">
									</td>
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
										ondblclick="chkView()">
										<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />"/>
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
										</logic:iterate>										
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
						</table>
						<TABLE width="99%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xjchForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
