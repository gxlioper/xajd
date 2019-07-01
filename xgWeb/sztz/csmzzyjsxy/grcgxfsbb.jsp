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
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<div id="rsTable">
			<p align="center" style="font-size: 18px;font-family:黑体">
				长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" />学生素质拓展学分申报表
			</p>
			<div align="center">
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xn"/>&nbsp;&nbsp;</u>学年
				第<u>&nbsp;&nbsp;<bean:write name="rs" property="xq"/> &nbsp;&nbsp;</u>学期 
				系别：<u>&nbsp;&nbsp;<bean:write name="rs" property="xymc"/>&nbsp;&nbsp;</u> 
				班级：<u>&nbsp;&nbsp;<bean:write  name="rs" property="bjmc"/>&nbsp;&nbsp;</u> 
				 学号：<u>&nbsp;&nbsp;<bean:write name="rs" property="xh"/>&nbsp;&nbsp;</u> 
			</div>
			<table width="100%" border="0" class="tbstyle">
				<tr>
					<td colspan="8" align="center">
						<strong>思想政治与道德素养（限 2 分） </strong>
					</td>
				</tr>
				<logic:notEmpty name="szList">
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>			
				<logic:iterate id="rs1" name="szList">
					<tr>
						<td>
							<bean:write name="rs1" property="hh" />
						</td>
						<td>
							<bean:write name="rs1" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs1" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs1" property="jxm" />
						</td>
						<td>
							<bean:write name="rs1" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs1" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs1" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>社会实践与志愿服务（限 2 分） </strong>
					</td>
				</tr>
				<logic:notEmpty name="shsjList">
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>
				<logic:iterate id="rs2" name="shsjList">
					<tr>
						<td>
							<bean:write name="rs2" property="hh" />
						</td>
						<td>
							<bean:write name="rs2" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs2" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs2" property="jxm" />
						</td>
						<td>
							<bean:write name="rs2" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs2" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs2" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>科技学术与创新创业（限 1 分） </strong>
					</td>
				</tr>
				<logic:notEmpty name="kjcxList">
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>
				<logic:iterate id="rs3" name="kjcxList">
					<tr>
						<td>
							<bean:write name="rs3" property="hh" />
						</td>
						<td>
							<bean:write name="rs3" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs3" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs3" property="jxm" />
						</td>
						<td>
							<bean:write name="rs3" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs3" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs3" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>文化艺术与身心发展（限 2 分） </strong>
					</td>
				</tr>
				<logic:notEmpty name="whsxList">
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>
				<logic:iterate id="rs4" name="whsxList">
					<tr>
						<td>
							<bean:write name="rs4" property="hh" />
						</td>
						<td>
							<bean:write name="rs4" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs4" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs4" property="jxm" />
						</td>
						<td>
							<bean:write name="rs4" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs4" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs4" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td colspan="8" align="center">
						<strong>社团活动与社会工作（限 1 分） </strong>
					</td>
				</tr>
				<logic:notEmpty name="sthdList">
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>
				<logic:iterate id="rs5" name="sthdList">
					<tr>
						<td>
							<bean:write name="rs5" property="hh" />
						</td>
						<td>
							<bean:write name="rs5" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs5" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs5" property="jxm" />
						</td>
						<td>
							<bean:write name="rs5" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs5" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs5" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
				<logic:notEmpty name="jnpxList">
				<tr>
					<td colspan="8" align="center">
						<strong>技能培训及其他 </strong>
					</td>
				</tr>
				<tr>
					<td>
						序号
					</td>
					<td>
						名称
					</td>
					<td>
						时间
					</td>
					<td>
						成果
					</td>
					<td>
						性质
					</td>
					<td>
						级别
					</td>
					<td>
						<p align="center">
							自评学分
						</p>
					</td>
					<td>
						<p align="center">
							认证学分
						</p>
					</td>
				</tr>
				<logic:iterate id="rs6" name="jnpxList">
					<tr>
						<td>
							<bean:write name="rs6" property="hh" />
						</td>
						<td>
							<bean:write name="rs6" property="xmmc" />
						</td>
						<td>
							<bean:write name="rs6" property="zbsj" />
						</td>
						<td>
							<bean:write name="rs6" property="jxm" />
						</td>
						<td>
							<bean:write name="rs6" property="cyjs" />
						</td>
						<td>
							<bean:write name="rs6" property="xmjb" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<bean:write name="rs6" property="xf" />
						</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
			</table>
			<table width="100%" border="0" class="tbstyle">
				<tr>
					<td width="5%">
						<strong>自评<br>总分 </strong>
					</td>
					<td width="45%">
						&nbsp;
					</td>
					<td width="15%">
						<p align="center">
							<strong>认证总分 <br>（由辅导员填写） </strong>
						</p>
					</td>
					<td width="35%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td rowspan="3">
						<p>
							认
						</p>
						<p>
							证
						</p>
						<p>
							意
						</p>
						<p>
							见
						</p>
					</td>
					<td>
						<p>
							审核意见：
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							班素质拓展认证小组（签字）：
						</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p>
					</td>
					<td colspan="2" >
						<p align="left" >
							审核意见：
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							&nbsp;
						</p>
						<p align="left">
							辅导员签字：
						</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p>
					</td>
				</tr>
				<tr>
					<td height="80px" valign="top">					
						审核意见：						
					</td>
					<td colspan="2" height="80px" valign="top">
						审核意见：
					</td>
				</tr>
				<tr>
					<td>
						<p>
							&nbsp;
						</p>
						<p>
							系素质拓展认证中心（盖章）
						</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p>
					</td>
					<td colspan="2">
					    <p>
							&nbsp;
						</p>
						<p>
							院素质拓展认证中心（盖章）
						</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日</p>
					</td>
				</tr>
			</table>
		</div>
		<div class="buttontool" align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="打印" name="button_print"
				style="cursor:hand;" onclick="expTab('rsTable','')">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>	

