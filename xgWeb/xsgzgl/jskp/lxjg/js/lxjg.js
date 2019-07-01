function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "jskp_lxjg.do?method=addLxjg";
	var title = "������";
	showDialog(title, 770, 552, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly'] == '1'){
			return showAlertDivLayer("��������������ݲ������޸ģ�");
		}
		var url = 'jskp_lxjg.do?method=updateLxjg&xmid=' + rows[0]["xmid"];
		var title = "�������޸�";
		showDialog(title, 770, 552, url);
	}
}

//�������
function saveLxsq(){
	var ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"fzrlxfs"+"-"+"zdls"+"-"+"zdlslxfs"+"-"+"zxf"+"-"+"zdf";
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	if(jQuery("#zdbm").val() == ""){
		return showAlert("ϵͳ�޸�ָ���������ƣ���������д��");
	}
	if(parseInt(jQuery("#zdf").val()) < parseInt(jQuery("#zxf").val())){
		return showAlert("���ֱ��벻��С����С�֣�");
	}
	var url = "jskp_lxjg.do?method=saveForLxjg";
	ajaxSubFormWithFun("LxjgForm", url, function(data) {
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

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		return showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} 
	for ( var i = 0; i < ids.length; i++) {
		if (rows[i]["sjly"] == "1") {
			showAlertDivLayer("������̹����ļ�¼����ɾ����");
			return false;
		}
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("jskp_lxjg.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * �鿴��������
 * @return
 */
function ckLxjg(xmid){
	showDialog("�������鿴", 770, 450, "jskp_lxjg.do?method=ckLxjg&xmid="
			+ xmid );
}

/**
 * ��Ŀ����Link
 * @return
 */
function xmmcLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='ckLxjg(\""
	+ rowObject["xmid"] + "\");'>" + cellValue
	+ "</a>";
}

var DCCLBH = "pjpy_jskp_lxjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, lxjgExportData);
}

//��������
function lxjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "jskp_lxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function rysz(sqid){
	showDialog("��Ա����", 770, 450, "jskp_lxsq.do?method=rysz&sqid="
			+ sqid );
}

/**
 * ��Ա����Link
 * @return
 */
function ryszLink(cellValue, rowObject){
	if(rowObject['xmdl'] == "gdx"){
		return "";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='rysz(\""
		+ rowObject["xmid"] + "\");'>" + "��Ա����"
		+ "</a>";
	}
}

//��������
function jbsz(sqid){
	showDialog("��������", 770, 450, "jskp_lxjg.do?method=jbsz&xmid="
			+ sqid );
}

function jbszLink(cellValue, rowObject){
	if(rowObject['xmdl'] == "zlx"){
		return "";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='jbsz(\""
		+ rowObject["xmid"] + "\");'>" + "��������"
		+ "</a>";
	}
}