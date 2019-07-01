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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
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
				<bean:write name="clin" />
				�Ƽ�
			</div>
		</div>
		<table width="100%" class="tbstyle">
			<tr>
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�೤��
				</td>
				<td align="left">
					<bean:write name="rs" property="bzxm" />
				</td>
			</tr>
			<tr>
				<td align="right">
					ѧ��������
				</td>
				<td align="left" id="">
					<bean:write name="rs" property="xsrs" />
				</td>
				<td align="right">
					�����Σ�
				</td>
				<td align="left">
					<bean:write name="rs" property="bzr" />
				</td>
			</tr>
			<tr>
				<td align="right" nowrap>
					ѧ�꣺
				</td>
				<td align="left">
					<html:select name="rs" property="xn" style="width:120px"
						disabled="true" style="background-color:#FFFFFF;">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
				<td align="right" nowrap>
					ѧ�ڣ�
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
				<td align="right" style="font: bold;color: red">
					<logic:equal value="xy" name="userType"><bean:message key="lable.xsgzyxpzxy" />��ˣ�</logic:equal>
					<logic:notEqual value="xy" name="userType">ѧУ��ˣ�</logic:notEqual>
				</td>
				<td align="left" id="">
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
				<logic:equal value="xy" name="userType">
					<td align="right">
						(ϵ)Ժǩ����
					</td>
					<td align="left">
						<html:text property="xyqm" name="rs" styleId="xyqm"></html:text>
					</td>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</logic:notEqual>

			</tr>
			<tr>
				<td align="right">
					��Ҫ�¼���
				</td>
				<td align="left" colspan="3">
					<bean:write name="rs" property="zysj" />
				</td>
			</tr>
			<tr>
				<td align="right">
					��������
				</td>
				<td align="left" colspan="3">
<%--					<logic:notEqual value="xy" name="userType">--%>
<%--				    --%>
<%--					    <logic:equal value="xxyj" name="rs" parameter="xxyj">--%>
<%--						<textarea name="xxyj" id="xxyj" rows="3" style="width:98%" type="_moz"/>ͬ��</textarea>--%>
<%--					    </logic:equal>	--%>
<%--					<logic:equal value="xxyj" name="rs" parameter="xxyj">	--%>
<%--					<textarea name="xxyj" id="xxyj" rows="3" style="width:98%" type="_moz"/>${xxyj}</textarea>--%>
<%--					</logic:equal>--%>
<%--					</logic:notEqual>--%>
<%--					<logic:equal value="xy" name="userType">--%>
<%--						<textarea name="xyyj" id="xyyj" rows="3" style="width:98%" type="_moz">--%>
<%--							<logic:equal value="" name="rs" property="xyyj">ͬ���Ƽ�</logic:equal>--%>
<%--							--%>
<%--				        </textarea>--%>
<%--				<logic:notEqual value="" name="rs" property="xyyj">${xyyj}</logic:notEqual>--%>
<%--					</logic:equal>--%>
				</td>
			</tr>
		</table>
		<div class="buttontool">
			<logic:notEqual value="no" name="xxSubmit">
				<button class="button2" id="buttonSave"
					onclick="refreshForm('hzyXjchChk.do?method=xjchChk&doType=save');this.disabled=true;">
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
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var pkValue = $("pkValue").value;
	requestPath += "&titName=xjbj";	
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
