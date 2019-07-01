<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
    function querygo(type){
       var xydm = document.getElementById("xydm").value;
       var nd  = document.getElementById("nd").value;
       var zydm  = document.getElementById("zydm").value;
       if(xydm==""){
       	 	alert("<bean:message key="lable.xsgzyxpzxy" />代码不能为空！");
       		return false;
       }
       if(zydm==""){
      	 	alert("专业代码不能为空！");
      		return false;
      }
       if(nd==""){
       	 	alert("毕业年度不能为空！");
       		return false;
       }
       if(type == 'tj'){
       		document.forms[0].action = "/xgxt/bysjcxxtj.do?type="+type;
			document.forms[0].submit();
	   }else{
			window.open('/xgxt/bysjcxxtj.do?tjxm=tjxm&nd='+nd+'&xydm='+xydm+'&zydm='+zydm);
	   }
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
				xydm = "and '%'";;
			} else{
				xydm = "and xydm='" + xydm +"'";
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
		var prtname = $('tjtitle').innerText;
		expTabdy('rsTable','','');
	}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/bysjcxxtj" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 统计分析 - 毕业生基础信息
				</div>
			</div>
			<fieldset>
				<legend>
					统计查询
				</legend>
				<div>
					<br>
					&nbsp;&nbsp;系（院）：
					<html:select property="xydm" onchange="initZyList();" style="width:260px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
					</html:select>
					&nbsp;&nbsp;毕业年度：
					<html:select property="nd" style="width:80px" styleId="nd">
						<html:options collection="ndList" labelProperty="nd" property="nd"/>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="querygo('tj');" style="width:80px">
						统 计
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
					&nbsp;&nbsp;专&nbsp;&nbsp;&nbsp;&nbsp;业：
					&nbsp;&nbsp;<html:select property="zydm" onchange="" style="width:260px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
				    </html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--					<button class="button2" onclick="querygo('dc');"--%>
<%--						style="width:80px">--%>
<%--						导 出--%>
<%--					</button>--%>
					<br>
					&nbsp;
				</div>
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
				<table width="100%" id="rsTable" class="tbstyle">
					<legend align="center">
						<font color="blue">
							<span id="tjtitle"></span>
						</font>
					</legend>
				
								
										系（院）：
								
										<span id="xytj"></span>
								
										专业：
								
										<span id="zytj"></span>
								
					
							<thead>
							
								<tr align="center" style="cursor:hand">
									<td>
										<bean:write name="tabtop" property="shzh" />
									</td>
									<td>
										<bean:write name="tabtop" property="xh" />
									</td>
									<td>
										<bean:write name="tabtop" property="xm" />
									</td>
									<td>
										<bean:write name="tabtop" property="xb" />
									</td>
									<td>
										<bean:write name="tabtop" property="mzmc" />
									</td>
									<td>
										<bean:write name="tabtop" property="zzmm" />
									</td>
									<td>
										<bean:write name="tabtop" property="csrq" />
									</td>
									<td>
										<bean:write name="tabtop" property="xz" />
									</td>
									<td>
										<bean:write name="tabtop" property="xl" />
									</td>
									<td>
										<bean:write name="tabtop" property="syszd" />
									</td>
									<td>
										<bean:write name="tabtop" property="pyfs" />
									</td>
									<td>
										<bean:write name="tabtop" property="sjhm" />
									</td>
									<td>
										<bean:write name="tabtop" property="sfpks" />
									</td>
								</tr>
							</thead>
							<logic:iterate id="rs" name="rs">
								<tr>
									<td>
										<bean:write name="rs" property="sfzh" />
									</td>
									<td>
										<bean:write name="rs" property="xh" />
									</td>
									<td>
										<bean:write name="rs" property="xm" />
									</td>
									<td>
										<bean:write name="rs" property="xb" />
									</td>
									<td>
										<bean:write name="rs" property="mz" />
									</td>
									<td>
										<bean:write name="rs" property="zzmm" />
									</td>
									<td>
										<bean:write name="rs" property="csrq" />
									</td>
									<td>
										<bean:write name="rs" property="xz" />
									</td>
									<td>
										<bean:write name="rs" property="xldm" />
									</td>
									<td>
										<bean:write name="rs" property="syd" />
									</td>
									<td>
										<bean:write name="rs" property="pyfs" />
									</td>
									<td>
										<bean:write name="rs" property="sjhm" />
									</td>
									<td>
										<bean:write name="rs" property="sfdk" />
									</td>
								</tr>
							</logic:iterate>
					</table>
				</fieldset>
				<script language="javascript">
					$('tjtitle').innerText = $('nd').options[$('nd').selectedIndex].text+'届'+$('zydm').options[$('zydm').selectedIndex].text+"专业";
					$('xytj').innerText = $('xydm').options[$('xydm').selectedIndex].text;
					$('zytj').innerText = $('zydm').options[$('zydm').selectedIndex].text;
				</script>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				  &nbsp;&nbsp;&nbsp;&nbsp;
				 <button class="button2" onclick="prtlt();" style="width: 100px">
				打印列表
 				 </button>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>