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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<body>
	     <fieldset>               			    
			      <legend> &nbsp;&nbsp;�� �� �� ֤ �� �� �� �� ��&nbsp;&nbsp; </legend> 	
			       <logic:notEmpty name="rswj"> 			
  					<table border="0" id="rsTable" width="100%"> 
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfile.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj"/>">����      							
      							</a> </td> 
      							<td > <bean:write name="list" property="cfwh" /> </td> 
      							<td > <bean:write name="list" property="cflbmc" /> </td> 
       							<td > <bean:write name="list" property="cfyymc" /> </td>
       							<td > <bean:write name="list" property="sssj" /> </td>
<%--       							<td>--%>
<%--        							<a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ�ļ���'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >ɾ��</a> </td>--%>
    						</tr> 
    					</logic:iterate> 
  					</table> 
  				</logic:notEmpty> 
  				<logic:empty name="rswj"> 
  				<center>
    			 ���޲��ϻ�֤������ 
  				</center> 
  				</logic:empty> 
			</fieldset>	   
		  <fieldset>
			<legend>
             &nbsp;&nbsp;Υ�ʹ�������������ϸ��Ϣ&nbsp;&nbsp;
			</legend>
			<table width="100%" class="tbstyle">
				<tr>
					<td align="right" width="15%" bgcolor="DOEOEE">
						ѧ�ţ�
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="right" width="18%" bgcolor="DOEOEE">
						�����ļ��ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="cfwh" />
					</td>
					<td align="left" width="15%" rowspan="5">
							<img border="0" style="height:120px;width:100px;"
							src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
					</td>	
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right" bgcolor="DOEOEE">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						ѧ�ڣ�
					</td>
					<td align="left" >
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="cfsj" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						�������
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="cflbmc" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						�������ɣ�
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="cfyymc" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						��ϵ��ַ��
					</td>
					<td align="left">
						<bean:write name="rs" property="dz" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						����ʱ�䣺
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="sssj" />
					</td>
				</tr>					
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						�ı�<bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;&nbsp;<br>����Ҫ��
					</td>
					<td align="left" colspan="4">
						<bean:write name="rs" property="yq" />
					</td>
				</tr>								
			</table>
		</fieldset>
		<fieldset>
			<legend>
             &nbsp;&nbsp;ѧУ�������&nbsp;&nbsp;
			</legend>
			<table width="100%" class="tbstyle">
				<tr>
					<td align="right" bgcolor="DOEOEE"  width="15%">
						����Ա��ˣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="fdysh" />	
					</td>
					<td>&nbsp;</td><td>&nbsp;</td>
				</tr>
			   <tr>
					<td align="right" bgcolor="DOEOEE"  width="15%">
					<logic:equal value="yes" name="isSHGC">
						<bean:message key="lable.xsgzyxpzxy" />���������
					</logic:equal>
					<logic:notPresent name="isSHGC">
						<bean:message key="lable.xsgzyxpzxy" />��ˣ�
					</logic:notPresent>
					</td>
					<td align="left"  width="30%">
						<logic:equal value="yes" name="isSHGC">
							<bean:write name="rs" property="slqk"  />
						</logic:equal>
						<logic:notPresent name="isSHGC">
							<bean:write name="rs" property="sh"  />
						</logic:notPresent>
					</td>
					<td align="right" bgcolor="DOEOEE"  width="18%">
						ѧУ��ˣ�
					</td>
					<td align="left" colspan="2">
						<logic:equal value="yes" name="isSHGC">
							<bean:write name="rs" property="mqzt" />
						</logic:equal>
						<logic:notPresent name="isSHGC">
							<bean:write name="rs" property="jd" />
						</logic:notPresent>
					</td>
				</tr>
				<tr>
				    <td align="right" bgcolor="DOEOEE">
						(���/����)&nbsp;&nbsp;&nbsp;<br>	�ĺţ�
					</td>
					<td align="left">
					<bean:write name="rs" property="jdwh" />						
					</td>
					<td align="right" bgcolor="DOEOEE">
						(���/����)&nbsp;&nbsp;&nbsp;<br>	ʱ�䣺
					</td>
					<td align="left" colspan="2">
					    <bean:write name="rs" property="jdsj" />	
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">											
						����Ա�����
					</td>
					<td align="left" colspan="4">
						
							<bean:write name="rs" property="fdyyj"  />
					
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">											
						<bean:message key="lable.xsgzyxpzxy" />�����
					</td>
					<td align="left" colspan="4">

							<bean:write name="rs" property="tlly"  />

					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						ѧУ�����
					</td>
 					<td align="left" colspan="4">
						
							<bean:write name="rs" property="jdly" />

					</td>
				</tr>
			</table>
		</fieldset>
	</body>
</html>
