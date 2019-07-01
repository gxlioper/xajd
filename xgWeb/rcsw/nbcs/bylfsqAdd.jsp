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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/bylfgl.js'></script>
	<script>
		function save(){
			if(filedNotNull('nd-xydm','-')){
				bylfgl.checkBylf({nd:val('nd'),xydm:val('xydm')},function(data){
					if(data == true){
						alert('��Ҫ��ӵļ�¼�Ѿ����ڣ�');
						return false;
					}else{
						refreshForm('bylfgl.do?method=saveBylfsqxx&doType=add');		
					}
				});				
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
	
	<base target="_self">
	<body onload="xyDisabled('xydm')">
		<html:form action="/bylfgl.do">
			<input type="hidden" id="userType" value="${userType}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - ��ҵ�����װ���� - ����
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								<bean:message key="lable.xsgzyxpzxy" />��ҵ���������Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td style="width:40%">
							<div align="right">
								<font color="red">*</font>��ȣ�
							</div>
						</td>
						<td>
							<html:select property="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>							
							<html:select property="xydm">
								<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>						
					</tr>					
					<tr>
						<td align="right">
							��ȡ�ˣ�
						</td>
						<td>
							<html:text property="lqr" maxlength="30"></html:text>
						</td>					
					</tr>
					<tr>
						<td align="right">
							���Ʒ���
						</td>
						<td>
							<b>xl</b>:<html:text property="bkfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>l</b>:<html:text property="bkfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>m</b>:<html:text property="bkfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>s</b>:<html:text property="bkfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							ר�Ʒ���
						</td>
						<td>
							<b>xl</b>:<html:text property="zkfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>l</b>:<html:text property="zkfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>m</b>:<html:text property="zkfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>s</b>:<html:text property="zkfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							У������
						</td>
						<td>
							<b>xl</b>:<html:text property="xzfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>l</b>:<html:text property="xzfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>m</b>:<html:text property="xzfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>s</b>:<html:text property="xzfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							��ʦ����
						</td>
						<td>
							<b>xl</b>:<html:text property="dsfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>l</b>:<html:text property="dsfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>m</b>:<html:text property="dsfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
							<b>s</b>:<html:text property="dsfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " style="width:60px"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							ñ�ӣ�
						</td>
						<td>
							<html:text property="maozi" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							���磺
						</td>
						<td>
							<html:text property="pijian" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�����
						</td>
						<td>
							<html:text property="lingdai" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							��᣺
						</td>
						<td>
							<html:text property="lingjie" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
