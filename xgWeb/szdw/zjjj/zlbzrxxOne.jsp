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
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zjjj_xsgzdw" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<input type="hidden" name="xyV" value="<bean:write name = "rs" property="xydm" scope="request"/>"/>
				<input type="hidden" name="zyV" value="<bean:write name = "rs" property="zydm" scope="request"/>"/>
				<input type="hidden" name="bjV" value="<bean:write name = "rs" property="bjdm" scope="request"/>"/>
				<fieldset>
					<legend>
						�����������Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />:
							</td>
							<td align="left">
									<html:select name = "rs" property="xydm" style="width:180px" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>רҵ��
							</td>
							<td align="right">
									<html:select name = "rs"  property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>�༶:
							</td>
							<td align="left">
									<html:select name = "rs" property="bjdm" style="width:120px" styleId="bj" onchange="refreshForm('/xgxt/zjjj_xsgzdw.do?method=zlbzrxxOne')" >
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>�������
							</td>
							<td align="right">
									<html:select name = "rs" property="zlsf" style="width:180px" onchange="refreshForm('/xgxt/zjjj_xsgzdw.do?method=zlbzrxxOne')" >
										<html:option value="����ѧ��">����ѧ��</html:option>
										<html:option value="�о���">�о���</html:option>
										<html:option value="����">����</html:option>
									</html:select>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<font color="red">*</font>����:
							</td>
							<logic:equal name = "sfbbxs" value="yes" >
							<td align="left">
								<html:select name = "rs" property="xm" style="width:120px" styleId="xm">
										<html:option value=""></html:option>
										<html:options collection="bjxsList" property="xm"
											labelProperty="xm" />
								</html:select>
							</td>
							</logic:equal>
							<logic:notEqual name = "sfbbxs" value="yes" >
							<td align="left">
								<html:text name = "rs" property="xm" />
							</td>
							</logic:notEqual>
							<td align="right">
								��ϵ�绰:
							</td>
							<td align="left">
								<html:text name = "rs" property="lxdh" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								�ֹ����� 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='fgsw' style="width:99%"
									rows='10' />
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
						onclick="szsxDataDoSave('zjjj_xsgzdw.do?method=saveZlbzrxxOne','zgh-xm');"
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
