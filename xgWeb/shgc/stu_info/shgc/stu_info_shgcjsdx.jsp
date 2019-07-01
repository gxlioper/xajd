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
		href="skin1/style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>	
	<script type="text/javascript" src="js/function.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript">
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function send(){	
			var ssbh = document.getElementById("ssbh").value;
			var ssch = document.getElementById("ssch").value;
			var zsrq = document.getElementById("zsrq").value;
			var zsjzrq = document.getElementById("zsjzrq").value;
			var byxx = document.getElementById("byxx").value;
			if(ssbh!=""){
				if(ssch==""){
					alert("宿舍床号不能为空！");
					return false;
				}
				if(ssch.length>1){
					alert("宿舍床号只能是一位字符!");
					return false;
				}
				
			}
			if(zsrq > zsjzrq){
				alert("住宿截止日期晚于入住日期，请重新设置！！");
				return false;
			}
			if(byxx != null){
	         	if(byxx.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("学生毕业信息不能大于500个字符");
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
				当前位置：个人信息-学生信息维护
			</div>
		</div>
			<input type="hidden" value="<bean:write name="oper"/>" id="oper" />			
			<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp">
			<input type="hidden" name="variable" id="variable" value="ydinfo">
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj">
			
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
						<br />
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</logic:equal>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>年级：
						<br />
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<br />
					</td>
						<td align="left" width="15%" rowspan="6">
							<img align="center" border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg" id="pic">
						</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
						<br />
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">男</html:radio>
						<html:radio name="rs" property="xb" value="2">女</html:radio>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>专业：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						民族：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>班级：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						政治面貌：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						学籍状态：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:option value="有学籍">有学籍</html:option>
						<html:option value="无学籍">无学籍</html:option>
						</html:select>
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						姓名拼音：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="xmpy" maxlength="64"/>
						<br />
					</td>
					<td align="right">
						身高：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="sg" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						(cm)
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						曾用名：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="cym" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						体重：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tz" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="3"/>
						(kg)
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						出生日期：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
						<br />
					</td>
					<td align="right">
						特长：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tc" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						培养方式：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="pyfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						培养层次：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="pycc" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						入学方式：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="rxfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						考生类别：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						身份证号：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
						<br />
					</td>
					<td align="right">
						电子邮箱：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						来源地区：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="syd" maxlength="25"/>						
						<br />
					</td>
					<td align="right">
						联系电话：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="lxdh" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="13"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						籍贯：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="jg" maxlength="10"/>
						<br />
					</td>
					<td align="right">
						手机号码：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="11"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						学制：
					</td>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="1"/>年						
					</td>
					<td></td>
					<td colspan="2"></td>
					
				</tr>
				<tr>
					<td align="right">
						入党时间：
						<br />
					</td>
					<td align="left">
						<html:text name="rs" property="jrgcdsj" styleId="jrgcdsj"
						 onclick="return showCalendar('jrgcdsj','y-mm-dd');" readonly="true"/>
						<br />
					</td>
					<td align="right">
						入团时间：
						<br />
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="jrgqtsj" styleId="jrgqtsj"
						onclick="return showCalendar('jrgqtsj','y-mm-dd');" readonly="true"/>
						<br />
					</td>
				</tr>
				<tr height="22">
					<td align="right">
						宿舍号：
					</td>
					<td align="left">
						<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
					</td>
					<td align="right">
						宿舍床号：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="ssch" readonly="true"/>
					</td>
				</tr>
				<tr height="22">
					<td align="right">
						住宿日期：
					</td>
					<td align="left" valign="middle">
						<html:text name="rs"
							property="zsrq"
							styleId="zsrq" readonly="true"/>
					</td>
					<td align="right">
						住宿截止日期：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs"
							property="zsjzrq" styleId="zsjzrq" readonly="true"/>
					</td>
				</tr>
				<tr height="80">
					<td align="right">
						住宿信息备注：
					</td>
					<td align="left" colspan="4">
						<html:textarea name="rs" property="ssbz"
							style="width:100%;height:100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						毕业时间：
					</td>
					<td>
						<html:text property="byny" name="rs" style="width:100%" 
						maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<td align="right">
						是否毕业生：
					</td>
					<td align="left" colspan="2">
						<html:select property="sfbys" name="rs" style="width:120px">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr height="80">
					<td align="right">
						学生毕业信息：
					</td>
					<td align="left" colspan="4">
						<html:textarea name="rs" property="byxx"
							style="width:100%;height:100%" styleId="byxx"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" width="100%">
			<logic:notPresent name="details">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
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
