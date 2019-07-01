//加载已经分配信息
function loadYfpInfo(){
	
	// 编班类型
	var fplx=jQuery("#hid_fplx").val();
	
	// 辅导员编班
	if(fplx=="fdy"){
		
		loadYfpFdy();
	
	// 班主任编班
	}else if(fplx=="bzr"){
		
		loadYfpBzr();
		
	}
}

//已分配辅导员信息加载
function loadYfpFdy(){

	var ie = "ie";
	
	var bjdm=jQuery("#bj").val();
	
	var url="szdw_szbb.do?method=loadYfpFdy";
	
	//其他数据
 	var parameter = {
 		"ie":ie,
 		"stylePath":stylePath,
		"bjdm":bjdm
	};
	
	jQuery("#yfp_tbody").load(url,parameter,function(){});
}

//已分配班主任信息加载
function loadYfpBzr(){
	
	
	var ie = "ie";
	
	var bjdm=jQuery("#bj").val();
	
	var url="szdw_szbb.do?method=loadYfpBzr";
	
	//其他数据
 	var parameter = {
 		"ie":ie,
 		"stylePath":stylePath,
		"bjdm":bjdm
	};
	
	jQuery("#yfp_tbody").load(url,parameter,function(){});
}

// 默认加载
function onShow(){

	// 判断是否有选定班级
	if($("bj") && $("bj").length>0 && ($("bj").value==null || $("bj").value=="")){// 无
		// 去除第一行空白行数据
		$("bj").removeChild($("bj").options[0]);
		// 选中第一行
	}else if($("bj") && $("bj").length>0){// 有
		// 去除第一行空白行数据
		$("bj").removeChild($("bj").options[0]);
	} else {
        initBjListBySzbb();
	}

	loadYfpInfo();
	searchRs();
	titleNjxyzybj();
	
}

//查询结果
function searchRs(){

	jQuery.ajaxSetup({async:false});
	// 思政编班
	var url = "szdw_szbb.do?method=searchSetting";
	var ie = "ie";

	// 分班选中的班级代码
	var bjdm=jQuery("#bj").val();
	var parameter = {"ie":ie,"bjdm":bjdm};
	
	// 查询条件
	jQuery("select,input",jQuery("#tbody_search_query")).each(function(){
		parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
	})
	
	// 编班类型
	parameter["hid_fplx"]=jQuery("#hid_fplx").val();

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	// 查询操作
	searchGo(url,parameter);

	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// 思政队伍 查询出为带选定班级的
// 辅导员 班主任信息
function searchGo(url,parameter){
	
	// 分页
	var currentPage = "1";
	if($("currentPage")){
		currentPage = $("currentPage").value;
	}
	
	var editPageSize = "";
	if($("editPageSize")){
		editPageSize = $("editPageSize").value;	
	}
		
	var pagesize = "";
	if($("pagesize")){
		pagesize = $("pagesize").value;
	}


	parameter["currentPage"]=currentPage;
	parameter["editPageSize"]=editPageSize;
	parameter["pagesize"]=pagesize;

 	$("divWaiting").style.display="";
	$("divDisable").style.display="";

	jQuery("#div_rs").load(url,parameter,function(){
		
		jQuery("#table_rs td").each(function(){
			// 行高控制
			jQuery(this).attr("height","10");
			jQuery(this).css("text-align","center");
		});
		setTimeout("setPageInfo()",500);
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
	});
}

// 取消班主任编班
function cancelBzrbb(zgh,bjdm){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=cancelBzrbb";

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// 取消辅导员编班
function cancelFdybb(zgh,bjdm){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=cancelFdybb";

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// 辅导员编班
function setFdybb(zgh){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=setFdybb";

	var bjdm=jQuery("#bj").val();

	if(bjdm==null || bjdm==""){
		alertInfo("请选择需要分配的班级！",function(){});
		return false;
	}

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// 班主任编班
function setBzrbb(zgh){
	
	jQuery.ajaxSetup({async:false});
	
	var url = "szdw_szbb.do?method=setBzrbb";

	var bjdm=jQuery("#bj").val();
	
	if(bjdm==null || bjdm==""){
		alertInfo("请选择需要分配的班级！",function(){});
		return false;
	}

	var parameter = {"zgh":zgh,"bjdm":bjdm};

	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	jQuery.post(url,parameter,function(data){
		
		if(data=="true"){
			
			loadYfpInfo();
			searchRs();
		}
		
	})
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
	
	jQuery.ajaxSetup({async:true});
}

// 返回
function goHome(fplx){
    var url;
	if("fdy" == fplx){
        url = "szdw_general_szbb.do";
	} else {
		url = "szdw_general_szbzrbb.do";
	}

	refreshForm(url);
}

// 思政编班 班级列表加载
function initBjListBySzbb(){
    //dataLoad(true);
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var fbqk= "";
	var isFdy="false";
	var isBzr="false";
	var fplx="";
	var sydm="";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("fbqk")){fbqk=$("fbqk").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if(jQuery("#hid_fplx")){
        fplx = jQuery("#hid_fplx").val();
	}
	if($("sy")){sydm = $("sy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){
		isFdy = "true";
	}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}
	if(fbqk == null || fbqk == ""){
		fbqk = "%";
	} else{
		fbqk = "%" + fbqk + "%";
	}
    if(sydm == null || sydm == ""){
        sydm = "%";
    } else{
        sydm = "%" + sydm + "%";
    }
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;
    query += "!!-!!";
    query += sydm;
	query += "!!-!!";
	query += fbqk;
		GetListData.getBjListBySzbb(query,userName,isFdy,isBzr,fplx,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($("ysbjdm")){//原始班级
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//模块班级
						DWRUtil.removeAllOptions("mkbjdm");
						DWRUtil.addOptions("mkbjdm",data,"dm","mc");						
						$("mkbjdm").options[0].value = "";
					}
					
					if($(objId + "V")){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
					
					if($("bj").options.length>0 ){
						$("bj").options[0].selected=true;
					}
					loadYfpInfo();
					searchRs();
					titleNjxyzybj();
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function titleLoad(id){
	
	if(jQuery("#"+id)){
	
		jQuery("#"+id).children("option").each(function(){
			jQuery(this).attr("title",jQuery(this).text());
		});
	}
}

function titleNjxyzybj(){

	titleLoad('nj');
	setTimeout("titleLoad('xy')",500);
	setTimeout("titleLoad('zy')",500);
	setTimeout("titleLoad('bj')",500);
}