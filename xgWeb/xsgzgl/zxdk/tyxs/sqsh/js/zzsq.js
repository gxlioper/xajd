var dkzesx = jQuery("#dkzesx").val();

function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "tyxs_zzsq.do?method=zzsh&id="+id;
		showDialog("退役学生资助审核",800,500,url);
	}
}

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
		
		showDialog("修改申请表",800,500,"tyxs_zzsq.do?method=xgZzsq&id="+rows[0]["id"]);
	}
}


/**
 * 查看申请表
 * @param id
 */
function ckSqb(id){
	showDialog("查看申请表",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
}

/**
 * 查看申请表
 * @param id
 */
function ckShbb(id){
	showDialog("查看审核详细",800,590,"tyxs_zzsq.do?method=ckZzsq&id="+id);
}


/**
 * 查询
 */
function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 填写申请表
 */
function editSqb(){
	showDialog("填写申请表",800,500,"tyxs_zzsq.do?method=zzsq");
}


/**
 * 撤销
 */
function cancelsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();		
	if (ids.length == 0){
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (rows[0]["shzt"] != "5"){
		showAlertDivLayer("只有审核中的记录才能被撤销！");
		return false;	
	} else {
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
			jQuery.post("tyxs_zzsq.do?method=cancel",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}
		});
		
	
	}
}




/**
 * 保存申请
 * @returns {Boolean}
 */
function saveJxsq(type){
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == ""){
		showAlert("请将必填项填写完整！");
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


function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
	}
	
	var map = getSuperSearch();
	map["shzt"] = zt;
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}


//导出
function exportConfig(){
	var DCCLBH='tyxs_zzsq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='tyxs_zzsq.do';
	setSearchTj();//设置高级查询条件
	
	var url = "tyxs_zzsq.do?method=dcsq&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//提交
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();	
	if (ids.length != 1){
		showAlertDivLayer("请选择您要提交的记录！！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("请选择未提交或者已退回的记录！！");
			return false;
		
	}else {
		showConfirmDivLayer("您确定要提交选择的记录吗？",{
			"okFun" : function(){

			jQuery.post("tyxs_zzsq.do?method=submitTj",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();			
					refershParent();
				
			},'json');
			
		}
	});
	
		
		
	}
	
}



