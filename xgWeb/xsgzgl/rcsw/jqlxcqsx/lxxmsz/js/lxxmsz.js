//��ѯ
function searchRs() {
	var map = {};
	map['xmmc'] = encodeURI(encodeURI(jQuery("#xmmc").val()));
	gridSetting["params"] = map;
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

//�鿴ѧ������
function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["xmid"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(xmid) {
	showDialog("��У��Ŀ���ò鿴", 450, 300, "jqlx_lxxmsz.do?method=ckLxxmsz&xmid="
			+ xmid);
}

function add(){
	showDialog("������У��Ŀ����", 450, 300, "jqlx_lxxmsz.do?method=addLxxmsz"
			);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		var url = 'jqlx_lxxmsz.do?method=updateLxxmsz&xmid=' + rows[0]['xmid'];
		var title = "�޸���У��Ŀ����";
		showDialog(title, 450, 300, url);
	}
}

//������
function saveSzjg(type){
	var url = "jqlx_lxxmsz.do?method=saveSzjg&type="+type;
	//��������
	if(!checkNotNull("xmmc-lxkssj-lxxmsm")){
		 showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		 return false;
	}
	if(jQuery("#lxxmsm").val().length > 500){
		 showAlert("��У��Ŀ˵�����ܴ���500�֣�");
		 return false;
	}
	ajaxSubFormWithFun("LxxmszForm", url, function(data) {
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

//ɾ�����ý��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("jqlx_lxxmsz.do?method=delSzjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_rcsw_cqsx_jqlx_lxxmsz.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	var url = "jqlx_lxxmsz.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


