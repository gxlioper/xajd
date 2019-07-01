var action="xsgwsqnew_sq.do";
function saveForm(){
	if(yzForm()){
		//jQuery("#but_save").hide();
		//jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
		//	var message = data["message"];
		//	if(message=="true"){
				var url = "xsgwsqnew_sq.do?method=addXsgwSq&type=save";
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
	}else{
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
function tj(url){
	if(yzForm()){		
		var url = "xsgwsqnew_sq.do?method=addXsgwSq&type=submit";
		ajaxSubFormWithFun("demoForm",url,function(data){
			if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
			    	}
			    }});
			}else{
			    showAlertDivLayer(data["message"]);
			}
		});	
	}
}
//验证表单提交信息
function yzForm(){
	var gwdm = jQuery("#gwdm").val();
	var sqly = jQuery("#sqly").val();
	var xh = jQuery("#xh").val();
	var result =false;
	if(xh==null||""==xh){
		alertInfo("请选择一个学生");
		return false;
	}else if(sqly==null||""==sqly){
		alertInfo("请输入申请理由");
		return false;
	}else if(sqly.length>500){
		alertInfo("申请理由不能超过500字");
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
		url:"xsgwsqnew_sq.do?method=getXsgwSqList&type=query",
		colList:[
           {label:'',name:'sqbh', index: 'sqbh',width:'3%',key:true,hidden:true},
           {label:'学号',name:'xh', index: 'xh',width:'12%'},
		   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
		   {label:'学年',name:'zhxn', index: 'zhxn',width:'10%'},
		   {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
		   {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'15%',formatter:xhLink},
		   {label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'岗位类别',name:'bmlb', index: 'bmlb',width:'7%'},
		   {label:'岗位代码',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审批状态',name:'shztmc', index: 'shztmc',width:'12%'},
		   {label:'审批状态',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true}
		  
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["gwdm"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	if(jQuery("#xxdm").val() == '10704' && jQuery("#isTg")){
		if(jQuery("#isTg").val() == '1'){
			showAlertDivLayer("退岗不满一年，不能申请新岗位！");
			return false;
		}
	}
	
	var url="xsgwsqnew_sq.do?method=addXsgwSq";
	showDialog("岗位申请",800,500,url,null);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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
		if(shzt==99||shzt=="99"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		//showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}
//申请信息查看
function seeInfo(sqbh,gwdm,xh){
	if(sqbh == "" || sqbh == null || sqbh == "null") {
		showDialog("学生岗位申请",765,500,"xsgwsqnew_sq.do?method=ckXsgwJgwh&xh="+xh+"&gwdm="+gwdm,{
			close:function(){
			}
		});
	} else {
		showDialog("学生岗位申请",765,500,"xsgwsqnew_sq.do?method=ckXsgwSq&xh="+xh+"&sqbh="+sqbh,{
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
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['splc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');		
			}
		});
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
				showAlertDivLayer("只能删除未提交或者已退回的记录！");
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


//选择申请岗位
function wdgwxzCxF(){
	var xh = jQuery("#xh").val();
	if(xh==null || xh==""){
		showAlert("请选择一个学生");
	}else{
		showDialog("选择岗位",800,500,"xsgwsqnew_sq.do?method=wdgwxzCx&xh="+xh,{
			close:function(){
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