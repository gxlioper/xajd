var gridSetting = {
	caption:"��ҵ����֧��ά��",
	pager:"pager",
	url:"dtjs_bysdzbwh.do?method=dzbwhList&type=query",
	colList:[
	   {label:'��֧������',name:'dzbdm', index: 'dzbdm',width:'20%',key:true},
	   {label:'��֧������',name:'dzbmc', index: 'dzbmc',width:'80%'}
	],
	sortname: "dzbdm",
 	sortorder: "asc"
}


//����
function add(){
	var url = "dtjs_bysdzbwh.do?method=dzbwhAdd";
	var title = "���ӵ�֧��";
	showDialog(title,380,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'dtjs_bysdzbwh.do?method=dzbwhUpdate&dzbdm='+rows[0]["dzbdm"];
		var title = "�޸ĵ�֧��";
		showDialog(title,380,200,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("dtjs_bysdzbwh.do?method=dzbwhDelete",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//��ѯ
function query(){
	var map = {};
	map["dzbmc"] = jQuery("#dzbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function saveForm(url){
	 if (!checkNull("dzbdm-dzbmc")) {
			 return false;
		}
     ajaxSubFormWithFun("bysdzbwhForm",url,function(data){
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
 * ���뵼��
 */
var DCGLBH = "dtjs_bysdzbwh.do";//dcglbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCGLBH, xshdglExportData);
}

//��������
function xshdglExportData() {
	//setSearchTj();//���ø߼���ѯ����
	var url = "dtjs_bysdzbwh.do?method=dzbwhExport&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
	//url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_BYSDZBWH");
	return false;
}