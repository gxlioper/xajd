var gndm = "xsxx_update";// ���ܴ���
var dataO;
jQuery(function() {
	onShow();
	jQuery("#zdybdfl_li_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_zwjd").remove();
	jQuery("#zdybdfl_xsxx_update_byxx").remove();
	jQuery("#zdybdfl_li_xsxx_update_byxx").remove();
	xsGkPic();
	if (jQuery("td[name = 'zdybdcon_td_jtdzx']").length == 1) {
		var jtdz = jQuery("td[name = 'zdybdcon_td_jtdz']").html();
		var jtdzval = jQuery("#jtdz").val();
		jQuery("td[name = 'zdybdcon_td_jtdz']").parent().remove();
		jQuery("th[name = 'zdybdcon_th_jtdz']").html("");
		jQuery("td[name = 'zdybdcon_td_jtdzx']").append(jtdz);
		jQuery("#jtdz").val(jtdzval);
	}
	//������ �־�ס�� �ı���߶ȵ���
    if (jQuery("#xwzsxxdz").length == 1) {
        jQuery("#xwzsxxdz").css("height","20px")
    }
    if (jQuery("#zd2").length == 1) {
        jQuery("#zd2").css("height","20px")
    }
    if (jQuery("#sfzd").length == 1 && jQuery("#zd2").length) {
        var sfzd = jQuery("#sfzd").val();
        var xwdzTr = jQuery("#zd2").parent().parent();
        if(sfzd == '��'){
            xwdzTr.show();
        }else{
            xwdzTr.hide();
		}
        jQuery("#sfzd").click(function(){
        	var val = jQuery("#sfzd").val();
            if(val == '��'){
                xwdzTr.show();
            }else{
                jQuery("#zd2").val("");
                xwdzTr.hide();
            }
		});
    }

    //��ż�ͥ��Աɾ����
	//����ͥ��Ա���ɾ����ťʱ�ḳֵ��������zdybd.js  deleteTr������
    var html = "<input type='hidden' name='jtcyDelList' id='jtcyDelList' value=''>";
	if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg").length > 0){
        jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg").parent().append(html);
	}


});

// ������óѧУѧ������Ͷ������ѵ���Ի�
function onChange() {
	if ("��" == jQuery("#zd1").val()) {
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
	if ("" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
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
	if ("" == jQuery("#zd3").val()) {
		jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
	}

}
// ����ʦ���߿���Ƭ���Ի�
function xsGkPic() {
	if ("10511" != jQuery("#xxdm").val()) {
		jQuery("#addGkPic").css("display", "none");
		jQuery("#stuGkImg").css("display", "none");
		jQuery("#gkzpscbtn").css("display", "none");
	}

}
function onShow() {
	var url = "xsxx_xsxxgl.do?method=xsxxglXg&type=query";
	var xxdm = jQuery("#xxdm").val();
	jQuery
			.ajax( {
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
					var flag = "xg";
					zdybdInit(gndm, data, null, flag);
					dataO = data;// ��ֵ��ҳ�浱�е�js��
				// ���������Ի� �ظ������XG_2014-0020 ��3�� ---
				// ���ߡ���ͥ��Ա�б����ֶΡ�
				// ��ͥ��Ա��ϢĬ����ʾһ���ұ�������Ӷ��У���ÿ��ѧ����������дһ����ͥ��Ա��Ϣ
				if (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
						&& ("11660" == xxdm || "11067" == xxdm
								|| "10351" == xxdm || checkJtcyxxxgSfbt())) {
					addConMoreUpdateTr("xsxx_update_jtcyxxxg");
				}
			}
			});
}
// ������ó���Ի�Ͷ������ѵ�ֶ�ֵ����
function initParam() {
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
		}
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	} else if ("10351" == jQuery("#xxdm").val()) {
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
function saveForm() {
	if (!zdybdCheck()) {
		return;
	}
    jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr:gt(0)").each(function(index){
        var value = jQuery.trim(jQuery(this).find("td:visible [name='jtcyzjlx']").val());
        if(value == "�������"){
            var value = jQuery.trim(jQuery(this).find("td:visible [name='jtcyzjhm']").val());
            var result = checkZdybdSfzh("jtcyzjhm", "֤������", value);
            if(!result.success){
                alertError(result.message);
                return false;
			}
		}
	});
	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("�����ϴ�һ����Ƭ��")
		return false;
	}
	initParam();
	// ���������Ի� �ظ������XG_2014-0020 ��3�� ---
	// ���ߡ���ͥ��Ա�б����ֶΡ�
	// ��ͥ��Ա��ϢĬ����ʾһ���ұ�������Ӷ��У���ÿ��ѧ����������дһ����ͥ��Ա��Ϣ
	var xxdm = jQuery("#xxdm").val();
	if (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
			&& ("11660" == xxdm || "11067" == xxdm || "10351" == xxdm
					|| "12866" == xxdm || checkJtcyxxxgSfbt())) {
		alertError("������дһ����ͥ��Ա��Ϣ��");
		return false;
	}

	var url = "xsxx_xsxxgl.do?method=xsxxglXg&type=update";
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
/* ����ʦ����ѧ,����״��<-->�˲еȼ��������Ƿ�¶�<-->��˭һ���������� */
function onChange_10718() {
	jQuery("#jkzk").change(function() {
		if ("jkzt_002" == jQuery(this).val()) {
			jQuery("#jtdzs").show();
			jQuery("th[name='zdybdcon_th_jtdzs']").empty().append("�˲еȼ�");
		} else {
			jQuery("#jtdzs").hide();
			jQuery("#jtdzs").val("");
			jQuery("th[name='zdybdcon_th_jtdzs']").empty();
		}
	});

	jQuery("#sfzsb").change(function() {
		if ("��" == jQuery(this).val()) {
			jQuery("#zyjb").show();
			jQuery("th[name='zdybdcon_th_zyjb']").empty().append("��˭һ������");
		} else {
			jQuery("#zyjb").hide();
			jQuery("#zyjb").val("");
			jQuery("th[name='zdybdcon_th_zyjb']").empty()
		}
	});
}
/* ����ʦ����ѧ���˲еȼ�����˭һ������ҳ�����Ĭ������ */
function contentHide_10718() {
	if ("jkzt_002" != jQuery("#jkzk").val()) {
		jQuery("#jtdzs").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_jtdzs']").empty();
	}
	if ("��" != jQuery("#sfzsb").val()) {
		jQuery("#zyjb").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_zyjb']").empty();
	}

}

