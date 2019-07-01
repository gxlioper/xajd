var DCCLBH='xshjgl_list.do';

function add(){
	showDialog('增加',800,360,'rcsw_xshjgl.do?method=Xshjgladd');
}
function save(){
	var xh=jQuery("#xh").val();
	var qrzt=jQuery("#qyzt").val();
	var qysj=jQuery("#qysj").val();
	if(xh==null||xh==""||qrzt==null||qrzt==""||qysj==null||qysj==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	
	var url="rcsw_xshjgl.do?method=Xshjgladd&type=save";
	ajaxSubFormWithFun("xshjglForm",url,function(data){
   	 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }else if(data["success"] == "false"){
   		if("0" == qrzt) {
   			showAlert("该学生已存在迁入记录!");
   	   		return false;
   		}else if ("1" == qrzt){
   			showAlert("该学生已存在迁出记录!");
   			return false;
   		}
   	 }else{
   		showAlert(data["message"]);
   	 }
	});
		
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改学生户籍信息',800,340,'rcsw_xshjgl.do?method=Xshjgledit&hjglid='+rows[0]["hjglid"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	} else {
		showDialog('查看学生户籍信息',800,340,'rcsw_xshjgl.do?method=Xshjglview&hjglid='+rows[0]["hjglid"]);
	}
}
function viewXshjgl(hjglid,cellValue){
	
	showDialog('查看学生户籍信息',800,340,'rcsw_xshjgl.do?method=Xshjglview&hjglid='+hjglid+"&xh="+cellValue,null);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("rcsw_xshjgl.do?method=Xshjgldel",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var qyzt=jQuery("#qyzt").val();
	var qysj=jQuery("#qysj").val();
	if(qyzt==null||qyzt==""||qysj==null||qysj==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	
	var url="rcsw_xshjgl.do?method=Xshjgledit&type=save";
	ajaxSubFormWithFun("xshjglForm",url,function(data){
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
	var url = "rcsw_xshjgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function importConfig(){
	toImportDataNew("IMPORT_RCSW_XSHJGL");
	return false; //导入配置
}