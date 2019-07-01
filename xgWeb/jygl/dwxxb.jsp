<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function changePage(defaultId){//切换页面 
	var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName,anotherName,anotherName2;
	if (title == "zks"){
		titleName = "zks";
		document.getElementById("titName").value = "zks";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName2 = "bks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  } else if (title == "yjs") {
		titleName = "yjs";
		document.getElementById("titName").value = "yjs";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName2 = "bks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  }	else if (title == "bks") {
		titleName = "bks";
		document.getElementById("titName").value = "bks";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zks";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	  }	  	  
		document.forms[0].submit();
   }
   
   
	function loadchange(){
	var tab = document.getElementById("titName").value;
	document.getElementById(tab+"l").className = "xxk_on_l";
	document.getElementById(tab+"m").className = "xxk_on_m";
	document.getElementById(tab+"r").className = "xxk_on_r";
    }
    
    
    function add(url){
        var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
    	var sfzh = document.getElementById("sfzh").value;
    	var zydm = document.getElementById("zydm").value;
    	var zymc = document.getElementById("zymc").value;
    	var xldm = document.getElementById("xldm").value;
    	var sydqdm = document.getElementById("sydqdm").value;
    	var pyfsdm = document.getElementById("pyfsdm").value;
    	var xz = document.getElementById("xz").value;
    	var titName = document.getElementById("titName").value;
    	
    	if(xh==""){
    	alert("学号必须填写！");
    	return false;
    	}
    	if(!isNumber(xh)){
    	alert("学号输入错误！");
    	return false;
    	}
    	if(xm==""){
    	alert("姓名必须填写！");
    	return false;
    	}
    	if(zydm==""){
    	alert("专业代码必须填写！");
    	return false;
    	}	
    	if(zymc==""){
    	alert("专业名称必须填写！");
    	return false;
    	}
    	if(xldm==""){
    	alert("学历代码必须填写！");
    	return false;
    	}
    	if(xz=="4"&&titName!="bks"){
    	alert("学制与学生类别发生冲突，请检查！");
    	return false;
    	}
    	if(xz=="7"&&titName!="yjs"){
    	alert("学制与学生类别发生冲突，请检查！");
    	return false;
    	}
    	if(xz=="3"&&titName=="bks"){
    	alert("学制与学生类别发生冲突,请检查！");
    	return false;
    	}
    	if(sydqdm==""){
    	alert("生源地区代码必须填写！");
    	return false;
    	}
    	if(pyfsdm==""){
    	alert("培养方式代码必须填写！");
    	return false;
    	}	     
    	if(checkSfzh(sfzh)){
		 		document.forms[0].action = "/xgxt/bysjbxxbSave.do?doType=save&act=cancle";
		 		document.forms[0].submit();
        }
    }
    
    function reinputagain(url){
    		
            document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=cancle";
		 	document.forms[0].submit();
    }
    
    function checkSfzh(sfzh) {
    sfzh=sfzh.toUpperCase()
	var OldID;
	if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	return true;
    }

function isCard(s){ 
	s = trim(s); 
	var p = /^\d{15}(\d{2}[xX0-9])?$/; 
	return p.test(s);
}
		   
function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}

function queryxh(){
	var xh = document.getElementById("xh").value;
	
	 document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=query&xh="+xh;
     document.forms[0].submit();
}




	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="loadchange()">
		<html:form action="/bysjbxxb_input.do" method="post">
		
			<div class="title">
				<div class="title_img" id="title_m">
					就业管理 - 学生信息 - 学生信息上报
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="titName" name="titName"
					value="<bean:write name="titName" scope="request" />" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />
				<input type="hidden" id="url" name="url" value="/bysjbxxb_input.do" />

				<div class="xxk">
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request">
							<ul>
								<li id="<bean:write name='card' property='en'/>l"
									class="xxk_off_l"></li>
								<li id="<bean:write name='card' property='en'/>m"
									onclick="changePage(this)" class="xxk_off_m">
									&nbsp;
									<bean:write name='card' property='cn' />
									&nbsp;
								</li>
								<li id="<bean:write name='card' property='en'/>r"
									class="xxk_off_r"></li>
							</ul>
						</logic:iterate>
					</logic:notEmpty>
				</div>



				<table class="tbstyle" id="rsTable" width="100%" style="cursor:hand">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<b>学生信息录入</b>
						<!-- <button onclick="showTopWin('/xgxt/turndmkqueryjsp.do',427,480);"class="button2"> 代码库查询器</button>   -->		
							</td>
						</tr>
					</thead>
					<tr align="center" style="cursor:hand">
								<td align="right">
									<font color="red">*</font>
									单位名称：
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="if(window.event.keyCode==13) queryxh();" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>
								学号：
							</td>
							<td align="left">
								<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
							</td>
						</logic:equal>
						<td width="20%" align="right">
							<font color="red">*</font>
							身份证号：
						</td>
						<td align="left">
							<html:text name='rs' property="sfzh" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" readonly="true" />
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							性别代码：
						</td>
						<td align="left">
							<html:text name='rs' property="xbm" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							学校代码：
						</td>
						<td align="left">
							<html:text name='rs' property="xxdm" 
								readonly="true" />
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							学校名称：
						</td>
						<td align="left">
							<html:text name='rs' property="xxmc" 
								readonly="true" />

						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							专业代码：
						</td>
						<td align="left">
							<html:text name="rs" property="zydm" readonly="true" />
						</td>
						<td width="20%" align="right" readonly="true">
							<font color="red">*</font>
							专业名称：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							学历代码：
						</td>
						<td align="left">
						<logic:equal name="Listxl" value="zks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="41">专科生毕业</html:option>
									<html:option value="43">专科生结业</html:option>
									<html:option value="49">专科生肆业</html:option>
									<html:option value="61">高职</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="bks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="25">双学位毕业</html:option>
									<html:option value="31">本科生毕业</html:option>
									<html:option value="33">本科生结业</html:option>
									<html:option value="39">本科生肆业</html:option>
									<html:option value="61">高职</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="yjs">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="11">硕士生毕业</html:option>
									<html:option value="13">硕士生结业</html:option>
									<html:option value="19">硕士生肆业</html:option>
									<html:option value="21">硕班毕业</html:option>
								</html:select>
							</logic:equal>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							学制：
						</td>
						<td align="left">
							<html:text name='rs' property="xz" readonly="true" />
						</td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font> 
							生源地区代码：
						</td>
						<td align="left">
							<html:select name="rs" property="sydqdm" styleId="sydqdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="sydqList" property="sydqdm"
									labelProperty="sydq" />
							</html:select>
						</td>
						<td width="20%" align="right">
							<font color="red">*</font>
							入学年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true" />
						</td>

					</tr>
					<tr align="center" style="cursor:hand">
						<td width="20%" align="right">
							<font color="red">*</font>
							培养方式代码：
						</td>
						<td align="left">
							<html:select name="rs" property="pyfsdm" styleId="pyfsdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="pyfsList" property="pyfsdm"
									labelProperty="pyfs" />
							</html:select>
						</td>
						<td width="20%" align="right">
							备注：
						</td>
						<td align="left">
							<html:text name="rs" property="memo" />
						</td>
					</tr>
				</table>

				<div class="buttontool" align="center">
					<button class="button2" onclick="add('/xgxt/bysjbxxbSave.do')"
						style="width:80px">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="reinputagain('/xgxt/bysjbxxb_input.do')" type="reset"
						style="width:80px">
						重 填
					</button>
				</div>

			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("提交成功！");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("重复提交！操作失败!");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="que">
					<script>
                      alert("所有选项必须填写！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
</html>
