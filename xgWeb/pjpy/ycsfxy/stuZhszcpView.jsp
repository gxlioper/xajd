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
		<script type="text/javascript" src="js/BatAlert.js"></script>
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
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		</script>
		<html:form action="/pjpyycsfwh" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ��:�������� - ��Ϣά�� - �ۺ����ʲ�����ϸ��Ϣ
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
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									�ۺ����ʲ�����ϸ��Ϣ
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								ѧ�ţ�
							</td>
							<td align="left">
								${rs.xh }
							</td>
							
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								${rs.xn }
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								${rs.xm }
							</td>
							
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								${rs.xqmc }
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								${rs.xymc }
							</td>
							
							<td align="right">
								ƽʱ���˳ɼ���
							</td>
							<td align="left">
								${rs.pjkhcj }
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								${rs.zymc }
							</td>
							
							<td align="right">
								ѧҵ���˳ɼ���
							</td>
							<td align="left">
								${rs.xykhcj }
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								${rs.bjmc }
							</td>
							
							<td align="right">
								�׶ο��˳ɼ���
							</td>
							<td align="left">
								${rs.jdkhcj }
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								${rs.nj }
							</td>
							
							<td align="right">
								�ۺ����ʲ����ܷ֣�
							</td>
							<td align="left">
								${rs.zhszcpzf }
							</td>
						</tr>
						<tr>
							<td align="right">
								�ۺ����ʲ�����������
							</td>
							<td align="left">
								${rs.pm }
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<logic:notEmpty name="cjList">
						<tr>
							<td colspan="4" align="center"><b>�γ̳ɼ���Ϣ</b></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<table class="tbstyle" width="100%">
									<tr>
										<td align="center">�γ�����</td>
										<td align="center">�γ�����</td>
										<td align="center">�ɼ�</td>
									</tr>
										<logic:iterate id="s" name="cjList">
											<tr>
												<logic:iterate id="v" name="s">
													<td align="center">${v }</td>
												</logic:iterate>
											</tr>
										</logic:iterate>				
								</table>
							</td>
						</tr>
						</logic:notEmpty>
					</table>
				</logic:notEmpty>
				<div class="buttontool" align="center">
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
		</html:form>
	</body>
</html>
