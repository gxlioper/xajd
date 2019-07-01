var njxyzyList;
var sjfwdm;
var sjfwmc;
var ids;
var CHAR1 = ";";//两组间的分割符
var CHAR2 = ":";//年级后的符号
var CHAR3 = ",";//学院间的符号
var CHAR4 = "-";//学院与专业间的符号
var CHAR5 = "|";//专业间的符号

var sjfwdmSubmit = "";
var sjfwmcSubmit = "";

jQuery(function() {
	onShow();
});

function onShow() {
	var ids = jQuery("#ids").val();
	var url = "xtwh_yhsjfw.do?method=yhsjfwSq&type=query";
	jQuery.post(url, {
		ids : ids
	}, function(data) {
		njxyzyList = data.njxyzyList;
		ids = data.ids;
		sjfwdm = jQuery.trim(data.sjfwdm);
		sjfwmc = jQuery.trim(data.sjfwmc);
		setInit();// 设置初值
			bindEvent();// 绑定事件

			setDefault();// 默认显示
			jQuery("div.main_function").show();
		}, 'json');
}

function setDefault() {
	if (sjfwdm != null) {
		setDefaultValue();
	}

	jQuery(".function_list01 ul>li :checkbox").attr("disabled", "disabled");
	jQuery(".function_list01 ul>li :checkbox:first").removeAttr("disabled");

	jQuery(".function_list01 ul li").removeClass("current");
	jQuery(".function_list01 ul>li:first").addClass("current");

	jQuery(".function_list02 table:first").show();

}

function setDefaultValue() {
	var njxyzysArr = sjfwdm.split(CHAR1);
	if (njxyzysArr == null || njxyzysArr.length == 0) {
		return;
	}
	for ( var i = 0; i < njxyzysArr.length; i++) {
		var njxyzys = jQuery.trim(njxyzysArr[i]);
		if (njxyzys == null) {
			continue;
		}
		var njxyzyArr = njxyzys.split(CHAR2);
		if (njxyzyArr == null || njxyzyArr.length == 0) {
			return;
		}
		var nj = "";
		var xyzyStr = "";
		if (njxyzyArr.length > 0) {
			nj = jQuery.trim(njxyzyArr[0]);
		}
		if (njxyzyArr.length > 1) {
			xyzyStr = jQuery.trim(njxyzyArr[1]);
		}
		setNjxyzyOne(nj, xyzyStr);
	}
}

function setNjxyzyOne(nj, xyzyStr) {
	jQuery(":checkbox[name='njCheckbox'][value='" + nj + "']").attr("checked",
			"checked");
	if (xyzyStr == "") {
		jQuery("#njTable_" + nj + " :checkbox").attr("checked", "checked");
	} else {
		var xyzyArr = xyzyStr.split(CHAR3);
		for ( var i = 0; i < xyzyArr.length; i++) {
			var xyzy = xyzyArr[i];
			if(xyzy == null){
				continue;
			}
			var xyzyOneArr = xyzy.split(CHAR4);
			if(xyzyOneArr == null || xyzyOneArr.length == 0){
				continue;
			}
			var xydm = xyzyOneArr[0];
			
			jQuery(
			"#njTable_" + nj + " :checkbox[name='xyCheckbox'][value='"
					+ xydm + "']").attr("checked", "checked");
			
			var zys = "";
			if(xyzyOneArr.length > 1){
				zys = xyzyOneArr[1];
			}
			if(zys == ""){
				jQuery("td[id^='nj_"+nj+"_xydm_"+xydm+"_zydm_'] :checkbox[name='zyCheckbox']").attr("checked",
				"checked");
			}else{
				var zyArr = zys.split(CHAR5);
				for ( var j = 0; j < zyArr.length; j++) {
					var zydm = zyArr[j];
					jQuery("td[id^='nj_"+nj+"_xydm_"+xydm+"_zydm_'] :checkbox[name='zyCheckbox'][value='"+zydm+"']").attr("checked",
					"checked");
				}
			}			
		}
	}
}

function setInit() {
	if (njxyzyList == null || njxyzyList.length == 0) {
		return;
	}
	for ( var i = 0; i < njxyzyList.length; i++) {
		var o = njxyzyList[i];
		setInitNj(o);
		jQuery(".function_list02 table").hide();
		setInitXyzy(o);
	}
}

