function addDkxx(){
	showDialog("增加",800,520,"zxdkSyddk.do?method=addDkxx");
}

function editDkxx(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"] == "sqsh"){
			showAlertDivLayer("流程数据不能修改！");
			return false;
		}
		showDialog("修改",800,520,"zxdkSyddk.do?method=editDkxx&id="+rows[0]["id"]);
	}
}

function delDkxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}else {
		//结果删除判断bug修改，by yxx[1206]
	    var flag = true;
		jQuery(rows).each(function(i,row){
			if(row['sjly'] == "sqsh"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("流程数据无法删除！");
			return false;
		}
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("zxdkSyddk.do?method=delDkxx",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//导出
function exportConfig(){
	var DCCLBH='zxdk_syddk.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_syddk.do';
	setSearchTj();//设置高级查询条件
	
	var url = "zxdkSyddk.do?method=dcxx&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 查看申请表
 * @param id
 */
function ckDkxx(id){
	showDialog("查看申请表",800,520,"zxdkSyddk.do?method=ckDkxx&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/*
 * 验证贷款总额
 */
function checkZje(){
	var dkzs=jQuery("#dkje").val();
		if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
			showAlertDivLayer("贷款金额超过"+jQuery("#dkzesx").val()+"元!");
			jQuery("#dkje").focus();
				return false;
			}
	

}