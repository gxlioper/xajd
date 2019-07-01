var gndm = "tyxs_query";

function addZzjg(){	
	showDialog("增加",800,500,"tyxs_zzjg.do?method=addZzjg");
}

function editZzjg(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if (rows[0]["sqid"] != null){
			showAlertDivLayer("流程数据不能修改！");
			return false;
		}
		
		showDialog("修改",800,500,"tyxs_zzjg.do?method=editZzjg&id="+rows[0]["id"]);
	}
}

function delZzjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]["sqid"] != null) {
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("tyxs_zzjg.do?method=delZzjg",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//导出
function exportConfig(){
	var DCCLBH='tyxs_zzjg.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='tyxs_zzjg.do';
	setSearchTj();//设置高级查询条件
	
	var url = "tyxs_zzjg.do?method=dcjg&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 查看申请表
 * @param id
 */
function ckZzjg(id){
	showDialog("查看资助表",800,450,"tyxs_zzjg.do?method=ckZzjg&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//贷款审核信息
function onShow(gndm) {
	var url = "tyxs_zzjg.do?method=Zzxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			id : jQuery("#id").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data);
		}
	});
}



//验证贷款总额
function getzje(obj){
	checkMoney(obj);
	var sqxfzj = jQuery("#sqxfzj").val();//总计
	var dyzzxf = jQuery("#dyzzxf").val();//第一年
	var dezzxf = jQuery("#dezzxf").val();//第二年
	var dszzxf = jQuery("#dszzxf").val();//第三年
	var dsizzxf = jQuery("#dsizzxf").val();//第四年
  var dqdks = obj.value;
  var dkzs=0;
	if (null !=dyzzxf && ''!= dyzzxf) {
		
	  dkzs = parseInt(dyzzxf)+parseInt(dkzs);
	}
	if (null != dezzxf && ''!= dezzxf) {
	  dkzs = parseInt(dezzxf)+parseInt(dkzs);		
	  }
	if (null !=dszzxf && ''!= dszzxf) {
	  dkzs = parseInt(dszzxf)+parseInt(dkzs);
	  }
	if (null !=dsizzxf && ''!= dsizzxf) {
		  dkzs = parseInt(dsizzxf)+parseInt(dkzs);
		  }
	
	jQuery("#sqxfzj").val(parseInt(dkzs));
	
}





function saveZzsq(url){

	var xh= jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	
	
	// 检查学号是否存在
	if (xh=="" ){
		showAlertDivLayer("[学号]不许为空!");
		return false;
	}
	if(jQuery("#xxdm").val() == '10511'){
		
		var dklx = jQuery("#dklx").val();
		var dkbj = jQuery.trim(jQuery("#dkbj").val());
		if(dklx == ""){
			showAlert("[贷款类型]不许为空!");
			return false;
		}
		if(dkbj == ""){
			showAlert("[贷款金额]不许为空!");
			return false;
		}
	}

	if(!checkNull('xn-sqxfzj-sqly-sqsj') ){
				return false;
				
	}
	
	if(checksqlylength() == false){
		return false;
	}
	
	jQuery.post("tyxs_zzjg.do?method=getCountByXhAndXn",{xh:xh,xn:xn,id:jQuery("#id").val()},function(data){
			
			if (Number(data) == 0){
				ajaxSubFormWithFun("tyxsZzjgForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			} else {
				showAlertDivLayer("该学年已经申请过学费资助，请确认！");
			}
		},"json");
		

					
}