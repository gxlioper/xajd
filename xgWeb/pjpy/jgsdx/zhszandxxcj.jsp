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
	function validata(){
		if ((event.keyCode>=48)&&(event.keyCode<=57))
			{
				event.returnValue=true;	
			}
			else
			{
				event.returnValue=false;
				alert("����Ϊѧ���ɼ�������ֻ��������������");	
			}	
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<html:form action="/pjpyjgsdxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="pjpyjgsdx" key="pjpy_jgsdx_zyjxjsb" />
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="${pkValue }" />
			<input type="hidden" name="jxjdm" id="jxjdm" value="${jxjdm }"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:18px">
					<td align="right" style="width: 25%">
						ѧ�ţ�
					</td>
					<td align="left" style="width:25%">
						<bean:write name="rs"  property="xh"/>
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs"  property="xh"/>"/>
					</td>
					<td align="right">
						�Ƿ�ʦ����
					</td>
					<td align="left">
						<html:select property="sfsf" styleId="sfsf" style="width:70px" styleClass="select">
							<html:option value=""></html:option>
							<html:option value="��" key="��"></html:option>
							<html:option value="��" key="��"></html:option>
						</html:select>
					</td>
					
				</tr>
				<tr style="height:18px">
					<td align="right" >
						������
					</td>
					<td align="left">
						<bean:write name="rs"  property="xm"/>
					</td>
					<td align="right">
						ѧϰ�ɼ�������
					</td>
					<td align="left">
						<html:text property="xxcjpm" styleId="xxcjpm" styleClass="inputtext" onkeypress="validata(this)" maxlength="4" style="width:70px"></html:text>
					</td>
				</tr>
				<tr style="height:18px">
					<td align="right" >
						ѧ�꣺
					</td>
					<td align="left" >
						<html:select property="xn" disabled="true" style="width:90px" styleId="xn" styleClass="select">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
						<input type="hidden" name="xn" id="xn" value="${xn }"/>
					</td>
					<td align="right" style="width: 25%">
						�ۺ����ʲ���������
					</td>
					<td align="left" style="width:25%">
						<html:text property="zhszpm" styleId="zhszpm" onkeypress="validata(this)" styleClass="inputtext" maxlength="4" style="width:70px"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right"> 
						ѧ�ڣ�
					</td>
					<td colspan="">
						<html:select property="xq" style="width:90px" styleId="xq" styleClass="select">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select property="shzt" styleId="shzt">
							<html:option value=""></html:option>
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" >
						��ע��
					</td>
					<td align="left" style="" colspan="3">
						<html:textarea property="bz" style="width:100%" rows="4" styleClass="inputtext" styleId="bz"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:equal value="yes" name="fdywritable">
					<button class="button2"
					onclick="refreshForm('zhszandxxcjsave.do')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>	
			</div>
		</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
			<logic:equal value="noview" name="result">
			<script>
				alert("����ʧ��");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
