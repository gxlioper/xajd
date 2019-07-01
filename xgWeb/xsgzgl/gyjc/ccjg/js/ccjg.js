function searchRs() {
	var map = getSuperSearch();
	map["flag"] = jQuery("#flag").val();
	jQuery("#dataTable").reloadGrid(map);
}



/**
 * ����
 * @return
 */
function addCcjg(){
	var url = "gyjc_ccjgcx.do?method=addCcjg";
	showDialog("���ӳ����", 770, 550, url);
}

function showQsxxNotF5CallBack(rowData) {
	jQuery("#xydm").val(rowData["xydm"]);
	jQuery("#lddm").val(rowData["lddm"]);
	jQuery("#qsh").val(rowData["qsh"]);
	jQuery("#ldmc").html(rowData["ldmc"]);
	jQuery("#qshTd").html(rowData["qsh"]);
	jQuery("#ch").html(rowData["lddm"]);
	jQuery("#xymc").html(rowData["xymc"]);
	jQuery(".jcxmTr").remove();
}

//����
function saveCcjg() {
	var qsh =jQuery("#qsh").val();
	var jcrq = jQuery("#jcrq").val();
	if(null==qsh || ''==qsh){
		showAlert("����ѡ�����ң�");
		return false;
	}
	if(null==jcrq || ''==jcrq){
		showAlert("��ѡ�������ڣ�");
		return false;
	}
	var objArr= [];
	jQuery.each(jQuery(".pfidSel"),function(i,n){
			var pfid = jQuery(n).find("option:selected").val();
			objArr.push(pfid);
	});
	/*
	if(objArr.length!=0&&jQuery(".MultiFile-label").length==0){
		showAlert("����Ӹ�����");
		return false;
	}*/
	jQuery("#pfid").val(objArr.toString());
	var url = "gyjc_ccjgcx.do?method=saveCcjg";
	ajaxSubFormWithFun("ccjgForm", url, function(data) {
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
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gyjc_ccjgcx.do?method=editCcjg&guid=' + rows[0]["guid"];
		showDialog("������޸�", 770, 500, url);
	}
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyjc_ccjgcx.do?method=delCcjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�鿴
function viewCcjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = 'gyjc_ccjgcx.do?method=viewCcjg&guid=' + rows[0]["guid"];
		showDialog("������鿴", 770, 500, url);
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('gyjc_ccjgcx.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyjc_ccjgcx.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
