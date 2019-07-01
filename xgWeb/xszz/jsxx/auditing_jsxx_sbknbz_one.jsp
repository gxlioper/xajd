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
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}else if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_jsxx_sbknbz_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - �ˡ������Ѳ������ - �������
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
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xh" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="xm" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xb" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="csrq" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="xymc" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="zymc" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="bjmc" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="lxdh" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="col" width="12%">
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td colspan="8" scope="col">
						<div align="left">
							<bean:write name="rs" property="jtdz" />
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row">
						<div align="center">
							��ͥ��Ա
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
					<td width="10%">
						<div align="center">
							����״��
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							������λ��ְ��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy1_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy2_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy3_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy4_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
				</tr>
				<tr>
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
							<bean:write name="rs" property="jtcy5_stzk" />
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						<div align="center">
							�Ƿ����Ҿ���:&nbsp;
							<bean:write name="rs" property="sfljs" />
							&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ��ǵ���:&nbsp;
							<bean:write name="rs" property="sfdq" />
							&nbsp;&nbsp;&nbsp;&nbsp; ��ĸ�Ƿ���˫�¸�:&nbsp;
							<bean:write name="rs" property="sfsxg" />
							&nbsp;&nbsp;&nbsp;&nbsp; ��ĸ�Ƿ��вм�:&nbsp;
							<bean:write name="rs" property="sfcj" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧϰ���
						</div>
					</td>
					<td colspan="8">
						<bean:write name="xxqk" />
						<br />
						<div align="right">
							(���������޲�����γ�&nbsp;
							<bean:write name="rs" property="bkhywbjgkc" />
							&nbsp;)
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���еȵ�
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="cxdd" />
						</div>
					</td>
					<td>
						<div align="center">
							ƽʱ����
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="psbx" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jcqk" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						ÿ���ͥ�ṩ����&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="mnjttgfy" />
						&nbsp;Ԫ
					</td>
					<td colspan="2">
						������ѵ��ṩ&nbsp;
						<bean:write name="rs" property="mnqphytgfy" />
						&nbsp;Ԫ
					</td>
					<td colspan="3">
						�ϼ�ÿ���ṩ&nbsp;
						<bean:write name="rs" property="mnhjtgfy" />
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						ÿ��Ӧ���ɸ��ַ���&nbsp;
						<bean:write name="rs" property="mnyjgzfy" />
						&nbsp;Ԫ
					</td>
					<td colspan="2">
						ÿ��ƽ�������&nbsp;
						<bean:write name="rs" property="mypjshf" />
						&nbsp;Ԫ
					</td>
					<td colspan="3">
						�ϼ�ÿ�����&nbsp;
						<bean:write name="rs" property="mnhjfy" />
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="9" scope="row">
						ÿ��μ�Ժ���ڹ���ѧ����&nbsp;
						<bean:write name="rs" property="mncjynqgzxbt" />
						&nbsp;Ԫ, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �μ�Ժ���ڹ���ѧ��񱨳�&nbsp;
						<bean:write name="rs" property="cjywqgzxbc" />
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����˺��ͼ����ſ�
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="ywshhjbgk" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�ˡ���ʱ��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="sbsj" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����ҽԺ
						</div>
					</td>
					<td width="20%">
						<div align="center">
							<bean:write name="rs" property="zzyy" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							ҽҩ��
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<bean:write name="rs" property="yyf" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							��������
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<bean:write name="rs" property="qtfy" />
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ƿ�Ƿ��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="sfqf" />
						</div>
					</td>
					<td colspan="3">
						��ͥ���������&nbsp;
						<bean:write name="rs" property="jtbxlpk" />
						&nbsp;Ԫ
					</td>
					<td colspan="3">
						<bean:message key="lable.xsgzyxpzxy" />���������&nbsp;
						<bean:write name="rs" property="xybxlpk" />
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ����״���������ˡ������Ѳ���������
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jtjjzkjsqsbbzly" />
					</td>
				</tr>
				<tr>
					<td colspan="5" scope="row">
						<div align="right">
							���޼�ͥ���ڵش塢��(��ί���ְ�)���ߵ��й�֤��
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="ywzm" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							�����벹�����
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="nsqbzje" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="1">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="1">
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="8">
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
