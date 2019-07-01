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
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>

</head>

<body>
	<logic:present name="bcjg">
		<logic:equal value="true" name="bcjg">
			<script>
				alert("����ɹ���");
				window.dialogArguments.document.getElementById('search_go').click();
				Close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="bcjg">
			<script>
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>	
	</logic:present>
	<html:form action="hzjy_xssq.do">
		<logic:notPresent name="sh">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="shgc" key="hzjy_xssq" />
				</div>
			</div>
			<p align="center" style="font-size:15px">
				�Ǻ�������ѧ���μӺ������������
			</p>
			<table align="center" class="tbstyle" style="width:100%;height: 100%"
				id="mytab">
				<tr>

					<td width="100" height="31" align="center">
						ѧ ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="132" height="31" align="center">
						ѧ Ժ
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						�� ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td width="132" height="31" align="center">
						�� ��
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						�� ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bzxm" />
					</td>
					<td width="132" height="31" align="center">
						�� �� ��
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="bzrxm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						��ͥ�绰
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="jtdh" />
					</td>
					<td width="132" height="31" align="center">
						�ֻ�����
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="sjh" />
					</td>
				</tr>
				<!-- <tr><td align=center>�Ƿ񲹿�</td><td><html:radio property="sfbk" value="yes">&nbsp;&nbsp;&nbsp;��</html:radio>&nbsp;&nbsp;&nbsp;<html:radio property="sfbk" value="no">&nbsp;&nbsp;&nbsp;��</html:radio></td> 
		  <td></td><td></td>
		  </tr>-->
				<tr>
					<td width="540" height="264" colspan="4" valign="top">
						<p>
							�������ɣ�
						</p>
						<bean:write name="rs" property="sqly" />
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<p align="center">
							�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="center">
							&nbsp;��&nbsp; &nbsp;�� &nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>

					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" value="��ӡ"
					onclick="expTab('mytab','�Ǻ�������ѧ���μӺ������������')" />
			</div>
		</logic:notPresent>


		<logic:present name="sh">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="shgc" key="hzjy_dgsh" />
				</div>
			</div>
			<input type="hidden" name="pk" value="<bean:write name="pk"/>"/>
			<input type="hidden" name="pkValue" value="<bean:write name="pkValue"/>"/>
			<table class="tbstyle">
				<tr>
					<td width="100" height="31" align="center">
						ѧ ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="132" height="31" align="center">
						ѧ Ժ
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						�� ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td width="132" height="31" align="center">
						�� ��
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td width="100" height="31" align="center">
						�� ��
					</td>
					<td width="170" height="31">
						<bean:write name="rs" property="bzxm" />
					</td>
					<td width="132" height="31" align="center">
						�� �� ��
					</td>
					<td width="144" height="31">
						<bean:write name="rs" property="bzrxm" />
					</td>
				</tr>
				<tr>
					<td align='center'>
						��˽��
					</td>
					<td>
						<html:select property="shjg">
							<html:option value="" />
							<html:option value="δ���" />
							<html:option value="ͨ��" />
							<html:option value="��ͨ��" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td width="540" height="50%" colspan="4" valign="top">
						<p>
							�������ɣ�
						</p>
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
			</table>
			<center>
				<div class="buttontool" id="btn" 
					width="100%">
					<button class="button2" onclick="document.forms[0].action='hzjy_sh.do?tableName=hzjysqb&sh=sh&doType=save';document.forms[0].submit();">
						����
					</button>
					<button class="button2" onclick="window.close();return false;">
						�ر�
					</button>
				</div>
			</center>
		</logic:present>
	</html:form>
</body>
</html:html>
