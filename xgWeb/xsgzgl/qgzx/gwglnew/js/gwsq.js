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
	demoHtml += "工作时间：星期一、三（上午8:00~12:00)";
	
	jQuery("#gwms").val(demoHtml);
	jQuery("#gwms").css("color", "#999");
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


function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//查询
function searchRs(){
	var url = "qgzx_gwglnew_ajax.do?method=gwsqCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//增加
function showAdd(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	//showTopWin('qgzx_gwglnew.do?method=gwsqZj',720,500);
	showDialog('岗位申请', 760, 520, 'qgzx_gwglnew.do?method=gwsqZj');
}


//修改update	查看	view
function showModi(type,title){
	var kycz = jQuery("#kycz").val();
	if("update"==type&&"true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
	    var shzt = rows[0]["shzt"];
		if("update"==type && ("3"!=shzt && "0"!=shzt)){
			showAlertDivLayer("已经审核过的数据不能修改！");
	 		return false;
		}
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
		url+="&doType="+type;
		//showTopWin(url,720,500);
		showDialog(title, 760, 520, url);
	}else{
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	}
}


//删除
function deleteGwsq(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length>=1){
		var rows = jQuery("#dataTable").getSeletRow();
		var str = "";
		for (var i=0;i<ids.length;i++) {
			var shzt = rows[i]["shzt"];
			if(rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
				showAlertDivLayer("已经审核过的数据不能删除！");
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
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				var parameter={}
				var url="qgzx_gwglnew_ajax.do?method=gwsqSc";	
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


//验证删除信息
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
//重庆工商个性化增加人员
/*function showModi(type){//var ids = jQuery("#dataTable").getSeletIds();
	//var rows = jQuery("#dataTable").getSeletRow();
	//var len=jQuery("[name=yxssz]:checked").length;
	//var ids = jQuery("#dataTable").getSeletIds();
	//var len = ids.length;
	var rows = jQuery("#dataTable").getSeletRow();
	var len = rows.length;
	if(len==1){	
		var pkValue=rows[0].val();
		alert(pkValue);
		var url="qgzx_gwgl.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog('', 760, 488, url);
	}else{
		alertInfo("请勾选一条需要操作的记录！");
		return false;
	}
}*/
//修改gwxxXg 查看 gwxxCk人员增加ryxxZj人员退岗ryxxTg
function showModiTg(type,title){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
		if (rows[0]["shzt"] == "1") {
			var sfyxgw=rows[0]["sfyxgw"];
			if((type=='ryxxZj' || type=='ryxxTg') && sfyxgw=='否'){
				showAlertDivLayer("请选择有效岗位！");
				return false;
			}
			var pkValue=rows[0]["gwdm"];
			var url="qgzx_gwglnew.do?method=gwxxCz&pkValue="+pkValue;
			url+="&doType="+type;
			showDialog(title, 760, 520, url);
		}else{
			showAlertDivLayer("请选择一条审核通过的记录！");
		}
	}else{
		showAlertDivLayer("请选择一条您要操作的记录！");
	}
}
//增加保存岗位信息	
function zjBcGwsq(type){
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
    if(jQuery("#stfzrxm") && jQuery("#stfzrxm").val()==""){
    	showAlert("负责老师不能为空！");
 		return false;
    }
    if(jQuery("#ssxq") && jQuery("#ssxq").val()==""){
    	showAlert("所属校区不能为空！");
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
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("岗位审核人不能为空！");
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
    //衢州学院个性化字段
    if(jQuery("#gwlx") && jQuery("#gwlx").val()==""){
    	showAlert("岗位类型不能为空！");
    	return false;
    } 
    
    if(jQuery("#zxfdy") && jQuery("#zxfdy").val()==""){
    	showAlert("助学管理辅导员不能为空！");
    	return false;
    }

    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("联系人不能为空！");
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
    
    if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("请选择岗位申请开放学院！");
    		return false;
    	}
    }
    
    
	if(!checkSxje())	{
		return false;
	}
	var message = checkBcInfo("add");
	if("true"!=message){
		showAlert(message);
		return false;
	}
	saveZjGwsqInfo(type);	
}


//验证保存信息
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
	parameter["gwmc"]=escape(jQuery("#gwmc").val().trim());
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		parameter["gwjssj"]="";
	}else{
		parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	}
	parameter["yxssz"]=yxssz;
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=checkZjGwsqInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


//增加保存岗位信息
function saveZjGwsqInfo(type){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};
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
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()));
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	parameter["type"]=encodeURI(encodeURI(type));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xn"]=jQuery("#xn").val();
	

	//温州大学个性化
	if(jQuery("#xxdm").val() == '10351'){
		var checkboxs = jQuery("input[name='sqxy']:checked");
		var sqxy = [];
		jQuery.each(checkboxs,function(i,n){
			sqxy.push(jQuery(n).val());
		});
		parameter["sqxy"] = sqxy;
		parameter["doType"] = "zj";
        parameter["lxr"]=encodeURI(encodeURI(jQuery("#lxr").val()));
	}
	var url = "qgzx_gwglnew_ajax.do?method=bcZjGwsq&type="+type;
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
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


//验证需求人数
function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}

//验证
function checkJe(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


//验证修改岗位信息
function bcXgGwsq(type){
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
	
	if($("gwxh") && $("gwxh").value==""){
		showAlert("岗位序号不能为空！");
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
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("岗位审核人不能为空！");
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
	if(!checkSxje())	{
		return false;
	}
	if(jQuery("#gwxh").val()!=null&&jQuery("#gwxh").val()!=""){
		if(!checkGwxh(jQuery("#gwxh").val())){
			 return false;
		}
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
	saveXgGwxxInfo(type);	
}


//保存修改岗位信息
function saveXgGwxxInfo(type){
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
	parameter["gwxh"]=escape(jQuery("#gwxh").val()); 
	parameter["oldGwxh"]=escape(jQuery("#oldGwxh").val());
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
	parameter["type"]=encodeURI(encodeURI(type));
	var url = "qgzx_gwglnew_ajax.do?method=bcXgGwsqInfo&type="+type;
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
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
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

//岗位申请表导出
function printgwsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "qgzx_gwglnew.do?method=getGwsqb";
		url += "&pkValue=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "qgzx_gwglnew.do?method=getGwsqbTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
		return false;
	}
	showConfirmDivLayer("您确定要提交选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("qgzx_gwglnew.do?method=submit", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
	
}

//撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qgzx_gwglnew.do?method=cancel", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
//流程跟踪
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['gwdm'] + "&splc=" + rows[0]['splcid']);
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