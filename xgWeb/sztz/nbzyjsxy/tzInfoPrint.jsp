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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print" type="text/css">
   .brk{
	page-break-after:always;
    }
   .noPrin{display:none}
</style>
	<body onload="colorOn()">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/nbzy_sztz" method="post">
			<div align="center" style="font: bold;font-size: 18px;">
				<bean:write name="xxmc" />
				大学生素质拓展证书
			</div>
			<br>
			<div>
				学号：
				<u>&nbsp;<bean:write name="rsStu" property="xh" />&nbsp;</u>&nbsp;&nbsp;
				班级：
				<u>&nbsp;<bean:write name="rsStu" property="bjmc" />&nbsp;</u>&nbsp;&nbsp;
				<bean:message key="lable.xsgzyxpzxy" />：
				<u>&nbsp;<bean:write name="rsStu" property="xymc" />&nbsp;</u>&nbsp;&nbsp;
				专业：
				<u>&nbsp;<bean:write name="rsStu" property="zymc" />&nbsp;</u>&nbsp;&nbsp;
				入学时间：
				<u>&nbsp;<bean:write name="rsStu" property="rxrq" />&nbsp;</u>
			</div>
			<table width="100%" class="tbstyle">
				<tr>
					<td width="15%" align="center">
						姓名
					</td>
					<td>
						<bean:write name="rsStu" property="xm" />
					</td>
					<td align="center">
						性别
					</td>
					<td>
						<bean:write name="rsStu" property="xb" />
					</td>
					<td rowspan="5" width="20%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center">
						民族
					</td>
					<td >
						<bean:write name="rsStu" property="mzmc" />
					</td>
					<td align="center">
						籍贯
					</td>
					<td>
						<bean:write name="rsStu" property="jg" />
					</td>
				</tr>
				<tr>
					<td align="center">
						出生年月
					</td>
					<td >
						<bean:write name="rsStu" property="csrq" />
					</td>
					<td align="center">
						政治面貌
					</td>
					<td>
						<bean:write name="rsStu" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td align="center">
						身份证号码
					</td>
					<td colspan="3">
						<bean:write name="rsStu" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td align="center">
						素质拓展证书号
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						在校期间奖励情况
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" class="tbstyle">
							<tr>
								<td align="center">
									奖励名称
								</td>
								<td align="center">
									时间
								</td>
								<td align="center">
									备注
								</td>
							</tr>
							<logic:iterate name="rsJlxm" id="s">
								<tr>
									<logic:iterate id="v" name="s" offset="0">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						素质拓展记录（具体事项及成绩）
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<table width="100%" class="tbstyle">
							<logic:iterate name="rsTzXm" id="s">
								<bean:size id="tSize" name="s" property="xmList" />
								<logic:iterate id="v" name="s" property="kmList">
									<tr style="cursor:hand">
										<td rowspan="<%=tSize%>" >
											<bean:write name="v" property="kmm" />
										</td>
										<logic:notEqual name="tSize" value="0">
											<logic:iterate id="b" name="s" property="xmList">
												<td>
													&nbsp;
													<bean:write name="b" property="xmjl" />
												</td>												
												<%
												out.print("</tr>");
												%>
											</logic:iterate>
										</logic:notEqual>
										<logic:equal name="tSize" value="0">
											<td>
												&nbsp;
											</td>											
										</logic:equal>
									</tr>
								</logic:iterate>
							</logic:iterate>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5" height="40" align="center">
						学生素质拓展中心认证
					</td>
				</tr>
				<tr>
					<td colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;经核实，本证书记载我校 <u>&nbsp;<bean:write name="rsStu" property="xymc" />&nbsp;</u> 院（系）<u>&nbsp;<bean:write name="rsStu" property="nj" />&nbsp;</u> 级学生 <u> &nbsp;<bean:write name="rsStu" property="xm" />&nbsp;</u>  的素质拓展内容客观真实。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;特予证实。
					<br><br><br><br>
					<div align="right">
					学生素质拓展中心
					</div>
					<div align="right">
					(盖章有效)
					</div>
					<div align="right">
					<bean:write name="fzrq"/>
					</div>	
					</td>
				</tr>
			</table>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>

	</body>
</html>
