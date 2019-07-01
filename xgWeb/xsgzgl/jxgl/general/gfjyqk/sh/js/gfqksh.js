var gridSetting = {
			caption:"国防教育情况列表",
			pager:"pager",
			url:"gfjyqk_sh.do?method=gfjyShList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'gfqkfl',name:'gfqkfl', index: 'gfqkfl',hidden:true},
			   {label:'国防情况分类',name:'gfqkflmc', index: 'gfqkflmc',width:'8%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
			   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
			   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
			   {label:'审批流程',name:'splc',index:'splc',hidden:true}
			],
			params:{shzt:"dsh"},//默认待审核
			sortname: "sqsj",
		 	sortorder: "desc",
		 	radioselect:false
}
var gridSetting2 = {
		caption:"国防教育情况列表",
		pager:"pager",
		url:"gfjyqk_sh.do?method=gfjyShList&type=query",
		colList:[
		   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
		   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
		   {label:'学年',name:'xn', index: 'xn',width:'8%'},
		   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
		   {label:'gfqkfl',name:'gfqkfl', index: 'gfqkfl',hidden:true},
		   {label:'国防情况分类',name:'gfqkflmc', index: 'gfqkflmc',width:'8%'},
		   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
		   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
		   {label:'审核Id',name:'shid', index: 'shid',hidden:true},
		   {label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
		   {label:'审批流程',name:'splc',index:'splc',hidden:true}
		],
		params:{shzt:"ysh"},//默认待审核
		sortname: "sqsj",
	 	sortorder: "desc",
	 	radioselect:true
}
	
function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	

//点击学号查看
function viewGfjyqk(sqid, xh) {
	showDialog("查看", 700, 480, "gfjyqk_sh.do?method=view&sqid=" + sqid+ "&xh=" + xh);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewGfjyqk(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//审核
function Sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if(shzt=="ysh"){
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	}else if (rows.length == 0){
		showAlertDivLayer("请选择您要审核的记录");
		return false;
	}else if (rows.length == 1){
		var url = "gfjyqk_sh.do?method=gfqkDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("审核",700,500,url);
	} else{
		showDialog("批量审核",500,300,"gfjyqk_sh.do?method=gfqkPlsh");
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
					jQuery.post("gfjyqk_sh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		"gfjyqk_sh.do?method=gfqkPlsh&type=save",
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
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	var xxdm = jQuery("#xxdm").val();
	if (shzt == "dsh"){
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
		showDialog("国防教育情况审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}



var DCCLBH = "gfjy_gfjyqk_sh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var url = "gfjyqk_sh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}