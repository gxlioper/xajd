//查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xpjpy_pjjg.do?method=addPjjg";
	var title = "增加评奖结果信息";
	showDialog(title,800,520,url);
}

//增加保存
function saveFormAdd(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var wysp = jQuery("#wysp").val();
	var ssdh = jQuery("#ssdh").val();
	if( (xh == null || jQuery.trim(xh) == '')){
		showAlert("请选择学生！");
		return false;
	}
	if(xn == null || xn == ''){
		showAlert("请选择学年！");
		return false;
	}
	if(lxdm == null || lxdm == ''){
		showAlert("请选择项目类型！");
		return false;
	}
	if(xzdm == null || xzdm == ''){
		showAlert("请选择项目性质！");
		return false;
	}
	if(xmmc == null || xmmc == ''){
		showAlert("请填写项目名称！");
		return false;
	}
	if(sqsj == null || sqsj == ''){
		showAlert("请填写申请时间！");
		return false;
	}
	if( (wysp == null || jQuery.trim(wysp) == '')){
		showAlert("请填写外语水平！");
		return false;
	}
	var url = "xpjpy_pjjg.do?method=saveFormAdd";
	ajaxSubFormWithFun("pjjgForm", url, function(data) {
  	  if (data["success"] == "false"){
  		  var msg = "该学生在【"+jQuery.trim(xn)+"】学年，";
  		  msg += "已获得【"+jQuery.trim(xmmc)+"】。";
  		  showAlert(msg);
  	  } else {
  		  showAlert(data["message"],{},{"clkFun":function(){
      			if (parent.window){
      				refershParent();
      			}
		  }});
  	  }
    });
}

//删除评奖记录
function del(){
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
			jQuery.post("xpjpy_pjjg.do?method=delPjjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 生成优秀学生
 */
function scyxxs() {
    jQuery.post("xpjpy_pjjg.do?method=scyxxs",function(data){
        showAlertDivLayer(data["message"]);
        jQuery("#dataTable").reloadGrid();
    },'json');
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
	var url = 'xpjpy_pjjg.do?method=updatePjjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
	var title = "修改评奖信息";
	showDialog(title,800,520,url);
	}
}

//修改保存
function saveFormUpdate(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var wysp = jQuery("#wysp").val();
	var ssdh = jQuery("#ssdh").val();
	if( (xh == null || jQuery.trim(xh) == '')){
		showAlert("请选择学生！");
		return false;
	}
	if(xn == null || xn == ''){
		showAlert("请选择学年！");
		return false;
	}
	if(lxdm == null || lxdm == ''){
		showAlert("请选择项目类型！");
		return false;
	}
	if(xzdm == null || xzdm == ''){
		showAlert("请选择项目性质！");
		return false;
	}
	if(xmmc == null || xmmc == ''){
		showAlert("请填写项目名称！");
		return false;
	}
	if(sqsj == null || sqsj == ''){
		showAlert("请填写申请时间！");
		return false;
	}
	if( (wysp == null || jQuery.trim(wysp) == '')){
		showAlert("请填写外语水平！");
		return false;
	}
	if( (ssdh == null || jQuery.trim(ssdh) == '')){
		showAlert("请填写宿舍电话！");
		return false;
	}
	var url = "xpjpy_pjjg.do?method=saveFormUpdate";
	ajaxSubFormWithFun("pjjgForm", url, function(data) {
  	  if (data["success"] == "false"){
  		  var msg = "该学生在【"+jQuery.trim(xn)+"】学年，";
  		  msg += "已获得【"+jQuery.trim(xmmc)+"】。";
  		  showAlert(msg);
  	  } else {
  		  showAlert(data["message"],{},{"clkFun":function(){
      			if (parent.window){
      				refershParent();
      			}
		  }});
  	  }
    });
}

//学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewPjxxxx(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//点击学号查看详情
function viewPjxxxx(id,xh) {
	showDialog("查看评奖信息", 800, 500, "xpjpy_pjjg.do?method=viewPjjg&id="+id+"&xh="+xh);
}

//dcclbh,导出功能编号
var DCCLBH='xpjpy_wdpj_pjjg.do';
//导出
function exportConfig(){
	customExport(DCCLBH, exportData);
}
//导出方法
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_pjjg.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importPjjg(){
	toImportDataNew("IMPORT_N520303_XMJG");
	return false;
}