var checkId = 'sqzy-jfys-lxyj-gjwt-yjfa-yjjh-cgxsyqjg';
var zdmcList='����ժҪ-��������-��Ŀ����Ҫ���ݺʹ��µ㡢�����Ĺؼ�����-�о�����-�о��ƻ�-�ɹ���ʽ��Ԥ�ڽ��';
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
		showAlert("�뽫��������д������");
		flag= false;
		
	}
	if(!checkTextAreaLength("sqzy-lxyj-gjwt-yjfa-yjjh-cgxsyqjg",zdmcList,zsnumList)){
		flag=false;
	}
	return flag;
	return flag;
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sjxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function sjxmView(xmid) {
	showDialog("ʵ����Ŀ�鿴", 800, 550, "qgzx_kycxsjxmgl.do?method=viewSjxm&xmid="
			+ xmid);
}
function xmwh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}
	if ("0" != rows[0]["shzt"]&&"3" != rows[0]["shzt"]) {
		showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
		return false;
	}
		var url = "qgzx_kycxsjxmsq.do?method=xmwh&xmid="+ids;
		showDialog("��Ŀά��", 800, 508, url);
}
function cywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}  else {
		var url = "qgzx_kycxsjxmsq.do?method=cywh&xmid="+ids;
		showDialog("��Աά��", 800, 500, url);
	}
	
}

//������
function addXs() {
	var xmid=jQuery("#xmid").val();
	var xhs = new Array();
	jQuery.each(jQuery("#tbody_xsxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhs.push(xh);
	});
	var url = 'qgzx_kycxsjxmsq.do?method=getStu&xmid=' + xmid+'&xhs='+xhs;
	showDialog('', 800, 500, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

//������
function addJs() {
	var xmid=jQuery("#xmid").val();
	var zghs = new Array();
	jQuery.each(jQuery("#tbody_jsxx tr"),function(i,n){
			var zgh= jQuery(n).find("td").eq(1).text();
			zghs.push(zgh);
	});
	var url = 'qgzx_kycxsjxmsq.do?method=getTea&xmid=' + xmid+'&zghs='+zghs;
	showDialog('', 800, 500, url);
	return false;
}

function delJs() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ����ʦ��");
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
		html += "<td>"+ rows[int]["lxdh"] + "</td>";
		html += "<td><select  name='xmnzt' width='60px'>";
		html += "<option value='1'>����</option>" ;
		html += "<option value='0'>���</option>" ;
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
		
		var url = "qgzx_kycxsjxmsq.do?method=saveXmwh&type="+type;
		ajaxSubFormWithFun("SjxmglForm", url, function(data) {
			 if(data["message"]=="����ɹ���"){
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

//�ύ
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if('0'==rows[0]["sfytx"]){
		showAlertDivLayer("�뽫��������д���������ύ��");
		return false;
	}
	if("0"!=rows[0]["shzt"]&&"3"!=rows[0]["shzt"]){
		showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
		return false;
		
	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("qgzx_kycxsjxmsq.do?method=submitXmwh", {
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
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("qgzx_kycxsjxmsq.do?method=cancelXmwh", {
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
		var url = "qgzx_kycxsjxmsq.do?method=saveCywh"
		ajaxSubFormWithFun("SjxmglForm", url, function(data) {
			 if(data["message"]=="����ɹ���"){
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