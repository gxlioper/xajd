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
			var userType = document.getElementById('userType').value;
			var xxshjg = document.getElementById('xxshjg').value;
			if(("δ���" != xxshjg ) && (userType != "admin")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			 refreshForm('/xgxt/zgkydx_xszz.do?method=knsshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��������� - �������
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
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
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
					<td align="left" colspan="3">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td width="30%">
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
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nj" />
					</td>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
					<td colspan="2">
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd" />
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" />
					</td>
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
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
						<bean:write name="rs" property="ss" />
					</td>
					<td colspan="2">
						<div align="center">
							�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ���ڵ�
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtszd" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���׵�λ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fqdwlxdh" />
					</td>
					<td colspan="2">
						<div align="center">
							ĸ�׵�λ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqdwlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�绰���ʱ�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtlxdhjyzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							���ڻ�����֯�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="szjclxdh" />
					</td>
				</tr>
				<tr>
					<td width="6%" rowspan="8">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
						</div>
					</td>
					<td width="14%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td width="10%">
						<div align="center">
							�뱾��
							<br />
							��ϵ
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
							<br />
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							��ҵ
							<br />
							״̬
						</div>
					</td>
					<td>
						<div align="center">
							������λ(�Ͷ�ѧУ)
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy1_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy1_xm">
							<bean:write name="rs" property="jtcy1_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy2_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy2_xm">
							<bean:write name="rs" property="jtcy2_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy3_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy3_xm">
							<bean:write name="rs" property="jtcy3_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy4_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy4_xm">
							<bean:write name="rs" property="jtcy4_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy5_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy5_xm">
							<bean:write name="rs" property="jtcy5_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy6_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy6_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy6_xm">
							<bean:write name="rs" property="jtcy6_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy6_gzdw" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="rs" property="jtcy7_xm" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_xb" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_gx" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_nl" />
					</td>
					<td align="center">
						<logic:empty name="rs" property="jtcy7_xm">
						&nbsp;
					</logic:empty>
						<logic:notEmpty name="rs" property="jtcy7_xm">
							<bean:write name="rs" property="jtcy7_hklx" />
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_cyzt" />
					</td>
					<td align="center">
						<bean:write name="rs" property="jtcy7_gzdw" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<font color="red"> ע:1.��ͥ��Աָ��ѧ������֮�⹲ͬ�����ֱ��Ѫ�׼��ֵܽ��ã� <br />
							&nbsp;&nbsp;&nbsp;2.�������ͷ֣�A&nbsp;(ũҵ����)&nbsp;&nbsp;&nbsp;B&nbsp;(��ũҵ����)��
							<br />
							&nbsp;&nbsp;&nbsp;3.��ҵ״̬һ������д��������ũ���¸ڡ��򹤡����ݡ���ѧ(��ѧ����ר�����С����С�Сѧ)�ȡ�
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						����ƶ������������
						<font color="red">(���ڷ��ϵ���������Ҫ˵��)</font>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							���Թ��Ҽ�ƶ���ص�ũ�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_lzgjjpkxdncdq" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							���������������ϵĳ����ͥ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_syxszdshbzdczjt" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							�¶��򾭼����ѵĵ��׼�ͥ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_gehjjknddqjt" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ĸһ����˫���¸�(ʧҵ)
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_fmyfhsfxg" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ͥ��Ա����18-55�����׳�Ͷ���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcywqzndl" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ͥ��Ա��м��򼲲���ɥʧ�Ͷ�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyycjhjbrssndl" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyyzdjbxzfdefy" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ͥ�������������ϳ�Ա�����ܷ��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyzylghyscyzjsfywjy" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							��ͥ����Ա��������Ȼ�ֺ�������ͻ���ֱ�
							<br />
							��������Ʋ����ش���ʧ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_jtcyzszrzh" />
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="center">
							�������¼�ͥ�����������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly_qtqk" />
					</td>
				</tr>
				<tr>
					<td rowspan="8">
						<div align="center">
							��ͥ
							<br />
							����
							<br />
							���
							<br />
							֤��
							<br />
							����
							<br />
							�嵥
						</div>
					</td>
					<td rowspan="2">
						<div align="center">
							����һ
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl1_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���߲��ϻ��ؼ���ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl1_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl1_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							���϶�
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl2_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���߲��ϻ��ؼ���ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl2_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl2_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl3_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���߲��ϻ��ؼ���ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl3_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl3_dh" />
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="3">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zmcl4_mc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							���߲��ϻ��ؼ���ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zmcl4_jg" />
					</td>
					<td>
						<bean:write name="rs" property="zmcl4_dh" />
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
					<td colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="8">
						ƶ��ѧ����Ϊ�ر�ƶ��ѧ��(������)��һ��ƶ��ѧ�����ࡣ
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;��������ΪA��B������
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;������(A��)������������ͥ���ü������ѣ���������������Դ��
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;������(B��)������������ͥ�����ر����ѣ���Уʵ��������õ���ƽ��100Ԫ/�¡�
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;��һ��ƶ��ѧ��(C��)������������ͥ���ñȽ����ѣ�����ƶ��ѧ�����뽨��������
						<br />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								������������
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="pyrs" name="pyrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="pyrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								A������
							</div>
						</td>
						<td>
							<input type="text" id="adrs" name="adrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="adrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								B������
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="bdrs" name="bdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								C������
							</div>
						</td>
						<td>
							<input type="text" id="cdrs" name="cdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="cdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								��ͬ������
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="btyrs" name="btyrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="btyrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td colspan="2">
							<div align="center">
								���ڲ��轨������
							</div>
						</td>
						<td>
							<input type="text" id="csbyjdrs" name="csbyjdrs" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="csbyjdrs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								���ڲ��轨���������
							</div>
						</td>
						<td colspan="6">
							<input type="text" id="byjdjtqk" name="byjdjtqk" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="byjdjtqk"/>">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�������
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="dcfyqk">
								<html:option value=""></html:option>
								<html:options collection="dcfyqkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								���ʱ��
							</div>
						</td>
						<td>
							<input type="text" id=xbshsj name="xbshsj" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xbshsj"/>">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�༶������
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="bjpyjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxshjg" name="xxshjg"
								value="<bean:write name='rs' property="xxshjg" />" />
						</td>
						<td colspan="2">
							<div align="center">
								ϵ����˽��
							</div>
						</td>
						<td>
							<html:select name="rs" property="xbshjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td colspan="2">
							<div align="center">
								������������
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="pyrs"/>
							<input type="hidden" id="pyrs" name="pyrs"
								value="<bean:write name='rs' property="pyrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								A������
							</div>
						</td>
						<td>
							<bean:write name="rs" property="adrs"/>
							<input type="hidden" id="adrs" name="adrs"
								value="<bean:write name='rs' property="adrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								B������
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="bdrs"/>
							<input type="hidden" id="bdrs" name="bdrs"
								value="<bean:write name='rs' property="bdrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								C������
							</div>
						</td>
						<td>
							<bean:write name="rs" property="cdrs"/>
							<input type="hidden" id="cdrs" name="cdrs"
								value="<bean:write name='rs' property="cdrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								��ͬ������
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="btyrs"/>
							<input type="hidden" id="btyrs" name="btyrs"
								value="<bean:write name='rs' property="btyrs" />" />
						</td>
						<td colspan="2">
							<div align="center">
								���ڲ��轨������
							</div>
						</td>
						<td>
							<bean:write name="rs" property="csbyjdrs"/>
							<input type="hidden" id="csbyjdrs" name="csbyjdrs"
								value="<bean:write name='rs' property="csbyjdrs" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								���ڲ��轨���������
							</div>
						</td>
						<td colspan="6">
							<bean:write name="rs" property="byjdjtqk"/>
							<input type="hidden" id="byjdjtqk" name="byjdjtqk"
								value="<bean:write name='rs' property="byjdjtqk" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�������
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="dcfyqk"/>
							<input type="hidden" id="dcfyqk" name="dcfyqk"
								value="<bean:write name='rs' property="dcfyqk" />" />
						</td>
						<td colspan="2">
							<div align="center">
								ϵ�����ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xbshsj"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�༶������
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="bjpyjg"/>
							<input type="hidden" id="bjpyjg" name="bjpyjg"
								value="<bean:write name='rs' property="bjpyjg" />" />
						</td>
						<td colspan="2">
							<div align="center">
								ϵ����˽��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xbshjg"/>
							<input type="hidden" id="xbshjg" name="xbshjg"
								value="<bean:write name='rs' property="xbshjg" />" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧУ��˽��
							</div>
						</td>
						<td colspan="3">
							<html:select name="rs" property="xxshjg">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xxshsj"/>
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
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
