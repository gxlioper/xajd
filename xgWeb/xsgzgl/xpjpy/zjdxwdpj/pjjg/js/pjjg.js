//��ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpjpy_pjjg.do?method=addPjjg";
	var title = "�������������Ϣ";
	showDialog(title,800,520,url);
}

//���ӱ���
function saveFormAdd(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var wysp = jQuery("#wysp").val();
	var ssdh = jQuery("#ssdh").val();
	if( (xh == null || jQuery.trim(xh) == '')){
		showAlert("��ѡ��ѧ����");
		return false;
	}
	if(xn == null || xn == ''){
		showAlert("��ѡ��ѧ�꣡");
		return false;
	}
	if(lxdm == null || lxdm == ''){
		showAlert("��ѡ����Ŀ���ͣ�");
		return false;
	}
	if(xzdm == null || xzdm == ''){
		showAlert("��ѡ����Ŀ���ʣ�");
		return false;
	}
	if(xmmc == null || xmmc == ''){
		showAlert("����д��Ŀ���ƣ�");
		return false;
	}
	if(sqsj == null || sqsj == ''){
		showAlert("����д����ʱ�䣡");
		return false;
	}
	if( (wysp == null || jQuery.trim(wysp) == '')){
		showAlert("����д����ˮƽ��");
		return false;
	}
	var url = "xpjpy_pjjg.do?method=saveFormAdd";
	ajaxSubFormWithFun("pjjgForm", url, function(data) {
  	  if (data["success"] == "false"){
  		  var msg = "��ѧ���ڡ�"+jQuery.trim(xn)+"��ѧ�꣬";
  		  msg += "�ѻ�á�"+jQuery.trim(xmmc)+"����";
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

//ɾ��������¼
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpjpy_pjjg.do?method=delPjjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ��������ѧ��
 */
function scyxxs() {
    jQuery.post("xpjpy_pjjg.do?method=scyxxs",function(data){
        showAlertDivLayer(data["message"]);
        jQuery("#dataTable").reloadGrid();
    },'json');
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
	var url = 'xpjpy_pjjg.do?method=updatePjjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
	var title = "�޸�������Ϣ";
	showDialog(title,800,520,url);
	}
}

//�޸ı���
function saveFormUpdate(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var wysp = jQuery("#wysp").val();
	var ssdh = jQuery("#ssdh").val();
	if( (xh == null || jQuery.trim(xh) == '')){
		showAlert("��ѡ��ѧ����");
		return false;
	}
	if(xn == null || xn == ''){
		showAlert("��ѡ��ѧ�꣡");
		return false;
	}
	if(lxdm == null || lxdm == ''){
		showAlert("��ѡ����Ŀ���ͣ�");
		return false;
	}
	if(xzdm == null || xzdm == ''){
		showAlert("��ѡ����Ŀ���ʣ�");
		return false;
	}
	if(xmmc == null || xmmc == ''){
		showAlert("����д��Ŀ���ƣ�");
		return false;
	}
	if(sqsj == null || sqsj == ''){
		showAlert("����д����ʱ�䣡");
		return false;
	}
	if( (wysp == null || jQuery.trim(wysp) == '')){
		showAlert("����д����ˮƽ��");
		return false;
	}
	if( (ssdh == null || jQuery.trim(ssdh) == '')){
		showAlert("����д����绰��");
		return false;
	}
	var url = "xpjpy_pjjg.do?method=saveFormUpdate";
	ajaxSubFormWithFun("pjjgForm", url, function(data) {
  	  if (data["success"] == "false"){
  		  var msg = "��ѧ���ڡ�"+jQuery.trim(xn)+"��ѧ�꣬";
  		  msg += "�ѻ�á�"+jQuery.trim(xmmc)+"����";
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

//ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewPjxxxx(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//���ѧ�Ų鿴����
function viewPjxxxx(id,xh) {
	showDialog("�鿴������Ϣ", 800, 500, "xpjpy_pjjg.do?method=viewPjjg&id="+id+"&xh="+xh);
}

//dcclbh,�������ܱ��
var DCCLBH='xpjpy_wdpj_pjjg.do';
//����
function exportConfig(){
	customExport(DCCLBH, exportData);
}
//��������
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_pjjg.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importPjjg(){
	toImportDataNew("IMPORT_N520303_XMJG");
	return false;
}