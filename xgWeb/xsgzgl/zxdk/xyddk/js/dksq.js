var dkzesx = jQuery("#dkzesx").val();

function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var gwid = rows[0]["xtgwid"];
		var url = "zxdkXyddk.do?method=dksh&id="+id+"&gwid="+gwid;
		showDialog("国家助学贷款审核",800,500,url);
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
			showAlertDivLayer("该申请正在审核中，不能修改！");
			return false;
		}
		
		showDialog("修改申请表",800,500,"zxdkXyddk.do?method=xgDksq&id="+rows[0]["id"]);
	}
}


/**
 * 查看申请表
 * @param id
 */
function ckSqb(id){
	showDialog("查看申请表",800,590,"zxdkXyddk.do?method=ckDksq&id="+id);
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
	showDialog("填写申请表",800,500,"zxdkXyddk.do?method=dksq");
}


/**
 * 取消申请
 */
function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else {
		jQuery.post("zxdkXyddk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
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
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}


//导出
function exportConfig(){
	var DCCLBH='zxdk_xyddksq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_xyddksq.do';
	setSearchTj();//设置高级查询条件
	
	var url = "zxdkXyddk.do?method=dcsq&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		return false;
	}
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已经在审核中，无需重复提交！");
		return false;
	}
	jQuery.post("zxdkXyddk.do?method=submit",{id:ids.toString()},function(data){
		showAlertDivLayer(data["message"]);
		jQuery("#dataTable").reloadGrid();
	},'json');
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
			jQuery.post("zxdkXyddk.do?method=delDksq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}



//验证贷款总额
function getzje(obj){
	checkMoney(obj);
	var xfdks = jQuery("#xfdks").val();//学费贷款数
	var zsfdks = jQuery("#zsfdks").val();//住宿费贷款数
	var shfdks = jQuery("#shfdks").val();//生活费贷款数
	var dkqx = jQuery("#dkqx").val();
	var xzf = jQuery("#xzf").val();
	var xxdm = jQuery("#xxdm").val();
    var dqdks = obj.value;
    var dkzs=0;
    
   
	if (null !=xfdks && ''!= xfdks) {
		
	  dkzs = parseInt(xfdks)+parseInt(dkzs);
	}
	if (null != zsfdks && ''!= zsfdks) {
	  dkzs = parseInt(zsfdks)+parseInt(dkzs);		
	  }
	if (null !=shfdks && ''!= shfdks) {
	  dkzs = parseInt(shfdks)+parseInt(dkzs);
	  }
	
	if(dkzs>parseInt(jQuery("#dkzesx").val())){
		showAlertDivLayer("每年贷款总额超过"+jQuery("#dkzesx").val()+"元!",{},{"clkFun":function(){
			jQuery("#mnje").val(dkzs);
			if("10335"==xxdm){
				if(null == xzf || ''== xzf){
					jQuery("#dkje").val(parseInt(dkzs));
				}
				else{
					jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
				}
			}else{
				if(null == dkqx || ''== dkqx){
					jQuery("#dkje").val(parseInt(dkzs));
				}
				else{
					jQuery("#dkje").val(parseInt(dkzs)*parseInt(dkqx));
				}
			}
			obj.focus();
		}});
	}else{
		jQuery("#mnje").val(dkzs);
		if("10335"==xxdm){
			if((null != xzf && ''!= xzf)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs));
			}
		}else{
			if((null != dkqx && ''!= dkqx)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(dkqx));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs));
			}
		}
		
	}
	
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
	document.location.href="zxdkXyddk.do?method=dksq&xh="+xh;
}

//贷款申请信息
function onShow(gndm) {
	var url = "zxdkXyddk.do?method=dkxx";
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

//浙大个性化选择
function zddkqx(){
	var nj = jQuery("#nj").val();  //年级
	var xn = jQuery("#xn").val();  //学年
	var xz = jQuery("#xz").val();  //学制
	
	//贷款期限=剩余年份（年级-申请年份）+学制+13年     结果不能大于20 大于20取20
	var zddkqx = 0;
	if(null==xz||""==xz){
		xz=0;
	}
	if(null==nj||""==nj){
		nj=0;
	}
	if(xz!=0&&nj!=0){
		//parseInt(nj)-parseInt(xn.substring(5,10))+parseInt(xz)+13;
	zddkqx = parseInt(nj)-parseInt(jQuery("#qsxn").val())+parseInt(xz)+13;
	if(zddkqx>20){
		zddkqx=20;
	}
	jQuery("#dkqx").val(zddkqx);
	}
}

