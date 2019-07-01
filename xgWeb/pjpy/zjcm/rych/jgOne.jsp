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
	<base target="_self">
	<script type="text/javascript">
	function xnRychSh(shzt){
		var url = "/xgxt/zjcm_rych.do?method=rychshOne&doType=save&shzt="+shzt;
		showTips('处理数据中，请等待......');
		$("buttonTg").disabled=true;
		$("buttonBtg").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	  function rychBbPrint(){
         var url = "/xgxt/zjcm_rych.do?method=rychDjb";
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/zjcm_rych" method="post">
			<input type="hidden" name="pk" id="pk" value="${pk}"/>
			<div class="title">
				<div class="title_img">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="4">
								荣誉称号审核
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="20%">
							学号：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xh"/>
						</td>
						<td align="right" width="20%">
							学年：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xn"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							姓名：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" width="20%">
							学期：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xqmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							性别：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" width="20%">
							荣誉称号：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="rychmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							担任职务：
						</td>
						<td align="left">
							<bean:write name='rs' property="zw" />
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							专业名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							${rs.zzmmmc }
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							班级名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right">
							早锻炼出勤率：
						</td>
						<td align="left">
							<bean:write name="rs" property="zdlcql"/>
						</td>
					</tr>
					<tr style="height:22px">
					<td align="right">
						综测排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhpm" />/<bean:write name='rs' property="bjrs" />
					</td>
					<td align="right">
						综测分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						智育排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyfpm" />/<bean:write name='rs' property="bjrs" />
					</td>
					<td align="right">
						智育分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						德育排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyfpm" />/<bean:write name='rs' property="bjrs" />
					</td>
					<td align="right">
						德育分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyf" />
					</td>
				</tr>
					<logic:notEmpty name="cfqk">
					<tr>
						<td align="center" colspan="4">
							在校违纪情况：
						</td>
					</tr>
					<logic:iterate name="wjcfList" id="s" indexId="index">
						<tr>
							<td align="left" colspan="4">
								<bean:write name="s" property="xn" />&nbsp;
								<bean:write name="s" property="xqmc" />&nbsp;
								<bean:write name="s" property="cflbmc" />&nbsp;
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					<tr style="height:22px">
					<td align="right">
						主要事迹：
					</td>
					<td colspan="3" align="left">
						<bean:write name = "rs" property="zysj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td colspan="3" align="left">
						<bean:write name = "rs" property="bz"/>
					</td>
				</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyshyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							学校意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxshyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核:
						</td>
						<td align="left">
							<bean:write name='rs' property="xysh" />
						</td>
						<td align="right">
							学校审核:
						</td>
						<td align="left">
							<bean:write name='rs' property="xxsh" />
						</td>
					</tr>
					&nbsp;&nbsp;
						
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
						<button type="button" class="button2" id="buttonSave" onclick="rychBbPrint()">
							报  表
						</button>
						<button type="button" class="button2"
							onclick="Close();return false;"
							id="buttonClose"
							style="width:80px">
							关闭
						</button>
						</td>
					</tr>
				</table>
			</fieldset>
		<logic:present name="msg">
			<script>
				alert($("msg").value);
			</script>
		</logic:present>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
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
