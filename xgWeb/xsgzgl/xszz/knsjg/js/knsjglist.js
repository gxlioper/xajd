var DCCLBH = "xszz_knsjg_cx.do";//dcclbh,导出功能编号
var sfyc=true;
function initGridSetting(){
	var xbmc=jQuery("#xbmc").val();
	if("10335"==jQuery("#xxdm").val()){
		sfyc=false;
		}
var gridSetting = {};
if("xq"==jQuery("#sqzq").val()){
	if(jQuery("#xxdm").val() != '13871'){
		gridSetting  = {
				caption : "困难生结果列表",
				pager : "pager",
				url : "xszz_knsjg.do?method=getKnsjgList&type=query",
				colList : [ {
					label : 'guid',
					name : 'guid',
					index : 'guid',
					width : '10%',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '15%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '15%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '10%'
				},{
					label : xbmc,
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				},  {
                    label : '书院',
                    name : 'symc',
                    index:'symc',
                    width : '10%'
                },{
                    label : '行政班级',
                    name : 'bjmc',
                    index : 'bjdm',
                    width : '20%'
                },{
                    label : '专业班级',
                    name : 'zybjmc',
                    index : 'zybjmc',
                    width : '20%'
                },
				{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : '学期',
					name : 'xqmc',
					index : 'xqmc',
					width : '10%'
				}, {
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : '认定档次',
					name : 'dcmc',
					index : 'dcmc',
					width : '10%'
				}
//				,{label:'无偿资助金额',name:'ylzd3', index: 'ylzd3',width:'11%',hidden:sfyc}
				

				],
				sortname : "sqsj",
				sortorder : "desc"
			}
	}else{
		gridSetting  = {
				caption : "困难生结果列表",
				pager : "pager",
				url : "xszz_knsjg.do?method=getKnsjgList&type=query",
				colList : [ {
					label : 'guid',
					name : 'guid',
					index : 'guid',
					width : '10%',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '15%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '15%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '10%'
				},{
					label : xbmc,
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				}, {
                    label : '书院',
                    name : 'symc',
					index:'symc',
                    width : '10%'
				},{
					label : '行政班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				},{
                    label : '专业班级',
                    name : 'zybjmc',
                    index : 'zybjmc',
                    width : '20%'
				},{
					label : '困难排序',
					name : 'knpx',
					index : 'knpx',
					width : '10%'
				},
				{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : '学期',
					name : 'xqmc',
					index : 'xqmc',
					width : '10%'
				}, {
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : '认定档次',
					name : 'dcmc',
					index : 'dcmc',
					width : '10%'
				}
//				,{label:'无偿资助金额',name:'ylzd3', index: 'ylzd3',width:'11%',hidden:sfyc}
				

				]
				
			}
	}
	
}
else{
	if(jQuery("#xxdm").val() != '13871'){
	gridSetting  = {
			caption : "困难生结果列表",
			pager : "pager",
			url : "xszz_knsjg.do?method=getKnsjgList&type=query",
			colList : [ {
				label : 'guid',
				name : 'guid',
				index : 'guid',
				width : '10%',
				key : true,
				hidden : true
			}, {
				label : '学号',
				name : 'xh',
				index : 'xh',
				width : '15%',
				formatter : xhLink
			}, {
				label : '姓名',
				name : 'xm',
				index : 'xm',
				width : '15%'
			}, {
				label : '性别',
				name : 'xb',
				index : 'xb',
				width : '10%'
			}, {
				label : xbmc,
				name : 'xymc',
				index : 'xydm',
				width : '20%'
			},{
                label : '书院',
                name : 'symc',
                index:'symc',
                width : '10%'
            },{
                label : '行政班级',
                name : 'bjmc',
                index : 'bjdm',
                width : '20%'
            },{
                label : '专业班级',
                name : 'zybjmc',
                index : 'zybjmc',
                width : '20%'
            },{
				label : '学年',
				name : 'xn',
				index : 'xn',
				width : '10%'
			}, {
				label : 'sjly',
				name : 'sjly',
				index : 'sjly',
				hidden : true
			},{
				label : '认定档次',
				name : 'dcmc',
				index : 'dcmc',
				width : '10%'
			}
//			 ,{label:'无偿资助金额',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc}

			],
			sortname : "sqsj",
			sortorder : "desc"
		}
	}else{
		gridSetting  = {
		caption : "困难生结果列表",
		pager : "pager",
		url : "xszz_knsjg.do?method=getKnsjgList&type=query",
		colList : [ {
			label : 'guid',
			name : 'guid',
			index : 'guid',
			width : '10%',
			key : true,
			hidden : true
		}, {
			label : '学号',
			name : 'xh',
			index : 'xh',
			width : '15%',
			formatter : xhLink
		}, {
			label : '姓名',
			name : 'xm',
			index : 'xm',
			width : '15%'
		}, {
			label : '性别',
			name : 'xb',
			index : 'xb',
			width : '10%'
		}, {
			label : xbmc,
			name : 'xymc',
			index : 'xydm',
			width : '20%'
		},{
            label : '书院',
            name : 'symc',
            index:'symc',
            width : '10%'
        },{
            label : '行政班级',
            name : 'bjmc',
            index : 'bjdm',
            width : '20%'
        },{
            label : '专业班级',
            name : 'zybjmc',
            index : 'zybjmc',
            width : '20%'
        },{
			label : '困难排序',
			name : 'knpx',
			index : 'knpx',
			width : '10%'
		},{
			label : '学年',
			name : 'xn',
			index : 'xn',
			width : '10%'
		}, {
			label : 'sjly',
			name : 'sjly',
			index : 'sjly',
			hidden : true
		},{
			label : '认定档次',
			name : 'dcmc',
			index : 'dcmc',
			width : '10%'
		}
//		 ,{label:'无偿资助金额',name:'wczzje', index: 'wczzje',width:'11%',hidden:sfyc}

		]
	}
	}
	
}
return gridSetting;
}

