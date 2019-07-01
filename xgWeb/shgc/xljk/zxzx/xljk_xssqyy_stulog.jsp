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
<base target="_self" />
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
	</head>
	<script>
		function yanzheng()
		{
			var xh=document.all["xh"].value;
			var xsmm=document.all["xsmm"].value;
			if(xh=="")
			{
				alert("ѧ�Ų��ܿ�!");
				document.all["xh"].focus();
				return false;
			}
			else if(xsmm.value==""){
				alert("���벻��Ϊ��!");
				document.all["xsmm"].focus();
				return false;
			}
			else 
			{
				var zxszy_xnid = document.all["zxszy_xnid"].value;
				document.all["yanzheng_flag"].value="yes";
				refreshForm('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=student_check&zxszy_xnid='+zxszy_xnid);
			}
		}
		
		function enter()
		{
			//document.forms[0].action='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table';
			//document.forms[0].su
			//window.dialogArguments.location='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table';
			//window.close();
			var xh=document.all["xh"].value;
			var zxszy_xnid=document.all["zxszy_xnid"].value;
			window.dialogArguments.location='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table&xh='+xh+'&zxszy_xnid='+zxszy_xnid;
			window.close();
		}
	</script>
	<body>
	<html:form action="/xljk_xssqyy_zysq" >
	        <div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���������ѧ������ԤԼ��ѧ����֤��½
				</div>
			</div>
	<input type="hidden" id="zxszy_xnid" name="zxszy_xnid"
				value="<bean:write name="zxszy_xnid" scope="request"/>" />
	<input type="hidden" id="yanzheng_flag" name="yanzheng_flag" value="no" />
		<p align="center">
			<strong style="font-size:15px">ѧ��������֤��</strong>	
		<table class="tbstyle" style="width:100%;" align="center" id="tab" >
			<tr align="center">
				<td  height="20%" align="right">
					������ѧ�ţ�&nbsp;&nbsp;&nbsp;
				</td>
				<td  height="20%" align="left">
						<input type="text" name="xh" size="15" value="<bean:write name="xh" scope="request"/>">
				</td>
			</tr>
			<tr align="center">
				<td height="20%" align="right">
					������ѧ�����룺
				</td>
				<td  height="20%" align="left">
						<input type="password" name="xsmm" size="15" value="<bean:write name="xsmm" scope="request"/>" >
				</td>
			</tr>	
			<tr>
				<br/>
				<br/>
			</tr>
			<tr>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<button class="button2" style="width:80px"  id="search_go" 
									onclick="yanzheng()">
						ȷ ��
					</button>
				</td>
			</tr>		
		</table>
	</html:form>
	<logic:notEmpty name="message">
	
					<logic:equal name="message" value="log fail">
						<script>alert("�û����������������¼�����룡")</script>
					</logic:equal>
					<logic:equal name="message" value="true">
						<script>
						alert("��֤ͨ��������д�����");
						enter();
						</script>
					</logic:equal>
					<logic:equal name="message" value="date error">
						<script>alert("������������!")</script>
					</logic:equal>
	</logic:notEmpty>
</body>
</html>
