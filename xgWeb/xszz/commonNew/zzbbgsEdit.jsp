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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/BatAjax.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
var columns = null;

function initFList(data){
	if(data == null) return false;
	columns = data;
	var fuc = [
		function(data){return data.mc;},
		function(data){
			var r = document.createElement("<a></a>");
//			r.style.cssText = "border:0px; height:15px";
			r.href = "javascript:addzdSet('" + data.dm + "','"+data.mc+"')";
			r.innerText = "添加";
			return r;
		}
	];
	BatAjax.removeAllRows("rsT");
	BatAjax.addRows("rsT",data,fuc);
}

function getXmdm(){
	
}

function addzdSet(data1,data2){
	var dm = data1;
	var win=window.eWebEditor1.eWebEditor.document;//其中编辑区域是BatEditor中的一个iframe，id是eWebEditor
	window.eWebEditor1.eWebEditor.focus();//是编辑器获得焦点，防止代码插入在编辑器外地方
	win.selection.createRange().pasteHTML(dm)//在光标的位置插入html代码	
}

function saveEditor(){
	$("content1").value= window.eWebEditor1.getHTML();
	document.forms[0].action = "/xgxt/new_common_xszz.do?method=zzbbgsEdit&act=save";
	document.forms[0].submit();
}
	</script>
</head>

<body onload="GetListData.getColumnList('123',initFList);">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 资助报表格式维护
		</div>
	</div>
	<html:form action="shgc_kns.do" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="8%">
					资助名称:
				</td>
				<td>
					<bean:write name='rs' property="xmmc" />
					<input type="hidden" name="xmdm" id="xmdm"
						value="<bean:write name='rs' property='xmdm' />" />
				</td>
				<td align="center">
					字&nbsp;&nbsp;段&nbsp;&nbsp;设&nbsp;&nbsp;置
				</td>
			</tr>
			<tr>
				<td align="center">
					格&nbsp;&nbsp;&nbsp;&nbsp;式:
				</td>
				<td width="66%">
					<input type="hidden" name="content1" id="content1"
						value="<bean:write name='rs' property='bbgs' />"></input>
					<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
						scrolling="no" width="100%" height="350"></iframe>
				</td>
				<td>
					<div style="height:350px; overflow:scroll">
						<table width="100%">
							<tbody id="rsT"></tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="saveEditor();">
				保&nbsp;&nbsp;&nbsp;&nbsp;存
			</button>
			<button class="button2"
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				关&nbsp;&nbsp;&nbsp;&nbsp;闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
