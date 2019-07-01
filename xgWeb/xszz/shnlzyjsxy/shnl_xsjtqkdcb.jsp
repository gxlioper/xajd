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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								�ߵ�ѧУѧ������ͥ��������
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧУ��
						<logic:equal name="xxmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xxmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xxmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
						<logic:equal name="xymc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xymc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xymc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;רҵ��
						<logic:equal name="zymc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="zymc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="zymc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						<br />
						�꼶��
						<logic:equal name="nj" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="nj" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="nj" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;&nbsp;&nbsp;�༶��
						<logic:equal name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
					</div>
				</td>
			</tr>
			<tr>
				<td>
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
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="11%">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
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
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧǰ<br />����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									��&nbsp;����
    									&nbsp;&nbsp;
    									��&nbsp;ũ��
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="rxqhk" value="����">
    									��&nbsp;����
    									&nbsp;&nbsp;
    									��&nbsp;ũ��
    								</logic:equal>
									<logic:equal name="rxqhk" value="ũ��">
    									��&nbsp;����
    									&nbsp;&nbsp;
    									��&nbsp;ũ��
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ
									<br />
									�˿���
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td>
								<div align="center">
									��ҵѧУ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="byxx" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�����س�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="grtc" />
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
									<logic:equal name="xh" value="isnull">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfgc" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
									<logic:equal name="sfgc" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
    								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfdq" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
   								 	</logic:equal>
									<logic:equal name="sfdq" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
    								</logic:notEqual>
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="xh" value="isnull">
    									��&nbsp;��
    									&nbsp;&nbsp;
   								 		��&nbsp;��
 								   	</logic:equal>
 								   	<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sflszn" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
   									</logic:equal>
									<logic:equal name="sflszn" value="��">
    									��&nbsp;��
    									&nbsp;&nbsp;
    									��&nbsp;��
    								</logic:equal>
    								</logic:notEqual>
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
								<bean:write name='rs' property="jtxxtxdz" />
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
									<bean:write name='rs' property="jtyzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:equal name="jtlxdh" value="-">
   										�����ţ�-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   									</logic:equal>
									<logic:notEqual name="jtlxdh" value="-">
										<bean:write name='rs' property="jtlxdh" />
									</logic:notEqual>
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
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy1_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy2_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy3_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy4_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
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
								��ͥ�˾�������
								<logic:equal name="jtrjnsr" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtrjnsr" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtrjnsr" />&nbsp;</u>
								</logic:notEqual>
								(Ԫ)��ѧ����ѧ���ѻ��������
								<logic:equal name="xsbxnyhzzqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									<br />
									&nbsp;<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="xsbxnyhzzqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="xsbxnyhzzqk" />&nbsp;</u>��
								</logic:notEqual>
								<br />
								��ͥ������Ȼ�ֺ������
								<logic:equal name="jtzszrzhqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzszrzhqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEqual>
								����ͥ����ͻ�������¼���
								<logic:equal name="jtzstfsjqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzstfsjqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzstfsjqk" />&nbsp;</u>
								</logic:notEqual>
								��
								<br />
								��ͥ��Ա��м����������Ͷ������������
								<logic:equal name="jtcyycjnmrldnlrqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtcyycjnmrldnlrqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtcyycjnmrldnlrqk" />&nbsp;</u>
								</logic:notEqual>
								��
								<br />
								��ͥ��Աʧҵ�����
								<logic:equal name="jtcysyqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtcysyqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtcysyqk" />&nbsp;</u>
								</logic:notEqual>
								����ͥǷծ�����
								<logic:equal name="jtqzqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtqzqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtqzqk" />&nbsp;</u>
								</logic:notEqual>
								��
								<br />
								���������
								<logic:equal name="qtqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="qtqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="qtqk" />&nbsp;</u>
								</logic:notEqual>
								��
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ǩ��
								</div>
							</td>
							<td>
								<div align="center">
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
							<td>
								<div align="center">
									ѧ��
									<br />
									�ҳ�
									<br />
									���
									<br />
									����
								</div>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
							<td colspan="2">
								<div align="center">
									ѧ����ͥ
									<br />
									���ڵ���
									<br />
									���ֵ�
									<br />
									��������
								</div>
							</td>
							<td colspan="2">
								������ǩ�֣�
								<br />
								<br />
								��λ���ƣ�
								<div align="center">
									(�Ӹǹ���)
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;&nbsp;&nbsp;
								</div>
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
								<bean:write name='rs' property="mzbm_xxtxdz" />
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
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<logic:equal name="mzbm_lxdh" value="-">
    									�����ţ�-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    								</logic:equal>
									<logic:notEqual name="mzbm_lxdh" value="-">
										<bean:write name='rs' property="mzbm_lxdh" />
									</logic:notEqual>
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
