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
			refreshForm('/xgxt/auditing_shnl_kns_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��ͥ��������ѧ���϶���� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
						<td align="center" colspan="2">
							ѧ��
						</td>
						<td align="left" colspan="3">
							<bean:write name="xh" />
						</td>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td  colspan="3">
						<bean:write name="xb"/>
					</td>
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="sfzh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="mzmc"/>
					</td>
					<td colspan="2">
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ϵ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="xymc"/>
					</td>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<bean:write name="zymc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="3">
						<bean:write name="bjmc"/>
					</td>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="nj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="lxdh"/>
					</td>
					<td colspan="2">
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td align="center">
						<bean:write name="rxqhk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="6">
						<bean:write name="jtzz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="yzbm"/>
					</td>
					<td colspan="2">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="jtlxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�Ը��μ�<br />���ƻ�־Ը�
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="sfyycjcshzyhd"/>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�Ը���������<br />��ѧ������ڹ���ѧ
						</div>
					</td>
					<td align="center">
						<bean:write name="sfyysqgjzxdkhqgzx"/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="center">
							��ͥ����
						</div>
						<div align="left">
						<font color="#ff0000">
						ע��1.����ָһ��ȥ����2.�����ͥע���Է����������3.�¶�д���໤�˵���������������������
						<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.���������Ÿ���ͥ���ṩ��Ӧ֤����5.�м����ز���ͥ���ṩ�ؼ�����ҽԺ֤��
						</font>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ�ȫ
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="sfjq"/>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�¶�
						</div>
					</td>
					<td align="center">
						<bean:write name="sfge"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ���
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="sfdq"/>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�м�
						</div>
					</td>
					<td align="center">
						<bean:write name="sfcj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ������
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="sfjls"/>
					</td>
					<td colspan="2">
						<div align="center">
							�Ƿ�����
						</div>
					</td>
					<td align="center">
						<bean:write name="sfly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ƿ��ز�
						</div>
					</td>
					<td align="center" colspan="3">
						<bean:write name="sfzb"/>
					</td>
					<td colspan="2">
						<div align="center">
							��ͥ�˾�������(Ԫ)
						</div>
					</td>
					<td>
						<bean:write name="jtrjnsr"/>
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
						��ѧ��<br />��ϵ
					</td>
					<td width="12%" align="center">
						ְҵ
					</td>
					<td width="8%" align="center">
						������<br />(Ԫ)
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
						<bean:write name="jtcy1_xm"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_nl"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_gx"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_zy"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_nsr"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_jkzk"/>
					</td>
					<td align="center">
						<bean:write name="jtcy1_gzdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="jtcy2_xm"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_nl"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_gx"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_zy"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_nsr"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_jkzk"/>
					</td>
					<td align="center">
						<bean:write name="jtcy2_gzdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="jtcy3_xm"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_nl"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_gx"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_zy"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_nsr"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_jkzk"/>
					</td>
					<td align="center">
						<bean:write name="jtcy3_gzdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="jtcy4_xm"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_nl"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_gx"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_zy"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_nsr"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_jkzk"/>
					</td>
					<td align="center">
						<bean:write name="jtcy4_gzdw"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:write name="jtcy5_xm"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_nl"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_gx"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_zy"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_nsr"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_jkzk"/>
					</td>
					<td align="center">
						<bean:write name="jtcy5_gzdw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ���ڱ����������
						</div>
					</td>
					<td colspan="6">
						<bean:write name="xszbdszqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ������Ȼ�ֺ����
						</div>
					</td>
					<td colspan="6">
						<bean:write name="jtzszrzhqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����ͻ�������¼�
						</div>
					</td>
					<td colspan="6">
						<bean:write name="jtzstfywsj"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="6">
						<bean:write name="qtqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����������ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6">
						<bean:write name="mzbm_txdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����������������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="mzbm_yzbm"/>
					</td>
					<td colspan="2">
						<div align="center">
							����������ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="mzbm_lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="sqsj"/>
					</td>
					<td colspan="2">
						<div align="center">
							��˽��
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
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
