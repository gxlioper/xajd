//����
function add(){
	var url = "zjsy_sxhb.do?method=addSxhb";
	var title = "����˼��㱨��Ϣ";
	showDialog(title,800,350,url);
}

//�޸�һ����¼
function mod(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'zjsy_sxhb.do?method=modSxhb&sxhbid='+rows[0]["sxhbid"]+'&xh='+rows[0]["xh"];
		var title = "�޸�˼��㱨��Ϣ";
		showDialog(title,800,430,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zjsy_sxhb.do?method=delSxhb",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//����
function saveForm(method,type){
	
	 if (!checkNull("xh-sjfs-sjsj")) {
		 return false;
	 }
	 var url = "zjsy_sxhb.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("zjsySxhbForm",url,function(data){
     	  if (data["success"] == "false"){
    		  showAlert("ѧ����ǰ�׶β����ڻ��ߵ��������Ѵ���" );
    	  }else{
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if(parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
     });
}

//����
function exportConfig(){
	var DCCLBH='zjsy_sxhb.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='zjsy_sxhb.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "zjsy_sxhb.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_N022505_SXHB");
	return false;
}