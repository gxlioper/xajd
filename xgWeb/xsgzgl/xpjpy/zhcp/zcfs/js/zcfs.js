
var num = '10000'; //允许导入的信息条数

/**
 * 综测分录入
 * @returns {Boolean}
 */
function editZcfs(){
	
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	var url = "xpj_zcfs.do?method=editZcfss&id="+ids.toString()+"&xn="+xn+"&xq="+xq;
	/*按月提交，有且只能勾选一条记录 进行综测维护 */
	if(ids.length != 1 && jQuery("#szyf").val() == "1"){
		showAlertDivLayer("请勾选一条记录进行综测分维护！");
		return false;
	}
	if(ids.length == 0){
		
		url = url+"&jsonStr="+encodeURI(jsonStr);
		
		var flag=true;
		jQuery.post("xpj_zcfs.do?method=checkSubmitInfo",map,function(data){
			if (data == "false"){
				flag=false;

			}
		});
		if("false"==flag){
			showAlertDivLayer("没有未提交信息");
			return false;
		}	
		
		showConfirmDivLayer("确定要查询当前所有学生的综测信息吗？",{"okFun":function(){
			document.location.href=url;
		}});
		
	}else{
		url = url+"&jsonStr="+ encodeURI("{}");
		
		var tjbj = 0;  //已提交班级数
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<rows.length;i++){
			if(rows[i]['tjzt']=='1'){
				tjbj++;
			}
		}
		if(tjbj==rows.length){
			showConfirmDivLayer("选中的班级都<font color='blue'>已提交</font>，请重新选择",{"okFun":function(){
			}});
			return false;
		}else if(tjbj!=0){
			showConfirmDivLayer("会自动去除<font color='blue'>已提交</font>不可维护的班级",{"okFun":function(){
				document.location.href=url;
			}});
			return false;
		}
		
		document.location.href=url;
		
	}

}



/**
 * 保存综测分
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfs(obj,xmdm,xh){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	var zcyf = jQuery("#zcyf").val();
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("录入分数不能大于最大分："+max,{},{"clkFun":function(){
		}});
		return false;
	}
	
	if (min != "" && Number(fs) < Number(min)){
		showAlertDivLayer("录入分数不能低于最小分："+min,{},{"clkFun":function(){
		}});
		return false;
	}
	
	jQuery.post("xpj_zcfs.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs,zcyf:zcyf},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * 执行综测分提交
 * @param id
 */
