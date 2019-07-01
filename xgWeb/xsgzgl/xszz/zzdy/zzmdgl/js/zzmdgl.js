
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewZzmd(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function viewZzmd(id) {
	showDialog("资助变更查看", 800, 470, "xszz_zzdyzzmdgl.do?method=viewZzmd&id="
			+ id);
}

function zzmdTb() {
	var url = "xszz_zzdyzzmdgl.do?method=zzmdTb";
	showConfirmDivLayer("您确定要同步资助名单吗？", {
		"okFun" : function() {
			jQuery.post(url, {
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xszz_zzdyzzmdgl.do?method=updateZzmd&id=' + rows[0]["id"];
		var title = "修改资助发放";
		showDialog(title, 750, 500, url);
	}
}



function saveForm(type){
	 var bgly =  jQuery("#bgly").val();
	 var bgqje = jQuery("#bgqje").val();
	 var bgqzt = jQuery("#bgqzt").val();
	 var bghje=jQuery("#bghje").val();
	var bghzt=jQuery("#bghzt").val();
	if(null==bghje||""==bghje||null==bgly||""==bgly){
		showAlert("请将必填项填写完整！");
		return false;
	}
	if(bgqje==bghje&&bgqzt==bghzt){
		 showAlert("您并未修改发放金额和发放状态，无需提交！");
			return false;
	}
     var url = "xszz_zzdyzzmdgl.do?method=saveZzmd&type="+type;
      ajaxSubFormWithFun("ZzdyMdglForm",url,function(data){
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

function changeFfzt(){
	var bghzt = jQuery("#bghzt").val();
	if("1"!=bghzt){
		jQuery("#bghje").val(jQuery("#bgqje").val());
		jQuery("#bghje").attr("readonly","readonly");
	}else{
		jQuery("#bghje").attr("readonly","");
	}
	
}

