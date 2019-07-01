
/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
���뿪�غ���
*/
function changeSqkg(){
	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg){
		jQuery('#splcTr').show();
	}else if("0"==ksqkg){
		jQuery('#splcTr').hide();
	}	
}


/**
 * popup ѡ�񳡵���Ϣ��ѯ������ȡ����
 * @return
 */
function getCdcxSearch(){
	var search_cdmc;
	var search_rnrsmin ;
	var search_rnrsmax ;
	var search_yt;
	var search_dwkfsjkssj;
	var search_dwkfsjjssj;
	var search_sfkfsq;
	var path = jQuery("#path").val();
	//��ȡҳ���ѯֵ
	if(jQuery('#search_cdmc'))
		search_cdmc = jQuery.trim(jQuery('#search_cdmc').val());
	if(jQuery('#search_rnrsmin'))
		search_rnrsmin = jQuery.trim(jQuery('#search_rnrsmin').val());
	if(jQuery('#search_rnrsmax'))
		search_rnrsmax = jQuery.trim(jQuery('#search_rnrsmax').val());
	if(jQuery('#search_yt'))
		search_yt = jQuery.trim(jQuery('#search_yt').val());
	if(jQuery('#search_dwkfsjkssj'))
		search_dwkfsjkssj = jQuery.trim(jQuery('#search_dwkfsjkssj').val());
	if(jQuery('#search_dwkfsjjssj'))
		search_dwkfsjjssj = jQuery.trim(jQuery('#search_dwkfsjjssj').val());
	if(jQuery('#search_sfkfsq'))
		search_sfkfsq = jQuery.trim(jQuery('#search_sfkfsq').val());
	
	var map = {
			"search_cdmc":search_cdmc,
			"search_rnrsmin":search_rnrsmin,
			"search_rnrsmax":search_rnrsmax,
			"search_yt":search_yt,
			"search_dwkfsjkssj": search_dwkfsjkssj,
			"search_dwkfsjjssj": search_dwkfsjjssj,
			"path" : path,
			"search_sfkfsq" : search_sfkfsq
		};
	  
	return map;
}

/**
 * ����������Ϣ
 * @return
 */
function addCdxx(){
	showDialog('����������Ϣ',680,430,'rcsw_cdgl_cdxxwh.do?method=cdxxwhXz');
}

/**
 * ����������Ϣ�������
 * @return
 */
function addCdxxAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "cdmc-ld-fj-rnrs-dwkfsjkssj-dwkfsjjssj-lxr-lxfs";
	
	if(!checkNull(checkids)){
		return false;
	}
	
	if(jQuery('input[name="sfkfsq"]:checked').val() == undefined){
		showAlert("��ѡ���Ƿ񿪷����룡");
		return false;
	}

	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg&& !checkNull('splcid')){
		return false;
	}else if("0"==ksqkg){
		jQuery('#splcid').val("");
	}
	if(jQuery('#jbsbjs').val().length > 500){
		showAlert("�����豸�����������������"+500+",��ȷ�ϣ�");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#xfgfsyxy').val().length > 500){
			showAlert("�Ҹ�����ʹ��Э���������������"+500+",��ȷ�ϣ�");
			return false;
		}
	}
	
	//��ȡȨ�޷�Χ
    var qxfwIds="";
    jQuery("input[name=qxfwView]").each(function(){
    	var isChecked=jQuery(this).is(":checked");
    	if(isChecked){
    		qxfwIds+=jQuery(this).val();
    		qxfwIds+=",";
    	}
    });

    jQuery("#qxfw").val(qxfwIds);
    
	var url = "rcsw_cdgl_cdxxwh.do?method=cdxxwhXzAction";
		ajaxSubFormWithFun("rcswCdxxwhForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'repeat'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
				
			}});
		});
}


/**
 * �޸ĳ�����Ϣ
 * @return
 */
function updateCdxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('������Ϣ�޸�',680,430,'rcsw_cdgl_cdxxwh.do?method=cdxxwhXg&cdid=' + rows[0]['cdid']);
	}
}

/**
 * ���³�����Ϣ
 * @return
 */
function updateCdxxAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "cdmc-ld-fj-rnrs-dwkfsjkssj-dwkfsjjssj-lxr-lxfs";
	
	if(!checkNull(checkids)){
		return false;
	}
	
	if(jQuery('input[name="sfkfsq"]:checked').val() == undefined){
		showAlert("��ѡ���Ƿ񿪷����룡");
		return false;
	}
	
	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg && !checkNull('splcid')){
			return false;
	}else if("0"==ksqkg){
		jQuery("#splcid").val("");
	}
	
	if(jQuery('#jbsbjs').val().length > 500){
		showAlert("�����豸�����������������"+500+",��ȷ�ϣ�");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#xfgfsyxy').val().length > 500){
			showAlert("�Ҹ�����ʹ��Э���������������"+500+",��ȷ�ϣ�");
			return false;
		}
	}
	
	//��ȡȨ�޷�Χ
    var qxfwIds="";
    jQuery("input[name=qxfwView]").each(function(){
    	var isChecked=jQuery(this).is(":checked");
    	if(isChecked){
    		qxfwIds+=jQuery(this).val();
    		qxfwIds+=",";
    	}
    });

    jQuery("#qxfw").val(qxfwIds);

	var url = "rcsw_cdgl_cdxxwh.do?method=cdxxwhXgAction";
		ajaxSubFormWithFun("rcswCdxxwhForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


/**
 * ɾ��������Ϣ
 * @return
 */
function deleteCdxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlert("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("rcsw_cdgl_cdxxwh.do?method=cdxxwhScAction",{cdids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


var DCCLBH = "rcsw_cdgl_cdxxwh.do";//dcclbh,�������ܱ��


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_cdgl_cdxxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function isHaveV(V){
	var qxfw=jQuery("#qxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}

//���̿���
function setLckz(value) {
	if (value == "") {
		jQuery("#qxfwTd").html("");
		return;
	}
	var url = "rcsw_cdgl_cdxxwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				sHtml += "<label><input type='checkbox' name='qxfwView' value='"
						+ o.spgwdm + "'/>";
				sHtml += o.spgwmc;
				sHtml += "</label>";
				if (i != data.length - 1) {
					sHtml += "<br/>";
				}
			}
		}
		jQuery("#qxfwTd").html(sHtml);
		var isH=false;
	    jQuery("input[name=qxfwView]").each(function(){
	    	var V=jQuery(this).val();
	    	if(isHaveV(V)){
	    		jQuery(this).attr("checked","checked");
	    		isH=true;
	    	}
	    });
		jQuery("[name=qxfwView]").bind("click",function(){
			var selectV=jQuery(this).val();
			jQuery("[name=qxfwView]:checked").each(function(){
				var sv=jQuery(this).val();
				if(sv!=selectV){
					jQuery(this).removeAttr("checked");
				}
			});
		});
	
	}, 'json');
}



