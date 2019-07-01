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

//���������˿���
function checkKg(type){
	var result = false;
	jQuery.ajaxSetup({async:false});
		jQuery.post("zjsy_kqcssz.do?method=checkSqsh&type="+type,{},function(data){
			if("1" == data){
				result = true;
			}
		},'json');
	jQuery.ajaxSetup({async:true});
	return result;
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


//���
function kqsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	
	var shzt = jQuery("#shzt").val();
	if (!checkKg("sh")) {
		showAlertDivLayer("����ѹرգ�����ϵ����Ա��");
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	} else if (rows.length == 1) {
		var url = "zjsy_kqsh.do?method=kqDgsh&id=" + rows[0]["id"] + '&shid=' + rows[0]["shid"];
		showDialog(jQuery("#gnmkmc").val(), 800, 600, url);
	} else {
		showDialog("�������", 500, 250, "zjsy_kqsh.do?method=kqPlsh&values="+ids.toString());
	}
}

/**
 * ������˱���
 * @return
 */
function saveSh(){
	var shzt = jQuery("#shjg").val();
	if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "zjsy_kqsh.do?method=kqDgsh&type=save";
	ajaxSubFormWithFun("KqshForm",url,function(data){
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

//������˱���
function savePlsh(shzt, shyj) {
	
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['shjg'] = shzt;
	map['shyj'] = shyj;
	
	jQuery.post("zjsy_kqsh.do?method=kqPlsh&type=save", map, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

/**
 * ���̲鿴
 * @return
 */
function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {		
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}

//��˳���
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var id = rows[0]["id"];
		var shid = rows[0]["shid"];
		
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("zjsy_kqsh.do?method=kqshcx",{shlc:splc,id:id,shid:shid},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
		},'json');
		}});
	}
}


function bjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='kqshCk(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

function kqshCk(id) {
	showDialog("�ճ���Ϊ�����ѯ", 720, 520,
			"zjsy_kqsh.do?method=kqshCk&id=" + id);
}