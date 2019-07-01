/**
 *改自自定义公共导出js
 *实现学生信息自定义表单导出
 *@author yxy
 */

var isModify = false;

jQuery(function() {
	jQuery("#unselectUl, #selectUl").dragsort({
		dragSelector : "label",
		dragBetween : true,
		dragEnd : saveOrder,
		placeHolderTemplate : "<li><label class='college_li college_checkbox' style='border:#155FBE 1px dotted;background:#CBE4F8;height:20px;line-height:20px!important;*height:28px;width:120px;'></label></li>"
	});
});

//拖动后
function saveOrder() {
	isModify = true;
	jQuery("#unselectUl").find(":input").attr("name","unselectCol");
	
	var unspan = jQuery("#unselectUl").find(".choose_yx");
	unspan.parent().append("<span class='choose_wx' onclick='select(this)'></span>");
	unspan.remove();
	
	var span = jQuery("#selectUl").find(".choose_wx");
	span.parent().append("<span class='choose_yx' onclick='unselect(this)'></span>");
	span.remove();
};
			

//保存配置
function saveConfig(){
	var selectZd = jQuery("#selectUl input");
	var unselectZd = jQuery("#unselectUl input");
	var selectCol = new Array();
	var unselectCol = new Array();
	
	for (var i = 0 ; i < selectZd.length ; i++){
		selectCol[i]=selectZd.eq(i).val();
	}
	for (var i = 0 ; i < unselectZd.length ; i++){
		unselectCol[i]=unselectZd.eq(i).val();
	}
	
	if (selectCol.length == 0){
		showAlert("请选择您要导出的列！");
		return false;
	}
	
	//未修改过设置直接导出
	if (!isModify){
		doExport();
		return;
	}
	var url = "xsxx_hzdc.do?method=saveBcszDc";
	
	jQuery.ajaxSetup({async:false});
	jQuery.post(url, {
		selectCol:selectCol,
		unselectCol:unselectCol
	}, function(data) {
	    if(!data["success"]){
			showAlertDivLayer(data["message"]);
		}else{
			doExport();
			showAlertDivLayer(data["message"]);
		}
	}, 'json');
	jQuery.ajaxSetup({async:true});
}

//直接导出
function doExport(lx){
	//jQuery("#selectUl input").attr("name","exportModel.selectCol");
	if (jQuery("#selectUl input").length == 0){
		showAlert("请选择您要导出的列！");
		return false;
	}
	//导出方法
	var url = "xsxx_hzdc.do?method=exportXsxxWordDc&type="+lx;
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//点击加号
function select(obj){
	var li = jQuery(obj).parent();
	jQuery(li).find("input").attr("name","selectCol");
	jQuery(obj).parent().appendTo(jQuery("#selectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_yx' onclick='unselect(this)'></span>");
	saveOrder();
}

//点击减号
function unselect(obj){
	var li = jQuery(obj).parent();
	jQuery(li).find("input").attr("name","selectCol");
	jQuery(obj).parent().appendTo(jQuery("#unselectUl"));
	jQuery(obj).remove();
	li.append("<span class='choose_wx' onclick='select(this)'></span>");
	saveOrder();
}

//全选
function ec_selectAll(){
	
	if(jQuery("#unselectUl > li").length == 0){
		return;
	}
	
	jQuery("<span class='choose_yx' onclick='unselect(this)'></span>").replaceAll("#unselectUl > li > span");
	jQuery("#unselectUl > li > label").find("input").attr("name","selectCol");
	
	jQuery("#unselectUl > li").appendTo(jQuery("#selectUl"));
	
	saveOrder();
	
}





//全不选
function ec_unselectAll(){
	
	if(jQuery("#selectUl > li").length == 0){
		return;
	}
	
	jQuery("<span class='choose_wx' onclick='select(this)'></span>").replaceAll("#selectUl > li > span");
	jQuery("#selectUl > li > label").find("input").attr("name","unselectCol");

	jQuery("#selectUl > li").appendTo(jQuery("#unselectUl"));
	
	saveOrder();
	
}

