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


// 查看学生异动信息
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydXsInfoCk(\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

function xyfmt(cellValue,row){
	if("12866"==jQuery("#xxdm").val()){
		var str = cellValue;
		if(str.length > 8){
			str = str.substring(0,8)+"...";
		}
		return "<span title='"+cellValue+"'>"+str+"</span>";
	}
	return cellValue;
}


//查看学生异动信息
function xjydXsInfoCk(xh){
	showDialog("查看学生学籍异动信息",800, 500,"xjydjg.do?method=xjydXsInfoCk&xh="+xh,null);
}
//查看单条学籍异动信息
function ckYdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条您要查看的记录！");
	}  else{
		showDialog("学籍异动信息",800, 500,'xjydjg.do?method=xjydjgCk&xjydjgid='+rows[0]["xjydjgid"]);
	}
}

//增加
function addYdjg() {
		var url ="xjydjg.do?method=xjydjgAdd";
		var title = "学籍异动申请";
		showDialog(title, 800, 500, url);
}

//修改
function updYdjg() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条您要修改的记录！");
	}  else{
		var sjly = rows[0]["sjly"];
		if(sjly=="1"){
			showAlert("流程数据不能修改！");
			return false;
		}
		showDialog("学籍异动信息",810, 500,'xjydjg.do?method=xjydjgUpd&xjydjgid='+rows[0]["xjydjgid"]);
	}
}

function delYdjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		
		confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("xjydjg.do?method=xjydjgDel",{values:ids.toString()},function(data){
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}


//查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("xjyd_xjydjg.do", exportData,690,500);
}
// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xjydjg.do?method=exportData&dcclbh=xjyd_xjydjg.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 显示学籍和班级信息
 * @return
 */
function initShow(){
	var xxdm = jQuery("#xxdm").val();
	var xjlbdm = jQuery("#ydlbdm").val();
	if(xjlbdm == ""){
		jQuery("#xjlbmc").html("");
		jQuery("#sfyxj").html("");
		jQuery("#sfzx").html("");
		jQuery("#tzbj").hide();
		jQuery("#lrqzsj").hide();
		jQuery("#xzpd").hide();
		jQuery("#xxpd").hide();
	}else{
		jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
			if(data != null){
				jQuery("#xjlbmc").html(data["xjlbmc"]);
				jQuery("#sfyxj").html(data["sfyxjmc"]);
				jQuery("#sfzx").html(data["sfzxmc"]);

				if(data["lrqzsj"]=='1'){
					jQuery("#lrqzsj").show();
				}else{
					jQuery("#lrqzsj").hide();
					jQuery("#sqkssj").val("");
					jQuery("#sqjssj").val("");
					
				}
				
				if(data["sftjbj"]=='0'){
					jQuery("#tzbj").show();
				}else{
					jQuery("#tzbj").hide();
					jQuery("#nj").val("");
					jQuery("#xydm").val("");
					jQuery("#zydm").val("");
					jQuery("#bjdm").val("");
				}		
				if("10511" == xxdm) {
					if(data["xzsfkq"]=='1'){
						jQuery("#xzpd").show();
					}else{
						jQuery("#xzpd").hide();
						jQuery("#xz").val("");
					}
					if(data["xxsfkq"]=='1') {
						jQuery("#xxpd").show();
					}else{
						jQuery("#xxpd").hide();
						jQuery("#lyqxxxdm").val("");				
					}
				}
			}
		},'json');
	}
}

/**
 * 批量学籍异动
 * @return
 */
function plXjyd(){
	showDialog("批量学籍异动",800, 570,'xjydjg.do?method=xjydPlcl');
}

function selectStudent(){
	var path = jQuery("#path").val();	
	var xzxsKey = jQuery("#xzxsKey").val();
	showDialog("批量选择学生",800, 600,'xsxx_xsgl.do?method=selectStudentsNew&goto='+path+'&xzxsKey='+xzxsKey);
}