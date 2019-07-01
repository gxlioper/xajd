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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="Copyright" content="������� zfsoft" />

<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />

<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/comm/commFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/basicOperaDwr.js'></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
</head>
<body onload="chkPfbzDoType()"> 
<html:form action="/setPfbz" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ����ָ������</a>
	</p>
</div>
<div class="tab">
  <input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>">
  <table width="100%"  border="0" class="formlist">
    <thead>
    	<tr>
        	<th colspan="4"><span>����ָ������</span></th>
        </tr>
    </thead>
     <tfoot>
      <tr>
      	<logic:equal value="yes" name="writeAble" scope="request"> 
          <td colspan="4"><logic:notEqual name="operation" value="view"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:notEqual>
          <div class="btn">
          <logic:notEqual name="operation" value="view">
          		<button name="�ύ" id="buttonSave" onclick="savePfbz()"> ���� </button>
          </logic:notEqual>
            <button name="�ر�" onclick="Close()"> �ر� </button>
          </div></td>
          </logic:equal>
      </tr>
    </tfoot>
    <tr> 
      <th align="right" width="60"><span class="red">*</span>���</th> 
      <td align="left">
        <html:text property="bzbh" name="fdyglForm" styleId="bzbhT" style="width:100%" maxlength="4"></html:text>
        
        <div id="msg_div1" class="hide">
        	<div class="prompcon">
          		<p>����Ѿ����ڣ�</p>
          	</div>
        </div>
      </td> 
      <th align="right" width="60"><span class="red">*</span>����</th> 
      <td align="left" valign="middle">
      <html:text property="bzmc" name="fdyglForm" style="width:90px;" maxlength="20"></html:text><span style="width:18px;border:0px;">
      <html:select property="bzbh" name="fdyglForm" styleId="bzbhS" style="border:0px;margin-left:-100px;width:118px;background-color:#98BCE1">
        <option value="">���...</option>
        <html:options collection="bzList" property="bzbh" labelProperty="bzmc"/>
      </html:select></span>
      </td> 
    </tr>  
    <tr> 
      <th align="right" width="60"><span class="red">*</span>������Ŀ</th> 
      <td align="left" colspan="3">
        <input type="hidden" name="oldPfxm" value="<bean:write property="pfxm" name="fdyglForm"/>">
        <html:textarea property="pfxm" name="fdyglForm" style="width: 95%;word-break:break-all;" onblur="chLeng(this,100);"></html:textarea>
      </td> 
    </tr>
    <tr> 
      <th align="right" width="60"><span class="red">*</span>Ȩ��</th> 
      <td align="left">
        <html:text onblur="numFormatChk(this,0,1)" property="qz" styleId="qz" name="fdyglForm" style="width:90px" maxlength="10"></html:text>
      </td> 
      <th align="right" width="60"><span class="red">*</span>��ʾ˳��</th> 
      <td align="left">
        <html:text onblur="numFormatChk(this,0)" property="xssx" name="fdyglForm" style="width:90px" maxlength="2"></html:text>
      </td> 
    </tr>
</table> 
</div>


  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("����ɹ���");window.dialogArguments.refreshForm("setPfbz.do");Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("����ʧ�ܣ������ԣ�");</script>
    </logic:equal>
  </logic:present>
  
</html:form> 
</body>
</html>
