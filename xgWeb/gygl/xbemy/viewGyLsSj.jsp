<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ס����ʷ���� </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsgyglDispatch.do?method=viewGyLsSj" method="post">
		<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="5">
									<span>ס����Ϣά��</span>
								</th>
							</tr>
						</thead>
						<tbody>		
					<tr>
						<td align="left">
						 ѧ�ţ�<bean:write name="rsStu" property="xh" />&nbsp;&nbsp;&nbsp;&nbsp;
						 ������<bean:write name="rsStu" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="5">
									<span>��ס�����</span>
								</th>
							</tr>
						</thead>
						<tbody>		
					<tr>	
				<logic:empty name="zslsList">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="zslsList">
					<table border="0" width="100%" class="formlist">
						<thead>
							<tr>
								<td  align="center">
									��סʱ��
								</td>
								<td  align="center">
									�˷�ʱ��
								</td>
								<td  align="center">
									������
								</td>
								<td  align="center">
									¥������
								</td>
								<td  align="center">
									���Һ�
								</td>
								<td  align="center">
									��λ��
								</td>
								<td align="center">
									��סʱ�շѱ�׼
								</td></thead>
						<logic:iterate id="zsls" name="zslsList">
							<tr>
								<td>
									<bean:write name="zsls" property="rzrq" />
								</td>
								<td>
									<bean:write name="zsls" property="tfrq" />
								</td>
								<td>
									<bean:write name="zsls" property="ssbh" />
								</td>
								<td>
									<bean:write name="zsls" property="ldmc" />
								</td>
								<td>
									<bean:write name="zsls" property="qsh" />
								</td>
								<td>
									<bean:write name="zsls" property="cwh" />
								</td>
								<td>
									<bean:write name="zsls" property="sfbz" />
								</td>
							</tr>
						</logic:iterate>	
						<tfoot>
							<tr bgcolor="EEF4F9" align="center">
								<td colspan="7">
									<div class="btn">
										<button id="buttonClose" onclick="Close();return false;"
											style="width: 80px">
											��	��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>	
					</table>
				</logic:notEmpty>
<%--			<fieldset>--%>
<%--				<legend>--%>
<%--					�� ס �� �� ��--%>
<%--				</legend>--%>
<%--				<logic:empty name="xszsxxList">--%>
<%--					<p align="center">--%>
<%--						δ�ҵ��κμ�¼��--%>
<%--					</p>--%>
<%--				</logic:empty>--%>
<%--				<logic:notEmpty name="xszsxxList">--%>
<%--				<table border="0" width="100%" class="tbstyle">--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<td  align="center">--%>
<%--								��סʱ��--%>
<%--							</td>--%>
<%--							<td  align="center">--%>
<%--								������--%>
<%--							</td>--%>
<%--							<td  align="center">--%>
<%--								��λ��--%>
<%--							</td>--%>
<%----%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<logic:iterate id="zsls" name="xszsxxList">--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								<bean:write name="zsls" property="rzrq" />--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<bean:write name="zsls" property="ssbh" />--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<bean:write name="zsls" property="cwh" />--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</logic:iterate>--%>
<%--				</table>--%>
<%--				</logic:notEmpty>--%>
<%--			</fieldset>--%>
</tr>
</tbody>
</table>
		</html:form>
	</body>

</html>
