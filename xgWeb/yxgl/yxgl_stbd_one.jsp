<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base target="_self" />
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/yxglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/yxglFun.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
  <body onload="document.getElementById('report_go').focus()">
  <logic:present name="dors">
    	<logic:equal value="ok" name="dors">
    		<script>
    		   alert("����ɹ���");
    		</script>
    	</logic:equal>
    	<logic:equal value="no" name="dors">
    		<script>
    		   alert("����ʧ�ܣ�");
    		</script>
    	</logic:equal>
    </logic:present>
    <html:form action="yxgl_stbd_one" method="post">
    <div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�<logic:equal value="10463" name="xxdm">ӭ�¹��� �� �������� �� ѧ������</logic:equal>
			<logic:notEqual value="10463" name="xxdm"><bean:message bundle="yxgl" key="yxgl_stbd_one" /></logic:notEqual>						
		</div>
	</div>
	<table width="100%" class="tbstyle">
		 <tr>
		    <td width="16%" height="20%"><div align="right">������:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ksh" styleId="ksh" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">ѧ��:</div></td>
		    <td width="20%" height="20%"><html:text name="rs" property="xh" styleId="xh" readonly="true"/></td>
		    <td height="60%" rowspan="3">
		    	<img alt="�޷���ʾ" height="100" width="75"  border="1" align="right"
		    		src="<bean:write name="rs" property="picture"/>"></img>
		    </td>
		  </tr>
		  <tr>
		  	<td width="16%" height="20%"><div align="right">����:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="xm" styleId="xm" readonly="true"/></td>
		  	<td width="11%" height="20%"><div align="right">ʡ��:</div></td>
		    <td width="20%" height="20%" ><html:text name="rs" property="sfmc" styleId="sfmc" readonly="true"/></td>
		  </tr>
		  <tr>
		  	<td width="11%" height="20%"><div align="right">���֤��:</div></td>
		    <td width="36%" height="20%"><html:text name="rs" property="sfzh" styleId="sfzh" readonly="true"/></td>
		  	<td width="16%" height="20%"><div align="right"></div></td>
		    <td width="20%" height="20%"></td>
		  </tr>
		  <tr>		    
		    <td height="20%"><div align="right"><bean:message key="lable.xsgzyxpzxy" />����:</div></td>
		    <td height="20%">
		       <html:select name="rs" property="xydm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="xyList" property="xydm" labelProperty="xymc" />
		       </html:select>
			</td>
			 <td height="20%"><div align="right">רҵ����:</div></td>
		    <td height="20%" colspan="2">
				<html:select name="rs" property="zydm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="zyList" property="zydm" labelProperty="zymc" />
		       </html:select>			
			</td>
		  </tr>
		  <tr>
			<td height="20%"><div align="right">�༶����:</div></td>
		    <td height="20%" >
				<html:select name="rs" property="bjdm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
		       </html:select>			
			</td>
			<td width="11%" height="20%"><div align="right">�շѷ�ʽ:</div></td>
		    <td height="20%" colspan="2">
				<html:select name="rs" property="sffs" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="sffsList" property="en" labelProperty="cn" />
		       </html:select>			
			</td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">¥����:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ldh" styleId="ldh" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">���Һ�:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="qsh" styleId="qsh" readonly="true"/></td>
		  </tr>
		   <tr>
		    <td width="11%" height="20%"><div align="right">��λ��:</div></td>
		    <td width="36%" height="20%"><html:text name="rs" property="cwh" styleId="cwh" readonly="true"/></td>
		    <td width="11%" height="20%"></td>
		    <td width="36%" height="20%" colspan="2"></td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">Ӧ��ס�޷�:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ysqsf" styleId="ysqsf" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">ʵ��ס�޷�:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="sjqsf" styleId="sjqsf" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">Ӧ��ѧ��:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ysxf" styleId="ysxf" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">ʵ��ѧ��:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="sjxf" styleId="sjxf" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td width="16%" colspan="5" height="20%" >&nbsp;</td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right"><bean:message key="lable.xsgzyxpzxy" />����</div></td>
		    <logic:equal name="rs" property="xybd" value="��" scope="request">
		    <td width="37%" height="20%"><html:text name="rs" property="xybd" styleId="xybd" style="color:#ff0033;font-weight:bold" readonly="true"/></td>
		    </logic:equal>
		    <logic:notEqual name="rs" property="xybd" value="��" scope="request">
		     <td width="37%" height="20%"><html:text name="rs" property="xybd" styleId="xybd" style="font-weight:bold" readonly="true"/></td>
		    </logic:notEqual>
		    <td width="16%" height="20%"><div align="right">ҽԺ����</div></td>
		     <logic:equal name="rs" property="yybd" value="��" scope="request">
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="yybd" styleId="yybd" style="color:#ff0033;font-weight:bold" readonly="true"/></td>
		    </logic:equal>
		    <logic:notEqual name="rs" property="yybd" value="��" scope="request">
		     <td width="36%" height="20%" colspan="2"><html:text name="rs" style="font-weight:bold" property="yybd" styleId="yybd" readonly="true"/></td>
		    </logic:notEqual>
		  </tr>
		  <tr>
		  	<td width="16%" height="20%">
		  		<div align="right">
		  			<logic:equal value="10463" name="xxdm">��������</logic:equal>
		  			<logic:notEqual value="10463" name="xxdm">ʳ�ñ���</logic:notEqual>
		  		</div>
		  	</td>
		    <logic:equal name="rs" property="stbd" value="��" scope="request">
		    <td width="37%" height="20%"><html:text name="rs" property="stbd" styleId="stbd" style="color:#ff0033;font-weight:bold" readonly="true"/></td>
		    </logic:equal>
		    <logic:notEqual name="rs" property="stbd" value="��" scope="request">
		     <td width="37%" height="20%"><html:text name="rs"style="font-weight:bold" property="stbd" styleId="stbd" readonly="true"/></td>
		    </logic:notEqual>
		    <td width="16%" height="20%"><div align="right">���ᱨ��</div></td>
		     <logic:equal name="rs" property="ssbd" value="��" scope="request">
		   <td width="36%" height="20%" colspan="2"><html:text name="rs" property="ssbd" styleId="ssbd" style="color:#ff0033;font-weight:bold" readonly="true"/></td>
		    </logic:equal>
		    <logic:notEqual name="rs" property="ssbd" value="��" scope="request">
		     <td width="36%" height="20%" colspan="2"><html:text name="rs" style="font-weight:bold" property="ssbd" styleId="ssbd" readonly="true"/></td>
		    </logic:notEqual>
		  </tr>
		</table>
		<div id="tmpdiv"></div>
		<div class="buttontool">
		<logic:notPresent name="modify">
       <button class="button2" 
       			onclick="document.forms[0].action='yxgl_stbd_one.do?doType=add';document.forms[0].submit();getParentPageSearch();">
          �ύ��Ϣ
       </button>
       </logic:notPresent>
       <logic:present name="modify">
         <button class="button2" id="report_go" onclick="doBD('st')">
        	<!-- 
        		onkeydown="if(event.keyCode==13)report_go.click();" 
        		onclick="document.forms[0].action='xsbd_one.do?doType=st';
        		document.forms[0].submit();
        		alert('�����ɹ�');
        		window.dialogArguments.document.getElementById('search_go').click();
        		window.close()
        	 --> 
           �� ��
       </button>
       </logic:present>       
       &nbsp;&nbsp;&nbsp;&nbsp;       
       <button class="button2" onclick="window.close();return false;">
          �� ��
       </button>
    </div>
    </html:form>
    <script>
       window.onunload = function(){
          window.dialogArguments.document.getElementById('search_go').focus();
       }
    </script>
  </body>
</html:html>
