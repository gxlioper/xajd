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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>	
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script language="javascript">
	function hdwj(pk){
		var url = "/xgxt/nbcsPxpj.do?method=pxpjUpdate";
		url+="&pk="+pk;
		showTopWin(url,'800','600');
	}
	
	</script>
	<body onload="setWjList()">
		<html:form action="/nbcsPxpj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="xq" id="xq" value=""/>
			<input type="hidden" name="nd" id="nd" value=""/>
			<input type="hidden" name="isDjr" id="isDjr" value="${isDjr }"/>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										&nbsp;&nbsp;年级：
										<input type="hidden" name="queryequals_nj" value="${ stuInfo.nj}">
										<html:select name="stuInfo" property="nj" style="" styleId="nj" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;学年：
										<input type="hidden" name="queryequals_xn" value="${ stuInfo.xn}">
										<html:select name="stuInfo" property="xn" style="width:120px" styleId="xn" disabled="true">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:25px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/nbcsPxpj.do?method=pxpjManage');">
											查询
										</button>
										<br>
										<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('nj-xn-nd-xq-xh-xm-xy-zy-bj-id');">
											重置
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：	
										<input type="hidden" name="queryequals_xydm" value="${ stuInfo.xydm}">	
										<html:select name="stuInfo" property="xydm" style="" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<input type="hidden" name="queryequals_zydm" value="${ stuInfo.zydm}">
										<html:select name="stuInfo" property="zydm" style="" styleId="zy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<input type="hidden" name="queryequals_bjdm" value="${ stuInfo.bjdm}">
										<html:select name="stuInfo" property="bjdm" style="" styleId="bj" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										&nbsp;&nbsp;问卷：
										<html:select property="queryequals_id" style="" styleId="id">
											<html:options collection="wjList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
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
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以排序;点击“回答问卷”对问卷进行回答操作.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<td>
											操&nbsp;&nbsp;作
										</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">								
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">	
												<logic:equal name="isDjr" value="true">
													<a href="javascript:hdwj('<bean:write name="v" />')" />点击回答</a>
												</logic:equal>
												<logic:equal name="isDjr" value="false">
													非答卷人
												</logic:equal>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="100%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
		<logic:notEmpty name="result">
			<logic:present name="message">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
						$("doType").value = "";
					}
				</script>
			</logic:present>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
