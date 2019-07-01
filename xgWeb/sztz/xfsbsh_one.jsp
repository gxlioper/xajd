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
	<body onload="readOnly();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/sztz_xfsb_sh?act=sbsh" method="post">		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 审核审核与考评 - 审核 - 单个审核
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="xn||nd||xq||xmdm||xh" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble" scope="request"/>"/>								
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个学分申报审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
					学号：
					</td>
					<td align="left">
						<bean:write name="xh" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="nd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="xm" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="xb" />
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name='xq' />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="nj" />
					</td>
					<td align="right">
						拓展活动(项目)：
					</td>
					<td align="left">
						<bean:write name="xmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="xymc" />
					</td>
					<td align="right">
						 所属科目：
					</td> 
					<td align="left">
						<bean:write name="kmm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="zymc" />
					</td>
					<td align="right">
						 所获学分：
					</td>
					<td align="left">
					    <input type="hidden" id="xf" name="xf" value="<bean:write name="xf" />" /> 
						<bean:write name="xf" />
                  </td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="bjmc" />
					</td>
					<td align="right">
						拓展活动获奖类别：
					</td>
					<td align="left">
					    <input type="hidden" id="jbdm" name="jbdm" value="<bean:write name="jbdm" />" /> 
						<bean:write name="jbmc" />
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						申报时间：
					</td>
					<td align="left">
						<bean:write name="sbsj" />
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						活动级别：
					</td>
					<td align="left">
						<bean:write name="jb" />
					</td>	
					<td align="right">
						参加性质：
					</td>
					<td align="left">
						<bean:write name="xz" />
					</td>	
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						申报理由：
					</td>
					<td align="left"colspan="3" style="width:88%">
						<bean:write name="sqly" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						成果：
					</td>
					<td align="left"colspan="3">
						<bean:write name="cg" />
					</td>
					
				</tr>
     		</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/sztz_xfsb_sh.do?act=save');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		</html:form>
	</body>
</html>
