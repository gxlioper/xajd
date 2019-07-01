//��֤ѧ��
function checkXh(){
	var xh = jQuery("#cyry").val();
	var ryflag = jQuery("#ryflag").val();
	var sqid = jQuery("#sqid").val();
	jQuery.ajaxSetup({async:false});
	jQuery.post("jskp_lxsq.do?method=checkXh",{xh:xh,ryflag:ryflag,sqid:sqid},function(data){
		jQuery("#kcyryspan").empty();
		jQuery("#kcyry").html("");
		jQuery("#wtgry").html("");
		var userList = data['use'];
		var nouserList = data['nouse'];
		if(userList.length > 0){
			var usehtml = "";
			var kcystr = "";
			for(var i = 0;i < userList.length;i++){
				usehtml += "<input type='hidden' name='xh' value='"+userList[i]['xh']+"' />";
				kcystr += userList[i]['xh']+"&nbsp;"
			}
		}
		if(nouserList.length > 0){
			var wtgstr = "";
			for(var i = 0;i < nouserList.length;i++){
				wtgstr += nouserList[i]+"&nbsp;"
			}
		}
		jQuery("#kcyryspan").html(usehtml);
		jQuery("#kcyry").html(kcystr);
		jQuery("#wtgry").html(wtgstr);
	},'json');
	jQuery.ajaxSetup({async:true});
}

/**
 * ��ʼ������
 * @return
 */
function initData(){	
	demoHtml = "�밴���¸�ʽ��������ѧ��ѧ��\n\n";
	demoHtml += "���磺\n";
	demoHtml += "20110019\n20100019\n20090026";
	demoHtml += "\n���ߣ�\n";
	demoHtml += "20110019 20100019 20090026";
	if(jQuery("#sqid").length == 0){
		jQuery("#cyry").val(demoHtml);
		jQuery("#cyry").css("color", "#999");
	}
	jQuery("#cyry").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#cyry").blur( function() {
		if (jQuery.trim(jQuery(this).val()) == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}else{
			jQuery("#yztr1").show();
			jQuery("#yztr2").show();
			checkXh();
		}
	});
}

function searchRs() {
	var map = {};
	map["sqid"] = jQuery("#sqid").val();
	map["xh"] = jQuery("#xh").val();
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

/**
 * ������Ա
 * @return
 */
function add(){
	var url = "jskp_lxsq.do?method=addRy&sqid="+jQuery("#sqid").val()+"&shzt="+jQuery("#shzt").val();
	showDialog("������Ա", 500, 250, url);
}

/**
 * ɾ����Ա
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		return showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} 
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("jskp_lxsq.do?method=delRy",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * ������Ա����
 * @return
 */
function saveRysz(){
	jQuery("#cyry").blur();
	if(jQuery("[name='xh']").length == 0){
		return showAlert("�ɲ�����Ա����Ϊ�գ�");
	}
	var url = "jskp_lxsq.do?method=saveRysz";
	ajaxSubFormWithFun("LxsqForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			   var api = frameElement.api;
 				   var parentsW = api.get('parentDialog');
 				   parentsW.jQuery("#search_go").click();
 				   closeDialog();
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}
