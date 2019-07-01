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
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<body> 
<html:form action="/setPjzb" > 
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ���˲�������</a>
	</p>
</div>
<div class="toolbox">
	 <div class="buttonbox">
	 	<logic:equal value="yes" name="writeAble" scope="request"> 
	    <ul>
		<li> <a href="#" onclick="showTopWin('setPjzb.do?act=add',620,520)" class="btn_zj"> ���� </a> </li>
	    <li> <a href="#" onclick="if(chkCurrRow()){showTopWin('setPjzb.do?act=modi&id='+curr_row.cells[0].innerText,620,520);}" class="btn_xg"> �޸� </a> </li>
		<li> <a href="#" onclick="if(chkCurrRow() && confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){BatAlert.showTips('���ݴ����У����Ժ�');refreshForm('setPjzb.do?act=del&pjh='+curr_row.cells[0].innerText);return true;}return false;" class="btn_sc"> ɾ�� </a> </li>
	    </ul>
	    </logic:equal>
	 </div>
	 
	<div class="searchtab">
	  <table width="100%" border="0">
	  <tbody> 
	    <tr> 
	      <th align="left" width="10%">
	      �������
	     </th>
	     
	     <td colspan="5">
	      <html:select property="mxdx" name="fdyglForm" style="width:100px" onchange="refreshForm('setPjzb.do')">
	      	<option value=""> </option>
	        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
    	  </html:select>
        </td> 
	    </tr> 
	  </tbody> 
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
 		 	 <font color="blue">��¼����<bean:write name="rsNum"/>;˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
 		 </logic:notEmpty>
    </span>
    </h3>
<logic:notEmpty name="rslist">
 <table summary="" class="dateline" align="" width="100%">
  <thead> 
    <tr style="cursor:hand"> 
      <td  width="40" align="center" onclick="tableSort(this)">���ۺ�</td> 
      <td  width="60" onclick="tableSort(this)" align="center">����ָ��</td>
      <logic:equal name="xxdm" value="10290" scope="session">
       <td  align="center"  onclick="tableSort(this)"width="150" >����ָ��</td>
      </logic:equal> 
      <td  align="center" onclick="tableSort(this)" width="450">��������</td> 
      <td  width="30" align="center" onclick="tableSort(this)">��Ӧ��ֵ</td> 
      <td  width="60" align="center" onclick="tableSort(this)">�������</td> 
    </tr> 
  </thead> 
  <tbody>
 
    <logic:iterate id="rs" name="rslist" scope="request"> 
    <tr style="cursor:hand" onclick="rowOnClick(this)" ondblclick="showTopWin('setPjzb.do?act=modi&operation=view&id='+curr_row.cells[0].innerText,620,520);"> 
      <td  align="center"><bean:write name="rs" property="xh" /></td> 
       <td  align="center"><bean:write name="rs" property="stlbmc"/></td> 
       <logic:equal name="xxdm" value="10290" scope="session">
        <td nowrap align="center"><bean:write name="rs" property="scojzb"/></td> 
      </logic:equal> 
      <td  align="left" width="450"><bean:write name="rs" property="pjzb"/></td> 
      <td  align="center"><bean:write name="rs" property="fz" /></td> 
      <td  align="center"><bean:write name="rs" property="mxdx" /></td> 
    </tr> 
    </logic:iterate> 
 	</tbody>
</table>
</logic:notEmpty> 

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