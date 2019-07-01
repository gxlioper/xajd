var gridSetting_dmwh = {
	caption : "意见分类列表",
	pager : "pager",
	url : "gygl_gyyjxdmwh.do?method=listDmwh&type=query",
	colList : [
			/*{ name : 'yjfldm', index : 'yjfldm', key : true , hidden : true},*/
			{ label : '意见分类代码', name : 'yjfldm', index : 'yjfldm',  width : '30%', key : true },
			{ label : '意见分类名称', name : 'yjflmc', index : 'yjflmc', width : '60%' },
			{name : 'txsmc', index : 'txsmc', hidden: true }],
	sortname : "yjfldm", sortorder : "asc" };


function add_dmwh() {
	var url = "gygl_gyyjxdmwh.do?method=actionNavi&type=add";
	var title = "增加意见分类";
	showDialog(title, 400, 200, url);
}

function add_stu(){
	
	var userType = jQuery("#userType").val();
	if("stu" == userType){
		jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
			var lddm = data.lddm;
			
			if(!lddm){
				showAlertDivLayer("您尚未入住公寓寝室，无法反馈公寓意见！");
			}else{
				var url = "gygl_gyyjxstu.do?method=actionNavi&type=add";
				var title = "提意见";
				showDialog(title, 800, 520, url);
			}					
		},'json');
	}else{
		var url = "gygl_gyyjxstu.do?method=actionNavi&type=add";
		var title = "提意见";
		showDialog(title, 800, 520, url);
	}

	
}

function update_dmwh() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gygl_gyyjxdmwh.do?method=actionNavi&type=update&yjfldm=' + rows[0]["yjfldm"];
		var title = "修改意见分类";
		showDialog(title, 400, 200, url);
	}
}

function update_stu() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if(rows[0]["fkqk"] == '1'){
			showAlertDivLayer("该意见已被反馈，不能修改！");
			return false;
		}
		
		var url = 'gygl_gyyjxstu.do?method=actionNavi&type=update&gyyjid=' + rows[0]["gyyjid"];
		var title = "修改意见";
		showDialog(title, 800, 520, url);
	}
}

function fk_gl(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要反馈的意见！");
	} else {
		var url = 'gygl_gyyjxstu.do?method=actionNavi&type=yjfk&gyyjid=' + rows[0]["gyyjid"];
		var title = "意见反馈";
		showDialog(title, 800, 590, url);
	}
}

/**
 * 提交
 * @param type
 * @return
 */
function submitAction_dmwh(type){
	
	var yjflmc = jQuery('#yjflmc').val();
	
	
	if (yjflmc==null || jQuery.trim(yjflmc) == '') {
		showAlertDivLayer("请将带<font color=\"red\">*</font>的项目填写完整！");
		return false;
	}
	
	var url = "gygl_gyyjxdmwh.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		if(data['repeat'] && data['repeat'] == 'Y'){
			showAlertDivLayer(data['message']);
		}else{
			refershParent();
		}
		
	});
}

/**
 * 提交
 * @param type
 * @return
 */
function submitAction_stu(type){
	var xh = jQuery("input[name='xh']").val();
	var yjfldm = jQuery('#yjfldm').val();
	var yjms = jQuery('#yjms').val();
	
	if ((yjfldm==null || jQuery.trim(yjfldm) == '')  || (yjms==null || jQuery.trim(yjms) == '')) {
		showAlertDivLayer("请将带<font color=\"red\">*</font>的项目填写完整！");
		return false;
	}
	
	if( type == 'add' && (xh==null || jQuery.trim(xh) == '')){
		showAlertDivLayer("请选择一个学生！");
		return false;
	}
	
	if(yjms.length > 500){
		showAlertDivLayer("意见描述不能超过500字！");
		return false;
	}
	var url = "gygl_gyyjxstu.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}
/**
 * 提交
 * @return
 */
function submitAction_gl(type){
	var yjms = jQuery.trim(jQuery('#fknr').val());
	if(yjms.length ==0){
		showAlertDivLayer("请填写反馈意见！");
		return false;
	}
	if(yjms.length > 500){
		showAlertDivLayer("反馈意见不能超过500字！");
		return false;
	}
	
	var url = "gygl_gyyjxstu.do?method=" + type;
	ajaxSubFormWithFun("gyyjxForm", url, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

function del_dmwh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if(ids.length==1 && rows[0]['txsmc']=='y'){
		showAlertDivLayer("该意见分类已使用，不能删除！");
	}else {		
		
		for(i=0;i<rows.length;i++){
			if(rows[i]['txsmc']=='y'){
				showAlertDivLayer("所选包含已使用意见分类,不能删除，请确认！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", { "okFun" : function() {
			jQuery.post("gygl_gyyjxdmwh.do?method=del", { pks : ids
					.toString() }, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		} });

	}
}

function del_stu() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	}
	else if(ids.length==1 && rows[0]['fkqk']=='1'){
		showAlertDivLayer("该意见已被反馈，不能删除！");
	}else {
		
		for(i=0;i<rows.length;i++){
			if(rows[i]['fkqk']=='1'){
				showAlertDivLayer("所选记录包含已反馈公寓意见，反馈公寓意见不能删除，请确认！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", { "okFun" : function() {
			jQuery.post("gygl_gyyjxstu.do?method=del", { pks : ids
					.toString() }, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		} });

	}
}

/**
 * 高级查询
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink_stu(cellValue,rowObject){
	var onclickfn = "onclick=\"" + "showDialog('公寓意见信息' , 800 , 550 , 'gygl_gyyjxstu.do?method=query&gyyjid=" + rowObject['gyyjid'] + "&xh=" + rowObject['xh'] + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}

var DCCLBH = "gygl_jxjggl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "gygl_gyyjxstu.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

