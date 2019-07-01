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

var demoHtml = "";
function initData(){//岗位描述示例
	
	demoHtml = "请按如下格式输入内容\n\n";
	demoHtml += "例如：\n";
	demoHtml += "工作地点：A楼101室\n";
	demoHtml += "工作内容：日常文档处理\n";
	demoHtml += "工作时间：星期一、三（上午8:00~12:00）";
	if(null==jQuery("#gwms").val()||""==jQuery("#gwms").val()){
	jQuery("#gwms").val(demoHtml);
	jQuery("#gwms").css("color", "#999");
	}
	
	jQuery("#gwms").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#gwms").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}


jQuery(document).ready(function(){
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
	// 基础配置 设置的薪酬上限
	var gwzgcjsx=jQuery("#gwzgcjsx").val();
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
	// 岗位设置酬金上限
	var gwcjsx=jQuery("#gwcjsx").val();
	// 如果此岗位未设置
	if(gwcjsx==""){
		jQuery("#gwcjsx").val(gwzgcjsx);
		jQuery("#gwcjsxh").text(gwzgcjsx);
	}
	
	if("10704"!=jQuery("#xxdm").val()){
		if("no"==sfsdgwcjsx){
			jQuery("#gwcjsxTr").hide();
		}else{
			jQuery("#gwcjsxTr").show();
			if("no"==sfkgggwcjsx){
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}else{
				jQuery("#gwcjsx").show();
				jQuery("#gwcjsxh").hide();
				jQuery("#sxcolor").show();
			}
		}
	}else{
		var select = document.getElementsByName("gwxzdm");
		var gwxz = jQuery(select).find("option:selected").text();
		if(gwxz=="固定岗"){
			jQuery("#gdgcjbzTr").show();
			jQuery("#gwcjsxTr").hide();
		}else{
			if("no"==sfsdgwcjsx){
				jQuery("#gwcjsxTr").hide();
			}else{
				jQuery("#gwcjsxTr").show();
				if("no"==sfkgggwcjsx){
					jQuery("#gwcjsx").hide();
					jQuery("#gwcjsxh").show();
					jQuery("#sxcolor").hide();
				}else{
					jQuery("#gwcjsx").show();
					jQuery("#gwcjsxh").hide();
					jQuery("#sxcolor").show();
				}
			}
		}
	}
	var doType=jQuery("#doType").val();
	//人员增减 界面只能查看
	if(doType=="ryxxZj"||doType=="ryxxTg"){
		if("10704"==jQuery("#xxdm").val()&&jQuery("#gwxzmc").val()=="固定岗"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
		jQuery("#gwcjsx").hide();
		jQuery("#gwcjsxh").show();
		jQuery("#sxcolor").hide();
	}
});
function checkSxje(){
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
	//基础配置 设置的薪酬上限
	var gwzgcjsx=jQuery("#gwzgcjsx").val();
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
	if("yes"==sfsdgwcjsx&&"yes"==sfkgggwcjsx){
		//岗位设置酬金上限
		var gwcjsx=jQuery("#gwcjsx").val();
		if(gwcjsx==""){
			showAlert("岗位酬金上限不能为空！");
	 		return false;
		}
		if(parseInt(gwcjsx)>parseInt(gwzgcjsx)){
			showAlert("岗位酬金上限不得超过系统设置的岗位最高酬金上限("+gwzgcjsx+")！");
	 		return false;
		}
	}
	return true;
}
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


// 修改gwxxXg 查看 gwxxCk人员增加ryxxZj人员退岗ryxxTg
function showModi(type,title){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
		var sfyxgw=rows[0]["sfyxgw"];
		if((type=='ryxxZj' || type=='ryxxTg') && sfyxgw=='否'){
			showAlertDivLayer("请选择有效岗位！");
			return false;
		}
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog(title, 760, 520, url);
	}else{
		if(type=='ryxxTg'){
			if(rows.length == 0){
				// 重置
				jQuery("#gwdmPlHidden").val("");
				// 根据查询结果进行批量退岗
				var a_name_sf = jQuery("a[name=a_name_sf]");
				var sf_num = a_name_sf.length;
				if(sf_num == 1 && a_name_sf.eq(0).attr("id").replace("a_id_","") == '是'){
					var rowConut = jQuery("#rowConut").html();
					var url = "qgzx_gwglnew.do?method=gwxxTgWin&len="+rowConut;
					var title = "批量退岗";
					showDialog(title,380,200,url);
				}else{
					showAlertDivLayer("条件【是否有效岗位】必须选【是】才能批量退岗！");
					return false;
				}
			}else{
				// 根据勾选记录进行批量退岗
				var gwdms = new Array();
				for(var i=0;i<rows.length;i++){
					if(rows[i]['sfyxgw']=='否'){
						showAlertDivLayer("请选择有效岗位！");
						return false;
					}
					gwdms.push(rows[i]['gwdm']);
				}
				jQuery("#gwdmPlHidden").val(gwdms.join(','));
				var url = "qgzx_gwglnew.do?method=gwxxTgWin&len="+rows.length;
				var title = "批量退岗";
				showDialog(title,380,200,url);
			}
		}else{
			showAlertDivLayer("请选择一条您要操作的记录！");
		}
	}
}

//岗位信息导入
function gwxxDr(){
	toImportData("IMPORT_QGZX_GWXX");
	return false;
}

//岗位人员信息导入
function gwryDr(){
	toImportData("IMPORT_QGZX_GWRY");
	return false;
}

// 删除
function deleteGwxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length>=1){
		var str = "";
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly'] == "1"){
				showAlertDivLayer("审核流程过来的数据不能删除！");
				return false;
			}
			var pkValue = rows[i]["gwdm"];
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			showAlertDivLayer(message);
			return false;
		}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{
			"okFun" : function() {
				var parameter={}
				var url="qgzx_gwglnew_ajax.do?method=gwxxSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						showAlertDivLayer(result);
						jQuery("#dataTable").reloadGrid();
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		showAlertDivLayer(jQuery("#lable_some_sc").val());
	}
}

