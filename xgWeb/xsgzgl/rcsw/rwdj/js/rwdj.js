var action = "rwdj.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var rwdjid = rowObject["rwdjid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rwdjid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(rwdjid) {
	var url = action + "?method=showView&rwdjid=" + rwdjid;
	showDialog("入伍登记信息", 800, 500, url);
}
// 申请
function add() {
	var url = action + "?method=add";
	showDialog("入伍登记增加", 800, 500, url);
}
/**
 * 修改
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"] == "1"){
			showAlertDivLayer("审核流程数据不允许删除！");
			return false;
		}
		var url = action + '?method=update&rwdjid=' + rows[0]["rwdjid"]+"&xh="+ rows[0]["xh"];
		var title = "入伍登记修改";
		showDialog(title, 800, 500, url);
	}
}
/**
 * 保存
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId) {
	if (!checkNotNull(checkId)) {
		showAlert("请将带<font class='red'>*</font>的项目填写完整！")
		return false;
	}
	var ywqrwxy = jQuery("[name='ywqrwxy']:checked").val();
	if(ywqrwxy == "" ||  ywqrwxy == null){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！")
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "保存成功！"){
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}else{
			showAlert(data["message"]);
			return false;
		}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] == "1"){
				flag = false;
				return false;
			}
		});
		if(!flag){
			showAlertDivLayer("审核流程数据无法删除！");
			return false;
		}

  var rows = jQuery("#dataTable").getSeletRow(); 
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes = "成功删除了<font color='green'>&nbsp;"
							+ data["successDel"] + "&nbsp;</font>条数据";
					mes += "</br>";
					showAlertDivLayer(mes, {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});

	}
}
// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport('rwdj_export.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();// 设置高级查询条件
	var url = "rwdj.do?method=exportData&dcclbh=rwdj_export.do";// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function drxx(){
	toImportDataNew("IMPORT_N151601_RWDJ");
	return false;
}