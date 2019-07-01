function searchRs() {
	var map = getSuperSearch();
	map["xydm"]=jQuery("#xydm").val();
	map["js"]=jQuery("#js").val();
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
function add(){
	var url = "gyjc_jcrc.do?method=add";
	showDialog("���Ӽ���ճ�", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();	
	}})
}

/**
 * �޸�
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "gyjc_jcrc.do?method=update&guid="+rows[0]["guid"];
	showDialog("�޸ļ���ճ�", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();	
	}});
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
function saveJcrc(){
	var ids = "ccrq-jzrq";
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	if(jQuery("input[type='checkbox']:checked").length == 0){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	var flag = false;
	jQuery("[name='jcbl']").each(function(){
		if(jQuery.trim(this.value) != ""){
			flag = true;
			return;
		}
	})
	if(!flag){
		return showAlert("��������дһ������¼��");
	}
	var url = "gyjc_jcrc.do?method=saveJcRc";
	ajaxSubFormWithFun("JcrcForm", url, function(data) {
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
function saveJcrcUpdate(){
	var url = "gyjc_jcrc.do?method=saveJcrcUpdate";
	ajaxSubFormWithFun("JcrcForm", url, function(data) {
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
 * ɾ��
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyjc_jcrc.do?method=delJcrc",{rcid:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�ύ��¼���鿴
function tjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"ytjscx"+ "\",\""+"1"+ "\");'>" + cellValue
			+ "</a>";
}

//δ�ύ��¼���鿴
function wtjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"wtjscx"+ "\",\""+"0"+ "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(rcid,flag,tjzt) {
	showDialog("�鿴", 900, 450, "gyjc_jcjglr.do?method=getJcjgLrcxList&rcid="+rcid+"&flag="+flag+"&tjzt="+tjzt);
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