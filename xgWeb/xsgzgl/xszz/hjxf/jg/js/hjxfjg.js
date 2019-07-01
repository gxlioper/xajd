function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "hjxf_jg.do?method=add";
	var title = "����ѧ�ѽ������";
	showDialog(title, 770, 552, url);
}

//�����޸����뱣��
function saveHjxfJg(type){
	var ids = ""
	if(type == "update"){
	   ids = "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}else{
		ids ="xh"+"-"+ "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}
	
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(jQuery("#jqjzsj").val() > jQuery("#checksj").val() && jQuery.trim(jQuery("#checksj").val()) != ""){
		showAlert("������ֹʱ��"+jQuery("#checksj").val()+"��");
        return false;
	}
	var url = "hjxf_jg.do?method=saveHjxfjg&type=" + type;
	ajaxSubFormWithFun("HjxfJgForm", url, function(data) {
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
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'hjxf_jg.do?method=editHjxf&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "����ѧ�ѽ���޸�";
		showDialog(title, 770, 552, url);
	}
}

//ɾ������
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("hjxf_jg.do?method=delHjxf",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xszz_hjxf_jg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

//��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "hjxf_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='HjxfView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function HjxfView(sqbh, xh) {
	showDialog("����ѧ�ѽ���鿴", 700, 450, "hjxf_jg.do?method=HjxfView&jgid="
			+ sqbh + "&xh=" + xh);
}

//���ܱ���
function exportHjxfhz(){
	var xnobj = jQuery("a[name='tj_xn'][class='selectedValue']");
	var xn = "";
	if(xnobj.length != 1){
	  showAlertDivLayer("��ѡ��һ��ѧ�꣡");
	  return false;
	}else{
		xn = jQuery(xnobj).attr("id").replace("tj_xn_","");
		
	}

    var url = "hjxf_jg.do?method=getHzbexcel&xn="+xn ;
    window.open(url);  
   
}

//�����
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "hjxf_jg.do?method=getHjxfjg";
		url += "&jgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "hjxf_jg.do?method=gettHjxfTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//����
function importConfig(){
	toImportDataNew("IMPORT_HJXF");
	return false;
}
