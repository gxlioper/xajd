function initZyArray(xyId,zyId){

	var xydm = jQuery("#"+xyId).val();
	var url = "tdjs.do?method=getZyList";

	//if (xydm != ""){
		jQuery.post(url,{bmdm:xydm},function(data){
			if (data != null){
				jQuery("#"+zyId+" option").remove();

				jQuery("#"+zyId).append("<option></option>");
				
				for (var i = 0 , n = data.length ; i < n ; i++){
					var option = "<option value='"+data[i]["zydm"]+"'>"+data[i]["zymc"]+"</option>";
					jQuery("#"+zyId).append(option);
				}
			}
		},'json');
	//}
};


function loadZdls(){
	jQuery.post("tdjs.do?method=loadZdls",{zgh:jQuery("#zgh").val()},function(data){
		if (!jQuery.isEmptyObject(data)){
			jQuery("#xm").text(data["xm"]);
		} else {
			jQuery("#xm").text("");
			jQuery("#zgh").val("");
			alertInfo("该工号不存在！");
		}
	},'json');
}

function createTd(){
	showWindow("系统提示",750,500,"tdjs.do?method=cjtd");
}


function fpxs(){

	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		alertInfo("请选择您要分配学生的团队！");
	} else {
		var url = "tdjs.do?method=fpxs&values="+ids.toString();

		showWindow('分配学生',700,600,url);
	}
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		showWindow('修改团队信息',400,300,'tdjs.do?method=updateTdinfo&bjdm='+rows[0]["bjdm"]);
	}
}

function delTdinfo(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlert("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除所选择的团队吗？",{"okFun":function(t){
			jQuery.post("tdjs.do?method=delTdinfo",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function saveTd(){
	var url = "tdjs.do?method=saveTdinfo";

	var njArray = jQuery("select[name=njArray]");
	var xyArray = jQuery("select[name=xyArray]");
	var zyArray = jQuery("select[name=zyArray]");
	var tdArray = jQuery("input[name=tdArray]");

	var njFlg = true;
	jQuery.each(njArray,function(i,e){
		var njVal = jQuery(e).val();
		if (njVal == null || njVal == ''){
			njFlg = false;
			return;
		}
	});

	var xyFlg = true;
	jQuery.each(xyArray,function(i,e){
		var xyVal = jQuery(e).val();
		if (xyVal == null || xyVal == ''){
			xyFlg = false;
			return;
		}
	});

	var zyFlg = true;
	jQuery.each(zyArray,function(i,e){
		var zyVal = jQuery(e).val();
		if (zyVal == null || zyVal == ''){
			zyFlg = false;
			return;
		}
	});
	
	var tdFlg = true;
	jQuery.each(tdArray,function(i,e){
		var tdVal = jQuery(e).val();
		if (tdVal == null || tdVal == ''){
			tdFlg = false;
			return;
		}
	});
	
	if (njFlg || xyFlg || zyFlg || tdFlg){
		showAlert("团队信息必须填写完整！");
		return false;
	}
	
	ajaxSubFormWithFun("cjtdForm",url,callBackSaveTdinfo);
}

function callBackSaveTdinfo(data){
	alertInfo(data["message"],function(t){
		if (t == 'ok'){
			refershParent();
		}
	});
}

function showStudents(bjdm){
	showWindow("团队学生列表",700,500,"tdjs.do?method=showStudents&bjdm="+bjdm);
}

function tdxsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='showStudents(\""+rowObject["bjdm"]+"\");'>"+cellValue+"</a>";
}