function showTopWin(url, w, h,scrollbar) {
	var info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h + "px;help:no;";
	if (scrollbar != null){
		showModalDialog(url, window, info,scrollbar);
		return false;
	}
	showModalDialog(url, window, info);
}

//检测思政队伍维护修改
function checkDwwhUpdate(){
	
	var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
	var num = check.length;
	if(num == 1){
		var zgh = jQuery(check[0]).val();
		//showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
		showDialog('', 830, 500, "/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh);
		//window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
	}else{
		alertError("请勾选<font color='blue'>一条记录</font>进行修改");
		return false;
	}
}
//保存队伍维护
function saveDwwh(lx){

	var zgh = jQuery("#text_zgh").val();
	var xm = jQuery("#text_xm").val();
	
	if(zgh == ""){
		showAlert("职工号不能为空，请确认");
		return false;
	}
	var isExisting = checkInputIsExisting("fdyxxb","zgh",zgh,"");

	if(lx == "insert" && isExisting == "true"){
		alertError("该职工号已存在，请您确认");
		return false;
	}
	
	if(xm == ""){
		alertError("姓名不能为空，请确认");
		return false;
	}
	
	//路徑
	var url = "szdw_dwwh.do?method=saveDwwh";
	//參數
 	var parameter = {
 		"str_zgh":escape(jQuery("#text_zgh").val()),
		"str_xm":escape(jQuery("#text_xm").val()),
		"str_xb":escape(jQuery("#select_xb").val()),
		"str_bmdm":escape(jQuery("#select_bmdm").val()),
		"str_lxdh":escape(jQuery("#text_lxdh").val())
	};
	
 	//jQuery.ajaxSetup({async:false});
 	
 	jQuery.post(url,
			parameter,
			function(result){
	 		 showAlert(result,{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}else{
						iFClose();
					}
				}});
			}
		);
		
	jQuery.ajaxSetup({async:true});
}
function updateDwwh(lx){
	clearData('kzzd10', kzzd10Msg);
	clearData('grhjqk', grhjqkMsg);
	clearData('kzzd14', kzzd14Msg);
	clearData('pxqk', pxqkMsg);
	clearData('kzzd18', kzzd18Msg);
	var kzzd10 = jQuery("#kzzd10").val();
	var kzzd14 = jQuery("#kzzd14").val();
	var kzzd15 = jQuery("#kzzd15").val();
	var kzzd18 = jQuery("#kzzd18").val();
	
	var fields = jQuery("#dwwh_form").serializeArray();
	var zgh = jQuery("#zgh").val();
	var xm = jQuery("#xm").val();
	//jQuery.each(fields, function(i, field){
	//	alert(jQuery(field).attr("name")+"=="+jQuery(field).val());
    //});
	var jtzz = jQuery("#jtzz").val();
	
	var zyzz = jQuery("#zyzz").val();
	
	var grhjqk = jQuery("#grhjqk").val();
	
	var txdz = jQuery("#txdz").val();
	
	var gzjl = jQuery("#gzjl").val();
	
	var bz = jQuery("#bz").val();
	
	var pjpy = jQuery("#kzzd4").val();
	var fblw = jQuery("#fblw").val();
	var pxqk = jQuery("#pxqk").val();
	
	var sfzh = jQuery("#sfzh").val();

	var reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	
	if (sfzh!="" && reg != "" && !reg.exec(sfzh)) {
		alertError("身份证号输入不正确");
		return false;
	}
	if(zgh == ""){
		alertError("职工号不能为空，请确认");
		return false;
	}
	if(xm == ""){
		alertError("姓名不能为空，请确认");
		return false;
	}
	var isExisting = checkInputIsExisting("fdyxxb","zgh",zgh,"");
	
	if(lx == "insert" && isExisting == "true"){
		alertError("该职工号已存在，请您确认");
		return false;
	}
	if(jtzz.length>150){
		alertError("家庭住址只能输入150个字");
		return false;
	}else if(zyzz.length>200){
		alertError("主要职责只能输入200个字");
		return false;
	}else if(kzzd10.length>200){
		alertError("科研情况只能输入200个字");
		return false;
	}else if(grhjqk.length>200){
		alertError("获奖情况只能输入200个字");
		return false;
	}else if(kzzd14.length>200){
		alertError("考证情况只能输入200个字");
		return false;
	}else if(pxqk.length>200){
		alertError("培训只能输入200个字");
		return false;
	}else if(kzzd15.length>200){
		alertError("分管工作只能输入200个字");
		return false;
	}else if(kzzd18.length>200){
		alertError("历年考核结果只能输入200个字");
		return false;
	}else if(txdz.length>50){
		alertError("通讯地址只能输入50个字");
		return false;
	}else if(gzjl.length>2000){
		alertError("工作经历只能输入2000个字");
		return false;
	}else if(pjpy.length>2000){
		alertError("评奖评优只能输入2000个字");
		return false;
	}else if(fblw.length>2000){
		alertError("发表论文只能输入2000个字");
		return false;
	}else if(bz.length>2000){
		alertError("备注只能输入2000个字");
		return false;
	}
	
	//路徑
	var url = "szdw_dwwh.do?method=updateDwwh";
	
	jQuery.ajax({
		  type: 'POST',
		  url: url,
		  dataType: "text",
		  contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		  data: fields,
		  success: function(datas){
		 showAlert(datas,{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		  }
	});

}

//创建队伍维护DIV
function createDwwhDiv(lx){

	var zgh = "";
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(lx == "update"){
		zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
	}
	
	//路徑
	var url = "szdw_dwwh.do?method=createDwwhDiv";
	//參數
 	var parameter = {
		"str_lx":lx,
		"str_zgh":zgh
	};
 	url+="&str_lx="+lx+"&str_zgh="+zgh;
 	showDialog('', 400, 270, url);
 /*	jQuery("#div_dwwh").load(
		url,
		parameter,
		function(){
			//tipsWindown(" ","id:div_dwwh","400","300","true","","true","id");
			showDialogDiv("","400","300","div_dwwh");
		}
	);*/
}


//删除队伍维护
function deleteDwwh(){

	var len=jQuery("[name=primarykey_checkVal]:checked").length;

	if(len!=0){	
		var flag = true;	
		//jQuery("[name=primarykey_checkVal]:checked").each(function(){
		//	var shzt = jQuery(this).parents().children("td").eq(8).html();
		//	if(shzt != "未审核"){
		//		flag = false;
		//	}
		//});
		
		if(!flag){
			//alertError("只能删除<font color='blue'>未审核</font>的记录，请您确认");
		}
		
		if(flag){
		
			confirmInfo("请您确认<font color='blue'>是否删除</font>所勾选的教师记录？注：刪除的教师账户无法再登录系统",function(tag){
				if(tag=="ok"){
					var url = "szdw_dwwh.do?method=deleteDwwh";
					var pkValue=new Array();
					var i=0;
					
					jQuery("input[name=primarykey_checkVal]:checked").each(function(){
						pkValue[i]=jQuery(this).val();
						i++;
					});
					
					var parameter={};
					parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
			
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							searchRs();
							alertInfo(result);
							closeWindown();		
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{	
		alertError("请<font color='blue'>勾选</font>您希望删除的记录！");	
		return false;
	}
}

//创建用户库DIV
function createYhkDiv(){
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if(num == 0){
		alertInfo("请您<font color='blue'>勾选</font>希望添加到用户库的教师记录");
		return false;
	}
	
	//路徑
	var url = "szdw_dwwh.do?method=createYhkDiv";
	//參數
 	var parameter = {
		
	};
	
 	jQuery("#div_yhk").load(
		url,
		parameter,
		function(){
			tipsWindown(" ","id:div_yhk","400","150","true","","true","id");
		}
	);
}

//保存到用户库
function saveYhk(){
	
	var len=jQuery("[name=primarykey_checkVal]:checked").length;

	if(len!=0){	
			
		var url = "szdw_dwwh.do?method=saveYhk";
		var pkValue=new Array();
		var i=0;
		
		jQuery("input[name=primarykey_checkVal]:checked").each(function(){
			pkValue[i]=jQuery(this).val();
			i++;
		});
		
		var parameter={};
		parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
		parameter["str_zdm"]=escape(jQuery("#select_zdm").val());
		
	 	$("divWaiting").style.display="";
		$("divDisable").style.display="";
		
		jQuery.ajaxSetup({async:false});
		
		jQuery.post(url,
			parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				searchRs();
				alertInfo(result);
				closeWindown();		
			}
		);

		jQuery.ajaxSetup({async:true});
		
	}else{	
		alertError("请<font color='blue'>勾选</font>您希望添加到用户库的记录！");	
		return false;
	}
}

//创建院系兼任DIV
function createYxjrDiv(){
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 0){
		alertInfo("请您<font color='blue'>勾选</font>希望设置院系兼任情况的教师记录");
		return false;
	}
	
	var tr = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").parents("tr");
	var fdyDbs = jQuery("input[name=fdyDbs]",tr).val();
	var bzrDbs = jQuery("input[name=bzrDbs]",tr).val();
	if (Number(fdyDbs)== 0 && Number(bzrDbs) == 0){
		alertInfo("您无需进行该操作");
		return false;
	}
	
	//路徑
	var url = "szdw_dwwh.do?method=createYxjrDiv";
	
	var pkValue=new Array();
	var i=0;
	jQuery("input[name=primarykey_checkVal]:checked").each(function(){
		pkValue[i]=jQuery(this).val();
		i++;
	});
	url+="&array_pkValue="+escape(pkValue.join("!!array!!"));
	//var parameter={};
	//parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
	//parameter["str_sfjryx"]=jQuery("input[name=radio_yxjr]:checked").eq(0).val();
	
 	showDialog('院校兼任情况设置', 450,180, url);
 	/*jQuery("#div_yxjr").load(
		url,
		parameter,
		function(){
			tipsWindown(" ","id:div_yxjr","450","200","true","","true","id");
		}
	);*/
}

//保存院校兼任
function saveYxjr(){
			
	var url = "szdw_dwwh.do?method=saveYxjr";
	//var pkValue=new Array();
	//var i=0;
//	jQuery("input[name=primarykey_checkVal]:checked").each(function(){alert(111);
	//	pkValue[i]=jQuery(this).val();
//		i++;
	//});
	var parameter={};
	parameter["array_pkValue"]=jQuery("#selectId").val();
	parameter["str_sfjryx"]=jQuery("input[name=radio_yxjr]:checked").eq(0).val();
	jQuery.post(url,
		parameter,
		function(result){
		 showAlert(result,{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
				iFClose();
			}});
		}
	);

	jQuery.ajaxSetup({async:true});
}

//前往思政队伍维护
function goDwwh(){
	var url = "szdw_general_dwwh.do?ty=2";
	refreshForm(url);
}

//创建年级Div
function createNjLvDiv(){
	
	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();
	
	//路徑
	var url = "szdw_dwwh.do?method=createNjLvDiv";
	//參數
 	var parameter = {
 		"str_lx":lx,
 		"str_zgh":zgh
 	};

	jQuery.ajaxSetup({async: false});
	
 	jQuery("#div_nj").load(url,parameter,function(){});
 	
 	jQuery.ajaxSetup({async: true});
}

//创建其他级别Div
function createOtherLvDiv(type){
	
	var url = "szdw_dwwh.do?method=createOtherLvDiv";

	var njV = jQuery("#njV").val();
	var checked_nj = $("checkbox_nj_"+njV).checked;
	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();
	
	//参数
 	var parameter ={
 		"str_njV":njV,
 		"str_checked_nj":checked_nj,
 		"str_lx":lx,
 		"str_zgh":zgh,
 		"str_type":type
 	};
	
	jQuery.ajaxSetup({async: false});
	
	jQuery("#div_other").load(url,parameter,function(){
		//带班数
		var dbs = jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).length;
		
		jQuery("#span_dbs").html(dbs);	
		
		var node = $("div_fpbj_new");
		
		jQuery("input[type=checkbox]:checked").each(function(){
			
			var bjid = jQuery(this).attr("id");
			
			if(bjid.split("_")[1] == "bj"){
			
				if(!$("hidden_bjdm_"+jQuery(this).val())){
					var tmp = document.createElement("input");
						tmp.type = "text";
						tmp.name = "hidden_bjdm";
						tmp.id = "hidden_bjdm_"+jQuery(this).val();
						tmp.value = jQuery(this).val();
						node.appendChild(tmp);	
				}	
			}
		});
		
		createBjClick();
	});
	
	jQuery.ajaxSetup({async: true});
	
	$("divWaiting").style.display="none";
	$("divDisable").style.display="none";
}

//创建班级点击
function createBjClick(){
	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
		var bjdm = jQuery(this).val();
		if($("checkbox_bj_"+bjdm)){
			jQuery("#checkbox_bj_"+bjdm).attr("checked",true);
		}
	});
}

//点击年级
function clickNj(obj,nj,count,lx){
	
	$("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	var id=obj.id;
	
	var as = getElementsByClass('current',document,'li');
	for(var i=0;i<as.length;i++){
		as[i].className = "";
	}
	
	obj.parentNode.className = "current";
	
	jQuery("#njV").val(nj);
	jQuery("#hidden_nj").val(count);
	
	//次级别编制
	setTimeout("createOtherLvDiv('"+lx+"')",100);
}

//点击学院
function clickXyCheckBox(bzdm){
	
	var checked = $("checkbox_xy_"+bzdm).checked;		
	
	jQuery("input[name=checkbox_zy_"+bzdm+"]").each(
		function(){		
			var id = jQuery(this).attr("id");				
			var checked_zy = $(id).checked;	
			
			var zydm = id.replace("checkbox_zy_","");
			
			if(checked){				
				if(checked_zy){
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",true);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});
				}else{
					jQuery(this).attr("checked",true);
					
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",true);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});
				}								
			}else{	
				if(checked_zy){
					jQuery(this).attr("checked",false);	
					
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",false);
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);
					});	
				}else{
					jQuery("input[name=checkbox_bj_"+zydm+"]").each(
						function(){
							jQuery(this).attr("checked",false);	
							var bjdm = jQuery(this).attr("id").split("_")[2];
							clickBjCheckBox(bjdm);								
					});	
				}		
							
			}
	});
}

