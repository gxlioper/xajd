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
	return "<a href='javascript:void(0);' class='name' onclick='zzdsqView(\""
			+ rowObject["zzdid"]+"\");'>" + cellValue
			+ "</a>";
}

function zzdsqView(sqid) {
	showDialog("��Ŀ�걨�鿴", 800, 500, "xgygl_zdsq.do?method=viewZzdsq&zzdid="+sqid);
}

// �������
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if (rows.length == 1) {
		var url = "xgygl_zdsh.do?method=sbDgsh&zzdid=" + rows[0]["zzdid"] + '&shid=' + rows[0]["shid"];
		showDialog("���", 700, 480, url);
	} else {
		showDialog("�������", 500, 250, "xgygl_zdsh.do?method=sbPlsh");
	}
}
// �������
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["zzdid"]);
		gwid.push(row["gwid"]);
	});
	jQuery.post("xgygl_zdsh.do?method=sbPlsh&type=save", {
		shzt : shzt,
		id : guid,
		gwids : gwid,
		shyj:shyj
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
	}else{
		if(rows[0]["shzt"] == '1') {
			var zzdid = rows[0]["zzdid"];
			var flg = true;
			jQuery.ajaxSetup({async:false});
			jQuery.post("xgygl_zdsq.do?method=isGlyqr", {zzdid:zzdid}, function(data) {
				if(data){
					flg = false;					
				}	      		
				}, 'json');
			jQuery.ajaxSetup({async:true});
			if(!flg){
				showAlertDivLayer("��Ԣ����Ա��ȷ�ϣ����ܳ�����");
				return false;
			}
		}		
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["zzdid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("xgygl_zdsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("xgygl_zdsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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