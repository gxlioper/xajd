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
     function disabledBjzw(){
         var len = document.getElementById("pk").value.length;
         if(len>0){
              document.getElementById("bjgb").disabled="false";
         }
         
     }
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/show_classStudentCadre_list" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal value="12061" name = "xxdm" scope="session">
						当前位置：思政队伍-班级情况-学生总会干部
					</logic:equal>
					<logic:notEqual value="12061" name = "xxdm" scope="session">
						当前位置：思政队伍-班级情况-学生会干部
					</logic:notEqual>
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
				<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
				<input type="hidden" name="bjdm" property="bjdm" value="<bean:write name="rs" property="bjdm"/>" />
				<input type="hidden" name="url" property="url" value="/xshgb_updata.do?act=xshzw" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写学生会干部表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/sxjy_stu_info.do?act=xshzw&pk=<bean:write name='rs' property="pk" />',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						<td align="right" >
							<font color="red">*</font>担任职务
						</td>
						<td align="left">						
						<html:select property="bjgb" style="width:180px" styleId="bjgb">
							<html:option value=""></html:option>
							<html:options collection="xshgbList" property="xshgbdm"
								labelProperty="xshgbmc" />
						</html:select>				        
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							班级
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学生姓名：
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							性别
						</td>
						<td align="left" >
							<bean:write name="rs"  property="xb"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							任职开始时间：
						</td>
						<td align="left">
								<html:text name = "rs" property="kssj" styleId="zhdrq"
								onblur="dateFormatChg(this)" style="cursor:hand;" readonly = "true"
								onclick="return showCalendar('zhdrq','y-mm-dd');" />
						<td align="right" >
							任职结束时间：
						</td>
						<td align="left" >
								<html:text name = "rs" property="jssj" styleId="zhdrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"  readonly = "true"
								onclick="return showCalendar('jssj','y-mm-dd');" />
						</td>
					</tr>
					<tr style="height:22px">
					<td align="right" >
							联系方式
						</td>
						<td align="left" colspan="6">
							<html:text name="rs"  property="lxfs" style="width: 400px" maxlength="20"/>
					</td>
					</tr>
					<tr style="height:22px">
					<td align="right" >
							备注
						</td>
						<td align="left" colspan="6">
							<html:text name="rs"  property="bz" style="width: 400px" maxlength="40"/>
					</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="sxjyCommonSave('/xgxt/xshgb_save.do?type=xshzw&','bjgb-xh','xshzw')">
						保  存
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>