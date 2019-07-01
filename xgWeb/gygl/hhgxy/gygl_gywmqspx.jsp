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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function saveWmqs(url){
		var ssbh = document.getElementById("ssbh").value;
		var xn = document.getElementById("xn").value;
		var xq = document.getElementById("xq").value;
		var avgfs = document.getElementById("avgfs").value;
		var pk = xn+splitSignOne+xq+splitSignOne+ssbh+splitSignOne+avgfs;
		if (confirm("ȷ��Ҫ�޸�"+ssbh+"����������״̬��\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
			showTips('���������У���ȴ�......');
			refreshForm(url+pk);
		}
	}
	</script>
	<body>
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope = "session"/>" />
		<input type="hidden" id="msg" name="msg" value="${msg}"/>
		<html:form action="/XsgyglHhDispatch" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					����������ѡ
				</legend>
				<logic:notEmpty name="rs">
				<table width="100%" class="tbstyle">
					<tr>
						<td>
							&nbsp;&nbsp;���Һţ�
						</td>
						<td align="left">
							<html:text property="ssbh" styleId="ssbh" name="rs" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;ƽ���֣�
						</td>
						<td align="left">
							<html:text property="avgfs" name="rs" readonly="true"/>
						</td>

					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;ѧ��:
						</td>
						<td>
							<html:text property="xn" name="rs" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;ѧ�ڣ�
						</td>
						<td align="left">
							<html:text property="xq" name="rs" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;���ȵȼ�:
						</td>
						<td>
							<html:text property="pbdj" name="rs" maxlength="20"/>
						</td>
						<td>
							&nbsp;&nbsp;�������ڣ�
						</td>
						<td align="left">
							<html:text property="pbzq" name="rs" readonly="true"/>
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
							�������ɼ�
						</td>
					</tr>
					<logic:iterate name="rsList" id="s">	
						<logic:iterate id="b" name="s" property="wsjcList">
							<tr>
								<td colspan="2">
									<bean:write name="b" property="dj"/>
								</td>
								<td colspan="2">
									<bean:write name="b" property="djnum"/>��
								</td>
							</tr>
						</logic:iterate>
					</logic:iterate>	
					<tr align="center">
						<td colspan="4">
							���ҳ�ԱΥ�����
						</td>
					</tr>
					<logic:iterate name="rsList" id="s">	
						<logic:iterate id="b" name="s" property="wjList">
							<tr>
								<td colspan="2">
									<bean:write name="b" property="xm"/>
								</td>
								<td colspan="2">
									<bean:write name="b" property="wj"/>
								</td>
							</tr>
						</logic:iterate>
					</logic:iterate>	
				</table>
				</logic:notEmpty>
			</fieldset>
				<div class="buttontool">
					<logic:equal name="wmsq" value="no">
					<button class="button2"
						onclick="saveWmqs('/xgxt/XsgyglHhDispatch.do?method=gywmqspx&type=save&pk=');"
						style="width:100px" id="buttonSave">
						ȷ����������
					</button>
					&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<logic:equal name="wmsq" value="yes">
					<button class="button2"
						onclick="saveWmqs('/xgxt/XsgyglHhDispatch.do?method=gywmqspx&type=del&pk=');;"
						style="width:100px" id="buttonSave">
						ȡ����������
					</button>
					&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
		</html:form>
	</body>
</html>
<logic:present name="inserted">
	<logic:equal name="inserted" value="ok">
	<script>
		alert("�ύ�ɹ���");
    	dialogArgumentsQueryChick();
		Close();
	</script>
	</logic:equal>
</logic:present>
<logic:present name="msg">
	<script>
		alert(''+document.getElementById('msg').value);
	</script>
</logic:present>