function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function save() {
	var rows = jQuery("#dataTable").getSeletRow();
	var seniorTjlength = jQuery(".selectedValue").length;
	if(jQuery.trim(jQuery("#input_mhcx").val()) != ""){
		seniorTjlength = seniorTjlength+1;
	}
	if (rows.length == 0 && seniorTjlength == 0) {
		showAlert("请至少选择一个学生或选择一个高级查询条件！");
		return false;
	}
	if(rows.length > 0){
		var xhs = "";
		for(var i = 0; i < rows.length; i++){
			var xh = rows[i]["xh"] == null ? "" : rows[i]["xh"];
			if(xh){
				xhs +=xh;
				if(i != rows.length-1){
					xhs +=",";
				}
			}
		}
	    if(!xhs){
	    	showAlert("学号为空，请仔细核对学生学号是否有值！");
			return false;
	    }
			jQuery.post("dyxj_dyzgk.do?method=addZgk",{values:xhs},function(data){
				if(data["message"] == "保存成功！"){
					showAlert(data["message"],{},{"clkFun":function(){
						var W;
						var api = frameElement.api;
						if (api) {
							if (api.get('childDialog')) {
								W = api.get('parentDialog')
							} else {
								W = api.opener;
							}
						} else if (parent.window) {
							W = parent.window;
						}
						W.searchRs();
						iFClose();
				  }});
				}else{
					showAlert(data["message"]);
					return false;
				}
	
			},'json');
		
	}else{
		var url = "dyxj_dyzgk.do?method=saveZgkXsbySeniorTj&path=xsxx_dyxj_dyzgk.do";
		var map = getSuperSearch();
		 jQuery.post(url,map,function(data){
				if(data["message"] == "保存成功！"){
					showAlert(data["message"],{},{"clkFun":function(){
						var W;
						var api = frameElement.api;
						if (api) {
							if (api.get('childDialog')) {
								W = api.get('parentDialog')
							} else {
								W = api.opener;
							}
						} else if (parent.window) {
							W = parent.window;
						}
						W.searchRs();
						iFClose();
				  }});
				}else{
					showAlert(data["message"]);
					return false;
				}
	
			},'json');
  }
}