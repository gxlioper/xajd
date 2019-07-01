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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/sxjyFunction.js"></script>
<script language="javascript">
</script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<body> 
<FORM method="POST" name="myform" action="eWebEditor/submit.jsp"> 
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div> 
  <div class="formbox">
   <h3 class="datetitle_01">
			    <span>
			    	ҳ����ʾ
			    </span>
			    </h3>
  
  <logic:notEmpty name="rs"> 
  <table summary="" class="dateline" align="" width="100%">
   <tbody>
    <logic:iterate id="list" name="rs"> 
    <tr onmouseover="rowOnClick(this)"> 
      <td> <a href="show_One_Clob.do?ID=<bean:write name="list" property="����"/>&type=fdyzdxx" target="_blank"> <bean:write name="list"
											property="xxmc" /> </a> </td> 
      <td width="140"> <bean:write name="list" property="lrrq" /> </td> 
      <td width="80"> <bean:write name="list" property="puber" /> </td>
  	  <logic:notEmpty name="writeAble"> 
       <td align=right>
       	<a href="#" onclick="location='counsellor_system_updata.do?documentId=<bean:write name="list" property="����"/>&act=fdyzdxx&part=N170202'">�޸�</a>/ <a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ��Ϣ��'))location='counsellor_system_del.do?type=fdyzdxx&documentId=<bean:write name="list" property="����"/>'" >ɾ��</a> </td>
      </logic:notEmpty>
    </tr> 
    </logic:iterate> 
    </tbody>
  </table> 
  </logic:notEmpty> 
  <logic:empty name="rs"> 
  <center>
     ������Ϣ
  </center> 
  </logic:empty> 
 
  <logic:notEmpty name="writeAble"> 
  <div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�����Ϣ</span></th>
			        </tr>
			    </thead>
  			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="����" onclick="sxpub('<bean:write name="type"/>')">�� ��</button>
			              <button type="button" type="reset" class="btn_cz" id="btn_cz">
		              	           �� ��
		                         </button></TD> 
			          </div></td>
			      </tr>
			    </tfoot>
  
				    <input type="hidden" name="isModi" id="isModi" value="<bean:write name="isModi" />" /> 
				    <input type="hidden" name="documentId" id="documentId" value="<bean:write name="documentId" />" />
				    <input type="hidden" name="type" id="type" value="<bean:write name="type" />" />
				    <TR>
				      <th align=right width="100"> <span class="red">*</span>��Ϣ���⣺ </th> 
				      <TD align=left colspan="3"> <input type="text" name="title" id="title" style="width:100%" value="<bean:write name="documenttit"/>"> </TD> 
				    </TR> 
				    <TR> 
				      <th align=right width="100"> <span class="red">*</span>��Ϣ���ݣ� </th> 
				      <TD align=center colspan="3"> <INPUT type="hidden" name="content1" value="<bean:write name="documentcont"/>"> 
				        <IFRAME ID="eWebEditor1"
								src="BatEditor" frameborder="0"
								scrolling="no" width="100%" height="350"></IFRAME> </TD> 
    </TR> 
  </TABLE> 
  </logic:notEmpty>
</FORM> 
</body>
</html>
