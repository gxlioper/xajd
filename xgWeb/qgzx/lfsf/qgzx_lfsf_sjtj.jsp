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
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<html:form action="/qgzxLogic" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 考核 - 数据统计
				</div>
			</div>
			<fieldset>
				<legend>
					指定参数
				</legend>
				<table width="100%" class="tbstyle">
				<thead>
				<tr>
				<td>
					学年：
					<html:select property="xn" style="width:120px">
						<html:option value=""></html:option>
						<html:options collection="xnList" property="xn" labelProperty="xn"/>					
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					学期：
					<html:select property="xq" style="width:120px">
						<html:option value=""></html:option>	
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>					
					</html:select>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年级：
					<html:select property="nj" style="width:120px">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj" labelProperty="nj"/>						
					</html:select>
					</td>
					</tr>
					<tr>
					<td>
					<bean:message key="lable.xsgzyxpzxy" />：
					<html:select property="xydm" style="width:120px">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc"/>						
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月份：
					<html:select property="yf" style="width:120px" styleId="yf">
						<html:option value=""></html:option>
						<html:options collection="yfList" property="yf" labelProperty="yf"/>						
					</html:select>
					</td>
				</tr>
				</thead>
				</table>
			</fieldset>

			<logic:empty name="list">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="list">
				<fieldset>
					<legend>
						<font color="blue">勤工助学统计表</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr>
							<td colspan="8" align="center">
								${xxmc}勤工助学统计表
							</td>
						</tr>
					</thead>
						<logic:iterate id="rs" name="list" offset="0">
							<thead>
								<tr>
									<logic:iterate id="s" name="rs" offset="0" length="1">
									<td colspan="8">
										<logic:iterate id="v" name="s" offset="0" length="1" >
											<div align="center">
												<span id="xyyf">
													<bean:write name="yf" />
												</span>
												<bean:write name="v" />勤工助学报表												
											</div>
										</logic:iterate>
									</td>
								</logic:iterate>
								</tr>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tr style="cursor:hand">
								<logic:iterate id="s" name="rs" offset="0">
									<tr onclick="rowMoreClick(this,'',event);">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<div align="center">
													<bean:write name="v" />
												</div>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<logic:present name="writeAble">
			<logic:equal value="yes" name="writeAble">
				<div id="btn" class="buttontool" style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="refreshForm('/xgxt/qgzxLogic.do?method=dataStatistics&&doType=go'); BatAlert.showTips('正在统计，请稍等...');" style="width:80px">
							统 计
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="expAppTab('rsTable','','')"
							style="width:80px">
							打 印
						</button>
				</div>
			</logic:equal>
			</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>