// 验证删除信息
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_gwglnew_ajax.do?method=checkScInfo";	
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

// 增加保存岗位信息
function zjBcGwxx(){
	if($("xn") && $("xn").value==""){
		showAlert("学年不能为空！");
 		return false;
	}
	if($("yrbm") && $("yrbm").value==""){
		showAlert("用人部门不能为空！");
 		return false;
	}
	if($("gwmc") && $("gwmc").value==""){
		showAlert("岗位名称不能为空！");
 		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("岗位性质不能为空！");
 		return false;
	}
	
	if($("xqrs") && $("xqrs").value==""){
		showAlert("需求人数不能为空！");
 		return false;
	}
	if($("knsrs") && $("knsrs").value==""){
		showAlert("困难生数不能为空！");
 		return false;
	}
	if(jQuery("#zxdwlb") && jQuery("#zxdwlb").val() == ""){
		showAlert("用人单位类别 不能为空！");
 		return false;
	}
	if(jQuery("#zjly") && jQuery("#zjly").val() == ""){
		showAlert("资金来源不能为空！");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("困难生数不能大于需求人数！");
 		return false;
	}
	var yxsszLen = jQuery("[name=yxssz]:checked").length;
	if(yxsszLen==0){
		showAlert("请选择有效时设置！");
		return false;
	}
	var sfsgwsqsxzLen = jQuery("[name=sfsgwsqsxz]:checked").length;
	if(sfsgwsqsxzLen==0){
		showAlert("请选择是否受岗位申请数限制！");
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
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
		showAlert("岗位描述不能为空！");
 		return false;
	}
	if($("gwryyq") && $("gwryyq").value==""){
		showAlert("岗位人员要求不能为空！");
 		return false;
	}
    if(jQuery("#ssbm") && jQuery("#ssbm").val()==""){
    	showAlert("所属部门不能为空！");
 		return false;
	}
    if(jQuery("#fzls") && jQuery("#fzls").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#stfzr") && jQuery("#stfzr").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
    	showAlert("联系电话不能为空！");
 		return false;
    }
    if(jQuery("#lsbgs") && jQuery("#lsbgs").val()==""){
    	showAlert("负责老师办公室不能为空！");
    	return false;
    }
    if(jQuery("#ssxq") && jQuery("#ssxq").val()==""){
    	showAlert("所属校区不能为空！");
 		return false;
    }
    if(jQuery("#gzdd") && jQuery("#gzdd").val()==""){
    	showAlert("工作地点不能为空！");
    	return false;
    }
    if(jQuery("#gzsj") && jQuery("#gzsj").val()==""){
    	showAlert("工作时间不能为空！");
    	return false;
    }
    if(jQuery("#gznr") && jQuery("#gznr").val()==""){
    	showAlert("工作内容不能为空！");
    	return false;
    }
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("岗位酬金标准不能为空！");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("经费划拨不能为空！");
    	return false;
    } 
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("岗位审核人不能为空！");
    	return false;
    } 
    //衢州学院个性化字段
    if(jQuery("#gwlx") && jQuery("#gwlx").val()==""){
    	showAlert("岗位类型不能为空！");
    	return false;
    } 
    
    if(jQuery("#zxfdy") && jQuery("#zxfdy").val()==""){
    	showAlert("助学管理辅导员不能为空！");
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
    if((parseInt(jQuery("#gwcjbz").val())-parseInt(jQuery("#gwcjsx").val()))>0){
    	showAlert("岗位酬金标准不得大于岗位酬金上限！");
		return false;
    }
	if(!checkSxje()){
		return false;
	}
	if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("请选择岗位申请开放学院！");
    		return false;
    	}
    }
	var message = checkBcInfo("add");
	if("true"!=message){
		showAlert(message);
		return false;
	}
    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("联系人不能为空！");
        return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
        showAlert("联系电话不能为空！");
        return false;
    }
	saveZjGwxxInfo();	
}


