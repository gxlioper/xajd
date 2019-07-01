//第一次加载
var isFirst=true;
//结果查询页面

//学号链接
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneFyffjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneFyffjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
//费用发放结果查看
function oneFyffjgView(guid,xh){
	showDialog("费用发放结果查询",800,380,"rcsw_fyff_ffjg.do?method=oneFyffjgView&guid="+guid+"&xh="+xh);
}

//根据发放项目改变金额
function loadFfje(obj){
	var option=jQuery(obj).find("option:selected");
	var mrffje=jQuery(option).attr("mrffje");
	var fffs=jQuery(option).attr("fffs");
	
	jQuery("#sfje").val(mrffje);
	
	if("0"==fffs){
		jQuery("input[name=fffs][value=0]").attr("checked",'checked');
		jQuery("input[name=fffs][value=0]").click();
		jQuery("#bfyf").show();
		
	}else{
		jQuery("input[name=fffs][value=1]").attr("checked",'checked');
		jQuery("input[name=fffs][value=1]").click();
		jQuery("#bfyf").hide();
	}
	changeDate();
}


//更新日期显示方式
function changeDate(obj){
	var selectV=jQuery(obj).val();
	if(selectV=="0"){//mm
		jQuery("#mm").show();
		if(isFirst){
			jQuery("#mm").val(jQuery("#ffsj").val());
			isFirst=false;
		}
		jQuery("#dd").hide();
	}else if(selectV=="1"){
		jQuery("#mm").hide();
		jQuery("#dd").show();
		if(isFirst){
			jQuery("#dd").val(jQuery("#ffsj").val());
			isFirst=false;
		}
	}
}
//判断是否需要填写发放账号
function changeFfzh(obj){
	var option=jQuery(obj).find("option:selected");
	var ffzh=jQuery(option).attr("ffzh");
	if("0" == ffzh){
		jQuery("#ffzh").show();
	}else {
		jQuery("#ffzh").hide();
	}
}


//保存
function saveForm(method,type){
	
	var xh = jQuery("#xh").val();			//学号
	var ffxmdm = jQuery("#ffxmdm").val();   //发放项目代码
	var sfje = jQuery("#sfje").val();		//实发金额
	var fftjdm = jQuery("#fftjdm").val();	//发放途径代码
	var ffzh = jQuery("#ffzh").val();		//发放账号
	var mm = jQuery("#mm").val();			//月份时间
	var dd = jQuery("#dd").val();			//日期时间
	var fffs = jQuery('input[name="fffs"]:checked').val() // 发放方式
	var selectFfzh=jQuery("#fftjdm").find("option:selected").attr("ffzh");  //发放账号是否必填
	
	if("" == xh){
//		showAlert("学号不能为空");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if("" == ffxmdm){
//		showAlert("发放项目不能为空");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}if("" == sfje){
//		showAlert("发放金额不能为空");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}if(("0"==fffs&&""==mm)||("1"==fffs&&""==dd)){
//		showAlert("发放时间必须填写");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}if("" == fftjdm){
//		showAlert("发放途径必须填写");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}if("0" == selectFfzh && "" == ffzh){
//		showAlert("发放途径账号必须填写");
//		return false;
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		
	}
	
	//账号如果非必填，清空发放账号
	if("0" != selectFfzh){
		jQuery('#ffzh').val("");
	}
	//发放时间赋值
	if("0"==fffs){
		 jQuery('#ffsj').val(mm);
	}else if("1"==fffs){
		 jQuery('#ffsj').val(dd);
	}
	 var url = "rcsw_fyff_ffjg.do?method="+method+"&type="+type;
      ajaxSubFormWithFun("FyffjgForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("该学生的"+jQuery("#ffxmdm>option:selected").text()+"在"+jQuery('#ffsj').val()+"已发放" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
}


//初始化发放项目
function selectFfxm(){
	var selectV=jQuery("#ffxmdmId").val();
	jQuery("#ffxmdm>option").each(function(){
		if(selectV==jQuery(this).val()){
			jQuery(this).attr("selected",true);
			jQuery("[name=fffs]:checked").click();
		}
	});
}

//初始化发放途径
function selectFftj(){
	var selectV=jQuery("#fftjdmId").val();
	jQuery("#fftjdm>option").each(function(){
		if(selectV==jQuery(this).val()){
			jQuery(this).attr("selected",true);
			jQuery(this).change();
		}
	});
}


//增加
function add(){
	var url = "rcsw_fyff_ffjg.do?method=addFyffjg";
	var title = "增加费用发放结果信息";
	showDialog(title,800,430,url);
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_fyff_ffjg.do?method=updateFyffjg&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
		var title = "修改费用发放结果";
		showDialog(title,800,430,url);
	}
}


//删除
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("rcsw_fyff_ffjg.do?method=deleteFyffjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//导出
function exportConfig(){
	var DCCLBH='rcsw_fyff_fyffjg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_fyff_fyffjg.do';
	setSearchTj();//设置高级查询条件
	var url = "rcsw_fyff_ffjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportData("IMPORT_RCSW_FYFF");
	return false;
}

function changeMonthorDay(obj){
	if(obj.value == '0'){
		jQuery("#bfyf").show();
	}else if(obj.value == '1'){
		jQuery("#bfyf").hide();
	}
}


//新版导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_RCSW_FYFF");
	return false;

}



