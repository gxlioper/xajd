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

		<script type="text/javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+$('pkValue').value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" value="${realTable}" />
			<input type="hidden" id="tableName" value="${tableName}" />
			<input type="hidden" id="pkValue" value="${pkValue}" />
			<div class="title">
				<div class="title_img" id="title_m">
					${title }
				</div>
			</div>
			<logic:notPresent name="showbjlh">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="center">
								ѧ�ţ�
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������
								<bean:write name="userNameReal" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</thead>
				</table>
			</logic:notPresent>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						ѧ�������������
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">


						<thead>
							<tr>
								<td align="center" colspan="10">
									ѧ����Ϣ
								</td>
							</tr>
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									����
								</td>
								<td align="center">
									�Ա�
								</td>
								<td align="center">
									�༶
								</td>
								<td align="center">
									����ί���
								</td>
								<td align="center">
									У��ί���
								</td>
							</tr>
						</thead>
						<tr onclick="rowMoreClick(this,'',event);"
							ondblclick="modi('sjxy_shgzwh.do?method=shgzViewAndModi&doType=view')"
							style="cursor:hand">

							<td align="center">
								${rs.xh }
							</td>
							<td align="center">
								${rs.xm }
							</td>
							<td align="center">
								${rs.xb}
							</td>
							<td align="center">
								${rs.bjmc}
							</td>
							<td align="center">
								${rs.ftwsh }
							</td>
							<td align="center">
								${rs.xtwsh }
							</td>

						</tr>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>

		</html:form>
	</body>
</html>
