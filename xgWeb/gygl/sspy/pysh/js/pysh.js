var gridSetting = {
			caption:"宿舍评优审核列表",
			pager:"pager",
			url:"sspysh.do?method=pyShList&type=query",
			colList:[
			   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'申请人学号',name:'xh', index: 'xh',width:'8%'},
			   {label:'申请人姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'8%'},
			   {label:'寝室号',name:'qsh', index: 'qsh',width:'8%'},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'评优项目',name:'pyxmmc', index: 'pyxmmc',width:'8%'},
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
		caption:"宿舍评优审核列表",
		pager:"pager",
		url:"sspysh.do?method=pyShList&type=query",
		colList:[
				   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
				   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
				   {label:'申请人学号',name:'xh', index: 'xh',width:'8%'},
				   {label:'申请人姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'8%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'8%'},
				   {label:'学年',name:'xn', index: 'xn',width:'8%'},
				   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
				   {label:'评优项目',name:'pyxmmc', index: 'pyxmmc',width:'8%'},
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
	
function getSscyxx(){
	jQuery("#xsList").empty();
	var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
	var html = "";
	jQuery.post("sspysq.do?method=getCwxx", {
		ldqsxx : ldqsxx
	}, function(data) {
		for(var i =0;i<data.length;i++){
			html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
		}
		jQuery("#xsList").append(html);
	}, 'json');
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
		var url = "sspysh.do?method=sspyDgsh&sqid="+rows[0]["sqid"]+'&xh=' + rows[0]["xh"]+'&shid=' +rows[0]["shid"] ;
		showDialog("审核",700,500,url);
	} else{
		showDialog("批量审核",500,300,"sspysh.do?method=sspyPlsh");
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
					jQuery.post("sspysh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
		"sspysh.do?method=sspyPlsh&type=save",
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



//流程跟踪
function shLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {		
		showDialog("宿舍评优审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "sspy_sh.do";//dcclbh,导出功能编号
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	var shlx = jQuery("#shlx").val();
	setSearchTj();//设置高级查询条件
	var url = "sspysh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}