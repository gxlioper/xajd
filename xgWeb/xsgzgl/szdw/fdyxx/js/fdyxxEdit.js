var gndm = "szdw_fdyxx_update";// ���ܴ���

function onShow(flag) {
	var url = "szdw_fdyxx.do?method=getFdyxx";
	var xxdm = jQuery("#xxdm").val();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			zgh : jQuery("#zgh").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data,null,flag);
			//�Ƿ��ڱಹ��
			var sfzbSelect = jQuery("#sfzb");
			var sfzbTr = sfzbSelect.parent().parent();
			//ѡ�����ʾ��ע
			var bztr = "<tr id='bzbbzTr'><th>���ڱ౸ע</th><td colspan='4'><textarea id='bzbbz' name='bzbbz' rows='4' style='width:97%'></textarea></td></tr>"
            sfzbTr.after(bztr);
            sfzbChange();
            sfzbSelect.bind('change',sfzbChange);
            //��ֵ
            jQuery("#bzbbz").val(data["bzbbz"]);

            //3+2����Ա����
			var sfdrsjzfdySelect = jQuery("#sfdrsjzfdy")
			var sfdrsjzfdyTr = sfdrsjzfdySelect.parent().parent();
			var lxdmsjTr = "<tr id='lxdmsjTr'><th><span class='red'>*</span>����\"2+3\"����Աʱ��</th><td><input type='text' id='drsj' name='drsj' onfocus='WdatePicker({dateFmt:\"yyyy\"})' class='text_nor'></td>";
            lxdmsjTr += "<th><span class='red'>*</span>��У����</th><td colspan='2'><select id='lxbm' name='lxbm'><option value=''>---��ѡ��---</option>";
            var bmdms = jQuery("#bmdm").children();
            var option;
            bmdms.each(function(i,e){
                option = "<option value='"+bmdms[i].value+"'>"+bmdms[i].text+"</option>";
                lxdmsjTr += option;
            });
            lxdmsjTr +="</select></td></tr>";
            sfdrsjzfdyTr.after(lxdmsjTr);

            sfdrsjzfdyChange();
            sfdrsjzfdySelect.bind('change',sfdrsjzfdyChange);
            jQuery("#drsj").val(data["drsj"]);
            jQuery("#lxbm").val(data["lxbm"]);

		}
	});
}
function sfdrsjzfdyChange(){
	var sfdrsjzfdy = jQuery("#sfdrsjzfdy").val();
	if(sfdrsjzfdy == '��'){
        jQuery("#lxdmsjTr").show();
	} else {
        jQuery("#lxdmsjTr").hide();
	}
}
function sfzbChange(){
    var sfzb = jQuery("#sfzb").val();
    if(sfzb == '��'){
        jQuery("#bzbbzTr").hide();
    } else if(sfzb == '��'){
        jQuery("#bzbbzTr").show();
    } else {
        jQuery("#bzbbzTr").hide();
    }
}
function initXgsh() {
	var url = "szdw_fdyxx.do?method=getFdyxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			zgh : jQuery("#zgh").val()
		},
		dataType : "json",
		success : function(data) {

			zdybdInit(gndm, data,{xs:true});
			jQuery("#zdybdfl_szdw_fdyxx_qtxx td:last").attr("width","85%");
			setAuditXgzd();// �����޸��ֶ�
		}
	});
}


function saveForm() {
	if (!zdybdCheck()) {
		return;
	}
	var sfdrsjzfdy = jQuery("#sfdrsjzfdy").val();
	if(sfdrsjzfdy == "��"){
		var drsj = jQuery("#drsj").val();
		if(drsj == ""){
            showAlert("����д����\"2+3\"����Աʱ�䣡");
            return;
		}
		var lxbm = jQuery("#lxbm").val();
		if(lxbm == ""){
            showAlert("��ѡ����У���ţ�");
            return;
		}
	}
//	var sfbt = jQuery('#zpsfbt').val();
//	var sfcz = jQuery('#zpsfcz').val();
//	if (sfbt == "y" && "false" == sfcz) {
//		alertError("�����ϴ�һ����Ƭ��")
//		return false;
//	}

	var url = "szdw_fdyxx.do?method=fdyxxXg";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});
}

//��ʾ�ϴ���Ƭ
function showZpscDiv(){

	var zgh = jQuery("#zgh").val();
	
	if(zgh == ""){
		alertError("������дְ���ţ�");
	}else{
		
		var html = '<div class="open_win01">';
			html += '<table align="center" class="formlist">';
			html += '	<thead>';
			html += '	<tr>';
			html += '<th colspan="2">';
			html += '<span>�ϴ���Ƭ</span>';
			html += '</th>';
			html += '</tr>';
			html += '</thead>';
			html += '<tbody>';
			html += '<tr>';
			html += '<td>';
			html += '<input type="file" id="teaPic" name="teaPic" style="width:90%" onchange=\'uploadTeaPic();ymPrompt.close();\'/><br/>';
			html += '<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>';
			html += '</td>';
			html += '</tr>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
		
		
		tipsWindown("�ϴ���Ƭ",html,"380","130","true","","true","id");
		//tipsWindownNew("�ϴ���Ƭ","id:addPic",380,130);
	}
}

function uploadTeaPic(){
	
	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});
	
	var zgh = jQuery("#zgh").val();
	jQuery.ajaxFileUpload({
		  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//�������˳���
		  secureuri:false,
		  fileElementId:'teaPic',//input���ID
		  success:function(data,type){
			if (type=='success'){
				jQuery("#jszp").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
				jQuery("#zhaopian").attr("src","szdw_teaInfo.do?method=showTeaPic&zgh="+zgh+"&tt="+new Date());
				jQuery('#zpsfcz').attr("value", "true");
				jQuery('#sczp').attr("value", "1");
				showAlert(data);
			}
		  }
	});
}

