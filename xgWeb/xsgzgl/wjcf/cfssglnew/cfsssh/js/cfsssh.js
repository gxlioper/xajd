var gridSetting = {
		caption:"处分申诉审核列表",
		pager:"pager",
		url:"wjcf_cfsssh.do?method=cxCfssshList&type=query",
		colList:[
				   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
				   {label:'姓名',name:'xm', index: 'xm'},
            		{label:'专业班级',name:'zybjmc', index: 'zybjmc'},
            		{label:'书院',name:'symc', index: 'symc'},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xqmc'},
				   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
				   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
				   {label:'审核状态',name:'shzt', index: 'shzt'},
				   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true}
		],
		params:{shlx:"dsh"}
}

var gridSetting2 = {
		caption:"处分申诉审核列表",
		pager:"pager",
		url:"wjcf_cfsssh.do?method=cxCfssshList&type=query",
		colList:[
				   {label:'学号',name:'xh', index: 'xh'},//,formatter:xhLink
				   {label:'姓名',name:'xm', index: 'xm'},
            		{label:'专业班级',name:'zybjmc', index: 'zybjmc'},
            		{label:'书院',name:'symc', index: 'symc'},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xqmc'},
				   {label:'处分类别',name:'cflbmc', index: 'cflbmc'},
				   {label:'处分原因',name:'cfyymc', index: 'cfyymc'},
				   {label:'审核状态',name:'shzt', index: 'shzt'},
            	   {label:'审核时间',name:'shsj', index: 'shsj'},
				   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true}
		],
		params:{shlx:"ysh"}
}

////批量审核，由于在最后一步审核的时候需填写处分文号和时间，这里暂时取消
//function go_sh(){
//	var rows = jQuery("#dataTable").getSeletRow();
//	if (rows.length == 1){
//		showDialog("处分申诉审核",760,500,'wjcf_cfsssh.do?method=sssh&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]+"&cfid="+rows[0]["cfid"]);
//	}  else{
//		showDialog("处分申诉批量审核",500,300,"wjcf_cfsssh.do?method=ssplsh");
//	}
//}

//审核
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要审核的记录！");
	}  else{
		showDialog("处分申诉审核",820,500,'wjcf_cfsssh.do?method=sssh&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]+"&cfid="+rows[0]["cfid"]);
	}
}

//审批流查看
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ywid']+"&splc="+rows[0]['splcid']);
	}
}


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
/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	//当最后一级审核通过时，判段是否填写申诉文号及申诉时间
	if((shzt=="1"||shzt==1)){
		if(jQuery("#isZhgw").val()=="true"){
			if(jQuery("#sswh").val()==""){
				showAlertDivLayer("请填写申诉文号！");
				return false;
			}
			if(jQuery("#sssj").val()==""){
				showAlertDivLayer("请填写申诉时间！");
				return false;
			}
			if(jQuery("#cfggw").val()==""){
				showAlertDivLayer("请选择更改处分类别！");
				return false;
			}
		}
	}
	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splcid").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	//提交审核
	showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
		var url = "wjcf_cfsssh.do?method=sssh&type=save";
		ajaxSubFormWithFun("cfssshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
}


/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["ywid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"wjcf_cfsssh.do?method=ssplsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}
