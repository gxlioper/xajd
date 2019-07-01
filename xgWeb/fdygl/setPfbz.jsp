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
<body onunload="window.dialogArguments.refreshForm('setPjConf.do')"> 
<html:form action="/setPfbz" > 
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ���ֱ�׼����</a>
	</p>
</div>

<div class="toolbox">
	 <!-- ��ť -->
	 <logic:equal value="yes" name="writeAble" scope="request"> 
	 <div class="buttonbox">
	    <ul>
		<li> <a href="#" onclick="showTopWin('setPfbz.do?act=add',400,300)" class="btn_zj"> ���� </a> </li>
	    <li> <a href="#" onclick="if(chkCurrRow()){showTopWin('setPfbz.do?act=modi&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText,400,220);}" class="btn_xg"> �޸� </a> </li>
		<li> <a href="#" onclick="if(chkCurrRow() && confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){refreshForm('setPfbz.do?act=del&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText);return true;}return false;" class="btn_sc"> ɾ�� </a> </li>
	    </ul>
	 </div>
	</logic:equal>
	<div class="searchtab">
		<table width="100%" border="0">
		  <thead> 
		    <tr> 
		      <td align="left" nowrap> ��׼
		        <html:select property="bzbh" style="width:230px" styleId="bzbh" onchange="refreshForm('setPfbz.do')"> 
		          <html:option value=""></html:option> 
		          <html:options collection="bzList" property="bzbh" labelProperty="bzmc" /> 
		        </html:select> </td> 
		    </tr> 
		  </thead> 
	</table> 
	</div>
</div>
<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rslist">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rslist">
			 		 	��¼����<bean:write name="rsNum"/>;&nbsp;&nbsp;˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������
			 		 </logic:notEmpty>
			    </span>
			    </h3>
 <logic:present name="rslist" scope="request"> 
  <table summary="" class="dateline" align="" width="100%">
  <thead> 
    <tr style="cursor:hand"> 
      <td nowrap align="center" onclick="tableSort(this)">���</td> 
      <td nowrap align="center" onclick="tableSort(this)">����</td> 
      <td nowrap align="center" onclick="tableSort(this)">������Ŀ</td> 
      <td nowrap align="center" onclick="tableSort(this)">Ȩ��</td> 
      <td nowrap align="center" onclick="tableSort(this)">��ʾ˳��</td> 
    </tr> 
  </thead> 
  <tbody>
    <logic:iterate id="rs" name="rslist" scope="request"> 
    <tr style="cursor:hand" onclick="rowOnClick(this)" ondblclick="showTopWin('setPfbz.do?act=modi&operation=view&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText,400,220);"> 
      <td nowrap align="center"><bean:write name="rs" property="bzbh" /></td> 
      <td nowrap align="left"><bean:write name="rs" property="bzmc"/></td> 
      <td nowrap align="left"><bean:write name="rs" property="pfbz" /></td> 
      <td nowrap align="center"><bean:write name="rs" property="qz" /></td> 
      <td nowrap align="center"><bean:write name="rs" property="xssx" /></td> 
    </tr> 
    </logic:iterate> 
   </tbody>
</table> 
</logic:present> 
<br>

</html:form> 
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