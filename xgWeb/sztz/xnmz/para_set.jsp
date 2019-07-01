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
		
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<center>
			<html:form action="/para_set" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�������չ - �������� - �����趨
					</div>
				</div>
				<fieldset>
					<legend>
						��������
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									������չ�����趨
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								<font color="red">*</font>ѧ�꣺								
							</td>
							<td height="25" align="left">
								<html:select property="xn">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<logic:present name="XNMZ">						
						<tr>
							<td width="45%" height="25" align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td height="25" align="left">
								<html:select property="nd">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>	
						</logic:present>					
						<tr>
							<td width="45%" height="25" align="right">
								<font color="red">*</font>ѧ�ڣ�
							</td>
							<td height="25" align="left">
								<html:select property="xq">
									<option value="">
									</option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								���뿪ʼʱ�䣺
							</td>
							<td align="left">
								<input type="hidden" name="kssqsj" id="kssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('kssqsj1','y-mm-dd');"
									value="<bean:write name="kssj1" />" name="kssqsj1" id="kssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj2" />"
									name="kssqsj2" id="kssqsj2" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj3" />"
									name="kssqsj3" id="kssqsj3" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj4" />"
									name="kssqsj4" id="kssqsj4" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�������ʱ�䣺
							</td>
							<td align="left">
								<input type="hidden" name="jssqsj" id="jssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('jssqsj1','y-mm-dd');"
									value="<bean:write name="jssj1" />" name="jssqsj1" id="jssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj2" />"
									name="jssqsj2" id="jssqsj2" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj3" />"
									name="jssqsj3" id="jssqsj3" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj4" />"
									name="jssqsj4" id="jssqsj4" />
							</td>
						</tr>												
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">									
									<button class="button2"
										onclick="savePara(this,'xn-nd-xq')">
										����
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:notEmpty name="ok">
					<logic:equal name="ok" value="ok">
						<script>alert("����ɹ�!")</script>
					</logic:equal>
					<logic:equal name="ok" value="no">
						<script>alert("����ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
	<script type="text/javascript">
	 function savePara(obj,mustFill){
	   	var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {	    
		if ($(eles[i])&&$(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		   }
	    }
	    saveSztzParaSet('kssqsj','jssqsj','xn','nd','para_set.do?act=save');
	    obj.disabled=true;
	 
	 }
	</script>
</html>
