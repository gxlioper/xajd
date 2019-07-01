
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function saveForm(){
	var sqly = jQuery("#sqly").val();
	if(sqly==null||""==sqly){
		alertInfo("请输入申请理由");
		return false;
	}
	var url = "rcsw_txhd_xs_xmsq.do?method=xmsq&type=save";
	ajaxSubFormWithFun("demoForm",url,function(data){
	showAlert(data["message"],{},{"clkFun":function(){
						
	if (parent.window){
		    refershParent();
		    	}
		  }});
	});
			
}
var gridSetting1 = {
		caption:"项目申请选择列表",
		pager:"pager",
		url:"rcsw_txhd_xs_xmsq.do?method=xmsqAjaxXs&type=wsq",
		colList:[
		   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
		   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'活动时间',name:'hdsj', index: 'hdsj',width:'30%'},
		   {label:'活动地点',name:'hddd', index: 'hddd',width:'20%'},
		   {label:'剩余名额',name:'syme', index: 'syme',width:'10%'},
		   {label:'申请状态',name:'shztmc', index: 'shztmc',width:'20%'},
		   {label:'申请状态id',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {name:'splc', index: 'splc',hidden:true},
		   {name:'sqid', index: 'sqid',hidden:true}
		   
		],
		// dblclick:function(rowObject){
			// 选择岗位
		// xzGw(rowObject);
		// },
		sortname: "shzt",
	 	sortorder: "asc"
	};

var gridSetting2 = {
		caption:"项目申请列表",
		radioselect:true,
		pager:"pager",
		url:"rcsw_txhd_xs_xmsq.do?method=xmsqAjaxXs&type=ysq",
		colList:[
		   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
		   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'10%'},
		   {label:'活动时间',name:'hdsj', index: 'hdsj',width:'20%'},
		   {label:'活动地点',name:'hddd', index: 'hddd',width:'10%'},
		   {label:'限制人数',name:'sqrssx', index: 'sqrssx',width:'5%'},
		   {label:'已申请人数',name:'ysqrs', index: 'ysqrs',width:'10%'},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'25%'},
		   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'20%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {name:'splc', index: 'splc',hidden:true}
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	};




/**
 * 修改申请表
 * 
 * @returns {Boolean}
 */
function xgSqb(){
	 var xh = jQuery('#userName').val();
	 var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要修改的记录！");
		} else {
			var shzt=rows[0]["shzt"];
			if(shzt!='0'&&shzt!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			var gwdm=rows[0]["gwdm"];
			var url = 'rcsw_txhd_xs_xmsq.do?method=update&sqid=' + rows[0]["sqid"]+"&xh="+xh;
			var title = "修改岗位申请";
			showDialog(title, 800, 500, url);
		}
}

function updateForm(type){
	
	if(type == 'submit'){
		var sqly = jQuery("#sqly").val();
		if(sqly==null||""==sqly){
			alertInfo("请输入申请理由");
			return false;
		}
		jQuery("#but_save").hide();
				var url = "rcsw_txhd_xs_xmsq.do?method=update&type="+type;
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			}	
	else{
		var url = "rcsw_txhd_xs_xmsq.do?method=update&type="+type;
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
		});
	}
	
	
}

/**
 * 填写申请表
 */
function xmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请至少选择一个申请项目！");
		return false;
	}

	if(rows[0]["sqid"] != '' && rows[0]["sqid"] != null){
		showAlertDivLayer("所选项目已申请，请确认！");
		return false;
	}
	
	if(rows[0]["syme"] =='0'){
		showAlertDivLayer("当前活动项目申请人数已满！");
		return false;
	}
	

	var xmdm = rows[0]["xmdm"];
	var xh = jQuery('#userName').val();
	
	var url="rcsw_txhd_xs_xmsq.do?method=xmsqXs&xmdm=" + xmdm+"&xh="+xh;
	
	showDialog("项目申请",800,500,url,null);
}


/**
 * 流程跟踪
 * 
 * @return
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}	


// 提交
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var xh = jQuery('#userName').val();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length >1 ) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
						jQuery.post("rcsw_txhd_xs_xmsq.do?method=subBusi", {
							sqid : rows[0]['sqid'],
							splc : rows[0]['splc'],
							xmdm : rows[0]['xmdm'],
							xh :xh
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
		}
		});
		}
}

// 撤销
function cxSqb(){
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
			
				jQuery.post("rcsw_txhd_xs_xmsq.do?method=cancle", {
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


function tj(url){
	var sqly = jQuery("#sqly").val();
	if(sqly==null||""==sqly){
		alertInfo("请输入申请理由");
		return false;
	}
	ajaxSubFormWithFun("demoForm",url,function(data){
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
   		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }
		 else{
   		 showAlertDivLayer(data["message"]);
   	 }
	});
	}


/**
 * 删除申请
 */
function delSqb(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i]['sqid'])
	}
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	}else {
		showConfirmDivLayer("您确定要取消该申请吗？",{"okFun":function(){
			jQuery.post("rcsw_txhd_xs_xmsq.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/**
 * 下载登记表
 */
function printXmdjb(url){
var sqid="";
var gwdm="";
var rows = jQuery("#dataTable").getSeletRow();
if (rows.length <1) {
 showAlertDivLayer("请至少选择一条记录！");
} else {
for(var i=0;i<rows.length;i++){
if(i==rows.length-1){
	sqid +=rows[i]["sqbh"];
	gwdm +=rows[i]["gwdm"];
}else{
	sqbh +=rows[i]["sqid"]+",";
	gwdm +=rows[i]["gwid"]+",";
}
}
var url = url + "&sqid=" +sqid+"&gwdm="+gwdm;
window.open(url);
}

}

/**
 * 切换tab页
 * 
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	if (shzt == "wsq"){
		jQuery('#li_ts :first').text(jQuery('#curXn').val());
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","");
		jQuery("#li_sc").css("display","");
		jQuery("#li_tj").css("display","");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lc").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting1);
	} else {
		jQuery('#li_ts :first').text('');
		jQuery("#li_sq").css("display","none");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","");
		jQuery("#li_lc").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}



function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var syme=jQuery(this).find("td[name=syme]").text();
		syme=jQuery.trim(syme);
		if(parseInt(syme,10)<1){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}

