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
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��ͥ������� - ��ϸ��Ϣ
				</div>
			</div>
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="7" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xz" />
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧǰ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxqhk" />
					</td>
					<td>
						<div align="center">
							��ͥ�˿���
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ҵѧУ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="byxx" />
					</td>
					<td>
						<div align="center">
							�����س�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="grtc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�²�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfgc" />
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfdq" />
					</td>
					<td>
						<div align="center">
							��ʿ��Ů
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtdz" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ����У��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xszxlxdh" />
					</td>
					<td>
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
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
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td>
						<div align="center">
							������ѧϰ����λ
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�����루Ԫ��
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����״��
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy1_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy2_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy3_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy4_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy5_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_zk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrjysr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ����ѧ���ѻ��������
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="bxnyhzzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ��ɷ����
						</div>
					</td>
					<td colspan="5">
						��
						<logic:equal name='rs' property="sfjxf" value="��">
							��
						</logic:equal>
						<logic:notEqual name='rs' property="sfjxf" value="��">
							��
						</logic:notEqual>
						δ��ѧ��&nbsp;&nbsp;
						<logic:equal name='rs' property="sfjxf" value="��">
							��
						</logic:equal>
						<logic:notEqual name='rs' property="sfjxf" value="��">
							��
						</logic:notEqual>
						�ѽ�ѧ��
						<u>&nbsp; <logic:empty name="rs" property="yjxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty> <logic:notEmpty name="rs" property="yjxf">
								<bean:write name='rs' property="yjxf" />
							</logic:notEmpty> &nbsp;</u>Ԫ����
						<logic:equal name='rs' property="sfjzsf" value="��">
							��
						</logic:equal>
						<logic:notEqual name='rs' property="sfjzsf" value="��">
							��
						</logic:notEqual>
						δ��ס�޷�&nbsp;&nbsp;
						<logic:equal name='rs' property="sfjzsf" value="��">
							��
						</logic:equal>
						<logic:notEqual name='rs' property="sfjzsf" value="��">
							��
						</logic:notEqual>
						�ѽ�ס�޷�
						<u>&nbsp; <logic:empty name="rs" property="yjzsf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty> <logic:notEmpty name="rs" property="yjzsf">
								<bean:write name='rs' property="yjzsf" />
							</logic:notEmpty> &nbsp;</u>Ԫ��
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�ṩ�����(Ԫ/��)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jttgshf"/>
					</td>
					<td>
						<div align="center">
							��У������(Ԫ/��)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zxxxf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ������Ȼ�ֺ����
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcszrzhqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����ͻ�������¼�
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcstfywqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��Ա��м����������Ͷ����������
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcyycjnmrndnlrqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��Աʧҵ���
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcysyqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥǷծ���
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtqzqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="center">
							<logic:equal name="xxdm" value="11078">
								�Ƿ�ͱ�����һ�������м���,���ݻ�������ʿ���������������˻򼲹ʾ��˼������������ͥ
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								�Ƿ�ͱ�����һ�������м���,��ʿ���������������˻򼲹ʾ��˼������������ͥ
							</logic:notEqual>
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfdbh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
<!-- 				<tr>
					<td colspan="2">
						<div align="center">
							����������ϸͨѶ��ַ
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="mzbm_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzbm_yzbm"/>
					</td>
					<td>
						<div align="center">
							������������(Ԫ/��)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="ddzdshbz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jbrxm"/>
					</td>
					<td>
						<div align="center">
							�����˰칫��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jbrbgdh_qx"/>
						-
						<bean:write name="rs" property="jbrbgdh_dh"/>
					</td>
				</tr>-->
				<tr>
					<td colspan="2">
						<div align="center">
							��д����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="txsj"/>
					</td>
					<td>
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
