
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//�ϴ�����ҳ��
function toUpload(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xsxx_xsxxxg.do?method=uploadfjsc&hjid=' + rows[0]["hjid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "�񽱸�����Ϣ�ϴ�";
		showDialog(title, 770, 552, url);
	}
}

function saveUpload(){
	var url = "xsxx_xsxxxg.do?method=uploadfjsc&type=update";
	ajaxSubFormWithFun("xsxxglModel", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewUpload(\""
			+cellValue+ "\",\"" + rowObject["hjid"]+ "\");'>" + cellValue
			+ "</a>";
}

//�鿴
function viewUpload(xh,hjid){
	var url = 'xsxx_xsxxxg.do?method=uploadfjsc&hjid=' + hjid
			+ '&xh=' + xh+"&type=view";
	var title = "�񽱸�����Ϣ�鿴";
	showDialog(title, 770, 552, url);

}