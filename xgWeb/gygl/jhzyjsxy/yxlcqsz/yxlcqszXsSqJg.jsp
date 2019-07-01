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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript"> 
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<html:form action="/jhzy_yxlcqsz" method="post">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="userName" scope="session"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 优秀楼层寝室长管理 - 结果查询
				</div>
			</div>

			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							学号：
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							总申请次数：
							<bean:write name="numCout" scope="request" />
						</td>
					</tr>
				</thead>
			</table>
			<div id="result">
				<div class="searchcontent">
					<logic:equal value="0" name="numCout">
						<br />
						<br />
						<p align="center">
							未找到任何记录！
						</p>
					</logic:equal>
					<%--						<logic:notEqual value="0" name="numCout">--%>
					<%--							<div style="overflow-x:auto;overflow-y:auto;width:800px;">--%>
					<logic:notEmpty name="rsBjqsz">
						<fieldset>
							<legend>
								百佳寝室长，申请记录数：
								<bean:write name="rsBjqszNum" scope="request" />
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="">

										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											辅导员审核
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>

									</tr>
								</thead>
								<logic:iterate name="rsBjqsz" id="s">
									<tr
										style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<logic:notEmpty name="rsEsjcz">
						<fieldset>
							<legend>
								二十佳层长，申请记录数：
								<bean:write name="rsEsjczNum" scope="request" />
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="">

										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											辅导员审核
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>

									</tr>
								</thead>
								<logic:iterate name="rsEsjcz" id="s">
									<tr
										style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>					
					<logic:notEmpty name="rsWjlz">
						<fieldset>
							<legend>
								五佳楼长，申请记录数：
								<bean:write name="rsWjlzNum" scope="request" />
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="">

										<td>
											学号
										</td>
										<td>
											姓名
										</td>
										<td>
											班级
										</td>
										<td>
											辅导员审核
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</td>
										<td>
											学校审核
										</td>

									</tr>
								</thead>
								<logic:iterate name="rsWjlz" id="s">
									<tr
										style="background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        ">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>					
				</div>
			</div>

		</html:form>
	</body>
</html>
