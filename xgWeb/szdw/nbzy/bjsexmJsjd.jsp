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
var sjnr_length = "1";
function saveJd(){
	for(var i=0;i<sjnr_length;i++){
		if($("kssj"+i).value==""){
			alert("阶段"+(i+1)+"开始时间为空，请确认！！！");
			return false;
		}
		if($("jssj"+i).value==""){
			alert("阶段"+(i+1)+"结束时间为空，请确认！！！");
			return false;
		}
		if($("kssj"+i).value > $("jssj"+i).value){
			alert("阶段"+(i+1)+"结束时间早于开始时间，请确认！！！");
			return false;
		}
		if($("nr"+i).value==""){
			alert("阶段"+(i+1)+"基本内容为空，请确认！！！");
			return false;
		}
		$("sjjd").value += $("kssj"+i).value+"-"+$("jssj"+i).value+"!!@@!!";
		$("jbnr").value += $("nr"+i).value+"!!@@!!";;	
	}
	refreshForm("bjtsxm.do?method=bjtsxmJsjd&type=save&pk="+$("pkValue").value);
}

function xmSh(yj){
	refreshForm("bjtsxm.do?method=bjtsxmJsjd&type=save&shzt="+yj+"&pk="+$("pkValue").value);
}

function addSjnr(){
	var addSjnr = new Array();
	if(sjnr_length<9){
		$("sjnr"+sjnr_length).style.display="";
		sjnr_length++;
	}
}

function delSjnr(){
    if((sjnr_length - 1) ==0){
      	return false;
    }
    sjnr_length --;
   	if(confirm("确定要删除行？")){      
		$("sjnr"+sjnr_length).style.display="none";
		$("kssj"+sjnr_length).value="";
		$("jssj"+sjnr_length).value="";
		$("nr"+sjnr_length).value="";
    }
}

function chcd(obj,leng){
	if(obj.value.length > leng){
		alert("该项目最大字数为"+leng+",现已经超过，请确认！！");
		obj.focus();
	}
}

function setValue(obj){
	//obj.value="";
}

function onShow(){
	if($("jbnrnum")){
		if($("jbnrnum").value != "0"){
			sjnr_length = $("jbnrnum").value;
			for(var i=1;i< $("jbnrnum").value ;i++){
				$("sjnr"+i).style.display="";
			}
		}
	}
}

