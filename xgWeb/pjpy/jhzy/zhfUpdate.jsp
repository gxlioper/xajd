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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
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
	<script type="text/javascript">
	function saveSx(){
		var dyfsx = $("dyfsx").value;
		var zyfsx = $("zyfsx").value;
		var tyfsx = $("tyfsx").value;
		var jnfsx = $("jnfsx").value;
		var jcfsx = $("jcfsx").value;

		if((parseInt(dyfsx) + parseInt(zyfsx) + parseInt(tyfsx) + parseInt(jnfsx) + parseInt(jcfsx)) > 100){
			alert("各项合计不能超过100，请确认！！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		refreshForm('/xgxt/jhzy_zhf.do?method=zhfUpdate&doType=save');
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/chgPass" method="post">
			<div class="title">
				<div class="title_img">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					各分值上限
				</legend>
				<table width="80%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="2">
								比例设置
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="40%">
							德育分上限：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="dyfsx" style="width:30px" 
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							智育分上限：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="zyfsx" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							体育分上限：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="tyfsx" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							技能分上限：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="jnfsx" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%">
							奖惩分上限：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="jcfsx" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
						<button class="button2"
							onclick="saveSx();"
							id="buttonSave"
							style="width:80px">
							保 存
						</button>
						</td>
					</tr>
				</table>
			</fieldset>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
