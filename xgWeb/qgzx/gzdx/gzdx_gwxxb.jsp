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
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>

	<script language="javascript">
	function print1(){
		window.open('gzdx_scgwxxbPrint.do?pkValue=');
	}
	function save(){
		var gznr = document.getElementById('gznr').value;
		var gzsj = document.getElementById('gzsj').value;
		//工作内容
		if(gznr != null && gznr != ''){
			if(gznr.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          	alert("工作内容不能大于150个字符");
	          	return false;
	       }
		}
		//工作时间
		if(gzsj != null && gzsj != ''){
			if(gzsj.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          	alert("工作时间不能大于150个字符");
	          	return false;
	       }
		}
		if(save1('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-gznr-gzjssj-gzsj-gwxz')) 
			BatAlert.showTips('正在操作中，请稍等...'); 
	}
	function save1(url, pkFields) {	
		var xyrs = document.getElementById("xyrs").value;
		var gzsj = document.getElementById("gzsj").value;
		var eles = pkFields.split("-");
		var valu = "";
		var xxdm = "";
	
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {	
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
		for (i = 0; i < eles.length; i++) {
			valu += document.getElementById(eles[i]).value;
		}
		url = url + valu;
		if(xyrs.match(/^\d+\.{0,1}\d{0,3}$/)==null){
			alert("数据格式错误！");
			document.getElementById("xyrs").focus();
			return false;
		}
		
		document.forms[0].action = url;
		document.forms[0].submit();
		if(window.dialogArguments){
			window.close();
			window.dialogArguments.document.all("search_go").click();
		}
		return true;
	}
</script>
	<body>

		<html:form action="/comm_pub" method="post">
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm"/>" />
			<logic:present name="gwsbsj">
				<input type="hidden" id="gwsbsj" name="gwsbsj"
					value="<bean:write name="gwsbsj"/>" />
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 岗位发布 - 岗位信息发布
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="knsbl" name="knsbl"
					value="<bean:write name="rs" property="knsbl"/>" />
				<input type="hidden" id="xueqi" name="xueqi"
					value="<bean:write name="rs" property="xueqi"/>" />
				<input type="hidden" id="url" name="url"
					value="/qgzx/gzdx/gzdx_gwxxb.jsp" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								岗位信息发布
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>岗位名称：
						</td>
						<td height="22" align="left">
							<logic:notEqual value="modi" name="doType">
								<html:text name="rs" property="gwdm" styleId="gwdm"
									onkeyup="value=value.replace('-','');value=value.replace('+','＋')" maxlength="20" />
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:text name="rs" property="gwdm" styleId="gwdm"
									readonly="true" maxlength="20" />
							</logic:equal>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>申请单位(部门)：
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="sqdw" styleId="sqdw"
								style="width:120px" onchange="getYrdwInfo()">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
					</tr>
					<logic:equal name="xxdm" value="11078">
					<tr>
						<td height="22" align="right">
							申请人工号：
						</td>
						<td height="22" align="left" colspan="">
							${rs.userName }
						</td>
						<td height="22" align="right">
							申请人姓名：
						</td>
						<td height="22" align="left" colspan="">
							${rs.xm }
						</td>
					</tr>
					</logic:equal>
					<tr>
						<td height="22" align="right">
							本部门勤工助学管理员姓名：
						</td>
						<td height="22" align="left" colspan="">
							<html:text name="rs" property="fzr" styleId="lxr" maxlength="10" />
						</td>
						<td height="22" align="right">
							联系电话：
						</td>
						<td height="22" align="left" colspan="">
							<html:text name="rs" property="lxdh" styleId="lxdh"
								maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
								maxlength="15" style="width:100%" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学年：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xn" style="width: 90px"
								readonly="true" />
						</td>
						<td height="22" align="right">
							年度：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="nd" style="width: 90px"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作开始日期：
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzkssj" styleId="gzkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzkssj','y-mm-dd');" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>工作结束日期：
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzjssj" styleId="gzjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzjssj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>拟申请设立岗位数：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyrs" styleId="xyrs" maxlength="3"
								onkeyup="value=value.replace(/[^\d]/g,'') " />
						</td>
						<td align="right">
							<font color="red">*</font>岗位性质：
						</td>
						<td>
							<html:select property="gwxz" name="rs" styleId="gwxz">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>工作时间：
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="gzsj" styleId="gzsj"
								style="width:100%;height:80px" />
							(例：周一上，周二下...)
							<span id="gzsjDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>工作内容：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作要求：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gzyd" styleId="gzyd"
								style="width:100%" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							申请单位(部门)意见：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqdwyj" style="width:100%"
								onblur="chLeng(this,'100')" rows="5"></html:textarea>
						</td>
					</tr>
				</table>
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:notEqual name="xxdm" value="11078">
							<button type="button" class="button2" onclick="save();return false;" style="width:80px"
								id="buttonSave">
								保 存
							</button>
							</logic:notEqual>
							<logic:equal name="xxdm" value="11078">
								<logic:equal name="canWrite" value="yes">
									<button type="button" class="button2" onclick="save();return false;" style="width:80px"
									id="buttonSave">
									保 存
								</button>
								</logic:equal>
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="print1();"  style="width:80px">
								打 印 
							</button>
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