// 验证保存信息
function checkBcInfo(type){
	var data ="true";
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    if(type=="update"){
    	parameter["pkValue"]=escape(jQuery("#pkValue").val());
    }
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val());
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		parameter["gwjssj"]="";
	}else{
		parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	}
	parameter["yxssz"]=yxssz;
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=checkBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


// 增加保存岗位信息
function saveZjGwxxInfo(){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=encodeURI(encodeURI(jQuery("#gwmc").val()));
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["gwms"]=encodeURI(encodeURI(jQuery("#gwms").val()));
	parameter["gwryyq"]=encodeURI(encodeURI(jQuery("#gwryyq").val()));
	parameter["gwyqryxg"]=encodeURI(encodeURI(jQuery("#gwyqryxg").val()));
	parameter["bz"]=encodeURI(encodeURI(jQuery("#bz").val()));
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["zjly"] = jQuery("#zjly").val();
	parameter["zxdwlb"] = jQuery("#zxdwlb").val();
	parameter["ssbm"]=jQuery("#ssbm").val();
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#fzls").val()));
	parameter["lxdh"]=jQuery("#lxdh").val();
	parameter["lsbgs"]=encodeURI(encodeURI(jQuery("#lsbgs").val()));
	parameter["gzdd"]=encodeURI(encodeURI(jQuery("#gzdd").val()));
	parameter["gzsj"]=encodeURI(encodeURI(jQuery("#gzsj").val()));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()));
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	var url = "qgzx_gwglnew_ajax.do?method=save";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	//温州大学个性化
	if(jQuery("#xxdm").val() == '10351'){
		var checkboxs = jQuery("input[name='sqxy']:checked");
		var sqxy = [];
		jQuery.each(checkboxs,function(i,n){
			sqxy.push(jQuery(n).val());
		})
		parameter["sqxy"] = sqxy;
		parameter["doType"] = "zj";
	}
	//disabled字段表单提交处理
	var yrbddis;
	var ssbmdis;
	if(jQuery("#yrbm")){
		yrbddis = jQuery("#yrbm").attr("disabled");
		jQuery("#yrbm").attr("disabled", false);
	}
	if(jQuery("#ssbm")){
		ssbmdis = jQuery("#ssbm").attr("disabled");
		jQuery("#ssbm").attr("disabled", false);
	}

	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		//disabled字段表单提交处理
		if(jQuery("#yrbm")){
			jQuery("#yrbm").attr("disabled", yrbddis);
		}
		if(jQuery("#ssbm")){
			jQuery("#ssbm").attr("disabled", ssbmdis);
		}
		if(data["message"]=="保存成功！"){
	   		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	   	}else{
	   		showAlert(data["message"]);
	   		return false;
	   	}
	});
	jQuery.ajaxSetup({async:true});
}


