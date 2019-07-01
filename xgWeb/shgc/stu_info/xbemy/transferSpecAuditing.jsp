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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - ����ѧ��������-��תרҵ���
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
						<input type="hidden" value="<bean:write name="rs" property="sqrq" />" name="sqrq" id="sqrq"/>
					</td>
					<td align="right">ת��רҵ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="lqzymc" />						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">ת���꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="zcnj" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">&nbsp;
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">ת��ѧ�ƣ� </td>
					<td align="left">
						<bean:write name="rs" property="zcxz" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						׼��֤�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zkzh" />
					</td>
					<td align="right">ת��ѧ����Σ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zcxlcc" />
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						���壺
					</td>
					<td align="left">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td align="right">ת��רҵ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrzymc"/>
					</td>					
				</tr>
				<tr style="height:22px">
				  <td align="right">��ѧʱ�䣺</td>
				  <td align="left">
				  	<bean:write name="rs" property="rxsj"/>
				  </td>
				  <td align="right">ת���꼶��</td>
				  <td align="left">
				  	<bean:write name="rs" property="zrnj"/>
				  </td>
			  </tr>
			  <tr>
			  		<td align="right">ת��ѧ�ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxz"/>
					</td>	
					<td align="right">ת��ѧ����Σ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxlcc"/>
					</td>	
			  </tr>
				<tr style="height:22px">
					<td align="right">�������ɣ�
					</td>
					<td align="left" colspan="3" height="45px">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr style="height:22px">
				  <td align="right">ת��<bean:message key="lable.xsgzyxpzxy" />����� </td>
				  <td align="left" colspan="3">
					  <logic:present name="shdw">
					  
					  <logic:equal value="zcxy" name="shdw">
					  	<html:textarea property="zczyyxyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  
					  <logic:equal value="zrzcxy" name="shdw">
					  <html:textarea property="zczyyxyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					   
					  <logic:notEqual value="zcxy" name="shdw">
					  	<logic:notEqual value="zrzcxy" name="shdw">
					  	<bean:write name="rs" property="zczyyxyj"/>
					  	</logic:notEqual>
					  </logic:notEqual>
					  
					  </logic:present>
					 
				  </td>
			  	</tr>
			  	<tr style="height:22px">
				  <td align="right">ת��<bean:message key="lable.xsgzyxpzxy" />����� </td>
				  <td align="left" colspan="3">
					  <logic:present name="shdw">
					  <logic:equal value="zrxy" name="shdw">
					  	<html:textarea property="zrzyyxyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  
					  <logic:equal value="zrzcxy" name="shdw">
					 	 <html:textarea property="zrzyyxyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  
					  <logic:notEqual value="zrxy" name="shdw">
					  <logic:notEqual value="zrzcxy" name="shdw">
					  	<bean:write name="rs" property="zrzyyxyj"/>
					  	</logic:notEqual>
					  </logic:notEqual>
					  </logic:present>
				  </td>
				 </tr>
				 
				  <tr style="height:22px">
				  <td align="right">�оʹ������ </td>
				  <td align="left" colspan="3">
					  <logic:present name="shdw">
					  <logic:equal value="zjc" name="shdw">
					  	<html:textarea property="zjcyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  <logic:notEqual value="zjc" name="shdw">
					  	<bean:write name="rs" property="zjcyj"/>
					  </logic:notEqual>
					  </logic:present>
				  </td>
			  	</tr>
			  	
			  	<tr style="height:22px">
				  <td align="right">��������� </td>
				  <td align="left" colspan="3">
					  <logic:present name="shdw">
					  <logic:equal value="cwc" name="shdw">
					  	<html:textarea property="cwcyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  <logic:notEqual value="cwc" name="shdw">
					  	<bean:write name="rs" property="cwcyj"/>
					  </logic:notEqual>
					  </logic:present>
				  </td>
			  	</tr>
			  	
			  	<tr style="height:22px">
				  <td align="right">��������� </td>
				  <td align="left" colspan="3">
					  <logic:present name="shdw">
					  <logic:equal value="jwc" name="shdw">
					  	<html:textarea property="jwcyj" name="rs" style="width:100%;height:45px"/>
					  </logic:equal>
					  <logic:notEqual value="jwc" name="shdw">
					  	<bean:write name="rs" property="jwcyj"/>
					  </logic:notEqual>
					  </logic:present>
				  </td>
			  	</tr>
				<tr  style="height:22px">
				  <td align="right"> ��ˣ� </td>
				  <td align="left" colspan="3">
				  	<html:select property="xxsh" name="rs"> 
				  	<html:option value="δ���">δ���</html:option> 
				  	<html:option value="ͨ��">ͨ��</html:option> 
				  	<html:option value="��ͨ��">��ͨ��</html:option> 
				  </html:select> 
				 </td>				 
			  </tr>
				<tr  style="height:22px">
				  <td align="right">�ߵ�ѧУ���������</td>
				  <td align="left" colspan="3">
					<html:textarea property="zrxxyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">�������������������</td>
				  <td align="left" colspan="3">
					<html:textarea property="zcsjytyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
			</table>
			<div class="buttontool" align="center">
			<logic:equal value="yes" name="writeAble">
				<logic:present name="isUser">
				<button class="button2"
					onclick="refreshForm('xbemyStuStatus.do?method=stuTransferSpecAuditing&doType=save')"
					style="width:80px" id="buttonSave">
					 �� �� 
				</button>
				</logic:present>
				<logic:notPresent name="isUser">
				<button class="button2" id="buttonSave" disabled="disabled" style="width:80px">
					 �� �� 
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			  </logic:equal>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
