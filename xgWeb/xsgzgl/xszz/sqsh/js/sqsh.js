/**
 * 取消申请
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xszz_sqsh.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 取消申请 学生用
 * @return
 */
function xmsqDeleteStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	var ids = [];
	for ( var int = 0; int < rows.length; int++) {
		ids.push(rows[int]['guid'])
	}
	
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else {

		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("只能删除未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
			jQuery.post("xszz_sqsh.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 流程跟踪
 * @return
 */
function xmsqLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['guid']+"&splc="+rows[0]['shlc']);
	}
}

/**
 * 项目申请
 * @return
 */
function xmsq(){
	showDialog("资助项目申请",700,500,"xszz_sqsh.do?method=xszzXmsq");
}


/**
 * 修改申请 
 * @return
 */
function updateXmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		
		showDialog("资助项目修改",750,500,"xszz_sqsh.do?method=updateXmsq&guid="+rows[0]["guid"]);
	}
}


/**
 * 选择资助项目
 * @return
 */
function showXmxz(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("选择资助项目",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh);
	} else {
		showAlertDivLayer("请先选择学生！");
	}
}

/**
 * 展示家庭情况调查信息
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
 * 确定选择项目
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	
	var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
		tbody.find("tr").remove();
		
	jQuery.each(selectBox,function(i,e){
		var jesfxssq=jQuery(e).parents("tr").eq(0).find("td").eq(2).find("input").val();
		if(jesfxssq=='1'){
			var trHtml = "<tr>";
			trHtml+="<td style='width: 30%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td><td style='width: 30%'>";
			trHtml+="<input type='hidden' name='je' value='"+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"'/>";
			trHtml+="<input type='text' name='ylzd1'  onkeyup='checkInputData(this);checkJesx(this)' style='width: 40%'/>";
			trHtml+="<font color='blue'>上限金额："+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"<font/>";
			trHtml+="</td>";
			trHtml+="<td style='width: 30%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
		}else{
			var trHtml = "<tr>";
			trHtml+="<td style='width: 30%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td><td style='width: 30%'>";
			trHtml+="<input type='hidden' name='ylzd1' value='"+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(2).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
		}
	});
	iFClose();
}

//验证学生申请金额是否超过金额上限
function checkJesx(obj){
	var ylzd1= jQuery(obj).val();
	var je = jQuery(obj).parent("td").find("input").eq(0).val();
	if(parseFloat(ylzd1)>parseFloat(je)){
		showAlertDivLayer("申请金额超过项目金额上限！");
		jQuery(obj).val('');
	}
}
//验证学生申请金额是否超过金额上限（审核页面）
function checkJesxSh(obj){
	var ylzd1= jQuery(obj).val();
	var jesx = jQuery("#jesx").val();
	if(parseFloat(ylzd1)>parseFloat(jesx)){
		showAlertDivLayer("申请金额超过项目金额上限！");
		jQuery(obj).val('');
	}
}


/**
 * 保存项目申请
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	var xmdmArray = jQuery("input[name=xmdmArray]");
	
	if (xh == ""){
		showAlert("请先选择学生！");
		return false;
	}
	
	if (xmdmArray.length == 0){
		showAlert("请先选择学生您要申请的资助项目！",{},{"clkFun":function(){
			showDialog("资助项目申请",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh);
		}});
		return false;
	}
	
	if (!checkMustNotNull()){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	var url = "xszz_sqsh.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}
//提交
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		if('3'!=rows[0]['shzt']&&"false"==rows[0]['isopen']){
			showAlertDivLayer("当前申请项目时间已关闭，请与管理员联系！");
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_sqsh.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//提交 --学生用
function submitBusiStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var int = 0; int < rows.length; int++) {
		ids.push(rows[int]['guid'])
	}
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		if(rows[0]["sqkg"]!="1"&&"3"!=rows[0]["shzt"]){
			showAlertDivLayer("所选项目申请状态未开放，请重新选择");
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_sqsh.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : jQuery("input[name='xh']").val()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
function cancle(){
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
				jQuery.post("xszz_sqsh.do?method=cancle", {
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

/**
 * 选择项目页，切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").eq(-2).css("display","none");
		jQuery("#titleTr td").last().css("display","");
	} else {
		jQuery("#titleTr td").last().css("display","none");
		jQuery("#titleTr td").eq(-2).css("display","");
	}
}


/**
 * 保存修改申请
 * @return
 */
