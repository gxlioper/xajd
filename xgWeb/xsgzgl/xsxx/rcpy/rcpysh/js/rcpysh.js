var gridSetting = {
			caption:"人才培养待审核列表",
			pager:"pager",
			url:"rcpy_rcpysh.do?method=getshList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'培养项目',name:'xmmc', index: 'xmmc',width:'8%'},
			   {label:'培养类别',name:'pylbmc', index: 'pylbmc',width:'8%'},
			   {label:'考核指标',name:'khzbmc', index: 'khzbmc',width:'8%'},
			   {label:'限制条件',name:'xztjmc', index: 'xztjmc',width:'8%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
			   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
			   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
			   {label:'审批流程',name:'splc',index:'splc',hidden:true}
			],
			params:{shlx:"dsh"},//默认待审核
			sortname: "sqsj",
		 	sortorder: "desc",
		 	radioselect:false
}
var gridSetting2 = {
		caption:"人才培养已审核列表",
		pager:"pager",
		url:"rcpy_rcpysh.do?method=getshList&type=query",
		colList:[
		   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
		   {label:'培养项目',name:'xmmc', index: 'xmmc',width:'8%'},
		   {label:'培养类别',name:'pylbmc', index: 'pylbmc',width:'8%'},
		   {label:'考核指标',name:'khzbmc', index: 'khzbmc',width:'8%'},
		   {label:'限制条件',name:'xztjmc', index: 'xztjmc',width:'8%'},
		   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
		   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
		   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
		   {label:'审批流程',name:'splc',index:'splc',hidden:true}
		],
		params:{shlx:"ysh"},//默认待审核
		sortname: "sqsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var shlx = jQuery("#shlx").val();
	if (shlx != ""){
		map["shlx"] = shlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	


function viewRcpy(sqid,xh) {
	showDialog("查看", 720, 520, "rcpy_rcpysq.do?method=ckRcpysq&sqid=" + sqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewRcpy(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//审核
function Sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("请选择您要审核的记录");
		return false;
	}else if (rows.length == 1){
		var url = "rcpy_rcpysh.do?method=rcpyDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("审核",700,500,url);
	} else{
		showDialog("批量审核",500,300,"rcpy_rcpysh.do?method=rcpyPlsh");
	} 
}

//撤销审核
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// 判断是否最后一级撤销(1:最后一级撤销成功）
				if("1" == data["cancelFlg"]){
					jQuery.post("rcpy_rcpysh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
						showAlertDivLayer(result["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},'json');
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			
		},'json');
		}});
	}
}

//批量审核
function savePlsh(shzt,shyj){
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splc"]);
	});

	jQuery.post(
		"rcpy_rcpysh.do?method=rcpyPlsh&type=save",
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


//切换lab
function selectTab(obj,shlx){
	jQuery("#shlx").val(shlx);
	if (shlx == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
			jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}




function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("人才培养审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}



var DCCLBH = "xsxx_rcpy_rcpysh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	var shlx = jQuery("#shlx").val();
	setSearchTj();//设置高级查询条件
	var url = "rcpy_rcpysh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}