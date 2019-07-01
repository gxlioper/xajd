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
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	
	<body>
		<html:form action="/yxjzyjs.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：学生详细信息查询
			</div>
		</div>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb" />
		<input type="hidden" id="url" name="url" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="from" name="from" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="xxdm" name="xxdm"
			value="<bean:write name="xxdm"/>" />
		
		<table width="100%" align="center" class="tbstyle">		
			<tr>
				<td>
					<table width="100%" class="tbstyle">
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
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									学号：
								</td>
								<td align="left" width="25%">
									<html:text name="rs" property="xh" readonly="true" onkeypress="if(window.event.keyCode==13) queryxh();"></html:text>
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
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
										出生日期：
									</td>
									<td align="left">
										<bean:write name="rs" property="csrq" />
									</td>
									<td align="right">
										班级：
									</td>
									<td align="left">
										<bean:write name="rs" property="bjmc" />
									</td>
								</tr>
							<tr>
								<td align="right">
									民族：
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<td align="right">
									学制：
								</td>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							<tr>
								<td align="right">
									政治面貌：
								</td>
								<td align="left">
									<bean:write name="rs" property="zzmmmc" />
								</td>
								<td align="right">
									学籍状态：
								</td>
								<td align="left">
									<bean:write name="rs" property="xjztm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									身份证号：
								</td>
								<td align="left">
									<bean:write name="rs" property="sfzh" />
								</td>
								<td align="right">
									电子邮箱：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdzxx" />
								</td>
							</tr>
							<tr>
								<td align="right">
									来源地区：
								</td>
								<td align="left">
									<bean:write name="rs" property="lydq" />
								</td>
								<td align="right">
									联系电话：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
								<tr>
									<td align="right">
										籍贯：
									</td>
									<td align="left">
										<bean:write name="rs" property="jg" />
									</td>
									<td align="right">
										手机号码：
									</td>
									<td align="left" colspan="2">
										<bean:write name="rs" property="sjhm" />
									</td>
								</tr>
							<tr>
								<td align="right">
									宿舍号：
								</td>
								<td align="left">
									<bean:write name="rs" property="ssbh" />
								</td>
								<td align="right">
									宿舍电话：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="qsdh" />
								</td>
							</tr>
								<tr>
									<td align="right">
										家庭详细地址：
									</td>
									<td>
										<bean:write name="rs" property="jtszd" />
									</td>
									<td align="right">
										卡号：
									</td>
									<td colspan="2">
										<bean:write name="rs" property="kh" />
									</td>
								</tr>
							<tr>
								<td align="right">
									辅导员：
								</td>
								<td align="left">
									<bean:write name="rs" property="fdyxm" />
								</td>
								<td align="right">
									辅导员电话：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="fdyyddh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									班主任：
								</td>
								<td align="left">
									<bean:write name="rs" property="bzrxm" />
								</td>
								<td align="right">
									助理班主任：
								</td>
								<td align="left" colspan="2">
									<bean:write name="rs" property="zlbzrxm" />
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		</html:form>
	</body>
</html>
