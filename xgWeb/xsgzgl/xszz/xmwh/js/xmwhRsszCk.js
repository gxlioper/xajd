var RSKZFW_BJ = "bj";// �༶
var RSKZFW_NJXY = "njxy";// �꼶ѧԺ
var RSKZFW_NJZY = "njzy";// �꼶רҵ
var RSKZFW_XY = "xy";// ѧԺ
var RSKZFW_ZY = "zy";// רҵ
var RSKZFW_SY = "sy";// ��Ժ
var gridSetting;
jQuery(function() {
	var url = "xszz_xmwh_rssz.do?method=xmwhRsszCk&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rskzfw = jQuery("#rskzfw").val();
	url += "&rskzfw=" + rskzfw;
	if (rskzfw == RSKZFW_BJ) {// �༶
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : 'ѧԺ����',
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
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '20%'
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : '�꼶',
				name : 'nj',
				index : 'nj',
				width : '8%'
			}, {
				label : '�꼶s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : 'רҵ����',
				name : 'zydm',
				index : 'zydm',
				hidden : true
			}, {
				label : 'רҵ',
				name : 'zymc',
				index : 'zymc',
				width : '20%'
			}, {
				label : 'רҵs',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '�༶����',
				name : 'bjdm',
				index : 'bjdm',
				hidden : true
			}, {
				label : '�༶',
				name : 'bjmc',
				index : 'bjmc',
				width : '20%'
			}, {
				label : '�༶s',
				name : 'bjdms',
				index : 'bjdms',
				hidden : true
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '8%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '8%'
			} ],
			sortname : "xymc,nj,zymc,bjmc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_NJXY) {// �꼶+ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : 'ѧԺ����',
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
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '35%'
			}, {
				label : '�꼶',
				name : 'nj',
				index : 'nj',
				width : '15%'
			}, {
				label : '�꼶s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_NJZY) {// �꼶+רҵ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : 'ѧԺ����',
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
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '25%'
			}, {
				label : '�꼶',
				name : 'nj',
				index : 'nj',
				width : '10%'
			}, {
				label : '�꼶s',
				name : 'njs',
				index : 'njs',
				hidden : true
			}, {
				label : 'רҵ����',
				name : 'zydm',
				index : 'zydm',
				hidden : true
			}, {
				label : 'רҵ',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : 'רҵs',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,nj,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_XY) {// ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : 'ѧԺ����',
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
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '35%'
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rskzfw == RSKZFW_ZY) {// רҵ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {
				label : 'ѧԺ����',
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
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true
			}, {
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '25%'
			}, {
				label : 'רҵ����',
				name : 'zydm',
				index : 'zydm',
				width : '15%'
			}, {
				label : 'רҵ',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : 'רҵs',
				name : 'zydms',
				index : 'zydms',
				hidden : true
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%'
			} ],
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rskzfw == RSKZFW_SY) {// ��Ժ
        gridSetting = {
            caption : "",
            pager : "pager",
            url : url,
            colList : [ {
                label : 'ѧԺ����',
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
                label : 'ѧԺ����s',
                name : 'xydms',
                index : 'xydms',
                hidden : true
            }, {
                label : '��Ժ',
                name : 'xymc',
                index : 'xymc',
                width : '35%'
            }, {
                label : '����',
                name : 'zrs',
                index : 'zrs',
                width : '15%',
                hidden : true
            }, {
                label : '����',
                name : 'zrss',
                index : 'zrss',
                hidden : true
            }, {
                label : '��������',
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

	setHelpTip();// ��ʾ��ʾ

});

// ��ʾ��ʾ
function setHelpTip() {
	var tip = "";
	var rskzfw = jQuery("#rskzfw").val();
	if (rskzfw == RSKZFW_BJ) {// �༶
		tip = "�༶";
	} else if (rskzfw == RSKZFW_NJXY) {// �꼶+ѧԺ
		tip = "�꼶+"+jQuery("#xbmc").val();
	} else if (rskzfw == RSKZFW_NJZY) {// �꼶+רҵ
		tip = "�꼶+רҵ";
	} else if (rskzfw == RSKZFW_XY) {// ѧԺ
		tip = jQuery("#xbmc").val();
	} else if (rskzfw == RSKZFW_ZY) {// רҵ
		tip = "רҵ";
	}
	jQuery("#helpTip").html(tip);
}

// ��ΪѧԺʱ���������������꼶
function setRskznj() {
	var rskzfw = jQuery("#rskzfw").val();
	jQuery("#njTipDiv").hide();
	if (rskzfw == RSKZFW_XY) {// ѧԺ
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


