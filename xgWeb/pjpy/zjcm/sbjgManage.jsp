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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript">
	function searchJg(){
		var url = "/xgxt/zjcmPjpy.do?method=";
		var lx = $("lx").value;
		if("jxj" == lx){
			url += "jxjjgManage";
		}else{
			url += "rychjgManage";
		}
		
		url += "&lx="+lx;
		allNotEmpThenGo(url);
	}
	
	function delJg(){
		var url = "/xgxt/zjcmPjpy.do?method=";
		var lx = $("lx").value;
		if("jxj" == lx){
			url += "jxjjgManage";
		}else{
			url += "rychjgManage";
		}
		
		url += "&lx="+lx;
		sumitInfo(url,'del');
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjcmPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="message" name="message" value="${message}"/>
			<input type="hidden" id="fdyQx" name="fdyQx" value="<bean:write name="fdyQx" scope="session"/>"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" name="lx" id="lx" value="${lx }" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
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
										<logic:notEqual name="userType" value="stu">
											<html:select property="nj" style="" onchange="initZyList();initBjList()">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="nj" style="" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;学年：
										<html:select property="xn" style="" onchange="" disabled="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;学期： 
										<html:select property="xq" style="" onchange="" disabled="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="searchJg()">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<logic:notEqual name="userType" value="stu">
											<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;专业：
										<logic:notEqual name="userType" value="stu">
											<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="zydm" style="" styleId="zy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;班级：
										<logic:notEqual name="userType" value="stu">
											<html:select property="bjdm" style="" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="bjdm" style="" styleId="bj" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										学号：
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="" maxlength="20"/>
										<logic:equal name="lx" value="rych">
											&nbsp;&nbsp;<font color="red">*</font>荣誉称号：
											<html:select property="rychdm" style="" styleId="rychdm">
												<html:options collection="rychList" property="dm" labelProperty="mc" />
											</html:select>
										</logic:equal>
										<logic:equal name="lx" value="jxj">
											&nbsp;&nbsp;<font color="red">*</font>奖学金：
											<html:select property="jxjdm" style="" styleId="jxjdm">
												<html:options collection="jxjList" property="dm" labelProperty="mc" />
											</html:select>
										</logic:equal>
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
								<font color="blue">提示：双击可查看详细信息；上级部门已经审核，本级就不可以再执行删除操作.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:notEqual name="userType" value="stu">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
										</logic:notEqual>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showInfo('/xgxt/zjcmPjpy.do?method=sbjgUpdate',$('lx').value,'800','600')">			
											<!-- 奖学金 -->					
											<logic:equal name="lx"  value="jxj">
												<!-- 辅导员 -->
												<logic:equal name="fdyQx" value="true">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<logic:iterate id="x" name="s" offset="11" length="1">
															<td align="center">
																<!-- <bean:message key="lable.xsgzyxpzxy" />学校都未审核，可以进行删除操作 -->
																<logic:equal name="v" value="未审核">
																	<logic:equal name="x" value="未审核">
																		<input type="checkbox" id="checkVal" name="checkVal" 
																			value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																	</logic:equal>
																</logic:equal>
																<!-- <bean:message key="lable.xsgzyxpzxy" />已经审核，不可以进行删除操作 -->
																<logic:notEqual name="v" value="未审核">
																	<logic:equal name="x" value="未审核">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:equal>
																</logic:notEqual>
																<!-- 学校已经审核，不可以进行删除操作 -->
																<logic:notEqual name="x" value="未审核">
																	<logic:equal name="v" value="未审核">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:equal>
																</logic:notEqual>
																<!-- 学校<bean:message key="lable.xsgzyxpzxy" />都已经审核，不可以进行删除操作 -->
																<logic:notEqual name="x" value="未审核">
																	<logic:notEqual name="v" value="未审核">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:notEqual>
																</logic:notEqual>
															</td>
														</logic:iterate>
													</logic:iterate>
												</logic:equal>
												<logic:equal name="fdyQx" value="false">
													<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
													<logic:equal name="userType" value="xy">
														<logic:iterate id="v" name="s" offset="11" length="1">
															<td align="center">
																<!-- 学校未审核，可以进行删除操作 -->
																<logic:equal name="v" value="未审核">
																	<input type="checkbox" id="checkVal" name="checkVal" 
																		value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																</logic:equal>
																<!-- 学校已审核，不可以进行删除操作 -->
																<logic:notEqual name="v" value="未审核">
																	<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"
																		value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																</logic:notEqual>
															</td>
														</logic:iterate>	
													</logic:equal>
													<!-- 学校 -->
													<logic:notEqual name="userType" value="xy">
														<logic:notEqual name="userType" value="stu">
															<td>
																<input type="checkbox" id="checkVal" name="checkVal" 
																	value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:equal>
											</logic:equal>
											
											<!-- 荣誉称号 -->
											<logic:equal name="lx"  value="rych">
												<logic:equal name="userType" value="xy">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<td align="center">
															<!-- 学校未审核，可以进行删除操作 -->
															<logic:equal name="v" value="未审核">
																<input type="checkbox" id="checkVal" name="checkVal" 
																	value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</logic:equal>
															<!-- 学校已审核，不可以进行删除操作 -->
															<logic:notEqual name="v" value="未审核">
																<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"
																value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</logic:notEqual>
														</td>
													</logic:iterate>
												</logic:equal>
												<logic:notEqual name="userType" value="xy">
													<logic:notEqual name="userType" value="stu">
														<td align="center">
															<input type="checkbox" id="checkVal" name="checkVal" 
															value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
														</td>
													</logic:notEqual>
												</logic:notEqual>
											</logic:equal>
										
										<logic:equal name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="left">
													<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</logic:notEqual>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="100%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<logic:notEqual name="userType" value="stu">
							<button class="button2"
								onclick="delJg()"
								style="width:80px">
								删&nbsp;&nbsp;除
							</button>
							</logic:notEqual>
						</logic:equal>
							&nbsp;&nbsp;
							<button class="button2" onclick="impAndChkData()"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()"
								style="width:80px">
								导出数据
							</button>
					</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
