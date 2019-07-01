var gndm = "xsxx_update";// 功能代码
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
	//西交大 现居住地 文本域高度调整
    if (jQuery("#xwzsxxdz").length == 1) {
        jQuery("#xwzsxxdz").css("height","20px")
    }
    if (jQuery("#zd2").length == 1) {
        jQuery("#zd2").css("height","20px")
    }
    if (jQuery("#sfzd").length == 1 && jQuery("#zd2").length) {
        var sfzd = jQuery("#sfzd").val();
        var xwdzTr = jQuery("#zd2").parent().parent();
        if(sfzd == '是'){
            xwdzTr.show();
        }else{
            xwdzTr.hide();
		}
        jQuery("#sfzd").click(function(){
        	var val = jQuery("#sfzd").val();
            if(val == '是'){
                xwdzTr.show();
            }else{
                jQuery("#zd2").val("");
                xwdzTr.hide();
            }
		});
    }

    //存放家庭成员删除的
	//当家庭成员点击删除按钮时会赋值（代码在zdybd.js  deleteTr方法）
    var html = "<input type='hidden' name='jtcyDelList' id='jtcyDelList' value=''>";
	if(jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg").length > 0){
        jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg").parent().append(html);
	}


});

// 北京商贸学校学生保险投保、军训个性化
function onChange() {
	if ("是" == jQuery("#zd1").val()) {
		jQuery("#zd3").parents("tr").show();
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd3").parents("tr").hide();
		jQuery("#zd2").parents("tr").hide();
	}
	if ("否" == jQuery("#sftb").val()) {
		jQuery("#tbsj").parents("tr").hide();
		jQuery("#sfyqrzs").parents("tr").show();
	} else {
		jQuery("#tbsj").parents("tr").show();
		jQuery("#sfyqrzs").parents("tr").hide();
	}
	jQuery("#sftb").change(function() {
		if ("否" == jQuery("#sftb").val()) {
			jQuery("#tbsj").val("");
			jQuery("#bxxz").val("");
			jQuery("#tbsj").parents("tr").hide();
			jQuery("#sfyqrzs").parents("tr").show();
		} else {
			jQuery("#qtyy").val("");
			jQuery("#sfyqrzs").val("");
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
			jQuery("#tbsj").parents("tr").show();
			jQuery("#sfyqrzs").parents("tr").hide();
		}
	});
	jQuery("#zd1").change(function() {
		if ("是" == jQuery("#zd1").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
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
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
			jQuery("#tbsj").css("color", "");
		}
	});
	jQuery("#tbsj").blur(function() {
		if ("" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#tbsj").css("color", "#7D7D7D");
	}
	jQuery("#zd3").focus(function() {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").css("color", "");
		}
	});
	jQuery("#zd3").blur(function() {
		if ("" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#zd3").val()) {
		jQuery("#zd3").val("yyyy-mm-dd至yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
	}

}
// 华中师范高考照片个性化
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
					dataO = data;// 赋值给页面当中的js用
				// 重庆理工个性化 回复函编号XG_2014-0020 第3点 ---
				// 或者【家庭成员有必填字段】
				// 家庭成员信息默认显示一行且必填，可增加多行，即每个学生都至少填写一条家庭成员信息
				if (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
						&& ("11660" == xxdm || "11067" == xxdm
								|| "10351" == xxdm || checkJtcyxxxgSfbt())) {
					addConMoreUpdateTr("xsxx_update_jtcyxxxg");
				}
			}
			});
}
// 北京商贸个性化投保，军训字段值重置
function initParam() {
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
		}
		if ("yyyy-mm-dd至yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	} else if ("10351" == jQuery("#xxdm").val()) {
		if ("可填写自我评价，包括性格特点，兴趣安好，家庭情况等描述。。。" == jQuery("#bz").val()) {
			jQuery("#bz").val("");
		}
		if ("请填写手机长号" == jQuery("#sjhm").val()) {
			jQuery("#sjhm").val("");
		}
		if ("紧急情况联系人号码" == jQuery("#jtdh").val()) {
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
        if(value == "居民身份"){
            var value = jQuery.trim(jQuery(this).find("td:visible [name='jtcyzjhm']").val());
            var result = checkZdybdSfzh("jtcyzjhm", "证件号码", value);
            if(!result.success){
                alertError(result.message);
                return false;
			}
		}
	});
	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("请先上传一张照片！")
		return false;
	}
	initParam();
	// 重庆理工个性化 回复函编号XG_2014-0020 第3点 ---
	// 或者【家庭成员有必填字段】
	// 家庭成员信息默认显示一行且必填，可增加多行，即每个学生都至少填写一条家庭成员信息
	var xxdm = jQuery("#xxdm").val();
	if (jQuery("#zdybdcon_table_xsxx_update_jtcyxxxg tbody tr").length == 1
			&& ("11660" == xxdm || "11067" == xxdm || "10351" == xxdm
					|| "12866" == xxdm || checkJtcyxxxgSfbt())) {
		alertError("至少填写一条家庭成员信息！");
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
/* 陕西师范大学,健康状况<-->伤残等级联动，是否孤儿<-->和谁一起生活联动 */
function onChange_10718() {
	jQuery("#jkzk").change(function() {
		if ("jkzt_002" == jQuery(this).val()) {
			jQuery("#jtdzs").show();
			jQuery("th[name='zdybdcon_th_jtdzs']").empty().append("伤残等级");
		} else {
			jQuery("#jtdzs").hide();
			jQuery("#jtdzs").val("");
			jQuery("th[name='zdybdcon_th_jtdzs']").empty();
		}
	});

	jQuery("#sfzsb").change(function() {
		if ("是" == jQuery(this).val()) {
			jQuery("#zyjb").show();
			jQuery("th[name='zdybdcon_th_zyjb']").empty().append("和谁一起生活");
		} else {
			jQuery("#zyjb").hide();
			jQuery("#zyjb").val("");
			jQuery("th[name='zdybdcon_th_zyjb']").empty()
		}
	});
}
/* 陕西师范大学，伤残等级，和谁一起生活页面加载默认隐藏 */
function contentHide_10718() {
	if ("jkzt_002" != jQuery("#jkzk").val()) {
		jQuery("#jtdzs").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_jtdzs']").empty();
	}
	if ("是" != jQuery("#sfzsb").val()) {
		jQuery("#zyjb").unbind('hide').hide();
		jQuery("th[name='zdybdcon_th_zyjb']").empty();
	}

}

function onChange_11354() {
	jQuery("#sftb").change(function() {
		if ("是" == jQuery("#sftb").val()) {
			jQuery("#ylbxh").show();
			jQuery("th[name='zdybdcon_th_ylbxh']").empty().append("医保卡号");
		} else {
			jQuery("#ylbxh").hide();
			jQuery("#ylbxh").val("");
			jQuery("th[name='zdybdcon_th_ylbxh']").empty();
		}
	});
}

/* 湘潭大学学生宿舍信息确认 */
function xiangtan_ssxxqr_change_10530() {
	jQuery("#sfhq").change(function() {
		if ("否" == jQuery(this).val()) {
			showAlert("如果确认当前系统公寓宿舍信息与实际不符，请到公寓管理的宿舍异动功能中去申请宿舍异动！");
		}
	});
}

function initData_10125() {
	demoHtml = "请按如下格式输入:\n";
	demoHtml += "从小学填至现阶段，格式为：*年*月-*年*月，*学校，曾担任学生干部职务（未担任填学生）";
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
	jQuery("td[name=zdybdcon_td_zd1]").append('<br/>格式: 由___迁入___');

	if (jQuery("#shgxsjhm1").val() == "是") {
		jQuery("#zd1").attr("readonly", false);
		jQuery("#zd2").attr("readonly", true);
		jQuery("th[name=zdybdcon_th_zd1]").html(
				'<span class="red">*</span>户口迁入');
		jQuery("th[name=zdybdcon_th_zd2]").html('现户口所在地');
	} else {
		jQuery("#zd1").attr("readonly", true);
		jQuery("#zd2").attr("readonly", false);
		jQuery("th[name=zdybdcon_th_zd1]").html('户口迁入');
		jQuery("th[name=zdybdcon_th_zd2]").html(
				'<span class="red">*</span>现户口所在地');
	}
	jQuery("#shgxsjhm1").change(
			function() {
				if (jQuery(this).val() == "是") {
					jQuery("#zd1").attr("readonly", false);
					jQuery("#zd2").attr("readonly", true);
					jQuery("#zd2").val("");
					jQuery("th[name=zdybdcon_th_zd1]").html(
							'<span class="red">*</span>户口迁入');
					jQuery("th[name=zdybdcon_th_zd2]").html('现户口所在地');
				} else {
					jQuery("#zd1").attr("readonly", true);
					jQuery("#zd2").attr("readonly", false);
					jQuery("#zd1").val("");
					jQuery("th[name=zdybdcon_th_zd1]").html('户口迁入');
					jQuery("th[name=zdybdcon_th_zd2]").html(
							'<span class="red">*</span>现户口所在地');
				}
			})
	if (jQuery("#shgxgzdw1").val() == "其他(填写具体保险名称)") {
		jQuery("#zd3").attr("readonly", false);
		jQuery("th[name=zdybdcon_th_zd3]").html(
				'<span class="red">*</span>医保名称');
	} else {
		jQuery("#zd3").attr("readonly", true);
		jQuery("th[name=zdybdcon_th_zd3]").html('医保名称');
	}
	jQuery("#shgxgzdw1").change(
			function() {
				if (jQuery("#shgxgzdw1").val() == "其他(填写具体保险名称)") {
					jQuery("#zd3").attr("readonly", false);
					jQuery("th[name=zdybdcon_th_zd3]").html(
							'<span class="red">*</span>医保名称');
				} else {
					jQuery("#zd3").attr("readonly", true);
					jQuery("#zd3").val("");
					jQuery("th[name=zdybdcon_th_zd3]").html('医保名称');
				}
			})

}

/* 浙江传媒就业意向1和2选择互斥方法 begin */

var jjyx_1_opt_all; // 就业意向1 所有option
var jjyx_2_opt_all; // 就业一样2 所有option

var i = 0; // 标记页面是否第一次加载

function changeJjyx() {

	// 第一次将option值存起来
	if (i == 0) {
		jjyx_1_opt_all = jQuery("#shgxxm1 option");
		jjyx_2_opt_all = jQuery("#shgxgx1 option");
		i++;
	}

	var jjyx_1 = jQuery("#shgxxm1").val();
	var jjyx_2 = jQuery("#shgxgx1").val();

	// 避免第一次加载默认两个值相同的问题，理论上是不存在这样的问题
	if ("" != jjyx_1 && jjyx_1 == jjyx_2) {
		jjyx_2 = "";
	}

	// 根据就业意向1 加载意向2的下拉值
	jQuery("#shgxgx1 option").remove();
	jjyx_2_opt_all.each(function() {
		if ("" == jQuery(this).val() || jjyx_1 != jQuery(this).val()) {
			jQuery("#shgxgx1").append(this);
		}
	})

	// 根据就业意向2 加载意向1的下拉值
	jQuery("#shgxxm1 option").remove();
	jjyx_1_opt_all.each(function() {
		if ("" == jQuery(this).val() || jjyx_2 != jQuery(this).val()) {
			jQuery("#shgxxm1").append(this);
		}
	})

	jQuery("#shgxxm1").val(jjyx_1);
	jQuery("#shgxgx1").val(jjyx_2);

}

/* 浙江传媒就业意向1和2选择互斥方法 end */

/* 浙江传媒就业意向1和2改为联动begin */
function changeJjyxLd() {

	var jjyx_2 = dataO["shgxgx1"];

	// 删除就业意向2的值
	jQuery("#shgxgx1 option").remove();

	jjyx_2_opt_all = {
		"直接就业" : [ "省级单位", "地市单位", "区县单位" ]
	};
	jjyx_2_opt_all.国内考研 = [ "“985”高校", "“211”高校", "普通高校" ];
	jjyx_2_opt_all.出国出境 = [ "美国", "英国", "法国", "德国", "意大利", "北欧", "加拿大", "澳大利亚",
			"新西兰", "日本", "韩国", "新加坡", "香港、澳门", "其他地区" ];
	jjyx_2_opt_all.考公务员 = [ "国家公务员", "地方公务员" ];
	jjyx_2_opt_all.事业单位 = [ "高等教育单位", "中初教育单位", "其他事业单位" ];
	jjyx_2_opt_all.自主创业 = [ "法人创业", "合伙创业", "个体工商户" ];
	jjyx_2_opt_all.基层就业 = [ "西部计划", "报考村官", "三支一扶", "征兵入伍" ];

	var jjyx_1 = jQuery("#shgxxm1").val();

	if (null == jjyx_1 || "" == jjyx_1) {
		jQuery("#shgxgx1").append("<option value=''>--请先选择意向1--</option>");
	} else {
		jQuery("#shgxgx1").append("<option value=''>--请选择--</option>");
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
/* 浙江传媒就业意向1和2改为联动end */

function showAgeForXakj() {
	var age = calculateAgesForXakj(jQuery("#csrq").val().trim());
	var jgtr = jQuery("[name='zdybdcon_th_jg']").parents("tr").eq(0);
	jgtr
			.before("<tr><th width='15%'>年龄</th><td width='35%'>"
					+ age
					+ "</td><th width='15%'></th><td width='35%' colspan='2'></td></tr>");
}

// 根据出生日期计算年龄
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
	return ("输入的日期格式错误！");
}