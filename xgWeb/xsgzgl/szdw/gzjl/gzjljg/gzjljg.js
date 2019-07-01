
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function zghLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='gzjljgCk(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function setBz(cellValue,rowObject){
	if(null==cellValue){
		cellValue="";
	}
	return "<span title='"+rowObject["bz"]+"'>"+cellValue+"</span>";
	}
// ����
function savegzjljg(type) {
	if(checkZdz()){
	var url = "gzjljg.do?method=savegzjljg&type=" + type;
	ajaxSubFormWithFun("GzjljgForm", url, function(data) {
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
}
//����
function add() {
	var url = "gzjljg.do?method=gzjljgZj";
	var title = "������¼��д";
	showDialog(title, 750, 550, url);
}
function checkzgh(){
	if(jQuery("#zgh").val()==""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
}

//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'gzjljg.do?method=gzjljgXg&jgid=' + rows[0]["jgid"]
				+ '&zgh=' + rows[0]["zgh"];
		var title = "������¼�޸�";
		showDialog(title, 750, 550, url);
	}
}
//�鿴
function gzjljgCk(id, zgh) {
	showDialog("������¼�鿴", 750, 550, "gzjljg.do?method=gzjljgCk&jgid="
			+ id + "&zgh=" + zgh);
}
// ɾ��
function del() {
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
			jQuery.post("gzjljg.do?method=delGzjljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "gzjl_gzjljg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, gzjljgExportData);
}

//��������
function gzjljgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gzjljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//������¼ͳ��
function gzjltj(type) {
	kssj=jQuery("#gzsj_kssj").val();
	jssj=jQuery("#gzsj_jssj").val();
	if(null==kssj||""==kssj||null==jssj||""==jssj){
		showAlertDivLayer("��ѡ����ʱ��Σ�");
		return false;
	}
	var url = "gzjljg.do?method=gzjltj&type="+type;
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
/**
 * ��ӡ������¼��
 */
function printGzjlb(url) {

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}


function gzjlb(type){
	kssj=jQuery("#gzsj_kssj").val();
	jssj=jQuery("#gzsj_jssj").val();
	if(null==kssj||""==kssj||null==jssj||""==jssj){
		showAlertDivLayer("��ѡ����ʱ��Σ�");
		return false;
	}
	var url = "gzjljg.do?method=gzjlb&type="+type+"&kssj="+kssj+"&jssj="+jssj;
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}


