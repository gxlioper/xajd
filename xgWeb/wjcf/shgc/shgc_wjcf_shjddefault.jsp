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
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/wjcfFuction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<body>	   
	    <html:form action="/shgcwjcfwh" method="post">
			<input type="hidden" name="pkVal" id="pkVal" value="<bean:write name="pkVal" scope="request"/>"/>					
	       <div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ����������� - ���߾���
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>	
			<logic:notEmpty name="rs">
			<fieldset>               			    
			      <legend> &nbsp;&nbsp;�� �� �� ֤ �� �� �� �� ��&nbsp;&nbsp; </legend> 	
			       <logic:notEmpty name="rswj"> 			
  					<table border="0" id="rsTable" width="100%"> 
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfile.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">����     							
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
						&nbsp;&nbsp;���������������&nbsp;&nbsp;
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									���������������
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" width="20%">
								ѧ�ţ�
							</td>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
							</td>
							<td align="right" width="18%">
								�����ļ��ţ�
							</td>
							<td align="left">
								<bean:write name="rs" property="cfwh" />
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<td align="right">
								����ʱ�䣺
							</td>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<td align="right">
								�������
							</td>
							<td align="left" colspan="">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<td align="right">
								�������ɣ�
							</td>
							<td align="left" colspan="">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�������ڣ�
							</td>
							<td align="left" colspan="">
								<html:text property="fcrq" styleId="slrq" onblur="dateFormatChg(this)" style="cursor:hand;inputtext"
								onclick="return showCalendar('fcrq','y-mm-dd');"></html:text>
							</td>
							<td align="right">
								��ǰ״̬��
							</td>
							<td align="left" colspan="">
								<html:select property="mqzt" style="width:100px"
									styleId="mqzt">
									<html:options collection="mqztList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								(���/����)<br>���ڣ�
							</td>
							<td align="left" colspan="">
								<html:text property="jdsj" styleId="jdsj" onblur="dateFormatChg(this)" style="cursor:hand;inputtext"
								onclick="return showCalendar('jdsj','y-mm-dd');" readonly="true"></html:text>
							</td>
							<td align="right">
								(���/����)<br>�ĺţ�
							</td>
							<td align="left" colspan="">
								<html:text property="jdwh" styleId="jdwh" style="inputtext;" ></html:text>
							</td>
						</tr>
						<tr>
							<td align="right">
					       ����ʱ�䣺
							</td>
							<td align="left" colspan="3">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<td align="right" rowspan="">
								�������ɣ�
							</td>
							<td align="left" colspan="3" rowspan="" >
								<html:textarea property="yq" name="rs" rows="3" style="width:98%" disabled="true" readonly="true"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								���������
							</td>
							<td align="left" colspan="3">
								<html:textarea property="csqk" styleId="csqk" style="inputtext;width:98%" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								���������
							</td>
							<td align="left" colspan="3">
								<html:textarea property="fcqk" styleId="fcqk" style="inputtext;width:98%" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								����֪ͨ�飺
							</td>
							<td align="left" colspan="3">
								<html:textarea property="fctzs" styleId="fctzs" style="inputtext;width:98%" rows="4"></html:textarea>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2"
							onclick="refreshForm('savessjdxx.do');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
		<logic:present name="done">
		  <logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:notEqual value="yes" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:notEqual>
		  </logic:present>
	  </html:form>	
	</body>
</html>
