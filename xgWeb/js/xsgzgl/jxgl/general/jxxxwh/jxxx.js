function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function searchRs(){
	var url = "jxgl_jxxxwh_ajax.do?method=jxxxCx";
	var ie = "10.0";
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//增加
function jxxxZj(){
	jQuery("#jxid").val('');
	jQuery("#jxmc").val('');
	jQuery("#kssj").val('');
	jQuery("#jssj").val('');
	//获得参加年级
	getCjnj();
	tipsWindown("增加军训信息","id:tempDiv","320","300","true","","true","id");
}

function drCxmd(){
	toImportDataNew("IMPORT_N450102_DRCXMD");
	return false;
}
//军训信息保存(前台验证)
function jxxxDivSave(){
	
	if($("jxmc") && $("jxmc").value==""){
 		alertInfo("军训名称不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	var flag = checkJxxx();
	if(!flag){
		alertInfo("军训名称不能重复!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#kssj").val()==null || jQuery("#kssj").val()==""){
 		alertInfo("开始时间不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#jssj").val()==null || jQuery("#jssj").val()==""){
 		alertInfo("结束时间不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("kssj").value!="" && $("jssj").value!="" && $("kssj").value>$("jssj").value){
 		alertInfo("开始时间不能大于结束时间!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("cjnj") && $("cjnj").value==""){
 		alertInfo("参加年级不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}

	jxxxSave();
}


//军训信息后台验证
function checkJxxx(){
	var flag = false;
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["jxid"]=escape(jQuery("#jxid").val());
    parameter["jxmc"]=escape(jQuery("#jxmc").val());
    var url = "jxgl_jxxxwh_ajax.do?method=checkJxxx";
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}



//军训信息保存
function jxxxSave(){
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["jxid"]=escape(jQuery("#jxid").val());
    parameter["jxmc"]=escape(jQuery("#jxmc").val());
	parameter["kssj"]=escape(jQuery("#kssj").val());
	parameter["jssj"]=escape(jQuery("#jssj").val());
	parameter["cjnj"]=escape(jQuery("#cjnj").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["jxzt"]=escape(jQuery("input[name=jxzt]:checked").val());
	var url = "jxgl_jxxxwh_ajax.do?method=jxxxSave";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if("保存成功"==result && $("jxid") && $("jxid").value=="" && "start"==jQuery("input[name=jxzt]:checked").val()){
				closeWindown();
				searchRs();
				confirmInfo(jQuery("#jxmc").val()+"已成功创建，是否继续生成军训名单？",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]");
						var pkValue = jQuery(array[0]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
						refreshForm('jxgl_jxxxwh.do?method=jxmdCx&pkValue='+pkValue+'&cxqk=cx');
					}
				});
			}else{
				alertInfo(result);
				closeWindown();
				searchRs();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}


//修改，运行，停止，军训名单维护
function jxxxModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var jxzt = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
		if("update"==type){
			if("停止"==jxzt){
				alertInfo("不能修改停止的军训信息!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			/*
			getCjnj();
			jQuery.ajaxSetup({async:false});
			jQuery.ajaxSetup({cache:false});
			var url="jxgl_jxxxwh_ajax.do?method=jxxxXg";		
			url+="&pkValue="+pkValue;
			jQuery.getJSON(url,parameter,function(data){
				if(data != null){
					jQuery("#jxid").val(data.jxid);
					jQuery("#jxmc").val(data.jxmc);
					jQuery("#kssj").val(data.kssj);
					jQuery("#jssj").val(data.jssj);
					jQuery("#cjnj").val(data.cjnj);
					jQuery("[name=jxzt][type=radio][value="+data.jxzt+"]").click();
				}
			});
			jQuery.ajaxSetup({async:true});
			tipsWindown("修改军训信息","id:tempDiv","320","300","true","","true","id");*/
			var url="jxgl_jxxxwh.do?method=jxxxXg&pkValue="+pkValue;
			showDialog("修改军训信息", 400, 340,url);
		}else if("start"==type || "stop"==type){
			if("start"==type && "运行"==jxzt){
				alertInfo("不能运行已运行的军训信息!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if("stop"==type && "停止"==jxzt){
				alertInfo("不能停止已停止的军训信息!!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var parameter={}
			var url="jxgl_jxxxwh_ajax.do?method=jxxxCz";	
			parameter["jxid"]=pkValue;
			parameter["jxzt"]=type;
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
		}else if("cxmd"==type){
			refreshForm('jxgl_jxxxwh.do?method=jxmdCx&pkValue='+pkValue+'&cxqk=cx');
		}
	}else{
		alertInfo("请勾选一条记录进行操作！");
		return false;
	}
}



//删除
function jxxxSc(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			var jxzt = jQuery(array[i]).parent().parent().find("td").eq(7).text();
			if("运行"==jxzt){
		 		alertInfo("不能删除运行中的军训信息!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			str += pkValue+"!!@@!!";
		}
		
		var flag = checkScJxxx();
		if(!flag){
			alertInfo("军训信息记录已在军训建制中使用，不能删除!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		
		confirmInfo("是否确定删除勾选的军训信息及其对应名单？",function(tag){
			if(tag=="ok"){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="jxgl_jxxxwh_ajax.do?method=jxxxSc";	
				parameter["pkValue"]=str;							
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
	}else{
		alertInfo("请勾选需要删除的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


//验证删除信息
function checkScJxxx(){
	var flag = false;
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var parameter={}
	var url="jxgl_jxxxwh_ajax.do?method=checkScJxxx";	
	parameter["pkValue"]=str;	
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}

//获得参加年级
function getCjnj(){
	var parameter={};
	jQuery.ajaxSetup({async:false});
	url = "jxgl_jxxxwh_ajax.do?method=getCjnj";
	jQuery.getJSON(url,parameter,function(data){
		jQuery('#cjnj').empty();
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].cjnj + "\">" + data[i].cjnj + "</option>";
				jQuery('#cjnj').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}

/**
 * 弹出模态窗口，替换原来的showTopWin
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param 其它参数
 */
function showDialog(t,width,height,url,other){
	var title = t||"学生工作管理系统";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	if(frameElement&& frameElement.api){
		var api = frameElement.api;
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		params["zIndex"] = 1977;
		return W.lhgdialog(params);
	}else{
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
	
	
}
/**
 * 关闭模态窗口
 */
function closeDialog(){
	var api = frameElement.api; 
	api.close();
}