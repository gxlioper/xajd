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
		<base target = "_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		
	<body onload=" document.forms[0].xn.options[0]=null">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/nwwstj.js"></script>	
		<html:form action="/whlghxxy_Gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��������ͳ�� - ͳ�Ʒ��� - ���ܡ�ϵ������Աͳ��--ͳ������
				</div>
			</div>			 
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								����ѡ��
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							�꼶��
						</td>
						<td height="22" align="left">
								<html:select property="nj">								
								<html:options collection="njList" property="nj"
												labelProperty="nj"  />
								</html:select>						
						</td>                        					
					</tr>
					<tr>
						<td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
								<html:select property="xn">								
								<html:options collection="xnList" property="xn"
												labelProperty="xn"  />
								</html:select>						
						</td>                        					
					</tr>
					<tr>
						<td height="22" align="right">
							ѧ�ڣ�
						</td>
						<td height="22" align="left">
								<html:select property="xq">								
								<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc"  />
								</html:select>						
						</td>                        					
					</tr>										
	                <tr>
	                <td height="22" align="right">
							������
						</td>
						<td height="22" align="left">
								<html:select property="zs">								
								<html:options collection="zsList" property="en"
												labelProperty="cn"  />
								</html:select>	
						</td>	
	                </tr>
				</table>
				<br />
				<div class="buttontool" id="button" align="center">
					<button class="button2"
						onclick="toZXFDYTj()"
						style="width:80px"  id="buttonSave">
						ȷ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();return false;"
						style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>					
		</html:form>
		</body>
	<script type="text/javascript">


	</script>
</html>
