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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var csly = document.getElementById('csly').value;
			if(("未审核" != xxsh ) && (userType != "admin")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(csly != null){
	         	if(csly.replace(/[^\u0000-\u00ff]/g, "**").length > 800){	         
	          		 alert("陈述理由不能大于800个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/nblg_xszz.do?method=knsshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/nblg_xszz.do?method=knsshSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 困难生审核 - 单个审核
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							出生年月
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csny"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							宿舍及宿舍电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="ssjssdh"/>
					</td>
					<td>
						<div align="center">
							手机号码
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrjysr"/>
					</td>
					<td>
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							 家庭住址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学生陈述申请理由
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							学年
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<td>
							<div align="center">
								民主评议
							</div>
						</td>
						<td>
							<html:select name="rs" property="mzpy">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								陈述理由
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="csly" rows='5'
								style="width:100%" onblur="yzdx(this,'csly')" />
							<input type="hidden" id="csly" name="csly" value="">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td>
							<div align="center">
								民主评议
							</div>
						</td>
						<td>
							<bean:write name="rs" property="mzpy" />
							<input type="hidden" id="mzpy" name="mzpy"
								value="<bean:write name='rs' property="mzpy" />" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								陈述理由
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="csly" />
							<input type="hidden" id="csly" name="csly"
								value="<bean:write name='rs' property="csly" />" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								学校审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
							<div align="center">
								&nbsp;
							</div>
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual name="userType" value="admin">
					<logic:equal name="rs" property="xxsh" value="未审核">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave">
							保 存
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="未审核">
						<button type="button" class="button2" onclick="yz()" style="width:80px"
							id="buttonSave" disabled="disabled">
							保 存
						</button>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="userType" value="admin">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
