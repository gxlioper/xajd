function add(){
	showDialog("�װ๤��վ��Ա����",650,430,"ybgzzSqsh.do?method=add");
}

function xgsq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		
		showDialog("�޸������",650,430,"ybgzzSqsh.do?method=edit&id="+rows[0]["id"]);
	}
}

//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("ybgzzSqsh.do?method=submit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (rows[0]["shzt"] != "5"){
		showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("ybgzzSqsh.do?method=cancelSubmit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("ybgzzSqsh.do?method=cancel",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var map = getSuperSearch();
	map["shzt"]=jQuery("#shzt").val();
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
	} else if (rows[0]["shzt"] == "0"){
		showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
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
		var url = "ybgzzSqsh.do?method=jssh&id="+id;
		showDialog("�װ๤��վ��Ա���",800,520,url);
	}
}

