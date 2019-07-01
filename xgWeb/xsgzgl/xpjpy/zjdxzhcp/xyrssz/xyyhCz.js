/*定义全局变量*/
var flag = true;

/*评奖项目当中人数根据(参评人数或已提交人数) ytjrs:已提交人数 ,zrs:参评人数*/
var RSJSFS = jQuery("#rsjsfs").val();

jQuery(function() {
	RSJSFS = jQuery("#rsjsfs").val();
	var url = "xpjpy_xyrssz.do?method=xyrszCx&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	url += "&czfs=" + jQuery("#czfs").val();
	gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ 
			    {label : '学院代码',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
				{label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
				{label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
				{label : '学院代码s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms}, 
				{label : jQuery("#xbmc").val()+'名称',name : 'xymc',index : 'xymc',width : '35%'},
				{label : '已提交人数',name : 'ytjrs',index : 'ytjrs',width : '15%'}, 
				{label : '已提交人数',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
				{label : '参评人数',name : 'zrs',index : 'zrs',width : '15%'}, 
				{label : '参评人数',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss}, 
				{label : '参评比例(%)',name : 'fpbl',index : 'fpbl',width : '10%',formatter : setBl}, 
				{label : '比例(%)',name : 'fpbls',index : 'fpbls',hidden : true,formatter : setBls}, 
				{label : '浙大设置人数',name : 'zd3',index : 'zd3',hidden : true},
				{label : '计算人数',name : 'jsrs',index : 'to_number(jsrs)',width : '15%',noSort : true,formatter : setJsrs}, 
				{label : '最终人数',name : 'zzme',index : 'to_number(zzme)',width : '10%',formatter : setZzrs} 
			],
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	jQuery("#dataTable").initGrid(gridSetting);
	
	setHelpTip();// 提示显示
	fpmeTip();// 分配名额提示
	setSpzt();//根据审核状态进行设置
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

/* 设置学院代码，保存数据 */
function setXydms(cellValue, rowObject) {
	var xydm = rowObject.xydm;
	if (xydm == null) {
		xydm = "";
	}
	cellValue = "<input type='hidden' name='xydms' value='" + xydm + "' >";
	return cellValue;
}

/*已提交人数*/
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

/* 设置bl，为了定位重新赋值 */
function setBl(cellValue, rowObject) {
	var fpbl = rowObject.fpbl;
	if (fpbl == null) {
		fpbl = "";
	}
	cellValue = "<input type='hidden' name='blHid' value='" + fpbl + "'>" + fpbl;
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

/* 设置最终人数 */
function setZzrs(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' id='srrs' style='width:60px' name='zzmes' maxlength='10' value='"
			+ cellValue + "' onblur='checkRssxAndJesx(this,\"" + cellValue + "\",\""+rowObject["fpbl"]+"\",\""+rowObject["jsrs"]+"\")'/>";
	return cellValue;
}

//学院控制一、二、三等奖学金上限
function checkRssxAndJesx(obj,zzrs,fpbl,jsrs){
	var message="";

		var url = "xpjpy_xyrssz.do?method=rsszCheckAjax";
		jQuery.ajaxSetup({async:false});
		jQuery.post(url, {
			xmdm:jQuery("#xmdm").val(),
			xmje:jQuery("#xmje").val()
		}, function(data) {
			var zzrs_new = obj.value;
			dataObj = data;
			message=checkParam(obj,zzrs,zzrs_new,fpbl,jsrs);
			}, 'json');
	
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

	var url = "xpjpy_xyrssz.do?method=xmwhRsszXg&type=save";
	ajaxSubFormWithFun("xyrsszForm", url, function(data) {
		showAlert(data.message, {}, {
			"clkFun" : function(tag) {
				divLayerClose();
			}
		});
	});
	query();
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

//分配名额提示信息
function fpmeTip() {
	var rsfpfs = jQuery("#rsfpfs").val();
	var url = "xpjpy_xyrssz.do?method=xmwhRsszYszrs";
	var sTip = "注：";
	jQuery.post(url, {xmdm : jQuery("#xmdm").val()},
		function(data){
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

//提示显示
function setHelpTip() {
	var tip = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	tip = jQuery("#xbmc").val();
	jQuery("#helpTip").html(tip);
}

//根据审核状态进行设置
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
	}
}
