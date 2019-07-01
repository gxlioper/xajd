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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript">
		function view(url) {
			if (curr_row == null || curr_row == '') {
				alert("请双击要显示的行!");
				return false;
			}
			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;	
			showTopWin(url + pk,600,500);
		}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpyycsfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 信息维护 - 综合素质测评
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left" nowrap="nowrap">
							学号：
							${userName }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							${userNameReal }
						</td>
					</tr>
				</thead>
			</table>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
									<td></td>
									<td onclick="tableSort(this)" nowrap>
										学年
									</td>
									<td onclick="tableSort(this)" nowrap>
										学期
									</td>
									<td onclick="tableSort(this)" nowrap>
										平时考核成绩
									</td>
									<td onclick="tableSort(this)" nowrap>
										学业考核成绩
									</td>
									<td onclick="tableSort(this)" nowrap>
										阶段考核成绩
									</td>
									<td onclick="tableSort(this)" nowrap>
										综合素质测评总分
									</td>
									<td onclick="tableSort(this)" nowrap>
										综合素质测评总分班级排名
									</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr align="center" onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="view('pjpy_ycsf_stuZhszcpView.do?pk=')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap><bean:write name="v" /></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
