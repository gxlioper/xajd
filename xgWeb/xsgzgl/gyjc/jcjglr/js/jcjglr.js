function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �����Link
 * @return
 */
function jcxLink(cellValue, rowObject){
	var jcxmc = "";
	if(rowObject['wsjc'] == "1"){
		jcxmc +="������顢";
	}
	if(rowObject['aqjc'] == "2"){
		jcxmc +="��ȫ��顢";
	}
	if(rowObject['jljc'] == "3"){
		jcxmc +="���ɼ�顢";
	}
	return "<font color='blue'>"+jcxmc.substring(0,jcxmc.length-1)+"</font>";
}

/**
 * ���
 * @return
 */
function jc(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("��ѡ��һ����¼��");
	}
	if(rows[0]["jzrq"] < jQuery("#today").val() || rows[0]['ccrq'] > jQuery("#today").val()){
		return showAlertDivLayer("���ڼ���ճ�ά��ʱ�������ڣ�");
	}
	document.location.href = "gyjc_jcjglr.do?method=getJcjgLrcxList&rcid="+rows[0]['guid']+"&flag=lr&js="+rows[0]['js'];
}

/**
 * ����¼����
 * @return
 */
function saveLrjg(type){
	var url = "gyjc_jcjglr.do?method=saveLrjg&tjzt=" + type;
	ajaxSubFormWithFun("JcjglrForm", url, function(data) {
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

//�ύ��¼���鿴
function tjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"ytjscxsub"+ "\",\""+"1"+ "\");'>" + cellValue
			+ "</a>";
}

//δ�ύ��¼���鿴
function wtjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"wtjscxsub"+ "\",\""+"0"+ "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(rcid,flag,tjzt) {
	showDialog("�鿴", 900, 550, "gyjc_jcjglr.do?method=getJcjgLrcxList&username=isnotnull&rcid="+rcid+"&flag="+flag+"&tjzt="+tjzt);
}

