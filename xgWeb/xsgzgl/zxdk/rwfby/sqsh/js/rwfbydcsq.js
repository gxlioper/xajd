function bcsq(){
	showDialog("����",680,480,"rwfby_sqsh.do?method=dcsq");
}

function xgsq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��ѡ��ļ�¼��ǰ״̬Ϊ"+rows[0]["shztmc"]+"�������޸ģ�");
			return false;
		}
		showDialog("�޸�",680,480,"rwfby_sqsh.do?method=xgsq&id="+rows[0]["id"]);
	}
}

//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var ksqcs = jQuery("#ksqcs").val();
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼�Ѿ�������У������ظ��ύ��");
		return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{
			"okFun" : function(){
			jQuery.post("rwfby_sqsh.do?method=submit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');}
	});
	}
}

function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]['shzt']!='5'){
		showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
		return false;
	}else {
		showConfirmDivLayer("��ȷ��Ҫȡ����������",{
			"okFun" : function(){
			jQuery.post("rwfby_sqsh.do?method=cancelSubmit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');}
	});
	}
}

function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼��������У�����ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("rwfby_sqsh.do?method=qxsq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var shzt = jQuery("#shzt").val();
	var map = getSuperSearch();
	map["shzt"] = shzt;
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

function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "rwfby_sqsh.do?method=dcsh&id="+id+"&shid="+rows[0]["shid"];
		showDialog("��������",800,500,url);
	}
}
function getword(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
	 } else if (rows.length > 1){
		var ids = jQuery("#dataTable").getSeletIds();
		var url="rwfby_sqsh.do?method=getDjbZip&value="+ids;
		window.open(url);
	 } else {
		var url="rwfby_sqsh.do?method=getDjbWord&id="+rows[0]["id"];
		window.open(url);
    }
}