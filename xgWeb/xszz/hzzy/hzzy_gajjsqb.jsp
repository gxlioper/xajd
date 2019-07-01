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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable" class="tableprint">
			<tr>
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								�ذ����������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%">
						<tr>
							<td scope="col" width="15%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="15%">
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
							<td scope="col" width="15%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td width="15%" rowspan="5" scope="col">
								<div align="center">
									��Ƭ
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�༶�ۺ�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjzhpm" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
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
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									��ͥ�˾�������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtrjnsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ��ϸͨѶ��ַ
								</div>
							</td>
							<td colspan="5">
								<bean:write name='rs' property="xxtxdz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
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
							<td colspan="3">
								<div align="center">
									<logic:empty name='rs' property="jtlxdh">
									(����) - 
									</logic:empty>
									<logic:notEmpty name='rs' property="jtlxdh">
									<bean:write name='rs' property="jtlxdh" />
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									�ѻ��������
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="yhzzqk" />
							</td>
						</tr>
						<tr>
							<td colspan="7" scope="row">
								�������ԭ��:
								<br />
								<logic:empty name='rs' property="sqjjyy">
								<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqjjyy">
								<bean:write name='rs' property="sqjjyy" />
								</logic:notEmpty>
								<br /><br /><br />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ϵ(Ժ)
									<br />
									����
									<br />
									����
									<br />
									����Ա
									<br />
									���
								</div>
							</td>
							<td colspan="3">
								<br />
								<br />
								<br /><br />
								<div align="center">
									ǩ��:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									ϵ(Ժ)
									<br />
									<br />
									�ܸ���
									<br />
									<br />
									Ա���
									<br />
									<br />
									���
								</div>
							</td>
							<td colspan="2">
								<br />
								<br /><br /><br />
								<div align="center">
									ǩ��:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ϵ(Ժ)
									<br />
									<br />
									����֧
									<br />
									<br />
									���
									<br />
									<br />
									���
								</div>
							</td>
							<td colspan="3">
								<br />
								<br /><br /><br />
								<div align="center">
									ǩ��:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									<br />
									���
								</div>
							</td>
							<td colspan="2">
								<br />
								<br /><br /><br />
								<div align="center">
									ǩ��:
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<div align="left">
						��ע:����֤�����븽��,�˱�һʽ����,һ�ݽ�ϵ(Ժ)����,һ�ݽ�ѧ����(ѧ����������)������
					</div>
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