function saveSqxg(type){
	if (!checkMustNotNull()){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	var isopen = jQuery("#isopen").val();
	var shzt = jQuery("#shzt").val();
	
	if("submit"==type&&"false"==isopen&&"3"!=shzt){
		showAlertDivLayer("当前申请项目时间已关闭，请于管理员联系！");
		return false;
	}

	var url = "xszz_sqsh.do?method=saveSqxg&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * 切换审核页tab页
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		var gridSetting = getDclGird();
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		var gridSetting2 = getYclGrid();
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}


/**
 * 资助项目审核
 * @return
 */
function zzxmSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("请选择您要审核记录！");
	} else if (rows.length == 1){
		showDialog("资助项目审核",750,500,"xszz_sqsh.do?method=zzxmDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"]);
	} else {
		//showAlertDivLayer("请选择一条您要审核的记录！");
		showDialog("批量审核",500,340,"xszz_sqsh.do?method=zzxmPlsh");
	}
}


/**
 * 撤消审核
 * @return
 */
function cancelXmsh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		
		//最后一级撤销审核后调用的路径
		var cancelPath = "xszz_sqsh.do?method=cancelXmsh";
		confirmInfo("您确定要撤销操作吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("xszz_sqsh.do?method=cxshnew",{shlc:rows[0]["shlc"],shid:rows[0]["shid"],xh:rows[0]["xh"],xtgwid:rows[0]["xtgwid"]},function(data){
						// 判断是否最后一级撤销(1:最后一级撤销成功）
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[0]["guid"]},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});
		
	}
}


/**
 * 保存批量审核
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj,shxmje){
	
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var thgw = "";
	if(shzt == '3'){
		thgw = "-1";
	}
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xszz_sqsh.do?method=zzxmPlsh&type=save",
		{id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 shzt:shzt,
		 shxmje:shxmje,
		 thgw:thgw
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	)
}


/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function saveZzsh(){

	if(jQuery("#shxmje").css("display")!="none"){
		if(jQuery("#shxmje").val()==""){
			showAlertDivLayer("请填写项目金额！");
			return false;
		}
	}
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	
	//showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		var url = "xszz_sqsh.do?method=saveXmsh";
		ajaxSubFormWithFun("sqshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	//}});
}


/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='xmsqView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}

/**
 * 困难生认定查询
 * @param guid
 * @return
 */
function xmsqView(guid){
	showDialog('资助申请查看',750,500,'xszz_sqsh.do?method=viewXmsq&guid='+guid);
}

/**
 * 条件检测
 * @return
 */
function checkCondition(){
	var xh = jQuery("#xh").val();
	var checkbox = jQuery("#wsq input:checkbox");
	var _check = function(object){
		var _self = jQuery(object);
		var xmdm = _self.val();
		jQuery.post("xszz_sqsh.do?method=checkCondition",{xmdm:xmdm,xh:xh},function(data){
			if (""==data||data.length == 0){
				_self.parents("tr").eq(0).find("td").eq(-2).html("<font color='green'>无限制条件</font>");
			} else {
				
				var html="";
				
				for (var j = 0, m = data.length ; j<m ; j++){
					if (data[j]["result"] == "true"){
						html+="<img src='images/ico_38.gif' title='符合条件'/>";
					} else {
						html+="<img src='images/ico_39.gif' name='faidImg' title='不符合条件'/>";
						_self.attr("disabled","disabled");
					}
					html+= "  ";
					html+= (j+1)+"、";
					html+= data[j]["sqts"];
					html+= ";";
					html+="<br/>";
				}
				
				_self.parents("tr").eq(0).find("td").eq(-2).html(html);
				
			}
			_self.parents("tr").eq(0).find("td").last().css("display","none");
		},'json');
	}
	
	
	jQuery.ajaxSetup({async:false});
	
	jQuery.each(checkbox,function(i,e){
		_check(e);
	});
	
	jQuery.ajaxSetup({async:true});
}

