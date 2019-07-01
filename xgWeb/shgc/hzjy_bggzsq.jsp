<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <title><bean:message key="lable.title" /></title>
		<%
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
		%>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="������� zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>	
	
  </head>
  
  <body>
  	<div class="title">
		<div class="title_img" id="title_m">
			<bean:message bundle="shgc" key="hzjy_bgsq"/>
		</div>
	</div>
	<html:form action="/hzjy_bggzsq">
	<input type="hidden" name="url" id="url" value="/shgc/hzjy_bggzsq.jsp"/>
	<input type="hidden" name="getStuInfo" id="getStuInfo" value="xh-xm-xymc-zymc-bjmc" />
  <logic:equal value="true" name="sfksq">
    <p align="center"><strong style="font-size:15px">�����ѧ��������������λ�����</strong>
    <logic:notPresent name="rs">
	<table class="tbstyle" style="width:100%;" align="center">
	  <tr align="center">
	    <td width="20%" height="20%"><font color="red">*</font>ѧ ��</td>
	    <td width="156" height="20%" nowrap><html:text property="xh" styleId="xh" style="width:83%"/>
	    <button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
	    </td>
	    <td width="20%" height="20%" >ѧ Ժ</td>
	    <td width="156" height="20%"><html:text property="xymc" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">�� �� </td>
	    <td width="156" height="20%"><html:text property="bjmc" style="width:98%"/></td>
	    <td width="20%" height="20%" >�� �� </td>
	    <td width="156" height="20%"><html:text property="xm" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">ԭ������λ���� </td>
	    <td width="156" height="20%"><html:text property="ygzdwmc" style="width:98%"/></td>
	    <td width="20%" height="20%">ԭ��λ��ϵ�� </td>
	    <td width="156" height="20%"><html:text property="ygzdwlxr" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%" >ԭ��λ��ϵ�绰 </td>
	    <td width="156" height="20%"><html:text property="ygzdwlxdh" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">�¹�����λ����</td>
	    <td width="156" height="20%" ><html:text property="xgzdwmc" style="width:98%"/></td>
	    <td width="20%" height="20%">�¹�����λ��ַ</td>
	    <td width="156" height="20%"><html:text property="xgzdwdz" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">����</td>
	    <td width="156" height="20%"><html:text property="qy" style="width:98%"/></td>
	    <td width="96" height="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ʱ�</td>
	    <td width="156" height="20%" ><html:text property="yzbm" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">��ϵ��</td>
	    <td width="156" height="20%"><html:text property="xgzdwlxr" style="width:98%"/></td>
	    <td width="96" height="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</td>
	    <td width="156" height="20%" ><html:text property="xgzdwbm" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">��ϵ�绰</td>
	    <td width="156" height="20%"><html:text property="xgzdwlxdh" style="width:98%"/></td>
	    <td width="96" height="20%" align=center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ֻ�����</td>
	    <td width="156" height="20%"><html:text property="xgzdwsjh" style="width:98%"/></td>
	  </tr>
	  <tr>
	    <td width="540" height="50" colspan="5" valign="top">
	    �������ɣ�
	    <br> 
	    <html:textarea property="sqly" cols="115" rows="10"></html:textarea>
	        <p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �� �ˣ� </p>
	    	<p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �� �� </p></td>
	  </tr>
	</table>
	</logic:notPresent>
	<logic:present name="rs">
		<table class="tbstyle" style="width:100%;" align="center">
	  <tr align="center">
	    <td width="20%" height="20%"><font color="red">*</font>ѧ ��</td>
	    <logic:equal value="teacher" name="userOnLine" scope="session">
	    <td width="156" height="20%"><html:text name="rs" property="xh" style="width:83%"/>
	    
	    	<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
	    
	    </td>
	    </logic:equal>
	    <logic:notEqual value="teacher" name="userOnLine" scope="session">
	    <td width="156" height="20%"><html:text name="rs" property="xh" style="width:98%" readonly="true"/>
	    </logic:notEqual>
	    <td width="20%" height="20%" >ѧ Ժ</td>
	    <td width="156" height="20%"><html:text name="rs" property="xymc" style="width:98%" readonly="true"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">�� �� </td>
	    <td width="156" height="20%"><html:text name="rs" property="bjmc" style="width:98%" readonly="true"/></td>
	    <td width="20%" height="20%" >�� �� </td>
	    <td width="156" height="20%"><html:text name="rs" property="xm" style="width:98%" readonly="true"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">ԭ������λ���� </td>
	    <td width="156" height="20%"><html:text name="rs" property="ygzdwmc" style="width:98%" readonly="true"/></td>
	    <td width="20%" height="20%">ԭ��λ��ϵ�� </td>
	    <td width="156" height="20%"><html:text name="rs" property="ygzdwlxr" style="width:98%" readonly="true"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%" >ԭ��λ��ϵ�绰 </td>
	    <td width="156" height="20%"><html:text name="rs" property="ygzdwlxdh" style="width:98%" readonly="true"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">�¹�����λ����</td>
	    <td width="156" height="20%" ><html:text name="rs" property="xgzdwmc" style="width:98%"/></td>
	    <td width="20%" height="20%">�¹�����λ��ַ</td>
	    <td width="156" height="20%"><html:text name="rs" property="xgzdwdz" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">����</td>
	    <td width="156" height="20%"><html:text name="rs" property="qy" style="width:98%"/></td>
	    <td width="96" height="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ʱ�</td>
	    <td width="156" height="20%" ><html:text name="rs" property="yzbm" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">��ϵ��</td>
	    <td width="156" height="20%"><html:text name="rs" property="xgzdwlxr" style="width:98%"/></td>
	    <td width="96" height="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</td>
	    <td width="156" height="20%" ><html:text name="rs" property="xgzdwbm" style="width:98%"/></td>
	  </tr>
	  <tr align="center">
	    <td width="20%" height="20%">��ϵ�绰</td>
	    <td width="156" height="20%"><html:text name="rs" property="xgzdwlxdh" style="width:98%"/></td>
	    <td width="96" height="20%" align=center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ֻ�����</td>
	    <td width="156" height="20%"><html:text name="rs" property="xgzdwsjh" style="width:98%"/></td>
	  </tr>
	  <tr>
	    <td width="540" height="50" colspan="5" valign="top">
	    �������ɣ�
	    <br> 
	    <html:textarea name="rs" property="sqly" cols="115" rows="10"></html:textarea>
	        <p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �� �ˣ� </p>
	    	<p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� �� �� </p></td>
	  </tr>
	</table>
	</logic:present>
	<div class="buttontool" align=center>
	<button class="button2" onclick="document.forms[0].action='hzjy_bggzsq.do?doType=save';document.forms[0].submit();" style="width:10%">����</button>
	<button class="button2" onclick="document.forms[0].action='hzjy_bggzsq.do?doType=print';document.forms[0].submit();" style="width:10%">��ӡ</button>										
	</div>
</logic:equal>
<logic:equal value="false" name="sfksq">
	<div align=center style="font-size:25px;color: red;">���ڲ�������ʱ�䣡����</div>
</logic:equal>
<logic:present name="dors">
	<logic:equal value="true" name="dors">
		<script type="text/javascript">
			alert("����ɹ���");
		</script>
	</logic:equal>
	<logic:equal value="false" name="dors">
		<script type="text/javascript">
			alert("����ʧ�ܣ�");
		</script>
	</logic:equal>
</logic:present>
</html:form>
 </body>
</html:html>
