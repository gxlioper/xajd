
function save(){
	var mc =jQuery("#rddjmc").val();
	if (jQuery.trim(mc) == ""){
		showAlert("请先输入评定等级名称！");
		return false;
	}
	var url="pjpy_zyjgldmwh.do?method=rddjAdd&type=save";
    ajaxSubFormWithFun("ZyjrddjwhForm",url,function(data){
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
		var url = 'pjpy_zyjgldmwh.do?method=rddjUpdate&rddjdm='+rows[0]["rddjdm"];
		var title = "修改认定等级";
		showDialog(title,450,310,url);
	}
	
}
//修改保存
function saveUpdate(){
	var mc =jQuery("#rddjmc").val();
	if (jQuery.trim(mc) == ""){
		showAlert("请先输入评定等级名称！");
		return false;
	}
	var url="pjpy_zyjgldmwh.do?method=rddjUpdate&type=save";
    ajaxSubFormWithFun("ZyjrddjwhForm",url,function(data){
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
		jQuery.post("pjpy_zyjgldmwh.do?method=rddjDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}



