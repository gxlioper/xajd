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
		<%@include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/XsGyXxGl.do?method=xsGyXxView" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				
				<fieldset>
					<table width="100%" class="tabform">
					<tbody>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh"/>								
							</td>
							<td align="right">
								<font color="red">*</font>������:
							</td>
							<td align="left">
								<html:text name='rs' property="ssbh" readonly="readonly"
									styleId="xh"/>	
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<td align="right">
								<font color="red">*</font>��λ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="cwh" readonly="readonly"
									styleId="xh"/>	
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								��סʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="rzrq" styleId="rzrq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								�˷�ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="jzrq" styleId="jzrq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<logic:present name="showhzy">
							<td align="right">
								ס�޷ѣ�
							</td>
							<td align="left">
								<html:text name='rs' property="zsf" styleId="zsf" maxlength="10" onblur="chkInput(this,event)" />
							</td>
							</logic:present>
							<logic:notPresent name="showhzy">
							<td align="right">
							</td>
							<td align="left">
							</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<logic:present name="showhzy">
							<td align="right">
								���ӷѣ�
							</td>
							<td align="left">
								<html:text name='rs' property="dsjssf" styleId="dsjssf" maxlength="10" onblur="chkInput(this,event)" />
							</td>
							</logic:present>
							<logic:notPresent name="showhzy">
							<td align="right">
							</td>
							<td align="left">
							</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<logic:present name="showhzy">
							<td align="right">
								��Ԣ�����
							</td>
							<td align="left">
								<html:text name='rs' property="gyqk" styleId="gyqk"/>
							</td>
							</logic:present>
							<logic:notPresent name="showhzy">
							<td align="right">
							</td>
							<td align="left">
							</td>
							</logic:notPresent>
						</tr>
						<tr align="left">
							<td align="right">
								��ע��
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
