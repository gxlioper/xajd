//一级菜单选择默认全选二级菜单
function parentTjClick(obj) {
	childrenChoice(obj);

}
// 二级菜单选中默认选中一级菜单
function childTjClick(obj) {
	parentChoice(obj);

}

function childrenChoice(obj) {
	var id = obj.id;
	var ids = id.split('_');
	// 自身代码
	var dm = ids[1];
	var checked = obj.checked;
	var childName = 'tjz_' + ids[1];
	if (true == checked) {
		jQuery("input[name='" + childName + "']").attr("checked", "true");
	} else {
		jQuery("input[name='" + childName + "']").removeAttr("checked");
	}
}

function parentChoice(obj) {
	var ids = obj.id.split('_');

	var sjdm = ids[1];
	var checked = obj.checked;
	var Name = 'tjz_' + ids[1];
	var parentId = '#' + 'td_' + sjdm + '_lv1';
	if (true == checked) {
		jQuery(parentId).attr("checked", "true");
	} else {
		if (jQuery("input[name='" + Name + "']:checked").length == 0) {
			jQuery(parentId).removeAttr("checked");
		}
	}
}

function setInit(){
		var url = "axcs_wpsz_ajax.do?method=getWpTjList";
		jQuery.post(url, {
			xmdm:jQuery("#xmdm").val()
		}, function(data) {
			dataObj = data;
				initTj();
			}, 'json');
	}
function initTj(){
	jQuery.each(jQuery("input[type=checkbox]"),function(j,n){
		for ( var i = 0; i < dataObj.length; i++) {
			var tjObj=dataObj[i];
			var tjId= '#td_'+tjObj.dcdm+'_'+j+'_lv2';
			if(tjObj.dcdm==jQuery(n).val()){
				jQuery(n).attr("checked",true);
			}
			if(tjObj.tjz==jQuery(tjId).val()){
				jQuery(tjId).attr("checked",true);
			}
			
		}
	});
	
}
function saveForm(){	  
     var url = "axcsWpsz.do?method=wpTjsz&type=save";
      ajaxSubFormWithFun("WpszForm",url,function(data){
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