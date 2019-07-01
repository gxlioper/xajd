/**
 * @author 喻鑫源 
 * @功能:住宿结果的js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "gygl_xyzsjggl.do?method=add";
	var title = "校外住宿结果";
	showDialog(title, 900, 552, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'gygl_xyzsjggl.do?method=editZsjg&sqbh=' + rows[0]["sqbh"]
				+ '&xh=' + rows[0]["xh"];
		var title = "校外住宿结果";
		showDialog(title, 900, 552, url);
	}
}

//增加修改结果保存
function saveZsjg(type){
	var ids = "xh"+"-"+"start"+"-"+"end"+"-"+"xxdz"+"-"+"lxdh"+"-"+"parentslxdy"+"-"+"xl";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}else if(checkContentIsNull()== false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	//江西航空个性化
	if("13871" == jQuery("#xxdm").val()){
		if (jQuery(".MultiFile-label").length == 0){
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;
		}
	}
	if(checkDataIsExact() == false){
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if((jQuery("#start").val()>jQuery("#end").val())){
		showAlert("校外住宿申请起始日期不能大于终止日期，请选择！");
		return false;
	}
	var url = "gygl_xyzsjggl.do?method=saveZsjg&type=" + type;
	ajaxSubFormWithFun("XyzsglForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ZsjgView(\""
			+ rowObject["sqbh"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function ZsjgView(sqbh, xh) {
	showDialog("校外申请结果查看", 900, 480, "gygl_xyzsjggl.do?method=ZsjgView&sqbh="
			+ sqbh + "&xh=" + xh);
}
//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gygl_xyzsjggl.do?method=delZsjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "gygl_xyzsjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gygl_xyzsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//校外住宿结果申请表导出
function printXyzsjgsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqb";
		url += "&sqbh=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "gygl_xyzsjggl.do?method=getXyzsjgsqbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

//判断住宿结果重要内容是否为空的函数
function checkContentIsNull(){
	 if(jQuery('input[name="zwjzyy"]:checked').val() == null){
		return false;
	}
}

//验证数据的准确性
function checkDataIsExact(){
	if(jQuery("#start").val()>jQuery("#end").val()){
		showAlert("校外住宿申请起始日期不能大于终止日期，请选择！");
		return false;
	}
}

//手机电话验证
function phonecheck(obj){
	  var phone = obj.val();
	  var tel = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/;
	  if(phone != "") {
	   if (!tel.exec(phone)) {
		showAlertDivLayer("电话号码格式不对，正确格式如下：\n座机号码：区号-电话号码(或)\n手机号码：13635456878");
		obj.focus();
		return false;
	   }
	  }
}

function checkzs(){
	if(jQuery("#bz").val().length > 50){
		showAlertDivLayer("备注最大字数为50，现已经超过，请确认！！");
		return false;
	}
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



