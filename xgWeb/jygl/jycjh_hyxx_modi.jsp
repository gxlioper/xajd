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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<base target="_self">
	<script language="javascript">
	function jygl_hyxx_modi(){	
			var csrq=document.all["csrq"].value;
			if ( csrq==""){
				alert ("����д��Ա��������");
				document.all["csrq"].focus();
				return false;
			}
			var sjhm=document.all["sjhm"].value;
			if ( sjhm==""){
				alert ("����д��Ա�ֻ�����");
				document.all["sjhm"].focus();
				return false;
			}
			var qsdh=document.all["qsdh"].value;
			if ( qsdh==""){
				alert ("��д��Ա���ҵ绰");
				document.all["qsdh"].focus();
				return false;
			}
			var xn_id=document.getElementById('pk').value;
			underDealWith();
			refreshForm('/xgxt/jyglInfo.do?method=modi_hyxx&doType=modi&pk='+xn_id);
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyglInfo?method=modi_hyxx" method="post">
			<input type="hidden" value="<bean:write name="pk"/>" name="pk"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ��ҵ�ٽ��� - ��Ա������Ϣ - �޸Ļ�Ա������Ϣ
				</div>
			</div>
			<table class="tbstyle" align="center" style="width:100%">
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>ѧ �ţ�
					</td>
					<td align="left">
						<html:text name="rs" property="hyxh" styleId="hyxh"
							readonly="true" />
					</td>
					<td align="right">
						�� ����
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						�� ��
					</td>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" readonly="true" />
					</td>
					<td align="right">
						�� �� �� �ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq" 
							style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						ѧ Ժ��
					</td>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc"
							readonly="true" />
					</td>
					<td align="right">
						�� ����
					</td>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						�� �� �� �룺
					</td>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" />
					</td>
					<td align="right">
						�� �� �� ����
					</td>
					<td align="left">
						<html:text name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						�� Ա �� �ţ�
					</td>
					<td align="left">
						<html:text name="rs" property="hybh" readonly="true" />
					</td>

					<td align="right">
						ְ ��
					</td>
					<td align="left">
						<html:text name="rs" property="zw" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						�� ע��
					</td>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" />
					</td>
				</tr>
			</table>
			
			<div id="tmpdiv"></div>
			<div class="buttontool" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="jygl_hyxx_modi()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
						alert("�޸ĳɹ�!");
						dialogArgumentsQueryChick();
						Close();	
					</script>
				</logic:equal>
				<logic:equal name="message" value="update_fail">
					<script>
						alert("�޸�ʧ��!");
						document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
