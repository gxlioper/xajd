var flszList;// 分类设置列表
var zddyList;// 字段定义列表
var valueJson;// 值

var CONTENT_ID = "content";// 内容显示div
var NAVIGATION_ID = "navigation";// 导航栏div
var bkczzd = "nj,xydm,zydm,bjdm";// 学生不可操作字段，固定为只读

var PREFL_MKGS = 7;// 显示模块个数

var PREFL = "zdybdfl_";// 命名前缀，自定义表单分类
var PREFL_UL = "zdybdfl_ul_";// 命名前缀，自定义表单分类
var PREFL_LI = "zdybdfl_li_";// 命名前缀，自定义表单分类
var PREFL_HREF = "zdybdfl_href_";// 命名前缀，自定义表单分类
var PRECON_TABLE = "zdybdcon_table_";// 命名前缀，自定义表单内容
var PRECON_TH = "zdybdcon_th_";// 命名前缀，自定义表单内容
var PRECON_TD = "zdybdcon_td_";// 命名前缀，自定义表单内容
var PRECON_MORE_TR = "zdybdcon_more_tr_";// 命名前缀，自定义表单内容
var moreUpdateTrJson = {};// 多条修改记录，增加一行
var moreUpdateZdJson = [];// 多条修改记录，字段

var xs = "";// 为true，为仅显示模式
var js = "";//角色，xs为学生
var flag = null;
var index = 0;
function zdybdInit(gndm, param, otherParams,flag1) {
	jQuery("#" + CONTENT_ID).hide();
	if (otherParams != null) {
		xs = otherParams["xs"],
		js = otherParams["js"];
	}
	valueJson = param;
	flag = flag1;
	queryFl(gndm);

	jQuery("#" + CONTENT_ID).show();

}

/**
 * 分类查询
 * 
 * @return
 */
function queryFl(gndm) {
	// 字段定义
	var url = "zdybd_zddy.do?method=getZddyList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			gndm : gndm,
			xs : xs
		},
		dataType : "json",
		success : function(data) {
			zddyList = data;
			setInitZddy();			
		}
	});

	// 分类
	url = "zdybd_flsz.do?method=getFlszList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			gndm : gndm
		},
		dataType : "json",
		success : function(data) {
			flszList = data;
			if (flszList != null && flszList.length > 0) {
				if (jQuery("#" + NAVIGATION_ID).length > 0) {
					createNav();// 生成导航
				}
				if (jQuery("#" + CONTENT_ID).length > 0) {
					createConNavFirst();// 生成内容导航
					createCon();// 生成内容
					setValue();// 设置值
					navAA();// js特效
				}
			}
		}
	});
}

function setInitZddy(){
	if(js == "xs"){
		
		for ( var i = 0; i < zddyList.length; i++) {
			var o = zddyList[i];
			var zd = o.zd;// 
			
			if(bkczzd.indexOf(zd) > -1){
				o.yxxg = "0";//不允许修改
				o.yxwk = "1";//
			}
		}		
	}
}

/**
 * 生成导航
 * 
 * @return
 */
function createNav() {
	var sHtml = "";
	sHtml += "<div class='position_xxxx after' >";
	sHtml += "<ul class='list_xxxx' id='" + PREFL_UL + "' >";
	sHtml += "</ul>";
	sHtml += "</div>";
	jQuery("#" + NAVIGATION_ID).append(sHtml);
	createNavFirst();// 生成一级导航
}

/*
 * 生成一级导航
 */
function createNavFirst() {
	var mkNum = 0;
	var sHtml = "";
	var sHtmlMore = "";
	for ( var i = 0; i < flszList.length; i++) {
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flflszid = o.flflszid;// 父类id
		var flmc = o.flmc;// 分类名称
		if (flflszid == null || flflszid == "") {// 为顶级类别
			if (mkNum < PREFL_MKGS) {
				sHtml = "<li id='" + PREFL_LI + flszid + "'>";
				sHtml += "<a href='#" + PREFL_HREF + flszid
						+ "' class='smooth'>" + flmc + "</a>";
				sHtml += "</li>";
				jQuery("#" + PREFL_UL).append(sHtml);
				createNavSecond(flszid);// 生成二级导航
			} else {// 更多单独处理
				if (mkNum == PREFL_MKGS) {
					sHtml = "<li id='" + PREFL_LI + "more'>";
					sHtml += "<a href='javascript:;' class='smooth'>更多</a>";
					sHtml += "</li>";
					jQuery("#" + PREFL_UL).append(sHtml);
				}
				sHtmlMore += "<dd  style='text-align: left; text-indent: 1em;'>";
				sHtmlMore += "<a href='#" + PREFL_HREF + flszid
						+ "' class='smooth'>" + flmc + "</a>";
				sHtmlMore += "</dd>";
			}
			mkNum++;
		}
	}

	// more单独处理
	sHtmlMore = "<dl>" + sHtmlMore + "</dl>";
	sHtmlMore = "<div class='list_xxxx_downmenu' style='display: none;'>"
			+ sHtmlMore + "</div>";
	jQuery("#" + PREFL_LI + "more").append(sHtmlMore);
}

/*
 * 生成二级导航
 */
function createNavSecond(idFlag) {
	var sHtml = "";
	for ( var i = 0; i < flszList.length; i++) {
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flflszid = o.flflszid;// 父类id
		var flmc = o.flmc;// 分类名称
		if (flmc == null || flmc == "") {
			continue;
		}
		if (flflszid == idFlag) {// 为顶级类别
			sHtml += "<dd  style='text-align: left; text-indent: 1em;'>";
			sHtml += "<a href='#" + PREFL_HREF + flszid + "' class='smooth'>"
					+ flmc + "</a>";
			sHtml += "</dd>";
		}
	}
	if (sHtml != "") {
		sHtml = "<dl>" + sHtml + "</dl>";
		sHtml = "<div class='list_xxxx_downmenu' style='display: none;'>"
				+ sHtml + "</div>";
		jQuery("#" + PREFL_LI + idFlag).append(sHtml);
	}
}

/**
 * 生成内容导航
 * 
 * @return
 */
function createConNav() {
	var sHtml = "";
	sHtml += "<div class='position_xxxx after' >";
	sHtml += "<ul class='list_xxxx' id='nav_ul' >";
	sHtml += "</ul>";
	sHtml += "</div>";
	jQuery("#" + CONTENT_ID).append(sHtml);
	createConNavFirst();// 生成一级导航
}

/*
 * 生成一级内容导航
 */
function createConNavFirst() {
	var mkNum = 0;
	var sHtml = "";
	for ( var i = 0; i < flszList.length; i++) {
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flflszid = o.flflszid;// 父类id
		var flmc = o.flmc;// 分类名称
		var bdms = o.bdms;// 表单模式
		var bhmk = o.bhmk;// 包含模块
		var bdszz = o.bdszz;
		if (flflszid == null || flflszid == "") {// 为顶级类别
			sHtml = "<div id='" + PREFL + flszid + "'>";
			if(bdms == "4"){
				var bdszzObj = eval("[{" + bdszz + "}]")[0];
				var src = bdszzObj["src"];			
				sHtml += "<input type='hidden' id='" + src + "' name='" + src
						+ "'>";
			}
			if (bhmk == "1") {// 模块级
				sHtml += "<h3 class='college_title' style='margin-bottom:5px;'>";
				sHtml += "<span class='title_name'>" + flmc + "</span>";
				sHtml += "</h3>";
			} else {// 非模块
				sHtml += "<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'>";
				sHtml += "<thead>";
				sHtml += "<tr onclick='' style='cursor: hand;'>";
				sHtml += "<th colspan='5'>";
				sHtml += "<span>" + flmc;
				if (bdms == "4" && xs != true) {// 多条修改模式
					sHtml += "&nbsp;&nbsp;<a class='name' href='javascript:;' onclick='addConMoreUpdateTr(\""
							+ flszid + "\");'>增加一行</a>";
				}
				sHtml += "</span>";
				sHtml += "</th>";
				sHtml += "</tr>";
				sHtml += "</thead>";
				sHtml += "</table>";
			}
			sHtml += "<a name='" + PREFL_HREF + flszid + "'></a>";
			sHtml += "</div>";
			jQuery("#" + CONTENT_ID).append(sHtml);
			createConNavSecond(flszid);// 生成二级导航
		}
	}
}

/*
 * 生成二级内容导航
 */
