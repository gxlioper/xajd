function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	//autoTitleForGrid();
	//setTitle();
}

function addBx(){
	showDialog('����',750,450,'rcswBxglXsbxbx.do?method=addBx');;
}

//������ʽ��֤
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
//���ӱ���
function saveForm(){
	var url = "rcswBxglXsbxbx.do?method=addBx&type=save";
				
	if (!zdybdCheck()) {
		return;
	}
	
	if(jQuery("#xxdm").val()=="13871"&&jQuery("#filepath").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	if(jQuery("#xxdm").val() == "13871"){
		if(parseFloat(jQuery("#bxje").val()) == 0){
			return showAlert("�������������0��");
		}
	}
	
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
				
	ajaxSubFormWithFun("demoForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["message"]!="���ﱨ�������������") {
				refershParent();
			}
		}});
	});
}
//ɾ��
function delBx(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
			jQuery.post("rcswBxglXsbxbx.do?method=delBx",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//�޸�
function updateBx(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'rcswBxglXsbxbx.do?method=updateBx&bxid='+rows[0]["bxid"]+'&xh='+rows[0]["xh"];
		
		showDialog('�޸�', 750, 450, url);
	}
	
}
//�޸ı���
function saveFormU(){
	var url = "rcswBxglXsbxbx.do?method=updateBx&type=save";
				
	if (!zdybdCheck()) {
		return;
	}
	
	if(jQuery("#xxdm").val()=="13871"&&jQuery("#filepath").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	if(jQuery("#xxdm").val() == "13871"){
		if(parseFloat(jQuery("#bxje").val()) == 0){
			return showAlert("�������������0��");
		}
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
				
	ajaxSubFormWithFun("demoForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["message"]!="���ﱨ�������������") {
				refershParent();
			}
		}});
	});
}

//ѧ�����Ӳ鿴
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckBx(\""+rowObject["bxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function ckBx(bxid,cellValue){
	
	showDialog("�鿴",750,375,'rcswBxglXsbxbx.do?method=ckBx&bxid='+bxid+"&xh="+cellValue,null);

}

function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("rcsw_bxgl_xsbxbx.do", bxbxExportData);
}

//����
function bxbxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcswBxglXsbxbx.do?method=bxbxExportData&dcclbh=" + "rcsw_bxgl_xsbxbx.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importBx() {
	toImportData("IMPORT_N732502");
	return false;
}

//���ձ�����ӡ
function printBxbxZm() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	} else if (rows.length > 1) {
		var url="rcswBxglXsbxbx.do?method=getBxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	} else {
		var bxid = rows[0]["bxid"];
		var url ="rcswBxglXsbxbx.do?method=getBxzm&bxid="+bxid;
		window.open(url);
	}
}