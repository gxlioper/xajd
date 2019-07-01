var gridSetting = {
    caption : "",
    pager : "pager",
    url : "dycjgl_dmwh.do?method=dmwhQuery",
    colList : [
        { label : '德育成绩类型代码', name : 'xmdm', index : 'xmdm', width : '10%' },
        { label : '德育成绩类型名称', name : 'xmmc', index : 'xmmc', width : '11%' },
        { label : '成绩合格分数判定', name : 'cjhgfsx', index : 'cjhgfsx', width : '6%' },
        { label : '评分说明', name : 'xmsm', index : 'xmsm', width : '15%' },
        { label : '显示序号', name : 'xsxh', index : 'xsxh',width : '7%' }
	],
    sortname : "xsxh",
    sortorder : "asc" };



//修改保存
function updSaveForm(){
	var ffxmmc=jQuery("#ffxmmc").val();
	if(ffxmmc==""){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "rcsw_fyff_ffxm.do?method=updateFfxm&type=update";
	ajaxSubFormWithFun("FyffxmForm",url,function(data){
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