// 验证需求人数
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
// 验证修改岗位信息
function bcXgGwxx(){
	if($("gwmc") && $("gwmc").value==""){
		showAlert("岗位名称不能为空！");
 		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("岗位性质不能为空！");
 		return false;
	}
	if($("xqrs") && $("xqrs").value==""){
		showAlert("需求人数不能为空！");
 		return false;
	}
	if(parseFloat($("xqrs").value)<parseFloat($("zgrs").value)){
		showAlert("需求人数不能小于在岗人数！");
 		return false;
	}
	if($("knsrs") && $("knsrs").value==""){
		showAlert("困难生数不能为空！");
 		return false;
	}
	if(jQuery("#zxdwlb") && jQuery("#zxdwlb").val() == ""){
		showAlert("用人单位类别 不能为空！");
 		return false;
	}
	if(jQuery("#zjly") && jQuery("#zjly").val() == ""){
		showAlert("资金来源不能为空！");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("困难生数不能大于需求人数！");
 		return false;
	}
	var yxsszLen = jQuery("[name=yxssz]:checked").length;
	if(yxsszLen==0){
		showAlert("请选择有效时设置！");
		return false;
	}
	var sfsgwsqsxzLen = jQuery("[name=sfsgwsqsxz]:checked").length;
	if(sfsgwsqsxzLen==0){
		showAlert("请选择是否受岗位申请数限制！");
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
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
		showAlert("岗位描述不能为空！");
 		return false;
	}
	if($("gwryyq") && $("gwryyq").value==""){
		showAlert("岗位人员要求不能为空！");
 		return false;
	}
    if(jQuery("#ssbm") && jQuery("#ssbm").val()==""){
    	showAlert("所属部门不能为空！");
 		return false;
	}
    if(jQuery("#fzls") && jQuery("#fzls").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#stfzr") && jQuery("#stfzr").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
    	showAlert("联系电话不能为空！");
 		return false;
    }
    if(jQuery("#lsbgs") && jQuery("#lsbgs").val()==""){
    	showAlert("负责老师办公室不能为空！");
    	return false;
    }
    if(jQuery("#ssxq") && jQuery("#ssxq").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#gzdd") && jQuery("#gzdd").val()==""){
    	showAlert("工作地点不能为空！");
    	return false;
    }
    if(jQuery("#gzsj") && jQuery("#gzsj").val()==""){
    	showAlert("工作时间不能为空！");
    	return false;
    }
    if(jQuery("#gznr") && jQuery("#gznr").val()==""){
    	showAlert("工作内容不能为空！");
    	return false;
    }
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("岗位酬金标准不能为空！");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("经费划拨不能为空！");
    	return false;
    } 
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("岗位审核人不能为空！");
    	return false;
    } 
    
    //衢州学院个性化字段
    if(jQuery("#gwlx") && jQuery("#gwlx").val()==""){
    	showAlert("岗位类型不能为空！");
    	return false;
    } 
    
    if(jQuery("#zxfdy") && jQuery("#zxfdy").val()==""){
    	showAlert("助学管理辅导员不能为空！");
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
    if((parseInt(jQuery("#gwcjbz").val())-parseInt(jQuery("#gwcjsx").val()))>0){
    	showAlert("岗位酬金标准不得大于岗位酬金上限！");
		return false;
    }
	if(!checkSxje()){
		return false;
	}
	if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("请选择岗位申请开放学院！");
    		return false;
    	}
    }
	var message = checkBcInfo("update");
	if("true"!=message){
		showAlert(message);
		return false;
	}
    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("联系人不能为空！");
        return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
        showAlert("联系电话不能为空！");
        return false;
    }
	saveXgGwxxInfo();	
}


