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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function bgTj(){
		if($("lx").value == ""){
			alert("请确定项目类型！！");
			return false;
		}
		if($("xmmc").value == ""){
			alert("请确定项目名称！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('/xgxt/xmlgszbgtj.do?method=jhzjUpdate&doType=save');
		$("buttonSave").disabled=true;
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
		<html:form action="/xmlgszbgtj" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<span id="tipFollow"><bean:write name="title"/></span>
			</div>
		</div>
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<fieldset>
				<legend>
					安全报告
				</legend>
				<table align="center" width="100%" class="tbstyle">
					<tr>
						<td align="right" style="width:20%">
							职工号：
						</td>
						<td align="left" style="width:30%">
							<bean:write name="rs" property="zgh"/>
						</td>
						<td align="right" style="width:20%">
							部门名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="bmmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" style="">
							职务名称：
						</td>
						<td align="left">
							<bean:write name="rs" property="zwmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" style="">
							联系电话：
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdh"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							项目类型：
						</td>
						<td align="left">
							<html:select property="lx" style="" onchange="">
								<html:option value=""></html:option>
								<html:options collection="lxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" style="">
							项目名称：
						</td>
						<td align="left">
							<html:text property="xmmc" style="" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							内容：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="bgnr" style="width:100%" rows="5" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							备注：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="bz" style="width:100%" rows="5" onblur="chLeng(this,150)"></html:textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">
				<logic:empty name="doType">
				<button type="button" class="button2"
						onclick="bgTj();"
						style="width:80px" id="buttonSave">
						保 存
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:empty>
				<logic:notEmpty name="doType">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
				</button>
				</logic:notEmpty>
			</div>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    //dialogArgumentsQueryChick();
					//Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
