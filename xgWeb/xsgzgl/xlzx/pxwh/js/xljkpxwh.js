function pxztLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='pxztView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function pxztView(pxid){
	showDialog("�鿴��ѵ����",600, 400,"xlzx_pxwh.do?method=pxwhView&pxid="+pxid);
}

function ybmxsLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='ybmxsView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function ybmxsView(pxid){
	showDialog("�ѱ���ѧ���鿴",800, 500,"xlzx_pxwh.do?method=ybmxsList&pxid="+pxid);
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "xg_xlzx_pxwh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xlzx_pxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ɾ��
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	 }
	var flag = false;
	 for(var i = 0; i < rows.length; i++){
		 if(rows[i]["ybmrs"] != '0'){
			 flag = true;
		 }
	 }
	 if(flag){
		 showAlertDivLayer("����ѧ���������޷�ɾ����");
		 return false;
	 }
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xlzx_pxwh.do?method=pxwhDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//����ҳ����ת
function add(){
	showDialog('������ѵ��Ŀ',600,450,'xlzx_pxwh.do?method=pxwhAdd');
}


//�༭ҳ����ת
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸���ѵ��Ϣ',600,450,'xlzx_pxwh.do?method=pxwhEdit&pxid=' + rows[0]["pxid"]);
	}
}

//����
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	var ybmrs=jQuery("#ybmrs").html();
	if(ybmrs){ //�༭ҳ�����ybmrs�������ж�
		var sxrs=jQuery("#sxrs").val();
		if(parseInt(sxrs)<parseInt(ybmrs)){
			showAlertDivLayer("��������С���ѱ���������");
			return false;
		}
	}
	if(jQuery("#jssj").val()>jQuery("#pxsj").val()){
		showAlertDivLayer("��������ʱ�䲻��������ѵʱ��");
		return false;
	}
	//lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	//unlock();
}
