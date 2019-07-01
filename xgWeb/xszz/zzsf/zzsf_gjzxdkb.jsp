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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								�й��������й�����ѧ���������
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="col" width="4%">
								<div align="center">
									����������
								</div>
							</td>
							<td colspan="4" scope="col">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="5" scope="col">
								<div align="center">
									<logic:empty name="rs" property="csrq">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="csrq">
										<bean:write name='rs' property="csrq" />
									</logic:notEmpty>
								</div>
							</td>
							<td width="12%" rowspan="5" scope="col">
								<div align="center">
									��Ƭ
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ѧ��֤����
								</div>
							</td>
							<td colspan="10">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									�������ڵ�
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="hjszd" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="8" scope="row">
								<div align="center">
									�������
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<logic:equal name="jkzk" value="����">
    								��
    								</logic:equal>
									<logic:notEqual name="jkzk" value="����">
    								��
    								</logic:notEqual>
									����&nbsp;
									<logic:equal name="jkzk" value="һ��">
    								��
    								</logic:equal>
									<logic:notEqual name="jkzk" value="һ��">
    								��
    								</logic:notEqual>
									һ��&nbsp;
									<logic:equal name="jkzk" value="��">
    								��
    								</logic:equal>
									<logic:notEqual name="jkzk" value="��">
    								��
    								</logic:notEqual>
									��
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<logic:equal name="hyzk" value="δ��">
    								��
    								</logic:equal>
									<logic:notEqual name="hyzk" value="δ��">
    								��
    								</logic:notEqual>
									δ��&nbsp;
									<logic:equal name="hyzk" value="�ѻ�����Ů">
    								��
    								</logic:equal>
									<logic:notEqual name="hyzk" value="�ѻ�����Ů">
    								��
    								</logic:notEqual>
									�ѻ�����Ů&nbsp;
									<logic:equal name="hyzk" value="�ѻ�����Ů">
    								��
    								</logic:equal>
									<logic:notEqual name="hyzk" value="�ѻ�����Ů">
    								��
    								</logic:notEqual>
									�ѻ�����Ů
									<br />
									<logic:equal name="hyzk" value="ɥż">
    								��
    								</logic:equal>
									<logic:notEqual name="hyzk" value="ɥż">
    								��
    								</logic:notEqual>
									ɥż&nbsp;
									<logic:equal name="hyzk" value="���">
    								��
    								</logic:equal>
									<logic:notEqual name="hyzk" value="���">
    								��
    								</logic:notEqual>
									���
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���ѧ��
								</div>
							</td>
							<td colspan="19">
								<div align="left">
									<logic:equal name="zgxl" value="��ѧ����">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="��ѧ����">
    								��
    								</logic:notEqual>
									��ѧ����&nbsp;
									<logic:equal name="zgxl" value="��ר��ר��ѧУ">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="��ר��ר��ѧУ">
    								��
    								</logic:notEqual>
									��ר��ר��ѧУ&nbsp;
									<logic:equal name="zgxl" value="��רѧУ">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="��רѧУ">
    								��
    								</logic:notEqual>
									��רѧУ
									<br />
									<logic:equal name="zgxl" value="����">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="����">
    								��
    								</logic:notEqual>
									����&nbsp;
									<logic:equal name="zgxl" value="����ѧУ">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="����ѧУ">
    								��
    								</logic:notEqual>
									����ѧУ&nbsp;
									<logic:equal name="zgxl" value="����">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="����">
    								��
    								</logic:notEqual>
									����&nbsp;
									<logic:equal name="zgxl" value="Сѧ">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="Сѧ">
    								��
    								</logic:notEqual>
									Сѧ&nbsp;
									<logic:equal name="zgxl" value="��ä�����ä">
    								��
    								</logic:equal>
									<logic:notEqual name="zgxl" value="��ä�����ä">
    								��
    								</logic:notEqual>
									��ä�����ä
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									�ƶ��绰
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ַ
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									E-Mail��ַ
								</div>
							</td>
							<td colspan="19">
								<div align="left">
									<bean:write name='rs' property="e_mail" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<logic:empty name="rs" property="rxny">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="rxny">
										<bean:write name='rs' property="rxny" />
									</logic:notEmpty>
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��ҵ����
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<logic:empty name="rs" property="byny">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="byny">
										<bean:write name='rs' property="byny" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="xz" />&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									�Ͷ�ѧ��<br />�����꼶
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="nj" />&nbsp;&nbsp;�꼶
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									רҵ���༶
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name='rs' property="zymc" />&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="4" scope="row">
								<div align="center">
									��֤�����
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									֤������
									<br />
									������
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr1_zjlxjhm" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����˹�ϵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_yjkrgx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ
									<br />
									�绰
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr1_lxdh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									ͨѶ��ַ
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr1_txdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									֤������
									<br />
									������
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr2_zjlxjhm" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����˹�ϵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_yjkrgx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ
									<br />
									�绰
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jzr2_lxdh" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									ͨѶ��ַ
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="jzr2_txdz" />
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									���������
								</div>
							</td>
							<td>
								<div align="center">
									����ܽ��
									<br />
									(Сд)
								</div>
							</td>
							<td colspan="19">
								<div align="center">
									<bean:write name='rs' property="jkzje" />
									&nbsp;Ԫ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������&nbsp;&nbsp;
									<bean:write name='rs' property="dkqx" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���ʽ
								</div>
							</td>
							<td colspan="19">
								<logic:equal name="hkfs" value="�ȶϢ���">
    							��
    							</logic:equal>
								<logic:notEqual name="hkfs" value="�ȶϢ���">
    							��
    							</logic:notEqual>
								�ȶϢ���
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="hkfs" value="�ȶ�𻹿">
    							��
    							</logic:equal>
								<logic:notEqual name="hkfs" value="�ȶ�𻹿">
    							��
    							</logic:notEqual>
								�ȶ�𻹿
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="19">
								<logic:equal name="dkzl" value="���������Ϣ������ѧ����">
    							��
    							</logic:equal>
								<logic:notEqual name="dkzl" value="���������Ϣ������ѧ����">
    							��
    							</logic:notEqual>
								���������Ϣ������ѧ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="dkzl" value="�ط�������Ϣ������ѧ����">
    							��
    							</logic:equal>
								<logic:notEqual name="dkzl" value="�ط�������Ϣ������ѧ����">
    							��
    							</logic:notEqual>
								�ط�������Ϣ������ѧ����
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ѧ�ѵ���(����)
								</div>
							</td>
							<td colspan="8">
								<bean:write name='rs' property="xfdj" />
								&nbsp;Ԫ
							</td>
							<td colspan="4">
								<div align="center">
									ס�޷ѵ���(����)
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="zsfdj" />
								&nbsp;Ԫ
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									����ѵ���
									<br />
									(��
									<logic:equal name="shffs" value="��">
    								��
    								</logic:equal>
									<logic:notEqual name="shffs" value="��">
    								��
    								</logic:notEqual>
									��/
									<logic:equal name="shffs" value="��">
    								��
    								</logic:equal>
									<logic:notEqual name="shffs" value="��">
    								��
    								</logic:notEqual>
									��)
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="shfdj" />
								&nbsp;Ԫ
							</td>
							<td colspan="4">
								<div align="center">
									�������ʻ�
									<br />
									���ͼ��˺�
								</div>
							</td>
							<td colspan="9">
								<bean:write name='rs' property="skrzhlxjzh" />
							</td>
						</tr>
						<tr>
							<td colspan="21" scope="row">
								���������������
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;1�����������鼰������������������Ϊ�����������ȫ��ʵ�����˳е�����д��ʵ�����µ�һ�з������Σ�
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;2�����˳����Դ���������Ϊ����н������ݡ����͵����ϸ�ӡ���ɱ������������ƾ֤��
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;3����������鲻���Ϲ涨�Ľ��������δ������ʱ�����������飻
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;4�����˱�֤��ȡ�����д���󣬰�ʱ�������Ϣ��
								<br />
								<br />
								<div align="center">
									������ǩ�֣�
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									�����໤��ǩ�֣�������ڣ���
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��֤��ǩ�֣�
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</div>
								<div align="center">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
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
	</div>
</body>
</html:html>
