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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<base target="_self">
	<body>
		<html:form action="/xsfwzdzx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - ���������˹��� - �޸�
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								������������Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							�������û�����
						</td>
						<td>
							${rs.slryhm}
							<html:hidden property="slryhm" name="rs"/>
						</td>						
					</tr>
					<tr>
						<td>
							<div align="right">
								�����˲��ţ�
							</div>
						</td>
						<td>
							${rs.slbmmc}
							<html:hidden property="slbmdm" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							������������
						</td>
						<td>
							<html:text property="slrxm" name="rs" maxlength="30"></html:text>
						</td>					
					</tr>
					<tr>
						<td align="right">
							��������ϵ�绰��
						</td>
						<td>
							<html:text property="slrlxdh" name="rs" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="refreshForm('xsfwzdzx.do?method=saveXslfslrxx')"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
