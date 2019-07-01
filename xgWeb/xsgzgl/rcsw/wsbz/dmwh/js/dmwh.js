//function searchRs() {
//	var map = {};
//	jQuery("#dataTable").reloadGrid(map);
//}

/**
 * 综测分录入、查看--查询
 */
function doQuery(){
	var map = {};
	map['fdmc']=jQuery("#fdmc").val();
	jQuery("#dataTable").reloadGrid(map);
}	

//增加
function add() {
	var url = "wsbz_dmwh.do?method=addWsbz";
	var title = "增加小分队";
	showDialog(title, 470, 400, url);
}
//全局参数设置
function set() {
	var url = "wsbz_dmwh.do?method=setWsbz";
	var title = "全局参数设置";
	showDialog(title, 500, 350, url);
}


function setQjcs(){
	var ids = "bmcs-jzts-jzsj";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var bmcs = jQuery("#bmcs").val();
	var jzts = jQuery("#jzts").val();
	var jzsj = jQuery("#jzsj").val();
	var url = "wsbz_dmwh.do?method=savesetQjcs";
	showConfirmDivLayer("您确定要设置全局参数吗？",{
		"okFun" : function(){
		jQuery.post(url,{
			bmcs:bmcs.toString(),
			jzts:jzts.toString(),
			jzsj:jzsj.toString()
		},function(data){
			showAlertDivLayer(data["message"]);
			return true;
		},'json');
		
	}
});

}
//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'wsbz_dmwh.do?method=updateWsbz&fddm=' + rows[0]["fddm"];
		var title = "修改代码";
		showDialog(title, 470, 400, url);
	}
}

//增加修改结果保存
function saveFormjldm(type){
	var ids = "fdmc"+"-"+"sj"+"-"+"dd"+"-"+"rs";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	 var hdbl=jQuery('input:radio[name="hdpl"]:checked').val();
	 if(!hdbl){
		 showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			return false;
	 }
	if(jQuery.trim(jQuery("#fwyq").val()).length > 500 || jQuery.trim(jQuery("#gzzz").val()).length > 500){
		showAlert("服务要求和工作职责不能超过500字！");
		return false;
	}
	
	var url = "wsbz_dmwh.do?method=saveWsbz&type=" + type;
	ajaxSubFormWithFun("WsbzDmwhForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dmView(\"" + rowObject['fddm'] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function dmView(fddm) {
	showDialog("代码查看", 470, 400, "wsbz_dmwh.do?method=ckWsbz&fddm="
			+ fddm );
}
//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("wsbz_dmwh.do?method=delWsbz",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}