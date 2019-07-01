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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
function plsh(){
	var userType = document.getElementById('userType').value;
	var shType = document.getElementById('shType').value;
	var shjg = document.getElementById('shjg').value;
	
	if (shType == null || shType == ""){
		alert("��ѡ���������!");
		return false;
	}
	if (shType == null || shType == ""){
		alert("��ѡ����˽��!");
		return false;
	}
	
	
	if (userType != "xx" && userType != "admin"){
		if (!confirm("�¼��û������޸���ͨ���ϼ���˵����ݣ�ȷ��Ҫ�޸���ѡ��¼��")){
			return false;
		}
	}
	refreshForm('/xgxt/nblg_xszz.do?method=knsplsh&doType=save');
}

		</script>
		<html:form action="/nblg_xszz.do?method=knsplsh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:notEqual name="isQuery" value="is">
					��ǰ����λ�ã�ѧ������ - ��� - �������������
					</logic:notEqual>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="cbVal" name="cbVal"
				value="<bean:write name="rs" property="cbVal"/>" />
			<logic:present name="over">
				<logic:match value="over" name="over">
					<script language="javascript">
	         			alert("���������ɣ�");
	         		</script>
				</logic:match>
			</logic:present>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="2" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="50%">
						�������
					</td>
					<td align="left" width="50%">
						<logic:equal name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="1">��������</html:option>
								<html:option value="2"><bean:message key="lable.xsgzyxpzxy" />���</html:option>
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select name="rs" property="shType">
								<html:option value="3">ѧУ���</html:option>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td align="right" width="50%">
						��˽��
					</td>
					<td align="left" width="50%">
						<html:select name="rs" property="shjg">
							<html:option value="����">����</html:option>
							<html:option value="�ر�����">�ر�����</html:option>
							<html:option value="������">������</html:option>
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="plsh();" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