function createConNavSecond(idFlag) {
	var sHtml = "";
	for ( var i = 0; i < flszList.length; i++) {
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flflszid = o.flflszid;// 父类id
		var flmc = o.flmc;// 分类名称
		var bdms = o.bdms;// 表单模式
		var bdszz = o.bdszz;
		if (flflszid == idFlag) {// 为顶级类别
			sHtml = "<div id='" + PREFL + flszid + "'>";
			if(bdms == "4"){
				var bdszzObj = eval("[{" + bdszz + "}]")[0];
				var src = bdszzObj["src"];
				sHtml += "<input type='hidden' id='" + src + "' name='" + src
						+ "'>";
			}
			if (flmc != null && flmc != "") {
				sHtml += "<table width='100%' border='0' style='margin-bottom: 5px' class='formlist'>";
				sHtml += "<thead>";
				sHtml += "<tr onclick='' style='cursor: hand;'>";
				sHtml += "<th colspan='5'>";
				sHtml += "<span>" + flmc;
				if (bdms == "4" && xs != true) {// 多条修改模式
					sHtml += "&nbsp;&nbsp;<a class='name' href='javascript:;' onclick='addConMoreUpdateTr(\""
							+ flszid + "\");'>增加一行</a>";
				}
				sHtml += "</span>";
				sHtml += "</th>";
				sHtml += "</tr>";
				sHtml += "</thead>";
				sHtml += "</table>";
			}
			sHtml += "<a name='" + PREFL_HREF + flszid + "'></a>";
			sHtml += "</div>";
			jQuery("#" + PREFL + idFlag).append(sHtml);
		}
	}
}

/**
 * 生成内容
 * 
 * @return
 */
function createCon() {
	var sHtml = "";
	for ( var i = 0; i < flszList.length; i++) {
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flflszid = o.flflszid;// 父类id
		var flmc = o.flmc;// 分类名称
		var bdms = o.bdms;// 表单模式 1:单条记录模式:2:多条记录模式;3:功能自定义代码
		var bdls = o.bdls;// 表单列数 为表单整体字段列数设置，不包含照片列(由程序自动计算)
		var bdszz = o.bdszz;// 表单设置值
		var gnlx = o.gnlx;// 功能类型 1:增加,2:修改,3:查看
		if (bdms == "1" && bdls != null) {
			createConDtjl(o);
		} else if (bdms == "2") {
			createConMore(o);
		} else if (bdms == "3") {
			jQuery("#" + PREFL + flszid).append(jQuery("#" + bdszz).html());
		} else if (bdms == "4") {
			createConMoreUpdate(o);
		}
	}
	createConDtjlZp();
}

/*
 * 单条记录模式
 */
function createConDtjl(flszObj) {
	var flszid = flszObj.flszid;// 分类设置id
	var sHtml = "";
	sHtml += "<table width='100%' border='0' style='margin-bottom: 5px' class='formlist' id='"
			+ PRECON_TABLE + flszid + "' >";
	sHtml += "<tbody >";
	sHtml += "</tbody>";
	sHtml += "</table>";
	jQuery("#" + PREFL + flszid).append(sHtml);
	createConDtjlTr(flszObj);// 生成单条记录模式tr
}

// 生成单条记录模式tr
function createConDtjlTr(flszObj) {
	var flszid = flszObj.flszid;// 分类设置id
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var curFlszid = o.flszid;// 分类设置id
		var bdmc = o.bdmc;// 表单名称
		var zd = o.zd;// 
		var szz = o.szz;//字段长度
		var zbwz = o.zbwz;//坐标位置
		var szls = o.szls;// 所占列数
		var zdlx = o.zdlx;// 字段类型
		var yxwk = o.yxwk;// 允许为空
		if (zdlx == "21") {// 照片类型单独处理
			continue;
		}
		
		if (flszid != curFlszid ) {
			continue;
		}
		
		if(zdlx == "25"){// 附件单独处理
			jQuery("#" + PRECON_TABLE + flszid).remove();
			jQuery("#" + szz).css("display","block");
			continue;
		}
		
		if (zbwz == null) {
			continue;
		}		
			
		var zbwzs = zbwz.split(",");
		var zbwzh = parseInt(zbwzs[0]);
		

		var colspanLen = 1;// 合并列数
		if (szls == null) {
			szls = 1;
		}
		
		szls = parseInt(szls);
		
		if (szls != null) {
			colspanLen = parseInt(szls) * 2  - 1;
		}
		
		if (yxwk == "0" ) {
			bdmc = "<span class='red'>*</span>" + bdmc;
		}
		
		if(zbwzs.length > 1){
			var zbwzl = zbwzs[1];
			var tableLength = jQuery("#" + PRECON_TABLE + flszid + " tr").length;
			if (tableLength < zbwzh) {
				for ( var j = 0; j < zbwzh - tableLength; j++) {
					jQuery("#" + PRECON_TABLE + flszid + " tbody").append(
							createConDtjlTrHtml(flszObj));
				}
			}
			var _conTr = jQuery("#" + PRECON_TABLE + flszid + " tr:eq("
					+ (zbwzh - 1) + ")");// 当前行tr
			var _conTh = _conTr.find("th:eq(" + (zbwzl - 1) + ")");
			_conTh.attr("name", PRECON_TH + zd);
			_conTh.html(bdmc);
			var _conTd = _conTr.find("td:eq(" + (zbwzl - 1) + ")");
			_conTd.attr("name", PRECON_TD + zd);
			/*if(zdlx == '32'){
				var html = createFjHtml(zd, flszid);
				var name = _conTd.attr("name");
				_conTd.html("aaaa");
				_conTd.append("<input type='text' value='111'/>");
				jQuery("td[name='zdybdcon_td_zyjnzs2']").append("<input type='text' value='111'/>");
			}*/
			if (colspanLen > 1) {
				var oldColspanLen = _conTd.attr("colSpan");
				var newColspanLen = colspanLen;
				if (oldColspanLen != null && parseInt(oldColspanLen) > 1) {
					newColspanLen += parseInt(oldColspanLen);
				}
				_conTd.attr("colSpan", newColspanLen);
				for ( var k = 0; k < colspanLen - 1; k++) {
					_conTd.next().remove();
				}
				if(colspanLen == 3){
					_conTd.attr("width", "85%");
				}
			}
		}else{
			var _tbody = jQuery("#" + PRECON_TABLE + flszid + " tbody");
			
			var _lastTh = _tbody.find("tr:last th:not([name^='"+PRECON_TH+"'])");
			var lastThLength =  _lastTh.length;
			if(lastThLength == 0 || szls > lastThLength){
				if(szls > lastThLength){
					var _prevTd = _lastTh.first().prev();
					
					var prevTdCol = _prevTd.attr("colSpan");
					if (prevTdCol == null) {
						prevTdCol = 1;
					}
					prevTdCol += lastThLength * 2;
					
					_prevTd.attr("colSpan", prevTdCol);
					_lastTh.next().remove();
					_lastTh.remove();
					
				}
				_tbody.append(createConDtjlTrHtml(flszObj));
				_lastTh = _tbody.find("tr:last th:not([name^='"+PRECON_TH+"'])");
			}

			var _conTh = _lastTh.first();
			_conTh.attr("name", PRECON_TH + zd);
			_conTh.html(bdmc);
			var _conTd = _conTh.next();
			_conTd.attr("name", PRECON_TD + zd);
			
			if(szls > 1){				
				_conTd.attr("colSpan", colspanLen);
				for ( var k = 0; k < colspanLen - 1; k++) {
					_conTd.next().remove();
				}
			}

		}						

	}
}

/*
 * 生成单条记录模式tr html
 */
function createConDtjlTrHtml(flszObj) {
	var key = "thtdkd";
	var bdls = flszObj.bdls;// 表单列数
	var bdszz = flszObj.bdszz;
	var thtdkdStrs = getJsonParam(bdszz,key);
	var thtdkdArr = null;
	if(thtdkdStrs != null && thtdkdStrs != ""){
		thtdkdArr = thtdkdStrs.split(",");
	}
	var thWidth = "";
	var tdWidth = "";
	if(thtdkdArr == null || thtdkdArr.length == 0){
		thWidth = "15%";
		tdWidth = "35%";
	}else if(thtdkdArr.length == 1){
		thWidth = thtdkdArr[0];
	}else if(thtdkdArr.length  >= 1 ){
		thWidth = thtdkdArr[0];
		tdWidth = thtdkdArr[1];
	}

	
	var sHtml = "";
	sHtml += "<tr>";
	for ( var i = 0; i < parseInt(bdls); i++) {
		sHtml += "<th";
		if(thWidth != ""){
			sHtml += " width='"+thWidth+"' ";
		}else if(thtdkdArr.length > i*2){
			sHtml += " width='"+thtdkdArr[i*2]+"' ";
		}
		sHtml += ">";
		sHtml += "&nbsp;";
		sHtml += "</th>";
		sHtml += "<td ";
		if(tdWidth != ""){
			sHtml += " width='"+tdWidth+"' ";
		}else if(thtdkdArr.length > i*2 + 1){
			sHtml += " width='"+thtdkdArr[i*2 + 1]+"' ";
		}
		sHtml += ">";
		sHtml += "&nbsp;";
		sHtml += "</td>";
	}
	sHtml += "</tr>";
	return sHtml;
}


/*
 * 获取配置中的json格式值
 */
function getJsonParam(jsonStr,key){
	var value = "";
	if (jsonStr == null || jsonStr == "") {
		return value;
	}
	if(key == null){
		return jsonStr;
	}
	var jsonObj = eval("[{" + jsonStr + "}]")[0];
	value = jsonObj[key];
	return value;
}


