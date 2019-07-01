function searchRs() {
	var map = getSuperSearch();
	var qrzt = jQuery("#qrzt").val();
	if (null!=qrzt&&qrzt != "") {
		map["qrzt"] = qrzt;
	}else{
		map["qrzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zzdqrView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function zzdqrView(jgid) {
	showDialog("��Ŀ�걨�鿴", 800, 500, "xgygl_zdjg.do?method=viewZzdjg&jgid="+jgid);
}

// �������
function qr() {
	var rows = jQuery("#dataTable").getSeletRow();
	var qrzt = jQuery("#qrzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫȷ�ϵļ�¼")
		return false;
	}
	if (qrzt == "ysh") {
		showAlertDivLayer("��ȷ�ϼ�¼�����ٴ�ȷ��");
		return false;
	} else if (rows.length == 1) {
		var url = "xgygl_zdqr.do?method=dgqr&jgid=" + rows[0]["jgid"];
		showDialog("ת�߶�����Աȷ��", 700, 400, url);
	} else {
		showDialog("����ȷ��", 500, 250, "xgygl_zdqr.do?method=plqr");
	}
}

function saveQr(jg){
	var qryj = jQuery("#qryj").val();
	if (qryj == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "xgygl_zdqr.do?method=dgbc&jg="+jg;
	ajaxSubFormWithFun("zzdjgForm",url,function(data){
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

// �������
function savePlqr(qrzt, qryj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["jgid"]);
	});
	jQuery.post("xgygl_zdqr.do?method=plqr&type=save", {
		qrzt:qrzt,
		qrids : guid,
		qryj:qryj
	}, function(data) {		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

// �л�Tabҳ
function selectTab(obj, shzt) {
	jQuery("#qrzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["qrzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["qrzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//��˳���
function cancel(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫȡ������˼�¼��");
	}else{		
		var jgid = rows[0]["jgid"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("xgygl_zdqr.do?method=qxqr",{jgid:jgid},function(data){
				if (data["success"] == false) {
					showAlertDivLayer(data["message"]);
				} else {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function(tag) {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}			
		},'json');
		}});
	}
}

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['zzdid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "xgygl_zzdgl_zdsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, zzdshExportData);
}

//��������
function zzdshExportData() {
	setSearchTj();//���ø߼���ѯ����
	var shzt = jQuery("#shzt").val();
	var url = "xgygl_zdsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}