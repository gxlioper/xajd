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
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function SaveSq(){
		   var sqly = document.getElementById('sqly').value;
		   if(sqly.length > 1000){
		   		alert('申请理由不能大于1000字！');
		   	   	return false;
		   }
		   document.forms[0].action = "/xgxt/xssfzbb.do?method=xssfzbbsqb&doType=save";
		   document.forms[0].submit();
		}	
		function changeView(obj){
			var bbzt = document.getElementById('bbzt');
			if('已补办好' == bbzt.value){
				$('sfxs').style.display = 'block';
			}else{
				$('sfxs').style.display = 'none';
			}
		}	
	</script>
<body>
	<logic:equal value="false" name="sfksq">
		<br>
		<br>
		<br>
		<p align="center">
			<font color="red" size="2">您不是常住户口，不能申请身份证补办</font>
		</p>
	</logic:equal>
	<logic:notEqual value="false" name="sfksq">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：日常事务 - 学生身份证管理 - 补办申请
			</div>
		</div>
		<html:form action="/xssfzbb.do?method=xssfzbbsqb" method="post">
			<input type="hidden" name="url" value="/xssfzbb.do?method=xssfzbbsqb" />
			<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
			<input type="hidden" name="sqsj" value="${rs.sqsj}" />
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="35%">
						<html:text name='rs' property="xh" readonly="readonly"
							styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)"/>
						<logic:notEqual value="stu" name="userType">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
					</td>
					<td align="right" width="15%">
						姓名：
					</td>
					<td align="left" width="35%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>

				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						专业名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						班级名称：

					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">
						学年：

					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							学期：
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xqmc" />
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申报理由
							<br>
							<font color="red"><限1000字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="sqly" rows="8" styleId="sqly"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
					提交申请
				</button>
				&nbsp;&nbsp;
				<logic:notEqual value="stu" name="userType">
				<button type="button" class="button2" onClick="window.close()" id="buttonSave">
					关 闭
				</button>
				</logic:notEqual>
			</div>
		</html:form>
	</logic:notEqual>
	<logic:equal value="stu" name="userType">
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
		</script>
	</logic:equal>
	</logic:equal>
	<logic:notEqual value="stu" name="userType">
		<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
			 if(window.dialogArguments){
			 	window.dialogArguments.document.getElementById('search_go').onclick();
			 }
			 window.close();
		</script>
		</logic:equal>
	</logic:notEqual>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('操作失败！');
		</script>
	</logic:equal>
</body>
</html:html>
