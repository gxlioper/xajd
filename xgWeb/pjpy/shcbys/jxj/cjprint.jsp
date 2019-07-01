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
							${title }课程成绩表
						</h3>
		<table width="98%" class="tbstyle" >
		
			<tr align="center">
				<th  scope="col">
					学&nbsp;&nbsp;年
				</th>
				
				<th  scope="col">
					学&nbsp;&nbsp;期
				</th>
				<th  scope="col">
					课程名称
				</th>
				<th  scope="col">
					课程类型
				</th>
				<th scope="col">
					成&nbsp;&nbsp;绩
				</th>
				<th scope="col">
					补考成绩
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
