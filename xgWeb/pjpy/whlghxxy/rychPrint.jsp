<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/data_search" method="post">
		<div align="center" style="font-size:18px;font:'����' "><b>�人������<bean:message key="lable.xsgzyxpzxy" /> ${rs.xn } ��� ${rs.rychmc } �����ƺ������</b></div>
			<table width="99%" id="rsT" class="printstyle">
				<tr style="height:22px">
					<th width="15%">
						<div align="center">
							ѧ��
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xh" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							����
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xm" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							�Ա�
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xb" />
						</div>
					</th>
					<th width="20%" rowspan="5">
						<div align="center">
							����Ƭ��
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							����
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="mzmc" />
						</div>
					</th>
					<th>
						<div align="center">
							������ò
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="zzmmmc" />
						</div>
					</th>
					<th>
						<div align="center">
							����ˮƽ
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="wydj" />
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							��������
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="csrq" />
						</div>
					</th>
					<th>
						<div align="center">
							��ѧʱ��
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="rxrq" />
						</div>
					</th>
					<th>
						<div align="center">
							��ϵ�绰
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="lxdh" />
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							ϵ��רҵ���༶
						</div>
					</th>
					<th colspan="5">
						<bean:write name='rs' property="xymc" />
						<bean:write name='rs' property="zymc" />
						<bean:write name='rs' property="bjmc" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							���������ƺ�����
						</div>
					</th>
					<th colspan="5">
						<bean:write name='rs' property="rychmc" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							��
							<br />
							Ҫ
							<br />
							��
							<br />
							��
						</div>
					</th>
					<th colspan="6">
						<bean:write name='rs' property="zysj" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							����
							<br />
							���
							<br />
							����
							<br />
							���
						</div>
					</th>
					<th colspan="6">
						<bean:write name='rs' property="drshgzqk" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							��
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</th>
					<th colspan="6">
										<p>&nbsp;</p>
															<p>&nbsp;</p>
						<bean:write name='rs' property="fdyyj" />
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							ϵ
							<br />
							��
							<br />
							��
							<br />
							��
							<br />
							��
						</div>
					</th>
					<th colspan="6">
										<p>&nbsp;</p>
															<p>&nbsp;</p>
						<bean:write name='rs' property="xyyj" />
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							ѧ
							<br />
							Ժ
							<br />
							��
							<br />
							��
						</div>
						
					</th>
					<th colspan="6">
					<p>&nbsp;</p>
										<p>&nbsp;</p>
						<bean:write name='rs' property="xxyj" />
						<p align="right">ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;</p>
					</th>
				</tr>
			</table>
			<div align="center" class='noPrin'>
				<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					ҳ������
				</button>
				<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					��ӡԤ��
				</button>
				<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					ֱ�Ӵ�ӡ
				</button>
			</div>
		</html:form>
	</body>
</html>
