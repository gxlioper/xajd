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
	<meta name="Copyright" content="������� zfsoft" />

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
			var zydm = document.getElementById('zydm').value;
			var dkze = document.getElementById('dkze').value;
			var xfje = document.getElementById('xfje').value;
			if((zydm == null) || (zydm == "")){
				alert("��ѡ��רҵ!");
				return false;
			}
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
				alert("ѧ�ѽ��Ĵ��ڴ����ܶ�!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/bjlhdx_xszz.do?method=gjzxdkjeEdit&act=save";
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
				alert("ѧ�ѽ��Ĵ��ڴ����ܶ�!");
				return false;
			}
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��������ά�� - ������ѧ��������趨
		</div>
	</div>
	<html:form action="shgc_kns.do" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="90%">
			<tr>
				<td width="50%">
					<div align="center">
						רҵ
					</div>
				</td>
				<td width="50%">
						<input type="hidden" id="zydm" name="zydm"
							value="<bean:write name="rs" property="zydm"/>" />
						<input type="text" id="zymc" name="zymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						 �����ܶ�
					</div>
				</td>
				<td align="center">
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
						ѧ�ѽ��
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
		<logic:equal name="writeAble" value="yes">
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<button class="button2" onClick="yz();">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
			<button class="button2"
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
		</div>
		</logic:equal>
	</html:form>
</body>
</html:html>
