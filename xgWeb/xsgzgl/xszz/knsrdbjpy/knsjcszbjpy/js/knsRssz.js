var RSKZFW_BJ = "bj";// �༶
var RSKZFW_NJXY = "njxy";// �꼶ѧԺ
var RSKZFW_NJZY = "njzy";// �꼶רҵ
var RSKZFW_XY = "xy";// ѧԺ
var RSKZFW_ZY = "zy";// רҵ
var gridSetting;
jQuery(function() {
	var url = "xszz_knsjcszbjpy.do?method=knsRssz&type=query";
	var rskzfw = jQuery("#rskzfw").val();
	var rskznj = jQuery("#rskznj").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	url+="&rskzfw="+rskzfw;
	url+="&rskznj="+rskznj;
	url+="&xn="+xn;
	url+="&xq="+xq;
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
				hidden : true,
				formatter : setGuids
			}, {
				label : jQuery("#xbmc").val()+'����',
				name : 'xymc',
				index : 'xymc',
				width : '20%'
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
			}, {
				label : '�꼶',
				name : 'nj',
				index : 'nj',
				width : '8%'
			}, {
				label : '�꼶s',
				name : 'njs',
				index : 'njs',
				hidden : true,
				formatter : setNjs
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
				hidden : true,
				formatter : setZydms
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
				hidden : true,
				formatter : setBjdms
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '8%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '����(%)',
				name : 'bl',
				index : 'bl',
				width : '8%',
				formatter : setBl
			}, {
				label : '����(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '��������',
				name : 'jsrs',
				index : 'jsrs',
				width : '8%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '8%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setNjs
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '����(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '����(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '��������',
				name : 'jsrs',
				index : 'jsrs',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setNjs
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
				hidden : true,
				formatter : setZydms
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '����(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '����(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '��������',
				name : 'jsrs',
				index : 'jsrs',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setZrss
			}, {
				label : '����(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '����(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '��������',
				name : 'jsrs',
				index : 'jsrs',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : 'ѧԺ����s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				//hidden : true
			}, {
				label : 'רҵ',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : 'רҵs',
				name : 'zydms',
				index : 'zydms',
				hidden : true,
				formatter : setZydms
			}, {
				label : '����',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '����',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '����(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '����(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '��������',
				name : 'jsrs',
				index : 'jsrs',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '��������',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
			} ],
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}

	jQuery("#dataTable").initGrid(gridSetting);
	setHelpTip();
	fpmeTip();
	
});

/* ����guids���������� */
function setGuids(cellValue, rowObject) {
	var guid = rowObject.guid;
	if (guid == null) {
		guid = "";
	}
	cellValue = "<input type='hidden' name='guids' value='" + guid + "' >";
	return cellValue;
}

/* ����bls���������� */
function setBls(cellValue, rowObject) {
	var bl = rowObject.bl;
	if (bl == null) {
		bl = "";
	}
	cellValue = "<input type='hidden' name='bls' value='" + bl + "' >";
	return cellValue;
}

/* ����bl��Ϊ�˶�λ���¸�ֵ */
function setBl(cellValue, rowObject) {
	var bl = rowObject.bl;
	if (bl == null) {
		bl = "";
	}
	cellValue = "<input type='hidden' name='blHid' value='" + bl + "'>" + bl;
	return cellValue;
}

/* �����꼶���������� */
function setNjs(cellValue, rowObject) {
	var nj = rowObject.nj;
	if (nj == null) {
		nj = "";
	}
	cellValue = "<input type='hidden' name='njs' value='" + nj + "' >";
	return cellValue;
}

/* ����ѧԺ���룬�������� */
function setXydms(cellValue, rowObject) {
	var xydm = rowObject.xydm;
	if (xydm == null) {
		xydm = "";
	}
	cellValue = "<input type='hidden' name='xydms' value='" + xydm + "' >";
	return cellValue;
}

/* ����רҵ���룬�������� */
function setZydms(cellValue, rowObject) {
	var zydm = rowObject.zydm;
	if (zydm == null) {
		zydm = "";
	}
	cellValue = "<input type='hidden' name='zydms' value='" + zydm + "' >";
	return cellValue;
}

/* ���ð༶���룬�������� */
function setBjdms(cellValue, rowObject) {
	var bjdm = rowObject.bjdm;
	if (bjdm == null) {
		bjdm = "";
	}
	cellValue = "<input type='hidden' name='bjdms' value='" + bjdm + "' >";
	return cellValue;
}

/* �������������������� */
function setZrss(cellValue, rowObject) {
	var zrs = rowObject.zrs;
	if (zrs == null) {
		zrs = "";
	}
	cellValue = "<input type='hidden' name='zrss' value='" + zrs + "' >";
	return cellValue;
}

/* ���ü������� */
function setJsrs(cellValue, rowObject) {
	var bl = rowObject.bl;
	var zrs = rowObject.zrs;
	cellValue = setJsrsJs(cellValue, bl, zrs);
	return cellValue;
}

/* ���ü�������dd */
function setJsrsJs(cellValue, bl, zrs) {
	if (bl != null && bl != "" && zrs != null && zrs != "") {
		cellValue = parseInt(zrs, 10) * parseInt(bl * 100) / 10000;
	}
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='hidden' name='jsrsHid' value='" + cellValue
			+ "'>" + cellValue;
	return cellValue;
}

/* ������������ */
function setZzrs(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' style='width:60px' name='zzrss' maxlength='10' value='"
			+ cellValue + "' />";
	return cellValue;
}

/* �������� */
function blsz() {
	if ((jQuery("input[name=zzrss]").length == 0)) {
		showAlert("û����Ҫ���õļ�¼��");
		return false;
	}
	setRskznj();//��ΪѧԺʱ���������������꼶
	var title = "��������";
	var content = jQuery("#blDiv").html();
	var rskzfw = jQuery("#rskzfw").val();
	if (rskzfw == RSKZFW_XY) {// ѧԺ
		tipsWindown(title, content, 400, 320);
	}else{
		tipsWindown(title, content, 400, 160);
	}
}

//��ΪѧԺʱ���������������꼶
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
			if(i != 0 && i %4== 0){
				njHtml += "<br/>";
			}
			njHtml += "<label>";
			if(rskznj == "" || rskznj.indexOf(njList[i]) > -1){
				njHtml += "<input type='checkbox' name='nj' value='"+njList[i]+"' checked='checked'>"+njList[i]+"&nbsp;";
			}else{
				njHtml += "<input type='checkbox' name='nj' value='"+njList[i]+"'>"+njList[i]+"&nbsp;";
			}
			njHtml += "</label>";
		}
		njHtml += "<br/><font color='red'><span name='njTip'></span></font>";
		jQuery("td[name=njTd]").html(njHtml);
	}
}

