//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//д��
function xx(){
	var url = "wdznx.do?method=xx";
	var title = "վ��������";
	showDialog(title, 770, 500, url);
}

//ѧ����Ϣ����
function saveXsXXForm(){
	//���ı��༭������ͬ����nameΪeditorid��textarea�У����ں�̨��ȡ
	editor.sync();
	var ids = "ztlb"+"-"+"xjzt";
	//���ı��༭�������Ƿ�Ϊ�յ���html()�����������
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false ||html==null||html=="" || htmlString == 0){
		showAlert("�뽫��"+"<font color='red'>*</font>"+"��������д������");
		return false;
	}
	if(htmlString > 1000){
		showAlert("���������������1000�ַ�֮�ڣ�");
		return false;
	}
	var url = "wdznx.do?method=saveXX";
	ajaxSubFormWithFun("WdznxForm", url, function(data) {
		 if(data["message"]=="�ż����ͳɹ���"){
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

//��ʦ��Ϣ����
function saveTeaXXForm(){
	//���ı��༭������ͬ����nameΪeditorid��textarea�У����ں�̨��ȡ
	editor.sync();
	var ids = "jsrxm"+"-"+"xjzt";
	//���ı��༭�������Ƿ�Ϊ�յ���html()�����������
	var html=editor.html();
	var htmlString = editor.text().length;
	if(checkNotNull(ids) == false ||html==null||html=="" || htmlString == 0){
		showAlert("�뽫��"+"<font color='red'>*</font>"+"��������д������");
		return false;
	}
	if(htmlString > 1000){
		showAlert("���������������1000�ַ�֮�ڣ�");
		return false;
	}
	var url = "wdznx.do?method=saveXX";
	ajaxSubFormWithFun("WdznxForm", url, function(data) {
		 if(data["message"]=="�ż����ͳɹ���"){
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

//�ռ������鿴���ӣ�δ�鿴�ż����������Ӻ�
function sjxBtLink(cellValue, rowObject) {
	if(rowObject["jsrydbj"] == '0'){
		return "<a href='javascript:void(0);' style='font-weight:bold;' class='name' onclick='sjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["fsrxm"] + "\",\"" + "wd" + "\");'>" + cellValue
		+ "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='sjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["fsrxm"] + "\",\"" + "yd" + "\");'>" + cellValue
		+ "</a>";
	}
	
}

//�鿴����
function sjxBtView(xjbh,jsrbh,fsrxm,type){
	showDialog("վ���Ų鿴", 770, 450, "wdznx.do?method=sjxCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&type="+type+"&fsrxm="+fsrxm);
}

//���������鿴����
function fjxBtLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='fjxBtView(\""
		+ rowObject["xjbh"] + "\",\"" + rowObject["jsrbh"] + "\",\"" + rowObject["jsrxm"] + "\");'>" + cellValue
		+ "</a>";
}

//�鿴����
function fjxBtView(xjbh,jsrbh,jsrxm){
	showDialog("վ���Ų鿴", 770, 450, "wdznx.do?method=fjxCk&xjbh="
			+ xjbh + "&jsrbh=" + jsrbh+"&jsrxm="+jsrxm);
}

//�ռ���ɾ��
function sjxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("wdznx.do?method=delScSjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}
//������ɾ��
function fjxsc(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("wdznx.do?method=delScFjxjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//վ���Żظ�
function znxhf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ظ��ļ�¼��");
		return false;
	} 
	showDialog("վ���Żظ�", 770, 450, "wdznx.do?method=xjHf&xjbh="
			+ rows[0]['xjbh']+"&jsrbh="+rows[0]['jsrbh']+"&fsrxm="+
			rows[0]['fsrxm']+"&jsrydbj="+rows[0]['jsrydbj']);
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
	var url = "wdznx.do?method=savexjHf";
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
	showDialog("վ���Żظ�", 770, 450, "wdznx.do?method=xjHf&xjbh="
			+ xjbh+"&jsrbh="+jsrbh+"&fsrxm="+
			fsrxm+"&jsrydbj=1");
	
	iFClose();
}
