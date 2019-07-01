

jQuery(function(){
	var  gridSetting = {
			caption:"证件补办申请列表",
			pager:"pager",
			url:"rcsw_xszbb_bbsqgl.do?method=xszbbsqManage&type=query",
			colList:[
			   {label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'5%'},
			   {label:'年级',name:'nj', index: 'nj',width:'5%'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'13%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
			   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'10%'},
			   {label:'补办证件',name:'xszbblxmc', index: 'xszbblxmc',width:'8%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
			   {label:'补办证件代码',name:'xszbblxdm', index: 'xszbblxdm',hidden:true}
			],
			sortname: "sqsj",
		 	sortorder: "desc"
		}
	jQuery("#dataTable").initGrid(gridSetting);
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_null_isopen").show();
		return false;
	}
	if ("false" == isopen){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_false_isopen").show();
		return false;
	}
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
	var title = "申请补办";
	showDialog(title,790,525,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		
		var url = 'rcsw_xszbb_bbsqgl.do?method=updateXszbbsq&bbsqid=' + rows[0]["bbsqid"]
		+ '&xh=' + rows[0]["xh"] +'&xszbblxdm=' + rows[0]["xszbblxdm"];
		var title = "修改申请补办";
		showDialog(title, 720, 525, url);
	}

}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_xszbb_bbsqgl.do?method=delXszbbsq", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的申请已经提交不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function viewXszbbsq(bbsqid, xh) {
	showDialog("证件补办申请查询", 720, 520, "rcsw_xszbb_bbsqgl.do?method=viewXszbbsq&bbsqid=" + bbsqid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXszbbsq(\""
			+ rowObject["bbsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_xszbb_bbsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// 导出方法
function rcxwxxwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_xszbb_bbsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

