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
			refreshForm('/xgxt/auditing_hzzy_pksknbz_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ƶ�������Ѳ������ - �������
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
						<td colspan="9" align="center">
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
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="csny" />
					</td>
					<td colspan="2">
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxny" />
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
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�������ѧ����
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcjzxdk" />
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�μ��ڹ���ѧ
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfcjqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ֻ�����
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sjhm" />
					</td>
					<td colspan="2">
						<div align="center">
							����绰
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="jldh" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							ֱ
							<br />
							ϵ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td width="11%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							����(��ѧϰ)��λ
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jthk" />
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�˾�������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="rjysr" />
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtyzsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������Դ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="srly" />
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td rowspan="7" scope="row">
						<div align="center">
							��
							<br />
							ѧ
							<br />
							��
							<br />
							ѧ
							<br />
							ϰ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							�γ�
						</div>
					</td>
					<td>
						<div align="center">
							�ɼ�
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							�γ�
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�ɼ�
						</div>
					</td>
					<td width="20%">
						<div align="center">
							�γ�
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�ɼ�
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc1_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc1_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc2_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc2_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc3_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc3_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc4_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc4_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc5_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc5_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc6_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc6_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc7_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc7_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc8_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc8_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc9_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc9_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc10_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc10_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc11_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc11_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc12_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc12_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc13_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc13_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc14_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc14_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc15_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc15_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc16_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc16_cj" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="kc17_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc17_cj" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc18_mc" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="kc18_cj" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������Ѳ�������
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property="sqly" />
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
					<td colspan="2">
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
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="7">
						<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
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
