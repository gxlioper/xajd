
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zzdsqView(\""
			+ rowObject["zzdid"]+"\");'>" + cellValue
			+ "</a>";
}
function zzdsqView(sqid) {
	showDialog("ת�߶�����鿴", 800, 550, "xgygl_zdsq.do?method=viewZzdsq&zzdid="+sqid);
}


function saveZzdsq(type) {
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = "xh-sdyy"
	}else{
		ids = "sdyy"
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "xgygl_zdsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("zzdsqForm", url, function(data) {
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

function add() {
//	var sqkg = jQuery("#sqkg").val();
//	if ("0" == sqkg) {
//		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
//		return false;
//	}
	var flg = true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("xgygl_zdsq.do?method=isZhusu", {}, function(data) {
		if(!data){
			flg = false;					
		}	      		
		}, 'json');
	jQuery.ajaxSetup({async:true});
	if(!flg){
		showAlertDivLayer("��δס�ޣ��������룡");
		return false;
	}
	var url = "xgygl_zdsq.do?method=addZzdsq";
	var title = "ת�߶���������";
	showDialog(title, 800, 550, url);
}

function update() {
	//var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var shzt = rows[0]["shzt"];
//		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
//			return false;
//		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'xgygl_zdsq.do?method=editZzdsq&zzdid=' + rows[0]["zzdid"];
		var title = "ת�߶������޸�";
		showDialog(title, 800, 550, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xgygl_zdsq.do?method=delZzdsq", {
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

// �ύ
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	//var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
//	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
//		return false;
//	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("xgygl_zdsq.do?method=submitZzdsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
	
	

}
// ����
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
				jQuery.post("xgygl_zdsq.do?method=cancelZzdsq", {
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
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("ת�߶������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['zzdid'] + "&splc=" + rows[0]['splcid']);
}


var DCCLBH = "xgygl_zzdgl_zdsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, zzdsqExportData);
}

//��������
function zzdsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xgygl_zdsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
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

function printzzdsqb(url){
	var zzdid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				zzdid +=rows[i]["zzdid"];
			}else{
				zzdid +=rows[i]["zzdid"]+",";
			}
		}		
		var url = url + "&zzdid=" +zzdid;
		window.open(url);
	}
}