//打印报表
function printXmsq(url){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要打印的记录！");
	} else {

		var url= url+"&guid="+rows[0]["guid"]+"&xh="+rows[0]["xh"]+"&xmmc="+rows[0]["xmmc"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]
      //判断该项目是否有报表
        jQuery.post("xszz_xmwh.do?method=getXszzdm",{"xmmc":rows[0]["xmmc"],"xn":rows[0]["xn"],"xq":rows[0]["xq"]},function(data){
			if(data["message"] != null && data["message"]!=""){
				window.open(url);
			}else{
				showAlertDivLayer("该项目尚未设置报表，请联系管理员！");
				return false;
			}
		},'json');
    }
}


/**
 * 兼得控制
 * @param obj
 * @return
 */
function checkJdsz(obj){
	var xmdm = jQuery(obj).val();
	
	jQuery.post("xszz_xmwh_jdsz.do?method=getKjdxm",{xmdm:xmdm},function(jsonMap){

		var data = jsonMap["data"];
		var kgzt = jsonMap["jdkg"];
		
		if (kgzt != "1"){
			return ;
		}
		
		jQuery.each(data,function(i,n){
			var bjdxm = data[i]["dm"];
			
			var yhdxm = jQuery("#ysq input:checkbox[value="+bjdxm+"]");
			if (yhdxm.length >0){
				
				showAlert("您已申请资助项目“"+data[i]["mc"]+"”，不能再申请当前项目！",{},{"clkFun":function(){
					jQuery(obj).attr("checked",false);
				}});
			}

			var yxzxm = jQuery("#wsq input:checked[value="+bjdxm+"]");

			if (yxzxm.length >0){
				
				showAlert("您已选择资助项目“"+jQuery.trim(yxzxm.parent().next().html())+"”，不能再选择当前项目！",{},{"clkFun":function(){
					jQuery(obj).attr("checked",false);
				}});
			}
		});
	},'json')
}

function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}



var DCCLBH = "xszz_sqsh_xmsq.do";//dcclbh,导出功能编号
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号，执行导出的函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_sqsh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//审核导出
function exportConfigSh(){
	var DCCLBH='xszz_sqsh_xmsh.do';
	customExport(DCCLBH, exportDataSh);
}

//审核导出方法
function exportDataSh(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='xszz_sqsh_xmsh.do';
	
	setSearchTj();//设置高级查询条件
	
	var url = "xszz_sqsh.do?method=exportDataSh&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印word
function getWord(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
		showAlertDivLayer("请选择一条记录！");
	 } else if (rows.length > 1){
		var url="xszz_sqsh.do?method=downloadZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	 } else {
		var url="xszz_sqsh.do?method=downloadWord";		
		url += "&guid="+rows[0]["guid"];
		window.open(url);
   }
}

//打印学生资助申请流程图
function printSqlct(url){
	
	var url = "xszz_sqsh.do?method=printLct";
	window.open(url);
}


/**
 * 审核统计
 */
function zzxmShqk(){
	showDialog("学生资助审核统计情况",550,400,"xszz_sqsh.do?method=zzxmShtj",{max:false,min:false});
}
//验证项目金额是否可修改
function jeSfkt(obj){
	
	
	var xmdm=jQuery(obj).val();
	jQuery.post('xszz_sqsh.do?method=jeSfkt',{"xmdm":xmdm},function(data){
		if(data["jesfxssq"]=='1'){
			jQuery('.je').css("display","");
			jQuery('#jesx').val(data["je"]);
			if(jQuery("#shxmdm").val()==jQuery("#oldshxmdm").val()){
				getXmje();
			}
			else{
				jQuery("#shxmje").val('');
			}
			
			jQuery('#message').text('上限金额:'+data["je"]);
		}else{
			jQuery('.je').css("display","none");
			jQuery('#shxmje').val('');
		}
		
	},'json');
}
//获取项目金额
function getXmje(){
	if(jQuery("#shlccx_table tr").length>2){
		jQuery("#shxmje").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
		}
	else{
	jQuery("#shxmje").val(jQuery("#sqje").val());
	}
	
}

/**
 * 异常数据处理
 * @return
 */
function exceptionDataResolve(){

        showConfirmDivLayer("您确定要进行提交异常数据处理吗？",{"okFun":function(){
            jQuery.post("xszz_sqsh.do?method=exceptionDataResolve",function(data){
                showAlertDivLayer(data["message"]);
            },'json');
        }});
}