//点击专业
function clickZyCheckBox(bzdm,sjdm){
	
	var checked = $("checkbox_zy_"+bzdm).checked;
	var checked_xy = $("checkbox_xy_"+sjdm).checked;	
	
	if(checked){
		jQuery("input[name=checkbox_bj_"+bzdm+"]").each(
			function(){
				jQuery(this).attr("checked",true);
				var id = jQuery(this).attr("id");
				var bjdm = id.split("_")[2];
				clickBjCheckBox(bjdm);					
		});
	}else{
		jQuery("input[name=checkbox_bj_"+bzdm+"]").each(
			function(){
				jQuery(this).attr("checked",false);
				var id = jQuery(this).attr("id");
				var bjdm = id.split("_")[2];
				clickBjCheckBox(bjdm);		
		});
	}
}

//点击班级
function clickBjCheckBox(bzdm){

	var checked_bj = $("checkbox_bj_"+bzdm).checked;
	
	if(checked_bj){
		
		if(!$("hidden_bjdm_"+bzdm)){
			var node = $("div_fpbj_new");
		
			var tmp = document.createElement("input");
				tmp.type = "text";
				tmp.name = "hidden_bjdm";
				tmp.id = "hidden_bjdm_"+bzdm;
				tmp.value = bzdm;
				node.appendChild(tmp);
		}
	}else{
		if($("hidden_bjdm_"+bzdm)){
			jQuery("#hidden_bjdm_"+bzdm).remove();
		}
	}
}

