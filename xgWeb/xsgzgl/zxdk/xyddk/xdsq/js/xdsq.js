/**
 * 输入框onblur自动保存
 * @return
 */
function saveXdsq2(type){
	var xdly = jQuery("#xdlys").val();
	if (jQuery.trim(xdly) == ""){
		showAlertDivLayer("续贷理由不可为空！");
		return false;
	}
	/*加入字数限制判断*/
	if(xdly.length > 400){
		showAlertDivLayer("续贷理由不能超过400字！");
		return false;
	}
	var url = "zxdkDkjg.do?method=updatedkxx&type="+type;
	ajaxSubFormWithFun("HsdxdForm",url,function(data){
		if (data["message"] == "保存成功！") {
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				document.location.href=document.location;
			}});
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	});
}

/**
 * 提交
 */
function submitBusi() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (selectkey.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != "0" && shzt != "3") {
			showAlertDivLayer("未提交或已退回的数据才能被提交！");
			return false;
		}
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=submitBusi", {
					values : ids
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}
}

//撤销
function cancel() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (selectkey.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (selectkey.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != '5') {
			showAlertDivLayer("只有审核中的记录才能被撤销！");
			return false;
		}
		var splc = jQuery(selectrow).find("[name='splc']").val();
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=cancelXdsq", {
					values : ids,
					splcid : splc
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}

}

//流程跟踪
function lcgz() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		var id = jQuery(selectkey[0]).val();
		var splc = jQuery(selectrow).find("[name='splc']").val();
		if ("0" == shzt) {
			showAlertDivLayer("无相关流程跟踪信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ id + "&splc=" + splc);
	}
}

//删除
function del() {
	var selectkey = jQuery("[name='sqid']:checked");
	var ids = "";
	if (selectkey.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		var selectrow = jQuery(selectkey).parent().parent();
		var flag = true;
		for(var i=0;i<selectrow.length;i++){
			var shzt = jQuery(selectrow[i]).find("[name='shzt']").val();
			var id = jQuery(selectkey[i]).val();
			if(shzt != "0" && shzt != "3"){
				flag = false;
				break;
			}
			ids +=id;
			if(i != selectrow.length-1){
				ids +=",";
			}
		}
		if(!flag){
			showAlertDivLayer("只能删除未提交的记录！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zxdkDkjg.do?method=delXdxx",{values:ids},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
					   document.location.href=document.location;
					}
				});
			},'json');
		}});
	}
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='sqid']").attr("checked","checked");
	}else{
		jQuery("[name='sqid']").removeAttr("checked");
	}
}

function update(){
	var selectkey = jQuery("[name='sqid']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} 
	var selectrow = jQuery(selectkey[0]).parent().parent();
	var shzt = jQuery(selectrow).find("[name='shzt']").val();
	if (shzt != '0' && shzt != '3') {
		showAlertDivLayer("审核中的数据不能被修改！");
		return false;
	}
	var id = jQuery(selectrow).find("[name='sqid']").val();
	var xdxn = jQuery(selectrow).find("[name='xn']").val();
	var xdje = jQuery(selectrow).find("[name='xdjes']").val();
	var xdly = jQuery(selectrow).find("#xdlyss").text();
	jQuery("#updatexdxn").text(xdxn);
	jQuery("#id2").val(id);
	jQuery("#updatexdje").text(xdje);
	jQuery("#xdlys").val(xdly);
	jQuery("#xdsqTable2").show();
	
}

/**
 * 取消申请
 * @param obj
 * @return
 */
function qxsq(obj){
	var parentObj = jQuery(obj).parent().parent();
	var jgid = jQuery(parentObj).find("[name='jgid']").val();
	var url = 'gjdk_xdsqnew.do?method=qxsq';
	showConfirmDivLayer("放弃操作不可逆转，是否确认放弃操作！",{"okFun":function(){
		jQuery.ajax({ 
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'jgid='+jgid,
		async: false,
		success:function(result){
			if(result['message'] == "取消成功！"){
				showAlertDivLayer(result["message"], {}, {
					"clkFun" : function() {
					   document.location.href=document.location;
					}
				});
			}else{
				showAlertDivLayer(result["message"]);
			}
		 }
	    });
	}});
}

//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//放贷维护
function fdwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} 
	var url = 'gjdk_xdsqnew.do?method=fdWh&id=' + rows[0]["id"]
			+ '&xh=' + rows[0]["xh"];
	var title = "放贷维护";
	showDialog(title, 770, 450, url);
	
}

//续贷维护保存
function saveFdwh(){
	var ids = "fkje-fksj-fkpzh-dkzh-khh";
	if(!checkNotNull(ids)){
		showAlert("请将带<font class='red'>*</font>的项目填写完整！");
		return false;
	}
	if(parseInt(jQuery("#fkje").val()) > parseInt(jQuery("#xdjes").val())){
		showAlert("放贷金额不能大于该学年贷款金额"+jQuery("#xdjes").val()+"元！");
		return false;
	}
	var url = "gjdk_xdsqnew.do?method=saveFdwh";
	ajaxSubFormWithFun("HsdxdForm", url, function(data) {
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
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function jgView(id, xh) {
	showDialog("放贷维护查看", 770, 450, "gjdk_xdsqnew.do?method=fdCk&id="
			+ id + "&xh=" + xh);
}

//导入
function dr(){
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_HSDFDDR");
	return false;

}

var DCCLBH = "zxdk_gjdk_hsdfd.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gjdk_xdsqnew.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
