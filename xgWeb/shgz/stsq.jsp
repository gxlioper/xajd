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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">

	</script>
	<body onload="getStsqInfo()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getShgzTyDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPlan.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
		<script type="text/javascript" src="js/shgz/shgzFunction.js"></script>	
		<html:form action="/stgl" method="post">
		<input type="hidden" id="msg" name="msg" value="${msg}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：社会活动 - 社团管理 - 社团申请表
				</div>
			</div>
			<logic:notPresent name="inserted">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/stgl.do?method=zhsq" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name='pkValue' />" />
				<input type="hidden" id="doType" name="doType" value="<bean:write name='doType' />" />
				<input type="hidden" id="stdm" name="stdm" value="${rs.stdm }" />
				<input type="hidden" id="msg" name="msg" value="${msg}" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>社团名称：
						</td>
						<td align="left" colspan="2">
							<html:text property="stmc" onblur="getStdm();refreshForm('/xgxt/stgl.do?method=zhsq&doType=ch');"/>
						</td>
						<td align="right" >
							成立时间：
						</td>
						<td align="left" colspan="4">
							<html:text  property="clsj" styleId="clsj"  style="cursor:hand;"
								onclick="return showCalendar('clsj','y年mm月dd日');" />
						</td>
					</tr>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>负责人学号：
							</td>
							<td align="left" colspan="2">
								<html:text  property="xh" styleId="xh" name="rs"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" colspan="2">
								<bean:write name="rs" property="xh" /><html:hidden  property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							班级名称：
						</td>
						<td align="left" colspan="4">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							政治面貌：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="zzmmmc" />
						</td>
						<td align="right" >
							联系方式：
						</td>
						<td align="left" colspan="4">
							<html:text  property="lxfs" onkeypress="return onlyNum(this,20)" style="width:50%;ime-mode:disabled"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								个人简历：
							</td>
							<td colspan="7">
								<html:textarea  property='grjl' style="width:99%"
									rows='4' onblur="chLeng(this,200)"/>
							</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								<font color="red">*</font>社团活动内容：
							</td>
							<td colspan="7">
								<html:textarea  property='hdnr' style="width:99%"
									rows='4' onblur="chLeng(this,200)" />
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>指导老师：
						</td>
						<td align="left" colspan="2">
							<html:select  property="zgh" style="width:90px" name="rs"
											styleId="zgh" onchange="refreshForm('/xgxt/stgl.do?method=zhsq');">
											<html:option value=""></html:option>
											<html:options collection="szdwList" property="zgh"
												labelProperty="xm" />
							</html:select>
						</td>
						<td align="right" >
							老师性别：
						</td>
						<td align="left" colspan="4" id ="xb" name="rs">
							<bean:write name="rs" property="xb"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							部门职务：
						</td>
						<td align="left" colspan="2" id = "zwmc" name="rs">
							<bean:write name="rs" property="zwmc" />
						</td>
						<td align="right" >
							社团类别
						</td>
						<td align="left" colspan="4">
							<html:select property="lbdm" style="width:90px"
											styleId="lbdm">
											<html:option value=""></html:option>
											<html:options collection="stlbList" property="lbdm"
												labelProperty="lbmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							招收一年级男生：
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn1st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							招收一年级女生：
						</td>
						<td align="left" colspan="2">
							<html:text  property="zsm1st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							招收二年级男生：
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn2st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							招收二年级女生：
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsm2st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							招收三年级男生：
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn3st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							招收三年级女生：
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsm3st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
				<logic:notEmpty name = "doType">
					<tr style="height:22px">
						<td align="right">
							社团帐户用户名（要与用户对应）：
						</td>
						<td align="left" colspan="2">
							<html:text   property="yhm"/>
						</td>
						<td align="right">
							学校审核：
						</td>
						<td align="left" colspan="2">
							<html:select  property="shzt" style="width:90px"
											styleId="shzt">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								学生会社团部意见：
							</td>
							<td colspan="7">
							<html:textarea  property='xshyj' style="width:99%"
								rows='3' />
							</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
								院团委意见：
						</td>
						<td colspan="7">
							<html:textarea  property='ytwyj' style="width:99%"
								rows='3' />
						</td>
					</tr>
				</logic:notEmpty>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="szsxDataDoSave('/xgxt/stgl.do?method=saveStSq','stmc-xh-zgh-hdnr')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="showhzy">
					<button class="button2" onclick="window.open('sxjy_report.do?type=stsh&pk='+document.getElementById('pkValue').value)">
						打 印 报 表
					</button>
					</logic:notPresent>
				</div>
			</logic:notEmpty>
			
			</logic:notPresent>
			<logic:present name="inserted">
				<logic:present name="act">
				<logic:equal name="inserted" value="ok">
	    			<script>
	    				alert("提交成功！");
	    				if(opener){
	    					opener.document.getElementById("search_go").click();
							Close();
						}
	    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
		    			alert("提交失败！");
		    			if(opener){
		    				alert(opener);
		    				opener.document.getElementById("search_go").click();
							Close();
						}
    				</script>
				</logic:equal>
				</logic:present>
				<logic:notPresent name="act">
				<logic:equal name="inserted" value="ok">
	    			<script>
	    				alert("提交成功！");
	    				refreshForm('stgl.do?method=zhsq');
	    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
		    			alert("提交失败！");
		    			alert(''+document.getElementById('msg').value);
		    			refreshForm('stgl.do?method=zhsq');
    				</script>
				</logic:equal>
				</logic:notPresent>
			</logic:present>
		</html:form>
	</body>
</html>
