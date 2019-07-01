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
						<strong>
							<logic:equal name="xxdm" value="10856">
								<h3>
									�Ϻ��иߵ�ѧУ��ͥ��������ѧ���϶������
								</h3>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856">
								<h3>
									�ߵ�ѧУ��ͥ��������ѧ���϶������
								</h3>
							</logic:notEqual> 
						</strong>
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
						&nbsp;&nbsp;�༶��
						<logic:equal name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="bjmc" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;&nbsp;</u>
						</logic:notEqual>
						&nbsp;&nbsp;ѧ�ţ�
						<logic:equal name="xh" value="isnull">
							<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</logic:equal>
						<logic:notEqual name="xh" value="isnull">
							<u>&nbsp;&nbsp;<bean:write name='rs' property="xh" />&nbsp;&nbsp;</u>
						</logic:notEqual>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="4" scope="col">
								<div align="center">
									ѧ���������
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="18%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="10%">
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
							<td>
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ѧǰ����
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
									��Դ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="syd" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name="jtlxdh" value="-">
   										�����ţ�-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   									</logic:equal>
									<logic:notEqual name="jtlxdh" value="-">
										<bean:write name='rs' property="jtlxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="left">
									�Ƿ�Ը��μӴ��ƻ�־Ը�
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="xh" value="isnull">
   										&nbsp;
   									</logic:equal>
									<logic:notEqual name="xh" value="isnull">
										<bean:write name='rs' property="sfyycjcshzyhd" />
									</logic:notEqual>
								</div>
							</td>
							<td colspan="4">
								<div align="left">
									�Ƿ�Ը�����������ѧ������ڹ���ѧ
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="xh" value="isnull">
   										&nbsp;
   									</logic:equal>
									<logic:notEqual name="xh" value="isnull">
										<bean:write name='rs' property="sfyysqgjzxdkhqgzx" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								<div align="left">
									ѧ���ҳ���໤������ǩ����
								</div>
							</td>
							<td colspan="5">
								<div align="left">
									ѧ����������ǩ����
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="9" scope="col">
								<div align="center">
									<strong>��ͥ���������������</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="2" scope="row">
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td colspan="7">
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfjq" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfjq" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;��ȫ&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfge" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfge" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;�¶�&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfdq" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfdq" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;����&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfcj" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfcj" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;�м�&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfjls" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfjls" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;������&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfly" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfly" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;����&nbsp;&nbsp;
								<logic:equal name="xh" value="isnull">
   									&nbsp;��
   								</logic:equal>
								<logic:notEqual name="xh" value="isnull">
									<logic:equal name="sfzb" value="��">
										&nbsp;��
									</logic:equal>
									<logic:equal name="sfzb" value="��">
										&nbsp;��
									</logic:equal>
								</logic:notEqual>
								&nbsp;�ز�
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<div align="left">
									ע��1.����ָһ��ȥ��;2.�����ͥע���Է��������;3.�¶�д���໤�˵�������������������;
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.���������Ÿ���ͥ���ṩ��Ӧ֤��;5.�м����ز���ͥ���ṩ�ؼ�����ҽԺ֤��
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									��ͥ�˿�
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��ѧ��
									<br />
									��ϵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									����(ѧϰ)��λ
								</div>
							</td>
							<td width="12%">
								<div align="center">
									ְҵ
								</div>
							</td>
							<td width="12%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
							<td width="12%">
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
									&nbsp;
									<bean:write name='rs' property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
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
									&nbsp;
									<bean:write name='rs' property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
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
									&nbsp;
									<bean:write name='rs' property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
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
									&nbsp;
									<bean:write name='rs' property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
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
									&nbsp;
									<bean:write name='rs' property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="2">
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
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ����״��
								</div>
							</td>
							<td colspan="8">
								��ͥ�˾�������
								<logic:equal name="jtrjnsr" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtrjnsr" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtrjnsr" />&nbsp;</u>
								</logic:notEqual>
								(Ԫ)��ѧ���ڱ������������
								<logic:equal name="xszbdszqk" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="xszbdszqk" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="xszbdszqk" />&nbsp;</u>��
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
								<logic:equal name="jtzstfywsj" value="isnull">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:equal>
								<logic:notEqual name="jtzstfywsj" value="isnull">
									<u>&nbsp;<bean:write name='rs' property="jtzstfywsj" />&nbsp;</u>
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
								<br />
								<br />
								<div align="right">
									��ͥ���ڵ������������ƣ�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="right">
									���Ӹǹ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��������
									<br />
									ͨѶ��ַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="mzbm_txdz" />
							</td>
							<td width="10%">
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name="mzbm_lxdh" value="-">
    									(����)-&nbsp;&nbsp;
    								</logic:equal>
									<logic:notEqual name="mzbm_lxdh" value="-">
										<bean:write name='rs' property="mzbm_lxdh" />
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="5" scope="col">
								<div align="center">
									<strong>��ͥ���������϶�</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td width="4%" rowspan="3" scope="row">
								<div align="center">
									��������
								</div>
							</td>
							<td width="4%" rowspan="3">
								<div align="center">
									�Ƽ�����
								</div>
							</td>
							<td width="42%">
								<logic:equal name="xysh" value="�ر�����">
								&nbsp;A.��ͥ�����ر�����&nbsp;��
								</logic:equal>
								<logic:notEqual name="xysh" value="�ر�����">
								&nbsp;A.��ͥ�����ر�����&nbsp;��
								</logic:notEqual>
							</td>
							<td width="4%" rowspan="3">
								<div align="center">
									��������
								</div>
							</td>
							<td width="46%" rowspan="3">
								<bean:write name='rs' property="xyshyj" />
								<br />
								Ժ(ϵ)����С���鳤ǩ�֣�
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="xysh" value="һ������">
								&nbsp;B.��ͥ��������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</logic:equal>
								<logic:notEqual name="xysh" value="һ������">
								&nbsp;B.��ͥ��������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="xysh" value="��ͨ��">
								&nbsp;C.��ͥ���ò�����&nbsp;&nbsp;&nbsp;��
								</logic:equal>
								<logic:notEqual name="xysh" value="��ͨ��">
								&nbsp;C.��ͥ���ò�����&nbsp;&nbsp;&nbsp;��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									�϶����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td>
								<logic:equal name="xysh" value="δ���">
									<br />
									&nbsp;��&nbsp;&nbsp;ͬ������С�������
									<br />
									&nbsp;��&nbsp;&nbsp;��ͬ������С�������
									<br />
									<br />
									&nbsp;����Ϊ
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="xysh" value="δ���">
										<br />
										&nbsp;��&nbsp;&nbsp;ͬ������С�������
										<br />
										&nbsp;��&nbsp;&nbsp;��ͬ������С�������
										<br />
										<br />
										&nbsp;����Ϊ
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:notEqual>
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								&nbsp;Ժ(ϵ)�������鳤ǩ�֣�
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
								</div>
								<div align="right">
									���Ӹǲ��Ź��£� &nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td>
								<logic:equal name="xxsh" value="δ���">
									<br />
									&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
									<br />
									&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�������
									<br />
									<br />
									&nbsp;����Ϊ
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="xxsh" value="δ���">
									<logic:equal name="xy_xx" value="same">
										<br />
										&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
										<br />
										&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�������
										<br />
										<br />
										&nbsp;����Ϊ
										<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									</logic:equal>
									<logic:equal name="xy_xx" value="notSame">
										<br />
										&nbsp;��&nbsp;&nbsp;ͬ�⹤���������С�������
										<br />
										&nbsp;��&nbsp;&nbsp;��ͬ�⹤���������С�������
										<br />
										<br />
										&nbsp;����Ϊ
										<u>&nbsp;&nbsp;<bean:write name='rs' property="xxsh" />&nbsp;&nbsp;</u>��
									</logic:equal>
								</logic:notEqual>
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								&nbsp;ѧУѧ�������������������ǩ�֣�
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>�� &nbsp;&nbsp;
								</div>
								<div align="right">
									���Ӹǲ��Ź��£� &nbsp;&nbsp;
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
