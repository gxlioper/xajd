var DCCLBH = "pjpy_cpfwh.do";//dcclbh,�������ܱ��

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCpfwh(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function viewCpfwh(id) {
	showDialog("�鿴", 800, 500, "cpfwh_sq.do?method=viewCpfwh&id="+id);
}

function add() {
	var url = "cpfwh_sq.do?method=addCpfWh";
	var title = "����";
	showDialog(title, 800, 460, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'cpfwh_sq.do?method=editCpfwh&id=' + rows[0]["id"];
		var title = "�޸�";
		showDialog(title, 800, 460, url);
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
				jQuery.post("cpfwh_sq.do?method=delCpfwh", {
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

function saveSqjg(type){
	var ids = null;
	if(type=='add'){
		ids = "xh-fs";
	}else{
		ids = "fs";
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "cpfwh_sq.do?method=saveSqjg&type=" +type;
	ajaxSubFormWithFun("cpfwhForm", url, function(data) {
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

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, cpfwhExportData);
}

//��������
function cpfwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "cpfwh_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function downloadTemplate(){
	var url = "cpfwh_sq.do?method=downloadTemplate";
	//url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	
	jQuery("#cpfwhForm").attr("target","_blank");
	jQuery("#cpfwhForm").attr("action",url);
	jQuery("#cpfwhForm").submit();

}

function dr(){
	toImportDataNew("IMPORT_CPFWH");
	return false;
}

function uploadCpfwh(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
		return false;
	}
	
	url = addSuperSearchParams("cpfwh_sq.do?method=importCpfwh",map);// ���ø߼���ѯ����
	jQuery("#cpfwhForm").attr("target","");
	jQuery("#cpfwhForm").attr("action",url).submit();
}

function toImport(){
	//var id = jQuery("#id").val();
	//var jsonStr = jQuery("#jsonStr").val();
	//var map = JSON.parse(jsonStr);
	//map['num']=num; //���ص��������
	
	//���⵼�����ݳ��ڴ棬������������
	//jQuery.ajaxSetup({async:false});
//	jQuery.post("xpj_zcfs.do?method=checkDownload&id="+id,map,function(data){
//		if (data == "true"){
//			showDialog("�۲�ֵ���",550,250,"xpj_zcfs.do?method=toImportZcfs&id="+id+"&jsonStr="+jsonStr,{close:function(){
//				if (jQuery("#search_go")){
//					jQuery("#search_go").click();
//				}
//			}});
//		} else {
//			showAlertDivLayer("�������ݹ��࣬����<font color='red'>"+num+"</font>���������Ӳ�ѯ��������е�����");						
//		}
//	});
//	jQuery.ajaxSetup({async:true});
	
	showDialog("�۲�ֵ���",550,250,"cpfwh_sq.do?method=toImportCpfwh",{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
}
