//高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//全选
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

//经费增加页面
function add(){
	var url = "qgzx_jfgl.do?method=jfhbxxAdd";
	var title = "经费划拨增加";
	showDialog(title,800,500,url);
}

//经费管理增加
var count = 0;
//增加经费项
function addTr(){
	tr = jQuery('#hidden_jfxx').html();
	tr = tr.replace(/hbsj!!@@!!id/i,'hbsj'+(count));
	tr = tr.replace(/hbsj!!@@!!onclick/i,'hbsj'+(count++));
	jQuery('#tbody_jfxx').append(tr);
	var obj=jQuery('#tbody_jfxx').find("select[name=bm]").last();
	//changeTrBmdm(obj);
	return false;
}
//删除经费项
function delTr(){
	var checkbox = jQuery('#tbody_jfxx').find('input[type=checkbox]:checked[name!=th]');
	
	if (checkbox.length > 0){
		for (var i = 0 ; i < checkbox.length ; i++){
			jQuery(checkbox[i]).parents("tr").eq(0).remove();
		}
	} else {
		alertInfo('请选择您要删除的行!');
 		return false;
	}
}

//保存数据
function saveJfhb(){
	if($("nd") && $("nd").value==""){
 		alertInfo("年度不能为空!");
 		return false;
	}
	if(jQuery("#tbody_jfxx tr").length<1){
		alertInfo("经费项目不能为空,请至少增加一条!");
 		return false;
	}
	var bms = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(3)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(4)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(5)");
	var bm = "";
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < bms.length;i++){
		var bmdm=jQuery(bms[i]).find("select[name=bm]").val();
		if("-1"==bmdm||""==bmdm){
			alertInfo("用人单位不能为空!");
     		return false;
		}
		if(""==jQuery(hbsjs[i]).find("input[name=hbsj]").val()){
			alertInfo("划拨时间不能为空!");
     		return false;
		}
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("划拨金额不能为空!");
     		return false;
		}
		bm+=jQuery(bms[i]).find("select[name=bm]").val()+"!!@@!!";
		hbsj+=jQuery(hbsjs[i]).find("input[name=hbsj]").val()+"!!@@!!";
		hbje+=jQuery(hbjes[i]).find("input[name=hbje]").val()+"!!@@!!";
		bz+=jQuery(bzs[i]).find("input[name=bz]").val()+"!!@@!!";
	}
	jQuery("#bms").val(bm);
	jQuery("#hbsjs").val(hbsj);
	jQuery("#hbjes").val(hbje);
	jQuery("#bzs").val(bz+"end");
	
	var arr=[];
	jQuery("#tbody_jfxx").find("tr").each(function(){
		var bm0=jQuery(this).find("select[name=bm]").val();
		var hbsj0=jQuery(this).find("input[name=hbsj]").val();
		var pk=bm0+":"+hbsj0;
		arr.push(pk);
	});
	if(checkSame(arr)){
		alertInfo("同一用人单位不能存在两条相同划拨时间的经费项目,请确认！");
 		return false;
	}
	var message = checkBcInfo();
	if("true"!=message){
		alertInfo(message);
		return false;
	}
	confirmInfo("是否要保存新数据？",saveJfxxInfo);	
}

//判断数组arr是否存在重复值
function checkSame(arr){
	var json={};
	for (var i = 0; i < arr.length; i++) {
		if (!json[arr[i]]) {
			json[arr[i]] = 1;
		}else{
			return true;
		}
	}
	return false;
}

//验证历史记录中是否有重复数据
function checkBcInfo(){
	var data = "true";
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["nd"]=escape(jQuery("#nd").val());
    parameter["bm"]=escape(jQuery("#bms").val());
    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	var url = "qgzx_jfgl.do?method=checkBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data=result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

function saveJfxxInfo(tag){
	if(tag=="ok"){
		jQuery.ajaxSetup({async:false});	
		// 得到JSON对象
	    var parameter ={};	
	    parameter["nd"]=escape(jQuery("#nd").val());
	    parameter["bm"]=escape(jQuery("#bms").val());
	    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	    parameter["hbje"]=escape(jQuery("#hbjes").val());
	    parameter["bz"]=escape(jQuery("#bzs").val());
	    var url="";
	    if(jQuery("#savetype").val()=="init"){
			url = "qgzx_jfgl_ajax.do?method=init";
		}else{
			url = "qgzx_jfgl_ajax.do?method=save";
		}
	    $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				if("保存成功"==result){
					//alertInfo(result);
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
	}

//修改
function showModi(doType){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请勾选一条记录进行操作！");
	}else{
		var url='qgzx_jfgl.do?method=jfhbxxUpdate&pkValue='+ rows[0]["pkvalue"];
		url+="&doType="+doType;
		showDialog('经费划拨修改', 760, 525, url);
	}
}

//经费信息修改
//删除
function delXgTr(){
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length > 0){
		confirmInfo("确定要删除勾选的经费项目？",function(tag){
			if(tag=="ok"){	
				for (var i = 0 ; i < checkbox.length ; i++){
					jQuery(checkbox[i]).parents("tr").eq(0).remove();
				}
				return false;
			}
		});
		return false;
	} else {
		alertInfo('请选择您要删除的行!');
		return false;
	}
}

//修改方法保存
function saveXgJfhb(){
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(3)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(4)");
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < hbjes.length;i++){
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("划拨金额不能为空!");
     		return false;
		}
		hbsj+=jQuery(hbsjs[i]).find("input[name=hbsj]").val()+"!!@@!!";
		hbje+=jQuery(hbjes[i]).find("input[name=hbje]").val()+"!!@@!!";
		bz+=jQuery(bzs[i]).find("input[name=bz]").val()+"!!@@!!";
	}
	
	jQuery("#hbsjs").val(hbsj);
	jQuery("#hbjes").val(hbje);
	jQuery("#bzs").val(bz+"end");
	var message = checkXgBcInfo();
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	confirmInfo("是否要保存修改的数据？",saveXgJfxxInfo);	
}
//验证修改保存的信息
function checkXgBcInfo(){
	var data = "true";
	jQuery.ajaxSetup({async:false});	
	// 得到JSON对象
    var parameter ={};	
    parameter["nd"]=escape(jQuery("#nd").val());
    parameter["bm"]=escape(jQuery("#bm").val());
    parameter["hbje"]=escape(jQuery("#hbjes").val());
	var url = "qgzx_jfgl.do?method=checkXgBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}
//修改保存
function saveXgJfxxInfo(tag){
	if(tag=="ok"){
		jQuery.ajaxSetup({async:false});	
		// 得到JSON对象
	    var parameter ={};	
	    parameter["nd"]=escape(jQuery("#nd").val());
	    parameter["bm"]=escape(jQuery("#bm").val());
	    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	    parameter["hbje"]=escape(jQuery("#hbjes").val());
	    parameter["bz"]=escape(jQuery("#bzs").val());
		var url = "qgzx_jfgl.do?method=updateBc";
	    $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				if("保存成功"==result){
					//alertInfo(result);
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
	}

//查看
function view(doType){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请勾选一条记录进行操作！");
	}else{
		var url='qgzx_jfgl.do?method=jfhbxxView&pkValue='+ rows[0]["pkvalue"];
		showDialog('经费划拨查看', 760, 525, url);
	}
}

//导入
function importJfhb(){
	toImportDataNew("IMPORT_QGZX_JFHB_ZJDX");
	return false;
}

//dcclbh,导出功能编号
var DCCLBH = "qgzx_jfcjgl_jfhb_zjdx.do";
//自定义导出
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}
// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "qgzx_jfgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}