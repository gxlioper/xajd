<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<FORM method="POST" name="myform" action="/XsgyglDispatch.do?method=xsYjx">
		<input type="hidden" name="id" id="id" value="<bean:write name="yjid" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					ѧ�������
				</div>
			</div>
  				<fieldset>		
				<legend>
					�����Ϣ
				</legend>
				<TABLE class="tbstyle" width="100%">
					<TR>
						<TD align=right width="100">
							������⣺<br>
							(��50��)
						</TD>
						<TD align=left colspan="3">
							<html:text name="rs" property="title" styleId="title" style="width:600px" maxlength="50"></html:text>
						</TD>
					</TR>
					<TR>
						<TD align=right width="100">
							������ݣ�<br>
							(��һǧ��)
						</TD>
						<TD colspan="3">
							<html:textarea name="rs" property="content" styleId="content" cols="95" rows="15"></html:textarea>
						</TD>
					</TR>
					<TR>
						<TD colspan=4 align=center>
							<button class="button2" onclick="yjpub()">
								����
							</button>
							<INPUT type=reset name=b2 value="����" class="button2">
						</TD>
					</TR>
				</TABLE>
			</fieldset>
			 <fieldset>
			   <legend> &nbsp;&nbsp;�� �� �� �� �� ��&nbsp;&nbsp;</legend> 
			   		<logic:empty name="yjList">
			   		<p align="center">δ���������κ����</p>
			   		</logic:empty>
			   		<logic:notEmpty name="yjList">	      			
  					<table border="0"  width="100%" class="tbstyle">  
  					    <thead>
  					    <tr><td  onclick="tableSort(this)">����</td><td  onclick="tableSort(this)">������</td><td  onclick="tableSort(this)">����ʱ��</td><td  onclick="tableSort(this)">���޻ظ�</td><td>����</td></tr>
  					    </thead>
    					<logic:iterate id="list" name="yjList"> 
    						<tr onmouseover="rowOnClick(this)" > 
      							<td> <a href="/xgxt/XsgyglDispatch.do?method=viewYjXx&id=<bean:write name="list" property="id"/>" target=_black><bean:write name="list" property="title"/>    							
      							</a> </td> 					
      							<td > <bean:write name="list" property="xh" /> </td> 
       							<td > <bean:write name="list" property="pubdate" /> </td>
       							<td > <bean:write name="list" property="ywhf" /> </td>
       							<td>
       							<logic:equal value="��" name="list" property="ywhf">
       							<a href="#"  onclick="if(confirm('ȷʵҪ�޸ĸ�����¼��')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=modi&id=<bean:write name="list" property="id"/>';}" >�޸�</a>
       							/<a href="#" onclick="if(confirm('ȷʵҪɾ��������¼��')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=del&id=<bean:write name="list" property="id"/>';}" >ɾ��</a>
       							</logic:equal>
       							</td>
    						</tr> 
    					</logic:iterate> 
  					</table>  									
  					</logic:notEmpty>		
  				</fieldset>	 
			<logic:equal value="yes" name="done">
				<script type="text/javascript">
	           alert("�����ɹ���");
	        </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
	          alert("����ʧ�ܣ�");
	        </script>
			</logic:equal>
		</FORM>
	</body>
	<script type="text/javascript">
	   function yjpub(){
	       if($('title').value==''||$('content').value==''){
	          alert('������Ŀ����Ϊ�գ�');
	          return false;
	       }
	       if($('title').value.length>50){
	          alert("�����������ܴ���50�֣�");
	          return false;
	       }
	       if($('content').value.length>1000){
	          alert("�����������ܴ���1000�֣�");
	          return false;
	       }
	       showTips('��������У����Ժ�...');
	       refreshForm('/xgxt/XsgyglDispatch.do?method=xsYjx&doType=pub')	   	    
	 }
	
	</script>
</html>

