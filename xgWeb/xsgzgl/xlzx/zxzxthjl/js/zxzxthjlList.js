var DCCLBH='xlzx_thjl_zxzxthjl.do';
//���ӵķ���
function addZxzxthjl() {
	showDialog('���������ղ�̸����¼', 750, 520, 'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd');
}
//���ӱ��淽��
function save(){
	var ybwtlbIds = "";
	var checkYbwtlb = "";
	var zajbIds = "";
	var checkZajb = "";
	var xh = jQuery("#xh").val();//ѧ��
	var thsj = jQuery("#thsj").val();//Լ̸ʱ��ʱ��
	var jbqkms = jQuery("#jbqkms").val();//�����������
	var cbpgdm = jQuery("#cbpgdm").val();//��������
	var cbpgjg = jQuery("#cbpgjg").val();//�����������
	var zajbsmzx = jQuery("#zajbsmzx").val();//�����������sfzj
	var sfzj = jQuery("#sfzj").val();//�Ƿ�ת��
	//����ѧ�š�Լ̸ʱ�䣡
	if(xh==null||xh==""){
		showAlert("��ѡ��ѧ�ţ�");
		return false;
	}
	if(thsj==null||thsj==""){
		showAlert("��ѡ��Լ̸ʱ�䣡");
		return false;
	}
	if(jbqkms==null||jbqkms==""){
		showAlert("����д�������������");
		return false;
	}
	if(cbpgdm==null||cbpgdm==""){
		showAlert("��ѡ�����������Ӧѡ�");
		return false;
	}
	
	
	//��������
	var checkYbwtlb1 = false;
	jQuery("input[name='ybwtlb']:checked").each(function(){
		ybwtlbIds += jQuery(this).val()+ "," ;
		 if(jQuery(this).val()=="������ѯ"&&zajbsmzx=="2"){
			 checkYbwtlb1 =true;
			}
	});
	if(ybwtlbIds.length>0){
		ybwtlbIds = ybwtlbIds.substring(0, ybwtlbIds.length-1);
		checkYbwtlb ="1";
	}
	 if(cbpgdm=="2"&&jQuery.trim(checkYbwtlb) == ""){
			showAlert("����дһ���������");
			return false;
	}
	 //��ȡcheckbox��ֵΪ��������ѯ��������ǽ�����ѯ�ж��Ƿ�ԤԼ��ѯʱ���Ƿ�Ϊ��
	 if(cbpgdm=="2"&&checkYbwtlb1){
			showAlert("��ѡ���Ƿ�ԤԼ��ѯʱ�䣡");
			return false;
		}
	 
	//ռλ
	var checkZajb2 = false;
	var checkZajb3 = false;
	var checkZajb4 = false;
	
	//�����ϰ��;��񼲲�
	jQuery("input[name='zajb']:checked").each(function(){
		zajbIds += jQuery(this).val()+ "," ;
		if(jQuery(this).val()=="��������"&&cbpgjg==""){
			checkZajb2 =true;
		}
		if(jQuery(this).val()=="������ѯ"&&zajbsmzx=="2"){
			checkZajb3 =true;
		}
		if(jQuery(this).val()=="��������"&&sfzj=="2"){
			checkZajb4 =true;
		}
	});
	//�����ϰ������������������ϰ��;��񼲲�����������������Ƿ�ԤԼ��ѯʱ�䡢�Ƿ�ת��
	if(zajbIds.length>0){
		zajbIds = zajbIds.substring(0, zajbIds.length-1);
		checkZajb ="1";
	}
	if(cbpgdm=="3"&&jQuery.trim(checkZajb) == ""){
		showAlert("��ѡ�������ϰ��;��񼲲���");
		return false;
	}
	if(cbpgdm=="3"&&checkZajb2){
		showAlert("����д�������������");
		return false;
	}	if(cbpgdm=="3"&&checkZajb3){
		showAlert("��ѡ���Ƿ�ԤԼ��ѯʱ��");
		return false;
	}	if(cbpgdm=="3"&&checkZajb4){
		showAlert("��ѡ���Ƿ�ת�飡");
		return false;
	}
	
	var url = "xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlAdd&type=save&ybwtlb="+encodeURI(encodeURI(ybwtlbIds))+"&zajb="+encodeURI(encodeURI(zajbIds));
	ajaxSubFormWithFun("zxzxthjlForm",url,function(data){
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

//������ʾ
function change_cbpgdm(){//ѡ���һ��ѡ��ʱ
	var cbpgdm = jQuery('#cbpgdm').val();
	if(cbpgdm=="1"){
		jQuery("#ybwtlb1").hide();
		jQuery("#zajb1").hide();
	}else if(cbpgdm=="2"){//ѡ��ڶ���ѡ��ʱ
		jQuery("#ybwtlb1").show();
		jQuery("#zajb1").hide();
	}else if(cbpgdm=="3"){//ѡ�������ѡ��ʱ
		jQuery("#ybwtlb1").hide();
		jQuery("#zajb1").show();
	} 
	jQuery("input[name=ybwtlb]").each(function(){
		jQuery(this).attr("checked",false);
	});
	jQuery("input[name=zajb]").each(function(){
		jQuery(this).attr("checked",false);
	});
	jQuery("#zajbsmzx1").hide();
	jQuery("#cbpgjg1").hide();
	jQuery("#sfzj1").hide();
	jQuery("#zajbsmzx").val("");
	jQuery("#cbpgjg").val("");
	jQuery("#sfzj").val("");
}
function click_ybwtlb(obj){//���һ��������𣬻���ʾ �Ƿ�ԤԼ��ѯʱ��
	jQuery("#zajbsmzx1").hide();
	jQuery("input[name=ybwtlb]").each(function(){
		if( jQuery(this).val() == "������ѯ" && this.checked){
			jQuery("#zajbsmzx1").show();
		}
	});
	jQuery("#zajbsmzx").val("");
}
function click_zajbcbpg(obj){//
	var zajb = obj.value;
	if(zajb == "��������" && obj.checked){
		jQuery("#cbpgjg1").show();
	}else{
		jQuery("#cbpgjg1").hide();
	}
	jQuery("#cbpgjg").val("");
}
function click_zajbjyzx(obj){//
	var zajb = obj.value;
	if(zajb == "������ѯ" && obj.checked){
		jQuery("#zajbsmzx1").show();
	}else{
		jQuery("#zajbsmzx1").hide();
	}
	jQuery("#zajbsmzx").val("");
}
function click_zajbqtjy(obj){//
	var zajb = obj.value;
	if(zajb == "��������" && obj.checked){
		jQuery("#sfzj1").show();
	}else{
		jQuery("#sfzj1").hide();
	}
	jQuery("#sfzj").val("");
}
//�޸�
function updateZxzxthjl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸������ղ�̸����¼',750, 500,'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlUpdate&id='+rows[0]["id"]);
	}
}
//�޸ı��淽��
function saveUpdate(){
	var ybwtlbIds = "";
	var checkYbwtlb = "";
	var zajbIds = "";
	var checkZajb = "";
	var xh = jQuery("#xh").val();//ѧ��
	var thsj = jQuery("#thsj").val();//Լ̸ʱ��ʱ��
	var jbqkms = jQuery("#jbqkms").val();//�����������
	var cbpgdm = jQuery("#cbpgdm").val();//��������
	var cbpgjg = jQuery("#cbpgjg").val();//�����������
	var zajbsmzx = jQuery("#zajbsmzx").val();//�����������sfzj
	var sfzj = jQuery("#sfzj").val();//�Ƿ�ת��
	//����ѧ�š�Լ̸ʱ�䣡
	if(xh==null||xh==""){
		showAlert("��ѡ��ѧ�ţ�");
		return false;
	}
	if(thsj==null||thsj==""){
		showAlert("��ѡ��Լ̸ʱ�䣡");
		return false;
	}
	if(jbqkms==null||jbqkms==""){
		showAlert("����д�������������");
		return false;
	}
	if(cbpgdm==null||cbpgdm==""){
		showAlert("��ѡ�����������Ӧѡ�");
		return false;
	}
	
	
	//��������
	var checkYbwtlb1 = false;
	jQuery("input[name='ybwtlb']:checked").each(function(){
		ybwtlbIds += jQuery(this).val()+ "," ;
		 if(jQuery(this).val()=="������ѯ"&&zajbsmzx=="2"){
			 checkYbwtlb1 =true;
			}
	});
	if(ybwtlbIds.length>0){
		ybwtlbIds = ybwtlbIds.substring(0, ybwtlbIds.length-1);
		checkYbwtlb ="1";
	}
	 if(cbpgdm=="2"&&jQuery.trim(checkYbwtlb) == ""){
			showAlert("����дһ���������");
			return false;
	}
	 //��ȡcheckbox��ֵΪ��������ѯ��������ǽ�����ѯ�ж��Ƿ�ԤԼ��ѯʱ���Ƿ�Ϊ��
	 if(cbpgdm=="2"&&checkYbwtlb1){
			showAlert("��ѡ���Ƿ�ԤԼ��ѯʱ�䣡");
			return false;
		}
	//ռλ
	var checkZajb2 = false;
	var checkZajb3 = false;
	var checkZajb4 = false;
	
	//�����ϰ��;��񼲲�
	jQuery("input[name='zajb']:checked").each(function(){
		zajbIds += jQuery(this).val()+ "," ;
		if(jQuery(this).val()=="��������"&&cbpgjg==""){
			checkZajb2 =true;
		}
		if(jQuery(this).val()=="������ѯ"&&zajbsmzx=="2"){
			checkZajb3 =true;
		}
		if(jQuery(this).val()=="��������"&&sfzj=="2"){
			checkZajb4 =true;
		}
	});
	//�����ϰ������������������ϰ��;��񼲲�����������������Ƿ�ԤԼ��ѯʱ�䡢�Ƿ�ת��
	if(zajbIds.length>0){
		zajbIds = zajbIds.substring(0, zajbIds.length-1);
		checkZajb ="1";
	}
	if(cbpgdm=="3"&&jQuery.trim(checkZajb) == ""){
		showAlert("��ѡ�������ϰ��;��񼲲���");
		return false;
	}
	if(cbpgdm=="3"&&checkZajb2){
		showAlert("����д�������������");
		return false;
	}	if(cbpgdm=="3"&&checkZajb3){
		showAlert("��ѡ���Ƿ�ԤԼ��ѯʱ��");
		return false;
	}	if(cbpgdm=="3"&&checkZajb4){
		showAlert("��ѡ���Ƿ�ת�飡");
		return false;
	}
	var url="xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlUpdate&type=save&ybwtlb="+encodeURI(encodeURI(ybwtlbIds))+"&zajb="+encodeURI(encodeURI(zajbIds));
	ajaxSubFormWithFun("zxzxthjlForm",url,function(data){
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
//���ѧ�Ų鿴������Ϣ
function viewZxzxthjl(id,cellValue){
	showDialog('�鿴�����ղ�̸����¼',666,520,'xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlView&id='+id+"&xh="+cellValue,null);
}
//ɾ���ķ���
function deleteZxzxthjl(){
	var ZxzxThjl = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				ZxzxThjl += rowsValue[i]["id"];
			} else {
				ZxzxThjl += rowsValue[i]["id"] + ",";
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlDelete", {
					ZxzxThjl : ZxzxThjl
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}
//��������
function exportData() {
	customExport(DCCLBH, config);
}
// ��������
function config() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xlzx_thjl_zxzxthjlgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//��ӡԼ̸��¼��
function getWord(){
	var rows = jQuery("#dataTable").getSeletRow();
	var id = "";
	if (rows.length == 1) {
		id +=rows[0]["id"];
		var url = "xlzx_thjl_zxzxthjlgl.do?method=getZxzxthjl&id="+id;
		window.open(url);
	} else if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			id +=rows[i]["id"]+",";
			}
		var url = "xlzx_thjl_zxzxthjlgl.do?method=getZxzxthjlZip&value="+id;
		window.open(url);
		}
}