//保存分配班级
function disfrockFpbj(){
	
//	var bjdm = new Array();//班级代码
//	var i=0;
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	var url = "szdw_dwwh.do?method=saveFpbj";
//
//	//参数
// 	var parameter = {
// 		"array_bjdm":bjdm.join("!!array!!"),
// 		"str_zgh":jQuery("#hidden_zgh").val(),
// 		"str_lx":jQuery("#hidden_lx").val()
//	};
//
//	jQuery.ajaxSetup({async:true});
//	
//	jQuery.post(url,
//		parameter,
//		function(result){
//		
//			alertInfo(result);
//			
//			//创建年级Div
//			createNjLvDiv();
//			
//			jQuery("#div_fpbj_new").html("");					
//			
//			var index = jQuery("#hidden_nj").val();
//		
//			var id = "";
//			
//			if(index == ""){
//				id = "a_nj_0";
//			}else{
//				id = "a_nj_"+index;
//			}
//					
//			if($(id)){
//				$(id).click();
//			}	
//		}
//	);
//
//	jQuery.ajaxSetup({async:true});
	
	var zgh = jQuery("#select_jzg").val();
	
	if(zgh == "" || zgh == null){
		alertError("请在教职工列表中选择一位您希望编班的老师");
		return false;
	}

	var bjdm_my = new Array();//本人班级
	
	var i=0;

	jQuery("input[name=checkVal]:checked").each(function(){
		bjdm_my[i]= jQuery(this).val();
		i++;
	});
	
	if(i==0){
		alertInfo("请勾选您要操作的班级！");
		return false;
	}
	
	//参数
 	var parameter = {
 		"str_zgh":jQuery("#select_jzg").val(),
 		"array_bjdm_my":bjdm_my.join("!!array!!"),
 		"str_lx":jQuery("#hidden_lx").val()
	};

 	confirmInfo("确定要撤销该老师与选中班级的编班关系吗?",function(ok){
		if(ok=="ok"){
			
			jQuery.ajaxSetup({async:true});
			
			var url = "szdw_dwwh.do?method=disfrockFpbj";
			
			jQuery.post(url,
				parameter,
				function(result){	
					alertInfo(result);
					onShow(zgh);
				}
			);
		
			jQuery.ajaxSetup({async:true});
		}
 	});
}


