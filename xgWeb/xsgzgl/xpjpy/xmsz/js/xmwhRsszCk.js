var RSFPFS_BJ = "bj";// 班级
var RSFPFS_NJXY = "njxy";// 年级学院
var RSFPFS_NJZY = "njzy";// 年级专业
var RSFPFS_XY = "xy";// 学院
var RSFPFS_ZY = "zy";// 专业
var RSFPFS_XX = "xx";// 学校
var RSFPFS_CPZ = "cpz";// 参评组
var gridSetting;
jQuery(function() {
	var url = "xpj_xmwh_rssz.do?method=xmwhRsszCk&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	if (rsfpfs == RSFPFS_BJ) {// 班级
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label :jQuery("#xbmc").val()+'名称',name : 'xymc',index : 'xymc',width : '20%'}, 
						{label : '年级',name : 'nj',index : 'nj',width : '8%'}, 
						{label : '专业代码',name : 'zydm',index : 'zydm',hidden : true}, 
						{label : '专业',name : 'zymc',index : 'zymc',width : '20%'}, 
						{label : '班级代码',name : 'bjdm',index : 'bjdm',hidden : true}, 
						{label : '班级',name : 'bjmc',index : 'bjmc',width : '20%'}, 
						{label : '限制人数',name : 'zzme',index : 'to_number(zzme)',width : '8%'} 
					  ],
			multiselect:false,
			sortname : "xymc,nj,zymc,bjmc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_NJXY) {// 年级+学院
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : jQuery("#xbmc").val()+'名称',name : 'xymc',index : 'xymc',width : '35%'}, 
			            {label : '年级', name : 'nj', index : 'nj', width : '15%'},
			            {label : '限制人数', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_NJZY) {// 年级+专业
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码', name : 'xydm', index : 'xydm', key : true, hidden : true},
			            {label : 'guid', name : 'guid', index : 'guid', hidden : true},
			            {label : jQuery("#xbmc").val()+'名称', name : 'xymc', index : 'xymc', width : '25%'},
			            {label : '年级', name : 'nj', index : 'nj', width : '10%'},
			            {label : '专业代码', name : 'zydm', index : 'zydm', hidden : true},
			            {label : '专业', name : 'zymc', index : 'zymc', width : '25%'},
			            {label : '限制人数', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,nj,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_XY) {// 学院
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码', name : 'xydm', index : 'xydm', key : true, hidden : true}, 
			            {label : 'guid', name : 'guid', index : 'guid',hidden : true}, 
			            {label : jQuery("#xbmc").val()+'名称', name : 'xymc', index : 'xymc', width : '35%'},
			            {label : '限制人数', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_ZY) {// 专业
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码', name : 'xydm', index : 'xydm', key : true, hidden : true}, 
			            {label : 'guid', name : 'guid', index : 'guid', hidden : true}, 
			            {label : jQuery("#xbmc").val()+'名称', name : 'xymc', index : 'xymc', width : '25%'}, 
			            {label : '专业代码', name : 'zydm', index : 'zydm', hidden : true}, 
			            {label : '专业', name : 'zymc', index : 'zymc', width : '25%'},
			            {label : '限制人数', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rsfpfs == RSFPFS_CPZ) {// 参评组
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '参评组代码',name : 'cpzdm',index : 'cpzdm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : '参评组名称',name : 'cpzmc',index : 'cpzmc',width : '40%'},
			            {label : '最终人数',name : 'zzme',index : 'to_number(zzme)',width : '15%'} 
			          ],
			multiselect:false,
			sortname : "cpzdm",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_XX) {// 学校
		gridSetting = {
			caption : "",
			pager : "none",
			url : url,
			colList : [ {label : '学校分配方式',name : 'xx',index : 'xx',width : '40%',formatter : setXxfs},
						{label : '最终人数',name : 'zzme',index : 'zzme',width : '30%'} 
					  ],
			multiselect:false,
			rowNum : 10,
			sortorder : "asc"
		}
	} else{// 
		alert("请先在[项目设置]中配置[人数分配方式]！");
	}

	jQuery("#dataTable").initGrid(gridSetting);

	setHelpTip();// 提示显示
	
});



// 提示显示
function setHelpTip() {
	var tip = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_BJ) {// 班级
		tip = "班级";
	} else if (rsfpfs == RSFPFS_NJXY) {// 年级+学院
		tip = "年级+"+jQuery("#xbmc").val();
	} else if (rsfpfs == RSFPFS_NJZY) {// 年级+专业
		tip = "年级+专业";
	} else if (rsfpfs == RSFPFS_XY) {// 学院
		tip = jQuery("#xbmc").val();
	} else if (rsfpfs == RSFPFS_ZY) {// 专业
		tip = "专业";
	}else if (rsfpfs == RSFPFS_CPZ) {// 参评组
		tip = "参评组";
	}else if (rsfpfs == RSFPFS_XX) {// 学校
		tip = "学校";
	}
	jQuery("#helpTip").html(tip);
}



/* 设置guids，保存数据 */
function setGuids(cellValue, rowObject) {
	var guid = rowObject.guid;
	if (guid == null) {
		guid = "";
	}
	cellValue = "<input type='hidden' name='guids' value='" + guid + "' >";
	return cellValue;
}

function query() {
	var map = {};
	map["xmmc"] = jQuery("#xmmc").val();
	map["xmdm"] = jQuery("#xmdm").val();
	
	map["njq"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["zydm"] = jQuery("#zy").val();
	map["bjdm"] = jQuery("#bj").val();
	map["sfysz"] = jQuery("#sfysz").val();
	jQuery("#dataTable").reloadGrid(map);
}

/* 保存 */
function update() {
	var blsFlag = false;
	jQuery("input[name=fpbls]").each(function(index) {
		if (jQuery(this).val() == null || jQuery(this).val() == "") {
			blsFlag = true;
			return false;
		}
	});

	var flag = false;
	if ((jQuery("input[name=zzmes]").length == 0)) {
		showAlert("没有需要保存的记录！");
		return false;
	}

	jQuery("input[name=zzmes]").each(
			function(index) {
				var value = jQuery.trim(jQuery(this).val());
				if (value != "" && chkNumInput2(value)) {
					showAlert("第" + (index + 1) + "行记录，最终人数[" + value
							+ "]格式不符，必须为数字！");
					flag = true;
					return false;
				}
				var zrs = jQuery(this).parent().parent().find("[name=zrss]")
						.val();
				if (parseInt(value, 10) > parseInt(zrs, 10)) {
					showAlert("第" + (index + 1) + "行记录，最终人数[" + value
							+ "]不能大于总人数[" + zrs + "]！");
					flag = true;
					return false;
				}
			});

	if (flag) {
		return false;
	}

	var url = "xpj_xmwh_rssz.do?method=xmwhRsszXg&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data.message, {}, {
			"clkFun" : function(tag) {
				query();
				divLayerClose();
			}
		});
	});
}

/* 设置学校分配方式 */
function setXxfs(cellValue, rowObject) {
	return "学校分配方式";
}

