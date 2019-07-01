
function save(){
	var zdmc = jQuery("#zdmc").val();
	var shenfen = jQuery("#shenfen").val();
	if (jQuery.trim(zdmc) == "" || jQuery.trim(zdmc) == null || jQuery.trim(shenfen) == "" || jQuery.trim(shenfen) == null){
		showAlert("请填写带*的必填项！");
		return false;
	}
	var url="hczdgl_hczdwh.do?method=hczdAdd&type=save";
    ajaxSubFormWithFun("hczdForm",url,function(data){
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


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'hczdgl_hczdwh.do?method=hczdUpdate&zdmc='+encodeURI(encodeURI(rows[0]["zdmc"]));
		var title = "修改火车站点";
		showDialog(title,450,310,url);
	}
	
}
//修改保存
function saveUpdate(){
	var zdmc = jQuery("#zdmc").val();
	var shenfen = jQuery("#shenfen").val();
	if (jQuery.trim(zdmc) == "" || jQuery.trim(zdmc) == null || jQuery.trim(shenfen) == "" || jQuery.trim(shenfen) == null){
		showAlert("请填写带*的必填项！");
		return false;
	}
	var url="hczdgl_hczdwh.do?method=hczdUpdate&type=save";
    ajaxSubFormWithFun("hczdForm",url,function(data){
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

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("hczdgl_hczdwh.do?method=hczdDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}



