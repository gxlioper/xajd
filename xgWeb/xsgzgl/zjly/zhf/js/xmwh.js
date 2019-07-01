function queryXmmk(){
	var map = {};
	map["cxzd"] = jQuery("#cxzd").val();
	jQuery("#dataTable").reloadGrid(map);
}

function addXmmk(){
	var url = "zhf_xmmkwh.do?method=addZhfXmmk";
	var title = "������Ŀģ��";
	showDialog(title, 500, 350, url);
}

//������Ŀģ��
function saveXmmk(type){
	var xmmkdm = jQuery("#xmmkdm").val();
	var xmmkmc = jQuery("#xmmkmc").val();
	var xf = jQuery("#xf").val();
	var hgf = jQuery("#hgf").val();
	if(xmmkdm == null || xmmkdm == '' || xmmkmc == null || xmmkmc == '' || xf == null || xf == '' || hgf == null || hgf == ''){
		showAlertDivLayer("����д�����");
		return false;
	}
	if(Number(hgf)>Number(xf)){
		showAlertDivLayer("�ϸ�ֲ��ܴ����ܷ�");
		return false;
	}
	var url = "zhf_xmmkwh.do?method=saveXmmk&type=" + type;
	ajaxSubFormWithFun("myForm", url, function(data) {
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

//�޸���Ŀģ��
function updateXmmk(){
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length!=1){
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var xmmkdm = rows[0]["xmmkdm"];
	var url = "zhf_xmmkwh.do?method=updateZhfXmmk&xmmkdm="+xmmkdm;
	var title = "�޸�";
	showDialog(title, 500, 350, url);
}

//ɾ����Ŀģ��
function delXmmk(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("zhf_xmmkwh.do?method=delZhfXmmk", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});	
}

//�л�Tabҳ
function selectTab(obj, xmlx) {
	//jQuery("#shzt").val(shzt);
	if (xmlx == "xmmk") {
		jQuery("#xmmk").css("display", "");
		jQuery("#xmmkGjcx").css("display", "");		
		jQuery("#jfxm").css("display", "none");
		jQuery("#jfxmGjcx").css("display", "none");
		//var map = getSuperSearch();
		//map["shzt"]="dsh";
		//gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#xmmk").css("display", "none");
		jQuery("#xmmkGjcx").css("display", "none");	
		jQuery("#jfxm").css("display", "");
		jQuery("#jfxmGjcx").css("display", "");		
		//var map = getSuperSearch();
		//map["shzt"]="ysh";
		//gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function searchRs() {
	var map = getSuperSearch();	
	jQuery("#dataTable").reloadGrid(map);	
}

//���ӼƷ���Ŀ
function addJfxm() {
	var url = "zhf_jfxmwh.do?method=addZhfJfxm";
	var title = "���ӼƷ���Ŀ";
	showDialog(title, 600, 350, url);
}

//����Ʒ���Ŀ
function saveJfxm(type) {
	var xmmkdm = jQuery("#xmmkdm").val();
	var jfxmmc = jQuery("#jfxmmc").val();
	var khyd = jQuery("#khyd").val();
	var fs = jQuery("#fs").val();
	var sfbt = jQuery("input[name='sfbt']:checked").val();
	var sfxf = jQuery("input[name='sfxf']:checked").val();
	if(sfxf == '1'){
		var xdfs = jQuery("#xdfs").val();
		if(xdfs == '' || xdfs == null){
			showAlertDivLayer("����д�����");
			return false;
		}
	}
	if(xmmkdm == null || xmmkdm == '' || jfxmmc == null || jfxmmc == '' || khyd == null || khyd == '' || sfbt == null || sfbt == '' || sfxf == null || sfxf == ''){
		showAlertDivLayer("����д�����");
		return false;
	}
	var url = "zhf_jfxmwh.do?method=saveJfxm&type=" + type;
	ajaxSubFormWithFun("myForm", url, function(data) {
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

//�޸ļƷ���Ŀ
function updateJfxm(){
	var rows = jQuery('#dataTable').getSeletRow();
	if(rows.length!=1){
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var jfxmdm = rows[0]["jfxmdm"];
	var url = "zhf_jfxmwh.do?method=updateZhfJfxm&jfxmdm="+jfxmdm;
	var title = "�޸�";
	showDialog(title, 600, 350, url);
}

//��ʾ�޷�
function xs(obj){
	var sfbt = jQuery(obj).val();
	if(sfbt=='1'){
		jQuery("#xdfstr").css("display","");
	}else{
		jQuery("#xdfstr").css("display","none");
	}
}

//ɾ����Ŀģ��
function delJfxm(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=delZhfJfxm", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});	
}

//������Ȩ
function bmsq(){
	var rows = jQuery('#dataTable').getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ��Ȩ�ļ�¼��");
		return false;
	}
	var jfxms = ids.toString();
	var url = "zhf_jfxmwh.do?method=getBmList&jfxms="+jfxms;
	var title = "��Ŀ��Ȩ";
	showDialog(title, 700, 550, url);	
}

//�л�Tabҳ
function selectSqxm(obj, shzt) {
	jQuery("#cxzt").val(shzt);
	if (shzt == "wsq") {
		document.getElementById("bcsq").style.display='block';
		document.getElementById("qxsq").style.display='none';
		//var map = getSuperSearch();
		//map["shzt"]="dsh";
		//gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		document.getElementById("qxsq").style.display='block';
		document.getElementById("bcsq").style.display='none';
		//var map = getSuperSearch();
		//map["shzt"]="ysq";
		//gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//��Ȩ
function sq(){
	var jfxmss = jQuery("#jfxms").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��Ȩ�Ĳ��ţ�");
		return false;
	}
	var bmdms = ids.toString();
	showConfirmDivLayer("��ȷ��Ҫ��Ȩ���ϲ�����", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=saveSq", {
				bmdms : ids.toString(),
				jfxms : jfxmss
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}
//ȡ����Ȩ
function qx(){
	var jfxmss = jQuery("#jfxms").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫȡ����Ȩ�Ĳ��ţ�");
		return false;
	}
	var bmdms = ids.toString();
	showConfirmDivLayer("��ȷ��Ҫȡ����Ȩ���ϲ�����", {
		"okFun" : function() {
			jQuery.post("zhf_jfxmwh.do?method=cancelSq", {
				bmdms : ids.toString(),
				jfxms : jfxmss
			},
			function(data){
				if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	}
			}, 'json');
		}
	});
}

//��������
function queryBm(){
	var cxmc = jQuery("#cxmc").val();
	var cxzd = jQuery("#cxzt").val();
	var map = {};
	map["cxmc"] = cxmc;
	map["cxzd"] = cxzd;
	jQuery("#dataTable").reloadGrid(map);
}

//�������
function jdsz() {
	var url = "zhf_jfxmwh.do?method=jdsz";
	var title = "�������";
	showDialog(title, 450, 300, url);
}

//����������
function saveJdsz() {
	var jdsz = jQuery("#jdszContent").val();
	if(jdsz == null || jdsz == ""){
		showAlertDivLayer("����д������ã�");
		return false;
	}else{
		var url = "zhf_jfxmwh.do?method=saveJdsz";
		ajaxSubFormWithFun("myForm", url, function(data) {
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
}

var DCCLBH = "zjly_zhf_jfxmwh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jfxmExportData);
}

//��������
function jfxmExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zhf_jfxmwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