function setInitNj(obj) {
	var nj = obj.nj;
	if (jQuery("#njLi_" + nj).length == 0) {
		var njHtml = "<li id='njLi_" + nj
				+ "'><input type='checkbox' name='njCheckbox' value='" + nj
				+ "');'><a href='javascript:;' onclick='njSet(\"" + nj
				+ "\");'>&nbsp;&nbsp;" + nj + "</a></li>";
		jQuery(".function_list01 ul").append(njHtml);
	}
	if (jQuery("#njTable_" + nj).length == 0) {
		var njTableHtml = "<table width='100%' border='0' id='njTable_" + nj
				+ "'>";
		njTableHtml += "<tbody></tbody>";
		njTableHtml += "</table>";
		jQuery(".function_list02").append(njTableHtml);
	}
}

function setInitXyzy(obj) {
	var nj = obj.nj;
	var xydm = obj.xydm;
	var xymc = obj.xymc;
	var zydm = obj.zydm;
	var zymc = obj.zymc;
	var xyId = "nj_" + nj + "_xydm_" + xydm;
	var zyId = xyId + "_zydm_" + zydm;
	var zyHtml = "<td class='list_03' id='" + zyId + "'>";
	zyHtml += "<label>";
	zyHtml += "<input type='checkbox' name='zyCheckbox'  value='" + zydm + "'>";
	zyHtml += zymc;
	zyHtml += "</label>";
	zyHtml += "</td>";
	if (jQuery("#" + xyId).length == 0) {
		var sHtml = "<tr id='" + xyId + "'>";
		sHtml += "<td name='xydmTd' class='list_02'>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' name='xyCheckbox' value='" + xydm
				+ "'>";
		sHtml += xymc;
		sHtml += "</label>";
		sHtml += "</td>";
		sHtml += zyHtml;
		sHtml += "</tr>";
		jQuery("#njTable_" + nj + " tbody").append(sHtml);
	} else {
		var _xyTd = jQuery("#" + xyId + " td[name='xydmTd']");
		var rowspan = _xyTd.attr("rowspan");
		if (rowspan == null) {
			rowspan = 1;
		}
		rowspan = parseInt(rowspan) + 1;
		_xyTd.attr("rowspan", rowspan);
		zyHtml = "<tr>" + zyHtml + "</tr>";
		jQuery("#njTable_" + nj + " tbody").append(zyHtml);
	}
}

function njSet(nj) {
	jQuery(".function_list01 ul li").removeClass("current");
	jQuery("#njLi_" + nj).addClass("current");

	jQuery(".function_list01 ul>li :checkbox").attr("disabled", "disabled");
	jQuery("#njLi_" + nj + " :checkbox").removeAttr("disabled");

	jQuery(".function_list02 table").hide();
	jQuery("#njTable_" + nj).show();
}