/*
 * 多条记录模式
 */

function createConMore(flszObj) {
	var flszid = flszObj.flszid;// 分类设置id
	var bdszz = flszObj.bdszz;// 表单设置值
	if (bdszz == null || bdszz == "") {
		return;
	}
	var bdszzObj = eval("[{" + bdszz + "}]")[0];
	var src = bdszzObj["src"];
	var srcList = valueJson[src];
	var zdStr = bdszzObj["zd"];// 字段列表
	var zdmcStr = bdszzObj["zdmc"];// 字段名称列表
	var bdkdStr = bdszzObj["bdkd"];// 表单宽度
	var zdArr = zdStr.split(",");
	var zdmcArr = zdmcStr.split(",");
	var bdkdArr = "";
	if(bdkdStr != null){
		bdkdArr = bdkdStr.split(",");
	}
	var sHtml = "";
	if(flszObj.bdls == '99'){
		
		sHtml+="<div  style='width:100%;overflow-x:auto;overflow-y:hidden;'>";	
	}
	sHtml += "<table width='100%' border='0' style='margin-bottom:5px' class='formlist'>";
	sHtml += "<tbody >";
	sHtml += "<tr>";
	for ( var i = 0; i < zdmcArr.length; i++) {
		var zdmc = zdmcArr[i];
		if(zdmc.indexOf("_fj") > -1){
			zdmc = zdmc.substr(0,zdmc.length-3);
		}
		sHtml += "<th";
		if(bdkdArr != null && bdkdArr.length > i && bdkdArr[i] != ""){
			sHtml += " width='"+bdkdArr[i]+"px' ";
		}
		sHtml += "><div align='center'>" + zdmc + "</div>";
		sHtml += "</th>";
	}
	sHtml += "</tr>";

	if (srcList == null || srcList.length == 0) {
		sHtml += "<tr>";
		sHtml += "<td colSpan='" + zdmcArr.length + "'>";
		sHtml += "<div align='center'>暂无数据！</div>";
		sHtml += "</td>";
		sHtml += "</tr>";
	} else {
		for ( var i = 0; i < srcList.length; i++) {
			var o = srcList[i];
			sHtml += "<tr>";
			for ( var j = 0; j < zdArr.length; j++) {
				var value = o[zdArr[j]];
				var zdmc = zdmcArr[j]
				if (value == null) {
				   	value = "&nbsp;";
				 }                
				if(zdmc.indexOf("_fj") > -1){
					//附件查看
					value = "<div id='commonfileupload-list-"+flszid+i+"' style='padding: 5px;'></div><script type='text/javascript'>" +
							"jQuery(function(){" +
							"var gid = '"+value+"';" +
							"jQuery.MultiUploader_q({" +
							"gid : gid," +
							"targetEl : 'commonfileupload-list-"+flszid+i+"'" +
							"});" +
							"});" +
							"</script>";
					/*value = "<div id='fjidDiv"+flszid+i+"'></div>"+
        					"<script type='text/javascript'>jQuery(function(){"+
        					"jQuery('#fjidDiv"+flszid+i+"').multiUploader_q({"+
        					"gid : '"+value+"',"+
        					"uid : '"+flszid+i+"' });});</script>"*/
				}
				sHtml += "<td align='center'>" + value + "</td>";
			}
			sHtml += "</tr>";
		}
	}
	sHtml += "</tbody>";
	sHtml += "</table>";
	if(flszObj.bdls == '99'){
		sHtml+="</div>";	
	}
	jQuery("#" + PREFL + flszid).append(sHtml);
	if(jQuery("#xxdm").val() == '12867'){
		if(srcList == null || srcList.length == 0){
			jQuery("#" + PREFL + flszid).hide();
		}
	}
}

/*
 * 多条记录模式，修改模式
 */
function createConMoreUpdate(flszObj) {
	var flszid = flszObj.flszid;// 分类设置id
	var bdls = flszObj.bdls;
	var sHtml = "";
	if(flszid=="xsxx_update_jtcyxxxg" || bdls == '99'){
		sHtml+="<div  style='width:100%;overflow-x:auto;overflow-y:hidden;'>";	
	}
	sHtml += "<table width='100%' border='0' style='margin-bottom: 5px' class='formlist' id='"
			+ PRECON_TABLE + flszid + "' >";
	sHtml += "<tbody >";
	sHtml += "</tbody>";
	sHtml += "</table>";
	if(flszid=="xsxx_update_jtcyxxxg" || bdls == '99'){
		sHtml+="</div>";	
	}
	jQuery("#" + PREFL + flszid).append(sHtml);
	createConMoreUpdateTr(flszObj);// 生成多条记录修改模式tr
}

/*
 * 多条记录模式，增加一行
 */
function addConMoreUpdateTr(flszid) {
	index++;
	var reg = /index/g;
	var trHtml = moreUpdateTrJson[flszid];
	if(trHtml != null){
		trHtml = trHtml.replace(reg,index);
	}
	jQuery("#" + PRECON_TABLE + flszid + " tbody").append(trHtml);
    if(flszid == "szdw_fdyxx_zyjszw" && flag != "ck"){
        var url = "szdw_fdyxx.do?method=getGwxx";
        var jTrHtml = jQuery(trHtml);
        var select = jQuery("select[name='zyjszwgwdjdm']").last();
        select.on("change",function(){
			var currSelect = jQuery(this);
			//岗位名称select
			var gwSelect = jQuery(this.parentElement.nextElementSibling.firstElementChild);
			var v = currSelect.val();
			jQuery.ajaxSetup({
				async:false
			});
			jQuery.post(url,{gwdjdm:v},function(data){
				gwSelect.empty();
				for(var i=0;i<data.length;i++){
					gwSelect.append("<option value='"+data[i]["dm"]+"'>"+data[i]["mc"]+"</option>");
				}
			},'json');
			jQuery.ajaxSetup({
				async:true
			});
		});

        select.trigger("change");
    }
}

// 生成多条记录修改模式tr
function createConMoreUpdateTr(flszObj) {
	var flszid = flszObj.flszid;// 分类设置id
	var bdszz = flszObj.bdszz;// 表单设置值

	var sHtml = "";
	var titleHtml = "";

	var flszZdJson = {};
	var zds = [];
	
	if (bdszz != null && bdszz != "") {
		var bdszzObj = eval("[{" + bdszz + "}]")[0];
		var src = bdszzObj["src"];
		var bzzd = bdszzObj["bzzd"];// 标志字段
		flszZdJson.src = src;
		flszZdJson.bzzd = bzzd;
		flszZdJson.flszid = flszid;
		flszZdJson.flmc = flszObj.flmc;
		flszZdJson.zds = zds;
		moreUpdateZdJson.push(flszZdJson);
	}

	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var curFlszid = o.flszid;// 分类设置id
		var bdmc = o.bdmc;// 表单名称
		var zd = o.zd;// 
		var szz = o.szz;
		var szlx = o.szlx;
		var zbwz = o.zbwz;
		var bdkd = o.bdkd;
		var zdlx = o.zdlx;// 字段类型
		var yxwk = o.yxwk;// 允许为空
		var yxxg = o.yxxg;
		if (zdlx == "21" || zdlx == "25") {// 照片类型、附件单独处理
			continue;
		}
		var tdHtml = "";
		if (flszid == curFlszid) {// 			
			zds.push(o);
			if (zdlx == "1") {
				titleHtml += "<th style='display:none;'><div></div></th>";
			} else {
				if (yxwk == "0" ) {
					bdmc = "<span class='red'>*</span>" + bdmc;
				}
				titleHtml += "<th><div align='center'>" + bdmc + "</div></th>";
			}
			if(yxxg == "0"){
				tdHtml += createHiddenNoIdHtml(zd, "");
			}else{
				if (zdlx == "11") {
					o.includeId = false;
					tdHtml += createTextHtmlByObj(o);
				} else if (zdlx == "13") {
					tdHtml += createDateTextHtml(zd, szz, bdkd);
				} else if (zdlx == "1") {
					tdHtml += createHiddenNoIdHtml(zd, "");
				} else if (zdlx == "2") {
					o.includeId = false;
					tdHtml += createSelectHtmlByObj(o);
				} else if (zdlx == "3") {
					tdHtml += createRadioHtml(zd, szz);
				} else if (zdlx == "4") {
					tdHtml += createCheckboxHtml(zd, szz);
				}else if (zdlx == "32") {
					tdHtml += createFjHtml(zd, curFlszid);
				}
				
			}
			if (zdlx == "1") {
				sHtml += "<td style='display:none;'>" + tdHtml + "</td>";
			} else if(zdlx == "32"){
				sHtml += "<td align='left' style='width:50px;'>" + tdHtml + "</td>";
			}else {
				sHtml += "<td align='center'>" + tdHtml + "</td>";
			}
		}
	}

	if (titleHtml != "") {
		if (xs != true) {
			titleHtml += "<th><div align='center' style='width:35px;'>操作</div></th>";
		}
		titleHtml = "<tr>" + titleHtml + "</tr>";
		jQuery("#" + PRECON_TABLE + flszid + " tbody").append(titleHtml);
	}

	if (sHtml != "") {
		if (xs != true) {
			sHtml += "<td align='center'><a class='name' href='javascript:;' onclick=\"deleteTr(this,'"+flszid+"')\">删除</a></td>";
		}
		sHtml = "<tr  name='" + PRECON_MORE_TR + "'>" + sHtml + "</tr>";
		moreUpdateTrJson[flszid] = sHtml;
	}
}

