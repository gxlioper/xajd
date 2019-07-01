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
function saveJxrc(url){
	refreshForm(url);
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 信息维护 - 军训日程安排
				</div>
			</div>
				<fieldset>
					<legend>
						军训日程安排
					</legend>
					<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" />"/>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								日期：
							</td>
							<td align="left">
							<html:text  property="jxrq" styleId="jxrq" name="rs"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jxrq','y-mm-dd');"/>
							</td>
							<td align="right">
								时间：
							</td>
							<td align="left">
								<html:select property="sj" name="rs" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="上午">上午</html:option>
									<html:option value="下午">下午</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								地点：
							</td>
							<td align="left">
								<html:text property="dd" name="rs"/>
							</td>
							<td align="right">
								组织者：
							</td>
							<td align="left">
								<html:text  property="zzz" name="rs"/>
							</td>
						</tr>
						<tr>
						<td align="right">
							内容：
						</td>
						<td colspan="3" align="left">
							<html:textarea  property="nr" name="rs" style="width:90%" rows="3"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="saveJxrc('jxgljz_xbmz.do?method=xbmzjxrcEdit&save=save');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
</html>
