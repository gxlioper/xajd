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
function gzjlsqCk(sqid, zgh) {
	showDialog("��˲�ѯ", 750, 550, "gzjlsq.do?method=gzjlsqCk&sqid=" + sqid
			+ "&zgh=" + zgh);
}

function zghLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='gzjlsqCk(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
// �������
function gzjlsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	var shkg = jQuery("#shkg").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}
	if ("0" == shkg) {
		showAlertDivLayer("����ѹرգ�����ϵ����Ա��");
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if (rows.length == 1) {
		var url = "gzjlsh.do?method=gzjlDgsh&sqid=" + rows[0]["sqid"] + '&zgh='
				+ rows[0]["zgh"] + '&shid=' + rows[0]["shid"];
		showDialog("������¼���", 700, 550, url);
	} else {
		showDialog("������¼�������", 500, 250, "gzjlsh.do?method=gzjlPlsh");
	}
}
// �������
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var zghs = new Array();
	var yxgss = new Array();
	var gss = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		zghs.push(row["zgh"]);
		
	});
	jQuery.post("gzjlsh.do?method=gzjlPlsh&type=save", {
		shzt : shzt,
		splc : rows[0]["splc"],
		id : guid,
		gwids : gwid,
		zghs : zghs,
		gss:gss,
		shyj : shyj,
		yxgss:yxgss
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
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("gzjlsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "gzjl_gzjlsh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khshExportData);
}

//��������
function khshExportData() {
	setSearchTj();//���ø߼���ѯ����
	var shzt = jQuery("#shzt").val();
	var url = "gzjlsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}