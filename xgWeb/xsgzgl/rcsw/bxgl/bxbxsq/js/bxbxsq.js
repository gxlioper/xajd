function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);

}

function bxbxsqZj(){
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
	showDialog('���ձ�������',750,450,'rcswBxglBxbxsq.do?method=bxbxsqZj');;
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
function saveForm(type){
	var xh=jQuery("#xh").val();
	if(null==xh||""==xh){
		return showAlert("�뽫��*����Ŀ��д����");
	}
	if(jQuery("#xxdm").val()=="13871"&&jQuery("#filepath").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "rcswBxglBxbxsq.do?method=bxbxsqBc&type="+type;
	
	if (!zdybdCheck()) {
		return;
	}
	if(jQuery("#xxdm").val() == "13871"){
		if(parseFloat(jQuery("#bxje").val()) == 0){
			return showAlert("�������������0��");
		}
	}
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	ajaxSubFormWithFun("BxbxSqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["success"]!="false") {
				refershParent();
			}
		}});
	});
}
//ɾ��
function bxbxsqDel(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for (var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
				showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
			jQuery.post("rcswBxglBxbxsq.do?method=bxbxsqDel",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//�޸�
function bxbxsqXg(){
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		} else {
		var url = 'rcswBxglBxbxsq.do?method=bxbxsqXg&sqid='+rows[0]["sqid"]+"&xh="+rows[0]["xh"];
		
		showDialog('���ձ��������޸�', 750, 450, url);
	}
	}
	
}
//�޸ı���
function saveUpdateForm(type){
	var url = "rcswBxglBxbxsq.do?method=bxbxsqXgBc&type="+type;
	
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
				
	ajaxSubFormWithFun("BxbxSqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["success"]!="false") {
				refershParent();
			}
		}});
	});
}

//�ύ
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcswBxglBxbxsq.do?method=submitBxbxsq", {
					values : ids.toString(),
					type:"submit",
					bxje:rows[0]['bxje'],
					csfysj:rows[0]['csfysj'],
					xh:rows[0]['xh'],
					lx:rows[0]['lx'],
					bxxz:rows[0]['bxxzmc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	

}
// ����
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcswBxglBxbxsq.do?method=cancelBxbxsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("���ձ��������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


//ѧ�����Ӳ鿴
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='bxbxsqCk(\""+rowObject["sqid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function bxbxsqCk(sqid,cellValue){
	
	showDialog("���ձ�������鿴",750,450,'rcswBxglBxbxsq.do?method=bxbxsqCk&sqid='+sqid+"&xh="+cellValue,null);

}

function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("rcsw_bxgl_bxbxsq.do", bxbxExportData);
}

//����
function bxbxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcswBxglBxbxsq.do?method=bxbxExportData&dcclbh=" + "rcsw_bxgl_bxbxsq.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//���ձ�����ӡ
function printBxbxZm() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	} else if (rows.length > 1) {
		var url="rcswBxglBxbxsq.do?method=getBxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	} else {
		var sqid = rows[0]["sqid"];
		var url ="rcswBxglBxbxsq.do?method=getBxzm&sqid="+sqid;
		window.open(url);
	}
}

