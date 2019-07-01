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
		<base target="_self" />
		<title><bean:message key="lable.title" /></title>
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
		<script type="text/javascript">
			function aztj(){
				var xq = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
				var ldmc = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
				if(confirm("ȷ��Ҫͳ��"+$('xn').value+"ѧ��"+xq+"ѧ��"+ldmc +"�������������")){
					  document.forms[0].target = "_blank";
					  document.forms[0].submit();    
				    }
			}
		</script>
	<body onload="document.forms[0].xn.options[0] = null">
		<center>
			<html:form action="/whlghxxy_Gygl.do?method=nwwsAzExpData" method="post" >
					<div class="title">
						<div class="title_img" id="title_m">
							��ǰ����λ�ã���ǰ����λ�ã���Ԣ���� - ��������ͳ�� - ͳ�Ʒ��� - ����ͳ��--ͳ������
							
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
								<td align="right">
									ѧ�꣺
									
								</td >
								<td align="left">
									<html:select property="xn" style="width:90px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									ѧ�ڣ�
								</td>
								<td align="left">
									<html:select property="xq" style="width:40px" styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">									
									¥�����ƣ�
								</td>
								<td align="left">
									<html:select property="lddm" style="width:100px" styleId="lddm">
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</td>
							</tr>
							
						
					</table>
			<div class="buttontool" id="button" align="center">
					<button class="button2"
						onclick="aztj();"
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
			<script language="javascript">
		
   			 </script>
			</html:form>
		</center>
	</body>
</html>