/*
 * 删除一行
 */
var jtcyDelArr = [];
function deleteTr(obj,flszid) {
    var tr = jQuery(obj).parents("tr[name='" + PRECON_MORE_TR + "']");
	tr.remove();
	//家庭成员信息删除时插入历史表
	if("xsxx_update_jtcyxxxg" == flszid){
		var guid = tr.find("input[name='guid']").eq(0).val();
		if(guid != ""){
			var input = tr.find("input");
			var select = tr.find("select");

            var zdsArr = [];
            var zdJson = {};
            var flag = false;
            input.each(function(){
            	var zd = jQuery(this).attr("name");
            	var val = jQuery(this).val();
            	if(val != "") flag=true;
                zdJson[zd] = val;
			});
            select.each(function(){
                var zd = jQuery(this).attr("name");
                var val = jQuery(this).val();
                if(val != "") flag=true;
                zdJson[zd] = val;
            });
            if(!flag) return;
            jtcyDelArr.push(zdJson);
            var dataJson = {};
            dataJson.data = jtcyDelArr;
			jQuery("#jtcyDelList").val(JSON.stringify(dataJson));
        }

	}
}

/**
 * 设置值
 * 
 * @return
 */
function setValue() {
	var flszFirstObj = flszList[0];
	var gnlx = flszFirstObj.gnlx;// 功能类型型 1:增加 2:修改 3:查看
	if (gnlx == "3") {
		if (valueJson != null) {
			for ( var i = 0; i < zddyList.length; i++) {
				var o = zddyList[i];
				var zd = o.zd;// 
				var zdlx = o.zdlx;
				var szz = o.szz;
				var zdValue = valueJson[zd];
				if (zdValue == null) {
					zdValue = "";
				}
				var _zdTd = jQuery("td[name='" + PRECON_TD + zd + "']");

				if (zdlx == "0") {
					if (_zdTd.length > 0 && szz != null) {
						_zdTd.html(szz + "&nbsp;");
					}
				}else if (zdlx == "22") {
					if (zdValue == "") {
						continue;
					} else {
						var ssxDms = getSsx(szz, zdValue);
						if (ssxDms != null && ssxDms["shdm"] != null) {
							zdValue = ssxDms['shmc'] + ssxDms['shimc']
									+ ssxDms['ximc'];
						}
					}
					_zdTd.html(zdValue);
				} else if (zdlx == "5") {
					if (_zdTd.length > 0) {
						zdValue = htmlJsEncode(zdValue);
						_zdTd.html(zdValue + "&nbsp;");
					}
				} else {
					if (_zdTd.length > 0) {
						_zdTd.html(zdValue + "&nbsp;");
					}
				}
			}
		}
	} else {
		setValueUpdateZd();// 字段设置
		setValueUpdateCheck();// 字段验证
		setValueUpdateTip();// 增加提示信息
		if (gnlx == "2") {
			setValueUpdate();// 字段赋值
			setValueUpdateMore();// 字段赋值,多条记录模式
		}
	}
}

/*
 * 修改功能，字段赋值
 */
function setValueUpdate() {
	if (valueJson != null) {
		for ( var i = 0; i < zddyList.length; i++) {
			var o = zddyList[i];
			var zd = o.zd;// 
			/*
			 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 13:日期文本框 21:照片
			 * 22:省市县选择 23:链接 24;学院专业班级 99:功能自定义
			 */
			var zdlx = o.zdlx;
			var szlx = o.szlx;
			var szz = o.szz;
			var yxxg = o.yxxg;
			var bdms = o.bdms;
			if (bdms == "4") {// 多条记录模式，另外处理
				continue;
			}
			if (zdlx == "99") {
				continue;
			}
			
			var zdValue = valueJson[zd];
			if (zdValue == null) {
				zdValue = "";
			}
			if (yxxg == "0") {
				jQuery("#" + zd).val(zdValue);
				var _zdTd = jQuery("td[name='" + PRECON_TD + zd + "']");
				if (_zdTd.length > 0) {
					if (zdlx == "2" || zdlx == "3" || zdlx == "4") {
						_zdTd.prepend(getNameByDm(zdValue, szz));
					} else if (zdlx == "22") {
						if (zdValue != "") {
							var ssxDms = getSsx(szz, zdValue);
							if (ssxDms != null && ssxDms['shmc'] != null) {
								_zdTd.prepend(ssxDms['shmc'] + ssxDms['shimc']
										+ ssxDms['ximc']);
							}
						}

					} else if (zdlx == "24") {
						var mc = zdValue;
						if (zd == "nj") {
							mc = valueJson["nj"];
						} else if (zd == "xydm") {
							mc = valueJson["xymc"];
						} else if (zd == "zydm") {
							mc = valueJson["zymc"];
						} else if (zd == "bjdm") {
							mc = valueJson["bjmc"];
						} else if (zd == "zybj"){
                            mc = valueJson["zybjmc"];
						}
						_zdTd.prepend(mc);
					} else {
						_zdTd.prepend(zdValue);
					}
				}
			} else {
				if (zdlx == "3") {
					jQuery(
							"#" + CONTENT_ID + " [name='" + zd + "'][value='"
									+ zdValue + "']")
							.attr("checked", "checked");
				} else if (zdlx == "4") {
					//遍历值,对选中的值打勾
					jQuery("#" + CONTENT_ID + " [name='" + zd + "']").each(function(i){
						var zdValueArr = zdValue.split(",");
						for ( var i = 0; i < zdValueArr.length; i++) {
							var zddgValue = zdValueArr[i];
							if(zddgValue == this.value){
								jQuery(this).attr("checked","checked");
								break;
							}
						}
					})
				} else if (zdlx == "22") {
					if (zdValue != "") {
						var ssxDms = getSsx(szz, zdValue);
						if (ssxDms != null) {
							var shdm = ssxDms['shdm'];
							var shidm = ssxDms['shidm'];
							var xidm = ssxDms['xidm'];
							if(shdm != ""){
								jQuery("#" + zd + "_province").val(shdm);
								jQuery("#" + zd + "_province").change();
							}
							if(shidm != ""){
								jQuery("#" + zd + "_city").val(shidm);
								jQuery("#" + zd + "_city").change();
							}
							if(xidm != ""){
								jQuery("#" + zd + "_locale").val(xidm);
								jQuery("#" + zd + "_locale").change();
							}
						}
					}
				} else if (zdlx == "24") {
					if (zd == "nj") {
						if (valueJson["nj"] != null) {
							jQuery("#" + zd).val(zdValue);
							jQuery("#nj").val(valueJson["nj"]);
						}
					} else if (zd == "xydm") {
						if (valueJson["xymc"] != null) {
							jQuery("#" + zd).val(zdValue);
							jQuery("#xymc").val(valueJson["xymc"]);
						}
					} else if (zd == "zydm") {
						if (valueJson["zymc"] != null) {
							jQuery("#" + zd).val(zdValue);
							jQuery("#zymc").val(valueJson["zymc"]);
						}
					} else if (zd == "bjdm") {
						if (valueJson["bjmc"] != null) {
							jQuery("#" + zd).val(zdValue);
							jQuery("#bjmc").val(valueJson["bjmc"]);
						}
					} else if(zd == "zybj"){
                        if (valueJson["zybjmc"] != null) {
                            jQuery("#" + zd).val(zdValue);
                            jQuery("#zybjmc").val(valueJson["zybjmc"]);
                        }
					}
				} else if (zdlx == "1" || zdlx == "2" || zdlx == "5"
						|| zdlx == "11" || zdlx == "13" || zdlx == "25") {
					jQuery("#" + zd).val(zdValue);
				} else {
					var _zdTd = jQuery("td[name='" + PRECON_TD + zd + "']");
					_zdTd.append(zdValue + "&nbsp;");
				}
			}
		}
	}
}

/*
 * 修改功能，字段赋值，多条记录模式
 */
function setValueUpdateMore() {
	if (valueJson != null) {
		for ( var i = 0; i < flszList.length; i++) {
			var o = flszList[i];
			var flszid = o.flszid;// 分类设置id
			var flmc = o.flmc;// 分类名称
			var bdms = o.bdms;// 表单模式
			var bdszz = o.bdszz;//
			if (bdms != "4") {
				continue;
			}
			var bdszzObj = eval("[{" + bdszz + "}]")[0];
			var src = bdszzObj["src"];
			var srcList = valueJson[src];
			if (srcList == null) {
				continue;
			}
			for ( var j = 0; j < srcList.length; j++) {
				var srcObj = srcList[j];
				setValueUpdateMoreTr(flszid, srcObj);
			}
		}
	}
}

