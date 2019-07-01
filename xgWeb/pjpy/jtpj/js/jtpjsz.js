var action="jtpjsz.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function jxmc(cellValue,rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["jxid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(jxid) {
	var url = action+"?method=showView&jxid=" + jxid;
	showDialog("奖项信息", 700, 400, url);
}
function pjdwmc(cellValue,rowObject){
	return fomartPjdw(cellValue);
}
function fomartPjdw(pjdw){
	var mc="";
	switch (pjdw) {
	case "xy":
		mc="以<font color='blue'>"+jQuery("#xbmc").val()+"</font>为单位";
		break;
	case "bj":
		mc="以<font color='blue'>班级</font>为单位";
		break;
	case "qs":
		mc="以<font color='blue'>寝室</font>为单位";
		break;	
	default:
		mc="其他";
		break;
	}
	return mc;
}
/**
 * 自动设置评奖单位名称
 * 
 * @return
 */
function autoSetPjdwMc(){
	var jtpjdw=jQuery("#jtpjdw").val();
	jQuery("#jtpjdwmc").html(fomartPjdw(jtpjdw));
}
function sqkg(cellValue,rowObject){
	if(cellValue=="1"){
		return "开启";
	}
	return "关闭";
}
function jxpdzq(cellValue,rowObject){
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
// 申请
function add() {
		var url =action+"?method=add";
		showDialog("集体评奖设置增加", 700, 440, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		var url = action+'?method=update&jxid=' + rows[0]["jxid"];
		var title = "修改集体评奖设置";
		showDialog(title, 700, 440, url);
	}
}
function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("不存在其他申请周期，不能复制。",{},{"clkFun":function(){
		}});
		return false;
	}
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	var url =action+"?method=jtpjszcopy&sqxn="+sqxn+"&sqxq="+sqxq;
	showDialog("集体评奖设置复制", 400, 200, url);
}
function savecopy(){
	var lyzq=jQuery("#lyzq").val();
	var lyxn=lyzq.split(",")[0];
	var lyxq=lyzq.split(",")[1];
	
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	jQuery.post(action + "?method=savecopy", {
		lyxn:lyxn,
		lyxq:lyxq,
		sqxn:sqxn,
		sqxq:sqxq
	}, function(data) {
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	}, 'json');
}
/**
 * 自动设置申请人员选中
 * 
 * @return
 */
function autoSetSelectSqr(){
	var sqrid=jQuery("#ksqxslx").val();
	jQuery("[type=checkbox][name=ksqxslx]").each(function(){
		var v=jQuery(this).val();
		if(sqrid.indexOf(v)!=-1){
			jQuery(this).attr("checked", true);
		}
	});
}

function autoSetKsqr(){
	var sqrId="";
	jQuery("[type=checkbox][name=ksqxslx]").each(function(){
		if(jQuery(this).is(":checked")){
			sqrId+=jQuery(this).val()+",";
		}
	});
	jQuery("#ksqxslx").val(sqrId);
}
function save(url,checkId){
	if(!checkNull(checkId)){
		return false;
	}
	// 申请开关开启的时候需要判断是否选中了审核流程
	var kqqk=jQuery("[name=sfkfsq]:checked").val();
	if(kqqk=="1"){
		if(!checkNull('splcid')){
			return false;
		}
	}else{
		// 申请关闭状态，审核流程置空
		jQuery("#splcid").val("");
	}
	
	autoSetKsqr();
	// lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
			}else{
				showAlert(data["message"]);
				
			}},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
	// unlock();
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					//var mes="成功删除了<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>条数据";
					//mes+="</br>";
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						query();
					}});
				}, 'json');
			}
		});

	}
}
function autoSetSqkg(){
	var kqqk=jQuery("[name=sfkfsq]:checked").val();
	if(kqqk=="1"){
		jQuery("#kqxx").show();
	}else{
		jQuery("#kqxx").hide();
	}
}
function query(){
	var map = {};
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	map["jxmc"] = jQuery("#jxmc").val();
	map["sqxn"] =sqxn;
	map["sqxq"] =sqxq;
	jQuery("#dataTable").reloadGrid(map);
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



/*
 *条件设置
 */
function setTjsz(cellValue,rowObject){
	var xmdm = rowObject.jxid;
	var sqkg = rowObject.sfkfsq;
	var xmmc = rowObject.jxmc;
	var jtpjdw = rowObject.jtpjdw;
	var value;
	if(jtpjdw != 'bj'&&jtpjdw != 'qs'){
		return "";
	}
	if(cellValue == '1'){
		value = "已设置";
	}else{
		value = "未设置";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*条件设置*/
function tjsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["jxid"];
		sqkg = rows[0]["sfkfsq"];
		xmmc = rows[0]["pjjtmc"];
	}
	var url = 'xpj_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	url += "&flagpath=jtpj";
	var title = "条件设置";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}