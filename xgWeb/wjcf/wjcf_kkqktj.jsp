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
		<script type="text/javascript">
			function searchView(url)
			{
				var qssj=document.getElementById("qssj").value;
				var zzsj=document.getElementById("zzsj").value;
				if (qssj==""||qssj.length<0||zzsj==""||zzsj.length<0){
					alert("该次操作是按旷课起始时间及终止时间进行查询！请再次确认！");
				}
				else{
					allNotEmpThenGo(url);
				}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
		</script>
	</head>
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
	<body onload="xyDisabled('xy');initPage()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<html:form action="/wjcfKkqktj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<logic:equal value="11049" name="xxdm">
						日常管理 - 考勤管理 - 统计情况
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						<bean:write name="tips" scope="request" />
					</logic:notEqual>
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						旷  课  查  询  条  件
					</legend>
				   	<input type="hidden" id="userType" name="userType" 
					   value="<bean:write name="userType" scope="request"/>" />  
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" name="xyV" value=""/>
					<input type="hidden" name="zyV" value=""/>
					<input type="hidden" name="bjV" value=""/>
					<input type="hidden" name="njV" value=""/>
					<table width="100%" class="tbstyle" >
						<thead>
							<tr>
								<td align="left"> 
								 	起始时间：
									<input type="text" name="qssj" id="qssj" onclick="return showCalendar('qssj','y-mm-dd');" onblur="getRqVal('qssj')">
									&nbsp;&nbsp;&nbsp;终止时间：
									<input type="text" name="zzsj" id="zzsj" onclick="return showCalendar('zzsj','y-mm-dd');" onblur="getRqVal('zzsj')"> 
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="go" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="searchView('/xgxt/wjcfKkqktj.do')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									年级：
									<input type="hidden" name="njV" value=""/>
									<select name="nj" id="nj" onchange="initZyList();initBjList();"></select>
									&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:120px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
							 	<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<logic:present name="setxy">
									<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									<select name="xy" id="xy" style="width: 180px" disabled="true" onchange="initZyList();initBjList()"></select>
									</logic:present>
									<logic:notPresent name="setxy">
									<input type="hidden" name="xyV" value=""/>
									<select name="xy" id="xy" style="width: 180px" onchange="initZyList();initBjList()"></select>
									</logic:notPresent>
									&nbsp;&nbsp;专业：
									<input type="hidden" name="zyV" value=""/>
									<select name="zy" id="zy" style="width: 180px" onchange="initBjList()"></select>
									&nbsp;&nbsp;班级：
									<input type="hidden" name="bjV" value=""/>
									<select name="bj" id="bj" style="width: 180px"></select>
									
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
							<font color="blue">提示：单击表头可以排序( 
							<logic:present name="isPrint">
								<bean:write name="isPrint"/>
							</logic:present>)</font>
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
									<tr align="center" onclick="rowOnClick(this);" style="cursor:hand">
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
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								<button type="button" class="button2"
								onclick="expTab('rsTable','<bean:write name="isPrint" scope="request"/>')"
								style="width:80px">
								打印/预览
							</button>
							</logic:equal>
						</div>
					</center>
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

