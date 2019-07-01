/*
 * ����Դ 2016-04-26
 * 
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjrsLink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='bjrsview(\""
	+ rowObject["bjid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
	+ "</a>";
}

function bjrsview(bjid, bjdm) {
	showDialog("�鿴", 770, 510, "cxdd_pysb.do?method=getXsView&bjdms="+bjdm+"&bjid="+bjid);
}

/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("���̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['bjid'] + "&splc=" + rows[0]['splc']);
	}
}

//�ύ
function bjtj() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("cxdd_pysb.do?method=submitBusi", {
					values : ids.toString(),bjdm : rows[0]["bjdm"]
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//����
function bjtjcx() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
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
				jQuery.post("cxdd_pysb.do?method=cancelZssq", {
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

function whpy(){
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer("�����ϱ��ڼ��ڣ����ܽ�������ά����");
			return false;
		}
		showAlertDivLayer("��ѡ��һ����Ҫά���ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
			return false;
		}
		var url = "cxdd_pysb.do?method=getXsPageList&bjdms="+rows[0]['bjdm']+"&bjid="+ids;
		document.location.href = url;
	}
}

/**
 * �۲��¼�롢�鿴--��ѯ
 */
function doQuery(){
	var map = {};
	map['bjdms']=jQuery("#bjdms").val();
	map['xh']=jQuery('#xh').val();
	jQuery("#dataTable").reloadGrid(map);
}	

//����
function pyLink(cellValue,rowObject){
	var html = "<textarea name='py'  data-xsid ='"+rowObject["xsid"]+"' data-bjdm ='"+rowObject["bjdm"]+"' onblur='saveThisRow(this)' data-xh='"+rowObject["xh"]+"' style='width:98%;height:30px' />";
	var obj = jQuery(html);
	jQuery(obj).text(cellValue);
	return obj;
}

//����
function pjLink(cellValue,rowObject){
	var html = "<select name='pj' data-bjdm ='"+rowObject["bjdm"]+"'  data-xsid ='"+rowObject["xsid"]+"' style='width:80%' data-xh='"+rowObject["xh"]+"'  onchange='saveThisRow(this)' >"+jQuery("select[id='pjhtml']").html()+"</select>";
	var obj = jQuery(html);
	jQuery(obj).find("[value='"+cellValue+"']").attr('selected','selected');
	return obj;
}

//��������
function saveThisRow(obj){
	var py = "";
	var pj = "";
	var xh = jQuery(obj).attr("data-xh");
	var bjdm = jQuery(obj).attr("data-bjdm");
	var xsid = jQuery(obj).attr("data-xsid");
	var bjid = jQuery("#bjid").val();
	var nameflag = obj.name;
	if(nameflag == 'py'){
		py = obj.value;
		pj = jQuery(obj).parent().parent().find("[name='pj']").val();
		if(jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120){
			showAlertDivLayer("���������80��-120��֮�䣡");
			return false;
		}
		if(jQuery.trim(pj) == ""){
			showAlertDivLayer("���۲���Ϊ�գ�");
			return false;
		}
	
	}else if(nameflag == 'pj'){
		pj = obj.value;
		py = jQuery(obj).parent().parent().find("[name='py']").val();
		if(jQuery.trim(pj) == ""){
			showAlertDivLayer("���۲���Ϊ�գ�");
			return false;
		}
//		if(jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120){
////			showAlertDivLayer("���������80��-120��֮�䣡");
//			return false;
//		}
	}
	if((jQuery.trim(py).length < 80 || jQuery.trim(py).length > 120) && jQuery.trim(py).length != 0){
		showAlertDivLayer("���������80��-120��֮�䣡");
		return false;
	}
	jQuery.post("cxdd_pysb.do?method=saveData", {
		xh : xh,bjdm : bjdm,xsid:xsid,py:py,pj:pj,bjid:bjid
	}, function(data) {
		//showAlertDivLayer(data["message"]);
		if(data['message'] == '����ɹ���'){
			jQuery("#dataTable").reloadGrid();
		}
	}, 'json');
	
}

//�ύ
function submittj(){
			jQuery.post("cxdd_pysb.do?method=submitBusi", {
				values : jQuery("#bjid").val(),bjdm : jQuery("#bjdms").val()
			}, function(data) {
				showAlertDivLayer(data.message,{},{"clkFun":function(){
					if(data.message == '�ύ�ɹ���'){
						document.location.href='xsxx_cxdd_pysb.do';
					}else{
						//jQuery("#dataTable").reloadGrid();
					}
					
				}});
				
			}, 'json');
	
}
