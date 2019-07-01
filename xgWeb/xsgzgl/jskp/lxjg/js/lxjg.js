function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "jskp_lxjg.do?method=addLxjg";
	var title = "立项结果";
	showDialog(title, 770, 552, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly'] == '1'){
			return showAlertDivLayer("审核流过来的数据不允许修改！");
		}
		var url = 'jskp_lxjg.do?method=updateLxjg&xmid=' + rows[0]["xmid"];
		var title = "立项结果修改";
		showDialog(title, 770, 552, url);
	}
}

//结果保存
function saveLxsq(){
	var ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"fzrlxfs"+"-"+"zdls"+"-"+"zdlslxfs"+"-"+"zxf"+"-"+"zdf";
	if(!checkNotNull(ids)){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	if(jQuery("#zdbm").val() == ""){
		return showAlert("系统无该指导部门名称，请重新填写！");
	}
	if(parseInt(jQuery("#zdf").val()) < parseInt(jQuery("#zxf").val())){
		return showAlert("最大分必须不得小于最小分！");
	}
	var url = "jskp_lxjg.do?method=saveForLxjg";
	ajaxSubFormWithFun("LxjgForm", url, function(data) {
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

//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		return showAlertDivLayer("请选择您要删除的记录！");
	} 
	for ( var i = 0; i < ids.length; i++) {
		if (rows[i]["sjly"] == "1") {
			showAlertDivLayer("审核流程过来的记录不能删除！");
			return false;
		}
	}
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("jskp_lxjg.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * 查看立项申请
 * @return
 */
function ckLxjg(xmid){
	showDialog("立项结果查看", 770, 450, "jskp_lxjg.do?method=ckLxjg&xmid="
			+ xmid );
}

/**
 * 项目名称Link
 * @return
 */
function xmmcLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='ckLxjg(\""
	+ rowObject["xmid"] + "\");'>" + cellValue
	+ "</a>";
}

var DCCLBH = "pjpy_jskp_lxjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, lxjgExportData);
}

//导出方法
function lxjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "jskp_lxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function rysz(sqid){
	showDialog("人员设置", 770, 450, "jskp_lxsq.do?method=rysz&sqid="
			+ sqid );
}

/**
 * 人员设置Link
 * @return
 */
function ryszLink(cellValue, rowObject){
	if(rowObject['xmdl'] == "gdx"){
		return "";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='rysz(\""
		+ rowObject["xmid"] + "\");'>" + "人员设置"
		+ "</a>";
	}
}

//基本设置
function jbsz(sqid){
	showDialog("基本设置", 770, 450, "jskp_lxjg.do?method=jbsz&xmid="
			+ sqid );
}

function jbszLink(cellValue, rowObject){
	if(rowObject['xmdl'] == "zlx"){
		return "";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='jbsz(\""
		+ rowObject["xmid"] + "\");'>" + "基本设置"
		+ "</a>";
	}
}