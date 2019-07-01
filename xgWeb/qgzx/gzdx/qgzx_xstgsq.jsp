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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
			<div class="title_img" id="title_m">
				当前位置：勤工助学 - 申请 - 退岗申请
			</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("您输入的学号无效!");
    				</script>
				</logic:equal>
					<logic:notEqual name="gwExists" value="yes">
						<script>
    					alert("必须是勤工助学在岗学生才能申请辞岗!");
    					</script>
					</logic:notEqual>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="url" name="url" value="/qgzxCgsq.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>填写退岗申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" width="300">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left" width="300">
								<input type="hidden" name="xh" id="xh"
									value="<bean:write name='rs' property="xh" />">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							姓名：
						</td>
						<td align="left" width="300">
							<bean:write name='rs' property="xm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>辞去岗位：
						</td>
						<td align="left">
							<html:select property="gwdm" styleId="gwdmgwsbsj">
								<html:options collection="gwList" property="gwdmgwsbsj" labelProperty="gwdm"/>
							</html:select>
						</td>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学年：
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"></html:text>
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
							学期：
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"></html:text>
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
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
							本人联系电话：
						</td>
						<td align="left">
							<html:text property="lxdh" name="rs" styleId="lxdh" />
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							<font color="red">*</font>退岗原因：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="zgdzdx_cg_chkisDataExist('xh-sqly');">
							提 交 申 请
					</button>
					<logic:notPresent name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申请表','')">
							打 印 预 览
						</button>
					</logic:notPresent>
					<logic:present name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
							onclick="printReport('qgzx_bb_gwsqb.do?gwdm=')">
							打 印 预 览
						</button>
					</logic:present>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
				    alert("申请成功！");
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
						var html_reason= '<bean:write name="reason"/>';
					    alert("申请失败！"+html_reason);
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
