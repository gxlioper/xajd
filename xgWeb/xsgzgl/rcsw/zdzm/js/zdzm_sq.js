
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
function addZdzmSq(){
	var isopen = jQuery("#isopen").val();
	
	if("true"!=isopen){
		showAlertDivLayer("��ǰ����δ���ţ�����ϵ����Ա��");
		return false;
	}
	
	showDialog('�ڶ�֤������',780,350,'rcsw_zdzm_sqgl.do?method=addZdzmSq');
}
/**
 * �����ڶ�֤���������
 * @return
 */
function addZdzmSqAction(){
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
	
	var url = "rcsw_zdzm_sqgl.do?method=addZdzmSqAction&type=save";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * �������ύ�ڶ�֤���������
 * @return
 */
function addSubmitZdzmSqAction(){
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
	
	var url = "rcsw_zdzm_sqgl.do?method=addZdzmSqAction&type=submit";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
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
function updateZdzmSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
			showAlertDivLayer("�������Ѿ�����ˣ������޸ģ�");
			return false;
		}
		showDialog('�ڶ�֤���޸�',780,350,'rcsw_zdzm_sqgl.do?method=updateZdzmSq&zdzmsqid=' + rows[0]['zdzmsqid']);
	}
}

/**
 * �����ڶ�֤���������
 * @return
 */
function updateZdzmSqAction(){
	var sqly = jQuery('#sqly').val();
	
	if (sqly == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(sqly.length > 500){
		showAlertDivLayer("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "rcsw_zdzm_sqgl.do?method=updateZdzmSqAction&type=save";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * ���²��ύ�ڶ�֤���������
 * @return
 */
function updateSubmitZdzmSqAction(){
	var sqly = jQuery('#sqly').val();
	var shzt = jQuery('#shzt').val();
	var isopen = jQuery('#isopen').val();
	
	if("3"!=shzt&&"true"!=isopen){
		showAlertDivLayer("��ǰ����δ���ţ�����ϵ����Ա��");
		return false;
	}
	
	if (sqly == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "rcsw_zdzm_sqgl.do?method=updateZdzmSqAction&type=submit";
		ajaxSubFormWithFun("rcswZdzmSqForm",url,function(data){
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
function deleteZdzmSq(){
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
			jQuery.post("rcsw_zdzm_sqgl.do?method=deleteZdzmSqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
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
				jQuery.post("rcsw_zdzm_sqgl.do?method=cancelZdzmSqAction", {
					zdzmsqid : rows[0]['zdzmsqid'] , splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * �ύ�ڶ�֤������
 * @return
 */
function submitBusi(){
	var rows = jQuery("#dataTable").getSeletRow();
	var isopen = jQuery("#isopen").val();
	if('3' != rows[0]['shzt']&&"true"!=isopen){
		showAlertDivLayer("��ǰ����δ���ţ�����ϵ����Ա��");
		return false;
	}
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ��Ҫ�ύ�ļ�¼��");
	} else {
		if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_zdzm_sqgl.do?method=submitZdzmSqAction", {
					zdzmsqid : rows[0]['zdzmsqid'] , splcid : rows[0]['splcid'] , xh : rows[0]['xh'], shzt : rows[0]['shzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * �ڶ�֤�����
 */
function zdzmSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if(rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˼�¼��");
		return false;
	}
	
	if (rows.length == 1){
		showDialog("�ڶ�֤�����",750,500,"rcsw_zdzm_shgl.do?method=shZdzmsq&zdzmsqid="+rows[0]["zdzmsqid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splcid"]);
	} else {
		showDialog("�ڶ�֤���������",500,300,"rcsw_zdzm_shgl.do?method=zdzmPlsh");
	} 
}



/**
 * ������˱���
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["zdzmsqid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
	});

	jQuery.post(
		"rcsw_zdzm_shgl.do?method=zdzmPlsh&type=save",
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
 * �ύ�ڶ�֤�����
 * @return
 */
function submitZdzmSh(){
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "rcsw_zdzm_shgl.do?method=shZdzmsqAction";
		ajaxSubFormWithFun("rcswZdzmShForm",url,function(data){
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
function cancelZdzmSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "rcsw_zdzm_shgl.do?method=cancelZdzmsqAction";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splcid"],shid:rows[0]["shid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{zdzmsqid:rows[0]["zdzmsqid"]},function(result){
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
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['zdzmsqid']+"&splc="+rows[0]['splcid']);
	}
}

/**
 * ���̸���
 * @return
 */
function lcinfoSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['zdzmsqid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "rcsw_zdzm_sqgl.do";//dcclbh,�������ܱ��


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_zdzm_sqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}