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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();if($('buttonClose')){document.all('buttonClose').focus()}">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/prove_query" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_zdzmsqsh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - �ڶ�֤����-���������
				</div>
			</div>			
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
						<input type="hidden" value="<bean:write name="rs" property="nd" />" name="nd" id="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						���������
					</td>
					<td align="left">
						<bean:write name="rs" property="blfs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						�������ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="sqrq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right" rowspan="4">
						�������ɣ�
					</td>
					<td align="left" rowspan="4">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>					
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>		
								
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>				
					
				</tr>				
				<tr  style="height:22px">					
					<td align="right">
						��ˣ�
					</td>
					<td align="left" colspan="3">					
					<logic:equal value="xx" name="userType" scope="request">
					<html:select name="rs" styleId="yesNo" property="xxsh">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:equal value="xy" name="userType" scope="request">
					<html:select name="rs" styleId="yesNo" property="xysh">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					</td>
				</tr>
				<tr>
					<td align="right">
						ѧУ�����
					</td>
					<td align="left" colspan="3">
						<logic:equal value="xx" name="userType" scope="request">
						<html:textarea property="xxyj" name="rs" style="height:80;width:500"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType" scope="request">						
						<html:textarea property="xxyj" name="rs" style="height:80;width:500;" disabled="true"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />�����
					</td>
					<td align="left" colspan="3">
						<logic:equal value="xy" name="userType" scope="request">
						<html:textarea property="xyyj" name="rs" style="height:80;width:500"/>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="request">						
						<html:textarea property="xyyj" name="rs" style="height:80;width:500;" disabled="true"/>
						</logic:notEqual>
					</td>
				</tr>			
			</table>
			<logic:equal value="yes" name="writeAble">
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="viewAdd('prove_query.do?doType=auditing&type=save&xh=','add');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			</logic:equal>
		</html:form>
		<logic:notEmpty name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
				<script>
				alert("����ʧ��!");				
			</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
