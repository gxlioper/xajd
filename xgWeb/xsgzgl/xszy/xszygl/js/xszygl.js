

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xszyView(\""
			+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}

function ppqsLink(cellValue, rowObject) {
	var ppqs =null;
	var ppqsdm =null;
	if(null!=cellValue){
	ppqs=cellValue.split(";");
	ppqsdm = rowObject["ppqsdm"].split(";");
	var lddm = "";
	var qsh = "";
	var hrefHtml="";
	for ( var i = 0; i < ppqs.length; i++) {
		lddm=ppqsdm[i].split("-")[0];
		qsh=ppqsdm[i].split("-")[1];
		hrefHtml+="<a href='javascript:void(0);' class='name' onclick='qsxxView(\""
			+ lddm + "\",\"" + qsh + "\",\""
			+ rowObject["nj"] + "\");'>" + ppqs[i]
			+ "</a>"+" ";
	}
	return hrefHtml;
	}
	
	
}

// 查看
function viewXszy(id) {
	showDialog("新生之友信息查看", 650, 350, "xszygl.do?method=viewXszy&id="
			+ id );
}


// 新生之友信息查看
function zghLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXszy(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

function kyxbjFormatter(cellValue, rowObject) {
	var value=cellValue;
	if(null!=cellValue&&""!=cellValue){
	value = "已标记[<font color='red'>"+cellValue+"</font>]";
	}
	return value;
}


// 保存
function saveXszy(type) {
	var ids = null;
	if("save" == type){
		ids = "zgh-xm-xb-zwzc-dwdm-lxdh-zzmmdm-dzyx-dlyq";
	}else{
		ids = "xm-xb-zwzc-dwdm-lxdh-zzmmdm-dzyx-dlyq";
	}
	if(!checkNotNull(ids)){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "xszygl.do?method=saveXszy&type="+type;
	ajaxSubFormWithFun("XszyglForm", url, function(data) {
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
// 增加
function add() {
	var url = "xszygl.do?method=addXszy";
	var title = "新生之友信息增加";
	showDialog(title, 700, 450, url);
}


// 修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要修改的记录！");
		return false;
	}else if(rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
// if(rows[0]['sjly']=='1'){
// showAlertDivLayer("审核流程过来的记录不能修改！");
// return false;
// }
		var url = 'xszygl.do?method=editXszy&id=' + rows[0]["id"];
		var title = "新生之友信息修改";
		showDialog(title, 700, 450, url);
	}
}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<rows.length;i++){
			if(""!=rows[i]['ppqs']&&null!=rows[i]['ppqs']){
				showAlertDivLayer("已匹配寝室的新生之友不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xszygl.do?method=delXszy",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

// 跨院系标记
function kyxbj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要操作的记录！");
	} else {
		showDialog("跨院系标记", 450, 200, "xszygl.do?method=kyxbj");
}
}



// 跨院系标记保存
function saveKyxbj(dlyq) {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要操作的记录！");
	} else {
		jQuery.post("xszygl.do?method=kyxbj&type="+"save", {
			kyxbj : dlyq,
			ids : ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					jQuery("#dataTable").reloadGrid();
				}
			});
		}, 'json');
}
}

//院系分配
function fpyx() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要操作的记录！");
		return false;
	} else {
		 var xm = new Array();
			jQuery.each(rows, function(i, row) {
				xm.push(row["xm"]);
			});
		showDialog("分配院系", 450, 300, "xszygl.do?method=fpyx&xm="+xm);
}
}

//院系分配保存
function saveFpyx(bjyx) {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要操作的记录！");
	} else {
		jQuery.post("xszygl.do?method=fpyx&type="+"save", {
			bjyx : bjyx,
			ids : ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					jQuery("#dataTable").reloadGrid();
				}
			});
		}, 'json');
}
}
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N950103_XSZYGL");
	return false;

}

var DCCLBH = "xszy_xszygl.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, xszyglExportData);
}

// 导出方法
function xszyglExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xszygl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



// 验证必填项
function checkNulls() {
	var fields = jQuery("#XszyglForm").serializeArray();
	var isNull=false;
	jQuery.each(fields, function(i, field) {
			if (("" == jQuery(field).val() || null == jQuery(field).val())&&"bz"!=jQuery(field).attr("name")) {
				isNull = true;
			}
		});
	return isNull;

}