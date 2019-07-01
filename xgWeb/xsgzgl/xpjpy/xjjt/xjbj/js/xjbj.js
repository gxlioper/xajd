/**
 * ��ѯ
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function add(){
	var url = "xpjpy_xjbj.do?method=addXjbj";
	var title = "�����Ƚ��༶��Ϣ";
	showDialog(title,600,300,url);
}


/**
 * �޸�
 * @return
 */
function updateXjbj(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpjpy_xjbj.do?method=updateXjbj&guid='+rows[0]["guid"];
		var title = "�޸��Ƚ��༶��Ϣ";
		showDialog(title,600,300,url);
	}
}



/**
 * ����
 * @return
 */
function saveForm(){
	var xn = jQuery("#xn").val();
	var bjmc = jQuery("#bjmc").val();
	var lrsj = jQuery("#lrsj").val();
	var type = jQuery("#type").val();
	var guid = jQuery("#guid").val();
	
	if("" == lrsj){
		showAlert("¼��ʱ�䲻��Ϊ��");
		return false;
	}
	if("" == xn){
		showAlert("ѧ�겻��Ϊ��");
		return false;
	}
	if("" == bjmc){
		showAlert("�Ƚ��༶���Ʋ���Ϊ��");
		return false;
	}
	
	var url = "xpjpy_xjbj.do?method=saveForm&type="+type+"&guid="+guid;
    ajaxSubFormWithFun("xjbjForm",url,function(data){
  	  
  	  if (data["success"] == "false"){
  		  var msg = "�ð༶��"+jQuery.trim(xn)+"ѧ�꣬";
  		  msg += "�ѻ���Ƚ��༶�ƺš�";
  		  showAlert(msg);
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
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpjpy_xjbj.do?method=delXjbj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/* ���� */
function importXjbj(){
	toImportDataNew("IMPORT_XPJPY_XJBJ");
	return false;
}

//����
var DCCLBH='xpjpy_xjbj.do';
function exportConfig(){
	
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_xjbj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
