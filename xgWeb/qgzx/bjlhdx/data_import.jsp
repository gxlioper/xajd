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
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
			function FileLoad(filedId) {
				var tmp = document.getElementById(filedId).value;
				if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".xls") {
					alert("��ѡ����ļ����Ϸ���������ѡ��");
					document.getElementById(filedId).value = "";
					return false;
				} else {
					document.forms[0].action = "/xgxt/qgzxUpload.do?method=uploadFile&tabName=" + document.getElementById("realTable").value + "&id=" + filedId;
					document.forms[0].submit();
					return true;
				}
			}
		</script>
		<html:form action='/uploadFile.do' method='post'
			enctype='multipart/form-data'>
			<input type="hidden" id="realTable" name="realTable" value=""/>
			<logic:present name="moditag">
				<input type="hidden" id="moditag" name="moditag"
					value="<bean:write name="moditag"/>" />
			</logic:present>
			<logic:notPresent name="moditag">
				<input type="hidden" id="moditag" name="moditag" value="" />
			</logic:notPresent>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã� �ڹ���ѧ - ���ݵ���
				</div>
			</div>
			<logic:notEmpty name="dataImported">
				<script>
	alert("�����ɹ���");
	window.close();
	</script>
			</logic:notEmpty>
			<div id="search_m">
				<div class="searchcontent">
					<br />
					<br />
					<table width="99%" class="tbstyle">
						<thead>
							<tr>
								<td align="center" colspan="4">
									��ѡ��Ҫ������ļ�
								</td>
							</tr>
						</thead>
						<tr>
						<td align="right">
							��λ��Ϣ��
						</td>
						<td align="center">								 
						    <input type="file" name="file" id="file" value="" />
						</td>
						<td align="center">
								<button type="button" class="button2" onclick="document.getElementById('realTable').value='gwxxb';FileLoad('file')">
									��������
								</button>
						</td>
						<td >
							<a href="/xgxt/xlsDown/bjlh_gwxxb.xls" target="_blank">ģ������</a>
						</td>						
						</tr>
						
						<tr>
						<td align="right">
							ѧ�������λ��
						</td>
						<td align="center">								 
						    <input type="file" name="file2" id="file2" value="" />
						</td>
						<td align="center">
								<button type="button" class="button2" onclick="document.getElementById('realTable').value='xsgwxxb';FileLoad('file2')">
									��������</button>
						</td>
						<td >
							<a href="/xgxt/xlsDown/bjlh_xsgwxxb.xls" target="_blank">ģ������</a>
						</td>						
						</tr>
						
						<tr>
						<td align="right">
							ѧ����𷢷ţ�
						</td>
						<td align="center">								 
						    <input type="file" name="uploadFile" id="uploadFile" value="" />
						</td>
						<td align="center">
								<button type="button" class="button2" onclick="document.getElementById('realTable').value='xscjffb';FileLoad('uploadFile')">
									��������</button>
						</td>
						<td >
							<a href="/xgxt/xlsDown/bjlh_xscjffb.xls" target="_blank">ģ������</a>
						</td>						
						</tr>
						<thead>
							<tr>
								<td align="center" colspan="4">
									&nbsp;
								</td>
							</tr>
						</thead>
					</table>
					<br />
				</div>
			</div>
		</html:form>
	</body>
</html>
