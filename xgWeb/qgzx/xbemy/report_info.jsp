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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript">
		function chgEditer(obj){
			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
			eWebEditor1.location = url;
		}
		function commitInfo(){
			document.getElementById('content1').value = frames('eWebEditor1').getHTML();
			refreshForm('xbemyQgzx.do?method=showReportManager&doType=save');
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��ڹ���ѧ - �������� - �Զ���ά�� - ����ά��
		</div>
	</div>
	<html:form action="xbemyQgzx.do" method="post">
			<table width="100%" class="tbstyle">
				<TR>
					<TD align="right">
						������Ŀ���룺
					</TD>
					<TD>
						<bean:write name="xmdm"/>
						<input id="xmdm" name="xmdm" type="hidden" value="<bean:write name="xmdm"/>"/>
					</TD>
				</TR>
				<TR>
					<TD align="right">
						������Ŀ���ƣ�
					</TD>
					<TD>
						<bean:write name="xmmc"/>
						<input id="xmmc" name="xmmc" type="hidden" value="<bean:write name="xmmc"/>"/>
					</TD>
				</TR>
				<TR>
						<TD align=right width="100">
							�༭���ݣ�
						</TD>
						<TD align=center colspan="3">
								<INPUT type="hidden" name="content1"
									value="<bean:write name="content1"/>">
							<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME>
						</TD>
				</TR>
				<tr>
					<td colspan="4">
					  <font color="red">��ʾ������Ҫ������ݵ�λ����\${rs.�ֶ�����}�滻�� ���� ���� \${rs.xm}</font>	
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="commitInfo()">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				<button type="button" class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:present>
	</html:form>
</body>
</html:html>
