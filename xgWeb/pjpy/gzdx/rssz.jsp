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
	<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script language="javascript">
		function priseDataInitForGzdx() {
			if (confirm("初始化操作将会清空当前学年的所有数据，并重新生成。\n确定要初始化吗?")) {
				var dd_html = "";
				dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在初始化数据，请稍候......<br><br>";
				dd_html += "<span class='roll_tip'></span>";
				dd_html += "</td></tr></table>";
				for (i = 1; i < document.getElementsByTagName("table").length; i++) {
						document.getElementsByTagName("table")[i].style.display = "none";
				}
				showDiv(dd_html, 300, 120);
				refreshForm("/xgxt/gzdxPjpy.do?method=cshrs");
			return true;
			}
			return false;
		}
		function chkPriseBatForGzdx() {
			var jxjMsg = document.getElementById("dm").options[document.getElementById("dm").selectedIndex].text;
			var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
			var userType =$("userType").value;
			jxjMsg = (jxjMsg == "") ? "全部" : jxjMsg;
			xyMsg = (xyMsg == "") ? "全部" : xyMsg;
			var d_width = document.body.clientWidth;
			var d_height = document.body.clientHeight ;
			var d_left = 0;
			var d_top = 0;
			var d_color = "#EEF4F9";
			var d_width_top = 300;
			var d_height_top = 200;
			var d_left_top = (d_width - d_width_top) / 2;
			var d_top_top = (d_height - d_height_top) / 2;
			var d_color_top = "#EEF4F9";
			dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
			dd_html += "</div>";
			dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
			dd_html += "<table width='300' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td colspan='2'>";
			dd_html += "------------------------批量设置------------------------";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='right'>";
			dd_html += "所属<bean:message key="lable.xsgzyxpzxy" />:";
			dd_html += "</td>";
			dd_html += "<td align='left'>";
			dd_html += xyMsg;
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='right'>";
			dd_html += "奖学金（荣誉称号）:";
			dd_html += "</td>";
			dd_html += "<td align='left'>";
			dd_html += jxjMsg;
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='right'>";
			dd_html += "设定比例:";
			dd_html += "</td>";
			dd_html += "<td align='left'>";
			dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tr bgcolor='EEF4F9'>";
			dd_html += "<td colspan='2' align='center'>";
			dd_html += "<button class='button2' onclick=plszSave('gzdxPjpy.do?method=rsblsz')>确定</button>";
			dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			dd_html += "<button class='button2' onclick='closeAdd1()'>关闭</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table>";
			dd_html += "</div>";
			tmpdiv1.innerHTML = dd_html;
}
	</script>
	<body onload="xyDisabled('xy')">
		<html:form action="/gzdxPjpy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" scope="request" />
				</div>
			</div>
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="nd" value="<bean:write name ="nd"/>"/>
			<input type="hidden" id="xq" value="<bean:write name ="xq"/>"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="method" name="method" value="rssz" />
			<fieldset>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" >
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="key" value="jxj" onclick="document.forms[0].go.value = 'a';refreshForm('/xgxt/gzdxPjpy.do?method=rssz')" >奖学金</html:radio>
								&nbsp;&nbsp;
								<html:radio property="key" value="rych" onclick="document.forms[0].go.value = 'a';refreshForm('/xgxt/gzdxPjpy.do?method=rssz')">荣誉称号</html:radio>
								<br/>
								<logic:equal value="jxj" name="pjpyGzdxActionForm" property="key" > 
								奖学金：
								<html:select property="dm" style="width:180px"
										styleId="dm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
								</html:select>
								</logic:equal>
								<logic:equal value="rych" name="pjpyGzdxActionForm" property="key">
								&nbsp;&nbsp;荣誉称号：
								<html:select property="dm" style="width:180px"
										styleId="dm">
										<html:option value=""></html:option>
										<html:options collection="rychList" property="rychdm"
											labelProperty="rychmc" />
								</html:select>
								</logic:equal>
								&nbsp;&nbsp;审核状态：
								<html:select property="xxsh" style="width:90px"
											styleId="xxsh">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
								</html:select>
								
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" id = "go" value="go" />
								<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/gzdxPjpy.do?method=rssz');">
										查询
								</button>
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
						<font color="blue"> 提示：双击一行可以调整人数；单击表头可以排序</font>
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
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('尚未进行批量设置生成建议人数，不能调整人数！');return false;}else{showTopWin('/xgxt/gzdxPjpy.do?method=dgrstz&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,650,450)}">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
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
											page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
				</fieldset>
			</logic:notEmpty>
			<logic:notEqual name="userType" value="xy" scope="session">	
			   <div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
						&nbsp;&nbsp;
						<button class="button2"
						onclick="showTopWin('chg_prise_xn.do',300,200)" style="width:100px">
						调整学年
					    </button>
					    &nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="showTopWin('pjpy_gzdx_cfxzsz.do',400,300)">
							处分限制设置
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="priseDataInitForGzdx()" style="width:100px">
							初始化数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
    					<button class="button2" onclick="chkPriseBatForGzdx()" style="width:100px">
							批量设置
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
    					<button class="button2" id="btn_dc"
								onclick="dataExport()"
								style="width:80px">
								导出数据
							</button>
				</div>
			</logic:notEqual>
			<div id="tmpdiv1"></div>
			<div id="tmpdiv"></div>
			<logic:present name="initOK">
			<logic:equal name="initOK" value = "ok">
				<script language="javascript">
					alert("初始化成功");
				</script>
			</logic:equal>
			<logic:equal name="initOK" value = "no">
				<script language="javascript">
					alert("初始化失败");
				</script>
			</logic:equal>
			</logic:present>
			<logic:present name="updateOK">
			<logic:equal name="updateOK" value = "ok">
				<script language="javascript">
					alert("修改成功");
				</script>
			</logic:equal>
			<logic:equal name="updateOK" value = "no">
				<script language="javascript">
					alert("修改失败");
				</script>
			</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -85;
}
if($("btn")){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
	</body>
</html>
