var demoHtml = "";

function initData(){//岗位描述示例
	
	demoHtml = "请按如下格式输入内容\n\n";
	demoHtml += "例如：\n";
	demoHtml += "工作地点：A楼101室\n";
	demoHtml += "工作内容：日常文档处理\n";
	demoHtml += "工作时间：星期一、三（上午8:00~12:00)";
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
	
	var doType=jQuery("#doType").val();
	//人员增减 界面只能查看
	if(doType=="ryxxZj"||doType=="ryxxTg"){
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
	 		alertInfo("上限金额不能为空!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		if(parseInt(gwcjsx)>parseInt(gwzgcjsx)){
	 		alertInfo("上限金额不得超过系统设置的岗位最高酬金上限("+gwzgcjsx+")!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
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


// 查询
function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=gwxxCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


// 修改gwxxXg 查看 gwxxCk人员增加ryxxZj人员退岗ryxxTg
function showModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var url="qgzx_gwgl.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog('', 760, 488, url);
	}else{
		alertInfo("请勾选一条需要操作的记录！");
		return false;
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

//岗位人员信息导入
function gwryDr(){
	toImportData("IMPORT_QGZX_GWRY");
	return false;
}

// 复制
function copyGwxx(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	var blog=true;
	if(len>=1){	
		var array = jQuery("[name=div_pkValue]:checked");
		var num = 0;
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(2).text();
			if(str.indexOf(pkValue)==-1){
				str += pkValue+"!!@@!!";
				num++;
			}
		}
		var parameter={};
		url = "qgzx_gwgl_ajax.do?method=getXn";
		jQuery.getJSON(url,parameter,function(data){
			jQuery('#xn').empty();
			jQuery('#xn').append("<option value=''>--请选择--</option>");
			if(data != null && data.length != 0){
				for(var i=0; i<data.length; i++){
					if(str.indexOf(data[i].xn)==-1){
						var option = "<option value=\"" + data[i].xn + "\">" + data[i].xn + "</option>";
						jQuery('#xn').append(option);
					}
				}
			}
		});
		jQuery('#yxgw').html("当前共选中<font class='red'>"+num+"</font>个学年中的<font class='red'>"+len+"</font>个岗位");
		tipsWindown("岗位复制","id:tempDiv","380","180","true","","true","id");
	}else{
		alertInfo("请勾选需要复制的记录！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


// 复制保存
function gwxxDivSave(){
	if($("xn").value.trim()==""){
		alertInfo("请选择要复制的学年！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	document.getElementById('tempDiv').style.display='none';
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var message = checkFzInfo(str);
	if(message!="true"){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=gwxxFz&xn=";
	url+=$("xn").value.trim()
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			alertInfo(result,function(tag){
				if(tag=="ok"){
					closeWindown();
					searchRs();
				}
			});
		}
	);
	jQuery.ajaxSetup({async:true});
	
}


// 删除
function deleteGwxx(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="qgzx_gwgl_ajax.do?method=gwxxSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						if("删除成功"==result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
								}
							});
						}else{
							alertInfo(result);
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		alertInfo("请勾选需要删除的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


// 验证复制信息
function checkFzInfo(str){
	var date = "true";
	var parameter={};
	var url="qgzx_gwgl_ajax.do?method=checkFzInfo&xn=";
	url+=$("xn").value.trim()
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


// 验证删除信息
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=checkScInfo";	
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
 		alertInfo("学年不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("yrbm") && $("yrbm").value==""){
 		alertInfo("用人部门不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("岗位名称不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("add");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("岗位性质不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("需求人数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("困难生数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("困难生数不能大于需求人数!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
 		alertInfo("岗位描述不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("岗位人员要求不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(!checkSxje()){
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
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val());
	var url = "qgzx_gwgl_ajax.do?method=checkBcInfo";
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
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val();
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	var url = "qgzx_gwgl_ajax.do?method=save";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if("保存成功"==result){
				// alertInfo(result);
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}else{
				alertInfo(result,function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
			}
		}
	);
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


// 验证修改岗位信息
function bcXgGwxx(){
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("岗位名称不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("update");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("岗位性质不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("需求人数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
		if(parseFloat($("xqrs").value)<parseFloat($("zgrs").value)){
			alertInfo("需求人数不能小于在岗人数!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("困难生数不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("困难生数不能大于需求人数!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
 		alertInfo("岗位描述不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("岗位人员要求不能为空!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	
	if(!checkSxje()){
		return false;
	}
	saveXgGwxxInfo();	
}


// 保存修改岗位信息
function saveXgGwxxInfo(){
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val();
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	var url = "qgzx_gwgl_ajax.do?method=update";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			if("保存成功"==result){
				// alertInfo(result);
				showAlert(result,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			}});
			}else{
				alertInfo(result,function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}


// 全选
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}


// 增加行
function addTr(){
	// showTopWin('qgzx_gwgl.do?method=getStu&pkValue='+jQuery("#pkValue").val(),800,700);
	var xn=jQuery("#xn").val();
	var url='qgzx_gwgl.do?method=getStu&pkValue='+jQuery("#pkValue").val()+'&xn='+xn;
	showDialog('', 760, 520, url);
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
			alertInfo(show);
			return false;
		}
		var message = checkScRyxx(xhs);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("是否确定删除勾选的记录？",function(tag){
			if(tag=="ok"){
				jQuery.ajaxSetup({async:false});
				// 得到JSON对象
			    var parameter ={};	
			    parameter["gwdm"]=escape(jQuery("#pkValue").val());
			    parameter["xh"]=xhs;
				var url = "qgzx_gwgl_ajax.do?method=bcScryxx";
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
		alertInfo('请选择您要删除的行!',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
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
	var url = "qgzx_gwgl_ajax.do?method=checkScRyxx";
	jQuery.post(url,parameter,
		function(result){
			message = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return message;
}

// 获得学生信息
function getXsxx(){
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["pkValue"]=escape(jQuery("#xhs").val());
	var url = "qgzx_gwgl_ajax.do?method=getXsxx";
	jQuery.getJSON(url,parameter,
		function(data){
			if(data!=null && data.length !=0){
				var tr="";
				for(var i = 0; i < data.length; i++){
					tr+="<tr><td><input id='xh' name='xh' type='checkbox' value='"+data[i].xh+"'/></td><td>"+data[i].xh+"</td>";
					tr+="<td>"+data[i].xm+"</td><td>"+data[i].bjmc+"</td><td>"+data[i].sfkns+"</td>";
					tr+="<td><a href='#' onclick='showXsxx(this);return false;' value='"+data[i].xh+"'>"+data[i].qggws+"</a></td>";
					tr+="</tr>";					
				}
				jQuery('#tbody_zgryxx').prepend(tr);
			}
		}
	);
	jQuery.ajaxSetup({async:true});
	jQuery("#xhTal").val(jQuery("#xhTal").val()+jQuery("#xhs").val());
}


// 验证保存人员信息
function bcZjRyxx(){
	var len = jQuery("#xhs").val().split("!!@@!!").length-1;
	if(len>parseInt(jQuery("#xqrs").val())){
		alertInfo('增加的人员已大于岗位的需求人数！',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	saveRyxxInfo();
	
}


// 保存人员信息
function saveRyxxInfo(){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["fknsrs"]=escape(jQuery("#fknsrs").val());
    parameter["xn"]=escape(jQuery("#xn").val());
	var url = "qgzx_gwgl_ajax.do?method=bcZjryxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if(result!="保存成功"){
				alertInfo(result,function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}else{
				document.forms[0].action = window.location.href;
				document.forms[0].submit();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
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
		tipsWindown("人员退岗","id:tempDiv","380","250","true","","true","id");
		jQuery('#xhs').val(xhs);
		jQuery('#sgsjs').val(sgsjs);
		jQuery('#sqbhs').val(sqbhs);
		jQuery('#tgry').html("当前共选中<font class='red'>"+len+"</font>个在岗学生进行退岗");
	}else{
		alertInfo("请勾选需要退岗的人员！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}

// 保存退岗信息验证
function bcTgryxx(){
	if($("tgyy") && $("tgyy").value==""){
		alertInfo("退岗原因不能为空!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
 		return false;
	}
	saveTgryxx();
}


function saveTgryxx(){
	jQuery.ajaxSetup({async:false});
	// 得到JSON对象
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery('#xhs').val());
    parameter["sgsj"]=escape(jQuery('#sgsjs').val());
    parameter["tgyy"]=escape(jQuery("#tgyy").val());
    parameter["sqbhs"]=escape(jQuery("#sqbhs").val());
	var url = "qgzx_gwgl_ajax.do?method=bcTgryxx";
	jQuery.post(url,parameter,
		function(result){
			alertInfo(result,function(tag){
				if(tag="ok"){
					closeWindown();
					document.forms[0].action = window.location.href;
					document.forms[0].submit();
					return false;
				}
			});
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
	var url="qgzx_gwgl_ajax.do?method=ryxxCk";
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
						jQuery('#tbody_zggwxx').append("<tr><td>该学生无上岗记录！</td></tr>");
						
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
	tipsWindownNew("学生在岗信息","id:xszgxxDiv",540,320);
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

//新版查看弹出层
function zxsxxView(xh) {
	showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
			+ "&xs");
}