/*
 * 多条记录模式，一行赋值
 */
var index1 = 0;
function setValueUpdateMoreTr(flszid, srcObj) {
	addConMoreUpdateTr(flszid);// 增加一行
	for ( var i = 0; i < moreUpdateZdJson.length; i++) {
		var o = moreUpdateZdJson[i];
		if (flszid != o.flszid) {
			continue;
		}
		var zds = o.zds;
		for ( var j = 0; j < zds.length; j++){
			var zdObj = zds[j];
			var zd = zdObj.zd;
			var zdlx = zdObj.zdlx;
			var szz = zdObj.szz;
			var _zd = jQuery("#" + PRECON_TABLE + flszid + " tbody tr:last").find(
					"[name='" + zd + "']");
			var dm = srcObj[zd];
			if(_zd.attr("type") == "hidden") {
				var mc = "";
				if(zdlx == "2") {
					mc = getNameByDm(dm, szz);
				} else {
					mc = dm;
				}
				if(zdlx != "32"){
					_zd.after(mc)
				} else {
					if(flag != "xg"){
						index1++;
						/*var value = "<div id='commonfileupload-list-"+index1+"' style='padding: 5px;width:130px;'></div><script type='text/javascript'>" +
								"jQuery(function(){" +
								"var gid = '"+mc+"';" +
								"jQuery.MultiUploader_q({" +
								"gid : gid," +
								"targetEl : 'commonfileupload-list-"+index1+"'" +
								"});" +
								"});" +
								"</script>";*/

						var value = "<div id='fjidDiv"+index1+"'></div>"+
                        			"<script type='text/javascript'>jQuery(function(){"+
                        			"jQuery('#fjidDiv"+index1+"').multiUploader_q({"+
                                    "gid : '"+mc+"',"+
                                    "uid : '"+index1+"' });});</script>"
                               
						_zd.after(value);
						_zd.css('text-align','left');
						
					}
				};
			}
			/*if(flag != "xg" && zdlx == "32"){
				//附件查看
				index1++;
				var value = "<div id='commonfileupload-list-"+index1+"' style='padding: 5px;'></div><script type='text/javascript'>" +
						"jQuery(function(){" +
						"var gid = '"+dm+"';" +
						"jQuery.MultiUploader_q({" +
						"gid : gid," +
						"targetEl : 'commonfileupload-list-"+i+"'" +
						"});" +
						"});" +
						"</script>";
				var value = "<div id='fjidDiv"+index1+"'></div>"+
    					"<script type='text/javascript'>jQuery(function(){"+
    					"jQuery('#fjidDiv"+index1+"').multiUploader_q({"+
    					"gid : '"+mc+"',"+
    					"uid : '"+index1+"' });});</script>"
    					_zd.after(value);
			}else{
				_zd.val(dm);
			}*/
            _zd.val(dm);
            if(flszid == "szdw_fdyxx_zyjszw" && flag != "ck" && zd == "zyjszwgwdjdm"){
                _zd.trigger("change");
			}

		}
	}
}

/*
 * //字段设置
 */
function setValueUpdateZd() {
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var zd = o.zd;// 
		var zdlx = o.zdlx;// 字段类型
		var bdmc = o.bdmc;// 表单名称
		var szz = o.szz;// 设置值
		var szls = o.szls;// 所占列数
		var szlx = o.szlx;// 设置类型
		/*
		 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 12:数字文本框 13:日期文本框 32:附件
		 * 21:照片 22:省市县选择 23:链接 99:功能自定义
		 */
		var zdlx = o.zdlx;// 
		var yxwk = o.yxwk;// 允许为空
		var yxxg = o.yxxg;// 允许修改
		var _zdTd = jQuery("td[name='" + PRECON_TD + zd + "']");
		if (_zdTd.length > 0) {
			var sHtml = "";
			if(zdlx == "0" && szz != null){
				sHtml += szz;
			}else if (yxxg == "0") {
				sHtml += createHiddenHtml(zd, szz);
			} else {
				if (zdlx == "99" || zdlx == "21") {
					sHtml += jQuery("#" + szz).html();
					jQuery("#" + szz).remove();
				} else if (zdlx == "11") {
					sHtml += createTextHtml(zd, szz);
				} else if (zdlx == "12") {
					sHtml += createSzTextHtml(zd, szz);
				} else if (zdlx == "13") {
					sHtml += createDateTextHtml(zd, szz);
				} else if (zdlx == "1") {
					sHtml += createHiddenHtml(zd, szz);
				} else if (zdlx == "2") {
					sHtml += createSelectHtml(zd, szz, szlx);
				} else if (zdlx == "3") {
					sHtml += createRadioHtml(zd, szz);
				} else if (zdlx == "4") {
					sHtml += createCheckboxHtml(zd, szz);
				} else if (zdlx == "5") {
					sHtml += createTextareaHtml(zd, szz);
				} else if (zdlx == "6") {

				} else if (zdlx == "22") {// 22:省市县选择
					sHtml += createSsxHtml(zd, szz);
				} else if (zdlx == "23") {

				} else if (zdlx == "25") {// 25:附件

				} else if (zdlx == "24") {
					sHtml += createXyzybjHtml(zd, szz);
				}
			}
			_zdTd.html(sHtml);
			if (zdlx == "22") {// 22:省市县选择
				setSsxLiandong(zd, szz);
			}
		}
	}
}

/*
 * 保存，增加字段的验证信息
 */
function setValueUpdateCheck() {
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var zd = o.zd;
		var bdmc = o.bdmc;
		var szlx = o.szlx;
		var szz = o.szz;
		var yxwk = o.yxwk;
		/*
		 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 12:数字文本框 13:日期文本框
		 * 21:照片 22:省市县选择 23:链接 99:功能自定义
		 */
		var zdlx = o.zdlx;// 

		/*
		 * 设置类型 当“字段类型”为：2:下拉框、3:单选框、4:复选框三种类型时，来源类型字段有效 1.固定值，格式为：1:男,2:女
		 * 2:数据库取值,“表名:代码,名称”, 3:类名全称#方法名|参数:代码,名称，其中，若有参数，则参数仅支持一个String类型；
		 * 字段类型为输入框时，做为验证类型配置： 11:数字、字母、下划线 12:仅字母 13:仅数字 14:小数 15:字母+空格 21:邮箱 22:电话号码
		 * 23:手机号码 24:身份证号 25:邮编 99:正则表达式
		 * 31:仅自然数
		 */
		if (zdlx == "11" || zdlx == "5") {
			var tsxs = bdmc;
			var _zd = jQuery("[name='" + zd + "']");
			if (szlx == "13") {
				_zd.blur(function() {
					limitSz(this);
					if("11842" == jQuery("#xxdm").val()){
						checkyhkh(this,"yhdm","yhkh");
					}
				});
				_zd.keyup(function() {
					limitSz(this);
				});
				
			} else if (szlx == "11") {
				_zd.blur(function() {
					limitSzZmXhx(this);
				});
				_zd.keyup(function() {
					limitSzZmXhx(this);
				});
			} else if (szlx == "14") {
				_zd.blur(function() {
					limitXsXhx(this);
				});
				_zd.keyup(function() {
					limitXsXhx(this);
				});
			} else if (szlx == "22") {
				_zd.blur(function() {
					// checkDhhm(this, tsxs);
					});
			}else if (szlx == "31") {
				_zd.blur(function() {
					limitSz(this);
					if(jQuery(this).val()!=""){
						jQuery(this).val(parseInt(jQuery(this).val()));//去0
					}
				});
				_zd.keyup(function() {
					limitSz(this);
				});
			}
			var max = getMaxLength(szz);
			if (max > 0) {
				if (zdlx == "5") {
					var sHtml = "<br/><font color='red'>(限" + max
							+ "字以内)</font>";
					if (xs != true) {
						jQuery("[name='" + PRECON_TH + zd + "']").append(sHtml);
					}
				} else {
					_zd.attr("maxlength", max);
				}
			}
		}
	}
}
/*
 * 增加提示信息
 */
