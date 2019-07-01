function searchRs(){
	var map = getSuperSearch();
	var jtlb = jQuery("#jtlb").val();
	if (null != jtlb && jtlb != "") {
		map["jtlb"] = jtlb;
	}else{
		map["jtlb"] = "zc";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj, jtlb) {
	jQuery("#jtlb").val(jtlb);
	if (jtlb == "zc") {
		jQuery("#li_zc").css("display", "");
		jQuery("#li_gd").css("display", "none");
		jQuery("#bt").css("display", "");
		jQuery("#bt1").css("display", "none");
		var map = getSuperSearch();
		map["jtlb"]="zc";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_zc").css("display", "none");
		jQuery("#li_gd").css("display", "");
		jQuery("#bt1").css("display", "");
		jQuery("#bt").css("display", "none");
		var map = getSuperSearch();
		map["jtlb"]="gd";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function addzcjtff(){
	var url = "jtff_jtmdwh.do?method=AddZcJtff";
	var title = "����������ʦ����ά��";
	showDialog(title, 770, 450, url);
}

function addgdjtff(){
	var url = "jtff_jtmdwh.do?method=AddGdJtff";
	var title = "�̶�������ʦ����ά��";
	showDialog(title, 770, 450, url);
}


function savezcjt(type){
	var ids = "";
	if(type == "save"){
		ids = "zgh"+"-"+"gw";
	}else{
		ids = "gw";
	}
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(!checkzs()){
		return false;
	}
	var url = "";
	if(type == 'save'){
		 url = "jtff_jtmdwh.do?method=saveZcjt&type=" + type;
		
	}else{
		 url = "jtff_jtmdwh.do?method=saveZcjt&type=" + type;
	}
	ajaxSubFormWithFun("JtmdwhForm", url, function(data) {
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

function checkzs(){
	if(jQuery("#bz").val().length > 50){
		showAlertDivLayer("��ע��������50��֮�ڣ�");
		return false;
	}else{
		return true;
	}
}

function selectzghFirst(){
	jQuery(":input").not('#zgh').click(function(){
		if(jQuery.trim(jQuery("#zgh").val()) == ""){
			showAlert("����ѡ���ʦ��");
			return false;
		}
	});
}

function savegdjts(type){
	var ids = "";
	if(type == "save"){
		ids = "zgh"+"-"+"gdffje";
	}else{
		ids = "gdffje";
	}
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(!checkzs()){
		return false;
	}
	var url = "";
	if(type == 'save'){
		 url = "jtff_jtmdwh.do?method=saveGdjt&type=" + type;
		
	}else{
		 url = "jtff_jtmdwh.do?method=saveGdjt&type=" + type;
	}
	ajaxSubFormWithFun("JtmdwhForm", url, function(data) {
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

function initgdjtPara(){
	var zgh = jQuery.trim(jQuery("#zgh").val());
	var id = "";
	var gw = "";
	if(zgh != ""){
		var rs = null;
		var url = "jtff_util.do?method=getRywh";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'zgh='+zgh,
		async: false,
		success:function(result){
			if(result['id']==null||result['id']=="null"||result['id']=="" || result['id'] == "undifined"){
				
			}else{
				id = result['id'];
				gw = result['gw'];
			}
		 }
	    });
		  if(id == ""){
		    	showAlert("�ý�ʦ������������ά��������!",{},{"clkFun":function(){
		    		jQuery("#savegdjt").hide();
					return false;
				}});
		    }else{
		    	jQuery("#id").val(id);
		    	jQuery("#gw").text(gw);
		    	jQuery("#savegdjt").show();
		    }
	}
}
   
	//���������޸�
	function updatezcjt(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} else {
			var url = 'jtff_jtmdwh.do?method=UpdateZcJtff&id=' + rows[0]["id"]
					+ '&zgh=' + rows[0]["zgh"];
			var title = "����������ʦ�����޸�";
			showDialog(title, 770, 552, url);
		}
	}
	
	//�̶������޸�
	function updategdjt(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} else {
			var url = 'jtff_jtmdwh.do?method=UpdateGdJtff&id=' + rows[0]["id"]
					+ '&zgh=' + rows[0]["zgh"];
			var title = "�̶�������ʦ�����޸�";
			showDialog(title, 770, 552, url);
		}
	}
	
	//ɾ��
	function delzcjt() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("jtff_jtmdwh.do?method=DelZcjt",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	//ɾ��
	function delgdjt() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("jtff_jtmdwh.do?method=DelGdjt",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	//�鿴ѧ������
	function xhLink(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='zcjtck(\""
				+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
				+ "</a>";
	}
	
	//�鿴ѧ������
	function xhLink1(cellValue, rowObject) {
		return "<a href='javascript:void(0);' class='name' onclick='gdjtck(\""
				+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
				+ "</a>";
	}
	
	//���������鿴
	function zcjtck(id,zgh){
		showDialog("����������ʦ�����鿴", 650, 450, "jtff_jtmdwh.do?method=ZcjtCk&id="
				+ id + "&zgh=" + zgh);
	}
	
	
	//�̶������鿴
	function gdjtck(id,zgh){
		showDialog("�̶�������ʦ�����鿴", 650, 450, "jtff_jtmdwh.do?method=GdjtCk&id="
				+ id + "&zgh=" + zgh);
	}
	
	var DCCLBH = "szdw_jtff_jtmdwh.do";
	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		if(jQuery("#jtlb").val() == 'zc'){
			DCCLBH = 'szdw_jtff_jtmdwh.do';
		}else{
			DCCLBH = 'szdw_jtff_jtmdwhs.do';
		}
		customExport(DCCLBH,ExportData);
	}

	//��������
	function ExportData() {
		setSearchTj();//���ø߼���ѯ����
		var jtlb = "";
		if(jQuery("#jtlb").val() == 'zc'){
			jtlb = 'zc';
		}else{
			jtlb = 'gd';
		}
		var url = "jtff_jtmdwh.do?method=exportData&dcclbh=" + DCCLBH+"&jtlb="+jtlb;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	
	//����
	function importConfig(){
		toImportDataNew("IMPORT_10026_JTMD");
		return false;
	}
