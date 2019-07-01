/**
 * 查询
 * @return
 */
function searchRs() {
	var map = {};
	var jjlx = jQuery("#jjlx").val();
	if (null!=jjlx && jjlx != "") {
		map["jjlx"] = jjlx;
	}else{
		map["jjlx"] = "1";
	}
	map["xydm"] = jQuery("#xydm").val();
	map["js"] = jQuery("#userType").val();
	map["wsqkyq"] = jQuery("#wsqkyq").val();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 页签选择
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, jjlx) {
	jQuery("#jjlx").val(jjlx);
	var map = {};
	map["jjlx"]=jjlx;
	map["xydm"] = jQuery("#xydm").val();
	map["js"] = jQuery("#userType").val();
	if (jjlx == "1") {
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(jjlx == "2"){
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}else {
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * 保存评分标准
 * @return
 */
function savePfbz(){
	var ids = "fjid"+"-"+"xh"+"-"+"wsqkyq";
	if(!checkNotNull(ids)){
		return showAlert("请将带<font color='red'>*</font>项目填写完整！");
	}
	var url = "gyjc_pfbz.do?method=savePfbz";
	ajaxSubFormWithFun("PfbzForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			    var api = frameElement.api,W = api.opener;
    				jQuery(W.document).find('#search_go').click();
    				document.location.href = document.location.href;
//					if (parent.window){
//						refershParent();
//					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

/**
 * 删除评分标准
 * @return
 */
function del(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("请先选择需要删除的记录！");
	}
	var guids = new Array();
	var fjids = new Array();
	for ( var i = 0; i < rows.length; i++) {
		guids.push(rows[i]['guid']);
		fjids.push(rows[i]['fjid']);
	}
	var para = {
			guids:guids,
			fjids:fjids
	};
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("gyjc_pfbz.do?method=delPfbz",para,function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * 返回基础设定主查询页面
 * @return
 */
function fhjcsd(){
	document.location.href = "xg_gyjc_jcsd.do";
}

/**
 * 增加
 * @return
 */
function add(){
	var url = "gyjc_pfbz.do?method=add&xydm="+jQuery("#xydm").val()+"&js="+jQuery("#userType").val()
	+"&jjlx="+jQuery("#jjlx").val();
	showDialog("增加项目", 550,400, url);
}

/**
 * 修改
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("有且只能选择一条记录！");
	}
	var url = "gyjc_pfbz.do?method=update&guid="+rows[0]['guid'];
	showDialog("增加项目", 550,400, url);
}