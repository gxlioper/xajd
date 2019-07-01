var checkId="stxmmc-stlbdm-xmlbdm-xn-gkdw-fzrlb-stfzr-stclsj-splc";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='StjgView(\""
			+ rowObject["stid"] + "\");'>" + cellValue + "</a>";
}

function StjgView(stid) {
	showDialog("���Ž���鿴", 750, 400, "stglStjg.do?method=viewStjg&stid="
			+ stid);
}
//��Ŀ���ø�ʽ��
function setXmsz(cellValue, rowObject) {
	var stid = rowObject.stid;
	var splc = rowObject.splc;
	var value = "δ����";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if ((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg) {
		value = "������";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + stid
			+ "\",\"" + splc+ "\");' >" + value + "</a>";
	return value;
}
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}
/*
 * ��Ŀ��������
 */
function xmsz(stid,splc) {
	if(splc == "null" || splc == null || jQuery.trim(splc) == ""){
		showAlertDivLayer("���������Ž���޸������������̣�");
		return false;
	}
	if (stid == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		stid = rows[0]["stid"];
	}
	var url = 'stglStjg.do?method=xmsz&stid=' + stid;
	var title = "��Ŀ����";
	showDialog(title, 680, 385, url);
}
function saveStjg(type) {
	var flg = true;
	var xxdm=jQuery("#xxdm").val();
    jQuery("#xhs").val(getxhs);
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
    var obj = jQuery("input[name='zgh']");
    if(obj.length == 0){
        showAlert("�����ָ����ʦ��");
        return false;
    }
	if(checkzsinput_yz(jQuery("#stsm"),500) == false || checkzsinput_yz(jQuery("#sthjqk"),500) == false){
		return false;
	}
	if("12872" == xxdm){
		if(jQuery("#sjly").val() != '1'){
			jQuery("#zdls").attr("readonly",false);
		}
	}else{
		if(jQuery("#sjly").val() != '1'){
			jQuery("#ssbm").attr("disabled",false);
			jQuery("#zdlszc").attr("disabled",false);
			jQuery("#zdlslxfs").attr("readonly",false);
		}
	}
	var url = "stglStjg.do?method=saveStjg&type="+type;
	
	if("12872" == xxdm){
		ajaxSubFormWithFun("StjgForm", url, function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				flg = false;
				 showAlert(data["message"],{},{"clkFun":function(){
	 				jQuery("#zdls").attr("readonly",true);
	 		    }});
			}
		});
	}else{
		ajaxSubFormWithFun("StjgForm", url, function(data) {
			if (data["message"] == "����ɹ���") {
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			} else {
				flg = false;
				 showAlert(data["message"],{},{"clkFun":function(){
	 				jQuery("#ssbm").attr("disabled",true);
	 				jQuery("#zdlszc").attr("disabled",true);
	 				jQuery("#zdlslxfs").attr("readonly",true);
	 		    }});
			}
		});
	}
	
}
function add() {
	var url = "stglStjg.do?method=addStjg";
	var title = "���Ž������";
	showDialog(title, 800, 500, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("��ѡ���û������걨����Ŀ��");
	} 
	/*�汾����ȥ���ÿ��ƣ�Ϊ�˷����ӳ���Чѧ��*/
	/*
	else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}*/
	else {
		var url = 'stglStjg.do?method=editStjg&stid=' + rows[0]["stid"];
		var title = "���Ž���޸�";
		showDialog(title, 800, 500, url);
	}

}

function getxhs(){
    var xhs = "";
    var xhobj = jQuery("[name='zgh']");
    jQuery(xhobj).each(function(i){
        xhs +=this.value;
        if(xhobj.length-1 != i){
            xhs +=",";
        }

    });
    return xhs;
}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "stglStjg.do?method=delStjg";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N980203_STJG");
	return false;

}

var DCCLBH = "stgl_stgl_stjg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, stjgExportData);
}

// ��������
function stjgExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "stglStjg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�޸�
function updatestcyxx(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} 
		var stid = rows[0]["stid"];
		var rtid = "";
		if(getsqkg(stid) != 1){
			showAlertDivLayer("��ǰ������Ŀδ������״̬���������޸ĳ�Ա��Ϣ��");
			return false;
		}
	var url = 'stglRtjg.do?method=RtjgWh&stid=' + stid+"&rtid="+rtid;
	var title = "���ų�Ա��ϸ�޸�";
	showDialog(title, 770, 550, url);
	
	
}

function getsqkg(stid){
	var sqkg = 1;
	var url = "stglRtjg.do?method=getSqkg";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'stid='+stid,
			async: false,
			success:function(result){
				if(result==null||result=="null"){
					return false;
				}else{
					sqkg = result;
				}
			}
			
		});
	   return sqkg;
	
}

