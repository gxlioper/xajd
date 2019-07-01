

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""
			+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴
function KqjgView(jgid, bjdm) {
	showDialog(jQuery("#gnmkmc").val()+"�鿴", 750, 550, "zwzxkqKqjg.do?method=viewKqjg&jgid="
			+ jgid + "&bjdm=" + bjdm);
}
/**
 *ȱ��ѧ����Ϣ�鿴
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='qqxsView(\""
			+ rowObject["id"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴
function qqxsView(id, xh) {
	showDialog(jQuery("#gnmkmc").val()+"�鿴", 600, 350, "zwzxkqKqjg.do?method=qqxsView&sqid="
			+ id + "&xh=" +xh);
}

// ����
function saveKqjg(type) {
	var flg=true;
	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
		if (flg){
			var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
			var xh= jQuery(n).find("td").eq(1).text();
			var kkjs = jQuery(n).find("input[name=kkjs]").val();
			var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
			obj.xh=xh;
			obj.kkjs=kkjs;
			obj.qqlxdm=qqlx;
			obj.ylzd1=ylzd1;
			objArr.push(obj);
			flg = (qqlx != "" );
		}
	});
	var validateFlag = true;
	validateFlag = checkKqxx(flg,null);
	if(validateFlag){
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zwzxkqKqjg.do?method=saveKqjg&type="+type;
	ajaxSubFormWithFun("ZwzxKqjgForm", url, function(data) {
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
	var url = "zwzxkqKqjg.do?method=addKqjg";
	var title = jQuery("#gnmkmc").val()+"����";
	showDialog(title, 800, 500, url);
}
function checkXh(){
	if(jQuery("#xh").val()==""){
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
		var url = 'zwzxkqKqjg.do?method=editKqjg&jgid=' + rows[0]["jgid"];
		var title = jQuery("#gnmkmc").val()+"�޸�";
		showDialog(title, 800, 500, url);
	}
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
			jQuery.post("zwzxkqKqjg.do?method=delKqjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "rcsw_zwzxkq_kqjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, zwzxkqKqjgExportData);
}

//��������
function zwzxkqKqjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zwzxkqKqjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ȱ��ѧ������ ����
var qqxsDCCLBH = "rcsw_zwzxkq_xskqxx.do";
function qqxsExportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(qqxsDCCLBH, qqxsExportData);
}

//��������
function qqxsExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zwzxkqKqjg.do?method=qqxsExportData&dcclbh=" + qqxsDCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//ȱ��ѧ������
function qqxsDr(){
	toImportData("IMPORT_QGZX_QQXS");
	return false;
}

function kqxxTb() {
	var ids = jQuery("#dataTable").getSeletIds();
		showConfirmDivLayer("��ȷ��Ҫͬ��ȱ��������Ϣ��",{"okFun":function(){
			jQuery.post("zwzxkqKqjg.do?method=qqxsxxTb",{},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
}