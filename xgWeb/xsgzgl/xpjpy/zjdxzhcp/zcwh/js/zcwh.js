/*参评人LINK*/
function cprLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewZcmdxx(\""+rowObject["id"]+"\",\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/*点击参评人查看详情*/
function viewZcmdxx(id,xmdm) {
	var url = "xpjpy_zcwh.do?method=zcwhView&id=" + id + "&zcxmdmForTop=" + xmdm;
	showDialog("查看综测分",700,475,url);
}

/*综测维护LINK*/
function zcczLink(cellValue, rowObject) {
	if(rowObject["tjzt"] != "1"){
		return "<a href='javascript:void(0);' class='name' onclick='zccz(\""+rowObject["id"]+"\",\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>数据维护</a>";
	}else{
		return "<font color='red'>数据已维护!</font>";
	}
}
/*点击综测操作*/
function zccz (id,xmdm){
	/*取后台传回来的参数设置学年*/
	var xn = jQuery("#xn").val();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	var url = "xpjpy_zcwh.do?method=zcwhEdit&id="+id.toString()+"&xn=" + xn + "&zcxmdmForTop=" + xmdm;
	document.location.href=url;
}

/**
 * 综测名单及分数维护
 * @return
 */
function zcwhEdit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	/*取后台传回来的参数设置学年*/
	var xn = jQuery("#xn").val();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	
	if(ids.length != 1){
		showAlertDivLayer("请选择一条您要操作的记录！");
		return false;
	}else{
		var zcxmdmForTop = rows[0]["xmdm"];
		var url = "xpjpy_zcwh.do?method=zcwhEdit&id="+ids.toString()+"&xn="+xn + "&zcxmdmForTop=" + zcxmdmForTop;
		url = url+"&jsonStr="+ encodeURI("{}");
		/*已提交学院数*/
		var tjbj = 0;
		for(var i=0;i<rows.length;i++){
			if(rows[i]['tjzt']=='1'){
				tjbj++;
			}
		}
		if(tjbj==rows.length){
			showConfirmDivLayer("选中的记录<font color='blue'>已提交</font>，请重新选择",{"okFun":function(){
			}});
			return false;
		}else if(tjbj!=0){
			showConfirmDivLayer("会自动去除<font color='blue'>已提交</font>不可维护的学院",{"okFun":function(){
				document.location.href=url;
			}});
			return false;
		}
		document.location.href=url;
	}
}

/**
 * 综测学生名单查询
 * @return
 */
function doQuery(){

	var jsonStr = jQuery("#jsonStr").val();
	var map = {};
	if(jsonStr){
		map = JSON.parse(jsonStr);
	}
	map["id"] = jQuery("#id").val();
	map["xn"] = jQuery("#xn").val();
	map["xh"] = jQuery("#xh").val();
	jQuery("#dataTable").reloadGrid(map);
	
}

/**
 * 查看综测分
 * @returns {Boolean}
 */
function zcwhView(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var zcxmdmForTop = rows[0]["xmdm"];
	if (rows.length != 1){
		showAlertDivLayer("请选择一个您要查看的学院！");
		return false;
	} 
	var id = rows[0]["id"];
	var url = "xpjpy_zcwh.do?method=zcwhView&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	showDialog("查看综测分",700,475,url);
}

/**
 * 新评奖评优-综合测评-综测维护
 * 综测分输入框 上、下、左、右键事件
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//左   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	//上      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	//右   或者 回车
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	//下 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
}

/**
 * 实时保存综测分
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfs(obj,xmdm,xh){
	var xn = jQuery("#xn").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("录入分数不能大于最大分："+max,{},{"clkFun":function(){
		}});
		return false;
	}
	if (min != "" && Number(fs) < Number(min) && Number(fs) != ""){
		showAlertDivLayer("录入分数不能低于最小分："+min,{},{"clkFun":function(){
		}});
		return false;
	}
	jQuery.post("xpjpy_zcwh.do?method=saveZcfs",{xn:xn,xmdm:xmdm,xh:xh,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * 增加参评学生
 * @return
 */
function cpxsAdd(){
	var url = "xpjpy_zcwh.do?method=cpxsAdd";
	var title = "增加参评学生";
	showDialog(title,800,550,url);
}

