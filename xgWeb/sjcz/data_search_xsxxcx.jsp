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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');removeXnXq()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
function query(){
		var act = document.getElementById('act').value;
		var l = "/xgxt/data_search.do?act=" + act;
		allNotEmpThenGo(l);
	}
	
	function onCh(){
		var act = document.getElementById('act').value;
		var l = "/xgxt/data_search.do?act=" + act;
		refreshForm(l);
	}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<logic:present name="showhzyjx">
						<table width="100%" class="tbstyle">
							<html:radio property="grhj" value="grhj" styleId="grhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">个人获奖</html:radio>
							<html:radio property="grhj" value="bjhj" styleId="bjhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">班级获奖</html:radio>
							<html:radio property="grhj" value="yxhj" styleId="yxhj"
								onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')"><bean:message key="lable.xsgzyxpzxy" />获奖</html:radio>
						</table>
					</logic:present>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="onCh();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="onCh();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:120px"></html:text>
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" id="search_go"
										onclick="query();">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:notPresent name="xsjxjb">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</logic:notPresent>
									<logic:present name="xsjxjb">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</logic:present>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
<%--								<logic:notPresent name="xsjxjb">--%>
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="viewMore('view')">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
<%--								</logic:notPresent>--%>
<%--								<logic:present name="xsjxjb">--%>
<%--									<tr onclick="rowOnClick(this);" style="cursor:hand"--%>
<%--										ondblclick="viewMore('modi')">--%>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--												<input type="hidden" value="<bean:write name="v"/>" />--%>
<%--											</logic:iterate>--%>
<%--											<logic:iterate id="v" name="s" offset="1" length="1">--%>
<%--												<input type="hidden" value="<bean:write name="v"/>" />--%>
<%--											</logic:iterate>--%>
<%--											<logic:iterate id="v" name="s" offset="2" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</td>--%>
<%--										<logic:iterate id="v" name="s" offset="3">--%>
<%--											<td nowrap>--%>
<%--												<bean:write name="v" />--%>
<%--											</td>--%>
<%--										</logic:iterate>--%>
<%--									</tr>--%>
<%--								</logic:present>--%>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:present name="showzdjs">
								<button type="button" class="button2"
									onclick="AutoAccountCj('/xgxt/AutoAccount.do')"
									style="width:80px">
									自动计算
								</button>
							</logic:present>
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:present name="xsjxjb">
									<button type="button" class="button2" onclick="viewMore2('modi')"
										style="width:80px">
										修 改
									</button>
								</logic:present>
								<logic:notPresent name="xsjxjb">
									<button type="button" class="button2" onclick="viewMore('modi')"
										style="width:80px">
										修 改
									</button>
								</logic:notPresent>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
										onclick="impAndChkData();"
										style="width:80px">
										导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
									导出数据
								</button>
						</div>
					</center>
				</logic:equal>
				<div id="tmpdiv"></div>
				<logic:present name="autoCj">
					<logic:equal name="autoCj" value="ok">
						<script language="javascript">
      						alert("自动计算完成！");
	  					</script>
					</logic:equal>
					<logic:equal name="autoCj" value="no">
						<script language="javascript">
	  						alert("自动计算失败! ");
	  					</script>
					</logic:equal>
				</logic:present>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

