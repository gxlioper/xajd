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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript">
	function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
	function loadShi2(){
		var shen = document.getElementById("jgshen2").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi2";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
		function loadShi3(){
		var shen = document.getElementById("jgshen3").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi3";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}
	
	function jygljyxyupdate(){
	
	    var whichArea = document.getElementById("whichArea").value;
	    var fdysh = document.getElementById("fdysh").value;
	    var xxsh = document.getElementById("xxsh").value;
	    var xsxh = document.getElementById("xsxh").value;
	    var xsxh = document.getElementById("xsxh").value;
	var username = document.getElementById("name").value;
	if(xsxh==""){
	alert("学号不能为空！");
	return false;
	}
	if(username==""){
	alert("姓名不能为空！");
	return false;
	}
	
	
	var xysbh = document.getElementById("xysbh").value;
	var xb = document.getElementById("xb").value;
	var id = document.getElementById("id").value;
    var mz = document.getElementById("mz").value;
    var xldm = document.getElementById("xldm").value;
    var zzmm = document.getElementById("zzmm").value;
    var xzdm = document.getElementById("xzdm").value;
    if(xysbh == ""){
    	alert("协议书编号不能为空");
    	return false;
    	}
    
    if(mz == ""){
    	alert("民族不能为空");
    	return false;
    	}
    
    if(xldm == ""){
    	alert("学历不能为空");
    	return false;
    	}
    
    if(zzmm == ""){
    	alert("政治面貌不能为空");
    	return false;
    	}
    
    if(xzdm == ""){
    	alert("学制不能为空");
    	return false;
    	}
    if(xb == ""){
    	alert("性别不能为空");
    	return false;
    	}
	    /*
	var name = document.getElementById("name").value;
	if(xsxh==""){
	alert("学号不能为空！");
	return false;
	}
	if(name==""){
	alert("姓名不能为空！");
	return false;
	}
	var sydq = document.getElementById("sydq").value;
	var zgbm = document.getElementById("zgbm").value;
	
	
	var xxdjh = document.getElementById("xxdjh").value;
	var zzjgdm = document.getElementById("zzjgdm").value;
    var wjybz = document.getElementById("wjybz").value;
    var zzmmdm = document.getElementById("zzmmdm").value;
    var lxdz = document.getElementById("lxdz").value;
    var yb = document.getElementById("yb").value;
    var dh = document.getElementById("dh").value;
    var dwxzdm = document.getElementById("dwxzdm").value;
    var blueno = document.getElementById("blueno").value;
    var dajsd = document.getElementById("dajsd").value;
    var dajsddz = document.getElementById("dajsddz").value;
    var dajsdyb = document.getElementById("dajsdyb").value;
    
    var dwdh = document.getElementById("dwdh").value;
    var dwyb = document.getElementById("dwyb").value;
    var wyj =document.getElementById("wyj").value;
    var dynypjgz =document.getElementById("dynypjgz").value;
    
    var byqxdm =document.getElementById("byqxdm").value;
    var dwmc =document.getElementById("dwmc").value;
    var sydzgbm =document.getElementById("sydzgbm").value;
    
    var dwxzdm2 =document.getElementById("dwxzdm2").value;
    var dwdz = document.getElementById("dwdz").value;
    
    var dqlx = document.getElementById("dqlx").value;
    var hyfl = document.getElementById("hyfl").value;
    var zydk = document.getElementById("zydk").value;
    var dwdq = document.getElementById("dwdq").value;
    var wjyyy = document.getElementById("wjyyy").value;
    var sydqdm = document.getElementById("sydqdm").value;
    var zgbmdm = document.getElementById("zgbmdm").value;
	
	if(byqxdm=="01"&&dwdq==""||byqxdm=="02"&&dwdq==""||byqxdm=="03"&&dwdq==""||
	   byqxdm=="04"&&dwdq==""||byqxdm=="12"&&dwdq==""||
	   byqxdm=="13"&&dwdq==""||byqxdm=="14"&&dwdq==""||
	   byqxdm=="15"&&dwdq==""||byqxdm=="17"&&dwdq==""){   
	   alert("单位地区名称不能为空！")
	   return false;   
	}
	if(byqxdm!="04"&&dwdq=="境外"){
	alert("毕业去向与单位地区名称发生冲突！请重新核对！");
	return false;
	}
	
	
	if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   &&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&wjybz=="0"){
	   alert("请再次确认是否就业！如未就业，未就业标志不成立")
	   return false;
	}
	if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   &&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&dwdq!=""){
	   alert("请再次确认是否就业！如未就业，单位地区不成立。")
	   return false;
	}
	
	
	
	
	if(zgbm=="请选择"){
	alert("请选择主管部门！");
	return false;
	}
	if(xxdjh.length!=7&&xxdjh!=""){
	alert("信息登记号长度应为7位！");
	return false;
	}
	if(!isNumber(xxdjh)&&xxdjh!=""){
	alert("信息登记号应为数字！");
	return false;
	}
	if(zzjgdm.length!=9&&zzjgdm!=""){
	alert("组织机构代码长度应为9位！");
	return false;
	}
	if(dwdq!=""&&dwmc==""){
		alert("请输入单位名称！");
	return false;
	}
	
	if(wjybz==""){
	alert("未就业标志不能为空！");
	return false;
	}
	
	if(byqxdm=="01"&&wjybz=="1"||byqxdm=="02"&&wjybz=="1"||byqxdm=="03"&&wjybz=="1"||
	   byqxdm=="04"&&wjybz=="1"||byqxdm=="12"&&wjybz=="1"||byqxdm=="13"&&wjybz=="1"||
	   byqxdm=="14"&&wjybz=="1"||byqxdm=="15"&&wjybz=="1"||byqxdm=="17"&&wjybz=="1"){
	   alert("毕业去向与未就业标志发生冲突！请重新核对！");
	   return false;
	}
	
	
	
	if(zzmmdm==""){
	alert("政治面貌代码不能为空！");
	return false;
	}
	if(wjybz=="1"&&wjyyy==""){
	alert("请输入未就业原因！");
	return false;
	}
	if(lxdz==""){
	alert("联系地址不能为空！");
	return false;
	}
	if(yb==""){
	alert("邮编不能为空！");
	return false;
	}
	if(!isNumber(yb)){
	alert("邮编应是数字！");
	return false;
	}
	if(yb.length!=6){
	alert("邮编长度应为6位！");
	return false;
	}
	if(dh==""){
	alert("电话不能为空！");
	return false;
	}
	if(!isNumber(dh)){
	alert("电话号码应是数字！");
	return false;
	}
	if(dh.length<7){
	alert("电话号码长度不正确！");
	return false;
	}
	
	if(sydqdm!="310000"&&sydzgbm==""){
	alert("生源地主管部门不能为空！")
	return false;
	}
	if(sydqdm!="310000"&&byqxdm=="04"&&dwmc!=sydzgbm){
	alert("如选择出国，单位名称应等同于生源地区主管部门！")
	return false;
	}
	if(sydqdm=="310000"&&sydzgbm!=""){
	alert("上海生源不需填写生源地主管部门！");
	return false;
	}
	if(dwxzdm=="请选择"){
	alert("请选择单位性质代码！")
	return false;
	}
    if(!isNumber(blueno)&&blueno!=""){
	alert("蓝表批准文号应为数字！");
	return false;
	}
	
	
	
	if(dajsdyb!=""&&!isNumber(dajsdyb)){
	alert("档案接收地邮编应为数字！");
	return false;
	}
	if(dwdh!=""&&!isNumber(dwdh)){
	alert("单位电话应为数字！");
	return false;
	}
	if(dwyb!=""&&!isNumber(dwyb)){
	alert("单位邮编应为数字！");
	return false;
	}
	if(wyj!=""&&!isNumber(wyj)){
	alert("违约金应为数字！");
	return false;
	}
    if(dynypjgz!=""&&!isNumber(dynypjgz)){
	alert("第一年月平均工资应为数字！");
	return false;
	}
	
	
	
	if(dwxzdm2==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("单位性质代码2不能为空！");
	return false;
	}
	if(dwxzdm2!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("单位性质代码2不用填写！");
	return false;
	}
	
	
	
	if(dwdz==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("单位地址不能为空！");
	return false;
	}
	if(dwdz!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("单位地址不用填写！");
	return false;
	}
	
	if(dwdh==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("单位电话不能为空！");
	return false;
	}
	if(dwdh!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("单位电话不用填写！");
	return false;
	}
	

	
	if(dwyb==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("单位邮编不能为空！");
	return false;
	}
	if(dwyb!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("单位邮编不用填写！");
	return false;
	}
	
	
	
	if(wyj==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("违约金不能为空！");
	return false;
	}
	if(wyj!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("违约金不用填写！");
	return false;
	}
	
	if(dynypjgz==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("第一年月平均工资不能为空！");
	return false;
	}
	if(dynypjgz!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("第一年月平均工资不用填写！");
	return false;
	}
	
	
	if(dqlx==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("地区流向不能为空！");
	return false;
	}
	if(dqlx!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("地区流向不用填写！");
	return false;
	}
	
	
	
	if(hyfl==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("行业分类不能为空！");
	return false;
	}
	if(hyfl!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("行业分类不用填写！");
	return false;
	}
	
	
	
	if(zydk==""&&(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17")){
	alert("专业对口不能为空！");
	return false;
	}
	if(zydk!=""&&byqxdm!="01"&&byqxdm!="13"&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"){
	alert("专业对口不用填写！");
	return false;
	}    
	
	if((byqxdm=="01"||byqxdm=="13"||byqxdm=="15")&&
	   dwxzdm!="10"&&dwxzdm!="15"&&dwxzdm!="20"&&dwxzdm!="21"&&dwxzdm!="22"&&
	   dwxzdm!="23"&&dwxzdm!="25"&&dwxzdm!="29"&&dwxzdm!="31"&&dwxzdm!="32"&&
	   dwxzdm!="33"&&dwxzdm!="35"&&dwxzdm!="39"&&dwxzdm!="40"&&dwxzdm!="55"&&dwxzdm!="56"){
	   
	   alert("毕业去向代码与单位性质代码发生冲突，请检查！");
	   return false;
	}
	if((byqxdm=="02"||byqxdm=="03"||byqxdm=="12")&&dwxzdm!="80"){
	   alert("如以毕业去向代码为准，单位性质代码应选择升学！");
	   return false;
	}
	if(byqxdm=="04"&&dwxzdm!="85"){
	   alert("如以毕业去向代码为准，单位性质代码应选择出国、出境！");
	   return false;
	}
	if(byqxdm=="14"&&dwxzdm!="75"&&dwxzdm!="76"&&dwxzdm!="77"){
	   alert("如以毕业去向代码为准，单位性质代码应选择自主创业，自由职业或其他灵活就业！");
	   return false;
	}
	if(byqxdm=="17"&&dwxzdm!="50"&&dwxzdm!="51"){
	   alert("如以毕业去向代码为准，单位性质代码应选择国家基层项目或地方基层项目！");
	   return false;
	}
	if(byqxdm=="06"&&dwxzdm!="70"){
	   alert("如以毕业去向代码为准，单位性质代码应选择待就业！");
	   return false;
	}
	if(byqxdm=="07"&&dwxzdm!="71"&&dwxzdm!="72"){
	   alert("如以毕业去向代码为准，单位性质代码应选择不就业拟升学或其他暂不就业！");
	   return false;
	}
	if((byqxdm=="16"||byqxdm=="")&&dwxzdm!="70"&&dwxzdm!="71"&&dwxzdm!="72"){
	   alert("如以毕业去向代码为准，单位性质代码应选择待就业,不就业拟升学或其他暂不就业！");
	   return false;
	}
	if((byqxdm=="05"||byqxdm=="08"||byqxdm=="11")&&dwxzdm!=""){
	   alert("如以毕业去向代码为准，单位性质代码应选择空！");
	   return false;
	}
	
	
	if((byqxdm=="02"||byqxdm=="03"||byqxdm=="12")&&zgbmdm!="010"){
	   alert("如以毕业去向代码为准，主管部门名称应选择升学！");
	   return false;
	}
	
	if((byqxdm=="04"||byqxdm=="05"||byqxdm=="08"||byqxdm=="11")&&zgbmdm!="011"){
	   alert("如以毕业去向代码为准，主管部门名称应选择出国、退学及其他！");
	   return false;
	}
	if((byqxdm=="01"||byqxdm=="13"||byqxdm=="14"||byqxdm=="15"||byqxdm=="17")&&(zgbmdm=="010"||zgbmdm=="011"||zgbmdm=="")){
	   alert("毕业去向代码与主管部门名称发生冲突，请检查！");
	   return false;
	}
	if((byqxdm=="06"||byqxdm=="07"||byqxdm=="16"||byqxdm=="")&&zgbmdm!=""){
	   alert("如以毕业去向代码为准，主管部门名称应为空值！");
	   return false;
	}
	if(byqxdm=="16"&&dwmc!=""){
	   alert("回省二分学生不用填写单位名称！");
	   return false;
	}
	if(byqxdm=="16"&&sydzgbm==""){
	   alert("请选择生源地主管部门！");
	   return false;
	}
	*/
	            
	    if(fdysh=="已通过√"&&xxsh=="未审核"||xxsh=="已通过√"){
	    alert("审核已通过，你已无权修改！");
	    return false;
	    }        
	    		BatAlert.showTips('正在操作，请等待...');
		 		document.forms[0].action = "/xgxt/savejyxyupdate.do?doType=update&act=update&whichArea="+whichArea;
		 		document.forms[0].submit();
    }
    
 
    function refreshtheweb(){
    var dwdq = document.getElementById("dwdq").value;
    var zgbm = document.getElementById("zgbm").value; 
    var url="/xgxt/refreshjyxyupdate.do?doType2=refresh&act=first&dwdq=";
    url+=dwdq+"&zgbm="+zgbm;
    
		 	document.forms[0].action =url; 
		 	document.forms[0].submit();
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }
	</script>
	<body>
		<html:hidden name="rs" property="whichArea" />
		<html:hidden name="rs" property="nd" />
		<html:form action="/data_search" method="post">
			<fieldset>
				<legend>
					就业协议修改
				</legend>

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height: 25px">
							<td colspan="4" align="center">
								<b>填写就业协议</b>
							</td>
						</tr>
						<tr style="height: 22px">
							<td colspan="4" align="center">

								<b> 学生基本信息</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								学生类别:
								<html:text property="xslb" name="rs" readonly="true"
									style="width:50px" />
								&nbsp;&nbsp;毕业年度:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:40px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
									
								<html:hidden name="rs" property="xxdm" />
							    <html:hidden name="rs" property="xxmc" />
							    <html:hidden name="rs" property="nd" />
								
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="black">注:</font>
								<font color="red">*</font>为必填内容 &nbsp; 其余由系统生成 &nbsp;&nbsp;
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									代码库查询器
								</button>
							</td>
						</tr>
					</thead>
					<tr style="height: 22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" style="">
								<font color="red">*</font>学号：
							</td>
							<td align="left" style="">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" style="">
								<font color="red">*</font>学号：
							</td>
							<td align="left" style="">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<td align="right">
							协议书编号：
						</td>
						<td align="left">
							<html:text name="rs" property="xysbh" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right" style="">
							姓名：
						</td>
						<td align="left">
							<html:text name="rs" property="name" readonly="true"
								style="width=33%" />
						</td>
						<td align="right" style="">
							性别：
						</td>
						<td align="left">
							<html:text name="rs"  property="xb" readonly="true"
								style="width=33%" />
							<html:hidden name="rs"  property="xbdm" />
					</tr>
					<tr style="height: 22px">
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							QQ号：
						</td>
						<td align="left">
							<html:text name="rs" property="qq" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							民族：
						</td>
						<td align="left">
							<html:text name="rs" property="mz" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name="rs" property="xymc" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							学历：
						</td>
						<td align="left">
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							<html:select name="rs" property="zzmm" styleId="zzmmdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="zzmmdmList" property="zzmm" />
							</html:select>
						</td>
						<td align="right">
							学制：
						</td>
						<td align="left">
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height: 22px">

						<td align="right">
							生源地区代码：
						</td>
						<td align="left">
							<html:select name="rs" property="sydqdm" onchange="loadShi()"
								styleId="jgshen">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi" styleId="jgshi">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
						</td>
					</tr>
					<thead>
						<tr style="height: 22px">
							<td colspan="4" align="center">
								<b>填写学生就业信息</b>
							</td>
						</tr>
					</thead>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 培养方式：
						</td>
						<td align="left">
							<html:select name="rs" property="pyfsdm" styleId="pyfsdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="pyfsList" property="pyfs"
									labelProperty="pyfs" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 委培单位：
						</td>
						<td align="left">
							<html:text name="rs" property="wpdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 招生名册：
						</td>
						<td align="left">
							<html:text name="rs" property="zsmc"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 自动处理：
						</td>
						<td align="left">
							<html:text name="rs" property="zdcl"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 毕业去向：
						</td>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px" >
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 就业状况：
						</td>
						<td align="left">
							<html:select name="rs" property="jyzk" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">已就业</html:option>
								<html:option value="1">未就业</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 未就业标志：
						</td>
						<td align="left">
							<html:text name="rs" property="wjybz"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 为就业原因：
						</td>
						<td align="left">
							<html:text name="rs" property="wjyyy"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 报到证备注：
						</td>
						<td align="left">
							<html:text name="rs" property="bdzbz"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 实际单位：
						</td>
						<td align="left">
							<html:text name="rs" property="sjdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 派遣单位ID：
						</td>
						<td align="left">
							<html:text name="rs" property="pqdwid"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 派遣单位：
						</td>
						<td align="left">
							<html:text name="rs" property="pqdw"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 单位性质：
						</td>
						<td align="left">
							<html:text name="rs" property="dwxz1"></html:text>&nbsp<html:text name="rs" property="dwxz2"></html:text>
							
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 性质选择：
						</td>
						<td align="left">
							<html:select name="rs" property="dwxzdm2" styleId="dwxzdm2"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dwxzdm2List" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 主管部门：
						</td>
						<td align="left">
							<html:text name="rs" property="zgbm1"></html:text>
							<html:text name="rs" property="zgbm2"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 主管选择：
						</td>
						<td align="left">
								<html:select name="rs" property="zgbmmc" styleId="sydzgbm"
									style="width:275px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="sydzgbm"
										labelProperty="sydzgbm" />
								</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 单位所在地：
						</td>
						<td align="left">
							<html:select name="rs" property="dwszd1" onchange="loadShi2()"
								styleId="jgshen2">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi2" styleId="jgshi2">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 所在地：
						</td>
						<td align="left">
							<html:text name="rs" property="dwszd2"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 实际所在地：
						</td>
						<td align="left">
							<html:select name="rs"  property="dwszd3" onchange="loadShi3()"
								styleId="jgshen3">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi3" styleId="jgshi3">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 所在地：
						</td>
						<td align="left">
							<html:text name="rs" property="dwdq"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 档案地址：
						</td>
						<td align="left">
							<html:text name="rs" property="dajsd"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 档案邮编：
						</td>
						<td align="left">
							<html:text name="rs" property="dajsdyb"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 户籍地址：
						</td>
						<td align="left" colspan="10">
							<html:text name="rs" property="hkdz"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 改派日期：
						</td>
						<td align="left">
							<html:text name="rs" property="gprq"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 改派次数：
						</td>
						<td align="left">
							<html:text name="rs" property="gpcs"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 改派原因：
						</td>
						<td align="left">
							<html:text name="rs" property="gpyy"></html:text>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font> 原单位名称：
						</td>
						<td align="left">
							<html:text name="rs" property="ydwmc"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 报道证编号：
						</td>
						<td align="left">
							<html:text name="rs" property="bdzbh"></html:text>
						</td>
					</tr>
					<tr>
						<td width="20%" align="right">
							<font color="red">*</font> 备注：
						</td>
						<td align="left">
							<html:textarea name="rs" property="memo"></html:textarea>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							辅导员审核结果：
						</td>
						<td align="left">
							<html:text name="rs" property="fdysh" style="width:210px"
								readonly="true" />
						</td>
						<td align="right">
							学校审核结果：
						</td>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							辅导员审核人：
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshren" style="width:210px"
								readonly="true" />
						</td>
						<td align="right">
							学校审核人：
						</td>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							辅导员审核时间：
						</td>
						<td align="left">
							<html:text name="rs" property="fdyshsj" style="width:210px"
								readonly="true" />
						</td>
						<td align="right">
							学校审核时间：
						</td>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width:210px"
								readonly="true" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="jygljyxyupdate();">
						提 交 修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="whichArea" value="shenhe">
						<button class="button2"
							onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
							关 闭 窗 口
						</button>
					</logic:equal>
					<logic:equal name="whichArea" value="result">
						<button class="button2" onclick="Close();refreshtheweb()">
							关 闭 窗 口
						</button>
					</logic:equal>
				</div>


				<logic:equal name="update" value="ok">
					<script>
    			alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('query_go').click();
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</fieldset>
		</html:form>
	</body>
</html>
