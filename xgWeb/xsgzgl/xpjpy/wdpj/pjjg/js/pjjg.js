//加载学生信息
function getStuInfo(){
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(jQuery("#xh").val());
	var url="xpj_pjjg.do?method=addPjxmjg";
	jQuery.getJSON(url,parameter,
			function(data){
				if(data!=null){
					jQuery("#xm").html(data.xm);
					jQuery("#nj").html(data.nj);
					jQuery("#xymc").html(data.xymc);
					jQuery("#zymc").html(data.zymc);
					jQuery("#bjmc").html(data.bjmc);
					jQuery("#zzmm").html(data.ldmc);
					jQuery("#mz").html(data.qsh);
					jQuery("#yhmc").html(data.cwh);
					jQuery("#yhkh").html(data.qsdh);
					changeTqs();
				}
			}
		);
	jQuery.ajaxSetup({async:true});
}

//保存
function saveForm(){
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var sqsj = jQuery("#sqsj").val();
	var xxdm=jQuery("#xxdm").val();
	var sqly=jQuery("#sqly").val();
	if("" == sqsj){
		showAlert("申请时间不能为空");
		return false;
	}
	if("" == xn){
		showAlert("学年不能为空");
		return false;
	}
	if("" == xn||"" == lxdm|| "" == xzdm|| "" == xmmc){
		showAlert("请将申请信息当中带*号的填写完整");
		return false;
	}
	if("12056"==xxdm&&""==sqly){
		showAlert("请将申请信息当中带*号的填写完整");
		return false;
	}
	if (jQuery("#sqly").val().length > 500){
		showAlert("申请理由最大字数为500，现已经超过，请确认！！");
		return false;
	}
	 var url = "xpj_pjjg.do?method=addPjxmjg&type=save";
      ajaxSubFormWithFun("pjxmjgForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  var msg = "该学生在"+jQuery.trim(xn)+"学年，";
    		  if (jQuery("#xq option:selected").text() != ""){
    			  msg += jQuery("#xq option:selected").text() + "学期，";
    		  }
    		  msg += "已获得"+jQuery.trim(xmmc)+"。";
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


//增加
function add(){
	var url = "xpj_pjjg.do?method=addPjxmjg&xzdm="+jQuery("#xzdm").val();
	var title = "增加评奖结果信息";
	showDialog(title,800,540,url);
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'xpj_pjjg.do?method=updatePjxmjg&id='+rows[0]["id"]+'&xh='+rows[0]["xh"];
		var title = "修改评奖信息";
		showDialog(title,800,540,url);
	}
}

//删除记录
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
			jQuery.post("xpj_pjjg.do?method=delPjxmjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//导出
function exportConfig(){
	var DCCLBH='pj_pjjg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='pj_pjjg.do';
	setSearchTj();//设置高级查询条件
	var url = "xpj_pjjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//评奖结果查看

function knsView(id,xh){
	showDialog("评奖结果查询",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

//高级查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function hjwhDr(){
	var pjzq=jQuery("#pjzq").val();
	if(""==pjzq){
	toImportDataNew("IMPORT_HJWHDR_11318_XN");
		
	}else{
	toImportDataNew("IMPORT_HJWHDR_11318_XQ");
	}
	return false;
}
