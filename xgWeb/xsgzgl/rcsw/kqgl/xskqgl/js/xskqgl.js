//第一次加载
var isFirst=true;
//结果查询页面
var gridSetting = {
	caption:"综测排名组",
	pager:"pager",
	url:"rcsw_kqgl_xskqgl.do?method=viewKqdjList&type=query",
	colList:[
		{label:'key',name:'kqdjid', index: 'kqdjid',key:true,hidden:true},
		{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'姓名',name:'xm', index: 'xm',width:'10%'},
		{label:'性别',name:'xb', index: 'xb',width:'3%'},
		{label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
	    {label:'学年',name:'xn', index: 'xn',hidden:true},
	    {label:'学期',name:'xqmc', index: 'xq',hidden:true},
	    {label:'学年学期',name:'xnxq', index: 'xn,xq',width:'13%',formatter:xnxq},
		{label:'考勤课程',name:'kqkc', index: 'kqkc',width:'16%'},
		{label:'周次',name:'zc', index: 'zc',width:'8%',formatter:xszc},
		{label:'考勤时间',name:'kqsj', index: 'kqsj',width:'15%'},
		{label:'考勤类型代码',name:'kqlxdm', index: 'kqlxdm',hidden:true},
		{label:'考勤类型',name:'kqlxmc', index: 'kqlxdm',width:'10%'}
	],
	sortname: "kqsj",
 	sortorder: "desc"
};

function xnxq(cellValue, rowObject) {
	return rowObject["xn"] + " " +rowObject["xqmc"];
}

function xszc(cellValue, rowObject) {
	return "第" + cellValue + "周";
}

//学号链接
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneKqdjView(\""+rowObject["kqdjid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//费用发放结果查看
function oneKqdjView(kqdjid,xh){
	showDialog("考勤登记结果查询",800,380,"rcsw_kqgl_xskqgl.do?method=oneKqdjView&kqdjid="+kqdjid+"&xh="+xh);
}

//保存
function saveForm(method,type){

	 //学号-学年-学期-周次-考勤课程-上课地点-考勤时间-考勤类型
	 if (!checkNull("xh-xn-xq-zc-kqkc-kqsj-kqlxdm")) {
		 return false;
	 }

	 var url = "rcsw_kqgl_xskqgl.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqdjForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("该学生的"+jQuery("#kqlxdm>option:selected").text()+"在"+jQuery('#kqsj').val()+"已登记" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
     });
}

//增加
function add(){
	var url = "rcsw_kqgl_xskqgl.do?method=addKqdj";
	var title = "增加考勤登记信息";
	showDialog(title,800,430,url);
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_kqgl_xskqgl.do?method=updateKqdj&kqdjid='+rows[0]["kqdjid"]+'&xh='+rows[0]["xh"];
		var title = "修改考勤登记信息";
		showDialog(title,800,430,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("rcsw_kqgl_xskqgl.do?method=deleteKqdj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//导出
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqgl.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqgl.do';
	setSearchTj();//设置高级查询条件
	var url = "rcsw_kqgl_xskqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportData("IMPORT_RCSW_KQGL");
	return false;
}