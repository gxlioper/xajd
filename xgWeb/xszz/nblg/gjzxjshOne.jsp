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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var bdsyj = document.getElementById('bdsyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(bdsyj != null){
	         	if(bdsyj.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("�ർʦ������ܴ���600���ַ�");
	          		 return false;
	       		 }
	       	}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/nblg_xszz.do?method=gjzxjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/nblg_xszz.do?method=gjzxjshSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ����� - �������
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csny"/>
					</td>
					<td>
						<div align="center">
							��ѧ������Υ�ʹ���
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sxnywwjcf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧϰ���<br />(ѧϰ̬��<br />���ɼ���)
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xxqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������ֽ���
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="chhzjlhzzqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���ѳ̶�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="kndj"/>
					</td>
					<td>
						<div align="center">
							���������ѧ�����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqdj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jthk"/>
					</td>
					<td>
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtlx"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfly"/>
					</td>
					<td>
						<div align="center">
							�Ƿ�����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfls"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrkzs"/>
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrjysr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtyzsr"/>
					</td>
					<td>
						<div align="center">
							������Դ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="srly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtzz"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="17%">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ְҵ�͹���(ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy1_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy1_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy1_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy2_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy2_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy2_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy3_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy3_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy3_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy4_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy4_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy4_gz" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="jtcy5_xm" />
					</td>
					<td>
						<bean:write name='rs' property="jtcy5_cw" />
					</td>
					<td>
						&nbsp;<bean:write name='rs' property="jtcy5_sr" />&nbsp;
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtcy5_gz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								���ѵȼ�
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�ർʦ���<br />
								(ѧ����ͥ���á�ѧ��ѧϰ���ճ����ֵ����)
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="bdsyj" rows='5'
								style="width:100%" onblur="yzdx(this,'bdsyj')" />
							<input type="hidden" id="bdsyj" name="bdsyj" value="">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xyshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								���ѵȼ�
							</div>
						</td>
						<td colspan="2">
							<bean:write name='rs' property="kndj" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name='rs' property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ���
							</div>
						</td>
						<td colspan="2">
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�ർʦ���<br />
								(ѧ����ͥ���á�ѧ��ѧϰ���ճ����ֵ����)
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="bdsyj" />
							<input type="hidden" id="bdsyj" name="bdsyj"
								value="<bean:write name='rs' property="bdsyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="4">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ������
							</div>
						</td>
						<td colspan="4">
							<html:textarea name="rs" property="xxshyj" rows='3'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual name="userType" value="admin">
					<logic:equal name="rs" property="xxsh" value="δ���">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="δ���">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave" disabled="disabled">
							�� ��
						</button>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="userType" value="admin">
					<button type="button" class="button2" onclick="yz()" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
