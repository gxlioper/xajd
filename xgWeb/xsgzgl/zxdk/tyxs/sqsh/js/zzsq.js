var dkzesx = jQuery("#dkzesx").val();

function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "tyxs_zzsq.do?method=zzsh&id="+id;
		showDialog("����ѧ���������",800,500,url);
	}
}

/**
 * �޸������
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		
		showDialog("�޸������",800,500,"tyxs_zzsq.do?method=xgZzsq&id="+rows[0]["id"]);
	}
}


/**
 * �鿴�����
 * @param id
 */
function ckSqb(id){
	showDialog("�鿴�����",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
}

/**
 * �鿴�����
 * @param id
 */
function ckShbb(id){
	showDialog("�鿴�����ϸ",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
}


/**
 * ��ѯ
 */
function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ��д�����
 */
function editSqb(){
	showDialog("��д�����",800,500,"tyxs_zzsq.do?method=zzsq");
}


/**
 * ����
 */
function cancelsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();		
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (rows[0]["shzt"] != "5"){
		showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
		return false;	
	} else {
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
			jQuery.post("tyxs_zzsq.do?method=cancel",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}
		});
		
	
	}
}




/**
 * ��������
 * @returns {Boolean}
 */
function saveJxsq(type){
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("��ѡ����Ҫ����Ľ���",{},{"clkFun":function(){
			showDialog("ѡ�����뽱��",450,350,"xpj_sqsh.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	
	var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
	ajaxSubFormWithFun("sqshForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
	}
	
	var map = getSuperSearch();
	map["shzt"] = zt;
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}


//����
function exportConfig(){
	var DCCLBH='tyxs_zzsq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='tyxs_zzsq.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "tyxs_zzsq.do?method=dcsq&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼����");
			return false;
		
	}else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{
			"okFun" : function(){

			jQuery.post("tyxs_zzsq.do?method=submitTj",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();			
					refershParent();
				
			},'json');
			
		}
	});
	
		
		
	}
	
}