function onChange_11354() {
	jQuery("#sftb").change(function() {
		if ("��" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("ҽ������");
		} else {
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
	});
}

/* ��̶��ѧѧ��������Ϣȷ�� */
function xiangtan_ssxxqr_change_10530() {
	jQuery("#sfhq").change(function() {
		if ("��" == jQuery(this).val()) {
			showAlert("���ȷ�ϵ�ǰϵͳ��Ԣ������Ϣ��ʵ�ʲ������뵽��Ԣ����������춯������ȥ���������춯��");
		}
	});
}

function initData_10125() {
	demoHtml = "�밴���¸�ʽ����:\n";
	demoHtml += "��Сѧ�����ֽ׶Σ���ʽΪ��*��*��-*��*�£�*ѧУ��������ѧ���ɲ�ְ��δ������ѧ����";
	if (jQuery("#rxqwhcd").val() == "") {
		jQuery("#rxqwhcd").val(demoHtml);
		jQuery("#rxqwhcd").css("color", "#999");
	}

	jQuery("#rxqwhcd").focus(function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		} else {
			jQuery(this).css("color", "");
		}
	});

	jQuery("#rxqwhcd").blur(function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
	jQuery("td[name=zdybdcon_td_zd1]").append('<br/>��ʽ: ��___Ǩ��___');

	if (jQuery("#shgxsjhm1").val() == "��") {
		jQuery("#zd1").attr("readonly", false);
		jQuery("#zd2").attr("readonly", true);
		jQuery("th[name=zdybdcon_th_zd1]").html(
				'<span class="red">*</span>����Ǩ��');
		jQuery("th[name=zdybdcon_th_zd2]").html('�ֻ������ڵ�');
	} else {
		jQuery("#zd1").attr("readonly", true);
		jQuery("#zd2").attr("readonly", false);
		jQuery("th[name=zdybdcon_th_zd1]").html('����Ǩ��');
		jQuery("th[name=zdybdcon_th_zd2]").html(
				'<span class="red">*</span>�ֻ������ڵ�');
	}
	jQuery("#shgxsjhm1").change(
			function() {
				if (jQuery(this).val() == "��") {
					jQuery("#zd1").attr("readonly", false);
					jQuery("#zd2").attr("readonly", true);
					jQuery("#zd2").val("");
					jQuery("th[name=zdybdcon_th_zd1]").html(
							'<span class="red">*</span>����Ǩ��');
					jQuery("th[name=zdybdcon_th_zd2]").html('�ֻ������ڵ�');
				} else {
					jQuery("#zd1").attr("readonly", true);
					jQuery("#zd2").attr("readonly", false);
					jQuery("#zd1").val("");
					jQuery("th[name=zdybdcon_th_zd1]").html('����Ǩ��');
					jQuery("th[name=zdybdcon_th_zd2]").html(
							'<span class="red">*</span>�ֻ������ڵ�');
				}
			})
	if (jQuery("#shgxgzdw1").val() == "����(��д���屣������)") {
		jQuery("#zd3").attr("readonly", false);
		jQuery("th[name=zdybdcon_th_zd3]").html(
				'<span class="red">*</span>ҽ������');
	} else {
		jQuery("#zd3").attr("readonly", true);
		jQuery("th[name=zdybdcon_th_zd3]").html('ҽ������');
	}
	jQuery("#shgxgzdw1").change(
			function() {
				if (jQuery("#shgxgzdw1").val() == "����(��д���屣������)") {
					jQuery("#zd3").attr("readonly", false);
					jQuery("th[name=zdybdcon_th_zd3]").html(
							'<span class="red">*</span>ҽ������');
				} else {
					jQuery("#zd3").attr("readonly", true);
					jQuery("#zd3").val("");
					jQuery("th[name=zdybdcon_th_zd3]").html('ҽ������');
				}
			})

}

