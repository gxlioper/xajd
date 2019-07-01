var ids="mc-fz";
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});
function query(){
	var map = {};
	map["mc"] = jQuery("#mc").val();
	jQuery("#dataTable").reloadGrid(map);
}
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xsxwkhJcxmwh.do?method=delJcxm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}
//新增保存
function saveForm(){
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
    var url = "xsxwkhJcxmwh.do?method=save";
     ajaxSubFormWithFun("xsxwJcxmwhForm",url,function(data){
   	 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else{
   		 showAlert(data["message"]);
   	 }
		});
	}
 }
//更新保存
function saveUpdateForm(){
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
    var url = "xsxwkhJcxmwh.do?method=update";
     ajaxSubFormWithFun("xsxwJcxmwhForm",url,function(data){
   	 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else{
   		 showAlert(data["message"]);
   	 }
		});
	}
 }