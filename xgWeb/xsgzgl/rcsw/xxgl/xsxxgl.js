//保存学生献血信息
function saveForm(method){
	if(yzForm()){
		var url = "rcsw_xsxxgl.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});

		});
	}
}
//验证表单提交信息
function yzForm(){
	var result =false;
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xxsj = jQuery("#xxsj").val();
	var bz = jQuery("#bz").val();
	if(xh==null||""==xh){
		alertInfo("学号不能空");
	}if(xn==null||""==xn){
		alertInfo("请选择学年");
	}else if(xxsj==null||""==xxsj){
		alertInfo("献血时间不能空");
	}else if(bz.length>2000){
		alertInfo("备注只能输入1000字!");
	}else{
		result =true;
	}
	return result;
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckXsxxgl(\""+rowObject["xxgldm"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function add(){
	//showWindow("添加学生献血信息",680,600,'rcsw_xsxxgl.do?method=zjXxgl',null);

	showDialog("添加学生献血信息",680,420,'rcsw_xsxxgl.do?method=zjXxgl');
	//showTopWin("rcsw_xsxxgl.do?method=zjXxgl",800,600);
	//refreshForm("rcsw_xsxxgl.do?method=zjXxgl");
}
function ckXsxxgl(xxgldm,cellValue){
	showDialog("查看学生献血信息",680,350,'rcsw_xsxxgl.do?method=ckXxgl&xxgldm='+xxgldm+"&xh="+cellValue,null);
	//showWindow("查看学生献血信息",680,450,'rcsw_xsxxgl.do?method=ckXxgl&xxgldm='+xxgldm+"&xh="+cellValue,null);

}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function btn_close(){

	iFClose();
}
//加载查询结果
function query(){
	var map ={};
	var array = jQuery("#myForm").serializeArray();
		jQuery(array).each(function(index) {
			map[jQuery(this).attr("name")] = jQuery(this).val();
	 });
	jQuery("#dataTable").reloadGrid(map);
}
//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		//refreshForm('rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]);
		//showWindow("修改学生献血信息",680,450,'rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]+"&xh="+rows[0]["xh"],null);
		showDialog("修改学生献血信息",680,450,'rcsw_xsxxgl.do?method=xgXxgl&xxgldm='+rows[0]["xxgldm"]+"&xh="+rows[0]["xh"],null);

	}
}
//删除记录
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		confirmInfo("您确定要删除这些记录吗？",function(tp){
			if(tp=="ok"){
				jQuery.post("rcsw_xsxxgl.do?method=scXxgl",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
	}
}
function checkStudent(){
	showWindow('请选择一个学生',680,450,'xsxx_xsgl.do?method=showStudents&goto=rcsw_xsxxgl.do?method=zjXxgl');
}

function xsxxxxwhExportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport("rcsw_xsxxgl_xsxxxxwh.do", xsxxxxwhExportData);
}
	

	
// 导出方法
function xsxxxxwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xsxxgl.do?method=xsxxxxwhExportData&dcclbh=" + "rcsw_xsxxgl_xsxxxxwh.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}