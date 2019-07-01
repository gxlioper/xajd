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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">

</script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<body>
		<html:form action="/hzjy_cjlr" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：合作教育 - 合作教育安置 - 成绩录入
				</div>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="" />
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年度：
								<html:select property="nd" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;学年：
								<html:select property="xn" style="width:120px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq" style="width:50px">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:170px">
									<html:option value="" />
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</td>
							<td rowspan="2" width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/hzjy_cjlr.do?go=go')">
									查 询
								</button>
							</td>
							<td rowspan="2" width="10" align="center" valign="middle">
								<button class="button2" onclick="">
									&nbsp;&gt;&nbsp;&gt;&nbsp;
								</button>
							</td>
						</tr>
						<tr>
							<td>
								专业：
								<html:select property="zydm" style="width:170px">
									<html:option value="" />
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								&nbsp;班级：
								<html:select property="bjdm" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
								&nbsp;协调员代码：
								<input type="text" name="xtydm" id="xtydm" style="width: 100px"/>
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
						<%-- <font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font> --%>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle" align="center">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center">学号</td>
								<td align="center">姓名</td>
								<td align="center">学年</td>
								<td align="center">学期</td>
								<td align="center">是否补考</td>
								<td align="center">申请时间</td>
								<td align="center" style="width: 3%">成绩</td>
							</tr>
						</thead>
						<tbody id="rsTables">
						<logic:iterate name="rs" id="s">
						     <tr>
								<td align="left"><bean:write name="s" property="xhArray"/><html:hidden name="s" property="xhArray"/></td>
								<td align="left"><bean:write name="s" property="xmArray"/><html:hidden name="s" property="xmArray"/></td>
								<td align="left"><bean:write name="s" property="xnArray"/><html:hidden name="s" property="xnArray"/></td>
								<td align="left"><bean:write name="s" property="xqArray"/><html:hidden name="s" property="xqArray"/></td>
								<td align="left"><bean:write name="s" property="sfbk"/></td>
								<td align="left"><bean:write name="s" property="sqsj"/></td>
								<td align="left"><html:text styleId="text" name="s" property="cjArray" style="width: 50px" size="8" maxlength="5"/></td>
							 </tr>
						</logic:iterate>
						</tbody>
					</table>
					<p>&nbsp;</p>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" />
			</div>
			<logic:equal value="true" name="writeAble">
			<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2"
						onclick="cjSave('hzjycjSave.do');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
			</div>
			</logic:equal>	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
