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
	ajaxSubFormWithFun("xdsqForm",url,function(data){
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
	var selectkey = jQuery("[name='id']:checked");
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
	var selectkey = jQuery("[name='id']:checked");
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
	var selectkey = jQuery("[name='id']:checked");
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
	var selectkey = jQuery("[name='id']:checked");
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
		jQuery("[name='id']").attr("checked","checked");
	}else{
		jQuery("[name='id']").removeAttr("checked");
	}
}

function update(){
	var selectkey = jQuery("[name='id']:checked");
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
	var id = jQuery(selectrow).find("[name='id']").val();
	var xdxn = jQuery(selectrow).find("[name='ysqxn']").val();
	var xdje = jQuery(selectrow).find("[name='yxdje']").text();
	var xdly = jQuery(selectrow).find("[name='yxdly']").val();
	jQuery("#dqxn").text(xdxn);
	jQuery("#id2").val(id);
	jQuery("#mnje").text(xdje);
	jQuery("#xdlys").val(xdly);
	jQuery("#xdsqTable2").show();
	
}
