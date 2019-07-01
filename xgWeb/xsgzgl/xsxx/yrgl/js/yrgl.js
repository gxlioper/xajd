var DCCLBH='yrgl_list.do';

function add(){
	showDialog('增加蕴瑞管理信息',800,360,'xsxx_yrgl.do?method=Yrgladd');
}
function save(){
	var xh=jQuery("#xh").val();
	var xn = jQuery('#xn').val();
	if(xh==null||xh==""||xn==null||xn==""){
		showAlert("请填写带*号的字段");
		return false;
	}	
	var url="xsxx_yrgl.do?method=Yrgladd&type=save";
	ajaxSubFormWithFun("yrglForm",url,function(data){
   	 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else if (data["success"] == "false"){
		  showAlert("该学生在当前学年已存在！");
	  } else {
		  showAlert(data["message"],{},{"clkFun":function(){
  			if (parent.window){
				 refershParent();
  			}
		  }});
   	 }
	});		
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改蕴瑞管理信息',800,340,'xsxx_yrgl.do?method=Yrgledit&guid='+rows[0]["guid"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	} else {
		showDialog('查看蕴瑞管理信息',800,340,'xsxx_yrgl.do?method=Yrglview&guid='+rows[0]["guid"]);
	}
}
function Yrglview(guid,cellValue){
	showDialog('查看蕴瑞管理信息',800,340,'xsxx_yrgl.do?method=Yrglview&guid='+guid+"&xh="+cellValue,null);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xsxx_yrgl.do?method=Yrgldel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var xh=jQuery("#xh").val();
	var xn = jQuery('#xn').val();
	if(xh==null||xh==""||xn==null||xn==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	
	var url="xsxx_yrgl.do?method=Yrgledit&type=save";
	ajaxSubFormWithFun("yrglForm",url,function(data){
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

function exportConfig(){     //导出
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xsxx_yrgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function importConfig(){
	toImportDataNew("IMPORT_N711901_YRGL");
	return false; //导入配置
}