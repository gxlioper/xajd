var RSFPFS_BJ = "bj";// 班级
var RSFPFS_NJXY = "njxy";// 年级学院
var RSFPFS_NJZY = "njzy";// 年级专业
var RSFPFS_XY = "xy";// 学院
var RSFPFS_ZY = "zy";// 专业
var RSFPFS_XX = "xx";// 学校
var RSFPFS_CPZ = "cpz";// 参评组
var flag=true;
var RSJSFS = jQuery("#rsjsfs").val(); //评奖项目当中人数根据(参评人数或已提交人数) ytjrs:已提交人数 ,zrs:参评人数
var gridSetting;
jQuery(function() {
	RSJSFS = jQuery("#rsjsfs").val();
	var url = "xpj_xmwh_rssz.do?method=xmwhRsszCx&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	url += "&czfs=" + jQuery("#czfs").val();
	if(jQuery("#xxdm").val() == "10704"){
		url += "&sfyxgb="+jQuery("#sfyxgb").val();
	}
	if (rsfpfs == RSFPFS_BJ) {// 班级
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : '学院代码',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
			            {label : jQuery("#xbmc").val()+'名称',name : 'xymc',index : 'xymc',width : '20%'}, 
						{label : '学院代码s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms}, 
						{label : '年级',name : 'nj',index : 'nj',width : '8%'}, 
						{label : '年级s',name : 'njs',index : 'njs',hidden : true,formatter : setNjs}, 
						{label : '专业代码',name : 'zydm',index : 'zydm',hidden : true}, 
						{label : '专业',name : 'zymc',index : 'zymc',width : '20%'}, 
						{label : '专业s',name : 'zydms',index : 'zydms',hidden : true,formatter : setZydms}, 
						{label : '班级代码',name : 'bjdm',index : 'bjdm',hidden : true}, 
						{label : '班级',name : 'bjmc',index : 'bjmc',width : '20%'}, 
						{label : '班级s',name : 'bjdms',index : 'bjdms',hidden : true,formatter : setBjdms}, 
						{label : '已提交人数',name : 'ytjrs',index : 'ytjrs',width : '8%'}, 
						{label : '已提交人数',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss}, 
						{label : '参评人数',name : 'zrs',index : 'zrs',width : '8%'}, 
						{label : '参评人数',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss}, 
						{label : '比例(%)',name : 'fpbl',index : 'fpbl',width : '8%',formatter : setBl}, 
						{label : '比例(%)',name : 'fpbls',index : 'fpbls',hidden : true,formatter : setBls}, 
						{label : '计算人数',name : 'jsrs',index : 'to_number(jsrs)',width : '8%',noSort : true,formatter : setJsrs},
						{label : '最终人数',name : 'zzme',index : 'to_number(zzme)',width : '8%',formatter : setZzrs} 
			],
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
			            {label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
			            {label : '学院代码s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms}, 
			            {label : jQuery("#xbmc").val()+'名称',name : 'xymc',index : 'xymc',width : '35%'}, {
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
				label : '已提交人数',
				name : 'ytjrs',
				index : 'ytjrs',
				width : '8%'
			},{
				label : '已提交人数',
				name : 'ytjrss',
				index : 'ytjrss',
				hidden : true,
				formatter : setYtjrss
			},{
				label : '参评人数',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '参评人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			},{
				label : '比例(%)',
				name : 'fpbl',
				index : 'fpbl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'fpbls',
				index : 'fpbls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'to_number(jsrs)',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzme',
				index : 'to_number(zzme)',
				width : '10%',
				formatter : setZzrs
			} ],
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_NJZY) {// 年级+专业
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
				label : '学院代码',
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
				label : '已提交人数',
				name : 'ytjrs',
				index : 'ytjrs',
				width : '8%'
			},{
				label : '已提交人数',
				name : 'ytjrss',
				index : 'ytjrss',
				hidden : true,
				formatter : setYtjrss
			},{
				label : '参评人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '参评人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'fpbl',
				index : 'fpbl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'fpbls',
				index : 'fpbls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'to_number(jsrs)',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzme',
				index : 'to_number(zzme)',
				width : '10%',
				formatter : setZzrs
			} ],
			sortname : "xymc,nj,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_XY) {// 学院
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
			},{
				label : '已提交人数',
				name : 'ytjrs',
				index : 'ytjrs',
				width : '15%'
			}, {
				label : '已提交人数',
				name : 'ytjrss',
				index : 'ytjrss',
				hidden : true,
				formatter : setYtjrss
			},{
				label : '参评人数',
				name : 'zrs',
				index : 'zrs',
				width : '15%'
			}, {
				label : '参评人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '参评比例(%)',
				name : 'fpbl',
				index : 'fpbl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'fpbls',
				index : 'fpbls',
				hidden : true,
				formatter : setBls
			}, {
				label : '浙大设置人数',
				name : 'zd3',
				index : 'zd3',
				hidden : true
			},{
				label : '计算人数',
				name : 'jsrs',
				index : 'to_number(jsrs)',
				width : '15%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzme',
				index : 'to_number(zzme)',
				width : '10%',
				formatter : setZzrs
			} ],
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	} else if (rsfpfs == RSFPFS_ZY) {// 专业
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
			},{
				label : '已提交人数',
				name : 'ytjrs',
				index : 'ytjrs',
				width : '8%'
			},{
				label : '已提交人数',
				name : 'ytjrss',
				index : 'ytjrss',
				hidden : true,
				formatter : setYtjrss
			},{
				label : '参评人数',
				name : 'zrs',
				index : 'zrs',
				width : '10%'
			}, {
				label : '参评人数',
				name : 'zrss',
				index : 'zrss',
				hidden : true,
				formatter : setZrss
			}, {
				label : '比例(%)',
				name : 'fpbl',
				index : 'fpbl',
				width : '10%',
				formatter : setBl
			}, {
				label : '比例(%)',
				name : 'fpbls',
				index : 'fpbls',
				hidden : true,
				formatter : setBls
			}, {
				label : '计算人数',
				name : 'jsrs',
				index : 'to_number(jsrs)',
				width : '10%',
				noSort : true,
				formatter : setJsrs
			}, {
				label : '最终人数',
				name : 'zzme',
				index : 'to_number(zzme)',
				width : '10%',
				formatter : setZzrs
			} ],
			sortname : "xymc,zymc",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rsfpfs == RSFPFS_CPZ) {// 参评组
		gridSetting = {
				caption : "",
				pager : "pager",
				url : url,
				colList : [
				           {label : '参评组代码',name : 'cpzdm',index : 'cpzdm',key : true,hidden : true}, 
				           {label : '参评组代码s',name : 'cpzdms',index : 'cpzdms',hidden : true,formatter : setCpzdms}, 
				            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
				            {label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
				            {label : '参评组名称',name : 'cpzmc',index : 'cpzmc',width : '40%'},
				            {label : '已提交人数',name : 'ytjrs',index : 'ytjrs',width : '8%'},
				            {label : '已提交人数',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
							{label : '参评人数',name : 'zrs',index : 'zrs',width : '15%'}, 
							{label : '参评人数',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss}, 
							{label : '比例(%)',name : 'fpbl',index : 'fpbl',width : '15%',formatter : setBl}, 
							{label : '比例(%)',name : 'fpbls',index : 'fpbls',hidden : true,formatter : setBls}, 
							{label : '计算人数',name : 'jsrs',index : 'to_number(jsrs)',width : '15%',noSort : true,formatter : setJsrs},
							{label : '最终人数',name : 'zzme',index : 'to_number(zzme)',width : '15%',formatter : setZzrs} 
				],
				sortname : "cpzdm",
				rowNum : 10,
				sortorder : "asc"
			}
	} else if (rsfpfs == RSFPFS_XX) {// 学校
		gridSetting = {
				caption : "",
				pager : "none",
				url : url,
				colList : [
				            {label : '学校分配方式',name : 'xx',index : 'xx',width : '40%',formatter : setXxfs},
				            {label : '已提交人数',name : 'ytjrs',index : 'ytjrs',width : '8%'},
				            {label : '已提交人数',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
							{label : '参评人数',name : 'zrs',index : 'zrs',width : '30%'}, 
							{label : '参评人数s',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss}, 
							{label : '最终人数',name : 'zzme',index : 'zzme',width : '30%',formatter : setZzrsOfXx} 
				],
				rowNum : 10,
				sortorder : "asc"
			}
	} else{// 
		alert("请先在[修改]中配置[人数分配方式]！");
	}

	jQuery("#dataTable").initGrid(gridSetting);

	setHelpTip();// 提示显示
	fpmeTip();// 分配名额提示
	setSpzt();//根据审核状态进行设置
	
});

//根据审核状态进行设置
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
	}
}


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

