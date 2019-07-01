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
					��ǰ����λ�ã�ѧ����Ϣ - ����ѧ��������-��ѧ���䶯���
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
					<td width="21%" align="right">
						ѧ�ţ�
					</td>
					<td width="31%" align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
						<input type="hidden" value="<bean:write name="rs" property="sqsj" />" name="sqrq" id="sqrq"/>
					</td>
					<td width="25%" align="right">�䶯ǰרҵ��
					</td>
					<td width="23%" align="left">
						<bean:write name="rs" property="ydqzymc" />						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">�䶯ǰ�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="ydqnj" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">&nbsp;
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">�䶯ǰ�༶�� </td>
					<td align="left">
						<bean:write name="rs" property="ydqbjmc" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">��Դ�أ�
					</td>
					<td align="left">
						<bean:write name="rs" property="syd" />
					</td>
					<td align="right">�䶯��רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="ydhzymc" />
					</td>					
				</tr>
				<tr style="height:22px">
					<td align="right">
						���壺
					</td>
					<td align="left">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td align="right">�䶯���꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="ydhnj"/>
					</td>					
				</tr>
				<tr style="height:22px">
				  <td align="right">��ͥ��ַ:</td>
				  <td align="left">
				  	<bean:write name="rs" property="jtdz"/>
				  </td>
				  <td align="right">�䶯��༶���ƣ�</td>
				  <td align="left">
				  	<bean:write name="rs" property="ydhbjmc"/>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">��ͥ�绰��</td>
				  <td align="left">
				  	<bean:write name="rs" property="jtdh"/>
				  </td>
				  <td align="right">��ʼʱ�䣺</td>
				  <td align="left">
				  	<bean:write name="rs" property="qssj"/>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">����ʱ�䣺</td>
				  <td align="left">
				  	<bean:write name="rs" property="sqsj"/>
				  </td>
				  <td align="right">��ֹʱ�䣺</td>
				  <td align="left">
				  	<bean:write name="rs" property="jzsj"/>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">�������ɣ�</td>
				  <td colspan="3">
				  	<bean:write name="rs" property="sqly"/>
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
				<tr style="height:22px">
				  <td align="right">ѧ������Ժ�����</td>
				  <td colspan="3">
				  <logic:present name="shdw">
				  	<logic:equal value="xsszy" name="shdw">
				  		<html:textarea property="xsszyyj" name="rs" style="width:100%"/>
				  	</logic:equal>
				  	<logic:notEqual value="xsszy" name="shdw">
				 	 <bean:write name="rs" property="xsszyyj"/>
				  	</logic:notEqual>
				  </logic:present>
				  </td> 
			  </tr>
				<tr style="height:22px">
				  <td align="right">���ڴ������</td>
				  <td colspan="3">
					  <logic:present name="shdw">
					  	<logic:equal value="hqc" name="shdw">
					  		<html:textarea property="hqcyj" name="rs" style="width:100%"/>
					  	</logic:equal>
					  	<logic:notEqual value="hqc" name="shdw">
					 	 	<bean:write name="rs" property="hqcyj"/>
					  	</logic:notEqual>
					  </logic:present>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">���������</td>
				  <td colspan="3">
				  		<logic:present name="shdw">
					  	<logic:equal value="cwc" name="shdw">
					  		<html:textarea property="cwcyj" name="rs" style="width:100%"/>
					  	</logic:equal>
					  	<logic:notEqual value="cwc" name="shdw">
					 	 	<bean:write name="rs" property="cwcyj"/>
					  	</logic:notEqual>
					  </logic:present>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">���������</td>
				  <td colspan="3"  height="45px">
				  		<logic:present name="shdw">
					  	<logic:equal value="jwc" name="shdw">
					  		<html:textarea property="jwcyj" name="rs" style="width:100%"/>
					  	</logic:equal>
					  	<logic:notEqual value="jwc" name="shdw">
					 	 	<bean:write name="rs" property="jwcyj"/>
					  	</logic:notEqual>
					  </logic:present>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right">ѧ���������</td>
				  <td colspan="3" height="45px">
				  		<logic:present name="shdw">
					  	<logic:equal value="xsc" name="shdw">
					  		<html:textarea property="xscyj" name="rs" style="width:100%"/>
					  	</logic:equal>
					  	<logic:notEqual value="xsc" name="shdw">
					 	 	<bean:write name="rs" property="xscyj"/>
					  	</logic:notEqual>
					  </logic:present>
				  </td>
			  	</tr>
				<tr style="height:22px">
				  <td align="right">�оʹ������</td>
				  <td colspan="3"  height="45px">
				  		<logic:present name="shdw">
					  	<logic:equal value="zjc" name="shdw">
					  		<html:textarea property="zjcyj" name="rs" style="width:100%"/>
					  	</logic:equal>
					  	<logic:notEqual value="zjc" name="shdw">
					 	 	<bean:write name="rs" property="zjcyj"/>
					  	</logic:notEqual>
					  </logic:present>
				  	
				  </td>
			  </tr>
			</table>
			<div class="buttontool" align="center">
			<logic:equal value="yes" name="writeAble">
				<logic:present name="isUser">
				<button class="button2"
					onclick="refreshForm('xbemyStuStatus.do?method=stuTransferStatusAuditing&doType=save')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				</logic:present>
				<logic:notPresent name="isUser">
				<button class="button2" style="width:80px" id="buttonSave" disabled="disabled">
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
