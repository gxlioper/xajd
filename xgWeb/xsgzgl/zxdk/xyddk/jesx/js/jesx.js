//��ѯ
function searchRs() {
	var map = {};
	map['xlccmc'] = encodeURI(encodeURI(jQuery("#xlccmc").val()));
	gridSetting["params"] = map;
	jQuery("#dataTable").reloadGrid(map);
}

//�س��¼�
function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  searchRs();
	  }else{
		  return false;
	  }
}

//�鿴ѧ������
function xlccLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["xlccdm"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(xlccdm) {
	showDialog("��������ά���鿴", 450, 250, "gjdk_jesx.do?method=jesxCk&xlccdm="
			+ xlccdm);
}

//��������ά��
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gjdk_jesx.do?method=jesxPlwh&values=' + ids.toString();
		var title = "��������ά��";
		showDialog(title, 450, 300, url);
	}
}

//������
function saveRs(){
	var url = "gjdk_jesx.do?method=saveRs";
	//��������
	var rs = true;
	jQuery("[name='jesx']").each(function(i,row){
		if(this.value == null || this.value == ""){
			rs = false;
			return rs;
		}
	})
	if(!rs){
		 showAlert("������޲���Ϊ�գ�");
		 return false;
	}
	ajaxSubFormWithFun("JesxForm", url, function(data) {
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