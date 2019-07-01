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
			var tsrqdm = document.getElementById('tsrqdm').value;
			var zzxmdm = document.getElementById('zzxmdm').value;
			if((zzxmdm == null) || (zzxmdm == "")){
				alert("��ѡ��������Ŀ!");
				return false;
			}
			if((tsrqdm == null) || (tsrqdm == "")){
				alert("��ѡ��������Ⱥ!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=tsrqxmSave";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��������ά�� - ������Ⱥ��������Ŀ����
		</div>
	</div>
	<html:form action="zgkydx_xszz.do?method=tsrqxmSave" method="post">
		<logic:present name="have">
			<logic:match value="have" name="have">
				<script language="javascript">
	         	alert("��������Ŀ�����ô�������Ⱥ��");
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<table class="tbstyle" width="100%">
			<tr>
				<td colspan="2" align="center">
					��ѡ��
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">
					������Ŀ
				</td>
				<td width="60%">
					<html:select name="rs" property="zzxmdm" style="width:100%" >
						<html:options collection="zzxmList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					������Ⱥ
				</td>
				<td>
					<html:select name="rs" property="tsrqdm" style="width:100%" >
						<html:options collection="tsrqList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button type="button" class="button2" onClick="yz();" style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2"
				onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
				style="width:80px" id="buttonClose">
				�� ��
			</button>
		</div>

	</html:form>
</body>
</html:html>
