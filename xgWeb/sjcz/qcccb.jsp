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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoadTest();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
								<input type="hidden" id="url" name="url" value="/sjcz/hcccb.jsp" />
								<fieldset>
									<legend>
										车次信息维护
									</legend>
									<table width="99%" class="tbstyle">
										<tr>
											<td align="right">
												<font color="red">*</font>车次：
											</td>
											<td align="left">
												<html:text name='rs' property="cc" style="width: 120px"
													styleId="cc" />
											</td>
											<td align="right">
												当前状态：
											</td>
											<td align="left">
												<html:select name="rs" property="dqzt" style="width:90px"
													styleId="dqzt">
													<html:option value="出售">出售</html:option>
													<html:option value="售完">售完</html:option>
												</html:select>
											</td>
										</tr>
										<tr>
											<td align="right">
												<font color="red">*</font>起点站：
											</td>
											<td align="left">
									        <html:select name="rs" property="qdz" style="width:120px">
									             <html:option value=""></html:option>
									             <html:options collection="czList" property="czdm"
										           labelProperty="czmc" />
								            </html:select>
											</td>
											<td align="right">
												<font color="red">*</font>终点站：
											</td>
											<td align="left">
										    <html:select name="rs" property="zdz" style="width:120px">
									             <html:option value=""></html:option>
									             <html:options collection="czList" property="czdm"
										            labelProperty="czmc" />
								                 </html:select>
											</td>
										</tr>
										<tr>
											<td align="right">
												<font color="red">*</font>开车时间：
											</td>
											<td align="left">
												<html:text name='rs' property="kcsj" style="width: 120px"
													styleId="kcsj" />
											</td>
											<td align="right">
												到达时间：
											</td>
											<td align="left">
												<html:text name='rs' property="ddsj" style="width: 120px"
													styleId="ddsj" />
											</td>
										</tr>
										<tr>											
											<td align="right">
												运行时间：
											</td>
											<td align="left">
												<html:text name='rs' property="yxsj" style="width: 90px"
													styleId="yxsj" />(小时)
											</td>
											<td align="right">
												<font color="red">*</font>票价：
											</td>
											<td align="left">
												<html:text name='rs' property="pj" style="width: 90px"
													styleId="pj" />(元)
											</td>
										</tr>
										<tr>
											<td align="right">
												停靠站：
											</td>
											<td colspan="3">
												<html:textarea name='rs' property='tkz' style="width:95%"
													rows='4' />
											</td>
										</tr>
									</table>
								</fieldset>
								<div class="buttontool">
									<button type="button" class="button2" onclick="dataCanModi(true)"
										style="width:80px" id="buttonModi">
										修 改
									</button>
									<button type="button" class="button2"
										onclick="dataDoSave('cc-qdz-zdz-kcsj-pj')"
										style="width:80px" id="buttonSave">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
										id="buttonClose">
										关 闭
									</button>
								</div>
							</logic:notEmpty>
						</div>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
