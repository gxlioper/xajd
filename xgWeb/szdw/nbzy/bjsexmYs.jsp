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
var ry_length = "1";
var zc_length = "1";
function chcd(obj,leng){
	if(obj.value.length > leng){
		alert("该项目最大字数为"+leng+",现已经超过，请确认！！");
		obj.focus();
	}
}
function addRy(){
	if(ry_length<20){
		$("rypj"+ry_length).style.display="";
		ry_length++;
	}
}
function delRy(){
    if((ry_length - 1) ==0){
      	return false;
    }
    ry_length --;
   	if(confirm("确定要删除行？")){      
		$("rypj"+ry_length).style.display="none";
		$("ry"+ry_length).value="";
		$("pjnr"+ry_length).value="";
    }
}
function addZc(){
	var addZc = new Array();
	if(zc_length<25){
		addZc[0]= document.createElement("input");
		addZc[0].style.width="150px";
		addZc[0].name="zcxm" +zc_length;
		addZc[0].id="zcxm" +zc_length;
		addZc[0].maxLength="20";
		addZc[0].onblur=function(){};
		addZc[0].value="";
		
		addZc[1]= document.createElement("input");
		addZc[1].style.width="150px";
		addZc[1].name="zcje" +zc_length;
		addZc[1].id="zcje" +zc_length;
		addZc[1].maxLength="10";
		addZc[1].onblur=function(){hj(this.id)};
		addZc[1].onfocus=function(){};
		addZc[1].onkeyup=function(){chValue(this)};
		addZc[1].value="";
		
		DWRUtil.addRows("zcqd",['dd'],addZc);
	
		zc_length++;
	}
}

function chValue(obj){
	$(obj.id).value = obj.value.replace(/[^\d]/g,'');
}
function hj(value){

	var id = "";
	if(value.length>5){
		id=value.substr(4,2);
	}else{
		id=value.substr(4,1);
	}
	
	var hj = "0";
	for(var i=0;i < zc_length;i++){
		if($("zcje"+i).value != ""){
			hj = parseInt(hj) + parseInt($("zcje"+i).value);
		}
	}
	$("hj").value= hj;
}
function jshj(value){
	var hj = "0";
	for(var i=0;i < zc_length;i++){
		if($("zcje"+i).value != ""){
			hj = parseInt(hj) + parseInt($("zcje"+i).value);
		}
	}
	$("hj").value= hj;
}
function delZc(){
    var tabobj = document.getElementById("zcqd");
    var length = tabobj.rows.length;   
    if(length==1){
      	return false;
    }
   	if(confirm("确定要删除行？")){    
   		zc_length --; 
   		$("hj").value=parseInt($("hj").value)- parseInt($("zcje"+zc_length).value); 
       	tabobj.deleteRow(tabobj.rows.length-1);
    }
}
function saveXm(){
	var pjzxh = "";
	var pjnr = "";
	var zcxm = "";
	var zcje = "";
	$("pjzxh").value = "";
	$("pjnr").value = "";
	$("zcjf").value = "";
	$("zcxm").value = "";
	if(ry_length < 10){
		alert("班级同学意见至少要十人，请确认！！！");
		return false;
	}
	if($("jsgc").value.length < 1000){
		alert("项目的建设详细过程不能少于1000字");
		return false;
	}
	for(var i = 0;i<ry_length;i++){
		if($("ry"+i).value == ""){
			alert("第"+(i+1)+"行班级同学姓名为空，请确认！！！");
			return false;
		}
		if($("pjnr"+i).value == ""){
			alert("第"+(i+1)+"行班级同学评价为空，请确认！！！");
			return false;
		}
		for(j=(i+1);j<ry_length;j++){
			if($("ry"+i).value == $("ry"+j).value){
				alert("第"+(i+1)+"行班级同学姓名重复，请确认！！！");
				return false;
			}
		}
		$("pjzxh").value += $("ry"+i).value+"!!@@!!";
		$("pjnr").value += $("pjnr"+i).value+"!!@@!!";
	}
	for(var i = 0;i<zc_length;i++){
		if($("zcxm"+i).value == ""){
			alert("第"+(i+1)+"行支出项目为空，请确认！！！");
			return false;
		}
		if($("zcje"+i).value == ""){
			alert("第"+(i+1)+"行支出金额为空，请确认！！！");
			return false;
		}
		$("zcxm").value += $("zcxm"+i).value+"!!@@!!";
		$("zcjf").value += $("zcje"+i).value+"!!@@!!";
	}
	$("zcjf").value += $("hj").value+"!!@!!";
	refreshForm("bjtsxm.do?method=bjtsxmYs&type=save&pk="+$("pkValue").value);
}

