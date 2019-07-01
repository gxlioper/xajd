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
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript" src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function isOverWj(){
		var flag = true;
		if(curr_row != null){
			var mc = curr_row.cells[11].innerText;
			if(mc == "未做 "){
				alert("此人尚未完成该问卷，请确认");
				flag = false;
			}
		}
		return flag;
	}
	</script>
	<body onload="getWjList()">
		<html:form action="/nbcsPjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" />
					</FONT>
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
										<html:select property="queryequals_nj" style=""
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;学年：
										<html:select property="queryequals_xn" style="width:120px"
											styleId="xn" onchange="getWjList()">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;年度：
										<html:select property="queryequals_nd" style="" styleId="nd"
											onchange="getWjList()">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;学期：
										<html:select property="queryequals_xq" style="" styleId="xq"
											onchange="getWjList()">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="querylike_xh" styleId="xh" style=""
											maxlength="20" />
										&nbsp;&nbsp;姓名：
										<html:text property="querylike_xm" styleId="xm" style=""
											maxlength="20" />
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:25px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/nbcsPjpy.do?method=hdpjManage');">
											查询
										</button>
										<br>
										<button class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('nj-xn-nd-xq-xh-xm-xy-zy-bj-id-isover');return false;">
											重置
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:equal name="userType" value="xy">
											<html:hidden property="queryequals_xydm" />
											<html:select property="queryequals_xydm" style=""
												styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<!-- 非<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="queryequals_xydm" style=""
												styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										&nbsp;&nbsp;专业：
										<html:select property="queryequals_zydm" style="" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="queryequals_bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										&nbsp;&nbsp;问卷：
										<html:select property="queryequals_id" style="" styleId="id">
											<html:options collection="wjList" property="dm"
												labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;是否完成：
										<html:select property="queryequals_isover" style=""
											styleId="isover">
											<html:option value="">----请选择----</html:option>
											<html:options collection="overList" property="en"
												labelProperty="cn" />
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
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
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
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="if(isOverWj()){showInfo('/xgxt/nbcsPjpy.do?method=hdpjUpdate','view','800','600')}">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td align="center">
												<bean:write name="v" />
												<input type="checkbox" id="checkVal" style="display : none"
													name="primarykey_checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
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
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal name="writeAble" value="yes">
								<button class="button2"
									onclick="if(isOverWj()){showInfo('/xgxt/nbcsPjpy.do?method=hdpjUpdate','update','800','600')}"
									style="width:80px">
									查 看
								</button>
							</logic:equal>
						</div>
					</center>
				</div>
			</logic:empty>
		</html:form>
		<logic:notEmpty name="result">
			<logic:present name="message">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
					}
				</script>
			</logic:present>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
