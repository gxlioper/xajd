//function searchRs() {
//	var map = {};
//	jQuery("#dataTable").reloadGrid(map);
//}

/**
 * �۲��¼�롢�鿴--��ѯ
 */
function doQuery(){
	var map = {};
	map['fdmc']=jQuery("#fdmc").val();
	jQuery("#dataTable").reloadGrid(map);
}	

//����
function add() {
	var url = "wsbz_dmwh.do?method=addWsbz";
	var title = "����С�ֶ�";
	showDialog(title, 470, 400, url);
}
//ȫ�ֲ�������
function set() {
	var url = "wsbz_dmwh.do?method=setWsbz";
	var title = "ȫ�ֲ�������";
	showDialog(title, 500, 350, url);
}


function setQjcs(){
	var ids = "bmcs-jzts-jzsj";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var bmcs = jQuery("#bmcs").val();
	var jzts = jQuery("#jzts").val();
	var jzsj = jQuery("#jzsj").val();
	var url = "wsbz_dmwh.do?method=savesetQjcs";
	showConfirmDivLayer("��ȷ��Ҫ����ȫ�ֲ�����",{
		"okFun" : function(){
		jQuery.post(url,{
			bmcs:bmcs.toString(),
			jzts:jzts.toString(),
			jzsj:jzsj.toString()
		},function(data){
			showAlertDivLayer(data["message"]);
			return true;
		},'json');
		
	}
});

}
//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'wsbz_dmwh.do?method=updateWsbz&fddm=' + rows[0]["fddm"];
		var title = "�޸Ĵ���";
		showDialog(title, 470, 400, url);
	}
}

//�����޸Ľ������
function saveFormjldm(type){
	var ids = "fdmc"+"-"+"sj"+"-"+"dd"+"-"+"rs";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	 var hdbl=jQuery('input:radio[name="hdpl"]:checked').val();
	 if(!hdbl){
		 showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
	 }
	if(jQuery.trim(jQuery("#fwyq").val()).length > 500 || jQuery.trim(jQuery("#gzzz").val()).length > 500){
		showAlert("����Ҫ��͹���ְ���ܳ���500�֣�");
		return false;
	}
	
	var url = "wsbz_dmwh.do?method=saveWsbz&type=" + type;
	ajaxSubFormWithFun("WsbzDmwhForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='dmView(\"" + rowObject['fddm'] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function dmView(fddm) {
	showDialog("����鿴", 470, 400, "wsbz_dmwh.do?method=ckWsbz&fddm="
			+ fddm );
}
//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("wsbz_dmwh.do?method=delWsbz",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}