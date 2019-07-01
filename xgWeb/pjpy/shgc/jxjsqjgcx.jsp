<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
function chkAssisOne() {
	if (curr_row == null) {
		return false;
	} else {
		url = curr_row.getElementsByTagName("input")[0].value;
		if (url==""){
			return false;
		}
		showTopWin(url, 750, 550);
		return true;
	}
}
    </script>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>


	<body>
		<html:form action="/stu_result_cdopen" method="post">
			<table width="100%" id="rsTable" class="tbstyle">
				<tr>
					<td align="center">
						<b>优秀学生奖学金</b>
					</td>
				</tr>
				<tr>
					<td>
						<logic:empty name="yxjxj">
							<br />
							<br />
							<p align="center">
								未找到任何记录！
							</p>
							<br />
						</logic:empty>
						<logic:notEmpty name="yxjxj">
							<fieldset>
								<legend>
									记录数：
									<bean:write name="yxjxjrsNum" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">提示：单击表头可以排序</font>
								</legend>
								<table width="100%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="yxjxjtopTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="yxjxj" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand"
											ondblclick="">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="0">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>

							</fieldset>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>自强奖学金</b>
					</td>
				</tr>
				<tr>
					<td>
						<logic:empty name="zqjxj">
							<br />
							<br />
							<p align="center">
								未找到任何记录！
							</p>
							<br />
						</logic:empty>
						<logic:notEmpty name="zqjxj">
							<fieldset>
								<legend>
									记录数：
									<bean:write name="zqjxjrsNum" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">提示：单击表头可以排序</font>
								</legend>
								<table width="100%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="zqjxjtopTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="zqjxj" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand"
											ondblclick="">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="0">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>

							</fieldset>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="center">
						<b>其它奖学金</b>
					</td>
				</tr>
				<tr>
					<td>
						<logic:empty name="qtjxj">
							<br />
							<br />
							<p align="center">
								未找到任何记录！
							</p>
							<br />
						</logic:empty>
						<logic:notEmpty name="qtjxj">
							<fieldset>
								<legend>
									记录数：
									<bean:write name="qtjxjrsNum" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">提示：单击表头可以排序</font>
								</legend>
								<table width="100%" id="qtjxjtopTr" class="tbstyle">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="qtjxjtopTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="qtjxj" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand"
											ondblclick="chkAssisOne();">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>

							</fieldset>
						</logic:notEmpty>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>

