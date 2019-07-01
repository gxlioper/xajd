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
	<body>
		<script language="javascript" src="js/function.js"></script>						
	<html:form action="/wmqspb_result" method="post">		
			<logic:empty name="rs">
				<div align="center"  style="font:17px;">
				ѧ����Ԣ�����������Ƚ����<bean:write name="commanForm" property="nd" />��<bean:write name="commanForm" property="yf" />�·ݣ�
			    </div>
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<div id="tabTit">
				<div align="center"  style="font:30px;"> ���ٰ� </div>								
				<div align="center">Ϊ�������ʵ��������Ӫ��������ѧϰ�������ٽ�ѧ���¡��ǡ��塢��ȫ�淢չ��
				��Ժѧ����Ԣ��չ��������������ҵ����ȡ��ֽ��ٻ�<bean:write name="commanForm" property="nd" />��<bean:write name="commanForm" property="yf" />�·����������ѧ����Ԣ���������������£�
				</div>
			</div>
			<logic:notEmpty name="rs">
				<fieldset>
					<div id="rsTable">
						<logic:iterate name="rs" id="s">
							<logic:iterate id="v" name="s" property="lbList">
								<br />
								<table width="99%" class="tbstyle" border="0">
									<tr>
										<td colspan="5" align="center">
											<bean:write name="v" property="lbmc" />
										</td>
									</tr>
									<logic:iterate id="b" name="s" property="qsList">
										<tr>
											<td width="15%">
												<bean:write name="b" property="ldmc" />
											</td>
											<td align="left">
												<bean:write name="b" property="qs" />
											</td>
										</tr>
									</logic:iterate>
									<tr>
										<logic:iterate id="vn" name="s" property="hjList">
											<td colspan="5" align="right">
												���ƣ�
												<bean:write name="vn" property="cout" />
												��
												<bean:write name="vn" property="qsjje" />
												=
												<bean:write name="vn" property="hj" />
												Ԫ
											</td>
										</logic:iterate>
									</tr>
								</table>
							</logic:iterate>
						</logic:iterate>
						<br>
						<div align="right">
							��Ԣ�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br>
							<bean:write name="sysData" scope="request" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br>
							<br>
						</div>						
							<div align="center" style="font:17px;">
								��Ԣ
								<bean:write name="qslbSize" scope="request" />
								����������������Ƚ��𷢷��嵥
							</div>
							<br />
						<table width="99%" class="tbstyle" border="0">
							<tr>
								<td rowspan="2" align="center">
									¥������
								</td>
								<td colspan="<bean:write name="qslbSize" scope="request" />"
									align="center">
									�� �Ԫ��
								</td>
								<td rowspan="2" align="center">
									�ϼƣ�Ԫ��
								</td>
								<td rowspan="2" align="center">
									ǩ ��
								</td>
							</tr>

							<tr>
								<logic:iterate name="rs3" id="u">
									<logic:iterate id="l" name="u" property="qslbList">
										<td align="center">
											&nbsp;
											<bean:write name="l" property="lbmc" />
										</td>
									</logic:iterate>
								</logic:iterate>
							</tr>

							<logic:iterate name="rs2" id="w">
								<logic:iterate id="n" name="w" property="ssbhList">
									<tr>
										<td>
											<bean:write name="n" property="ldmc" />
											&nbsp;&nbsp;
											<bean:write name="n" property="qsh" />
										</td>
										<logic:iterate name="rs3" id="u">
											<logic:iterate id="hj" name="w" property="lbcjList">
												<td>
													<bean:write name="hj" property="qsjje" />
												</td>

											</logic:iterate>
										</logic:iterate>
										<td>
											<bean:write name="n" property="jjhj" />
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</logic:iterate>
							</logic:iterate>
							<tr>
								<td align="right">
									�ϼƣ�
								</td>
								<logic:iterate name="rs3" id="g">
									<logic:iterate id="dxhj" name="g" property="dxjjhjList">
										<td>
											<bean:write name="dxhj" property="dxjjhj" />
										</td>
									</logic:iterate>
								</logic:iterate>
								<td>
                                 <bean:write name="allHj" scope="request" />
								</td>
								<td>
								
								</td>
							</tr>
							<tr>
								<td align="right">
									�ϼ�(��д)��
								</td>
								<td colspan="<bean:write name="rowSize" scope="request" />">
									<bean:write name="jjzhjdx" scope="request" />
								</td>
							</tr>
						</table>
						<br>
						<br>
						<div align="right">
							��Ԣ�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br>
							<bean:write name="sysData" scope="request" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>						
					</div>
				</fieldset>
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
			</logic:notEmpty>
		</html:form>				
</body>
</html>		

		
