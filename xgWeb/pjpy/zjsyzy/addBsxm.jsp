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
	function commit(obj){
		var values = obj.split('-');
		for(var i=0; i<values.length; i++){
			if(document.getElementById(values[i]).value == ''){
				alert('�뽫���е���Ŀ��д������');
				return false;
			}
		}
		refreshForm('pjpy_zjsyzy.do?method=saveBsxm');
	}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/pjpy_zjsyzy.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �������� - �۲�������Ŀά��
				</div>
			</div>			
				<fieldset>
					<legend>
						�۲�������Ŀά��
					</legend>
					
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								��Ŀ���룺
							</td>
							<td>
								<html:text property="xmdm" name="rs" styleId="xmdm"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								��Ŀ���ƣ�
							</td>
							<td>
								<html:text property="xmmc" name="rs" styleId="xmmc"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								�����ӷ���Ŀ���ƣ�
							</td>
							<td>
								<html:select property="ssjfxm" name="rs" styleId="ssjfxm">
								<html:option value=""></html:option>
								<html:options collection="ssjfxmList" property="en" labelProperty="cn"/>
								</html:select>
							</td>							
						</tr>
						<tr>
							<td align="right">
								�������ͣ�
							</td>
							<td>
								<html:select property="czlx" name="rs" styleId="czlx">
								<html:option value=""></html:option>
								<html:options collection="czlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>							
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="commit('xmdm-xmmc-ssjfxm')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ�!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">				
				<script>
					alert('����ʧ�ܣ�');
					Close();
				</script>
				</logic:equal>
				</logic:present> 
		</html:form>
	</body>
</html>
