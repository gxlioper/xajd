function save(){
	var cfyymc=jQuery("#cfyymc");
	var kffs=jQuery("#kffs");
	if(cfyymc.val()=="" || kffs.val()==""){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var url="wjcf_cfyydmwh.do?method=cfyydmAdd&type=save";
    ajaxSubFormWithFun("cfyydmForm",url,function(data){
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


function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'wjcf_cfyydmwh.do?method=cfyydmUpdate&cfyydm='+rows[0]["cfyydm"];
		var title = "修改处分原因";
		showDialog(title,450,150,url);
	}
	
}

//修改保存
function saveUpdate(){
	var cfyymc=jQuery("#cfyymc");
	var kffs=jQuery("#kffs");
	if(cfyymc.val()=="" || kffs.val()==""){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var url="wjcf_cfyydmwh.do?method=cfyydmUpdate&type=save";
    ajaxSubFormWithFun("cfyydmForm",url,function(data){
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
		jQuery.post("wjcf_cfyydmwh.do?method=cfyydmDelete",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}