//�������
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}else if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼")
		return false;
	}
	if (shzt != "dsh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else{
		var url = "dtjs_tsdzbsh.do?method=dgsh&dzbid="+rows[0]["dzbid"];
		showDialog("���", 700, 480, url);
	}
}

//��˳���
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	}else{
		var guid = new Array();
		jQuery.each(rows, function(i, row) {
			guid.push(row["dzbid"]);
		});
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("dtjs_tsdzbsh.do?method=cx",{values:guid.toString()},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
		},'json');
		}});
	}
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tsdzbView(\""
			+ rowObject["dzbid"]+"\");'>" + cellValue
			+ "</a>";
}

function tsdzbView(dzbid) {
	showDialog("��ɫ��֧���鿴", 800, 550, "dtjs_tsdzb.do?method=viewTsdzb&dzbid="+dzbid);
}

//�л�Tabҳ
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

var DCCLBH = "dtjs_tsdzb_tsdzbsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, tsdzbshExportData);
}

//��������
function tsdzbshExportData() {
	setSearchTj();//���ø߼���ѯ����
	var shzt = jQuery("#shzt").val();
	var url = "dtjs_tsdzbsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}