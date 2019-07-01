var DCCLBH = "szdw_bfjs_bfjsglwh.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, bfjsExportData);
}

//��������
function bfjsExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "bfjsgl_bfjsglwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//��֤���������Ƿ����
function checkSelects(){
	var selects = jQuery("select[name='qqlxdm']");
	var flg = true;
	var nums = 0;
	jQuery.each(selects,function(i,n){
		var options = jQuery(n).find("option");
		nums = 0;
		for(j=0;j<options.length;j++){
			if(jQuery(options[j]).prop("selected") == true){
				nums++;
				if(jQuery(options[j]).val() == '' || jQuery(options[j]).val() == null){
					flg = false;
					break;
				}
			}
			if(nums>0){
				break;
			}
		}
		if(nums < 1){
			flg = false;
		}
		if(!flg){
			return false;
		}
	});
	if(!flg){
		return false;
	}else{
		return true;
	}		
}

//���ӱ���
function addSave() {
	var rq = jQuery("#jcrq").val();
	if(rq == null || rq == ''){
		showAlert("��ѡ��������");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("��ѡ��༶");
		return false;
	}
	
	if(!checkSelects()){
		showAlert("����д��������");
		return false;
	}
	
	var url = "bfjsgl_bfjsglwh.do?method=addSave";
	ajaxSubFormWithFun("bfjsglForm", url, function(data) {
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

function updateSave() {
	if(!checkSelects()){
		showAlert("����д��������");
		return false;
	}
	var url = "bfjsgl_bfjsglwh.do?method=updateSave";
	ajaxSubFormWithFun("bfjsglForm", url, function(data) {
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

function showView() {
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlert("����ѡ��һ����¼");
		return false;
	}else{
		var url = 'bfjsgl_bfjsglwh.do?method=viewBfjs&jcid=' + rows[0]["jcid"];
		var title = "��翼�ڲ鿴";
		showDialog(title, 800, 550, url);
	}
	
}

function showBj(){
	var sj = jQuery("#jcrq").val();
	if(sj == null || sj == ''){
		showAlert("����ѡ��������");
		return false;
	}else{
		showDialog('��ѡ��һ���༶',800,500,'bfjsgl_bfjsglwh.do?method=bjManage&jcrq='+sj);
	}
}

function add() {
	var url = "bfjsgl_bfjsglwh.do?method=addBfjs";
	var title = "��翼������";
	showDialog(title, 800, 550, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'bfjsgl_bfjsglwh.do?method=updateBfjs&jcid=' + rows[0]["jcid"];
		var title = "��翼���޸�";
		showDialog(title, 800, 550, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("bfjsgl_bfjsglwh.do?method=delBfjs", {
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

function showxmxx(obj,jclx){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#"+jclx+"xx").toggle();
}
