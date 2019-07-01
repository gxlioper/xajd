
var demoHtml = "";

function initData(){//岗位描述示例
	demoHtml = "请按如下格式输入内容\n\n";
	demoHtml += "例如：\n";
	demoHtml += "工作地点：A楼101室\n";
	demoHtml += "工作内容：日常文档处理\n";
	demoHtml += "工作时间：星期一、三（上午8:00~12:00)";
	
	jQuery("#gwms").val(demoHtml);
	jQuery("#gwms").css("color", "#999");
	jQuery("#gwms").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#gwms").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}


function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//查询
function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=gwsqCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//增加
function showAdd(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		alertInfo("未开放岗位申请或不在岗位申请时间段内,请确认!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	//showTopWin('qgzx_gwgl.do?method=gwsqZj',720,500);
	showDialog('', 760, 590, 'qgzx_gwgl.do?method=gwsqZj');
}


//修改update	查看	view
function showModi(type){
	var kycz = jQuery("#kycz").val();
	if("update"==type&&"true"!=kycz){
		alertInfo("未开放岗位申请或不在岗位申请时间段内,请确认!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
	    var shzt = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(9).text();
		if("update"==type && ("通过"==shzt || "不通过"==shzt)){
			alertInfo("已经审核过的数据不能修改!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var url="qgzx_gwgl.do?method=gwsqXg&pkValue="+pkValue;
		url+="&doType="+type;
		//showTopWin(url,720,500);
		showDialog('', 760, 590, url);
	}else{
		alertInfo("请勾选一条需要操作的记录！");
		return false;
	}
}


//删除
function deleteGwsq(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		alertInfo("未开放岗位申请或不在岗位申请时间段内,请确认!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		var shztIndex = 9;
		for (var i=0;i<array.length;i++) {
			var shzt = jQuery(array[i]).parent().parent().find("td").eq(shztIndex).text();
			if("通过"==shzt || "不通过"==shzt){
				alertInfo("已经审核过的数据不能删除!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="qgzx_gwgl_ajax.do?method=gwsqSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						if("删除成功"==result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
								}
							});
						}else{
							alertInfo(result);
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		alertInfo("请勾选需要删除的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


//验证删除信息
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=checkScInfo";	
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

function checkSxje(){
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
	//基础配置 设置的薪酬上限
	var gwzgcjsx=jQuery("#gwzgcjsx").val();
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
	if("yes"==sfsdgwcjsx&&"yes"==sfkgggwcjsx){
		//岗位设置酬金上限
		var gwcjsx=jQuery("#gwcjsx").val();
		if(gwcjsx==""){
	 		alertInfo("上限金额不能为空!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		if(parseInt(gwcjsx)>parseInt(gwzgcjsx)){
	 		alertInfo("上限金额不得超过系统设置的岗位最高酬金上限("+gwzgcjsx+"元)!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}
	return true;
}

//增加保存岗位信息	
function zjBcGwsq(){
	
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("岗位名称不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("add");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("岗位性质不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("需求人数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("困难生数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("困难生数不能大于需求人数!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && $("gwms").value==""){
 		alertInfo("岗位描述不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("岗位人员要求不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(!checkSxje())	{
		return false;
	}
	saveZjGwsqInfo();	
}


//验证保存信息
function checkBcInfo(type){
	var data ="true";
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    if(type=="update"){
    	parameter["pkValue"]=escape(jQuery("#pkValue").val());
    }
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val().trim());
	var url = "qgzx_gwgl_ajax.do?method=checkZjGwsqInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


//增加保存岗位信息
function saveZjGwsqInfo(){
	
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val(); 
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	var url = "qgzx_gwgl_ajax.do?method=bcZjGwsq";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if("保存成功"==result){				
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}else{
				alertInfo(result,function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}


//验证需求人数
function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


//验证修改岗位信息
function bcXgGwsq(){
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("岗位名称不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("update");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("岗位性质不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("需求人数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("困难生数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("困难生数不能大于需求人数!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && $("gwms").value==""){
 		alertInfo("岗位描述不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("岗位人员要求不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwxh") && $("gwxh").value==""){
		alertInfo("岗位序号不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(!checkSxje())	{
		return false;
	}
	if(jQuery("#gwxh").val()!=null&&jQuery("#gwxh").val()!=""){
		if(!checkGwxh(jQuery("#gwxh").val())){
			 return false;
			}

}
	saveXgGwxxInfo();	
}


//保存修改岗位信息
function saveXgGwxxInfo(){
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val();
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["gwxh"]=escape(jQuery("#gwxh").val()); 
	parameter["oldGwxh"]=escape(jQuery("#oldGwxh").val());
	var url = "qgzx_gwgl_ajax.do?method=bcXgGwsqInfo";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			//alertInfo(result);
			if("保存成功"==result){
				// alertInfo(result);
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}else{
				alertInfo(result,function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}