//保存分配班级
function saveFpbj(){
	
//	var bjdm = new Array();//班级代码
//	var i=0;
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
//		bjdm[i]= jQuery(this).val();
//		i++;
//	});
//	
//	var url = "szdw_dwwh.do?method=saveFpbj";
//
//	//参数
// 	var parameter = {
// 		"array_bjdm":bjdm.join("!!array!!"),
// 		"str_zgh":jQuery("#hidden_zgh").val(),
// 		"str_lx":jQuery("#hidden_lx").val()
//	};
//
//	jQuery.ajaxSetup({async:true});
//	
//	jQuery.post(url,
//		parameter,
//		function(result){
//		
//			alertInfo(result);
//			
//			//创建年级Div
//			createNjLvDiv();
//			
//			jQuery("#div_fpbj_new").html("");					
//			
//			var index = jQuery("#hidden_nj").val();
//		
//			var id = "";
//			
//			if(index == ""){
//				id = "a_nj_0";
//			}else{
//				id = "a_nj_"+index;
//			}
//					
//			if($(id)){
//				$(id).click();
//			}	
//		}
//	);
//
//	jQuery.ajaxSetup({async:true});
	
	var zgh = jQuery("#select_jzg").val();
	
	if(zgh == "" || zgh == null){
		alertError("请在教职工列表中选择一位您希望编班的老师");
		return false;
	}
	
	var bjdm_other = new Array();//其他班级
	
	var bjdm_my = new Array();//本人班级
	
	var i=0;
	jQuery("input[name=checkVal][checked=false]").each(function(){
		bjdm_other[i]= jQuery(this).val();
		i++;
	});
	
	i=0;
	jQuery("input[name=checkVal]:checked").each(function(){
		bjdm_my[i]= jQuery(this).val();
		i++;
	});
	
	//参数
 	var parameter = {
 		"str_zgh":jQuery("#select_jzg").val(),
 		"array_bjdm_other":bjdm_other.join("!!array!!"),
 		"array_bjdm_my":bjdm_my.join("!!array!!"),
 		"str_lx":jQuery("#hidden_lx").val()
	};

	jQuery.ajaxSetup({async:true});
	
	var url = "szdw_dwwh.do?method=saveFpbj";
	
	jQuery.post(url,
		parameter,
		function(result){	
			alertInfo(result);
			onShow(zgh);
		}
	);

	jQuery.ajaxSetup({async:true});
}