function onShow(){
	if($("rynum")){
		if($("rynum").value!="0"){
			for(var i=0;i<$("rynum").value;i++){
				if(i==0){
					ry_length=0;
				}
				$("rypj"+i).style.display="";
				ry_length++;
			}
		}
	}
	var zcjf = $("zcjf").value;
	if(zcjf != ""){
		var arrZc = new Array();
		arrZc = zcjf.split("!!@!!");
		if(arrZc[0] != ""){
			var arrZcje = new Array();
			arrZcje = arrZc[0].split("!!@@!!");
			arrZcxm = arrZc[1].split("!!@@!!");
			for(var i=0;i<arrZcje.length;i++){
				if(i == 0){
					$("zcje0").value = arrZcje[0];
					$("zcxm0").value = arrZcxm[0];	
				}else if(i != arrZcje.length -1){
					var addZc = new Array();
				
					addZc[0]= document.createElement("input");
					addZc[0].style.width="150px";
					addZc[0].name="zcxm" +zc_length;
					addZc[0].id="zcxm" +zc_length;
					addZc[0].maxLength="20";
					addZc[0].onblur=function(){};
					addZc[0].value=arrZcxm[i];
					
					addZc[1]= document.createElement("input");
					addZc[1].style.width="150px";
					addZc[1].name="zcje" +zc_length;
					addZc[1].id="zcje" +zc_length;
					addZc[1].maxLength="10";
					addZc[1].onblur=function(){hj(this.id)};
					addZc[1].onfocus=function(){};
					addZc[1].onkeyup=function(){chValue(this)};
					addZc[1].value=arrZcje[i];
					
					DWRUtil.addRows("zcqd",['dd'],addZc);
				
					zc_length++;
				}else{
					$("hj").value = arrZcje[i];
				}
			}
		}
	}
}
function xmSh(yj){
	var userType=$("userType").value;
	if(userType == "teacher"){
		if($("bzryj").value == ""){
			alert("班主任意见不能为空，请确认！！！");
			return false;
		}
	}else if(userType == "xy"){
		if($("xyyj").value == ""){
			alert("<bean:message key="lable.xsgzyxpzxy" />意见不能为空，请确认！！！");
			return false;
		}
	}else if(userType == "xx" || userType == "admin" ){
		if($("xxyj").value == ""){
			alert("学工部意见不能为空，请确认！！！");
			return false;
		}
	}
	refreshForm("bjtsxm.do?method=bjtsxmYs&type=save&shzt="+yj+"&pk="+$("pkValue").value);
}
jQuery(function(){
	onShow();
})

