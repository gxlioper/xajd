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
	<style type="text/css">
	<!--
	.style1 {font-family: "�����п�"}
	-->
	</style>
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
				<td scope="col">
					<div align="center">
							<h1 class="style1">
						<strong>
								������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />
						</strong>
							</h1> 
					</div>
					<div align="center">
							<h2>
						<strong>
								У���ڹ���ѧ�걨��
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ϵ(��)&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;�༶&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;����&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;�Ա�&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;&nbsp;��������:&nbsp;
						<bean:write name='rs' property="sqsj_year" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_mon" />
						&nbsp;��&nbsp;
						<bean:write name='rs' property="sqsj_day" />
						&nbsp;��
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									����
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									������ò
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="zzmm" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									�����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="ssbh" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ���绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row" width="4%">
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
							<td scope="row" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(ѧϰ)��λ��ְ��
								</div>
							</td>
							<td>
								<div align="center">
									������(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gzdwjzw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧϰ���
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xxqk" />
								<div align="right">
									(���������޲�����γ�&nbsp;
									<bean:write name='rs' property="bkhywbjgkc" />
									)&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								ÿ��Ӧ�ɸ��ַ���&nbsp;
								<bean:write name='rs' property="mnyjngzfy" />
								&nbsp;Ԫ
							</td>
							<td colspan="3">
								��ͥÿ���ṩ&nbsp;
								<bean:write name='rs' property="jtmntg" />
								&nbsp;Ԫ
							</td>
							<td colspan="2">
								�ϼ�ÿ����ȱ����&nbsp;
								<bean:write name='rs' property="hjmnsqfy" />
								&nbsp;Ԫ
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ͥ���þ������
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="jtjjknqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									Ƿ��ѧ����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtjjknqk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�Ѵ������༰���
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtjjknqk" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" scope="row">
								<div align="center">
									�кμ����س�(�鷨�����Ե�)
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yhtc" />
								</div>
							</td>
							<td>
								<div align="center">
									����ְ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="btzw" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									����μ��ڹ���ѧ������
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xssq" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ͥ����
									<br />
									�������
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									�¶���
									<bean:write name='rs' property="sfgr" />
									�� ��ʿ��Ů��
									<bean:write name='rs' property="sflszn" />
									�� �����뻧��
									<bean:write name='rs' property="sfwsrh" />
									�� �ز�����
									<bean:write name='rs' property="sfzbh" />
									�� �ͱ�����
									<bean:write name='rs' property="sfdbh" />
									<br />
									��ĸ˫�¸ڣ�
									<bean:write name='rs' property="sffmsxg" />
									�� ��ũ����
									<bean:write name='rs' property="sfcnh" />
									�� ������(��ͥ���벻����֧����ѧ��������)��
									<bean:write name='rs' property="sfdsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									Ŀǰ�ڹ���ѧ���
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="mqqgzxqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ӦƸ���
								</div>
							</td>
							<td colspan="4">
								ӦƸ��λ�����š���λ:
								<bean:write name='rs' property="gwdm" />
							</td>
							<td colspan="3">
								�Ƿ�Ը��ͳһ����:
								<bean:write name='rs' property="sfyytytp" />
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="5" scope="row">
								<div align="center">
									���������
								</div>
							</td>
							<td colspan="2">
								���еȵ�:
								<bean:write name='rs' property="cxdd" />
							</td>
							<td>
								ƽʱ����
							</td>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���������
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���޼�ͥ���ڵ�����(�ְ�)�������ų��ߵļ�ͥ��������֤�����С�&nbsp;�ޡ�
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���϶�&nbsp;������𡢳̶ȣ�&nbsp;ƶ����&nbsp;�ǡ�&nbsp;���&nbsp;&nbsp;������&nbsp;�ǡ�&nbsp;���&nbsp;&nbsp;�������:
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<div align="right">
									ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									<bean:message key="lable.xb" />���
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ѧ�������
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									�ù���λ���
								</div>
							</td>
							<td colspan="7">
								<div align="right">
									ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��λ����
								</div>
							</td>
							<td colspan="7">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="7">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					&nbsp;&nbsp;&nbsp;&nbsp;Ӧ������֤��(��ӡ��)
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
