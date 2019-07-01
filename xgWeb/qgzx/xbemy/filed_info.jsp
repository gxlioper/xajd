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


	<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function commitInfo(url){
			var xmdm = document.getElementById('xmdm').value;
			var zddm = document.getElementById('zddm').value;
			var zddx = document.getElementById('zddx').value;
			var act = document.getElementById('act').value;
			
			if((xmdm == null) || (xmdm == "")){
				alert("请选择定义项目!");
				return false;
			}
			if((zddm == null) || (zddm == "")){
				alert("字段代码不能为空!");
				return false;
			}
			if((zddx == null) || (zddx == "")){
				alert("字段大小不能为空!");
				return false;
			}
			if(Math.round(zddx) >= 4000){
				alert("字段大小不能超过4000个字符!");
				return false;
			}
			if(act!=null && act=="add"){		
				if(window.dialogArguments.rsTable!=null){
					for (i = 1; i < window.dialogArguments.rsTable.rows.length; i++) {
					if (replaceChar(zddm, " ", "") == replaceChar(window.dialogArguments.rsTable.rows[i].cells[2].innerText, " ", "")) {
						alert("字段代码已经存在！");
						return false;
					}
				}
			}
			}
			document.getElementById('btu_zj').disabled="true";
			refreshForm(url);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：勤工助学 - 参数设置 - 自定义维护 - 字段维护
		</div>
	</div>
	<html:form action="xbemyQgzx.do" method="post">
	<input type="hidden" id="act" name="act" value="<bean:write name="act"/>"/>
		<logic:present name="have">
			<logic:match value="have" name="have">
				<script language="javascript">
	         	alert("字段代码已经存在！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%">
			<tr>
				<td width="50%">
					<div align="center">
						定义项目：
					</div>
				</td>
				<td width="50%">
					<logic:equal name="act" value="add">
						<html:select property="xmdm"
							style="width:100%;heigh:100%">
							<html:option value=""></html:option>
							<html:options collection="xmList" property="xmdm"
								labelProperty="xmmc" />
						</html:select>
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<input type="hidden" id="xmdm" name="xmdm"
							value="<bean:write name="rs" property="xmdm"/>" />
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						字段代码：
					</div>
				</td>
				<td>
					<logic:equal name="act" value="add">
						<input type="text" id="zddm" name="zddm" maxlength="20"
							style="width:100%;heigh:100%"
							value="">
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<input type="text" id="zddm" name="zddm" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zddm"/>">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						字段名称：
					</div>
				</td>
				<td>
					<input type="text" id="zdmc" name="zdmc" maxlength="100"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zdmc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						字段大小(单位：字符)：
					</div>
				</td>
				<td>
					<logic:equal name="act" value="add">
					<input type="text" id="zddx" name="zddx" maxlength="5"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zddx"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</logic:equal>
					<logic:notEqual name="act" value="add">
					<input type="text" id="zddx" name="zddx"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zddx"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						字段类型：
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="zdlx">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<logic:match value="一般文本" name="rs" property="zdlx">
							<input type="radio" name="zdlx" value="一般文本" checked>&nbsp;&nbsp;一般文本
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="zdlx" value="文本框">&nbsp;&nbsp;文本框
						</logic:match>
						<logic:match value="文本框" name="rs" property="zdlx">
							<input type="radio" name="zdlx" value="一般文本">&nbsp;&nbsp;一般文本
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="zdlx" value="文本框" checked>&nbsp;&nbsp;文本框
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="zdlx">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zdlx" value="一般文本" checked>&nbsp;&nbsp;一般文本
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="zdlx" value="文本框">&nbsp;&nbsp;文本框
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						是否必须为数字：
					</div>
				</td>
				<td align="center">
					<logic:present name="rs" property="bxwsz">
						<logic:match value="是" name="rs" property="bxwsz">
							<input type="radio" name="bxwsz" value="是" checked>&nbsp;&nbsp;是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="bxwsz" value="否">&nbsp;&nbsp;否
						</logic:match>
						<logic:match value="否" name="rs" property="bxwsz">
							<input type="radio" name="bxwsz" value="是">&nbsp;&nbsp;是
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="bxwsz" value="否" checked>&nbsp;&nbsp;否
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="bxwsz">
						<input type="radio" name="bxwsz" value="是" checked>&nbsp;&nbsp;是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bxwsz" value="否">&nbsp;&nbsp;否
					</logic:notPresent>
				</td>
			</tr>
		</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="commitInfo('xbemyQgzx.do?method=showFieldManager&doType=save')" id="btu_zj">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				<button type="button" class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("操作失败！");
					</script>
				</logic:equal>
			</logic:present>
	</html:form>
</body>
</html:html>
