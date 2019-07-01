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
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
		function docheck(){
			if(!mustFillALL()){
				return false;
			}
			underDealWith();
			refreshForm('/xgxt/cqkj_xszbb_update.do');
		}
		
		function doaddCqkj(){
			if(!mustFillALL()){
				return false;
			}
			underDealWith();
			refreshForm('/xgxt/cqkj_xszbb_input.do?doType=save');
		}
		
		function mustFillALL(){
			if(!checkXnNd('xn','nd')){
				return false;
			}
			var mustFill = "xn-nd-xq-xh-sqsj-bbsj-jbr-sflq-bbyy-hczm";
			var eles = mustFill.split("-");
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
			return true;
		}
	</script>
	<body>
		<html:form action="/cqkj_xszbb_input" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />
			<input type="hidden" id="url" name="url" value="/cqkj_xszbb_input.do" />
	
				<input type="hidden" id="sfzh" name="sfzh" value="<bean:write name='rs' property="sfzh" />" />
				<input type="hidden" id="csrq" name="csrq" value="<bean:write name='rs' property="csrq" />" />
				<input type="hidden" id="xz" name="xz" value="<bean:write name='rs' property="xz" />" />
				<input type="hidden" id="syd" name="syd" value="<bean:write name='rs' property="syd" />" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 学生证管理 - 补办
				</div>
			</div>
			<fieldset>
				<legend>
					学生证补办维护
				</legend>

				<table width="100%" class="tbstyle">
					<tr>
						<td align="right" width="15%" bgcolor="DOEOEE">
							<font color="red">*</font>学号：
						</td>
						<td align="left" width="35%">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="if(window.event.keyCode==13) queryxh();"
								readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
						<td align="right" width="15%" bgcolor="DOEOEE">
							姓名：
						</td>
						<td align="left" width="35%">
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>年度：
						</td>
						<td>	
							<html:select name="rs" property="nd" style="width:90px" styleId="nd">
									<html:option value=""></html:option>
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
							</html:select>
						</td>	
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							学制：
						</td>
						<td align="left">
							<bean:write name="rs" property="xz" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							年级：
						</td>
						<td align="left">
								<bean:write name="rs" property="nj"/>
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>申请时间：
						</td>
						<td align="left">
							<html:text name="rs" style="cursor:hand; width:110px;"
								styleId="sqsj" property="sqsj"
								onclick="return showCalendar('sqsj','y-mm-dd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							专业：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>补办时间：
						</td>
						<td align="left">
							<html:text name="rs" style="cursor:hand; width:110px;"
								styleId="bbsj" property="bbsj"
								onclick="return showCalendar('bbsj','y-mm-dd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							班级：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>经办人：
						</td>
						<td align="left">
							<html:select name="rs" property="jbr" style="width:90px"
								styleId="jbr">
								<html:option value=""></html:option>
								<html:options collection="jbrList" property="jbrgh"
									labelProperty="jbrxm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							身份证号：
						</td>
						<td align="left">
							<bean:write name="rs" property="sfzh" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							是否领取：
						</td>
						<td align="left">
							<html:select name="rs" property="sflq" styleId="sflq">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							生源地：
						</td>
						<td align="left">
							<bean:write name="rs" property="syd" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>补办原因：
						</td>
						<td align="left">
							<html:text name="rs" property="bbyy" styleId="bbyy"/>
						</td>
					</tr>
					<tr>
						<td align="right" bgcolor="DOEOEE">
							出生日期：
						</td>
						<td align="left">
							<bean:write name="rs" property="csrq" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>火车站名：
						</td>
						<td align="left">
							<html:text name="rs" property="hczm" styleId="hczm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="8"
								style="width:100%" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="4">
						<logic:notPresent name="isModi">
							<button type="button" class="button2"
								onclick="doaddCqkj();"
								style="width:80px" id="buttonSave">
								提交信息
							</button>
						</logic:notPresent>
						<logic:present name="isModi">
							<button type="button" class="button2"
								onclick="docheck();"
								style="width:80px" id="buttonUpdate">
								修改保存
							</button>
						</logic:present>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" type="reset" id="buttonReset">取消重填</button>&nbsp;&nbsp;
						<button type="button" class="button2" id="buttonClose"  onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">关闭窗口</button>
						</td>
					</tr>
				</table>
			</fieldset>
		</html:form>
		<div id="tmpdiv"></div>
		<logic:present name="result">
			<logic:equal value="ok" name="result">
				<script language="javascript">
				alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('query_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script language="javascript">
				alert("操作失败！");
				Close();
				window.dialogArguments.document.getElementById('query_go').click();
				</script>
			</logic:equal>
		</logic:present>
		
	</body>
</html>


