var gndm = "xsxx_update";// ���ܴ���
var isQzxg = false;

//�㽭��ҵ���ŷ��͸��Ի��ֶ�
var InterValObj; //timer����������ʱ��
var count = 300; //���������1��ִ��
var curCount;//��ǰʣ������


jQuery(function() {
	onShow("xg");
	if("10530" == jQuery("#xxdm").val()){
		xiangtan_ssxxqr_change_10530();
	}else if ("40030" == jQuery("#xxdm").val()) {
		onChange();
	}else if ("14073" == jQuery("#xxdm").val()) {
		onChange_14073();
	}else if("12883"==jQuery("#xxdm").val()){
		onChange_12883();
	}else if ("12727" == jQuery("#xxdm").val()) {
		onChange_12727();
	}else if("10264" == jQuery("#xxdm").val()){
		var jtdhqh = '<font color="red" style="padding-left:20px">����������ţ�</font>';
		var zmr = '<font color="red" style="padding-left:20px">������ѧ��ʼ��д��</font>';
		jQuery("#zdybdfl_xsxx_update_xlshjlxxxg>table:first").find('span').append(zmr);
		jQuery("td[name=zdybdcon_td_jtdh]").append(jtdhqh);
		
	}else if ("10718" == jQuery("#xxdm").val()) {
		/*����ʦ����ѧ���Ի��ֶ������жϼ�����*/
		contentHide_10718();
		onChange_10718();
	}else if ("12867" == jQuery("#xxdm").val()) { 
		//�㽭����
		//����ٻ𳵳˳�����
		var fHtml = "";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;��:���ݶ�");	
		citySelect("zd4");
	}else if ("11458" == jQuery("#xxdm").val()) {
		jQuery("#byxx").parents("tr").hide();
		jQuery("#bz").parents("tr").hide();
		jQuery("#jtdz").css("width","300px");
		jQuery("#zsjj").css("margin-top", "5px");
		var helpMsg = '��������������������򡢺ӱ����������Ϻ������ա��㽭��������ɽ�����㶫�ͺ��ϵ�11��ʡ�У�\n';
		helpMsg += '�в���������ɽ�������֡������������ա����������ϡ����������ϵ�8ʡ��\n';
		helpMsg += '���������������졢�Ĵ������ݡ����ϡ����ء����������ࡢ�ຣ�����ġ��½������������ɹŵ�12��ʡ����������';
		var helpHtml = '<div class="tab_cur" style="display: inline;background-image: none;" title="'+helpMsg+'">';
		helpHtml += '<p class="help" style="margin-right:36px;">';
		helpHtml += '<a href="#" onclick="return false;" onmousedown ="showHelpDiv();" >����</a>';
		helpHtml += '</p>';
		helpHtml += '</div>';
		var msg = '��������������������򡢺ӱ����������Ϻ������ա��㽭��������ɽ�����㶫��10��ʡ�У�</br>';
		msg += '�в���������ɽ�������֡������������ա����������ϡ����������Ϻͺ��ϵ�9ʡ��</br>';
		msg += '���������������졢�Ĵ������ݡ����ϡ����ء����������ࡢ�ຣ�����ġ��½������������ɹŵ�12��ʡ����������</br>';
		jQuery("#zsjj").parent().prepend(msg);
//		jQuery("#zsjj").parent().append(helpHtml);
		jQuery("#rxfs").parent().append("&nbsp;&nbsp;&nbsp;��:X��XXX");
		var zmr = '<font color="red" style="padding-left:20px">�磺Сѧ��XXXX��XX�¡�XXXX��XX��    XXXXѧУѧϰ</font>';
		jQuery("#zdybdfl_xsxx_update_xlshjlxxxg>table:first").find('span').append(zmr);
		//����ٻ𳵳˳�����
		var fHtml = "�Ϻ�<��>";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;��:������");
		citySelect("zd4");
		//��ͥ��ԱĬ��2��
		var jtcyTr=2;
		if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1){
			for ( var int = 0; int < jtcyTr; int++) {
				addConMoreUpdateTr("xsxx_update_jtcyxxxg");
			}
		}
		//������ϢĬ��3��
		var jyxxTr=3;
		if(jQuery("#zdybdcon_table_xsxx_update_xlshjlxxxg tbody tr").length == 1){
			for ( var int = 0; int < jyxxTr; int++) {
				addConMoreUpdateTr("xsxx_update_xlshjlxxxg");
			}
		}
		
	}else if("10279" == jQuery("#xxdm").val()) {		
		var yhtsxx = '<br /><font color="red">��ע������д��ѧУ���������ѧ�����п���</font>';
		jQuery("td[name=zdybdcon_td_yhdm]").append(yhtsxx);
		var dsxxsm = '<br /><font color="red">��ע���������ɲ���д��</font>';
		jQuery("td[name=zdybdcon_td_xzxm]").append(dsxxsm);
		
	}else if ("10351" == jQuery("#xxdm").val()){

		if ("" == jQuery("#bz").val()) {
			jQuery("#bz").val("����д�������ۣ������Ը��ص㣬��Ȥ���ã���ͥ���������������");
			jQuery("#bz").css("color", "#7D7D7D");
		}
		jQuery("#bz").focus(function() {
			if ("����д�������ۣ������Ը��ص㣬��Ȥ���ã���ͥ���������������" == jQuery("#bz").val()) {
				jQuery("#bz").val("");
				jQuery("#bz").css("color", "");
			}
		});
		jQuery("#bz").blur(function() {
			if ("" == jQuery("#bz").val()) {
				jQuery("#bz").val("����д�������ۣ������Ը��ص㣬��Ȥ���ã���ͥ���������������");
				jQuery("#bz").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#sjhm").val()) {
			jQuery("#sjhm").val("����д�ֻ�����");
			jQuery("#sjhm").css("color", "#7D7D7D");
		}
		jQuery("#sjhm").focus(function() {
			if ("����д�ֻ�����" == jQuery("#sjhm").val()) {
				jQuery("#sjhm").val("");
				jQuery("#sjhm").css("color", "");
			}
		});
		jQuery("#sjhm").blur(function() {
			if ("" == jQuery("#sjhm").val()) {
				jQuery("#sjhm").val("����д�ֻ�����");
				jQuery("#sjhm").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#jtdh").val()) {
			jQuery("#jtdh").val("���������ϵ�˺���");
			jQuery("#jtdh").css("color", "#7D7D7D");
		}
		jQuery("#jtdh").focus(function() {
			if ("���������ϵ�˺���" == jQuery("#jtdh").val()) {
				jQuery("#jtdh").val("");
				jQuery("#jtdh").css("color", "");
			}
		});
		jQuery("#jtdh").blur(function() {
			if ("" == jQuery("#jtdh").val()) {
				jQuery("#jtdh").val("���������ϵ�˺���");
				jQuery("#jtdh").css("color", "#7D7D7D");
			}
		});
	} else if("10335" == jQuery("#xxdm").val()){
		if(jQuery("input:radio[name='shgxzw1']:checked").val() == '��'){
			jQuery("th[name='zdybdcon_th_shgxdwdh1'] ").html("<span class='red'>*</span>У��ס�޵�ַ");
		}else{
			jQuery("th[name='zdybdcon_th_shgxdwdh1']").remove();
			jQuery("td[name='zdybdcon_td_shgxdwdh1']").remove();
			//���˷�ֹ��ԭ����e�`�������Aԓ�����[����
			jQuery("#zdybdcon_table_xsxx_update_lxfs > tbody ").append("<input type='hidden' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'>");
		};
		//�ڻ��պ���
		jQuery("#shgxxm2").focus(function() {
			if ("���û������д���ޡ�" == jQuery("#shgxxm2").val()) {
				jQuery("#shgxxm2").val("");
				jQuery("#shgxxm2").css("color", "");
			}
		});
		jQuery("#shgxxm2").blur(function() {
			if ("" == jQuery("#shgxxm2").val()) {
				jQuery("#shgxxm2").val("���û������д���ޡ�");
				jQuery("#shgxxm2").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxxm2").val()) {
			jQuery("#shgxxm2").val("���û������д���ޡ�");
			jQuery("#shgxxm2").css("color", "#7D7D7D");
		}
		//�۰�ͨ��֤����
		jQuery("#shgxxm1").focus(function() {
			if ("���û������д���ޡ�" == jQuery("#shgxxm1").val()) {
				jQuery("#shgxxm1").val("");
				jQuery("#shgxxm1").css("color", "");
			}
		});
		jQuery("#shgxxm1").blur(function() {
			if ("" == jQuery("#shgxxm1").val()) {
				jQuery("#shgxxm1").val("���û������д���ޡ�");
				jQuery("#shgxxm1").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxxm1").val()) {
			jQuery("#shgxxm1").val("���û������д���ޡ�");
			jQuery("#shgxxm1").css("color", "#7D7D7D");
		}
		//̨��ͨ��֤����
		jQuery("#shgxsjhm1").focus(function() {
			if ("���û������д���ޡ�" == jQuery("#shgxsjhm1").val()) {
				jQuery("#shgxsjhm1").val("");
				jQuery("#shgxsjhm1").css("color", "");
			}
		});
		jQuery("#shgxsjhm1").blur(function() {
			if ("" == jQuery("#shgxsjhm1").val()) {
				jQuery("#shgxsjhm1").val("���û������д���ޡ�");
				jQuery("#shgxsjhm1").css("color", "#7D7D7D");
			}
		});
		if ("" == jQuery("#shgxsjhm1").val()) {
			jQuery("#shgxsjhm1").val("���û������д���ޡ�");
			jQuery("#shgxsjhm1").css("color", "#7D7D7D");
		}
		jQuery("td[name='zdybdcon_td_yhkh']").append("<span class='red'>&nbsp;&nbsp;&nbsp;��ѧ�����п�</span>");
		jQuery("input:radio[name='shgxzw1']").change(function(){
			if(this.value == '��'){
				jQuery("#shgxdwdh1").remove();
				jQuery("td[name='zdybdcon_td_shgxzw1']").parent().append("<th width='15%' name='zdybdcon_th_shgxdwdh1'><span class='red'>*</span>У��ס�޵�ַ</th><td width='35%' name='zdybdcon_td_shgxdwdh1' ><input type='text' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'></td>");
			}else{
				jQuery("th[name='zdybdcon_th_shgxdwdh1']").remove();
				jQuery("td[name='zdybdcon_th_shgxdwdh1']").remove();
				jQuery("td[name='zdybdcon_td_shgxdwdh1']").remove();
				jQuery("#shgxdwdh1").remove();
				jQuery("#zdybdcon_table_xsxx_update_lxfs > tbody ").append("<input type='hidden' name='shgxdwdh1' value='' id='shgxdwdh1' class='text_nor' maxlength='25'>");
			}
		});
	}else if("12871" == jQuery("#xxdm").val()){
		var sHtml_dxyz =  "<tr> <th>������֤��<font color='red'>*</font></th><td colspan='3'>";
		sHtml_dxyz = sHtml_dxyz + "<input type='text' class='text_nor' id='dxyzId'/>";
		sHtml_dxyz = sHtml_dxyz + "<button style='margin-left:15px;' id='sendCode' type='button'>����</button>";
		sHtml_dxyz = sHtml_dxyz + "<span id='sendCodeMsg' style='margin-left:15px;color:blue;font-size:12px;'>�������ͻ�ȡ��֤��</span>";
		sHtml_dxyz = sHtml_dxyz + "</td></tr>";
		jQuery("#zdybdcon_table_xsxx_update_lxfs").children("tbody").append(sHtml_dxyz);
		jQuery("#sendCode").click(function(){
			sendCodeF();
		});
	}else if("10125" == jQuery("#xxdm").val()){//ɽ���ƾ�
		jQuery("#zdybdfl_xsxx_update_jnzs")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>�ϴ�����֤�鸽��</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >������...</span></div>" +
				"</td></tr></tbody></table>");
		jQuery('#fjid').val(jQuery('#yyfj').val());
		initData_10125();
		if(jQuery("#shgxdwdh1").val() == "��"){
			jQuery("[name='zdybdcon_th_shgxgx2']").html("");
			jQuery("#shgxgx2").hide();
			jQuery("#shgxgx2").val("");
		}else{
			jQuery("[name='zdybdcon_th_shgxgx2']").html("<span class='red'>*</span>�м�֤���");
			jQuery("#shgxgx2").show();
		}
		jQuery("#shgxdwdh1").change(function(){
			if(jQuery("#shgxdwdh1").val() == "��"){
				jQuery("[name='zdybdcon_th_shgxgx2']").html("");
				jQuery("#shgxgx2").hide();
				jQuery("#shgxgx2").val("");
			}else{
				jQuery("[name='zdybdcon_th_shgxgx2']").html("<span class='red'>*</span>�м�֤���");
				jQuery("#shgxgx2").show();
			}
		})
	}else if("10596" == jQuery("#xxdm").val()){
		if ("��" == jQuery("#zd1").val()) {
			jQuery("#zd2").show();
		} else {
			jQuery("#zd2").hide();
		}
		jQuery("#zd1").change(function() {
			if ("��" == jQuery("#zd1").val()) {
				jQuery("#zd2").show();
			} else {
				jQuery("#zd2").hide();
			}
		});
	}else if("10704" == jQuery("#xxdm").val()){/*�����Ƽ���ѧ���Ի������ϴ���2017-11-20��Meng.Wei*/
		jQuery("#zdybdfl_xsxx_update_fjxx_10704")
		.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>�ϴ�����</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
				"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >������...</span></div>" +
				"</td></tr></tbody></table>");
		jQuery('#fjid').val(jQuery('#zd6_fj').val());
		showAge();
	}
	jQuery("#zdybdfl_li_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_byxx").remove();
	jQuery("#zdybdfl_li_xsxx_update_byxx").remove();
	xsGkPic();
	if("10718" != jQuery("#xxdm").val()){
		jQuery("#zdybdfl_xsxx_update_jtcyxxxg > table:eq(0) > thead > tr > th > span").append("<lable style='font-weight:normal'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ͥ��Ա����(��������)&nbsp;&nbsp;</lable>" +
		"<input type='text' name='checkjtrs'  id='checkjtrs' class='text_nor' onkeyup='checkInput(this)' style='width:35px;'>");
		var jtrs = jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1;
		if(parseInt(jtrs) > 0){
			jQuery("#checkjtrs").val(jtrs);
		}
		jQuery("#checkjtrs").keydown(function(event){
	        if(event.keyCode == 13){ 
	        	var txrs = jQuery(this).val();
	        	var xyrs = jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1;
	        	var checknum =parseInt(txrs) - parseInt(xyrs);
	        	if(checknum < 0){
	        		showAlert("��д�ļ�ͥ��Ա����С���������еļ�ͥ��Ա��������ȷ�ϣ�");
	        		return false;
	        	}
	            for(i = 0;i<checknum;i++){
	            	addConMoreUpdateTr('xsxx_update_jtcyxxxg');
	            } 
	        }
	    }); 
	}
	if(jQuery("td[name = 'zdybdcon_td_jtdzx']").length == 1){
		var jtdz = jQuery("td[name = 'zdybdcon_td_jtdz']").html();
		var jtdzval = jQuery("#jtdz").val();
		jQuery("td[name = 'zdybdcon_td_jtdz']").find("#jtdz").remove();
		jQuery("th[name = 'zdybdcon_th_jtdz']").html("");
		jQuery("td[name = 'zdybdcon_td_jtdzx']").append(jtdz);
		jQuery("#jtdz").val(jtdzval);
	}
	if("11354" == jQuery("#xxdm").val()) {
		if ("��" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("ҽ������");
		}else{
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
		onChange_11354();
	}
	//������ҽҩ��ѧ���Ի�
	if("10026" == jQuery("#xxdm").val()){
		var fHtml = "����<��>";
		var zd1Val=jQuery("#zd1").val();
		var tHtml="<input type='text' name='zd1' value='"+zd1Val+"' id='zd1' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd1']").html(fHtml+tHtml);
		jQuery("#zd1").parent().append("&nbsp;&nbsp;&nbsp;��:������");
		
		citySelect("zd1");
	}
	//���ϳ��и��Ի�
	if("11527" == jQuery("#xxdm").val()){
		var fHtml = "��ɳ<��>";
		var zd4Val=jQuery("#zd4").val();
		var tHtml="<input type='text' name='zd4' value='"+zd4Val+"' id='zd4' class='text_nor' maxlength='20' style='width:50px'>"
		jQuery("td[name='zdybdcon_td_zd4']").html(fHtml+tHtml);
		jQuery("#zd4").parent().append("&nbsp;&nbsp;&nbsp;��:������");
		
		citySelect("zd4");
	}
});
//������ũ��ְҵѧԺ
function onChange_12727() {
	jQuery("#jkzk").change(function() {
		if ("����" == jQuery(this).val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").parents("tr").hide();
		}else{
			jQuery("#zd3").parents("tr").show();
		}
	});
	jQuery("#jkzk").change();
}
//�������ù���ְҵѧԺ
function onChange_14073() {
	jQuery("#sfzsb").change(function() {
		if ("�˳�����" == jQuery(this).val()) {
			jQuery("#sfzfx").parents("tr").show();
		}else{
			jQuery("#sfzfx").val("");
			jQuery("#sfzfx").parents("tr").hide();
		}
	});
	jQuery("#sfzsb").change();
}
//���ְͨҵ
function onChange_12883() {
	if ("��" == jQuery("#zd1").val()) {
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd2").parents("tr").hide();
	}
	jQuery("#zd1").change(function() {
		if ("��" == jQuery("#zd1").val()) {
			jQuery("#zd2").val("");
			jQuery("#zd2").parents("tr").hide();
		} else {
			jQuery("#zd2").parents("tr").show();
		}
	});
}
//������óѧУѧ������Ͷ�����Ի�
function onChange(){
	
	if ("��" == jQuery("#zd1").val()) {
		jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
		jQuery("#zd3").parents("tr").show();
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd3").parents("tr").hide();
		jQuery("#zd2").parents("tr").hide();
	}
	if ("��" == jQuery("#sftb").val()) {
		jQuery("#tbsj").parents("tr").hide();
		jQuery("#sfyqrzs").parents("tr").show();
	} else {
		jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
		jQuery("#tbsj").css("color", "#7D7D7D");
		jQuery("#tbsj").parents("tr").show();
		jQuery("#sfyqrzs").parents("tr").hide();
	}
	jQuery("#sftb").change(function() {
		if ("��" == jQuery("#sftb").val()) {
			jQuery("#tbsj").val("");
			jQuery("#bxxz").val("");
			jQuery("#tbsj").parents("tr").hide();
			jQuery("#sfyqrzs").parents("tr").show();
		} else {
			jQuery("#qtyy").val("");
			jQuery("#sfyqrzs").val("");
			jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
			jQuery("#tbsj").parents("tr").show();
			jQuery("#sfyqrzs").parents("tr").hide();
		}
	});
	jQuery("#zd1").change(function() {
		if ("��" == jQuery("#zd1").val()) {
			jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
			jQuery("#zd3").parents("tr").show();
			jQuery("#zd2").parents("tr").show();
		} else {
			jQuery("#zd3").val("");
			jQuery("#zd2").val("");
			jQuery("#zd3").parents("tr").hide();
			jQuery("#zd2").parents("tr").hide();
		}
	});
	jQuery("#tbsj").focus(function() {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
			jQuery("#tbsj").css("color", "");
		}
	});
	jQuery("#tbsj").blur(function() {
		if ("" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
		}
	});
	if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").css("color", "#7D7D7D");
	}
	jQuery("#zd3").focus(function() {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").css("color", "");
		}
	});
	jQuery("#zd3").blur(function() {
		if ("" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
		}
	});
	if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
		jQuery("#zd3").css("color", "#7D7D7D");
	}
	jQuery("#qtyy").attr("readonly","readonly");
	jQuery("#qtyy").attr("title","�ò����ɰ�������д");
}
//����ʦ���߿���Ƭ���Ի�
function xsGkPic(){
	 if("10511" != jQuery("#xxdm").val()) {
			jQuery("#stuGkImg").css("display", "none");
			}
		    else{
		    	jQuery("#stuGkImg").css("display", "");
			    }
	        jQuery("#zpscbtn").css("display", "");
		    jQuery("#gkzpscbtn").css("display", "none");
}
function onShow(flag) {
	var url = "xsxx_xsxxxg.do?method=xgsq&type=query";
	var xxdm = jQuery("#xxdm").val();
	
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : jQuery("#xh").val()
		},
		dataType : "json",
		success : function(data) {
			var xm = data.xm;
			jQuery("#xmView").html(xm);
			var params = {js:'xs'};//ѧ����ɫ
			zdybdInit(gndm, data,params,flag);

			setXgzd(flag);// �����޸��ֶ�
			
			//���������Ի�  �ظ������XG_2014-0020  ��3�� --- ��ͥ��Ա��ϢĬ����ʾһ���ұ�������Ӷ��У���ÿ��ѧ����������дһ����ͥ��Ա��Ϣ
			// ���ߡ���ͥ��Ա�б����ֶΡ�
			if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
					&& ("11660" == xxdm||"11067"==xxdm||"10351"==xxdm || checkJtcyxxxgSfbt())
					){ // ����ͥ��Ա�Ƿ��б����ֶ�
				addConMoreUpdateTr("xsxx_update_jtcyxxxg");
			}
	}
	});

	if (parent.document.getElementById("ifm") == null) {// ǿ���޸�ҳ��
		isQzxg = true;
	}

	jsts();// ��Ե�����λ
	var kfxg = jQuery("#kfxg").val();// �ɷ��޸�,y,n
	var shjg = jQuery("#shjg").val();// ���µļ�¼��˽��
	var sqid = jQuery("#shzSqid").val();
	var shyj = jQuery("#shyj").val();
	var sqcs = jQuery("#sqcs").val();
	var shjgzt = jQuery("#shjgzt").val();
	if (kfxg != "n") {// �����޸Ĺ���
		if (sqid != null && sqid != "") {
			var tip = "��ǰ�޸���Ϣ����<font color='red'>�����</font>�����ܽ��в�����";
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#zpscbtn").hide();
			if(shjgzt==""||shjgzt==null){
				jQuery("#btnTable").show();
				jQuery("#buttonSave").hide();
			}
		} else if("2"==shjg){
			var tip = "��ǰ��Ϣ�޸�����<font color='red'>��ͨ��</font>��<br/>��������"+shyj;
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
		}
		else if("3"==shjg){
			var tip = "��ǰ��Ϣ�޸�����<font color='red'>���˻�</font>��<br/>��������"+shyj;
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
			
		} else if("1"==shjg && "1"==sqcs){
			var tip = "��<font color='red'>�Ѿ��޸�</font>��ѧ����Ϣ�����ܽ��в�����";
			jQuery("#pts p").html(tip);
			jQuery("#pts").show();
			jQuery("#btnTable").show();
			jQuery("#buttonSave").hide();
			jQuery("#buttonSubmit").hide();
		}
		else{
			jQuery("#btnTable").show();
		}
	} else {// δ�����޸Ĺ���
		var tip = "ѧ����Ϣ�޸�״̬�ѹرգ�";
		jQuery("#pts p").html(tip);
		jQuery("#pts").show();
		jQuery("#zpscbtn").hide();
		jQuery("#btnTable").show();
		jQuery("#buttonSave").hide();
		jQuery("#buttonSubmit").hide();
	}
}

