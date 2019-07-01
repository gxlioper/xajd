<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function jygljyxyupdate(){

	    var whichArea = document.getElementById("whichArea").value;
	    var fdysh = "";
	    if($("fdysh")){
	   		fdysh = document.getElementById("fdysh").value;
	    }
	    var xxsh = document.getElementById("xxsh").value;
	    var xsxh = document.getElementById("xsxh").value;
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
	
	            
	    if(fdysh=="已通过√"&&xxsh=="未审核"||xxsh=="已通过√"){
	    alert("审核已通过，你已无权修改！");
	    return false;
	    }        
	            
		 		document.forms[0].action = "/xgxt/savejyxyupdate.do?doType=update&act=update&whichArea="+whichArea;
		 		document.forms[0].submit();
		 		var sumbtn = document.getElementsByTagName("button");
		 		for(var i = 0;i<sumbtn.length;i++){
		 			sumbtn[i].disabled = true;
		 		}
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
	</head>
	
	<body>
		<html:hidden name="rs" property="whichArea" />
		<html:hidden name="rs" property="nd" />
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 就业协议方案 - 就业协议修改</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b> 第一部分</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								学生类别
								<html:text property="xslb" name="rs" readonly="true"
									style="width:45px" />
								毕业年度
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" readonly="true"
									style="width:90px" />
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									代码库查询器
								</button>
								提交时间
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
							</td>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<th align="right" width="14%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="36%">
							<html:text property="xsxh" name="rs" styleId="xsxh"
								readonly="true" style="width:210px" />
						</td>
						<th align="right" width="15%">
						</th>
						<td align="left" width="35%">
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							协议书编号
						</th>
						<td align="left">
							<html:text name="rs" property="xysbh" style="width:210px" maxlength="60"/>
						<th align="right" style="width=20%">
							姓名
						</th>
						<td align="left">
							<html:text name="rs" property="name" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							性别代码
						</th>
						<td align="left">
							<html:text name="rs" property="xbdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							身份证号
						</th>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							学校代码
						</th>
						<td align="left">
							<html:text name="rs" property="xxdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							学校名称
						</th>
						<td align="left">
							<html:text name="rs" property="xxmc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							专业代码
						</th>
						<td align="left">
							<html:text name="rs" property="zydm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							专业名称
						</th>
						<td align="left">
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							学制代码
						</th>
						<td align="left">
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							学历代码
						</th>
						<td align="left">
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							培养方式代码
						</th>
						<td align="left">
							<html:text name="rs" property="pyfsdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							生源地区代码
						</th>
						<td align="left">
							<html:text name="rs" property="sydqdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>第二部分</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							毕业去向代码
						</th>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th align="right">
							生源地区名称
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" style="width:210px"
								readonly="true" />
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							单位地区名称
						</th>
						<td align="left">
							<html:select name="rs" property="dwdq" styleId="dwdq"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="dwdqList" property="dwdq"
									labelProperty="dwdq" />
							</html:select>
						</td>
						<th align="right">
							主管部门名称
						</th>
						<td align="left">
							<html:select name="rs" property="zgbm" styleId="zgbm"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="zgbmList" property="zgbm"
									labelProperty="zgbm" />
							</html:select>
						</td>

					</tr>
					<tr style="height:22px">
						<th align="right">
							单位地区代码
						</th>
						<td align="left">
							<html:text name="rs" property="dwdqdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							主管部门代码
						</th>
						<td align="left">
							<html:text name="rs" property="zgbmdm" readonly="true"
								style="width:210px" />
						</td>

					</tr>
					<tr style="height:22px">
						<th align="right">
							信息登记号
						</th>
						<td align="left">
							<html:text name="rs" property="xxdjh" style="width:210px" />

						</td>
						<th align="right">
							组织机构代码
						</th>
						<td align="left">
							<html:text name="rs" property="zzjgdm" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位名称
						</th>
						<td align="left">
							<html:text name="rs" property="dwmc" style="width:210px" />

						</td>
						<th align="right">
							政治面貌代码
						</th>
						<td align="left">
							<html:select name="rs" property="zzmmdm" styleId="zzmmdm"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="zzmmdmList" property="zzmmdm"
									labelProperty="zzmm" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							未就业标志
						</th>
						<td align="left">
							<html:select name="rs" property="wjybz" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">已就业</html:option>
								<html:option value="1">未就业</html:option>
							</html:select>
							
						</td>
						<th align="right">
							自定义1
						</th>
						<td align="left">
							<html:text name="rs" property="bz1" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							未就业原因
						</th>
						<td align="left">
							<html:text name="rs" property="wjyyy" style="width:210px" />

						</td>
						<th align="right">
							自定义2
						</th>
						<td align="left">
							<html:text name="rs" property="bz2" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							联系地址
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px" />
							
						</td>
						<th align="right">
							自定义3
						</th>
						<td align="left">
							<html:text name="rs" property="bz3" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							邮编
						</th>
						<td align="left">
							<html:text name="rs" property="yb" style="width:210px" />
							
						</td>
						<th align="right">
							自定义4
						</th>
						<td align="left">
							<html:text name="rs" property="bz4" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							电话
						</th>
						<td align="left">
							<html:text name="rs" property="dh" style="width:210px" />
							
						</td>
						<th align="right">
							自定义5
						</th>
						<td align="left">
							<html:text name="rs" property="bz5" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							
						</th>
						<td align="left">
						
						</td>
						<th align="right">
							居住证或蓝表标志位
						</th>
						<td align="left">
							<html:select name="rs" property="jzzhlbbzwdm"
								styleId="jzzhlbbzwdm" style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="jzzhlbbzwdmList"
									property="jzzhlbbzwdm" labelProperty="jzzhlbbzw" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							生源地主管部门
						</th>
						<td align="left">
							<html:select name="rs" property="sydzgbm" styleId="sydzgbm"
								style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="sydzgbmList" property="sydzgbm"
									labelProperty="sydzgbm" />
							</html:select>
						</td>
						<th align="right">
							单位性质代码
						</th>
						<td align="left">
							<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
								style="width=100%">
								<html:option value=""></html:option>
								<html:options collection="dwxzdmList" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							蓝表批准文号
						</th>
						<td align="left">
							<html:text name="rs" property="blueno" style="width:210px" />

						</td>
						<th align="right">
							备注
						</th>
						<td align="left">
							<html:text name="rs" property="memo" style="width:210px" />

						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>第三部分</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							单位性质代码2
						</th>
						<td align="left">
							<html:select name="rs" property="dwxzdm2" styleId="dwxzdm2"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dwxzdm2List" property="dwxzdm"
									labelProperty="dwxz" />
							</html:select>
						</td>
						<th align="right">
							<logic:equal value="10856" name="xxdm">
								档案接收单位名称
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								档案接收地
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text name="rs" property="dajsd" style="width:210px" />
							
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位地址
						</th>
						<td align="left">
							<html:text name="rs" property="dwdz" style="width:210px" />

						</td>
						<th align="right">
							档案接收地地址
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddz" style="width:210px" />
							
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位联系人
						</th>
						<td align="left">
							<html:text name="rs" property="dwlxr" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th align="right">
							档案接收单位名称
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddwmc" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位电话
						</th>
						<td align="left">
							<html:text name="rs" property="dwdh" style="width:210px" />
						</td>
						<th align="right">
							档案接收地邮编
						</th>
						<td align="left">
							<html:text name="rs" property="dajsdyb" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位邮编
						</th>
						<td align="left">
							<html:text name="rs" property="dwyb" style="width:210px" />
						</td>
						<th align="right">
							<logic:equal value="10856" name="xxdm">
								单位所在地区
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								地区流向
							</logic:notEqual>
						</th>
						<td align="left">
							<html:select name="rs" property="dqlx" styleId="dqlx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="dqlxdmList" property="dqlx"
									labelProperty="dqlx" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							违约金
						</th>
						<td align="left">
							<html:text name="rs" property="wyj" style="width:210px" />
						</td>
						<th align="right">
							行业分类
						</th>
						<td align="left">
							<html:select name="rs" property="hyfl" styleId="hyfl"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="hyflList" property="hyfl"
									labelProperty="hyfl" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							第一年月平均工资
						</th>
						<td align="left">
							<html:text name="rs" property="dynypjgz" style="width:210px" />
						</td>
						<th align="right">
							专业对口
						</th>
						<td align="left">
							<html:select name="rs" property="zydk" styleId="zydk"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="1">是</html:option>
								<html:option value="2">否</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							单位规模
						</th>
						<td align="left">
							<html:select name="rs" property="dwgm" style="width: 210px;">
								<html:option value=""></html:option>
								<html:option value="10人以下">10人以下</html:option>
								<html:option value="10-49人">10-49人</html:option>
								<html:option value="50-99人">50-99人</html:option>
								<html:option value="100-499人">100-499人</html:option>
								<html:option value="500以上">500以上</html:option>
							</html:select>
						</td>
						<th align="right">
							单位注册资金
						</th>
						<td align="left">
							<html:text name="rs" property="dwzczj" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							学校审核结果
						</th>
						<td align="left">
							<html:text name="rs" property="xxsh" style="width:210px"
								readonly="true" />
						</td>
						<th align="right">
							学校审核人
						</th>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						
						<th align="right">
							学校审核时间
						</th>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width:210px"
								readonly="true" />
						</td>
						<th align="right">
							报道开始时间
						</th>
						<td align="left">
								<html:text name="rs" property="bdkssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdkssj','y-mm-dd');" ></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						
						<th align="right">
							报道结束时间
						</th>
						<td align="left">
							<html:text name="rs" property="bdjssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdjssj','y-mm-dd');"></html:text>
						</td>
						<th align="right">
							报道证号
						</th>
						<td align="left">
							<html:text name="rs" property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button  onclick="jygljyxyupdate()" >
										保 存
									</button>
									<logic:equal name="whichArea" value="shenhe">
										<button 
											onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
											关 闭
										</button>
									</logic:equal>
									<logic:equal name="whichArea" value="result">
										<button " onclick="Close();refreshtheweb()">
											关 闭
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<logic:equal name="update" value="ok">
					<script>
				    	alert("提交成功！");
				    	if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
				    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
					    alert("提交失败！");
					    if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
					</script>
				</logic:equal>
			</fieldset>
		</html:form>
	</body>
</html>
