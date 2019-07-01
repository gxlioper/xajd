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
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<base target="_self">
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
<body style="font-size:14px">
	<html:form action="/hzyXjchChk" method="post">
		<input type="hidden" name="pkValue" id="pkValue"
			value="<bean:write name="pkValue" scope="request" />" />
		<input type="hidden" name="xjType" id="xjType"
			value="<bean:write name="xjType" scope="request" />" />
		<div align="center" style="font: bold;font-size: 18px">
			<div id="mainTitle">
				�Ƚ�����֧�Ƽ�
			</div>
		</div>
		<table width="100%" class="tbstyle">
			<tr>
				<td align="right" nowrap>
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					<html:select name="rs" property="xn" style="width:120px"
						disabled="true" style="background-color:#FFFFFF;">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
				<td align="right" nowrap>
					<font color="red">*</font>ѧ�ڣ�
				</td>
				<td align="left">
					<html:select name="rs" property="xq" style="width:90px"
						disabled="true" style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<div align="right">
						<font color="red">*</font>����֯���ƣ�
					</div>
				</td>
				<td align="left">
					<bean:write name="rs" property="tzzmc" />
				</td>
				<td align="right">
					���������
				</td>
				<td align="left">
					<bean:write name="rs" property="tzzsj" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<div align="right">
						��֧������
					</div>
				</td>
				<td align="left">
					<bean:write name="rs" property="tzbs" />
				</td>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<html:select name="rs" property="xydm" style="width:90px"
						disabled="true" style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					��Ա����
				</td>
				<td align="left">
					<bean:write name="rs" property="tys" />
				</td>
				<td align="right" style="font: bold;color: red">
					<logic:equal value="xy" name="userType"><bean:message key="lable.xsgzyxpzxy" />��ˣ�</logic:equal>
					<logic:notEqual value="xy" name="userType">ѧУ��ˣ�</logic:notEqual>
				</td>
				<td align="left">
					<logic:notEqual value="no" name="xxSubmit">
						<html:select name="rs" property="yesNo" style="width:100px"
							styleClass="select" styleId="yesNo">
							<html:options collection="chkList" property="cn"
								labelProperty="cn" />
						</html:select>
					</logic:notEqual>
					<logic:equal value="no" name="xxSubmit">
					��<bean:message key="lable.xsgzyxpzxy" />���ͨ��
					</logic:equal>
				</td>
			</tr>

			<tr>
				<td scope="col" scope="row">
					<div align="center">
						��Ҫ�¼�
					</div>
				</td>
				<td align="left" colspan='4'>
					<bean:write name="rs" property="zysj" />
				</td>
			</tr>
		</table>
		<logic:notEmpty name="rswj">
			<fieldset>
				<legend>
					&nbsp;&nbsp;�� �� �� �� �� &nbsp;&nbsp;
				</legend>
				<table border="0" id="rsTable" width="100%" class="tbstyle">
					<thead>
						<tr>
							<td></td>
							<td>
								������
							</td>
							<td>
								����ʱ��
							</td>
						</tr>
					</thead>
					<logic:iterate id="list" name="rswj">
						<tr onmouseover="rowOnClick(this)">
							<td>
								<a href="pjpy_hzy_xjbjandwmbj.do?method=DownLoadFile&downType=xjtzz&param=<bean:write name="list" property="pk" />"
									target="_black">���� </a>
							</td>
							<td>
								<bean:write name="list" property="wjm" />
							</td>
							<td>
								<bean:write name="list" property="sqsj" />
							</td>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
			
		</logic:notEmpty>
		<div class="buttontool">
			<logic:notEqual value="no" name="xxSubmit">		
				<button class="button2" id="buttonSave" onclick="refreshForm('hzyXjchChk.do?method=xjchChk&doType=save');this.disabled=true;">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>			
			<button class="button2" onclick="printTable()">
				��ӡ����
			</button>
		</div>

	</html:form>
</body>
<script type="text/javascript">
function printTable(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint';
	var pkValue = $("pkValue").value;
	requestPath += "&titName=xjtzz";	
	requestPath += "&pkValue="+pkValue;	
	document.forms[0].action = requestPath;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
</script>
<logic:present name="done">
	<logic:equal value="true" name="done">
		<script type="text/javascript">
    alert("����ɹ���");
     window.dialogArguments.document.all("search_go").click();
    Close();
  </script>
	</logic:equal>
	<logic:equal value="false" name="done">
		<script type="text/javascript">
    alert("����ʧ�ܣ�");
  </script>
	</logic:equal>
</logic:present>
</html:html>
