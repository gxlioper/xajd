
var gridSetting = {
		caption : "考核对象列表",
		radioselect:true,
		pager : "pager",
		url : "khglPfxq.do?method=khxqlList&type=query",
		colList : [ 
		   {label : 'xmid',name : 'xmid',index : 'xmid',hidden:true},
		   {label : 'khbid',name : 'khbid',index : 'khbid',hidden:true}, 
		   {label : 'pfr',name : 'pfr',index : 'pfr',hidden:true},
		   {label : 'khdxr',name : 'khdxr',index : 'khdxr',hidden:true},
		   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%'}, 
		   {label : '考核表',name : 'khbmc',index : 'khbmc',width : '20%'}, 
		   {label : '考核对象',name : 'khdxmc',index : 'khdxmc',width : '10%'},
		   {label : '评分人',name : 'pfrxm',index : 'pfrxm',width : '10%'},
		   {label : '评分时间',name : 'tjsj',index : 'tjsj',width : '15%'},
		   {label : '总分',name : 'zf',index : 'zf',width : '5%'}
		   ],
		sortname : "tjsj",
		sortorder : "desc"
	}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

//高级查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
			
/**
 * 评分详情
 * @return
 */
function viewPf(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		showDialog("查看",800,520,"khglKhpf.do?method=viewPf&xmid="+rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]);
	}
	
}


//导出
function exportConfig(){
	var DCCLBH='khgl_pfxq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='khgl_pfxq.do';
	setSearchTj();//设置高级查询条件
	var url = "khglPfxq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}