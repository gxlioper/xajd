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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataDoSave(mustFill,byyfbz,bybfbz,bysfbz) {
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
			if(!isNumber(byyfbz)){
				alert("本月应发补助必须为正整数！");
				return false;
			}
			if(!isNumber(bybfbz)){
				alert("本月补发补助必须为正整数！");
				return false;
			}
			if(!isNumber(bysfbz)){
				alert("本月实发补助必须为正整数！");
				return false;
			}
			if(bysfbz != (Math.round(byyfbz) + Math.round(bybfbz))){
				alert("应发补助和补发补助之和与实发补助不相等！");
				return false;
			}
			var url = "/xgxt/fsbz_zdff.do?doType=save";
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function xfhj(){
			var byyfbz = document.getElementById('byyfbz').value;
			var bybfbz = document.getElementById('bybfbz').value;
			if((byyfbz == null) || (byyfbz == "")){
				byyfbz = "0";
			}
			if((bybfbz == null) || (bybfbz == "")){
				bybfbz = "0";
			}
			var bysfbz = Math.round(byyfbz) + Math.round(bybfbz);
			document.getElementById('bysfbz').value=bysfbz;
		}
	</script>
</head>

<body onload="xyDisabled('xy')">
	<html:form action="/fsbz_zdff.do" method="post">
		<input type="hidden" id="url" name="url" value="/fsbz_zdff.do" />

		<logic:present name="over">
			<logic:match value="ok" name="over">
				<script language="javascript">
	         	alert("自动发放完成！");
	         	window.close();
	         	dialogArgumentsQueryChick();
	         	</script>
			</logic:match>
			<logic:match value="no" name="over">
				<script language="javascript">
	         	alert("自动发放失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="request"/>" />
		<table width="100%" border="1" class="tbstyle">
			<thead>
				<tr>
					<td colspan="4" align="center">
						副食补助自动发放
					</td>
				</tr>
			</thead>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td colspan="3">
					<html:select name="rs" property="xydm">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="16%">
					<div align="center">
						<font color="red">*</font>本月应发补助
					</div>
				</td>
				<td width="34%">
					<div align="left">
						<input type="text" id="byyfbz" name="byyfbz"
							style="width:100%;heigh:100%" maxlength="5"  onblur="xfhj();"
							value="<bean:write name='rs' property="byyfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
				<td scope="row" width="16%">
					<div align="center">
						<font color="red">*</font>本月补发补助
					</div>
				</td>
				<td width="34%">
					<div align="left">
						<input type="text" id="bybfbz" name="bybfbz"
							style="width:100%;heigh:100%" maxlength="5"  onblur="xfhj();"
							value="<bean:write name='rs' property="bybfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<font color="red">*</font>本月实发补助
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" id="bysfbz" name="bysfbz"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="bysfbz" />"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</div>
				</td>
				<td scope="row">
					<div align="center">
						<font color="red">*</font>发放补助年月
					</div>
				</td>
				<td>
					<div align="left">
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('bzffny','y-mm');"
							value="<bean:write name='rs' property="bzffny" />" name="bzffny"
							id="bzffny" />
					</div>
				</td>
			</tr>
		</table>

		<div class="buttontool" align="center">
			<button class="button2"
				onclick="dataDoSave('byyfbz-bybfbz-bysfbz-bzffny',document.getElementById('byyfbz').value,document.getElementById('bybfbz').value,document.getElementById('bysfbz').value);"
				style="width:80px" id="buttonSave">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="window.dialogArguments.document.getElementById('search_go').click();Close();"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>

	</html:form>
</body>
</html:html>
