
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function save(type) {
	var ids = null;
	var url = "";
	if(type=='add'){
		ids = "xh-sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
		url = "zxzx_zxzxjl.do?method=addZxJbxx&type=save";
	}else{
		ids = "sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
		url = "zxzx_zxzxjl.do?method=updateZxJbxx&type=save";
	}
	if(!check(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var checkboxs = jQuery("input[name='yzxwts']:checked");
	if(checkboxs.length == 0){
		if(jQuery("#wtbc").val().length == 0){
			showAlert("�빴ѡ��ѯ���������д��ѯ���ⲹ��");
			return false;
		}
	}
	
	ajaxSubFormWithFun("zxzxjlForm", url, function(data) {
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
	var flg = true;
	var url = "zxzx_zxzxjl.do?method=addZxJbxx&type=add";
	var title = "������ѯ���˻�����Ϣ";
	showDialog(title, 800, 500, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'zxzx_zxzxjl.do?method=updateZxJbxx&type=update&xh=' + rows[0]["xh"];
		var title = "�޸���ѯ���˻�����Ϣ";
		showDialog(title, 800, 500, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("ɾ��������ɾ����ѧ������ص�����̸����¼��Ϣ����ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("zxzx_zxzxjl.do?method=delZxJbxx", {
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

//��ѯ��¼ά��
function wh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫά����ѧ���ļ�¼��");
	}else {
		var url = 'zxzx_zxzxjl.do?method=whZxzxjl&xh=' + rows[0]["xh"];
		var title = "ά����ѯ��¼";
		showDialog(title, 800, 500, url);
	}
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ck(\""
			+ rowObject["xh"]+"\");'>" + cellValue
			+ "</a>";
}

//��ѯ��¼ά��
function ck(xh){	
		var url = 'zxzx_zxzxjl.do?method=ckZxzxjl&xh=' + xh;
		var title = "�鿴";
		showDialog(title, 800, 500, url);	
}

var DCCLBH = "xlzx_zxzx_zxzxjl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xlzxjlExportData);
}

//��������
function xlzxjlExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zxzx_zxzxjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		var lddm=jQuery("#"+id[i]).val().trim();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//��ӡ�ǼǱ�
function printExportDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxDjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxDjbPl";
		url += "&value=" + ids;
		window.open(url);
	}
}

//��ӡ��ѯ��¼�ǼǱ�
function printExportZxjlDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var len = ids.length;
	if (len == 1) {
		if(rows[0]["cs"] == 0){
			showAlertDivLayer("��ѧ������ѯ��¼��");
			return false;
		}
		var url = "zxzx_zxzxjl.do?method=ExportxlzxjlDjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxjlDjbPl";
		url += "&value=" + ids;
		window.open(url);
	}
}
