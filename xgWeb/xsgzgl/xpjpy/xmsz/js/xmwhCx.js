var tjszDialog ;
var DCCLBH='xpj_xmwh.do?method=xmwhCx';



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
	var value = "未开启";
	var status = '0';
	var color;
	if(cellValue == '1'){
		var currDate = jQuery("#currDate").val();
		if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
		}else{
			value = "已开启";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+xmdm+"\");' >"+value+"</a>";
	return value;
}

/*
 *审核开关 
 */
function setShkg(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var value = "未开启";
	var status = '0';
	var color;
	if(cellValue == '1'){
		var currDate = jQuery("#currDate").val();
		if(rowObject.shkssj != null && currDate < rowObject.shkssj || rowObject.shjssj != null && currDate > rowObject.shjssj ){
		}else{
			value = "已开启";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+xmdm+"\");' >"+value+"</a>";
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
 *人数设置
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= 1){
		cellValue = 1; //后面的代码为公用，这里将参数重新还原
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}

/*
 *浙大学院人数设置
 */
function setRsszXy(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= '1'){
		cellValue = "1";  //后面的代码为公用，这里将参数重新还原
		value = "已设置("+rowObject.rssz+"人)";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
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
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
        var xmxz = jQuery("#xmxz").val();
		var url = 'xpj_xmwh.do?method=xmwhZjXg&xmxz='+xmxz+'&xmdm=' + rows[0]["xmdm"];
		var title = "项目修改";
		showDialog(title, 680, 480, url);
	}
}

function add() {
    var xmxz = jQuery("#xmxz").val();
	var url = "xpj_xmwh.do?method=xmwhZjXg&xmxz="+xmxz;
	var title = "项目增加";
	showDialog(title, 720, 460, url);
}

/*
 * 时间开关
 */
function sjkg(xmdm) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh.do?method=xmwhSjkg&xmdm=' + xmdm+'&xmxz='+xmxz;
	var title = "项目时间控制";
	showDialog(title, 600, 380, url);
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
        var xmxz = jQuery("#xmxz").val();
	}
	var url = 'xpj_xmwh_tjsz.do?method=xmwhTjszCx&xmxz='+xmxz;
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "条件设置";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}


/*人数设置*/
function rssz(xmdm,sqkg,sqxn,sqxq,rsfpfs,xmmc,xmje) {
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
		rsfpfs = rows[0]["rsfpfs"];
		xmmc = rows[0]["xmmc"];
		xmje=rows[0]["xmje"];
	}
    
	if (rsfpfs == null || rsfpfs == "all" || rsfpfs == "" || rsfpfs == "null") {
		showAlertDivLayer("请先在[修改]中配置[人数分配方式]！");
		return false;
	}
	var czfs = jQuery("#czfs").val();
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_rssz.do?method=xmwhRsszCx&czfs='+czfs;
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	url += "&xmje=" + xmje;
	var title = "人数设置";
	showDialog(title, 750, 520, url,{close:function(){query();}});
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
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "不可兼得设置";
	showDialog(title, 500, 280, url);
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
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_tzsz.do?method=xmwhShszCx';
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "奖项调整设置";
	showDialog(title, 500,  235, url);
}

/*奖项复制*/
function jxfz() {
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh.do?method=xmwhJxfz&xmxz='+xmxz;
	var title = "奖项复制";
	showDialog(title, 390, 230, url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？",{"okFun":function(){
			var url = "xpj_xmwh.do?method=xmwhSc";
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

//导出
function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
    var xmxz = jQuery("#xmxz").val();
	
	var url = "xpj_xmwh.do?method=exportData&dcclbh=" + DCCLBH+"&xmmc="+xmmc+"&lxdm="+lxdm+"&xzdm="+xzdm+"&xmxz="+xmxz;//dcclbh,导出功能编号
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

