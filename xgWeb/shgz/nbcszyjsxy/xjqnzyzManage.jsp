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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/stcygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="zyV" value=""/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：社会工作 - 信息维护 - 星级青年志愿者统计
					</div>
				</div>
				<fieldset>
					<legend>
						条 件
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
							    <td>
<%--							  志愿者星级：--%>
<%--								<html:select property="xjzyzlb" >--%>
<%--										<html:option value="yxjzyz">一星级青年志愿者</html:option>--%>
<%--										<html:option value="exjzyz">二星级青年志愿者</html:option>--%>
<%--										<html:option value="sxjzyz">三星级青年志愿者</html:option>--%>
<%--								</html:select>--%>
								年级：
								<html:select property="nj" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" />
								&nbsp;&nbsp;姓名：
								<html:text property="xm" />
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/stcygl.do?method=xjqnzyzManage&go=go')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						未找到任何记录！
						<br>
						<br>
						<br>
						<br>
						<br>
					</p>
					<div align="left" style="color: red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
					<ul style="color: blue" >
						<li>
						“一星级青年志愿者”应正式参加青年志愿者服务一年以上，志愿服务时间累积40小时以上
						</li>
						<li>
						“二星级青年志愿者”应正式参加青年志愿者服务一年以上，志愿服务时间累积100小时以上
						</li>
						<li>
						“三星级青年志愿者”应正式参加青年志愿者服务两年以上，志愿服务时间累积150小时以上
						</li>
					</ul>
					</div>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" >
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">	
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="result">
			</logic:notEmpty>
<%--			<logic:equal value="yes" name="writeAble">--%>
<%--						<div class="buttontool" id="btn"--%>
<%--							style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--							<button class="button2" onclick="dataExport()" style="width:80px">--%>
<%--								导出数据--%>
<%--							</button>--%>
<%--						</div>--%>
<%--				</logic:equal>				--%>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
