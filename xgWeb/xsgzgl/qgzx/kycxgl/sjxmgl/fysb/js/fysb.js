var checkId = 'jfys-sqzy-lxyj-gjwt-yjfa-yjjh-cgxsyqjg';
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

function addFysb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "qgzx_kycxsjxmfysb.do?method=addFysb";
		showDialog("费用申报", 800, 500, url);
}

function editFysb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length!=1){
		showAlertDivLayer("请选择一条您要操作的记录！");
		return false;
	}
	if(rows[0]["shzt"]=="1"){
		showAlertDivLayer("已通过的记录不允许修改！");
		return false;
	}
	var url = "qgzx_kycxsjxmfysb.do?method=editFysb&sbid="+ids+"&xmid="+rows[0]["xmid"];
		showDialog("费用申报修改", 800, 500, url);
}
function cywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要操作的记录！");
	}  else {
		var url = "qgzx_kycxsjxmfysb.do?method=cywh&xmid="+ids;
		showDialog("成员维护", 800, 580, url);
	}
	
}

//增加行
function addXs() {
	var xmid=jQuery("#xmid").val();
	var xhs = new Array();
	jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhs.push(xh);
	});
	var url = 'qgzx_kycxsjxmfysb.do?method=getStu&xmid=' + xmid+'&xhs='+xhs;
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
	var url = 'qgzx_kycxsjxmfysb.do?method=getTea&xmid=' + xmid+'&zghs='+zghs;
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
		html += "<td name='bjmc'>" + rows[int]["bjmc"] + "</td><td><input type='text' name='xmfg' width='80px'/></td>";
		html += "<td>"+ rows[int]["sjhm"] + "</td>";
		html += "<td><select  name='xmnzt' width='60px'>";
		html += "<option value='0'>正常</option>" ;
		html += "<option value='1'>离岗</option>" ;
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
		html += "<td><input type='text' name='zc' width='85px'/></td>";
		html += "<td><input type='text' name='yjfx' width='160px'/></td>";
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

function saveXmwh(type){
	if(check()){
		var flg=true;
		var xsxx= [];
		var jsxx = [];
		jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var obj = {};
				var xh= jQuery(n).find("td").eq(1).text();
				var xmfg = jQuery(n).find("td").find("input[name=xmfg]").val();
				var xmnzt = jQuery(n).find("select[name=xmnzt] option:selected").val();
				obj.xh=xh;
				obj.xmfg=xmfg;
				obj.xmnzt=xmnzt;
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
		
		var url = "qgzx_kycxsjxmfysb.do?method=saveXmwh&type="+type;
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
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sjxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function sjxmView(xmid) {
	showDialog("实践项目查看", 800, 550, "qgzx_kycxsjxmgl.do?method=viewSjxm&xmid="
			+ xmid);
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
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("qgzx_kycxsjxmfysb.do?method=submitFysb", {
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
			if (rows[i]['shzt'] != '1') {
				showAlertDivLayer("只有已提交的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qgzx_kycxsjxmfysb.do?method=cancelFysb", {
					values : ids.toString(),
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']=='1'){
				showAlertDivLayer("已通过的记录不允许删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "qgzx_kycxsjxmfysb.do?method=delFysb";
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
function saveFysb(type){
	var xmid=jQuery("#xmid").val();
	
	if(null==xmid||""==xmid){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	initFfxx();
		var url = "qgzx_kycxsjxmfysb.do?method=saveFysb&type="+type;
		ajaxSubFormWithFun("SjxmFysbForm", url, function(data) {
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

function saveEditFysb(type){
	initFfxx();
		var url = "qgzx_kycxsjxmfysb.do?method=saveEditFysb&type="+type;
		ajaxSubFormWithFun("SjxmFysbForm", url, function(data) {
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


function initFfxx(){
	var sbxx = [];
	jQuery.each(jQuery("#tbody_sbxx tr"),function(i,n){
		var obj = {};
			var xh= jQuery(n).find("td").eq(0).text();
			var gs = jQuery(n).find("td").find("input[name=gs]").val();
			var cjje = jQuery(n).find("td").find("input[name=cjje]").val();
			obj.xh=xh;
			obj.gs=gs;
			obj.cjje=cjje;
			sbxx.push(obj);
			
	});
	var sbxxStr = JSON.stringify(sbxx);
	jQuery("#sbxxStr").val(sbxxStr);
	
}

function getCjje(obj){
	var gs = obj.value;
	var cjbz = "0";
	if(null!=jQuery("#cjbz").val()&&""!=jQuery("#cjbz").val()){
		cjbz=jQuery("#cjbz").val();
		}
	var cjje = parseFloat(cjbz)*parseFloat(gs);
	if(null==gs||""==gs){
		jQuery(obj).parent().parent().find("td").eq(-1).find("input[name=cjje]").val("");
	
	}else{
		jQuery(obj).parent().parent().find("td").eq(-1).find("input[name=cjje]").val(cjje);
	}
	}

var DCCLBH = "qgzx_kycx_sjgl_sjxmfycx.do";//dcclbh,导出功能编号

//自定义导出 功能
function dc() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ffcxExportData);
}

//导出方法
function ffcxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "qgzx_kycxsjxmfysb.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}