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
					<td align="right">
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
					<td align="right" >
						<font color="red">*</font>年级：
					</td>
					<td align="left">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<html:hidden property="nj" name="rs"/>
					</td>
					<td align="left" rowspan="6">
						<img border="0" style="height:133px;width:100px;"
							src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" disabled="true"/>
						<html:hidden property="xm" name="rs"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<html:hidden property="xydm" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						招生类别：
					</td>
					<td>
						<html:text name="rs" property="zslb" disabled="true"/>	
						<html:hidden property="zslb" name="rs"/>					
					</td>
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left">
						<html:select name="rs" property="zydm" styleId="zy" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<html:hidden property="zydm" name="rs"/>
					</td>
				</tr>
				<tr>
				<td align="right">
						学制：
					</td>
					<td>
						<html:text name="rs" property="xz" disabled="true"/>年		
						<html:hidden property="xz" name="rs"/>				
					</td>
					<td align="right">
						<font color="red">*</font>班级：
						<br />
					</td>
					<td align="left">
						<html:select name="rs" property="bjdm" styleId="bj" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<html:hidden property="bjdm" name="rs"/>
					</td>
				</tr>
				<tr>
				<td align="right">
						一卡通号：
					</td>
					<td colspan="3">
						<html:text name="rs" property="kh" disabled="true" style="width:100%"/>	
						<html:hidden property="kh" name="rs"/>					
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1" disabled="true">男</html:radio>
						<html:radio name="rs" property="xb" value="2" disabled="true">女</html:radio>
						<html:hidden name="rs" property="xb"/>
					</td>
					<td align="right">
						学籍状态：
					</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150" disabled="true">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--							<html:option value="有学籍">有学籍</html:option>-->
<!--							<html:option value="无学籍">无学籍</html:option>-->
						</html:select>
						<html:hidden property="xjzt" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						民族：
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px" disabled="true">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<html:hidden property="mz" name="rs"/>
					</td>
					<td align="right">
						国籍：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="gj" styleId="gj" disabled="true"/>
						<html:hidden property="gj" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						身份证号：
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh" disabled="true"/>
						<html:hidden property="sfzh" name="rs"/>
					</td>
					<td align="right">
						身高：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sg" disabled="true"/>
						(cm)
						<html:hidden property="sg" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						<html:text name="rs" property="jg" disabled="true"/>
						<html:hidden property="jg" name="rs"/>
					</td>
					<td align="right">
						体重：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="tz" disabled="true"/>
						(kg)
						<html:hidden property="tz" name="rs"/>
					</td>
				</tr>
				<tr>					
					<td align="right">
						特长：
					</td>
					<td align="left">
						<html:text name="rs" property="tc" disabled="true"/>
						<html:hidden property="tc" name="rs"/>
					</td>
					<td align="right">
						政治面貌：
					</td>
					<td align="left" colspan="2">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						是否已婚：
					</td>
					<td align="left">
						<html:select property="sfjh" name="rs">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
						</html:select>
					</td>
					<td align="right">
						乘车区间：
					</td>
					<td align="left" colspan="2">
						<html:text property="ccqj" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<html:text name="rs" property="lxdh"/>
					</td>
					<td align="right">
						手机号码：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						QQ号：
					</td>
					<td align="left">
						<html:text property="qqhm" name="rs" disabled="true"/>
						<html:hidden name="rs" property="qqhm"/>
					</td>
					<td align="right">
						电子邮箱：
					</td>
					<td align="left" colspan="2">
						<html:text property="dzyx" name="rs" disabled="true"/>
						<html:hidden name="rs" property="dzyx"/>
					</td>
				</tr>	
			</table>
			<div class="buttontool" id="btn" width="100%">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
					onclick="send();">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
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
