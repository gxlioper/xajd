<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/zgkydx_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									����
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�༶
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤��
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="ss" />
								</div>
							</td>
							<td>
								<div align="center">
									�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="dh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ס��ַ
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name="rs" property="jtszd" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ��ϵ�绰���ʱ�
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtlxdhjyzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									���׵�λ��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="fqdwlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���ڻ�����֯��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="szjclxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									ĸ�׵�λ��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="mqdwlxdh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
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
							<td width="30%">
								<div align="center">
									������λ(�Ͷ�ѧУ)
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
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy1_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy1_xm">
										<bean:write name="rs" property="jtcy1_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_cyzt" />
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
									<bean:write name="rs" property="jtcy2_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy2_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy2_xm">
										<bean:write name="rs" property="jtcy2_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_cyzt" />
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
									<bean:write name="rs" property="jtcy3_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy3_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy3_xm">
										<bean:write name="rs" property="jtcy3_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_cyzt" />
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
									<bean:write name="rs" property="jtcy4_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy4_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy4_xm">
										<bean:write name="rs" property="jtcy4_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_cyzt" />
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
									<bean:write name="rs" property="jtcy5_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy5_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy5_xm">
										<bean:write name="rs" property="jtcy5_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy6_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy6_xm">
										<bean:write name="rs" property="jtcy6_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_xb" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_gx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="jtcy7_xm">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcy7_xm">
										<bean:write name="rs" property="jtcy7_hklx" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy7_cyzt" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								ע:1.��ͥ��Աָ��ѧ������֮�⹲ͬ�����ֱ��Ѫ�׼��ֵܽ��ã�
								<br />
								&nbsp;&nbsp;&nbsp;2.�������ͷ֣�A&nbsp;(ũҵ����)&nbsp;&nbsp;&nbsp;B&nbsp;(��ũҵ����)��
								<br />
								&nbsp;&nbsp;&nbsp;3.��ҵ״̬һ������д��������ũ���¸ڡ��򹤡����ݡ���ѧ(��ѧ����ר�����С����С�Сѧ)�ȡ�
							</td>
						</tr>
						<tr>
							<td colspan="8">
								���뽨����������(��ֱ���ڶ�Ӧ���ǰ�ġ������̡���������Ӧ�����ϼ�Ҫ˵��):
								<br />
								<br />
								<logic:empty name="rs" property="sqly_lzgjjpkxdncdq">
								&nbsp;&nbsp;�����Թ��Ҽ�ƶ���ص�ũ�������__________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_lzgjjpkxdncdq">
								&nbsp;&nbsp;�����Թ��Ҽ�ƶ���ص�ũ�������<u>&nbsp;<bean:write name="rs"
											property="sqly_lzgjjpkxdncdq" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_syxszdshbzdczjt">
								&nbsp;&nbsp;�����������������ϵĳ����ͥ��_______________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_syxszdshbzdczjt">
								&nbsp;&nbsp;�����������������ϵĳ����ͥ��<u>&nbsp;<bean:write name="rs"
											property="sqly_syxszdshbzdczjt" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_gehjjknddqjt">
								&nbsp;&nbsp;���¶��򾭼����ѵĵ��׼�ͥ��____________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_gehjjknddqjt">
								&nbsp;&nbsp;�̹¶��򾭼����ѵĵ��׼�ͥ��<u>&nbsp;<bean:write name="rs"
											property="sqly_gehjjknddqjt" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_fmyfhsfxg">
								&nbsp;&nbsp;����ĸһ����˫���¸�(ʧҵ)��____________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_fmyfhsfxg">
								&nbsp;&nbsp;�̸�ĸһ����˫���¸�(ʧҵ)��<u>&nbsp;<bean:write name="rs"
											property="sqly_fmyfhsfxg" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcywqzndl">
								&nbsp;&nbsp;����ͥ��Ա����18-55�����׳�Ͷ�����_____________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcywqzndl">
								&nbsp;&nbsp;�̼�ͥ��Ա����18-55�����׳�Ͷ�����<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcywqzndl" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyycjhjbrssndl">
								&nbsp;&nbsp;����ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������____________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyycjhjbrssndl">
								&nbsp;&nbsp;�̼�ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyycjhjbrssndl" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyyzdjbxzfdefy">
								&nbsp;&nbsp;����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�_________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyyzdjbxzfdefy">
								&nbsp;&nbsp;�̼�ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyyzdjbxzfdefy" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyzylghyscyzjsfywjy">
								&nbsp;&nbsp;����ͥ�������������ϳ�Ա�����ܷ����������_________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyzylghyscyzjsfywjy">
								&nbsp;&nbsp;�̼�ͥ�������������ϳ�Ա�����ܷ����������<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyzylghyscyzjsfywjy" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_jtcyzszrzh">
								&nbsp;&nbsp;����ͥ����Ա��������Ȼ�ֺ�������ͻ���ֱ䣬��������Ʋ����ش���ʧ��_____________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_jtcyzszrzh">
								&nbsp;&nbsp;�̼�ͥ����Ա��������Ȼ�ֺ�������ͻ���ֱ䣬��������Ʋ����ش���ʧ��<u>&nbsp;<bean:write
											name="rs" property="sqly_jtcyzszrzh" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<logic:empty name="rs" property="sqly_qtqk">
								&nbsp;&nbsp;���������¼�ͥ�������������____________________________________________________
								<br />
									<br />
								&nbsp;&nbsp;__________________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly_qtqk">
								&nbsp;&nbsp;���������¼�ͥ�������������<u>&nbsp;<bean:write name="rs"
											property="sqly_qtqk" />&nbsp;</u>
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									������:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl1_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									���߲��ϻ��ؼ���ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl1_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl1_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl2_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									���߲��ϻ��ؼ���ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl2_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl2_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl3_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									���߲��ϻ��ؼ���ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl3_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl3_dh" />
								</div>
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
								<div align="center">
									<bean:write name="rs" property="zmcl4_mc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									���߲��ϻ��ؼ���ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zmcl4_jg" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zmcl4_dh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<br />
								<strong>&nbsp;&nbsp;���˳�ŵ���ϳ���������������ṩ��ȫ��֤�����Ͼ���ʵ�����е����ڲ�ʵ��������ɵ�һ�к����<br />
									&nbsp;&nbsp;����Ҳ���������ѣ��ڼ���ѧ����ʵ���ţ�Ŭ�������������š���ǿ��ƴ�����ֹۡ����ϣ�������ĳɼ��ر���ĸ�������Ĺ��ҡ�ѧУ��������<br />
									<br /> </strong>
								<div align="right">
									��ŵ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����
									<br />
									��׼
									<br />
									����
									<br />
									����
									<br />
									����
									<br />
									����
									<br />
									��
								</div>
							</td>
							<td colspan="7">
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
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;�༶��������������������������������Ϊ
								<logic:empty name="rs" property="pyrs">______</logic:empty>
								<logic:notEmpty name="rs" property="pyrs">
									<u>&nbsp;<bean:write name="rs" property="pyrs" />&nbsp;</u>
								</logic:notEmpty>
								�ˣ�����ͬ�⽨��A����
								<logic:empty name="rs" property="adrs">______</logic:empty>
								<logic:notEmpty name="rs" property="adrs">
									<u>&nbsp;<bean:write name="rs" property="adrs" />&nbsp;</u>
								</logic:notEmpty>
								Ʊ��ͬ�⽨��B����
								<logic:empty name="rs" property="bdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="bdrs">
									<u>&nbsp;<bean:write name="rs" property="bdrs" />&nbsp;</u>
								</logic:notEmpty>
								Ʊ��ͬ�⽨��C����
								<logic:empty name="rs" property="cdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="cdrs">
									<u>&nbsp;<bean:write name="rs" property="cdrs" />&nbsp;</u>
								</logic:notEmpty>
								Ʊ����ͬ�⽨����
								<logic:empty name="rs" property="btyrs">______</logic:empty>
								<logic:notEmpty name="rs" property="btyrs">
									<u>&nbsp;<bean:write name="rs" property="btyrs" />&nbsp;</u>
								</logic:notEmpty>
								Ʊ����ӳ���ڲ��轨�������
								<logic:empty name="rs" property="csbyjdrs">______</logic:empty>
								<logic:notEmpty name="rs" property="csbyjdrs">
									<u>&nbsp;<bean:write name="rs" property="csbyjdrs" />&nbsp;</u>
								</logic:notEmpty>
								Ʊ��ͬѧ��ӳ���ڲ��轨���ľ������Ϊ:
								<logic:empty name="rs" property="byjdjtqk">________________________</logic:empty>
								<logic:notEmpty name="rs" property="byjdjtqk">
									<u>&nbsp;<bean:write name="rs" property="byjdjtqk" />&nbsp;</u>
								</logic:notEmpty>
								��������ͬѧ��ӳ���&nbsp;
								<logic:empty name="rs" property="byjdjtqk">
								����ʵ&nbsp;&nbsp;������ʵ��
								</logic:empty>
								<logic:notEmpty name="rs" property="byjdjtqk">
									<logic:equal name="rs" property="dcfyqk" value="��ʵ">
										����ʵ&nbsp;&nbsp;������ʵ��
									</logic:equal>
									<logic:notEqual name="rs" property="dcfyqk" value="��ʵ">
										����ʵ&nbsp;&nbsp;�̲���ʵ��
									</logic:notEqual>
								</logic:notEmpty>
								<br />
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;�༶���������������Ϊ&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="A��">
									��A��
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="A��">
									��A��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="B��">
									��B��
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="B��">
									��B��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="bjpyjg" value="C��">
									��C��
								</logic:equal>
								<logic:notEqual name="rs" property="bjpyjg" value="C��">
									��C��
								</logic:notEqual>
								<br />
								<br />
								<div align="right">
									������ǩ��:____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ϵ��
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;���༶���������ϵ��ˣ�ͬ�����Ϊ
								<logic:equal name="rs" property="xbshjg" value="A��">
									��A��
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="A��">
									��A��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xbshjg" value="B��">
									��B��
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="B��">
									��B��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xbshjg" value="C��">
									��C��
								</logic:equal>
								<logic:notEqual name="rs" property="xbshjg" value="C��">
									��C��
								</logic:notEqual>
								��ͥ��������ѧ����
								<br />
								<br />
								<div align="right">
									ǩ��(����)��_____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;��ѧУ��ˣ�ͬ�����Ϊ
								<logic:equal name="rs" property="xxshjg" value="A��">
									��A��
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="A��">
									��A��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xxshjg" value="B��">
									��B��
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="B��">
									��B��
								</logic:notEqual>
								&nbsp;&nbsp;
								<logic:equal name="rs" property="xxshjg" value="C��">
									��C��
								</logic:equal>
								<logic:notEqual name="rs" property="xxshjg" value="C��">
									��C��
								</logic:notEqual>
								��ͥ��������ѧ����
								<br />
								<br />
								<div align="right">
									ǩ&nbsp;&nbsp;�£�_____________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
