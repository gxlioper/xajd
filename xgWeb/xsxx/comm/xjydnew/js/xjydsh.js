
//查看学生异动信息
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydshInfoCk(\""+row["xjydsqid"]+"\")'>"+cellValue+"</a>";
}

//查看学生异动信息
function xjydshInfoCk(xjydsqid){
	showDialog("查看学生学籍异动信息",800, 500,"xjydsh.do?method=xjydshCk&xjydsqid="+xjydsqid,null);
}


//
function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
	}else{
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
	}
	searchRs();
}
//查询
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}

//学籍异动审核
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlert("已处理的记录不能再次审核！");
	}else if (rows.length != 1){
		showAlert("请选择一条您要审核的记录！");
	}  else{
		showDialog("学籍异动审核",810, 500,'xjydsh.do?method=xjydsh&xjydsqid='+rows[0]["xjydsqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	}
}


//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条记录！");
	} else {
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['xjydsqid']+"&splc="+rows[0]['splc']);
	}
}



/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	var xxdm = jQuery("#xxdm").val();
	var shzt = jQuery("#shjg").val();
	if(jQuery("#shjg").val() == "0"){
		showAlertDivLayer("请选择审核状态！");
		return false;
	}
	var checks = "shyj";
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "通过";
		
		var isZhgw = jQuery("#isZhgw").val();
		if("true" == isZhgw){
			if("13871" == xxdm||"70002" == xxdm ||"5002" == xxdm ){
				checks = "xjydsj-shyj";
			}else{
				checks = "xjydwh-xjydsj-shyj";
			}
			if(!jQuery("#zzTzbj").is(":hidden")){
				checks += "-nj-xydm-zydm-bjdm"
			}
			if("10511" == xxdm) {
				if(!jQuery("#zzxzpd").is(":hidden")){
					checks += "-xz";
				}
				if(!jQuery("#zzxxpd").is(":hidden")){
					checks += "-lyqxxxdm";
				}
				checks += "-sfsfs-dqztdm-ydyydm";		
			}
		}
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	
	if(jQuery("#shjg").val() == "2"){
		message = "不通过";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "退回";
	}
	if(!check(checks)){
		showAlertDivLayer("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	//提交审核
	showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
		var url = "xjydsh.do?method=xjydsh&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}


function exportConfig() {
	customExport("ydshbase.do", exportData);
}
// 导出方法
function exportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	
	var url = "xjydsh.do?method=exportData&dcclbh='xjydsh.do'"+ "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
