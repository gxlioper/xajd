var gridSetting = {
	caption:"辅导员培训结果列表",
	pager:"pager",
	url:"szdw_fdypxxmsh.do?method=fdypxjgList&type=query",
	colList:[
	   {label:'职工号',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
	   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'20%'},
	   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'20%'},
	   {label:'培训项目',name:'xmmc', index: 'xmmc',width:'20%'},
	   {label:'审核时间',name:'shsj', index: 'shsj',width:'20%'},
	   {label:'',name:'sqid', index: 'sqid',key:true,hidden:true}
	],
	sortname: "shsj",
 	sortorder: "asc"
}

function zghLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='viewjgz(\""+rowObject["sqr"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function viewjgz(zgh){
	var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
	showDialog('', 830, 500, url);
}


function fdyxxwhExportConfig() {
	customExport("szdw_fdypxxmsh.do", fdyxxwhExportData);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 导出方法
function fdyxxwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "szdw_fdypxxmsh.do?method=fdypxjgExportData&dcclbh=" + "szdw_fdypxxmsh.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function go_ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	}  else{
		showDialog("辅导员培训结果查看",760,500,'szdw_fdypxxmsh.do?method=fdypxxmsh&type=ck&sqid='+rows[0]["sqid"]+"&tt="+new Date().getTime());
	}
}