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
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
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
	<script type='text/javascript'
			src='/xgxt/dwr/interface/dateUtil.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript">
		function save(){
		   var bz = document.getElementById('bz').value;
		   if(bz.length > 300){
		   		alert('备注不能大于300字！');
		   	   	return false;
		   }
		   if($('act').value == 'add'){
		      document.forms[0].action = "/xgxt/xsgly.do?method=operateXsglyxx&doType=add";
		   }else{
		   	  document.forms[0].action = "/xgxt/xsgly.do?method=operateXsglyxx&doType=update";
		   }	   
		   document.forms[0].submit();
		}	

	</script>
</head>
<body>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：日常事务 - 值班人员管理 - 
				<logic:equal value="add" name="act">
					学生管理员增加
				</logic:equal>
				<logic:equal value="update" name="act">
					学生管理员修改
				</logic:equal>
			</div>
		</div>
		<html:form action="/xsgly" method="post">
			<input type="hidden" name="pkVStr" id="pkVStr" value="${pkvals}"/>
			<input type="hidden" name="act" id=act value="${act}"/>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="35%">
						<html:text name="rs" property="xh" readonly="true"></html:text>
						<logic:equal value="add" name="act">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">选择
							</button>
						</logic:equal>			
					</td>
					<td align="right" width="15%">
						姓名：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right" width="15%">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>				
				<tr>
					<td align="right" width="15%">
						专业名称：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right" width="15%">
						班级名称：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>	
				<tr>
					<td align="right" width="15%">
						是否启用：
					</td>
					<td align="left" width="35%">
						<html:select name="rs" property="sfqy">
							<html:option value="启用"></html:option>
							<html:option value="不启用"></html:option>
						</html:select>
					</td>
					<td align="right" width="15%">
					</td>
					<td align="left" width="35%">
					</td>
				</tr>			
				<tr>
					<td>
						<div align="center">
							备注：
							<br>
							<font color="red"><限300字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="bz" rows="8" styleId="bz"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="save()" id="buttonSave">
					保 存
				</button>		
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="window.close()" id="buttonSave">
					关 闭
				</button>
			</div>
		</html:form>
	
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
			 Close();
			 dialogArgumentsQueryChick();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('操作失败！');
			  </script>
	</logic:equal>
</body>
</html:html>
