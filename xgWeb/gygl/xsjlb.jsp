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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/detailData" method="post">
		<input type="hidden" id="pk" name="pk" value="xh||xn||xq||sj" />
		<input type="hidden" id="pkValueB" name="pkValueB" value="<bean:write name="tabName" scope="request"/>" />
		<input type="hidden" id="pkValueA" name="pkValueA" value="<bean:write name="pkValueA" scope="request"/>" />		
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ԣ�������� - ��ϸ��Ϣ�鿴 - ѧ��������Ϣ
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
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								ѧ������
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="true"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />						
						</td>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true" />
						</td>						
					</tr>					
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">						
						<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>							
						</td>											
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true" />
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">						
						<html:text name="rs" property="xq" value="${rs.xq}" readonly="true"></html:text>							
						</td>				
					</tr>					
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true" />
						</td>
						<td align="right">
							¥�����ƣ�
						</td>
						<td align="left">
							<input type="text" value="${rs.ldmc}" readonly="true"/>
							<html:hidden name="rs" property="lddm"></html:hidden>
						</td>				
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							���Һţ�
						</td>
						<td align="left">						
						<html:text name="rs" property="qsh" readonly="true" style="width: 120px"></html:text>							
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true" />
						</td>
						<td align="right">
							����ʱ�䣺									
						</td>
						<td align="left">							
						<html:text name="rs" property="sj" style="width: 120px" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							�������ݣ�
						</td>
						<td align="left">
							<logic:present name="showhzy">
								<html:select  name ="rs" property="jlnr" style="width: 120px" onchange="getGyJcXf_Modi('jl')">
								    <html:option value=""></html:option>
								    <html:options collection="xsjlList" property="jldm" 
								          labelProperty="jlmc"/>
								</html:select>
							</logic:present>
							<logic:notPresent name="showhzy">
								<html:text name='rs' property="jlnr" styleId="jlnr" />
							</logic:notPresent>
						</td>
						<td align="right">
							�����ӷ֣�
						</td>
						<td align="left">							
							<html:text name="rs" property="ryjf" style="width: 120px" styleId="ryjf" onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)"/>
						</td>			
					</tr>
					<tr>
						<td align="right">
							��ע��									
						</td>
						<td align="left" colspan="3">
							<textarea rows="5" cols="65" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			
						</td>					
					</tr>
				</table>
				<div class="buttontool" align="center">	
				<logic:notEqual  value="view" name="doType">			
					<button class="button2" onclick="hh_zrssave();return false;" style="width:80px" id="buttonSave">
						�� ��
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>	
			<logic:equal value="ok" name="done">
				<script type="text/javascript">
	            alert("����ɹ���");
		        Close();
		        window.dialogArguments.document.getElementById('search_go').click();
	    </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
	           alert("����ʧ�ܣ�");
		       Close();
		       window.dialogArguments.document.getElementById('search_go').click();
	    </script>
	    </logic:equal>	
		</html:form>
  </body>
 <script type="text/javascript">
   function saveData(){
	var xh = document.forms[0].xh.value;
	var pk = document.forms[0].pk.value;
	var pkValueA = document.forms[0].pkValueA.value;
	var pkValueB = document.forms[0].pkValueB.value;
	var url = "/xgxt/detailData.do?act=";
	url += "save";
	url += "&xh=";
	url += xh;
	url += "&pk=";
	url += pk;
	url += "&pkValueA=";
	url += pkValueA;
	url += "&pkValueB=";
	url += pkValueB;
	refreshForm(url);
	$("buttonSave").disabled=true;
  }
</script>
</html>
