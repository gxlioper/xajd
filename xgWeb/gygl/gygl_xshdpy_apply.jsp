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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/Ry_manager" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 工程里弄 - 学生活动评优申请表
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/Ry_manager.do" />
					<table width="85%" id="rsT" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>填写申请表</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right">
										<font color="red">*</font>学号：
									</td>
									<td align="left">
										<html:text name='rs' property="xh" styleId="xh"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right">
										<font color="red">*</font>学号：
									</td>
									<td align="left">
										<html:text property="xh" name="rs" styleId="xh"
											readonly="true"></html:text>
									</td>
								</logic:equal>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right">
									手机号码：
								</td>
								<td align="left">
									<html:text name='rs' property="sjh" styleId="sjh" onkeyup="chkInput(this,event)"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>寝室电话：
								</td>
								<td align="left">
									<html:text name='rs' property="qsdh" styleId="qsdh" onkeyup="chkInput(this,event)"/>
								</td>
								<td align="right">
									<font color="red">*</font>楼栋名称：
								</td>
								<td align="left">
									<html:select name="rs" property="lddm" styleId="lddm">
										<html:options collection="ldList" property="lddm" labelProperty="ldmc"/>
									</html:select>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>寝室号：
								</td>
								<td align="left">
									<html:text name='rs' property="qsh" styleId="qsh" onkeyup="chkInput(this,event)"/>
								</td>
								<td align="right">
									申请种类：
								</td>
								<td align="left">
									<html:select name="rs" property="sqzl" styleId="sqzl" >
										<html:option value="A">优秀楼长</html:option>
										<html:option value="B">优秀层长</html:option>
									</html:select>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									担任职务：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="sqrzw" styleId="sqrzw" rows="2" cols="80"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									主要事迹：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="zysj" rows="4" cols="80"/>
								</td>
							</tr>
							<logic:equal value="lz" name="lz">
							<tr style="height:22px;display: none;" id="lztj">
								<td align="right">
									楼长推&nbsp;&nbsp;&nbsp;<br>荐意见：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="lztjyj" rows="4" cols="80"/>
								</td>
							</tr>
							</logic:equal>
							<logic:equal value="teacher" name="userOnLine" scope="session">
							<tr style="height:22px">
								<td align="right">
									学校意见：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="xxyj" rows="4" cols="80"/>
								</td>
							</tr>
							</logic:equal>
						</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="SaveApply('xh-qsdh-qsh','/xgxt/dataSave.do')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expAppTab('rsT','','')">
						打 印 报 表
					</button>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