// 分配名额提示信息
function fpmeTip() {
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_XX) {// 学校
		jQuery("#fpszLi").css("display","none");
		jQuery("#zzrsscTip").css("display","none");
		return;
	}
	var url = "xpj_xmwh_rssz.do?method=xmwhRsszYszrs";
	var sTip = "注：";
	jQuery
			.post(url, {
				xmdm : jQuery("#xmdm").val()
			},
					function(data) {
						var zme = data.zme;
						jQuery("#zme").val(zme);
						var yszrs = data.yszrs;
						if (zme == null || zme == "null" || zme == "") {
							sTip += "当前项目未设置总名额，";
							sTip += "已分配名额<font color='red'>" + yszrs
									+ "</font>人";
						} else {
							sTip += "当前项目设置总名额为<font color='red'>" + zme
									+ "</font>人，";
							sTip += "已分配名额<font color='red'>" + yszrs
									+ "</font>人，";
							var syme = zme - yszrs;
							if (syme < 0) {
								sTip += "已超过总名额<font color='red'>"
										+ (syme * (-1)) + "</font>人";
							} else {
								sTip += "剩余可分配名额<font color='red'>" + syme
										+ "</font>人";
							}
						}
						jQuery("#fpmeTip").html(sTip);

					}, 'json');
}

