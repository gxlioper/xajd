//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "gygl_sdftj.do?method=addSdfTj";
	var title = "����ˮ���ά��";
	showDialog(title, 700, 450, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gygl_sdftj.do?method=editSdfTj&id=' + rows[0]["id"];
		var title = "�޸�ˮ���ά��";
		showDialog(title, 700, 450, url);
	}
}

//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gygl_sdftj.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xg_gygl_sdftj.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH,dcExportData);
}

//��������
function dcExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gygl_sdftj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�����޸Ľ������
function saveData(type){
	var ids = "nd"+"-"+"jd"+"-"+"lddm"+"-"+"qsh"+"-"+"sf"+"-"+"df";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "gygl_sdftj.do?method=saveData&type=" + type;
	ajaxSubFormWithFun("sdfTjForm", url, function(data) {
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

//¥���л�
function lddmChange(){
	jQuery("#lddm").change(function(){
		if(this.value == null || this.value == '' ){
			jQuery("#ch").empty();
			jQuery("#qsh").empty();
			return false;
		}
		var data = getData("ld");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#ch").empty();
		jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['ch']+"'>"+dataList[i]['ch']+"</option>";
			}
			jQuery("#ch").append(html);
		}
		
	})
}

//����л�
function chChange(){
    jQuery("#ch").change(function(){
    	if(this.value == null || this.value == '' ){
			jQuery("#qsh").empty();
			return false;
		}
    	var data = getData("cc");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['qsh']+"'>"+dataList[i]['qsh']+"</option>";
			}
			jQuery("#qsh").append(html);
		}
		
	})
}

//��ȡ��������Ҫ������
function getData(type){
	var url = "gygl_sdftj.do?method=changeSelect&type="+type;
	var data = null;
	var para = {lddm:jQuery("#lddm").val(),ch:jQuery("#ch").val()};
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:para,
		async: false,
		success:function(result){
		   data = result;
		}
		
	});
	return data;
}

//�鿴
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = 'gygl_sdftj.do?method=ckSdfTj&id=' + rows[0]["id"];
		var title = "�޸�ˮ���ά��";
		showDialog(title, 700, 450, url);
	}
}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_GYGL_SDFTJ");
	return false;

}
