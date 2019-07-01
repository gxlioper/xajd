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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gbk">
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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	function querygo() {
		var byyf = document.getElementById("byyf").value;
		document.forms[0].action = "/xgxt/bysjyjzqk.do?act=query&doType=query&tj=tj&byyf="+byyf;
		document.forms[0].submit();
	}
	function expTabdy(the_table, tabTit, titSpan) {
		/*var HKEY_Root="HKEY_CURRENT_USER";
		var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		var Wsh=new ActiveXObject("WScript.Shell");
		var HKEY_Key="header";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		    HKEY_Key="footer";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */
		var table_title = (titSpan == null || titSpan == "") ? tabTit
				: document.getElementById(titSpan).outerHTML;
		var the_content = "<style media='print'>\n";
		the_content += ".noPrin{\n";
		the_content += "display:none;}\n";
		the_content += "</style>\n";
		the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
		the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
		the_content += "<center><h3><b>";
		the_content += table_title;
		the_content += "</b></h3>";
		the_content += document.all(the_table).outerHTML;
		the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
		the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
		the_content = the_content.replace(
				/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
		the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
		the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
		the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
		the_content += "<\/div>";
		//confirm(the_content);
		var newwin = window.open("about:blank", "_blank", "");
		newwin.document.open();
		newwin.document.write(the_content);
		newwin.document.close();
		newwin = null;
	}
	function initZyList() {
		var xydm = "";
		var query = "";
		var userName = "";
		var isFdy = "false";
		if ($("isFdy")) {
			isFdy = $("isFdy").value
		}
		;
		if ($("xy")) {
			xydm = $("xy").value
		}
		;
		if ($("userName")) {
			userName = $("userName").value
		}
		;
		if (xydm == null || xydm == "") {
			xydm = "%";
		} else {
			xydm = "%" + xydm + "%";
		}
		query = xydm;
		GetListData
				.getZyList(
						query,
						userName,
						isFdy,
						function initTjList(data) {
							if (data != null && typeof data == 'object') {
								var objId = data[0].dm;
								if ($(objId)
										&& $(objId).tagName.toLowerCase() == "select") {
									DWRUtil.removeAllOptions(objId);
									DWRUtil.addOptions(objId, data, "dm", "mc");
									$(objId).options[0].value = "";
									if (objId) {
										if ($(objId).value != ""
												&& $(objId).value != null) {
											alert($(objId).tagName);
											for ( var i = 0; i < $(objId).options.length; i++) {
												if ($(objId).options[i].value == $(objId).value) {
													$(objId).options[i].selected = true;
													return true;
												}
											}
										}
									}
								}
							} else {
								showMsgWin("有错误出现：远程数据读取失败！");
							}
						});
	}
	function expTabpt(the_table, tabTit, titSpan) {
		/*var HKEY_Root="HKEY_CURRENT_USER";
		var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		var Wsh=new ActiveXObject("WScript.Shell");
		var HKEY_Key="header";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		    HKEY_Key="footer";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */  
		var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
		var the_content = "<style media='print'>\n";
		the_content += ".noPrin{\n";
		the_content += "display:none;}\n";
		the_content += "</style>\n";
		the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
		the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
		the_content += "<center><h3><b>";
		the_content += table_title;
		the_content += "</b></h3>";		
		the_content += document.all(the_table).outerHTML;			
		the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
		the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
		the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
		the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
		the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
		the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
		the_content += "<\/div>";
		//confirm(the_content);
		var newwin = window.open("about:blank", "_blank", "");
		newwin.document.open();
		newwin.document.write(the_content);
		newwin.document.close();
		newwin = null;
	}
	function prtlt(){
		if(!$('tjtitle')){
			alert("查询未找到数据，请查询出数据后进行打印");
			return false;
		}
		 var bynf = document.getElementById("bynf").value;
	      var byyf = document.getElementById("byyf").value;
	      var xydm = document.getElementById("xymc").value;
	      if(bynf=="--请选择--"||bynf==""){
	      alert("请选择年份打印！");
	      return false;
	      }
	      if(byyf=="--请选择--"||byyf==""){
		      alert("请选择月份打印！");
		      return false;
		      }
	      if(xydm=="--请选择--"||xydm==""){
		      alert("请选择<bean:message key="lable.xsgzyxpzxy" />打印！");
		      return false;
		      }
		var prtname = $('tjtitle').innerText;
		expTabpt('rsTable',prtname,'');
	}
	function expjyyxData() {
		var zymc = document.getElementById("zydm").value;
		var bynd = document.getElementById("bynd").value;
		if (zymc == "--请选择--" || zymc == "") {
			alert("请选择专业名称！");
			document.getElementById("zydm").focus();
			return false;
		}
		if (bynd == "--请选择--" || bynd == "") {
			alert("请选择毕业年度！");
			document.getElementById("bynd").focus();
			return false;
		}
		document.forms[0].action = "/xgxt/jyyxdcb.do?act=expjyyx";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	function changetext() {
		document.getElementById("zymc").value = $('zydm').options[$('zydm').selectedIndex].text;

	}
</script>
	<body>
		<html:form action="/jyyxdcb.do" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" name="pkstring" value="" />
			<html:hidden name="rs1" property="zymc" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业协议方案 - 毕业生就业进展情况统计
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor: hand">
							<td>
							年份：
								<html:select name="rs1" property="bynf">
								<html:option value=""></html:option>
									<html:option value="2004"></html:option>
									<html:option value="2005"></html:option>
									<html:option value="2005"></html:option>
									<html:option value="2006"></html:option>
									<html:option value="2007"></html:option>
									<html:option value="2008"></html:option>
									<html:option value="2009"></html:option>
									<html:option value="2010"></html:option>
									<html:option value="2011"></html:option>
									<html:option value="2012"></html:option>
									<html:option value="2013"></html:option>
									<html:option value="2014"></html:option>
								</html:select>
								月份：
								<html:select property="byyf">
									<html:option value=""></html:option>
									<html:option value="01" ></html:option>
									<html:option value="02"></html:option>
									<html:option value="03"></html:option>
									<html:option value="04"></html:option>
									<html:option value="05"></html:option>
									<html:option value="06"></html:option>
									<html:option value="07"></html:option>
									<html:option value="08"></html:option>
									<html:option value="09"></html:option>
									<html:option value="10"></html:option>
									<html:option value="11"></html:option>
									<html:option value="12"></html:option>
								</html:select>
								&nbsp;&nbsp;系（院）：
					<html:select name="rs1" property="xymc" onchange="initZyList();" style="width:260px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xymc"
										labelProperty="xymc" />
					</html:select>
								<div style="display: none">
								</div>
								
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height: 40px"
									onclick="querygo()" id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
							
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
				<fieldset>
					<legend align="center">
						<font color="blue"> <span id="tjtitle"></span> </font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
					<logic:iterate id="rsmap" name="rsmap">
						<tr>
							<td rowspan="4" align="center" width="30">
								<b>毕<br>业<br>生<br>总<br>数</b>
							</td>
							<td width="180" rowspan="4">
								<bean:write name="rsmap" property="byszs"/>
							</td>
							<td align="center" rowspan="2" style="width: 85px">
								北京生源
							</td>
							<td width="180" rowspan="2">
								<bean:write name="rsmap" property="byszsbj"/>
							</td>
							<td align="center" rowspan="2" width="70">
								本科生
							</td>
							<td width="150" rowspan="2">
								<bean:write name="rsmap" property="byszsbks"/>
							</td>
							<td align="center" style="width: 85px">
								北京生源
							</td>
							<td width="180">
								<bean:write name="rsmap" property="byszsbkbj"/>
							</td>
						</tr>
						<tr>
							<td align="center" >
								京外生源
							</td>
							<td width="150">
								<bean:write name="rsmap" property="byszsbkjw"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="2" style="width: 85px">
								京外生源
							</td>
							<td align="center" width="180" rowspan="2">
								<bean:write name="rsmap" property="byszsjw"/>
							</td>
							<td align="center" rowspan="2" width="70">
								专科（高职）
							</td>
							<td width="150" rowspan="2">
								<bean:write name="rsmap" property="byszszks"/>
							</td>
							<td align="center" style="width: 85px">
								北京生源
							</td>
							<td width="180">
								<bean:write name="rsmap" property="byszszkbj"/>
							</td>
						</tr>
						<tr>
							<td align="center" >
								京外生源
							</td>
							<td width="150">
								<bean:write name="rsmap" property="byszszkjw"/>
							</td>
						</tr>
						<tr>
							<td rowspan="4" align="center" width="30">
								<b>毕<br>业<br>生<br>签<br>约<br>数</b>
							</td>
							<td width="180" rowspan="4">
								<bean:write name="rsmap" property="bysqys1"/>
							</td>
							<td align="center" rowspan="2" style="width: 85px">
								本科生
							</td>
							<td width="180" rowspan="2" colspan="2">
								<bean:write name="rsmap" property="bysqysbks"/>
							</td>
							<td align="center" style="width: 85px">
								北京生源
							</td>
							<td width="180" colspan="2">
								<bean:write name="rsmap" property="bysqysbksbj"/>
							</td>
						</tr>
						<tr>
							<td align="center" width="70">
								京外生源
							</td>
							<td width="150" colspan="2">
								<bean:write name="rsmap" property="bysqysbksjw"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="2" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="2" colspan="2">
								<bean:write name="rsmap" property="bysqyszks"/>
							</td>
							<td align="center" style="width: 85px">
								北京生源
							</td>
							<td width="180" colspan="2">
								<bean:write name="rsmap" property="bysqyszksbj"/>
							</td>
						</tr>
						<tr>
							<td align="center" width="70">
								京外生源
							</td>
							<td width="150" colspan="2">
								<bean:write name="rsmap" property="bysqyszksjw"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" width="30">
								<b>毕<br>业<br>生<br>签<br>约<br>率</b>
							</td>
							<td width="180" rowspan="2">
							<logic:greaterThan name="rsmap" property="bysqyl" value="100">
							100%
							</logic:greaterThan>
							<logic:lessEqual name="rsmap" property="bysqyl" value="100">
								<bean:write name="rsmap" property="bysqyl"/>%
								</logic:lessEqual>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								本科生
							</td>
							<td width="180" rowspan="1" colspan="2">
							<logic:greaterThan name="rsmap" property="bysqylbks" value="100">
							100%
							</logic:greaterThan>
							<logic:lessEqual name="rsmap" property="bysqylbks" value="100">
								<bean:write name="rsmap" property="bysqylbks"/>%
								</logic:lessEqual>
							</td>
							<td align="center" rowspan="2" style="width: 85px">
								去年同期签约率
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								本科生
							</td>
							<td width="180" rowspan="1" colspan="1">
							<logic:greaterThan name="rsmap" property="bysqylqnbks" value="100">
							100%
							</logic:greaterThan>
							<logic:lessEqual name="rsmap" property="bysqylqnbks" value="100">
								<bean:write name="rsmap" property="bysqylqnbks"/>%
								</logic:lessEqual>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="1" colspan="2">
							<logic:greaterThan name="rsmap" property="bysqylzks" value="100">
							100%
							</logic:greaterThan>
							<logic:lessEqual name="rsmap" property="bysqylzks" value="100">
								<bean:write name="rsmap" property="bysqylzks"/>%
								</logic:lessEqual>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="1" colspan="1">
							<logic:greaterThan name="rsmap" property="bysqylqnzks" value="100">
							100%
							</logic:greaterThan>
							<logic:lessEqual name="rsmap" property="bysqylqnzks" value="100">
								<bean:write name="rsmap" property="bysqylqnzks"/>%
								</logic:lessEqual>
							</td>
						</tr>
						<tr>
							<td rowspan="1" align="center" colspan="3">
								<b>家庭经济困难毕业生人数</b>
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="jtjjknbysrs"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								签约人数
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="jtjjknbysqyrs"/>
							</td>
						</tr>
						<tr>
							<td rowspan="1" align="center" colspan="3">
								<b>地震灾区 毕业生人数</b>
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="jtjjknbysrs"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								签约人数
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="jtjjknbysqyrs"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" colspan="3">
								<b>有签约意向毕业生人数</b>
							</td>
							<td width="180" rowspan="2" colspan="2">
								<bean:write name="rsmap" property="yqyyybysrs"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								本科生
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="yqyyybysbksrs"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="yqyyybyszksrs"/>
							</td>
						</tr>
						<tr>
							<td rowspan="8" align="center" width="30">
								<b>毕<br>业<br>生<br>签<br>约<br>数</b>
							</td>
							<td width="180" rowspan="8">
								<bean:write name="rsmap" property="bysqys"/>
							</td>
							<td align="center" rowspan="4" style="width: 85px">
								本科生
							</td>
							<td width="180" rowspan="4" colspan="2">
								<bean:write name="rsmap" property="bysqysbks"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								已升学
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqysbksysx"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								事业单位
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqysbkssydw"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								企业
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqysbksqydw"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								其他
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqysbksqt"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="4" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="4" colspan="2">
								<bean:write name="rsmap" property="bysqyszks"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								已升学
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqyszksysx"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								事业单位
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqyszkssydw"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								企业
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqyszksqy"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								其他
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="bysqyszksqt"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" width="30">
								<b>岗<br>位<br>需<br>求<br>人<br>数<br>数<br>合<br>计</b>
							</td>
							<td width="180" rowspan="2">
								<bean:write name="rsmap" property="gwxqshj"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								本科生
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								岗位数
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="gwxqshjbks"/>
							</td>
							<td align="center" rowspan="2" style="width: 85px">
								去年同期<br>需求数
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								岗位数
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="gwxqshjbksqntq"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								专科（高职）
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								岗位数
							</td>
							<td width="180" rowspan="1" colspan="1">
							<bean:write name="rsmap" property="gwxqshjzksqntq"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								专科（高职）
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="gwxqshjzksqntq"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" colspan="2">
								<b>本系（院）<br>举行大型招<br>聘会次数</b>
							</td>
							<td width="180" rowspan="2">
								<bean:write name="rsmap" property="dxzphcs"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								单位数量
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="dxzphdwsl"/>
							</td>
							<td align="center" rowspan="4" style="width: 85px">
								去年同期<br>相比
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="zphxjxb1"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								岗位数量
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="dxzphgwsl"/>
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="zphxjxb2"/>
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" colspan="2">
								<b>本系（院）<br>举办用人<br>单位宣讲次数</b>
							</td>
							<td width="180" rowspan="2">
								<bean:write name="rsmap" property="dwxjhcs"/>
							</td>
							<td align="center" rowspan="1" style="width: 85px">
								单位数量
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="dwsjhdwsl"/>
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="zphxjxb3"/>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="1" style="width: 85px">
								岗位数量
							</td>
							<td width="180" rowspan="1" colspan="1">
								<bean:write name="rsmap" property="dwxjhgwsl"/>
							</td>
							<td width="180" rowspan="1" colspan="2">
								<bean:write name="rsmap" property="zphxjxb4"/>
							</td>
						</tr>
						<tr>
							<td rowspan="4" align="center" colspan="2">
								<b>就业困<br>难的主<br>要专业</b>
							</td>
							<td align="center" rowspan="1" colspan="2" style="width: 85px">
								本科生
							</td>
							<td align="center" rowspan="1" colspan="4" style="width: 85px">
								高职（专科）生
							</td>
						</tr>
						<tr>
						<td width="180" rowspan="1" colspan="2">
						<logic:iterate id="hjrs1" name="hjrs1" offset="0" length="1">
								<bean:write name="hjrs1" property="zymc"/>
							</logic:iterate>
							</td>
								<td width="180" rowspan="1" colspan="4" >
							<logic:iterate id="hjrs4" name="hjrs4" offset="0" length="1">
									<bean:write name="hjrs4" property="zymc"/>
							</logic:iterate>
							</td>
						</tr>
						<tr>
						<td width="180" rowspan="1" colspan="2">
						<logic:iterate id="hjrs2" name="hjrs2" offset="1" length="1">
								<bean:write name="hjrs2" property="zymc"/>
							</logic:iterate>
								</td>
							<td width="180" rowspan="1" colspan="4" >
							<logic:iterate id="hjrs5" name="hjrs5" offset="1" length="1">
									<bean:write name="hjrs5" property="zymc"/>
							</logic:iterate>
							</td>
						</tr>
						<tr>
						<td width="180" rowspan="1" colspan="2">
						<logic:iterate id="hjrs3" name="hjrs3" offset="2" length="1">
								<bean:write name="hjrs3" property="zymc"/>
							</logic:iterate>
								</td>
									<td width="180" rowspan="1" colspan="4" >
							<logic:iterate id="hjrs6" name="hjrs6" offset="2" length="1">
									<bean:write name="hjrs6" property="zymc"/>
							</logic:iterate>
							</td>
						</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<script language="javascript">
					$('tjtitle').innerText = $('bynf').options[$('bynf').selectedIndex].text+'年'+$('byyf').options[$('byyf').selectedIndex].text+'月'+$('xymc').options[$('xymc').selectedIndex].text+'毕业生就业进展情况统计';
				</script>



			<div class="buttontool" id="btn"
				style="position: absolute; left: 0px; top: 100px" width="98%">
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="prtlt();" style="width: 100px">
					打印列表
				</button>
			</div>



		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
