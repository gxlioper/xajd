//��ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function add(){
	var url = "xpjpy_wmqs.do?method=addWmqs";
	var title = "��������������Ϣ";
	showDialog(title,575,253,url);
}
//�޸�һ����¼
function updateWmqs (){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpjpy_wmqs.do?method=updateWmqs&guid='+rows[0]["guid"];
		var title = "�޸�����������Ϣ";
		showDialog(title,575,253,url);
	}
}


//����
function saveForm(){
	var xn = jQuery("#xn").val();
	var xqmc = jQuery("#xqmc").val();
	var ldmc = jQuery("#ldmc").val();
	var qsh = jQuery("#qsh").val();
	var type = jQuery("#type").val();
	var guid = jQuery("#guid").val();
	if("" == xqmc){
		showAlert("У������Ϊ��");
		return false;
	}
	if("" == xn){
		showAlert("ѧ�겻��Ϊ��");
		return false;
	}
	if("" == qsh){
		showAlert("���Һ�Ϊ��");
		return false;
	}
	if("" == ldmc){
		showAlert("¥������Ϊ�ղ���Ϊ��");
		return false;
	}
	 var url = "xpjpy_wmqs.do?method=saveForm&type="+type+"&guid="+guid;
    ajaxSubFormWithFun("wmqsForm",url,function(data){
  	  
  	  if (data["success"] == "false"){
  		  if(data["message"] == "0"){
  			showAlert("����д��ȷ��¥����");
  		  }else{
	  		  var msg = "�ð༶��"+jQuery.trim(xn)+"ѧ�꣬";
	  		  msg += "�ѻ���������ҳƺš�";
	  		  showAlert(msg);
	  	  }
  		  
  	  } else {
  		  showAlert(data["message"],{},{"clkFun":function(){
      			if (parent.window){
   				 refershParent();
      			}
		  }});
  	  }
    });
}
//ɾ����¼
function delWmqs(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpjpy_wmqs.do?method=delWmqs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/* ���� */
function importWmqs(){
	toImportDataNew("IMPORT_XPJPY_WMQS");
	return false;
}

//����
var DCCLBH='xpjpy_wmqs.do';
function exportConfig(){
	
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_wmqs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function changeXmmc(obj){
	var objvalue = obj.value;
	alert(objvalue);
}