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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/Ry_manager" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ������Ū - ѧ����������
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk" value="<bean:write name='pk' scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name='pkValue' scope="request"/>" />
					<table width="85%" id="rsT" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>����ѧ�����</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									ѧ�ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</td>
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right">
									�꼶��
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�༶��
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right">
									�ֻ����룺
								</td>
								<td align="left">
									<bean:write name='rs' property="sjh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									���ҵ绰��
								</td>
								<td align="left">
									<bean:write name='rs' property="qsdh" />
								</td>
								<td align="right">
									¥�����ƣ�
								</td>
								<td align="left">
									<bean:write name='rs' property="ldmc" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									���Һţ�
								</td>
								<td align="left">
									<bean:write name='rs' property="qsh" />
								</td>
								<td align="right">
									�������ࣺ
								</td>
								<td align="left">
									<bean:write name='rs' property="sqzl" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									����ְ��
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="sqrzw" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									��Ҫ�¼���
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="zysj" />
								</td>
							</tr>
							<logic:equal value="lz" name="lz">
							<tr style="height:22px;" id="lztj">
								<td align="right">
									¥����&nbsp;&nbsp;&nbsp;<br>�������
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="lztjyj" rows="4" cols="50"/>
								</td>
							</tr>
							</logic:equal>
							
							<logic:equal value="teacher" name="userOnLine" scope="session">
							<tr style="height:22px;" id="lztj">
								<td align="right">
									¥����&nbsp;&nbsp;&nbsp;<br>�������
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="lztjyj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�Ƿ�ͨ����
								</td>
								<td align="left">
									<html:select name="rs" property="sfsh">
										<html:options collection="chkList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<td align="right">
								</td>
								<td align="left">
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									ѧУ�����
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="xxyj" rows="4" cols="50"/>
								</td>
							</tr>
							</logic:equal>
							<logic:present name="ptxs">
								<tr style="height:22px">
								<td align="center" colspan="4">
									<font color="red" size="3" >��û��Ȩ��</font>
								</td>
							</tr>
							</logic:present>
						</table>
				<logic:notPresent name="ptxs">
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="SaveCheck('/xgxt/CheckSave.do');return false;">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;">
						�� ��
					</button>
				</div>
				</logic:notPresent>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</body>
</html>
