
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='hdblsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}

function hdblsqView(sqid) {
	showDialog("���¼����鿴", 800, 550, "hdgl_hdblsq.do?method=viewHdblsq&sqid="+sqid);
}


function saveHdblsq(type) {
	var download = jQuery(".MultiFile-label").length;
	var hdxs = jQuery("#hdxs").val();
	var jzlx = jQuery("#jzlx").val();

	var ids = "xh-hdmc-hdsj-hdxs-hdlx-zbf-xsxxlx-jzjb";
    if("����" == hdxs){
        ids += "-zjrxm-zjrdw-zjrzc-zjrzw";
    }
	if(!check(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}

    var nlbqLen = jQuery("[name='nlbqs']:checked").length;
    if(nlbqLen > 3){
        showAlert("������ǩ���ֻ��ѡ��������ȷ�ϣ�");
        return false;
    }
    if("�γ�" == hdxs){
        if(jzlx == null || jzlx == ''){
            showAlert("��ѡ��γ̼���");
            return false;
        } else {
            var zxkclx = jQuery("#zxkclx").val();
            if(zxkclx == ""){
                showAlert("��ѡ����ѡ�γ����ͣ�");
                return false;
            }
        }
    }
	if(download < 1){
		showAlert("���ϴ�����");
		return false;
	}
	
	var url = "hdgl_hdblsq.do?method=saveSq&type=" + type;
	ajaxSubFormWithFun("hdblsqshForm", url, function(data) {
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

function add() {
	var url = "hdgl_hdblsq.do?method=addHdblsq";
	var title = "�������¼����";
	showDialog(title, 800, 550, url);
}

function update() {
	//var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var shzt = rows[0]["shzt"];
//		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
//			return false;
//		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'hdgl_hdblsq.do?method=updateHdblsq&sqid=' + rows[0]["sqid"];
		var title = "���¼�����޸�";
		showDialog(title, 800, 550, url);
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
				jQuery.post("hdgl_hdblsq.do?method=delHdblsq", {
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
	//var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
//	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
//		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
//		return false;
//	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("hdgl_hdblsq.do?method=submitHdblsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
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
				jQuery.post("hdgl_hdblsq.do?method=cancelHdblsq", {
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
		showDialog("���¼�����������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function changeHdxs(){
    var hdxs = jQuery("#hdxs").val();
    if("�γ�" == hdxs){
        jQuery("#jzlxTr").show();
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
    }else if("����" == hdxs){

        jQuery("tr[name='zjrxx_tr']").show();
        jQuery("#lx_span").html("��������");
        jQuery("#con_span").html("��������");

        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");
    }else{
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");

    }
}
function JzInfoEmpty(){
    var tr = jQuery("tr[name='zjrxx_tr']");
    tr.hide();
    tr.find("input").val("");
    tr.find("textarea").val("");
    tr.find("select").val("");
    jQuery("#lx_span").html("�����");
    jQuery("#con_span").html("����ݼ��ĵ�");
}
/**
 * �γ̼���change
 */
function kcjbChange(){
    var v = jQuery("#jzlx").val();
    if("003" == v){
        jQuery("#zxkclxTh").removeAttr("style");
        jQuery("#zxkclxTd").removeAttr("style");
    } else {
        jQuery("#zxkclxTh").attr("style","display:none");
        jQuery("#zxkclxTd").attr("style","display:none");
    }
}