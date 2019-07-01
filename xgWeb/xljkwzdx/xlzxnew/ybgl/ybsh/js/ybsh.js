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
	alert(rows[0]['splcid']);
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbid']+"&splc="+rows[0]['splcid']);
	}
}

//�������
function khsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ���ˣ�");
		return false;
	} else if (rows.length == 1) {
		var url = "xlzxnew_ybsh.do?method=ybsh&sbid=" + rows[0]["sbid"] + '&shid=' + rows[0]["shid"];
		showDialog("���", 770, 550, url);
	} else {
		showDialog("�������", 500, 250, "xlzxnew_ybsh.do?method=ybPlsh");
	}
}
// �������
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var txrss = new Array();
	var splcids = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["sbid"]);
		gwid.push(row["gwid"]);
		txrss.push(row["txr"]);
		splcids.push(row["splcid"]);
	});
	jQuery.post("xlzxnew_ybsh.do?method=saveYbplsh", {
		shzt : shzt,
		splcids : splcids,
		id : guid,
		gwids : gwid,
		txrss : txrss,
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
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sbid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("xlzxnew_ybsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("xlzxnew_ybsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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



/**
 * ����
 * @param cellValue
 * @param rowObject
 * @return
 */

function link(cellValue,rowObject){

	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_ybsb.do?method=ckYbsb&sbid=" + rowObject['sbid'] + "')" + "\"";
	if(rowObject['sbid'] == '' || rowObject['sbid'] == null){
		onclickfn = "onclick=\"" + "showAlertDivLayer('��Ϣδ�ϱ���')" + "\"";
	}
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}
