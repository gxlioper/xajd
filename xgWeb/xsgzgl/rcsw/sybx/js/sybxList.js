
	
	/**
     * 商业保险单个信息--查看
     * @param xh
     * @return
     */
    function viewSybx(guid,xh){
    	//showWindow("商业保险信息",710,530,"rcsw_sybx.do?method=viewSybx&guid="+guid+"&xh="+xh);
		showDialog("商业保险信息",720,440,"rcsw_sybx.do?method=viewSybx&guid="+guid+"&xh="+xh);
    }
	
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='viewSybx(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	
	/**
     * 商业保险单个信息--增加
     * @param xh
     * @return
     */
	function addSybx(){
		var url = "rcsw_sybx.do?method=addSybx";
		var title = "增加商业保险信息";
		//showWindow(title,720,560,url);
		showDialog('', 730, 510, url);
	}
	
	function searchRs(){
		var map = getSuperSearch();

		jQuery("#dataTable").reloadGrid(map);
	}
	
	/**
     * 商业保险单个信息--修改
     * @param xh
     * @return
     */
	function updateSybx(){
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
		} else {
			var url = 'rcsw_sybx.do?method=updateSybx&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
			var title = "修改商业保险信息";
			//showWindow(title,720,530,url);
			showDialog('', 730, 510, url);
		}
		
	}
	
	/**
     * 商业保险信息--删除
     * @param xh
     * @return
     */
	function delSybx(){
		var ids = jQuery("#dataTable").getSeletIds();

		if (ids.length == 0){
			showAlertDivLayer("请选择您要删除的记录！");
		} else {
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_sybx.do?method=deleteSybx",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
			}});
		}
	}
	
	/**
	 * 商业医疗保险维护 自定义导出
	 */
	function syylbxwhExportConfig() {
		customExport("rcsw_sybx_cx.do", syylbxwhExportData,1000,500);
	}
		
	
		
	// 导出方法
	function syylbxwhExportData() {
		setSearchTj();//设置高级查询条件
		var url = "rcsw_sybx.do?method=syylbxwhExportData&dcclbh=" + "rcsw_sybx_cx.do";//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	