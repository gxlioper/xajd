function add(){
	var url = 'wjcf_cfsbgl.do?method=cxCfsbAdd';
	showDialog("增加处分上报", 800,500, url);
	
}

function save(url){
	if(!check('xh-xn-xq-cflbdm-cfyydm-wjsj-filepath-filepath4')){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	/*if(jQuery("#xxdm").val()=="12872"){
		if (jQuery(".MultiFile-label").length == 0){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整");;
		}
	}*/
	if(jQuery("#xxdm").val()=="70002"){
		if(!check('cfyj-wjssjg')){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}
	}
	if(jQuery("#wjssjg").val().length > 1000){
		return showAlert("[违纪事实经过]最大字数为1000,现已经超过，请确认！！");
	}
	if(jQuery("#bz").val().length > 1000){
		return showAlert("[备注]最大字数为1000,现已经超过，请确认！！");
	}
	
	
	var xh = jQuery("#xh").val();
	var cflbdm = jQuery("#cflbdm").val();
	var wjsj = jQuery("#wjsj").val();
	
	// 验证处分在上报和结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
		xh:xh,
		cflbdm:cflbdm,
		wjsj:wjsj
	}, function(data) {
		if(data ==null || data){
			showAlert("该学生在"+wjsj+"的违纪已上报或已在处分结果中存在！");
			return false;
		}else{
			// 提交保存
		    ajaxSubFormWithFun("cfsbglForm",url,function(data){
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
	},"json");
	
}
//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["shjg"]!="未提交" && rows[0]["shjg"]!="退回"){
			showAlertDivLayer("请选择未提交或已退回的记录");
			return false;
		}
		var url = 'wjcf_cfsbgl.do?method=cxCfsbUpdate&cfid='+rows[0]["cfid"];
		var title = "修改处分上报";
		showDialog(title,800,500,url);
	}
	
}

//保存修改
function saveUpdate(obj){
	if(!check('xh-xn-xq-cflbdm-cfyydm-wjsj-filepath-filepath4')){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(jQuery("#xxdm").val()=="70002"){
		if(!check('cfyj-wjssjg')){
			return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		}
	}
	/*if(jQuery("#xxdm").val()=="12872"){
		if (jQuery(".MultiFile-label").length == 0){		
			return showAlert("请将带<font color='red'>*</font>的项目填写完整");;
		}
	}*/
	if(jQuery("#wjssjg").val().length > 1000){
		return showAlert("[违纪事实经过]最大字数为1000,现已经超过，请确认！！");
	}
	if(jQuery("#bz").val().length > 1000){
		return showAlert("[备注]最大字数为1000,现已经超过，请确认！！");
	}
	var url= "";
	var xh = jQuery("#xh").val();
	var cflbdm = jQuery("#cflbdm").val();
	var wjsj = jQuery("#wjsj").val();
	var cfid = jQuery("#cfid").val();
	
	if(obj=="save"){
		url="wjcf_cfsbgl.do?method=cxCfsbUpdateSave&type=save";
	}else{
		url="wjcf_cfsbgl.do?method=cxCfsbUpdateSave&type=submit";
	}
	
	// 验证处分在上报和结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
		xh:xh,
		cflbdm:cflbdm,
		wjsj:wjsj,
		cfid:cfid
	}, function(data) {
		if(data ==null || data){
			showAlert("该学生在"+wjsj+"的违纪已上报或已在处分结果中存在！无法提交请确认！");
			return false;
		}else{
			// 提交保存
		    ajaxSubFormWithFun("cfsbglForm",url,function(data){
		  	 if(data["message"]=="保存成功！"|| data["message"]=="提交成功！"){
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
	},"json");
}

function cfsbView(cfid){
	var url = 'wjcf_cfsbgl.do?method=cfsbView&cfid='+cfid;
	var title = "查看处分上报";
	showDialog(title,800,400,url);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length==0){
		alertInfo("请选择您要删除的记录！");
	}else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["sbjg"] != "0"){
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("wjcf_cfsbgl.do?method=cfsbDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}});
	}
}

//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shjg"];
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		if ("未提交" == shzt){
					showAlertDivLayer("该记录为未提交状态，请先提交！");
					return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['cfid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "wjcf_cfsbgl.do";//dcclbh,导出功能编号
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号，执行导出的函数
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "wjcf_cfsbgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function detailInit(){
	var cflbdm = jQuery("#cflbdm").val();
	showCfqxFlag(cflbdm);
}

function updateInit(){
	var cflbdm = jQuery("#cflbdm").val();
	showCfqxFlag(cflbdm);
}

function showCfqxFlag(cflbdm){
	//对于青岛酒店管理职业技术学院屏蔽该功能
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}

function fjxz(){
	var url="wjcf_cfsh.do?method=fjxz";
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}