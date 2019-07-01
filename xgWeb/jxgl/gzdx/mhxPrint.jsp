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

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'����' "><b>���ݴ�ѧѧ���⻺ѵ�����</b></div>
		
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			<td>
			<table width="99%" class="printstyle">
				<tr style="height:50px">
					<th width="10%">
						<div align="center">
							ѧ��
						</div>
					</th>
					<th width="20%">
						<div align="center">
							${rs.xh }
						</div>
					</th>
					<th width="10%">
						<div align="center">
							����
						</div>
					</th>
					<th width="20%">
						<div align="center">
							${rs.xm }
						</div>
					</th>
					<th width="10%">
						<div align="center">
							�Ա�
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.xb }
						</div>
					</th>
				</tr>
				<tr style="height:50px">
					<th width="">
						<div align="center">
							�꼶
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.nj }
						</div>
					</th>
					<th width="">
						<div align="center">
							����
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.jg }
						</div>
					</th>
					<th width="">
						<div align="center">
							���֤��
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.sfzh }
						</div>
					</th>
				</tr>
				<tr style="height:50px">
					<th width="">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.xymc }
						</div>
					</th>
					<th width="">
						<div align="center">
							רҵ
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.zymc }
						</div>
					</th>
					<th width="">
						<div align="center">
							�༶
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.bjmc }
						</div>
					</th>
				</tr>
				<tr style="height:50px">
					<th width="">
						<div align="center">
							�������
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.lxmc }
						</div>
					</th>
					<th width="">
						<div align="center">
							����ʱ��
						</div>
					</th>
					<th width="">
						<div align="center">
							${rs.sqsj }
						</div>
					</th>
					<th width="">
						<div align="center">
							
						</div>
					</th>
					<th width="">
						<div align="center">
							
						</div>
					</th>
				</tr>
				<tr style="height:150px">
					<th width="">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</th>
					<th width="" colspan="5">
						<div align="center">
							${sqly }
						</div>
					</th>
				</tr>
				<tr style="height:100px">
					<th width="" rowspan="2">
						<div align="center">
							ѧ<br>Ժ<br>��<br>��
						</div>
					</th>
					<th width="" colspan="5" valign="top" style="border-bottom-style: none">	
						<div align="left">
							${rs.xyshyj }		
						</div>
					</th>
				</tr>
				<tr style="height:50px">
					<th width="" colspan="5" valign="bottom" style="border-top-style: none">	
						<div align="left">
							������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<bean:message key="lable.xsgzyxpzxy" />���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��
						</div>
					</th>
				</tr>		
				<tr style="height:150px">
					<th width="">
						<div align="center" style="top: 100px">
							��<br>װ<br>��<br>��<br>��
						</div>
					</th>
					<th width="" colspan="5" valign="bottom">
						<div align="left" >
							������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<bean:message key="lable.xsgzyxpzxy" />���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��
						</div>
					</th>
				</tr>
				<tr style="height:100px">
					<th width="" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��<br>��
						</div>
					</th>
					<th width="" colspan="5" valign="top" style="border-bottom-style: none">
						<div align="left">
							${rs.xxshyj }		
						</div>
					</th>
				</tr>
				<tr style="height:50px">
					<th width="" colspan="5" valign="bottom" style="border-top-style: none">	
						<div align="left">
							������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<bean:message key="lable.xsgzyxpzxy" />���£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��
						</div>
					</th>
				</tr>
			</table>
		<br>
		<div align="left" style="font-size:15px;">���˵����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1�����˻�������ɱ�����ʵ��д��</div>
		<br>
		<div align="left" style="font-size:15px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		2���˱�һʽ���ݣ�<bean:message key="lable.xsgzyxpzxy" />����װ�������񴦸���һ�ݡ�
		</div>
		
		
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
