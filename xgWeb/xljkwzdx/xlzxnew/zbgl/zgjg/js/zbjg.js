/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();;
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 上报
	 * @return
	 */
	function sb(){
		var rows = jQuery("#dataTable").getSeletRow();
		showDialog('上报',680,550,'xlzxnew_zbjg.do?method=addZbjg');
	}
	
	
	/**
	 * 修改
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else{
			if(rows[0]['sjly'] == "1"){
				showAlertDivLayer("审核流程过来的数据不能修改！");
				return false;
			}
			showDialog('修改',680,500,'xlzxnew_zbjg.do?method=editZbjg&sbjgid=' + rows[0]['sbjgid']);
		}
	}



	/**
	 * 链接
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){

		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,500 , 'xlzxnew_zbjg.do?method=ckZbjg&sbjgid=" + rowObject['sbjgid'] + "')" + "\"";
		if(rowObject['sbjgid'] == '' || rowObject['sbjgid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('信息未上报！')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	function query(){
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
		jQuery("#dataTable").reloadGrid(map);
	}
	
	//删除结果
	function del() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("请选择您要删除的记录！");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			for(var i=0;i<ids.length;i++){
				if(rows[i]['sjly']=='1'){
					showAlertDivLayer("审核流程过来的记录不能删除！");
					return false;
				}
			}
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xlzxnew_zbjg.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	var DCCLBH = "xg_xlzxnew_zbjg.do";//dcclbh,导出功能编号

	//自定义导出 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport(DCCLBH, jgExportData);
	}

	//导出方法
	function jgExportData() {
		setSearchTj();//设置高级查询条件
		var url = "xlzxnew_zbjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}