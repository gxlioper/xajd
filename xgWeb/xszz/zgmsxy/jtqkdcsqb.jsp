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
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=knsrdsq";
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
								�ߵ�ѧУѧ������ͥ��������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧУ��
						<u>&nbsp;�й�����<bean:message key="lable.xsgzyxpzxy" />&nbsp;</u> <bean:message key="lable.xsgzyxpzxy" />(�β�):
						<logic:empty name="rs" property="xymc">
						___________________
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<u> &nbsp;<bean:write name="rs" property="xymc" />&nbsp; </u>
						</logic:notEmpty>
						רҵ:
						<logic:empty name="rs" property="zymc">
						___________________
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<u> &nbsp;<bean:write name="rs" property="zymc" />&nbsp; </u>
						</logic:notEmpty>
						�꼶:
						<logic:empty name="rs" property="nj">
						_______
						</logic:empty>
						<logic:notEmpty name="rs" property="nj">
							<u> &nbsp;<bean:write name="rs" property="nj" />&nbsp; </u>
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" width="6%">
								<div align="center">
									ѧ
									<br />
									��
									<br />
									��
									<br />
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
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤
									<br />
									����
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
									<br />
									��ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧǰ
									<br />
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;����&nbsp;&nbsp;��&nbsp;ũ��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="rxqhk" value="����">
											��&nbsp;����&nbsp;&nbsp;��&nbsp;ũ��
										</logic:equal>
										<logic:equal name="rs" property="rxqhk" value="ũ��">
											��&nbsp;����&nbsp;&nbsp;��&nbsp;ũ��
										</logic:equal>
									</logic:notEmpty>
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
									<bean:write name="rs" property="jtrks" />
								</div>
							</td>
							<td>
								<div align="center">
									��ҵ
									<br />
									ѧУ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="byxx" />
								</div>
							</td>
							<td>
								<div align="center">
									����
									<br />
									�س�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="grtc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�²�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;��&nbsp;&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfgc" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sfgc" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
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
										��&nbsp;��&nbsp;&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfdq" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sfdq" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										��&nbsp;��&nbsp;&nbsp;��&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sflszn" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
										</logic:equal>
										<logic:equal name="rs" property="sflszn" value="��">
											��&nbsp;��&nbsp;&nbsp;��&nbsp;��
										</logic:equal>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									��ͥ
									<br />
									ͨѶ
									<br />
									��ַ
								</div>
							</td>
							<td>
								<div align="center">
									��ϸͨ
									<br />
									Ѷ��ַ
								</div>
							</td>
							<td colspan="8">
								<bean:write name="rs" property="jt_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jt_yzbm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jt_lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="7">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ա
									<br />
									��
									<br />
									��
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
							<td width="10%">
								<div align="center">
									��ѧ��
									<br />
									��ϵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(ѧϰ)��λ
								</div>
							</td>
							<td>
								<div align="center">
									ְҵ
								</div>
							</td>
							<td>
								<div align="center">
									������
									<br />
									(Ԫ)
								</div>
							</td>
							<td>
								<div align="center">
									����
									<br />
									״��
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
									&nbsp;
									<bean:write name="rs" property="jtcy1_nl" />
									&nbsp;
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
							<td>
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
									&nbsp;
									<bean:write name="rs" property="jtcy2_nl" />
									&nbsp;
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
							<td>
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
									&nbsp;
									<bean:write name="rs" property="jtcy3_nl" />
									&nbsp;
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
							<td>
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
									&nbsp;
									<bean:write name="rs" property="jtcy4_nl" />
									&nbsp;
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
							<td>
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
									&nbsp;
									<bean:write name="rs" property="jtcy5_nl" />
									&nbsp;
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
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_jkzk" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy6_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name="rs" property="jtcy6_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_zy" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy6_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
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
							<td colspan="9">
								��ͥȫ������
								<logic:empty name="rs" property="jtqnsr">
								_____________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqnsr">
								<u>&nbsp;<bean:write name="rs" property="jtqnsr" />&nbsp;</u>
								</logic:notEmpty>
								(Ԫ),ѧ����ѧ���ѻ��������
								<logic:empty name="rs" property="xybxnyhzzqk">
								______________________________
								<br />
								<br />
								_________________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="xybxnyhzzqk">
								<u>&nbsp;<bean:write name="rs" property="xybxnyhzzqk" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								��ͥ������Ȼ�ֺ������
								<logic:empty name="rs" property="jtzszrzhqk">
								________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzszrzhqk">
								<u>&nbsp;<bean:write name="rs" property="jtzszrzhqk" />&nbsp;</u>
								</logic:notEmpty>
								����ͥ����ͻ�������¼���
								<logic:empty name="rs" property="jtzstfywsj">
								____________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtzstfywsj">
								<u>&nbsp;<bean:write name="rs" property="jtzstfywsj" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								��ͥ��Ա��м����������Ͷ������������
								<logic:empty name="rs" property="jtcyycjssldl">
								___________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcyycjssldl">
								<u>&nbsp;<bean:write name="rs" property="jtcyycjssldl" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								��ͥ��Աʧҵ�����
								<logic:empty name="rs" property="jtcysyqk">
								________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtcysyqk">
								<u>&nbsp;<bean:write name="rs" property="jtcysyqk" />&nbsp;</u>
								</logic:notEmpty>
								����ͥǷծ�����
								<logic:empty name="rs" property="jtqzqk">
								_____________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqzqk">
								<u>&nbsp;<bean:write name="rs" property="jtqzqk" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								���������
								<logic:empty name="rs" property="qtqk">
								________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="qtqk">
								<u>&nbsp;<bean:write name="rs" property="qtqk" />&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ǩ
									<br />
									��
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��
									<br />
									����
								</div>
							</td>
							<td>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								&nbsp;
							</td>
							<td>
								<div align="center">
									ѧ���ҳ�
									<br />
									��໤��
								</div>
							</td>
							<td colspan="2">
								&nbsp;
							</td>
							<td>
								<div align="center">
									ѧ����ͥ���ڵ�
									<br />
									�����ֵ�����
									<br />
									����
								</div>
							</td>
							<td colspan="3">
								<br />
								<br />
								���صͱ���׼��&nbsp;
								<logic:empty name="rs" property="dddbbz">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="dddbbz">
								<bean:write name="rs" property="dddbbz" />
								</logic:notEmpty>
								&nbsp;Ԫ/��.��
								<br />
								������ǩ�֣�
								<br />
								��λ����:
								<div align="center">
									(�Ӹǹ���)
								</div>
								<div align="right">
									________��____��____��&nbsp;&nbsp;
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
