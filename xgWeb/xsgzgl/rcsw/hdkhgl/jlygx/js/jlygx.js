
/**
 * ��ѯ
 * @return
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}



//ѧ�������ļ�¼�����
function XsJlGxWh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]["sfcj"] == '��' || rows[0]["sfcj"] == '' || rows[0]["sfcj"] == null ){
		showAlertDivLayer("��ѡ��һ�����Ѳμӵļ�¼��");
		return false;
	}
	else {
		var url = 'hdkhgl_jlygx.do?method=XsJlGxWh&hdjgid=' + rows[0]["hdjgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��¼�����";
		showDialog(title, 500, 200, url);
	}
}

//�����ļ�¼�����
function saveXsjlgx(){
	var obj = jQuery("#jlygx");
	if(jQuery(obj).val() == ''){
		showAlert("��¼����벻��Ϊ�գ�");
		return false;
	}else if(checkzsonsubmit(obj) == false){
		return false;
	}
	var url = "hdkhgl_jlygx.do?method=saveXsjlgx";
	ajaxSubFormWithFun("HdkhglForm", url, function(data) {
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

//��֤����
function checkzsonsubmit(obj){
	if(jQuery(obj).val().length > 500){
		showAlert("�������Ϊ500�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

function xhLink(cellValue, rowObject) {
	 if(rowObject["sfcj"] == '��' || rowObject["sfcj"] == '' || rowObject["sfcj"] == null ){
		 return cellValue;
	 }else{
		 return "<a href='javascript:void(0);' class='name' onclick='JlgxView(\""
			+ rowObject["hdjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
	 }
	 
}

//�鿴ѧ��ajaxurl��ת
function JlgxView(hdjgid,xh) {
	//����ǵ�����Ӳ�ѯ��˵������ǰ�ť��xh��ȻΪ�գ�����ȡֵ
	if(!xh){
		var rows = jQuery("#dataTable").getSeletRow();
		hdjgid = rows[0]['hdjgid'];
		xh = rows[0]['xh'];
	}
	showDialog("��¼�����鿴", 700, 490, "hdkhgl_jlygx.do?method=XsJlGxWhview&hdjgid="
			+ hdjgid + "&xh=" + xh);
}