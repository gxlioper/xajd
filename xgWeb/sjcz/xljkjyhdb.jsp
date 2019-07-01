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
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />

				<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/xlytqkb.jsp" />
				<fieldset>
					<legend>
						工作动态
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									心理健康教育活动 (时间格式：00:00)
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>主题：
								<html:hidden name='rs' property="id" styleId="id" />
							</td>
							<td align="left">
								<html:text name='rs' property="zt" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:select name="rs" property="xy" style="width:180px"
									styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								教育形式：
							</td>
							<td align="left">
								<html:text name='rs' property="jyxs" styleId="jyxs" />
							</td>
							<td align="right">
								地点：
							</td>
							<td align="left">
								<html:text name='rs' property="dd" styleId="dd" />
							</td>
						</tr>
						<tr>
							<td align="right">
								活动日期：
							</td>
							<td align="left">
								<html:text name='rs' property="hdrq" styleId="hdrq"
									onclick="return showCalendar('hdrq','y-mm-dd')" />
							</td>
							<td align="right">
								开始时间：
							</td>
							<td align="left">
								<html:select name='rs' property="kssjs" styleId="jssj">
								<html:option value="0"></html:option>
								<html:option value="1"></html:option>
								<html:option value="2"></html:option>
								<html:option value="3"></html:option>
								<html:option value="4"></html:option>
								<html:option value="5"></html:option>
								<html:option value="6"></html:option>
								<html:option value="7"></html:option>
								<html:option value="8"></html:option>
								<html:option value="9"></html:option>
								<html:option value="10"></html:option>
								<html:option value="11"></html:option>
								<html:option value="12"></html:option>
								<html:option value="13"></html:option>
								<html:option value="14"></html:option>
								<html:option value="15"></html:option>
								<html:option value="16"></html:option>
								<html:option value="17"></html:option>
								<html:option value="18"></html:option>
								<html:option value="19"></html:option>
								<html:option value="20"></html:option>
								<html:option value="21"></html:option>
								<html:option value="22"></html:option>
								<html:option value="23"></html:option>
								</html:select>时
								<html:select name='rs' property="kssjf" styleId="jssj">
								<html:option value="0"></html:option>
								<html:option value="10"></html:option>
								<html:option value="20"></html:option>
								<html:option value="30"></html:option>
								<html:option value="40"></html:option>
								<html:option value="50"></html:option>
								</html:select>分
							</td>
						</tr>
						<tr>
							<td>
								结束时间：
							</td>
							<td>
								<html:select name='rs' property="jssjs" styleId="jssj">
								<html:option value="0"></html:option>
								<html:option value="1"></html:option>
								<html:option value="2"></html:option>
								<html:option value="3"></html:option>
								<html:option value="4"></html:option>
								<html:option value="5"></html:option>
								<html:option value="6"></html:option>
								<html:option value="7"></html:option>
								<html:option value="8"></html:option>
								<html:option value="9"></html:option>
								<html:option value="10"></html:option>
								<html:option value="11"></html:option>
								<html:option value="12"></html:option>
								<html:option value="13"></html:option>
								<html:option value="14"></html:option>
								<html:option value="15"></html:option>
								<html:option value="16"></html:option>
								<html:option value="17"></html:option>
								<html:option value="18"></html:option>
								<html:option value="19"></html:option>
								<html:option value="20"></html:option>
								<html:option value="21"></html:option>
								<html:option value="22"></html:option>
								<html:option value="23"></html:option>
								</html:select>时
								<html:select name='rs' property="jssjf" styleId="jssj">
								<html:option value="0"></html:option>
								<html:option value="10"></html:option>
								<html:option value="20"></html:option>
								<html:option value="30"></html:option>
								<html:option value="40"></html:option>
								<html:option value="50"></html:option>
								</html:select>分
							</td>
							<td align="right">
								主持人：
							</td>
							<td align="left">
								<html:text name='rs' property="zcr" styleId="zcr" />
							</td>
						</tr>
						<tr>
							<td align="right">
								参与学生：
							</td>
							<td align="left">
								<html:text name='rs' property="cyxs" styleId="cyxs" />
							</td>
							<td align="right">
								参与学生数：
							</td>
							<td align="left">
								<html:text name='rs' property="cyrs" styleId="cyrs" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								活
								<br>


								动
								<br>


								记
								<br>


								录
								<br>
								：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='hdjl' style="width:99%"
									rows='15' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								活动效果:
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='hdxg' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width: 80px" id="buttonModi">
							修 改
						</button>
						<button type="button" class="button2" onclick="dataDoSave('zt')"
							style="width: 80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width: 80px"
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
