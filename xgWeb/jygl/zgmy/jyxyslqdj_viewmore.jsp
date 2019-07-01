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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function saveJyxy(){
		var url = "viewJyxyLqdjInfo.do?act=save&lqqk=";
		var lqqk = document.getElementById("lqqk").value;
		if(document.getElementById("xh").value == ""){
			alert("请确认学号非空！");
			return false;
		}
		if(document.getElementById("jyxybh").value == ""){
			alert("请确认就业协议书编号非空！");
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm(url+lqqk);
	}
	</script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="url" name="url" value="/jygl/zgmy/jyxyslqdj_viewmore.jsp" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<fieldset>
			<legend>
				就业协议书领取登记详细信息
			</legend>
			<table width="100%" id="rsT" class="tbstyle">
				<logic:notPresent name="type">
				<tr style="height:22px">
					<td align="right" width="20%">
						学号:
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="right" width="20%">
						姓名：
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						就业协议书编号:
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="jyxybh" />
					</td>
				</tr>
				<logic:equal name="xxdm" value="10355">
					<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="newremark" rows="15"
							style="width:100%" readonly="true" />
					</td>
					</tr>
				</logic:equal>				
				<logic:notEqual name="xxdm" value="10355">
					<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="remark" rows="15"
							style="width:100%" readonly="true" />
					</td>
					</tr>
				</logic:notEqual>
				</logic:notPresent>
				<logic:present name="type">
					<logic:equal name="type" value="add">
					<tr style="height:22px">
					<td align="right" width="20%">
						学号:
					</td>
					<td align="left" width="30%">
						<html:text name='rs' property="xh" styleId="xh" />
						<logic:notEqual value="stu" name="userType" scope="session">
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
						</logic:notEqual>
					</td>
					<td align="right" width="20%">
						姓名：
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<logic:equal name="update" value="no">
				<tr style="height:22px">
					<td align="right">
						就业协议书编号:
					</td>
					<td align="left">
						<html:text name='rs' property="jyxybh" styleId="jyxybh" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
					</td>
					<td align="right">
						领取情况:
					</td>
					<td align="left">
						<input type="text" id="lqqk" value="未领取" readOnly="true">
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="update" value="yes">
				<tr style="height:22px">
					<td align="right">
						就业协议书编号:
					</td>
					<td align="left">
						<html:text name='rs' property="jyxybh" styleId="jyxybh" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
					</td>
					<td align="right">
						领取情况:
					</td>
					<td align="left">
						<html:select name='rs' property="lqqk" style="width:150px">
							<html:option value=""></html:option>
							<html:option value="未领取">未领取</html:option>
							<html:option value="已领取">已领取</html:option>
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="remark" rows="15"
							style="width:100%"/>
					</td>
				</tr>
				</logic:equal>
				</logic:present>
			</table>
			<logic:present name="type">
				<logic:equal name="type" value="add">
					<div class="buttontool">
						<button class="button2" style="width:80px" id="buttonSave"
							onclick="saveJyxy()">
							保 存
						</button>	
						&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</logic:equal>
			</logic:present>
		</fieldset>
		</html:form>
	</body>
</html>
<logic:present name="inserted">
	<logic:equal name="inserted" value="ok">
		<script>
    		alert("信息添加成功！");
    		dialogArgumentsQueryChick();
			Close();
   		</script>
	</logic:equal>
	<logic:equal name="inserted" value="no">
		<script>
    		alert("信息添加失败！");
   		</script>
	</logic:equal>
</logic:present>
