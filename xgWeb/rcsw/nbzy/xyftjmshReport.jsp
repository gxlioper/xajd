<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzympgc" method="post">
					<div align="center" style="font-size:18px"><strong>����ְԺ��Լ��У�ѷ�̸��Ŀ�ǼǱ�</strong></div>
					</br>
					</br>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right" width="20%">
								��̸��Ŀ��
							</td>
							<td align="left" colspan="3">
								${rs.fttm }
							</td>
						</tr>
						<tr>
							<td align="right">
								��̸ʱ�䣺
							</td>
							<td align="left">
								${rs.ftsj }
							</td>
							<td align="right">
								��̸�ص㣺
							</td>
							<td align="left">
								${rs.ftdd }
							</td>
						</tr>
						<tr>
							<td align="right">
								�����ˣ�
							</td>
							<td align="left">
								${rs.zcr }
							</td>
							<td align="right">
								��Ŀ������:
							</td>
							<td align="left">
								${rs.jmfzr }
							</td>
						</tr>
						<tr>
							<td align="right" rowspan="4">
								�ܷüα�����&nbsp;&nbsp;&nbsp;</br>�����飺
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
								ְ��
							</td>
							<td align="center">
								�绰
							</td>
						</tr>
						<tr>
							<td align="right">
								${rs.sfjbf }
							</td>
							<td align="left">
								${rs.sfjbfzw }
							</td>
							<td align="right">
								${rs.sfjbfdh }
							</td>
						</tr>
						<tr>
							<td align="right">
								${rs.sfjbs }
							</td>
							<td align="left">
								${rs.sfjbszw }
							</td>
							<td align="right">
								${rs.sfjbsdh }
							</td>
						</tr>
						<tr>
							<td align="right">
								${rs.sfjbt }
							</td>
							<td align="left">
								${rs.sfjbtzw }
							</td>
							<td align="right">
								${rs.sfjbtdh }
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��̸�������ݣ�
							</td>
							<td colspan="3" height="180px">
								${rs.ftjbnr }
							</td>
						</tr>
						<tr align="left">
							<td align="right" height="160px">
								���첿�������
							</td>
							<td colspan="3">
								${rs.zbbmyj }
								
								<p align="right" >������ǩ�֣����£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
								<p align="right">�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
							</td>
						</tr>
						<tr align="left">
							<td align="right" height="160px">
								ѧ���������
							</td>
							<td colspan="3">
								${rs.xgbyj }
								
								<p align="right">������ǩ�֣����£���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
								<p align="right">�� &nbsp;&nbsp;  ��  &nbsp;&nbsp;  ��&nbsp;&nbsp;</p>
							</td>
						</tr>
						<tr align="left">
							<td align="right" height="160px">
								��ע��
							</td>
							<td colspan="3">
								${rs.bz }
							</td>
						</tr>
					</table>
						</br>
						<div align="right">����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧ������</div>
						<div align="right">${dysj }</div>
		</html:form>
	</body>
</html>
