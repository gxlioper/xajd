
/**
 * ����ʹ�������ѯ
 * @return
 */
function cdxxck(){
	showDialog('����ʹ����Ϣ',700,450,'rcsw_cdgl_cdsqck.do?method=cdsqjlCx')
}

/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
  var map = getSuperSearch();
  jQuery("#dataTable").reloadGrid(map);
}

/**
 * ������������
 * @return
 */
function addCdsq(){
	showDialog('��������',780,500,'rcsw_cdgl_cdsq.do?method=cdsqSq');
}

/**
 * �����������뱣�����
 * @return
 */
function addCqsqAction(){
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
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("���Ƚ��ܡ���������Э�顷 ��");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=addCqsqAction";
		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
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
 * �������ύ�������뱣�����
 * @return
 */
function addSubmitCdsqAction(){
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
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("���Ƚ��ܡ���������Э�顷 ��");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=addSubmitCqsqAction";
		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
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
 * �ύ������������
 * @return
 */
function submitBusi(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ��Ҫ�ύ�ļ�¼��");
		return false;
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		}

		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				if(shzt!="3"){
					// ��֤�Ƿ���ύ
					jQuery.post("rcsw_cdgl_cdsq.do?method=checkSfktj", {
						cdid:rows[0]["cdid"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("������ĳ����ѹر����룬�޷��ύ����������ϵ����Ա��");
							return false;
						}else{
	
							// �ύ
							jQuery.post("rcsw_cdgl_cdsq.do?method=submitCdsqAction", {
								sqid : rows[0]['sqid']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
					
				}else{
					jQuery.post("rcsw_cdgl_cdsq.do?method=submitCdsqAction", {
						sqid : rows[0]['sqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			
			}
		});
	}
}

/**
 * ɾ����������
 * @return
 */
function deleteCdsq(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		for(i=0 ; i< rows.length; i++){
			if(rows[i]['shzt'] != '0'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("rcsw_cdgl_cdsq.do?method=deleteCdsqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �޸ĳ�������
 * @return
 */
function updateCdsq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		}
		showDialog('���������޸�',780,500,'rcsw_cdgl_cdsq.do?method=cdsqXg&sqid=' + rows[0]['sqid']);
	}
}

/**
 * ���³������뱣�����
 * @return
 */
function updateCdsqAction(type){
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
	var xysfilepath = jQuery("#xysfilepath").val();
	if (xysfilepath && jQuery("#xys_checkbox") && !jQuery("#xys_checkbox").attr('checked')){
		showAlert("���Ƚ��ܡ���������Э�顷 ��");
		return false;
	}
	var url = "rcsw_cdgl_cdsq.do?method=cdsqXgAction&type="+type;
	
	var shzt = jQuery("#shzt").val();
	if(shzt!="3" && type=='submit'){
		// ��֤�Ƿ���ύ
		jQuery.post("rcsw_cdgl_cdsq.do?method=checkSfktj", {
			cdid:jQuery("#cdid").val()
		}, function(data) {
			if(data ==null || data=="false"){
				showAlertDivLayer("������ĳ����ѹر����룬�޷��ύ����������ϵ����Ա��");
				return false;
			}else{

				ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
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
		});
		
	}else{

		ajaxSubFormWithFun("rcswCdsqForm",url,function(data){
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
}

/**
 * ����
 * @return
 */
function cancle(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ��Ҫ�����ļ�¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(rows[i]['shzt'] != '5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_cdgl_cdsq.do?method=cancelCdsqAction", {
					sqid : rows[0]['sqid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}



/**
 * �����������
 */
function cdshSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫ��˼�¼��");
		return false;
	} else if(rows.length == 1){
		showDialog("�����������",750,485,"rcsw_cdgl_cdsh.do?method=cdshSh&sqid="+rows[0]["sqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splcid"]);
	} else {
		showDialog("�����������",500,300,"rcsw_cdgl_cdsh.do?method=cdsqPlsh");
	}
}


/**
 * ���������������
 * @param shzt
 * @param shyj
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs  = new Array();
	var splc = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
		
	});
	
	jQuery.post(
			"rcsw_cdgl_cdsh.do?method=cdsqPlsh&type=save",
			{
			 shzt:shzt,
			 id:guid,
			 gwids:gwid,
			 xhs:xhs,
			 shyj:shyj,
			 splcs:splc
			 
			},function(data){
				
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
			},
			'json'
	);
	
}



/**
 * �ύ�����������
 * @return
 */
function submitCdshSh(){
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "ͨ��";
	}
	if(jQuery("#shjg").val() == "2"){
		message = "��ͨ��";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "�˻�";
	}
	
	
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "rcsw_cdgl_cdsh.do?method=cdshShAction";
		ajaxSubFormWithFun("rcswCdshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

/**
 * �������
 * @return
 */
function cancelCdshSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "rcsw_cdgl_cdsh.do?method=cancelCdshAction";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splcid"],shid:rows[0]["shid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{sqid:rows[0]["sqid"]},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});

	}
}

/**
 * ���̸���
 * @return
 */
function lcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("��ѡ�����ύ�ļ�¼��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/**
 * ��ʦ����Ի�   ����
 */
function pj(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼�������ۣ�");
	} else {
		if(rows[0]['shzt'] != '1'){
			showAlertDivLayer("ֻ�����ͨ���ļ�¼�������ۣ�");
			return false;
		}else{
			jQuery.post("rcsw_cdgl_cdjg.do?method=sfpj",{sqid:rows[0]["sqid"]},function(data){
				if(data["flag"]=="0"){
					showAlertDivLayer("�����ۣ�");
					return false;
				}else{
					showDialog("����",530,310,'rcsw_cdgl_cdjg.do?method=pj&sqid='+rows[0]['sqid']);
				}
			},'json');
		}
	}
}

/**
 * ���̸���
 * @return
 */
function lcinfoSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ�����ύ�ļ�¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "rcsw_cdgl_cdsq.do";//dcclbh,�������ܱ��


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_cdgl_cdsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}