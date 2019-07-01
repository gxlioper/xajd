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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
				<span id="tipFollow"> 勤工助学 - 酬金发放 - 酬金发放审核 - </span>审核</div>
			</div>
			<input type="hidden" id="gwdm" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="rs" property="gwsbsj"/>" />
			<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="rs" property="gwdm"/><bean:write name="rs" property="gwsbsj"/>"/>
			<input type="hidden" id="jybcbz" name="jybcbz" value="<bean:write name="rs" property="jybcbz"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<fieldset>
				<legend>
					<logic:notPresent name="showbjlh">
						学年:<bean:write name="rs" property="xn" />
						&nbsp;&nbsp;&nbsp;
						年度:<bean:write name="rs" property="nd" />
						&nbsp;&nbsp;&nbsp;
						学期:<bean:write name="rs" property="xq" />
						&nbsp;&nbsp;&nbsp;
						月份:<bean:write name="rs" property="yf" />
					</logic:notPresent>
					<logic:present name="showbjlh">
						年度:<bean:write name="rs" property="xn" />&nbsp;&nbsp;&nbsp;学期:<bean:write
							name="rs" property="xq" />&nbsp;&nbsp;&nbsp;月份:<bean:write
							name="rs" property="yf" />
					</logic:present>
				</legend>
				<table width="100%" class="tbstyle" id="rsT">
					<tr>
						<td width="15%" height="27" align="center">
							用人单位
						</td>
						<td width="85%" align="center">
							<bean:write name="rs" property="yrdwmc" />
						</td>
					</tr>
					<tr>
						<td width="15%" height="27" align="center">
							所属部门
						</td>
						<td width="85%" align="center">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							工作内容
						</td>
						<td align="center">
							<bean:write name="rs" property="gznr" />
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							岗位性质
						</td>
						<td align="center">
							<bean:write name="rs" property="gwxzmc" />
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							计酬标准
						</td>
						<td align="center">
							<bean:write name="rs" property="spbcbz" />
							<input type="hidden" id="jybcbz" value="<bean:write name="rs" property="spbcbz" />"/>
							<bean:write name="rs" property="jcfs" />
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							剩余经费
						</td>
						<td align="center">
							<bean:write name="rs" property="syjf" />
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
												<bean:write name="rs" property="gzsjdw" />
												)
											</div>
										</td>
										<td width="8%">
											<div align="center">
												金额
											</div>
										</td>
										<logic:present name="showjsxx">
											<td width="10%">
												<div align="center">
													签字
												</div>
											</td>
										</logic:present>
										<td width="16%">
											<div align="center">
												备注
											</div>
										</td>										
									</tr>
								</thead>
								<logic:empty name="stuList">
									<tr>
										<td colspan="6">
											无记录!
										</td>
									</tr>
								</logic:empty>
								<logic:notEmpty name="stuList">
									<logic:iterate name="stuList" id="stuList" indexId="index">
										<tr>
											<td>
												<div align="center">
													<input type="text" name="xh${index}"
														value="<bean:write name="stuList" property="xh"/>"
														style="width:95%" readonly />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="stuList" property="xm" />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="stuList" property="bjmc" />
												</div>
											</td>
											<logic:present name="showshgc">
												<td>
													<div align="center">
														<bean:write name="stuList" property="kh" />
													</div>
												</td>
											</logic:present>
											<logic:present name="bjlh">
												<td>
													<div align="center">
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="stuList" property="gzsj"/>"
															style="width:95%"															
															readonly="readonly" />
													</div>
												</td>
											</logic:present>
											<logic:notPresent name="bjlh">
												<td>
													<div align="center">
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="stuList" property="gzsj"/>"
															style="width:95%"
															readonly="readonly" />
													</div>
												</td>
											</logic:notPresent>
											<logic:present name="bjlh">
												<td>
													<div align="center">
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="stuList" property="cjje"/>"
															style="width:95%"															
															readonly="readonly"/>
													</div>
												</td>
											</logic:present>
											<logic:notPresent name="bjlh">
											<logic:equal value="10497" name="xxdm">
											<td>
													<div align="center">
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="stuList" property="cjje"/>"
															style="width:95%" readonly="readonly"
															/>
													</div>
												</td>
											</logic:equal>
											<logic:notEqual value="10497" name="xxdm">
												<td>
													<div align="center">
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="stuList" property="cjje"/>"
															style="width:95%"
															readonly="readonly"/>
													</div>
												</td>
											</logic:notEqual>
											</logic:notPresent>
											<logic:present name="showjsxx">
												<td>
													<div align="center">
														&nbsp;
													</div>
												</td>
											</logic:present>
											<td>
												<div align="center">
													<input type="text" name="bz${index}"
														value="<bean:write name="stuList" property="bz"/>"
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
						<td height="22">
							<div align="center">
								审核结果：
							</div>
						</td>
						<td>
							<html:select property="xxsh" name="rs" styleId="xxsh">
                                  <html:option value="未审核">未审核</html:option>
                                  <html:option value="通过">通过</html:option>
                                  <html:option value="不通过">不通过</html:option>
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">
				<button type="button" class="button2"
					onclick="refreshForm('whlggwgl.do?method=saveAudit')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				
				<button type="button" class="button2"
					onclick="Close();return false;"
					style="width:80px" id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:present name="result">
			<logic:equal name="result" value="true">
					<script>
				    alert("操作成功！");
				    Close();
				    window.dialogArguments.document.getElementById('search_go').click();
				    </script>
			</logic:equal>
			<logic:equal name="result" value="false">
					<script>
				    alert("操作失败！");
				    </script>
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

