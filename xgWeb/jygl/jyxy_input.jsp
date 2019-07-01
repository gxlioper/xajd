<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	var stylePath = "<%=stylePath%>";
	function savejyxy(){
	var xsxh = document.getElementById("xsxh").value;
	var name = document.getElementById("name").value;
	var xxdm = document.getElementById("xxdm").value;
	if(xsxh==""){
		alert("学号不能为空！");
		return false;
	}
	if(name==""){
		alert("姓名不能为空！");
		return false;
	}
	var byqxdm =document.getElementById("byqxdm").value;
	var dwdq = document.getElementById("dwdq").value;
	if(xxdm=="10333"){
		$("dwdz").value=$("dwshen").value+$("dwshi").value+$("dwxian").value;
		dwdz=$("dwdz").value;
		$("dwdq").value=$("shen").value+$("shi").value+$("xian").value;
		dwdq=$("dwdq").value;
	}
	
	var zgbm = document.getElementById("zgbm").value;
	
	var xxdjh = document.getElementById("xxdjh").value;
	var zzjgdm = document.getElementById("zzjgdm").value;
   
    var zzmmdm = document.getElementById("zzmmdm").value;
    var lxdz = document.getElementById("lxdz").value;
    var yb = document.getElementById("yb").value;
    var dh = document.getElementById("dh").value;
    var dwxzdm = document.getElementById("dwxzdm").value;
    var blueno = document.getElementById("blueno").value;
    
    var sydqdm = document.getElementById("sydqdm").value;
    var jzzhlbbzwdm = document.getElementById("jzzhlbbzwdm").value;
    var dwmc =document.getElementById("dwmc").value;
    var sydzgbm =document.getElementById("sydzgbm").value;
    var wjybz;
    var wjyyy;
    if(xxdm!='10856'){
    	wjybz = document.getElementById("wjybz").value;
    	wjyyy = document.getElementById("wjyyy").value;
    }
    
    var dajsd = document.getElementById("dajsd").value;
    var dajsddz = document.getElementById("dajsddz").value;
    var dajsdyb = document.getElementById("dajsdyb").value;   
    var dwdh = document.getElementById("dwdh").value;
    var dwyb = document.getElementById("dwyb").value;
    var wyj =document.getElementById("wyj").value;
    var dynypjgz =document.getElementById("dynypjgz").value;
    var dqlx = document.getElementById("dqlx").value;
    var hyfl = document.getElementById("hyfl").value;
    var zydk = document.getElementById("zydk").value;
    var dwxzdm2 =document.getElementById("dwxzdm2").value;
    var dwdz = document.getElementById("dwdz").value;
   	
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
	if(xxdm!='10856'){	
		if(byqxdm!="01"&&byqxdm!="02"&&byqxdm!="03"&&byqxdm!="04"&&byqxdm!="12"&&byqxdm!="13"
	   		&&byqxdm!="14"&&byqxdm!="15"&&byqxdm!="17"&&wjybz=="0"){
	   		alert("请再次确认是否就业！如未就业，未就业标志不成立")
	   		return false;
		}
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
	
	if(dwmc !=''&&zzjgdm==""){
		alert("组织机构代码不能为空！");
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
	
	if(xxdm!='10856'){
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
		if(wjybz=="1"&&wjyyy==""){
			alert("请输入未就业原因！");
			return false;
		}
	}
		
	if(zzmmdm==""){
		alert("政治面貌代码不能为空！");
		return false;
	}
	
	if(lxdz==""){
		alert("家庭地址地址不能为空！");
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
	
	if(jzzhlbbzwdm==""){
		alert("居住证或蓝表标志位不能为空！");
		return false;
	}
	
	if(sydqdm!="310000"&&sydzgbm==""){
		alert("生源地主管部门不能为空！");
		return false;
	}
	if(sydqdm!="310000"&&byqxdm=="04"&&dwmc!=sydzgbm){
		alert("如选择出国，单位名称应等同于生源地区主管部门！")
		return false;
	}
	
	if(dwxzdm=="请选择"){
		alert("请选择单位性质代码！");
		return false;
	}
	
    if(!isNumber(blueno)&&blueno!=""){
		alert("蓝表批准文号应为数字！");
		return false;
	}
	if(byqxdm=="01"||byqxdm=="13"&&byqxdm=="14"&&byqxdm=="15"&&byqxdm=="17"){
		if(dwxzdm2==""){
			alert("单位性质代码2不能为空！");
			return false;
		}
		if(dwdh==''){
			alert("单位电话不能为空！");
			return false;
		}
		if(dwyb==''){
			alert("单位邮编不能为空！");
			return false;
		}
		if(dwdz==''){
			alert("单位地址不能为空！");
			return false;
		}
		if(dqlx==''){
			if(xxdm=='10856'){
				alert("单位所在地区不能为空！");
			}else{
				alert("地区流向不能为空！");
			}				
			return false;
		}
		if(hyfl==''){
			alert("行业分类不能为空！");
			return false;
		}
		if(zydk==''){
			alert("专业对口不能为空！");
			return false;
		}
		if(byqxdm=="01"){
			//2010.11.30 by lr 档案接收单位名称为非必填字段（在页面上）
			//if(dajsd==''){
			//	alert("档案接收单位名称不能为空！");
			//	return false;
			//}
			if(dajsddz==''){
				alert("档案接收地地址不能为空！");
				return false;
			}
			if(dajsdyb==''){
				alert("档案接收地邮编不能为空！");
				return false;
			}
			
			if(wyj==''){
				alert("违约金不能为空！");
				return false;
			}		
			if(dynypjgz==''){
				alert("第一年月平均工资不能为空！");
				return false;
			}
			
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
	}else{
		if(dwxzdm2!=""){
			alert("单位性质代码2不用填写！");
			return false;
		}
		if(dwdz!=""){
			alert("单位地址不用填写！");
			return false;
		}
		if(dwdh!=""){
			alert("单位电话不用填写！");
			return false;
		}
		if(dwyb!=""){
			alert("单位邮编不用填写！");
			return false;
		}
		if(wyj!=""){
			alert("违约金不用填写！");
			return false;
		}
		if(dynypjgz!=""){
			alert("第一年月平均工资不用填写！");
			return false;
		}
		if(dqlx!=""){
			alert("地区流向不用填写！");
			return false;
		}
		if(hyfl!=""){
			alert("行业分类不用填写！");
			return false;
		}
		if(zydk!=""){
			alert("专业对口不用填写！");
			return false;
		}
		if(sydqdm!="310000"&&((byqxdm=="04")||(byqxdm=="16"))){
			if(dajsd==""){
				alert("档案接收单位名称不能为空！");
				return false;
			}
			if(dajsddz==""){
				alert("档案接收地地址不能为空！");
				return false;
			}
			if(dajsdyb==""){
				alert("档案接收地邮编不能为空！");
				return false;
			}
		}else{
			if(dajsd!=""){
				alert("档案接收单位名称不用填写！");
				return false;
			}
			if(dajsddz!=""){
				alert("档案接收地地址不用填写！");
				return false;
			}
			if(dajsdyb!=""){
				alert("档案接收地邮编不用填写！");
				return false;
			}
		}	
	}	

		document.forms[0].action = "/xgxt/savejyxy.do?doType=save";
		document.forms[0].submit();
    }
    
   
    
    function refreshtheweb(){
    
    	//常熟学院
    	
    	
    	if($("xxdm").value=="10333"){
    			var byqxdm = document.getElementById("byqxdm").value;
   				var dwdq = document.getElementById("shen").value;
   				var xsxh = document.getElementById("xsxh").value;
    			var zgbm = document.getElementById("zgbm").value;
    			
    	}else{
    			var byqxdm = document.getElementById("byqxdm").value;
   				var dwdq = document.getElementById("dwdq").value;
   				var xsxh = document.getElementById("xsxh").value;
    			var zgbm = document.getElementById("zgbm").value;
        }      
         document.forms[0].action = "/xgxt/savejyxy.do?doType=go&byqxdm="+byqxdm+"&dwdq="+dwdq+"&xsxh="+xsxh+"&zgbm"+zgbm;
         document.forms[0].submit(); 
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	
	function jyxyreinput(){
	            window.location.href="/xgxt/jyxy_input.do?act=first";  
	}
	function getFormatValue(){
		   var getSelectValue = document.getElementById("syd").value;
		   document.getElementById("sydzgbm").value = getSelectValue;         
		}
		
	function loadShiXx(){
		var shen = document.getElementById("shen").value;	
		getStuDetails.shiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "shi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shimc","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			
		});
	}
	
	function dwShiXx(){
		var shen = document.getElementById("dwshen").value;	
		getStuDetails.shiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "dwshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shimc","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			
		});
	}
		
	
	</script>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post" enctype="multipart/form-data">
			<input type="hidden"  name="xxdm" id="xxdm" value="${xxdm}"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 就业协议方案 - 就业协议录入</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
					    alert("您输入的学号无效!");
					</script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />
				<div class="tab">
					<table width="100%" id="rsT" border="0" class="formlist">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>第一部分</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								学生类别
								<html:text property="xslb" name="rs" readonly="true"
									style="width:50px" />
								毕业年度
								<html:text property="bynd" name="rs" readonly="true"
									style="width:40px" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
								<font color="black">注:</font>
								<font color="red">*</font>为必填内容 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
								<button
									onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,350);"
									class="button2">
									代码库查询器
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!-- <a href="/xgxt/jygl/jyxyhelp.jsp" target="_blank"><font color="red">填写帮助说明</font></a> -->
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th style="width=18%">
								<font color="red">*</font>学号
							</th>
							<td style="width=32%" colspan="3">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th style="width=18%">
								<font color="red">*</font>学号
							</th>
							<td style="width=32%" colspan="3">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
					</tr>
					<tr style="height:22px">
						<th >
							协议书编号
						</th>
						<td>
							<html:text property="xysbh" style="width:210px" maxlength="60"/>
						</td>
						<th style="width=20%">
							姓名
						</th>
						<td>
							<html:text name="rs" property="name" readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							性别代码
						</th>
						<td>
							<html:text name="rs" property="xbdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							身份证号
						</th>
						<td>
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							学校代码
						</th>
						<td>
							<html:text name="rs" property="xxdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							学校名称
						</th>
						<td>
							<html:text name="rs" property="xxmc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							专业代码
						</th>
						<td>
							<html:text name="rs" property="zydm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							专业名称
						</th>
						<td>
							<html:text name="rs" property="zymc" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							学制代码
						</th>
						<td>
							<html:text name="rs" property="xzdm" readonly="true"
								style="width:210px" />
						</td>
						<th align="right">
							学历代码
						</th>
						<td>
							<html:text name="rs" property="xldm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							培养方式代码
						</th>
						<td>
							<html:text name="rs" property="pyfsdm" readonly="true"
								style="width:210px" />
						</td>
						<th>
							生源地区代码
						</th>
						<td>
							<html:text name="rs" property="sydqdm" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>第二部分</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th>
							<font color="red">*</font>
							毕业去向代码
						</th>
						<td align="left">
							<html:select name="rs" property="byqxdm" styleId="byqxdm"
								style="width:210px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqxdm"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th>
							生源地区名称
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" style="width:210px"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位地区名称
						</th>
						<td>
							<!-- 常熟理工 -->
							<logic:equal name="xxdm" value="10333">
								<logic:equal name="danweiname" value="X">
							<html:select name="rs" property="sydqdm" onchange="loadShiXx()" styleId="shen"
							style="width:180px">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssmc"
									labelProperty="ssmc" />
							</html:select>
							<br/>
							<html:select name="rs" property="jgshi" styleId="shi"
								 style="width:180px">
								<html:options collection="shiList" property="shimc" 
									labelProperty="shimc" />
							</html:select>
							<br/>
							<html:select name="rs" property="jgxian" styleId="xian"
								style="width:180px">
								<html:options collection="xianList" property="xianmc" 
									labelProperty="xianmc" />
							</html:select>
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="shen" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="shen" readonly="true" value="境外" 
									style="width:210px" />
							</logic:equal>
							<html:hidden name="rs" property="dwdq" styleId="dwdq"/>
							</logic:equal>
							<!-- 非常熟理工 -->
							<logic:notEqual name="xxdm" value="10333">
							<logic:equal name="danweiname" value="X">
								<html:select name="rs" property="dwdq" styleId="dwdq"
									style="width:210px" onchange="refreshtheweb()">
									<html:option value="上海市市辖区">上海市市辖区</html:option>
									<html:options collection="dwdqList" property="dwdq"
										labelProperty="dwdq" />
								</html:select>
								<br/>
								<font color="blue">(默认为上海，可自行选择)</font>
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwdq" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwdq" readonly="true" value="境外"
									style="width:210px" />
							</logic:equal>
							</logic:notEqual>
							
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zgbm">
								<font color="red">*</font>
							</logic:equal>
							(单位所在)主管部门名称
						</th>
						<td>
							<logic:equal name="whichzgbmlist" value="X">
								<html:text name="rs" property="zgbm" style="width:210px"
									readonly="true" />
							</logic:equal>

							<logic:equal name="whichzgbmlist" value="J">
								<html:text name="rs" property="zgbm" value="升学"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="K">
								<html:text name="rs" property="zgbm" value="出国、退学、延长"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="L">
								<html:select name="rs" property="zgbm" styleId="zgbm"
									style="width:210px" onchange="refreshtheweb()">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:options collection="ListL" property="zgbm"
										labelProperty="zgbm" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="M">
								<html:text name="rs" property="zgbm" style="width:210px"
									value="" readonly="true" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位地区代码
						</th>
						<td>
							<logic:equal name="danweiname" value="X">
								<html:text name="rs" property="dwdqdm" readonly="true"
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwdqdm" readonly="true"
									style="width:210px" />
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwdqdm" readonly="true" value=""
									style="width:210px" />
							</logic:equal>
						</td>


						<th>
							主管部门代码
						</th>
						<td>
							<logic:equal name="whichzgbmlist" value="X">
								<html:text name="rs" property="zgbmdm" style="width:210px"
									readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="J">
								<html:text name="rs" property="zgbmdm" value="010"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="K">
								<html:text name="rs" property="zgbmdm" value="011"
									style="width:210px" readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="L">
								<html:text name="rs" property="zgbmdm" style="width:210px"
									readonly="true" />
							</logic:equal>
							<logic:equal name="whichzgbmlist" value="M">
								<html:text name="rs" property="zgbmdm" value=""
									style="width:210px" readonly="true" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="xxdjh">
								<font color="red">*</font>
							</logic:equal>
							信息登记号
						</th>
						<td align="left">
							<html:text name="rs" property="xxdjh" style="width:210px"
								maxlength="7" />
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zzjgdm">
								<font color="red">*</font>
							</logic:equal>
							组织机构代码
						</th>
						<td align="left">
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="zzjgdm" style="width:210px"
								maxlength="9" readonly="true"/>
							</logic:equal>
							<logic:notEqual name="danweiname" value="O">
								<html:text name="rs" property="zzjgdm" style="width:210px"
								maxlength="9" />
							</logic:notEqual>
							

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dwmc">
								<font color="red">*</font>
							</logic:equal>
							单位名称
						</th>
						<td align="left">
							<logic:equal name="danweiname" value="X">
								<html:text name="rs" property="dwmc" style="width:210px"
									maxlength="25" />
							</logic:equal>
							<logic:equal name="danweiname" value="O">
								<html:text name="rs" property="dwmc" style="width:210px"
									value="" readonly="true" />
							</logic:equal>
							<logic:equal name="danweiname" value="Y">
								<html:text name="rs" property="dwmc" style="width:210px"
									maxlength="25" />
							</logic:equal>

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zzmmdm">
								<font color="red">*</font>
							</logic:equal>
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

					<logic:notEqual value="10856" name="xxdm">
					<tr style="height:22px">
						<th>
							<font color="red">*</font>未就业标志：
						</th>
						<td align="left">
							<html:select name="rs" property="wjybz" styleId="wjybz"
								style="width:210px">
								<html:option value=""></html:option>
								<html:option value="0">已就业</html:option>
								<html:option value="1">未就业</html:option>
							</html:select>
						</td>
						<th>
							未就业原因
						</th>
						<td align="left">
							<html:text name="rs" property="wjyyy" style="width:210px"
								maxlength="64" />

						</td>					
					</tr>
					</logic:notEqual>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="lxdz">
								<font color="red">*</font>
							</logic:equal>
							家庭地址
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="64" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="yb">
								<font color="red">*</font>
							</logic:equal>
							邮编
						</th>
						<td align="left">
							<html:text name="rs" property="yb" style="width:210px"
								maxlength="6" />

						</td>
						
					</tr>
					
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dh">
								<font color="red">*</font>
							</logic:equal>
							个人电话
						</th>
						<td align="left">
							<html:text name="rs" property="dh" style="width:210px"
								maxlength="12" />

						</td>
						<th>
							备注
						</th>
						<td align="left">
							<html:text name="rs" property="memo" style="width:210px"
								readonly="true" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="jzzhlbbzwdm">
								<font color="red">*</font>
							</logic:equal>
							居住证或蓝表标志位
						</th>
						<td align="left">
							<html:select name="rs" property="jzzhlbbzwdm"
								styleId="jzzhlbbzwdm" style="width:260px">
								<html:option value=""></html:option>
								<html:options collection="jzzhlbbzwdmList"
									property="jzzhlbbzwdm" labelProperty="jzzhlbbzw" />
							</html:select>

						</td>
						<th>
							蓝表批准文号
						</th>
						<td align="left">
							<html:text name="rs" property="blueno" style="width:210px"
								maxlength="5" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:notEqual value="上海市" name="rs" property="sydq">
								<logic:equal value="yes" name="redflag" property="sydzgbm">
									<font color="red">*</font>
								</logic:equal>
							</logic:notEqual>
							生源地主管部门
						</th>
