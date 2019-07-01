var gndm = "zxdk_query";

function addDkjg(){
	showDialog("增加",800,500,"zxdkDkjg.do?method=addDkjg");
}

function editDkjg(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(jQuery("#xxdm").val() != "10704"){			
			if (rows[0]["sjly"] == "sqsh"){
				showAlertDivLayer("流程数据不能修改！");
				return false;
			}
		}
		showDialog("修改",800,500,"zxdkDkjg.do?method=editDkjg&id="+rows[0]["id"]);
	}
}

function delDkjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['sjly'] == 'sqsh') {
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("zxdkDkjg.do?method=delDkjg",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//导出
function exportConfig(){
	var DCCLBH='zxdk_xyddkjg.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_xyddkjg.do';
	setSearchTj();//设置高级查询条件
	
	var url = "zxdkDkjg.do?method=dcjg&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 查看申请表
 * @param id
 */
function ckDkjg(id){
	showDialog("查看申请表",800,500,"zxdkDkjg.do?method=ckDkjg&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//贷款审核信息
function onShow(gndm) {
	var url = "zxdkDkjg.do?method=dkxx";
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
	var xfdks = jQuery("#xfdks").val();//学费贷款数
	var zsfdks = jQuery("#zsfdks").val();//住宿费贷款数
	var shfdks = jQuery("#shfdks").val();//生活费贷款数
	var dkqx = jQuery("#dkqx").val();
	var xzf = jQuery("#xzf").val();
	var xxdm = jQuery("#xxdm").val();
  var dqdks = obj.value;
  var dkzs=0;
  
 
	if (null !=xfdks && ''!= xfdks) {
		
	  dkzs = parseInt(xfdks)+parseInt(dkzs);
	}
	if (null != zsfdks && ''!= zsfdks) {
	  dkzs = parseInt(zsfdks)+parseInt(dkzs);		
	  }
	if (null !=shfdks && ''!= shfdks) {
	  dkzs = parseInt(shfdks)+parseInt(dkzs);
	  }
	
	if(dkzs>parseInt(jQuery("#dkzesx").val())){
		showAlertDivLayer("每年贷款总额超过"+jQuery("#dkzesx").val()+"元!",{},{"clkFun":function(){
			jQuery("#mnje").val(dkzs);
			if(null == xzf || ''== xzf){
				jQuery("#dkje").val(parseInt(dkzs));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
			}
			
			obj.focus();
		}});
	}else{
		jQuery("#mnje").val(dkzs);
		if((null != xzf && ''!= xzf)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
			jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
		}
		else{
			jQuery("#dkje").val(parseInt(dkzs));
		}
	}
	
}