// 保存修改岗位信息
function saveXgGwxxInfo(){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwmc"]=encodeURI(encodeURI(jQuery("#gwmc").val()));
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["gwms"]=encodeURI(encodeURI(jQuery("#gwms").val()));
	parameter["gwryyq"]=encodeURI(encodeURI(jQuery("#gwryyq").val()));
	parameter["gwyqryxg"]=encodeURI(encodeURI(jQuery("#gwyqryxg").val()));
	parameter["bz"]=encodeURI(encodeURI(jQuery("#bz").val()));
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["zjly"] = jQuery("#zjly").val();
	parameter["zxdwlb"] = jQuery("#zxdwlb").val();
	parameter["ssbm"]=jQuery("#ssbm").val();
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#fzls").val()));
	parameter["lxdh"]=jQuery("#lxdh").val();
	parameter["lsbgs"]=encodeURI(encodeURI(jQuery("#lsbgs").val()));
	parameter["gzdd"]=encodeURI(encodeURI(jQuery("#gzdd").val()));
	parameter["gzsj"]=encodeURI(encodeURI(jQuery("#gzsj").val()));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()))
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	var url = "qgzx_gwglnew_ajax.do?method=update";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	//温州大学个性化
	if(jQuery("#xxdm").val() == '10351'){
		var checkboxs = jQuery("input[name='sqxy']:checked");
		var sqxy = [];
		jQuery.each(checkboxs,function(i,n){
			sqxy.push(jQuery(n).val());
		})
		parameter["sqxy"] = sqxy;
		parameter["doType"] = "xg";
		parameter["gwdm"] = jQuery("#pkValue").val();
	}
	//disabled字段表单提交处理
	var yrbddis;
	var ssbmdis;
	if(jQuery("#yrbm")){
		yrbddis = jQuery("#yrbm").attr("disabled");
		jQuery("#yrbm").attr("disabled", false);
	}
	if(jQuery("#ssbm")){
		ssbmdis = jQuery("#ssbm").attr("disabled");
		jQuery("#ssbm").attr("disabled", false);
	}
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		//disabled字段表单提交处理
		if(jQuery("#yrbm")){
			jQuery("#yrbm").attr("disabled", yrbddis);
		}
		if(jQuery("#ssbm")){
			jQuery("#ssbm").attr("disabled", ssbmdis);
		}
		if(data["message"]=="保存成功！"){
   		 	showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 	}else{
   	 		showAlert(data["message"]);
   	 		return false;
   	 	}
	});
	jQuery.ajaxSetup({async:true});
}


// 全选
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}


// 增加行
function addTr(){
	// showTopWin('qgzx_gwglnew.do?method=getStu&pkValue='+jQuery("#pkValue").val(),800,700);
	var xn=jQuery("#xn").val();
	var url='qgzx_gwglnew.do?method=getStu&pkValue='+jQuery("#pkValue").val()+'&xn='+xn;
	showDialog('学生列表', 800, 520, url);
	return false;
}