/**
 * �����޸��ֶ�
 * 
 * @return
 */
function setXgzd(flag) {
	var sqid = jQuery("#shzSqid").val();
	if (sqid == null || sqid == "") {
		sqid = jQuery("#dshSqid").val();
	}
	if (sqid == null || sqid == "") {
		return;
	}
	var url = "xsxx_xsxxxg.do?method=getXgzdList";
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
		}
	});
}
//������ó���Ի�Ͷ������ѵ�ֶ�ֵ����
function initParam(){
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
		}
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	} else if ("10351" == jQuery("#xxdm").val()){
		if ("����д�������ۣ������Ը��ص㣬��Ȥ���ã���ͥ���������������" == jQuery("#bz").val()) {
			jQuery("#bz").val("");
		}
		if ("����д�ֻ�����" == jQuery("#sjhm").val()) {
			jQuery("#sjhm").val("");
		}
		if ("���������ϵ�˺���" == jQuery("#jtdh").val()) {
			jQuery("#jtdh").val("");
		}
	}
	
}
function saveForm(type) {
	var xxdm = jQuery("#xxdm").val();
	if("12871" == xxdm){
		var codeFlag = checkCode();
		if(!codeFlag) return false;
	}
	initParam();
	if (!zdybdCheck()) {
		return;
	}
	//����ְҵ���Ի�
	if ("12872" == jQuery("#xxdm").val()) {
		if(jQuery("#sjhm").val() == jQuery("#jtdh").val()){
			alertError("��ϵ�绰���ͥ�绰������ͬ�����޸ģ�");
			return false;
		}
	}
	
	/*�����Ի��ж�У��ס�޸��Ի��ж�*/
	if("10335" == jQuery("#xxdm").val()){
		if( jQuery("th[name='zdybdcon_th_shgxdwdh1']").length == 1 && jQuery.trim(jQuery("#shgxdwdh1").val()) == ''){
			alertError("У��ס�޵�ַ����Ϊ�գ�");
			jQuery("input[id=shgxdwdh1]").focus();
			jQuery("input[id=shgxdwdh1]").css("border", "4px solid #15bcaa");
			jQuery("input[id=shgxdwdh1]").keyup(function(){jQuery('input[id=shgxdwdh1]').removeAttr('style');});
			return false;
		}
		if(jQuery("#shgxgzdw1").val().length != 0 && jQuery("#shgxgzdw1").val().length != 5 && jQuery("#shgxgzdw1").val().length != 6){
			alertError("У԰���˺�λ��������5-6λ��");
			return false;
		}
	}

	/*ɽ���ƾ�������Ի��ж�*/
	if("10125" == jQuery("#xxdm").val()){
		//���Ƿ�м��ֶ�Ϊ��ʱ���м�֤��Ų�Ϊ���
		if(jQuery("#shgxdwdh1").val() == "��" && jQuery.trim(jQuery("#shgxgx2").val()) == ""){
			alertError("�м�֤��Ų���Ϊ�գ�");
			return false;
		}
		if(jQuery("#shgxsjhm1").val() == "��" && jQuery.trim(jQuery("#zd1").val()) == ""){
			alertError("����Ǩ�벻��Ϊ�գ�");
			return false;
		}
		if(jQuery("#shgxsjhm1").val() == "��" && jQuery.trim(jQuery("#zd2").val()) == ""){
			alertError("�ֻ������ڵز���Ϊ�գ�");
			return false;
		}
		if(jQuery("#shgxgzdw1").val() == "����(��д���屣������)" && jQuery.trim(jQuery("#zd3").val()) == ""){
			alertError("ҽ�����Ʋ���Ϊ�գ�");
			return false;
		}
		demoHtml = "�밴���¸�ʽ����:\n��Сѧ�����ֽ׶Σ���ʽΪ��*��*��-*��*�£�*ѧУ��������ѧ���ɲ�ְ��δ������ѧ����";
		if(jQuery("#rxqwhcd").val()==demoHtml){
			jQuery("#rxqwhcd").val("");
		}
	}
	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("�����ϴ�һ����Ƭ��")
		return false;
	}
	if (!getXgzdJson()) {
//		alertError("���޸�������������ݣ�")
//		return false;
	}
	
	
	//���������Ի�  �ظ������XG_2014-0020  ��3�� --- ��ͥ��Ա��ϢĬ����ʾһ���ұ�������Ӷ��У���ÿ��ѧ����������дһ����ͥ��Ա��Ϣ
	// ���ߡ���ͥ��Ա�б����ֶΡ�
	if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
			&& ("11660" == xxdm||"11067"==xxdm||"10351"==xxdm || checkJtcyxxxgSfbt())
			){ // ����ͥ��Ա�Ƿ��б����ֶ�
		alertError("������дһ����ͥ��Ա��Ϣ��")
		return false;
	}
	
	//�����Ƽ�ʦ����ѧ  ������д����ѧ����ᾭ����Ϣ
	if(jQuery("#zdybdcon_table_xsxx_update_xlshjlxxxg tbody tr").length < 3
			&& ("11318" == xxdm)
			){
		alertError("������д����ѧ����ᾭ����Ϣ��")
		return false;
	}
	
	if("10718" != xxdm){
		if(jQuery("#checkjtrs").val() != (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg > tbody > tr").length-1)){
			showAlert("��ͥ��Ա��������д�ļ�ͥ��Ա������һ�£�");
			return false;
		}
	}

	var url = "xsxx_xsxxxg.do?method=xgsq&type="+type;
	url += "&timestamp=" + new Date().getTime();

	jQuery("#btnTable button").attr("disabled", "disabled");
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlertDivLayer(data["message"]);
		} else {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						//ǿ���޸�
						if (isQzxg) {
						//	refershParent();
							location.href = "/xgxt/" + "stuPage.jsp";
						} else {
							jQuery("#btnTable button").removeAttr("disabled");
							var xsxgsplc = jQuery("#xsxgsplc").val();
							if (xsxgsplc != null && xsxgsplc != '' && xsxgsplc!= 'wxsh') {
								if("submit" == type) {
									var tip = "��ǰ�޸���Ϣ����<font color='red'>�����</font>�����ܽ��в�����";
									jQuery("#pts p").html(tip);
									jQuery("#pts").show();
									jQuery("#btnTable").show();
									jQuery("#buttonSave").hide();
								}
							}
					//	window.location.reload();
						}
					}
				}
			});
		}
	});

}

