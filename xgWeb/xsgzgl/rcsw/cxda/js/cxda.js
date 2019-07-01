/**
 * @author 喻鑫源 
 * @功能:的js
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "rcsw_cxda.do?method=add";
	var title = "增加诚信档案";
	showDialog(title, 900, 450, url);
}

//删除诚信档案
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("rcsw_cxda.do?method=delCxda",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

//修改
function update(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_cxda.do?method=editCxda&id=' + ids;
		var title = "修改诚信档案";
		showDialog(title, 800, 400, url);
	}
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CxdaView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//查看学生ajaxurl跳转
function CxdaView(id, xh) {
	showDialog("查看学生干部考核结果", 800, 350, "rcsw_cxda.do?method=CxdaView&id="
			+ id + "&xh=" + xh);
}

//查看学生v2(勾选一条学生记录，点击查看)
function CxdaViewv2(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (parseInt(ids.length) != 1){
		showAlertDivLayer("请选择一条学生记录进行查看！");
		return false;
	}
	showDialog("查看学生干部考核结果", 800, 350, "rcsw_cxda.do?method=CxdaView&id="
			+ ids);
}

var DCCLBH = "rcsw_cxda_cxda.do";
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData1);
}

//导出方法
function exportData1() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_cxda.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_N732901_CXDA");
	return false;
}

//增加修改结果保存
function saveCxda(){
	var ids = "xn"+"-"+"xq"+"-"+"cxfs";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "rcsw_cxda.do?method=saveCxda";
	ajaxSubFormWithFun("CxdaForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function checkzs(){
	if(jQuery("#bz").val().length > 500){
		var num = jQuery("#bz").val().substr(0,50);
		showAlert("备注超过字数500,请确认！");
		return false;
	}
}


function addrowclick(){
	var value = jQuery("#rownum").val();
	if(value >= 100){
		jQuery("#rownum").val(100);
		showAlert("行数不得超过100！");
		return false;
	}else if(value == ''){
		value = 0;
	}
	var value1 = parseInt(value)+1;
	jQuery("#rownum").val(value1);
}

function delrowclick(){
	var value = jQuery("#rownum").val();
	var value1 = parseInt(value)-1;
	if(value <= 1){
		jQuery("#rownum").val(1);
		showAlert("行数不得少于1！");
		return false;
	}
	jQuery("#rownum").val(value1);
}

function checkrownum(){
	var value = jQuery("#rownum").val();
	if(value>100){
		jQuery("#rownum").val(100);
		showAlert("行数不得超过100！");
		return false;
	}
	if(value<1 || value == ''){
		jQuery("#rownum").val(1);
		showAlert("行数不得少于1！");
		return false;
	}
}

function saveCxdaAdd(){
	var arrlen = jQuery("#tbody_self tr").length;
	if(arrlen == 0){
		showAlert("无任何学生档案数据，不允许保存！");
		return false;
	};
	if(jQuery("#xn").val() == ''){
		showAlert("带"+"<font color='red'>*</font>"+"必填项不可为空！");
		return false;
	}
	var xh = '';
	var cxfs = '';
	var bz = '';
	var flag = true;
	jQuery("#tbody_self tr").each(function(b){
		var tempxh=jQuery(this).find("td:eq(1) input").val();
		var tempcxfs=jQuery(this).find("td:eq(4) input").val();
		var tempbz =jQuery(this).find("td:eq(5) textarea").val();
		if(tempxh == '' || tempcxfs == ''){
			flag = false;
			var falserow = b+1;
			showAlert("第"+falserow+"行数据带"+"<font color='red'>*</font>"+"号必填字段，请填写完整！");
			return false;
		}else if(tempbz.length>500){
			flag = false;
			var falserow = b+1;
			showAlert("第"+falserow+"行数据备注字段长度超过500，请确认！");
			return false;
		}else if(tempcxfs < 0){
			flag = false;
			var falserow = b+1;
			showAlert("第"+falserow+"行数据诚信分数为负数，请确认！");
			return false;
		}
	})
	if(flag == false){
		return false;
	}
	var url = "rcsw_cxda.do?method=saveCxdaAdd";
	ajaxSubFormWithFun("CxdaForm",url,function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    		}
		});
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//备注等长字段数据
function checkzs(){
	if(jQuery("#bz").val().length > 500){
		var num = jQuery("#bz").val().substr(0,50);
		showAlert("备注超过字数500,请确认！");
		return false;
	}
}

function checkzsonKeyUp(obj){
	if(obj.value.length>500){
		showAlert("备注超过字数500,请确认！");
		return false;
	}
}

//onblur时验证诚信分数 
function checkfs(obj){
	if(obj.value>100){
		obj.value = '';
		showAlert("诚信分数不得超过100分,请确认！");
		return false;
	}
}