function setValueUpdateTip() {
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var zd = o.zd;
		var zdsm = o.zdsm;
		/*
		 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 12:数字文本框 13:日期文本框
		 * 21:照片 22:省市县选择 23:链接 99:功能自定义
		 */
		var zdlx = o.zdlx;// 
		var tslx = o["tslx"];
		if(zdsm == null || jQuery.trim(zdsm) == ""){
			continue;
		}
		
		var sHtml = "";
		
		if(tslx == "1"){
			sHtml += "<span style='color: #4a74a1;'>";
			sHtml += zdsm;
			sHtml += "</span>";
		}else if(tslx == "2"){
			sHtml += "<div style='color: #4a74a1;'>";
			sHtml += zdsm;
			sHtml += "</div>";
		}else{
			sHtml += "<span style='margin-left:0px;margin-top: 0px'>";
			sHtml += "<img src='"+stylePath+"images/blue/ico_79.gif' style='width: 16px;height: 16px;' title='"+zdsm+"' />";
			sHtml += "</span>";
		}
		if (zdlx == "2" || zdlx == "5" || zdlx == "11" || zdlx == "12" || zdlx == "13"|| zdlx == "22"|| zdlx == "24") {
			var _zd = jQuery("#" + zd).parents("td[name='"+PRECON_TD+zd+"']");
			_zd.append(sHtml);
		}
		
		if (zdlx == "3" || zdlx == "4") {
			var _zd = jQuery("#" + CONTENT_ID + " [name='" + zd + "']").parents("td[name='"+PRECON_TD+zd+"']");
			_zd.append(sHtml);
		}
	}
}


/*
 * 增加照片列
 */
function createConDtjlZp() {
	// zpzdObjArr
	for ( var i = 0; i < zddyList.length; i++) {
		var sHtml = "";
		var o = zddyList[i];
		var zdlx = o.zdlx;
		var szlx = o.szlx;
		var szz = o.szz;
		var flszid = o.flszid;
		var szls = o.szls;
		if (szls == null || szls == "") {
			szls = 1;
		}

		if (zdlx == "21") {
			sHtml += "<td rowspan='" + szls + "'>";
			sHtml += jQuery("#" + szz).html();
			jQuery("#" + szz).remove();
			sHtml += "</td>";
			jQuery("#" + PRECON_TABLE + flszid + ">tbody>tr:first").append(
					sHtml);

			jQuery(
					"#" + PRECON_TABLE + flszid + ">tbody>tr:gt("
							+ eval(parseInt(szls) - 1) + ")").find("td:last")
					.each(
							function(index) {
								var oldColspan = jQuery(this).attr("colSpan");
								if (oldColspan == null || oldColspan == "") {
									oldColspan = 1;
								}
								jQuery(this).attr("colSpan",
										eval(parseInt(oldColspan) + 1));
							});
		}
	}
}
// 检查家庭成员是否有必填字段
function checkJtcyxxxgSfbt(){
	var jtcyxxxgSfbt = false; 
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var flszid = o.flszid;
		var yxwk = o.yxwk;
		if(flszid == 'xsxx_update_jtcyxxxg' && yxwk == "0"){
			jtcyxxxgSfbt = true; 
		}
	}
	return jtcyxxxgSfbt;
}
//仅仅获取表单修改值，不验证
function getXgzd(){
    var sHtml = "";
    /*for ( var i = 0; i < zddyList.length; i++) {
        var o = zddyList[i];
        var zd = o.zd;
        var bdmc = o.bdmc;
        var zdlx = o.zdlx;
        var szlx = o.szlx;
        var szz = o.szz;
        var bdms = o.bdms;
        var yxxg = o.yxxg;
        if(bdms == "4"){// 多条记录修改模式，单独处理
            continue;
        }
        if(zdlx == null || zdlx == "" || zdlx == "1" || zdlx == "0"){
            continue;
        }
        if(yxxg == "0"){
            continue;
        }

        var value = jQuery.trim(jQuery("#" + zd).val());
        // var result = checkZdybdYxwk(o);// 非空验证
        if (!(jQuery("#" + zd).is(":hidden") && zdlx != "22") && !(jQuery("#" + zd).val() == undefined && zdlx == "5")) {
            showAlert(result.message);
            if (zdlx != "13"){
                jQuery("input[id="+zd+"]").focus();
            }
            jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
            jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
            return false;
        }

        if(zdlx == "4"){
            var checkBoxValue = "";

            jQuery("input[name='" + zd + "']:checked").each(function(){
                checkBoxValue += jQuery(this).val()+ "," ;
            });
            if(checkBoxValue.length > 0){
                checkBoxValue= checkBoxValue.substring(0, checkBoxValue.length - 1);
            }
            jQuery("[name='" + PRECON_TH + zd + "']").append(createHiddenHtml(zd,checkBoxValue));
        }

        if ((zdlx != "5" && zdlx != "11") || value == "") {// 文本框，文本域
            continue;
        }

        result = checkZdybd(o);// 格式验证
        if (!result.success) {
            showAlert(result.message);
            jQuery("input[id="+zd+"]").focus();
            jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
            jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
            return false;
        }

        result = checkZdybdLength(o);// 长度验证
        if (!result.success) {
            showAlert(result.message);
            jQuery("input[id="+zd+"]").focus();
            jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
            jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
            return false;
        }
    }*/


    var sHtml = "";
    var returnRs = true;
    for ( var i = 0; i < moreUpdateZdJson.length; i++) {
        var o = moreUpdateZdJson[i];
        var flszid = o.flszid;
        var flmc = o.flmc;
        var zds = o.zds;
        var src = o.src;
        var zdsArr = [];
        var dataJson = {};
        dataJson.data = zdsArr;
        jQuery("#" + PRECON_TABLE + flszid + " tbody tr:gt(0)").each(
            function(index) {
                var pretip = flmc + "：第" +(index + 1) + "行";

                var zdJson = {};
                var flag = false;
                for ( var j = 0; j < zds.length; j++) {
                    var zdObj = zds[j];
                    var zd = zdObj.zd;
                    var zdlx = zdObj.zdlx;
                    var value = jQuery.trim(jQuery(this).find("td:visible [name='" + zd + "']")
                        .val());
                    // 格式验证///////////////////////////
                    if(value == null){
                        value = "";
                    }
                    /*result = checkZdybdYxwk(zdObj,value);// 非空验证
                    if (!result.success) {
                        showAlert(pretip + result.message);
                        returnRs = false;
                        return false;
                    }*/
                   /* if ((zdlx == "5" || zdlx == "11") && value != "") {// 文本框，文本域
                        result = checkZdybd(zdObj,value);// 格式验证
                        if (!result.success) {
                            showAlert(pretip + result.message);
                            returnRs = false;
                            return false;
                        }

                        result = checkZdybdLength(zdObj,value);// 长度验证
                        if (!result.success) {
                            showAlert(pretip + result.message);
                            returnRs = false;
                            return false;
                        }
                    }*/

                    /*if(zdlx == "32"){
                        //jQuery
                    }*/

                    // ////////////////////////////////////////
                    if(value != null && value != ""){
                        flag = true;
                    }
                    zdJson[zd] = value;
                }
                if(flag){// 若一行值均为空，则不保存
                    zdsArr.push(zdJson);
                }
            });
        jQuery("#" + src).val(JSON.stringify(dataJson));// 数据保存
    }

    return returnRs;
}
/**
 * 自定义表单验证
 * 
 * @return
 */
function zdybdCheck() {
	var sHtml = "";
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var zd = o.zd;
		var bdmc = o.bdmc;
		var zdlx = o.zdlx;
		var szlx = o.szlx;
		var szz = o.szz;
		var bdms = o.bdms;
		var yxxg = o.yxxg;
		if(bdms == "4"){// 多条记录修改模式，单独处理
			continue;
		}
		if(zdlx == null || zdlx == "" || zdlx == "1" || zdlx == "0"){
			continue;
		}
		if(yxxg == "0"){
			continue;
		}
		
		var value = jQuery.trim(jQuery("#" + zd).val());
		var result = checkZdybdYxwk(o);// 非空验证
		if (!result.success && !(jQuery("#" + zd).is(":hidden") && zdlx != "22") && !(jQuery("#" + zd).val() == undefined && zdlx == "5")) {
			showAlert(result.message);
			if (zdlx != "13"){
				jQuery("input[id="+zd+"]").focus();
			}
			jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
			jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
			return false;
		}

		if(zdlx == "4"){
			var checkBoxValue = "";
	
			jQuery("input[name='" + zd + "']:checked").each(function(){
				checkBoxValue += jQuery(this).val()+ "," ;
			});
			if(checkBoxValue.length > 0){
				checkBoxValue= checkBoxValue.substring(0, checkBoxValue.length - 1);
			}
			jQuery("[name='" + PRECON_TH + zd + "']").append(createHiddenHtml(zd,checkBoxValue));
		}
		
		if ((zdlx != "5" && zdlx != "11") || value == "") {// 文本框，文本域
			continue;
		}

		result = checkZdybd(o);// 格式验证
		if (!result.success) {
			showAlert(result.message);
			jQuery("input[id="+zd+"]").focus();
			jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
			jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
			return false;
		}

		result = checkZdybdLength(o);// 长度验证
		if (!result.success) {
			showAlert(result.message);
			jQuery("input[id="+zd+"]").focus();
			jQuery("input[id="+zd+"]").css("border", "4px solid #15bcaa");
			jQuery("input[id="+zd+"]").keyup(function(){jQuery("input[id="+zd+"]").removeAttr("style");});
			return false;
		}
	}

	// 自定义函数调用执行
	var flszFirstObj = flszList[0];
	var yzsz = flszFirstObj.yzsz;
	if (yzsz != null) {
		try {
			eval(yzsz + "()");
		} catch (ex) {
		}
	}

	// 多条修改模式记录值处理
	if(!dealConMoreValue()){
		return false;
	}

	return true;
}

