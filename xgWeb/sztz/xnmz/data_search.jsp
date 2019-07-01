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
	<body onload="xyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		
    <html:form action="/tz_dataSearch.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
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
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style="width:100px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<logic:present name="isdisabled">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
									<html:select property="xn" style="width:100px" styleId="xn"
										onchange="refresh('/xgxt/tz_dataSearch.do')" disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>	
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:100px" styleId="xq"
									    onchange="refresh('/xgxt/tz_dataSearch.do')" disabled="true">										
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>																												
									</logic:present>
									<logic:notPresent name="isdisabled">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
									<html:select property="xn" style="width:100px" styleId="xn"
										onchange="refresh('/xgxt/tz_dataSearch.do')">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>	
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:100px" styleId="xq"
									    onchange="refresh('/xgxt/tz_dataSearch.do')">										
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>																		
									</logic:notPresent>								
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;素质拓展班级：
									<html:select property="dm" style="width:150px" styleId="dm">
										<html:option value=""></html:option>
										<html:options collection="tzBjList" property="dm"
											labelProperty="mc" />
									</html:select>									
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/tz_dataSearch.do')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>									
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									班级：
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px"></html:text>
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
						</legend>
						<div
							style="overflow-x:auto;overflow-y:auto;width:800px;height:330px;">
							<table width="100%" id="rsTab" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:equal value="true" name="isSH">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                         "ondblclick="MoreCheck()">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:equal value="false" name="isSH">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);" style="cursor:hand;"
											ondblclick="MoreDo('view')">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</logic:equal>
							</table>
						</div>						
					</fieldset>
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				  <center>
				  <logic:equal value="false" name="isSH">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button class="button2" onclick="MoreDo('add')"
								style="width:80px">
								增 加
							</button>								
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<button class="button2" onclick="MoreDo('modi')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="button2" onclick="MoreDo('del')"
								style="width:80px">
								删 除
							</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
					</div>
					</logic:equal>
				</center>
			</div>		
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
</html>	
