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
		<div align="center" style="font-size:28px;font:'����' "><b>����ְҵ����<bean:message key="lable.xsgzyxpzxy" />�༶��ɫ��Ŀ�����������������</b></div>
		<br>
		<div align="left" style="font-size:10px;">
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="20%">
					<div align="center">
						��Ŀ����
					</div>
				</th>
				<th colspan="3">
					<div align="center">
						<bean:write name='rs' property="xmmc" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center" >
						����༶
					</div>
				</th>
				<th width="30%">
					<div align="center">
						<bean:write name='rs' property="bjmc" />
					</div>
				</th>
				<th width="20%">
					<div align="center">
						������Ժ��ϵ��
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="xymc" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center" >
						��Ŀ������
					</div>
				</th>
				<th width="30%">
					<div align="center">
						<bean:write name='rs' property="xmfzr" />
					</div>
				</th>
				<th width="20%">
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
					<div align="center" >
						��������<br>��������500�֣�
					</div>
				</th>
				<th colspan="3" height="300">
					<div align="center">
						<bean:write name='rs' property="sqly" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center" >
						ԭ����ʱ��
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="yyssj" />
					</div>
				</th>
				<th>
					<div align="center">
						��������ʱ��
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="yqyssj" />
					</div>
				</th>
			</tr>
						<tr>
				<th style="height: 60px;">
					<div align="center">
						������<br>���
					</div>
				</th>
				<th colspan="3">
					<div align="left">
						<bean:write name='rs' property="bzryj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name='rs' property="bzrxm" />
					</div>
					<div align="right">
						<bean:write name='rs' property="bzrshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						������<br>Ժ��ϵ��<br>���
					</div>
				</th>
				<th colspan="3">
					<div align="left">
						<bean:write name='rs' property="xyyj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</div>
					<div align="right">
						<bean:write name='rs' property="xyshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						ѧ����<br>���
					</div>
				</th>
				<th colspan="3">
					<div align="left">
						<bean:write name='rs' property="xxyj" />
					</div>
					<br>
					<div align="right">
						�����ˣ�	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�
					</div>
					<div align="right">
						<bean:write name='rs' property="xxshsj" />
					</div>
				</th>
			</tr>
		</table>	
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					ҳ������
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					��ӡԤ��
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					ֱ�Ӵ�ӡ
				</button>
			</div>
		</html:form>
	</body>
</html>
