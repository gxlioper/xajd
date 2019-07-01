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
<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<body onload="initSetFdyZy();xyDisabled('xy');"> 
<script language="javascript">
			function dataExport() {
	document.forms[0].action = "/xgxt/expData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
<html:form action="/jhzy_pjpysz" method="post">
<div class="title"> 
  <div class="title_img" id="title_m"> ��ǰ����λ�ã�˼������ - ��Ϣά�� - ����֧����ǻ��� </div> 
</div> 
<fieldset> 
<legend>�����α��</legend>
<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>"> 
<input type="hidden" id="method" name="method" value="fdybb"> 
<input type="hidden" id="realTable" name="realTable" value="dzzbsjhfb"> 
<input type="hidden" id="tableName" name="tableName" value="view_dzzbsjhf"> 
<table width="100%" class="tbstyle">
  <thead> 
    <tr> 
      <td colspan="6">�꼶:
        <html:select property="nj" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="njList" property="nj" labelProperty="nj"/> 
        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
        <html:select property="xydm" styleId="xy" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="xyList" property="xydm" labelProperty="xymc"/> 
        </html:select>
         <br/>
       ����Ա�������ţ�
        <html:select property="bmdm" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="bmList" property="bmdm" labelProperty="bmmc"/> 
        </html:select></td> 
    </tr> 
  </thead> 
    <tr>
      <td width="30" rowspan="4" align="center" valign="middle"><p>��</p>
      <p>��</p>
      <p>֧</p>
      <p>��</p>
      <p>��</p>
      <p>��</p>
      </td>
      <td width="40%" rowspan="4">
      <html:select property="fdyxm" size="10" styleId="fdyxmList" style="width:100%" ondblclick="getDzbsjInfo()"> 
        <html:options collection="fdyList" property="zgh" labelProperty="xm"/> 
      </html:select></td>
      <td  height="30" style="text-align: center;" >ְ���ţ�</td>
    <td width="100"><bean:write  name="fdyInfo" property="zgh"/><html:hidden name="fdyInfo" property="zgh" styleId="zgh"/></td>
      <td  align="center" class="tbstyle">�������ţ�</td>
      <td nowrap><bean:write name="fdyInfo" property="bmmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" >������</td>
      <td><bean:write name="fdyInfo" property="xm"/> </td> 
      <td align="center" class="tbstyle">ְ��</td>
      <td><bean:write name="fdyInfo" property="zwmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" >�Ա�</td>
      <td><bean:write name="fdyInfo" property="xb"/> </td> 
      <td align="center" class="tbstyle">��ϵ�绰��</td>
      <td><bean:write name="fdyInfo" property="lxdh"/> </td> 
    </tr>
    <tr>
      <td align="center" height="75" valign="top" nowrap >��Ҫְ��</td>
      <td colspan="3" valign="top"><bean:write name="fdyInfo" property="zyzz"/></td> 
    </tr>
    <tr>
      <td align="center" valign="middle"><p>ר</p>
      <p>ҵ</p></td>
      <td >
      <html:select name="fdyInfo" property="zymc" styleId="zyFlist" ondblclick="addFdyZy()" size="13" style="width:100% "> 
        <html:options collection="zyList" property="zydm" labelProperty="zymc"/> 
      </html:select></td>
      <td align="middle" nowrap>
    <button type="button" class="button2" style="width:30%" id="addFdyZyB" onclick="addFdyZy()"> &gt;&nbsp;&gt; </button><br><br><br>
    <button type="button" class="button2" style="width:30%" id="delFdyZyB" onclick="delFdyZy()"> &lt;&nbsp;&lt; </button></td>
      <td colspan="3">����רҵ��<br>
      <html:select name="fdyInfo" property="zylist" size="12" ondblclick="delFdyZy()" styleId="zyTlist" style="width:100% "> 
        <html:options collection="fdyZyList" property="zydm" labelProperty="zymc"/> 
      </html:select></td>
    </tr>
</table> 
</fieldset> 

<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%"> 
  <button type="button" class="button2" onclick="saveBzrZy()"> �� �� </button> 
 &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="refreshForm('jhzy_szdw_dzzsjhf.do')"> �� �� </button> 
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="impAndChkData();" style="width:80px">��������</button>
  &nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="button2" onclick="dataExport()" style="width:80px">
										��������
									</button>
</div> 

  <logic:present name="ok" scope="request"> 
    <logic:equal value="ok" name="ok" scope="request"> 
      <script>alert("����ɹ���");</script> 
  </logic:equal> 
  <logic:equal value="no" name="ok" scope="request"> 
    <script>alert("����ʧ�ܣ������ԣ�");</script> 
  </logic:equal> 
</logic:present> 
<script language="javascript" src="js/bottomButton.js"></script>
</html:form> 
</body>
</html>