//前往辅导员编班
function goFdybb(){
	
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 1){
		var zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		var url = "general_szdw.do?method=szdwRybb&lx=fdy&zgh="+zgh;
		refreshForm(url);
	}else{
		alertError("请<font color='blue'>勾选一条</font>您希望编班的教师记录");
		return false;
	}		
}

//前往班主任编班
function goBzrbb(){
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	
	if(num == 1){
		var zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").eq(0).val();
		var url = "general_szdw.do?method=szdwRybb&lx=bzr&zgh="+zgh;
		refreshForm(url);
	}else{
		alertError("请<font color='blue'>勾选一条</font>您希望编班的教师记录");
		return false;
	}	
}

//显示班级信息
function showBjxx12(zgh,lx,num){
	if(num == 0){
		alertError("带班数为0，无法查看详细");
		return false;
	}else{
	
		//路徑
		var url = "szdw_dwwh.do?method=createBjxxDiv";
		//參數
	 	var parameter = {
			"str_lx":lx,
			"str_zgh":zgh
		};
		
	 	jQuery("#div_bjxx").load(
			url,
			parameter,
			function(){
				tipsWindown(" ","id:div_bjxx","700","360","true","","true","id");
			}
		);
	}
}

/**
 * 修改弹出层页面
 * @param zgh
 * @param lx
 * @param num
 * @return
 */
function showBjxx(zgh,lx,num){
	if(num == 0){
		alertError("带班数为0，无法查看详细");
		return false;
	}else{
		var url = "szdw_dwwh.do?method=createBjxx&zgh="+zgh+"&lx="+lx;
		showDialog("", 700, 360, url);
	}
}

//显示教职工信息
function showJzgxx(bjdm,lx,num){
	if(num == 0){
		alertError("老师数为0，无法查看详细");
		return false;
	}else{
	
		//路徑
		var url = "szdw_dwwh.do?method=createJzgxxDiv";
		//參數
	 	var parameter = {
			"str_lx":lx,
			"str_bjV":bjdm
		};
		
	 	jQuery("#div_jzgxx").load(
			url,
			parameter,
			function(){
				tipsWindown(" ","id:div_jzgxx","700","360","true","","true","id");
			}
		);
	}
}