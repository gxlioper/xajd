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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		
	</script>
	<body>
		<html:form action="rcswKqglXskq" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 -- 考勤管理 -- 学生请假统计 		
				</div>
			</div>			
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
				</logic:empty>
			</logic:empty>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序;</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<logic:notEqual name="index" value="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" 
								 style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="9">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9" length="1">
									<td nowrap>
										<bean:write name="v" />天
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						关 闭
					</button>
					</div>
				
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
