var tjszDialog ;
var gridSetting = {
	caption : "项目列表",
	pager : "pager",
	url : "xszz_xmwh.do?method=xmwhCx&type=query",
	colList : [
	    {label : '项目代码',name : 'xmdm',index : 'xmdm',key : true,hidden : true}, 
	    {label : '学年',name : 'sqxn',index : 'sqxn',hidden : true},
	    {label : '学期',name : 'sqxq',index : 'sqxq',hidden : true},
	    {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
	    {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true},
	    {label : '人数控制范围',name : 'rskzfw',index : 'rskzfw',hidden : true},
	    {label : '金额是否学生申请',name : 'jesfxssq',index : 'jesfxssq',hidden : true},
		{label : '项目名称',name : 'xmmc',index : 'xmmc',width : '25%'}, 
		{label : '类别代码',name : 'lbdm',index : 'lbdm',hidden : true},
		{label : '项目类别',name : 'lbmc',index : 'lbmc',width : '15%'},
		{label : '学生类别',name : 'xslb',index : 'xslb',hidden : true},
		{label : '金额',name : 'je',	index : 'je',width : '10%',noSort:true ,formatter:jesfkt},
		{label : '基本设置',	name : 'sqkg',index : 'sqkg',width : '10%',	formatter:setSqkg}, 
		{label : '条件设置',name : 'tjsz',index : 'tjsz',width : '10%',formatter:setTjsz}, 
		{label : '人数设置',name : 'rssz',index : 'rssz',width : '10%',formatter:setRssz},
		{label : '不可兼得设置',name : 'jdsz',index : 'jdsz',width : '10%',formatter:setJdsz},
		{label : '调整奖项设置',name : 'shsz',index : 'shsz',width : '10%',formatter:setShsz}, 
		{label : '经费设置',name : 'jfsz',index : 'jfsz',width : '10%',formatter:setJfsz}
		],
	sortname : "xmmc",
	sortorder : "asc"
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

//由于外层样式影响，颜色必须写在元素上
function setColor(value,status){
	var color;
	if(status == '1'){
		color = "#004400";
	}else{
		color = "red";
	}
	return value = "<font color='"+color+"'>" + value + "</font>";
}

/*
 *申请开关 
 */
function setSqkg(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var value = "未设置";
	var status = '0';
	var color;
	if(cellValue == '1'){
		var currDate = jQuery("#currDate").val();
		if((null==rowObject.sqkssj||currDate >= rowObject.sqkssj) && (null==rowObject.sqjssj ||currDate <= rowObject.sqjssj) ){
			value = "已设置";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return jbsz(\""+xmdm+"\");' >"+value+"</a>";
	return value;
}
function jesfkt(cellValue,rowObject){
	var jesfxssq=rowObject.jesfxssq;
	var value='';
	if(jesfxssq=='1'){
		value=cellValue+"<font color='red'>(调)</font>";
	}else{
		value=cellValue;
	}
	return value;
}

/*
 *条件设置
 */
function setTjsz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var xmmc = rowObject.xmmc;
	var value;
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*
 *经费设置
 */
function setJfsz(cellValue,rowObject){
	var jfsz = rowObject.jfsz;
	var value;
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return szjf(\""+rowObject["xmdm"]+"\");'>"+value+"</a>";
	return value;
}

function szjf(xmdm){
	var url = 'xszz_xmwh.do?method=xmwhJfsz&xmdm=' + xmdm;
	showDialog("经费设置", 680, 550, url);
}


/*
 *人数设置
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
	var rskzfw = rowObject.rskzfw;
	var xmmc = rowObject.xmmc;
	var xslb=rowObject.xslb;
	var value;
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rskzfw+"\",\""+xmmc+"\",\""+xslb+"\");' >"+value+"</a>";
	return value;
}


/*
 *兼得设置
 */
function setJdsz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var xmmc = rowObject.xmmc;
	var value;
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return jdsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*
 *审核调整奖项设置
 */
function setShsz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var xmmc = rowObject.xmmc;
	var value;
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return shsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["sqzqdm"] = jQuery.trim(jQuery("#sqzqdm").val());
	map["lbdm"] = jQuery("#lbdm").val();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xszz_xmwh.do?method=xmwhXg&xmdm=' + rows[0]["xmdm"];
		var title = "项目修改";
		showDialog(title, 490, 400, url);
	}
}

function add() {
	var url = "xszz_xmwh.do?method=xmwhZj";
	var title = "项目增加";
	showDialog(title, 490, 400, url);
}

/*
 * 基本设置
 */
function jbsz(xmdm) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xszz_xmwh.do?method=xmwhJbsz&xmdm=' + xmdm;
	var title = "基本设置";
	showDialog(title, 680, 600, url);
}

/*条件设置*/
function tjsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}
	var url = 'xszz_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "条件设置";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}


/*人数设置*/
function rssz(xmdm,sqkg,sqxn,sqxq,rskzfw,xmmc,xslb) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqxn = rows[0]["sqxn"];
		sqxq = rows[0]["sqxq"];
		sqkg = rows[0]["sqkg"];
		rskzfw = rows[0]["rskzfw"];
		xmmc = rows[0]["xmmc"];
		xslb = rows[0]["xslb"];
		
	}
	if (rskzfw == null  || rskzfw == "null"  || rskzfw == "all" || rskzfw == "") {
		showAlertDivLayer("请先在[基本设置]中配置[人数控制范围]！");
		return false;
	}

	var url = 'xszz_xmwh_rssz.do?method=xmwhRsszCx';
	url += "&xmdm=" + xmdm;
	url += "&xn=" + sqxn;
	url += "&xq=" + sqxq;
	url += "&rskzfw=" + rskzfw;
	url += "&xmmc=" + xmmc;
	url += "&xslb=" + xslb;
	var title = "人数设置";
	showDialog(title, 750, 545, url,{close:function(){query();}});
}


/*兼得设置*/
function jdsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}

	var url = 'xszz_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "不可兼得设置";
	showDialog(title, 600, 235, url);
}


/*审核调整奖项设置*/
function shsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}

	var url = 'xszz_xmwh_shsz.do?method=xmwhShszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "审核调整奖项设置";
	showDialog(title, 600, 400, url);
}


function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？",{"okFun":function(){
			var url = "xszz_xmwh.do?method=xmwhSc";
			jQuery.post(url, {
				values : ids.toString()
			}, function(data) {
				if(data["success"] == false){
					showAlertDivLayer(data["message"]);
				}else{
					showAlertDivLayer(data["message"], {},{"clkFun": function(tag) {
						jQuery("#dataTable").reloadGrid();
					}});
				}
			}, 'json');
		}});
	}
}

/*项目复制*/
function jxfz() {
	var url = 'xszz_xmwh.do?method=xmwhfz';
	var title = "资助项目复制";
	showDialog(title, 390, 230,url);
}
