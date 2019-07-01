

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var isopen = jQuery("#sqkg").val();
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("该学生在当前学年已有申请记录，请勿重复填写！");
		return false;
	}
	var url = "xnwxdk_jg.do?method=add";
	var title = "校内无息贷款结果增加";
	showDialog(title, 770, 552, url);
}

//增加修改申请保存
function saveDkjg(type){
	var ids = ""
	if( type == "update"){
	   ids = "jttg"+"-"+"zxj"+"-"+"jxj"+"-"+"qgzxsr"+"-"+"xnwxjk"+"-"+"qtsr"+"-"+"zxdksqje"+"-"+"zxdksqsj"+"-"+"ffje"+"-"+"ffsj"+"-"+"sqje"+"-"+"sqly";
	}else{
		ids ="xh"+"-"+ "jttg"+"-"+"zxj"+"-"+"jxj"+"-"+"qgzxsr"+"-"+"xnwxjk"+"-"+"qtsr"+"-"+"zxdksqje"+"-"+"zxdksqsj"+"-"+"ffje"+"-"+"ffsj"+"-"+"sqje"+"-"+"sqly";
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(parseInt(jQuery("#sqje").val()) > parseInt(jQuery("#jesx").val())){
		showAlert("超出金额申请上限"+jQuery("#jesx").val()+"元！");
        return false;
	}
	var url = "xnwxdk_jg.do?method=saveDkjg&type=" + type;
	ajaxSubFormWithFun("XnwxdkJgModel", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='DkjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function DkjgView(sqbh, xh) {
	showDialog("校内无息贷款结果查看", 770, 450, "xnwxdk_jg.do?method=DkjgView&jgid="
			+ sqbh + "&xh=" + xh);
}

//删除贷款申请
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}  else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xnwxdk_jg.do?method=delDkjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
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
		var url = 'xnwxdk_jg.do?method=editDkjg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "校内无息贷款结果修改";
		showDialog(title, 770, 552, url);
	}
}

var DCCLBH = "zxdk_xnwxdk_jg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xnwxdk_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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


//校内无息贷款结果申请表导出
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xnwxdk_jg.do?method=getXnwxdksq";
		url += "&jgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xnwxdk_jg.do?method=gettXnwxdkTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

function printHzb(){
	setSearchTj();//设置高级查询条件
	var url = "xnwxdk_jg.do?method=getHzbexcel";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//导入
function importConfig(){
	toImportDataNew("IMPORT_XNWXDK");
	return false;
}


