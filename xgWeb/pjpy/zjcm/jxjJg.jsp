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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
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
	<base target="_self">
	<script type="text/javascript">
	function xnjxjSh(shzt){
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh&doType=save&shzt="+shzt;
		showTips('���������У���ȴ�......');
		$("buttonTg").disabled=true;
		$("buttonBtg").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function jxjPrint(){
	    var url = "/xgxt/zjcm_xnjxj.do?method=jxjDjb";
	    var xh = $("xh").value;
	    var jxjmc = $("jxjmc").value; 
		url+="&xh="+xh;
		url+="&jxjmc="+jxjmc;
	  
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/zjcm_xnjxj" method="post">
			<input type="hidden" name="pk" id="pk" value="${pk}"/>
			<div class="title">
				<div class="title_img">
					��ǰ����λ�ã�<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="4">
								��ѧ���걨���
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="20%">
							ѧ�ţ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xh"/>
							<input type="hidden" id="xh" value="<bean:write name="rs" property="xh"/>"/>
						</td>
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							<html:select property="xn" style="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							������
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" width="20%">
							����ѧ�ڣ�
						</td>
						<td align="left" width="30%">
							<html:select property="xq" style="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�Ա�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" width="20%">
							��ѧ��
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="jxjmc"/>
							<input type="hidden" id="jxjmc" value="<bean:write name="rs" property="jxjmc"/>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="dyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							רҵ���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�༶���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="tyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							��Ƿѧ�ѣ�
						</td>
						<td align="left" width="30%">
							<html:select property="tqxf" disabled="true">
								<html:option value="no">��</html:option>
								<html:option value="yes">��</html:option>
							</html:select>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="nlf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�������Ŀ����
						</td>
						<td align="left" width="30%">
							<html:text name="rs" property="bjgkms" style="width:10%" readonly="true"
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="1"/>��
						</td>
						<td align="right" width="20%">
							�ۺϷ֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zhf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							Ӣ����������
						</td>
						<td align="left" width="30%">
							<html:select name="rs" property="yygjqk" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="yyList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="20%">
							�༶������
						</td>
						<td align="left" width="30%">
							��<bean:write name="rs" property="zhfpm"/>��
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							��������������
						</td>
						<td align="left" width="30%">
							<html:select name="rs" property="jsjgjqk" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="jsjList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="20%">
							���������
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="kkqk"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							����ҽ�ѧ�������
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="hgjjxjqk" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<logic:notEmpty name="cfqk">
					<tr>
						<td align="center" colspan="4">
							��УΥ�������
						</td>
					</tr>
					<logic:iterate name="wjcfList" id="s" indexId="index">
						<tr>
							<td align="left" colspan="4">
								<bean:write name="s" property="xn" />&nbsp;
								<bean:write name="s" property="xqmc" />&nbsp;
								<bean:write name="s" property="cflbmc" />&nbsp;
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="xw">
					<tr>
						<td align="right" width="20%">
							����Ա�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					</logic:empty>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							ѧУ�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
						<button class="button2"
							onclick="jxjPrint()"
							id="buttonPrint"
							style="width:80px">
							��ӡ
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="Close();return false;"
							id="buttonClose"
							style="width:80px">
							�ر�
						</button>
						</td>
					</tr>
				</table>
			</fieldset>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
