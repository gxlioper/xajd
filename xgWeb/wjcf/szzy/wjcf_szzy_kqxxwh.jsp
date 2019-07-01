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
	<script language="javascript">
		
	</script>
	<%
      response.setHeader("Pragma","No-cache");
      response.setHeader("Cache-Control","no-cache");
      response.setDateHeader("Expires", 0);
    %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="wjcf/szzy/szzyjs/szzyjs.js"></script>
		<html:form action="/szzykqxxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="wjcfszzy" key="wjcf_szzy_kqxxwh"/>
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" name="xyV" value=""/>
					<input type="hidden" name="zyV" value=""/>
					<input type="hidden" name="bjV" value=""/>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style="width:80px" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
									&nbsp;学年：
									<html:select property="xn" style="width:110px" styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
									&nbsp;学期：
									<html:select property="xq" style="width:80px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
									&nbsp;日期：
									<html:text property="rq" styleId="rq" readonly="true" style="cursor:hand; width:130px;" styleClass="inputtext" onclick="return showCalendar('rq','y-mm-dd','xqj');" onblur="chgxqjval('xqj');"></html:text>
									&nbsp;星期：
									<html:text property="xqj" styleId="xqj" readonly="true" style="cursor:hand;width:100px " ></html:text>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="if (document.getElementById('jsbh').value=='')alert('查询时教室编号为必填项!');else allNotEmpThenGo('szzykqxxquery.do')">
										查询
									</button>
								</td>
	
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									&nbsp;专业：
									<html:select property="zydm" style="width:150px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
									&nbsp;班级：
									<html:select property="bjdm" style="width:150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
									&nbsp;教室：
									<html:text property="jsbh" styleId="jsbh" styleClass="inputtext" style="width:100px"></html:text>
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="4">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="chkPriseOne5('szzyaddkqxx.do?pkValue=',570,410)" align="center">
											<td>
											<logic:iterate id="v" name="s" offset="0" length="4">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											</td>
										<logic:iterate id="v" name="s" offset="5">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2"
								onclick="chkPriseOne5('szzyaddkqxx.do?pkValue=',570,410)"
								style="width:100px">
								增加考勤信息
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="expTab('rsTable','考勤信息统计表',null,null,null,null)" style="width:90px">
								打印/预览
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;
							<a href="xlsDown/<bean:write name="realTable" scope="request"/>.doc" target="_blank">模板下载</a>
						</div>
					</center>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
