var gndm = "tyxs_query";

function addZzjg(){	
	showDialog("����",800,500,"tyxs_zzjg.do?method=addZzjg");
}

function editZzjg(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["sqid"] != null){
			showAlertDivLayer("�������ݲ����޸ģ�");
			return false;
		}
		
		showDialog("�޸�",800,500,"tyxs_zzjg.do?method=editZzjg&id="+rows[0]["id"]);
	}
}

function delZzjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["sqid"] != null) {
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("tyxs_zzjg.do?method=delZzjg",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//����
function exportConfig(){
	var DCCLBH='tyxs_zzjg.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='tyxs_zzjg.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "tyxs_zzjg.do?method=dcjg&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * �鿴�����
 * @param id
 */
function ckZzjg(id){
	showDialog("�鿴������",800,450,"tyxs_zzjg.do?method=ckZzjg&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//���������Ϣ
function onShow(gndm) {
	var url = "tyxs_zzjg.do?method=Zzxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			id : jQuery("#id").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data);
		}
	});
}



//��֤�����ܶ�
function getzje(obj){
	checkMoney(obj);
	var sqxfzj = jQuery("#sqxfzj").val();//�ܼ�
	var dyzzxf = jQuery("#dyzzxf").val();//��һ��
	var dezzxf = jQuery("#dezzxf").val();//�ڶ���
	var dszzxf = jQuery("#dszzxf").val();//������
	var dsizzxf = jQuery("#dsizzxf").val();//������
  var dqdks = obj.value;
  var dkzs=0;
	if (null !=dyzzxf && ''!= dyzzxf) {
		
	  dkzs = parseInt(dyzzxf)+parseInt(dkzs);
	}
	if (null != dezzxf && ''!= dezzxf) {
	  dkzs = parseInt(dezzxf)+parseInt(dkzs);		
	  }
	if (null !=dszzxf && ''!= dszzxf) {
	  dkzs = parseInt(dszzxf)+parseInt(dkzs);
	  }
	if (null !=dsizzxf && ''!= dsizzxf) {
		  dkzs = parseInt(dsizzxf)+parseInt(dkzs);
		  }
	
	jQuery("#sqxfzj").val(parseInt(dkzs));
	
}





function saveZzsq(url){

	var xh= jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	
	
	// ���ѧ���Ƿ����
	if (xh=="" ){
		showAlertDivLayer("[ѧ��]����Ϊ��!");
		return false;
	}
	if(jQuery("#xxdm").val() == '10511'){
		
		var dklx = jQuery("#dklx").val();
		var dkbj = jQuery.trim(jQuery("#dkbj").val());
		if(dklx == ""){
			showAlert("[��������]����Ϊ��!");
			return false;
		}
		if(dkbj == ""){
			showAlert("[������]����Ϊ��!");
			return false;
		}
	}

	if(!checkNull('xn-sqxfzj-sqly-sqsj') ){
				return false;
				
	}
	
	if(checksqlylength() == false){
		return false;
	}
	
	jQuery.post("tyxs_zzjg.do?method=getCountByXhAndXn",{xh:xh,xn:xn,id:jQuery("#id").val()},function(data){
			
			if (Number(data) == 0){
				ajaxSubFormWithFun("tyxsZzjgForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			} else {
				showAlertDivLayer("��ѧ���Ѿ������ѧ����������ȷ�ϣ�");
			}
		},"json");
		

					
}