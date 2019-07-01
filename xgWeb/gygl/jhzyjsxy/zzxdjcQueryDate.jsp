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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
function getrq(){
	var jcrq = $("jcrq").value;
	if (jcrq.length == 8) {
		var nd = jcrq.substr(0,4);
		var yf = jcrq.substr(4,2);
		var xn = "";
		var xq = "";
		
		if (toInt(yf) >= 9) {
			xn = nd + "-" + (toInt(nd)+1);
			xq = "01";
		} else {
			xn = (toInt(nd)-1) + "-" + nd;
			xq = "02";
		}
		$("xn").value = xn;
		$("xq").value = xq;
		$("yf").value = yf;
	} else {
		$("xn").value = "";
		$("xq").value = "";
		$("yf").value = "";
	}
}
function tz(){
	$("lddm").value = "";
	$("cs").value = "";
	$("qsh").value = "";
	$("xn").value = "";
	$("xq").value = "";
	$("yf").value = "";
	$("jcrq").value = "";
	refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcInfoManage');
}
function tj(){
	$("lddm").value = "";
	$("cs").value = "";
	$("qsh").value = "";
	$("xn").value = "";
	$("xq").value = "";
	$("yf").value = "";
	$("jcrq").value = "";
	refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcTj');
}
</script>
	<body>
		<center>
			<script language="javascript" src="js/commanFunction.js"></script>
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
			<script language="javascript" src="js/calendar.js"></script>
			<script language="javascript" src="js/calendar-zh.js"></script>
			<script language="javascript" src="js/calendar-setup.js"></script>
			
			<html:form action="/jhzy_gygl" method="post">
				<input type="hidden" id="userType" name="userType" value="${userType}" />
				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：公寓管理 - 信息维护 - 自主熄灯检查管理 
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
									楼栋
									<html:select property="lddm" styleId="lddm"
										onchange="getLcList()" >
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									楼层
									<html:select property="cs" styleId="lc"
										onchange="getQshList2()" >
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
									寝室
									<html:select property="qsh" styleId="qsh" >
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/jhzy_gygl.do?method=zzxdjcQueryDate&go=go');">
										查询
									</button>
								</td>
							</tr>
							<tr>
							<td align="left" nowrap>
								学年
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								学期
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								月份
								<html:select property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf"
										labelProperty="yf" />
								</html:select>
								检查日期
								<input type="text" style="cursor:hand;width:80px"
									onclick="return showCalendar('jcrq','y-mm-dd');" onblur="dateFormatChg(this);getrq();"
									value="<bean:write name='rs1' property="jcrq" />" name="jcrq"
									id="jcrq" />
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
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr style="cursor:hand">
										<td align="center" width="10%">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<bean:write name='v'/>
											</logic:iterate>
										</td>
										<td align="center" width="5%">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name='v'/>
											</logic:iterate>
										</td>
										<td align="center" width="10%">
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name='v'/>
											</logic:iterate>
										</td>
										<td align="left" width="75%">
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name='v'/>
											</logic:iterate>
										</td>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="tz();" style="width:80px">
							结果录入
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="tj();" style="width:80px">
							统计熄灯率
						</button>
					</div>
				</center>				
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
		document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
		document.getElementById("btn").style.width = "96%";
		window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