/*
 * /多条修改模式记录值处理
 */
function dealConMoreValue() {
	var sHtml = "";
	var returnRs = true;
	for ( var i = 0; i < moreUpdateZdJson.length; i++) {
		var o = moreUpdateZdJson[i];
		var flszid = o.flszid;
		var flmc = o.flmc;
		var zds = o.zds;
		var src = o.src;
		var zdsArr = [];
		var dataJson = {};
		dataJson.data = zdsArr;
		jQuery("#" + PRECON_TABLE + flszid + " tbody tr:gt(0)").each(
				function(index) {
					var pretip = flmc + "：第" +(index + 1) + "行";
					var zdJson = {};
					var flag = false;
					for ( var j = 0; j < zds.length; j++) {
						var zdObj = zds[j];
						var zd = zdObj.zd;
						var zdlx = zdObj.zdlx;
						var value = jQuery.trim(jQuery(this).find("td:visible [name='" + zd + "']")
								.val());
                        //家庭成员信息加入guid的值
                        if(flszid == "xsxx_update_jtcyxxxg" && zd == "guid"){
                           value = jQuery.trim(jQuery(this).find("input[name='" + zd + "']").val());

                        }
						// 格式验证///////////////////////////
						if(value == null){
							value = "";
						}
						result = checkZdybdYxwk(zdObj,value);// 非空验证
						if (!result.success) {
							showAlert(pretip + result.message);
							returnRs = false;
							return false;
						}
						if ((zdlx == "5" || zdlx == "11") && value != "") {// 文本框，文本域
							result = checkZdybd(zdObj,value);// 格式验证
							if (!result.success) {
								showAlert(pretip + result.message);
								returnRs = false;
								return false;
							}
	
							result = checkZdybdLength(zdObj,value);// 长度验证
							if (!result.success) {
								showAlert(pretip + result.message);
								returnRs = false;
								return false;
							}
						}

						if(zdlx == "32"){
							//jQuery
						}
						
						// ////////////////////////////////////////
						if(value != null && value != ""){
							flag = true;
						}
						zdJson[zd] = value;
					}
					if(flag){// 若一行值均为空，则不保存
						zdsArr.push(zdJson);
					}
				});
		jQuery("#" + src).val(JSON.stringify(dataJson));// 数据保存
	}
	
	return returnRs;
}

/**
 * 用户申请修改字段的显示
 * 
 * @param data
 * @return
 */
function zdybdXgzd(data,flag) {
	if (data == null || data == "") {
		return;
	}
	zdybdXgzdDt(data);// 单条记录单个
	zdybdXgzdMore(data,flag);// 多条记录修改模式
}

function zdybdXgzdDt(data) {
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		var zd = o.zd;
		var zdz = o.zdz;
		var xgqz = o.xgqz;
		var _zdTd = jQuery("td[name='" + PRECON_TD + zd + "']");
		var oldValue = xgqz;
		if (oldValue == null) {
			oldValue = "";
		}
		var newValue = zdz;
		if (newValue == null) {
			newValue == "";
		}
		/*
		 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 13:日期文本框 21:照片
		 * 22:省市县选择 23:链接 24;学院专业班级 99:功能自定义
		 */
		var zdflg  = false;
		for ( var j = 0; j < zddyList.length; j++) {
			var zddyObj = zddyList[j];
			var zddyZd = zddyObj.zd;//
			var zddySzz = zddyObj.szz;
			var zddyZdlx = zddyObj.zdlx;//
			if (zddyZd == zd) {
				if (zddyZdlx == "2" || zddyZdlx == "3" || zddyZdlx == "4") {
					newValue = getNameByDm(zdz, zddySzz);
					oldValue = getNameByDm(oldValue, zddySzz);
				} else if (zddyZdlx == "22") {
					var ssxDms;
					if (newValue != null && newValue != "") {
						ssxDms = getSsx(zddySzz, newValue);
						if (ssxDms != null && ssxDms['shmc'] != null) {
							newValue = ssxDms['shmc'] + ssxDms['shimc']
									+ ssxDms['ximc'];
						}
					}
					if (oldValue != "") {
						ssxDms = getSsx(zddySzz, oldValue);
						if (ssxDms != null && ssxDms['shmc'] != null) {
							oldValue = ssxDms['shmc'] + ssxDms['shimc']
									+ ssxDms['ximc'];
						}
					}
				} else if (zddyZdlx == "6") {
					jQuery("#" + zd + "_fj").val(newValue);
				}
				zdflg  = true;
				break;
			}
		}
		if (oldValue == null) {
			oldValue = "";
		}
		if (newValue == null) {
			newValue = "";
		}
		if (oldValue != newValue) {
			if (oldValue == "") {
				oldValue = "空";
			}
			if (newValue == "") {
				newValue = "空";
			}
			if(zddyZdlx == "5" && zdflg){
				newValue =  htmlJsEncode(newValue);
			}
			var title = "修改前信息为：" + oldValue;
			var newTip = "<a href='javascript:void(0);' >";
			newTip += "<font color='red'  title='" + title + "' alt='" + title
					+ "' >" + newValue + "</font></a>";
			_zdTd.html(newTip);
		}
	}
}

/*
 * 多条记录修改模式
 */
function zdybdXgzdMore(data,flag) {
	for ( var i = 0; i < moreUpdateZdJson.length; i++) {
		var o = moreUpdateZdJson[i];
		var src = o.src;
		for ( var j = 0; j < data.length; j++) {
			var xgzdObj = data[j];
			var xgzd = xgzdObj.zd;
			if(src != xgzd){
				continue;
			}
			zdybdXgzdMoreSz(o,xgzdObj,flag);//
		}
	}
}

/*
 * 多条记录修改模式值设置
 */
function zdybdXgzdMoreSz(moreTrObj,xgzdObj,flag){
	var flszid = moreTrObj.flszid;
	var src = moreTrObj.src;
	var bzzd = moreTrObj.bzzd;
	var zds = moreTrObj.zds;
	
	var xgzd = xgzdObj.zd;
	var xgzdZdz = xgzdObj.zdz;
	var xgzdXgqz = xgzdObj.xgqz;
	
	jQuery("#" + PRECON_TABLE + flszid + " tbody tr:gt(0)").remove();
	for ( var i = 0; i < xgzdXgqz.length; i++) {
		var srcObj = xgzdXgqz[i];
		setValueUpdateMoreTr(flszid, srcObj, flag);
	}
	
	var tdLength = jQuery("#" + PRECON_TABLE + flszid + " tbody tr:first").find(
	"th:visible").length;
	var tipTr = "<tr><td colspan='"+tdLength+"'><font color='red'>以下为修改后的记录</font></td></tr>";
	jQuery("#" + PRECON_TABLE + flszid ).append(tipTr);
	for ( var i = 0; i < xgzdZdz.length; i++) {// 修改后记录
		var srcObj = xgzdZdz[i];
		setValueUpdateMoreTr(flszid, srcObj, flag);
	}	
}


/**
 * 用提供的记录，替换表单值
 * 
 * @param data
 * @return
 */
function zdybdReplaceZd(data,flag1) {

	if (data == null || data == "") {
		return;
	}
	flag = flag1;
	zdybdReplaceZdDt(data);
	zdybdReplaceZdMore(data);
}

/*
 * 用提供的记录，替换表单值，单条记录
 */
function zdybdReplaceZdDt(data) {

	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		var zd = o.zd;
		var zdz = o.zdz;
		var xgqz = o.xgqz;
		var newValue = zdz;
		if (newValue == null) {
			newValue == "";
		}
		/*
		 * 字段类型 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 13:日期文本框 21:照片
		 * 22:省市县选择 23:链接 24;学院专业班级 99:功能自定义
		 */
		for ( var j = 0; j < zddyList.length; j++) {
			var zddyObj = zddyList[j];
			var zddyZd = zddyObj.zd;//
			var zddySzz = zddyObj.szz;
			var zddyZdlx = zddyObj.zdlx;//
			var zddyBdms = zddyObj.bdms;
			if(zddyBdms == "4"){
				continue;
			}
			if (zddyZd == zd) {
				if (zddyZdlx == "2" || zddyZdlx == "5" || zddyZdlx == "11"
						|| zddyZdlx == "13") {
					jQuery("#" + zd).val(newValue);
				} else if (zddyZdlx == "3") {
					jQuery(
							"input:radio[name='" + zd + "'][value='" + newValue
									+ "']").attr("checked", "checked");
				} else if (zddyZdlx == "4") {
					//遍历值,对选中的值打勾
					jQuery("#" + CONTENT_ID + " [name='" + zd + "']").each(function(i){
						if(newValue.indexOf(this.value) != -1 ){
							jQuery(this).attr("checked","checked");
						}						
					})
				}  else if (zddyZdlx == "22") {
					if (newValue != "") {
						var ssxDms = getSsx(zddySzz, newValue);
						if (ssxDms != null) {
							jQuery("#" + zd + "_province").val(ssxDms['shdm']);
							jQuery("#" + zd + "_province").val(ssxDms['shdm']);
							jQuery("#" + zd + "_province").change();
							jQuery("#" + zd + "_city").val(ssxDms['shidm']);
							jQuery("#" + zd + "_city").change();
							jQuery("#" + zd + "_locale").val(ssxDms['xidm']);
							jQuery("#" + zd + "_locale").change();
						}
					}
					jQuery("#" + zd).val(newValue);
				} else if (zddyZdlx == "6") {
					jQuery("#" + zd + "_fj").val(newValue);
				}
				break;
			}
		}
	}
}

