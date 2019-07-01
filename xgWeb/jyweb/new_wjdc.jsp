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
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		   function save(){
		   
		    var bt = document.getElementById("bt").value;
		    var choice = document.getElementById("choice").value;
		    if( bt==""){
		     alert("���ⲻ��Ϊ�գ�");
		     return false;
		    }
		    if( choice==""){
		     alert("��һ��ѡ���Ϊ�գ�");
		     return false;
		    }
		    document.forms[0].action = "newwjdc.do?method=newwjdc&doType=save&jytype=jyweb";
		    document.forms[0].submit();
		   
		   }
		
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/newwjdc" method="post">
			<fieldset>
				<legend>
					<font color="red"><b>�����ʾ����</b> </font>
				</legend>
				<br>
				<logic:equal name="first" value="first">
					<table>
						<tr>
							<td align="right">
								������������:
							</td>
							<td align="left">
								<input type="text" name="bt" maxlength="11" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�������һ��ѡ��:
							</td>
							<td align="left">
								<input type="text" name="choice" maxlength="11" />
							</td>
						</tr>
					</table>
					<br>
					<button onclick="save();return false;">
						�ύ
					</button>
				</logic:equal>
				<logic:equal name="save" value="ok">
				�ύ�ɹ��������رհ�ť�������һ��������
				</logic:equal>
				<logic:equal name="save" value="no">
				�ύʧ�ܣ������رհ�ť�����²�����
				</logic:equal>
				&nbsp;&nbsp;
				<button
					onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
					�ر�
				</button>
				<br>
				ע�����⼰ѡ���������11���ַ����ȣ�����Ӱ�����ۡ�
				<br>
				<br>
				<br>
			</fieldset>
		</html:form>
	</body>

</html>
