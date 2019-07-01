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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/addkqxx.do" method="post">
		<html:text  property="xn_id" styleId="xn_id" style="display:none;"></html:text>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά�� - ���ӿ�����Ϣ
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name='pkValue' />" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<html:text name="rs" property="xn" styleId="xn" readonly="true"></html:text>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="xq" styleId="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶���ƣ�
					</td>
					<td align="left">
						<html:hidden name="rs" property="bjdm"/>
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
					<td align="right">
						�γ����ƣ�
					</td>
					<td align="left">
						<html:hidden name="rs" property="kcdm"/>
						<html:text name="rs" property="kcmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>ʵ��������
					</td>
					<td align="left">
						<html:hidden name="rs" property="ydrs" styleId="ydrs"/>
						<html:text  property="sdrs" onblur="CheckData(this)"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>���������
					</td>
					<td align="left">
						<html:text  property="qjrs" onblur="CheckData(this)"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
				
					</td>
					<td align="left">
						
					</td>
					<td align="right">
						<font color="red">*</font>������ڣ�
					</td>
					<td align="left">
						<html:text style="cursor:hand; width:85px;" styleId="dateF"
							property="rq"
							onclick="return showCalendar('dateF','yy-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<logic:notPresent name="isSZXXZYJSXY">								
				<tr style="height:22px">				
					<td align="right">
						<font color="red">*</font>���ʱ�䣨��ʼʱ�䣩��
					</td>
					<td align="left">
						<html:text  property="kssj" onblur="check_time('kssj')" styleId="kssj"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>���ʱ�䣨����ʱ�䣩��
					</td>
					<td align="left">
						<html:text  property="jssj" onblur="check_time('jssj')" styleId="jssj"></html:text>
					</td>				
				</tr>
				</logic:notPresent>
				<logic:present name="isSZXXZYJSXY">
				     <html:hidden property="kssj" styleId="kssj" value="08:00"/>
				     <html:hidden property="jssj" styleId="jssj" value="09:00"/>
				</logic:present>			   
				<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea  property="bz" cols="50"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="Savedata('sdrs-qjrs-rq-kssj-jssj','/xgxt/saveKqxx.do');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
