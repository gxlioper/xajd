/**
 * 验证是否存在空项
 * 
 * @param ids
 *            要验证的控件id "-"分隔
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

function searchRs(){

	var map = getSuperSearch();	
	var sfyby = jQuery("#sfyby").val();
	map["sfyby"] = sfyby;
	if("1" ==sfyby){
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
}

// 页签切换
function selectTab(obj,sfyby){
	jQuery("#sfyby").val(sfyby);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	if('1' == sfyby){
		jQuery("#li_qxby").show();
		jQuery("#li_by").hide();
		jQuery("#li_bydr").hide();
		jQuery("#li_jcdy").hide();
	}else{
		jQuery("#li_by").show();
		jQuery("#li_bydr").show();
		jQuery("#li_jcdy").show();
		jQuery("#li_qxby").hide();
	}
	searchRs();
}	
// 毕业处理
function bycl(){	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "bycl.do?method=bycl";
		// 高级查询
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		// 模糊查询
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";

		
		showDialog("毕业处理",420,230,url);
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("毕业处理",420,230,"bycl.do?method=bycl&values="+ids.toString());
	}
}

// 取消毕业处理
function qxbycl(){	
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "bycl.do?method=qxbycl";
		// 高级查询
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		// 模糊查询
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";
		
		confirmInfo("您确定要<font color='blue'>【取消毕业】</font><br><font color='red'>全部的记录</font>吗?",function(ty){
			if(ty=="ok"){
				jQuery.post(url,{},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		confirmInfo("您确定要对这<font color='red'>"+ids.length +"条记录</font>进行<font color='blue'>【取消毕业】</font>处理吗?",function(ty){
			if(ty=="ok"){
				jQuery.post("bycl.do?method=qxbycl",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}

function exportConfig() {
	var path = "xjyd_bycl.do";
	customExport(path, exportData,690,500);
}
// 导出方法
function exportData() {

	var sfyby = jQuery("#sfyby").val();
	var path = "xjyd_bycl.do";
	setSearchTj();// 设置高级查询条件
	var url = "bycl.do?method=exportData&dcclbh="+path+"&sfyby="+sfyby;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}
// 老版查看弹出层
function zxsxxView(xh){

		showDialog("学生信息查询",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
}
// 新版查看弹出层
// function zxsxxView(xh) {
// showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
// + "&xs");
// }

function getJcXxWord(){
	var xh="";
	var ids = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				showAlertDivLayer("没有学生信息，请重新查询！");
				return ;
			}
		var url = "bycl.do?method=printPlJcXx";
		var map = getSuperSearch();
		//高级查询
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		//模糊查询
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		window.open(url);
	}
	else if(ids.length == 1){
		xh = ids[0]["xh"];
		var url = "bycl.do?method=printJcXx&xh="+xh;
		var flag=false;
		jQuery.ajaxSetup({async:false});
		jQuery.post("bycl.do?method=checkJcxx",{xh:xh},function(data){
			 if(data["message"]=="true"){
				 flag=true;
	   	 }else{
	   		 showAlert(data["message"]);
	   		}
			}, 'json');
		jQuery.ajaxSetup({async:true});
		if(flag){
			window.open(url);
		}
	}
	else{
		for(var i=0;i<ids.length;i++){
			if(i==ids.length-1){
				xh +=ids[i]["xh"];
			}else{
				xh +=ids[i]["xh"]+",";
			}
		}
		var url ="bycl.do?method=printJcXxZip&xh="+xh;
		window.open(url);
				
	}
}

/**
 * 北京中医药大学个性化导入按钮
 */
function byxsImport(){
	var url = "bycl.do?method=byxsImport";
	var title = "毕业学生信息导入";
	showDialog(title, 500, 309, url);
}
/**
 * 下载模板
 * @return
 */
function mbDownload(){
	var url = "bycl.do?method=downloadFile";
	window.open(url);
}

/**
 * 将文件名称赋值到input框
 * @param obj
 * @return
 */
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * 获取input file的名称
 * @param obj
 * @return
 */
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

/**
 * 文件类型控制
 * @param obj
 * @return
 */
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*如果不符合上传类型,清空input file，兼容性写法，兼容ie和chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("只允许上传xls类型的文件。");
		return false;
	}
}

/**
 * 导出验证
 * @return
 */
function saveRr(){
	var url = "bycl.do?method=SaveDrForm";
	if(!check("byny")){
		return showAlert("请选择毕业年月！");
	}
	if(jQuery("#drmb").val() == "" ||　jQuery("#drmb").val() == null){
		showAlert("请选择导入文件！");
		return false;
	}
	ajaxSubFormWithFun("byclForm", url, function(data) {
		 if(data["message"]=="导入成功！"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
	   	 }else{
			 showAlert(data["message"],{},{"clkFun":function(){
				      if(data["message"] == "导入失败,请仔细核对【错误数据.xls】！"){
				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
				    		  jQuery("#errortr").show();
				    		  jQuery("#errora").attr("data_file","bycl.do?method=downloadFileError&filename="+data["gid"]);
				    	  }
				      }else{
				    	 jQuery("#errortr").hide();
				    	jQuery("#errora").attr("data_file","");
				      }
				      jQuery("#drmb").val("");
			}});
	   	 }
	});
}

/**
 * 错误数据下载
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}