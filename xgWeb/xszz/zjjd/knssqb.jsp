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
	<title><bean:message key="lable.title" />
	</title>
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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=knssq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								�㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />�����������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" scope="col" width="6%">
								<div align="center">
									ѧ�����˻������
								</div>
							</td>
							<td scope="col" width="9%">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td scope="col" width="11%">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name="rs" property="csrq" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
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
									������ò
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ
									<br />
									�˿���
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="52">
								<div align="center">
									�²�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfgc" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sfgc" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfdq" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sfdq" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sflszn" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sflszn" value="��">
											��&nbsp;��&nbsp;/&nbsp;��&nbsp;��
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									��ͥ
									<br />
									ͨѶ
									<br />
									��Ϣ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϸͨѶ��ַ
								</div>
							</td>
							<td colspan="8">
								<bean:write name="rs" property="xxtxdz" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row">
								<div align="center">
									��ͥ��Ա���
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
								</div>
							</td>
							<td width="8%">
								<div align="center">
									��ѧ����ϵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(ѧϰ)��λ
								</div>
							</td>
							<td width="8%">
								<div align="center">
									ְҵ
								</div>
							</td>
							<td width="8%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									����״��
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
									&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy1_jkzk" />
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
									&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy2_jkzk" />
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
									&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy3_jkzk" />
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
									&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy4_jkzk" />
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
									&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									�ѻ�
									<br />
									����
									<br />
									���
								</div>
							</td>
							<td>
								<div align="center">
									������ѧ<br />����
								</div>
							</td>
							<td>
								<div align="center">
									������ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									������־<br />��ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									���ҽ�ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" /><br />��ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									�ڹ���ѧ
								</div>
							</td>
							<td>
								<div align="center">
									ѧ�Ѽ���
								</div>
							</td>
							<td>
								<div align="center">
									��ʱ<br />����
								</div>
							</td>
							<td>
								<div align="center">
									����������<br />�������
								</div>
							</td>
							<td>
								<div align="center">
									�ϼ�
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjzxdk" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjzxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjlzjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_gjjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_xyjxj" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_qgzx" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_xfjm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_lxbz" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name="rs" property="yhzzqk_ddzfshjxj" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="yhzzqk_hj" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									Ӱ��
									<br />
									��ͥ
									<br />
									����
									<br />
									״��
									<br />
									�й�
									<br />
									��Ϣ
								</div>
							</td>
							<td colspan="10">
								��ͥ������Ȼ�ֺ������
								<logic:empty name="rs" property="jtzszrzhqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzszrzhqk">
								<u>&nbsp;<bean:write name="rs" property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEmpty>
								����ͥ����ͻ�������¼���
								<logic:empty name="rs" property="jttfywsj">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jttfywsj">
								<u>&nbsp;<bean:write name="rs" property="jttfywsj" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								��ͥ��Ա��м����������Ͷ������������
								<logic:empty name="rs" property="jtcyycjnmndlqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcyycjnmndlqk">
								<u>&nbsp;<bean:write name="rs" property="jtcyycjnmndlqk" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								��ͥ��Աʧҵ�����
								<logic:empty name="rs" property="jtcysyqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcysyqk">
								<u>&nbsp;<bean:write name="rs" property="jtcysyqk" />&nbsp;</u>
								</logic:notEmpty>
								����ͥǷծ�����
								<logic:empty name="rs" property="jtqzqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqzqk">
								<u>&nbsp;<bean:write name="rs" property="jtqzqk" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								���������
								<logic:empty name="rs" property="qtqk">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="qtqk">
								<u>&nbsp;<bean:write name="rs" property="qtqk" />&nbsp;</u>
								</logic:notEmpty>
								��
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ
									<br />
									��ϸ
									<br />
									����
									<br />
									���
									<br />
									˵��
								</div>
							</td>
							<td colspan="10">
								<logic:empty name="rs" property="jtjjqkxxsm">
								<br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqkxxsm">
								<br /><bean:write name="rs" property="jtjjqkxxsm" /><br />
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td rowspan="2" scope="row">
								<div align="center">
									����
									<br />
									����
									<br />
									��Ϣ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϸͨѶ��ַ
								</div>
							</td>
							<td colspan="8">
								<bean:write name="rs" property="mzbm_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="mzbm_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="mzbm_lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��
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
							<td colspan="10">
								<br />
								�϶�Ϊ��
								<logic:equal name="rs" property="fdysh" value="һ������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="һ������">
								��
								</logic:notEqual>
								&nbsp;һ������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="��������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="��������">
								��
								</logic:notEqual>
								&nbsp;��������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="����">
								��
								</logic:notEqual>
								&nbsp;����&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="fdysh" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="������">
								��
								</logic:notEqual>
								&nbsp;������
								<br />
								<logic:empty name="rs" property="fdyshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name="rs" property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ϵ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="10">
								<br />
								�϶�Ϊ��
								<logic:equal name="rs" property="xysh" value="һ������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="һ������">
								��
								</logic:notEqual>
								&nbsp;һ������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="��������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="��������">
								��
								</logic:notEqual>
								&nbsp;��������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="����">
								��
								</logic:notEqual>
								&nbsp;����&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xysh" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="������">
								��
								</logic:notEqual>
								&nbsp;������
								<br />
								<logic:empty name="rs" property="xyshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name="rs" property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧ
									<br />
									Ժ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="10">
								<br />
								�϶�Ϊ��
								<logic:equal name="rs" property="xxsh" value="һ������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="һ������">
								��
								</logic:notEqual>
								&nbsp;һ������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="��������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="��������">
								��
								</logic:notEqual>
								&nbsp;��������&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="����">
								��
								</logic:notEqual>
								&nbsp;����&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="xxsh" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="������">
								��
								</logic:notEqual>
								&nbsp;������
								<br />
								<logic:empty name="rs" property="xxshyj">
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
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
