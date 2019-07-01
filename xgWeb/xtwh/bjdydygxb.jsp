<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <base target="_self" />
  <head>
    <title><bean:message  key="lable.title"/></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
    <link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
    <script type="text/javascript" src="js/function.js"></script>
  </head>
  
  <body>
  <div class="title"> 
    <div class="title_img" id="title_m">��ǰ����λ�ã�ϵͳά�� - ����ά�� - ˼�����</div> 
  </div> 
  <logic:present name="sfbc">
    <logic:equal value="true" name="sfbc">
    <script>
      alert("����ɹ���");
      ///window.dialogArguments.document.forms.action="/xgxt/code_man.do?act="+window.parent.document.forms.codeType.value;
      ///window.parent.document.forms.submit();
  	</script>
  	</logic:equal>
  	<logic:equal value="false" name="sfbc">
    <script>
      alert("����ʧ�ܣ�");
  	</script>
  	</logic:equal>
  </logic:present>
  <html:form action="/bjdydygxb.do">
    <fieldset>
        <legend>
           ��������ά��
        </legend>
		   <table class="tbstyle" style="width:100%">
		   	 <tr>
		   	 	<td><bean:message key="lable.xsgzyxpzxy" />��</td>
		   	 	<td>
		   	 		<html:select  property="xydm" onchange="document.forms[0].action='/xgxt/bjdydygxb.do';document.forms[0].submit();">
		   	 			<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
		   	 		</html:select>
		   	 	</td>
		   	 </tr>
		   	 <tr>
		   	 	<td>רҵ��</td>
		   	 	<td>
		   	 		<html:select property="zydm" onblur="document.forms[0].action='/xgxt/bjdydygxb.do';document.forms[0].submit();">
		   	 			<html:options collection="zyList"  property="zydm" labelProperty="zymc"/>
		   	 		</html:select>
		   	 	</td>
		   	 </tr>
		     <tr>
		     	<td>�༶��</td>
		     	<td>
		     		<html:select property="bjdm">
		   	 			<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
		   	 		</html:select>
		     	</td>
		     </tr>
		     <tr><td>��Ա��ϵ�˴��룺</td><td><html:text name="rs" property="dylxrdm" styleId="dylxrdm" /></td></tr>
		     <tr><td>��Ա��ϵ��������</td><td><html:text name="rs" property="dylxrxm" styleId="dylxrxm" /></td></tr>
		     <tr><td>��Ա��ϵ�˰칫�ң�</td><td><html:text name="rs" property="dylxrbgs" styleId="dylxrbgs" /></td></tr>
		     <tr><td>��Ա��ϵ�˰칫�ҵ绰��</td><td><html:text name="rs" property="dylxrdh" styleId="dylxrdh" /></td></tr>
		     <tr><td>��Ա��ϵ���ֻ���</td><td><html:text name="rs" property="dylxrsjh" styleId="dylxrsjh" /></td></tr>
		   </table>
	</fieldset>	
	<div align=center class="buttontool">
	   <button type="button" class="button2" onclick="document.forms[0].action='/xgxt/bjdydygxb.do?act=save';document.forms[0].submit();">
			&nbsp;&nbsp;����&nbsp;&nbsp;
	   </button>
	   &nbsp;&nbsp;&nbsp;&nbsp;
	   <button type="button" class="button2" onclick="window.close();return false;">
			&nbsp;&nbsp;�ر�&nbsp;&nbsp;
	   </button>
	</div>     
  </html:form>
  
  </body>
</html:html>