/*
 * 用提供的记录，替换表单值，多条记录
 */
function zdybdReplaceZdMore(data) {
	for ( var i = 0; i < moreUpdateZdJson.length; i++) {
		var o = moreUpdateZdJson[i];
		var flszid = o.flszid;
		var src = o.src;
		var bzzd = o.bzzd;
		var zds = o.zds;

		for ( var j = 0; j < data.length; j++) {
			var xgzdObj = data[j];
			var xgzd = xgzdObj.zd;
			if(src != xgzd){
				continue;
			}
			var xgzdZdz = xgzdObj.zdz;
			if(xgzdZdz != null){
                jQuery("#" + PRECON_TABLE + flszid + " tbody tr:gt(0)").remove();
                for ( var k = 0; k < xgzdZdz.length; k++) {// 修改后记录
                    var srcObj = xgzdZdz[k];
                    setValueUpdateMoreTr(flszid, srcObj);
                }
            }
		}
	}	
}


/**
 * 得到修改字段及值json
 * 
 * @return
 */
function getXgzdJson() {
	var xgzdArr = [];
	var xgzdJson = {};
	xgzdJson.data = xgzdArr;
	
	var newValue = "";
	var flag = false;
	for ( var i = 0; i < zddyList.length; i++) {
		var xgzdDt = {};
		var o = zddyList[i];
		var zd = o.zd;//
		var szz = o.szz;
		var xgzdDtFlag = false;
		/*
		 * 字段类型 0:仅显示 1:隐藏域 2:下拉框 3:单选框 4:复选框 5:文本域 6:文件 11:字符文本框 13:日期文本框 21:照片
		 * 22:省市县选择 23:链接 24;学院专业班级 99:功能自定义
		 */
		var zdlx = o.zdlx;
		var yxxg = o.yxxg;
		var bdms = o.bdms;
		if(bdms == "4"){
			continue;
		}
		if(zdlx == "0"){
			continue;
		}
		if (yxxg != "0") {// 允许修改
			if (!flag) {
				flag = true;
			} else {
			}
			if (zdlx == "3") {
				var oldDm = valueJson[zd];
				var newValue = "";
				if (zdlx == "2") {
					newValue = jQuery("#" + zd).val();
				} else {
					newValue = jQuery("[name='" + zd + "']:checked").val();
				}
				if (oldDm == null) {
					oldDm = "";
				}
				if (newValue == null) {
					newValue = "";
				}
				if (oldDm != newValue) {
					xgzdDt.zd = zd;
					xgzdDt.zdz = newValue;
					xgzdDt.xgqz = oldDm;	
					xgzdDtFlag = true;
				}
//			} else if (zdlx == "22") {
//				var oldDm = valueJson[zd];
//				var newValue = jQuery("#" + zd + "_locale").val();
//				if (oldDm == null) {
//					oldDm = "";
//				}
//				if (newValue == null) {
//					newValue = "";
//				}
//				if (oldDm != newValue) {
//					xgzdDt.zd = zd;
//					xgzdDt.zdz = newValue;
//					xgzdDt.xgqz = oldDm;	
//					xgzdDtFlag = true;
//
//				}
			} else if (zdlx == "5" || zdlx == "11" || zdlx == "13"
					|| zdlx == "2"|| zdlx == "22") {
				var oldDm = valueJson[zd];

				newValue = jQuery.trim(jQuery("#" + zd).val());
				if (oldDm == null) {
					oldDm = "";
				}
				if (newValue == null) {
					newValue = "";
				}
				if (oldDm != newValue) {
					xgzdDt.zd = zd;
					xgzdDt.zdz = newValue;
					xgzdDt.xgqz = oldDm;	
					xgzdDtFlag = true;
				}
			} else if (zdlx == "4"){
				var oldDm = valueJson[zd];
				var newValue = "";

				jQuery("input[name='" + zd + "']:checked").each(function(){
					newValue += jQuery(this).val()+ "," ;
				});
				
				if (oldDm == null) {
					oldDm = "";
				}
				if (newValue == null || newValue == "") {
					newValue = "";
				} else {
					newValue = newValue.substr(0,newValue.length-1);
				}
				if (oldDm != newValue) {
					xgzdDt.zd = zd;
					xgzdDt.zdz = newValue;
					xgzdDt.xgqz = oldDm;	
					xgzdDtFlag = true;
				}				
			} else if (zdlx == "6"){
				var newValue = jQuery("input[name='" + zd + "']").val();

				if (newValue == null) {
					newValue = "";
				}
				xgzdDt.zd = zd;
				xgzdDt.zdz = newValue;
				xgzdDt.xgqz = "";	
				xgzdDtFlag = true;
			}
		}

		if (xgzdDtFlag) {
			xgzdArr.push(xgzdDt);
		}
	}
	debugger
	var v1 = jQuery("#bzbbz").val();
	if(v1 != null && v1 != jQuery("#bzbbzBefore").val()){
        var xgzd1 = {zd:"bzbbz",zdz:v1,xgqz:jQuery("#bzbbzBefore").val()};
        xgzdArr.push(xgzd1);
	}
	var v2 = jQuery("input[name='drsj']").val();
	if(v2 != null && v2 != jQuery("#drsjBefore").val()){
        var xgzd2 = {zd:"drsj",zdz:v2,xgqz:jQuery("#drsjBefore").val()};
        xgzdArr.push(xgzd2);
	}
	var v3 = jQuery("#lxbm").val();
	if(v3 != null && v3 != jQuery("#drsjBefore").val()){
        var xgzd3 = {zd:"lxbm",zdz:v3,xgqz:jQuery("#drsjBefore").val()};
        xgzdArr.push(xgzd3);
	}

	// 多条记录模式
	for ( var i = 0; i < moreUpdateZdJson.length; i++) {

		var o = moreUpdateZdJson[i];
		var flszid = o.flszid;
		var zds = o.zds;
		var src = o.src;
		var xgzdDt = {};
		xgzdDt.zd = src;
		var xgqzSrcList = valueJson[src];
		xgzdDt.xgqz = JSON.stringify(xgqzSrcList);
		var newValue = jQuery("#" + src).val();
		var newValueArr = eval("[" + newValue + "]");
		var zdzSrcList = "";
		if(newValueArr.length > 0){
			zdzSrcList = newValueArr[0]["data"];
			xgzdDt.zdz = JSON.stringify(zdzSrcList);
		}

	 if(!xgqzIsZdz(zds,xgqzSrcList,zdzSrcList)){
			xgzdArr.push(xgzdDt);
	 }
	}
	if(xgzdJson.data == null || xgzdJson.data.length == 0){
		return false;
	}
	jQuery("#xgzdJson").val(JSON.stringify(xgzdJson));
	return true;
}

/*
 * 判断修改前值是否与修改后值相同
 */
function xgqzIsZdz(zds,xgqzSrcList,zdzSrcList){
	var flag = true;
	var xgqzStr = "";
	var zdzStr = "";
	for ( var i = 0; i < zds.length; i++) {
		var zdObj = zds[i];
		var zd = zdObj.zd;
		var zdlx = zdObj.zdlx;
		if(zdlx == "1"){
			continue;
		}
		if(xgqzSrcList != null && xgqzSrcList.length > 0 ){
			for ( var j = 0; j < xgqzSrcList.length; j++) {
				var xgqzObj = xgqzSrcList[j];
				var xgqzValue = xgqzObj[zd];
				if(xgqzValue == null){
					xgqzValue = "";
				}
				xgqzStr += "zd:" +  zd + ",value:" + xgqzValue + ",";		
			}	
		}

		if(zdzSrcList != null && zdzSrcList.length > 0 ){
			for ( var j = 0; j < zdzSrcList.length; j++) {
				var zdzObj = zdzSrcList[j];
				var zdzValue = zdzObj[zd];
				if(zdzValue == null){
					zdzValue = "";
				}
				zdzStr += "zd:" + zd + ",value:" + zdzValue + ",";		
			}		
		}
	}	
	if(xgqzStr != zdzStr){
		flag = false;
	}	
	
	return flag;
}