/**
 * 困难生单个结果调查--查看
 * 
 * @param xh
 * @return
 */
function knsView(guid, xh) {
	showDialog("困难生查询", 700, 435, "xszz_knsjg.do?method=knsjgView&guid=" + guid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='knsView(\""
			+ rowObject["guid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function add() {
	var url = "xszz_knsjg.do?method=addKnsjg";
	var title = "增加困难生认定信息";
	showDialog(title, 720, 480, url);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("您选择的记录为流程数据，不能修改!");
			return false;
		}
		
		var url = 'xszz_knsjg.do?method=updateKnsjg&guid=' + rows[0]["guid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "修改困难生认定信息";
		showDialog(title, 720, 480, url);
	}

}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("所选的记录中包含流程数据，不能删除，请重新选择！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsjg.do?method=delKnsjg", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// 打印报表
function printKnssq(url) {

	/*var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要打印的记录！");
	} else {

		var url = url + "&guid=" + rows[0]["guid"] + "&xh=" + rows[0]["xh"]
		window.open(url);
	}*/
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else if (rows.length ==1){
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		//var url = url + "&guid=" +guid;
		//window.open(url);
		post(url, {guid:guid});
	}
}

function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)         
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
} 

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("不存在其他学年学期的困难生，不能复制。",{},{"clkFun":function(){
		}});
		return false;
	}

	var url ="xszz_knsjg.do?method=knsjgcopy";
	showDialog("困难生认定结果复制", 400, 200, url);
}
function savecopy(){
	var lyxnxq=jQuery("#lyxnxq").val();
	var lyxn=lyxnxq.split(",")[0];
	var lyxq=lyxnxq.split(",")[1];
	
	var xnxq=jQuery("#xnxq").val();
	var xn=xnxq.split(",")[0];
	var xq=xnxq.split(",")[1];
	
	jQuery("#form").ajaxSubmit({
		url:"xszz_knsjg.do?method=savecopy",
		type:"post",
		dataType:"json",
		data:{
			lyxn:lyxn,
			lyxq:lyxq,
			mbxn:xn,
			mbxq:xq},
		success:function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});	
		}
	});
	
//	jQuery.post("xszz_knsjg.do?method=savecopy", {
//		lyxn:lyxn,
//		lyxq:lyxq,
//		xn:xn,
//		xq:xq
//	}, function(data) {
//		showAlert(data["message"],{},{"clkFun":function(){
//			if (parent.window){
//				refershParent();
//			}
//		}});
//	}, 'json');
}
//贵州大学困难生统计导出
function knsrdTjExport(){
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsjg.do?method=knsrdTjExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function knsExport(){
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsjg.do?method=knsExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
/**
 * 江西陶瓷个性化
 * @return
 */
function rdhzbExport(){
	setSearchTj();//设置高级查询条件
	var url = "xszz_knsjg.do?method=rdhzbExport"
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function exportKnsjg(type){
	var xbmc=jQuery("#xbmc").val();
	var xn_num =  jQuery("a[name=a_name_xn]").length;
	var xy_num =  jQuery("a[name=a_name_xy]").length;
	var url = "xszz_knsjg.do?method=ExportKnsjg"
	if(xn_num!="1"){
		showAlertDivLayer("请选择一个学年！");
		return false;
	}
	if(type=="dashk"){
		url=url+"&expType=dashk";
	}
	if(type=="rdpxb"){
		url=url+"&expType=rdpxb";
		/*if(xn_xy!="0"){
			showAlertDivLayer("该导出至少选择1个"+xbmc);
			return false;
		}*/
	}
	setSearchTj();//设置高级查询条件
	
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}



/* 	浙大个性化 ！
	显示困难生结果困难、特别困难学生的各学院分布情况和比例情况按钮
	2016年10月17日     开发人员：孟威
*/
function knstksPercent(){
	showDialog("各院系困难、特困学生人数",750,400,"xszz_knsjg.do?method=knstksPercent");
}
