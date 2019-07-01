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
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="js/String.js"></script>
  <body>
    <html:form action="/pjpy_apply_sony">
	   <input type="hidden" name="redirect" id="redirect" value="true">
	   <input type="hidden" name="variable" id="variable" value="none">
       <input type="hidden" name="url"  id="url"          value="/pjpy/pjpy_sony_apply.jsp">
       <input type="hidden" name="jxjlx" id="jxjlx"       value="sony">
       <input type="hidden" name="jxjdm" id="jxjdm"       value="<bean:write name="jxjdm"/>">
       <logic:present name="dboperation">
         <logic:equal value="true" name="dboperation">
         <script type="text/javascript">
         	alert("����ɹ���");
         </script>
         </logic:equal>
         <logic:equal value="false" name="dboperation">
         <script type="text/javascript">
         	alert("����ʧ�ܣ�");
         </script>
         </logic:equal>
       </logic:present>
    <div class="title">
       <div class="title_img" id="title_m">
       	��ǰ����λ�ã��������� �� ��ѧ������ �� ��д�����
       </div>
    </div>
      <p align="center">�Ϻ��иߵ�ѧУ����ѧ�������ᣩ��ѧ������� </p>
			<table align="center" class="tbstyle" style="width:99%;">
			 <tr>
				<logic:equal value="teacher" name="userOnLine" scope="session">    
		             <td  width="10%" colspan="2">ѧ��</td><td colspan="2"><html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button onclick="showTopWin('stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button></td>
	          	</logic:equal>
    	      	<logic:notEqual value="teacher" name="userOnLine" scope="session">
        	  		<td>ѧ��</td><td colspan="2"><html:text name="rs" property="xh" readonly="true"/></td>
	          	</logic:notEqual>
		  		<td colspan="7">&nbsp;</td>
        	  </tr>
			  <tr>
			    <td width="91" height="25" colspan="2">ѧ У</td>
			    <td width="192"><html:text name="rs" property="xxmc" /></td>
			    <td width="96">�� ��</td>
			    <td width="84"  colspan="3" ><html:text name="rs" property="xm" /></td>
			    <td width="60" colspan="2">�Ա�</td>
			    <td width="45" colspan="2"><html:text name="rs" property="xb" /></td>
			  </tr>
			  <tr>
			    <td width="91" height="26" colspan="2">��������</td>
			    <td width="120"><html:text name="rs" property="csny" readonly="true"/></td>
			    <td width="72">����</td>
			    <td width="96" colspan="3"><html:text name="rs" property="mzmc" /></td>
			    <td width="84" colspan="2">��ѧʱ��</td>
			    <td width="105" colspan="2"><html:text name="rs" property="rxny" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td width="91" height="28" colspan="2">ר ҵ</td>
			    <td width="288" colspan="5" ><html:text name="rs" property="zymc" /></td>
			    <td width="84" colspan="2">ѧ ��</td>
			    <td width="105" colspan="2"><html:text name="rs" property="xz" /></td>
			  </tr>
			  <tr>
			    <td>�񽱼�¼</td>
			    <td colspan="10" valign="top"><html:textarea name="rs" property="hjjl" rows="6" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td>Ʒ������/����Ա���</td>
			    <td colspan="10" valign="top"><html:textarea name="rs" property="dsyj" rows="6" style="width:98%"/></td>
			  </tr>
		  <logic:equal value="xy" name="userType" scope="session">
		  <tr>
		    <td  height="50"><bean:message key="lable.xsgzyxpzxy" />��ϵ����С�����</td>
		    <td colspan="10"><html:textarea name="rs" property="xyshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		  <logic:equal value="admin" name="userType" scope="session">
		  <tr>
		    <td height="50">ѧУ��λ�������</td>
		    <td colspan="10"><html:textarea name="rs" property="xxshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
			  <tr>
			    <td>��ί���</td>
			    <td colspan="10"><html:textarea property="pwyj" rows='6' style="width:98%"></html:textarea></td>
			  </tr>
			</table>
			<p>ע�� </p>
			<p>1 ���˱�һʽ���ݣ��Ƽ�ѧУ���н�ί�����ṫ˾��һ�ݡ� </p>
			<p>2 ����������ͳһ�õ��Դ�ӡ����ϵ��У��Ժ��������Ч�� </p>
			<p>3 ������������Ҫ�¼����ɼ�������֤��һ���� A4 ֽ��ӡ��ӡ�� </p>
    </html:form>
	<div class="buttontool">
       <button class="button2" onclick="shgcPriseApplication()">�ύ����</button>
       <button class="button2" onclick="shgcPriseAppicationPrint()">�����ӡ</button>
    </div>
  </body>
</html:html>
