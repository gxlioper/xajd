var RSFPFS_BJ = "bj";// �༶
var RSFPFS_NJXY = "njxy";// �꼶ѧԺ
var RSFPFS_NJZY = "njzy";// �꼶רҵ
var RSFPFS_XY = "xy";// ѧԺ
var RSFPFS_ZY = "zy";// רҵ
var RSFPFS_XX = "xx";// ѧУ
var RSFPFS_CPZ = "cpz";// ������
var gridSetting;
jQuery(function() {
	var url = "xpj_xmwh_rssz.do?method=xmwhRsszCk&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	if (rsfpfs == RSFPFS_BJ) {// �༶
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label :jQuery("#xbmc").val()+'����',name : 'xymc',index : 'xymc',width : '20%'}, 
						{label : '�꼶',name : 'nj',index : 'nj',width : '8%'}, 
						{label : 'רҵ����',name : 'zydm',index : 'zydm',hidden : true}, 
						{label : 'רҵ',name : 'zymc',index : 'zymc',width : '20%'}, 
						{label : '�༶����',name : 'bjdm',index : 'bjdm',hidden : true}, 
						{label : '�༶',name : 'bjmc',index : 'bjmc',width : '20%'}, 
						{label : '��������',name : 'zzme',index : 'to_number(zzme)',width : '8%'} 
					  ],
			multiselect:false,
			sortname : "xymc,nj,zymc,bjmc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_NJXY) {// �꼶+ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : jQuery("#xbmc").val()+'����',name : 'xymc',index : 'xymc',width : '35%'}, 
			            {label : '�꼶', name : 'nj', index : 'nj', width : '15%'},
			            {label : '��������', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_NJZY) {// �꼶+רҵ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����', name : 'xydm', index : 'xydm', key : true, hidden : true},
			            {label : 'guid', name : 'guid', index : 'guid', hidden : true},
			            {label : jQuery("#xbmc").val()+'����', name : 'xymc', index : 'xymc', width : '25%'},
			            {label : '�꼶', name : 'nj', index : 'nj', width : '10%'},
			            {label : 'רҵ����', name : 'zydm', index : 'zydm', hidden : true},
			            {label : 'רҵ', name : 'zymc', index : 'zymc', width : '25%'},
			            {label : '��������', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,nj,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_XY) {// ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����', name : 'xydm', index : 'xydm', key : true, hidden : true}, 
			            {label : 'guid', name : 'guid', index : 'guid',hidden : true}, 
			            {label : jQuery("#xbmc").val()+'����', name : 'xymc', index : 'xymc', width : '35%'},
			            {label : '��������', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_ZY) {// רҵ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����', name : 'xydm', index : 'xydm', key : true, hidden : true}, 
			            {label : 'guid', name : 'guid', index : 'guid', hidden : true}, 
			            {label : jQuery("#xbmc").val()+'����', name : 'xymc', index : 'xymc', width : '25%'}, 
			            {label : 'רҵ����', name : 'zydm', index : 'zydm', hidden : true}, 
			            {label : 'רҵ', name : 'zymc', index : 'zymc', width : '25%'},
			            {label : '��������', name : 'zzme', index : 'to_number(zzme)', width : '10%'} 
			          ],
			multiselect:false,
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rsfpfs == RSFPFS_CPZ) {// ������
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '���������',name : 'cpzdm',index : 'cpzdm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : '����������',name : 'cpzmc',index : 'cpzmc',width : '40%'},
			            {label : '��������',name : 'zzme',index : 'to_number(zzme)',width : '15%'} 
			          ],
			multiselect:false,
			sortname : "cpzdm",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_XX) {// ѧУ
		gridSetting = {
			caption : "",
			pager : "none",
			url : url,
			colList : [ {label : 'ѧУ���䷽ʽ',name : 'xx',index : 'xx',width : '40%',formatter : setXxfs},
						{label : '��������',name : 'zzme',index : 'zzme',width : '30%'} 
					  ],
			multiselect:false,
			rowNum : 10,
			sortorder : "asc"
		}
	} else{// 
		alert("������[��Ŀ����]������[�������䷽ʽ]��");
	}

	jQuery("#dataTable").initGrid(gridSetting);

	setHelpTip();// ��ʾ��ʾ
	
});



// ��ʾ��ʾ
function setHelpTip() {
	var tip = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_BJ) {// �༶
		tip = "�༶";
	} else if (rsfpfs == RSFPFS_NJXY) {// �꼶+ѧԺ
		tip = "�꼶+"+jQuery("#xbmc").val();
	} else if (rsfpfs == RSFPFS_NJZY) {// �꼶+רҵ
		tip = "�꼶+רҵ";
	} else if (rsfpfs == RSFPFS_XY) {// ѧԺ
		tip = jQuery("#xbmc").val();
	} else if (rsfpfs == RSFPFS_ZY) {// רҵ
		tip = "רҵ";
	}else if (rsfpfs == RSFPFS_CPZ) {// ������
		tip = "������";
	}else if (rsfpfs == RSFPFS_XX) {// ѧУ
		tip = "ѧУ";
	}
	jQuery("#helpTip").html(tip);
}



/* ����guids���������� */
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

/* ���� */
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
		showAlert("û����Ҫ����ļ�¼��");
		return false;
	}

	jQuery("input[name=zzmes]").each(
			function(index) {
				var value = jQuery.trim(jQuery(this).val());
				if (value != "" && chkNumInput2(value)) {
					showAlert("��" + (index + 1) + "�м�¼����������[" + value
							+ "]��ʽ����������Ϊ���֣�");
					flag = true;
					return false;
				}
				var zrs = jQuery(this).parent().parent().find("[name=zrss]")
						.val();
				if (parseInt(value, 10) > parseInt(zrs, 10)) {
					showAlert("��" + (index + 1) + "�м�¼����������[" + value
							+ "]���ܴ���������[" + zrs + "]��");
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

/* ����ѧУ���䷽ʽ */
function setXxfs(cellValue, rowObject) {
	return "ѧУ���䷽ʽ";
}

