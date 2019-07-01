
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='kqsqView(\""
			+ rowObject["sqid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}
function kqsqView(sqid, bjdm) {
	showDialog(jQuery("#gnmkmc").val()+"�鿴", 800, 500, "zwzxKqsq.do?method=viewKqsq&sqid="
			+ sqid + "&bjdm=" + bjdm);
}
function saveKqsq(type) {
	var flg=true;
	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
		if (flg){
			var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
			var xh= jQuery(n).find("td").eq(1).text();
			var kkjs = jQuery(n).find("input[name=kkjs]").val();
			var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
			obj.xh=xh;
			obj.kkjs=kkjs;
			obj.qqlxdm=qqlx;
			obj.ylzd1=ylzd1;
			objArr.push(obj);
			flg = (qqlx != "" );
		}
	});
	var validateFlag = true;
		validateFlag = checkKqxx(flg,type);
	if(validateFlag){
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zwzxKqsq.do?method=saveKqsq&type=" + type;
	ajaxSubFormWithFun("ZwzxKqsqForm", url, function(data) {
		 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
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

}

function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
	var url = "zwzxKqsq.do?method=addKqsq";
	var title = jQuery("#gnmkmc").val()+"����";
	showDialog(title, 800, 500, url);
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

		var url = 'zwzxKqsq.do?method=editKqsq&sqid=' + rows[0]["sqid"];
		var title = jQuery("#gnmkmc").val()+"�޸�";
		showDialog(title, 800, 500, url);
	}

}

function fdyfk() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ������д�ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'zwzxKqsq.do?method=fdyfk&sqid=' + rows[0]["sqid"];
		var title = "������ϰ���ڷ���";
		showDialog(title, 800, 500, url);
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
				jQuery.post("zwzxKqsq.do?method=delKqsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>������";
							showAlertDivLayer(mes);
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
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
		 if(rows[0]["qqxss"]!=rows[0]["qqrs"]){
		    	showAlertDivLayer('ȱ������Ϊ' + rows[0]["qqrs"] + '�ˣ�ȱ��ѧ����Ϊ'+rows[0]["qqxss"]+'��,���޸ģ�');
				return false;
		    }
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("zwzxKqsq.do?method=submitKqsq", {
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
				jQuery.post("zwzxKqsq.do?method=cancelKqsq", {
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
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "rcsw_zwzxkq_kqsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khsqExportData);
}

//��������
function khsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zwzxKqsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}