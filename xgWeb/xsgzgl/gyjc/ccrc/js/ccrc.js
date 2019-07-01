function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �����Link
 * @return
 */
function jcxLink(cellValue, rowObject){
	var jcxmc = "";
	if(rowObject['wsjc'] == "1"){
		jcxmc +="������顢";
	}
	if(rowObject['aqjc'] == "2"){
		jcxmc +="��ȫ��顢";
	}
	if(rowObject['jljc'] == "3"){
		jcxmc +="���ɼ�顢";
	}
	return "<font color='blue'>"+jcxmc.substring(0,jcxmc.length-1)+"</font>";
}

/**
 * ����
 * @return
 */
function addpage(){
	var url = "gyjc_ccrcsz.do?method=add";
	showDialog("���ӳ���ճ�", 770, 350, url);
}

/**
 * �޸�
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	document.location.href="gyjc_ccrcsz.do?method=update&ccid="+rows[0]["ccid"];
}

/**
 * �������ްٷֱ�
 * @param obj
 * @return
 */
function calBfbOver(obj){
	var inputBfb = parseFloat(jQuery(obj).val());
	var ztxbl = parseFloat(jQuery(obj).parent().find("[name='ztxbl']").val());
	var isUpate = jQuery("#guid").length == 1 ? true :false;
	if(isUpate){
		var bcjcbl = parseFloat(jQuery(obj).parent().find("[name='bcjcbl']").val());
		ztxbl = parseFloat(ztxbl)-parseFloat(bcjcbl);
	}
	var sxBfb =parseFloat((parseFloat(100) - parseFloat(ztxbl)));
	if(!isUpate && ztxbl == 100){
		sxBfb = 100;
	}
	if((sxBfb < parseFloat(inputBfb) && (ztxbl != parseFloat(100))) || inputBfb > 100){
		showAlert("����������ٷֱ�����"+sxBfb+"%",{},{"clkFun":function(){
				obj.focus();
		}});
	}else{
		return;
	}
}

/**
 * �������ճ�
 * @return
 */
function saveCcrc(){
	var ids = "jcrq";
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	var url = "gyjc_ccrcsz.do?method=saveCcRc";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
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

/**
 * ֻ�޸�����
 * @return
 */
function saveCcrcUpdate(){
	var ids = "jcrq";
	if(!checkNotNull(ids)){
		return showAlertDivLayer("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
			 showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlertDivLayer(data["message"]);
    		}
		});
}

/**
 * ɾ��
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyjc_ccrcsz.do?method=delCcrc",{rcid:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//��������
function fpqs(){
	var ccid = jQuery("#ccid").val();
	var url = "gyjc_ccrcsz.do?method=fenpei&ccid="+ccid;
	showDialog("�����б�", 770, 550, url,{close:function(){
	}});
}

//��������
function fpccr(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ccid = jQuery("#ccid").val();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��������ң�");
	}else{
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	document.location.href = "xg_gyjc_ccry.do?qshs="+qshs+"&lddms="+lddms+"&ccid="+ccid;
	
	}
}

//����˲鿴
function ccrLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
			+ "</a>";
}

//���Ҳ鿴
function qsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ViewQs(\""
	+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
	+ "</a>";
}

//������޸�
function View(ccid,zgh) {
	showDialog("ѡ������", 900, 450, "gyjc_ccrcsz.do?method=updateCcrList&ccid="+ccid+"&xgzgh="+zgh);
}
//��ѡ������ת
function ViewQs(ccid,zgh) {
	showDialog("ѡ������", 900, 450, "gyjc_ccrcsz.do?method=updateQsList&ccid="+ccid+"&zgh="+zgh);
}

/**
 * ѡ¥��
 * @param obj
 * @return
 */
function selLd(obj){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var guid = jQuery("#guid").val();
	if(!guid){
		guid = "";
	}
	var xydm = jQuery(obj).parent().parent().parent().find("[name='xydm']").val();
	var lddm = new Array();
	var lddmInputObj = jQuery(obj).parent().parent().parent().find("[name='lddm']");
	if(lddmInputObj != null && lddmInputObj.length >0){
		for ( var j = 0; j < lddmInputObj.length; j++) {
			lddm.push(lddmInputObj[j].value);
		}
	}
	var url = "gyjc_jcrc.do?method=getSelLdCx";
	url += "&xn="+xn;
	url += "&xq="+xq;
	url += "&guid="+guid;
	url += "&xydm="+xydm;
	url += "&lddms="+lddm;
	var title = "¥��ѡ��";
	showDialog(title, 300, 200, url);
	
}


function fhjcsd(){
	document.location.href = "xg_gyjc_ccrcsz.do";
}


/**
 * ҳǩѡ��
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ������Ա����
 * @return
 */
function saveFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val()
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=saveRyFp",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		
	},'json');
}

//�����޸ĵķ�����ԱupdateFyfp
function updateFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val(),
				xgzgh:jQuery("#xgzgh").val()
				};
	jQuery.post("gyjc_ccrcsz.do?method=updateRyFp",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}



function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
	
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
	
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}



function updateQs(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("��ѡ�����ң�");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
				zgh:jQuery("#zgh").val(),
				ccid:jQuery("#ccid").val(),
				lds:lddms,
				qss:qshs
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=insertQs",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}


/**
 * ȡ������
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ�����ң�");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
			ccid:jQuery("#ccid").val(),
			lds:lddms,
			qss:qshs
	};
	jQuery.post("gyjc_ccrcsz.do?method=cancelRyfp",para,function(data){
		showAlertDivLayer(data["message"]);
		 if (parent.window){
				var api = frameElement.api,W = api.opener;
				W.jQuery("#dataTable").reloadGrid();
			}
		jQuery("#dataTable").reloadGrid();
	},'json');
}




