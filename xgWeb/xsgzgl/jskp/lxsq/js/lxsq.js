
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function add(){
	var sfsh = jQuery("#sfsh").val();
	var url = "jskp_lxsq.do?method=addLxsq";
	var title = "��������";
	if("0" == sfsh){
		showDialog(title, 670, 438, url);
	}else{
		showDialog(title, 770, 552, url);
	}
	
}

/**
 * �޸�
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if("0" == sfsh){
			if ("0" != rows[0]['shzt1']&&"3" !=rows[0]['shzt1']) {
				showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
				return false;
			}
		}else{
			if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
				showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
				return false;
			}
		}
		var url = 'jskp_lxsq.do?method=updateLxsq&sqid=' + rows[0]["sqid"];
		var title = "���������޸�";
		if("0" == sfsh){
			showDialog(title, 670, 438, url);
		}else{
			showDialog(title, 770, 552, url);
		}
	}
}

/**
 * ������������
 * @param saveFlag
 * @return
 */
function saveLxsq(saveFlag){
	/*ȡ���������е��Ƿ����ֵ��0������ˡ�1�����*/
	var sfsh = document.getElementById('sfsh').value;
	var ids = null;
	/*��������Ϊ��ʱ����ָ����ʦzdls������ָ����ʦ��ϵ��ʽzdlslxfs��������ֵ����zxf - zdf�������п�*/
	if("0" == sfsh){
		ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"filepath";
	}else{
		ids = "xmmc"+"-"+"bmmc"+"-"+"xmlb"+"-"+"lxsj"+"-"+"fzrlxfs"+"-"+"zdls"+"-"+"zdlslxfs"+"-"+"zxf"+"-"+"zdf";
	}
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	/*�����ж�*/
	if(jQuery("#zdbm").val() == ""){
		return showAlert("ϵͳ�޸�ָ���������ƣ���������д��");
	}
	/*���ܲ�������Ϊ�ǻ�񣬲�Ӱ����ж�*/
	if(parseInt(jQuery("#zdf").val()) < parseInt(jQuery("#zxf").val())){
		return showAlert("���ֱ��벻��С����С�֣�");
	}
	
	var url = null;
	if("0" == sfsh){
		var tjsf = "Edit";
		var tjxs = "submit";
		if("submitAdd" == saveFlag){
			url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + tjxs;
		}
		if("submitUpdate" == saveFlag){
			url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + tjxs +"&tjsf=" + tjsf;
		}
	}else{
		url = "jskp_lxsq.do?method=saveForLxsq&saveFlag=" + saveFlag;
	}
	ajaxSubFormWithFun("LxsqForm", url, function(data) {
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

/**
 * �ύ
 * @return
 */
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var sfsh = jQuery("#sfsh").val();
	var shztndd;
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		
		if("0" == sfsh){
			shztndd = rows[0]["shzt1"];
		}else{
			shztndd = rows[0]["shzt"];
		}
		
		if("0" == sfsh){
			if (rows[0]["shzt1"] != "0" && rows[0]["shzt1"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}else{
			if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jskp_lxsq.do?method=submit", {
					values : ids.toString(),shzt:shztndd
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * ��ʦ���Ҫ��Ҫ���������ύ���ܣ�2018-04-02��MengWei
 * ���ڸ��˺ܶ�Σ��жϽ϶࣬�������Ӹ���ť
 */
function batchSubmission(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt1"] && "3" != rows[i]["shzt1"]){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			var tips = submitLoading();
			try{
				tips.show();
				
				
				
			}catch(e){
				
			}
			jQuery.post("jskp_lxsq.do?method=batchSubmission",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * ����
 * @return
 */
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var sfsh = jQuery("#sfsh").val();
		if("1" == sfsh){
			if (rows[0]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}else{
			if (rows[0]['shzt1'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jskp_lxsq.do?method=cancel", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

/**
 * ɾ��
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	if (ids.length == 0){
		return showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}
	if("0" == sfsh){
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt1"] != "0" && rows[i]["shzt1"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
	}else{
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("jskp_lxsq.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sfsh = jQuery("#sfsh").val();
	var shzt = null;
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		if("0" == sfsh){
			shzt = rows[0]["shzt1"];
		}else{
			shzt = rows[0]["shzt"];
		}
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}

/**
 * �鿴��������
 * @return
 */
function ckLxsq(sqid){
	showDialog("��������鿴", 770, 450, "jskp_lxsq.do?method=ckLxsq&sqid="
			+ sqid );
}

/**
 * ��Ŀ����Link
 * @return
 */
function xmmcLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='ckLxsq(\""
	+ rowObject["sqid"] + "\");'>" + cellValue
	+ "</a>";
}

/**
 * �鿴��������
 * @return
 */
function rysz(sqid){
	showDialog("��Ա����", 770, 450, "jskp_lxsq.do?method=rysz&sqid="
			+ sqid );
}

/**
 * ��Ա����Link
 * @return
 */
function ryszLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rysz(\""
	+ rowObject["sqid"] + "\");'>" + "��Ա����"
	+ "</a>";
}

/**
 * ���ݵ���
 */
function dataImport(){
	var url = "jskp_lxsq.do?method=dataImport";
	var title = "��ʵ������Ϣ����";
	showDialog(title, 500, 309, url);
}

/**
 * ����ģ��
 * @return
 */
function mbDownload(){
	var url = "jskp_lxsq.do?method=downloadFile";
	window.open(url);
}

/**
 * ѡ���ļ������ļ����Ƹ�ֵ��input��
 */
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * ��ȡinput file������
 * @param obj
 * @return
 */
function getFullPath(obj){ 
	if(obj){ 
	 if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
		 if(obj.files) 
		 { 
		   return obj.files.item(0).getAsDataURL(); 
		 } 
		 return obj.value; 
	 }
	 return obj.value; 
	}
}

/**
 * �ļ����Ϳ���
 * @param obj
 * @return
 */
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ���");
		return false;
	}
}

/**
 * ���뱣��
 */
function importSave(){
	var url = "jskp_lxsq.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	
	ajaxSubFormWithFun("LxsqForm", url, function(data) {
		if(data["message"]=="����ɹ���"){
		 jQuery("#errortr").hide();
		 jQuery("#errora").attr("href","");
		 showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
			  refershParent();
			}
		 }});
   	 }else{
		 showAlert(data["message"],{},{"clkFun":function(){
		      if(data["message"] == "����ʧ��,����ϸ�˶ԡ���������.xls����"){
		    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
		    		  jQuery("#errortr").show();
		    		  jQuery("#errora").attr("data_file","jskp_lxsq.do?method=downloadFileError&filename="+data["gid"]);
		    	  }
		      }else{
		    	 jQuery("#errortr").hide();
		    	jQuery("#errora").attr("data_file","");
		      }
		      jQuery("#drmb").val("");
			}});
   		}
	});
}

/**
 * ������������
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}