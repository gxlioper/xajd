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
		<base target="_self" />
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
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript">
    	
    </script>
	<body >
		<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ����
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<logic:equal value="xy" name="userType">
					<thead>
					<tr align="center">
						<td height="22" colspan="11">
							��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="center" style="" nowrap="nowrap">
						�к�
					</td>
					<td height="22" align="center" style=""nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�༶
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						����
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�����ƺ�
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�۲��ܳɼ��༶����
					</td>
					<td height="22" align="center" nowrap="nowrap">
						����Ա���
					</td>
					<td height="22" align="center" nowrap="nowrap">
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧУ���
					</td>
				</tr>
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" >
								<logic:iterate id="v" name="s" >
									<td align="center" nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="rs">
					<tr>
					<td height="22" align="center" style="" colspan="11">
						���޼�¼
					</td>
					</tr>
				</logic:empty>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<thead>
					<tr align="center">
						<td height="22" colspan="10">
							��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="center" nowrap="nowrap">
						�к�
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�༶
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧ��
					</td>
					<td height="22" align="center" nowrap="nowrap">
						����
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�����ƺ�
					</td>
					<td height="22" align="center" nowrap="nowrap">
						�۲��ܳɼ��༶����
					</td>
					<td height="22" align="center" nowrap="nowrap">
						ѧУ���
					</td>
				</tr>
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" >
								<logic:iterate id="v" name="s" >
									<td align="center" nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="rs">
					<tr>
					<td height="22" align="center" style="" colspan="10">
						���޼�¼
					</td>
					</tr>
				</logic:empty>
				</logic:notEqual>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��!" + $('message').value);
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
