var DCCLBH = "xszz_knsrd_sq.do";//dcclbh,导出功能编号
/**
 * 加载家庭情况调查信息
 * @param obj
 * @return
 */
function showJtqk(obj){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";

		jQuery(obj).attr("class",newClass);
		jQuery("#t_jtqk").toggle();
	}else{
		showAlertDivLayer("请先选择学生！");
	}
}

/**
 * 加载困难生认定历史记录
 * @param obj
 * @return
 */
function showLsjl(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_rdlsjl").toggle();
}

/**
 * 保存困难生申请
 * @return
 */
function saveKnssq(saveType){
    if("12389"==jQuery("#xxdm").val()) {
        var sqlydmIds = "";
        var checkSqlydm = "";
        jQuery("input[name=sqly_ckb]:checked").each(function(){
            sqlydmIds += jQuery(this).val()+ "," ;
        });
        if(sqlydmIds.length>0){
            sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
            checkSqlydm ="1";
        }
        if (jQuery.trim(checkSqlydm) == ""){
            showAlert("请选择申请理由！");
            return false;
        }
    }

	if("10742"==jQuery("#xxdm").val()) {		
		var sqlydmIds = "";
		var checkSqlydm = "";
		jQuery("input[name=sqlydm]:checked").each(function(){
			sqlydmIds += jQuery(this).val()+ "," ;
		});
		if(sqlydmIds.length>0){
			sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
			checkSqlydm ="1";
		}
		if (jQuery.trim(checkSqlydm) == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
	}
	if("10344"==jQuery("#xxdm").val()){
		if(jQuery(".MultiFile-label").length == 0){
			showAlert("附件信息不可为空！");
			return false;
		}
	}
	if (!checkMustNotNull() || jQuery("#xh").val() == ""){
		showAlert("请将必填项填写完整！");
		return false;
	}
	
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if(isopen==null||isopen==''){
		showAlert('基础设置未初始化，请联系管理员！');
		return false;
	}
	if ("false" == isopen && '3'!=shzt){
		showAlert("当前未开放困难生申请，请联系管理员！");
		return false;
	}
	
	
	//是否弹出家庭情况调查填写页面
	var openJtqk = jQuery("#openJtqk").val();

	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();

		showAlert("请先填写家庭情况调查表！",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	
    var sqsftxdc=jQuery("#sqsftxdc").val();
    var xxdm=jQuery("#xxdm").val();
	if(sqsftxdc=='1' && (xxdm=='12861' || xxdm=='10718' || xxdm=='11318' || xxdm=='70002')){
		if(null==jQuery("#ylzd1").val()||""==jQuery("#ylzd1").val()){
			showAlert("困难档次不能为空,请选择！");
			return false;
		}
	}
	if(xxdm=='12861' || '10335'==xxdm || '12389'==xxdm ){
	   if(jQuery(".MultiFile-label").length<=0){
		   showAlert("请上传附件！");
			return false;
	   }
	}
	//杭州师范大学个性化
	if(xxdm == '10346'){
		var jtknlx = jQuery("#ylzd9").val();
		var gdxfplx = jQuery("#ylzd5").val();
		if(jtknlx == '' || jtknlx == null || gdxfplx == '' || gdxfplx == null){
			showAlert("请把带*的项填写完整！");
			return false;
		}
	}
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if("submit"== saveType && "3"!=shzt && "false" == isopen){
		showAlert("当前未开放困难生申请，请联系管理员！");
		return false;
	}
	
	if("10742"==xxdm ) {
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&sqlydm="+sqlydmIds;
	}else if("10277"==jQuery("#xxdm").val()){
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&ylzd5="+pjyydm();
	}else if("12389"==jQuery("#xxdm").val()){
        var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&ylzd9="+sqlydmIds;
    }else {
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt;
	}
	
	ajaxSubFormWithFun("knsrdForm",url,function(data){
		var msg = data["message"];
		if("save"==saveType){
			msg = msg.replace('！','，') + '但需提交后才生效！';
		} else if ("submit"==saveType) {
			msg = '提交成功！';
		}
		showAlert(msg,{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}		
		}});
	});
}

