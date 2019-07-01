<%@ page language="java" pageEncoding="GBK"%>

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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/commanFunction.js"></script>	
	<script type="text/javascript" src="js/function.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript">		
		function send(){	
			var jlcfjl = document.getElementById('jlcfjl').value;
			var bz = document.getElementById('bz').vlaue;
			var grjl = document.getElementById('grjl').vlaue;
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("备注信息不能大于500个字符");
	          		 return false;
	       		 }
			}
			if(jlcfjl != null){
	         	if(jlcfjl.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		 alert("奖励处分经历不能大于150个字符");
	          		 return false;
	       		 }
			}
			if(grjl != null){
	         	if(grjl.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("个人简历不能大于1000个字符");
	          		 return false;
	       		 }
			}
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
	<body>		
		<html:form action="/stu_info_add" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：学生信息 - 个人信息 - 信息维护
			</div>
		</div>
			<input type="hidden" value="<bean:write name="oper"/>" id="oper" />			
			<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp">
			<input type="hidden" name="variable" id="variable" value="ydinfo">
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="5" align="center">
							<center>
								学生个人信息
							</center>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
						</logic:equal>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>年级：
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
						<td align="left" width="15%" rowspan="6">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
						</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>						
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">男</html:radio>
						<html:radio name="rs" property="xb" value="2">女</html:radio>
					</td>
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>						
					</td>
				</tr>
				<tr>
					<td align="right">
						民族：
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>班级：
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
					<td align="right">
						学籍状态：
					</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">		
								<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>				
<!--							<html:option value="有学籍">有学籍</html:option>-->
<!--							<html:option value="无学籍">无学籍</html:option>-->
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名拼音：
					</td>
					<td align="left">
						<html:text name="rs" property="xmpy" maxlength="64"/>
					</td>
					<td align="right">
						身高：
					</td>
					<td align="left">
						<html:text name="rs" property="sg" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(cm)
					</td>
				</tr>
				<tr>
					<td align="right">
						曾用名：
					</td>
					<td align="left">
						<html:text name="rs" property="cym" maxlength="16"/>
					</td>
					<td align="right">
						体重：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tz" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(kg)
					</td>

				</tr>
				<tr>
					<td align="right">
						出生日期：
					</td>
					<td align="left">
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
					</td>
					<td align="right">
						特长：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tc" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						培养方式：
					</td>
					<td align="left">
						<html:text name="rs" property="pyfs" maxlength="32"/>
					</td>
					<td align="right">
						培养层次：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="pycc" maxlength="32"/>
					</td>
				</tr>
				<tr>
<%--					<td align="right">--%>
<%--						入学方式：--%>
<%--					</td>--%>
<%--					<td align="left">--%>
<%--						<html:text name="rs" property="rxfs" maxlength="32"/>--%>
<%--					</td>--%>
					<td align="right">
						入学时间：
					</td>
					<td align="left">
						<html:text name="rs" property="rxrq" maxlength="10" onclick="return showCalendar('rxrq','y-mm-dd');" />
					</td>
					<td align="right">
						考生类别：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
					</td>
					<td align="right">
						电子邮箱：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						来源地区：
					</td>
					<td align="left">
						<html:text name="rs" property="syd" maxlength="25" styleId="syd"/>
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="lxdh" maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " styleId="lxdh"/>
					</td>	
				</tr>
				<tr>
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						<html:text name="rs" property="jg" maxlength="10" styleId="jg"/>
					</td>
					<td align="right">
						手机号码：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm" maxlength="11" styleId="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						学制：
					</td>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') " styleId="xz" maxlength="1"/>年						
					</td>
					<td align="right">
						是否贫困：
					</td>
					<td  colspan="2">
						<html:text property="sfpk" name="rs" readonly="true" styleId="sfpk"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						毕业时间：
					</td>
					<td colspan="5">
						<html:text property="byny" name="rs" style="width:100%" 
						maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<td align="right">
						准考证号：
					</td>
					<td>
						<html:text property="zkzh" name="rs" maxlength="20" styleId="zkzh" onkeyup="value=value.replace(/[^\d]/g,'') "/>					
					</td>
					<td align="right">
						是否残疾：
					</td>
					<td colspan="2">
						<html:radio property="sfcj" value="0" name="rs">否</html:radio>
						<html:radio property="sfcj" value="1" name="rs">是</html:radio>
					</td>
				</tr>
				<tr>
					<td align="right">
						银行名称：
					</td>
					<td>
						<html:select name="rs" property="yhdm" styleId="yhdm">
							<html:option value=""></html:option>
							<html:options collection="yhList" property="yhdm" labelProperty="yhmc"/>
						</html:select>
					</td>
					<td align="right">
						银行卡号：
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh" maxlength="20"
							onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
				</tr>	
				<tr>
					<td align="right">
						备注：
					</td>
					<td colspan="4">
						<html:textarea property="bz" name="rs" styleId="bz" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						本人简历：
					</td>
					<td colspan="4">
						<html:textarea property="grjl" name="rs" styleId="grjl" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						奖励处分经历：
					</td>
					<td colspan="4">
						<html:textarea property="jlcfjl" name="rs" styleId="jlcfjl" style="width:100%"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" width="100%">
				<logic:notPresent name="details">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="buttonSave" style="height:20px;width:80px"
						onclick="send();">
						保 存
					</button>			
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notPresent>
				<button class="button2" style="height:20px;width:80px"
					onclick="Close();return false;">
					关 闭
				</button>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
