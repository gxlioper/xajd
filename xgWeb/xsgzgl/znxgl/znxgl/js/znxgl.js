//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����鿴���ӣ�δ�鿴�ż����������Ӻ�
function btLink(cellValue, rowObject) {
	if(rowObject["jsrydbj"] == '0'){
		return "<a href='javascript:void(0);' style='font-weight:bold;' class='name' onclick='btView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + "wd" + "\");'>" + cellValue
		+ "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='btView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + "yd" + "\");'>" + cellValue
		+ "</a>";
	}
	
}

//�鿴����
function btView(xjbh,jsrbh,type){
	showDialog("վ���Ų鿴", 770, 450, "znxgl.do?method=glyxjCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&type="+type);
}

//�ż�ɾ��
function znxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("znxgl.do?method=delScSjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//վ���ŷ���ҳ��
function znxfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ����ļ�¼��");
		return false;
	} 
	showDialog("վ���ŷ���", 770, 450, "znxgl.do?method=XjFp&xjbh="
			+ rows[0]['xjbh'] );
	
}

//վ���ŷ���ҳ�汣��
function saveXjForm(){
	var ids = "jsrxm"+"-"+"xjzt";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��"+"<font color='red'>*</font>"+"��������д������");
		return false;
	}
	var url = "znxgl.do?method=saveXjFp";
	ajaxSubFormWithFun("ZnxglForm", url, function(data) {
		 if(data["message"]=="�ż�����ɹ���"){
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

//վ���Żظ�
function znxhf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ظ��ļ�¼��");
		return false;
	} 
	showDialog("վ���Żظ�", 770, 450, "znxgl.do?method=xjHf&xjbh="
			+ rows[0]['xjbh']+"&jsrydbj="+rows[0]['jsrydbj'] );
}

//վ���Żظ�����
function saveZnxhf(){
	//���ı��༭������ͬ����nameΪeditorid��textarea�У����ں�̨��ȡ
	editor.sync();
	var ids = "xjzt";
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false||html==null||jQuery.trim(html)=="" || htmlString == 0){
		showAlert("�ż�����ͷ������ݲ���Ϊ�գ�");
		return false;
	}
	if(htmlString > 1000){
		showAlert("���������������1000�ַ�֮�ڣ�");
		return false;
	}
	var url = "znxgl.do?method=savexjHf";
	ajaxSubFormWithFun("ZnxglForm", url, function(data) {
		 if(data["message"]=="�ż��ظ��ɹ���"){
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

//�鿴ҳ��ظ���ť 
function zjhf(){
	var fsrxm = jQuery("#fsrxm").val();
	var jsrbh = jQuery("#jsrbh").val();
	var xjbh = jQuery("#xjbh").val();
	//�ȵ�������ִ�йر�
	showDialog("վ���Żظ�", 770, 450, "znxgl.do?method=xjHf&xjbh="
			+ xjbh+"&jsrydbj=1" );
	iFClose();
}
