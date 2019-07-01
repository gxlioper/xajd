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
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();changezje()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> 勤工助学 - 酬金发放 - 酬金发放 - 酬金发放详单</span>
				</div>
			</div>
			<input type="hidden" id="mes" name="mes" value="${mes}" />
			<logic:notEmpty name="workInfo">
				<input type="hidden" id="doType" name="doType" value="" />
				<input type="hidden" id="pkValue" name="pkValue" value="" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="gwdm" name="gwdm"
					value="<bean:write name="workInfo" property="gwdm"/>" />
				<input type="hidden" id="gwsbsj" name="gwsbsj"
					value="<bean:write name="workInfo" property="gwsbsj"/>" />
				<input type="hidden" id="count" name="count"
					value="<bean:write name="count"/>" />
				<input type="hidden" id="xn" name="xn"
					value="<bean:write name="workInfo" property="xn"/>" />
				<input type="hidden" id="nd" name="nd"
					value="<bean:write name="workInfo" property="nd"/>" />
				<input type="hidden" id="xq" name="xq"
					value="<bean:write name="workInfo" property="xq"/>" />
				<input type="hidden" id="yf" name="yf"
					value="<bean:write name="workInfo" property="yf"/>" />
				<input type="hidden" id="gwxz" name="gwxz"
					value="<bean:write name="workInfo" property="gwxz"/>" />
				<%--剩余经费--%>
				<input type="hidden" id="syjf" name="syjf"
					value="<bean:write name="workInfo" property="syjf"/>" />

				<fieldset>
					<legend>
						学年:
						<bean:write name="workInfo" property="xn" />
						&nbsp;&nbsp;&nbsp;年度:
						<bean:write name="workInfo" property="nd" />
						&nbsp;&nbsp;&nbsp;学期:
						<bean:write name="workInfo" property="xq" />
						&nbsp;&nbsp;&nbsp;月份:
						<bean:write name="workInfo" property="yf" />
					</legend>
					<table width="100%" class="tbstyle" id="rsT">
						<tr>
							<td width="15%" height="27" align="center">
								用人单位
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="yrdwmc" />
							</td>
						</tr>
						<tr>
							<td width="15%" height="27" align="center">
								所属部门
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="xymc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								工作内容
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gznr" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								岗位性质
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gwxzmc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								计酬标准
							</td>
							<td align="center">
								<bean:write name="workInfo" property="jybcbz" />
								<input type="hidden" id="jybcbz" value="<bean:write name="workInfo" property="jybcbz" />" />
								<bean:write name="workInfo" property="jcfs" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								剩余经费
							</td>
							<td align="center">
								<bean:write name="workInfo" property="syjf" />
								（单位:元）
							</td>
						</tr>
						<tr>
							<td align="center">
								工
								<br />
								作
								<br />
								人
								<br />
								员
								<br />
								情
								<br />
								况
							</td>
							<td valign="top" align="center">
								<table width="100%" class="tbstyle">
									<thead>
										<tr>
											<td width="14%">
												<div align="center">
													学号
												</div>
											</td>
											<td width="14%">
												<div align="center">
													姓名
												</div>
											</td>
											<td width="16%">
												<div align="center">
													班级
												</div>
											</td>
											<td width="15%">
												<div align="center">
													工作时间(单位:
													<bean:write name="workInfo" property="gzsjdw" />
													)
												</div>
											</td>
											<td width="8%">
												<div align="center">
													金额
												</div>
											</td>
											<td width="16%">
												<div align="center">
													备注
												</div>
											</td>
										</tr>
									</thead>
									<logic:empty name="ffList">
										<tr>
											<td colspan="6">
												无参加该岗位的学生记录!
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="ffList">
										<logic:iterate name="ffList" id="ffList" indexId="index">
											<tr>
												<td>
													<div align="center">
														<input type="text" name="xh${index}"
															value="<bean:write name="ffList" property="xh"/>"
															style="width:95%" readonly />
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="xm" />
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="bjmc" />
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="ffList" property="gzsj"/>"
															style="width:95%"
															onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="ffList" property="cjje"/>"
															style="width:95%"
															onblur="changezje();if(this.value>300&&document.forms[0].gwxz.value == '001') {alert('金额超限,请填写相应备注!');document.all(this.id.replace('cjje','bz')).focus();}" />
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" name="bz${index}"
															value="<bean:write name="ffList" property="bz"/>"
															style="width:95%" />
													</div>
												</td>

											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr>
							<td height="27">
								<div align="center">
									金额总计（单位:元）
								</div>
							</td>
							<td>
								<div align="center" id="zje"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="if(parseFloat(document.forms[0].syjf.value)-parseFloat(document.getElementById('zje').innerText)<0){alert('经费不足，发放失败！');Close();} else refreshForm('workPaySave.do');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonSave">
						关 闭
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','酬金发放单','')">
						打印报表
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('操作成功！');
					Close();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<logic:present name="mes">
						<script>
				alert(document.getElementById('mes').value);
				Close();
				</script>
					</logic:present>
					<logic:notPresent name="mes">
						<script>
					alert("操作失败！");
				</script>
					</logic:notPresent>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

