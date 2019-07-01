function searchRs(){
	var map = getSuperSearch();
	var shlx = jQuery("#shlx").val();
	if (shlx != ""){
		map["shlx"] = shlx;
	}
	jQuery("#dataTable").reloadGrid(map);
}
	
function selectTab(obj,shlx){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	if (shlx=="dsh"){
		var dclGrid = getDclGird();
		var map = getSuperSearch();
			map["shlx"] = "dsh"; 
		dclGrid["params"] = map;
		jQuery("#dataTable").initGrid(dclGrid);
		
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
		jQuery("#shlx").val("dsh");
	} else {
		var yclGrid = getYclGrid();
		var map = getSuperSearch();
			map["shlx"] = "ysh"; 
		yclGrid["params"] = map;
		jQuery("#dataTable").initGrid(yclGrid);
		
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
		jQuery("#shlx").val("ysh");
	}
}

//宿舍异动审核
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		alertInfo("已处理的记录不能再次审核！");
	}else if (rows.length == 0){
		alertInfo("请选择一条您要审核的记录！");
		return false;
	} else if (rows.length ==1){
		showDialog("宿舍异动审核",760,490,'ydsh.do?method=ydsh&ssydsqid='+rows[0]["ssydsqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	} else {
		for(var i=0;i<rows.length;i++){
			var sqshHideCwxx = rows[i]['sqshHideCwxx'];
			if(rows[i]['ssydlx']=='01' && (sqshHideCwxx=='' || sqshHideCwxx=="null_null_null")){
				showAlertDivLayer("有宿舍调整的数据没有选择床位，不能批量审核！");
				return false;
			}
			if(i!=0 && rows[i]["ssydlx"]!=rows[i-1]["ssydlx"]){
				showAlertDivLayer("请选择相同异动类型的数据！");
				return false;
			}
		}
		showDialog("宿舍异动批量审核",500,300,"ydsh.do?method=ydPlsh&ssydlx="+rows[0]["ssydlx"]);
	}
}

function savePlsh(shzt,shyj,sfcwcsh,jflx,zsfje){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splc = new Array();
	var ssydlx = new Array();
	var sqshHideCwxx = new Array();
	var thgw = "";
	if(shzt == '3'){
		thgw = "-1";
	}
	jQuery.each(rows,function(i,row){
		guid.push(row["ssydsqid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splc.push(row["splcid"]);
		ssydlx.push(row["ssydlx"]);
		sqshHideCwxx.push(row["sqshHideCwxx"]);
	});

	jQuery.post(
		"ydsh.do?method=ydPlsh&type=save",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 splcs:splc,
		 ssydlxs:ssydlx,
		 sqshHideCwxxs:sqshHideCwxx,
		 sfcwcsh:sfcwcsh,
		 jflx:jflx,
		 zsfje:zsfje,
		 thgw:thgw
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
		showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssydsqid']+"&splc="+rows[0]['splcid']);
	}
}



/**
 * 保存审核操作
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	
	var zsfje = jQuery.trim(jQuery("#zsfje").val());
	var jflx = jQuery("#jflx").val();
	var shjg = jQuery("#shjg").val();
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}
	if(jQuery("#xxdm").val() != "13871"){
		if (shjg == "1" && ((jflx == "" && zsfje != "") || (jflx != "" && zsfje == ""))) {
			showAlertDivLayer("请将住宿费信息填写完整！");
			return false;
		}
	}
	
	
	var ssydlx = jQuery("#ssydlx").val();
	if(shjg == "1" && ssydlx == "01" && jQuery("#xxdm").val() != "10351"&& jQuery("#xxdm").val() != "12872"  && jQuery("#xxdm").val() != "10344"  && (jQuery("#cwxx").val() == "" || jQuery("#cwxx").val() == "null_null_null")){
		showAlertDivLayer("请选择床位！");
		return false;
	}
	if(ssydlx == "01" && jQuery("#xxdm").val() == "12861"){
		if (jQuery("#sstz_checkbox") && !jQuery("#sstz_checkbox").attr('checked')){
			showAlertDivLayer("请确认宿舍调整双方本人是否已同意！如已确认，请勾选【双方本人已同意】！");
			return false;
		}
	}
	//提交审核
	showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
		jQuery("#btqd").attr("disabled","disabled");
		var url = "ydsh.do?method=ydsh&type=save";
		jQuery("#demoForm").ajaxSubmit({
			url:url,
			type:"post",
			dataType:"json",
			success:function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					refershParent();
				}});
			},
			contentType:"application/x-www-form-urlencoded;charset=utf-8"
		});	
		
	}});
}


function exportConfig() {
	customExport("ydshbase.do", exportData);
}
// 导出方法
function exportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	
	var url = "ydshbase.do?method=exportData&dcclbh='ydshbase.do'"+ "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//选择床位（调整 审核信息）
function selectCw(){
	var xh = jQuery("#xh").val();
	showDialog('请选择一个床位',800,500,'ydsq.do?method=selectCwxx&goto=path&xh='+xh);
}
//选择床位（入住 审核信息）
function selectRzcw(){
	var xh = jQuery("#xh").val();
	showDialog('请选择一个床位',800,500,'ydsq.do?method=selectRzcwxx&goto=path&xh='+xh);
}
//显示选中床位信息（调整 审核信息）
function showCwxx(cwxx){
	var cwxxSetting = {
			caption:"已选择床位信息",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
			colList:[
			   {label:'床位信息id',name:'cwxx', index: 'cwxx',key:true,hidden:true},
			   {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
			   {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
			   {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
			   {label:'床位性别',name:'qsxb', index: 'qsxb'},
			   {label:'所属年级',name:'nj', index: 'nj'},
			   {label:'所属'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'学号',name:'xh', index: 'xh'},
			   {label:'姓名',name:'xm', index: 'xm'}
			],
			sortname: "sfrz",
		 	sortorder: "desc"
		}
 	jQuery("#cwxxTable").initGrid(cwxxSetting);
	jQuery("#yxzcwxx").show();
	jQuery("#cwxx").val(cwxx);
}
//显示选中床位信息（入住 审核信息）
function showRzcwxx(rzcwxx){
	var rzcwxxSetting = {
			caption:"已选择床位信息",
			multiselect:false,
			rowNum:1,
			url:"ydsq.do?method=selectRzcwxx&type=query&rzcwxx="+rzcwxx,
			colList:[
			         {label:'床位信息id',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
			         {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
			         {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'床位性别',name:'qsxb', index: 'qsxb'},
			         {label:'所属年级',name:'nj', index: 'nj'},
			         {label:'所属'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
			         //{label:'学号',name:'xh', index: 'xh'},
			         //{label:'姓名',name:'xm', index: 'xm'}
			         ],
			         sortname: "sfrz",
			         sortorder: "desc"
	}
	jQuery("#rzcwxxTable").initGrid(rzcwxxSetting);
	jQuery("#yxzrzcwxx").show();
	jQuery("#rzcwxx").val(rzcwxx);
}

