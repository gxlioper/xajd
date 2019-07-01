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
		function kqcc_view()
		{
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc&doType=kqcc_view&xn_id="+xn_id,525,400);
		}
		function wjcf_print()
		{
			openWin("/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc&doType=kqcc_print",800,650);
		}
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
	<style media="print">
		.noPrin{
			display:none;
		}
		.brkPage{
			page-break-after:always;
		}
	</style>
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
		<html:form action="/Wjcf_Kqcc" method="post">
		<html:text style="display:none; width:85px;" styleId="realTable" property="tableName"></html:text>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
			<div class="title">
				<div class="title_img" id="title_m">
					
					<logic:equal value="11049" name="xxdm">
						当前所在位置：日常管理 - 考勤管理 - 学工考勤抽查情况查询
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						当前所在位置：违纪处分 - 学生考勤 - 考勤信息维护
					</logic:notEqual>
				</div>
			</div>
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
									<html:select property="nj" style="width:120px" styleId="nj" onchange="refreshForm('/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
									<html:select property="xn" style="width:120px" styleId="xn">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:120px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;抽查日期：
									<html:text style="cursor:hand; width:85px;" styleId="dateF"
										property="rq"
										onclick="return showCalendar('dateF','yy-mm-dd');"
										/>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc&doType=kqcc_search')">
										查询
									</button>
								</td>
								<%--<td width="10" rowspan="2" align="center" valign="middle">
									<button type="button" class="button2" style="height:40px;width:40px"
										onclick="">
										&nbsp;选择&nbsp;
									</button>
								</td>
							--%></tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy" onchange="refreshForm('/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy" onchange="refreshForm('/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="width:184px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="kqcc_view()">
										<td>
											<input type="hidden" id="XN_ID" name="XN_ID"
												value="<bean:write name="s" property="XN_ID"/>" />
											<bean:write name="s" property="NJ"/>
										</td>
										<td>
											<bean:write name="s" property="XYMC"/>
										</td>
										<td>
											<bean:write name="s" property="ZYMC"/>
										</td>
										<td>
											<bean:write name="s" property="BJMC"/>
										</td>
										<td>
											<bean:write name="s" property="KCMC"/>
										</td>
										<td>
											<bean:write name="s" property="RS"/>
										</td>
										<td>
											<bean:write name="s" property="SDRS"/>
										</td>
										<td>
											<bean:write name="s" property="QJRS"/>
										</td>
										<td>
											<bean:write name="s" property="QQ"/>
										</td>
										<td>
											<bean:write name="s" property="CQL"/>
										</td>
										<td>
											<bean:write name="s" property="RQ"/>
										</td>
										<td>
											<bean:write name="s" property="SJD"/>
										</td>
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
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="wjcf_print()" style="width:90px">
								打印
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="xlsDown/<bean:write name="realTable" scope="request"/>.doc" target="_blank">模板下载</a>
						</div>
					</center>
					<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					</script>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
	</body>
</html>

