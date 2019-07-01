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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/yjs/yjs.js"></script>
	<script language="javascript">	
			
	</script>
	<body onload="">
		<html:form action="/yjs_jyjhsy" method="post">
			<%@ include file="/dtjs/hiddenValue.jsp"%>
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
										年级：
										<html:select property="queryequals_nj" style="" onchange="initZylist()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="querylike_xh" styleId="xh" style="width:100px" maxlength="20"/>
										&nbsp;&nbsp;姓名：
										<html:text property="querylike_xm" styleId="xm" style="width:100px" maxlength="20"/>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:25px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwh');">
											查询
										</button>
										<br>
										<button class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('nj-xh-xm-xy-zy');">
											重置
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										院系：
										<html:select property="queryequals_xydm" style="" styleId="xy" onchange="initZylist()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="queryequals_zydm" style="" styleId="zy">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="dm" labelProperty="mc" />
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
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
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
										ondblclick="showInfo('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwhOne','view','800','600')">
										<td align="center">
											<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v" />	
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
											page="/sjcz/turnpage.jsp?form=yjsForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
								<button class="button2" onclick="showTopWin('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwhOne',800,600);" style="width:80px">
									增	加
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="showInfo('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwhOne','update','800','600')" style="width:80px">
									修	改
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="sumitInfo('/xgxt/yjs_jyjhsy.do?method=yjsjbxxwh','del')" style="width:80px">
									删	改
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="impAndChkData()"
									style="width:80px">
									导入数据
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="wjcfDataExport('yjs_jyjhsy.do?method=yjsjbxxwh&doType=exp')"
									style="width:80px">
									导出数据
								</button>
						</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
						$("doType").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
