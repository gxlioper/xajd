/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

/**
 * 增加基础数据
 * @return
 */
function zjJcsj(){
	var url = "jcsj.do?method=jcsjAdd";
	var title = "增加基础数据";
	showDialog(title,380,280,url);
}

 /**
  * 修改基础数据
  * @return
  */
 function xgJcsj(){
 	var rows = jQuery("#dataTable").getSeletRow();
 	if (rows.length != 1){
 		showAlertDivLayer("请选择一条您要修改的记录！");
 		return false;
 	} else {

		var xzflg = jQuery("#xzflg").val();
 		var url = "jcsj.do?method=jcsjUpd&xzflg="+xzflg;
		if("" ==xzflg || "0" ==xzflg){
			url += "&xydm="+rows[0]["xydm"];
		}else if("1" ==xzflg){
			url += "&zydm="+rows[0]["zydm"];
		}else if("2" ==xzflg){
			url += "&bjdm="+rows[0]["bjdm"];
		}
 		var title = "修改基础数据";
 		showDialog(title,380,280,url);
 	}
 }

  /**
   * 删除
   * @return
   */
  function scJcsj(){
	var rows = jQuery("#dataTable").getSeletRow();
  	if (rows.length == 0){
  		showAlertDivLayer("请选择您要删除的记录！");
  	} else {

		var xzflg = jQuery("#xzflg").val();
			
  		for(var i=0;i<rows.length;i++){
  			
  			if("" ==xzflg || "0" ==xzflg ){
  				
  				if(rows[0]["zys"]>0){
  					showAlert("该"+jQuery("#xbmc").val()+"已分配专业，不能删除！");
  	  				return false;
  				}
  			}else if("1" ==xzflg){
  				if(rows[0]["bjs"]>0){
  					showAlert("该专业已分配班级，不能删除！");
  	  				return false;
  				}
  			}else if("2" ==xzflg){

  				if(rows[0]["xss"]>0){
  					showAlert("该班级已分配学生，不能删除！");
  	  				return false;
  				}
  			}
  		}
  		var ids = jQuery("#dataTable").getSeletIds();
  		
  		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
  				jQuery.post("jcsj.do?method=jcsjDel",{xzflg:xzflg,values:ids.toString()},function(data){
  					showAlertDivLayer(data["message"]);
  					jQuery("#dataTable").reloadGrid();
  				},'json');
  		}});
  	}
  }

  	function exportConfig() {
		var xzflg = jQuery("#xzflg").val();
		var path = "jcsjcl.do?xzflg=0";
		if("1" ==xzflg){					
			path = "jcsjcl.do?xzflg=1";
		}else if("2" ==xzflg){	
			path = "jcsjcl.do?xzflg=2";
		}
		customExport(path, exportData,680,500);
	}
	// 导出方法
	function exportData() {

		var xzflg = jQuery("#xzflg").val();
		var path = "jcsjcl.do?xzflg=0";
		if("1" ==xzflg){					
			path = "jcsjcl.do?xzflg=1";
		}else if("2" ==xzflg){	
			path = "jcsjcl.do?xzflg=2";
		}
				
		setSearchTj();//设置高级查询条件
		var url = "jcsj.do?method=exportData&dcclbh="+path;//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	
