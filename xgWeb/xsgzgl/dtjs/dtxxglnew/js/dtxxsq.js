var action="dtxxsq.do";

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function dcmcLink(cellValue, rowObject) {
	var dtxxsqid = rowObject["dtxxsqid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + dtxxsqid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(dtxxsqid) {
	var url = action+"?method=showView&dtxxsqid=" + dtxxsqid;
	var title = "党团申请信息";
	showDialog(title, 700, 380, url);
}
//申请
function add() {
	var url =action+"?method=add";
	var title = "党团信息申请";
	showDialog(title, 700, 430, url);
	jQuery("#dataTable").reloadGrid();
}
function save(url,checkId,keyid){
	if(!check(checkId)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(!checkother()){
		return false;
	}
	var xh=jQuery("#xh").val();
	var dtxxsqid=jQuery("#"+keyid).val();
	lock();
	jQuery.post("dtxxsq.do?method=isCanAdd", {
		xh:xh,dtxxsqid:dtxxsqid,jddm:jQuery("#jddm").val()
	}, function(data) {
		if(data["success"]=="true"){
			 	jQuery("#form").ajaxSubmit({
					url:url,
					type:"post",
					dataType:"json",
					success:function(data){
				 		 if(data["message"]=="保存成功！" ||data["message"]=="提交成功！" ){
				    		 showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
					},
					contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});
					 	
		}else{
			showAlert(data["message"]);
		}
		unlock();
	}, 'json');
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
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt=rows[0]["shzt"];
		if(shzt!="0"&&shzt!="3"){
			showAlert("请选择未提交或者已退回的记录！");
			return false;
		}
		var url = action+'?method=update&dtxxsqid=' + rows[0]["dtxxsqid"];
		var title = "修改党团信息申请";
		showDialog(title, 700, 430, url);
		jQuery("#dataTable").reloadGrid();
	}
}
function checkother(){
	return true;
}
function checkNum(){
	var kssj=jQuery("#kssj").val();
	var str=kssj.substring(0,1);//截取第一位
	if(parseInt(str)<=0&&kssj.length>1){
		return true;
	}
	var jssj=jQuery("#jssj").val();
	str=jssj.substring(0,1);//截取第一位
	if(parseInt(str)<=0&&jssj.length>1){
		return true;
	}
	return false; 
}
//删除
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
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>["+data["nodel"]+"]</font>";
						mes+="已进入审核流程不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function dtxxsqLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	var shzt = rows[0]["shztmc"];

	if ("未提交" == shzt){
				showAlertDivLayer("该记录为未提交状态，请先提交！");
				return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("党团管理审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['dtxxsqid']+"&splc="+rows[0]['splc']);
	}
}
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(action,exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = action+"?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function showRdzys(){
	var jdmc=jQuery("#jddm option:selected").text().trim();
	if("预备党员"==jdmc){
		jQuery("#jddm").parent().removeAttr("colspan");
		jQuery("[name='rdzys'").show();
	}else{
		jQuery("#jddm").parent().attr("colspan","3");
		jQuery("[name='rdzys'").hide();
	}
}