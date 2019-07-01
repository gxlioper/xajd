/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 上报
	 * @return
	 */
	function sb(){
		showDialog('上报',680,500,'xlzxnew_ybsb.do?method=addYbsb');
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
			var shzt = rows[0]['shzt'];
			if(shzt == '' || shzt == null){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			if(shzt != '0' && shzt != '3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			showDialog('修改',680,500,'xlzxnew_ybsb.do?method=editYbsb&sbid=' + rows[0]['sbid']);
		}
	}

	/**
	 * 撤销
	 * @return
	 */
	function cancle(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条要撤销的记录！");
		} else {
			for(var i=0;i<rows.length;i++){
				if(rows[i]['shzt'] != '5'){
					showAlertDivLayer("只有审核中的记录才能被撤销！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要撤销选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("xlzxnew_ybsb.do?method=cancel", {
						sbsqid : rows[0]['sbid'],
						splcid : rows[0]['splcid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
	}

	/**
	 * 流程跟踪
	 * @return
	 */
	function lcinfo(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条记录！");
		} else {
			if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="" || rows[0]["shzt"]==null){
				showAlertDivLayer("请选择已提交的记录！");
				return false;
			}
			showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * 提交
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlert("请选择一条要提交的记录！");
			return false;
		} else {
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlert("请选择未提交或者已退回的记录！");
				return false;
			}
			
			showConfirmDivLayer("您确定要提交选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("xlzxnew_ybsb.do?method=submit", {
						sbsqid : rows[0]['sbid'],
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
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
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,500 , 'xlzxnew_ybsb.do?method=ckYbsb&sbid=" + rowObject['sbid'] + "')" + "\"";
		if(rowObject['sbid'] == '' || rowObject['sbid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('信息未上报！')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}
	
	//删除结果
	function del() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("请选择您要删除的记录！");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			for(var i=0;i<ids.length;i++){
				if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3'){
					showAlertDivLayer("请选择未提交或者已退回的记录！");
					return false;
				}
			}
			showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xlzxnew_ybsb.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	

