
/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �������ؽ��
 * @return
 */
function addCdjg(){
	showDialog('��������ʹ�ý��',780,485,'rcsw_cdgl_cdjg.do?method=cdjgSq');
}

/**
 * �������ؽ���������
 * @return
 */
function addCdjgAction(){
	var xxdm = jQuery("#xxdm").val();
	var xh = jQuery('#xh').val();
	var cdid = jQuery('#cdid').val();
	
	if (xh==""){
		showAlert("��ѡ��һ��ѧ����");
		return false;
	}
	if (cdid==""){
		showAlert("��ѡ��һ�����أ�");
		return false;
	}
	
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("����������С����"+50+",��ȷ�ϣ�");
			return false;
		}
	}
	var url = "rcsw_cdgl_cdjg.do?method=addCdjgAction";
		ajaxSubFormWithFun("rcswCdjgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'checkerror'){
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
 * ɾ�����ؽ��
 * @return
 */
function deleteCdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		var cdjgids = [];
		for(i = 0 ; i < rows.length ; i ++){
			var jgid = rows[i]['jgid'];
			var sjly = rows[i]['sjly'];
			if(sjly == '1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}else{
				cdjgids.push(jgid);
			}
				
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("rcsw_cdgl_cdjg.do?method=deleteCdjgAction",{cdjgids:cdjgids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * �޸ĳ��ؽ��
 * @return
 */
function updateCdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("�������ݲ����޸ģ�");
			return false;
		}
		showDialog('���ؽ���޸�',780,485,'rcsw_cdgl_cdjg.do?method=cdjgXg&jgid=' + rows[0]['jgid']);
	}
}

/**
 * ���³��ؽ���������
 * @return
 */
function updateCdjgAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("����������С����"+50+",��ȷ�ϣ�");
			return false;
		}
	}
	var url = "rcsw_cdgl_cdjg.do?method=cdjgXgAction";
		ajaxSubFormWithFun("rcswCdjgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


var DCCLBH = "rcsw_cdgl_cdjg.do";//dcclbh,�������ܱ��


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_cdgl_cdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}