</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body >
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
				<input type="hidden" name="pjzxh" id="pjzxh" value="<bean:write name="rs" property="pjzxh"/>" >
				<input type="hidden" name="pjnr" id="pjnr" value="<bean:write name="rs" property="pjnr"/>" >
				<input type="hidden" name="zcjf" id="zcjf" value="<bean:write name="rs" property="zcjf"/>" >
				<input type="hidden" name="zcxm" id="zcxm" value="<bean:write name="rs" property="zcxm"/>" >
				<logic:notEqual name="doType"value="add">
					<input type="hidden" name="rynum" id="rynum" value="<bean:write name="rynum"/>" >
				</logic:notEqual>
				<fieldset>
					<legend>
						
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">基本信息</b>
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
									<b style="font-size:14">项目建设的详细过程（不少于1000字）</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" colspan="4">
								<html:textarea property="jsgc" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'2000')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班级学生对项目建设的评价（不少于10人）</b>
									<logic:equal name="userType" value="stu">
									<input  value="+" title="增加行" style="height:18px;width:20px" id="add" onclick="addRy();">
									<input  value="-" title="删除行" style="height:18px;width:20px" id="del" onclick="delRy();">
								</logic:equal>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right" colspan="4">
								<table width="100%" class="tbstyle">
									<tbody width="100%" class="tbstyle" id="zyjf">
										<tr>
											<th align="left" style="width: 15%">
												班级学生姓名
											</th>
											<th align="left" style="">
												评价内容
											</th>
										</tr>
										<tr id="rypj0">
											<th align="left">
												<html:select  name="rs" property="ry0" styleId="ry0" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr0" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj1" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry1" styleId="ry1" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr1" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj2" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry2" styleId="ry2" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr2" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj3" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry3" styleId="ry3" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr3" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj4" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry4" styleId="ry4" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr4" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj5" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry5" styleId="ry5" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr5" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj6" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry6" styleId="ry6" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr6" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj7" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry7" styleId="ry7" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr7" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj8" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry8" styleId="ry8" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr8" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj9" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry9" styleId="ry9" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr9" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj10" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry10" styleId="ry10" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr10" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj11" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry11" styleId="ry11" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr11" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj12" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry12" styleId="ry12" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr12" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj13" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry13" styleId="ry13" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr13" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj14" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry14" styleId="ry14" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr14" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj15" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry15" styleId="ry15" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr15" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj16" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry16" styleId="ry16" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr16" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj17" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry17" styleId="ry17" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr17" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj18" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry18" styleId="ry18" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr18" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
										<tr id="rypj19" style="display: none">
											<th align="left">
												<html:select  name="rs" property="ry19" styleId="ry19" style="width:100px" >
													<html:option value=""></html:option>
													<html:options collection="ryList" property="xh" labelProperty="xm" />
												</html:select>
											</th>
											<th align="left">
												<html:textarea property="pjnr19" style="width:100%" rows='5' name="rs" onblur="chcd(this,'500')"/>
											</th>
										</tr>
									</tbody>	
								</table>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目实施前班级状况</b>
								</td>
							</tr>
						</thead>
						<td align="right" colspan="4" >
								<html:textarea property="ssqzk" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'1000')"/>
						</td>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目实施后班级状况</b>
								</td>
							</tr>
						</thead>
						<td align="right" colspan="4" >
								<html:textarea property="sshzk" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'1000')"/>
						</td>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目建设的成果</b>
								</td>
							</tr>
						</thead>
						<td align="right" colspan="4" >
								<html:textarea property="jscg" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'1000')"/>
						</td>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目建设的得失</b>
								</td>
							</tr>
						</thead>
						<td align="right" colspan="4" >
								<html:textarea property="jsds" style="width:100%" rows='8' name="rs"  
								onblur="chcd(this,'1000')"/>
						</td>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">项目经费使用清单</b>
									<logic:equal name="userType" value="stu">
										<input  value="+" title="增加行" style="height:18px;width:20px" id="add" onclick="addZc();">
										<input  value="-" title="删除行" style="height:18px;width:20px" id="del" onclick="delZc();">
									</logic:equal>
								</td>
							</tr>
						</thead>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tbody width="100%" class="tbstyle" id="zcqd">
										<tr>
											<th align="left" style="width: 50%">
												支出项目
											</th>
											<th align="left" style="">
												支出金额
											</th>
										</tr>
										<tr>
											<th align="left" style="width: 50%">
												<input type="text" style="width:150px" id="zcxm0" name="zcxm0" value=""  
													onblur=""; maxlength="20">
											</th>
											<th align="left" style="">
												<input type="text" style="width:150px" id="zcje0" name="zcje0" onblur="jshj(this.value)" 
													onkeyup="value=value.replace(/[^\d]/g,'') "  maxlength="10">
											</th>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="width: 50%">
								合计
							</td>
							<td align="left" colspan="2" style="">
								<input type="text" style="width:150px" id="hj" name="hj"   readOnly="true"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10">
							</td>
						</tr>
				</table>
			</fieldset>
			<logic:notEqual name="userType" value="stu">
			<fieldset>
				<legend>
					项目审批
				</legend>
				<table width="100%" class="tbstyle">
					<logic:equal name="userType" value="teacher">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">学工部意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xxyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">班主任意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="bzryj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14"><bean:message key="lable.xsgzyxpzxy" />意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xyyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
						<thead>
							<tr valign="middle" >
								<td align="center" colspan="4">
									<b style="font-size:14">学工部意见</b>
								</td>
							</tr>
						</thead>
						<tr>
							<td align="left" colspan="4">
								<html:textarea property="xxyj" style="width:100%" rows='5' name="rs"  
								onblur="chcd(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
				</table>
			</fieldset>
			</logic:notEqual>
				<div class="buttontool">
					<logic:equal name="userType" value="stu">
						<logic:notEqual name="doType" value="view">
							<logic:equal name="noSh" value="yes">
							<button type="button" class="button2"
								onclick="saveXm();"
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
								onclick="showOpenWindow('bjtsxm.do?method=bjtsxmYsPrint&pk='+$('pkValue').value,680,600);"
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
	</body>
</html>
