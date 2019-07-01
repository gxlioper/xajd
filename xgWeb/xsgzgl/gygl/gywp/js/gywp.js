var DCCLBH="gygl_qywpxx.do";

function cxGywpxxList(pk){
	document.forms[0].action="gygl_qywpxx.do?method=cxGywpxxList&pk="+escape(pk);
	document.forms[0].submit();
}
//增加物品维护
function addGywpxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("请选择您要维护物品的寝室！");
		return false;
	}
	showDialog('增加公寓物品',800,375,'gygl_qywpxx.do?method=addGywpxx&ids='+escape(ids.toString()));
}
//保存物品维护
function saveAdd(){
	var result=checkNotNull("wpmc-wpdldm-wplbdm-sl");
	if(!result){
		showAlert("请填写带*号的字段");
		return false;
	}
	var url="gygl_qywpxx.do?method=saveAdd";
	ajaxSubFormWithFun("gywpxxForm",url,function(data){
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
function updateGywpxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length!=1){
		showAlert("请选择一条您要修改的记录！");
	} else {
		showDialog('修改公寓物品信息',800,375,'gygl_qywpxx.do?method=updateGywpxx&id='+rows[0]["id"]);
	}
}
//保存修改
function saveUpdate(){
	var result=checkNotNull("wpdldm-wplbdm-sl");
	if(!result){
		showAlert("请填写带*号的字段");
		return false;
	}
	var url="gygl_qywpxx.do?method=saveUpdate";
	ajaxSubFormWithFun("gywpxxForm",url,function(data){
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

//删除
function delGywpxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gygl_qywpxx.do?method=delGywpxx",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//查看
function viewGywpxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条您要查看的记录！");
		return false;
	} else {
		showDialog('查看公寓物品信息',800,375,'gygl_qywpxx.do?method=viewGywpxx&id='+rows[0]["id"]);
	}
}

function gywpxxView(id){
	showDialog('查看公寓物品信息',800,375,'gygl_qywpxx.do?method=viewGywpxx&id='+id);
}

//返回
function back(){
	var url="gygl_qywpxx.do?method=cxGywplrxxList";
	refreshForm(url);
}

//改变是否完好字段
function changeSfwh(){
	var sfwh=jQuery("#sfwh").val();
	var hhyy=jQuery("#hhyy");
	if(sfwh=="是"){
		hhyy.val("");
		hhyy.attr("readonly",true);
	}else if(sfwh=="否"){
		hhyy.removeAttr("readonly");
	}
	
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "gygl_qywpxx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}