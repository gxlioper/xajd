var RSKZFW_BJ = "bj";// 班级
var RSKZFW_NJXY = "njxy";// 年级学院
var RSKZFW_NJZY = "njzy";// 年级专业
var RSKZFW_XY = "xy";// 学院
var RSKZFW_ZY = "zy";// 专业
var gridSetting;
jQuery(function() {
	var url = "xszz_jcsz.do?method=knsRssz&type=query";
	var rskzfw = jQuery("#rskzfw").val();
	var rskznj = jQuery("#rskznj").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	url+="&rskzfw="+rskzfw;
	url+="&rskznj="+rskznj;
	url+="&xn="+xn;
	url+="&xq="+xq;
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
				hidden : true,
				formatter : setGuids
			}, {
				label : jQuery("#xbmc").val()+'名称',
				name : 'xymc',
				index : 'xymc',
				width : '20%'
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
			}, {
				label : '年级',
				name : 'nj',
				index : 'nj',
				width : '8%'
			}, {
				label : '年级s',
				name : 'njs',
				index : 'njs',
				hidden : true,
				formatter : setNjs
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
				hidden : true,
				formatter : setZydms
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
				hidden : true,
				formatter : setBjdms
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '8%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'bl',
				index : 'bl',
				width : '8%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'jsrs',
				width : '8%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '8%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setNjs
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'jsrs',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setNjs
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
				hidden : true,
				formatter : setZydms
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'jsrs',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'jsrs',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzrs',
				index : 'zzrs',
				width : '10%',
				formatter : setZzrs
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
				hidden : true,
				formatter : setGuids
			}, {
				label : '学院代码s',
				name : 'xydms',
				index : 'xydms',
				hidden : true,
				formatter : setXydms
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
				//hidden : true
			}, {
				label : '专业',
				name : 'zymc',
				index : 'zymc',
				width : '25%'
			}, {
				label : '专业s',
				name : 'zydms',
				index : 'zydms',
				hidden : true,
				formatter : setZydms
			}, {
				label : '人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'bl',
				index : 'bl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'bls',
				index : 'bls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'jsrs',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
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
	query();
});

/* 设置guids，保存数据 */
function setGuids(cellValue, rowObject) {
	var guid = rowObject.guid;
	if (guid == null) {
		guid = "";
	}
	cellValue = "<input type='hidden' name='guids' value='" + guid + "' >";
	return cellValue;
}

/* 设置bls，保存数据 */
function setBls(cellValue, rowObject) {
	var bl = rowObject.bl;
	if (bl == null) {
		bl = "";
	}
	cellValue = "<input type='hidden' name='bls' value='" + bl + "' >";
	return cellValue;
}

/* 设置bl，为了定位重新赋值 */
function setBl(cellValue, rowObject) {
	var bl = rowObject.bl;
	if (bl == null) {
		bl = "";
	}
	cellValue = "<input type='hidden' name='blHid' value='" + bl + "'>" + bl;
	return cellValue;
}

/* 设置年级，保存数据 */
function setNjs(cellValue, rowObject) {
	var nj = rowObject.nj;
	if (nj == null) {
		nj = "";
	}
	cellValue = "<input type='hidden' name='njs' value='" + nj + "' >";
	return cellValue;
}

/* 设置学院代码，保存数据 */
function setXydms(cellValue, rowObject) {
	var xydm = rowObject.xydm;
	if (xydm == null) {
		xydm = "";
	}
	cellValue = "<input type='hidden' name='xydms' value='" + xydm + "' >";
	return cellValue;
}

/* 设置专业代码，保存数据 */
function setZydms(cellValue, rowObject) {
	var zydm = rowObject.zydm;
	if (zydm == null) {
		zydm = "";
	}
	cellValue = "<input type='hidden' name='zydms' value='" + zydm + "' >";
	return cellValue;
}

/* 设置班级代码，保存数据 */
function setBjdms(cellValue, rowObject) {
	var bjdm = rowObject.bjdm;
	if (bjdm == null) {
		bjdm = "";
	}
	cellValue = "<input type='hidden' name='bjdms' value='" + bjdm + "' >";
	return cellValue;
}

/* 设置总人数，保存数据 */
function setZrss(cellValue, rowObject) {
	var zrs = rowObject.zrs;
	if (zrs == null) {
		zrs = "";
	}
	cellValue = "<input type='hidden' name='zrss' value='" + zrs + "' >";
	return cellValue;
}

/* 设置计算人数 */
function setJsrs(cellValue, rowObject) {
	var bl = rowObject.bl;
	var zrs = rowObject.zrs;
	cellValue = setJsrsJs(cellValue, bl, zrs);
	return cellValue;
}

/* 设置计算人数dd */
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

/* 设置最终人数 */
function setZzrs(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' style='width:60px' name='zzrss' maxlength='10' value='"
			+ cellValue + "' />";
	return cellValue;
}

/* 比例设置 */
function blsz() {
	if ((jQuery("input[name=zzrss]").length == 0)) {
		showAlert("没有需要设置的记录！");
		return false;
	}
	setRskznj();//当为学院时，设置人数控制年级
	var title = "分配设置";
	var content = jQuery("#blDiv").html();
	var rskzfw = jQuery("#rskzfw").val();
	if (rskzfw == RSKZFW_XY&&jQuery("#knsrsszfs").val()!="1") {// 学院
		tipsWindown(title, content, 400, 320);
	}else{
		tipsWindown(title, content, 400, 160);
	}
}

