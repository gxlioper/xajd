/*加载*/
jQuery(function() {
	onShow();
});

/*修改模式，已保存审核流程*/
var defaultShlc;

function onShow() {
	/*人数控制级别根据审核流程进行显示*/
	jQuery("[name=shlc]").change(function() {
				setKzlc(jQuery(this).val());
			});
	defaultShlc = jQuery("[name=shlc]:checked").val();
	jQuery("[name=shlc]").change();
	/*人数分配方式赋值*/
	jQuery("input:radio[name=rsfpfsView][value=" + jQuery("#rsfpfs").val()+ "]").attr("checked", "checked");
}

function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		return;
	}
	var url = "xpj_xmwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		var radio1 = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
		}
		sHtml += radio1;
		jQuery("#rskzjbTd").html(sHtml);
		/*人数控制级别*/
		jQuery("input:radio[name=rskzjbView][value=" + jQuery("#rskzjb").val()+ "]").attr("checked", "checked");	
		if (defaultShlc == value) {
		}
		/*设置审批状态*/
		setSpzt();
	}, 'json');
}


/**
 * 根据审核状态，设置部分项
 * @return
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery(".prompt").css("display","");
		jQuery("table input,select").not(jQuery("#xssx")).not(jQuery("#ywmc")).attr("disabled", "disabled");
	}
}



/*项目名称、项目类型、项目性质、申请开关 模糊查询*/
function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*评奖项目增加页面*/
function add() {
	var url = "xpjpy_xmwh.do?method=xmwhAdd";
	var title = "项目增加";
	showDialog(title, 650, 565, url);
}

/*评奖项目增加保存*/
var ids = "xmmc-lxdm-xzdm-xssx-shlc";
function saveFormAdd(){
	
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	// 人数开关级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	if (rskzjb == null || rskzjb == "") {
		showAlert("请选择人数控制级别！");
		return false;
	}
	
	/*人数分配方式设置值，以便提交用*/
	var rsfpfs = jQuery("input:radio[name=rsfpfsView]:checked").val();
	if (rsfpfs == null || rsfpfs == "") {
		showAlert("[人数分配方式]不允许为空！");
		return false;
	}
	jQuery("#rsfpfs").val(rsfpfs);
	
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	/*判断是否设置人数*/
	var sfrssz = jQuery("input:radio[name=sfrssz]:checked").val();
	if (sfrssz == null || sfrssz == "") {
		showAlert("请选择是否设置人数！");
		return false;
	}
	
	var url = "xpjpy_xmwh.do?method=saveFormAdd";
	
	jQuery("table input,select").attr("disabled",false);
	
	ajaxSubFormWithFun("zjdxXmwhForm", url, function(data) {
		if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 	}else{
   	 		showAlert(data["message"]);
   		}
	});
}

/*评奖项目修改页面*/
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xpjpy_xmwh.do?method=xmwhUpdate&xmdm=' + rows[0]["xmdm"];
		var title = "项目修改";
		showDialog(title, 650, 565, url);
	}
}

/*评奖项目修改保存*/
function saveFormUpdate(){
	if(!checkNotNull(ids)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	// 人数控制级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	if (rskzjb == null || rskzjb == "") {
		showAlert("请选择人数控制级别！");
		return false;
	}
	
	// 人数分配方式设置值，以便提交用
	var rsfpfs = jQuery("input:radio[name=rsfpfsView]:checked").val();
	if (rsfpfs == null || rsfpfs == "") {
		showAlert("[人数分配方式]不允许为空！");
		return false;
	}
	jQuery("#rsfpfs").val(rsfpfs);
	
	
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	/*判断是否设置人数*/
	var sfrssz = jQuery("input:radio[name=sfrssz]:checked").val();
	if (sfrssz == null || sfrssz == "") {
		showAlert("请选择是否设置人数！");
		return false;
	}
	
	/*定义URL*/
	var url = "xpjpy_xmwh.do?method=saveFormUpdate";
	/*选中*/
	jQuery("table input,select").attr("disabled",false);
	ajaxSubFormWithFun("zjdxXmwhForm", url, function(data) {
		if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 	}else{
   	 		showAlert(data["message"]);
   		}
	});
}

/*评奖项目删除数据*/
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xpjpy_xmwh.do?method=xmwhDelete",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

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

/*点击申请开关*/
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

/*点击审核开关*/
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

/*时间开关*/
function sjkg(xmdm) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xpjpy_xmwh.do?method=xmwhSjkg&xmdm=' + xmdm;
	var title = "项目时间控制";
	showDialog(title, 600, 355, url);
}

/*奖项复制弹出框*/
function jxfz() {
	var url = 'xpjpy_xmwh.do?method=xmwhJxfz';
	var title = "奖项复制";
	showDialog(title, 390, 230, url);
}

/*点击条件设置*/
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

	var url = 'xpjpy_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "不可兼得设置";
	showDialog(title, 500, 280, url);
}

/*勾选一条记录点击条件设置按钮*/
var tjszDialog ;
function tjsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}
	var url = 'xpjpy_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "条件设置";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}

/*报表设置*/
function bbsz(type){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要设置的记录！");
	} else {
		var xmdm = rows[0]["xmdm"];
		document.location.href="xpjpy_xmwh_bbsz.do?method=bbylList&bblx="+type+"&xmdm="+rows[0]["xmdm"];
	}
}


/*
 *人数设置
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
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
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
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
	var url = 'xpjpy_xmwh_rssz.do?method=xmwhRsszCx';
	url += "&xmdm=" + xmdm;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	url += "&xmje=" + xmje;
	var title = "人数设置";
	showDialog(title, 750, 520, url,{close:function(){query();}});
}