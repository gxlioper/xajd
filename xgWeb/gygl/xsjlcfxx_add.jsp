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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/modiData3.do" method="post">
		<input type="hidden" name="nrV" id="nrV">
		<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 公寓德育考评 - 详细信息查看 - 学生奖惩信息
				</div>
			</div>
			<logic:equal value="ok" name="done">
				<script type="text/javascript">
	            alert("保存成功！");
		        Close();
		        window.dialogArguments.document.getElementById('search_go').click();
	    </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
	           alert("保存失败！");
		       Close();
		       window.dialogArguments.document.getElementById('search_go').click();
	    </script>
			</logic:equal>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								学生奖惩
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="true" styleId="xh" />
						</td>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="xm" styleId="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="xb" styleId="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}"
									readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="nj" styleId="nj" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs"  property="xq" value="${rs.xq}"
									readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs"  property="xq" style="width:90px"
									styleId="xq">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="xymc" styleId="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>楼栋名称：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.ldmc}" readonly="true" />
								<html:hidden name="rs" property="lddm"></html:hidden>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:text name='rs' property="lddm" readonly="true"
									styleId="lddm" />
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>寝室号：
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" readonly="true"
									style="width: 120px"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="qsh" styleId="qsh"
									readonly="true" />
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' readonly="true" property="bjmc" styleId="bjmc" />
						</td>
						<td align="right">
						</td>
						<td align="left">
						</td>
					</tr>

					<tr>
						<td colspan="4">
							<table width="100%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td align="center" style="cursor:hand"
											onclick="document.getElementById('ly').style.display=(document.getElementById('ly').style.display==''?'none':'')">
											奖惩信息
										</td>
									</tr>
								</thead>
								<tr id="ly" style="display:none">
									<td>
										<fieldset>
											<legend>
												奖惩 &nbsp;&nbsp;&nbsp;
												<font color="blue"><button class="button2"
														onclick="add('tTb')" style="height:18px;width:20px">
														+
													</button> &nbsp;
													<button class="button2" onclick="decrease('tTb')"
														style="height:18px;width:20px">
														-
													</button> </font>
											</legend>
											<table width="100%" align="center" class="tbstyle" id="tTb">
												<tbody>
												<thead>
													<tr>
														<td align="center" style="cursor:hand" style="width:50px">
															序号
														</td>
														<td align="center" style="cursor:hand">
															奖惩
														</td>
														<td align="center" style="cursor:hand">
															奖惩时间
														</td>
														<td align="center" style="cursor:hand">
															加减分数
														</td>
														<td align="center" style="cursor:hand">
															奖惩内容
														</td>
														<td align="center" style="cursor:hand">
															备注
														</td>
													</tr>
												</thead>
												<tr onclick="rowMoreClick(this,'',event);" style="display:none;cursor:hand" id="1" >
													<td>
														1
													</td>
													<td>
														<select id="jc1" name="jc1" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj1" name="sj1" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj1','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf1" id="xf1" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr1" id="nr1" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872"  name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr1" id="nr1" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr1" id="nr1">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz1" id="bz1">
													</td>
												</tr>
												<tr onclick="rowMoreClick(this,'',event);" style="display:none;cursor:hand" id="2">
													<td>
														2
													</td>
													<td>
														<select id="jc2" name="jc2" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj2" name="sj2" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj2','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf2" id="xf2" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr2" id="nr2" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr2" id="nr2" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr2" id="nr2">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz2" id="bz2">
													</td>
												</tr>
												<tr onclick="rowMoreClick(this,'',event);" style="display:none;cursor:hand" id="3">
													<td>
														3
													</td>
													<td>
														<select id="jc3" name="jc3" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj3" name="sj3" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj3','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf3" id="xf3" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr3" id="nr3" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr3" id="nr3" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr3" id="nr3">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz3" id="bz3">
													</td>
												</tr>
												<tr onclick="rowMoreClick(this,'',event);" style="display:none;cursor:hand" id="4">
													<td>
														4
													</td>
													<td>
														<select id="jc4" name="jc4" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj4" name="sj4" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj4','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf4" id="xf4" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr4" id="nr4" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr4" id="nr4" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr4" id="nr4">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz4" id="bz4">
													</td>
												</tr>
												<tr onclick="rowMoreClick(this,'',event);" style="display:none;cursor:hand" id="5">
													<td>
														5
													</td>
													<td>
														<select id="jc5" name="jc5" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj5" name="sj5" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj5','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf5" id="xf5" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr5" id="nr5" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr5" id="nr5" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr5" id="nr5">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz5" id="bz5">
													</td>
												</tr>
												<tr style="display:none" id="6">
													<td>
														6
													</td>
													<td>
														<select id="jc6" name="jc6" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj6" name="sj6" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj6','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf6" id="xf6" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr6" id="nr6" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr6" id="nr6" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr6" id="nr6">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz6" id="bz6">
													</td>
												</tr>
												<tr style="display:none" id="7">
													<td>
														7
													</td>
													<td>
														<select id="jc7" name="jc7" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj7" name="sj7" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj7','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf7" id="xf7" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr7" id="nr7" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr7" id="nr7" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr7" id="nr7">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz7" id="bz7">
													</td>
												</tr>
												<tr style="display:none" id="8">
													<td>
														8
													</td>
													<td>
														<select id="jc8" name="jc8" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj8" name="sj8" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj8','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf8" id="xf8" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr8" id="nr8" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr8" id="nr8" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr8" id="nr8">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz8" id="bz8">
													</td>
												</tr>
												<tr style="display:none" id="9">
													<td>
														9
													</td>
													<td>
														<select id="jc9" name="jc9" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj9" name="sj9" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj9','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf9" id="xf9" style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr9" id="nr9" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr9" id="nr9" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr9" id="nr9">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz9" id="bz9">
													</td>
												</tr>
												<tr style="display:none" id="10">
													<td>
														10
													</td>
													<td>
														<select id="jc10" name="jc10" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj10" name="sj10" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj10','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf10" id="xf10"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr10" id="nr10" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr10" id="nr10" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr10" id="nr10">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz10" id="bz10">
													</td>
												</tr>
												<tr style="display:none" id="11">
													<td>
														11
													</td>
													<td>
														<select id="jc11" name="jc11" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj11" name="sj11" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj11','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf11" id="xf11"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr11" id="nr11" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr11" id="nr11" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr11" id="nr11">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz11" id="bz11">
													</td>
												</tr>
												<tr style="display:none" id="12">
													<td>
														12
													</td>
													<td>
														<select id="jc12" name="jc12" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj12" name="sj12" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj12','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf12" id="xf12"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr12" id="nr12" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr12" id="nr12" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr12" id="nr12">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz12" id="bz12">
													</td>
												</tr>
												<tr style="display:none" id="13">
													<td>
														13
													</td>
													<td>
														<select id="jc13" name="jc13" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj13" name="sj13" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj13','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf13" id="xf13"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr13" id="nr13" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr13" id="nr13" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr13" id="nr13">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz13" id="bz13">
													</td>
												</tr>
												<tr style="display:none" id="14">
													<td>
														14
													</td>
													<td>
														<select id="jc14" name="jc14" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj14" name="sj14" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj14','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf14" id="xf14"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr14" id="nr14" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr14" id="nr14" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr14" id="nr14">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz14" id="bz14">
													</td>
												</tr>
												<tr style="display:none" id="15">
													<td>
														15
													</td>
													<td>
														<select id="jc15" name="jc15" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj15" name="sj15" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj15','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf15" id="xf15"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr15" id="nr15" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr15" id="nr15" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr15" id="nr15">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz15" id="bz15">
													</td>
												</tr>
												<tr style="display:none" id="16">
													<td>
														16
													</td>
													<td>
														<select id="jc16" name="jc16" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj16" name="sj16" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj16','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf16" id="xf16"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr16" id="nr16" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr16" id="nr16" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr16" id="nr16">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz16" id="bz16">
													</td>
												</tr>
												<tr style="display:none" id="17">
													<td>
														17
													</td>
													<td>
														<select id="jc17" name="jc17" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj17" name="sj17" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj17','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf17" id="xf17"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr17" id="nr17" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr17" id="nr17" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr17" id="nr17">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz17" id="bz17">
													</td>
												</tr>
												<tr style="display:none" id="18">
													<td>
														18
													</td>
													<td>
														<select id="jc18" name="jc18" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj18" name="sj18" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj18','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf18" id="xf18"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr18" id="nr18" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr18" id="nr18" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr18" id="nr18">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz18" id="bz18">
													</td>
												</tr>
												<tr style="display:none" id="19">
													<td>
														19
													</td>
													<td>
														<select id="jc19" name="jc19" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj19" name="sj19" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj19','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf19" id="xf19"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr19" id="nr19" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr19" id="nr19" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr19" id="nr19">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz19" id="bz19">
													</td>
												</tr>
												<tr style="display:none" id="20">
													<td>
														20
													</td>
													<td>
														<select id="jc20" name="jc20" onchange="getGyJcList(this)">
															<option value="jl">
																奖励
															</option>
															<option value="cf">
																惩罚
															</option>
														</select>
													</td>
													<td>
														<input type="text" id="sj20" name="sj20" readonly="true"
															onblur="dateFormatChg(this)"
															onclick="return showCalendar('sj20','y-mm-dd');"
															style="width: 80px">
													</td>
													<td>
														<input type="text" name="xf20" id="xf20"
															style="width: 80px"
															onkeypress="return sztzNumInputValue(this,5,event)" onblur="chkInput(this,event)">
													</td>
													<td>
														<logic:equal value="12872" name="xxdm">
															<!-- 杭州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
															<select name="nr20" id="nr20" onchange="getGyJcXf(this)"></select>
														</logic:equal>
														<logic:notEqual value="12872" name="xxdm">
															<logic:equal value="11641" name="xxdm">
															<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" /> -->
																<select name="nr20" id="nr20" onchange="getGyJcXf(this)"></select>
															</logic:equal>
															<logic:notEqual value="11641" name="xxdm">
																<input name="nr20" id="nr20">
															</logic:notEqual>
														</logic:notEqual>
													</td>
													<td>
														<input type="text" name="bz20" id="bz20">
													</td>
												</tr>
											</table>

										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="gydyDataTj('xh-xn-xq-lddm-qsh');return false;"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>			
		</html:form>
	</body>
	<script type="text/javascript">
