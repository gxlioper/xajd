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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var dkze = document.getElementById('dkze').value;
			var xfje = document.getElementById('xfje').value;
			if((dkze == null) || (dkze == "")){
				dkze = '0';
				document.getElementById('dkze').value = '0';
			}
			if((xfje == null) || (xfje == "")){
				xfje = '0';
				document.getElementById('xfje').value = '0';
			}
			document.getElementById('dkze').value = Math.round(dkze);
			document.getElementById('xfje').value = Math.round(xfje);
			if(Math.round(dkze) < Math.round(xfje)){
				alert("学费金额不的大于贷款总额!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=gjzxdkjePlsz&act=save";
			document.forms[0].submit();
		}
		
		function je(){
			var dkze = document.getElementById('dkze').value;
			var xfje = document.getElementById('xfje').value;
			
			if((dkze == null) || (dkze == "")){
				dkze = '0';
				document.getElementById('dkze').value = '0';
			}
			if((xfje == null) || (xfje == "")){
				xfje = '0';
				document.getElementById('xfje').value = '0';
			}
			document.getElementById('dkze').value = Math.round(dkze);
			document.getElementById('xfje').value = Math.round(xfje);
			if(Math.round(dkze) < Math.round(xfje)){
				alert("学费金额不的大于贷款总额!");
				return false;
			}
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 基础数据维护 - 国家助学贷款金额批量设置
		</div>
	</div>
	<html:form action="shgc_kns.do" method="post">

		<input type="hidden" id="pkDel" name="pkDel"
			value="<bean:write name='rs' property='pkDel'/>" />
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("设置完成！");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%">
			<tr>
				<td width="50%">
					<div align="center">
						 贷款总额
					</div>
				</td>
				<td align="center" width="50%">
					<input type="text" id="dkze" name="dkze" onblur="je();"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="dkze" />" 
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学费金额
					</div>
				</td>
				<td align="center">
					<input type="text" id="xfje" name="xfje" onblur="je();"
							style="width:100%;heigh:100%" maxlength="5"
							value="<bean:write name='rs' property="xfje" />" 
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<button class="button2" onClick="yz();">
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
