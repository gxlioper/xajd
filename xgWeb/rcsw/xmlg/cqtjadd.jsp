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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function SaveAdd(){
		   if($('xydm').value==''||$('jcsj').value==''||$('ydrs').value==''||$('sdrs').value==''){
		   		alert('��*��ѡ���Ϊ�գ�');
		   		return false;
		   }  
		   document.forms[0].action = "/xgxt/cqtjgl.do?method=cqtjAdd&doType=save";
		   document.forms[0].submit();
		}		
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function toNumber(obj){
			obj.value = obj.value.replace(/[^\d]/g,'');
		}

	</script>
</head>
<body>

	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��ճ����� - ����ͳ��ά�� - ����ͳ������
		</div>
	</div>
	<html:form action="/cqtjgl.do?method=cqtjAdd" method="post">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right">
						ѧ�꣺
				</td>
				<td align="left">
					<html:text property="xn" readonly="true"></html:text>
				</td>
				<td align="right">
						ѧ�ڣ�
				</td>
				<td align="left">
					<html:text property="xq" readonly="true"></html:text>
				</td>

			</tr>
			<tr>
				<td align="right" width="15%">
					<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left" width="35%">
					<html:select property="xydm" styleId="xydm">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
						
				</td>
				<td align="right" width="15%">
					<font color="red">*</font>���ʱ�䣺
				</td>
				<td align="left" width="35%">
					<html:text property="jcsj" styleId="jcsj" readonly="true"
										onclick="this.value='';return showCalendar('jcsj','y-mm-dd');"
										onblur="getRqVal('jcsj')"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
						<font color="red">*</font>Ӧ��������
				</td>
				<td align="left">
					<html:text property="ydrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
				<td align="right">
						<font color="red">*</font>ʵ��������
				</td>
				<td align="left">
					<html:text property="sdrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>

			</tr>
			<tr>
				<td align="right">
						���������
				</td>
				<td align="left">
					<html:text property="qjrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
				<td align="right">
						����������
				</td>
				<td align="left">
					<html:text property="kkrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
			</tr>
			
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveAdd()" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="Close()" id="buttonSave">
					�� ��
				</button>
		</div>

	</html:form>
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('�����ɹ���');
			 window.dialogArguments.document.getElementById('search_go').onclick();
			 window.close();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			 alert('����ʧ�ܣ�');
		</script>
	</logic:equal>
</body>
</html:html>
