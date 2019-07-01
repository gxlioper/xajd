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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="readOnly();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/sztz_xfsb_sh?act=sbsh" method="post">		
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - �������뿼�� - ��� - �������
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="xn||nd||xq||xmdm||xh" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble" scope="request"/>"/>								
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							����ѧ���걨���
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
					ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="xh" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="nd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="xm" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="xb" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name='xq' />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="nj" />
					</td>
					<td align="right">
						��չ�(��Ŀ)��
					</td>
					<td align="left">
						<bean:write name="xmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="xymc" />
					</td>
					<td align="right">
						 ������Ŀ��
					</td> 
					<td align="left">
						<bean:write name="kmm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="zymc" />
					</td>
					<td align="right">
						 ����ѧ�֣�
					</td>
					<td align="left">
					    <input type="hidden" id="xf" name="xf" value="<bean:write name="xf" />" /> 
						<bean:write name="xf" />
                  </td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="bjmc" />
					</td>
					<td align="right">
						��չ������
					</td>
					<td align="left">
					    <input type="hidden" id="jbdm" name="jbdm" value="<bean:write name="jbdm" />" /> 
						<bean:write name="jbmc" />
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						�걨ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="sbsj" />
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�����
					</td>
					<td align="left">
						<bean:write name="jb" />
					</td>	
					<td align="right">
						�μ����ʣ�
					</td>
					<td align="left">
						<bean:write name="xz" />
					</td>	
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						�걨���ɣ�
					</td>
					<td align="left"colspan="3" style="width:88%">
						<bean:write name="sqly" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ɹ���
					</td>
					<td align="left"colspan="3">
						<bean:write name="cg" />
					</td>
					
				</tr>
     		</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/sztz_xfsb_sh.do?act=save');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		</html:form>
	</body>
</html>
