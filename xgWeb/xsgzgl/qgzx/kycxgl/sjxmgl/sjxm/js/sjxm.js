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
		showAlert("�뽫��������д������");
		flag= false;
		
	}
	return flag;
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function SjxmView(jgid) {
	showDialog("ʵ����Ŀ���Ӳ鿴", 800, 350, "qgzx_kycxsjxmgl.do?method=viewSjxm&jgid="
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
 * ��Ŀ��������
 */
function ztsz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
		return false;
	}
	var url = 'qgzx_kycxsjxmgl.do?method=ztsz&xmid=' + rows[0]["xmid"];
	var title = "��Ŀ״̬����";
	showDialog(title, 680, 450, url);
}

function saveZtsz(type) {
	var flg = true;
	var url = "qgzx_kycxsjxmgl.do?method=ztsz&type="+type;
	ajaxSubFormWithFun("SjxmglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
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
	showDialog("ʵ����Ŀ�鿴", 800, 550, "qgzx_kycxsjxmgl.do?method=viewSjxm&xmid="
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
function add() {
	var url = "qgzx_kycxsjxmgl.do?method=addSjxm";
	var title = "ʵ����Ŀ����";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'qgzx_kycxsjxmgl.do?method=editSjxm&xmid=' + rows[0]["xmid"];
		var title = "ʵ����Ŀ�޸�";
		showDialog(title, 800, 508, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��δ�ύ�����ݲſ�ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
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
//��Ŀ����ά��
function SjxmFywh() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}  else {
		var url = "qgzx_kycxsjxmgl.do?method=SjxmFywh&xmid="+ids;
		showDialog("��Ŀ����ά��", 800, 550, url);
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
		showAlertDivLayer("��ѡ����Ҫɾ���ĸ�λ��");
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
