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
	});
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//查询
function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=gwshCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


//审核
function showModi(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var url="qgzx_gwgl.do?method=gwshDgsh&pkValue="+pkValue;
		//showTopWin(url,800,600);
		showDialog('', 760, 470, url);
	}else if(len>1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
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
		var url="qgzx_gwgl.do?method=gwshPlsh&pkValue="+str;
		showDialog('', 580, 280, url);
	}else{
		alertInfo("请勾选需要审核的记录！");
		return false;
	}
}


function gwxxshBc(type){
	if($("shyj") && $("shyj").value==""){
 		alertInfo("审核意见不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=gwxxshBc";	
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["shzt"]=type;
	parameter["yrbm"]=escape(jQuery("#yrdwdm").val());
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["shyj"]=escape(jQuery("#shyj").val());
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			var len = jQuery("#pkValue").val().split("!!@@!!").length;
			if(len>1){
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}else if(len==1){
				//alertInfo(result);
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}