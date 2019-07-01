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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
	function expStuinfoByBj(){
	document.forms[0].target = "_black";
	var bjmc = document.getElementById("bjmc").value;
	refreshForm('stuGroupInfo.do?method=expData&bjmc=' + bjmc);
	}
	
	function goBack(){
		refreshForm('stu_group_info.do?go=go');
	}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/stuGroupInfo" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
			<input type="hidden" name="bjmc" id="bjmc" value="<bean:write name="bjmc" scope="request"/>"/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 学生集体信息 - 班级学生信息
					</div>
				</div>
				<fieldset>
					<legend>
						班级学生信息
					</legend>
					<table width="100%" class="tbstyle">
						<thead>							
							<tr>								
								<td align="center">
									<bean:write name="bjmc"/>班学生信息
								</td>
							</tr>	
						</thead>
					</table>
				</fieldset>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="stuInfoWin(this)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="expStuinfoByBj()"
								style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<button type="button" class="button2" onclick=" expTab('rsTable','<bean:write name="bjmc"/>班学生信息','')">
								打印列表
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<button type="button" class="button2" onclick="goBack();" style="width:80px">
								返 回
							</button>							
						</div>
			</html:form>
			<script type="text/javascript" src="js/bottomButton.js"></script>
		</center>
	</body>
</html>
									