function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸�',700,480,'wsjcJcrc.do?method=edit&id='+rows[0]["id"]);
	}
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=delete",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function add(){
	showDialog('����',700,480,'wsjcJcrc.do?method=add');;
}

function jcrcSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫ�����ļ�¼��");
	} else {
		showConfirmDivLayer("�ò�������������������ճ̣����������¼�룬�Ƿ�ȷ������������",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=submit",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function jcrcCancel(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫ�����ļ�¼��");
	} else {
		showConfirmDivLayer("�ò�������ȡ����������ճ��������������¼�룬�Ƿ�ȷ������������",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=cancel",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}