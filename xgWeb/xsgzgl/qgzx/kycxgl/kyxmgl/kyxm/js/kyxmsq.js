var checkId = 'sqzy-lxyj-gjwt-yjfa-yjjh-cgxsyqjg';
var zdmcList='申请摘要-立项依据-项目的主要内容和创新点、拟解决的关键问题-研究方案-研究计划-成果形式及预期结果';
var zsnumList='300-500-500-500-500-500';

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
	if(!checkTextAreaLength(checkId,zdmcList,zsnumList)){
		flag=false;
	}
	return flag;
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KyxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function KyxmView(xmid) {
	showDialog("科研项目查看", 800, 550, "qgzx_kycxkyxmgl.do?method=viewKyxm&xmid="
			+ xmid);
}

function xmwh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要操作的记录！");
		return false;
	}
	if ("0" != rows[0]["shzt"]&&"3" != rows[0]["shzt"]) {
		showAlertDivLayer("请选择未提交和已退回的记录！");
		return false;
	}
		var url = "qgzx_kycxkyxmsq.do?method=xmwh&xmid="+ids;
		showDialog("项目维护", 800, 508, url);
}
function cywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要操作的记录！");
	}  else {
		var url = "qgzx_kycxkyxmsq.do?method=cywh&xmid="+ids;
		showDialog("成员维护", 800, 580, url);
	}
	
}
function selectAll(obj) {
	jQuery(obj).parents(".datelist").find('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}
//增加行
function addXs() {
	var xmid=jQuery("#xmid").val();
	var xhs = new Array();
	jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhs.push(xh);
	});
	var url = 'qgzx_kycxkyxmsq.do?method=getStu&xmid=' + xmid+'&xhs='+xhs;
	showDialog('', 800, 500, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

//增加行
function addJs() {
	var xmid=jQuery("#xmid").val();
	var zghs = new Array();
	jQuery.each(jQuery("#tbody_jsxx tr"),function(i,n){
			var zgh= jQuery(n).find("td").eq(1).text();
			zghs.push(zgh);
	});
	var url = 'qgzx_kycxkyxmsq.do?method=getTea&xmid=' + xmid+'&zghs='+zghs;
	showDialog('', 800, 500, url);
	return false;
}

function delJs() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除老师！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}


function setSqxs(rows) {

	var html = "";
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_xsxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		var index=qqxsTrLen+int;
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td><td name='xb'>" + rows[int]["xb"] + "</td><td name='xymc'>" + rows[int]["xymc"] + "</td>";
		html += "<td name='bjmc'>" + rows[int]["bjmc"] + "</td><td><input type='text' name='xmfg' style='width:100px' maxlength='20'/></td>";
		html += "<td>"+rows[int]["lxdh"]+"</td>";
		html += "<td><select  name='sfsfs' width='60px'>";
		html += "<option value='0'>否</option>" ;
		html += "<option value='1'>是</option>" ;
		html += "</select></td>";
		jQuery("#tbody_xsxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqxsZj(html);

	iFClose();
}

function sqxsZj(html){
	jQuery("#tbody_xsxx").append(html);	
	}


function setSqjs(rows) {

	var html = "";
	var lxdh='';
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_jsxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='zgh'>" + rows[int]["zgh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td><td name='xymc'>" + rows[int]["xymc"] + "</td>";
		html += "<td><input type='text' name='zc' width='85px' maxlength='10'/></td>";
		html += "<td><input type='text' name='yjfx' width='160px' maxlength='100'/></td>";
		html += "<td name='lxdh'>" + rows[int]["lxdh"] + "</td>";
		jQuery("#tbody_jsxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqjsZj(html);

	iFClose();
}

function sqjsZj(html){
	jQuery("#tbody_jsxx").append(html);	
	}

function addJf() {
	var html = jQuery("tbody[name=jfysTbody]").html();
	jQuery("#tbody_ysxx").append(html);	
	
}

function delJf() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除记录！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}


function saveXmwh(type){
	if(check()){
		var flg=true;
		var xsxx= [];
		var jsxx = [];
		var ysxx = [];
		jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var obj = {};
				var xh= jQuery(n).find("td").eq(1).text();
				var xmfg = jQuery(n).find("td").find("input[name=xmfg]").val();
				var sfsfs = jQuery(n).find("select[name=sfsfs] option:selected").val();
				obj.xh=xh;
				obj.xmfg=xmfg;
				obj.sfsfs=sfsfs;
				xsxx.push(obj);
				
		});
		var xsxxStr = JSON.stringify(xsxx);
		jQuery("#xsxxStr").val(xsxxStr);
		jQuery.each(jQuery("#tbody_jsxx tr"),function(i,n){
			var obj = {};
				var zgh= jQuery(n).find("td").eq(1).text();
				var zc = jQuery(n).find("td").find("input[name=zc]").val();
				var yjfx =jQuery(n).find("td").find("input[name=yjfx]").val();
				obj.zgh=zgh;
				obj.zc=zc;
				obj.yjfx=yjfx;
				jsxx.push(obj);
				
		});
		var jsxxStr = JSON.stringify(jsxx);
		jQuery("#jsxxStr").val(jsxxStr);
		
		jQuery.each(jQuery("#tbody_ysxx tr"),function(i,n){
			var obj = {};
				var zclb= jQuery(n).find("td").find("input[name=zclb").val();
				if(null!=zclb&&""!=zclb){
				var ysje = jQuery(n).find("td").find("input[name=ysje]").val();
				var zyyt = jQuery(n).find("td").find("input[name=zyyt]").val();
				obj.zclb=zclb;
				obj.ysje=ysje;
				obj.zyyt=zyyt;
				ysxx.push(obj);
				}
		});
		var ysxxStr = JSON.stringify(ysxx);
		jQuery("#ysxxStr").val(ysxxStr);
		var url = "qgzx_kycxkyxmsq.do?method=saveXmwh&type="+type;
		ajaxSubFormWithFun("KyxmglForm", url, function(data) {
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

//提交
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if("0"!=rows[0]["shzt"]&&"3"!=rows[0]["shzt"]){
		showAlertDivLayer("请选择未提交或已退回的记录！");
		return false;
		
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("qgzx_kycxkyxmsq.do?method=submitXmwh", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}
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
				jQuery.post("qgzx_kycxkyxmsq.do?method=cancelXmwh", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

function saveCywh(type){
		var flg=true;
		var xsxx= [];
		var jsxx = [];
		jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var obj = {};
				var xh= jQuery(n).find("td").eq(1).text();
				var xmfg = jQuery(n).find("td").find("input[name=xmfg]").val();
				var sfsfs = jQuery(n).find("select[name=sfsfs] option:selected").val();
				
				obj.xh=xh;
				obj.xmfg=xmfg;
				obj.sfsfs=sfsfs;
				xsxx.push(obj);
				
		});
		var xsxxStr = JSON.stringify(xsxx);
		jQuery("#xsxxStr").val(xsxxStr);
		jQuery.each(jQuery("#tbody_jsxx tr"),function(i,n){
			var obj = {};
				var zgh= jQuery(n).find("td").eq(1).text();
				var zc = jQuery(n).find("td").find("input[name=zc]").val();
				var yjfx =jQuery(n).find("td").find("input[name=yjfx]").val();
				obj.zgh=zgh;
				obj.zc=zc;
				obj.yjfx=yjfx;
				jsxx.push(obj);
				
		});
		var jsxxStr = JSON.stringify(jsxx);
		jQuery("#jsxxStr").val(jsxxStr);
		var url = "qgzx_kycxkyxmsq.do?method=saveCywh"
		ajaxSubFormWithFun("KyxmglForm", url, function(data) {
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