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
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<body onload="xyDisabled('xy')"> 
<html:form action="/setPjzb" > 
<div class="title"> 
  <div class="title_img" id="title_m"> ��ǰ����λ�ã�����Ա���� - ˼������ - ���˲������� </div> 
</div> 
<fieldset> 
	<input type="hidden" name="userType" value="${userType}" />
	<table width="100%" class="tbstyle"> 
	  <thead> 
	    <tr> 
	      <td align="left">
	      �������
      <html:select property="mxdx" name="fdyglForm" style="width:100px" onchange="refreshForm('setPjzb.do')">
      	<option value=""> </option>
        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
      </html:select>
      &nbsp;&nbsp;
         <bean:message key="lable.xsgzyxpzxy" />���ƣ�
      <html:select property="xydm" name="rs" name="fdyglForm" style="width:100px" styleId="xy" onchange="refreshForm('setPjzb.do')">
      	<option value=""> </option>
        <html:options collection="xyList" property="xydm" labelProperty="xymc"/>
      </html:select>
      </td> 
	    </tr> 
	  </thead> 
	</table> 
	</fieldset> 
<fieldset> 
<legend>��¼����<bean:write name="rsNum"/></legend> 
<table class="tbstyle" width="100%"> 
  <thead> 
    <tr style="cursor:hand"> 
      <td nowrap width="40" align="center" onclick="tableSort(this)">���ۺ�</td> 
      <td nowrap width="60" onclick="tableSort(this)" align="center">����ָ��</td>
       <td nowrap align="center"  onclick="tableSort(this)"width="150" ><bean:message key="lable.xsgzyxpzxy" />����</td>
      <td nowrap align="center" onclick="tableSort(this)" width="450">��������</td> 
      <td nowrap width="30" align="center" onclick="tableSort(this)">��Ӧ��ֵ</td> 
      <td nowrap width="60" align="center" onclick="tableSort(this)">�������</td> 
    </tr> 
  </thead> 
  <logic:present name="rslist" scope="request"> 
    <logic:iterate id="rs" name="rslist" scope="request"> 
    <tr style="cursor:hand" onclick="rowOnClick(this)" ondblclick="showTopWin('setPjzb.do?act=modi&id='+curr_row.cells[0].innerText,550,500);"> 
      <td nowrap align="center"><bean:write name="rs" property="xh" /></td> 
       <td nowrap align="center"><bean:write name="rs" property="stlbmc"/></td> 
        <td nowrap align="center"><bean:write name="rs" property="xymc"/></td> 
      <td nowrap align="left" width="450"><bean:write name="rs" property="pjzb"/></td> 
      <td nowrap align="center"><bean:write name="rs" property="fz" /></td> 
      <td nowrap align="center"><bean:write name="rs" property="mxdx" /></td> 
    </tr> 
    </logic:iterate> 
  </logic:present> 
</table> 
</fieldset> 

<logic:equal value="yes" name="writeAble" scope="request"> 
<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%"> 
  <button class="button2" onclick="showTopWin('setPjzb.do?act=add',550,500)">���Ӽ�¼</button> 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <button class="button2" onclick="if(chkCurrRow()){showTopWin('setPjzb.do?act=modi&id='+curr_row.cells[0].innerText,550,500);}">�޸ļ�¼</button> 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <button class="button2" onclick="if(chkCurrRow() && confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){refreshForm('setPjzb.do?act=del&pjh='+curr_row.cells[0].innerText);return true;}return false;">ɾ����¼</button> 
</div> 
</logic:equal>

</html:form> 
 <script type="text/javascript" src="js/bottomButton.js"></script>  
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("�����ɹ���");</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("����ʧ�ܣ������ԣ�");</script>
    </logic:equal>
  </logic:present>
</body>
</html>