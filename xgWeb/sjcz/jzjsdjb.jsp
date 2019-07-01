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
	<body onload="checkWinType();dataManLoad();">
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
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
					   alert("您输入的学号无效!");
					</script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="" />
				<input type="hidden" id="url" name="url"
					value="/sjcz/xlytqkb.jsp" />
				<fieldset>
					<legend>
						工作动态
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									兼职教师简历
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>姓 名：
								<html:hidden name='rs' property="jzid" styleId="jzid" />
							</td>
							<td align="left">
								<html:text name='rs' property="xm" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								
							</td>
							<td align="right">
								性  别：
							</td>
							<td align="left">
								<html:select name='rs' property="xb" styleId="xm">
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								出生年月：
							</td>
							<td align="left">
								<html:text name='rs' property="csny" styleId="csny"
							onclick="return showCalendar('csny','y-mm-dd');" />
							</td>
							<td align="right">
								所在<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:select name="rs" property="xymc" style="width:180px"
														styleId="xy">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xymc"/>
													</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								办公室电话：
							</td>
							<td align="left">
								<html:text name='rs' property="bgsdh" styleId="xymc" />
							</td>
							<td align="right"> 
								职务职称： 
							</td>
							<td align="left">
								<html:text name='rs' property="zwzc" styleId="ssbh" />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								手机号码： 
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" />
							</td>
							<td align="right"> 
								邮箱： 
							</td>
							<td align="left">
								<html:text name='rs' property="dzyx" styleId="lxdzxx" />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								其他联系方式： 
							</td>
							<td align="left">
								<html:text name='rs' property="qtlxdh" styleId="chf" />
							</td>
						</tr>
						<tr align="left">
							<td align="center">
							工<br>


							作<br>


							简<br>


							历<br>
							 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='gzjl' style="width:99%"
									rows='15' />
							</td>
						</tr>
						<tr align="left">
							<td align="center">备注: 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width:80px" id="buttonModi">
							修 改
						</button>
						<button type="button" class="button2" onclick="dataDoSave('xh')"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
