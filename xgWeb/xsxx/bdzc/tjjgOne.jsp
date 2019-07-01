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
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
</head>

<body>
	<html:form action="/bdzcgl" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
		</div>
		<input type="hidden" name="pk" value="${pkValue }">
		<input type="hidden" id="url" name="url" value="/bdzcgl.do?method=lstdOne" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="queryequals_xn" value="${xn }">
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					年级：
				</td>
				<td align="left" width="34%">
					${classInfo.nj }
				</td>
				<td width="16%" align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td width="34%">
					${classInfo.xymc }
				</td>
			</tr>
			<tr>
				<td align="right">
					专业：
				</td>
				<td>
					${classInfo.zymc }
				</td>
				<td align="right">
					班级：
				</td>
				<td>
					${classInfo.bjmc }
				</td>
			</tr>
		</table>
		<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								学号：
								<html:text property="querylike_xh" maxlength="20" style="width:70px"></html:text>
								&nbsp;&nbsp;
								姓名：
								<html:text property="querylike_xm" maxlength="20" style="width:70px"></html:text>
								&nbsp;&nbsp;
								注册状态：
								<html:select property="queryequals_zczt">
									<html:option value=""></html:option>
									<html:options collection="zcztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
								
								<td width="10"  align="center" valign="middle">
								<button type="button" class="button2"  id="search_go"
									onclick="allNotEmpThenGo('/xgxt/bdzcgl.do?method=tjjgOne&doType=query')">
									查询
								</button>
							</td>
						</tr>
					</thead>
				</table>
				<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以排序</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=bdzcForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
				</div>
			</fieldset>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
					关&nbsp;&nbsp;闭
				</button>
		</div>
	</html:form>
</body>
</html:html>
