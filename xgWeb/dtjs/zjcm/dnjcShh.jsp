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
	function saveSwcl(url,pk){
		
		var zht;
		if (pk == "wtg"){
			zht = "未通过";
		}else if (pk == "tg"){
			zht = "通过";
		}else if (pk == "wsh"){
			zht = "未审核"
		}

		if (confirm("该事务状态要修改为"+zht+"，确认吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
			showTips('处理数据中，请等待......');
			refreshForm(url);
		}
	}
	</script>
	<body onload="checkWinType();">
		<input type="hidden" id="method" name="method" value="swclShh" />
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope = "session"/>" />
		<html:form action="/dtjs_zjcm" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					组织审核
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
							&nbsp;&nbsp;送审时间：
						</td>
						<td align="left">
							<html:text property="jcsj" styleId="jcsj" readonly="true" name="rs"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;奖惩类型：
						</td>
						<td>
							<html:select property="jclx"  styleId="lx" name="rs">
								<html:option value=""></html:option>
								<html:options collection="lxList" property="lxdm"
									labelProperty="lxmc" />
							</html:select>
						</td>
						<td>
							&nbsp;&nbsp;奖惩理由：
						</td>
						<td>
							<html:select property="jcly"  styleId="ly" name="rs">
								<html:option value=""></html:option>
								<html:options collection="lyList" property="lydm"
									labelProperty="lymc" />
							</html:select>	
						</td>
						
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;审核状态：
						</td>
						<td>
							<logic:notPresent name="db">
								<html:select property="shlx" style="width:150px" name="rs" styleId="shlx">
									<html:option value="wsh">未审核</html:option>
									<html:option value="tg">通过</html:option>
									<html:option value="wtg">未通过</html:option>
								</html:select>
							</logic:notPresent>
							<logic:present name="db">
								<html:select property="shlx" style="width:150px" name="rs" disabled="true" styleId="shlx">
									<html:option value="wsh">未审核</html:option>
									<html:option value="tg">通过</html:option>
									<html:option value="wtg">未通过</html:option>
								</html:select>
							</logic:present>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="left">
							&nbsp;&nbsp;备注：<br>
							<font color="blue">限200个字符</font>
						</td>
						<td colspan="4">
							<logic:notPresent name="db">
								<html:textarea property="bz" style="width:430px" rows='10' name="rs"  />
							</logic:notPresent>
							<logic:present name="db">
								<html:textarea property="bz" style="width:430px" rows='10' name="rs" readonly="true" />
							</logic:present>
						</td>
					</tr>
				</table>
				</logic:notEmpty>
			</fieldset>
				<div class="buttontool">
					<logic:notPresent name="db">
					<button type="button" class="button2"
						onclick="saveSwcl('dtjs_zjcm.do?method=saveDnjcshh',document.getElementById('shlx').value);"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					</logic:notPresent>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			<logic:present name="tg">
				<logic:equal name="tg" value="tg">
					<script>
    					alert("该学生申请已通过");
   					 </script>
				</logic:equal>
			</logic:present>
			<logic:present name="update">
				<logic:equal name="update" value="ok">
					<script>
    					alert("审核成功！");
    					dialogArgumentsQueryChick();
						Close();
   					 </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    					alert("审核失败！");
    					Close();
   					 </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
