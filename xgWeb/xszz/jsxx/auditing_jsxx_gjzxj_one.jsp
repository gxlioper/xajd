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
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
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
			refreshForm('/xgxt/auditing_jsxx_gjzxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function getJe1(){
			var temp = $('xytydjdm').options[$('xytydjdm').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var je = "";
			if (msgArray.length > 1) {
				je = msgArray[1];
			}
			document.getElementById('xytysqje').value=je;
		}
		
		function getJe2(){
			var temp = $('xxtydjdm').options[$('xxtydjdm').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var je = "";
			if (msgArray.length > 1) {
				je = msgArray[1];
			}
			document.getElementById('xxtyshje').value=je;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="center">
							����ְ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="btzw" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ���绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xsdh" />
					</td>
					<td>
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtdz" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ҫ
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
					<td width="10%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="14%">
						<div align="center">
							����״��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����(ѧϰ)��λ��ְ��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy1_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy2_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy3_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy4_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;<bean:write name="rs" property="jtcy5_nsr" />&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�¶�
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfgr" />
					</td>
					<td>
						<div align="center">
							��ʿ��Ů
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�����뻧
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfwsrh" />
					</td>
					<td>
						<div align="center">
							�ز���
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfzbh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ͱ���
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfdbh" />
					</td>
					<td>
						<div align="center">
							��ĸ˫�¸�
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sffmsxg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ũ��
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcnh" />
					</td>
					<td>
						<div align="center">
							������(��ͥ���벻����֧����ѧ��������)
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfdsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ÿ��Ӧ���ɸ��ַ���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nyyjngzfy" />
					</td>
					<td>
						<div align="center">
							��ͥÿ���ṩ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtmntg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ϼ�ÿ����ȱ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjmnsqfy" />
					</td>
					<td>
						<div align="center">
							Ƿ��ѧ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="qjxfs" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ѵ������༰���
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="yhdkzljje" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�������Ѿ������
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjknqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������ѧ������ɡ����
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="sqly" />
						<div align="right">
						������:&nbsp;
						<bean:write name="rs" property="xssqdjje" />&nbsp;Ԫ&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="isXX" value="no">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />ͬ��ȼ�
							</div>
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="xytydjdm" styleId="xytydjdm" onchange="getJe1();">
							<html:option value="">------��ѡ��------</html:option>
							<html:options collection="gjzxjList" property="djdm"
								labelProperty="djmc" />
							</html:select>
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />ͬ����
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="xytysqje" readonly="readonly" name="xytysqje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xytysqje"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="isXX" value="is">
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />ͬ��ȼ�
							</div>
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="xytydjmc"/>
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />ͬ����
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xytysqje"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />ͬ��������
							</div>
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="xxtydjdm" styleId="xxtydjdm" onchange="getJe2();">
							<html:option value="">------��ѡ��------</html:option>
							<html:options collection="gjzxjList" property="djdm"
								labelProperty="djmc" />
							</html:select>
						</td>
						<td>
							<div align="center">
								ѧУͬ��������
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="xxtyshje" readonly="readonly" name="xxtyshje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xxtyshje"/>">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
