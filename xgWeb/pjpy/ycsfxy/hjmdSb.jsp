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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
			function saveinfos() {
				var userType = document.getElementById('userType').value;
				var yj = '';
				if(userType == 'fdy'){
					yj = 'fdyyj';
				}else if(userType == 'xy'){
					yj = 'xyshyj';
				}else{
					yj = 'xxshyj';
				}
				if (yj != null && yj.length > 500) {
					alert("上报意见字数过大!");
					return false;
				}
				saveinfo('pjpy_ycsf_hjmdsb.do?act=save','dm');
			}
		</script>
		<html:form action="/pjpyycsfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置:评奖评优 - 审核 - <bean:message key="lable.xsgzyxpzxy" />获奖名单上报 - 批量上报
				</div>
			</div>
			<input type="hidden" name="xhList" id="xhList" value="${xhList }" />
			<input type="hidden" name="pk" id="userType" value="${userType }" />
			<input type="hidden" name="lb" id="lb" value="${lb }" />
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							批量上报
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						学年：
					</td>
					<td align="left">
						${xn }
					</td>
				</tr>
				<tr>
					<td align="right">
						学期：
					</td>
					<td align="left">
						${xq }
					</td>
				</tr>
				<tr>
					<td align="right">
						奖项类别：
					</td>
					<td align="left">
						${lbmc }
					</td>
				</tr>
				<tr>
					<td align="right">
						奖项：
					</td>
					<td align="left">
						<logic:equal value="jxj" name="lb">
							<html:select property="dm" styleId="dm">
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</logic:equal>
						<logic:equal value="rych" name="lb">
							<html:select property="dm" styleId="dm">
								<html:options collection="rychList" property="rychdm"
									labelProperty="rychmc" />
							</html:select>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td align="right">
						上报意见：
						<br />
						<font color="red">(字数限制在500以内)</font>
					</td>
					<logic:equal value="fdy" name="userType">
						<td align="left">
							<html:textarea property="fdyyj" styleId="fdyyj" style="width:95%"
								rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="xy" name="userType">
						<td align="left">
							<html:textarea property="xyshyj" styleId="xyshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="xx" name="userType">
						<td align="left">
							<html:textarea property="xxshyj" styleId="xxshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="admin" name="userType">
						<td align="left">
							<html:textarea property="xxshyj" styleId="xxshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="saveinfos()" style="width:80px"
					id="btn_save">
					保 存
				</button>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<!-- 保存后提示页面 -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</body>
</html>
