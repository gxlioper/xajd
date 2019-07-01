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
		function SelectrwOption(a,b){
   			document.forms[0].tag.value=a;
   			document.forms[0].action =b;
   			document.forms[0].submit();
		}
		function thoughtLoad(defaultid){
		   if(document.forms[0].tname.value != ""){
			  document.getElementById(document.forms[0].tname.value+"l").className = "xxk_on_l";
			  document.getElementById(document.forms[0].tname.value).className = "xxk_on_m";
			  document.getElementById(document.forms[0].tname.value+"r").className = "xxk_on_r";
		   }else{
			  document.getElementById(defaultid+"l").className = "xxk_on_l";
			  document.getElementById(defaultid).className = "xxk_on_m";
			  document.getElementById(defaultid+"r").className = "xxk_on_r";
			  document.forms[0].tname.value = defaultid;
		   }
		}
	</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<body onload="thoughtLoad('zxszy_sjd')">
		<script language="javascript" src="js/function.js"></script>
		 <html:form action="/xljk_zxzx_dmwh" method="post">
		 <html:hidden property="xh"/>
	 	 <input type="hidden" name="dm" value="<bean:write name="map" property="xh"/>">
	 	 <html:hidden property="tag" styleId="tname"/> 
	  	 <html:hidden   property="xmbh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：系统维护 - 代码维护 -<span id="expTit">
					<bean:write name="tips" scope="request" /></span>
				</div>
			</div>
						<ul>
							<li id="zxszy_sjdl"class="xxk_off_l"></li>
							<li id="zxszy_sjd" onclick="SelectrwOption('zxszy_sjd','')" class="xxk_off_m">&nbsp;时间段&nbsp;</li>
							<li id="zxszy_sjdlr"class="xxk_off_r"></li>
						</ul>
						<ul>
							<li id="zxszy_ddl" class="xxk_off_l"></li>
							<li id="zxszy_dd" onclick="SelectrwOption('zxszy_dd','')" class="xxk_off_m">&nbsp;地点&nbsp;</li>
							<li id="zxszy_ddr" class="xxk_off_r"></li>
						</ul>
			<fieldset>
				<legend>
					基础代码维护
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<logic:iterate id="v" name="s">
									<td><bean:write name="v" /></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
				<div id="tmpdiv"></div>
				<div class="buttontool" id="btn" style="position: absolute;left:0px;top:100px" width="100%">
					<button type="button" class="button2" onclick="codeConf('add')">
						&nbsp;&nbsp;增&nbsp;&nbsp;加&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="codeConf('del')">
						&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="codeConf('modi')">
						&nbsp;&nbsp;修&nbsp;&nbsp;改&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();" style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expTab('rsTable','','expTit')" style="width:80px">
						打印列表
					</button>
				</div>
			</fieldset>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
