
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='kqsqView(\""
			+ rowObject["sqid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}
function kqsqView(sqid, bjdm) {
	showDialog(jQuery("#gnmkmc").val()+"查看", 800, 500, "zwzxKqsq.do?method=viewKqsq&sqid="
			+ sqid + "&bjdm=" + bjdm);
}
function saveKqsq(type) {
	var flg=true;
	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
		if (flg){
			var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
			var xh= jQuery(n).find("td").eq(1).text();
			var kkjs = jQuery(n).find("input[name=kkjs]").val();
			var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
			obj.xh=xh;
			obj.kkjs=kkjs;
			obj.qqlxdm=qqlx;
			obj.ylzd1=ylzd1;
			objArr.push(obj);
			flg = (qqlx != "" );
		}
	});
	var validateFlag = true;
		validateFlag = checkKqxx(flg,type);
	if(validateFlag){
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zwzxKqsq.do?method=saveKqsq&type=" + type;
	ajaxSubFormWithFun("ZwzxKqsqForm", url, function(data) {
		 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
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

}

function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	var url = "zwzxKqsq.do?method=addKqsq";
	var title = jQuery("#gnmkmc").val()+"增加";
	showDialog(title, 800, 500, url);
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

		var url = 'zwzxKqsq.do?method=editKqsq&sqid=' + rows[0]["sqid"];
		var title = jQuery("#gnmkmc").val()+"修改";
		showDialog(title, 800, 500, url);
	}

}

function fdyfk() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要反馈的记录！");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前考勤填写已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'zwzxKqsq.do?method=fdyfk&sqid=' + rows[0]["sqid"];
		var title = "早晚自习考勤反馈";
		showDialog(title, 800, 500, url);
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
				jQuery.post("zwzxKqsq.do?method=delKqsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "成功删除了<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>条数据";
							showAlertDivLayer(mes);
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
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
		 if(rows[0]["qqxss"]!=rows[0]["qqrs"]){
		    	showAlertDivLayer('缺勤人数为' + rows[0]["qqrs"] + '人，缺勤学生数为'+rows[0]["qqxss"]+'人,请修改！');
				return false;
		    }
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("zwzxKqsq.do?method=submitKqsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
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
				jQuery.post("zwzxKqsq.do?method=cancelKqsq", {
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
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "rcsw_zwzxkq_kqsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khsqExportData);
}

//导出方法
function khsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zwzxKqsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}