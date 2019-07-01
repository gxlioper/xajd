//页面点击填写
function add() {
		var url ="rcsw_xscxqybgl.do?method=XscxqybAdd";
		var title = "月报填写";
		showDialog(title, 800, 500, url);
	}
	
//填写完成的保存方法
function saveForm(type){
	var yf = jQuery("#yf").val();
	var bygzkzqk = jQuery("#bygzkzqk").val();
	var xsgzrd = jQuery("#xsgzrd").val();
	var xssxdt = jQuery("#xssxdt").val();
	var xstsjgzjy = jQuery("#xstsjgzjy").val();
	if (jQuery.trim(yf) == ""){
		showAlert("月份不能为空！");
		return false;
	}
	if (jQuery.trim(bygzkzqk) == ""){
		showAlert("本月工作开展情况不能为空！");
		return false;
	}	
	if (bygzkzqk.length > 1000){
		showAlert("本月工作开展情况最多1000字！");
		return false;
	}	
	if (jQuery.trim(xsgzrd) == ""){
		showAlert("学生关注热点不能为空！");
		return false;
	}	
	if (xsgzrd.length > 1000){
		showAlert("学生关注热点最多1000字！");
		return false;
	}	
	if (jQuery.trim(xssxdt) == ""){
		showAlert("学生思想动态不能为空！");
		return false;
	}	
	if (xssxdt.length > 1000){
		showAlert("学生思想动态最多1000字！");
		return false;
	}	
	if (jQuery.trim(xstsjgzjy) == ""){
		showAlert("学生诉求及工作建议不能为空！");
		return false;
	}	
	if (xstsjgzjy.length > 1000){
		showAlert("学生诉求及工作建议最多1000字！");
		return false;
	}	
	var url = "rcsw_xscxqybgl.do?method=XscxqybAdd&type="+type;
  	ajaxSubFormWithFun("xscxqybForm",url,function(data){
	 if(data["message"]=="保存成功！" ){
		 showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
	 }else{
		 showAlert(data["message"]);
	 }});
	}

//List页面修改按钮
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	}else {
		var selTxr = rows[0]["txr"];//数据库填写人记录单条
		var dqUser = jQuery("#userName").val();//当前操作人
		if(selTxr != dqUser) {	
			alertInfo("选择记录中包含的填写人不是当前用户，请重新选择！");
			return false;
		}
		showDialog('月报修改',750,490,'rcsw_xscxqybgl.do?method=XscxqybUpdate&jgid='+rows[0]["jgid"]);
	}
}

//修改完成后的保存方法
function saveUpdate(){
var yf=jQuery("#yf").val();
var bygzkzqk=jQuery("#bygzkzqk").val();
var xsgzrd=jQuery("#xsgzrd").val();
var xssxdt=jQuery("#xssxdt").val();
var xstsjgzjy=jQuery("#xstsjgzjy").val();
if(yf==null||yf==""||bygzkzqk==null||bygzkzqk==""||xsgzrd==null||xsgzrd==""||xssxdt==null||xssxdt==""||xstsjgzjy==null||xstsjgzjy==""){
	showAlert("请填写带*号的字段");
	return false;
}
var url="rcsw_xscxqybgl.do?method=XscxqybUpdate&type=save";
ajaxSubFormWithFun("xscxqybForm",url,function(data){
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

//删除方法
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var dqUser = jQuery("#userName").val();//当前操作人
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	}else {
		var flg = false;
		for(var i=0;i<ids.length;i++){
			if(rows[i]["txr"]!=dqUser){	//rows[i]["txr"]是因为要拿多条txr数据
				flg = true;
				break;
			}
		}		
		if(flg == true) {
			alertInfo("选择记录中包含的填写人不是当前用户，请重新选择！");
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("rcsw_xscxqybgl.do?method=XscxqybDel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

//导出
function exportConfig(){     //导出
	var DCCLBH='rcsw_xscxqybgl.do';
	customExport(DCCLBH, exportData);
	}
function exportData(){
	var DCCLBH='rcsw_xscxqybgl.do';
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xscxqybgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//点击月份查看详细信息
function XscxqybView(jgid,cellValue){
	showDialog('月报查看',700,350,'rcsw_xscxqybgl.do?method=XscxqybView&jgid='+jgid);
}