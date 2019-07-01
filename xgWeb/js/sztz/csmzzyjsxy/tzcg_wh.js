//通过XN，XQ，XMDM，XMJB获取活动项目
function getHdxm(){
	var xn=$("xn").value;
	var xq = $("xq").value;
	var kmmc = $("kmmc").value;
	var xmjb = $("xmjb").value;
	sztzLzzy.getHdxm(xn,xq,kmmc,xmjb,getData)
}

function getData(data){
	DWRUtil.removeAllOptions($("xmmc"));		
	DWRUtil.addOptions($("xmmc"),data,"dm","mc");
}


function getTzxmXx(){
	var xmdm=$("xmmc").value;
	var str = "/xgxt/csmz_sztz.do?method=tzcg_wh&xmdm="+xmdm;
	document.forms[0].action = str;
 	document.forms[0].submit(); 	
}

function getXzx(){
	DWRUtil.removeAllOptions($("titList"));
	var xzx=$("xzx").value;
	if(xzx=="xy"){
		$("xymc").style.display="";
	    $("zymc").style.display="none";
	    $("bjmc").style.display="none";
		$("xy").style.display = "";
		$("zy").style.display = "none";
		$("bj").style.display = "none";
		$("xh").style.display = "none";
		$("xm").style.display = "none";
		$("xhbutton").style.display="none";
		$("xmbutton").style.display="none";
	}else if(xzx=="zy"){
	    $("xymc").style.display="";
	    $("zymc").style.display="";
	    $("bjmc").style.display="none";
		$("xy").style.display = "";
		$("zy").style.display = "";
		$("bj").style.display = "none";
		$("xh").style.display = "none";
		$("xm").style.display = "none";
		$("xhbutton").style.display="none";
		$("xmbutton").style.display="none";
	}else if(xzx=="bj"){
		$("xymc").style.display="";
	    $("zymc").style.display="";
	    $("bjmc").style.display="";
		$("xy").style.display = "";
		$("zy").style.display = "";
		$("bj").style.display = "";
		$("xh").style.display = "none";
		$("xm").style.display = "none";
		$("xhbutton").style.display="none";
		$("xmbutton").style.display="none";
	}else if(xzx=="xh"){
		$("xymc").style.display="none";
	    $("zymc").style.display="none";
	    $("bjmc").style.display="none";
		$("xy").style.display = "none";
		$("zy").style.display = "none";
		$("bj").style.display = "none";
		$("xh").style.display = "";
		$("xm").style.display = "none";
		$("xhbutton").style.display="";
		$("xmbutton").style.display="none";
	}else if(xzx=="xm"){
		$("xymc").style.display="none";
	    $("zymc").style.display="none";
	    $("bjmc").style.display="none";
		$("xy").style.display = "none";
		$("zy").style.display = "none";
		$("bj").style.display = "none";
		$("xh").style.display = "none";
		$("xm").style.display = "";
		$("xhbutton").style.display="none";
		$("xmbutton").style.display="";
	}
}
//根据学院获取学生
function getStuXxByXy(){
	var xydm=$("xy").value;
	var userType=$("userType").value;
	var userName=$("userName").value;
	var userDep=$("userDep").value;
	var isFdy=$("isFdy").value;
	var isBzr=$("isBzr").value;
	sztzLzzy.getStuXxByXy(xydm,userType,userDep,userName,isFdy,isBzr,getStuData);
}

//根据专业获取学生
function getStuXxByZy(){
	var zydm=$("zy").value;
	var xydm=$("xy").value;
	var userType=$("userType").value;
	var userName=$("userName").value;
	var userDep=$("userDep").value;
	var isFdy=$("isFdy").value;
	var isBzr=$("isBzr").value;
	if(zydm=="" || zydm.length<1){
		sztzLzzy.getStuXxByXy(xydm,userType,userDep,userName,isFdy,isBzr,getStuData);
	}else{
		sztzLzzy.getStuXxByZy(zydm,userType,userDep,userName,isFdy,isBzr,getStuData);
	}
}

//根据班级获取学生
function getStuXxByBj(){
	var bjdm=$("bj").value;
	var zydm=$("zy").value;
	var userType=$("userType").value;
	var userName=$("userName").value;
	var userDep=$("userDep").value;
	var isFdy=$("isFdy").value;
	var isBzr=$("isBzr").value;
	if( bjdm.length<1 || bjdm==""){
		sztzLzzy.getStuXxByZy(zydm,userType,userDep,userName,isFdy,isBzr,getStuData);
	}else{
		sztzLzzy.getStuXxByBj(bjdm,userType,userDep,userName,isFdy,isBzr,getStuData);
	}
}