//当为学院时，设置人数控制年级
function setRskznj() {
	var rsfpfs = jQuery("#rsfpfs").val();
	jQuery("#njTipDiv").hide();
	if (rsfpfs == RSFPFS_XY) {// 学院
		jQuery("tr[name=njTr]").show();
		jQuery("#njTipDiv").show();

		var rsfpnj = jQuery("#rsfpnj").val();
		var njHtml = "";
		var njList = eval(jQuery("#njList").val());
		for ( var i = 0; i < njList.length; i++) {
			if(i != 0 && i %4== 0){
				njHtml += "<br/>";
			}
			njHtml += "<label>";
			if(rsfpnj == "" || rsfpnj.indexOf(njList[i]) > -1){
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
	var fpbl = rowObject.fpbl;
	if (fpbl == null) {
		fpbl = "";
	}
	cellValue = "<input type='hidden' name='fpbls' value='" + fpbl + "' >";
	return cellValue;
}

/* 设置bl，为了定位重新赋值 */
function setBl(cellValue, rowObject) {
	var fpbl = rowObject.fpbl;
	if (fpbl == null) {
		fpbl = "";
	}
	cellValue = "<input type='hidden' name='blHid' value='" + fpbl + "'>" + fpbl;
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


/* 设置参评组代码，保存数据 */
function setCpzdms(cellValue, rowObject) {
	var cpzdm = rowObject.cpzdm;
	if (cpzdm == null) {
		cpzdm = "";
	}
	cellValue = "<input type='hidden' name='cpzdms' value='" + cpzdm + "' >";
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

function setYtjrss(cellValue, rowObject) {
	var ytjrs = eval("rowObject."+RSJSFS); 
	if (ytjrs == null) {
		ytjrs = "";
	}
	cellValue = "<input type='hidden' name='ytjrs' value='" + ytjrs + "' >";
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
	var bl = rowObject.fpbl;
	var ytjrs = eval("rowObject."+RSJSFS);
	var xxdm = jQuery("#xxdm").val();
	var cellValue = rowObject.zd3;
	if("10335"==xxdm){
		rowObject.jsrs=cellValue;
		cellValue = "<input type='hidden' name='jsrsHid' value='" + cellValue
				+ "'>" + cellValue;
	}else{
		cellValue = setJsrsJs(cellValue, bl, ytjrs,rowObject);
	}
	return cellValue;
}

/* 设置计算人数dd */
function setJsrsJs(cellValue, bl, ytjrs,rowObject) {
	if (bl != null && bl != "" && ytjrs != null && ytjrs != "") {
		cellValue = parseInt(ytjrs, 10) * parseInt(bl * 100) / 10000;
	}
	if (cellValue == null) {
		cellValue = "";
	}
	rowObject.jsrs=cellValue;
	cellValue = "<input type='hidden' name='jsrsHid' value='" + cellValue
			+ "'>" + cellValue;
	
	return cellValue;
}

/* 设置最终人数 */
function setZzrs(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' id='srrs' style='width:60px' name='zzmes' maxlength='10' value='"
			+ cellValue + "' onblur='checkRssxAndJesx(this,\"" + cellValue + "\",\""+rowObject["fpbl"]+"\",\""+rowObject["jsrs"]+"\")'/>";
	return cellValue;
}
//浙大-学院控制一、二、三等奖学金上限（个性化）
function checkRssxAndJesx(obj,zzrs,fpbl,jsrs){
	var message="";

	if("10335"==jQuery("#xxdm").val()){
		var url = "xpj_xmwh_rssz.do?method=rsszCheckAjax";
		jQuery.ajaxSetup({async:false});
		jQuery.post(url, {
			xmdm:jQuery("#xmdm").val(),
			xmje:jQuery("#xmje").val()
		}, function(data) {
			var zzrs_new = obj.value;
			
			dataObj = data;
			message=checkParam(obj,zzrs,zzrs_new,fpbl,jsrs);
			}, 'json');
		}
	
	if(""!=message){
		  showAlertDivLayer(message,{},{"clkFun":function(){
			  obj.focus();
			}});
		  return false;
	}
	jQuery.ajaxSetup({async:true});
	}
function checkParam(obj,zzrs,zzrs_new,fpbl,jsrs){
	
	var rssx=0;//上限人数
	var sxje=0;//上限金额
	for ( var i = 0; i < dataObj.length; i++) {
		var res = dataObj[i];
		//奖学金总金额=修改前奖学金实际总金额-修改前所修改奖项总金额+修改后所修改奖项总金额
		var sxje = res.jxsjze-(zzrs*jQuery("#xmje").val())+(zzrs_new*jQuery("#xmje").val());
		if(res.xmmc==jQuery("#xmmc").val()){
		   rssx=parseFloat(jsrs)+parseFloat(res.rssx);
		   if(rssx<zzrs_new){
			  message="最终人数超过上限"+parseInt(rssx)+"人";
				return message;
		   }
		}
	 if(sxje>res.jxjze){
		   message="奖学金总金额"+sxje+"超过上限"+res.jxjze+"元,"+"请修改最终人数！";
			return message;
	   }
	}	   	   
}

/* 学校用户设置最终人数 */
function setZzrsOfXx(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' style='width:60px' name='zzmes' maxlength='10' value='"
			+ cellValue + "' />";
	return cellValue;
}


/* 设置学校分配方式 */
function setXxfs(cellValue, rowObject) {
	return "学校分配方式";
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
	map["cpzmc"] = jQuery("#cpzmc").val();
	jQuery("#dataTable").reloadGrid(map);
	fpmeTip();
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
				divLayerClose();
			}
		});
	});
	query();
}

