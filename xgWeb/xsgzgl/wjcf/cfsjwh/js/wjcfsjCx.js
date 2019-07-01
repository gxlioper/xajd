function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function showModi(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	

	if(len==1){	
		var sjly=jQuery("[name=div_pkValue]:checked").parent().parent().find("input[name=sjly]").val();
		
		if(sjly == "1"){
			alertInfo("流程数据不能修改 ！");
			return false;
		}
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
		var url="wjcfsjXg.do";
		
		url+="?cfid="+pkValue;
		
		jQuery.ajaxSetup({async:false});
		var count = 0;	
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							
							if (json[0].sswh != "" && json[0].sswh != null) {
								count = 1;
								alertInfo("该处分已申诉不能再进行修改！");
								return false;
							}
							else if (json[0].zzwh != "" && json[0].zzwh != null) {
								count = 1;
								alertInfo("该处分已终止不能再进行修改！");
								return false;
							}
							else if (json[0].jcwh != "" && json[0].jcwh != null) {
								count = 1;
								alertInfo("该处分已"+jQuery("#text").val()+"不能再进行修改！");
								return false;
							}
							else if (json[0].jclc != "0" && json[0].jclc != null) {
								count = 1;
								alertInfo("该处分"+jQuery("#text").val()+"审核中，不能再进行修改！");
								return false;
							}
							else if (json[0].sslc != "0" && json[0].sslc != null) {
								count = 1;
								alertInfo("该处分申述审核中，不能再进行修改！");
								return false;
							}
						}
					);
		jQuery.ajaxSetup({async:true});	
		if (count == 0) { 
		//showTopWin(url,780,660);}
		showDialog("", 800, 500, url);}
	}else{
		
		alertInfo("请勾选一条需要修改的记录！");
		
		return false;
	}
}

function showView(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		//showTopWin(url,780,660);
		showDialog("", 800, 500, url);
	}else{
		
		alertInfo("请勾选一条需要查看的记录！");
		
		return false;
	}
}

function deleteJcrcgl(){
	
	var n=jQuery("[name=div_pkValue]:checked").length;
	
	var blog=true;
	if(n>0){
		var count = 0;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			var sjly=jQuery(this).parent().parent().find("input[name=sjly]").val();
			
			if(sjly == "1"){
				count = count + 1;
			}
		
		});
		
		if(count >0){
			alertInfo("选择的数据记录中已有走完审核流的，不能删除！");
			return false;
		}
	
		if(blog){
			confirmInfo("该操作将会删除处分信息，是否确定继续操作？",function(tag){
				
				if(tag=="ok"){
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
					
					});
					
					var parameter={};

					parameter["pkValue"]=escape(pkValue.join("!!array!!"));
					
					var url= "wjcfsjSc.do";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
									searchRs();
								}
							
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{
		
		alertInfo("请勾选需要删除的数据！",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

function cfsscl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		jQuery.ajaxSetup({async:false});	
		var count = 0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].jcwh != "" && json[0].jcwh != null) {
								alertInfo("该处分已"+jQuery("#text").val()+"不能再进行申诉！");
								count = 1;
								return false;
							}
							if (json[0].zzwh != "" && json[0].zzwh != null) {
								alertInfo("该处分已终止不能再进行申诉！");
								count = 1;
								return false;
							}
							if (json[0].ssjg == '更改处分') {
								document.getElementById('cfggw').style.display = "block";
							}
						}
					);
		
		jQuery.ajaxSetup({async:true});	
		if (count == 0) {
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfss&&cfid="+pkValue;
			showDialog("", 450, 280, url);
		}
	}else{
		alertInfo("请勾选一条需要操作的记录！");
		return false;
	}
}
function ssjgsave() {
	var sswh = $("sswh").value;//申诉问号
	var sssj = $("sssj").value;//申诉时间
	var ssjg = $("ssjg").value;//申诉结果
	var cflbdm = $("cflbdm").value;//处分类别代码
	if("block"==document.getElementById('cfggw').style.display){
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbdm||""==cflbdm){
			alertError("请将带*的项目填写完整！");
			return false;
		}
	}else{
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
			alertError("请将带*的项目填写完整！");
			return false;
		}
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	refreshForm('saveWjcfssjg.do?cfid='+pkValue);
}
function cfjccl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		jQuery.ajaxSetup({async:false});	
		var count=0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].zzwh != "" && json[0].zzwh != null) {
								alertInfo("该处分已终止不能再进行"+jQuery("#text").val()+"！");
								count = 1;
								return false;
							}
						}
					);
		
		jQuery.ajaxSetup({async:true});	
		if(count == 0){
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfjc&cfid="+pkValue;
			showDialog("", 400, 240, url);
		}
	}else{
		
		alertInfo("请勾选一条需要操作的记录！");
		
		return false;
	}
}

function cfzzcl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		jQuery.ajaxSetup({async:false});	
		var count = 0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].jcwh != "" && json[0].jcwh != null) {
								alertInfo("该处分已"+jQuery("#text").val()+"不能再进行终止！");
								count = 1;
								return false;
							}
							if(!sfkzzFlag(json[0])){
								count = 1;
								return false;
							}
						},'json'
					);
		
		jQuery.ajaxSetup({async:true});	
		if (count == 0) {
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfzz&cfid="+pkValue;
			showDialog("", 400, 240, url);
		}
	}else{
		
		alertInfo("请勾选一条需要操作的记录！");
		
		return false;
	}
}


function sfkzzFlag(cfInfo){
	var flag = true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("wjcfCfshwh_cfsjwh.do?method=getKzzFlag",{cfsj:cfInfo.cfsj,zzsj:cfInfo.zzsj,cflbmc:cfInfo.cflbmc},function(data){ 
		if(data["message"]!=null && data["message"]!=""){
			alertInfo(data["message"]);
			flag = false;
		}
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
}


function jcjgsave() {
	var jcwh = $("jcwh").value;//解除问号
	var jcsj = $("jcsj").value;//解除时间
	if(null==jcsj||""==jcsj||null==jcwh||""==jcwh){
		alertError("请将带*的项目填写完整！");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	refreshForm('saveWjcfjcjg.do?cfid='+pkValue);
}
function discfgg() {
	var ssjg = jQuery("#ssjg").val();
	if (ssjg =='更改处分') {
		document.getElementById('cfggw').style.display = "block";
	} else {
		document.getElementById('cfggw').style.display = "none";
		document.getElementById('cflbdm').value = "";
	}
}
/**
 * 下载申请表格
 * @return
 */
function getWord() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	
	var jcwh=jQuery("[name=div_pkValue]:checked").parent().parent().find("input[name=jcwh]").val();
	if(jcwh == null || jQuery.trim(jcwh) == ''){
		showAlertDivLayer("请选择一条"+jQuery("#text").val()+"处分记录！");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url = "wjcfCfshwh_cfsjwh.do?method=doPrint&&cfid="+pkValue;
	window.open(url);
}
/**
 * 违纪处分通知书打印
 * @return
 */
function getWjCfWord(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url ="wjcfCfshwh_cfsjwh.do?method=doPrintWjcfWord&&cfid="+pkValue;
	window.open(url);
	
}
/**
 * 浙江商业职业技术学院个性化：学生处分决定书下载
 * @return
 */
function getCfjdWord(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url ="wjcfCfshwh_cfsjwh.do?method=doPrintCfjdWord&&cfid="+pkValue;
	window.open(url);
}

	
		
		
	