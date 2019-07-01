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
			document.forms[0].action = "/xgxt/n05_xszz.do?method=knsrd3sq";
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
								<bean:write name='rs' property="xxmc" />ѧ����ͥ������������
							</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="6">
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
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="2">
								<logic:empty name="rs" property="nj">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="nj">
									<bean:write name='rs' property="nj" />
								</logic:notEmpty>
								��
								<logic:empty name="rs" property="bjmc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="bjmc">
									<bean:write name='rs' property="bjmc" />
								</logic:notEmpty>
								��
							</td>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
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
							<td>
								<div align="center">
									��ѧǰ����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="rxqhk" value="����">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="rxqhk" value="����">
										��
									</logic:notEqual>
									����&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="rxqhk" value="ũ��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="rxqhk" value="ũ��">
										��
									</logic:notEqual>
									ũ��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˿���
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ҵѧУ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="byxx" />
								</div>
							</td>
							<td>
								<div align="center">
									�����س�
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="grtc" />
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
									<logic:equal name='rs' property="sfgc" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfgc" value="��">
										��
									</logic:notEqual>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sfgc" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfgc" value="��">
										��
									</logic:notEqual>
									��
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="sfdq" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfdq" value="��">
										��
									</logic:notEqual>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sfdq" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfdq" value="��">
										��
									</logic:notEqual>
									��
								</div>
							</td>
							<td>
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name='rs' property="sflszn" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sflszn" value="��">
										��
									</logic:notEqual>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sflszn" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sflszn" value="��">
										��
									</logic:notEqual>
									��
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ϣ
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ��ַ
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
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
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ����У
									<br />
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xszxlxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
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
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									�뱾�˹�ϵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(ѧϰ)���
								</div>
							</td>
							<td>
								<div align="center">
									������(Ԫ)
								</div>
							</td>
							<td>
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zk" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zk" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zk" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zk" />
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									Ӱ
									<br />
									��
									<br />
									��
									<br />
									ͥ
									<br />
									��
									<br />
									��
									<br />
									״
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									Ϣ
								</div>
							</td>
							<td colspan="8">
								<p>
									��ͥ�˾�������
									<u>&nbsp;
									<logic:empty name="rs" property="jtrjnsr">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjnsr">
										<bean:write name='rs' property="jtrjnsr" />
									</logic:notEmpty>
									&nbsp;</u>��Ԫ������ͥ�˾�������
									<u>&nbsp;
									<logic:empty name="rs" property="jtrjysr">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjysr">
										<bean:write name='rs' property="jtrjysr" />
									</logic:notEmpty>
									&nbsp;</u>��Ԫ����
								</p>
								<p>
									ѧ����ѧ���ѻ����������
									<u>&nbsp;
									<logic:empty name="rs" property="bxnyhzzqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bxnyhzzqk">
										<bean:write name='rs' property="bxnyhzzqk" />
									</logic:notEmpty>
									&nbsp;</u>��
								</p>
								<p>
									��ѧ��ɷ��������
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
									<u>&nbsp;
									<logic:empty name="rs" property="yjxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="yjxf">
										<bean:write name='rs' property="yjxf" />
									</logic:notEmpty>
									&nbsp;</u>Ԫ����
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
									<u>&nbsp;
									<logic:empty name="rs" property="yjzsf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="yjzsf">
										<bean:write name='rs' property="yjzsf" />
									</logic:notEmpty>
									&nbsp;</u>Ԫ��
								</p>
								<p>
									��ͥ�ṩ�����
									<u>&nbsp;
									<logic:empty name="rs" property="jttgshf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jttgshf">
										<bean:write name='rs' property="jttgshf" />
									</logic:notEmpty>
									&nbsp;</u>Ԫ /
									�£���У������
									<u>&nbsp;
									<logic:empty name="rs" property="zxxxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zxxxf">
										<bean:write name='rs' property="zxxxf" />
									</logic:notEmpty>
									&nbsp;</u>Ԫ / �¡�
								</p>
								<p>
									��ͥ������Ȼ�ֺ������
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcszrzhqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcszrzhqk">
										<bean:write name='rs' property="jtcszrzhqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>��
								</p>
								<p>
									��ͥ����ͻ�������¼���
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcstfywqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcstfywqk">
										<bean:write name='rs' property="jtcstfywqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>��
								</p>
								<p>
									��ͥ��Ա��м����������Ͷ������������
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcyycjnmrndnlrqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcyycjnmrndnlrqk">
										<bean:write name='rs' property="jtcyycjnmrndnlrqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>��
								</p>
								<p>
									��ͥ��Աʧҵ�����
									<u>&nbsp;
									<logic:empty name="rs" property="jtcysyqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcysyqk">
										<bean:write name='rs' property="jtcysyqk" />
									</logic:notEmpty>
									&nbsp;</u>����ͥǷծ�����
									<u>&nbsp;
									<logic:empty name="rs" property="jtqzqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtqzqk">
										<bean:write name='rs' property="jtqzqk" />
									</logic:notEmpty>
									&nbsp;</u>��
								</p>
								<p>
									<logic:equal name="xxdm" value="11078">
										�Ƿ�ͱ�����һ�������м���,���ݻ�������ʿ���������������˻򼲹ʾ��˼������������ͥ
									</logic:equal>
									<logic:notEqual name="xxdm" value="11078">
										�Ƿ�ͱ�����һ�������м���,��ʿ���������������˻򼲹ʾ��˼������������ͥ
									</logic:notEqual>
									<u>&nbsp;
									<logic:empty name="rs" property="sfdbh">
										&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="sfdbh">
										<bean:write name='rs' property="sfdbh" />
									</logic:notEmpty>
									&nbsp;</u> ��
								</p>
								<p>
									���������
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="qtqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="qtqk">
										<bean:write name='rs' property="qtqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>��
								</p>
								<div align="center">
									���˳�ŵ���������ʵ��������٣�����Ը��е�һ�к����
								</div>
								<br />
								<div align="center">
									ѧ���ҳ���໤��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ��ǩ����
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="4">
								<div align="center">
									ѧ����ͥ
									<br />
									���ڵ���
									<br />
									���ֵ�
									<br />
									��������
									<br />
									��Ϣ
								</div>
							</td>
							<td>
								<div align="center">
									ͨѶ��ַ
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									&nbsp;
								</div>
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
									&nbsp;
								</div>
							</td>
							<td colspan="3" rowspan="3">
								<div align="left">
									<strong>������������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ/�¡�</strong>
								</div>
								<br />
								��λ���ƣ�
								<div align="center">
									(����)
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����������
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�����˰칫
									<br />
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>(����)
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
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
		<!--  <input  value="����" onclick="back();" />-->
	</div>
</body>
</html:html>
