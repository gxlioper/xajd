var DCCLBH='rcsw_xbzj.do';

function add(){
	showDialog('增加西部支教学生',800,360,'rcsw_xbzj.do?method=addXbzjxs');
}
function save(){
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var zjsj=jQuery("#zjsj").val();
	if(xh==null||xh==""||xn==null||xn==""||xq==null||xq==""||zjsj==null||zjsj==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	
	var url="rcsw_xbzj.do?method=addXbzjxs&type=save";
	ajaxSubFormWithFun("xbzjForm",url,function(data){
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
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改西部支教学生信息',800,340,'rcsw_xbzj.do?method=updateXbzjxs&id='+rows[0]["id"]);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	} else {
		showDialog('查看西部支教学生信息',800,340,'rcsw_xbzj.do?method=viewXbzjxs&id='+rows[0]["id"]);
	}
}

function viewXbzjxs(id){
	
	showDialog('查看西部支教学生信息',800,340,'rcsw_xbzj.do?method=viewXbzjxs&id='+id);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("rcsw_xbzj.do?method=delXbzjxs",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function saveUpdate(){
	var zjsj=jQuery("#zjsj").val();
	if(zjsj==null||zjsj==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	
	var url="rcsw_xbzj.do?method=updateXbzjxs&type=save";
	ajaxSubFormWithFun("xbzjForm",url,function(data){
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

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xbzj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
