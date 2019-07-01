
/**
 * 结果查看
 * @return
 */
function viewJg(){
	var xxdm=jQuery("#xxdm").val();
	var url = "khgljgcx.do?method=xmjgList";
	if("10029"==xxdm||"12424"==xxdm){
		url= "khgljgcx.do?method=xmjgListOfSdty";
	}
	var rows = jQuery("#dataTable").getSeletRow();
	
	if(rows.length == 1){
		url+="&xmid="+rows[0]["xmid"]+"&khlx="+rows[0]["khlx"];
		document.location.href=url;
	}else{
		showAlertDivLayer("请选择一个项目");
		return false;
	}
	
}


//导出
function exportConfig(){
	var dclb = jQuery("#dclb").val();
	var DCCLBH='khgl_jgcx_ckJs.do';
	if('xs' == dclb){
		DCCLBH='khgl_jgcx_ckXs.do';
	}
	customExport(DCCLBH, exportData);
}

function exportData(){
	
	var dclb = jQuery("#dclb").val();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	
	var DCCLBH='khgl_jgcx_ckJs.do';
	
	if('xs' == dclb){
		DCCLBH='khgl_jgcx_ckXs.do';
	}
	setSearchTj();//设置高级查询条件
	var url = "khgljgcx.do?method=exportData&dcclbh=" + DCCLBH+"&xmid="+xmid+"&khlx="+khlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function exportConfigOfSdty(){
	
	var dclb = jQuery("#dclb").val();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	setSearchTj();//设置高级查询条件
	var url = "khgljgcx.do?method=exportConfigOfSdty&xmid="+xmid+"&khlx="+khlx+"&dclb="+dclb;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



/**
 * 打分统计学生
 * @return
 */
function dftj(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	var dclb = jQuery("#dclb").val();
	
	if(rows.length == 1){
		var url = "khgljgcx.do?method=dftjList&xmid="+xmid+"&khlx="+khlx+"&khdxr=";
		if('xs' == dclb){
			url+=rows[0]["xh"]+"&bmdm="+rows[0]["xydm"];
		}else{
			url+=rows[0]["zgh"]+"&bmdm="+rows[0]["bmdm"];
		}
		showDialog("打分统计",500,400,url,{max:false,min:false});
	}else{
		showAlertDivLayer("请选择一个人");
		return false;
	}
}

/**
 * 学生对班主任汇总打印
 * 浙江商业技师学院个性化
 * @return
 */
function xsdbzrhzDy(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var xmid = jQuery("#xmid").val();
//	var khlx = jQuery("#khlx").val(); 到这里考核类型铁定是2呀
	var khdxrs = [];
	for(var i=0;i<rows.length;i++){
		khdxrs.push(rows[i]["zgh"]);
	}
	
	if(rows.length == 0){
		showAlertDivLayer("请先选择记录！");
		return false;
	}else{
		var url = "khgljgcx.do?method=xsdbzrhzDy&xmid="+xmid+"&khdxrs="+khdxrs;
		window.open(url);
	}
}
