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
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
	
	function tbsj() {
		if($("xn").value == ""){
			alert("请确定转需要同步的军训学年");
			return false;
		}
		var url = "/xgxt/zjcmJxgl.do?method=jxcjManage&doType=tb";
		showTips('处理数据中，请等待......');
		refreshForm(url);
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjcmJxgl" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="csh" name="csh" value="${csh }" />
			<input type="hidden" id="message" name="message" value="${message}"/>
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
										<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;学年：
										<html:hidden property="xn"/>
										<html:select property="xn" style="" onchange="" disabled="true">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zjcmJxgl.do?method=jxcjManage');">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										学号：
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="" maxlength="20"/>
										&nbsp;&nbsp;性别：
										<html:select property="xb" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xbList" property="en" labelProperty="cn" />
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
										ondblclick="">
										<logic:iterate id="v" name="s" offset="1">
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
											page="/sjcz/turnpage.jsp?form=jxglTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="tbsj();">
								同步数据
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()"
								style="width:80px">
								导出数据
							</button>
						</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