//根据学号获取学生
function getStuXxByXh(){
	var xh=$("xh").value;
	var userType=$("userType").value;
	var userName=$("userName").value;
	var userDep=$("userDep").value;
	var isFdy=$("isFdy").value;
	var isBzr=$("isBzr").value;
	if(xh=="" || xh.length<1){
		alert("请输入学号！");
		return false;
	}
	sztzLzzy.getStuXxByXh(xh,userType,userDep,userName,isFdy,isBzr,getStuData);
}

//根据姓名获取学生
function getStuXxByXm(){
	var xm=$("xm").value;
	var userType=$("userType").value;
	var userName=$("userName").value;
	var userDep=$("userDep").value;
	var isFdy=$("isFdy").value;
	var isBzr=$("isBzr").value;
	if(xm=="" || xm.length<1){
		alert("请输入姓名！");
		return false;
	}
	sztzLzzy.getStuXxByXm(xm,userType,userDep,userName,isFdy,isBzr,getStuData);
}

function getStuData(data){
	DWRUtil.removeAllOptions($("titList"));		
	DWRUtil.addOptions($("titList"),data,"xh","xm");
}

//保存信息
function saveInfo(){
	var xmmc=$("xmmc").value;
	var jxlb=$("jxlb").value;
	var num = document.getElementById("titList").options.length;
	if(xmmc=="" || xmmc==null){
		alert("项目名称不能为空！");
		return false;
	}
	if(jxlb=="" || jxlb==null){
		alert("获奖奖项不能为空！");
		return false;
	}
	if(num<1){
		alert("请选择要申报的学生！");
		return false;
	}
	
	var url="/xgxt/csmz_sztz.do?method=tzcg_wh&doType=save"
	var titList=$("titList");
	for(var i = 0 ; i <titList.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "stuInfo";
			tmp.value = titList.options[i].value;
			document.forms[0].appendChild(tmp);
		}
	saveUpdate(url,'');
}

//统计时增加反选功能
function fxxs(){
	var num = document.getElementById("titList").options.length;
	var obj = document.getElementById("titList");
	document.forms[0].cols.value = "";
	for(i=0; i<num; i++){
		if(obj.options[i].text.substring(0, 1) == "√"){
			obj.options[i].text = obj.options[i].text.replace("√", "\xd7");
			obj.options[i].value = obj.options[i].value.replace("√", "\xd7");
		}else{
			obj.options[i].text = obj.options[i].text.replace("\xd7", "√");
			obj.options[i].value = obj.options[i].value.replace("\xd7", "√");
			document.forms[0].cols.value = document.forms[0].cols.value + obj.options[i].value;
		}
	}
}

function chkOnRow(obj) {
	var tmp = obj.selectedIndex;
	var vCol = document.getElementById("titList").options.length;
	if (obj.options[tmp].text.substring(0, 1) == "√") {
		obj.options[tmp].text = obj.options[tmp].text.replace("√", "\xd7");
		obj.options[tmp].value = obj.options[tmp].value.replace("√", "\xd7");
		var removeFlag;
		for (i = 0; i < vCol.length; i++) {
			if (vCol[i] == obj.options[tmp].value) {
				removeFlag = i;
			}
		}
		document.forms[0].cols.value = "";
		for (j = 0; j < vCol.length; j++) {
			if (j != removeFlag) {
				document.forms[0].cols.value = document.forms[0].cols.value + vCol[j];
			}
		}
	} else {
		obj.options[tmp].text = obj.options[tmp].text.replace("\xd7", "√");
		obj.options[tmp].value = obj.options[tmp].value.replace("\xd7", "√");
		document.forms[0].cols.value = document.forms[0].cols.value + obj.options[tmp].value;
	}
	obj.selectedIndex = -1;
}

function onLoadXsxx(){
	var xydm="";
	var zydm="";
	var bjdm="";
	var xh="";
	var xm="";
	var xzx="";
	if($("xzx")){
		xzx=$("xzx").value;
	}
	if($("xy")){
		xydm=$("xy").value;
	}
	if($("zy")){
		zydm=$("zy").value;
	}
	if($("bj")){
		bjdm=$("bj").value;
	}
	if($("xm")){
		xm=$("xm").value;
	}
	if($("xh")){
		xh=$("xh").value;
	}
	
	if(xzx=="xh"){
		getStuXxByXh();
	}
	if(xzx=="xm"){
		getStuXxByXm();
	}
	if(xzx=="xy"){
		getStuXxByXy();
	}
	if(xzx=="zy"){
		getStuXxByZy();
	}
	if(xzx=="bj"){
		getStuXxByBj();
	}
}

