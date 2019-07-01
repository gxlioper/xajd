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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();changezje()">
		<html:form action="/qgzxLogic" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> �ڹ���ѧ - ��𷢷� - ���ʲ��� </span>
				</div>
			</div>
			<input type="hidden" id="mes" name="mes" value="${mes}" />
			<input type="hidden" id="count" name="count" value="${count}" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="xn" name="xn" value="${xn}" />	
			<input type="hidden" id="nd" name="nd" value="${nd}" />
			<input type="hidden" id="xq" name="xq" value="${xq}" />
			<input type="hidden" id="yf" name="yf" value="${yf}" />
			<input type="hidden" id="pk" name="pk" value="${pkValue}" />
			<logic:notEmpty name="workInfo">
			<input type="hidden" id="syjf" name="syjf" value="${workInfo.syjf}" />
				<fieldset>
					<legend>
						ѧ��:
						${xn}
						&nbsp;&nbsp;&nbsp; ���:
						${nd}
						&nbsp;&nbsp;&nbsp; ѧ��:
						${xq }
						&nbsp;&nbsp;&nbsp; �·�:
						${yf}
					</legend>
					<table width="100%" class="tbstyle" id="rsT">
						<tr>
							<td width="15%" height="27" align="center">
								���˵�λ
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="yrdwmc" />
							</td>
						</tr>
						<tr>
							<td width="15%" height="27" align="center">
								��������
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="xymc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								��������
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gznr" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								��λ����
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gwxzmc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								�Ƴ��׼
							</td>
							<td align="center">
								<bean:write name="workInfo" property="jybcbz" />
								<input type="hidden" id="jybcbz"
									value="<bean:write name="workInfo" property="jybcbz" />" />
								<bean:write name="workInfo" property="jcfs" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								ʣ�ྭ��
							</td>
							<td align="center">
								<bean:write name="workInfo" property="syjf" />
								����λ:Ԫ��
							</td>
						</tr>
						<tr>
							<td align="center">
								��
								<br />
								��
								<br />
								��
								<br />
								Ա
								<br />
								��
								<br />
								��
							</td>
							<td valign="top" align="center">
								<table width="100%" class="tbstyle">
									<thead>
										<tr>
											<td width="14%">
												<div align="center">
													ѧ��
												</div>
											</td>
											<td width="14%">
												<div align="center">
													����
												</div>
											</td>
											<td width="16%">
												<div align="center">
													�༶
												</div>
											</td>
											<td width="15%">
												<div align="center">
													����ʱ��(��λ:
													<bean:write name="workInfo" property="gzsjdw" />
													)
												</div>
											</td>
											<td width="8%">
												<div align="center">
													���
												</div>
											</td>
											<logic:present name="showjsxx">
												<td width="10%">
													<div align="center">
														ǩ��
													</div>
												</td>
											</logic:present>
											<td width="16%">
												<div align="center">
													��ע
												</div>
											</td>
											<logic:present name="showshgc">
												<td width="10%">
													<div align="center">
														�������
													</div>
												</td>
											</logic:present>
										</tr>
									</thead>
									<logic:empty name="ffList">
										<tr>
											<td colspan="6">
												�޲μӸø�λ��ѧ����¼!
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="ffList">
										<logic:iterate name="ffList" id="ffList" indexId="index">
											<tr>
												<td>
													<div align="center">
														<input type="text" name="xh${index}"
															value="<bean:write name="ffList" property="xh"/>"
															style="width:95%" readonly />
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="xm" />
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="bjmc" />
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="ffList" property="gzsj"/>"
															style="width:95%"
															onblur="changecjje(this);" />

													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="ffList" property="cjje"/>"
															style="width:95%"
															onblur="changezje();" />
													</div>
												</td>

												<td>
													<div align="center">
														<input type="text" name="bz${index}"
															value="<bean:write name="ffList" property="bz"/>"
															style="width:95%" />
													</div>
												</td>

											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr>
							<td height="27">
								<div align="center">
									����ܼƣ���λ:Ԫ��
								</div>
							</td>
							<td>
								<div align="center" id="zje"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">

					<button type="button" class="button2"
						onclick="if(parseFloat(document.forms[0].syjf.value)-parseFloat(document.getElementById('zje').innerText)<0){alert('���Ѳ��㣬����ʧ�ܣ�');Close();} else refreshForm('qgzxLogic.do?method=saveReWorkPay');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;

					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','��𷢷ŵ�','')">
						��ӡ����
					</button>
				</div>
			</logic:notEmpty>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('�����ɹ���');
				</script>
					<logic:notEmpty name="mes">
						<script>
						alert(document.getElementById('mes').value);
					</script>
					</logic:notEmpty>
					<script>
				</script>
				</logic:equal>

				<logic:equal value="false" name="result">
					<logic:notEmpty name="mes">
						<script>
							alert(document.getElementById('mes').value);
						</script>
					</logic:notEmpty>
					<logic:empty name="mes">
						<script>
							alert("����ʧ�ܣ�");
						</script>
					</logic:empty>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

