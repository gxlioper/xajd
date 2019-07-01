var DCCLBH='szdw_fdyjtff.do';

function add(){
	showDialog('增加辅导员津贴发放',800,320,'szdw_fdyjtff.do?method=addFdyjtff');
}

function saveAdd(){
	var zgh=jQuery("#zgh").val();
	var bzlx=jQuery("#bzlx").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var bzje=jQuery("#bzje").val();
	if(zgh==null||zgh==""||bzlx==null||bzlx==""||xn==null||xn==""||xq==null||xq==""||bzje==null||bzje==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	var url="szdw_fdyjtff.do?method=addFdyjtff&type=save";
	ajaxSubFormWithFun("fdyjtffForm",url,function(data){
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
//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改辅导员津贴发放信息',800,320,'szdw_fdyjtff.do?method=updateFdyjtff&id='+rows[0]["id"]);
	}
}
function saveUpdate(){
	var zgh=jQuery("#zgh").val();
	var bzlx=jQuery("#bzlx").val();
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var bzje=jQuery("#bzje").val();
	if(zgh==null||zgh==""||bzlx==null||bzlx==""||xn==null||xn==""||xq==null||xq==""||bzje==null||bzje==""){
		showAlert("请填写带*号的字段");
		return false;
	}
	var url="szdw_fdyjtff.do?method=updateFdyjtff&type=save";
	ajaxSubFormWithFun("fdyjtffForm",url,function(data){
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
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("szdw_fdyjtff.do?method=delFdyjtff",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	} else {
		showDialog('查看辅导员津贴发放信息',800,320,'szdw_fdyjtff.do?method=viewFdyjtff&id='+rows[0]["id"]);
	}
}
function jtffView(id){
	showDialog('查看辅导员津贴发放信息',800,320,'szdw_fdyjtff.do?method=viewFdyjtff&id='+id);
}




function changbzlx(){
	// 潍坊学院 则不禁用
	if(jQuery("#xxdm").val() == '11067'){
		var kpdj=jQuery("#kpdj");
		kpdj.removeAttr("disabled");
		return;
	}
	var bzlx=jQuery("#bzlx").val();
	if(bzlx=="1"){
		var kpdj=jQuery("#kpdj");
		kpdj.removeAttr("disabled");
		
	}else {
		var kpdj=jQuery("#kpdj");
		kpdj.val("");
		kpdj.attr("disabled",true);
	}
}


function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "szdw_fdyjtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}