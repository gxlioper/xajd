<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="0">
<script type="text/javascript">
</script>
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/GetListData.js'></script>
<script language="javascript" src="js/AjaxFunction.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript" src="js/lrh_new_js.js"></script>
<script type="text/javascript">
/**
 * @author ChenHuamao  E-MAIL:chhuma@hotmail.com
 * ͳ��ǰ����Ч�飬����������Ⱥ����μ�ѡ�����б���ͼ��
 */
function validate() {
	if ($('nd').value==null || $('nd').value=='') {
		alert('��ѡ�����');
		$('nd').focus();
		return false;
	}
	//alert($('wlpcmcV').value);
	return true;
}
</script>
<body onLoad="initPage();">
<html:form action="xszzAnalyse.do?method=jsxxzyjsxyGjzxdkhz" method="post">
<div class="title"> 
  <div class="title_img" id="title_m"> ��ǰ����λ�ã�ѧ������ - ͳ�Ʒ��� - ��ѧ�����걨����</div> 
</div>
<%--<input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />--%>
<input type="hidden" id="ndV" value="<bean:write name="xszzForm" property="nd" scope="request"/>">
<fieldset>
  <legend> �� ѯ </legend>
	<table class="tbstyle" width="100%">
	<thead>
	  <tr>	     
	  	<td>
	  	  ��ȣ�<select name="nd" id="nd" style="width:100"></select>&nbsp;
	  	</td>
	  	<td rowspan="3" width="20">
	  	  <button style="width:100px;height:20px" id="searchBtn" class="button2" onclick="if(validate())refreshForm('xszzAnalyse.do?method=jsxxzyjsxyGjzxdkhz');">ȷ��</button>
	  	</td>	  	
	  </tr>	 	  	
	 </thead>
	</table>
</fieldset>
<fieldset>
  <logic:notPresent name="url">	
  <legend> ��ѯ�������<logic:present name="rsNum"><bean:write name="rsNum"/></logic:present>����¼�� </legend>
  <table id="tabPri" class="tbstyle" width="100%">
    <thead>
	  <tr align="center" style="cursor:hand">
	    <td align="center">���</td>
        <td align="center">�༶</td>
        <td align="center">����</td>
        <td align="center">�Ա�</td>
		<td align="center">ѧ��</td>
		<td align="center">���֤��</td>
		<td align="center">������</td>
		<td align="center">����</td>
		<td align="center">��ҵʱ��</td>
		<td align="center">��ͥ��ϸ��ַ</td>
		<td align="center">�ʱ�</td>
		<td align="center">�ҳ�����</td>
		<td align="center">��ͥ�绰</td>
		<td align="center">ѧ���绰</td>
		<td align="center">����ʱ��</td>
      </tr>
    </thead>
    <tbody id="rsTable">
    <logic:present name="list">
      <logic:iterate id="s" name="list"> 
		<tr style="cursor:hand">                        
		  <td><bean:write name="s" property="rownum" /></td>         
          <td><bean:write name="s" property="bjmc" /></td>
          <td><bean:write name="s" property="xm" /></td>
		  <td><bean:write name="s" property="xb" /></td>
		  <td><bean:write name="s" property="xh" /></td>
		  <td><bean:write name="s" property="sfzh" /></td>
		  <td><bean:write name="s" property="bxqdks" /></td>
		  <td><bean:write name="s" property="nx" /></td>
		  <td><bean:write name="s" property="byny" /></td>
		  <td><bean:write name="s" property="jtzz" /></td>
		  <td><bean:write name="s" property="jhbl" /></td>
		  <td><bean:write name="s" property="jtcy1_xm" /></td>
		  <td><bean:write name="s" property="xslxdh" /></td>
		  <td><bean:write name="s" property="xslxdh" /></td>
		  <td><bean:write name="s" property="jhbl" /></td>
        </tr>
      </logic:iterate>
    </logic:present>
    </tbody>
  </table>
  </logic:notPresent>
  <div class="buttontool" id="btn" style="position: absolute;left:0px;top:100px" width="100%">
	<button class="button2" onclick="expTabWebPrint('tabPri','������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />'+$('nd').value+'��ȸ�У��ѧ�����걨����','','15-15-15-10', null, null)" name="priBtn" style="width:100px">��ӡ</button>
  </div>
</fieldset><br/> <br/> <br/>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>