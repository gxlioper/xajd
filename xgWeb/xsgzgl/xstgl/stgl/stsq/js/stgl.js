var checkId="stxmmc-stlbdm-xmlbdm-xn-gkdw-fzrlb-stfzr-stclsj";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='StsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function StsqView(id) {
	showDialog("社团申请查看", 800, 450, "stglStsq.do?method=viewStsq&sqid="+id);
}
function saveStsq(type) {
	var flg=true;
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
    var obj = jQuery("input[name='zgh']");
    if(obj.length == 0){
        showAlert("请添加指导老师！");
        return false;
    }
	if(checkzsinput_yz(jQuery("#stsm"),500) == false || checkzsinput_yz(jQuery("#sthjqk"),500) == false){
		return false;
	}

	jQuery("#xhs").val(getxhs);
	jQuery("#ssbm").attr("disabled",false);
	jQuery("#zdlszc").attr("disabled",false);
	jQuery("#zdlslxfs").attr("readonly",false);
	var url = "stglStsq.do?method=saveStsq&type=" + type;
	ajaxSubFormWithFun("StsqForm", url, function(data) {
		 if(data["message"]=="提交成功！"||data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 flg = false;
    		 showAlert(data["message"],{},{"clkFun":function(){
 				jQuery("#ssbm").attr("disabled",true);
 				jQuery("#zdlszc").attr("disabled",true);
 				jQuery("#zdlslxfs").attr("readonly",true);
 		    }});
    	}
		});
}

function getxhs(){
    var xhs = "";
    var xhobj = jQuery("[name='zgh']");
    jQuery(xhobj).each(function(i){
        xhs +=this.value;
        if(xhobj.length-1 != i){
            xhs +=",";
        }

    });
    return xhs;
}

//修改
function editStsq(type) {
	var flag = true;
	jQuery("select").attr("disabled",false);
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
    var obj = jQuery("input[name='zgh']");
    if(obj.length == 0){
        showAlert("请添加指导老师！");
        return false;
    }
	if(checkzsinput_yz(jQuery("#stsm"),500) == false || checkzsinput_yz(jQuery("#sthjqk"),500) == false){
		return false;
	}
    jQuery("#xhs").val(getxhs);
	jQuery("#ssbm").attr("disabled",false);
	jQuery("#zdlszc").attr("disabled",false);
	jQuery("#zdlslxfs").attr("readonly",false);
	var url = "stglStsq.do?method=saveEditStsq&type=" + type;
	ajaxSubFormWithFun("StsqForm", url, function(data) {
		 if(data["message"]=="提交成功！"||data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 flag = false;
    		 showAlert(data["message"],{},{"clkFun":function(){
    				jQuery("#ssbm").attr("disabled",true);
    				jQuery("#zdlszc").attr("disabled",true);
    				jQuery("#zdlslxfs").attr("readonly",true);
    		    }});
    		}
		});
	
}

function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前已关闭，请与管理员联系！");
		return false;
	}
	var url = "stglStsq.do?method=addStsq";
	var title = "社团申请";
	showDialog(title, 800, 450, url);
}
function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'stglStsq.do?method=editStsq&sqid=' + rows[0]["sqid"];
		var title = "社团申请修改";
		showDialog(title, 800, 450, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("stglStsq.do?method=delStsq", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// 提交
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("stglStsq.do?method=submitStsq", {
				values : ids.toString(),
				xmlbdm : rows[0]["xmlbdm"]
			}, function(data) {
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});

			}, 'json');
		}
	});
	
	

}
// 撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("stglStsq.do?method=cancelStsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("社团申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "stgl_stgl_stsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khsqExportData);
}

//导出方法
function khsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "stglStsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ajax动态获取项目类别list
function getXmlblist(stlbdm){
	var rs = null;
	var url = "stglXmlbDmwh.do?method=getXmlblist";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:'stlbdm='+stlbdm,
	async: false,
	success:function(result){
		if(result==null||result=="null"){
			rs = null;
		}else{
			rs = result['html'];;
		}
	 }
    });
	return rs;
};