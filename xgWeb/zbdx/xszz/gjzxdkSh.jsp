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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
		function validate(){
			var userType= document.all['userType'].value;
			if("xy"==userType)
			{
				var xyshyj = document.getElementById('xyshyj').value;
				if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
				}
			}
			if("xx"==userType)
			{
				var xxshyj = document.getElementById('xxshyj').value;
				if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
				}
			}
			 return true;
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/zbdx_xszz.do?method=gjzxdkShSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ������ѧ�������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" name="nd"
				value="<bean:write name="map"  property="nd"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="map"  property="xh"/>" />
			<input type="hidden" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<table width="98%" align="center" class="tbstyle">
				<tr>
					<td width="11%" height="31">
						����
					</td>
					<td>
						&nbsp;
						<bean:write name="map" property="xm" />
						&nbsp;
					</td>
					<td height="31">
						�Ա�
					</td>
					<td>
						&nbsp;
						<bean:write name="map" property="xb" />
						&nbsp;
					</td>
					<td height="31">
						����
					</td>
					<td>
						&nbsp;
						<bean:write name="map" property="mz" />
						&nbsp;
					</td>
					<td height="31">
						��������
					</td>
					<td>
						&nbsp;
						<bean:write name="map" property="csrq" />
						&nbsp;
					</td>
					<td height="31">
						��ѧʱ��
					</td>
					<td>
						&nbsp;
						<bean:write name="map" property="rxny" />
						&nbsp;
					</td>

				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td height="31">
							ѧ ��
						</td>
						<td height="31">
							&nbsp;
							<bean:write name="map" property="xh" />
							&nbsp;
						</td>
					</logic:equal>
					<td height="31">
						ѧ��
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="xz" />
						&nbsp;
					</td>
					<td height="31">
						ѧ�����
					</td>
					<td height="31">
						&nbsp;
						<bean:write name="map" property="xslb" />
						&nbsp;
					</td>
					<td height="31">
						�������
					</td>
					<td height="31">
						&nbsp;
						<bean:write name="map" property="hklb" />
						&nbsp;
					</td>

				</tr>
				<tr>
					<td height="31">
						Ժ ϵ
					</td>
					<td height="31">
						&nbsp;
						<bean:write name='map' property="xy" />
						&nbsp;
					</td>
					<td height="31">
						רҵ
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="zymc" />
						&nbsp;
					</td>
					<td height="31">
						�༶
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="bjmc" />
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						���֤����
					</td>
					<td height="31" colspan="9">
						&nbsp;
						<bean:write name="map" property="sfzh" />
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						��סַ
					</td>
					<td height="31" colspan="5">
						&nbsp;
						<bean:write name="map" property="xzz" />
						&nbsp;
					</td>
					<td height="31">
						ѧ���绰
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="xsdh" />
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						��ͥ��ַ
					</td>
					<td height="31" colspan="5">
						&nbsp;
						<bean:write name="map" property="jtdz" />
						&nbsp;
					</td>
					<td height="31">
						��������
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="yzbm" />
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						��ͥ�˿�
					</td>
					<td height="31">
						&nbsp;
						<bean:write name="map" property="jtrk" />
						&nbsp;
					</td>
					<td height="31">
						������
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="nsr" />
						&nbsp;Ԫ
					</td>
					<td height="31">
						��ͥ�绰
					</td>
					<td height="31" colspan="3">
						&nbsp;
						<bean:write name="map" property="jtdh" />
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="31">
						������
					</td>
					<td height="31" colspan="7">
						&nbsp;
						<bean:write name="map" property="dkje" />
						&nbsp;Ԫ
					</td>
					<td height="31">
						��˽��
					</td>
					<td height="31">
						&nbsp;
						<logic:equal name="userType" value="xx">
							<select name="xxsh"
								value="<bean:write name="map" property="xxsh"/>">
								<option value="δ���">
									δ���
								</option>
								<option value="δͨ��">
									δͨ��
								</option>
								<option value="ͨ��">
									ͨ��
								</option>
							</select>
						</logic:equal>
						<logic:notEqual name="userType" value="xx">
							<select name="xysh"
								value="<bean:write name="map" property="xysh"/>">
								<option value="δ���">
									δ���
								</option>
								<option value="δͨ��">
									δͨ��
								</option>
								<option value="ͨ��">
									ͨ��
								</option>
							</select>
						</logic:notEqual>
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td>
							<div align="left">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>

						<td colspan="9">
							<html:textarea rows="5" style="width:100%" name="map"
								property="xyshyj" styleId="xyshyj" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td>
							<div align="left">
								ѧУ������
							</div>
						</td>
						<td colspan="9">
							<html:textarea rows="5" style="width:100%" name="map"
								property="xxshyj" styleId="xxshyj" />
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="if (validate()) {refreshForm('zbdx_xszz.do?method=gjzxdkShSave');Close();window.dialogArguments.document.getElementById('search_go').click();}"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
<script type="text/javascript">
<logic:equal name="userType" value="xx">
	$('xxsh').value="<bean:write name="map" property="xxsh"/>";
</logic:equal>
<logic:notEqual name="userType" value="xx">
	$('xysh').value="<bean:write name="map" property="xysh"/>";
</logic:notEqual>
</script>

</html>