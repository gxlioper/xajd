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
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			if((("ƶ����" == xysh)||("������" == xysh)) && (user == "fdy")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if((("ƶ����" == xxsh)||("������" == xxsh)) && (user == "xy")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("ѧУ���������ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
		
			refreshForm('/xgxt/csmz_xszz.do?method=knsshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ƶ������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh" value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
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
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
					</td>
					<td colspan="2" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2" scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name='rs' property="mzmc" />
					</td>
					<td colspan="2">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name='rs' property="rxny" />
					</td>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							ѧ����ͥ������������
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ס��ͥ��ϸ��ַ
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="xzjtxxdz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yzbm" />
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							ԭѧϰѧУ
						</div>
					</td>
					<td>
						<bean:write name='rs' property="yxxxx" />
					</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jg" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6" scope="row">
						<div align="center">
							ֱ
							<br />
							ϵ
							<br />
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							���ںδ�������ְ��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							��ϵ
						</div>
					</td>
					<td width="14%">
						<div align="center">
							ÿ�¹���
							<br />
							����(Ԫ)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy1_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy2_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy3_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy4_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zsjtcy5_lxdh"/>
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="2" scope="row">
						<div align="center">
							��ͥ
							<br />
							����
							<br />
							����
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							ȫ��������
							<bean:write name="rs" property="jtjj_cz_qjnsr"/>
							Ԫ���˾�������
							<bean:write name="rs" property="jtjj_cz_rjysr"/>
							Ԫ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ũ��
						</div>
					</td>
					<td colspan="5">
						<div align="left">
							�����������ܼ�
							<bean:write name="rs" property="jtjj_nc_dnzsr"/>
							Ԫ���˾�������
							<bean:write name="rs" property="jtjj_nc_rjnsr"/>
							Ԫ
							<br />
							<font color="red">��ע����ũ��Ա�����ũ�������������ת��Ϊ�������룬���벻��Ϊ�㡣��</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtrks" />
					</td>
					<td colspan="2">
						<div align="center">
							���������������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="ddzdshshbz" />
						(Ԫ/��)
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="5" scope="row">
						<div align="center">
							��
							<br />
							Ҫ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							ϵ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<div align="center">
							���ںδ�������ְ��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							��ϵ
						</div>
					</td>
					<td width="14%">
						<div align="center">
							ÿ�¹���
							<br />
							����(Ԫ)
						</div>
					</td>
					<td width="20%">
						<div align="center">
							����Ҿ�����ϵ
							<br />
							�������
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx1_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx2_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx3_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_xm"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_gzdwjzw"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_nl"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_gx"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_ysr"/>
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zyshgx4_ynjtjjlxhgyqk"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ�������������ԭ��
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtjjknqkjyy"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							���׵�λ
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqdw" />
					</td>
					<td colspan="2">
						<div align="center">
							ĸ�׵�λ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="mqdw" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="center">
							ƶ��ѧ���ǼǱ�
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ס����
						</div>
					</td>
					<td>
						<bean:write name='rs' property="szqs" />
					</td>
					<td colspan="2">
						<div align="center">
							���ҵ绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="qsdh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�س�
						</div>
					</td>
					<td>
						<bean:write name='rs' property="tc" />
					</td>
					<td colspan="2">
						<div align="center">
							�ͲͿ�����
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jckkh" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ʱ�μӹ��ڹ���ѧ
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="hscjgqgzx" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����ѧ���ɲ����
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="drxsgbqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��У�ڼ�����ʱ������ֽ�ѧ��
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="zxqjhschghzjxj" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥ���
						</div>
					</td>
					<td align="right">
						�Ƿ�ƶ����
					</td>
					<td colspan="4">
						<bean:write name='rs' property="pkx" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							����ְҵ
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fqzy" />
					</td>
					<td colspan="2">
						<div align="center">
							ĸ��ְҵ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="mqzy" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							�����ͥ����
						</div>
					</td>
					<td>
						<bean:write name='rs' property="snjtsr" />
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ������Դ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jtjjly" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ͥÿ���ṩ�����
						</div>
					</td>
					<td>
						<bean:write name='rs' property="jtmytgshf" />
					</td>
					<td colspan="2">
						<div align="center">
							�����Ƿ���Ƿծ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="jzsfyqz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��ĸ�Ƿ��в���м�
						</div>
					</td>
					<td>
						<bean:write name='rs' property="fmsfycj" />
					</td>
					<td colspan="2">
						<div align="center">
							��ĸ�Ƿ���
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="fmsfjz" />
					</td>
				</tr>
				<tr>
					<td colspan="2" scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="5">
						<bean:write name='rs' property="sqly" />
					</td>
				</tr>
				<logic:equal name="user" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td colspan="3">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
					</tr>
				</logic:equal>
				<tr>
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
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
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
						<div align="left">
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
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
