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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/prise_conf_rs" method="post">
			<div id="title">
				<div id="title_l"></div>
				<div id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
				<div id="title_r"></div>
			</div>
			<div id="main" style="heigth:100px;">
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="bmlb" name="bmlb" value="xy" />
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<table width="100%">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年度：
								<html:select property="nd" style="width:90px" disabled="true"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="listPriseConf('/xgxt/prise_conf_rs.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<span id="dispZy" style="display:none"> 专业： <html:select
										property="zydm" style="width:230px;" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select> </span>
								<span id="dispXy"> <bean:message key="lable.xsgzyxpzxy" />： <html:select property="xydm"
										style="width:230px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select> </span> &nbsp;&nbsp;奖学金：
								<html:select property="xmdm" style="width:200px" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="chgMode" disabled="disabled" type="checkbox"
									style="border:0px" onclick="showZyList(this)" />
								按专业设置
							</td>
						</tr>
					</thead>
				</table>
				<div id="result">
					<div class="searchcontent">
						<logic:empty name="rs">
							<br />
							<br />
							<p align="center">
								未找到任何记录！
							</p>
						</logic:empty>
						<logic:notEmpty name="rs">
    记录数：<bean:write name="rsNum" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <font color="blue">提示：双击一行可以调整人数；单击表头可以排序</font>
							<table width="99%" id="rsTable">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="if(curr_row.cells[6].innerText==''){alert('尚未生成 建议人数，不能调整人数！');return false;}unableAllSel('');showTopWin('/xgxt/sjcz/prise_conf_one.jsp',450,400);">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</logic:notEmpty>
						<br />
					</div>
				</div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div id="button">
						<button type="button" class="button2" onclick="priseDataInit()">
							初始化数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="showTopWin('/xgxt/sjcz/chg_prise_xn.jsp',300,200)">
							调整学年
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="chkPriseBat()">
							批量设置
						</button>
					</div>
				</logic:equal>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
