//第一次加载
var isFirst=true;
//结果查询页面
var gridSetting = {
    pager:"pager",
	url:"rcsw_kqgl_kqgljg.do?method=viewKqjgList&type=query",
	colList:[
		{label:'key',name:'id', index: 'id',key:true,hidden:true},
		{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
		{label:'姓名',name:'xm', index: 'xm',width:'10%'},
		{label:'性别',name:'xb', index: 'xb',width:'6%'},
		{label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
		{label:'专业',name:'zymc', index: 'zydm',width:'12%'},
		{label:'学院',name:'xymc', index: 'xydm',width:'12%'},
		{label:'考勤日期',name:'kqrq', index: 'kqrq',width:'12%'},
		{label:'缺勤天数',name:'qqts', index: 'qqts',width:'15%'},
		{label:'缺勤类别',name:'kqlbmc', index: 'kqlbdm',width:'15%'}
	],
	sortname: "kqrq",
 	sortorder: "desc"
};


//学号链接
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


function KqjgView(id,xh){
	showDialog("考勤结果查询",800,470,"rcsw_kqgl_kqgljg.do?method=viewKqjg&id="+id+"&xh="+xh);
}

//保存
function saveForm(method,type){
    var qqlb = jQuery("#kqlbdm").find("option:selected").text();
	if("病假"!=qqlb){
		if (!checkNull("xh-kqrq-kqlbdm-qqts-kkjs")) {
			 return false;
		 }
	}else{
		 if (!checkNull("xh-kqrq-kqlbdm-qqts-kkjs-qkjblbdm-ybqkjbdm-dqztdm")) {
			 return false;
		 }
	}
	if(!checkother()){
		return false;
	} 
	 var url = "rcsw_kqgl_kqgljg.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqgljgForm",url,function(data){
    	  if (data["success"] == "false"){
    		  showAlert("该学生的"+jQuery("#kqlbdm").find("option:selected").text()+"在"+jQuery('#kqrq').val()+"已维护" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
     });
}
function checkother(){
	if(chkNumInputForThis("qqts")){
		showAlert("缺勤天数必须为数字!");
		return false;
	}
	
	if(chkNumInputForThis("kkjs")){
		showAlert("旷课节数必须为数字!");
		return false;
	}
	return true;
}
function chkNumInputForThis(obj){
	//这里是共用js 处理不同页面 有些对象不存在的问题
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
}
//保存
function updateForm(method,type){
    var qqlb = jQuery("#kqlbdm").val();
	if("病假"!=qqlb){
		if (!checkNull("qqts-kkjs")) {
			 return false;
		 }
	}else{
		 if (!checkNull("qqts-kkjs-qkjblbdm-ybqkjbdm-dqztdm")) {
			 return false;
		 }
	}
	if(!checkother()){
		return false;
	}
	 var url = "rcsw_kqgl_kqgljg.do?method="+method+"&type="+type;
     ajaxSubFormWithFun("KqgljgForm",url,function(data){
    	  if (data["success"] == "false"){
    		  showAlert("该学生的"+jQuery("#kqlbmc").text()+"在"+jQuery('#kqrq').val()+"已维护" );
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
	var url = "rcsw_kqgl_kqgljg.do?method=addKqjg";
	var title = "增加考勤信息";
	showDialog(title,800,430,url);
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_kqgl_kqgljg.do?method=updateKqjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
		var title = "修改考勤信息";
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
			jQuery.post("rcsw_kqgl_kqgljg.do?method=deleteKqjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//导出
function exportConfig(){
	var DCCLBH='rcsw_kqgl_kqgljg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_kqgl_kqgljg.do';
	setSearchTj();//设置高级查询条件
	var url = "rcsw_kqgl_kqgljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_RCSW_KQGLJG");
	return false;
}