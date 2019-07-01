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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<style type="text/css">
<!--
.style1 {font-size: 16px}
.style2 {font-size: 14px}
.style3 {
	color: #000000;
	font-size: 15px;
}
.style4 {
	font-size: 15px;
	font-weight: bold;
}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<body>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：学生成绩查询
			</div>
		</div>
		<table width="100%" border="1" align="center" class="tbstyle">
			<tr>
				<td>
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main1" style="color:blue;cursor:hand"
									onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>学生基本信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child1">
						<table width="100%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									学号：
								</td>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right" width="15%">
									年级：
								</td>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main12" style="color:blue;cursor:hand"
									onclick="document.all.child12.style.display=(document.all.child12.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>学生成绩</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child12"">
						<table width="100%" border="1" class="tbstyle">
							<thead>
<%--								<tr>--%>
<%--									<td>--%>
<%--										<div align="center" class="style2">--%>
<%--											学生成绩记录--%>
<%--										</div>--%>
<%--									</td>--%>
<%--								</tr>--%>
							</thead>
							<tbody>
<%--								<tr>--%>
<%--									<td rowspan="11">记录数：<bean:write name="rsNum22" scope="request"/></td>--%>
<%--								</tr>--%>
								<tr>
									<td rowspan="11">
									<table width="100%" border="1" class="tbstyle">
										<thead>
											<tr>
											<td align="center">行号</td>
											<td align="center">学年</td>
											<td align="center">学期</td>
<%--											<td align="center">课程代码</td>--%>
											<td align="center">课程名称</td>
											<td align="center">成绩</td>
											<td align="center">补考成绩</td>
											<td align="center">重修成绩</td>
											<td align="center">绩点</td>
											<td align="center">学分</td>
											<td align="center">是否选修课</td>
											</tr>
										</thead>
										<tbody id="rsTable23">
											<logic:present name="rs22">
      											<logic:iterate id="s22" name="rs22">
													<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" bgcolor="${s22.bgcolor }">
          												<td align="center"><bean:write name="s22" property="hh" /></td>
         										 		<td align="center"><bean:write name="s22" property="xn" /></td>
         	 											<td align="center"><bean:write name="s22" property="xq" /></td>
<%--          												<td align="center"><bean:write name="s22" property="kcdm" /></td>--%>
         	 											<td align="center"><bean:write name="s22" property="kcmc" /></td>
         	 											<td align="center"><bean:write name="s22" property="cj" /></td>
         										 		<td align="center"><bean:write name="s22" property="bkcj" /></td>
         	 											<td align="center"><bean:write name="s22" property="cxcj" /></td>
          												<td align="center"><bean:write name="s22" property="jd" /></td>
         	 											<td align="center"><bean:write name="s22" property="xf" /></td>
         	 											<td align="center"><bean:write name="s22" property="sfxx" /></td>
        											</tr>
      											</logic:iterate>
    										</logic:present>
    										<logic:empty name="rs22">
    											<tr>
    												<td align="center" colspan="10">
    													<br/>
    													暂无成绩!
    												</td>
    											</tr>
    										</logic:empty>
										</tbody>
									</table>
								</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
