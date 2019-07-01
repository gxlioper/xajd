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
		function showData() {
			if (curr_row != null) {
				showTopWin('wjcf_nbcs_txcftzs.do?pkValue=' + curr_row.cells[0].getElementsByTagName('input')[0].value, 700, 550);
			}
		}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：违纪处分 - 下发处分拟通知 - 填写拟处分通知
				</div>
			</div>
			
			<logic:equal value="no" name="jswrite">
				<br/>
				<p align="center">
					<font color="red">您无权访问该页面，该页面只有学生用户才可以访问!</font>
				</p>
			</logic:equal>
			<logic:notEqual value="no" name="jswrite">
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left" nowrap="nowrap">
							学号：
							${userName }&nbsp;&nbsp;&nbsp;
							姓名：
							${userNameReal }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							拟处分通知次数：
							${rsNum }
							<input  id="search_go" onclick="refreshForm('wjcf_nbcs_writeNcftz.do')" style="display: none;"/>
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>操作</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr align="center" onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap ><bean:write name="v" /></td>
								</logic:iterate>
								<td>
									<a href="javascript:showData()" >填写通知书</a>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			</logic:notEqual>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>			
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
