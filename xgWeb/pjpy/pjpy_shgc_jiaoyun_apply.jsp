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
<html:html>
<head>
	<title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<body>
	<html:form action="/pjpy_apply_bg">
		<input type="hidden" name="redirect" id="redirect" value="true">
		<input type="hidden" name="variable" id="variable" value="none">
		<!--input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" /-->
		<input type="hidden" name="url" id="url"
			value="/pjpy/pjpy_shgc_jiaoyun_apply.jsp">
		<input type="hidden" name="jxjlx" id="jxjlx" value="jiaoyun">
		<input type="hidden" name="jxjdm" id="jxjdm"
			value="<bean:write name="jxjdm"/>">
		<logic:present name="dboperation">
			<logic:equal value="true" name="dboperation">
				<script type="text/javascript">
         	alert("保存成功！");
         </script>
			</logic:equal>
			<logic:equal value="false" name="dboperation">
				<script type="text/javascript">
         	alert("保存失败！");
         </script>
			</logic:equal>
		</logic:present>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 － 奖学金申请 － 填写申请表
			</div>
		</div>
		<table width="99%" class="tbstyle" align="center">
			<thead>
				<tr>
					<td colspan="10" align="center" style="font-size:14px">
						上海工程技术大学交运奖学金申请表
					</td>
				</tr>
			</thead>
			<tr>
				<logic:equal value="teacher" name="userOnLine" scope="session">
					<td width="10%">
						学号
					</td>
					<td colspan="2">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
						<button onclick="showTopWin('stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
				</logic:equal>
				<logic:notEqual value="teacher" name="userOnLine" scope="session">
					<td>
						学号
					</td>
					<td colspan="2">
						<html:text name="rs" property="xh" readonly="true" />
					</td>
				</logic:notEqual>
				<td colspan="7">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="25">
					姓名
				</td>
				<td colspan="2">
					<html:text name="rs" property="xm" style="width:98%"
						readonly="true" />
				</td>
				<td width="14%">
					<div align="center">
						性别
					</div>
				</td>
				<td width="12%">
					<html:text name="rs" property="xb" style="width:98%"
						readonly="true" />
				</td>
				<td width="13%">
					出生年月
				</td>
				<td colspan="4">
					<html:text name="rs" property="csny" style="width:98%"
						readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="25">
					政治面貌
				</td>
				<td colspan="2">
					<html:text name="rs" property="zzmm" style="width:98%" />
				</td>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td>
					<html:text name="rs" property="mzmc" style="width:98%" />
				</td>
				<td>
					入学年月
				</td>
				<td colspan="4">
					<html:text name="rs" property="rxny" style="width:98%"
						readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="25">
					院、系
				</td>
				<td colspan="2">
					<html:text name="rs" property="xymc" style="width:98%"
						readonly="true" />
				</td>
				<td>
					<div align="center">
						专业
					</div>
				</td>
				<td colspan="6">
					<html:text name="rs" property="zymc" style="width:98%"
						readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="25">
					家庭地址
				</td>
				<td colspan="2">
					<html:text name="rs" property="email" style="width:98%" />
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="6">
					<html:text name="rs" property="lxdh" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25">
					就业单位
				</td>
				<td colspan="9">
					<html:text name="rs" property="email" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="50">
					曾获何种奖励
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="zysj" rows='6' style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25" colspan="3">
					入学以来的学习成绩
				</td>
				<td colspan="7">
					优秀
					<html:text name="rs" property="njrs" size="3" />
					门&nbsp;&nbsp;&nbsp;&nbsp;良好
					<html:text name="rs" property="njpm" size="3" />
					门 合格
					<html:text name="rs" property="njrs" size="3" />
					门&nbsp;&nbsp;&nbsp;&nbsp;不及格
					<html:text name="rs" property="njpm" size="3" />
					门
				</td>
			</tr>
			<logic:present name="cjList">
				<tr>
					<td rowspan="<bean:write name='cjRow'/>">
						主要课程成绩
					</td>
					<td>
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
					<td>
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
					<td colspan="2">
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
				</tr>
				<logic:iterate id="e" name="cjIter" indexId="index">
					<tr>
						<td>
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
						<td>
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 1) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 1) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 2) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 2) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>

			<logic:notPresent name="cjList">
				<tr>
					<td rowspan="5">
						主要课程成绩
					</td>
					<td>
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
					<td>
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
					<td colspan="2">
						课程
					</td>
					<td colspan="2">
						成绩
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
			</logic:notPresent>
			<tr>
				<td height="50">
					申请奖学金理由
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="sqly" rows='6' style="width:98%" />
				</td>
			</tr>

			<logic:equal value="xy" name="userType" scope="session">
				<tr>
					<td height="50">
						<bean:message key="lable.xsgzyxpzxy" />或系评审小组意见
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="xyshyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="admin" name="userType" scope="session">
				<tr>
					<td height="50">
						学校或单位评审意见
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="xxshyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
				<tr>
					<td height="50">
						评审委员会复审意见
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="shjyyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
			</logic:equal>
			<tr>
				<td height="50">
					备注
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="beizhu" rows='6'
						style="width:98%" />
				</td>
			</tr>
		</table>
	</html:form>
	<div class="buttontool">
		<button class="button2" onclick="shgcPriseApplication()">
			提交申请
		</button>
		<button class="button2" onclick="shgcPriseAppicationPrint()">
			报表打印
		</button>
	</div>
</body>
</html:html>
