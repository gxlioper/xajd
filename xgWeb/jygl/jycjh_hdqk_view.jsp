<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
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
	<base target="_self">
	<script language="javascript">
	</script>
	<body>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlxhhd" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ��ҵ�ٽ��� - ������¼ - �鿴������¼
				</div>
			</div>		
				<table  class="tbstyle" align="center" style="width:100%">
					<tr style="height:22px">
							<td align="right" colspan="2">
								<font color="red">*</font>�� �⣺
							</td>
							<td colspan="6" align="left">
								<html:text  name="rs" style="width:98%" property="zt" styleId="zt" readonly="true"/>
							</td>
					</tr>
				<tr style="height:22px">
						<td align="right" colspan="2">
							<font color="red">*</font>�� �� �� ʽ��
						</td>
						<td align="left" colspan="2">
							<html:select name="rs" property="hdxs" style="width:145px" styleId="hdxs" disabled="true">
								<html:options collection="hdxsList" property="dm"
										labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" colspan="2">
							�� �� �� ʽ��
						</td>
						<td align="left" colspan="2" >
							<html:text name="rs" property="qthdxs" styleId="qthdxs" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<font color="red">*</font>�� �㣺
						</td>
						<td align="left" colspan="2">
							<html:text  name="rs" property="dd" styleId="dd" readonly="true"/>
						</td>
						<td align="right" colspan="2">
							<font color="red">*</font>�� �� �� �ڣ�
						</td>
						<td colspan="2">
							<html:text name="rs" style="cursor:hand;" styleId="dateF"
									property="rq" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							�� ʼ ʱ �䣺
						</td>
						<td align="left" colspan="2">
							<html:text  name="rs" property="kssj" styleId="kssj" readonly="true"/>
						</td>
						<td align="right" colspan="2">
							�� �� ʱ �䣺
						</td>
						<td align="left" colspan="2">
							<html:text  name="rs" property="jssj" styleId="jssj" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<font color="red">*</font>�� �� �ˣ�
						</td>
						<td align="left" colspan="2">
							<html:text  name="rs" property="zcr" styleId="zy" readonly="true"/>
						</td>
						<td align="right" colspan="2">
							<font color="red">*</font>ѧ ����
						</td>
						<td align="left" colspan="2">
							<html:text  name="rs" property="xs"  readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<font color="red">*</font>�� �� ѧ ����
						</td>
						<td colspan="6" align="left">
								<html:text  name="rs" style="width:98%" property="cyxs" styleId="cyxs" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							<font color="red">*</font>�� �� ѧ �� �� ����
						</td>
						<td align="left" colspan="6">
							<html:text  name="rs" property="rs" styleId="rs" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" colspan="2"><font color="red">*</font> �� �� �� ¼�� </td>
                    	<td colspan="6" align="left"><html:textarea name="rs" rows="5"  style="width:98%" property="hdjl" styleId="a" readonly="true"/></td>
					</tr>			
				   <tr>
				  		<td align="right" colspan="2"><font color="red">*</font> �� �� Ч ���� </td>
                    	<td colspan="6" align="left"><html:textarea name="rs" rows="5"  style="width:98%" property="hdxg" styleId="a" readonly="true"/></td>
				  </tr>
				</table>
				
				<div id="tmpdiv"></div>	
				<div class="buttontool" align="center" style="width:100%">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="Close();return false;" style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
		</html:form>
	</body>
</html>