/* �㽭��ý��ҵ����1��2ѡ�񻥳ⷽ�� begin */

var jjyx_1_opt_all; // ��ҵ����1 ����option
var jjyx_2_opt_all; // ��ҵһ��2 ����option

var i = 0; // ���ҳ���Ƿ��һ�μ���

function changeJjyx() {

	// ��һ�ν�optionֵ������
	if (i == 0) {
		jjyx_1_opt_all = jQuery("#shgxxm1 option");
		jjyx_2_opt_all = jQuery("#shgxgx1 option");
		i++;
	}

	var jjyx_1 = jQuery("#shgxxm1").val();
	var jjyx_2 = jQuery("#shgxgx1").val();

	// �����һ�μ���Ĭ������ֵ��ͬ�����⣬�������ǲ���������������
	if ("" != jjyx_1 && jjyx_1 == jjyx_2) {
		jjyx_2 = "";
	}

	// ���ݾ�ҵ����1 ��������2������ֵ
	jQuery("#shgxgx1 option").remove();
	jjyx_2_opt_all.each(function() {
		if ("" == jQuery(this).val() || jjyx_1 != jQuery(this).val()) {
			jQuery("#shgxgx1").append(this);
		}
	})

	// ���ݾ�ҵ����2 ��������1������ֵ
	jQuery("#shgxxm1 option").remove();
	jjyx_1_opt_all.each(function() {
		if ("" == jQuery(this).val() || jjyx_2 != jQuery(this).val()) {
			jQuery("#shgxxm1").append(this);
		}
	})

	jQuery("#shgxxm1").val(jjyx_1);
	jQuery("#shgxgx1").val(jjyx_2);

}

/* �㽭��ý��ҵ����1��2ѡ�񻥳ⷽ�� end */

/* �㽭��ý��ҵ����1��2��Ϊ����begin */
function changeJjyxLd() {

	var jjyx_2 = dataO["shgxgx1"];

	// ɾ����ҵ����2��ֵ
	jQuery("#shgxgx1 option").remove();

	jjyx_2_opt_all = {
		"ֱ�Ӿ�ҵ" : [ "ʡ����λ", "���е�λ", "���ص�λ" ]
	};
	jjyx_2_opt_all.���ڿ��� = [ "��985����У", "��211����У", "��ͨ��У" ];
	jjyx_2_opt_all.�������� = [ "����", "Ӣ��", "����", "�¹�", "�����", "��ŷ", "���ô�", "�Ĵ�����",
			"������", "�ձ�", "����", "�¼���", "��ۡ�����", "��������" ];
	jjyx_2_opt_all.������Ա = [ "���ҹ���Ա", "�ط�����Ա" ];
	jjyx_2_opt_all.��ҵ��λ = [ "�ߵȽ�����λ", "�г�������λ", "������ҵ��λ" ];
	jjyx_2_opt_all.������ҵ = [ "���˴�ҵ", "�ϻﴴҵ", "���幤�̻�" ];
	jjyx_2_opt_all.�����ҵ = [ "�����ƻ�", "�������", "��֧һ��", "��������" ];

	var jjyx_1 = jQuery("#shgxxm1").val();

	if (null == jjyx_1 || "" == jjyx_1) {
		jQuery("#shgxgx1").append("<option value=''>--����ѡ������1--</option>");
	} else {
		jQuery("#shgxgx1").append("<option value=''>--��ѡ��--</option>");
	}
	for ( var key in jjyx_2_opt_all) {
		if (jjyx_1 == key) {
			for ( var i = 0; i < jjyx_2_opt_all[key].length; i++) {
				var selected = "";
				if (jjyx_2 == jjyx_2_opt_all[key][i]) {
					selected = "selected";
				}
				jQuery("#shgxgx1").append(
						"<option value='" + jjyx_2_opt_all[key][i] + "' "
								+ selected + ">" + jjyx_2_opt_all[key][i]
								+ "</option>");
			}
		}
	}

}
/* �㽭��ý��ҵ����1��2��Ϊ����end */

function showAgeForXakj() {
	var age = calculateAgesForXakj(jQuery("#csrq").val().trim());
	var jgtr = jQuery("[name='zdybdcon_th_jg']").parents("tr").eq(0);
	jgtr
			.before("<tr><th width='15%'>����</th><td width='35%'>"
					+ age
					+ "</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

// ���ݳ������ڼ�������
function calculateAgesForXakj(str) {
	if (str.indexOf("-") == -1) {
		str = str.substring(0, 4) + "-" + str.substring(5, 7) + "-"
				+ str.substring(7, 8);
	}
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return "";
	var d = new Date(r[1], r[3] - 1, r[4]);
	if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
			&& d.getDate() == r[4]) {
		var Y = new Date().getFullYear();
		return ((Y - r[1]));
	}
	return ("��������ڸ�ʽ����");
}