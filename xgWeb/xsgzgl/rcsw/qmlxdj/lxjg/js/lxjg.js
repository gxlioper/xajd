
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "qmlxjg.do?method=add";
	var title = "期末离校趋向结果";
	showDialog(title, 770, 550, url);
}

/**
 * 保存结果
 * @param type
 * @return
 */
function saveJg(){
    if(jQuery("#sflxdm").val() == "是"){
        var ids = "xh"+"-"+"jhrxm"+"-"+"jhrlxfs"+"-"+"lxsj"+"-"+"lxjtgjdm"
            +"-"+"fxsj" +"-"+"fxjtgjdm"+"-"+"fxcchb"+"-"+"mddssx"+"-"+"xn"+"-"+"xq";
        if(checkNotNull(ids) == false){
            showAlert("请将带<font color='red'>*</font>的项目填写完整");
            return false;
        }
        if(jQuery("[name='sflx']:checked").val() == "" || jQuery("[name='sflx']:checked").val() == null){
            showAlert("请将带<font color='red'>*</font>的项目填写完整");
            return false;
        }
	}

	var url = "qmlxjg.do?method=saveJg";
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

/**
 * 查看
 * @param sqid
 * @param xh
 * @return
 */
function jgView(jgid, xh) {
	showDialog("期末离校趋向结果查看", 770, 450, "qmlxjg.do?method=ckSq&jgid="
			+ jgid + "&xh=" + xh);
}

/**
 * 删除
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] == "1"){
				flag = false;
				return flag;
			}
		});
		if(!flag){
			showAlertDivLayer("审核流程数据不能删除！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("qmlxjg.do?method=delJg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		if ("1" == rows[0]['sjly']) {
			showAlertDivLayer("审核流程数据不能修改！");
			return false;
		}
		var url = 'qmlxjg.do?method=editJg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "期末离校趋向结果修改";
		showDialog(title, 770, 550, url);
	}
}


var DCCLBH = "rcsw_qmlxjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "qmlxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



