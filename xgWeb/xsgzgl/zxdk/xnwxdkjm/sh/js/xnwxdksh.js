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
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

//�������
function dgsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
//	var shkg = jQuery("#shkg").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��")
		return false;
	}
//	if ("0" == shkg) {
//		showAlertDivLayer("����ѹرգ�����ϵ����Ա��");
//		return false;
//	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ���ˣ�");
		return false;
	} else if (rows.length == 1) {
		var url = "xnwxdkjm_sh.do?method=xnwxdkDgsh&sqid=" + rows[0]["sqid"] + '&xh='
				+ rows[0]["xh"] + '&shid=' + rows[0]["shid"];
		showDialog("���", 770, 550, url);
	} else {
		showDialog("�������", 500, 250, "xnwxdkjm_sh.do?method=xnwxdkPlsh");
	}
}
// �������
function savePlsh(shzt, shyj,tyjml,zd1) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();

	jQuery.each(rows, function(i, row) {
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});
	jQuery.post("xnwxdkjm_sh.do?method=xnwxdkPlsh&type=save", {
		shzt : shzt,
		splc : rows[0]["splc"],
		id : guid,
		gwids : gwid,
		xhs : xhs,
		shyj : shyj,
		zd1 : zd1,
		zd3 : tyjml
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
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("xnwxdkjm_sh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("xnwxdkjm_sh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='shview(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function shview(sqid, xh) {
	showDialog("�鿴", 770, 510, "xnwxdkjm_sh.do?method=DkshView&sqid="
			+ sqid + "&xh=" + xh);
}