function add(the_tab){
  var sum=document.getElementById(the_tab).rows.length;
  var i = sum;
     for(var i=1;i<=sum-1;i++)
     {
       if(document.getElementById(i).style.display=='none')
       {
         document.getElementById(i).style.display='';
         getGyJcList("",i-1);
	     break;
       }
     }
  
}

function decrease(the_tab){
   var sum=document.getElementById(the_tab).rows.length;
   var my_JcAarry = ["jc","sj","xf","nr","bz"];
   for(var i=sum-1;i>0;i--){ 
   
      if(document.getElementById(i).style.display=='')
      {
         document.getElementById(i).style.display='none';
         //document.getElementById(my_JcAarry[0]+i).value='jl';
         document.getElementById(my_JcAarry[1]+i).value='';
         document.getElementById(my_JcAarry[2]+i).value='';
         document.getElementById(my_JcAarry[3]+i).value='';
         document.getElementById(my_JcAarry[4]+i).value='';
	     break;
      }
   }
}

function gydyDataTj(mustFill) {
	var sum=document.getElementById('tTb').rows.length;
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var my_JcAarry = ["jc","sj","xf","nr","bz"];
	for(i=0;i<sum-1;i++){
		    var n=i+1;	
			if($(my_JcAarry[1]+n).value!=""){
			    if($(my_JcAarry[2]+n).value==""){
				   alert("第"+n+"个加减分数为空！");		
				   $(my_JcAarry[2]+n).focus();	
				   return false;			
				   break;
				}
				if($(my_JcAarry[3]+n).value==""||$(my_JcAarry[3]+n).value=="null"){
				   alert("第"+n+"个奖惩内容为空！");		
				   $(my_JcAarry[3]+n).focus();	
				   return false;			
				   break;
				}
			}else if($(my_JcAarry[2]+n).value!=""||$(my_JcAarry[3]+n).value!=""){
			    if($(my_JcAarry[1]+n).value==""){
			        alert("第"+n+"个奖惩时间为空！");			
				    $(my_JcAarry[1]+n).focus();	
				    return false;			
				    break;
			    }
			}			
	  }
	if(confirm('确定要保存该信息？')){
	var url = "/xgxt/modiData3.do?jcs=";
	url += sum-1;
	document.forms[0].action = url;
	document.forms[0].submit();
	 showTips('数据保存中，请稍候...');	
    }else{
       return false;
    }
   }
 </script>
</html>
