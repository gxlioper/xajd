function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//ɾ�������ۺ�ά����Ϣ
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("stglStzhwh.do?method=delStzhwhxx",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "stglStzhwh.do?method=getStcycjList";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, stzhwhExportData);
}

//��������
function stzhwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "stglStzhwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='StjgView(\""
			+ rowObject["stid"] + "\");'>" + cellValue + "</a>";
}

function StjgView(stid) {
	showDialog("������ϸ�鿴", 750, 400, "stglStzhwh.do?method=StzhwhCk&stid="
			+ stid);
}

//���ų�Ա״̬ά��
function stzhwhCyztwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}
	var stid = rows[0]["stid"];
	showDialog("���ų�Ա״̬ά��", 750, 500, "stglStzhwh.do?method=StCyZtwh&stid="
			+ stid);
}

//���ų�Ա״̬ά������
function saveStCyZtwh(){
	var len = jQuery("#tbody_ztwh tr").length;
	if(len == 1){
		showAlert("�������޳�Ա��Ϣ��������ִ�б��������");
		return false;
	}
	var flag = true;
	jQuery("#tbody_ztwh tr").each(function(b){
		if(b != 0){
			var temptnzt=jQuery(this).find("select[name='tnzt']").val();
			if(temptnzt == ''){
				flag = false;
				showAlert("����״̬����Ϊ�գ�");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "stglStzhwh.do?method=saveStCyZtwh"
		ajaxSubFormWithFun("StzhwhForm", url, function(data) {
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

//���ų�Ա�ɼ�����
function stzhwhCycjpd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}else if(rows[0]["bz"] != '1'){
		showAlertDivLayer("�����Ų�����Ч�ʱ���ڣ��޷������ɼ���");
		return false;
	}
	var stid = rows[0]["stid"];
	showDialog("���ų�Ա�ɼ�����", 750, 500, "stglStzhwh.do?method=StCyCjpd&stid="
			+ stid);
}

//���ų�Ա�ɼ���������
function saveStCyCjpd(){
	var len = jQuery("#tbody_ztwh tr").length;
	if(len == 1){
		showAlert("�������޳�Ա��Ϣ��������ִ�б��������");
		return false;
	}
	var flag = true;
	jQuery("#tbody_ztwh tr").each(function(b){
		if(b != 0){
			var tempcjpd=jQuery(this).find("select[name='cjpd']").val();
			if(tempcjpd == ''){
				flag = false;
				showAlert("��Ա�ɼ�����Ϊ�գ�");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "stglStzhwh.do?method=saveStCyCjpd"
		ajaxSubFormWithFun("StzhwhForm", url, function(data) {
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

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CycjView(\""
			+ rowObject["stid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//�鿴ѧ��ajaxurl��ת
function CycjView(id, xh) {
	showDialog("���ų�Ա��ϸ��ѯ", 770, 400, "stglStzhwh.do?method=Cycjck&stid="
			+ id + "&xh=" + xh);
}