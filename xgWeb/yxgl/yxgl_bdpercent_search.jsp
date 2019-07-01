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
		<base target="_self"/>
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
	<body onload="getNowDate();">
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/yxglFun.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<html:form action="yxgl_xsgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：迎新管理 － 新生管理 － 报到率查询
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="newdate" style="color:#BB44BB" ></span>
				</div>
			</div>
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
					<%-- 报到率查询  针对河南工业大学--%>
					<logic:present name="percentOfBdSearch">
						<!-- 全校范围统计 -->
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)">统计所有部门</td>
									<td onclick="tableSort(this)">应报到人数</td>
									<td onclick="tableSort(this)">实报到人数</td>
									<td onclick="tableSort(this)">报到率(%)</td>
									<td  style="width:25%">打印预览</td>
								</tr>
							</thead>
								<tr style="cursor:hand" onclick="rowOnClick(this)">
										<td>全校统计</td>
										<td><bean:write name="rs" property="totalNum"/></td>
										<td><bean:write name="rs" property="OkNum"/></td>
										<td><bean:write name="rs" property="percentBd"/></td>
										<td>
										<select name="tag" id="tag" style="width:110px">
											<option value="">--请选择--</option>
											<option value="ybd">已报到学生数据</option>
											<option value="wbd">未报到学生数据</option>
										</select>&nbsp;&nbsp;
										<a href="javascript:getTotalStuData('print');" style="font-weight:bold;">打印</a>&nbsp;&nbsp;&nbsp;
										<a href="javascript:getTotalStuData('exp');" style="font-weight:bold;">导出</a>
										</td>
								</tr>
						</table>
						<br/>
						
						<!-- 各个部门统计 -->
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)">部门名称</td>
									<td onclick="tableSort(this)">应报到人数</td>
									<td onclick="tableSort(this)">实报到人数</td>
									<td onclick="tableSort(this)">报到率(%)</td>
									<td onclick="tableSort(this)" style="width:25%">打印预览</td>
								</tr>
							</thead>
							<logic:iterate name="rs2" id="s">	
								<tr style="cursor:hand" onclick="rowOnClick(this)">
									<td>
									<input type="hidden" name="xydm" value="<bean:write name="s" property="xydm"/>"/>		
									<bean:write name="s" property="xymc"/></td>
									<td><bean:write name="s" property="totalNum"/></td>
									<td><bean:write name="s" property="OkNum"/></td>
									<td><bean:write name="s" property="percentBd"/></td>
									<td>
									<select name="tag" id="tag" style="width:110px">
										<option value="">--请选择--</option>
										<option value="ybd">已报到学生数据</option>
										<option value="wbd">未报到学生数据</option>
									</select>&nbsp;&nbsp;
									<a href="javascript:getStuDataByXy('print');" style="font-weight:bold;">打印</a>&nbsp;&nbsp;&nbsp;
									<a href="javascript:getStuDataByXy('exp');" style="font-weight:bold;">导出</a>
									</td>
								</tr>
							</logic:iterate>	
						</table>
						<br/>
					</logic:present>
					</fieldset>
				</logic:notEmpty>	
		</html:form>
	</body>
</html>


