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
				saveinfo('pjpy_ycsf_hjmdxxModi.do','dm');
			}
		</script>
		<html:form action="/pjpyycsfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置:评奖评优 - 审核 - <bean:message key="lable.xsgzyxpzxy" />获奖名单上报 - 修改
				</div>
			</div>
			<!-- 主键 -->
			<input type="hidden" name="pk" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="pk" id="userType" value="${userType }" />
			<input type="hidden" name="act" id="act" value="save" />
			<!-- 奖项类别 -->
			<input type="hidden" name="lb" id="lb" value="${lb }" />
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个修改
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						学号：
					</td>
					<td align="left">
						${rs.xh }
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						${rs.xn }
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						${rs.xm }
					</td>

					<td align="right">
						学期：
					</td>
					<td align="left">
						${rs.xqmc }
					</td>
				</tr>
				<logic:notEqual value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${rs.xymc }
						</td>

						<td align="right">
							平时考核成绩：
						</td>
						<td align="left">
							${rs.pjkhcj }
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>

						<td align="right">
							学业考核成绩：
						</td>
						<td align="left">
							${rs.xykhcj }
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>

						<td align="right">
							阶段考核成绩：
						</td>
						<td align="left">
							${rs.jdkhcj }
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>

						<td align="right">
							综合素质测评总分：
						</td>
						<td align="left">
							${rs.zhszcpzf }
						</td>
					</tr>
				</logic:notEqual>
				<logic:equal value="yes" name="ahzyjsxy">
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${rs.xymc }
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							总科平均分：
						</td>
						<td align="left">
							${rs.pjf }
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							${rs.pm }
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="4">
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main2" style="color:blue;cursor:hand"
										onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>课程信息</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="child2" style="display:none">
							<table width="100%" border="1" align="center" class="tbstyle">
								<thead>
									<tr>
										<td align="center">
											学年
										</td>
										<td align="center">
											学期
										</td>
										<td align="center">
											课程名称
										</td>
										<td align="center">
											课程性质
										</td>
										<td align="center">
											考试类型
										</td>
										<td align="center">
											成绩
										</td>
										<logic:notEqual value="yes" name="ahzyjsxy">
											<td align="center">
												学分
											</td>
											<td align="center">
												补考成绩
											</td>
										</logic:notEqual>
									</tr>
								</thead>
								<logic:iterate id="s" name="cjList">
									<logic:notEqual value="yes" name="ahzyjsxy">
										<tr>
											<logic:iterate id="v" name="s">
												<td align="center">
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:notEqual>
									<logic:equal value="yes" name="ahzyjsxy">
										<tr>
											<logic:iterate id="v" name="s" offset="0" length="6">
												<td align="center">
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:equal>

								</logic:iterate>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						奖项类别：
					</td>
					<td align="left">
						${rs.lb }
					</td>
					<td align="right">
						奖项：
					</td>
					<td align="left">
						<logic:equal value="jxj" name="lb">
							<html:select name="rs" property="dm" styleId="dm">
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</logic:equal>
						<logic:equal value="rych" name="lb">
							<html:select name="rs" property="dm" styleId="dm">
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
						<td align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" styleId="fdyyj" style="width:95%"
								rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="xy" name="userType">
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyshyj" styleId="xyshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="xx" name="userType">
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxshyj" styleId="xxshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
					<logic:equal value="admin" name="userType">
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxshyj" styleId="xxshyj"
								style="width:95%" rows="5">
							</html:textarea>
						</td>
					</logic:equal>
				</tr>
			</table>
			<div class="buttontool" align="center">

				<logic:equal value="true" name="modi">
					<button class="button2" onclick="saveinfos()" style="width:80px"
						id="btn_save">
						保 存
					</button>
				</logic:equal>

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
