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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/sztz_modiData" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：素质拓展 - 拓展活动参加申请 - 填写申请表
				</div>
			</div>
			<span id="tipFollow" style="display:none"></span>
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
				<input type="hidden" id="doType" name="doType"
					   value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					   value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="disableEle" name="disableEle"
					   value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" 
				       value="/sjcz/tzhd_sq_modi.jsp" />
 			    <input type="hidden" id="pk" name="pk"
					   value="<bean:write name="pk" scope="request"/>" />
			    <input type="hidden" id="act" name="act"
					   value="<bean:write name="act" scope="request"/>" />			
				<input type="hidden" id="realTable" name="realTable"
					   value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					   value="<bean:write name="tableName" scope="request"/>" />					
 				<fieldset>
					<legend>
						填写申请表
					</legend>
					<table width="100%" class="tbstyle">
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
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										选择
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right">
									<font color="red">*</font><bean:write name='rs' property="pk" />学号：
								</td>
								<td align="left">
									<bean:write name='rs' property="xh" />
								</td>
							</logic:equal>
							<td align="right">
								<font color="red">*</font>拓展活动(项目)：
							</td>
							<td align="left">
								<bean:write name='rs' property="xmmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
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
								<html:text name='rs' property="xb" styleId="xb" />
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
								<html:text name='rs' property="nj" styleId="nj" />
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
								<html:text name='rs' property="xymc" styleId="xy" />
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
								<html:text name='rs' property="zymc" styleId="zy" />
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
								<html:text name='rs' property="bjmc" styleId="bj" />
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
							<td align="right">&#25191;&#34892;&#21333;&#20301;&#65306; 
							</td>
							<td align="left">
								<bean:write name='rs' property='xsdw' />
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
							<td colspan="3">
								<html:textarea name='rs' property='sqly' styleId="sqly"
									style="width:99%" rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button class="button2"
						onclick="SztzDataDoSaveApply('/xgxt/sztz_modiData.do','pk','sztz_hdcjsqb')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