function addXs() {
	var xmdm=jQuery("#xmdm").val();
	var jcxf=jQuery("#jcxf").val();
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_xmsqxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'stglRtjg.do?method=getStu'+'&xhArr='+xhArr;
	showDialog('', 800, 550, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var rtids = "";
	var message = "ɾ���ɹ���";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		sjly=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=sjly]").val();
		xh=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=xh]").val();
		temprtid = jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=rtid]");
		if("1"==sjly){
			if(i!=0){
				xhs+=",";
			}
			xhs+=xh;
		}
		if(temprtid != null){
			if(jQuery(temprtid).val() != '' &&��jQuery(temprtid).val() != null){
				rtids+= jQuery(temprtid).val()+",";
			}
		}
	}
	if(""!=xhs){
		showAlertDivLayer("<font color='red'>["+xhs+"]</font>"+"Ϊ�������ݣ�����ɾ����");
		return false;
	}
	if(rtids != "" && rtids != null){
		var url = "stglRtjg.do?method=delCyxx";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'values='+rtids.toString(),
		async: false,
		success:function(result){
			if(result==null||result=="null"){
				return false;
			}else{
				message = "ɾ���ɹ���";;
			}
		 }
	    });
	}

	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
	showAlert(message);
}

function setSqxs(rows,selobj) {

	var html = "";
	var jcxf=jQuery("#jcxf").val();
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_xmsqxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		var index=qqxsTrLen+int;
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/>"+"<input type='hidden' name='xh' id = 'xh_"+int+"' value='" +rows[int]["xh"]+"'/><input type='hidden' name='rtid' value=''/>"+"</td>";
		html += "<td>" + rows[int]["xh"] + "</td>";
		html += "<td>" + rows[int]["xm"] + "</td>";
		html += "<td>" + rows[int]["xb"] + "</td>";
		html += "<td>"+selobj+"</td>"
		html +="<td>"+"<input name = 'tc' id='tc_"+int  + "' maxlength='100'>";
		html +="<td>"+"<input name = 'sqly' id='sqly_"+int  + "' maxlength='100'>";
		

		jQuery("#tbody_xmsqxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqxsZj(html);

	iFClose();
}

function sqxsZj(html){
	jQuery("#tbody_xmsqxx").append(html);	
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

//���ά����Ϣ����
function saveJgwh(){
	//alert(parseInt(jQuery("#flagnum").val()));
	if(jQuery("#tbody_xmsqxx tr").length == 0){
		showAlert("������д��Ա��Ϣ��");
		return false;
	}
	var flag = true;
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("#tbody_xmsqxx tr").each(function(b){
			var temptc=jQuery(this).find("td:eq(5) input[name='tc']").val();
			var tempsqly=jQuery(this).find("td:eq(6) input[name='sqly']").val();
			if(temptc == '' || tempsqly == ''){
				flag = false;
				showAlert("��"+"<font color='red'>*</font>"+"�������Ϊ�գ�");
				return false;
			}
		});
	}else{
		var temptc=jQuery("input[name='tc']").val();
		var tempsqly=jQuery("input[name='sqly']").val();
		if(temptc == '' || tempsqly == ''){
			flag = false;
			showAlert("��"+"<font color='red'>*</font>"+"�������Ϊ�գ�");
			return false;
		}
	}

	if(flag == false){
		return false;
	}
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("select[name='rylbdm']").attr("disabled",false);
		var url = "stglRtjg.do?method=saveJgwh"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
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
	}else{
		var url = "stglRtjg.do?method=saveJgwh_XS"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
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
	
}

//����
function copystxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ��������Ŀ��");
		return false;
	}
	var url = "stglStjg.do?method=copeOfStxx&stxmmc="+rows[0]['stxmmc']+"&stid="+rows[0]['stid'];
	var title = "���Ž������";
	showDialog(title, 400, 180, url);
}


//�������Ž��
function saveCopyStjg(){
	if(jQuery("#stxmmc").val()=="" || jQuery("#xn").val()==""){
		showAlert("������Ŀ���ƺ���Чѧ�겻��Ϊ�գ�")
		return false;
	}
	var url = "stglStjg.do?method=saveCopeStxx"
		ajaxSubFormWithFun("StjgForm", url, function(data) {
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


//ajax��̬��ȡ��Ŀ���list
function getXmlblist(stlbdm){
	var rs = null;
	var url = "stglXmlbDmwh.do?method=getXmlblist";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:'stlbdm='+stlbdm,
	async: false,
	success:function(result){
		if(result==null||result=="null"){
			rs = null;
		}else{
			rs = result['html'];
		}
	 }
    });
	return rs;
};

//�����ű���������
function getStqkdjb(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	var xhs=new Array();
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
	 } else if (rows.length > 1){
		 post('stglStjg.do?method=getStqkdjbZip', {value:ids});
	 } else {
		var url="stglStjg.do?method=getStqkdjb&stid="+rows[0]["stid"];
		url = addSuperSearchParams(url);
		document.forms[0].action = url;
		document.forms[0].submit();
     }
}