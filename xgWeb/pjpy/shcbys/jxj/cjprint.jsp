<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		  <!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpyshcbyswh">
		<div align="center" style="font-size:18px;font:'����' ">
			&nbsp;
		</div>
		<h3 align="center">
							${title }�γ̳ɼ���
						</h3>
		<table width="98%" class="tbstyle" >
		
			<tr align="center">
				<th  scope="col">
					ѧ&nbsp;&nbsp;��
				</th>
				
				<th  scope="col">
					ѧ&nbsp;&nbsp;��
				</th>
				<th  scope="col">
					�γ�����
				</th>
				<th  scope="col">
					�γ�����
				</th>
				<th scope="col">
					��&nbsp;&nbsp;��
				</th>
				<th scope="col">
					�����ɼ�
				</th>
			</tr>
			<logic:notEmpty name="rss">
								<logic:iterate name="rss" id="s">
									<tr style="cursor:hand;" align="center"  onclick="rowOnClick(this)">
										<logic:iterate id="v" name="s" >
											<td scope="col" >
												<bean:write name="v"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="rss">
								<tr>
									<th scope="col" colspan="6">
										δ�ҵ��κμ�¼��
									</th>
								</tr>
							</logic:empty>
						</table>
<%--						<div align="center" class='noPrin'>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">--%>
<%--				ҳ������--%>
<%--			</button>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">--%>
<%--				��ӡԤ��--%>
<%--			</button>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">--%>
<%--				ֱ�Ӵ�ӡ--%>
<%--			</button>--%>
<%--		</div>--%>
    </html:form>
</body>
</html:html>
