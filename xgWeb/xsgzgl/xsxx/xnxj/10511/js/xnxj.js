
/**
 * ����ѧ��С��
 * @return
 */
function saveXnxj(saveType){

	var id = jQuery('#id').val();
	
	if(id != ""){
		showAlertDivLayer("��ѧ������дѧ��С�ᣬ��ȷ�ϣ�");
		return false;
	}

	if (jQuery("#xjnr").val()=="" || jQuery("#xh").val() == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	
	//С�����ݲ��ó���2000�ַ�
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("С����������ֳ���Ϊ"+2000+",���Ѿ���������ȷ�ϣ�");
		obj.focus();
		return false;
	}
	
	var url = "xsxx_xnxj_xjtxgl.do?method=doXnxjsq&type="+saveType;
	
	ajaxSubFormWithFun("xnxjForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * ����ѧ��С��
 * @param type
 * @return
 */
function updateXnxj(type){
	
	if (!checkNull('xjnr')){
		return false;
	}
	//С�����ݲ��ó���2000�ַ�
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("����С�����"+2000+"�֣�");
		obj.focus();
		return false;
	}
	var url = "xsxx_xnxj_xjtxgl.do?method=doUpdateXnxj&type="+type;
	ajaxSubFormWithFun("xnxjForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * ѧ��С������
 * @return
 */
function xnxjsq(){
	var kgzt = jQuery("#kgzt").val();
	if(kgzt==null||kgzt==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("n" == kgzt){
		showAlertDivLayer("��ǰδ����ѧ��С����д������ϵ����Ա��");
		return false;
	}
	
	showDialog('ѧ��С����д',780,440,'xsxx_xnxj_xjtxgl.do?method=xnxjsq');
}


/**
 * ѧ��С��--�޸�
 * @return
 */
function xnxjUpdate(){
	var kgzt = jQuery("#kgzt").val();
	if(kgzt==null||kgzt==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shjg = rows[0]["shjg"];
		
		if("-1" == shjg){
			showAlertDivLayer("��Ա��δ��дѧ��С�ᣬ������д��");
			return false;
		}
		
		if ("0" != shjg && "3" != shjg){
			showAlertDivLayer("�������Ѿ�����ˣ������޸ģ�");
			return false;
		}
		showDialog("ѧ��С���޸�",780,450,"xsxx_xnxj_xjtxgl.do?method=xnxjxg&type=update&id="+rows[0]["id"]);
	}
}

/**
 * ѧ��С��--ɾ��
 * @return
 */
function xnxjDelete(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���������¼��");
	} else {
		
		for(var i=0;i<rows.length;i++){
			if(rows[i]['shjg']!='0'&& "" != rows[i]['shjg'] && null != rows[i]['shjg']){
				showAlertDivLayer("ֻ��ɾ��δ��˵ļ�¼��");
				return false;
			}else if("" == rows[i]['shjg'] || null == rows[i]['shjg']){
				showAlertDivLayer("δ����ļ�¼,����ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ���������¼��",{"okFun":function(){
			jQuery.post("xsxx_xnxj_xjtxgl.do?method=doXnxjdel",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ѧ��С��--����
 * @return
 */
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shjg']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxx_xnxj_xjtxgl.do?method=doXnxjCancel", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * ѧ��С��--�����ύ
 * @return
 */
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shjg']!='0'&&rows[i]['shjg']!='3' && "" != rows[i]['shjg'] && null != rows[i]['shjg']){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				if(rows[0]['shjg']=='3'){
					jQuery.post("xsxx_xnxj_xjtxgl.do?method=doSubmit", {
						values : ids.toString()
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
					
				}else{
					jQuery.post("xsxx_xnxj_xjtxgl.do?method=checkKg",{}, function(data){
						if(data == null||data=="false"){
							showAlertDivLayer("ѧ��С����д�����ѹرգ��޷��ύ������ϵ����Ա");
							return false;
						}else{
							jQuery.post("xsxx_xnxj_xjtxgl.do?method=doSubmit", {
								values : ids.toString()
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
				}
			}
		});
	}
}

/**
 * ѧ��С��--���̸���
 * @return
 */
function xnxjLcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shjg"]=="0" || rows[0]['shjg'] == '-1'){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splid']);
	}
}

/**
 * �������Ѵ�����ǩ�л�
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * ѧ��С��--���
 * @return
 */
function xnxjSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˼�¼��");
	} else {
		showDialog("ѧ��С�����",750,550,"xsxx_xnxj_xjtxgl.do?method=toXnxjDgsh&id="+rows[0]["id"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&splid="+rows[0]["splid"]);
	} 
}

/**
 * �������
 * @return
 */
function cancelXnxjSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "xsxx_xnxj_xjtxgl.do?method=doXnxjShCancel";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["splid"],shid:rows[0]["shid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{id:rows[0]["id"]},function(result){
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
 * ������˱���
 * @param shzt
 * @return
 */
function savePlsh(shzt,rddc,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xszz_knsrd.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

/**
 * ������˲���
 */
function saveXnxjSh(){
	var shzt = jQuery("#shjg").val();
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length > 200){
		showAlertDivLayer("���������ܳ���200�֣�");
		return false;
	}
	
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "xsxx_xnxj_xjtxgl.do?method=doXnxjSh";
		ajaxSubFormWithFun("xnxjForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

function saveShzt(){
	var shzt = jQuery("#shjg").val();
	if(jQuery("#shjg").val() == "0"){
		showAlertDivLayer("��ѡ�����״̬��");
		return false;
	}
	
	var shyj = jQuery("#shyj").val();
	if (jQuery.trim(shyj) == ""){
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
	showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
		var url = "rcsw_rcxwwh_rcxwshgl.do?method=rcxwDgsh&type=save";
		ajaxSubFormWithFun("rcxwshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

//��ӡ����
function printXnxjSq(url) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	var vals = [];
	
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		
		for(i = 0 ; i < rows.length ; i++){
			if(rows[i]['txzt'] != '1'){
				showAlertDivLayer("ѧ��С��δ��д��");
				return ;
			}
			vals[i] = rows[i]['xh'] + '|' + rows[i]['xn'];
		}
		
		var url = url + "&vals=" +vals.join(',');
		window.open(url);
	}
}

