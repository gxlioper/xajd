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
		<script language="javascript" src="wjcf/szzy/szzyjs/szzyjs.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szzykqxxwh.do" method="post">
		<html:text  property="xn_id" styleId="xn_id" style="display:none;"></html:text>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά�� - ѧ��������Ϣ������
				</div>
			</div>
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name='pkValue' scope="request" />" />
				<input type="hidden" name="pkValue2" id="pkValue2"
				value="<bean:write name='pkValue2' scope="request"/>" />
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
						Ӧ��������
					</td>
					<td align="left">
						<html:text property="ydrs" name="rs" readonly="true"></html:text>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>ʵ��������
					</td>
					<td align="left">
						<html:hidden name="rs" property="ydrs" styleId="ydrs"/>
						<html:text name="rs" property="sdrs" readonly="true" styleClass="inputtext" ></html:text>
					</td>
					<td align="right">
						���������
					</td>
					<td align="left">
						<html:text name="rs" property="qjrs" readonly="true" styleClass="inputtext"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����,�¼٣�
					</td>
					<td align="left">
						<html:text name="rs" property="bjsj" styleId="bjsj" readonly="true" styleClass="inputtext"></html:text>
					</td>
					<td align="right">
						���٣�
					</td>
					<td align="left">
						<html:text name="rs" property="gj" styleId="gj" readonly="true" styleClass="inputtext"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����Ա��
					</td>
					<td>
						<html:text name="rs" property="kqy" styleId="kqy" readonly="true" styleClass="inputtext" styleClass="inputtext" maxlength="15"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>������ڣ�
					</td>
					<td align="left">
						<html:text name="rs" style="inputtext" styleId="rq"
							property="rq"
							readonly="true"/>
					</td>
				</tr>							
				<tr style="height:22px">				
					<td align="right">
						<font color="red">*</font>��鿪ʼʱ�䣺
					</td>
					<td align="left">
						<html:text name="rs" property="kssj" readonly="true" styleId="kssj" styleClass="inputtext" maxlength="5"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>������ʱ�䣺
					</td>
					<td align="left">
						<html:text name="rs" property="jssj" readonly="true" styleId="jssj" styleClass="inputtext" maxlength="5"></html:text>
					</td>				
				</tr>		   
				<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="bz" cols="56" rows="3" styleClass="inputtext"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
