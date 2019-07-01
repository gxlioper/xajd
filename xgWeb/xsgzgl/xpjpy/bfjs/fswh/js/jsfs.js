
var num = '10000'; //允许导入的信息条数

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 保存竞赛分
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveBfjsFswh(obj,xmdm,bjdm){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("录入分数不能大于最大分："+max,{},{"clkFun":function(){
		}});
		return false;
	}
	
	if (min != "" && Number(fs) < Number(min)){
		showAlertDivLayer("录入分数不能低于最小分："+min,{},{"clkFun":function(){
		}});
		return false;
	}
	
	jQuery.post("xpjpyBfjsFswh.do?method=saveBfjsFswh",{xn:xn,xq:xq,xmdm:xmdm,bjdm:bjdm,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}



/**
 * 提交竞赛分
 * @returns {Boolean}
 */
function submitJsfs(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['id']=ids.toString();
	map['tjzt']='tj';
	if (ids.length == 0){
		showAlertDivLayer("请选择您要提交的记录！");
	}else{
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpjpyBfjsFswh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("您确定提交当前班级吗？",{"okFun":function(){
					jQuery.post("xpjpyBfjsFswh.do?method=submitBjJsf",map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json'); 
				}});
			} else {
				showAlertDivLayer("竞赛项目分录入完成后可提交，目前仍有班级的竞赛分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
	}
	
}


/**
 * 竞赛分输入框 上、下、左、右键事件
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//左   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//上      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//右   或者 回车
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//下 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}
/**
 * 导出竞赛分
 */
function ExportData(){
	var url="xpjpyBfjsFswh.do?method=exportViewJsfs";
	var map = getSuperSearch();
	url = addSuperSearchParams(url,map);// 设置高级查询参数
	document.location.href=url;
}



/**
 * 竞赛分导入 
 */
function toImport(){
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	showDialog("竞赛分导入",550,280,"xpjpyBfjsFswh.do?method=toImportJsfs&jsonStr="+jsonStr,{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
}






/**
 * 下载竞赛分导入模版
 */
function downloadTemplate(){
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var url = "xpjpyBfjsFswh.do?method=downloadTemplate&editType='dr'";
	url = addSuperSearchParams(url,map);// 设置高级查询参数
	jQuery("#BfjsFswhModel").attr("target","_blank");
	jQuery("#BfjsFswhModel").attr("action",url);
	jQuery("#BfjsFswhModel").submit();

}


/**
 * 导入 竞赛分--上传文件
 * @returns {Boolean}
 */
function uploadJsfs(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("导入文件只能为.xls格式,请确认！");
		return false;
	}
	
	url = addSuperSearchParams("xpjpyBfjsFswh.do?method=importJsfs",map);// 设置高级查询参数
	jQuery("#BfjsFswhModel").attr("target","");
	jQuery("#BfjsFswhModel").attr("action",url).submit();
}



//设置高级查询条件，导出方法中使用
function addSuperSearchParams(url,map){
	
	if(url.indexOf("?") > -1){
		url += "&";
	}else{
		url += "?";
	}
	url += "path=" + map.path + "&mhcx_lx=" + map.mhcx_lx;
	jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
	
	var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
	html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
	html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
	if (map.input_mhcx){
		html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
	}
	jQuery("#BfjsFswhModel").append(html);
	return url;
}

