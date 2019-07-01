var checkId="stxmmc-stlbdm-xmlbdm-xn-gkdw-fzrlb-stfzr-stclsj";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='StsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function StsqView(id) {
	showDialog("��������鿴", 800, 450, "stglStsq.do?method=viewStsq&sqid="+id);
}
function saveStsq(type) {
	var flg=true;
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
    var obj = jQuery("input[name='zgh']");
    if(obj.length == 0){
        showAlert("�����ָ����ʦ��");
        return false;
    }
	if(checkzsinput_yz(jQuery("#stsm"),500) == false || checkzsinput_yz(jQuery("#sthjqk"),500) == false){
		return false;
	}

	jQuery("#xhs").val(getxhs);
	jQuery("#ssbm").attr("disabled",false);
	jQuery("#zdlszc").attr("disabled",false);
	jQuery("#zdlslxfs").attr("readonly",false);
	var url = "stglStsq.do?method=saveStsq&type=" + type;
	ajaxSubFormWithFun("StsqForm", url, function(data) {
		 if(data["message"]=="�ύ�ɹ���"||data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 flg = false;
    		 showAlert(data["message"],{},{"clkFun":function(){
 				jQuery("#ssbm").attr("disabled",true);
 				jQuery("#zdlszc").attr("disabled",true);
 				jQuery("#zdlslxfs").attr("readonly",true);
 		    }});
    	}
		});
}

function getxhs(){
    var xhs = "";
    var xhobj = jQuery("[name='zgh']");
    jQuery(xhobj).each(function(i){
        xhs +=this.value;
        if(xhobj.length-1 != i){
            xhs +=",";
        }

    });
    return xhs;
}

//�޸�
function editStsq(type) {
	var flag = true;
	jQuery("select").attr("disabled",false);
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
    var obj = jQuery("input[name='zgh']");
    if(obj.length == 0){
        showAlert("�����ָ����ʦ��");
        return false;
    }
	if(checkzsinput_yz(jQuery("#stsm"),500) == false || checkzsinput_yz(jQuery("#sthjqk"),500) == false){
		return false;
	}
    jQuery("#xhs").val(getxhs);
	jQuery("#ssbm").attr("disabled",false);
	jQuery("#zdlszc").attr("disabled",false);
	jQuery("#zdlslxfs").attr("readonly",false);
	var url = "stglStsq.do?method=saveEditStsq&type=" + type;
	ajaxSubFormWithFun("StsqForm", url, function(data) {
		 if(data["message"]=="�ύ�ɹ���"||data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 flag = false;
    		 showAlert(data["message"],{},{"clkFun":function(){
    				jQuery("#ssbm").attr("disabled",true);
    				jQuery("#zdlszc").attr("disabled",true);
    				jQuery("#zdlslxfs").attr("readonly",true);
    		    }});
    		}
		});
	
}

function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}
	var url = "stglStsq.do?method=addStsq";
	var title = "��������";
	showDialog(title, 800, 450, url);
}
function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'stglStsq.do?method=editStsq&sqid=' + rows[0]["sqid"];
		var title = "���������޸�";
		showDialog(title, 800, 450, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("stglStsq.do?method=delStsq", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// �ύ
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("stglStsq.do?method=submitStsq", {
				values : ids.toString(),
				xmlbdm : rows[0]["xmlbdm"]
			}, function(data) {
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});

			}, 'json');
		}
	});
	
	

}
// ����
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("stglStsq.do?method=cancelStsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("���������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "stgl_stgl_stsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khsqExportData);
}

//��������
function khsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "stglStsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ajax��̬��ȡ��Ŀ���list
function getXmlblist(stlbdm){
	var rs = null;
	var url = "stglXmlbDmwh.do?method=getXmlblist";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:'stlbdm='+stlbdm,
	async: false,
	success:function(result){
		if(result==null||result=="null"){
			rs = null;
		}else{
			rs = result['html'];;
		}
	 }
    });
	return rs;
};