//前往调整记录页面
function goTzjl(){
	var url = "pj_tzjl.do";
	refreshForm(url);
}

//前往参评人员名单页面
function goCpry(){
	var url = "pj_cpmd.do";
	refreshForm(url);
}

/**
 * 增加参评学生
 */

function cpxsZj(){
	
	showDialog("调整参评班级",640,288,"xpj_cpmd.do?method=tzcpxszt");
}

/**
 * 调整参评学生
 */

function cpxsTz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要调整的人员记录");
		return false;
	}
	
	var ids = jQuery("#dataTable").getSeletIds();
	var tjzt = rows[0]["tjzt"];
	var sfdh = rows[0]["sfdh"];
	var tzr = rows[0]["tzr"];
	var tzrxm = rows[0]["tzrxm"];
	var userName = document.getElementById("userName").value;
	
	if("已提交"==tjzt){
		showAlertDivLayer("学生综测分已被提交确认，不能再进行调整");
		return false;
	}
	if(tzr!=userName&&"是"==sfdh&&null!=tzr){
		showAlertDivLayer("该学生已被“"+tzrxm+"”调整过一次，不能再进行调整");
		return false;
	}
	
	
	showDialog("调整参评班级",640,288,"xpj_cpmd.do?method=tzcpxszt&xh=" + rows[0]["xh"]+"&ids="+ids);
}

/**
 * 取消参评学生
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消参评资格的学生！");
	} else {
		showConfirmDivLayer("您确定取消选中学生的参评资格吗？",{"okFun":function(){
			jQuery.post("xpj_cpmd.do?method=delCpxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 选择班级 
 */
function getBjbyPzzd(){
	var zdpzstr = jQuery('#zdpzstr').val();
	getBj(zdpzstr);
}


/**
 * 保存调整参评班级
 */

function saveCpxs(){

	var tzqbjdm = jQuery('#tzqbjdm').val();		//调整前班级代码
	var tzhbjdm = jQuery('#bjdm').val();		//调整后班级代码
	var ids = jQuery('#ids').val();				//选中id
	var bjmc = jQuery('#bjmc').val();			//调整后班级名称
	var xh = jQuery('#wjxh').val();				//学号

	if(xh == ""){
		showAlert("学号不能为空");
		return false;
	}if(tzhbjdm == ""){
		showAlert("参评班级不能为空");	
		return false;
	}
	
	showConfirm("您确定将该学生调整到“"+bjmc+"”班吗？",{"okFun":function(){
		var url = "xpj_cpmd.do?method=updateCpxs&tzqbjdm="+tzqbjdm+"&ids="+ids+"&tzhbjdm="+tzhbjdm+"&xh="+xh;
		jQuery.post(url,{},function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}})
		},"json");
	}});
	
}

//加载学生信息
function checkXh(xh){

	var xhInfo = jQuery('#xhInfo').val();
	if(""!=xhInfo){
		showAlert(xhInfo);
	}
	
	if (xh !="" ){
		document.location.href='xpj_cpmd.do?method=tzcpxszt&xh='+xh;
	}
}

//调整记录导出
function exportConfig(){
	var DCCLBH="pj_tzjl.do";
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH="pj_tzjl.do";
	setSearchTj();//设置高级查询条件
	var url = "xpj_cpmd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function tzCpbj(tzqCpbj,xh){
	var tzhCpbj = jQuery("#bjdm").val();
	
	jQuery.post("xpj_cpmd.do?method=updateCpxs",{xh:xh,tzqbjdm:tzqCpbj,tzhbjdm:tzhCpbj},function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	},"json");
}

//显示班级调整Div
function showBjtzDiv(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	for(var i=0;i<rows.length;i++){
		if(rows[i]['tjzt']=='1'){
			showAlertDivLayer("已锁定的人员不可以调整！");
			return false;
		}
	}

	if(rows.length == 0){
		showAlertDivLayer("请您<font color='blue'>勾选</font>需要调整班级的学生");
		return false;
	}else{
		tipsWindown("系统提示","id:div_bjtz","360","200","true","","true","id");
	}
}


//人员班级调整
function tzCpry(){
	
	var bjdm = jQuery("#bjdm").val();
	var ids = jQuery("#dataTable").getSeletIds();
	
	jQuery.post("xpj_cpmd.do?method=updateCpbj",{bjdm:bjdm,ids:ids.toString()},function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	},"json");
}

