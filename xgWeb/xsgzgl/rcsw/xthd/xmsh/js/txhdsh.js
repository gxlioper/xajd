var action="rcsw_txhd_sh.do";

//��������ת
function dcmcLink(cellValue, rowObject) {
	var sqid = rowObject["sqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + sqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("�鿴���Ŀ", 800, 500, url);
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "ͨ��";
		break;
	case "2":
		shztmc = "��ͨ��";
		break;
	case "3":
		shztmc = "���˻�";
		break;
	case "5":
		shztmc = "�����";
		break;
	default:
		shztmc = "�����";
		break;
	}
	return mc+"["+shztmc+"]";
}
//�л�������/�Ѵ���ҳ��
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#title").html("��ѧ������б�");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		jQuery("#dataTable").reloadGrid(map);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#title").html("��ѧ������б�");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		jQuery("#dataTable").reloadGrid(map);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//���
function rcsw_txhd_sh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var xh = rows[0]["xh"];
		var url = action + '?method=txhdXmSh&sqid='
		+ rows[0]["sqid"]+"&gwid="+rows[0]["gwid"];
		showDialog("��Ŀ���",800,500,url);
	}
}
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	var text=jQuery("#shjg").find("option:selected").text();
	//�ύ���
	showConfirmDivLayer("��ȷ��<font color='red'>[" + text + "]</font>��������",{"okFun":function(){
			zx(shzt,text);
	}});
	
}
function zx(shzt,text){
	var url = "rcsw_txhd_sh.do?method=txhdXmSh&type=save&tt="+new Date();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				
				showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		showDialog("��������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}
function reloadsh(){
	jQuery("#dataTable").reloadGrid();
}