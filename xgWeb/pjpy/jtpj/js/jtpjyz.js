/*集体评奖条件设置申请时联动js 1.0,by yxy[1206]*/
function jxidChange(){
	var jxid = jQuery.trim(jQuery("#jxid").val());
	var jtpjdw  = jQuery.trim(jQuery("[name='jtpjdw']").val());
	var pjjtid = jQuery.trim(jQuery("#pjjtid").val());
	 if(jxid == "" || jxid == null || jtpjdw != "bj" || pjjtid == "" || pjjtid == null){
		 jQuery("#divyzts").empty();
		 jQuery("#divyzts").hide();
		//下面做验证参数的清除
			//
			//
		 return false;
	 }
	var jsondata = getData(jxid,pjjtid); 
	if(jsondata == null){
		jQuery("#divyzts").empty();
		jQuery("#divyzts").hide();
		//下面做验证参数的清除
		//
		//
		return false;
	}
	createYztshtml(jsondata);
	
	
	
}

function initFormYz(){
	var jxid = jQuery.trim(jQuery("#jxid").val());
	var jtpjdw  = jQuery.trim(jQuery("[name='jtpjdw']").val());
	var pjjtid = jQuery.trim(jQuery("#pjjtid").val());
	 if(jxid == "" || jxid == null || jtpjdw != "bj" || pjjtid == "" || pjjtid == null){
		 return false;
	 }
	 var jsondata = getData(jxid,pjjtid); 
	if(jsondata == null){
		return false;
	}
	createYztshtml(jsondata);
}

function getData(xmdm,bjdm){
	var jsondata = null;
	jQuery.post("xpj_sqsh.do?method=selectJtpjxm",{xmdm:xmdm,bjdm:bjdm},function(data){
		if(data.length != 0 && data != "" && data != null  && data !="null" && data !="undefined"){
			jsondata = data;
		}
	},'json');
	return jsondata;
}

function pjjtidchange(){
	var jxid = jQuery.trim(jQuery("#jxid").val());
	var jtpjdw  = jQuery.trim(jQuery("[name='jtpjdw']").val());
	var pjjtid = jQuery.trim(jQuery("#pjjtid").val());
    if(jxid == "" || jxid == null || (jtpjdw != "bj"&&jtpjdw!="qs") || pjjtid == "" || pjjtid == null){
      jQuery("#divyzts").empty();
      jQuery("#divyzts").hide();
    //下面做验证参数的清除
		//
		//
	 return false;
    }
	var jsondata = getData(jxid,pjjtid); 
	if(jsondata == null){
		jQuery("#divyzts").empty();
		jQuery("#divyzts").hide();
		//下面做验证参数的清除
		//
		//
		return false;
	}
	createYztshtml(jsondata);
}

function createYztshtml(jsondata){
	var html = "";
	var len = jsondata.length;
	html +="<table width='' border='0' class='formlist'>";
	html +="<tr width ='100%'>";
	html +="<td width ='100%' colspan='4'>";
	html +="<table width ='100%'>";
	html +="<tr width ='100%' ><th width = '10%' style='text-align:center'>序号</th><th width ='90%' style='text-align:center'>限制条件</th></tr>";
	for(var i = 0; i<jsondata.length;i++){
		var result = jsondata[i]["result"];
		html +="<tr width ='100%' >";
		html +="<td width = '10%' style='text-align:center'>"+jsondata[i]["rownum"]+"<input type='hidden' name='yzresult' value='"+result+"'/>"+"</td>";
		if(result == 'true'){
			html +="<td width = 90%>"+"<img src='images/ico_38.gif' title='符合条件' />"+jsondata[i]["sqts"]+"</td>";
		}else{
			html +="<td width = 90%>"+"<img src='images/ico_39.gif' title='不符合条件' />"+jsondata[i]["sqts"]+"</td>";
		}
		html +="</tr>";
	}
	html +="</table>"
	html +="</td>";
	html +="</tr>";
	html +="</table>";
	jQuery("#divyzts").empty();
	jQuery("#divyzts").html(html);
	jQuery("#divyzts").show();
	
}

//班级为单位的评奖条件验证
function bjyzts(){
	var jtpjdw  = jQuery.trim(jQuery("[name='jtpjdw']").val());
	var tshowflag = jQuery("#divyzts").css("display");
	if((jtpjdw != "bj"&&jtpjdw != "qs") || tshowflag == "none"){
		return true;
	}
	var resultflag = true;
	var resultobj = jQuery("[name=yzresult]");
	for(var i=0;i<resultobj.length;i++){
		if(resultobj[i].value == "false"){
			resultflag = false;
			break;
		}
	}
	if(jQuery.trim(jQuery("#bjmc").val()) == ""&&jtpjdw == "bj"){
		showAlert("班级不可为空！");
		return false;
	}
	if(!resultflag){
		 showAlert("当前评奖项目有限制条件未通过,不允许提交！");
	}
	
	return resultflag;
}