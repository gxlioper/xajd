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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function editBtn(){
		if (confirm("确认要修改该生的组织关系接转吗?")) {
			document.getElementById("zwsj").disabled=false;
			document.getElementById("jrlx").disabled=false;
			document.getElementById("jrsj").disabled=false;
			document.getElementById("gxzw").disabled=false;
			document.getElementById("buttonSave").disabled=false;
			document.getElementById("buttonEdit").disabled=true;
			return true;
		} else {
			return false;
		}
	}
	</script>
	<body onload="checkWinType();">
		<input type="hidden" id="method" name="method" value="zzgx" />
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope = "session"/>" />
		<html:form action="/dtjs_zjcm" method="post">
			<fieldset>
				<legend>
					组织关系接转
				</legend>
				<logic:notEmpty name="rs">
				<table width="100%" class="tbstyle">
					<tr>
						<td>
							&nbsp;&nbsp;学号：
						</td>
						<td align="left">
							<html:text property="xh" styleId="xh" name="rs" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;姓名：
						</td>
						<td align="left">
							<html:text property="xm" name="rs" readonly="true"/>
						</td>

					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;性别:
						</td>
						<td>
							<html:select property="xb" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;年级：
						</td>
						<td align="left">
							<html:select property="nj" style="width:90px"
								onchange="initZyList();initBjList();" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList();" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							<input type="hidden" name="xyV"
								value="<bean:write name="xydm" scope="request"/>" />
						</td>
						<td>
							&nbsp;&nbsp;专业：
						</td>
						<td align="left">
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList();" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							<input type="hidden" name="zyV" value="" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;班级：
						</td>
						<td align="left">
							<html:select property="bjdm" style="width:180px" styleId="bj" name="rs" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							<input type="hidden" name="bjV" value="" />
						</td>
						<td>
							&nbsp;&nbsp;入党时间：
						</td>
						<td align="left">
							<html:text property="rdsj" styleId="rdsj" readonly="true" name="rs"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;进入支部类型：
						</td>
						<td>
							<html:select property="jrlx" name="rs">
								<html:option value=""></html:option>
								<html:option value="0">新发展</html:option>
								<html:option value="1">组织关系恢复</html:option>
								<html:option value="2">组织关系转入</html:option>
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;进入时间：
						</td>
						<td>
							<html:text property="jrsj" styleId="jrsj" name="rs"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('jrsj','y-mm-dd');"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;组织关系转往：
						</td>
						<td>
							<html:text property="gxzw" name="rs" maxlength="10"/>
						</td>
						<td>
							&nbsp;&nbsp;转往时间：
						</td>
						<td>
							<html:text property="zwsj" styleId="zwsj"  name="rs"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zwsj','y-mm-dd');"/>
						</td>
					</tr>
				</table>
				</logic:notEmpty>
				<logic:notEmpty name="rs_zzgx">
				<table width="100%" class="tbstyle">
					<tr>
						<td>
							&nbsp;&nbsp;学号：
						</td>
						<td align="left">
							<html:text property="xh" styleId="xh" name="rs_zzgx" readonly="true"/>
						</td>
						<td>
							&nbsp;&nbsp;姓名：
						</td>
						<td align="left">
							<html:text property="xm" name="rs_zzgx" readonly="true"/>
						</td>

					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;性别:
						</td>
						<td>
							<html:select property="xb" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;年级：
						</td>
						<td align="left">
							<html:select property="nj" style="width:90px"
								onchange="initZyList();initBjList();" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList();" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							<input type="hidden" name="xyV"
								value="<bean:write name="xydm" scope="request"/>" />
						</td>
						<td>
							&nbsp;&nbsp;专业：
						</td>
						<td align="left">
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList();" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							<input type="hidden" name="zyV" value="" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;班级：
						</td>
						<td align="left">
							<html:select property="bjdm" style="width:180px" styleId="bj" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							<input type="hidden" name="bjV" value="" />
						</td>
						<td>
							&nbsp;&nbsp;入党时间：
						</td>
						<td align="left">
							<html:text property="rdsj" styleId="rdsj" disabled="true" name="rs_zzgx"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;进入支部类型：
						</td>
						<td>
							<html:select property="jrlx" styleId="jrlx" name="rs_zzgx" disabled="true">
								<html:option value=""></html:option>
								<html:option value="0">新发展</html:option>
								<html:option value="1">组织关系恢复</html:option>
								<html:option value="2">组织关系转入</html:option>
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;进入时间：
						</td>
						<td>
							<html:text property="jrsj" styleId="jrsj" disabled="true" name="rs_zzgx"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('jrsj','y-mm-dd');"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;组织关系转往：
						</td>
						<td>
							<html:text property="gxzw" name="rs_zzgx" styleId="gxzw" disabled="true"/>
						</td>
						<td>
							&nbsp;&nbsp;转往时间：
						</td>
						<td>
							<html:text property="zwsj" styleId="zwsj" disabled="true" name="rs_zzgx"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('zwsj','y-mm-dd');"/>
						</td>
					</tr>
				</table>
				</logic:notEmpty>
			</fieldset>
			<logic:notEmpty name="rs">
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="showTips('处理数据中，请等待......');refreshForm('dtjs_zjcm.do?method=saveZzgx');"
						
						style="width:80px" id="buttonSave">
						确 认
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
				<logic:notEmpty name="rs_zzgx">
				<div class="buttontool">
					<button type="button" class="button2" disabled="true"
						onclick="showTips('处理数据中，请等待......');refreshForm('dtjs_zjcm.do?method=saveZzgx');"
						
						style="width:80px" id="buttonSave">
						确 认
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="editBtn()"
						style="width:80px" id="buttonEdit">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="ok">
					<script>
    					alert("添加信息成功！");
    					dialogArgumentsQueryChick();
						Close();
   					 </script>
				</logic:equal>
			<logic:equal name="update" value="no">
					<script>
    					alert("添加失败失败！");
   					 </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
