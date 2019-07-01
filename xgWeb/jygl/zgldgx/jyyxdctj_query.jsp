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
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	function querygo(){
		 var zymc = document.getElementById("zydm").value; 
	      var bynd = document.getElementById("bynd").value;
	      if(zymc=="--请选择--"||zymc==""){
	      alert("请选择专业名称！");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(bynd=="--请选择--"||bynd==""){
		      alert("请选择毕业年度！");
		      document.getElementById("bynd").focus();
		      return false;
		      }
		 	document.forms[0].action = "/xgxt/jyyxdcb.do?act=query&doType=query&tj=tj";
		 	document.forms[0].submit();
    }
	function expTabdy(the_table, tabTit, titSpan) {
		var zydm = document.getElementById("zydm").value;
		var bynd = document.getElementById("bynd").value;
		if(zydm == "" && bynd == ""){
			alert("请选择专业代码和毕业年度后进行打印");
			return false;
		}
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
	function initZyList(){
		var xydm = "";
		var query = "";
		var userName = "";
		var isFdy = "false";
		if($("isFdy")){isFdy = $("isFdy").value};
		if($("xy")){xydm = $("xy").value};	
		if($("userName")){userName = $("userName").value};
			if(xydm == null || xydm == ""){
				xydm = "%";
			} else{
				xydm = "%" + xydm +"%";
			}
			query = xydm;	
			if($("isBzr")){isBzr = $("isBzr").value};
			GetListData.getZyList(query,userName,isFdy,isBzr,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId){
						if($(objId).value != "" && $(objId).value != null){
							alert($(objId).tagName);
							for(var i = 0;i < $(objId).options.length; i++){
								if($(objId).options[i].value == $(objId).value){
									$(objId).options[i].selected = true;
									return true;
								}
							}
						}
						}
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			});
	}
	function prtlt(){
		if(!$('tjtitle')){
			alert("查询未找到数据，请查询出数据后进行打印");
			return false;
		}
		 var zymc = document.getElementById("zydm").value; 
	      var bynd = document.getElementById("bynd").value;
	      if(zymc=="--请选择--"||zymc==""){
	      alert("请选择专业名称！");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(bynd=="--请选择--"||bynd==""){
		      alert("请选择毕业年度！");
		      document.getElementById("bynd").focus();
		      return false;
		      }
		var prtname = $('tjtitle').innerText;
		expTabdy('rsTable',prtname,'');
	}
	function expjyyxData(){
	      var zymc = document.getElementById("zydm").value; 
	      var bynd = document.getElementById("bynd").value;
	      if(zymc=="--请选择--"||zymc==""){
	      alert("请选择专业名称！");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(bynd=="--请选择--"||bynd==""){
		      alert("请选择毕业年度！");
		      document.getElementById("bynd").focus();
		      return false;
		      }
		   document.forms[0].action = "/xgxt/jyyxdcb.do?act=expjyyx";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
	}
	function changetext(){
		document.getElementById("zymc").value=$('zydm').options[$('zydm').selectedIndex].text;
		
	}
		</script>
	<body>
		<html:form action="/jyyxdcb.do" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" name="pkstring" value="" />
			<html:hidden name="rs1" property="zymc"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业协议方案 - 就业意向调查统计
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
							<logic:equal value="xy" name="userType" scope="session">
								<bean:message key="lable.xsgzyxpzxy" />：
								<input id="xydmdt" name="xydmdt" value="${xydmdt }" disabled="true"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<html:select name="rs1" property="zydm" onchange="" style="width:170px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:equal>
								
								
									<logic:equal value="stu" name="userType" scope="session">
								<bean:message key="lable.xsgzyxpzxy" />：
								<input id="xydmdt" name="xydmdt" value="${xydmdt}" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<input id="zydmdt" name="zydmdt" value="${zydmdt }" disabled="disabled"/>
								<html:select name="rs1" property="zydm" onchange="" style="width:170px;display: none" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
								<logic:notEqual value="xy" name="userType" scope="session">
									<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<html:select name="rs1" property="zydm" onchange="" style="width:170px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:notEqual>
								</logic:notEqual>
								<div style="display: none">
								<logic:equal name="who" value="xy">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:text name="rs1" property="xymc" style="width:150px"
										readonly="true" />
										
								&nbsp;&nbsp;&nbsp;
								</logic:equal>
								</div>
								
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()"
									id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								毕业年度：
								<html:select name="rs1" property="bynd" style="width:120px">
									<html:option value=""></html:option>
									<html:option value="2006">
										2006
									</html:option>
									<html:option value="2007">
										2007
									</html:option>
									<html:option value="2008">
										2008
									</html:option>
									<html:option value="2009">
										2009
									</html:option>
									<html:option value="2010">
										2010
									</html:option>
									<html:option value="2011">
										2011
									</html:option>
									<html:option value="2012">
										2012
									</html:option>
									<html:option value="2013">
										2013
									</html:option>
									<html:option value="2014">
										2014
									</html:option>
									<html:option value="2015">
										2015
									</html:option>
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsmap">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rsmap">
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="tjtitle"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="3">
										<bean:write name="toptr" property="xh" />
									</td>
									<td rowspan="3">
										<bean:write name="toptr" property="xm" />
									</td>
									<td rowspan="3">
										<bean:write name="toptr" property="xb1" />
									</td>
									<td colspan="9">
										<bean:write name="toptr" property="jyyx" />
									</td>
									<td rowspan="3">
										<bean:write name="toptr" property="zgzs" />
									</td>
								</tr>
								<tr align="center" style="cursor:hand">
									<td rowspan="2">
										<bean:write name="toptr" property="ky" />
									</td>
									<td colspan="3">
										<bean:write name="toptr" property="bkgwy" />
									</td>
									<td rowspan="2">
										<bean:write name="toptr" property="szyf" />
									</td>
									<td rowspan="2">
										<bean:write name="toptr" property="xbjh" />
									</td>
									<td colspan="2">
										<bean:write name="toptr" property="zgz" />
									</td>
									<td rowspan="2">
										<bean:write name="toptr" property="zzcy" />
									</td>
								</tr>
								<tr align="center" style="cursor:hand">
									<td rowspan="1">
										<bean:write name="toptr" property="gjj" />
									</td>
									<td rowspan="1">
										<bean:write name="toptr" property="bjs" />
									</td>
									<td rowspan="1">
										<bean:write name="toptr" property="dfss" />
									</td>
									<td rowspan="1">
										<bean:write name="toptr" property="wd" />
									</td>
									<td rowspan="1">
										<bean:write name="toptr" property="bj" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="s" name="rsmap">
								<tr style="cursor:hand">
										<td><font style="font: bolder;"><bean:write name="s" property="xh"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xm"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xb1"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="ky"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="gjj"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="bjs"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="dfss"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="szyf"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="xbjh"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="wd"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="bj"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="zzcy"/></font></td>
										<td><font style="font: bolder;"><bean:write name="s" property="zgzs"/></font></td>
								</tr>
							</logic:iterate>
							<tr style="cursor:hand">
										<td colspan="3" align="center"><font style="font: bolder;color :red">合计</font></td>
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="ky"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="gjj"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="bjs"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="dfss"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="szyf"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="xbjh"/></font></td>
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="wd"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="bj"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="zzcy"/></font></td>	
										<td><font style="font: bolder;color :red"><bean:write name="hjrs" property="zgzs"/></font></td>			
							</tr>
							
					</table>
				</fieldset>
				<script language="javascript">
					$('tjtitle').innerText = $('bynd').options[$('bynd').selectedIndex].text+'届'+$('zydm').options[$('zydm').selectedIndex].text+'专业毕业生就业意向调查表';
				</script>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				<button class="button2"
						onclick="expjyyxData();"
						style="width:80px">
						导出数据
				</button>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				 <button class="button2" onclick="prtlt();" style="width: 100px">
				打印列表
 				 </button>
			</div>

			
			
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
