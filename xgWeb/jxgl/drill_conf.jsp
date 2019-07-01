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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript">
	function checkRedio(){
		var sfxz = document.getElementById('jxmdsfqgx').value;
		if(sfxz == '0'){
			document.getElementById('close').checked=true;			
		}else{
			document.getElementById('open').checked=true;			
		}
	}
	//begin ���ΰ 2009/3/30
	function chkInputJx(){
		var num = document.getElementById('jxll').value;
		if(num.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("����Ϊ���֣�");
				return false;
			}
		if(num>100){
				alert("��ѵ�ɼ��������ܴ���100%��");
				return false;
			}	
		return true;
	}
</script>
	<body onload="checkRedio()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/drill_conf" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã���ѵ���� - �������� - ������������
					</div>
				</div>
				<fieldset>
					<legend>
						�����趨
					</legend>
					<table width="80%" class="tbstyle" align="center">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									������������
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								��ѵѧ����������ȡ����
							</td>
							<td height="25" align="left">
								<INPUT type="hidden" value="<bean:write name="jxmdsfqgx"/>" id="jxmdsfqgx"/>
								<input type="radio" value="1" name="jxmdsfqgx" id="open">��
								<input type="radio" value="0" name="jxmdsfqgx" id="close">��
<%--								 <input type="radio" name="fpfs" value="bj">--%>
<%--								<html:radio property="jxmdsfqgx" value="1" idIndex="open">��</html:radio>--%>
<%--								<html:radio property="jxmdsfqgx" value="0" idIndex="close">��</html:radio>--%>
							</td>
						</tr>
						<!-- begin ���ΰ 2009/3/30 -->
						<logic:equal value="11647" name="xxdm" scope = "session">
						<tr align="center">
							<td width="45%" height="25" align="right">
								��ѵ���۳ɼ�������
							</td>
							<td height="25" align="left">
								<input type="text" name="jxll" id="jxll" width="10%" onkeyup="chkInputJx()" maxlength="3"/>%
							</td>
						</tr>	
						</logic:equal>	
						<logic:equal value="11647" name="xxdm" scope = "session">
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="refreshForm('drill_conf.do?method=saveDrillConf')">
										����
									</button>
								</td>
							</tr>
						</thead>
						</logic:equal>
						<logic:notEqual value="11647" name="xxdm" scope = "session">		
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="refreshForm('drill_conf.do?method=saveDrillConf')">
										����
									</button>
								</td>
							</tr>
						</thead>
						</logic:notEqual>
						<!-- end ���ΰ 2009/3/30 -->	
					</table>
				</fieldset>
				<logic:notEmpty name="result">
					<logic:equal name="result" value="true">
						<script>alert("����ɹ�!")</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>alert("����ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
