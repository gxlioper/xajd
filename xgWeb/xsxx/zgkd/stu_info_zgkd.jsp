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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgkd/zgkd_stu_modinfo.jsp">
			<input type="hidden" name="redirect" id="redirect" value="">
			<input type="hidden" name="variable" id="variable" value="">
			<input type="hidden" name="xyV" id="xyV" value="">
			<input type="hidden" name="zyV" id="zyV" value="">
			<input type="hidden" name="bjV" id="bjV" value="">
			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ
				</div>
			</div>
			
			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ����Ÿù���!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			<table class="tbstyle" id="rsTable" width="100%">
			<thead>
			<tr>
			<td align="center" colspan="5">
				������Ϣ�޸�
			</td>
			</tr>
			</thead>
			<tr>
			<td align="right">
				<font color="red">*</font>ѧ�ţ�
			</td>
			<td>
				<html:text property="xh" name="rs" styleId="xh" maxlength="20"/>
			</td>
			<td align="right">
				������
			</td>
			<td> <html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/> </td>
			<td rowspan="6">
				<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
			</td>
			</tr>
			
			<tr>
			<td align="right">
				<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
			</td>
			<td>
				<html:select property="xydm" name="rs" disabled="true" styleId="xy">
					<html:option value=""></html:option>
					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>רҵ��
			</td>
			<td> <html:select property="zydm" name="rs" disabled="true" styleId="zy"> <html:option value=""></html:option> <html:options collection="zyList" property="zydm" labelProperty="zymc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				�꼶��
			</td>
			<td>
				<html:select property="nj" name="rs" disabled="true" styleId="nj">
					<html:option value=""></html:option>
					<html:options collection="njList" property="nj" labelProperty="nj"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>�༶��
			</td>
			<td> <html:select property="bjdm" name="rs" disabled="true" styleId="bj"> <html:option value=""></html:option> <html:options collection="bjList" property="bjdm" labelProperty="bjmc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				�Ա�
			</td>
			<td>
				<html:radio property="xb" value="��" name="rs" disabled="true" styleId="xbn">��</html:radio>
				<html:radio property="xb" value="Ů" name="rs" disabled="true"  styleId="xbv">Ů</html:radio>
			</td>
			<td align="right">
				ѧ�ƣ�
			</td>
			<td> <html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>�� </td>
			</tr>
			
			<tr>
			<td align="right">
				���壺
			</td>
			<td>
				<html:select property="mz" name="rs" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				������ò��
			</td>
			<td> <html:select property="zzmm" name="rs" disabled="true" styleId="zzmm"> <html:option value=""></html:option> <html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/> </html:select> </td>
			</tr>
			
			<tr>
			<td align="right">
				ѧ��״̬��
			</td>
			<td>
				<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm">
					<html:option value=""></html:option>
					<html:option value="��ѧ��">��ѧ��</html:option>
					<html:option value="��ѧ��">��ѧ��</html:option>
				</html:select>
			</td>
			<td align="right">
				�������ڣ�
			</td>
			<td> <html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				����ƴ����
			</td>
			<td>
				<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
			</td>
			<td align="right">
				��������
			</td>
			<td colspan="2"> <html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				��ߣ�
			</td>
			<td>
				<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ����
			</td>
			<td align="right">
				���أ�
			</td>
			<td colspan="2"> <html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ǧ�� </td>
			</tr>
			
			<tr>
			<td align="right">
				���֤�ţ�
			</td>
			<td>
				<html:text property="sfzh" name="rs" disabled="true" styleId="sfzh" maxlength="18"/>
			</td>
			<td align="right">
				�س���
			</td>
			<td colspan="2"> <html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				������ʽ��
			</td>
			<td>
				<html:text property="pyfs" name="rs" disabled="true" styleId="pyfs" maxlength="32"/>
			</td>
			<td align="right">
				������Σ�
			</td>
			<td colspan="2"> <html:text property="pycc" name="rs" disabled="true" styleId="pycc" maxlength="32"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				��ѧ��ʽ��
			</td>
			<td>
				<html:text property="rxfs" name="rs" disabled="true" styleId="rxfs" maxlength="32"/>
			</td>
			<td align="right">
				�������
			</td>
			<td colspan="2"> 
				<html:text property="kslb" name="rs" disabled="true" styleId="kslb" maxlength="32"/> 
			</td>
			</tr>
			
			<tr>
			<td align="right">
				��Դ������
			</td>
			<td>
				<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
			</td>
			<td align="right">
				���᣺
			</td>
			<td colspan="2"> <html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				�������䣺
			</td>
			<td>
				<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32"/>
			</td>
			<td align="right">
				��ϵ�绰��
			</td>
			<td colspan="2"> <html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15"/> </td>
			</tr>
			
			<tr>
			<td align="right">
				�ֻ����룺
			</td>
			<td colspan="">
				<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</td>	
			<td>
				�����ţ�
			</td>
			<td colspan="2">
				<html:text property="ssbh" name="rs" disabled="true" styleId="ssbh" maxlength="20"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				ƶ�����ȼ���
			</td>
			<td colspan="">
				<bean:write name="pksdj"/>
			</td>
			<td>
				���ҵ绰��
			</td>
			<td colspan="2">
				<html:text property="qsdh" name="rs" disabled="true" styleId="qsdh" maxlength="10"/>
			</td>
			</tr>
			</table>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="refreshForm('xsxx_zgkd.do?method=saveStuinfo')">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="Close();return false;">
						�� ��
					</button>
				</div>
			</logic:equal>
			
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");	
					Close();	
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
