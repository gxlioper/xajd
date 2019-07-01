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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function checkRedio(){
		var sfxz = document.getElementById('jxmdsfqgx').value;
		if(sfxz == '0'){
			document.getElementById('close').checked=true;			
		}else{
			document.getElementById('open').checked=true;			
		}
	}
	//begin 骆嘉伟 2009/3/30
	function chkInputJx(){
		var num = document.getElementById('jxll').value;
		if(num.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("必需为数字！");
				return false;
			}
		if(num>100){
				alert("军训成绩比例不能大于100%！");
				return false;
			}	
		return true;
	}
</script>
	<body onload="checkRedio()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/drill_conf" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：军训管理 - 参数设置 - 其它参数设置
					</div>
				</div>
				<fieldset>
					<legend>
						参数设定
					</legend>
					<table width="80%" class="tbstyle" align="center">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									其它参数设置
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								军训学生名单数据取共享：
							</td>
							<td height="25" align="left">
								<INPUT type="hidden" value="<bean:write name="jxmdsfqgx"/>" id="jxmdsfqgx"/>
								<input type="radio" value="1" name="jxmdsfqgx" id="open">开
								<input type="radio" value="0" name="jxmdsfqgx" id="close">关
<%--								 <input type="radio" name="fpfs" value="bj">--%>
<%--								<html:radio property="jxmdsfqgx" value="1" idIndex="open">开</html:radio>--%>
<%--								<html:radio property="jxmdsfqgx" value="0" idIndex="close">关</html:radio>--%>
							</td>
						</tr>
						<!-- begin 骆嘉伟 2009/3/30 -->
						<logic:equal value="11647" name="xxdm" scope = "session">
						<tr align="center">
							<td width="45%" height="25" align="right">
								军训理论成绩比例：
							</td>
							<td height="25" align="left">
								<input type="text" name="jxll" id="jxll" width="10%" onkeyup="chkInputJx()" maxlength="3"/>%
							</td>
						</tr>	
						</logic:equal>	
						<logic:equal value="11647" name="xxdm" scope = "session">
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="refreshForm('drill_conf.do?method=saveDrillConf')">
										保存
									</button>
								</td>
							</tr>
						</thead>
						</logic:equal>
						<logic:notEqual value="11647" name="xxdm" scope = "session">		
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="refreshForm('drill_conf.do?method=saveDrillConf')">
										保存
									</button>
								</td>
							</tr>
						</thead>
						</logic:notEqual>
						<!-- end 骆嘉伟 2009/3/30 -->	
					</table>
				</fieldset>
				<logic:notEmpty name="result">
					<logic:equal name="result" value="true">
						<script>alert("保存成功!")</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
