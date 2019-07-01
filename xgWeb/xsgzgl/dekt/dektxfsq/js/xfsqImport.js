/**
 * 下载导入模版
 * @params drmkdm 导入模块代码
 */
function downloadTemplate(drmkdm){
	var jqFrom=jQuery("#importForm");
	var url = "dekt_xfsq.do?method=downloadTemplate";
	jqFrom.attr("action",url);
	jqFrom.submit();
}

/**
 * 验证是否选择导入文件及文件格式
 */
function notNullImportFile(){
	var importFile=jQuery("#importFile").val();
	if(importFile == null || importFile == ""){
		showAlert("请选择导入文件!");
		return false;
	}
	var fileSuffix=importFile.substring(importFile.lastIndexOf(".")+1,importFile.length);
	fileSuffix = fileSuffix.toLowerCase();
	if(fileSuffix != "xls"){
		showAlert("导入文件格式不合法，请确认!")
		return false;
	}
	return true;
}

/**
 * 保存导入（这里省略了下一步的导入配置）
 */
function selectImport(){
	if(!notNullImportFile()){
		return false;
	}
	var url = "dekt_xfsq.do?method=saveImport";
	ajaxSubFormWithFun("importForm",url,function(data){
		var file = jQuery("#importFile") 
		file.after(file.clone().val("")); 
		file.remove(); 
		
		if(data["result"]){
            jQuery("#cwsj").html("<font>无错误数据</font> ");
			showImportResult(data["totalCount"],data["totalCount"],0,"成功导入");
		}else{
			if(data["error"]=="01"){
				showAlert(data["message"]);
			}else{
				var filename = data["errorTipsExcelName"];
				jQuery("#cwsj").html("<a href='javascript:void(0);' class='name' onclick=\"downloadError('"+filename+"')\">点击下载异常数据</a> ");
				showImportResult(data["totalCount"],data["totalCount"]-data["errorCount"],data["errorCount"],"可导入");
			}
		}
	});
}

/**
 * 显示导入结果提示信息
 */
function showImportResult(drzs,cgs,cws,tf){
	var reslutMsg="<font color='blue'>总计["+drzs+"]条</font>，<font color='green'>"+tf+"["
				   +cgs+"]条</font>，<font color='red'>错误["+cws+"]条</font>。";
	jQuery("#importResult").html(reslutMsg);
	jQuery("#dr_result").show();
}

/**
 * 下载错误数据
 * @params drmkdm 导入模块代码
 */
function downloadError(filename){
	var jqFrom=jQuery("#importForm");
	var url = "dekt_xfsq.do?method=downloadImportError&filename="+filename;
	jqFrom.attr("action",url);
	jqFrom.submit();
}







