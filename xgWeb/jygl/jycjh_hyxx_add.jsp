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
		<base target="_self">
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>	
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function xljk_xlxhhy_save(){
			var hyxh=document.all["hyxh"].value;
			if ( hyxh==""){
				alert ("请填写会员学号");
				document.all["hyxh"].focus();
				return false;
			}
			var hyxm=document.all["hyxm"].value;
			if ( hyxm==""){
				alert ("请填写会员姓名");
				document.all["hyxm"].focus();
				return false;
			}
			var sex=document.all["xb"].value;
			if ( sex==""){
				alert ("请填写会员性别");
				document.all["xb"].focus();
				return false;
			}
			var csrq=document.all["csrq"].value;
			if ( csrq==""){
				alert ("请填写会员出生日期");
				document.all["csrq"].focus();
				return false;
			}
			var xymc=document.all["xymc"].value;
			if ( xymc==""){
				alert ("请填写<bean:message key="lable.xsgzyxpzxy" />名称");
				document.all["xymc"].focus();
				return false;
			}
			var bjmc=document.all["bjmc"].value;
			if ( bjmc==""){
				alert ("请填写班级名称");
				document.all["bjmc"].focus();
				return false;
			}
			var sjhm=document.all["sjhm"].value;
			if ( sjhm==""){
				alert ("请填写会员手机号码");
				document.all["sjhm"].focus();
				return false;
			}
			var qsdh=document.all["qsdh"].value;
			if ( qsdh==""){
				alert ("填写会员寝室电话");
				document.all["qsdh"].focus();
				return false;
			}
			var hybh=document.all["hybh"].value;
			if ( hybh==""){
				alert ("请填写会员编号");
				document.all["hybh"].focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			underDealWith();
			refreshForm('/xgxt/jyglInfo.do?method=add_hyxx&doType=add');
		}
	</script>
	</head>	
	<body>
		<html:form action="/jyglInfo.do?method=add_hyxx" method="post">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/jygl/jycjh_hyxx_add.jsp" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业促进会 - 会员基本信息 - 增加会员基本信息
				</div>
			</div>
			<table class="tbstyle" style="width:100%">
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>学 号：
					</td>
					<td align="left">
						<html:text name='rs' property="hyxh" styleId="xh" onblur=""
							readonly="true" />
						<button
							onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
					<td align="right">
						姓 名：
					</td>
					<td align="left">
						<html:text name="rs" property="hyxm" styleId="hyxm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2" readOnly="true">
						性 别：
					</td>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>出 生 日 期：
					</td>
					<td align="left">
						<html:text name='rs' property="csrq" styleId="csrq"
							 style="cursor:hand;"
							onclick="return showCalendar('csrq','y-mm-dd');" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						学 院：
					</td>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc" />
					</td>
					<td align="right">
						班 级：
					</td>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>手 机 号 码：
					</td>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" />
					</td>
					<td align="right">
						<font color="red">*</font>寝 室 电 话：
					</td>
					<td align="left">
						<html:text name="rs" property="qsdh" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<font color="red">*</font>会 员 编 号：
					</td>
					<td align="left">
						<html:text name="rs" property="hybh" />
					</td>

					<td align="right">
						<font color="red">*</font>职 务：
					</td>
					<td align="left">
						<html:text name="rs" property="zw" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						备 注：
					</td>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" />
					</td>
				</tr>
			</table>				
			<div id="tmpdiv"></div>
			<div class="buttontool" align="center">
				<button class="button2" onclick="xljk_xlxhhy_save()" style="width:80px">
					保 存
				</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px">
					关 闭
				</button>
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="bh exits">
			<script>alert("编号已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="stu exits">
			<script>alert("该学生已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="insert success">
			<script>
				alert("增加成功!");
				dialogArgumentsQueryChick();
				Close();	
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert fail">
			<script>
				alert("增加失败!");
				document.getElementById("tmpdiv").innerHTML = "";
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
