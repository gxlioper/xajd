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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript">
    	
    </script>
	<body >
		<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 荣誉称号审核
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<logic:equal value="xy" name="userType">
					<thead>
					<tr align="center">
						<td height="22" colspan="11">
							详细信息
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="center" style="" nowrap="nowrap">
						行号
					</td>
					<td height="22" align="center" style=""nowrap="nowrap">
						学年
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学期
					</td>
					<td height="22" align="center" nowrap="nowrap">
						班级
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学号
					</td>
					<td height="22" align="center" nowrap="nowrap">
						姓名
					</td>
					<td height="22" align="center" nowrap="nowrap">
						荣誉称号
					</td>
					<td height="22" align="center" nowrap="nowrap">
						综测总成绩班级排名
					</td>
					<td height="22" align="center" nowrap="nowrap">
						辅导员审核
					</td>
					<td height="22" align="center" nowrap="nowrap">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学校审核
					</td>
				</tr>
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" >
								<logic:iterate id="v" name="s" >
									<td align="center" nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="rs">
					<tr>
					<td height="22" align="center" style="" colspan="11">
						暂无记录
					</td>
					</tr>
				</logic:empty>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<thead>
					<tr align="center">
						<td height="22" colspan="10">
							详细信息
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="center" nowrap="nowrap">
						行号
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学年
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学期
					</td>
					<td height="22" align="center" nowrap="nowrap">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td height="22" align="center" nowrap="nowrap">
						班级
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学号
					</td>
					<td height="22" align="center" nowrap="nowrap">
						姓名
					</td>
					<td height="22" align="center" nowrap="nowrap">
						荣誉称号
					</td>
					<td height="22" align="center" nowrap="nowrap">
						综测总成绩班级排名
					</td>
					<td height="22" align="center" nowrap="nowrap">
						学校审核
					</td>
				</tr>
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" >
								<logic:iterate id="v" name="s" >
									<td align="center" nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<logic:empty name="rs">
					<tr>
					<td height="22" align="center" style="" colspan="10">
						暂无记录
					</td>
					</tr>
				</logic:empty>
				</logic:notEqual>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败!" + $('message').value);
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
