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
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jtqkdcsq";
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
								�й����ʴ�ѧ���人��
								<bean:write name="rs" property="xn" />
								ѧ��������ͥ��������
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />(�β�):
						<logic:empty name="rs" property="xymc">
						____________________________
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<u> &nbsp;<bean:write name="rs" property="xymc" />&nbsp; </u>
						</logic:notEmpty>
						רҵ:
						<logic:empty name="rs" property="zymc">
						____________________________
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<u> &nbsp;<bean:write name="rs" property="zymc" />&nbsp; </u>
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
									ѧ��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="xh" />
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
									������&nbsp;&nbsp;��ũ��
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="rxqhk" value="����">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="rxqhk" value="����">
											��
										</logic:notEqual>
										����&nbsp;&nbsp;
										<logic:equal name="rs" property="rxqhk" value="ũ��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="rxqhk" value="ũ��">
											��
										</logic:notEqual>
										ũ��
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��Դ
								</div>
							</td>
							<td colspan="5">
								<logic:empty name="rs" property="sy">
									<div align="right">
										ʡ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��(��)
									</div>
								</logic:empty>
								<logic:notEmpty name="rs" property="sy">
									<div align="center">
										<bean:write name="rs" property="sy" />
									</div>
								</logic:notEmpty>
							</td>
							<td>
								<div align="center">
									��ͥ
									<br />
									�˿���
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="jtrks" />
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
									����&nbsp;&nbsp;����
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfgc" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sfgc" value="��">
											��
										</logic:notEqual>
										��&nbsp;&nbsp;
										<logic:equal name="rs" property="sfgc" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sfgc" value="��">
											��
										</logic:notEqual>
										��
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
									����&nbsp;&nbsp;����
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sfdq" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sfdq" value="��">
											��
										</logic:notEqual>
										��&nbsp;&nbsp;
										<logic:equal name="rs" property="sfdq" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sfdq" value="��">
											��
										</logic:notEqual>
										��
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
									����&nbsp;&nbsp;����
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="sflszn" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sflszn" value="��">
											��
										</logic:notEqual>
										��&nbsp;&nbsp;
										<logic:equal name="rs" property="sflszn" value="��">
											��
										</logic:equal>
										<logic:notEqual name="rs" property="sflszn" value="��">
											��
										</logic:notEqual>
										��
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
									<bean:write name="rs" property="jtcy1_nl" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy1_sr" />
									&nbsp;
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
									<bean:write name="rs" property="jtcy2_nl" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy2_sr" />
									&nbsp;
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
									<bean:write name="rs" property="jtcy3_nl" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy3_sr" />
									&nbsp;
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
									<bean:write name="rs" property="jtcy4_nl" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy4_sr" />
									&nbsp;
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
									<bean:write name="rs" property="jtcy5_nl" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy5_sr" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_jkzk" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								�������֤���룺
								<bean:write name="rs" property="fqsfzh" />
							</td>
							<td colspan="4">
								ĸ�����֤���룺
								<bean:write name="rs" property="mqsfzh" />
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
								__________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jtqnsr">
									<u> &nbsp;<bean:write name="rs" property="jtqnsr" />&nbsp;
									</u>
								</logic:notEmpty>
								(Ԫ),�˾�������
								<logic:empty name="rs" property="rjnsr">
								__________________
								</logic:empty>
								<logic:notEmpty name="rs" property="rjnsr">
									<u> &nbsp;<bean:write name="rs" property="rjnsr" />&nbsp;
									</u>
								</logic:notEmpty>
								(Ԫ)��
								<br />
								<br />
								��ͥƶ��ԭ��
								<logic:equal name="rs" property="sfncpkdq" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfncpkdq" value="��">
									��
								</logic:notEqual>
								ũ��ƶ������&nbsp;
								<logic:equal name="rs" property="sfczxgjt" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfczxgjt" value="��">
									��
								</logic:notEqual>
								�����¸ڼ�ͥ&nbsp;
								<logic:equal name="rs" property="sffmxcj" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sffmxcj" value="��">
									��
								</logic:notEqual>
								��ĸ�²м�&nbsp;
								<logic:equal name="rs" property="sfhzdjb" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfhzdjb" value="��">
									��
								</logic:notEqual>
								���ش󼲲�&nbsp;
								<logic:equal name="rs" property="sfdqjt" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfdqjt" value="��">
									��
								</logic:notEqual>
								���׼�ͥ
								<br />
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<logic:equal name="rs" property="sfgr" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfgr" value="��">
									��
								</logic:notEqual>
								�¶�&nbsp;
								<logic:equal name="rs" property="sfzrzh" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfzrzh" value="��">
									��
								</logic:notEqual>
								��Ȼ�ֺ�&nbsp;
								<logic:equal name="rs" property="sfjtrkd" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfjtrkd" value="��">
									��
								</logic:notEqual>
								��ͥ�˿ڶ�&nbsp;
								<logic:equal name="rs" property="sfqt" value="��">
									��
								</logic:equal>
								<logic:notEqual name="rs" property="sfqt" value="��">
									��
								</logic:notEqual>
								����
								<logic:empty name="rs" property="qtnr">
								____________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="qtnr">
								<u>
								&nbsp;<bean:write name="rs" property="qtnr" />&nbsp;
								</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								ƶ��ԭ����ϸ˵��:
								<logic:empty name="rs" property="pkyyxxsm">
								_________________________________________________________
								<br />
									<br />
								______________________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="pkyyxxsm">
								<u>
								&nbsp;<bean:write name="rs" property="pkyyxxsm" />&nbsp;
								</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								����Ƿծ���:
								<logic:empty name="rs" property="jzqzqk">
								___________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="jzqzqk">
								<u>
								&nbsp;<bean:write name="rs" property="jzqzqk" />&nbsp;
								</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								ѧ����ѧǰ�ѻ��������(���������)
								<logic:empty name="rs" property="xsrxqyhzzqk">
								___________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="xsrxqyhzzqk">
								<u>
								&nbsp;<bean:write name="rs" property="xsrxqyhzzqk" />&nbsp;
								</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								�������:
								<logic:empty name="rs" property="qtqk">
								______________________________________________________________
								</logic:empty>
								<logic:notEmpty name="rs" property="qtqk">
								<u>
								&nbsp;<bean:write name="rs" property="qtqk" />&nbsp;
								</u>
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
									ѧ����ͥ
									<br />
									���ڵ���
									<br />
									���ֵ�
									<br />
									��������
								</div>
							</td>
							<td colspan="3">
								<br />
								&nbsp;������ǩ��:
								<br />
								<br />
								<br />
								&nbsp;��λ����:
								<div align="center">
									(�Ӹǹ���)
								</div>
								<div align="right">
									________��____��____��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									����
									<br />
									����
									<br />
									��Ϣ
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
								<bean:write name="rs" property="mzbm_xxtxdz" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="mzbm_yzbm" />
								</div>
							</td>
							<td>
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
