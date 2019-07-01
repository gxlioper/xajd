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

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+ids+"&splc="+rows[0]['splc']);
	}
}

//�������
function dgsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
//	var shkg = jQuery("#shkg").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}
//	if ("0" == shkg) {
//		showAlertDivLayer("����ѹرգ�����ϵ����Ա��");
//		return false;
//	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if (rows.length == 1) {
		var url = "cxdd_pysh.do?method=Dgsh&bjid=" + rows[0]["bjid"] + '&bjdm='
				+ rows[0]["bjdm"] + '&shid=' + rows[0]["shid"]+'&bjmc='+rows[0]["bjmc"];
		showDialog("���", 770, 550, url);
	} else {
		showDialog("�������", 500, 250, "cxdd_pysh.do?method=Plsh");
	}
}
// �������
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var bjdms = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["bjid"]);
		gwid.push(row["gwid"]);
		bjdms.push(row["bjdm"]);
	});
	jQuery.post("cxdd_pysh.do?method=Plsh&type=save", {
		shzt : shzt,
		splc : rows[0]["splc"],
		id : guid,
		gwids : gwid,
		bjdms : bjdms,
		shyj : shyj
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//��˳���
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var bjid = rows[0]["bjid"];
		var shzt = rows[0]["shzt"];
		var bjdm = rows[0]["bjdm"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("cxdd_pysh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("cxdd_pysh.do?method=cancelSh",{bjid:bjid,shzt:shzt,bjdm:bjdm},function(result){
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
		}});
	}
}

//�鿴ѧ������
function bjrsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='bjrsview(\""
			+ rowObject["bjid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}

function bjrsview(bjid, bjdm) {
	showDialog("�鿴", 770, 510,"cxdd_pysb.do?method=getXsView&bjdms="+bjdm+"&bjid="+bjid);
}