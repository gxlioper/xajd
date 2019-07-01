var ids="xh-xm-xn-yrbm-gwmc-ffny-gs-je-gznr";
function getSqlTj(){
	var xn = jQuery("#xn").val();
	var yrdwdm = jQuery("#yrbm").val();
	var sqlTj = " and xn = '"+xn+"' ";
	sqlTj+=" and yrdwdm = '"+yrdwdm+"' ";
	return sqlTj;
}
function changeGwmc(){
	// 重置
	jQuery("#gwmc").val("");
	var autoSetting = {
		dataTable:"xg_qgzx_gwxxb",
		dataField:"gwmc",
		sqlTj: getSqlTj,
		scrollHeight:135										
	}
	// 模糊搜索下拉【项目名称】
	jQuery("#gwmc").setAutocomplete(autoSetting);
}
//保存酬金发放表单
function saveForm(method){
	if(checkNull(ids)&&checkTextAreaLength("gznr","工作内容","500")){
	var ffny=jQuery("#ffny").val();
	var ffje=jQuery("#je").val();
	var yrdwdm=jQuery("#yrbm").val();
	var oldyrbm=jQuery("#oldyrbm").val();
	var oldje=jQuery("#oldje").val();
	//验证是否发放过参数
	var guid=jQuery("#guid").val();
	var xh=jQuery("#xh").val();
	var xn=jQuery("#xn").val();
	var yrdwdm=jQuery("#yrbm").val();
	var gwmc=jQuery("#gwmc").val();
	var ffny=jQuery("#ffny").val();
	if(!checkFfxx(guid,xh,xn,yrdwdm,gwmc,ffny)){
		return false;
	}
	var url = "qgzxJfglYjscjff.do?method="+method+"&type=save";
	ajaxSubFormWithFun("YjsCjffForm",url,function(data){
		alertInfo(data["message"],function(ty){
			if(ty=="ok"){
				//window.parent.searchRs();
				var api = frameElement.api,W = api.opener;
				W.searchRs();
				btn_close();
			}
		});
	});
}
}
function checkFfxx(guid,xh,xn,yrdwdm,gwmc,ffny){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.post("qgzxJfglYjscjff.do?method=checkFfxx",
			{guid:guid,xh:xh,xn:xn,yrdwdm:yrdwdm,gwmc:gwmc,ffny:ffny},
			function(result){
				if(result!="false"){
					var ts="此学生在<font color='blue'>"+xn+"</font>学年<font color='blue'>"+ffny+"</font> 发放年月已存在同岗位发放信息，请检查！";
					alertInfo(ts,function(tag){
						if(tag=="ok"){
							isok=false;
						}
					});
				}else{
					isok=true;
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	return isok;
}
function add(){
	showDialog('添加酬金发放信息', 720, 360, 'qgzxJfglYjscjff.do?method=zjyjsCjff');
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function btn_close(){
	frameElement.api.close();	
}

//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改酬金发放信息', 720, 360, 'qgzxJfglYjscjff.do?method=xgyjsCjff&guid='+rows[0]["guid"]);
	}
}
//删除记录
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
			confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
		
				if(ty=="ok"){
					jQuery.post("qgzxJfglYjscjff.do?method=scCjff",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
	}
}


function qgjgwhExportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport("qgzx_jfgl_yjscjff.do", qgjgwhExportData);
}
	

	
// 导出方法
function qgjgwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "qgzxJfglYjscjff.do?method=qgjgwhExportData&dcclbh=" + "qgzx_jfgl_yjscjff.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



