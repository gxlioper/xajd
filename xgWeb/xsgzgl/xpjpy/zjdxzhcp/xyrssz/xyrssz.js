/*查询数据*/
function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*学院人数设置*/
function setRsszXy(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= 1){
		cellValue = "1";  //后面的代码为公用，这里将参数重新还原
		value = "已设置("+rowObject.rssz+"人)";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}

/*由于外层样式影响，颜色必须写在元素上*/
function setColor(value,status){
	var color;
	if(status == '1'){
		color = "#004400";
	}else{
		color = "red";
	}
	return value = "<font color='"+color+"'>" + value + "</font>";
}

/*人数设置*/
function rssz(xmdm,sqkg,rsfpfs,xmmc,xmje) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		rsfpfs = rows[0]["rsfpfs"];
		xmmc = rows[0]["xmmc"];
		xmje=rows[0]["xmje"];
	}
    
	if (rsfpfs == null || rsfpfs == "all" || rsfpfs == "" || rsfpfs == "null") {
		showAlertDivLayer("请先在[修改]中配置[人数分配方式]！");
		return false;
	}
	var url = 'xpjpy_xyrssz.do?method=xyrszCx';
	url += "&xmdm=" + xmdm;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + xmmc;
	url += "&xmje=" + xmje;
	var title = "人数设置";
	showDialog(title, 750, 520, url,{close:function(){query();}});
}