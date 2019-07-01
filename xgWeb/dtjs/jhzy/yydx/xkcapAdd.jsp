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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript">
function dataSave(){
      var kssj="";
	   var kssjH="";//��ʼʱ��Сʱ
	   var kssjM="";//��ʼʱ���
	   var kssjS="";//��ʼʱ����
	   var jssj="";
	   var jssjH="";//����ʱ��Сʱ
	   var jssjM="";//����ʱ���
	   var jssjS="";//����ʱ����
	   if($("kssj")){
	     kssj= $("kssj").value;
	   }
	   if($("kssjH")){
	     kssjH= $("kssjH").value;
	   }
	   if($("kssjM")){
	     kssjM= $("kssjM").value;
	   }
	   if($("kssjS")){
	     kssjS= $("kssjS").value;
	   }
	   if($("jssj")){
	     jssj= $("jssj").value;
	   }
	   if($("jssjH")){
	     jssjH= $("jssjH").value;
	   }
	   if($("jssjM")){
	     jssjM= $("jssjM").value;
	   }	   	
	   if($("jssjS")){
	     jssjS= $("jssjS").value;
	   }	 
	   if(kssjH > 24 || jssjH > 24){
	   	 alert("Сʱ�����ܴ���24��");
	     return false;
	   }
	   if(kssjM > 60 || jssjM > 60){
	   	 alert("���������ܴ���60��");
	     return false;
	   }  
	   if(kssjS > 60 || jssjS > 60){
	   	 alert("�������ܴ���60��");
	     return false;
	   }     	   	
	   if($("xydm")){
	      if($("xydm").value==""){
	         alert("<bean:message key="lable.xsgzyxpzxy" />����Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("xn")){
	      if($("xn").value==""){
	         alert("ѧ�겻��Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("dkjs")){
	      if($("dkjs").value==""){
	         alert("���ν�β���Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("xq")){
	      if($("xq").value==""){
	         alert("ѧ�ڲ���Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("kssj")){
	      if($("kssj").value==""){
	         alert("��ʼʱ�䲻��Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("jssj")){
	      if($("jssj").value==""){
	         alert("����ʱ�䲻��Ϊ�գ�");
	         return false;
	      }
	   }
	   
	   if($("kczy")){
	      if($("kczy").value==""){
	         alert("�γ�ժҪ����Ϊ�գ�");
	         return false;
	      }
	   }
	   if($("jtap")){
	      if($("jtap").value.length>500){
	         alert("���尲������������500�֣�");
	         return false;
	      }
	   }
	   if($("bz")){
	      if($("bz").value.length>300){
	         alert("��ע����������300�֣�");
	         return false;
	      }
	   }
	   var kssjV = kssj+kssjH+kssjM+kssjS;
	   var jssjV = jssj+jssjH+jssjM+jssjS;
	   if(kssjV>=jssjV){
	     alert("��ʼʱ�����ڽ���ʱ�䣬���ʵ�����ύ��");
	     return false;
	   }
	   var url = "/xgxt/jhzyYydx.do?method=xkcapAdd&doType=save";
	   refreshForm(url);	   
}

</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/dtjsFunction.js"></script>
		<html:form action="/jhzyYydx" method="post" enctype="multipart/form-data">
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />
			<input type="hidden" id="msg" name="msg" value="${msg}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã����Ž��� - ҵ�൳У - �տγ̰��� - ����
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							&nbsp;
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:select property="xydm" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left">
						<html:select property="xn" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>���ν�Σ�
					</td>
					<td align="left">
						��&nbsp;
						<html:text property="dkjs" styleId="dkjs" style="width:30px" onkeypress="return NumInputValue1(this,3);" onblur='onlyNumInput(this)'></html:text>
						&nbsp;��
					</td>
					<td align="right">
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left">
						<html:select property="xq" styleId="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>��ʼʱ�䣺
					</td>
					<td align="left">
						<html:text property="kssj"
							onclick="return showCalendar('kssj','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							style="cursor:hand;" styleId="kssj"></html:text>
						&nbsp;
						<html:text property="kssjH" size="2" styleId="kssjH" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="kssjM" size="2" styleId="kssjM" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="kssjS" size="2" styleId="kssjS" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>����ʱ�䣺
					</td>
					<td align="left">
						<html:text property="jssj"
							onclick="return showCalendar('jssj','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							styleId="jssj"></html:text>
						&nbsp;
						<html:text property="jssjH" size="2" styleId="jssjH" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="jssjM" size="2" styleId="jssjM" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="jssjS" size="2" styleId="jssjS" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>�γ�ժҪ��
					</td>
					<td colspan="3" align="left">
						<html:text property="kczy" style="width:90%" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						�����ϴ���
					</td>
					<td align=left colspan="3"> 
						<input type="file" name="uploadFile" style="width:60%" id="kk">
						&nbsp;&nbsp;
						<font color="red">(�ļ���СС��&lt;10M&gt;)</font>
					</td>
				</tr>
				<tr>
					<td align="right">
						�γ̰��ţ�
						<br>
						<font color="red"><��500��> </font>
					</td>
					<td colspan="3" align="left">
						<html:textarea property="jtap" style="width:90%" rows="6"
							styleId="jtap"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ע��
						<br>
						<font color="red"><��300��> </font>
					</td>
					<td colspan="3" align="left">
						<html:textarea property="bz" style="width:90%" rows="3"></html:textarea>
					</td>
				</tr>
			</table>
			<br />
			<div id="button" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="dataSave()"
					style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="true" name="done">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script>
				var msg = $("msg").value;
				if(msg == ""){
					alert("����ʧ��!");
				}else{
					alert(msg);
				}
			</script>
		</logic:equal>
	</body>
</html>
