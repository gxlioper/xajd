function searchRs() {
	var map = getSuperSearch();
	var fpzt = jQuery("#fpzt").val();
	if (null!=fpzt&&fpzt != "") {
		map["fpzt"] = fpzt;
	}else{
		map["fpzt"] = "dfp";
	}
	jQuery("#dataTable").reloadGrid(map);
}



//�л�Tabҳ
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "dfp") {
		jQuery("#li_plfp").css("display", "");
		jQuery("#li_qxfp").css("display", "none");
		jQuery("#li_fpxq").css("display", "none");
		jQuery("#li_fptj").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}else if (fpzt == "yth") {
		jQuery("#li_plfp").css("display", "");
		jQuery("#li_qxfp").css("display", "none");
		jQuery("#li_fpxq").css("display", "none");
		jQuery("#li_fptj").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting3);
	} else{
		jQuery("#li_plfp").css("display", "none");
		jQuery("#li_qxfp").css("display", "");
		jQuery("#li_fpxq").css("display", "");
		jQuery("#li_fptj").css("display", "");
		var map = getSuperSearch();
		map["fpzt"]="yfp";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//�ֹ�����
function sdfpLink(cellValue, rowObject) {
	return "<label class='btn_01' onclick='fpcz(\""
			+ rowObject["lddm"] + "\",\"" + rowObject["qsgxid"] + "\",\"" + rowObject["qsh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["id"] + "\",\"" +1+ "\");'>"
			+ "�ֹ�����</label>";
}
//����
function tzfpLink(cellValue, rowObject) {
	return "<label class='btn_01' onclick='fpcz(\""
			+ rowObject["lddm"] + "\",\"" + rowObject["qsgxid"] + "\",\"" + rowObject["qsh"] + "\",\"" + rowObject["nj"] + "\",\"" + rowObject["id"] + "\",\"" +2+ "\");'>"
			+ "����</label>";
}
function fpcz(lddm,qsgxid,qsh,nj,id,czlx) {
	if("2"==czlx){
		if(null==qsgxid||""==qsgxid||"null"==qsgxid){
			showDialog("�������", 450, 200, "xszyXsqshf.do?method=fpcz&id="+id+"&type="+"update");
		}else{
			showAlertDivLayer("��������ƥ������֮�ѣ�����������������������������ƥ�������֮�ѣ�");
			return false;
		}
		
	}else{
		showDialog("�ֶ�����", 450, 200, "xszyXsqshf.do?method=fpcz&lddm="
			+ lddm + "&qsh=" + qsh+"&nj="+nj+"&type="+"save");
	}
}
function plfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==0){
		showAlertDivLayer("��ѡ����Ҫ������������ң�");
		return false;
	}
	var qsh = new Array();
    var sfhhqs = new Array();
    var qsxb = new Array();
	jQuery.each(rows, function(i, row) {
		
		qsh.push(row["qsh"]);
		sfhhqs.push(null==row["sfhhqs"]?'��':row["sfhhqs"]);
		qsxb.push(row["qsxb"]);
	});
	showDialog("��������", 500, 200, "xszyXsqshf.do?method=plfp&qsh=" + qsh+"&sfhhqs="+sfhhqs+"&qsxb="+qsxb);
}

//�������䱣��
function savePlfp(ssyxdm) {
	var rows = jQuery("#dataTable").getSeletRow();
	var lddm = new Array();
    var qsh = new Array();
	jQuery.each(rows, function(i, row) {
		if("��"!=row["sfhhqs"]){
			lddm.push(row["lddm"]);
			qsh.push(row["qsh"]);
		}
	});
	jQuery.post("xszyXsqshf.do?method=savePlfp&ssyxdm="+ssyxdm, {
		lddm : lddm.join(","),
		qsh : qsh.join(",")
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}
//����ȡ�� 
function qxFp(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var qshs="";
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ���ķ����¼��");
		return false;
	} 
	jQuery.each(rows, function(i, row) {
		if(null!=row["qsgxid"]&&""!=row["qsgxid"]){
			if(i!=0){
				qshs+=",";
			}
			qshs+=row["qsh"];
		}
	});
	if(""!=qshs){
		showAlertDivLayer("����"+qshs+"�ѷ�������֮���޷�ȡ����");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫȡ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xszyXsqshf.do?method=qxFp",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//
