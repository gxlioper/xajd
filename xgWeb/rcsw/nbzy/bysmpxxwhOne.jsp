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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzympgc" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					�ճ�����-��ҵ����Ƭ��Ϣ����-�����޸�
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<fieldset>
					<legend>
						��ҵ����Ƭ��Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm"/>
							</td>
							<td align="right">
								<font color="red">*</font>�Ա�
							</td>
							<td align="left">
								<html:select name='rs' property="xb" style="width:90px" styleId="xb" >
										<html:option value=""></html:option>
										<html:option value="��"></html:option>
										<html:option value="Ů"></html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:select name = "rs" property="xydm" style="width:180px" styleId="xy" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>�꼶��
							</td>
							<td align="left">
								<html:select name = "rs" property="nj" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>���˵绰��
							</td>
							<td align="left">
								<html:text name='rs' property="grdh" styleId="grdh"/>
							</td>
							<td align="right">
								��λ
							</td>
							<td align="left">
								<html:text name='rs' property="dw" styleId="dw"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ַ��
							</td>
							<td align="left">
								<html:text name='rs' property="jtdz"/>
							</td>
							<td align="right">
								��λ�绰
							</td>
							<td align="left">
								<html:text name='rs' property="dwdh"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								��λ��
							</td>
							<td align="left">
								<html:text name='rs' property="gw"/>
							</td>
							<td align="right">
								ְ��
							</td>
							<td align="left">
								<html:text name='rs' property="zw"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								������ˮƽ��
							</td>
							<td align="left">
								<html:text name='rs' property="nsrsp"/>
							</td>
							<td align="right">
								�Ƿ�Կ�
							</td>
							<td align="left">
								<html:select name='rs' property="sfdk" style="width:90px" styleId="sfdk" >
										<html:option value=""></html:option>
										<html:option value="��"></html:option>
										<html:option value="��"></html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ڼ��ξ�ҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="djcjy"/>
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('nbzympgc.do?method=saveBysmpOne','xm-xb-xy-nj-grdh');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
