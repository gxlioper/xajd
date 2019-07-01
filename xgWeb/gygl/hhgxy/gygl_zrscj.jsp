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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript">
	function hh_zrsCjsave(){
		var url="/xgxt/XsgyglHhDispatch.do?method=zrscj&type=save";
		refreshForm(url);
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>	
		<html:form action="/holiday_lsxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name = "title" />
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center" style="color: red">
					��ѧ�꣬������ֵ������δά����
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" name="lcV" id="lcV" value=""/>
				<input type="hidden" name="cwhV" id="cwhV" value=""/>
				<input type="hidden" name="qshV" id="qshV" />
				<input type="hidden" name="xhV" value=""/>
				<input type="hidden" name="zsV" id="zsV" value=""/>
				
				<table width="100%" class="tbstyle" id="rsTable">
					<thead>
						<tr>
							<td colspan="4" align="center">
								ֵ����ֵ�ܳɼ�
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
						</td>			
						<td align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq" value="${rs.xq}"/>
							<html:text name="rs" property="xqmc" value="${rs.xqmc}" readonly="true"/>
						</td>				
					</tr>
					<tr>
						<td align="right">
							&nbsp;&nbsp;ѧ��:
						</td>
						<td align="left">
							<html:text name="rs" property="xh" styleId="xh" value="${rs.xh}" readonly="true"></html:text>
						</td>
						<td align="right">
							&nbsp;&nbsp;����:
						</td>
						<td align="left">
							<html:text name="rs" property="xm" value="${rs.xm}" readonly="true"></html:text>
						</td>
					</tr>	
					<tr>
						<td align="right">
							&nbsp;&nbsp;������:
						</td>
						<td align="left">					
							<html:hidden name="rs" property="ssbh" value="${rs.ssbh}"/>
							<html:text name="rs" property="ssbh1" styleId="ssbh1" value="${rs.ssbh1}" readonly="true"></html:text>
						</td>
						<td align="right">
							&nbsp;&nbsp;��ʱ:
						</td>
						<td align="left">
							<html:hidden name="rs" property="zs" value="${rs.zs}"/>
							<html:text name="rs" property="zs1" value="${rs.zs1}" readonly="true"></html:text>
						</td>
					</tr>	
					<tr>
						<td align="right">
							&nbsp;&nbsp;�����������ɼ�:
						</td>
						<td align="left">
							<html:text name="rs" property="dj" value="${rs.dj}" readonly="true"></html:text>
						</td>
						<td align="right">
							&nbsp;&nbsp;ֵ����ֵ�ܳɼ�:
						</td>
						<td align="left">
							<html:text name="rs" property="cj" styleId="cj" value="${rs.cj}" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"></html:text>
						</td>
					</tr>
				</table>		
				<div class="buttontool" align="center">

					<button class="button2" onclick="hh_zrsCjsave()" style="width:80px" id="buttonSave">
						�� ��
					</button>

					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>		
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ�ܣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
  </body>
</html>
