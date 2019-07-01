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
	<script language="javascript">
function saveJgxx(){
	var jgbh = document.getElementById("jgbh").value;
	var xm = document.getElementById("xm").value;
	var jxnd = document.getElementById("jxnd").value;
	var bz = document.getElementById("bz").value;
	if(jgbh == "" || xm == ""){
		alert("教官编号或教官姓名不能为空，请确认！！");
		return false;
	}
	if(jxnd == "" ){
		alert("负责年级不能为空，请确认！！");
		return false;
	}
	if(bz.length > 200){
		alert("备注最多可输入200字，请确认！！");
		return false;
	}
	return true;
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 信息维护 - 教官信息维护
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					无记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<fieldset>
					<legend>
						教官信息
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>教官编号：
							</td>
							<td align="left">
							<logic:equal value="edit" name="doType">
								<html:text name="rs" property="jgbh" styleId="jgbh" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="edit" name="doType">
								<html:text name="rs" property="jgbh" styleId="jgbh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</logic:notEqual>
							</td>
							<td align="right">
								<font color="red">*</font>教官姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:select name="rs" property="xb" styleId="xb">
									<html:options collection="xbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<td align="right">
								民族：
							</td>
							<td align="left">
								<html:select name="rs" property="mzdm" styleId="mz">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								联系电话：
							</td>
							<td align="left">
								<html:text name="rs" property="lxdh" styleId="lxdh" maxlength="16"
									onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>负责年级：
							</td>
							<td align="left">
								<html:select property="jxnd" styleId="jxnd" name="rs">
									<html:options collection="njList" labelProperty="njmc" property="njdm"/>
								</html:select>
							</td>
							<td align="right">
								职务：
							</td>
							<td align="left">
								<html:text name='rs' property="zw" styleId="zw" maxlength="50"/>
							</td>
						</tr>	
						<logic:present name="tp">
						<tr>
						<td align="right">
							负责连队：
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="sfzld" style="width:350px" rows="3" disabled="true"></html:textarea>
						</td>
						</tr>	
						</logic:present>
						<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:350px" rows="3"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2"
						onclick="if(saveJgxx()){Savedata('jgbh-xm','jxglgt.do?method=jgxxOne&type=save&doType='+$('doType').value)};"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("该教官编号已存在，请重新确认!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
	</body>
</html>