/**
 * 取消、删除参评学生
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消参评资格的学生！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		/*循环选中记录是有有已提交数据，如果有则不能删除*/
		for(var i=0;i<ids.length;i++){
			if(rows[i]['tjzt']=='1'){
				showAlertDivLayer("提交的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定取消选中学生的参评资格吗？",{"okFun":function(){
			jQuery.post("xpjpy_zcwh.do?method=cpxsDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 综测提交
 */
function cpxsTj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['tjzt']='tj';
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpjpy_zcwh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("您确定提交当前学生吗？",{"okFun":function(){
					jQuery.post("xpjpy_zcwh.do?method=tjCpxs",map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json'); 
				}});
			} else {
				showAlertDivLayer("学生综测成绩录入完成后可提交，目前仍有学生的综测分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * 取消提交
 */
function cpxsQxtj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values'] = ids.toString();
	map['tjzt'] = 'qxtj';
	showConfirmDivLayer("您确定取消提交当前学生吗？",{"okFun":function(){
		jQuery.post("xpjpy_zcwh.do?method=tjCpxs",map,function(data){
			showAlertDivLayer("取消提交成功");
			jQuery("#dataTable").reloadGrid();
		},'json'); 
	}});
}

/**
 * 综测分导出
 * @return
 */
var DCCLBH = 'xpjpy_zhcp_zcwh.do';
function cpxsExport() {
	var jsonStr = jQuery("#jsonStr").val();
	var xhxm = jQuery("#xh").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var url = "xpjpy_zcwh.do?method=cpxsExport&dcclbh="+DCCLBH + "&xh="+xhxm + "&xm="+xhxm + "&zcxmdmForTop"+zcxmdmForTop;
	url = addSuperSearchParams(url,map)
	jQuery("#zcwhForm").attr("target","_blank");
	jQuery("#zcwhForm").attr("action",url);
	jQuery("#zcwhForm").submit();
}



/**
 * 综测分导入
 * @return
 */
function cpxsImport(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var urlOne = "xpjpy_zcwh.do?method=checkDownload&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	var urlTwo = "xpjpy_zcwh.do?method=toImportZcfs&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop + "&jsonStr=" + jsonStr;
	/*避免导入数据超内存，控制下载条数*/
	jQuery.ajaxSetup({async:false});
	jQuery.post(urlOne,map,function(data){
		if (data == "true"){
			showDialog("综测分导入",550,250,urlTwo,{close:function(){
				if (jQuery("#search_go")){
					jQuery("#search_go").click();
				}
			}});
		} else {
			showAlertDivLayer("导入下载模板数据过多，超出<font color='red'>"+num+"</font>条，请增加查询条件后在进行导入操作！");						
		}
	});
	jQuery.ajaxSetup({async:true});
}

/**
 * 综测分导入模板
 * @return
 */
function downloadTemplate(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var url = "xpjpy_zcwh.do?method=downloadTemplate&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	url = addSuperSearchParams(url,map);// 设置高级查询参数
	jQuery("#zcwhForm").attr("target","_blank");
	jQuery("#zcwhForm").attr("action",url);
	jQuery("#zcwhForm").submit();
}

/**
 * 设置高级查询条件，导出方法中使用
 * @param url
 * @param map
 * @return
 */
function addSuperSearchParams(url,map){
	if(url.indexOf("?") > -1){
		url += "&";
	}else{
		url += "?";
	}
	url += "path=" + map.path + "&mhcx_lx=" + map.mhcx_lx;
	jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
	
	var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
	html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
	html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
	if (map.input_mhcx){
		html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
	}
	jQuery("#zcwhForm").append(html);
	return url;
}

/**
 * 导入综测分，上传文件
 * @return
 */
function uploadZcfs(){
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("导入文件只能为.xls格式,请确认！");
		return false;
	}
	
	url = addSuperSearchParams("xpjpy_zcwh.do?method=importZcfs&zcxmdmForTop=" + zcxmdmForTop,map);// 设置高级查询参数
	jQuery("#zcwhForm").attr("target","");
	jQuery("#zcwhForm").attr("action",url).submit();
}


/**
 * 综测分提交
 * @return
 */
function zcwhSubmit(){
	/*选择记录的ID*/
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var zcxmdmForTop = rows[0]["xmdm"];
		doSubmit(ids.toString(),zcxmdmForTop);
	}
}

/*
 * 执行综测分提交
 */
function doSubmit(id,zcxmdmForTop){
	var xn = jQuery("#xn").val();
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpjpy_zcwh.do?method=checkZcfSubmit",{id:id,xn:xn,zcxmdmForTop:zcxmdmForTop},function(data){
			if (data == "true"){
				showConfirmDivLayer("综测分提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
					jQuery.post("xpjpy_zcwh.do?method=submitXyzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							searchRs();
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("全部学生综测成绩录入完成后可提交，该学院仍有学生的综测分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * 取消提交
 * @return
 */
function zcwhCancelSubmit(){
	/*选择记录的ID*/
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("请选择一条您要操作的记录！");
	}else{
		var id = rows[0]["id"];
		if("0" == rows[0]["tjzt"]){
			showAlert("只能取消状态为已提交的记录！");
			return false;
		}
		showConfirmDivLayer("您确定取消提交当前记录吗？",{"okFun":function(){
			jQuery.post("xpjpy_zcwh.do?method=zcwhCancelSubmit",{id:id},function(data){
				showAlertDivLayer("取消提交成功!");
				jQuery("#dataTable").reloadGrid();
			},'json'); 
		}});
	}
}

/**
 * 一键同步综测分（5个单项）
 */
function dataSynchronization(){
	jQuery.post("xpjpy_zcwh.do?method=dataSynchronization",function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
			}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}


