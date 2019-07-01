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
		<base target="_self">
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xljkFunction.js"></script>
	<body>
		<html:form action="/xljk_zgmsxy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：心理健康 - 心理咨询 - 咨询师资源维护
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="bh" name="bh"
				value="<bean:write name="form" property="bh" scope="request"/>" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="refreshPage" name="refreshPage"
				onclick="refreshForm('xljk_zgmsxy.do?method=zxszywh&bh=<bean:write name="form" property="bh"/>')" />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="${tit.en}" onclick="tableSort(this)" nowrap>
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								ondblclick="viewOneData('xljk_zgmsxy.do?method=zxszypre&doType=view',300,200)">
								<td align=center>
									<input type="checkbox" id="pk" name="pk"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td align=center>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble" scope="request">
				<div id="toolTipLayer" style="position:absolute;" /></div>
				<center>
					<div class="buttontool" id="btn" style="top:400px">
						<button class="button2"
							onclick="showTopWin('/xgxt/xljk_zgmsxy.do?method=zxszypre&bh=<bean:write name="form" property="bh"/>',300,200);"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" style="width:80px"
							onclick="modi('/xgxt/xljk_zgmsxy.do?method=zxszypre&doType=modi&bh=<bean:write name="form" property="bh"/>',300,200)">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="del('/xgxt/xljk_zgmsxy.do?method=zxszywh&doType=del&bh=<bean:write name="form" property="bh"/>')"
							style="width:80px">
							删 除
						</button>
					</div>
				</center>
			</logic:equal>
		</html:form>
		<%--<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	--%>
	</body>
</html>
