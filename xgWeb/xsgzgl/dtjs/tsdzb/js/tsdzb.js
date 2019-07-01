
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tsdzbView(\""
			+ rowObject["dzbid"]+"\");'>" + cellValue
			+ "</a>";
}

function tsdzbView(dzbid) {
	showDialog("特色党支部查看", 800, 550, "dtjs_tsdzb.do?method=viewTsdzb&dzbid="+dzbid);
}

function save(type){
	var ids = null;
	if(type=='save'){
		ids = "dzbmc-fzr-lxfs"
	}else{
		ids = "dzbmc-fzr-lxfs"
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "dtjs_tsdzb.do?method=saveJg&type=" + type;
	ajaxSubFormWithFun("tsdzbForm", url, function(data) {
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

function add() {
	var url = "dtjs_tsdzb.do?method=addTsdzb";
	var title = "特色党支部新增";
	showDialog(title, 800, 550, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var shzt = rows[0]["shzt"];
		if (shzt != '0') {
			showAlertDivLayer("已审核的数据不能修改");
			return false;
		}

		var url = 'dtjs_tsdzb.do?method=editTsdzb&dzbid=' + rows[0]["dzbid"];
		var title = "特色党支部修改";
		showDialog(title, 800, 550, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt'] != '0'){
				showAlertDivLayer("已审核的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {"okFun" : function() {
		var url = "dtjs_tsdzb.do?method=delTsdzb";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');	
	}});
  }
}

var DCCLBH = "dtjs_tsdzb_tsdzbgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, tsdzbExportData);
}


//导出方法
function tsdzbExportData() {
	setSearchTj();//设置高级查询条件
	var url = "dtjs_tsdzb.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


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

//全选按钮
function xz(obj){
	var checkboxs = jQuery("input[name='bjdms']");
	if(jQuery(obj).prop("checked") == true){
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == false){
				jQuery(n).attr("checked",true);	
			}
		})
	}else{
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == true){
				jQuery(n).attr("checked",false);	
			}
		})
	}
}

//全选按钮
function xzForUpdate(obj){
	var checkboxs = jQuery("input[name='bjdms']");
	if(jQuery(obj).prop("checked") == true){
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == false){
				jQuery(n).attr("checked",true);				
			}
			pd(this);
		})
	}else{
		jQuery(checkboxs).each(function(i,n){
			if(jQuery(n).prop("checked") == true){
				jQuery(n).attr("checked",false);	
			}
			pd(this);
		})
	}
}

//单个审核
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}else if(rows.length != 1){
		showAlertDivLayer("请选择一条您要审核的记录")
		return false;
	}
	if (shzt != "dsh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else{
		var url = "dtjs_tsdzbsh.do?method=dgsh&dzbid="+rows[0]["dzbid"];
		showDialog("审核", 700, 480, url);
	}
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	}else{
		var guid = new Array();
		jQuery.each(rows, function(i, row) {
			guid.push(row["dzbid"]);
		});
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("dtjs_tsdzbsh.do?method=cx",{values:guid.toString()},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
		},'json');
		}});
	}
}


function changeXy(){
	jQuery.getJSON('dtjs_tsdzb.do?method=addZy',{xydm:jQuery('#xydm').val()},function(data){
		jQuery('#zydm').empty();
		jQuery('#zydm').append("<option value=''></option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].zydm + "\">" + data[i].zymc + "</option>";
				jQuery('#zydm').append(option);
			}
		}
	});
}

function searchBj(){
	jQuery.getJSON('dtjs_tsdzb.do?method=addBj',{xydm:jQuery('#xydm').val(),zydm:jQuery('#zydm').val(),njdm:jQuery('#njdm').val()},function(data){
		if(data != null && data.length != 0){
			jQuery("#displayArea").empty();
			var content = "";
			for(var i = 0; i<data.length; i++){
				content+="<font style='width:100px;'><input type='checkbox' name='bjdms' value='"+data[i].bjdm+"' />"+data[i].bjmc+"</font>";
				if((i + 1)% 5 == 0) {
					content+="</br>";
				}
			}
			jQuery("#displayArea").html(content);
//			var bjdms = jQuery("[name='bjdms']");
//			var xsbjdms = [];
//			for(var i=0; i<data.length; i++){
//				xsbjdms.push(data[i].bjdm);
//			}
//			jQuery(bjdms).each(function(){
//				var bjdm = jQuery(this).val();
//				if(xsbjdms.indexOf(bjdm) == -1){
//					jQuery(this).parents("span").eq(0).css("display","none");
//				}
//			})
		}else{
			jQuery("#displayArea").empty();
		}
	});
}

function searchBjForUpdate(){
	jQuery.getJSON('dtjs_tsdzb.do?method=addBj',{xydm:jQuery('#xydm').val(),zydm:jQuery('#zydm').val(),njdm:jQuery('#njdm').val(),dzbid:jQuery('#dzbid').val()},function(data){
		if(data != null && data.length != 0){
			jQuery("#displayArea").empty();
			var content = "";
			for(var i = 0; i<data.length; i++){
				content+="<font style='width:100px;'><input type='checkbox' name='bjdms' onchange='pd(this)' value='"+data[i].bjdm+"'";
				if(data[i].zt == "1") {
					content+=" checked='checked'";
				}
				content+="/>";
				content+=data[i].bjmc+"</font>";
				if((i + 1)% 5 == 0) {
					content+="</br>";
				}
			}
			jQuery("#displayArea").html(content);
		}else{
			jQuery("#displayArea").empty();
		}
	});
}

function tc(){
	var checks = jQuery("[name='bjdms']:checked");
	jQuery(checks).each(function(){
		jQuery("#existArea").append("<input type='hidden' name='bjdmms' value='"+jQuery(this).val()+"' />");
	})
}

function pd(obj){
	var checks = jQuery("[name='bjdmms']");
	if(obj.checked){
		flg = true;
		jQuery(checks).each(function(){
			if(jQuery(this).val() == obj.value){
				flg = false;
				return false;
			}
		})
		if(flg){			
			jQuery("#existArea").append("<input type='hidden' name='bjdmms' value='"+jQuery(obj).val()+"' />");
		}
	}else{
		jQuery(checks).each(function(){
			if(jQuery(this).val() == obj.value){
				jQuery(this).remove();
				return false;
			}
		})
	}
}
