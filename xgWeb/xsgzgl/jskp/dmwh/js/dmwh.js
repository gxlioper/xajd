//��ѯ
function searchRs() {
	var map = {};
	map['xmlbmc'] =jQuery("#xmlbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//�س��¼�
function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  searchRs();
	  }else{
		  return false;
	  }
}

/**
 * �������
 * @return
 */
function add(){
	showDialog("������Ŀ���", 450, 300, "jskp_dmwh.do?method=addDmwh"
	);
}

/**
 * �޸����
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		var url = 'jskp_dmwh.do?method=editDmwh&xmlbdm=' + rows[0]['xmlbdm'];
		var title = "�޸���Ŀ���";
		showDialog(title, 450, 300, url);
	}
}

/**
 * ����
 * @return
 */
function saveXmlbmc(){
	var url = "jskp_dmwh.do?method=saveDmwh";
	var xmlbmc = jQuery("#xmlbmc").val();
	//��������
	if(xmlbmc == null || jQuery.trim(xmlbmc) == ''){
		 showAlert("��Ŀ������Ʋ���Ϊ�գ�");
		 return false;
	}
	ajaxSubFormWithFun("DmwhForm", url, function(data) {
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

/**
 * ɾ��
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jskp_dmwh.do?method=delXm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}