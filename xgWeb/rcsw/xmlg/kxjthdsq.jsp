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


	<title><bean:message key="lable.title" /></title>
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
		function SaveSq(){
		   var zzdw = document.getElementById('zzdw').value;
		   var hdnr = document.getElementById('hdnr').value;
		   var aqcs = document.getElementById('aqcs').value;
		   if(zzdw == ''){
		   		alert('��֯��λ����Ϊ�գ�');
		   	   	return false;
		   }
		   if(hdnr.length > 1000){
		   		alert('����ݲ��ܴ���1000�֣�');
		   	   	return false;
		   }
		   if(aqcs.length > 400){
		   		alert('��ȫ��ʩ���ܴ���400�֣�');
		   	   	return false;
		   }
		   
		   document.forms[0].action = "/xgxt/kxjthdgl.do?method=kxjthdglsq&doType=save";
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
		function toPrint(){
			document.forms[0].action = "/xgxt/bxjthdgl.do?method=bxjthdPrint&lb=kx";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<logic:equal value="no" name="sfksq">
		<br>
		<br>
		<br>
		<p align="center">
			<font color="red" size="2">��ҳ��ֻ����ѧ�����ʣ�</font>
		</p>
	</logic:equal>
	<logic:notEqual value="no" name="sfksq">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��ճ����� - ��ϵ������������ - �����
			</div>
		</div>
		<html:form action="/kxjthdgl" method="post">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>��֯��λ��
					</td>
					<td align="left" width="35%">
						<html:text property="zzdw"></html:text>
					</td>
					<td align="right" width="15%">
						�����ˣ�
					</td>
					<td align="left" width="35%">
						<html:text property="fzr"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						�����˵绰��
					</td>
					<td align="left">
						<html:text property="fzrdh"></html:text>
					</td>
					<td align="right">
						���ӽ�ʦ��
					</td>
					<td align="left">
						<html:text property="ddjs"></html:text>
					</td>

				</tr>
				<tr>
					<td align="right">
						���ӽ�ʦ�绰��
					</td>
					<td align="left">
						<html:text property="ddjsdh"></html:text>
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
						<html:text property="cxsj" styleId="cxsj" readonly="true"
							onclick="this.value='';return showCalendar('cxsj','y-mm-dd');"
							onblur="getRqVal('cxsj')"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�����
							<br>
							<font color="red"><��1000����> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea  property="hdnr" rows="8" styleId="hdnr"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ȫ��ʩ
							<br>
							<font color="red"><��400����> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea property="aqcs" rows="8" styleId="aqcs"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
					�ύ����
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrint()" id="buttonSave">
					�� ӡ
				</button>
			</div>
		</html:form>
	
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('�����ɹ���');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
	</logic:equal>
	</logic:notEqual>
</body>
</html:html>