/* 比例设置 */
function blsz() {
	if ((jQuery("input[name=zzmes]").length == 0)) {
		showAlert("没有需要设置的记录！");
		return false;
	}
	setRskznj();//当为学院时，设置人数控制年级
	var title = "分配设置";
	var content = jQuery("#blDiv").html();
	var zme=jQuery("#zme").val();
	//zem为不在弹出div中的值故要传递过去
	
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_XY) {// 学院
		tipsWindownNew("分配设置","id:blDiv",380,270,"zme:"+zme);
	}else{
		tipsWindownNew("分配设置","id:blDiv",380,160,"zme:"+zme);
	}
	
	//tipsWindown(title, content, 400, 320);
}
//分配方式选择开关
function setFpfs(obj){
	var index=0;
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// 比例方式
		jQuery("tr[name=blTr]").eq(index).show();
		jQuery("tr[name=zmeTr]").eq(index).hide();
	}else if (fpfs == "zme") {// 总名额
		jQuery("tr[name=zmeTr]").eq(index).show();
		jQuery("tr[name=blTr]").eq(index).hide();
		jQuery("input[name=zmeView]").eq(index).val(jQuery("#zme").val());
	}
}
/* 比例设置保存 */
function saveForm() {
	var index=0;
	var blView = jQuery.trim(jQuery("input[name=blView]")[index].value);

	/*var index=0;
	var fpfs = jQuery.trim(jQuery("input:radio[name=fpfs]:checked")[index].value);
	alert(fpfs);
	if (fpfs == "bl") {// 比例方式
		var blView = jQuery.trim(jQuery("input[name=blView]")[index].value);
		if (blView == "") {
			return false;
		}
		var reg = /^(([1-9]\d{0,2})|[0-9])([.]\d{1,2})?$/;
		if (!blView.match(reg) || blView > 100) {
			jQuery("span[name=blTip]").html("请输入0-100的数字，最多两位小数");
			return false;
		}
	} else if (fpfs == "zme") {// 总名额分配
		var zmeView = jQuery.trim(jQuery("input[name=zmeView]")[index].value);
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
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_XY) {// 学院
		var rsfpnj = jQuery(jQuery("tr[name=njTr]")[0]).find("input:checkbox[name=nj]:checked").map(function() {
		  return jQuery(this).val();
		}).get().join(',');
		if (rsfpnj == "") {
			jQuery("span[name=njTip]").html("请选择需要控制的年级");
			return false;
		}
		jQuery("#rsfpnj").val(rsfpnj);
	}
	jQuery("span[name$=Tip]").html("");
	
	jQuery("#fpfs").val(fpfs);
	*/
	// ////form提交，处理。。。。。////////
	jQuery("#fpbl").val(blView);
	var flag = false;
	var result = false;// jQuery跳出循环
	var bmdm = "";
	var zzme = "";
	var rsfpfs = jQuery("#rsfpfs").val();
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
		if (rsfpfs == RSFPFS_BJ) {// 班级
			bmdm = rows[i].bjdm;
		} else if (rsfpfs == RSFPFS_NJXY) {// 年级+学院
			bmdm = rows[i].xydm;
		} else if (rsfpfs == RSFPFS_NJZY) {// 年级+专业
			bmdm = rows[i].zydm;
		} else if (rsfpfs == RSFPFS_XY) {// 学院
			bmdm = rows[i].xydm;
		} else if (rsfpfs == RSFPFS_ZY) {// 专业
			bmdm = rows[i].zydm;
		} else if (rsfpfs == RSFPFS_CPZ) {// 参评组
			bmdm = rows[i].cpzdm;
		}
		json += ",bmdm:'" + bmdm + "'";
		zzme = Math.round(parseInt(eval("rows[i]."+RSJSFS), 10) * parseInt(blView * 100)
				/ 10000);//四舍五入计算总人数
		json += ",zzme:'" + zzme + "'";
		json += "}";
	}
	json += "]}";

	jQuery("#json").val(json);

	divLayerClose();
	var url = "xpj_xmwh_rssz.do?method=xmwhRsszBlsz&type=save";
	if(jQuery("#xxdm").val() == '10704'){
		url+="&sfyxgb="+jQuery("#sfyxgb").val();
	}
	
	ajaxSubFormWithFun("form1", url, function(data) {
//		showAlert(data.message);   去掉保存提示
		query();
		jQuery(parent.window.document).find('#search_go').click();
	});
}

// 历年名额分配情况 弹层
function lnme() {
	var xmdm = jQuery("#xmdm").val();
	var url = 'xpj_xmwh_rssz.do?method=xmwhRsszLnme';
	url += "&xmdm=" + xmdm;
	var title = "历年名额分配情况";
	showDialog(title, 700, 500, url);
}


