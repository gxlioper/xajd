<%@ page language="java" contentType="text/html;charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
	<html:form action="lyjszxjsqb.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<p align="center" style="font-size:24px">
						<bean:write name='rs' property="nd" />
						��ȹ㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />
						<bean:write name='rs' property="jlmc" />
						�����
					</p>
					<div align="right">
						����ʱ��:
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="16%">
								ѧ��
							</td>
							<td align="left" width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<td width="16%" scope="col">
								<div align="center">
									����
								</div>
							</td>
							<td width="34%" scope="col">
								<bean:write name='rs' property="xm" />
							</td>
						</tr>
						<tr>
							<td width="16%" scope="row">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="34%">
								<bean:write name='rs' property="xb" />
							</td>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<bean:write name='rs' property="nj" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									���֤��
								</div>
							</td>
							<td>
								<bean:write name='rs' property="sfzh" />
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xymc" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									רҵ
								</div>
							</td>
							<td>
								<bean:write name='rs' property="zymc" />
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<bean:write name='rs' property="bjmc" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��Դ��
								</div>
							</td>
							<td>
								<bean:write name='rs' property="syd" />
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="hkxz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ��ס��ַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtjzdz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="yzbm" />
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<bean:write name='rs' property="lxdh" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ�˿���
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtrks" />
							</td>
							<td>
								<div align="center">
									��ͥ�˾�������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtrjysr" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jtnzsr" />
							</td>
							<td>
								<div align="center">
									ѧ��������ʵ�����Ѷ��
								</div>
							</td>
							<td>
								<bean:write name='rs' property="xsbrysjxfed" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ������Դ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="srly" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ���
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtqk" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ͥ�������˵��
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="jtjjqksm" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ѧ�ڳɼ�����
								</div>
							</td>
							<td>
								<bean:write name="rs" property="sxqcjpm" />
							</td>
							<td>
								<div align="center">
									�ۺϲ�������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="zhcppm" />
							</td>
						</tr>
						<tr>
							<td scope="row" colspan="4">
								��У�ڼ������ý�����ѧ�����Ѳ����ʹ����¼��
								<div align="left">
									<%
												ArrayList list = (ArrayList) request
												.getAttribute("zzjl");
												for (Iterator it = list.iterator(); it.hasNext();) {
											String temp = (String)it.next();
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<%=temp%>
									<br />
									<%
									}
									%>
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									�Ƿ��Ѳμ��ڹ���ѧ
								</div>
							</td>
							<td>
								<bean:write name="rs" property="sfycjqgzx" />
							</td>
							<td>
								<div align="center">
									Ƿѧ�ѽ��
								</div>
							</td>
							<td>
								<bean:write name='rs' property="qxfje" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									���뽱ѧ��ȼ�
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jxjdj" />
							</td>
							<td>
								<div align="center">
									������
								</div>
							</td>
							<td>
								<bean:write name='rs' property="jlgrje" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xyshyj" />
								<br />
								<div align="right">
									ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xxshyj" />
								<br />
								<div align="right">
									ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
