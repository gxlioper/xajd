var url="";
function getXmdj(){
	var zzxm=$("zzxm").value;
	zzxmTj.getXmdjList(zzxm,getData);
}

function getData(data){
	if(data==null){
		$('xmdj').disabled="true";
	}
	DWRUtil.removeAllOptions($("xmdj"));		
	DWRUtil.addOptions($("xmdj"),data,"dm","mc");
}

//判断是否分级
function checkSffj(){
	var zzxm=$("zzxm").value;
	if(zzxm!=""){
		zzxmTj.checkSffj(zzxm,getSffj);
	}
}

function getSffj(data){
	if(data=="true"){
		$("xmdjdiv").style.display="";
		$("xmdjVdiv").style.display="";
	}else{
		$("xmdjdiv").style.display="none";
		$("xmdjVdiv").style.display="none";
	}
}

//根据选项变更信息
function changeXyZyBj(){
	var xzx=$("xzx").value;
	var userType=$("userType").value;
	if(xzx!="" && userType!="xy"){
		if(xzx=="xydm"){
		  $("xy").disabled="";
		  $("zy").disabled="true";
		  $("bj").disabled="true";
		}else if(xzx=="zydm"){
		  $("xy").disabled="";
		  $("zy").disabled="";
		  $("bj").disabled="true";
		}else if(xzx=="bjdm"){
		  $("xy").disabled="";
		  $("zy").disabled="";
		  $("bj").disabled="";
		}else {
		  $("xy").disabled="";
		  $("zy").disabled="";
		  $("bj").disabled="";
		}
	}else if(xzx!="" && userType=="xy"){
		if(xzx=="xydm"){
		  $("xy").disabled="true";
		  $("zy").disabled="true";
		  $("bj").disabled="true";
		}else if(xzx=="zydm"){
		  $("xy").disabled="true";
		  $("zy").disabled="";
		  $("bj").disabled="true";
		}else if(xzx=="bjdm"){
		  $("xy").disabled="true";
		  $("zy").disabled="";
		  $("bj").disabled="";
		}else {
		  $("xy").disabled="true";
		  $("zy").disabled="";
		  $("bj").disabled="";
		}
	}
}
//获取资助周期
function getZzZq(){
	var zzxm=$("zzxm").value;
	zzxmTj.getZzZq(zzxm,zzzqData);
}

//
function zzzqData(data){
	if(data.sqzq=="学年"){
		$("xn").disabled="";
		$("xq").disabled="true";
		$("nd").disabled="true";
	}else if(data.sqzq=="学期"){
		$("xn").disabled="true";
		$("xq").disabled="";
		$("nd").disabled="true";
	}else if(data.sqzq=="年度"){
		$("xn").disabled="true";
		$("xq").disabled="true";
		$("nd").disabled="";
	}else{
		$("xn").disabled="true";
		$("xq").disabled="true";
		$("nd").disabled="true";
	}
}

function checkXzTj(){
		var zzxm=$("zzxm").value;
		var xzx=$("xzx").value;
		if(!checkSearchTj("kssj","jssj")){
			return false;
		}
		if(zzxm=="" || zzxm==null){
			alert("请选择资助项目！");
			return false;
		}
		
		if(xzx=="" || xzx==null){
			alert("请选择统计范围！");
			return false;
		}
		allNotEmpThenGo('xszzZzxmtj.do?method=zzxmtj&doType=qry');
}

function zzxmtj(url){
		var zzxm=$("zzxm").value;
		var xzx=$("xzx").value;
		if(!checkSearchTj("kssj","jssj")){
			return false;
		}
		if(zzxm=="" || zzxm==null){
			alert("请选择资助项目！");
			return false;
		}
		
		if(xzx=="" || xzx==null){
			alert("请选择统计范围！");
			return false;
		}
	 	document.forms[0].target = "_blank";
        document.forms[0].action=url;
        document.forms[0].submit();
        document.forms[0].target = "_self";
}