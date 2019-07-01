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
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>

		<html:form action="/qgzxNblg" method="post">
			<p align="center">
				<logic:equal name="gwxz" value="gdgw">
					<font size="5"><strong><bean:write name="xxmc"/>固定勤工助学岗位申请表 </strong></font>
				</logic:equal>
				<logic:equal name="gwxz" value="lsgw">
					<font size="5"><strong><bean:write name="xxmc"/>临时勤工助学岗位申请表 </strong></font>
				</logic:equal>		
			</p>
			<br>
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				部门名称：<bean:write name="rs" property="bmmc"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				岗位名称：<bean:write name="rs" property="gwdm"/> 
			
			<table cellspacing="0" cellpadding="0" class="tbstyle" align="center">
				<tr height="32">
					<td align="center" width="100">
							申请单位
					</td>
					<td align="center" width="200">
							<bean:write name="rs" property="sqdw"/>
					</td>
					<td align="center" width="100">
							申请时间
					</td>
					<td align="center" width="200">
							<bean:write name="rs" property="year"/>-<bean:write name="rs" property="month"/>-<bean:write name="rs" property="day"/>
					</td>
				</tr>
				<tr height="32">
					<td align="center">
						<p align="center">
							考核人
						</p>
					</td>
					<td align="center">
							<bean:write name="rs" property="fzr"/>
					</td>
					<td align="center">
							联系电话
					</td>
					<td align="center">
							<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr height="32">
					<td align="center">
							工作内容
					</td>
					<td>
							<bean:write name="rs" property="gznr"/>
					</td>
					<td align="center">
							岗位人数
					</td>
					<td align="center">
							<bean:write name="rs" property="xyrs"/>
					</td>
				</tr>
				<logic:equal name="gwxz" value="lsgw">
					<tr height="32">
						<td align="center">
								是否需学生处勤工<br>
								助学中心代为招聘
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="dwzp"/>
						</td>
					</tr>
					<tr>
						<td align="center">
								人员落实情况
						</td>
						<td colspan="3">
							<bean:write name="rs" property="rylsqk"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="gwxz" value="gdgw">
					<tr height="32">
						<td align="center">
								每月勤工助学时间
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="myqgzxsj"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center">
								特殊要求
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="tsyq"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center">
								招聘面试时间
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="mssj"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center" >
								是否需学生处勤工<br>
								助学中心代为招聘<br>
								(如果人员已确定，<br>
								请注明）
						</td>
						<td colspan="3" valign="top">
							<bean:write name="rs" property="dwzp"/>
							<logic:present name="rs" property="rylsqk">
								 <br>
								(<bean:write name="rs" property="rylsqk"/>)
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td align="center">
						<p align="center">
							部
						</p>
						<p align="center">
							门
						</p>
						<p align="center">
							意
						</p>
						<p align="center">
							见
						</p>
					</td>
					<td colspan="3" valign="top">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							负责人签名（公章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<p align="center">
							学
						</p>
						<p align="center">
							生
						</p>
						<p align="center">
							处
						</p>
						<p align="center">
							意
						</p>
						<p align="center">
							见
						</p>
					</td>
					<td colspan="3" valign="top">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							负责人签名（公章）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<p align="center">
							备
						</p>
						<p align="center">
							注
						</p>
					</td>
					<td colspan="3" valign="top">
						<p>
							<bean:write name="rs" property="bz"/>
						</p>
					</td>
				</tr>
			</table>
			<p align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：本表一式两份，一份由申请单位保存，一份交学生处备案。
			</p>
		</html:form>
	</body>
</html>
