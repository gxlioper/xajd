<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
</head>
<body onload="xyDisabled('xy')"> 
<html:form action="/setCpdx" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ���ò������� </a>
	</p>
</div>
<div class="toolbox">
	 <div class="buttonbox">
	 	<logic:equal value="yes" name="writeAble" scope="request"> 
	    <ul>
		  <li> <a href="#" onclick="refreshForm('/xgxt/setCpdx.do?act=all')" class="btn_shtg"> ȫ������ </a> </li>
	      <li> <a href="#" onclick="refreshForm('/xgxt/setCpdx.do?act=none')" class="btn_shbtg"> ȫ�������� </a> </li>
	    </ul>
	   	</logic:equal>
	 </div>	
 
   <input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
   <div class="searchtab">
	<table width="100%" border="0">
	<thead>
		<bean:write name="fdyglForm" property="xn" />ѧ�� (<bean:write name="fdyglForm" property="nd" />���)�� ��<bean:write name="fdyglForm" property="xq" />ѧ�� ˼�����˲��������趨 
	</thead>
 	<tbody>
    <tr> 
      <th> <bean:message key="lable.xsgzyxpzxy" /></th>
      <td colspan="5">
        <html:select property="xydm" style="width:230px" styleId="xy" onchange="refreshForm('setCpdx.do')"> 
          <html:option value=""></html:option> 
          <html:options collection="bmList" property="xydm" labelProperty="xymc" /> 
        </html:select> </td> 
    </tr> 
  </tbody>
</table> 
</div>
</div>
<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	 ��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	��¼����<bean:write name="rsNum" /> ; ˫���ı��Ƿ������
			 		 </logic:notEmpty>
			    </span>
			    </h3>
  <table summary="" class="dateline" align="" width="100%">  
  <thead> 
    <tr align="center" style="cursor:hand"> 
      <td onclick="tableSort(this)">����</td> 
      <td onclick="tableSort(this)">ְ����</td> 
      <td onclick="tableSort(this)">����</td> 
      <td onclick="tableSort(this)">�Ա�</td> 
      <td onclick="tableSort(this)">ְ��</td> 
      <td onclick="tableSort(this)">��ϵ�绰</td> 
      <td onclick="tableSort(this)">�Ƿ����</td> 
    </tr> 
  </thead> 
  <logic:present name="rs"> 
	<logic:iterate id="s" name="rs"> 
	<tr onclick="rowOnClick(this)" ondblclick="refreshForm('/xgxt/setCpdx.do?act='+curr_row.getElementsByTagName('input')[0].value)"
					  style="cursor:hand;color:
	<logic:equal name="s" property="color" value="����"> 
	  blue
	</logic:equal> 
	<logic:equal name="s" property="color" value="������"> 
	   <bean:write name = "bcpColor" />
	</logic:equal>"> 
	  <td><bean:write name="s" property="bmmc" /></td> 
	  <td><input type ="hidden" value="<bean:write name="s" property="zgh" />" /><bean:write name="s" property="zgh" /></td> 
	  <td><bean:write name="s" property="xm" /></td> 
	  <td><bean:write name="s" property="xb" /></td> 
	  <td><bean:write name="s" property="zwmc" /></td> 
	  <td><bean:write name="s" property="lxdh" /></td> 
	  <td><bean:write name="s" property="color" /></td> 
	 </tr> 
	 </logic:iterate> 
  </logic:present> 
</table> 
</div>

   
</html:form> 
 <script type="text/javascript" src="js/bottomButton.js"></script> 
 
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("���óɹ���");</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("����ʧ�ܣ������ԣ�");</script>
    </logic:equal>
  </logic:present>
  
</body>
</html>
