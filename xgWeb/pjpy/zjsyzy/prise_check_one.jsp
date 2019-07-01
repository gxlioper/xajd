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
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function saveinfo() {
			var userType = document.getElementById('userType').value;
			if ('xy'==userType) {
				var xyyj = document.getElementById('xyyj').value;
				var fdyyj = document.getElementById('fdyyj').value;
				if (xyyj.length>400||fdyyj.length>400) {
					alert('字数超过限制,请重填!');
					return;
				}
			} else {
				var xxyj = document.getElementById('xxyj').value;
				if (xxyj.length>400) {
					alert('字数超过限制,请重填!');
					return;
				}
			}
			refreshForm('pjpy_zjsyzy.do?method=priseCheckOne');
			BatAlert.showTips('正在操作，请等待...');
		}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 奖学金审核 - 单个奖学金审核
				</div>
			</div>
<%--			<input type="hidden" name="pkVal"--%>
<%--				value="<bean:write name="xn||nd||xh||jxjdm"/>" />--%>
				<input type="hidden" name="tg" id="tg" value="${tgres }"/>
				<input type="hidden" name="res" id="res" value="${mes}"/>
				<input type="hidden" name="userType" id="userType" value="${userType }"/>
				<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个奖学金审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left" id="selxh">
						<bean:write name="rs" property="xh" />
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh" />"/>
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
						<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd"/>"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
						<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />"/>
						<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />"/>
					</td>
 				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						奖学金：
					</td>
					<td align="left">
						<bean:write name="rs" property="jxjmc" />
						<html:hidden property="jxjdm" name="rs"/>
						<input type="hidden" name="jxjmc" id="jxjmc" value="${rs.jxjmc }">
						<input type="hidden" name="bmdm" id="xdm" value="${rs.xydm }">
						<input type="hidden" name="nj" id="nj" value="${rs.nj }">
						<input type="hidden" name="sh" id="sh" value="${rs.yesNo }">
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						德成绩：
					</td>
					<td align="left">
						${rs.dcj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						德成绩排名：
					</td>
					<td align="left">
						${rs.dcjpm }
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						智成绩：
					</td>
					<td align="left">
						${rs.zcj }
					</td>
					
				</tr>

				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
						智成绩排名：
					</td>
					<td align="left">
						${rs.zcjpm }
					</td>
					
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						体成绩：
					</td>
					<td align="left">
						${rs.tcj }
					</td>
					<td align="right">
						体成绩排名：
					</td>
					<td align="left">
						${rs.tcjpm }
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						技能加分：
					</td>
					<td align="left">
						<bean:write name="rs" property="jnjf"/>
					</td>
					<td align="right">
						技能加分排名：
					</td>
					<td align="left">
						${rs.jnjfpm }
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						综合测评总分：
					</td>
					<td align="left">
						${rs.kpf }
					</td>
					<td align="right">
						综合测评总分排名：
					</td>
					<td align="left">
						${rs.cpzfpm }
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						综合素质测评参评排名：
					</td>
					<td align="left">
						${pm }
					</td>
					<td align="right">
						能否获得特等奖学金：
					</td>
					<td align="left">
						${nfhdjxj }
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						担任职务：
					</td>
					<td align="left">
						${rs.drzw }
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left" colspan="">
						<html:select property="yesNo" styleId="yesNo" name="rs">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<!-- 成绩信息 -->
				<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child0.style.display=(document.all.child0.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>课程成绩信息</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child0" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="center">
											课程
										</td>
										<td align="center">
											课程类型
										</td>
										<td align="center">
											成绩
										</td>
										
									</tr>
									<logic:empty name="cjList">
									<tr style="width:20px">
										<td align="center" colspan="3">
											<p align="center">无任何记录!</p>
										</td>
									</tr>
									</logic:empty>
									<logic:notEmpty name="cjList">
										<logic:iterate id="s" name="cjList">
											<tr bgcolor="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
												<logic:iterate id="v" name="s" offset="1">
													<td align="center">
													${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</div>
							</td>
						</tr>
				<!-- 违纪信息 -->	
				<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main3" style="color:blue;cursor:hand"
											onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>违纪处分信息</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child1" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="center">
											学年
										</td>
										<td align="center">
											学期
										</td>
										<td align="center">
											处分类别
										</td>
										<td align="center">
											处分原因
										</td>
									
									</tr>
									<logic:empty name="wjList">
										<tr style="width:20px">
										<td align="center" colspan="4">
											<p align="center">无任何记录!</p>
										</td>
									</tr>
									</logic:empty>
									<logic:notEmpty name="wjList">
										<logic:iterate id="s" name="wjList">
											<tr>
												<logic:iterate id="v" name="s" >
													<td align="center">
													${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</div>
							</td>
						</tr>	
				<logic:equal value="xy" name="userType">
				<tr>
					<td align="right">
						辅导员意见：
						<br/>
						<font color="red">(限制在400字内)</font>
					</td>
					<td align="left" colspan="3">
						<textarea name="fdyyj" id="fdyyj" style="width:100%" rows="3"><bean:write name="rs" property="fdyyj"/></textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
						<br/>
						<font color="red">(限制在400字内)</font>
					</td>
					<td align="left" colspan="3">
						<textarea name="xyyj" id="xyyj" style="width:100%" rows="3"><bean:write name="rs" property="xyshyj" /></textarea>
					</td>
				</tr>

				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr>
					<td align="right">
						辅导员意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="fdyyj" />
					</td>
				</tr>				
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						学校审核意见：
						<br/>
						<font color="red">(限制在400字内)</font>
					</td>
					<td align="left" colspan="3">
						<textarea name="xxyj" id="xxyj" style="width:100%" rows="3"><bean:write name="rs" property="xxshyj" /></textarea>
					</td>
				</tr>
				
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="saveinfo()"
					style="width:90px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert(""+document.getElementById('res').value);
					Close();
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
