var ids = "xh-xn-fwxss-fwsj-fz";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='fjfglView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function fjfglView(id) {
	showDialog("���ӷֲ鿴", 650, 450, "xsxwkhFjfgl.do?method=fjfglView&id="+id);
}
function saveFjfgl(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
	var url = "xsxwkhFjfgl.do?method=saveFjfgl&type=" + type;
	ajaxSubFormWithFun("fjfglForm", url, function(data) {
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

//�޸�
function editFjfgl(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
	var url = "xsxwkhFjfgl.do?method=saveEditFjfgl&type=" + type;
	ajaxSubFormWithFun("fjfglForm", url, function(data) {
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
	var url = "xsxwkhFjfgl.do?method=addFjfgl";
	var title = "���ӷ�";
	showDialog(title, 650, 450, url);
}
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xsxwkhFjfgl.do?method=editFjfgl&id=' + rows[0]["id"];
		var title = "���ӷ��޸�";
		showDialog(title, 650, 450, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else{
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxwkhFjfgl.do?method=delFjfgl", {
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

var DCCLBH = "xsxwkh_fjfgl.do";//dcclbh,�������ܱ��

function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N158705_FJFGL");
	return false;

}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, fjfExportData);
}

//��������
function fjfExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxwkhFjfgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}