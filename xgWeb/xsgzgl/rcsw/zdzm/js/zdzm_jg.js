
/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �����ڶ�֤��
 * @return
 */
function addZdzmJg(){
	showDialog('�����ڶ�֤�����',780,350,'rcsw_zdzm_jggl.do?method=addZdzmJg');
}
/**
 * �����ڶ�֤���������
 * @return
 */
function addZdzmJgAction(){
	var xh = jQuery('#xh').val();
	var sqly = jQuery('#sqly').val();
	
	if (xh=="" || sqly == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	
	var url = "rcsw_zdzm_jggl.do?method=addZdzmJgAction";
		ajaxSubFormWithFun("rcswZdzmJgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


/**
 * �޸��ڶ�֤��
 * @return
 */
function updateZdzmJg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("�������������������ݣ������޸ģ�");
			return false;
		}
		showDialog('�ڶ�֤������޸�',780,350,'rcsw_zdzm_jggl.do?method=updateZdzmJg&zdzmjgid=' + rows[0]['zdzmjgid']);
	}
}

/**
 * �����ڶ�֤���������
 * @return
 */
function updateZdzmJgAction(){
	var sqly = jQuery('#sqly').val();
	
	if (sqly == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(sqly.length > 500){
		showAlertDivLayer("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "rcsw_zdzm_jggl.do?method=updateZdzmJgAction";
		ajaxSubFormWithFun("rcswZdzmJgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * ɾ���ڶ�֤�������¼
 * @return
 */
function deleteZdzmJg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		var zdzmjgids = [];
		for(i = 0 ; i < rows.length ; i ++){
			var zdzmjgid = rows[i]['zdzmjgid'];
			var sjly = rows[i]['sjly'];
			if(sjly == '0'){
				zdzmjgids.push(zdzmjgid);
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("rcsw_zdzm_jggl.do?method=deleteZdzmJgAction",{sqids:zdzmjgids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "rcsw_zdzm_jggl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_zdzm_jggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}