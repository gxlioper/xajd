jQuery(function() {
	var gridSetting = {
		caption : "物品申请列表",
		pager : "pager",
		url : "axcswpjg.do?method=wpjgList&type=query",
		colList : [ {
			label : 'key',
			name : 'jgid',
			index : 'jgid',
			key : true,
			hidden : true
		},{
			label : 'xmdm',
			name : 'xmdm',
			index : 'xmdm',
			hidden : true
		},{
			label : 'sqid',
			name : 'sqid',
			index : 'sqid',
			hidden : true
		},{
			label : '学号',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter:xhLink
		},{
			label : '学年',
			name : 'xn',
			index : 'xn',
			width : '12%'
		},{
			label : '姓名',
			name : 'xm',
			index : 'xm',
			width : '8%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '20%'
		}, {
			label : '班级',
			name : 'bjmc',
			index : 'bjdm',
			width : '20%'
		}, {
			label : '申请时间',
			name : 'sqsj',
			index : 'sqsj',
			width : '18%'
		}, {
			label : '物品名称',
			name : 'xmmc',
			index : 'xmmc',
			width : '10%'
		}, {
			label : '物品类别',
			name : 'xmlbmc',
			index : 'xmlbmc',
			width : '8%'
		}],
		sortname : "sqsj",
		sortorder : "desc"
	}
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




function add(){
	var url = "axcswpjg.do?method=wpjgZj";
	var title = "增加结果信息";
	showDialog(title,680,550,url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(sjly == '1'){
		showAlertDivLayer("流程数据不能修改！");
	}else {
		var url = 'axcswpjg.do?method=wpjgXg&jgid='+ rows[0]["jgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改结果信息";
		showDialog(title, 680,550, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpjg.do?method=delWpjg", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="流程数据不能修改！";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function wpjgView(jgid, xh) {
	showDialog("结果查询", 680,400, "axcswpjg.do?method=wpjgCk&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='wpjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "axcs_axcswpjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, wpjgExportData);
}

// 导出方法
function wpjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "axcswpjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function getWpxx(obj){
	var url = "axcs_wpsz_ajax.do?method=getWpxx";
	jQuery.post(url, {
		xmdm:obj.value
	}, function(data) {
		dataObj = data;
		var xmtpDiv="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+obj.value+"&type=view'>";
			jQuery("#xmtp").empty().append(xmtpDiv);
			jQuery("#xmlbid").empty().append(dataObj.xmlbmc);
		}, 'json');
}

function getWpxxByXmdm(xmdm){
	var url = "axcs_wpsz_ajax.do?method=getWpxx";
	jQuery.post(url, {
		xmdm:xmdm
	}, function(data) {
		dataObj = data;
		var xmtpDiv="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+xmdm+"&type=view'>";
			jQuery("#xmtp").empty().append(xmtpDiv);
			jQuery("#xmlbid").empty().append(dataObj.xmlbmc);
		}, 'json');
}