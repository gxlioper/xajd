/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["id"] + "\",\""
			+ rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(id,xh) {
	showDialog("留校名单查看", 770, 450, "jqlx_lxmdwh.do?method=ckMdwh&id="
			+ id+"&xh="+xh);
}

/**
 * 批量维护
 * @return
 */
function add(){
	showDialog("批量留校名单维护", 770, 500, "jqlx_lxmdwh.do?method=plMdwh"
			);
}


//批量保存
function savePlwh(){
	var url = "jqlx_lxmdwh.do?method=savePlMdwh";
	//检验数据
	if(!checkNotNull("lxqksm")){
		 showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		 return false;
	}
	if(jQuery("[name='xh']").length == 0){
		 showAlert("请至少选择一个学生！");
		 return false;
	}
	if(jQuery("#lxqksm").val().length > 500){
		 showAlert("留校情况说明不能大于500字！");
		 return false;
	}
	ajaxSubFormWithFun("LxmdwhForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		 return false;
    		}
		});
}

//删除维护结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jqlx_lxmdwh.do?method=delWhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_rcsw_cqsx_jqlx_lxmdwh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "jqlx_lxmdwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_LXMDWH");
	return false;
}


/**
 * 保存修改
 * @return
 */
function saveXg(){
	//必填数据验证
	if((!checkNotNull("lxqksm"))){
		 showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		 return false;
	}
	if(jQuery("#lxqksm").val().length > 500){
		 showAlert("留校情况说明不能大于500字！");
		 return false;
	}
	var url = "jqlx_lxmdwh.do?method=xgMdwh&type=save";
	ajaxSubFormWithFun("LxmdwhForm", url, function(data) {
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

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		var url = 'jqlx_lxmdwh.do?method=xgMdwh&id=' + rows[0]['id'];
		var title = "修改留校名单维护";
		showDialog(title, 700, 450, url);
	}
}

//复选框选中
function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

/**
 * 留校项目下拉框值改变联动
 * @return
 */
function changeXmmc(obj){
	var objvalue = obj.value;
	document.location.href = "jqlx_lxmdwh.do?method=plMdwh&xmid="+objvalue;
}

/**
 * 添加学生
 * @return
 */
function addRowDialog(){
	if(jQuery("#xmid").val() == "" || jQuery("#xmid").val() == null){
		  showAlert("请先选择项目！");
		  return false;
	}
	var xhs = getxhs();
	 var xmid= jQuery("#xmid").val();
    var url = "jqlx_lxmdwh.do?method=getCanAddUserList&xhs="+xhs+"&xmid="+xmid;
    var title = "学生选择";
	showDialog(title, 800, 550, url);
}

//得到已添加的学号字符串
function getxhs(){
	var xhs = "";
	var xhobj = jQuery("[name='xh']");
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
		
	});
	return xhs;
}

//删除行
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("请先选择成员信息，再进行删除操作！");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
}