<%--						<td align="left">--%>
<%--							<logic:equal name="sydqdmIsSh" value="notkong">--%>
<%--								<html:select name="rs" property="sydzgbm" styleId="sydzgbm"--%>
<%--									style="width:260px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="sydzgbmList" property="sydzgbm"--%>
<%--										labelProperty="sydzgbm" />--%>
<%--								</html:select>--%>
<%--							</logic:equal>--%>
<%--							<logic:equal name="sydqdmIsSh" value="kong">--%>
<%--								<html:text name="rs" property="sydzgbm" value=""--%>
<%--									style="width:210px" readonly="true" />--%>
<%--							</logic:equal>--%>
<%--						</td>--%>
							<td align="left">
							<html:hidden name="rs" property="sydzgbm" styleId="sydzgbm"
								style="width:200px;height:21px;font-size:10pt;" />
								<html:select name="rs" property="syd" styleId="syd" style="width:210px" onchange="getFormatValue()">
									<html:options collection="sydzgbmList" property="sydzgbm"
										 labelProperty="sydzgbm" />
								</html:select>
<%--						<html:select name="rs" property="sydzgbm" styleId="sydzgbm"--%>
<%--									style="width:260px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="sydzgbmList" property="sydzgbm"--%>
<%--										labelProperty="sydzgbm" />--%>
<%--								</html:select>--%>
							<logic:equal name="sydqdmIsSh" value="notkong1">
								<html:select name="rs" property="sydzgbm" styleId="sydzgbm"
									style="width:260px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="sydzgbm"
										labelProperty="sydzgbm" />
								</html:select>
							</logic:equal>
							<logic:equal name="sydqdmIsSh" value="kong1">
								<html:text name="rs" property="sydzgbm" value=""
									style="width:210px" readonly="true" />
							</logic:equal>
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="dwxzdm">
								<font color="red">*</font>
							</logic:equal>
							单位性质代码
						</th>
						<td>
							<logic:equal name="whichlist" value="X">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:options collection="dwxzdmList" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="A">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:options collection="ListA" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="B">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="80">升学</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="C">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="85">出国、出境</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="D">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:options collection="ListD" property="dwxzdm"
										labelProperty="dwxz" />
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="E">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:option value="50">国家基层项目*</html:option>
									<html:option value="51">地方基层项目*</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="F">
								<html:select name="rs" property="dwxzdm" style="width:210px">
									<html:option value="70">待就业</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="G">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:option value="71">不就业拟升学</html:option>
									<html:option value="72">其他暂不就业</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="H">
								<html:select name="rs" property="dwxzdm" styleId="dwxzdm"
									style="width:210px">
									<html:option value="请选择">----------请选择----------</html:option>
									<html:option value="70">待就业</html:option>
									<html:option value="71">不就业拟升学</html:option>
									<html:option value="72">其他暂不就业</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="whichlist" value="I">
								<html:text name="rs" property="dwxzdm" value="" readonly="true"
									style="width:210px" />
							</logic:equal>
						</td>
					</tr>
					<tr style="height:22px">
						
						<th>
							自定义1
						</th>
						<td align="left">
							<html:text name="rs" property="bz1" style="width:210px"
								maxlength="50" />

						</td>
						<th>
							自定义2
						</th>
						<td align="left">
							<html:text name="rs" property="bz2" style="width:210px"
								maxlength="50" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							自定义3
						</th>
						<td align="left">
							<html:text name="rs" property="bz3" style="width:210px"
								maxlength="50" />

						</td>
						<th>
							自定义4
						</th>
						<td align="left">
							<html:text name="rs" property="bz4" style="width:210px"
								maxlength="50" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							自定义5
						</th>
						<td >
							<html:text name="rs" property="bz5" style="width:210px"
								maxlength="50" />

						</td>
						<th></th>
						<td></td>
					</tr>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>第三部分</b>
							</td>
						</tr>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>毕业去向为〈派遣、隐性就业、灵活就业、定向委培在职、国家地方项目〉的毕业生需要填写此部分</b>
							</td>
						</tr>
					</thead>

					<tr style="height:22px">
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dwxzdm2">
								<font color="red">*</font>
							</logic:equal>
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
								<logic:equal value="yes" name="redflag" property="dajsd">
									<font color="red">*</font>
								</logic:equal>
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
							<logic:equal value="yes" name="redflag" property="dwdz">
								<font color="red">*</font>
							</logic:equal>
							单位地址
						</th>
						<td align="left">
							<!-- 常熟理工 -->
							<logic:equal name="xxdm" value="10333">
							<html:select name="rs" property="dwshen" onchange="dwShiXx()" styleId="dwshen"
							style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="ssList" property="ssmc"
									labelProperty="ssmc" />
							</html:select>
							<br/>
							<html:select name="rs" property="dwshi" styleId="dwshi"
								 style="width:180px">
								 <html:option value=""></html:option>
								<html:options collection="shiList" property="shimc" 
									labelProperty="shimc" />
							</html:select>
							<br/>
							<html:select name="rs" property="dwxian" styleId="dwxian"
								style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xianList" property="xianmc" 
									labelProperty="xianmc" />
							</html:select>
							<html:hidden  property="dwdz" styleId="dwdz"/>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10333">
								<html:text name="rs" property="dwdz" style="width:210px" />
							</logic:notEqual>
						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="dajsddz">
								<font color="red">*</font>
							</logic:equal>
							档案接收地地址
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddz" style="width:210px" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位联系人
						</th>
						<td>
							<html:text name="rs" property="dwlxr" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
							档案接收单位名称
						</th>
						<td align="left">
							<html:text name="rs" property="dajsddwmc" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dwdh">
								<font color="red">*</font>
							</logic:equal>
							单位电话
						</th>
						<td align="left">
							<html:text name="rs" property="dwdh" style="width:210px"
								maxlength="12" />

						</td>
						<th align="right">
							<logic:equal value="yes" name="redflag" property="dajsdyb">
								<font color="red">*</font>
							</logic:equal>
							档案接收地邮编
						</th>
						<td>
							<html:text name="rs" property="dajsdyb" style="width:210px"
								maxlength="6" />

						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<logic:equal value="yes" name="redflag" property="dwyb">
								<font color="red">*</font>
							</logic:equal>
							单位邮编
						</th>
						<td align="left">
							<html:text name="rs" property="dwyb" style="width:210px"
								maxlength="6" />

						</td>
						<th>
							<logic:equal value="10856" name="xxdm">
								<logic:equal value="yes" name="redflag" property="dqlx">
									<font color="red">*</font>
								</logic:equal>
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
						<th>
							<logic:equal value="yes" name="redflag" property="wyj">
								<font color="red">*</font>
							</logic:equal>
							违约金
						</th>
						<td align="left">
							<html:text name="rs" property="wyj" style="width:210px"
								maxlength="5" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="hyfl">
								<font color="red">*</font>
							</logic:equal>
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
						<th>
							<logic:equal value="yes" name="redflag" property="dynypjgz">
								<font color="red">*</font>
							</logic:equal>
							第一年月平均工资
						</th>
						<td align="left">
							<html:text name="rs" property="dynypjgz" style="width:210px"
								maxlength="5" />

						</td>
						<th>
							<logic:equal value="yes" name="redflag" property="zydk">
								<font color="red">*</font>
							</logic:equal>
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
						<th>
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
						<th>
							单位注册资金
						</th>
						<td align="left">
							<html:text name="rs" property="dwzczj" style="width: 210px;" maxlength="60"></html:text>
						</td>
					</tr>
					<logic:equal value="11122" name="xxdm" scope="session">
					<tr style="height:22px">
						<th>
							报道开始时间
						</th>
						<td align="left">
								<html:text property="bdkssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdkssj','y-mm-dd');" ></html:text>
						</td>
						<th>
							报道结束时间
						</th>
						<td align="left">
							<html:text property="bdjssj" maxlength="60" style="width: 210px;" onclick="return showCalendar('bdjssj','y-mm-dd');"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							报道证号
						</th>
						<td align="left">
							<html:text property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					</logic:equal>
					<logic:equal value="12620" name="xxdm">
							<tr >
						<th align="right">处理决定书、书面材料或附件：</th>
						<td align="left" colspan="3">
							<font color="red">(如材料过多，请打包上传)</font>
							<br/>
							<input type="file" name="uploadFile" id="cfwj" contentEditable="false" style="width:60%" />
						</td>
						</tr>
					</logic:equal>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button  onclick="savejyxy()">
										提 交 协 议
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button  onclick="jyxyreinput()">
										取 消 重 填
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button  onclick="expAppTab('rsT','就业协议','')">
										打 印 协 议
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("提交失败！请检查是否重复提交！");
    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("学校审核尚未通过，请等待学校审核通过以后再来申请！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
