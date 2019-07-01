<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base />
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<%
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","No-cache");
	response.setDateHeader("Expires",0);
	 %>
    <link rel="icon" href="images/icon.ico" type="image/x-icon" />
    <link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
											type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />											
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
  </head>
  
  <body>
      <html:form action="/hzjy_xmfb" name="ShgcForm">
      	<logic:empty name="rs">
      		<div align=center>
      			<p>有错误发生！
      		</div>
      	</logic:empty>
      	<logic:notEmpty name="rs">
				<input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="" />
				<fieldset>
					<legend>
						项目发布
					</legend>
					<table width="100%" class="tbstyle">
						<tr valign="middle">
							<td width="15%" align="right" nowrap>
								学年：
							</td>
							<td width="35%" align="left">
								<html:select name="rs" styleId="xn" property="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn"></html:options>
								</html:select>
							</td>
							<td width="15%" align="right" nowrap>
								学期：
							</td>
							<td width="35%" align="left">
								<html:select name="rs" styleId="xq" property="xq">
									<option value=""></option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								年度：
							</td>
							<td align="left">
								<html:select name="rs" styleId="nd" property="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd"></html:options>
								</html:select>
							</td>
							<td align="right" nowrap>
								合作项目：
							</td>
							<td align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									onchange="">
									<option value=""></option>
									<html:options collection="xmdmList" property="xmdm"
										labelProperty="xmmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr valign=middle>
							<td align=right>项目开始时间：</td>
							<td align=left><input type="text" onclick="return showCalendar('jzrq','y-mm-dd');" onblur="dateFormat();"></td>
							<td align=right>项目结束时间：</td>
							<td align=left><input type="text" onclick="return showCalendar('jzrq','y-mm-dd');" onblur="dateFormat();"></td>
						</tr>
						<tr valign="middle">
							<td align="right" nowrap>
								申请截至日期：
							</td>
							<td align="left">
								<html:text name="rs" property="jzrq" readonly="true"
									onclick="return showCalendar('jzrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " />
							</td>
							<td align="right" nowrap>
								
							</td>
							<td align="left">
								
							</td>
						</tr>
						
						<tr align="left" valign="top">
							<td colspan="4">
								项目信息：
								<br />
								<html:textarea name="rs" property="jlxx" rows="8"
									style="width:95% "></html:textarea>
							</td>
						</tr>
						<!-- <tr align="left" valign="top">
							<td colspan="4">
								交流生条件：
								<br />
								<html:textarea name="rs" property="jltj" rows="8"
									style="width:95% "></html:textarea>
							</td>
						</tr> -->
					</table>
					<div class="buttontool" id="btn" align="center">
						<button class="button2"
							onclick="if(checkXnNd('xn','nd'))dataDoSavePub('/xgxt/comm_pub.do?doType=save&tableName=view_dwjlxx&pkValue=','xn-nd-xq-xmdm');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
      </html:form>
  </body>
</html:html>