/**
 * ȡ������
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼����ˣ�����ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("tyxs_zzsq.do?method=delZzsq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}



//��֤�����ܶ�
function getzje(obj){
	//checkMoney(obj);
	var sqxfzj = jQuery("#sqxfzj").val();//�ܼ�
	var dyzzxf = jQuery("#dyzzxf").val();//��һ��
	var dezzxf = jQuery("#dezzxf").val();//�ڶ���
	var dszzxf = jQuery("#dszzxf").val();//������
	var dsizzxf = jQuery("#dsizzxf").val();//������
    var dqdks = obj.value;
    var dkzs=0;
	if (null !=dyzzxf && ''!= dyzzxf) {
		
	  dkzs = parseInt(dyzzxf)+parseInt(dkzs);
	}
	if (null != dezzxf && ''!= dezzxf) {
	  dkzs = parseInt(dezzxf)+parseInt(dkzs);		
	  }
	if (null !=dszzxf && ''!= dszzxf) {
	  dkzs = parseInt(dszzxf)+parseInt(dkzs);
	  }
	if (null !=dsizzxf && ''!= dsizzxf) {
		  dkzs = parseInt(dsizzxf)+parseInt(dkzs);
		  }
	
	jQuery("#sqxfzj").val(parseInt(dkzs));
	
}

/*function checkZje(obj){
	checkMoney(obj);
	var dkzs=obj.value;
	if (null !=dkzs && ''!= dkzs) {
		if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
			showAlertDivLayer("�ܶ��"+jQuery("#dkzesx").val()+"Ԫ!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		}
}*/
function checkQx(obj){
	var dkqx=obj.value;
	if (null !=dkqx && ''!= dkqx) {
		if(parseInt(dkqx)>4){
			showAlertDivLayer("�������޳���4��!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		else{
			if(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val()){
				jQuery("#dkje").val(parseInt(jQuery("#mnje").val())*parseInt(dkqx));
			}
		}
		}
	
}

/**
 * ���ؼ�ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj){
	
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
}

/**
 * ��ѧ�����������༭��ͥ���
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('��ͥ�������',780,500,'xszz_jtqkdc.do?method=dcxxModify&type=update&xh='+xh,{
		close:function(){
			reloadWindow();
		}
	});
}

/**
 * ��ѧ���������������
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="tyxs_zzsq.do?method=zzxx&xh="+xh;
}

//������Ϣ
function onShow(gndm) {	
	var url = "tyxs_zzsq.do?method=zzxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			id : jQuery("#id").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data);
		}
	});
}


//���۱��ǿ�
function tyCheckNull(){
	var flag = true;
	var xxdm =jQuery("#xxdm").val();
	if(xxdm=="10704"){
		if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly-fistyj-secondyj-thirdyj-fourthyj-xfyjzj-sfbb')){
			
			//showAlertDivLayer("�뽫��<span class='red'>*</span>����Ŀ��д����!");			
			flag = false;				
		}
	}else{
		if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly')){
			
			//showAlertDivLayer("�뽫��<span class='red'>*</span>����Ŀ��д����!");			
			flag = false;				
		}
	}
	
	return flag;

	}

//��ѧ��Ӧ�ɽ���ܼ�
function getyjzj(obj){
	var fistyj = jQuery("#fistyj").val();//��һ��
	var secondyj = jQuery("#secondyj").val();//�ڶ���
	var thirdyj = jQuery("#thirdyj").val();//������
	var fourthyj = jQuery("#fourthyj").val();//������
    var dkzs=0;
	if (null !=fistyj && ''!= fistyj) {
		
	  dkzs = parseInt(fistyj)+parseInt(dkzs);
	}
	if (null != secondyj && ''!= secondyj) {
	  dkzs = parseInt(secondyj)+parseInt(dkzs);		
	  }
	if (null !=thirdyj && ''!= thirdyj) {
	  dkzs = parseInt(thirdyj)+parseInt(dkzs);
	  }
	if (null !=fourthyj && ''!= fourthyj) {
		  dkzs = parseInt(fourthyj)+parseInt(dkzs);
		  }
	
	jQuery("#xfyjzj").val(parseInt(dkzs));
	
}


//��֤�����ܶ�
function getzjehsd(obj){
	//checkMoney(obj);
	var objs = jQuery("input[name='je']");
	var len = objs.length;
    var dkzs=0;
    for(var i=0;i<len;i++){
    	var jeval = jQuery.trim(objs[i].value);
    	if(jeval != null &&��jeval != "" ){
    		dkzs +=parseInt(jeval);
    	}
    }
	jQuery("#sqxfzj").val(dkzs);
	
}

function dklxchange(){
	jQuery("#dklx").change(function(){
		var xh = jQuery.trim(jQuery("#xh").val());
		var dklx = jQuery("#dklx").val();
		if((xh == '' || xh == null) && dklx != '' ){
			jQuery("#dklx").val("");
			showAlert("����ѡ��ѧ����");
			return false;
		}
		
		var xn = jQuery("input[name='xn']").val();
		jQuery.post("dkbc_sqsh.do?method=dklxChange",{xh:xh,dklx:dklx,xn:xn},function(data){
		if(data != null){
			jQuery("#dkbj").val(data['dkje']);
			jQuery("#yhdm").val(data['yhdm']);			
		    jQuery("#dkhth").val(data['htbh']);     
		         if(data['dkkssj'] != "" &&data['dkkssj'] != null ){
			         jQuery("#dkkssj").val(data['dkkssj']);
			         jQuery("#dkjssj").val(data['dkjssj']);
		         }
		}else{
			jQuery("#dkbj").val("");
			jQuery("#yhdm").val("");			
		    jQuery("#dkhth").val("");
		    jQuery("#dkkssj").val("");
	         jQuery("#dkjssj").val("");
		}
		
		   			
		},"json");
	});
}

function fxjdnxchange(){
	jQuery("#fxjdnx").change(function(){
		var fxsj = jQuery("#fxsj").val();
		var xh = jQuery.trim(jQuery("#xh").val());
		var fxjdnx = jQuery("#fxjdnx").val();
		if(fxjdnx != '' && xh == ''){
			jQuery("#fxjdnx").val("");
			showAlert("����ѡ��ѧ����");
			return false;
		}
		if(fxjdnx != '' && fxsj == ''){
			jQuery("#fxjdnx").val("");
			showAlert("����ѡ��ѧʱ�䣡");
			return false;
		}
		var ksnd = fxsj.substr(0, 4);
		var html = "";
		html += "<tr width='100%' >"+
	       "<th width='40%' ><span class='red'>*</span>���</th>"+
	        "<th width='60%' ><span class='red'>*</span>������</th>"+
           "</tr>"
		if(fxjdnx == ''){
			jQuery(".cn").html(html);
		}else{
			var len = fxjdnx;
			jQuery(".cn").empty();
			for(var i=0;i<len;i++){
				html += "<tr width='100%'>"+
				"<td width='40%' >"+(parseInt(ksnd)+i)+"<input name='nd' type='hidden'  value='"+(parseInt(ksnd)+i)+"'></td>"+
				"<td width='60%' ><input name='je' type='text' onkeyup='checkInput(this);getzjehsd(this)' maxlength='5' style='width:80%'></td>"+
			"</tr>"
			}
			html += "<tr width='100%'>"+
			"<td width='40%' >����ѧ�Ѽ����ܼ�</td>"+
			"<td width='60%' ><input name='sqxfzj' id='sqxfzj' maxlength='10' readonly='true' type='text' style='width:80%'/></td>"+
		"</tr>"
			jQuery(".cn").append(html);
		}

	});
}

//���۱��ǿ�
function tyCheckNullhsd(){
		
	var flag = true;
	if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly-dklx-dkbj')){
		
		//showAlertDivLayer("�뽫��<span class='red'>*</span>����Ŀ��д����!");			
		flag = false;				
	}
	return flag;

	}

//��֤��׼������Ƿ���д��
function checkNdJe(){
	if(jQuery(".cn").find("tr").length == '1'){
		showAlert("�뽫��<span class='red'>*</span>����Ŀ��д����!");
		return false;
	}
	if(jQuery("[name='nd']").length == 0){
		showAlert("��ȣ���Ƚ���Ϊ��!");
		return false;
	}
	var objs = jQuery("input[name='je']");
	var len = objs.length;
	var flag = true;
    for(var i=0;i<len;i++){
    	var jeval = jQuery.trim(objs[i].value);
    	if(jeval == null ||��jeval == "" ){
    		showAlert("�뽫��<span class='red'>*</span>����Ŀ��д����!");
    		flag = false;
    		break;
    	}
    }
    return flag;
}

//��֤�������ɳ���
function checksqlylength(){
	if(jQuery("#sqly").val().length > 250){
		showAlert("�������ɲ�Ҫ����250��!");
		return false;
	}else{
		return true;
	}
}
	