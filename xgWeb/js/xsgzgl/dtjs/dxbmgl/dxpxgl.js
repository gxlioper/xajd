function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
function setCjbl(cellValue, rowObject) {
	var id = rowObject["id"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('"+id+"')\" class='name'>设置</a>";
}
//查看信息
function ckxx(id) {
	var url = "dtjs_dxbmgl_dxpxglSz.do?id=" + id;
	var title = "课程分数比例设置";
	showDialog(title, 700, 250, url);
}
//申请
function add() {
		var url ="dtjs_dxbmgl_dxpxglZj.do";
		var title = "增加党校培训信息";
		showDialog(title, 700, 260, url);
		jQuery("#dataTable").reloadGrid();
}
function formatStr(str){
	if(str==""||null==str){
		return "-1";
	}
	return str;
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
		var sjly=rows[0]["sjly"];
		if(sjly=="1"){
			showAlertDivLayer("该条党校培训信息已<font color='red'>有学生报名</font>,不能修改！");
			return false;
		}
		var url = 'dtjs_dxbmgl_dxpxglXg.do?id=' + rows[0]["id"];
		var title = "修改党校培训信息";
		showDialog(title, 700, 250, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//删除
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var ids='';
		var sjyls=0;
		var gxts=rows.length;
		for(var i=0;i<gxts;i++){
			if(rows[i]["sjly"]=='0'){
				ids+=rows[i]["id"];
			}else{
				sjyls++;
			}
		}
		if(sjyls==gxts){//所选都不能删除
			showAlertDivLayer("勾选项目中均有学生报名,不能删除！");
			return false;
		}else if(sjyls>0&&sjyls<gxts){
			showConfirmDivLayer("勾选项目中包含学生报名项,确定只删除未报名的记录吗？", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxglSc.do", {values : ids}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data+"&nbsp;</font>条数据";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}else{
			showConfirmDivLayer("您确定要删除选择的记录吗？", {
				"okFun" : function() {
				jQuery.post("dtjs_dxbmgl_dxpxglSc.do", {values : ids}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data+"&nbsp;</font>条数据";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
			});
		}

	}
}