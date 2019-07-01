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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<logic:equal name="name" value="work_payput_sum">
				<fieldset>
					<legend align="center">
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						��
						<bean:write name="yf" />
						��ѧ���幤���귢�Ż��ܵ�
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						��
						<bean:write name="yf" />
						��ѧ���ڹ���ѧ���귢�Ż��ܵ�
					</logic:notEqual>						
					</legend>
					<table width="100%" border="0" align="center" id="rsT">
						<tr>
							<td>
								<center>
									<bean:write name="nd" />
									��
									<bean:write name="yf" />									
									�¸�<bean:message key="lable.xsgzyxpzxy" />�������
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="50%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="25%">
												���˹���
											</td>
											<td width="25%">
												���Ž��
											</td>
										</tr>
									</thead>
									<logic:empty name="rs1">
										<tr align="center">
											<td colspan="3">
												�޼�¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s1">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v1" name="s1" offset="0">
													<td nowrap>
														<bean:write name="v1" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								<center>
									<bean:write name="nd" />
									��
									<bean:write name="yf" />
									�¸����˵�λ���귢��ͳ��
								</center>								
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												���
											</td>
											<td width="40%">
												���˵�λ
											</td>
											<td width="25%">
												���˹���
											</td>
											<td width="25%">
												���Ž��
											</td>
										</tr>
									</thead>
									<logic:empty name="rs">
										<tr align="center">
											<td colspan="4">
												�޼�¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v" name="s" offset="0">
													<td nowrap>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								���˴�:&nbsp;
								<u><logic:notEmpty name="ygzrc">
										<bean:write name="ygzrc" />
									</logic:notEmpty>
								</u>&nbsp;�˴�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ܽ��:&nbsp;
								<u><logic:notEmpty name="ffzje">
										<bean:write name="ffzje" />
									</logic:notEmpty>
								</u>&nbsp;Ԫ��
								<br/>
								�쵼ǩ��(����):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������:
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:equal>
			
			<logic:equal name="name" value="work_payput_details">
				<fieldset>
				<%--��ɳ����--%>
				<logic:equal value="10827" name="xxdm">
					<legend align="center">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						��
						<bean:write name="yf" />
						��ѧ���幤���귢����ϸ��
					</legend>
				</logic:equal>
				<logic:notEqual value="10827" name="xxdm">
					<legend align="center">
						<bean:write name="xxmc" />
						<bean:write name="nd" />
						��
						<bean:write name="yf" />
						��ѧ���ڹ���ѧ���귢����ϸ��
					</legend>
				</logic:notEqual>
					<table width="100%" border="0" align="center" id="rsT">
						<tr>
							<td>
								<center>
									1.�̶���λ
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												ѧ��
											</td>
											<td width="10%">
												����
											</td>
											<td width="20%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="20%">
												��λ����
											</td>
											<td width="15%">
												���˵�λ
											</td>
											<td width="10%">
												���Ž��
											</td>
											<%--�人����ѧ--%>
											<logic:equal value="10497" name="xxdm">
											<td width="10%">
												���˵ȼ�
											</td>
											</logic:equal>
											<!--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<logic:equal value="11647" name="xxdm">
											<td nowrap="nowrap">
												���п���
											</td>
											<td nowrap="nowrap">
												��������
											</td>
											</logic:equal>
											<!--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<td width="15%">
												��ע
											</td>
										</tr>
									</thead>
									<logic:empty name="rs">
										<tr align="center">
											<td colspan="7">
												�޼�¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v" name="s" offset="0">
													<td nowrap>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								<br/>
								<center>
									2��У����ʱ
								</center>
								<table width="100%" class="tbstyle" align="center">
									<thead>
										<tr align="center">
											<td width="10%">
												ѧ��
											</td>
											<td width="10%">
												����
											</td>
											<td width="20%">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td width="20%">
												��λ����
											</td>
											<td width="15%">
												���˵�λ
											</td>
											<td width="10%">
												���Ž��
											</td>
											<!--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<logic:equal value="11647" name="xxdm">
											<td nowrap="nowrap">
												���п���
											</td>
											<td nowrap="nowrap">
												��������
											</td>
											</logic:equal>
											<!--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<td width="15%">
												��ע
											</td>
										</tr>
									</thead>
									<logic:empty name="rs1">
										<tr align="center">
											<td colspan="7">
												�޼�¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s1">
											<tr onclick="rowOnClick(this)" style="cursor:hand">
												<logic:iterate id="v1" name="s1" offset="0">
													<td nowrap>
														<bean:write name="v1" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:equal>
			<div class="buttontool" id="btn">
				<button type="button" class="button2"
					onclick="expTab('rsT','<bean:write name="xxmc" /><bean:write name="nd" />��<bean:write name="yf" />��ѧ���ڹ���ѧ���귢�Ż��ܵ�')">
					�� ӡ �� ��
				</button>
			</div>
		</html:form>
	</body>
</html>

