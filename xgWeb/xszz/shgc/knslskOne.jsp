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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��Ϣά�� - ��������ʷ����ϸ��Ϣ
		</div>
	</div>
		<html:form action="shgc_xszz_new.do?method=knslskOne" method="post">
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" />">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="rs" property="xh" />">
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />">

			<table class="tbstyle" width="100%">
				<tr>
				<td align="center" colspan="2">
					ѧ��
				</td>
				<td align="left" colspan="3">
					<bean:write name="rs" property="xh" />
				</td>
				<td colspan="2">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xb"/>
				</td>
				<td colspan="2">
					<div align="center">
						���֤��
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzmc"/>
				</td>
				<td colspan="2">
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xymc"/>
				</td>
				<td colspan="2">
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�༶
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bjmc"/>
				</td>
				<td colspan="2">
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��Դ��
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="syd"/>
				</td>
				<td colspan="3">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="lxdh"/>
				</td>
				<td colspan="2">
					<div align="center">
						��ѧǰ����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="rxqhk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzz"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="yzbm"/>
				</td>
				<td colspan="2">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtlxdh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�Ƿ�Ը��μ�
						<br />
						���ƻ�־Ը�
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfyycjcshzyhd"/>
				</td>
				<td colspan="2">
					<div align="center">
						�Ƿ�Ը���������
						<br />
						��ѧ������ڹ���ѧ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfyysqgjzxdkhqgzx"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtlx"/>
				</td>
				<td colspan="2">
					<div align="center">
						��ͥ�˾�������(Ԫ)
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtrjnsr"/>
				</td>
			</tr>
			<tr>
				<td rowspan="6" width="4%">
					<div align="center">
						��ͥ��Ա���
					</div>
				</td>
				<td width="12%" align="center">
					����
				</td>
				<td width="10%" align="center">
					����
				</td>
				<td width="12%" align="center">
					��ѧ��
					<br />
					��ϵ
				</td>
				<td width="12%" align="center">
					ְҵ
				</td>
				<td width="8%" align="center">
					������
					<br />
					(Ԫ)
				</td>
				<td width="8%" align="center">
					����״��
				</td>
				<td align="center">
					����(ѧϰ)��λ
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy1_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy1_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy2_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy2_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy3_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy3_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy4_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy4_gzdw"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:write name="rs" property="jtcy5_xm"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_nl"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_gx"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_zy"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_nsr"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_jkzk"/>
				</td>
				<td align="center">
					<bean:write name="rs" property="jtcy5_gzdw"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						ѧ���ڱ���
						<br />
						�������
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="xszbdszqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
						<br />
						��Ȼ�ֺ����
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzszrzhqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ����
						<br />
						ͻ�������¼�
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="jtzstfywsj"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�������
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="qtqk"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
						<br />
						��ϸͨѶ��ַ
					</div>
				</td>
				<td colspan="6">
					<bean:write name="rs" property="mzbm_txdz"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						����������������
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbm_yzbm"/>
				</td>
				<td colspan="2">
					<div align="center">
						����������ϵ�绰
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzbm_lxdh"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						���
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="nd"/>
				</td>
				<td colspan="2">
					<div align="center">
						�϶�ʱ��
					</div>
				</td>
				<td>
					<bean:write name='rs' property="rdsj" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						�����϶����
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="knrdjg" />
				</td>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>

		</html:form>
</body>
</html:html>