function xmjsjdPrint(){
	var pk = $('pkValue').value;
	var url = '/xgxt/bjtsxm.do?method=bjtsxmjsjdPrint&pk='+pk;
	showOpenWindow(url,680,600);
}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="onShow()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getBjtsxmDAO.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					你不是班长，本模块只能由班长访问！！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" name="sjjd" id="sjjd" value="" >
				<input type="hidden" name="jbnr" id="jbnr" value="" >
				<logic:equal name="doType" value="edit">
					<input type="hidden" name="jbnrnum" id="jbnrnum" value="<bean:write name="jbnrnum"/>" >
				</logic:equal>
				<logic:equal name="doType" value="view">
					<input type="hidden" name="jbnrnum" id="jbnrnum" value="<bean:write name="jbnrnum"/>" >
				</logic:equal>
				<fieldset>
					<legend>
						班级特色项目建设进度
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目建设基本情况</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								项目代码：
							</td>
							<td align="left">
								<bean:write name="rs" property="xmdm"/>
							</td>
							<td align="right">
								项目名称：
							</td>
							<td align="left">
								<bean:write name="rs" property="xmmc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班长：
							</td>
							<td align="left">
								<bean:write name="rs" property="bzxm"/>
							</td>
							<td align="right">
								项目申请时间：
							</td>
							<td align="left">
								<bean:write name="rs" property="xmsqsj"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">申报项目的动因</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" colspan="4">
								<html:textarea property="sbdy" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'2000')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班级特色项目建设的时间阶段和内容</b>
									<logic:equal name="userType" value="stu">
										<input  value="+" title="增加行" style="height:18px;width:20px" id="add" onclick="addSjnr();">
										<input  value="-" title="删除行" style="height:18px;width:20px" id="del" onclick="delSjnr();">
									</logic:equal>
								</td>	
							</tr>
						</thead>
						<tr>
							<td align="center" style="width:20%" >
								开始时间
							</td>
							<td align="center" style="width:20%" >
								结束时间
							</td>
							<td align="center" colspan="2">
								基本内容
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="text" id="kssj0" name="kssj0" value="<bean:write name="rs" property="kssj0"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj0','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj0" name="jssj0" value="<bean:write name="rs" property="jssj0"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj0','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr0" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr1" style="display: none">
							<td align="center">
								<input type="text" id="kssj1" name="kssj1" value="<bean:write name="rs" property="kssj1"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj1','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj1" name="jssj1" value="<bean:write name="rs" property="kssj1"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj1','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr1" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr2" style="display: none">
							<td align="center">
								<input type="text" id="kssj2" name="kssj2" value="<bean:write name="rs" property="kssj2"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj2','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj2" name="jssj2" value="<bean:write name="rs" property="jssj2"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj2','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr2" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr3" style="display: none">
							<td align="center">
								<input type="text" id="kssj3" name="kssj3" value="<bean:write name="rs" property="kssj3"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj3','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj3" name="jssj3" value="<bean:write name="rs" property="jssj3"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj3','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr3" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr4" style="display: none">
							<td align="center">
								<input type="text" id="kssj4" name="kssj4" value="<bean:write name="rs" property="kssj4"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj4','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj4" name="jssj4" value="<bean:write name="rs" property="jssj4"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj4','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr4" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr5" style="display: none">
							<td align="center">
								<input type="text" id="kssj5" name="kssj5" value="<bean:write name="rs" property="kssj5"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj5','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj5" name="jssj5" value="<bean:write name="rs" property="jssj5"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj5','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr5" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr6" style="display: none">
							<td align="center">
								<input type="text" id="kssj6" name="kssj6" value="<bean:write name="rs" property="kssj6"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj6','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj6" name="jssj6" value="<bean:write name="rs" property="jssj6"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj6','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr6" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr7" style="display: none">
							<td align="center">
								<input type="text" id="kssj7" name="kssj7" value="<bean:write name="rs" property="kssj7"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj7','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj7" name="jssj7" value="<bean:write name="rs" property="jssj7"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj7','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr7" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
						<tr id="sjnr8" style="display: none">
							<td align="center">
								<input type="text" id="kssj8" name="kssj8" value="<bean:write name="rs" property="kssj8"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('kssj8','y-mm-dd');">
							</td>
							<td align="center">
								<input type="text" id="jssj8" name="jssj8" value="<bean:write name="rs" property="jssj8"/>"
									onblur="dateFormatChg(this)" style="cursor:hand;width:100%"
									onclick="return showCalendar('jssj8','y-mm-dd');">
							</td>
							<td align="center" colspan="2" style="height: 50px" >
								<html:textarea property="nr8" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
							</td>
						</tr>
				</table>
			</fieldset>
				<div class="buttontool">
					<logic:equal name="userType" value="stu">
						<logic:notEqual name="doType" value="view">
							<logic:equal name="noSh" value="yes">
							<button type="button" class="button2"
								onclick="saveJd();"
								style="width:80px" id="buttonSave">
								保 存
							</button>
							</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="userType" value="stu">
						<button type="button" class="button2" onclick="xmSh('tg');" 
							style="width:80px" id="buttonSave">
								审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="xmSh('btg');" 
							style="width:80px" id="buttonSave">
								审核不通过
						</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notEqual name="doType" value="add">
						<button type="button" class="button2"
								onclick="xmjsjdPrint();"
								style="width:80px" id="buttonSave">
								打 印
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
					</logic:notEqual>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
		<logic:equal value="yes" name="isBzr">
			<script>
				alert("班长还未提交进度安排，无法审核!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
	</body>
</html>
