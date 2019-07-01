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
		showAlert("�뽫��������д������");
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
		showDialog("�����걨", 800, 500, url);
}

function editFysb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length!=1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}
	if(rows[0]["shzt"]=="1"){
		showAlertDivLayer("��ͨ���ļ�¼�������޸ģ�");
		return false;
	}
	var url = "qgzx_kycxsjxmfysb.do?method=editFysb&sbid="+ids+"&xmid="+rows[0]["xmid"];
		showDialog("�����걨�޸�", 800, 500, url);
}
function cywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}  else {
		var url = "qgzx_kycxsjxmfysb.do?method=cywh&xmid="+ids;
		showDialog("��Աά��", 800, 580, url);
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
		html += "<td name='bjmc'>" + rows[int]["bjmc"] + "</td><td><input type='text' name='xmfg' width='80px'/></td>";
		html += "<td>"+ rows[int]["sjhm"] + "</td>";
		html += "<td><select  name='xmnzt' width='60px'>";
		html += "<option value='0'>����</option>" ;
		html += "<option value='1'>���</option>" ;
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
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sjxmView(\""
			+ rowObject["xmid"]+"\");'>" + cellValue
			+ "</a>";
}
function sjxmView(xmid) {
	showDialog("ʵ����Ŀ�鿴", 800, 550, "qgzx_kycxsjxmgl.do?method=viewSjxm&xmid="
			+ xmid);
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
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
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
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '1') {
				showAlertDivLayer("ֻ�����ύ�ļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
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
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']=='1'){
				showAlertDivLayer("��ͨ���ļ�¼������ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
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
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	initFfxx();
		var url = "qgzx_kycxsjxmfysb.do?method=saveFysb&type="+type;
		ajaxSubFormWithFun("SjxmFysbForm", url, function(data) {
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

function saveEditFysb(type){
	initFfxx();
		var url = "qgzx_kycxsjxmfysb.do?method=saveEditFysb&type="+type;
		ajaxSubFormWithFun("SjxmFysbForm", url, function(data) {
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

var DCCLBH = "qgzx_kycx_sjgl_sjxmfycx.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function dc() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ffcxExportData);
}

//��������
function ffcxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzx_kycxsjxmfysb.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}