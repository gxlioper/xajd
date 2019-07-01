//初始化查询
var gridSetting = {
		caption:"辅导员任职审核列表",
		pager:"pager",
		url:"szdw_fdypxxmsh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'职工号',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'20%'},
		   {label:'培训项目',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'20%'},
		   {label:'主键',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'主键',name:'sqid', index: 'sqid',key:true,hidden:true},
		   {label:'gwid',name:'gwid', index:'gwid',hidden:true}
		],
		params:{shlx:"dsh"},
		sortname: "sqsj",
	 	sortorder: "asc",
	 	radioselect:false
}
var gridSetting2 = {
		caption:"辅导员任职审核列表",
		pager:"pager",
		url:"szdw_fdypxxmsh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'职工号',name:'sqr', index: 'sqr',width:'10%',formatter:zghLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		   {label:'部门名称',name:'bmmc', index: 'bmmc',width:'20%'},
		   {label:'审核时间',name:'shsj', index: 'shsj',width:'20%'},
		   {label:'申请培训项目',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',width:'20%'},
		   {label:'主键',name:'shid', index: 'shid',hidden:true},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'主键',name:'sqid', index: 'sqid',key:true,hidden:true}
		],
		params:{shlx:"ysh"},
		sortname: "shsj",
	 	sortorder: "desc",
	 	radioselect:true
}


function zghLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='viewjgz(\""+rowObject["sqr"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function viewjgz(zgh){
	var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
	showDialog('', 830, 500, url);
}

function knsView(id,xh){
	showDialog("教职工信息",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}
function zghView(){
	
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
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		alertInfo("已处理的记录不能再次审核！");
	}else if (rows.length == 0){
		alertInfo("请选择您要审核的记录！");
		return false;
	} else if (rows.length == 1){
		showDialog("辅导员培训审核",760,500,'szdw_fdypxxmsh.do?method=fdypxxmsh&sqid='+rows[0]["sqid"]+"&shid="+rows[0]["shid"]+"&tt="+new Date().getTime());
	} else{
		showDialog("辅导员培训批量审核",500,300,"szdw_fdypxxmsh.do?method=fdypxxmPlsh");
	}
}

/**
 * 批量保存审核
 * @param shzt
 * @param shyj
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs  = new Array();
	var splc = new Array();
	
	jQuery.each(rows, function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["sqr"]);
		splc.push(row["splc"]);
		
	});
	
	jQuery.post(
			"szdw_fdypxxmsh.do?method=fdypxxmPlsh&type=save",
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
function save_sh_bak(){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "通过";
	}
	if(jQuery("#shjg").val() == "2"){
		message = "不通过";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "退回";
	}
	//提交审核
	showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
		var url = "szdw_fdypxxmsh.do?method=fdypxxmsh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}