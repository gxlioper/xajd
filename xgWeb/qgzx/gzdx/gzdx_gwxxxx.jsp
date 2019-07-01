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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>
	<body>		
		<html:form action="/qgzxLogic" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
						当前所在位置：勤工助学 - 岗位发布 - 岗位详细信息
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">				
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								岗位信息
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>岗位名称：
						</td>
						<td height="22" align="left">
								${rs.gwdm }
						</td>
						<td height="22" align="right">
							<font color="red">*</font>申请单位(部门)：
						</td>
						<td height="22" align="left">
							${rs.yrdwmc }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							用人单位管理员姓名：
						</td>
						<td height="22" align="left" colspan="">
							${rs.fzr }
						</td>
						<td height="22" align="right">
							联系电话：
						</td>
						<td height="22" align="left" colspan="">
							${rs.lxdh }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学年：
						</td>
						<td height="22" align="left">
							${rs.xn }
						</td>
						<td height="22" align="right">
							年度：
						</td>
						<td height="22" align="left">
							${rs.nd }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作开始日期：
						</td>
						<td height="22" align="left">
							${rs.gzksrq }
						</td>
						<td height="22" align="right">
							<font color="red">*</font>工作结束日期：
						</td>
						<td height="22" align="left">
							${rs.gzjsrq }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>审批使用人数：
						</td>
						<td height="22" align="left">
							${rs.sqsyrs }
						</td>
						<td align="right">岗位性质：</td><td>${rs.gwxzmc }</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>工作时间：
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="gzsj" styleId="gzsj"
								style="width:100%;height:80px" />
							(例：周一上，周二下...)
							<span id="gzsjDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>工作内容：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作要求：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gzyd" styleId="gzyd"
								style="width:100%" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							申请单位(部门)意见：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqdwyj" style="width:100%"
								onblur="chLeng(this,'100')" rows="5"></html:textarea>
						</td>
					</tr>

					<!--显示参加岗位的学生列表-->
					<tr>
						<td colspan="4" bgcolor="#CCCCCC" onclick="document.getElementById('tbodyXs').style.display=document.getElementById('tbodyXs').style.display=='none' ? '' : 'none'" align="center"><font color="blue"><b>参加岗位的学生</b></font></td>
					</tr>
					<tr><td colspan="4">
					<table style="display:none" id="tbodyXs" width="100%">
						<thead>
							<tr>
								<td>
									学号
								</td>
								<td>
									姓名
								</td>
								<td>
									性别	
								</td>
								<td>
									班级
								</td>
							</tr>
						</thead>
						<logic:iterate id="v" name="xsList">			
							<tr>
								<td>
									<bean:write name="v" property="xh"/>
								</td>
								<td>
									<bean:write name="v" property="xm"/>	
								</td>
								<td>
									<bean:write name="v" property="xb"/>	
								</td>
								<td>
									<bean:write name="v" property="bjmc"/>	
								</td>
							</tr>
						</logic:iterate>				
					</table>
					</td>
					</tr>
					<!--end显示参加岗位的学生列表-->
				</table>
			</logic:notEmpty>

		</html:form>
	</body>
</html>
