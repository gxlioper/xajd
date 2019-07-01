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
function saveJxrc(url){
	refreshForm(url);
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��Ϣά�� - ��ѵ�ճ̰���
				</div>
			</div>
				<fieldset>
					<legend>
						��ѵ�ճ̰���
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								���ڣ�
							</td>
							<td align="left">
							<html:text  property="jxrq" styleId="jxrq"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jxrq','y-mm-dd');"/>
							</td>
							<td align="right">
								ʱ�䣺
							</td>
							<td align="left">
								<html:select property="sj" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="����">����</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ص㣺
							</td>
							<td align="left">
								<html:text property="dd" />
							</td>
							<td align="right">
								��֯�ߣ�
							</td>
							<td align="left">
								<html:text  property="zzz" />
							</td>
						</tr>
						<tr>
						<td align="right">
							���ݣ�
						</td>
						<td colspan="3" align="left">
							<html:textarea  property="nr" style="width:90%" rows="3"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="saveJxrc('jxgljz_xbmz.do?method=xbmzjxrcAdd&add=add');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