function doSubmit(id){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpj_zcfs.do?method=checkIsCanSubmit",{id:id,xn:xn,xq:xq},function(data){
			if (data == "true"){
				showConfirmDivLayer("综测分提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
					jQuery.post("xpj_zcfs.do?method=submitBjzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							searchRs();
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("全部学生综测成绩录入完成后可提交，该班级仍有学生的综测分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * 录综测分页面执行综测分提交
 * @param id
 */
function doEditSubmit(id){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpj_zcfs.do?method=checkIsCanSubmit",{id:id,xn:xn,xq:xq},function(data){
			if (data == "true"){
				showConfirmDivLayer("综测分提交后将不允许修改，您确定要提交吗？",{"okFun":function(){
					jQuery.post("xpj_zcfs.do?method=submitBjzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							document.location.href="xpj_zcfs.do?method=viewZcfs&id="+id;
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("学生综测成绩录入完成后可提交，该班级仍有学生的综测分未录入，请确认！");						
			}
		});
	jQuery.ajaxSetup({async:true});
}


/**
 * 提交综测分
 * @returns {Boolean}
 */
function submitZcfs(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	if (ids.length == 0){
		showAlertDivLayer("请选择您要提交的记录！");
	}else if(ids.length == 1){
		doSubmit(ids.toString());
	}else{
		showConfirmDivLayer("综测分提交后将不允许修改，您确定要提交吗？", {
			"okFun" : function() {
				jQuery.post("xpj_zcfs.do?method=plCheckIsCanSubmit", {
					id:ids.toString(),
					xn:xn,
					xq:xq
				}, function(data) {
					var mes="成功提交了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" 仍有学生的综测分未录入，请确认！";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
		
	};
	
}


/**
 * 查看综测分
 * @returns {Boolean}
 */
function viewZcfs(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一个您要查看的班级！");
		return false;
	} 
	
	var id = rows[0]["id"];
	showDialog("查看综测分",700,410,"xpj_zcfs.do?method=viewZcfs&id="+id);
}

/**
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
 * 切换综测结果页tab页
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,zcjg){
	jQuery("#zcjg").val(zcjg);

	if (zcjg == "cpzpm"){
		var map = getSuperSearch();
		map["zcjg"]="cpzpm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} if(zcjg == "bjpm"){
		var map = getSuperSearch();
		map["zcjg"]="bjpm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else {
		var map = getSuperSearch();
		map["zcjg"]="njzypm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting3);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}



/**
 * 综测分录入、查看--查询
 */
function doQuery(){

	var jsonStr = jQuery("#jsonStr").val();
	var map = {};
	if(jsonStr){
		map = JSON.parse(jsonStr);
	}
	map["id"] = jQuery("#id").val();
	map["xn"] = jQuery("#xn").val();
	map["xq"] = jQuery("#xq").val();
	map["xh"] = jQuery("#xh").val();
	map["zcyf"] = jQuery("#zcyf").val();
	jQuery("#dataTable").reloadGrid(map);
	
}

/**
 * 导出综测分（查看页）
 */
function exportZcf(){
	var id = jQuery("#id").val();
	var xh = jQuery("#xh").val();
	document.location.href="xpj_zcfs.do?method=exportViewZcfs&id="+id+"&xh="+xh;
}

function exportZcfOfYf(){
	var id = jQuery("#id").val();
	var xh = jQuery("#xh").val();
	var zcyf = jQuery("#zcyf").val();
	document.location.href="xpj_zcfs_yf.do?method=exportViewZcfs&id="+id+"&zcyf="+zcyf+"&xh="+xh;
}


/**
 * 综测分导入 
 */
function toImport(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var zcyf = jQuery("#zcyf").val();
	var map = JSON.parse(jsonStr);
	map['num']=num; //下载的最大条数
	map['zcyf']=zcyf;
	//避免导入数据超内存，控制下载条数
	jQuery.ajaxSetup({async:false});
	jQuery.post("xpj_zcfs.do?method=checkDownload&id="+id,map,function(data){
		if (data == "true"){
			showDialog("综测分导入",550,250,"xpj_zcfs.do?method=toImportZcfs&id="+id+"&jsonStr="+encodeURI(jsonStr)+"&zcyf="+zcyf,{close:function(){
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
 * 同步综测分
 * @param xmdm
 */
function getIntefaceData(xmdm){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['xmdm']=xmdm;
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * 同步综测分（上海戏剧个性化）
 * @param xmdm
 */
function getIntefaceDataS(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

function getIntefaceData_13431(){
    var id = jQuery("#id").val();
    var xn = jQuery("#xn").val();
    var xq = jQuery("#xq").val();
    var xmdms = [];
    jQuery("[name='zcxm']").each(function(i,n){
    	var xmmc = jQuery(n).attr('xmmc');
    	if(xmmc == "体育分" || xmmc == "智育分" || xmmc == "加分" || xmmc == "减分"){
            xmdms.push(jQuery(n).attr('xmdm'));
		}

    });
    var jsonStr = jQuery("#jsonStr").val();
    var map = JSON.parse(jsonStr);
    map['id']=id;
    map['xn']=xn;
    map['xq']=xq;
    map['xmdms'] = xmdms.join(",");
    jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
        showAlertDivLayer(data["message"],{},{"okFun":function(){
        }});
        jQuery("#dataTable").reloadGrid();
    },"json");
}
/**
 * 同步综测分（浙大宁波理工学业水平）
 * @param xmdm
 */
function getIntefaceDataXysp(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData&nzcs="+1,map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * 同步综测分（浙大宁波理工思想道德）
 * @param xmdm
 */
function getIntefaceDataSxdd(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData&nzcs="+2,map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * 下载综测分导入模版
 */
function downloadTemplate(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var zcyf=jQuery("#zcyf").val();
	var map = JSON.parse(jsonStr);
	
	var url = "xpj_zcfs.do?method=downloadTemplate&id="+id+"&zcyf="+zcyf;
	url = addSuperSearchParams(url,map);// 设置高级查询参数
	jQuery("#zcfsForm").attr("target","_blank");
	jQuery("#zcfsForm").attr("action",url);
	jQuery("#zcfsForm").submit();

}


/**
 * 导入 综测分--上传文件
 * @returns {Boolean}
 */
function uploadZcfs(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("导入文件只能为.xls格式,请确认！");
		return false;
	}
	
	url = addSuperSearchParams("xpj_zcfs.do?method=importZcfs",map);// 设置高级查询参数
	jQuery("#zcfsForm").attr("target","");
	jQuery("#zcfsForm").attr("action",url).submit();
}



//设置高级查询条件，导出方法中使用
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
	jQuery("#zcfsForm").append(html);
	return url;
}

/**
 * 保存综测分(月份)
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfsYf(obj,xmdm,xh){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	var zcyf = jQuery("#zcyf").val();
	
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("录入分数不能大于最大分："+max,{},{"clkFun":function(){
		}});
		return false;
	}
	
	if (min != "" && Number(fs) < Number(min)){
		showAlertDivLayer("录入分数不能低于最小分："+min,{},{"clkFun":function(){
		}});
		return false;
	}
	
	jQuery.post("xpj_zcfs_yf.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs,zcyf:zcyf},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

/**
 * 提交综测分
 * @returns {Boolean}
 */
function submitZcfsyf(){
	
	var ids = jQuery("#id").val();
	var zcyf = jQuery("#zcyf").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	

		showConfirmDivLayer("综测分提交后将不允许修改，您确定要提交吗？", {
			"okFun" : function() {
				jQuery.post("xpj_zcfs.do?method=plCheckIsCanSubmityf", {
					id:ids.toString(),
					xn:xn,
					xq:xq,
					zcyf:zcyf
				}, function(data) {
					var mes="成功提交了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" 仍有学生的综测分未录入，请确认！";
						showAlertDivLayer(mes);
						return false;
					}else{
						//提交成功需改变下拉框的值
						showAlertDivLayer(mes);
						jQuery("#dataTable").reloadGrid();
						jQuery("#tjzt").find("[value='"+zcyf+"']").text("1");
						jQuery("#zcyf").change();
						return false;
					}
					
				}, 'json');
			}
		});
		

	
}

/**
 * 内蒙古电子将日常行为德育分同步至综测编辑页面
 * @return
 */
function getIntefaceDataNmgdz(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();

	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

//江西应用职业技术学院
function getIntefaceDataNmgzc(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();

	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}