/* �������ñ��� */
function saveForm() {
	var fpfs = jQuery.trim(jQuery("input:radio[name=fpfs]:checked")[1].value);
	if (fpfs == "bl") {// ������ʽ
		var blView = jQuery.trim(jQuery("input[name=blView]")[1].value);
		if (blView == "") {
			jQuery("span[name=blTip]").html("���������");
			return false;
		}
		var reg = /^(([1-9]\d{0,2})|[0-9])([.]\d{1,2})?$/;
		if (!blView.match(reg) || blView > 100) {
			jQuery("span[name=blTip]").html("������0-100�����֣������λС��");
			return false;
		}
	} else if (fpfs == "zme") {// ���������
		var zmeView = jQuery.trim(jQuery("input[name=zmeView]")[1].value);
		if (zmeView == "") {
			return false;
		}
		var reg = /^\d*$/;
		if (!zmeView.match(reg) || zmeView < 0) {
			jQuery("span[name=zmeTip]").html("����������");
			return false;
		}
		jQuery("#zme").val(zmeView);
	}
	
	var rskzfw = jQuery("#rskzfw").val();
	if (rskzfw == RSKZFW_XY) {// ѧԺ
		var rskznj = jQuery(jQuery("tr[name=njTr]")[1]).find("input:checkbox[name=nj]:checked").map(function() {
		  return jQuery(this).val();
		}).get().join(',');
		if (rskznj == "") {
			jQuery("span[name=njTip]").html("��ѡ����Ҫ���Ƶ��꼶");
			return false;
		}
		jQuery("#rskznj").val(rskznj);
	}
	jQuery("span[name$=Tip]").html("");
	
	jQuery("#fpfs").val(fpfs);
	
	// ////form�ύ��������������////////
	jQuery("#bl").val(blView);
	var flag = false;
	var result = false;// jQuery����ѭ��
	var bmdm = "";
	var zzrs = "";
	var rskzfw = jQuery("#rskzfw").val();
	var nj = "";

	var rows = jQuery("#dataTable").getSeletRow();
	var json = "{data:[";
	for ( var i = 0; i < rows.length; i++) {
		if (flag) {
			json += ",";
		} else {
			flag = true;
		}
		json += "{";
		json += "guid:'" + rows[i].guid + "'";
		nj = rows[i].nj;
		if (nj == undefined) {
			nj = "";
		}
		json += ",nj:'" + nj + "'";// �꼶
		if (rskzfw == RSKZFW_BJ) {// �༶
			bmdm = rows[i].bjdm;
		} else if (rskzfw == RSKZFW_NJXY) {// �꼶+ѧԺ
			bmdm = rows[i].xydm;
		} else if (rskzfw == RSKZFW_NJZY) {// �꼶+רҵ
			bmdm = rows[i].zydm;
		} else if (rskzfw == RSKZFW_XY) {// ѧԺ
			bmdm = rows[i].xydm;
		} else if (rskzfw == RSKZFW_ZY) {// רҵ
			bmdm = rows[i].zydm;
		}
		json += ",bmdm:'" + bmdm + "'";
		zzrs = Math.round(parseInt(rows[i].zrs, 10) * parseInt(blView * 100)
				/ 10000);//�����������������
		json += ",zzrs:'" + zzrs + "'";
		json += "}";
	}
	json += "]}";

	jQuery("#json").val(json);
	
	divLayerClose();
	var url = "xszz_knsjcszbjpy.do?method=knsRsszSave&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data.message);
		query();
		fpmeTip();
	});
}