/**
 * 困难生申请
 * @return
 */
function knssq(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var sfysq = jQuery("#sfysq").val();
	
	if ("true" == sfysq){
		showAlertDivLayer("当前周期已有申请记录，无需重复申请");
		return false;
	}
	showDialog('困难生认定申请',780,550,'xszz_knsrd.do?method=knssq');
}


/**
 * 困难生申请管理--修改
 * @return
 */
function knssqUpdate(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		showDialog("困难生认定申请修改",780,550,"xszz_knsrd.do?method=knssq&type=update&xh="+rows[0]["xh"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]);
	}
}

/**
 * 困难生申请管理--删除
 * @return
 */
function knssqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的申请记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_sc").val());
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("xszz_knsrd.do?method=delKnssq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function cancle(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsrd.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function submitBusi(){
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsrd.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : rows[0]['xh'],
					shzt : rows[0]['shzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * 困难生申请管理--流程跟踪
 * @return
 */
function knssqLcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['guid']+"&splc="+rows[0]['shlc']);
	}
}

/**
 * 困难生审核--待处理、已处理面签切换
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		if("14008" == jQuery("#xxdm").val()) {
			var isopensh = jQuery("#isopensh").val();
			if ("false" == isopensh) {
				jQuery('#li_sh').css('display','none');
				jQuery("#li_qx").css("display","none");
			}else {
				jQuery("#li_sh").css("display","");
				jQuery("#li_qx").css("display","none");
			}
		}else {
			jQuery("#li_sh").css("display","");
			jQuery("#li_qx").css("display","none");
		}
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * 困难生认定--审核
 * @return
 */
function knsrdSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var flag=true;
	var xxdm = jQuery("#xxdm").val();
	
	if("14008" == xxdm) {
		var isopenSh = jQuery("#isopensh").val();
		if ("false" == isopenSh) {
			showAlertDivLayer("审核已关闭，请联系管理员！");
			return false;
		}
	}
	
	if (rows.length == 0){
		showAlertDivLayer("请选择您要审核记录！");
	} else if (rows.length == 1){
		var url = "xszz_knsrd.do?method=knsrdDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"];
		if("11998" == jQuery("#xxdm").val()){
			var zf = rows[0]['zf'];
			if(zf == null){
				zf = "";
			}
			url +="&zf="+zf;
		}
		showDialog("困难生认定审核",750,500,url);
	} else {
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["sjdcmc"]!=rows[i-1]["sjdcmc"]){
				flag=false;
				break;
			}

			if("12866" == xxdm || "13871" == xxdm){
                if(rows[i]["sjdcmc"]==""||rows[i]["sjdcmc"]==null){
                    showAlertDivLayer("前级推荐档次为空不能批量审核！");
                    return false;
                }
			}
		}
		if(!flag){
			showAlertDivLayer("请选择相同档次的困难生！");
			return false;
		}
		showDialog("批量审核",500,300,"xszz_knsrd.do?method=knsrdPlsh&sjdcmc="+rows[0]["sjdc"]);
	}
}

/**
 * 撤消审核
 * @return
 */
function cancelKnssh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("请选择您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "xszz_knsrd.do?method=cancelKnssh";
		var msg="<div><ul>";
		var callBackMsg="";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			jQuery.ajaxSetup({async:false});
			if(ty=="ok"){
				for ( var i = 0; i < rows.length; i++) {
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[i]["shlc"],shid:rows[i]["shid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[i]["guid"]},function(result){
								callBackMsg=result["message"];
							},'json');
						}else{
							callBackMsg=data["message"];
						}
						msg+="<li><span style='width:40%;display:inline-block'>"+rows[i]["xh"]+"</span><font class='have'>"+callBackMsg+"</font></li>";
				},'json');
				}
				msg+="</ul></div>";
				showAlertDivLayer(msg,{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
			}});
			}
			
			jQuery.ajaxSetup({async:true});
		});
		
	}
}

