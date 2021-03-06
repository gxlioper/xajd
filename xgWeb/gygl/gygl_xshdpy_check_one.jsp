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
					当前所在位置： 公寓管理 - 工程里弄 - 学生活动评优审核
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk" value="<bean:write name='pk' scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name='pkValue' scope="request"/>" />
					<table width="85%" id="rsT" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>单个学生审核</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									学号：
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</td>
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
									<bean:write name='rs' property="sjh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									寝室电话：
								</td>
								<td align="left">
									<bean:write name='rs' property="qsdh" />
								</td>
								<td align="right">
									楼栋名称：
								</td>
								<td align="left">
									<bean:write name='rs' property="ldmc" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									寝室号：
								</td>
								<td align="left">
									<bean:write name='rs' property="qsh" />
								</td>
								<td align="right">
									申请种类：
								</td>
								<td align="left">
									<bean:write name='rs' property="sqzl" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									担任职务：
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="sqrzw" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									主要事迹：
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="zysj" />
								</td>
							</tr>
							<logic:equal value="lz" name="lz">
							<tr style="height:22px;" id="lztj">
								<td align="right">
									楼长推&nbsp;&nbsp;&nbsp;<br>荐意见：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="lztjyj" rows="4" cols="50"/>
								</td>
							</tr>
							</logic:equal>
							
							<logic:equal value="teacher" name="userOnLine" scope="session">
							<tr style="height:22px;" id="lztj">
								<td align="right">
									楼长推&nbsp;&nbsp;&nbsp;<br>荐意见：
								</td>
								<td colspan="3" align="left">
									<bean:write name='rs' property="lztjyj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									是否通过：
								</td>
								<td align="left">
									<html:select name="rs" property="sfsh">
										<html:options collection="chkList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<td align="right">
								</td>
								<td align="left">
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									学校意见：
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="xxyj" rows="4" cols="50"/>
								</td>
							</tr>
							</logic:equal>
							<logic:present name="ptxs">
								<tr style="height:22px">
								<td align="center" colspan="4">
									<font color="red" size="3" >你没有权限</font>
								</td>
							</tr>
							</logic:present>
						</table>
				<logic:notPresent name="ptxs">
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="SaveCheck('/xgxt/CheckSave.do');return false;">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;">
						关 闭
					</button>
				</div>
				</logic:notPresent>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</body>
</html>