function query() {
	var map = {};
	map["rskzfw"] = jQuery("#rskzfw").val();
	map["rskznj"] = jQuery("#rskznj").val();
	map["xn"] = jQuery("#xn").val();
	map["xq"] = jQuery("#xq").val();	
	map["njq"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["sfysz"] = jQuery("#sfysz").val();
	map["zydm"] = jQuery("#zy").val();
	map["bjdm"] = jQuery("#bj").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����������ʾ��Ϣ
function fpmeTip() {
	var url = "xszz_knsjcszbjpy.do?method=knsRsszYszrs";
	var sTip = "ע��";
	jQuery
			.post(url, {
				xn : jQuery("#xn").val(),
				xq : jQuery("#xq").val()
			},
					function(data) {
						var zme = data.zme;
						jQuery("#zme").val(zme);
						var yszrs = data.yszrs;
						if (zme == null || zme == "null" || zme == "") {
							sTip += "��ǰ��Ŀδ���������";
							sTip += "�ѷ�������<font color='red'>" + yszrs
									+ "</font>��";
						} else {
							sTip += "��ǰ��Ŀ����������Ϊ<font color='red'>" + zme
									+ "</font>�ˣ�";
							sTip += "�ѷ�������<font color='red'>" + yszrs
									+ "</font>�ˣ�";
							var syme = zme - yszrs;
							if (syme < 0) {
								sTip += "�ѳ���������<font color='red'>"
										+ (syme * (-1)) + "</font>��";
							} else {
								sTip += "ʣ��ɷ�������<font color='red'>" + syme
										+ "</font>��";
							}
						}
						jQuery("#fpmeTip").html(sTip);

					}, 'json');
}

//��ʾ��ʾ
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

//���䷽ʽѡ�񿪹�
function setFpfs(obj){
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// ������ʽ
		jQuery("tr[name=blTr]").eq(1).show();
		jQuery("tr[name=zmeTr]").eq(1).hide();
	}else if (fpfs == "zme") {// ������
		jQuery("tr[name=zmeTr]").eq(1).show();
		jQuery("tr[name=blTr]").eq(1).hide();
		jQuery("input[name=zmeView]").eq(1).val(jQuery("#zme").val());
	}
}

/* ���� */
function update() {
	var blsFlag = false;
	//?????
	jQuery("input[name=bls]").each(function(index) {
		if (jQuery(this).val() == null || jQuery(this).val() == "") {
			blsFlag = true;
			return false;
		}
	});

	var flag = false;
	if ((jQuery("input[name=zzrss]").length == 0)) {
		showAlert("û����Ҫ����ļ�¼��");
		return false;
	}

	jQuery("input[name=zzrss]").each(
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

	var url = "xszz_knsjcszbjpy.do?method=rsszXg&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data.message, {}, {
			"clkFun" : function(tag) {
				query();
				divLayerClose();
				fpmeTip();
			}
		});
	});
}
