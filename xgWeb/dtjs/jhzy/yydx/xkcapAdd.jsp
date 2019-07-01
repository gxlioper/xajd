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
	<base target="_self" />
	<script language="javascript">
function dataSave(){
      var kssj="";
	   var kssjH="";//开始时间小时
	   var kssjM="";//开始时间分
	   var kssjS="";//开始时间秒
	   var jssj="";
	   var jssjH="";//结束时间小时
	   var jssjM="";//结束时间分
	   var jssjS="";//结束时间秒
	   if($("kssj")){
	     kssj= $("kssj").value;
	   }
	   if($("kssjH")){
	     kssjH= $("kssjH").value;
	   }
	   if($("kssjM")){
	     kssjM= $("kssjM").value;
	   }
	   if($("kssjS")){
	     kssjS= $("kssjS").value;
	   }
	   if($("jssj")){
	     jssj= $("jssj").value;
	   }
	   if($("jssjH")){
	     jssjH= $("jssjH").value;
	   }
	   if($("jssjM")){
	     jssjM= $("jssjM").value;
	   }	   	
	   if($("jssjS")){
	     jssjS= $("jssjS").value;
	   }	 
	   if(kssjH > 24 || jssjH > 24){
	   	 alert("小时数不能大于24！");
	     return false;
	   }
	   if(kssjM > 60 || jssjM > 60){
	   	 alert("分钟数不能大于60！");
	     return false;
	   }  
	   if(kssjS > 60 || jssjS > 60){
	   	 alert("秒数不能大于60！");
	     return false;
	   }     	   	
	   if($("xydm")){
	      if($("xydm").value==""){
	         alert("<bean:message key="lable.xsgzyxpzxy" />不能为空！");
	         return false;
	      }
	   }
	   if($("xn")){
	      if($("xn").value==""){
	         alert("学年不能为空！");
	         return false;
	      }
	   }
	   if($("dkjs")){
	      if($("dkjs").value==""){
	         alert("党课届次不能为空！");
	         return false;
	      }
	   }
	   if($("xq")){
	      if($("xq").value==""){
	         alert("学期不能为空！");
	         return false;
	      }
	   }
	   if($("kssj")){
	      if($("kssj").value==""){
	         alert("开始时间不能为空！");
	         return false;
	      }
	   }
	   if($("jssj")){
	      if($("jssj").value==""){
	         alert("结束时间不能为空！");
	         return false;
	      }
	   }
	   
	   if($("kczy")){
	      if($("kczy").value==""){
	         alert("课程摘要不能为空！");
	         return false;
	      }
	   }
	   if($("jtap")){
	      if($("jtap").value.length>500){
	         alert("具体安排字数过大，限500字！");
	         return false;
	      }
	   }
	   if($("bz")){
	      if($("bz").value.length>300){
	         alert("备注字数过大，限300字！");
	         return false;
	      }
	   }
	   var kssjV = kssj+kssjH+kssjM+kssjS;
	   var jssjV = jssj+jssjH+jssjM+jssjS;
	   if(kssjV>=jssjV){
	     alert("开始时间大等于结束时间，请核实后再提交！");
	     return false;
	   }
	   var url = "/xgxt/jhzyYydx.do?method=xkcapAdd&doType=save";
	   refreshForm(url);	   
}

</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/dtjsFunction.js"></script>
		<html:form action="/jhzyYydx" method="post" enctype="multipart/form-data">
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />
			<input type="hidden" id="msg" name="msg" value="${msg}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：党团建设 - 业余党校 - 日课程安排 - 增加
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							&nbsp;
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:select property="xydm" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<html:select property="xn" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>党课届次：
					</td>
					<td align="left">
						第&nbsp;
						<html:text property="dkjs" styleId="dkjs" style="width:30px" onkeypress="return NumInputValue1(this,3);" onblur='onlyNumInput(this)'></html:text>
						&nbsp;期
					</td>
					<td align="right">
						<font color="red">*</font>学期：
					</td>
					<td align="left">
						<html:select property="xq" styleId="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>开始时间：
					</td>
					<td align="left">
						<html:text property="kssj"
							onclick="return showCalendar('kssj','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							style="cursor:hand;" styleId="kssj"></html:text>
						&nbsp;
						<html:text property="kssjH" size="2" styleId="kssjH" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="kssjM" size="2" styleId="kssjM" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="kssjS" size="2" styleId="kssjS" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>结束时间：
					</td>
					<td align="left">
						<html:text property="jssj"
							onclick="return showCalendar('jssj','y-mm-dd');"
							onblur="dateFormatChg(this)" readonly="true" size="10"
							styleId="jssj"></html:text>
						&nbsp;
						<html:text property="jssjH" size="2" styleId="jssjH" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="jssjM" size="2" styleId="jssjM" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
						:
						<html:text property="jssjS" size="2" styleId="jssjS" maxlength="2" onkeypress="return NumInputValue2(this,2);" onblur='onlyNumInput(this);hmsCheck(this);'></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>课程摘要：
					</td>
					<td colspan="3" align="left">
						<html:text property="kczy" style="width:90%" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						附件上传：
					</td>
					<td align=left colspan="3"> 
						<input type="file" name="uploadFile" style="width:60%" id="kk">
						&nbsp;&nbsp;
						<font color="red">(文件大小小于&lt;10M&gt;)</font>
					</td>
				</tr>
				<tr>
					<td align="right">
						课程安排：
						<br>
						<font color="red"><限500字> </font>
					</td>
					<td colspan="3" align="left">
						<html:textarea property="jtap" style="width:90%" rows="6"
							styleId="jtap"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：
						<br>
						<font color="red"><限300字> </font>
					</td>
					<td colspan="3" align="left">
						<html:textarea property="bz" style="width:90%" rows="3"></html:textarea>
					</td>
				</tr>
			</table>
			<br />
			<div id="button" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="dataSave()"
					style="width:80px">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="true" name="done">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script>
				var msg = $("msg").value;
				if(msg == ""){
					alert("操作失败!");
				}else{
					alert(msg);
				}
			</script>
		</logic:equal>
	</body>
</html>
