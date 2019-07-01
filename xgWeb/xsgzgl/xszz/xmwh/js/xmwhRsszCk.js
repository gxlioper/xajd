var RSKZFW_BJ = "bj";// 班级
var RSKZFW_NJXY = "njxy";// 年级学院
var RSKZFW_NJZY = "njzy";// 年级专业
var RSKZFW_XY = "xy";// 学院
var RSKZFW_ZY = "zy";// 专业
var RSKZFW_SY = "sy";// 书院
var gridSetting;
jQuery(function() {
	var url = "xszz_xmwh_rssz.do?method=xmwhRsszCk&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rskzfw = jQuery("#rskzfw").val();
	url += "&rskzfw=" + rskzfw;
	if (rskzfw == RSKZFW_BJ) {// 班级
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : '学院代码',
				name : 'xydm',
				index : 'xydm',
				key : true,
				hidden : true
			}, {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				hidden : true
			}, {
				label : 'guids',
				name : 'guids',
				index : 'guids',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '20%'
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : '年级',
				name : 'nj',
				index : 'nj',
				width : '8%'
			}, {
				label : '年级s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : '专业代码',
				name : 'zydm',
				index : 'zydm',
				hidden : true
			}, {
				label : '专业',
				name : 'zymc',
				index : 'zymc',
				width : '20%'
			}, {
				label : '专业s',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '班级代码',
				name : 'bjdm',
				index : 'bjdm',
				hidden : true
			}, {
				label : '班级',
				name : 'bjmc',
				index : 'bjmc',
				width : '20%'
			}, {
				label : '班级s',
				name : 'bjdms',
				index : 'bjdms',
				hidden : true
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '8%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '8%'
			} ],
			sortname : "xymc,nj,zymc,bjmc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_NJXY) {// 年级+学院
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : '学院代码',
				name : 'xydm',
				index : 'xydm',
				key : true,
				hidden : true
			}, {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				hidden : true
			}, {
				label : 'guids',
				name : 'guids',
				index : 'guids',
				hidden : true
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '35%'
			}, {
				label : '年级',
				name : 'nj',
				index : 'nj',
				width : '15%'
			}, {
				label : '年级s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_NJZY) {// 年级+专业
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : '学院代码',
				name : 'xydm',
				index : 'xydm',
				key : true,
				hidden : true
			}, {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				hidden : true
			}, {
				label : 'guids',
				name : 'guids',
				index : 'guids',
				hidden : true
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '25%'
			}, {
				label : '年级',
				name : 'nj',
				index : 'nj',
				width : '10%'
			}, {
				label : '年级s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : '专业代码',
				name : 'zydm',
				index : 'zydm',
				hidden : true
			}, {
				label : '专业',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : '专业s',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,nj,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_XY) {// 学院
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : '学院代码',
				name : 'xydm',
				index : 'xydm',
				key : true,
				hidden : true
			}, {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				hidden : true
			}, {
				label : 'guids',
				name : 'guids',
				index : 'guids',
				hidden : true
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '35%'
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_ZY) {// 专业
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : '学院代码',
				name : 'xydm',
				index : 'xydm',
				key : true,
				hidden : true
			}, {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				hidden : true
			}, {
				label : 'guids',
				name : 'guids',
				index : 'guids',
				hidden : true
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '25%'
			}, {
				label : '专业代码',
				name : 'zydm',
				index : 'zydm',
				width : '15%'
			}, {
				label : '专业',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : '专业s',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rskzfw == RSKZFW_SY) {// 书院
        gridSetting = {
            caption : "",
            pager : "pager",
            url : url,
            colList : [ {
                label : '学院代码',
                name : 'xydm',
                index : 'xydm',
                key : true,
                hidden : true
            }, {
                label : 'guid',
                name : 'guid',
                index : 'guid',
                hidden : true
            }, {
                label : 'guids',
                name : 'guids',
                index : 'guids',
                hidden : true
            }, {
                label : '学院代码s',
                name : 'xydms',
                index : 'xydms',
                hidden : true
            }, {
                label : '书院',
                name : 'xymc',
                index : 'xymc',
                width : '35%'
            }, {
                label : '人数',
                name : 'zrs',
                index : 'zrs',
                width : '15%',
                hidden : true
            }, {
                label : '人数',
                name : 'zrss',
                index : 'zrss',
                hidden : true
            }, {
                label : '最终人数',
                name : 'zzrs',
                index : 'zzrs',
                width : '10%'
            } ],
            sortname : "xymc",
            rowNum : 10,
            sortorder : "asc"
        }
    }

	jQuery("#dataTable").initGrid(gridSetting);

	setHelpTip();// 提示显示

});

// 提示显示
function setHelpTip() {
	var tip = "";
	var rskzfw = jQuery("#rskzfw").val();
	if (rskzfw == RSKZFW_BJ) {// 班级
		tip = "班级";
	} else if (rskzfw == RSKZFW_NJXY) {// 年级+学院
		tip = "年级+"+jQuery("#xbmc").val();
	} else if (rskzfw == RSKZFW_NJZY) {// 年级+专业
		tip = "年级+专业";
	} else if (rskzfw == RSKZFW_XY) {// 学院
		tip = jQuery("#xbmc").val();
	} else if (rskzfw == RSKZFW_ZY) {// 专业
		tip = "专业";
	}
	jQuery("#helpTip").html(tip);
}

// 当为学院时，设置人数控制年级
function setRskznj() {
	var rskzfw = jQuery("#rskzfw").val();
	jQuery("#njTipDiv").hide();
	if (rskzfw == RSKZFW_XY) {// 学院
		jQuery("tr[name=njTr]").show();
		jQuery("#njTipDiv").show();

		var rskznj = jQuery("#rskznj").val();
		var njHtml = "";
		var njList = eval(jQuery("#njList").val());
		for ( var i = 0; i < njList.length; i++) {
			if (i != 0 && i % 4 == 0) {
				njHtml += "<br/>";
			}
			njHtml += "<label>";
			if (rskznj == "" || rskznj.indexOf(njList[i]) > -1) {
				njHtml += "<input type='checkbox' name='nj' value='"
						+ njList[i] + "' checked='checked'>" + njList[i]
						+ "&nbsp;";
			} else {
				njHtml += "<input type='checkbox' name='nj' value='"
						+ njList[i] + "'>" + njList[i] + "&nbsp;";
			}
			njHtml += "</label>";
		}
		njHtml += "<br/><font color='red'><span name='njTip'></span></font>";
		jQuery("td[name=njTd]").html(njHtml);
	}
}

function query() {
	var map = {};
	map["xmmc"] = jQuery("#xmmc").val();
	map["xmdm"] = jQuery("#xmdm").val();

	map["njq"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["sfysz"] = jQuery("#sfysz").val();
	map["zydm"] = jQuery("#zy").val();
	map["bjdm"] = jQuery("#bj").val();
	jQuery("#dataTable").reloadGrid(map);
}


