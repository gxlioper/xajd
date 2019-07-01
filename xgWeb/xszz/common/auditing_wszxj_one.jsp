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
		function yzt(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xyzzfzryj = document.getElementById('xyzzfzryj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xyzzfzryj != null){
	         	if(xyzzfzryj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />��֧������������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("ѧУ���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_wszxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzXNMZ(){
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("����Ա���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("ѧУ���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_wszxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
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
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("ѧУ���������ܴ���60���ַ�");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_wszxj_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function getJe(){
			var temp = $('zzdjmc').options[$('zzdjmc').selectedIndex].innerHTML;
			msgArray = new Array();
			msgArray = temp.split('||');
			var zzdjje = "";
			if (msgArray.length > 1) {
				zzdjje = msgArray[1];
			}
			document.getElementById('zzdjje').value=zzdjje;
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
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>

					<td align="center" width="20%">
						ѧ��
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
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
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td >
						<bean:write name="rs" property="csny" />
					</td>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td >
						<bean:write name="rs" property="jg" />
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td >
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							ѧ��
						</div>
					</td>
					<td >
						<bean:write name="rs" property="xz" />
					</td>
					<td >
						<div align="center">
							������ѧ�����
						</div>
					</td>
					<td >
						<bean:write name="rs" property="bzmc" />
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							���Һ�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="qsh" />
					</td>
					<td align="center">
						���ҵ绰
					</td>
					<td>
						<bean:write name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jcqk" />
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							������ò
						</div>
					</td>
					<td >
						<bean:write name="rs" property="zzmm" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td >
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:0;">
						<%@ include file="/xszz/zjjd/yhkh.jsp"%>
					</td>
				</tr>	
				<tr>
					<td >
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtdz" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="98%" align="center" class="tbstyle" >
					<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="9%">
						<div align="center">
							����
						</div>
					</td>
					<td width="13%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							������ѧϰ��λ
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						&nbsp;<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy1_ysr" />
					</td>
					<td colspan="3">
						&nbsp;<bean:write name="rs" property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						&nbsp;<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy2_ysr" />
					</td>
					<td colspan="3">
						&nbsp;<bean:write name="rs" property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						&nbsp;<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy3_ysr" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						&nbsp;<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy4_ysr" />
					</td>
					<td colspan="3">
						&nbsp;<bean:write name="rs" property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td width="12%">
						&nbsp;<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy5_ysr" />
					</td>
					<td colspan="3">
						&nbsp;<bean:write name="rs" property="jtcy5_gzdw" />
					</td>
				</tr>
				</table>
				</td>
				</tr>
				
				<tr>
					<td height="108">
						������Ṥ�����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="drshgzqk" />
					</td>
				</tr>
				<tr>
					<td>
						<br>
						����
						<br>
						����
					</td>
					<td colspan="3">
						��������ͥ���������ѧ����Դ������Ʒ�С� ѧϰ�����
						<bean:write name="rs" property="sqzzly" />
					</td>
				</tr>
				<tr>
					<td height="108">
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						ŵ
					</td>
					<td colspan="3">
						<bean:write name="rs" property="brcn" />
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					
				</tr>
				<logic:equal name="isZZSF" value="is">
					<tr>
						<logic:equal name="isXX" value="no">
							<td >
								<div align="center">
									ѧ�������ȼ�
								</div>
							</td>
							<td align="left">
								<html:select name="rs" property="zzdjdm" styleId="zzdjmc"
									onchange="getJe();">
									<html:option value="">------��ѡ��------</html:option>
									<html:options collection="xszzDjList" property="zzdjdm"
										labelProperty="zzdjmc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:equal name="isXX" value="is">
							<td>
								<div align="center">
									ѧ�������ȼ�
								</div>
							</td>
							<td align="left">
								<html:select name="rs" property="zzdjdm" styleId="zzdjmc"
									disabled="true">
									<html:option value="">------��ѡ��------</html:option>
									<html:options collection="xszzDjList" property="zzdjdm"
										labelProperty="zzdjmc" />
								</html:select>
							</td>
						</logic:equal>
						<td>
							<div align="center">
								�������
							</div>
						</td>
						<td>
							<input type="text" id="zzdjje" readonly="readonly" name="zzdjje"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zzdjje"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="isXNMZ" value="is">
					<tr>
						<td>
							<div align="left">
								����Ա
								<br>
								���
								<br>
								���
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />
							<br>
							���
							<br>
							���
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<logic:equal name="isZZSF" value="is">
					<tr>
						<td>
							<div align="left">
								<bean:message key="lable.xsgzyxpzxy" />
								<br>
								��֧
								<br>
								������
								<br>
								���
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xyzzfzryj" rows='5' style="width:100%" onblur="yzdx(this,'xyzzfzryj')"/>
							<input type="hidden" id="xyzzfzryj" name="xyzzfzryj" value="">
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="left">
							ѧУ
							<br>
							���
							<br>
							���
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<logic:equal name="isZZSF" value="is">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yzt();" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:equal>
			<logic:equal name="isZZSF" value="no">
			<logic:equal name="isXNMZ" value="is">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yzXNMZ();" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:equal>
			<logic:equal name="isXNMZ" value="no">
				<div class="buttontool" align="center">
					<button class="button2" onclick="yz();" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>
