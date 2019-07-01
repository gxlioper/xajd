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
		<script type="text/javascript">
		function rychSqPrint(){
        window.open('/xgxt/nbtyWmqs.do?method=printWmqs&pkValue=${pkValue}');
        }	
		</script>
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
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript">
	function dosubmit()
	{
		document.forms[0].submit();
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		  <html:form action="/nbtyWmqs.do?method=oneWmqs&doType=modi" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/nbtyWmqs.do?method=oneWmqs&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="save_lddm" name="save_lddm" value="${rs.lddm}" />
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn}" />
			<input type="hidden" id="save_qsh" name="save_qsh" value="${rs.qsh}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<fieldset>
			<div class="title">
			<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号申请 - 文明寝室申请结果查询 
			</div>
			</div>

			<logic:present name="result">
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>文明寝室</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>学年：
					</td>
					<td>
					<bean:write name="rs" property="save_xn"/>
					<html:hidden property="save_xn" value="${xn}"/>
					</td>
					
					<td align="right" style="width: 10%">
						<font color="red">*</font>楼栋名称：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="ldmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>寝室号：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="qsh"/>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:text name="rs" readonly="true" property="xymc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left">
						<html:text name="rs" readonly="true" property="zymc"/>
					</td>
					<td align="right">
						<font color="red">*</font>班级：
					</td>
					<td align="left">
						<html:text name="rs" readonly="true" property="bjmc"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" styleId="zysj" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" styleId="bz" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
						<logic:notEqual name="doType" value="view">
							<button type="button" class="button2"	onclick="dosubmit()" style="width:80px" >
								保 存
							</button>
						</logic:notEqual>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
						</button>
						<logic:equal name="doType" value="view">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px" >
								打  印
							</button>
						</logic:equal>
					</div>
				</fieldset>
		</html:form>
		<logic:notEqual name="write" value="yes">
		<script>
				alert("对不起，上级用户已审核通过。您不能再进行修改！");
			</script>
			</logic:notEqual>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>