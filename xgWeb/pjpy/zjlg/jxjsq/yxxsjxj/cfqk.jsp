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
		  <!-- 打印控件begin -->
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
		<div align="center" style="font-size:18px;font:'黑体' ">
			&nbsp;
		</div>
		<h3 align="center">
							${title }处罚情况
						</h3>
		<table width="98%" class="tbstyle" >
		
			<tr align="center">
<%--				<th  scope="col" style="width: 100px">--%>
<%--					处罚时间--%>
<%--				</th>--%>
				<th  scope="col">
					<font color="black">学号</font>
				</th>
				<th  scope="col">
					<font color="black">学年</font>
				</th>
				<th  scope="col">
					<font color="black">处罚类别</font>
				</th>
				<th  scope="col">
					<font color="black">处罚原因</font>
				</th>
			</tr>
			<logic:notEmpty name="cfqk">
			<logic:iterate id="s" name="cfqk" >
									<tr style="cursor:hand;" align="center"  onclick="rowOnClick(this)">
										
											<td scope="col" align="center">
												<bean:write name="s" property="xh"/>
											</td>
											<td scope="col" align="center">
												<bean:write name="s" property="xn"/>
											</td>
											<td scope="col" align="center">
												<bean:write name="s" property="cflb"/>
											</td>
											<td scope="col" align="center">
												<bean:write name="s" property="cfyy"/>
											</td>
										
									</tr>
									</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="cfqk">
								<tr>
									<th scope="col" colspan="6">
										未找到任何记录！
									</th>
								</tr>
							</logic:empty>
						</table>
<%--						<div align="center" class='noPrin'>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">--%>
<%--				页面设置--%>
<%--			</button>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">--%>
<%--				打印预览--%>
<%--			</button>--%>
<%--			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">--%>
<%--				直接打印--%>
<%--			</button>--%>
<%--		</div>--%>
    </html:form>
</body>
</html:html>
