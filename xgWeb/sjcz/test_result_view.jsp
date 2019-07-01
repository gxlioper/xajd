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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />	
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				心理健康 - 心理测试 - 在线测试 - 试题分析
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
				<table width="100%" class="tbstyle">
					<tr>
						<td align="center">
							<B><font size="5">${rs.sjm}</font></B>
						</td>
					</tr>
					<tr>
						<td align="left">
							${rs.xh}&nbsp;&nbsp;${rs.xm}&nbsp;&nbsp;${rs.bjmc}&nbsp;&nbsp;<br>
							考试时间:${rs.dtsj}&nbsp;&nbsp;考试用时:${rs.dtys}&nbsp;&nbsp;<font color="red">成绩:${rs.cj}</font>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									老师评语：
								</legend>
								${rs.bz}
							</fieldset>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									试卷说明：
								</legend>
								${rs.sjsm}
							</fieldset>
						</td>
					</tr>
					<tr>
						<td align="left">
							<fieldset>
								<legend>
									共${rs.stsl}题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:present name="lxList">
										<logic:iterate id="lx" name="lxList">
											${lx.stlxmc}:${lx.lxsl}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:iterate>
									</logic:present>
									时间限定:${rs.sjxd}(分钟)
								</legend>
								<logic:empty name="stxxList">暂无试题!</logic:empty>
								<logic:notEmpty name="stxxList">
									<table width="100%" class="tbstyle" id="tp">
									<% String i= "0"; %>
										<logic:iterate id="stxx" name="stxxList">
	
											<tr>
											<%-- 试题信息--%>
											<logic:iterate id="st" name="stList" offset="<%=i%>" length="1">
									             <td align="left" id="${st.stlsh}" width="100%">
													<B>${st.stxh}.</B>&nbsp;
													${st.stnr}&nbsp;&nbsp;[${st.stlxmc}&nbsp;&nbsp;${st.stndjbmc}]&nbsp;&nbsp;${st.stfz}																									
												</td>
											</logic:iterate>								
											</tr>
											<tr>
												<td>
													<table width="100%" class="tbstyle">
													<bean:define id="xxL" name="stxx" property="xxList" ></bean:define>
														<logic:iterate id="xx" name="xxL">
														<tr>
															<td>${xx.xxxh}&nbsp;${xx.xxnr}&nbsp;&nbsp;</td>
															<td>${xx.xxfz}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
														</tr>
														</logic:iterate>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<font color="red">
													<logic:iterate id="st" name="stList" offset="<%=i%>" length="1">
													正确答案:${st.stda}&nbsp;&nbsp;您的答题答案:${st.dtda}&nbsp;&nbsp;得分:${st.dtfz}<br>
													解释:<br>${st.stdajs}
													</logic:iterate>
													</font>
												</td>
											</tr>
											<% i = (Integer.parseInt(i) + 1) + ""; %>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</fieldset>
						</td>
					</tr>
				</table>
				<br />
				<div class="buttontool">
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		
	</body>
</html>