/*
 * ��Ե�����λ
 */
function jsts() {
	jQuery("a").each(function() {
		var link = jQuery(this);
		var href = link.attr("href");
		if (href && href[0] == "#") {
			var name = href.substring(1);
			jQuery(this).click(function() {
				var nameElement = jQuery("[name='" + name + "']");
				var idElement = jQuery("#" + name);
				var element = null;
				if (nameElement.length > 0) {
					element = nameElement;
				} else if (idElement.length > 0) {
					element = idElement;
				}
				if (element) {
					var offset = element.offset();
					window.parent.scrollTo(offset.left, offset.top + 85);// 85
				}
				return false;
			});
		}
	});
}
/*����ʦ����ѧ���˲еȼ�����˭һ������ҳ�����Ĭ������*/
function contentHide_10718(){
	if("jkzt_002" != jQuery("#jkzk").val()){
		jQuery("#jtdzs").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_jtdzs']").empty();
	}
	if("��" != jQuery("#sfzsb").val()){
		jQuery("#zyjb").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_zyjb']").empty();
	}
	
}
/* ����ʦ����ѧ,����״��<-->�˲еȼ��������Ƿ�¶�<-->��˭һ���������� */
function onChange_10718(){
	jQuery("#jkzk").change(function() {
		if ("jkzt_002" == jQuery(this).val()) {
			jQuery("#jtdzs").show();
			jQuery("th[name='zdybdcon_th_jtdzs']").empty().append("�˲еȼ�");
		}else{
			jQuery("#jtdzs").hide();
			jQuery("#jtdzs").val("");
			jQuery("th[name='zdybdcon_th_jtdzs']").empty();
		}
	});
	
	jQuery("#sfzsb").change(function() {
		if ("��" == jQuery(this).val()) {
			jQuery("#zyjb").show();
			jQuery("th[name='zdybdcon_th_zyjb']").empty().append("��˭һ������");
		}else{
			jQuery("#zyjb").hide();
			jQuery("#zyjb").val("");
			jQuery("th[name='zdybdcon_th_zyjb']").empty()
		}
	});
}

