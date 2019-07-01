function addHksq(){
	showDialog("����",800,520,"zxdkTqhk.do?method=hksq");
}

function editHksq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��������������У������޸ģ�");
			return false;
		}
		showDialog("�޸�",800,520,"zxdkTqhk.do?method=xgsq&id="+rows[0]["id"]);
	}
}

//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��ѡ��ļ�¼�Ѿ�������У������ظ��ύ��");
			return false;
		}
		
		jQuery.post("zxdkTqhk.do?method=submit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else {
		jQuery.post("zxdkTqhk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼����ˣ�����ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkTqhk.do?method=delTqhk",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
	}
	
	var map = getSuperSearch();
	map["shzt"] = zt;
	jQuery("#dataTable").reloadGrid(map);
}


function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "zxdkTqhk.do?method=hksh&id="+id;
		showDialog("��ǰ�������",800,500,url);
	}
}

function cancelAuding(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		jQuery.post("zxdkTqhk.do?method=cancelAudit",{id:ids.toString(),gwid:rows[0]["xtgwid"]},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}