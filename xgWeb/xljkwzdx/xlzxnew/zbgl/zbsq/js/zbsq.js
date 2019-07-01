/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 上报
	 * @return
	 */
	function sb(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一个上报周次！");
			return false;
		} else{
			if(rows[0]["zbksrq"] > jQuery("#nowtime").val() || jQuery("#nowtime").val() > rows[0]["zbjsrq"]){
				showAlertDivLayer("不在周报日程上报时间中！");
				return false;
			}
				var shzt = rows[0]['shzt'];
				if(shzt != '' && shzt != null){
					showAlertDivLayer("请选择未上报的周次！");
					return false;
				}
				showDialog('上报',680,500,'xlzxnew_zbsb.do?method=addZbsb' + "&sbzbid=" + rows[0]['zbid']);
		}
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
			showDialog('修改',680,500,'xlzxnew_zbsb.do?method=editZbsb&sbsqid=' + rows[0]['sbsqid']);
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
					jQuery.post("xlzxnew_zbsb.do?method=cancel", {
						sbsqid : rows[0]['sbsqid'],
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
			showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * 提交
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条要提交的记录！");
			return false;
		} else {
			if(rows[0]['shzt'] == null || rows[0]['shzt']== ''){
				showAlertDivLayer("请选择已上报的数据进行提交操作！");
				return false;
			}
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			
			showConfirmDivLayer("您确定要提交选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("xlzxnew_zbsb.do?method=submit", {
						sbsqid : rows[0]['sbsqid'],
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
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,500 , 'xlzxnew_zbsb.do?method=ckZbsb&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
		if(rowObject['sbsqid'] == '' || rowObject['sbsqid'] == null){
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