<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"/>
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
	<body onload="checkWinType()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ������� - ���߲���</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			
			<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�Ծ�����鿴</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										
										<button class="" onclick="Close();return false;" 
											id="buttonClose">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					<tbody>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>������Ŀ��
							</th>
							<td align="left">
								<html:text name='rs' property="xlcsxmmc" style="width: 260px"
									styleId="xlcsxmmc" readonly="true" />
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>�Ծ���
							</th>
							<td align="left" width="80%">
								<html:text name='rs' property="sjm" style="width: 260px"
									styleId="sjm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th align="right">
								ʱ���޶�
							</th>
							<td align="left">
								<html:text name='rs' property="sjxd" style="width: 100px"
									styleId="sjxd" readonly="true" onblur="numFormatChk(this)" />
								(����)
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ƿ���ʾ
							</th>
							<td align="left">
								<html:text name="rs" property="sjxsbj" readonly="true"></html:text>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								�Ծ�˵��
							</th>
							<td>
								<html:textarea name='rs' property='sjsm' style="width:340px"
									rows='6' readonly="true" />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
		</html:form>
	</body>
</html>
