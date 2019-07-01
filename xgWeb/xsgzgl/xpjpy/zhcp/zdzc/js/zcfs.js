
var num = '10000'; //允许导入的信息条数

/**
 * 取消参评学生
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消参评资格的学生！");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['tjzt']=='1'){
				showAlertDivLayer("提交的记录不能删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定取消选中学生的参评资格吗？",{"okFun":function(){
			jQuery.post("zdzcwh.do?method=delCpxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 综测分导入
 */
function toImport(){
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	map['num']=num; //下载的最大条数
	//避免导入数据超内存，控制下载条数
	jQuery.ajaxSetup({async:false});
	jQuery.post("zdzcwh.do?method=checkDownload",map,function(data){
		if (data == "true"){
			showDialog("综测分导入",550,250,"zdzcwh.do?method=toImportZdzc&jsonStr="+jsonStr,{close:function(){
				if (jQuery("#search_go")){
					jQuery("#search_go").click();
				}
			}});
		} else {
			showAlertDivLayer("导出数据过多，超出<font color='red'>"+num+"</font>条，请增加查询条件后进行导出！");						
		}
	});
	jQuery.ajaxSetup({async:true});
}
function toImportForAll(){
    var map = getSuperSearch();
    var jsonStr = JSON.stringify(map);
    map['num']=num; //下载的最大条数
    //避免导入数据超内存，控制下载条数
    jQuery.ajaxSetup({async:false});
    jQuery.post("zdzcwh.do?method=checkDownload",map,function(data){
        if (data == "true"){
            showDialog("综测分导入",550,250,"zdzcwh.do?method=toImportZdzc&jsonStr="+jsonStr,{close:function(){
                    if (jQuery("#search_go")){
                        jQuery("#search_go").click();
                    }
                }});
        } else {
            showAlertDivLayer("导出数据过多，超出<font color='red'>"+num+"</font>条，请增加查询条件后进行导出！");
        }
    });
    jQuery.ajaxSetup({async:true});
}

//高级查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 保存综测分
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfs(obj,xmdm,xh){
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
	
	jQuery.post("xpj_zcfs.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * 综测分输入框 上、下、左、右键事件
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
 * 下载综测分导入模版
 */
function downloadTemplate(){
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var url = "zdzcwh.do?method=downloadTemplate";
	url = addSuperSearchParams(url,map);// 设置高级查询参数
	jQuery("#zdzcform").attr("target","_blank");
	jQuery("#zdzcform").attr("action",url);
	jQuery("#zdzcform").submit();

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
	jQuery("#zdzcform").append(html);
	return url;
}


/**
 * 导入 综测分--上传文件
 * @returns {Boolean}
 */
function uploadZcfs(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("导入文件只能为.xls格式,请确认！");
		return false;
	}
	
	url = addSuperSearchParams("zdzcwh.do?method=importZcfs",map);// 设置高级查询参数
	jQuery("#zdzcform").attr("target","");
	jQuery("#zdzcform").attr("action",url).submit();
}


/**
 * 综测提交
 */
function cpxsTj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['tjzt']='tj';
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("zdzcwh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("您确定提交当前学生吗？",{"okFun":function(){
					jQuery.post("zdzcwh.do?method=tjCpxs",map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json'); 
				}});
			} else {
				showAlertDivLayer("学生综测成绩录入完成后可提交，目前仍有学生的综测分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * 取消提交
 */
function cpxsQxtj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['tjzt']='qxtj';
	showConfirmDivLayer("您确定取消提交当前学生吗？",{"okFun":function(){
		jQuery.post("zdzcwh.do?method=tjCpxs",map,function(data){
			showAlertDivLayer("取消提交成功");
			jQuery("#dataTable").reloadGrid();
		},'json'); 
	}});
}