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
<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function viewgrjl(obj){
		   var pkValue = document.getElementById("xh").value;   
		   obj.href = "viewgrjlinfo.do?method=grjlinfo&jytype=jyweb&pkValue="+pkValue;
		
		}
		function huifu(){
		  var xh =document.getElementById("xh").value;
		  var xm =document.getElementById("xm").value;
		  var yhm =document.getElementById("xm").value;
		  
		  url = "dwhf.do?method=dwhf&jytype=jyweb&xh="+xh+"&xm="+xm+"&yhm="+yhm+"&r="+Math.random();
		  
		  showTopWin(url, 390, 300);
	
		}
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/viewgrjlinfo" method="post" />
		<div class="mainframe">
			<div class="jy_midframe">
				<h1>
					求职信息
				</h1>
				<html:hidden name="rs" property="xh" />
				<html:hidden name="rs" property="xm" />
				<div class="view_zp">

					<table width="90%" align="center" class="grxx">
						<thead>
							<tr>
								<td colspan="4">
									基本信息
								</td>
							</tr>
						</thead>
						<tr>
							<td width="100">
								姓名：
							</td>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
							<td>
								性别：
							</td>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
							<td>
								专业：
							</td>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<td>
								联系电话：
							</td>
							<td>
								<logic:equal name="usertype" value="dw">
									<bean:write name="rs" property="lxdh" />
								</logic:equal>
								<logic:equal name="usertype" value="xs">
								    <font color="#888888">该信息只有注册单位才可查看</font>
								</logic:equal>
							</td>
							<td>
								电子邮箱：
							</td>
							<td>
								<bean:write name="rs" property="email" />
							</td>
						</tr>
						<tr>
							<td>
								求职行业：
							</td>
							<td colspan="3">
								<bean:write name="rs" property="qzhy" />

							</td>
						</tr>
						<tr>
							<td>
								求职意向：
							</td>
							<td colspan="3">
								<bean:write name="rs" property="qzyx" />
							</td>
						</tr>
						<tr>
							<td>
								发布时间：
							</td>
							<td colspan="3">
								<bean:write name="rs" property="fbsj" />
							</td>
						</tr>
					</table>
					<table width="90%" align="center" class="grxx">
						<thead>
							<tr>
								<td>
									个人简述
								</td>
							</tr>
						</thead>
						<tr>
							<td>
								<bean:write name="rs" property="grjs" />
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="usertype" value="dw" scope="session">
									<a onclick="viewgrjl(this);" href="" target="_blank">查看简历</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button onclick="huifu()">
										单位回复
									</button>
								</logic:equal>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</div>
	</body>
</html>
