//奖项申请


/**
 * 修改申请表
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		
		showDialog("修改申请表",800,480,"xpj_sqsh.do?method=updateSqb&sqid="+rows[0]["sqid"]);
	}
}


/**
 * 查看申请表
 * @param id
 */
function ckSqb(id){
	showDialog("查看申请表",800,495,"xpj_sqsh.do?method=viewSqb&sqid="+id);
}


/**
 * 查询
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




/**
 * 填写申请表
 */
function editSqb(){
	showDialog("填写申请表",800,455,"xpj_sqsh.do?method=editSqb&xzdm="+jQuery("#xzdm").val());
}


/**
 * 删除申请
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xpj_sqsh.do?method=cancelXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 保存申请
 * @returns {Boolean}
 */
function saveJxsq(type){
	var xh = jQuery("#xh").val();
	var xxdm = jQuery("#xxdm").val();
	if (jQuery("#sqly").val() == "" || xh == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		
	
	if (jQuery("#sqly").val().length > 500){
		showAlert("申请理由最大字数为500，现已经超过，请确认！！");
		return false;
	}
	
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("请选择您要申请的奖项",{},{"clkFun":function(){
			showDialog("选择申请奖项",450,350,"xpj_sqsh.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	
	var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
	ajaxSubFormWithFun("sqshForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * 切换已申请、未申请
 * @param obj
 * @param tabId
 */
function selectSqxm(obj,tabId){
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
}

/**
 * 选择评奖项目
 * @returns {Boolean}
 */
function selectXm(){
	
	var pjxm = jQuery("input[name=xmdm]:checked");
	
	if (pjxm.size() == 0){
		showAlert("请选择您要申请的奖项！");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#sqjx");
//	var parentTxsmDiv = jQuery(api.get('parentDialog').document).find("#txsmDiv")
	jQuery("tr",parentTbody).remove();
	
	
	var xxdmdiv = jQuery(api.get('parentDialog').document).find("#xxdm");
	var xxdm = xxdmdiv.val();
	var parent = jQuery(api.get('parentDialog').document).find("#fjxx");
	var djb = jQuery(api.get('parentDialog').document).find("#djb");
	if('11527' == xxdm){
		if(djb.length>0){
			djb.remove();
		}
	}
	//中国美术学院个性化，校验是否开启获奖条件的添加
	if(jQuery("#xxdm").val() == "10355"){
	  var parentDoc = jQuery(api.get('parentDialog').document);
	  if(pjxm.size() == 1){
		  var url = "xpj_sqsh.do?method=checkHjsqCanAdd";
		  var checkResult = "";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'xmdm='+pjxm[0].value,
			async: false,
			success:function(result){
				checkResult = result["message"];
			}
			
			});
			if(checkResult == "true"){
				jQuery(parentDoc).find("#hjbutton").show();
				jQuery(parentDoc).find("#hjtr").show();
			}else{
				jQuery(parentDoc).find("#hjbutton").hide();
				jQuery(parentDoc).find("#hjtr").hide();
			}
	  }else{
		  jQuery(parentDoc).find("#hjbutton").hide();
		  jQuery(parentDoc).find("#hjtr").hide();
	  }
		
		
		
	}	
	
	jQuery.each(pjxm,function(i,n){
		var tr = jQuery("<tr></tr>");
		var xmmcTd = jQuery("<td></td>");
		var xmjeTd = jQuery("<td></td>");
		var sqtsTd = jQuery("<td></td>");
		
		var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+jQuery(n).val()+"'/>");
		var xmmc = jQuery(n).parent().next().text();
		var xmje = jQuery(n).parent().next().next().text();
		var kgbz= jQuery(n).parent().next().next().next().text();
		
		xmjeTd.append(xmje);
		xmmcTd.append(xmmc);
		xmmcTd.append(xmdm);
		
		sqtsTd.append(jQuery("<p></p>").append(kgbz));
		
//		parentTxsmDiv.html(kgbz);
		tr.append(xmmcTd).append(xmjeTd).append(sqtsTd);
		parentTbody.append(tr);
		if('11527' == xxdm){
			var djbb = jQuery(api.get('parentDialog').document).find("#djb");
			if(xmmc.trim() == '湖南省2017届优秀毕业生'){
				if(djbb.length>0){
					var td = jQuery(djbb).find("td");
					var html = '&nbsp;&nbsp;<a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">省级登记表下载</a>';
					td.append(html);
				}else{					
					var html = '<tr id="djb"><th>登记表下载</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">省级登记表下载</a></td></tr>'
					parent.before(html);
				}
			}
		    if(xmmc.trim() == '湖南城市学院2017届优秀毕业生'){
		    	if(djbb.length>0){
		    		var td = jQuery(djbb).find("td");
		    		var html = '&nbsp;&nbsp;<a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">校级登记表下载</a>';
		    		td.append(html);
		    	}else{
		    		var html = '<tr id="djb"><th>登记表下载</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">校级登记表下载</a></td></tr>'
					parent.before(html);
		    	}
		    }
		}
		if('11799' == xxdm){
			if("单项奖学金" == xmmc.trim()  ||
					 "单项奖学金（体育运动）" == xmmc.trim() || 
					 "单项奖学金（文艺活动）" == xmmc.trim() || 
					 "单项奖学金（科技创新）" == xmmc.trim() || 
					 "单项奖学金（学习优秀1）" == xmmc.trim() || 
					 "单项奖学金（学习优秀3）" == xmmc.trim() || 
					 "单项奖学金（学习进步）" == xmmc.trim() ){
				//jQuery(api.get('parentDialog').document).find("#sqlyTr").hide();
				jQuery(api.get('parentDialog').document).find("#dxjxjTr").show();
				jQuery(api.get('parentDialog').document).find("#xmmc_11799").val(xmmc.trim());
			}else{
				//jQuery(api.get('parentDialog').document).find("#sqlyTr").show();
				jQuery(api.get('parentDialog').document).find("#dxjxjTr").hide();
				jQuery(api.get('parentDialog').document).find("#xmmc_11799").val("");
			}
		}
	});
	
	api.close();
}


/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
//		
//		var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
//		showDialog("流程跟踪",550,450,url,{max:false,min:false});
		
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
	
}


//导出
function exportConfig(){
	var DCCLBH='pj_jxsq.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='pj_jxsq.do';
	
	setSearchTj();//设置高级查询条件
	
	var url = "xpj_sqsh.do?method=sqExportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//提交
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			if (rows[i]["shzt"] != "0"&&rows[i]["shzt"] != "3"){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			if('3'!=rows[i]['shzt']&&"false"==rows[i]['isopen']){
				showAlertDivLayer("申请项目时间已关闭，请与管理员联系！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			var tips = submitLoading();
			try{ // 解决：先增加，关闭增加窗口，再提交时等待条无法关闭
				tips.show();
			}catch(e){
			}
			jQuery.post("xpj_sqsh.do?method=saveUpdateSqbPl",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancleRst(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		var SFBJPY_Y = jQuery("#SFBJPY_Y").val();
		for(var i=0;i<ids.length;i++){
			if(SFBJPY_Y == 'true'){
				if(rows[i]['shzt']!='6'){
					showAlertDivLayer("只有班级评议中的记录才能被撤销！");
					return false;
				}
			}else{
				if(rows[i]['shzt']!='5'){
					showAlertDivLayer("只有审核中的记录才能被撤销！");
					return false;
				}
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post("xpj_sqsh.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				
			}
		});
	}
}

/**
 * 异常数据处理
 * @return
 */
function exceptionDataResolve(){

    showConfirmDivLayer("您确定要进行提交异常数据处理吗？",{"okFun":function(){
        jQuery.post("xpj_sqsh.do?method=exceptionDataResolve",function(data){
            showAlertDivLayer(data["message"]);
        },'json');
    }});
}