function onChange_11354(){
	jQuery("#sftb").change(function() {
		if ("��" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("ҽ������");
		}else{
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
	});
}


/*��̶��ѧѧ��������Ϣȷ��*/
function xiangtan_ssxxqr_change_10530(){
	jQuery("#sfhq").change(function() {
		if ("��" == jQuery(this).val()) {
			showAlert("���ȷ�ϵ�ǰϵͳ��Ԣ������Ϣ��ʵ�ʲ������뵽��Ԣ����������춯������ȥ���������춯��");
		}
	});
	jQuery("#zdybdfl_xsxx_update_new_hjqk")
	.append("<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'><tbody><tr><th>�ϴ���֤�鸽��</th><td colspan='4'><input type='hidden' name='zd6' id='fjid'/><input type='file' id='commonfileupload' name='uploadFile'/><span id='tips' style='color: red'></span>" +
			"<span id='tips' style='color: red'></span><div id='commonfileupload-list' style='padding: 5px;'><span class='loading' id='loading' style='font-size: 10px; margin-left: 0; display: none;' >������...</span></div>" +
			"</td></tr></tbody></table>");
	jQuery('#fjid').val(jQuery('#yyfj').val());
}

function initData_10125(){	
	demoHtml = "�밴���¸�ʽ����:\n";
	demoHtml += "��Сѧ�����ֽ׶Σ���ʽΪ��*��*��-*��*�£�*ѧУ��������ѧ���ɲ�ְ��δ������ѧ����";
	if(jQuery("#rxqwhcd").val()==""){
		jQuery("#rxqwhcd").val(demoHtml);
		jQuery("#rxqwhcd").css("color", "#999");
	}
	
	jQuery("#rxqwhcd").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#rxqwhcd").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
	jQuery("td[name=zdybdcon_td_zd1]").append('<br/>��ʽ: ��___Ǩ��___');
	
	if(jQuery("#shgxsjhm1").val() == "��" ){
		jQuery("#zd1").attr("readonly",false);
		jQuery("#zd2").attr("readonly",true);
		jQuery("th[name=zdybdcon_th_zd1]").html('<span class="red">*</span>����Ǩ��');
		jQuery("th[name=zdybdcon_th_zd2]").html('�ֻ������ڵ�');
	}else{
		jQuery("#zd1").attr("readonly",true);
		jQuery("#zd2").attr("readonly",false);
		jQuery("th[name=zdybdcon_th_zd1]").html('����Ǩ��');
		jQuery("th[name=zdybdcon_th_zd2]").html('<span class="red">*</span>�ֻ������ڵ�');
	}
	jQuery("#shgxsjhm1").change(function(){
		if(jQuery(this).val() == "��" ){
			jQuery("#zd1").attr("readonly",false);
			jQuery("#zd2").attr("readonly",true);
			jQuery("#zd2").val("");
			jQuery("th[name=zdybdcon_th_zd1]").html('<span class="red">*</span>����Ǩ��');
			jQuery("th[name=zdybdcon_th_zd2]").html('�ֻ������ڵ�');
		}else{
			jQuery("#zd1").attr("readonly",true);
			jQuery("#zd2").attr("readonly",false);
			jQuery("#zd1").val("");
			jQuery("th[name=zdybdcon_th_zd1]").html('����Ǩ��');
			jQuery("th[name=zdybdcon_th_zd2]").html('<span class="red">*</span>�ֻ������ڵ�');
		}
	})
	if(jQuery("#shgxgzdw1").val() == "����(��д���屣������)" ){
		jQuery("#zd3").attr("readonly",false);
		jQuery("th[name=zdybdcon_th_zd3]").html('<span class="red">*</span>ҽ������');
	}else{
		jQuery("#zd3").attr("readonly",true);
		jQuery("th[name=zdybdcon_th_zd3]").html('ҽ������');
	}
	jQuery("#shgxgzdw1").change(function(){
		if(jQuery("#shgxgzdw1").val() == "����(��д���屣������)" ){
			jQuery("#zd3").attr("readonly",false);
			jQuery("th[name=zdybdcon_th_zd3]").html('<span class="red">*</span>ҽ������');
		}else{
			jQuery("#zd3").attr("readonly",true);
			jQuery("#zd3").val("");
			jQuery("th[name=zdybdcon_th_zd3]").html('ҽ������');
		}
	})
	
}

function sendCodeF() {
   var sjhm = jQuery("#sjhm").val();
   if(null==sjhm||""==sjhm||"undefind"==sjhm||sjhm.length!=11){
	   alertError("��������ȷ���ֻ�����!");
	   return false;
   }
  �� curCount = count;
   var url = "xsxx_xsxxxg.do?method=sendCode";
   var flag = false;
   jQuery.ajaxSetup( {async : false});
   jQuery.post(url,{'sjhm':sjhm},function(data){
	   if(data==true){
		   flag = true;
	   }
   },"json");
   jQuery.ajaxSetup( {async : true});
   if(flag){
	   jQuery("#sendCode").attr("disabled", "disabled");
	   jQuery("#sendCode").css({'background':'#ccc'});
	   jQuery("#sendCodeMsg").html("��֤���Ѿ�����,����" + curCount + "����������֤��");
	   InterValObj = window.setInterval(SetRemainTime, 1000); //������ʱ����1��ִ��һ��
   }else{
	   alertError("��ȡ��֤��ʧ��!");
	   return false;
   }
  
  
}

function checkCode() {
	   var flag = true;
	   var url = "xsxx_xsxxxg.do?method=checkCode";
	   var code = jQuery("#dxyzId").val();
	   if(null==code||""==code||"undefind"==code){
		   alertError("�����������֤��!")
		   return false;
	   }
	   jQuery.ajaxSetup( {async : false});
	   jQuery.post(url,{'code':code},function(data){
		   if(data==false){
			   alertError("��֤����Ч���ѹ�ʱ,�����»�ȡ!");
			   flag = false;
		   }
	   },"json");
	   jQuery.ajaxSetup( {async : true});
	   return flag;
	}

	//timer������
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//ֹͣ��ʱ��
        jQuery("#sendCode").removeAttr("disabled");//���ð�ť
        jQuery("#sendCode").text("���·�����֤��");
        jQuery("#sendCode").css({'background':''});
        jQuery("#sendCodeMsg").html("�������·��Ͱ�ť��ȡ��֤��!");
    }
    else {
        curCount--;
        jQuery("#sendCodeMsg").html("��֤���Ѿ�����,����" + curCount + "����������֤��,������Ч");
    }
}

function showAge(){
	var age = calculateAges(jQuery("#csrq").val());
	var jgtr = jQuery("[name='jg']").parents("tr").eq(0);
	jgtr.before("<tr><th width='15%'>����</th><td width='35%'>"+age+"</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

//���ݳ������ڼ�������
function calculateAges(str)   
{   
      var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null)return "";     
      var d= new Date(r[1],r[3]-1,r[4]);     
      if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4])   
      {   
            var Y = new Date().getFullYear();   
            return((Y-r[1]));   
      }   
      return("��������ڸ�ʽ����");   
}  