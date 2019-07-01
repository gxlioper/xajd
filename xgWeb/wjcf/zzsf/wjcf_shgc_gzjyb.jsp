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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								���ٽ���
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xh" scope="request"/>
						</td>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="nd" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" scope="request"/>
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<bean:write name="rs" property="xn" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" scope="request"/>
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xq" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" scope="request"/>
						</td>
						<td align="right">
							�������
						</td>
						<td align="left">
							<bean:write name="rs" property="cflb" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" scope="request"/>
						</td>
						<td align="right">
							�������ɣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="cfyy" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" scope="request"/>
						</td>
						<td align="right">
							<font color="red">*</font>����ʱ�䣺
						</td>
						<td align="left">
							<bean:write name="rs" property="cfsj" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" scope="request"/>
						</td>
						<td align="right">
							<font color="red">*</font>�����ĺţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="cfwh" scope="request"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>�������ټ�¼</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none">
								<logic:equal value="0" name="isSHGC">
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��һ���Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' property='xsbx1' style="width:99%" rows='3' />
											</td>
										</tr>
								</table>
							</logic:equal>	
								<logic:present name="isSHGC">
								<logic:equal value="1" name="isSHGC">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��һ���Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx1' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�ڶ����Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' property='xsbx2' style="width:99%" rows='3' />
											</td>
										</tr>
								</table>
								</logic:equal>
								<logic:equal value="2" name="isSHGC">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��һ���Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx1' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�ڶ����Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx2' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�������Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' property='xsbx3' style="width:99%" rows='3' />
											</td>
										</tr>
								</table>
								</logic:equal>
								<logic:equal value="3" name="isSHGC">
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��һ���Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx1' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�ڶ����Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx2' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�������Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx3' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													���ļ��Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' property='xsbx4' style="width:99%" rows='3' />
											</td>
										</tr>
								</table>
								</logic:equal>
								<logic:equal value="4" name="isSHGC">
									<div align="center" class="style2">
													<font color="red">�����ܽ��������¼����һ�꣬�������������д��</font>
									</div>
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��һ���Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx1' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�ڶ����Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx2' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
									<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													�������Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx3' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													���ļ��Ƚ��������¼
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea name='rs' disabled="true" property='xsbx4' style="width:99%" rows='3' readonly="true"/>
											</td>
										</tr>
								</table>
								</logic:equal>
							</logic:present>
							</div>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xyyj' style="width:99%" rows='3' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="refreshForm('/xgxt/SaveStuTrackTeach.do');Close();window.dialogArguments.document.getElementById('search_go').click();"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
