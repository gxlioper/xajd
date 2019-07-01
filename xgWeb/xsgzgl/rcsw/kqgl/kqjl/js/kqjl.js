//结果查询页面
var gridSetting = {
    pager:"pager",
	url:"kqgl_kqjl.do?method=viewKqjlList&type=query",
	colList:[
	    {label:'学年',name:'xn', index: 'xn',width:'11%'},
	    {label:'学期',name:'xq', index: 'xq',width:'10%'},	
		{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'姓名',name:'xm', index: 'xm',width:'8%'},
		{label:'楼栋',name:'ldmc', index: 'ldmc',width:'10%'},
		{label:'寝室号',name:'qsh', index: 'qsh',width:'10%'},
		{label:'考勤时间',name:'kqsj', index: 'kqsj',width:'16%'},
		{label:'考勤类别',name:'kqlb', index: 'kqlb',width:'10%'},
		{label:'打卡状态',name:'dkzt', index: 'dkzt',width:'10%'}
	],
	sortname: "kqsj",
 	sortorder: "desc"
};

//学号链接
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""+rowObject["kqsj"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function KqjgView(kqsj,xh){
	showDialog("考勤结果查询",800,450,"kqgl_kqjl.do?method=viewKqjl&kqsj="+kqsj+"&xh="+xh);
}
//导出
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqjl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqjl.do';
	setSearchTj();//设置高级查询条件
	var url = "kqgl_kqjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

