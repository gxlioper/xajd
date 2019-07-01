<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	
	<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRs.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRstg.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>

	<script type="text/javascript">
	function expTabdy(the_table, tabTit, titSpan) {
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
		expTabdy('rsTable','','');
	}
	function dataExportjxj() {
		document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjhzPrint";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	function jxjffcx(){
		var cpzdm = document.getElementById("cpzdm").value;
		var jxjdm = document.getElementById("jxjdm").value;
		//if(cpzdm!=""){
		//	if(jxjdm == ""){
		//		alert("请选择奖学金！！");
		//		return false;
		//	}
		//}
		allNotEmpThenGo('/xgxt/zjlgPjpy.do?method=jxjffhz&go=go');
	}
	</script>
	</head>
	<body >
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="cpzV" value=""/>
		<html:form action="/zjlgPjpy" method="post">

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="tableName" name="tableName" value="" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			
			<logic:notEqual value="stu" name="userType" scope="session">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_dy" onclick="expTabdy('rsTable','<bean:message key="lable.xsgzyxpzxy" />奖学金审核结果名单',null,null,null,null)">打印/预览</a></li>
						<li><a href="#" class="btn_dc"onclick="dataExportjxj()">数据导出</a></li>								
					</ul>
				</div>
				</div>
			</logic:notEqual>
					
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<thead>
						<tr>
							<th>学年</th>
							<td><html:select name="xnmap" property="xn" style="width:90px;display:none" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<input id="xnxdt" name="xnxdt" value="<bean:write name="xnmap" property="xn"/>" disabled="disabled"/>
							</td>
							<th>年级</th>
							<td><html:select property="nj" onchange="initBjList()" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
							<th>奖学金</th>
							<td><html:select property="jxjdm" style="width:200px" styleId="xmdm">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</td>
							</tr>
							<tr>
							<html:radio property="yesNo" value="通过" style="display: none"></html:radio>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text></td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>
							</td>
							<th>参评组</th>
							<td><html:select property="cpzdm" style="width:150px" styleId="cpz"
								onchange="initjxjcpzBjList();">
								<html:option value=""></html:option>
								<html:options collection="cpzList" property="cpzdm"
									labelProperty="cpzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList();initCpzList();"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</td>
							<th>专业</th>
							<td><html:select property="zydm" onchange="initBjList();" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td><html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>								
							</td>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go" onclick="jxjffcx();">
									查询
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重置
								 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;记录数：${rsNum};<font color="blue">浅绿色表示通过，无色表示未审核或审核不通过</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" scope="request">
									<td id="${tit.en}"
										onclick="tableSort(this)" nowrap>
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="1" length="1">
							    	<bean:write name="v"/>
							    </logic:iterate>
							    ">
								<logic:iterate id="v" name="s" offset="0">
									<td align=center>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
	</body>
</html>
