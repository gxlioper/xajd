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
	<base target="_self" />
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
	<script language="javascript">
</script>
	<body onload="xyDisabled('bmdm')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<html:form action="/SavaSztzHdsb.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 拓展项目申报 - 申报
				</div>
			</div>
			<logic:present name="isEXIST">
				<logic:equal value="ok" name="isEXIST">
					<script language="javascript">
	         	alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:equal>
				<logic:equal value="no" name="isEXIST">
					<script language="javascript">
	         	alert("操作失败！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();	         	
	         	</script>
				</logic:equal>
				<logic:equal value="ischeck" name="isEXIST">
					<script language="javascript">
	         	alert("该项目已通过审核,不能再进行修改、删除操作！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();	         		         		
	         </script>
				</logic:equal>

			</logic:present>
			<input type="hidden" id="writeAble" name="writeAble" value="yes" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType1" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue" value="" />
			<fieldset>
				<legend>
					拓展项目申报
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>修改申报表</b>
							</td>
						</tr>
					</thead>
					<tr valign="middle">
						<td width="15%" align="right" nowrap>
							<font color="red">*</font>学年：
						</td>
						<td width="35%" align="left">
							<html:select name="rs" property="xn" style="width:100px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td width="15%" align="right" nowrap>
							<font color="red">*</font>学期：
						</td>
						<td width="35%" align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr valign="middle">
						<td width="15%" align="right" nowrap>
							<font color="red">*</font>项目代码：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="xmdm" styleId="xmdm"
								style="cursor:hand;color:#999999" readonly="true" maxlength="8" />
						</td>
						<td width="15%" align="right" nowrap>
							<font color="red">*</font>项目名称：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="xmmc" />
						</td>
					</tr>
					<tr valign="middle">
						<td align="right" nowrap>
							<font color="red">*</font>所属科目：
						</td>
						<td align="left">
							<html:select name="rs" styleId="kmdm" property="kmdm"
								style="background-color:#FFFFFF;">
								<html:options collection="kmdmList" property="kmdm"
									labelProperty="kmm"></html:options>
							</html:select>
						</td>
						<td align="right" nowrap>
							<font color="red">*</font>申请部门：
						</td>
						<td align="left">
							<html:select name="rs" property="bmdm" styleId="bmdm"
								style="background-color:#FFFFFF;">
								<option value=""></option>
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc"></html:options>
							</html:select>
						</td>
					</tr>
					<tr valign="middle">
						<td width="15%" align="right" nowrap>
							执行单位：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="xsdw" />
						</td>
						<td width="15%" align="right" nowrap>
							学分：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="xf"
								onkeypress='return sztzNumInputValue(this,4,event)'
								onblur="chkInput(this,event)" />
						</td>
					</tr>
					<tr valign="middle">
						<td width="15%" align="right" nowrap>
							申请开始时间：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="sqkssj" onblur="dateFormatChg(this)"  style="cursor:hand;" readonly="true"
							onclick="return showCalendar('sqkssj','y-mm-dd');"/>
						</td>
						<td width="15%" align="right" nowrap>
							申请结束时间：
						</td>
						<td width="35%" align="left">
							<html:text name="rs" property="sqjssj" 
									onblur="dateFormatChg(this)" style="cursor:hand;" readonly="true"
									onclick="if($('sqkssj').value.replace(' ','')==''){return false;}else{return showCalendar('sqjssj','y-mm-dd');}"/>
						</td>
					</tr>
					<tr align="left" valign="top">
						<td align="right">
							项目描述：
							<br>
							(限500字)&nbsp;
						</td>
						<td colspan="3">

							<html:textarea name="rs" property="xmms" rows="5"
								style="width:90% "></html:textarea>
						</td>
					</tr>
				</table>
				<table width="100%" align="center" class="tbstyle">
					<thead>
						<tr>
							<td align="center" style="cursor:hand" title="单击该行显示或隐藏详细"
								onclick="document.getElementById('ly').style.display=(document.getElementById('ly').style.display==''?'none':'')">
								申请申报理由
							</td>
						</tr>
					</thead>
					<tr id="ly" style="display:none">
						<td>
							<fieldset>
								<legend>
									理由 &nbsp;&nbsp;&nbsp;
									<font color="blue">（提示：代码为项目代码加理由代码编号）</font>
								</legend>
								<table width="100%" align="center" class="tbstyle">
									<tr valign="middle">
										<td style="width:4%">
											1
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm1" styleId="lydm1"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc1" styleId="lymc1"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:8%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr1" styleId="lynr1"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											2
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm2" styleId="lydm2"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc2" styleId="lymc2"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr2" styleId="lynr2"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											3
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm3" styleId="lydm3"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc3" styleId="lymc3"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr3" styleId="lynr3"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											4
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm4" styleId="lydm4"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc4" styleId="lymc4"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr4" styleId="lynr4"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											5
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm5" styleId="lydm5"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc5" styleId="lymc5"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr5" styleId="lynr5"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											6
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm6" styleId="lydm6"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc6" styleId="lymc6"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr6" styleId="lynr6"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											7
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm7" styleId="lydm7"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc7" styleId="lymc7"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr7" styleId="lynr7"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											8
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm8" styleId="lydm8"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc8" styleId="lymc8"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr8" styleId="lynr8"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											9
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm9" styleId="lydm9"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc9" styleId="lymc9"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr9" styleId="lynr9"
												cols="40"></html:textarea>
										</td>
									</tr>
									<tr valign="middle">
										<td style="width:4%">
											10
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lydm10" styleId="lydm10"
												onfocus="AutoFillVal(this,'lydm')"
												onblur="return ChekLydm(this);"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lymc10" styleId="lymc10"
												style="width:120px" maxlength="15" />
										</td>
										<td style="width:7%" align="right">
											内容：
										</td>
										<td align="left">
											<html:textarea name="rs" property="lynr10" styleId="lynr10"
												cols="40"></html:textarea>
										</td>
									</tr>
								</table>
							</fieldset>

						</td>
					</tr>
				</table>
				<table width="100%" align="center" class="tbstyle">
					<thead>
						<tr>
							<td align="center" style="cursor:hand" title="单击该行显示或隐藏详细"
								onclick="document.getElementById('lb').style.display=(document.getElementById('lb').style.display==''?'none':'')">
								&nbsp;奖&nbsp;项&nbsp;类&nbsp;别&nbsp;
							</td>
						</tr>
					</thead>
					<tr id="lb" style="display:none">
						<td>
							<fieldset>
								<legend>
									类别 &nbsp;&nbsp;&nbsp;
									<font color="blue">（提示：代码为项目代码加类别代码编号）</font>
								</legend>

								<table width="100%" align="center" class="tbstyle">
									<tr valign="top">
										<td style="width:4%">
											1
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm1" styleId="lbdm1"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc1" styleId="lbmc1"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf1" styleId="lbxf1"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											2
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm2" styleId="lbdm2"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc2" styleId="lbmc2"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf2" styleId="lbxf2"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											3
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm3" styleId="lbdm3"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc3" styleId="lbmc3"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf3" styleId="lbxf3"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											4
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm4" styleId="lbdm4"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc4" styleId="lbmc4"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf4" styleId="lbxf4"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											5
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm5" styleId="lbdm5"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc5" styleId="lbmc5"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf5" styleId="lbxf5"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											6
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm6" styleId="lbdm6"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc6" styleId="lbmc6"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf6" styleId="lbxf6"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											7
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm7" styleId="lbdm7"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc7" styleId="lbmc7"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf7" styleId="lbxf7"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											8
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm8" styleId="lbdm8"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc8" styleId="lbmc8"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf8" styleId="lbxf8"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											9
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm9" styleId="lbdm9"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc9" styleId="lbmc9"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf9" styleId="lbxf9"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
									<tr valign="top">
										<td style="width:4%">
											10
										</td>
										<td style="width:8%" align="right">
											代码：
										</td>
										<td align="left" style="width:10%">
											<html:text name="rs" property="lbdm10" styleId="lbdm10"
												onfocus="AutoFillVal(this,'lbdm')"
												style="width:120px;cursor:hand;color:#999999" maxlength="16" />
										</td>
										<td style="width:8%" align="right">
											名称：
										</td>
										<td align="left">
											<html:text name="rs" property="lbmc10" styleId="lbmc10"
												style="width:120px" maxlength="30" />
										</td>
										<td style="width:10%" align="right">
											对应学分：
										</td>
										<td align="left" style="width:42%">
											<html:text name="rs" property="lbxf10" styleId="lbxf10"
												style="width:50px"
												onkeypress="return sztzNumInputValue(this,4,event)" onblur="ckinpdata(this)" />
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<div class="buttontool" id="btn" align="center">
					<button class="button2"
						onclick="SztzchkCode('xmdm-xmmc-kmdm-bmdm');" style="width:80px"
						id="buttonSave">
						保存
					</button>
				</div>
			</fieldset>
			<logic:present name="isEXIST">
				<logic:equal value="is" name="isEXIST">
					<script language="javascript">
	         		alert("该代码已经存在!");
	         		document.forms[0].xmdm.focus();
	         	</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
