var checkId = 'xh-xmbh-xmmc-xmsxdm-xmssdw-kssj-jssj';
function check() {
	
	var flag=true;
	var id = checkId.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			
			flag= false;
		}
	}
	if(!flag){
		showAlert("请将必填项填写完整！");
		flag= false;
		
	}
	return flag;
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function SjxmView(jgid) {
	showDialog("实践项目增加查看", 800, 350, "qgzx_kycxsjxmgl.do?method=viewSjxm&jgid="
			+ jgid);
}
function xsZj(xh,xm,xymc,xydm,nj,sjhm) {
	if(null==sjhm||"null"==sjhm){
		sjhm="";
	}
	jQuery("#xh").val(xh);
	jQuery("#xmssdw").val(xydm);
	jQuery("td[name='xsxm']").html(xm);
	jQuery("td[name='xsnj']").html(nj);
	jQuery("td[name='xsxy']").html(xymc);
	jQuery("td[name='sjhm']").html(sjhm);
}
/*
 * 项目设置详情
 */
function ztsz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要设置的记录！");
		return false;
	}
	var url = 'qgzx_kycxsjxmgl.do?method=ztsz&xmid=' + rows[0]["xmid"];
	var title = "项目状态设置";
	showDialog(title, 680, 450, url);
}

function saveZtsz(type) {
	var flg = true;
	var url = "qgzx_kycxsjxmgl.do?method=ztsz&type="+type;
	ajaxSubFormWithFun("SjxmglForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
}
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sjxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function sjxmView(xmid) {
	showDialog("实践项目查看", 800, 550, "qgzx_kycxsjxmgl.do?method=viewSjxm&xmid="
			+ xmid);
}
function saveSjxm(type) {
	var flg = true;
	if (check()) {
		var flg=true;
		var gwxx= [];
		jQuery.each(jQuery("#tbody_xmgw tr"),function(i,n){
			var obj = {};
				var zcyrs = jQuery(n).find("td").find("input[name=zcyrs]").val();
				var gwgzzy = jQuery(n).find("td").find("input[name=gwgzzy]").val();
				var gwlb = jQuery(n).find("select[name=gwlb] option:selected").val();
				obj.zcyrs=zcyrs;
				obj.gwgzzy=gwgzzy;
				obj.gwlb=gwlb;
				gwxx.push(obj);
				
		});
		var gwxxStr = JSON.stringify(gwxx);
		jQuery("#gwxxStr").val(gwxxStr);
		var url = "qgzx_kycxsjxmgl.do?method=saveSjxm&type="+type;
		ajaxSubFormWithFun("SjxmglForm", url, function(data) {
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
}
function add() {
	var url = "qgzx_kycxsjxmgl.do?method=addSjxm";
	var title = "实践项目增加";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'qgzx_kycxsjxmgl.do?method=editSjxm&xmid=' + rows[0]["xmid"];
		var title = "实践项目修改";
		showDialog(title, 800, 508, url);
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
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("只有未提交的数据才可删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "qgzx_kycxsjxmgl.do?method=delSjxm";
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
//项目费用维护
function SjxmFywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要操作的记录！");
	}  else {
		var url = "qgzx_kycxsjxmgl.do?method=SjxmFywh&xmid="+ids;
		showDialog("项目费用维护", 800, 550, url);
	}
	
}
function addXmgw() {
	var html = jQuery("tbody[name=xmgwTbody]").html();
	jQuery("#tbody_xmgw").append(html);	
	
}

function delXmgw() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除的岗位！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
	
	initCyrs();
}

function saveFywh(type){
	var flg=true;
	var gwxx= [];
	jQuery.each(jQuery("#tbody_xmfy tr"),function(i,n){
		var obj = {};
			var zcyrs = jQuery(n).find("td").find("input[name=zcyrs]").val();
			var gwgzzy = jQuery(n).find("td").find("input[name=gwgzzy]").val();
			var gwlb = jQuery(n).find("select[name=gwlb] option:selected").val();
			obj.zcyrs=zcyrs;
			obj.gwgzzy=gwgzzy;
			obj.gwlb=gwlb;
			gwxx.push(obj);
			
	});
	var gwxxStr = JSON.stringify(gwxx);
	jQuery("#gwxxStr").val(gwxxStr);
	var url = "qgzx_kycxsjxmgl.do?method=saveFywh"
	ajaxSubFormWithFun("SjxmglForm", url, function(data) {
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