/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,rddc,ylzd3,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var thgw = "";
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});
	if("13871" == jQuery("#xxdm").val()){
		if(shzt == '3'){
			thgw = '-1';
		}
	}
	jQuery.post(
		"xszz_knsrd.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 ylzd3:ylzd3,
		 shyj:shyj,
		 thgw:thgw
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function saveKnssh(){
	var shzt = jQuery("#shjg").val();

	if("12866"==jQuery("#xxdm").val()&&(jQuery("#ylzd4").val() == "")){
		showAlert("请选择民主评议结果！");
		return false;
	}
	
	if ("1" == shzt && jQuery("#rddc").val() == ""){
		showAlert("请选择推荐档次！");
		return false;
	}
	if("13871" == jQuery("#xxdm").val()){
		if(jQuery.trim(jQuery("#knpx").val()) == ""){
			showAlert("请填写困难排序！");
			return false;
		}
	}
	/*浙大需求改动，该字段去除，by yxy 2015-11-20
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlert("请选择无偿资助金额！");
		return false;
	}*/

	if (jQuery("#shyj").val() == ""){
		showAlert("请填写审核意见！");
		return false;
	}
	
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "xszz_knsrd.do?method=saveKnssh";
		ajaxSubFormWithFun("knsrdForm",url,function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

//打印报表
function printKnssq(url){

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}

//打印困难生申请流程图
function printSqlct(url){
	window.open(url);
}

/**
 * 刷新困难生认定申请界面
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrd.do?method=knssq&xh="+xh;
}

/**
 * 困难生认定申请界面编辑家庭情况
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog('家庭情况调查',945,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
			close:function(){
				loadJtqk(xh);
			}
		});
	}else{
		showAlertDivLayer("请先选择学生！");
	}
}

/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	if("11998" == jQuery("#xxdm").val()){
		return "<a href='javascript:void(0);' class='name' onclick='knsrdView(\""+rowObject["guid"]+"\",\""+rowObject["zf"]+"\");'>"+cellValue+"</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='knsrdView(\""+rowObject["guid"]+"\",\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
	}
}

function sqxzLink(cellValue,rowObject){
	if(null==cellValue||""==cellValue){
		return "新申请";
	}else{
		return cellValue;
	}
}
	

/**
 * 困难生认定查询
 * @param guid
 * @return
 */
function knsrdView(guid,para){
	if(guid == para){
		showDialog('困难生审核查看',780,520,'xszz_knsrd.do?method=knsrdView&guid='+guid);
	}else{
		if(para == "null"){
			para = "";
		}
		showDialog('困难生审核查看',780,520,'xszz_knsrd.do?method=knsrdView&guid='+guid+"&zf="+para);
	}
}

/**
 * 审核统计
 */
function knsrdShqk(){
	showDialog("困难生认定审核统计情况",470,300,"xszz_knsrd.do?method=knsrdShqk",{max:false,min:false});
}



//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsrd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//自定义导出功能（审核）
function exportConfigSh() {	
	var DCCLBH = "xszz_knsrd_sh.do";//dcclbh,导出功能编号，执行导出函数 
	customExport(DCCLBH, exportDataSh);
}

//导出方法（审核）
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var DCCLBH = "xszz_knsrd_sh.do";
	var url = "xszz_knsrd.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/* 浙大个性化 
       各院系审核通过的困难、特困的人数和比例
   2016年10月17日     开发人员：孟威*/
function viewYxKnsfp(){
	showDialog("各院系审核通过的困难生情况",750,400,"xszz_knsrd.do?method=viewYxKnsfp");
}

function pjyydm(){
	var values=[];
	jQuery("input[name=ylzd5]:checked").each(function(){
		values.push(jQuery(this).val());
	});
	return values.join(",");
}

//杭师大个性化功能
function downloadDjb(lx){

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}
//浙江同济下载登记表个性化
function viewPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var action = "xszz_knsrd.do?method=viewPrint";
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	}else{
		var guid = jQuery("#dataTable").getSeletIds();
		for(var i = 0; i < guid.length ; i++){
			var url = action + "&guid=" +guid[i];
			window.open(url);
		}
	}
	
	
}
