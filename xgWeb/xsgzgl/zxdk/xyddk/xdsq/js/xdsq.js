/**
 * �����onblur�Զ�����
 * @return
 */
function saveXdsq2(type){
	var xdly = jQuery("#xdlys").val();
	if (jQuery.trim(xdly) == ""){
		showAlertDivLayer("�������ɲ���Ϊ�գ�");
		return false;
	}
	/*�������������ж�*/
	if(xdly.length > 400){
		showAlertDivLayer("�������ɲ��ܳ���400�֣�");
		return false;
	}
	var url = "zxdkDkjg.do?method=updatedkxx&type="+type;
	ajaxSubFormWithFun("HsdxdForm",url,function(data){
		if (data["message"] == "����ɹ���") {
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				document.location.href=document.location;
			}});
		}else{
			showAlertDivLayer(data["message"]);
			return false;
		}
	});
}

/**
 * �ύ
 */
function submitBusi() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (selectkey.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != "0" && shzt != "3") {
			showAlertDivLayer("δ�ύ�����˻ص����ݲ��ܱ��ύ��");
			return false;
		}
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=submitBusi", {
					values : ids
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}
}

//����
function cancel() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (selectkey.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (selectkey.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		if (shzt != '5') {
			showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
			return false;
		}
		var splc = jQuery(selectrow).find("[name='splc']").val();
		var ids = jQuery(selectkey[0]).val();
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("zxdkDkjg.do?method=cancelXdsq", {
					values : ids,
					splcid : splc
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
						   document.location.href=document.location;
						}
					});
				}, 'json');
			}
		});
	}

}

//���̸���
function lcgz() {
	var selectkey = jQuery("[name='sqid']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var selectrow = jQuery(selectkey[0]).parent().parent();
		var shzt = jQuery(selectrow).find("[name='shzt']").val();
		var id = jQuery(selectkey[0]).val();
		var splc = jQuery(selectrow).find("[name='splc']").val();
		if ("0" == shzt) {
			showAlertDivLayer("��������̸�����Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ id + "&splc=" + splc);
	}
}

//ɾ��
function del() {
	var selectkey = jQuery("[name='sqid']:checked");
	var ids = "";
	if (selectkey.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		var selectrow = jQuery(selectkey).parent().parent();
		var flag = true;
		for(var i=0;i<selectrow.length;i++){
			var shzt = jQuery(selectrow[i]).find("[name='shzt']").val();
			var id = jQuery(selectkey[i]).val();
			if(shzt != "0" && shzt != "3"){
				flag = false;
				break;
			}
			ids +=id;
			if(i != selectrow.length-1){
				ids +=",";
			}
		}
		if(!flag){
			showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zxdkDkjg.do?method=delXdxx",{values:ids},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
					   document.location.href=document.location;
					}
				});
			},'json');
		}});
	}
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='sqid']").attr("checked","checked");
	}else{
		jQuery("[name='sqid']").removeAttr("checked");
	}
}

function update(){
	var selectkey = jQuery("[name='sqid']:checked");
	if (1 != selectkey.length) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} 
	var selectrow = jQuery(selectkey[0]).parent().parent();
	var shzt = jQuery(selectrow).find("[name='shzt']").val();
	if (shzt != '0' && shzt != '3') {
		showAlertDivLayer("����е����ݲ��ܱ��޸ģ�");
		return false;
	}
	var id = jQuery(selectrow).find("[name='sqid']").val();
	var xdxn = jQuery(selectrow).find("[name='xn']").val();
	var xdje = jQuery(selectrow).find("[name='xdjes']").val();
	var xdly = jQuery(selectrow).find("#xdlyss").text();
	jQuery("#updatexdxn").text(xdxn);
	jQuery("#id2").val(id);
	jQuery("#updatexdje").text(xdje);
	jQuery("#xdlys").val(xdly);
	jQuery("#xdsqTable2").show();
	
}

/**
 * ȡ������
 * @param obj
 * @return
 */
function qxsq(obj){
	var parentObj = jQuery(obj).parent().parent();
	var jgid = jQuery(parentObj).find("[name='jgid']").val();
	var url = 'gjdk_xdsqnew.do?method=qxsq';
	showConfirmDivLayer("��������������ת���Ƿ�ȷ�Ϸ���������",{"okFun":function(){
		jQuery.ajax({ 
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'jgid='+jgid,
		async: false,
		success:function(result){
			if(result['message'] == "ȡ���ɹ���"){
				showAlertDivLayer(result["message"], {}, {
					"clkFun" : function() {
					   document.location.href=document.location;
					}
				});
			}else{
				showAlertDivLayer(result["message"]);
			}
		 }
	    });
	}});
}

//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//�Ŵ�ά��
function fdwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} 
	var url = 'gjdk_xdsqnew.do?method=fdWh&id=' + rows[0]["id"]
			+ '&xh=' + rows[0]["xh"];
	var title = "�Ŵ�ά��";
	showDialog(title, 770, 450, url);
	
}

//����ά������
function saveFdwh(){
	var ids = "fkje-fksj-fkpzh-dkzh-khh";
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(parseInt(jQuery("#fkje").val()) > parseInt(jQuery("#xdjes").val())){
		showAlert("�Ŵ����ܴ��ڸ�ѧ�������"+jQuery("#xdjes").val()+"Ԫ��");
		return false;
	}
	var url = "gjdk_xdsqnew.do?method=saveFdwh";
	ajaxSubFormWithFun("HsdxdForm", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function jgView(id, xh) {
	showDialog("�Ŵ�ά���鿴", 770, 450, "gjdk_xdsqnew.do?method=fdCk&id="
			+ id + "&xh=" + xh);
}

//����
function dr(){
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_HSDFDDR");
	return false;

}

var DCCLBH = "zxdk_gjdk_hsdfd.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xyzsjgExportData);
}

//��������
function xyzsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gjdk_xdsqnew.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