// 删除行
function delTr(){
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	// [982]张昌路 增加删除判断，申请数据不能删除
	var isSqsj=false;
	var show="学号<br>[<font color='red'>";
	if (checkbox.length > 0){
		var xhs = "";
		for (var i = 0 ; i < checkbox.length ; i++){
			var xh = jQuery(checkbox[i]).val()+"!!@@!!";
			var xxxx=xh.split("!!@@!!");
			if(xxxx[2]=="1"||xxxx[2]==1){
				show+=xxxx[0];
				show+=",</br>";
				isSqsj=true;
			}
			xhs+=xh;
		}
		// 去掉最后一个多余 ",</br>"
		show=show.substring(0, show.length-",</br>".length);
		show+="</font>]<br>";
		if(isSqsj){
			show+="为申请数据，不能删除！";
			showAlert(show);
			return false;
		}
		var message = checkScRyxx(xhs);
		if("true"!=message){
			showAlert(message);
			return false;
		}
		showConfirmDivLayer("您确定要删除选择的学生吗？", {
			"okFun" : function() {
				jQuery.ajaxSetup({async:false});
				// 得到JSON对象
			    var parameter ={};	
			    parameter["gwdm"]=escape(jQuery("#pkValue").val());
			    parameter["xh"]=xhs;
				var url = "qgzx_gwglnew_ajax.do?method=bcScryxx";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						document.forms[0].action = window.location.href;
						document.forms[0].submit();
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	} else {
		showAlert("请选择您要删除的学生！");
 		return false;
	}
}


function checkScRyxx(xhs){
	var message = "true";
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=xhs;
	var url = "qgzx_gwglnew_ajax.do?method=checkScRyxx";
	jQuery.post(url,parameter,
		function(result){
			message = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return message;
}



// 验证保存人员信息
function bcZjRyxx(){
	var len = jQuery("#xhs").val().split("!!@@!!").length-1;
	if(len>parseInt(jQuery("#xqrs").val())){
		showAlert('增加的学生数已大于岗位的需求人数！');
 		return false;
	}
	saveRyxxInfo();
}


// 保存人员信息
function saveRyxxInfo(){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    var date=new Date;
    var year=date.getFullYear(); 
    var month=date.getMonth()+1;
    month =(month<10 ? "0"+month:month); 
    var mydate = (year.toString()+month.toString());
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["fknsrs"]=escape(jQuery("#fknsrs").val());
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["sgsj"]=escape(mydate);
    parameter["sfsgwsqsxz"]=escape(jQuery("#sfsgwsqsxz").val());
	var url = "qgzx_gwglnew_ajax.do?method=bcZjryxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if(result!="保存成功"){
				showAlert(result);
		 		return false;
			}else{
				document.forms[0].action = window.location.href;
				document.forms[0].submit();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}

function  saveRyzj(){
	var flag=true;
	var xsGwxx= [];
	jQuery.each(jQuery("#tbody_zgryxx tr"),function(i,n){
		var obj = {};
			var gwdm= jQuery("#pkValue").val();
			var sgsj = jQuery(n).find("td").find("input[name=sgsj]").val();
			if(null==sgsj||""==sgsj){
				flag=false;
				return false;
			}
			obj.gwdm=gwdm;
			obj.sgsj=sgsj;
			xsGwxx.push(obj);
	});
	if(!flag){
		showAlert("请填写上岗月份！");
		return false;
	}
	var xsGwxxStr = JSON.stringify(xsGwxx);
	jQuery("#xsGwxxStr").val(xsGwxxStr);
	var url = "qgzx_gwglnew_ajax.do?method=saveRyzj";
	ajaxSubFormWithFun("qgzxGwglNewForm", url, function(data) {
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
	
}

// 退岗信息div
function tgRyxx(){
	var len=jQuery("input[type=checkbox][name!=th]:checked").length;
	var blog=true;
	if(len>=1){	
		var array = jQuery("input[type=checkbox][name!=th]:checked");
		var xhs = "";
		var sgsjs = "";
		var sqbhs="";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).val().split("!!@@!!");
				xhs += pkValue[0]+"!!@@!!";
				sgsjs += pkValue[1]+"!!@@!!";
				sqbhs += pkValue[3]+"!!@@!!";
		}
		//tipsWindown("人员退岗","id:tempDiv","380","250","true","","true","id");
		var url='qgzx_gwglnew.do?method=ryxxTgWin&len='+len;
		showDialog('人员退岗', 380, 200, url);
		jQuery('#xhs').val(xhs);
		jQuery('#sgsjs').val(sgsjs);
		jQuery('#sqbhs').val(sqbhs);
	}else{
		showAlert("请选择您要退岗的学生！");
	}
}

// 保存退岗信息验证
function bcTgryxx(){
	if($("tgyy") && $("tgyy").value==""){
		showAlert("退岗原因不能为空！");
 		return false;
	}
	saveTgryxx();
}


function saveTgryxx(){
	var api = frameElement.api,W = api.get('parentDialog');
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue",W.document).val());
    parameter["xh"]=escape(jQuery('#xhs',W.document).val());
    parameter["sgsj"]=escape(jQuery('#sgsjs',W.document).val());
    parameter["tgyy"]=escape(jQuery("#tgyy").val());
    parameter["sqbhs"]=escape(jQuery("#sqbhs",W.document).val());
	var url = "qgzx_gwglnew_ajax.do?method=bcTgryxx";
	jQuery.post(url,parameter,
		function(result){
			showAlert(result,{},{"clkFun":function(){
				api.close();
				W.document.forms[0].action = W.window.location.href;
				W.document.forms[0].submit();
				return false;
			}});
			return false;
		}
	);
	jQuery.ajaxSetup({async:true});
}

// 展示学生岗位信息
function showXsxx(obj){
	jQuery.ajaxSetup({cache:false});
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(obj);
	var url="qgzx_gwglnew_ajax.do?method=ryxxCk";
	jQuery.getJSON(url,parameter,
			function(data){
				jQuery('#tbody_ryxx').empty();
				jQuery('#tbody_zggwxx').empty();
				if(data!=null){
					var tr="<tr><th width='16%'>学号</th><td width='34%'>"+data.xh+"</td><th width='16%'>姓名</th><td width='34%'>"+data.xm+"</td></tr>";
					tr+="<tr><th>性别</th><td>"+data.xb+"</td><th>年级</th><td>"+data.nj+"</td></tr>";
					tr+="<tr><th>"+jQuery("#xbmc").val()+"</th><td>"+data.xymc+"</td><th>专业</th><td>"+data.zymc+"</td></tr>";
					tr+="<tr><th>班级</th><td>"+data.bjmc+"</td><th>联系电话</th><td>"+((data.lxdh==null)?"":data.lxdh)+"</td></tr>";
					jQuery('#tbody_ryxx').append(tr);
					if(data.zggwHtml==null||""==data.zggwHtml){
						jQuery('#thead_zggwxx').hide();
						jQuery('#tbody_zggwxx').append("<tr><td>该学生无在岗记录！</td></tr>");
						
					}else{
						jQuery('#thead_zggwxx').show();
						jQuery('#tbody_zggwxx').append(data.zggwHtml);
					}
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	// showTopWin(url,600,400);
	// tipsWindown("学生在岗信息","id:xszgxxDiv","540","300","true","","true","id");
	tipsWindownNew("学生在岗信息","id:xszgxxDiv",560,380);
}

function refreshParentTg(){
	var api = frameElement.api,W = api.opener;
	
	if(W == undefined){
		if(W && W.document.getElementById('search_go')){
			W.document.getElementById('search_go').click();
		}
	}else{
		if(	W && W.document.getElementById('search_go')){
			W.document.getElementById('search_go').click();		
		}
	}
	Close();
}
function queryParentDocumentJgcxTj(){
	if(frameElement.api){
		var api = frameElement.api,W = api.opener;
		return W.document;
	}
	return parent.window.document;
}
//新版查看弹出层
function zxsxxView(xh) {
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}


//用人单位改变关联岗位申请开放学院
function changeYrdw(obj) {
	if(jQuery("#xxdm").val() == '10351'){//温州大学个性化
		var xydm = jQuery(obj).val();
		var checkboxs = jQuery("input[name='sqxy']");
		var num = 0;
		jQuery.each(checkboxs,function(i,n){
			if(jQuery(n).val() == xydm){
				jQuery(n).attr("checked",true);
				num++;
			}else{
				jQuery(n).attr("checked",false);
			}
		});
		if(num == 0){
			jQuery.each(checkboxs,function(i,n){
				jQuery(n).attr("checked",true);
			})
		}
	}else{
		return;
	}
}

//西安科技大学个性化 岗位性质变动事件
function gwxzChange111(obj){
	var option = obj.options[obj.selectedIndex];  
	var gwxz = option.innerText;
	if(gwxz=="固定岗"){
		jQuery("#gdgcjbzTr").show();
		jQuery("#gwcjsxTr").hide();
	}else{
		jQuery("#gwcjsx").hide();
		jQuery("#gdgcjbzTr").hide();
		jQuery("#gwcjsxTr").show();
	}
}



//西安科技大学个性化 岗位性质变动事件
function gwxzChange(obj){
	var option = obj.options[obj.selectedIndex];  
	var gwxz = option.innerText;
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();//是否设定岗位酬金上限	
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();//*用人单位申请岗位时可否更改岗位酬金上限
	if(gwxz=="固定岗"){
		jQuery("#gdgcjbzTr").show();
		jQuery("#gwcjsxTr").hide();
	}else{
		if("no"==sfsdgwcjsx){
			jQuery("#gwcjsxTr").hide();
		}else{
			jQuery("#gwcjsxTr").show();
			if("no"==sfkgggwcjsx){
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}else{
				jQuery("#gwcjsx").show();
				jQuery("#gwcjsxh").hide();
				jQuery("#sxcolor").show();
			}
		}
		jQuery("#gdgcjbzTr").hide();
		
	}
}