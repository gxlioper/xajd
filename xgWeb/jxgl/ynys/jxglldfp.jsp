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
<script language="javascript" src="js/jxglFunction.js"></script>
<body onload="initSetLdBj();xyDisabled('xy');"> 
<script language="javascript">
function dataExport() {
	document.forms[0].action = "/xgxt/expData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
<html:form action="/jxgljz_ynys" method="post">
<div class="title"> 
  <div class="title_img" id="title_m"> ��ǰ����λ�ã���ѵ����  - ��ѵ���� - ���ӱ��ƻ��� </div> 
</div> 
<fieldset> 
<legend>���ӱ��ƻ���(��ģ�����ڵ���������༶(��Ϊ��Ů)�Ļ��֣����и�����Ҫ��������ѵ����ά���޸�)</legend>
<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>"> 
<input type="hidden" id="realTable" name="realTable" value="ldbzfpb"> 
<input type="hidden" id="tableName" name="tableName" value="ldbzfpb"> 
<html:hidden name="LdInfo" property="ldbh" styleId="ldbh"/>
<table width="100%" class="tbstyle">
  <thead> 
    <tr> 
      <td colspan="6">�꼶:
        <html:select property="nj" onchange="refreshForm('jxgljz_ynys.do?method=jxglldfp')"> 
          <option value=""></option>
          <html:options collection="njList" property="nj" labelProperty="nj"/> 
        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
        <html:select property="xydm" styleId="xy" onchange="refreshForm('jxgljz_ynys.do?method=jxglldfp')"> 
          <option value=""></option>
          <html:options collection="xyList" property="xydm" labelProperty="xymc"/> 
        </html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;רҵ��
        <html:select property="zydm" onchange="refreshForm('jxgljz_ynys.do?method=jxglldfp')"> 
          <option value=""></option>
          <html:options collection="zyList" property="zydm" labelProperty="zymc"/> 
        </html:select></td> 
    </tr> 
    <tr> 
      <td colspan="6"> ������Ϣ:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������Ա�
			<html:select property="xb" style="width:90px"  styleId="xb"  onchange="refreshForm('jxgljz_ynys.do?method=jxglldfp')">
					<html:option value=""></html:option>
					<html:option value="��">��</html:option>
					<html:option value="Ů">Ů</html:option>
			</html:select>
    </tr>
  </thead> 
    <tr>
      <td width="30" rowspan="4" align="center" valign="middle"><p>��</p>
        <p>��</p>
      <p>��</p><p>��</p></td>
      <td width="40%" rowspan="4">
      <font color="">˫���������ƽ��б༭</font>
      <html:select property="ldmc" size="10" styleId="ldxmList" style="width:100% " ondblclick="getContInfo()"> 
        <html:options collection="ldList" property="ldbh" labelProperty="ldmc"/> 
      </html:select></td>
       <td align="center" height="30" width="60" class="tbstyle">���ӱ�ţ�</td>
      <td width="80"><bean:write  name="LdInfo" property="ldbh"/></td>
      <td width="60" align="center" class="tbstyle">�������ƣ�</td>
      <td nowrap><bean:write name="LdInfo" property="ldmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" class="tbstyle">ָ��Ա��</td>
      <td ><bean:write name="LdInfo" property="zdy"/> </td> 
      <td align="center" class="tbstyle">�̹����ƣ�</td>
      <td nowrap><bean:write name="LdInfo" property="jgmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" class="tbstyle">�Ա�</td>
      <td ><bean:write name="LdInfo" property="xb"/>&nbsp; </td> 
      <td align="center" class="tbstyle">&nbsp;</td>
      <td nowrap>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
    </tr>
    <tr>
      <td align="center" height="75" valign="middle" nowrap class="tbstyle"><div align="center">��ע��</div></td>
      <td colspan="3" valign="top"><bean:write name="LdInfo" property="bz"/></td> 
    </tr>
    <tr>
      <td align="center" valign="middle"><p>��</p>
      <p>��</p></td>
      <td>
      <html:select name="LdInfo" property="bjmc" styleId="bjFlist" ondblclick="addLdBj()" size="12" style="width:100% "> 
        <html:options collection="bjList" property="bjdm" labelProperty="bjmc"/> 
      </html:select></td>
      <td nowrap>
    <button type="button" class="button2" style="width:100%" id="addLdBjB" onclick="addLdBj()"> &gt;&nbsp;&gt; </button><br><br><br>
    <button type="button" class="button2" style="width:100%" id="delLdBjB" onclick="delLdBj()"> &lt;&nbsp;&lt; </button></td>
      <td colspan="3">����༶��<br>
      <html:select name="LdInfo" property="bjlist" size="11" ondblclick="delLdBj()" styleId="bjTlist" style="width:100% "> 
        <html:options collection="ldBjList" property="bjdm" labelProperty="bjmc"/> 
      </html:select></td>
    </tr>
</table> 
</fieldset> 
 
<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%"> 
  <button type="button" class="button2" onclick="saveLdBj()" style="width:80px"> �� �� </button> 
 &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="refreshForm('jxgljz_ynys.do?method=jxglldfp')" style="width:80px"> �� �� </button> 
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="showTopWin('/xgxt/data_import.do',600,480)" style="width:80px">��������</button>
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
