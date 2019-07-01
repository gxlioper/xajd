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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body onload="initPage()">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/jfhzcx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">	
								<logic:present name="showbjlh">
										查询方式：
										<html:select property="cxfs" style="width:100px" styleId="cxfs">
											<html:option value="详细">详细</html:option>
											<html:option value="汇总">汇总</html:option>
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:present>							
								年度：
								<input type="hidden" name="ndV" id="ndV" />
								<html:select property="nd" style="width:90px" styleId="nd">
								</html:select>	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：								
								<input type="hidden" name="xnV" id="xnV" />
								<html:select property="xn" style="width:120px" styleId="xn"> 
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:90px">
								<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
						    </td>
							<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/work_outlay_search.do?act=workOutlay')">
										查询
									</button>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<button type="button" class="button2" style="height:40px;width:40px"
										onclick="">
										&nbsp;&gt;&nbsp;&gt;&nbsp;
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									用人单位：
									<html:select property="yrdwdm" style="width:200px" styleId="yrdwdm">
										<html:option value=""></html:option>
										<html:options collection="yrdwList" property="yrdwdm"
											labelProperty="yrdwmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;划拨类别：
									<html:select property="hblb" style="width:100px" styleId="hblb">
										<html:option value=""></html:option>
										<html:option value="常规">常规</html:option>
										<html:option value="增拨">增拨</html:option>
									</html:select>
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
						当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
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
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
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
			<br/>
			<br/>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		</html:form>
	</body>
</html>