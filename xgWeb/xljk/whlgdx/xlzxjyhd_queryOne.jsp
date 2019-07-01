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
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script type="text/javascript">
		function setJyxsNull(){
			document.forms[0].jyxs.options[0].selected = true;
		}
		
		function doSaveInfo(){

			var jyhdjl=document.all["jyhdjl"].value;
			var jyhdxg=document.all["jyhdxg"].value;
			if(jyhdjl.length>800){
				alert("教育活动记录不能查过350个汉字");
				return false;
			}
			if(jyhdxg.length>800){
				alert("教育活动效果不能查过350个汉字");
				return false;
			}

			
			var mustFill = "zt-jyxs-dd-zcr-jyhdjl-jyhdxg";
			var mustFillStr = mustFill.split("-");
			for(var i=0;i<mustFillStr.length;i++){
				var tempVar = document.getElementById(mustFillStr[i]).value;
				if(tempVar == "" || tempVar == null){
					alert("请将带*号的文本框填满!");
					return false;
				}
			}
			var url = "/xgxt/whlgdx_xljk.do?method=xlzxjyhdper&doType=add";
			//if(doType == "modi"){
			//	url = url +  "&xn_id=" +  document.getElementById("pkVal").value;	
			//}
			refreshForm(url);
		}
		
		function check_qtjyhdxs(){

				
			
			var jyxs=document.all["jyxs"].value;
			if(jyxs=="013"){
				document.getElementById('qtjyhdxs').readOnly=false;
			}else{
				document.getElementById('qtjyhdxs').readOnly=true;
				document.all["qtjyhdxs"].value="";
			}
		}	
	</script>
	</head>
	<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
		当前所在位置：心理健康 - 心理咨询 - 增加心理咨询中心教育活动 
		</div>
	</div>
	<html:form action="/whlgdx_xljk.do?method=xlzxjyhdper" method="post"><%--
			<input type="hidden" id="userOnLine" name="userOnLine"
				value="<bean:write name="userOnLine" scope="session"/>" />	
			--%><input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal"/>" />	
			<table class="tbstyle" align="center" width="100%">
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>主题：
					</td>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="zt" styleId="zt" name="rs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>教育形式：
					</td>
					<td align="left" colspan="2">
						<html:select property="jyxs" style="width:120px" 
							styleId="jyxs"  name="rs" onchange="check_qtjyhdxs();">
							<html:option value=""></html:option>
							<html:options collection="hdxsList" property="DMH"
								labelProperty="DMMC"/>
						</html:select>
					</td>
					<td align="right" colspan="2" nowrap="nowrap">
						其他教育活动形式：
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="qtjyhdxs" styleId="qtjyhdxs" 
						     readonly="true"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>地点：
					</td>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd"  name="rs"/>
					</td>
					<td align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>活动日期：
					</td>
					<td colspan="2">
						<html:text style="cursor:hand; width:120px;" styleId="hdrq"  name="rs"
							property="hdrq" onclick="return showCalendar('hdrq','y-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						开始时间：
					</td>
					<td align="left" colspan="2">
						<html:text property="kssj" styleId="kssj"  name="rs"/>
					</td>
					<td align="right" colspan="2">
						结束时间：
					</td>
					<td align="left" colspan="2">
						<html:text property="jssj" styleId="jssj"  name="rs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" colspan="2">
						<font color="red">*</font>主持人：
					</td>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zcr"  name="rs"/>
					</td>
					<td align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>参与人数：
					</td>
					<td align="left" colspan="2">
						<html:text property="cyrs" styleId="cyrs" name="rs"/>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font> 教育活动记录：
					</td>
					<td colspan="6" align="left"> 
						<html:textarea rows="5" style="width:98%" property="jyhdjl" name="rs"
							styleId="jyhdjl" />
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<font color="red">*</font>教育活动效果：
					</td>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="width:98%" property="jyhdxg"  name="rs"
							styleId="jyhdxg" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:empty name="rs" property="doType">
					<button class="button2" onClick="doSaveInfo()" style="width:80px">
						保存
					</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:empty>
				<logic:equal value="modi" name="rs" property="doType">
					<button class="button2" onClick="doSaveInfo();" style="width:80px">
						修改保存
					</button>
				</logic:equal>&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onClick="Close();" style="width:80px">
					关闭
				</button>
			</div>	
		</html:form>
	</body>
	<logic:present name="ok">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("保存成功！");
	   			dialogArgumentsQueryChick();
	   			Close();
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("保存失败！");
	        </script>
		</logic:match>
	</logic:present>
</html:html>
