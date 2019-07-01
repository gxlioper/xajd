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
		<title><bean:message key="lable.title" /></title>
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
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			 refreshForm('/xgxt/auditing_hzzy_jtqkdc_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ѧ������ͥ���������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" colspan="3">
						ѧ��
					</td>
					<td align="left" colspan="2">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="right">
							����
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="right">
							רҵ����
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�༶����
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td>
						<div align="right">
							�Ա�
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��������
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="csny" />
					</td>
					<td>
						<div align="right">
							���֤��
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��������
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="right">
							������ò
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ҵѧУ
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="byxx" />
					</td>
					<td>
						<div align="right">
							��ѧǰ����
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�����س�
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="grtc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�Ƿ�²�
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sfgc" />
					</td>
					<td>
						<div align="right">
							�Ƿ���
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="sfdq" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�Ƿ���ʿ��Ů
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sflszn" />
					</td>
					<td>
						<div align="right">
							��ͥ�˿���
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��������
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="yzbm" />
					</td>
					<td>
						<div align="right">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="xxtxdz" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="9%">
						<div align="center">
							��ѧ����ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������ѧϰ��λ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							ְҵ
						</div>
					</td>
					<td width="11%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
					<td width="11%">
						<div align="center">
							����״��
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
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
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
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
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
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
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
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
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
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
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
					<td>
						<div align="right">
							��ͥǷծ���
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="jtqzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							ѧ���ѻ��������
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="xsbxnyhzzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ������Ȼ�ֺ����
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ����ͻ�������¼�
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��Ա��м����������Ͷ��������
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtcyycjnmndlrqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��Աʧҵ���
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="jtcysyqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�������
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="qtqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							����������������
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<td>
						<div align="right">
							����������ϵ�绰
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							����������ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="6" align="left">
						<bean:write name="rs" property="mzbm_xxtxdz" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							����ʱ��
						</div>
					</td>
					<td colspan="2" align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="right">
							��˽��
						</div>
					</td>
					<td colspan="3">
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
