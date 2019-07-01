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
    <base target="_self">
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
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/sztz_join_apply" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：素质拓展 - 拓展活动参加申请 - 填写申请表
				</div>
			</div>
			<logic:present name="isPASS">
         	<logic:match value="is" name="isPASS">
         		<script language="javascript">
	         		alert("该拓展活动申请正在审核中或已通过，不能再申请！");
	         	</script>
	         </logic:match>
           </logic:present>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("保存成功！");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sztz_join_apply.do" />
				<fieldset>
					<legend>
						填写申请表
					</legend>
					<table width="100%" id="rsT" class="tbstyle">
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
									 onkeypress="if(event.keyCode == 13) return false;" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
									<input type="text" id="xh" name="xh"  value="<bean:write name='rs' property="xh" />" readonly="readonly" />
								</td>
							</logic:equal>
							<td align="right">
								<font color="red">*</font>拓展活动(项目)：
							</td>
							<td align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									onchange="refreshForm('/xgxt/sztz_join_apply.do')"
									style="background-color:#FFFFFF;">
									<html:option value=""></html:option>
									<html:options collection="tzxmList" property="tzxmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								年度：
							</td>
							<td align="left">
								<bean:write name='rs' property="nd" />
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
								学年：
							</td>
							<td align="left">
								<bean:write name='rs' property="xn" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								<bean:write name='rs' property="xq" />
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
								所属科目：
							</td>
							<td align="left">
								<bean:write name='rs' property='kmm' />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
								活动开始时间：
							</td>
							<td align="left">
								<bean:write name='rs' property='xmkssj' />
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
								活动结束时间：
							</td>
							<td align="left">
								<bean:write name='rs' property="xmjssj" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								活动组织部门：
							</td>
							<td align="left">
								<bean:write name='rs' property='zzbmmc' />
							</td>
							<td align="right">
								<font color="red">*</font>申请理由：
							</td>
							<td>
								<html:select name="rs" property="lydm" styleId="lydm"
									onchange="refreshForm('/xgxt/sztz_join_apply.do')"
									style="background-color:#FFFFFF;">
									<html:option value=""></html:option>
									<html:options collection="lyList" property="lydm"
										labelProperty="lymc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
							执行单位： 
							</td>
							<td align="left">
								<bean:write name='rs' property='xsdw' />
							</td>
							<td align="right">								
							</td>
							<td>								
							</td>
						</tr>
						<tr align="left" style="height:30px">
							<td align="right">
								申请者条件：
							</td>
							<td colspan="3" align="left">
								<bean:write name='rs' property='sqztj' />
							</td>
						</tr>
						<tr align="left" style="height:30px">
							<td align="right">
								活动内容：
							</td>
							<td colspan="3" align="left">
								<bean:write name='rs' property='xmnr' />
							</td>
						</tr>
						<tr align="left" style="height:30px">
							<td align="right">
								备注：
							</td>
							<td colspan="3" align="left">
								<bean:write name='rs' property='bz' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								申请理由：
							</td>
							<td colspan="3" style="width:88%">
								<html:textarea name='rs' property='lynr' styleId="lynr"
									 rows='5' style="width:99%"/>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="SztzDataDoSaveApply('/xgxt/sztz_applySave.do','xmdm','hdcjsq','xh-xmdm-lydm')">
							提 交 申 请
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expAppTab('rsT','拓展活动参加申请表','')">
							打 印 报 表
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
