/**
 * @����Դ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "gyglnew_gyjldmgl.do?method=addZjlydm";
	var title = "���Ӵ���";
	showDialog(title, 470, 200, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gyglnew_gyjldmgl.do?method=updateZjlydm&gyjllbdldm=' + rows[0]["gyjllbdldm"];
		var title = "�޸Ĵ���";
		showDialog(title, 470, 220, url);
	}
}

//�����޸Ľ������
function saveFormjldm(type){
	var ids = "gyjllbdldm"+"-"+"gyjllbdlmc"+"-"+"lb"+"-"+"fz";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "gyglnew_gyjldmgl.do?method=saveZjlydm&type=" + type;
	ajaxSubFormWithFun("GyjldmglForm", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dmView(\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function dmView(gyjllbdldm) {
	showDialog("����鿴", 470, 220, "gyglnew_gyjldmgl.do?method=ckZjlydm&gyjllbdldm="
			+ gyjllbdldm );
}
//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyglnew_gyjldmgl.do?method=delZjlydm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}