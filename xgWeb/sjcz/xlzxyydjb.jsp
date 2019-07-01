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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();changezx();changelx();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xzb-xymc" />
				<input type="hidden" id="readonlyEle" name="readonlyEle"
					value="xm-xb-xzb-xymc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-xzb-ssbh-sjhm-lxdzxx" />
				<input type="hidden" id="url" name="url" value="/sjcz/xlzxyydjb.jsp" />
				<fieldset>
					<legend>
						心理普查信息维护
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									心理普查信息维护
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display: none">
									选择
								</button>
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								出生年月：
							</td>
							<td align="left">
								<html:text name='rs' property="csrq" styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xymc" />
							</td>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="xzb" styleId="xzb" />
							</td>
						</tr>
						<tr>
							<td align="right">
								籍贯：
							</td>
							<td align="left">
								<html:text name='rs' property="gjxx" styleId="sjhm" />
							</td>
							<td align="right">
								邮箱：
							</td>
							<td align="left">
								<html:text name='rs' property="dzyx" styleId="lxdzxx" />
							</td>
						</tr>
						<tr>
							<td align="right">
								宿舍：
							</td>
							<td align="left">
								<html:text name='rs' property="ssld" styleId="ssbh" />
							</td>
							<td align="right">
								电话：
							</td>
							<td align="left">
								<html:text name='rs' property="dhhm" styleId="sjhm" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								你是否接受过本中心咨询：
							</td>
							<td>
								<html:select name="rs" property="sfzx">
									<html:option value="是"></html:option>
									<html:option value="否"></html:option>
								</html:select>
							</td>
							<td align="right">
								是否做过心理测验：
							</td>
							<td colspan="3">
								<html:select name="rs" property="sfxlcs">
									<html:option value="是"></html:option>
									<html:option value="否"></html:option>
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								咨询的时间、地点及老师:
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='sczxxx' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								在何处做的心理测验及测验的名称:
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xlxsyy' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr>
							<td align="right">
								你来咨询是：
							</td>
							<td >
								<html:select name="rs" property="zxyy" onchange="changezx();">
									<html:option value="自动来访"></html:option>
									<html:option value="老师建议"></html:option>
									<html:option value="同学介绍"></html:option>
									<html:option value="其他"></html:option>
								</html:select>
							</td>
							<td align="right">
								其他：
							</td>
							<td align="left" >
								<html:text name='rs' property="qtzxxx" styleId="qtzxxx" readonly="true"/>
							</td>
						</tr><tr>
							<td align="right">
								来询问题是：
							</td>
							<td >
								<html:select name='rs' property="zxmd" onchange="changelx();"> 
									<html:option value="学习问题 "></html:option>
									<html:option value="老人际关系"></html:option>
									<html:option value="经济问题"></html:option>
									<html:option value="家庭问题"></html:option>
									<html:option value="个人发展"></html:option>
									<html:option value="其他"></html:option>
								</html:select>
							</td>
							<td align="right">
								其他：
							</td>
							<td align="left">
								<html:text name='rs' property="qtwtxx" styleId="ssbh" readonly="true"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								你期待从咨询中得到什么样的帮助:
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='ddnxbz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width: 80px" id="buttonModi">
							修 改
						</button>
						<button type="button" class="button2" onclick="dataDoSave('xh')"
							style="width: 80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width: 80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
		<script language="javascript">
		function changezx(){
			var qqzx = document.getElementById("zxyy").value;
			if("其他" == qqzx){
			document.forms[0].qtzxxx.readOnly = false;
			}else{
			document.forms[0].qtzxxx.readOnly = true;
			document.getElementById("qtzxxx").value = "";
			}
		}
			function changelx(){
			var qqlx = document.getElementById("zxmd").value;
			if("其他" == qqlx){
			document.forms[0].qtwtxx.readOnly = false;
			}else{
			document.forms[0].qtwtxx.readOnly = true;
			document.getElementById("qtwtxx").value = "";
			}
		}
	</script>
	</body>
</html>
