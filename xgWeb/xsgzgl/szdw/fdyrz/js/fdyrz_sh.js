//初始化查询
var gridSetting = {
		caption:"辅导员任职审核列表",
		pager:"pager",
		url:"szdw_fdyrz_sh.do?method=gjcxRzsh&type=query",
		colList:[
		   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},//,formatter:xhLink
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'专兼职',name:'zjz', index: 'zjz',width:'10%'},
		   {label:'带班个数',name:'sqdbgs', index: 'sqdbgs',width:'15%'},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'主键',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'主键',name:'sqid', index: 'sqid',key:true,hidden:true},
		   {label:'gwid',name:'gwid', index:'gwid',hidden:true}
		],
		params:{shlx:"dsh"},
		sortname: "sqsj",
	 	sortorder: "desc",
	 	radioselect:false
}
var gridSetting2 = {
		caption:"辅导员任职审核列表",
		pager:"pager",
		url:"szdw_fdyrz_sh.do?method=gjcxRzsh&type=query",
		colList:[
		   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'职工号',name:'zgh', index: 'zgh',width:'10%'},//,formatter:xhLink
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'专兼职',name:'zjz', index: 'zjz',width:'10%'},
		   {label:'带班个数',name:'sqdbgs', index: 'sqdbgs',width:'7%'},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'申请时间',name:'shsj', index: 'shsj',width:'18%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'主键',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'主键',name:'sqid', index: 'sqid',key:true,hidden:true}
		],
		params:{shlx:"ysh"},
		sortname: "shsj",
	 	sortorder: "desc",
	 	radioselect: true
}
//
function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
	}else{
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
	}
	searchRs();
}

//查询
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
function fdyrz_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		alertInfo("请选择您要审核的记录！");
		return false;
	} else if(rows.length == 1) {
		showDialog("辅导员任职审核",760,510,'szdw_fdyrz_sh.do?method=fdyrzsh&sqid='+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&tt="+new Date().getTime());
	} else{
		showDialog("辅导员任职批量审核",500,300,"szdw_fdyrz_sh.do?method=fdyrzPlsh");
	}
}


function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs  = new Array();
	var splc = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["zgh"]);
		splc.push(row["splc"]);
		
	});
	
	jQuery.post(
			"szdw_fdyrz_sh.do?method=fdyrzPlsh&type=save",
			{
			 shzt:shzt,
			 id:guid,
			 gwids:gwid,
			 xhs:xhs,
			 shyj:shyj,
			 splcs:splc
			 
			},function(data){
				
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
			},
			'json'
	);
	
}

function fydrz_sh_save(){
	var shzt = jQuery("#shzt").val();
	var shyj = jQuery("#shyj").val();
	var sqid = jQuery("#sqid").val();
	var shid = jQuery("#shid").val();
	jQuery.ajaxSetup({
		 contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});
	jQuery.post("szdw_fdyrz_sh.do?method=fdyrzsh", { shzt:shzt,shyj:shyj,type:"save",sqid:sqid ,shid:shid},'json',function(data){
		
		alertInfo(data["message"]);
	} );
}
function btn_close(){
	window.parent.ymPrompt.close();
}
//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条记录！");
	} else {
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showConfirmDivLayer("请填写审核意见！",{"okFun":function(){}});
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showConfirmDivLayer("审核意见不能超过200字",{"okFun":function(){}});
		return false;
	}
	if (jQuery("#splc").val() == "" || jQuery("#sqbh").val() == ""||  jQuery("#gwid").val() == ""){
		showConfirmDivLayer("系统异常请稍后",{"okFun":function(){}});
		return false;
	}
	//如果
	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splc").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	
	//提交审核
	showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
		var url = "szdw_fdyrz_sh.do?method=fdyrzsh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}


