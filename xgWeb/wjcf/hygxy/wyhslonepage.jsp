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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>	   
	    <html:form action="/wjcfhygxywh.do" method="post">
	    
								
	       <div class="title">
				<div class="title_img" id="title_m">
					<logic:equal value="11049" name="xxdm">
						��ǰ����λ�ã�Υ�ʹ��� - ���ߴ��� - ίԱ������
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						��ǰ����λ�ã�Υ�ʹ��� - ����������� - ίԱ������
					</logic:notEqual>
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
      							<td> <a href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">����     							
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
						&nbsp;&nbsp;����������������&nbsp;&nbsp;
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="5" align="center">
									����������������
								</td>
							</tr>
						</thead>
						<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
						<tr>
							<td align="right" width="15%">
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
							<td align="left" width="15%" rowspan="5">
								<img border="0" style="height:133px;width:100px;"
									src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
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
								��ȣ�
							</td>
							<td align="left">
								<bean:write name="rs" property="nd" />
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
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								<bean:write name="rs" property="xq" />
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
							<td align="left" colspan="2">
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
							<td align="left" colspan="2">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ϵ��ַ��
							</td>
							<td align="left">
								<bean:write name="rs" property="dz" />
							</td>
							<td align="right">
								<logic:present name="isZJJDZYJSXY">
					    ����/�������ʱ�䣺
					    </logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					       ����ʱ�䣺
					     </logic:notPresent>
							</td>
							<td align="left" colspan="2">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�������룺
							</td>
							<td align="left">
								<bean:write name="rs" property="yb" />
							</td>
							<td align="right">
								ίԱ������
							</td>
							<td align="left" colspan="2">
								<html:select name="rs" property="wyhsl" style="width:100px"
									styleId="syhsl">
									<html:option value="����">����</html:option>
									<html:option value="������">������</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ϵ�绰��
							</td>
							<td align="left">
								<bean:write name="rs" property="lxdh" />
							</td>
							<td align="right">

							</td>
							<td align="left" colspan="2">

							</td>
						</tr>
						<logic:present name="isZJJDZYJSXY">
							<tr>
								<td align="right">
									���봦�ָ�Ϊ��
								</td>
								<td align="left" colspan="4">
									<bean:write name="rs" property="cfxg" />
								</td>
							</tr>
						</logic:present>
						<tr>
							<td align="right">
								<logic:present name="isZJJDZYJSXY">
					    ����/���&nbsp;&nbsp;&nbsp;<br>�������ɣ�<br>
								</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					   �ı�<bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;&nbsp;<br>����Ҫ��
						</logic:notPresent>
							</td>
							<td align="left" colspan="4">
								<bean:write name="rs" property="yq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�������ɣ�
							</td>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="wyhsllr" rows="7"
									style="width:98%" styleId="wyhsllr">
								</html:textarea>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2"
							onclick="refreshForm('hygxy_wyhslone.do?act=save');BatAlert.showTips('���ڲ�������ȴ�...');"
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
		  <logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				///window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
	  </html:form>	
	</body>
</html>
