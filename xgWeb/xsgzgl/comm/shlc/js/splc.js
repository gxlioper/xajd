function splc_qr_th(){
	/*var api = frameElement.api.get('parentDialog');
	var go_path = api.jQuery("#go_path").val();
	var sqid = api.jQuery("#sqid").val();
	var shid = api.jQuery("#shid").val();
	var gwid = api.jQuery("#gwid").val();
	var splc = api.jQuery("#splc").val();
	var shzt = api.jQuery("#shzt").val();
	var shyj = api.jQuery("#shyj").val();
	var thgw = jQuery("input[name='thgw']:checked").val();
	jQuery("#sqid").val(sqid);
	jQuery("#shid").val(shid);
	jQuery("#gwid").val(gwid);
	jQuery("#splc").val(splc);
	jQuery("#shzt").val(shzt);
	jQuery("#shyj").val(shyj);
	alert(go_path);
	showConfirmDivLayer("��ȷ���˻ظ�������",{"okFun":function(){
		var url = go_path+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});*/
	var shid = jQuery("#shid").val();
	var shlc = jQuery("#shlc").val();
	var shyj = jQuery("#shyj").val();
	var thgw = jQuery("input[name='thgw']:checked").val();
	confirmInfo("��ȷ��Ҫ�˻������?",function(ty){
		//alert(ty);
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=lcth",{shlc:shlc,shid:shid,thgw:thgw,shyj:shyj},function(data){
				frameElement.api.get('parentDialog').refershParent();
				iFClose();
			},'json');
		}
	});
}
/*
 * ���������˻� 
 * gwid ��ǰ������λid
 * splc �������� id
 */
function splc_th(shid,shlc,shyj){
	showDialog("���������˻�",530,310,'comm_spl.do?method=shth&shid='+shid+"&shlc="+shlc+"&shyj="+shyj);
}
/*
 * �������̳���
 * shid ���id
 * splc ��������id 
 */
function splc_cx(splc,shid){
	confirmInfo("��ȷ��Ҫ����������?",function(ty){
		//alert(ty);
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					//if (parent.window){
						//refersh();
						jQuery("#dataTable").reloadGrid();
					//}
				}});
			},'json');
		}
	});
}
/*
 * ����[���һ�����ɳ���]
 */
function cxsh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else {
		splc_cx(rows[0]["splc"],rows[0]["shid"]);
	}
}

/*
 * ����[���һ���ɳ���]
 */
function cxshnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
	} else {
		splc_cx_new(rows[0]["splc"],rows[0]["shid"]);
	}
}
/*
 * ����
 */
function cxshnew_splc(obj){
	var sfkq=obj.data.sfkq;
	var rows = jQuery("#dataTable").getSeletRow();
	if(sfkq=="1"){//���� �����һ���ɳ���
		if (rows.length != 1){
			alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
		} else {
			splc_cx_new(rows[0][obj.data.splc],rows[0]["shid"]);
		}
	}else{
		splc_cx(rows[0][obj.data.splc],rows[0]["shid"]);

	}
}
/*
 * �������̳���[���һ���ɳ���]
 * shid ���id
 * splc ��������id 
 */
function splc_cx_new(splc,shid){
	//���һ��������˺���õ�·��
	var cancelPath = jQuery("#cancelPath").val();
	confirmInfo("��ȷ��Ҫ����������?",function(ty){
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
					// �ж��Ƿ����һ������(1:���һ�������ɹ���
					if("1" == data["cancelFlg"]){
						jQuery.post(cancelPath,{splc:splc,shid:shid},function(result){
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