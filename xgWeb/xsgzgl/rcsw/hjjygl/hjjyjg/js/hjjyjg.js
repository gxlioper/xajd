var ids = "xh-jykssj-jyjzsj";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='HjjyJgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}
function HjjyJgView(jgid) {
	showDialog("�������ò鿴", 650, 450, "hjjyJyjg.do?method=HjjyJgView&jgid="+jgid);
}
function saveHjjyJg(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
	var url = "hjjyJyjg.do?method=saveHjjyJg&type=" + type;
	ajaxSubFormWithFun("HjjyJgForm", url, function(data) {
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
function editHjjyJg(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
	var url = "hjjyJyjg.do?method=saveEditHjjyJg&type=" + type;
	ajaxSubFormWithFun("HjjyJgForm", url, function(data) {
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
	var url = "hjjyJyjg.do?method=addHjjyJg";
	var title = "��������";
	showDialog(title, 650, 450, url);
}
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'hjjyJyjg.do?method=editHjjyJg&jgid=' + rows[0]["jgid"];
		var title = "���������޸�";
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
				jQuery.post("hjjyJyjg.do?method=delHjjyJg", {
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
//���ù黹
function jygh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
    var flag = true;
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}else if("1"==rows[0]["jyzt"]){
		showAlertDivLayer("��ѡ��δ�黹�ļ�¼��");
	} else {
		showDialog("�����黹",650,300,"hjjyJyjg.do?method=jygh&jgid="+ids.toString());
	}
}

var DCCLBH = "rcsw_hjjy_jyjg.do";//dcclbh,�������ܱ��

function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N158605_HJJYJG");
	return false;

}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jyjgExportData);
}

//��������
function jyjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "hjjyJyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}