//当为学院时，设置人数控制年级
function setRskznj() {
	var rskzfw = jQuery("#rskzfw").val();
	jQuery("#njTipDiv").hide();
	if (rskzfw == RSKZFW_XY&&jQuery("#knsrsszfs").val()!="1") {// 学院
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

/* 比例设置保存 */
function saveForm() {
	var fpfs = jQuery.trim(jQuery("input:radio[name=fpfs]:checked")[1].value);
	if (fpfs == "bl") {// 比例方式
		var blView = jQuery.trim(jQuery("input[name=blView]")[1].value);
		if (blView == "") {
			jQuery("span[name=blTip]").html("请输入比例");
			return false;
		}
		var reg = /^(([1-9]\d{0,2})|[0-9])([.]\d{1,2})?$/;
		if (!blView.match(reg) || blView > 100) {
			jQuery("span[name=blTip]").html("请输入0-100的数字，最多两位小数");
			return false;
		}
	} else if (fpfs == "zme") {// 总名额分配
		var zmeView = jQuery.trim(jQuery("input[name=zmeView]")[1].value);
		if (zmeView == "") {
			return false;
		}
		var reg = /^\d*$/;
		if (!zmeView.match(reg) || zmeView < 0) {
			jQuery("span[name=zmeTip]").html("请输入整数");
			return false;
		}
		jQuery("#zme").val(zmeView);
	}
	
	var rskzfw = jQuery("#rskzfw").val();
	if(jQuery("#knsrsszfs").val()!="1"){
		if (rskzfw == RSKZFW_XY) {// 学院
			var rskznj = jQuery(jQuery("tr[name=njTr]")[1]).find("input:checkbox[name=nj]:checked").map(function() {
			  return jQuery(this).val();
			}).get().join(',');
			if (rskznj == "") {
				jQuery("span[name=njTip]").html("请选择需要控制的年级");
				return false;
			}
			jQuery("#rskznj").val(rskznj);
		}
	}
	jQuery("span[name$=Tip]").html("");
	
	jQuery("#fpfs").val(fpfs);
	
	// ////form提交，处理。。。。。////////
	jQuery("#bl").val(blView);
	var flag = false;
	var result = false;// jQuery跳出循环
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
		json += ",nj:'" + nj + "'";// 年级
		if (rskzfw == RSKZFW_BJ) {// 班级
			bmdm = rows[i].bjdm;
		} else if (rskzfw == RSKZFW_NJXY) {// 年级+学院
			bmdm = rows[i].xydm;
		} else if (rskzfw == RSKZFW_NJZY) {// 年级+专业
			bmdm = rows[i].zydm;
		} else if (rskzfw == RSKZFW_XY) {// 学院
			bmdm = rows[i].xydm;
		} else if (rskzfw == RSKZFW_ZY) {// 专业
			bmdm = rows[i].zydm;
		}
		json += ",bmdm:'" + bmdm + "'";
		zzrs = Math.round(parseInt(rows[i].zrs, 10) * parseInt(blView * 100)
				/ 10000);//四舍五入计算总人数
		json += ",zzrs:'" + zzrs + "'";
		json += "}";
	}
	json += "]}";

	jQuery("#json").val(json);
	divLayerClose();
	var url = "xszz_jcsz.do?method=knsRsszSave&type=save";
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
	if(jQuery("#rddc").length>0){ // rddc元素存在
		map["rddc"] = jQuery("#rddc").val();
	}
	jQuery("#dataTable").reloadGrid(map);
}

//分配名额提示信息
function fpmeTip() {
	var url = "xszz_jcsz.do?method=knsRsszYszrs";
	var sTip = "注：";
	var rddc="";
	if(jQuery("#rddc").length>0){ // rddc元素存在
		rddc = jQuery("#rddc").val();
	}
	jQuery.post(url, {
				xn : jQuery("#xn").val(),
				xq : jQuery("#xq").val(),
				rddc:rddc
			},
					function(data) {
						var zme = data.zme;
						jQuery("#zme").val(zme);
						var yszrs = data.yszrs;
						if (zme == null || zme == "null" || zme == "") {
							sTip += "当前项目未设置总名额，";
							sTip += "已分配名额<font color='red'>" + yszrs	+ "</font>人";
						} else {
							sTip += "当前项目设置总名额为<font color='red'>" + zme+ "</font>人，";
							sTip += "已分配名额<font color='red'>" + yszrs	+ "</font>人，";
							var syme = zme - yszrs;
							if (syme < 0) {
								sTip += "已超过总名额<font color='red'>"+ (syme * (-1)) + "</font>人";
							} else {
								sTip += "剩余可分配名额<font color='red'>" + syme+ "</font>人";
							}
						}
						jQuery("#fpmeTip").html(sTip);

					}, 'json');
}

//提示显示
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

//分配方式选择开关
function setFpfs(obj){
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// 比例方式
		jQuery("tr[name=blTr]").eq(1).show();
		jQuery("tr[name=zmeTr]").eq(1).hide();
	}else if (fpfs == "zme") {// 总名额
		jQuery("tr[name=zmeTr]").eq(1).show();
		jQuery("tr[name=blTr]").eq(1).hide();
		jQuery("input[name=zmeView]").eq(1).val(jQuery("#zme").val());
	}
}

/* 保存 */
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
		showAlert("没有需要保存的记录！");
		return false;
	}

	jQuery("input[name=zzrss]").each(
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

	var url = "xszz_jcsz.do?method=rsszXg&type=save";
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
