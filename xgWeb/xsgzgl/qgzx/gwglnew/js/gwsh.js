jQuery(function() {
	jQuery("#jfhb,#zc").change(function(){
		checkJe(this);
  		var jfhb = jQuery("#jfhb").val();
  		var zc = jQuery("#zc").val();
  		if(jfhb==""&&zc==""){
  			jQuery("#gwcjbz").val("");
  		}else{
  		if(jfhb!=""&&zc==""){
  			jQuery("#gwcjbz").val(parseInt(jfhb));
		}else if(jfhb==""&&zc!=""){
			jQuery("#gwcjbz").val(parseInt(zc));
		}else if(jfhb!=""&&zc!=""){
			jQuery("#gwcjbz").val(parseInt(jfhb)+parseInt(zc));
		}}
	});
});
jQuery(document).ready(function() {
	var sfsdgwcjsx = jQuery("#sfsdgwcjsx").val();
	// 基础配置 设置的薪酬上限
		var gwzgcjsx = jQuery("#gwzgcjsx").val();
		var sfkgggwcjsx = jQuery("#sfkgggwcjsx").val();
		// 岗位设置酬金上限
		var gwcjsx = jQuery("#gwcjsx").val();
		// 如果此岗位未设置
		
		if (gwcjsx == "") {
			jQuery("#gwcjsx").val(gwzgcjsx);
			jQuery("#gwcjsxh").text(gwzgcjsx);
		}
		if ("no" == sfsdgwcjsx) {
			jQuery("#gwcjsxTr").hide();
		} else {
			jQuery("#gwcjsxTr").show();
			jQuery("#gwcjsx").hide();
			jQuery("#gwcjsxh").show();
			jQuery("#sxcolor").hide();
		}
		if("10704"==jQuery("#xxdm").val()&&jQuery("#gwxzmc").val()=="固定岗"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
	});
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//查询
function searchRs(){
	var url = "qgzx_gwglnew_ajax.do?method=gwshCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


//审核
function showModi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length==1){	
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwshDgsh&pkValue="+pkValue+"&&shid=" + rows[0]["shid"]+ "&&gwid=" + rows[0]["gwid"];
		//showTopWin(url,800,600);
		showDialog('岗位审核', 760, 470, url);
	}else if(ids.length>1){
		var str = "";
		for (var i=0;i<ids.length;i++) {
			var pkValue = rows[i]["gwdm"];
			str += pkValue+"!!@@!!";
		}
		//var message = checkScInfo(str);
		//if("true"!=message){
		//	alertInfo(message,function(tag){
		//		if(tag=="ok"){
		//			return false;
		//		}
		//	});
		//	return false;
		//}
		//jQuery("#pkValue").val(str);
		//tipsWindown("岗位审核","id:tempDiv","580","180","true","","true","id");
		var url="qgzx_gwglnew.do?method=gwshPlsh&pkValue="+str;
		showDialog('岗位批量审核', 580, 280, url);
	}else{
		showAlertDivLayer("请选择您要审核的记录！");
		return false;
	}
}


