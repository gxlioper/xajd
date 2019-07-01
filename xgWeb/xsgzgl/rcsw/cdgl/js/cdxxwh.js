
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
申请开关函数
*/
function changeSqkg(){
	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg){
		jQuery('#splcTr').show();
	}else if("0"==ksqkg){
		jQuery('#splcTr').hide();
	}	
}


/**
 * popup 选择场地信息查询条件获取参数
 * @return
 */
function getCdcxSearch(){
	var search_cdmc;
	var search_rnrsmin ;
	var search_rnrsmax ;
	var search_yt;
	var search_dwkfsjkssj;
	var search_dwkfsjjssj;
	var search_sfkfsq;
	var path = jQuery("#path").val();
	//获取页面查询值
	if(jQuery('#search_cdmc'))
		search_cdmc = jQuery.trim(jQuery('#search_cdmc').val());
	if(jQuery('#search_rnrsmin'))
		search_rnrsmin = jQuery.trim(jQuery('#search_rnrsmin').val());
	if(jQuery('#search_rnrsmax'))
		search_rnrsmax = jQuery.trim(jQuery('#search_rnrsmax').val());
	if(jQuery('#search_yt'))
		search_yt = jQuery.trim(jQuery('#search_yt').val());
	if(jQuery('#search_dwkfsjkssj'))
		search_dwkfsjkssj = jQuery.trim(jQuery('#search_dwkfsjkssj').val());
	if(jQuery('#search_dwkfsjjssj'))
		search_dwkfsjjssj = jQuery.trim(jQuery('#search_dwkfsjjssj').val());
	if(jQuery('#search_sfkfsq'))
		search_sfkfsq = jQuery.trim(jQuery('#search_sfkfsq').val());
	
	var map = {
			"search_cdmc":search_cdmc,
			"search_rnrsmin":search_rnrsmin,
			"search_rnrsmax":search_rnrsmax,
			"search_yt":search_yt,
			"search_dwkfsjkssj": search_dwkfsjkssj,
			"search_dwkfsjjssj": search_dwkfsjjssj,
			"path" : path,
			"search_sfkfsq" : search_sfkfsq
		};
	  
	return map;
}

/**
 * 新增场地信息
 * @return
 */
function addCdxx(){
	showDialog('新增场地信息',680,430,'rcsw_cdgl_cdxxwh.do?method=cdxxwhXz');
}

/**
 * 新增场地信息保存操作
 * @return
 */
function addCdxxAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "cdmc-ld-fj-rnrs-dwkfsjkssj-dwkfsjjssj-lxr-lxfs";
	
	if(!checkNull(checkids)){
		return false;
	}
	
	if(jQuery('input[name="sfkfsq"]:checked').val() == undefined){
		showAlert("请选择是否开放申请！");
		return false;
	}

	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg&& !checkNull('splcid')){
		return false;
	}else if("0"==ksqkg){
		jQuery('#splcid').val("");
	}
	if(jQuery('#jbsbjs').val().length > 500){
		showAlert("基本设备介绍最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#xfgfsyxy').val().length > 500){
			showAlert("幸福工坊使用协议最大字数不超过"+500+",请确认！");
			return false;
		}
	}
	
	//获取权限范围
    var qxfwIds="";
    jQuery("input[name=qxfwView]").each(function(){
    	var isChecked=jQuery(this).is(":checked");
    	if(isChecked){
    		qxfwIds+=jQuery(this).val();
    		qxfwIds+=",";
    	}
    });

    jQuery("#qxfw").val(qxfwIds);
    
	var url = "rcsw_cdgl_cdxxwh.do?method=cdxxwhXzAction";
		ajaxSubFormWithFun("rcswCdxxwhForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'repeat'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
				
			}});
		});
}


/**
 * 修改场地信息
 * @return
 */
function updateCdxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('场地信息修改',680,430,'rcsw_cdgl_cdxxwh.do?method=cdxxwhXg&cdid=' + rows[0]['cdid']);
	}
}

/**
 * 更新场地信息
 * @return
 */
function updateCdxxAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "cdmc-ld-fj-rnrs-dwkfsjkssj-dwkfsjjssj-lxr-lxfs";
	
	if(!checkNull(checkids)){
		return false;
	}
	
	if(jQuery('input[name="sfkfsq"]:checked').val() == undefined){
		showAlert("请选择是否开放申请！");
		return false;
	}
	
	var ksqkg = jQuery("[name=sfkfsq]:checked").val();
	if("1"==ksqkg && !checkNull('splcid')){
			return false;
	}else if("0"==ksqkg){
		jQuery("#splcid").val("");
	}
	
	if(jQuery('#jbsbjs').val().length > 500){
		showAlert("基本设备介绍最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#xfgfsyxy').val().length > 500){
			showAlert("幸福工坊使用协议最大字数不超过"+500+",请确认！");
			return false;
		}
	}
	
	//获取权限范围
    var qxfwIds="";
    jQuery("input[name=qxfwView]").each(function(){
    	var isChecked=jQuery(this).is(":checked");
    	if(isChecked){
    		qxfwIds+=jQuery(this).val();
    		qxfwIds+=",";
    	}
    });

    jQuery("#qxfw").val(qxfwIds);

	var url = "rcsw_cdgl_cdxxwh.do?method=cdxxwhXgAction";
		ajaxSubFormWithFun("rcswCdxxwhForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


/**
 * 删除场地信息
 * @return
 */
function deleteCdxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlert("请选择一条您要删除的记录！");
		return false;
	} else{
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("rcsw_cdgl_cdxxwh.do?method=cdxxwhScAction",{cdids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


var DCCLBH = "rcsw_cdgl_cdxxwh.do";//dcclbh,导出功能编号


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_cdgl_cdxxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function isHaveV(V){
	var qxfw=jQuery("#qxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}

//流程控制
function setLckz(value) {
	if (value == "") {
		jQuery("#qxfwTd").html("");
		return;
	}
	var url = "rcsw_cdgl_cdxxwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				sHtml += "<label><input type='checkbox' name='qxfwView' value='"
						+ o.spgwdm + "'/>";
				sHtml += o.spgwmc;
				sHtml += "</label>";
				if (i != data.length - 1) {
					sHtml += "<br/>";
				}
			}
		}
		jQuery("#qxfwTd").html(sHtml);
		var isH=false;
	    jQuery("input[name=qxfwView]").each(function(){
	    	var V=jQuery(this).val();
	    	if(isHaveV(V)){
	    		jQuery(this).attr("checked","checked");
	    		isH=true;
	    	}
	    });
		jQuery("[name=qxfwView]").bind("click",function(){
			var selectV=jQuery(this).val();
			jQuery("[name=qxfwView]:checked").each(function(){
				var sv=jQuery(this).val();
				if(sv!=selectV){
					jQuery(this).removeAttr("checked");
				}
			});
		});
	
	}, 'json');
}