function bindEvent() {
	// 年级复选框操作
	jQuery(":checkbox[name='njCheckbox']").click(function() {
		var nj = jQuery(this).val();
		var njChecked = jQuery(this).prop("checked");
		jQuery("#njTable_" + nj + " :checkbox").attr("checked", njChecked);
	});

	// 学院复选框操作
	jQuery(":checkbox[name='xyCheckbox']")
			.click(
					function() {
						var xydm = jQuery(this).val();
						var njTable = jQuery(this).parents(
								"table[id^='njTable_']").attr("id");
						var nj = njTable.split("_")[1];
						var xyChecked = jQuery(this).prop("checked");
						jQuery(
								"#"+njTable+" td[id^='nj_" + nj + "_xydm_" + xydm
										+ "'] :checkbox").attr("checked",
								xyChecked);

						if (xyChecked) {
							jQuery(
									":checkbox[name='njCheckbox'][value='" + nj
											+ "']").attr("checked", xyChecked);
						} else {
							var checkedLen = jQuery("tr[id^='nj_"
									+ nj
									+ "'] :checkbox[name='xyCheckbox'][checked='true']").length;
							if (checkedLen == 0) {
								jQuery(
										":checkbox[name='njCheckbox'][value='"
												+ nj + "']").attr("checked",
										xyChecked);
							}
						}
					});

	// 专业复选框操作
	jQuery(":checkbox[name='zyCheckbox']")
			.click(
					function() {
						var zydm = jQuery(this).val();
						var njTable = jQuery(this).parents(
								"table[id^='njTable_']").attr("id");
						var nj = njTable.split("_")[1];
						var zyTd = jQuery(this).parents("td.list_03").attr("id");
						var xydm = zyTd.split("_xydm_")[1].split("_zydm_")[0];
						var zyChecked = jQuery(this).prop("checked");
						if (zyChecked) {
							jQuery(
									":checkbox[name='njCheckbox'][value='" + nj
											+ "']").attr("checked", zyChecked);
							jQuery(
									"#"+njTable+" :checkbox[name='xyCheckbox'][value='"
											+ xydm + "']").attr("checked",
									zyChecked);
						} else {
							var xyCheckedLen = jQuery("td[id^='nj_"
									+ nj
									+ "_xydm_"
									+ xydm
									+ "'] :checkbox[name='zyCheckbox'][checked='true']").length;
							if (xyCheckedLen == 0) {
								jQuery(
										"tr[id^='nj_"
												+ nj
												+ "'] :checkbox[name='xyCheckbox'][value='"
												+ xydm + "']").attr("checked",
										zyChecked);

								var checkedLen = jQuery("tr[id^='nj_"
										+ nj
										+ "'] :checkbox[name='xyCheckbox'][checked='true']").length;
								if (checkedLen == 0) {
									jQuery(
											":checkbox[name='njCheckbox'][value='"
													+ nj + "']").attr(
											"checked", zyChecked);
								}
							}
						}
					});
}

function saveForm() {
	sjfwdmSubmit = "";
	sjfwmcSubmit = "";
	jQuery(":checkbox[name='njCheckbox']:checked").each(function(index){
		var nj = jQuery(this).val();
		setNjxySubmit(nj);
	});
	jQuery("#sjfwdmSubmit").val(sjfwdmSubmit);
	jQuery("#sjfwmcSubmit").val(sjfwmcSubmit);
	var url = "xtwh_yhsjfw.do?method=yhsjfwSq&type=save";
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


function setNjxySubmit(nj){
	if(sjfwdmSubmit != ""){
		sjfwdmSubmit += CHAR1;
		sjfwmcSubmit += CHAR1;
	}
	sjfwdmSubmit += nj;
	sjfwmcSubmit += nj;

	var xyLen = jQuery("#njTable_" + nj + " :checkbox[name='xyCheckbox']").length;
	var _xyCheck = jQuery("#njTable_" + nj + " :checkbox[name='xyCheckbox']:checked");
	var xyCheckLen = _xyCheck.length;
	var xyFlag =false;
	if(xyLen != xyCheckLen){//未全部选择
		_xyCheck.each(function(xyIndex){
			var xydm = jQuery(this).val();
			var xymc = jQuery(this).parent().text();
			if(xyFlag){
				sjfwdmSubmit += CHAR3;
				sjfwmcSubmit += CHAR3;
			}else{
				xyFlag = true;
				sjfwdmSubmit += CHAR2;
				sjfwmcSubmit += CHAR2;
			}
			sjfwdmSubmit += xydm ;
			sjfwmcSubmit += xymc ;			
			setZySubmit(nj,xydm);
		});
	}
}

function setZySubmit(nj,xydm){
	var zyLen = jQuery("#njTable_"+nj+" td[id^='nj_"+nj+"_xydm_"+xydm+"_zydm_'] :checkbox[name='zyCheckbox']").length;
	var _zyCheck = jQuery("#njTable_"+nj+" td[id^='nj_"+nj+"_xydm_"+xydm+"_zydm_'] :checkbox[name='zyCheckbox']:checked");
	var zyCheckLen = _zyCheck.length;
	var zyFlag =false;
	if(zyLen != zyCheckLen){//未全部选择
		_zyCheck.each(function(zyIndex){
			var zydm = jQuery(this).val();
			var zymc = jQuery(this).parent().text();
			if(zyFlag){
				sjfwdmSubmit += CHAR5;
				sjfwmcSubmit += CHAR5;
			}else{
				zyFlag = true;
				sjfwdmSubmit += CHAR4;
				sjfwmcSubmit += CHAR4;
			}
			sjfwdmSubmit += zydm ;
			sjfwmcSubmit += zymc ;
		});
	}
}