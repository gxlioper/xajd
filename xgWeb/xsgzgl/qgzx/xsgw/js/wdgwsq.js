var action="qgzx_wdgwsq.do";
function saveForm(){
	if(yzForm()){
		//jQuery("#but_save").hide();
		//jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
		//	var message = data["message"];
		//	if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=wdgwSq&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
						
						 if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			//}else{
			//	jQuery("#but_save").show();
			//	showAlert(message);
			//}
		//},'json');
		
	}
}
function updateForm(type){
	
	var xssqkg = jQuery("#xssqkg").val();
	var shzt = jQuery("#shzt1").val();
	
//	if("3"!=shzt&&"submit"==type&&"true"!= xssqkg){
//		showAlert("当前岗位申请未开放，请确认！");
//		return false;
//	}
	
	
	if(type == 'submit'){if(yzForm()){
		jQuery("#but_save").hide();
		jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val(),sqbh:jQuery("#sqbh").val()},function(data){
			var message = data["message"];
			if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=update&type="+type;
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			}else{
				jQuery("#but_save").show();
				showAlert(message);
			}
		},'json');
		}
	}else{if(yzForm()){
		var url = "qgzx_wdgwsq.do?method=update&type="+type;
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
		});
	}
	}
	
	
}
function tj(url){
	if(yzForm()){
		//jQuery("#but_save").hide();
		jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
			var message = data["message"];
			if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=wdgwSq&type=submit";
				ajaxSubFormWithFun("demoForm",url,function(data){
			 		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
			 		 	/* var msg = data["message"]+"<br>请及时联系老师面试，一小时后岗位申请自动失效！";*/
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }
			 		 else{
			    		 showAlert(data["message"]);
			    	 }
				});
			}else{
				jQuery("#but_save").show();
				showAlert(message);
			}
		},'json');
		
	}
}
//验证表单提交信息
function yzForm(){
	var gwdm = jQuery("#gwdm").val();
	var sqly = jQuery("#sqly").val();
	var xh = jQuery("#xh").val();
	var result =false;
	if(xh==null||""==xh){
		showAlert("请选择一个学生");
		return false;
	}else if(gwdm==null||""==gwdm){
		showAlert("请选择申请岗位");
		return false;
	}else if(sqly==null||""==sqly){
		showAlert("请输入申请理由");
		return false;
	}else if(sqly.length>1500){
		showAlert("申请理由不能超过1500字");
		return false;
	} else if(!jQuery("#xyscheck").is(":checked")){
        showAlert("请阅读协议！");
        return false;
	}else{
		result =true;
	}

	return result;
}
//初始化查询
var gridSetting = {
		caption:"我的岗位申请列表",
		pager:"pager",
		url:"qgzx_wdgwsq.do?method=wdgwsqCx&type=query",
		colList:[
           {label:'',name:'sqbh', index: 'sqbh',width:'3%',key:true,hidden:true},
           {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
		   {label:'类别',name:'pyccmc', index: 'pyccmc',width:'12%'},
            {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'12%'},
		   {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'15%'},
            {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
/*		   {label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'岗位类别',name:'bmlb', index: 'bmlb',width:'7%'},
		   {label:'岗位申请人数',name:'sqrs', index: 'sqrs',width:'10%'},*/
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审批状态',name:'shztmc', index: 'shztmc',width:'12%'},
           {label:'岗位代码',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'审批状态',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true}

		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	/*jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			var url="qgzx_wdgwsq.do?method=wdgwSq";
			refreshForm("qgzx_wdgwsq.do?method=wdgwSq");
			showDialog("岗位申请",800,500,url,null);
		}else{
			alertInfo(message);
		}
	},'json');*/
	var isopen = jQuery("#xssqkg").val();
	/*if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}*/
	if(jQuery("#xxdm").val()=='10704' && jQuery("#isTg")){//西安科技大学个性化
		if(jQuery("#isTg").val() == '1'){
			showAlertDivLayer("退岗不满一年，不能申请新岗位！");
			return false;
		}
	}
	var url="qgzx_wdgwsq.do?method=wdgwSq";
	showDialog("岗位申请",800,500,url,null);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function btn_close(){
	refreshForm("qgzx_wdgwsq.do?method=wdgwsqCx");
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
//选择申请岗位
function wdgwxzCx(){
	var xh = jQuery("#xh").val();
	if(xh==null || xh==""){
		showAlert("请选择一个学生");
	}else{
		var flg = true;
		jQuery.ajaxSetup({async:false});
		if(jQuery("#xxdm").val() == '10704'){//西安科技大学个性化判断该学生退岗时间
			var url = 'qgzx_wdgwsq.do?method=checkSfTg';			
			jQuery.getJSON(url,{xh:xh},function(data){
				if(data["message"] == '1' || data["message"] == 1){					
					flg = false;
				}
			})			
		}
		jQuery.ajaxSetup({async:true});
		if(!flg){
			showAlertDivLayer("退岗不满一年，不能申请新岗位！");
			return false;
		}
		showDialog("选择岗位",800,500,"qgzx_wdgwsq.do?method=wdgwxzCx&xh="+xh,{
			close:function(){
			}
		});
	}
	
}
//审批流查看
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		var shzt=rows[0]["shzt"];
		if(shzt==0||shzt=="0"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		//showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}
//申请信息查看
function seeInfo(sqbh,xh){
	if(sqbh==""){
		alertInfo("请您选择一条要查看的岗位申请！");
	} else {
		showDialog("学生岗位申请",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh,{
			close:function(){
			}
		});
	}
}
function submitBusi(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:rows[0]["xh"],gwdm:rows[0]["gwdm"],sqbh:rows[0]["sqbh"]},function(data){
					var message = data["message"];
					if(message=="true"){
						jQuery.post(action+"?method=subBusi", {
							values : ids.toString(),
							lcid : rows[0]['splc'],
							xh : rows[0]['xh'],
							gwdm : rows[0]['gwdm']
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}else{
						showAlertDivLayer(message);
					}
				},'json');
			}
		});
	}
	
}
//修改
function update() {
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	var xssqkg = jQuery("#xssqkg").val();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt=rows[0]["shzt"];
		if(shzt!='0'&&shzt!='3'){
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var xh=rows[0]["xh"];
		var gwdm=rows[0]["gwdm"];
		var url = action+'?method=update&sqbh=' + rows[0]["sqbh"]+"&xh="+xh+"&gwdm="+gwdm+"&xssqkg="+xssqkg;
		var title = "修改岗位申请";
		showDialog(title, 800, 500, url);
	}
}
//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				
			}
		});
	}
}
//取消申请
function qx_sh(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				
			}
		});
	}
}


function qggwsqExportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport("qgzx_wdgwsq.do?method=wdgwsqCx", qgsqExportData);
}
	

	
// 导出方法
function qgsqExportData() {
    setSearchTj();//设置高级查询条件
    var url = "qgzx_wdgwsq.do?method=qgsqExportData&dcclbh=" + "qgzx_wdgwsq.do?method=wdgwsqCx";//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}