function gwxxshBc(){
	if("10351"==jQuery("#xxdm").val()){
		if(!checksave()){
			return false;
		}
	}
	if("12867" ==jQuery("#xxdm").val()){
		
		if(jQuery("#xn").length>0 && jQuery("#xn").val() == "" ){
			showAlert("请选择学年！");
			return false;
		}
		if(jQuery("#xq").length>0 && jQuery("#xq").val() == "" ){
			showAlert("请选择学期！");
			return false;
		}
		if(jQuery("#nd").length>0 && jQuery("#nd").val() == "" ){
			showAlert("请选择年度！");
			return false;
		}
		if(jQuery("#yxssz").length>0 && jQuery("#yxssz").val() == "" ){
			showAlert("请选择有效时设置！");
			return false;
		}
		if(jQuery("#sfsgwsqsxz").length>0 && jQuery("#sfsgwsqsxz").val() == "" ){
			showAlert("请选择是否受岗位申请数限制！");
			return false;
		}
		if(jQuery("#gwkssj").length>0 && jQuery("#gwkssj").val() == "" ){
			showAlert("请选择岗位开始日期！");
			return false;
		}
		var yxssz = jQuery("[name=yxssz]:checked").val();
		if(yxssz=='xs' && $("gwjssj").value==""){
			showAlert("岗位结束时间不能为空！");
			return false;
		}
	}
	if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
		showAlert("请填写审核意见！");
		return false;
	}
	if (jQuery("#gwmc").val() == "" || jQuery("#gwmc").val() == ""){
		showAlert("请填写岗位名称！");
		return false;
	}
	if (jQuery("#xqrs").val() == "" || jQuery("#xqrs").val() == ""){
		showAlert("请填写需求人数！");
		return false;
	}
	if (jQuery("#gwlx").val() == "" || jQuery("#gwlx").val() == ""){
		showAlert("请选择岗位性质！");
		return false;
	}
	if (jQuery("#knsrs").val() == "" || jQuery("#knsrs").val() == ""){
		showAlert("请填写困难生数！");
		return false;
	}
	if (jQuery("#gwshr").val() == "" || jQuery("#gwshr").val() == ""){
		showAlert("请选择岗位审核人！");
		return false;
	}
	if (jQuery("#gwryyq").val() == "" || jQuery("#gwryyq").val() == ""){
		showAlert("请填写岗位要求！");
		return false;
	}
	if (jQuery("#gwms").val() == "" || jQuery("#gwms").val() == ""){
		showAlert("请填写工作内容！");
		return false;
	}
	   if(jQuery("#jfhb").length > 0&&jQuery("#gwcjbz").length > 0&&jQuery("#zc").length > 0){
	    	var gwcjbz= parseInt(jQuery("#gwcjbz").val());
	    	var jfhb = parseInt(jQuery("#jfhb").val());
	    	var zc = parseInt(jQuery("#zc").val());  
	    	if((zc+jfhb)!=gwcjbz){
	    		showAlert("不符合【岗位酬金标准=经费划拨+自筹】！");
	    		return false;
	    	}
	    }
	   
	var parameter={}
	var url="qgzx_gwglnew_ajax.do?method=gwxxshBc";	
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["lxr"]=encodeURI(encodeURI(jQuery("#lxr").val()));
	parameter["lxPhone"]=escape(jQuery("#lxPhone").val());
	parameter["sqsj"]=escape(jQuery("#sqsj").val());
	parameter["yrbm"]=escape(jQuery("#yrdwdm").val());
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["xq"]=escape(jQuery("#xq").val());
	parameter["nd"]=escape(jQuery("#nd").val());
	parameter["yxssz"]=escape(jQuery("#yxssz").val());
	parameter["sfsgwsqsxz"]=escape(jQuery("#sfsgwsqsxz").val());
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["qgzq"]=escape(jQuery("#qgzq").val());
	parameter["shyj"]=escape(jQuery("#shyj").val());
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));	
	parameter["gwyxs"]=encodeURI(encodeURI(jQuery("#gwyxs").val()));
	jQuery.ajaxSetup({async:false});	
	
	ajaxSubFormWithFun("gwshform",url,function(data){
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	jQuery.ajaxSetup({async:true});
}

function checksave(){
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("岗位性质不能为空！");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("困难生数不能大于需求人数！");
 		return false;
	}
	if($("gwkssj").value==""){
		showAlert("岗位开始时间不能为空！");
 		return false;
	}
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='xs' && $("gwjssj").value==""){
		showAlert("岗位结束时间不能为空！");
		return false;
	}
	
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=bcXgGwshInfo";
	jQuery.post(url,parameter,
			function(result){
				if("保存成功"==result){
					/*showAlert(result+"！",{},{"clkFun":function(){
		 				if (parent.window){
		 					refershParent();
		 				}
		 			}});*/
				}else{
					showAlert(result+"！");
		     		return false;
				}
			}
		);
	return true;
}

function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}
function checkJe(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splcid']);
	}
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["gwdm"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("qgzx_gwglnew.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("qgzx_gwglnew.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		}});
	}
}

//批量审核
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcids =  new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["gwdm"]);
		gwid.push(row["gwid"]);
		xhs.push(row["sqrzgh"]);
		splcids.push(row["splcid"]);
	});
	jQuery.post("qgzx_gwglnew_ajax.do?method=savePlsh", {
		shzt : shzt,
		splcids : splcids,
		id : guid,
		gwids : gwid,
		sqrs : xhs,
		shyj : shyj,
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}