function save(){
	
	/*if (!zdybdCheck()) {
		return;
	}*/
    //������ȡ���޸�ֵ������֤
    getXgzd();

	if (!getXgzdJson()) {
	}
	
	var url = "szdw_fdyxx.do?method=bcsq";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						document.location.href="szdw_fdyxx_xgsq.do";
					}
				}
			});
		}
	});
}

function saveAndSubmit(){
	if (!zdybdCheck()) {
		return;
	}
	
	if (!getXgzdJson()) {
	}
	
	var url = "szdw_fdyxx.do?method=tjsq";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						document.location.href="szdw_fdyxx_xgsq.do";
					}
				}
			});
		}
	});
}

function setXgzd(flag) {

	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "szdw_fdyxx.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {

			zdybdReplaceZd(data,flag);
			//
			for(var q=0;q<data.length;q++){
				if("bzbbz"==data[q].zd){
					jQuery("#bzbbz").val(data[q].zdz);
				}
				if("drsj"==data[q].zd){
                    jQuery("#drsj").val(data[q].zdz);
				}
				if("lxbm"==data[q].zd){
                    jQuery("#lxbm").val(data[q].zdz);
				}
			}
		}
	});
}

function setAuditXgzd() {
	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "szdw_fdyxx.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {

			var flag="sh";
			zdybdXgzd(data,flag);
			/*����������ڱ౸ע������"2+3"����Աʱ�䡢��У����*/
			var addFlag = true;
			for(var p=0;p<data.length;p++){
				if(data[p].zd == "drsj" || data[p].zd == "lxbm"){

					if(addFlag){
                        var tr = jQuery("th[name='zdybdcon_th_sfdrsjzfdy']").parent();
                        var ht = "<tr><th>����\"2+3\"����Աʱ��</th><td name='sjedrsjTd'></td><th>��У����</th><td name='lxbmTd' colspan='2'><a href=\"javascript:void(0);\">" +
                            "<font color=\"red\" title=\"�޸�ǰ��ϢΪ��\" alt=\"�޸�ǰ��ϢΪ��\"></font></a></td></tr>";
                        tr.after(ht);
                        addFlag = false;

					}
                    if(data[p].zd == "drsj"){
                        var strA = "<a href=\"javascript:void(0);\"><font color=\"red\" title=\"�޸�ǰ��ϢΪ��"+data[p].xgqz+"\" alt=\"�޸�ǰ��ϢΪ��"+data[p].xgqz+"\">"+data[p].zdz+"</font></a>";
                        jQuery("td[name='sjedrsjTd']").append(strA);
                    } else {
                    	//��ȡ��У��������
						jQuery.post("szdw_fdyxx.do?method=getBmmc",{bmdm:data[p].zdz},function(rs){
                            var strA = "<a href=\"javascript:void(0);\"><font color=\"red\">"+rs.bmmc+"</font></a>";
                            jQuery("td[name='lxbmTd']").append(strA);
						},'json');
                    }
				} else if(data[p].zd == "bzbbz"){
                    var tr = jQuery("th[name='zdybdcon_th_sfzb']").parent();
                    var ht = "<tr><th>���ڱ౸ע</th><td colspan='4'><a href=\"javascript:void(0);\"><font color=\"red\" title=\"�޸�ǰ��ϢΪ��"+data[p].xgqz+"\" alt=\"�޸�ǰ��ϢΪ��"+data[p].xgqz+"\">"+data[p].zdz+"</font></a></td></tr>";
                    tr.after(ht);
				}
			}
		}
	});
}




function saveAudit() {
	// �ύ���
	var url = "szdw_fdyxx.do?method=saveAudit";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				refershParent();
			}
		});
	});

}

//����ʦ���Ƽ�ѧԺ����Ա�����ʾ���л�
function onChange() {
	if ("רְ����Ա" == jQuery("#kzzd16").val()) {
		jQuery("th[name = 'zdybdcon_th_kzzd17']").show();
		jQuery("td[name = 'zdybdcon_td_kzzd17']").show();
		jQuery("td[name = 'zdybdcon_td_kzzd12']").attr("colspan", "2");
	} else if ("��ְ����Ա" == jQuery("#kzzd16").val()
			|| "������" == jQuery("#kzzd16").val()) {
		jQuery("#kzzd17").val(jQuery('#kzzd17 option:first').val());
		jQuery("td[name = 'zdybdcon_td_kzzd12']").attr("colspan", "4");
		jQuery("th[name = 'zdybdcon_th_kzzd17']").hide();
		jQuery("td[name = 'zdybdcon_td_kzzd17']").hide();
	}
}

//��ʦ���(������ҽҩ��ѧ)
function jssfValueShow(){
	var jssf = jQuery("#jssfs").val();
	//���д�����Ϊ�˷�ֹͨ��js����checkboxֵע��bug��д��ͨ���Զ����ע��ֵʱ�޷������Ե����ֶδ洢��checkboxֵ�����޸�ʱ�޷�����׼ȷ��д��
	jQuery("[name='zdybdcon_td_jssf']").html(jQuery("label"));
	//����ֵ����ѡ�е�ֵ��
	jQuery("input[name='jssf']").each(function(i){
		var value = this.value;
		if(jssf.indexOf(value) != -1 ){
			jQuery(this).attr("checked","checked");
		}
		
	})
}
