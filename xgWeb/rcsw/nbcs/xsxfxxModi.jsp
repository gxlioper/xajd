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
	<script>
		function save(){
			refreshForm('xfxx.do?method=saveXsxfxx&doType=modi');
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xfxx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ�Ѵ߽� - ѧ����Ϣά�� - �޸�
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								ѧ����Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td>							
							${rs.xh}
							<html:hidden property="xh" name="rs"/>
						</td>
						<td>
							<div align="right">
								��ȣ�
							</div>
						</td>
						<td>
							${rs.nd}
							<html:hidden property="nd" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td>							
							${rs.xm}
						</td>	
						<td align="right">
							Ƿ��ѧ�ѣ�
						</td>
						<td>							
							<html:text property="qjxf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>	
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>							
							${rs.xymc}
						</td>	
						<td align="right">
							���������ѣ�
						</td>
						<td>			
							<html:text property="qtf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>							
							${rs.bjmc}
						</td>	
						<td align="right">
							Ƿ�ɴ��ܷѣ�
						</td>
						<td>							
							<html:text property="qjdgf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							������Σ�
						</td>
						<td>							
							<html:text property="pycc" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							����״̬��
						</td>
						<td>							
							<html:text property="jfzt" maxlength="15" name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							ѧϰ��ʽ��
						</td>
						<td>							
							<html:text property="xxxs" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							׼��֤�ţ�
						</td>
						<td>							
							<html:text property="zkzh" maxlength="150" name="rs"></html:text>
						</td>						
					</tr>
				</table>
				<logic:notEqual value="stu" name="userType">
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
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
				</logic:notEqual>
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
