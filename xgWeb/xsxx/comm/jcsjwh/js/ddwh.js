
/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
/**
 * 增加大队代码
 * @return
 */
function zjDdsj(){
	var url = "zjjcddwh.do?method=ddAdd";
	var title = "增加大队数据";
	showDialog(title,380,200,url);
}

/**
 * 修改大队代码
 * @return
 */
function modDdsj(){
	var rows = jQuery("#dataTable").getSeletRow();
 	if (rows.length != 1){
 		showAlertDivLayer("请选择一条您要修改的记录！");
 		return false;
 	} 
	var url = "zjjcddwh.do?method=ddUpdate&dddm="+rows[0]["dddm"];
	var title = "修改大队数据";
	showDialog(title,380,200,url);
}

/**
 * 删除
 * @return
 */
function delDdsj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("zjjcddwh.do?method=ddDel",{dddm:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
}

/**
 * 区队维护
 * @return
 */
function qdwh(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("请选择您维护的大队！");
		return false;
	} 
	var url = "zjjcddwh.do?method=qdwhList&dddm="+ids.toString();
	var title = "区队维护";
	showDialog(title,800,600,url);
}

/**
 * 区队添加
 * @return
 */
function saveQd(){
	var ids = jQuery("#dataTable").getSeletRow();
	var dddm = jQuery("#dddm").val();
	if (ids.length == 0){
		showAlertDivLayer("请选择您需要增加的区队！");
		return false;
	} 
	var qdAll = new Array();
	for(var i=0;i<ids.length;i++){
	  qdAll[i] = ids[i]["bjdm"];
	}
	jQuery.post("zjjcddwh.do?method=qdAdd",{dddm:dddm,qdAll:qdAll},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			var api = frameElement.api, W = api.opener;
			W.jQuery("#dataTable").reloadGrid();
		},'json');
}

/**
 * 区队删除
 * @return
 */
function delQd(){
	var ids = jQuery("#dataTable").getSeletRow();
	var dddm = jQuery("#dddm").val();
	if (ids.length == 0){
		showAlertDivLayer("请选择您需要增加的区队！");
		return false;
	} 
	var qdAll = new Array();
	for(var i=0;i<ids.length;i++){
	  qdAll[i] = ids[i]["bjdm"];
	}
	jQuery.post("zjjcddwh.do?method=qdDel",{dddm:dddm,qdAll:qdAll},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			var api = frameElement.api, W = api.opener;
			W.jQuery("#dataTable").reloadGrid();
		},'json');
}


function setQd(cellValue, rowObject) {
	var dddm = rowObject.dddm;
	if (dddm == null) {
		dddm = "";
	}
	html = "<a  href='javascript:void(0);'  class='name' onclick='viewQd(\"" + dddm
			+ "\");return false;' >" + cellValue + "</a>";
	return html;
}

/**
 * 区队删除
 * @return
 */
function viewQd(ids){
	var url = "zjjcddwh.do?method=viewQd&dddm="+ids;
	var title = "区队维护";
	showDialog(title,800,600,url);
}