/**
 * 取消申请
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已审核，不能删除！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("tyxs_zzsq.do?method=delZzsq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}



//验证贷款总额
function getzje(obj){
	//checkMoney(obj);
	var sqxfzj = jQuery("#sqxfzj").val();//总计
	var dyzzxf = jQuery("#dyzzxf").val();//第一年
	var dezzxf = jQuery("#dezzxf").val();//第二年
	var dszzxf = jQuery("#dszzxf").val();//第三年
	var dsizzxf = jQuery("#dsizzxf").val();//第四年
    var dqdks = obj.value;
    var dkzs=0;
	if (null !=dyzzxf && ''!= dyzzxf) {
		
	  dkzs = parseInt(dyzzxf)+parseInt(dkzs);
	}
	if (null != dezzxf && ''!= dezzxf) {
	  dkzs = parseInt(dezzxf)+parseInt(dkzs);		
	  }
	if (null !=dszzxf && ''!= dszzxf) {
	  dkzs = parseInt(dszzxf)+parseInt(dkzs);
	  }
	if (null !=dsizzxf && ''!= dsizzxf) {
		  dkzs = parseInt(dsizzxf)+parseInt(dkzs);
		  }
	
	jQuery("#sqxfzj").val(parseInt(dkzs));
	
}

/*function checkZje(obj){
	checkMoney(obj);
	var dkzs=obj.value;
	if (null !=dkzs && ''!= dkzs) {
		if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
			showAlertDivLayer("总额超过"+jQuery("#dkzesx").val()+"元!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		}
}*/
function checkQx(obj){
	var dkqx=obj.value;
	if (null !=dkqx && ''!= dkqx) {
		if(parseInt(dkqx)>4){
			showAlertDivLayer("贷款期限超过4年!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		else{
			if(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val()){
				jQuery("#dkje").val(parseInt(jQuery("#mnje").val())*parseInt(dkqx));
			}
		}
		}
	
}

/**
 * 加载家庭情况调查信息
 * @param obj
 * @return
 */
function showJtqk(obj){
	
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
}

/**
 * 助学贷款申请界面编辑家庭情况
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('家庭情况调查',780,500,'xszz_jtqkdc.do?method=dcxxModify&type=update&xh='+xh,{
		close:function(){
			reloadWindow();
		}
	});
}

/**
 * 助学贷款申请申请界面
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="tyxs_zzsq.do?method=zzxx&xh="+xh;
}

//申请信息
function onShow(gndm) {	
	var url = "tyxs_zzsq.do?method=zzxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			id : jQuery("#id").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data);
		}
	});
}


//退役表单非空
function tyCheckNull(){
	var flag = true;
	var xxdm =jQuery("#xxdm").val();
	if(xxdm=="10704"){
		if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly-fistyj-secondyj-thirdyj-fourthyj-xfyjzj-sfbb')){
			
			//showAlertDivLayer("请将带<span class='red'>*</span>的项目填写完整!");			
			flag = false;				
		}
	}else{
		if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly')){
			
			//showAlertDivLayer("请将带<span class='red'>*</span>的项目填写完整!");			
			flag = false;				
		}
	}
	
	return flag;

	}

//复学后应缴金额总计
function getyjzj(obj){
	var fistyj = jQuery("#fistyj").val();//第一年
	var secondyj = jQuery("#secondyj").val();//第二年
	var thirdyj = jQuery("#thirdyj").val();//第三年
	var fourthyj = jQuery("#fourthyj").val();//第四年
    var dkzs=0;
	if (null !=fistyj && ''!= fistyj) {
		
	  dkzs = parseInt(fistyj)+parseInt(dkzs);
	}
	if (null != secondyj && ''!= secondyj) {
	  dkzs = parseInt(secondyj)+parseInt(dkzs);		
	  }
	if (null !=thirdyj && ''!= thirdyj) {
	  dkzs = parseInt(thirdyj)+parseInt(dkzs);
	  }
	if (null !=fourthyj && ''!= fourthyj) {
		  dkzs = parseInt(fourthyj)+parseInt(dkzs);
		  }
	
	jQuery("#xfyjzj").val(parseInt(dkzs));
	
}


//验证贷款总额
function getzjehsd(obj){
	//checkMoney(obj);
	var objs = jQuery("input[name='je']");
	var len = objs.length;
    var dkzs=0;
    for(var i=0;i<len;i++){
    	var jeval = jQuery.trim(objs[i].value);
    	if(jeval != null &&　jeval != "" ){
    		dkzs +=parseInt(jeval);
    	}
    }
	jQuery("#sqxfzj").val(dkzs);
	
}

function dklxchange(){
	jQuery("#dklx").change(function(){
		var xh = jQuery.trim(jQuery("#xh").val());
		var dklx = jQuery("#dklx").val();
		if((xh == '' || xh == null) && dklx != '' ){
			jQuery("#dklx").val("");
			showAlert("请先选择学生！");
			return false;
		}
		
		var xn = jQuery("input[name='xn']").val();
		jQuery.post("dkbc_sqsh.do?method=dklxChange",{xh:xh,dklx:dklx,xn:xn},function(data){
		if(data != null){
			jQuery("#dkbj").val(data['dkje']);
			jQuery("#yhdm").val(data['yhdm']);			
		    jQuery("#dkhth").val(data['htbh']);     
		         if(data['dkkssj'] != "" &&data['dkkssj'] != null ){
			         jQuery("#dkkssj").val(data['dkkssj']);
			         jQuery("#dkjssj").val(data['dkjssj']);
		         }
		}else{
			jQuery("#dkbj").val("");
			jQuery("#yhdm").val("");			
		    jQuery("#dkhth").val("");
		    jQuery("#dkkssj").val("");
	         jQuery("#dkjssj").val("");
		}
		
		   			
		},"json");
	});
}

function fxjdnxchange(){
	jQuery("#fxjdnx").change(function(){
		var fxsj = jQuery("#fxsj").val();
		var xh = jQuery.trim(jQuery("#xh").val());
		var fxjdnx = jQuery("#fxjdnx").val();
		if(fxjdnx != '' && xh == ''){
			jQuery("#fxjdnx").val("");
			showAlert("请先选择学生！");
			return false;
		}
		if(fxjdnx != '' && fxsj == ''){
			jQuery("#fxjdnx").val("");
			showAlert("请先选择复学时间！");
			return false;
		}
		var ksnd = fxsj.substr(0, 4);
		var html = "";
		html += "<tr width='100%' >"+
	       "<th width='40%' ><span class='red'>*</span>年度</th>"+
	        "<th width='60%' ><span class='red'>*</span>减免金额</th>"+
           "</tr>"
		if(fxjdnx == ''){
			jQuery(".cn").html(html);
		}else{
			var len = fxjdnx;
			jQuery(".cn").empty();
			for(var i=0;i<len;i++){
				html += "<tr width='100%'>"+
				"<td width='40%' >"+(parseInt(ksnd)+i)+"<input name='nd' type='hidden'  value='"+(parseInt(ksnd)+i)+"'></td>"+
				"<td width='60%' ><input name='je' type='text' onkeyup='checkInput(this);getzjehsd(this)' maxlength='5' style='width:80%'></td>"+
			"</tr>"
			}
			html += "<tr width='100%'>"+
			"<td width='40%' >申请学费减免总计</td>"+
			"<td width='60%' ><input name='sqxfzj' id='sqxfzj' maxlength='10' readonly='true' type='text' style='width:80%'/></td>"+
		"</tr>"
			jQuery(".cn").append(html);
		}

	});
}

//退役表单非空
function tyCheckNullhsd(){
		
	var flag = true;
	if(!checkNull('rwqrxsj-rwsj-tysj-fxsj-lsgx-fxjdxlcc-sqxfzj-dyzzxf-dezzxf-dszzxf-dsizzxf-sqly-dklx-dkbj')){
		
		//showAlertDivLayer("请将带<span class='red'>*</span>的项目填写完整!");			
		flag = false;				
	}
	return flag;

	}

//验证年底减免金额是否都填写了
function checkNdJe(){
	if(jQuery(".cn").find("tr").length == '1'){
		showAlert("请将带<span class='red'>*</span>的项目填写完整!");
		return false;
	}
	if(jQuery("[name='nd']").length == 0){
		showAlert("年度，年度金额不可为空!");
		return false;
	}
	var objs = jQuery("input[name='je']");
	var len = objs.length;
	var flag = true;
    for(var i=0;i<len;i++){
    	var jeval = jQuery.trim(objs[i].value);
    	if(jeval == null ||　jeval == "" ){
    		showAlert("请将带<span class='red'>*</span>的项目填写完整!");
    		flag = false;
    		break;
    	}
    }
    return flag;
}

//验证申请理由长度
function checksqlylength(){
	if(jQuery("#sqly").val().length > 250){
		showAlert("申请理由不要超过250字!");
		return false;
	}else{
		return true;
	}
}
	