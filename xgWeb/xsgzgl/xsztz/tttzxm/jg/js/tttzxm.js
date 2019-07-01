function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "ttxm_jg.do?method=add";
	var title = "������չ��Ŀ���";
	showDialog(title, 770, 550, url);
}


//�����޸�����������
function saveTtxmSq(type){
	var ids = "xmmc"+"-"+"tdmc"+"-"+"sqly"+"-"+"dzxh";
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(jQuery("#usertype").val() != 'stu'){
		if(!checkDzIsSelect()){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
		}
	}
	if(!checkContentIsNull()){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "ttxm_jg.do?method=saveTtsq&type=" + type;
	ajaxSubFormWithFun("TttzxmJgForm", url, function(data) {
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


//�鿴�Ŷ�����
function ttsqLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TtsqView(\""
			+ rowObject["ttjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function TtsqView(ttjgid, tdmc) {
	showDialog("������չ��Ŀ����鿴", 770, 450, "ttxm_jg.do?method=TtsqView&ttjgid="
			+ ttjgid);
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  
	var flag = true;
	for(var i=0;i<rows.length;i++){
		if(rows[i]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼����ɾ����");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("��Ŀѧ�����϶�������ɾ����");
			return false;
		}
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("ttxm_jg.do?method=delTtsq",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}



//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("������̹��������ݲ����޸ģ�");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("����Ŀѧ�����϶��������޸ģ�");
			return false;
		}
		var url = 'ttxm_jg.do?method=editTtsq&ttjgid=' + rows[0]["ttjgid"];
		var title = "������չ��Ŀ���";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "sztz_ttxm_jg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ttsqExportData);
}

//��������
function ttsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ttxm_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



