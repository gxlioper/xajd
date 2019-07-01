var tjszDialog;
var gridSetting = {
	caption : "项目列表",
	pager : "pager",
	url : "axcsWpsz.do?method=wpszList&type=query",
	colList : [ {
		label : '项目代码',
		name : 'xmdm',
		index : 'xmdm',
		key : true,
		hidden : true
	}, {
		label : 'xxxxjs',
		name : 'xmxxjs',
		index : 'xmxxjs',
		hidden : true
	},{
		label : '专项物品名称',
		name : 'xmmc',
		index : 'xmmc',
		width : '12%',
		formatter:xmdmLink
	},{
		label : '学年',
		name : 'xn',
		index : 'xn',
		width : '12%'
	
	},{
		label : '类别',
		name : 'lbmc',
		index : 'lbmc',
		width : '12%'
	}, {
		label : '图片',
		name : 'xmtp',
		index : 'xmtp',
		width : '12%',
		formatter : setTp
	}, {
		label : '详细介绍',
		name : 'xmjs',
		index : 'xmjs',
		width : '24%',
		formatter : setXxjs
	}, {
		label : '基本设置',
		name : 'jbsz',
		index : 'jbsz',
		width : '14%',
		formatter : setJbsz
	}, {
		label : '条件设置',
		name : 'tjsz',
		index : 'tjsz',
		width : '14%',
		formatter : setTjsz
	},
	{label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',width : '8%',hidden : true},
	{label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',width : '8%',hidden : true},
	{label : '审核开始时间',name : 'shkssj',index : 'shkssj',width : '8%',hidden : true},
	{label : '申请开关',name : 'sqkg',index : 'sqkg',width : '8%',hidden : true},
	{label : '审核开关',name : 'shkg',index : 'shkg',width : '8%',hidden : true},
	{label : '审核结束时间',name : 'shjssj',index : 'shjssj',width : '8%',hidden : true}
	],
	sortname : "xmdm",
	sortorder : "asc"
}

jQuery(function() {
	gridSetting["params"]={"xn":jQuery("#xn").val()};
	jQuery("#dataTable").initGrid(gridSetting);
});
function xmdmLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='wpCk(\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
function setXxjs(cellValue,rowObject){
return "<span title='"+rowObject["xmxxjs"]+"'>"+cellValue+"</span>";
}

function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

/*
 * 基本设置
 */
function setJbsz(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "未设置";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if (((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg)
			||((null == rowObject.shkssj || currDate >= rowObject.shkssj)
					&& (null == rowObject.shjssj || currDate <= rowObject.shjssj)&&'1'==rowObject.shkg)) {
		value = "已设置";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return jbsz(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

function setTp(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	value = "<img style='width:100%;height:68px;margin:0;float:left;padding-right: 0px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+xmdm+"&type=view'>";
	return value;
}

/*
 * 基本设置
 */
function jbsz(xmdm) {
	if (xmdm == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'axcsWpsz.do?method=wpJbsz&xmdm=' + xmdm;
	var title = "基本设置";
	showDialog(title, 680, 350, url);
}
function jesfkt(cellValue, rowObject) {
	var jesfxssq = rowObject.jesfxssq;
	var value = '';
	if (jesfxssq == '1') {
		value = cellValue + "<font color='red'>(调)</font>";
	} else {
		value = cellValue;
	}
	return value;
}

/*
 * 条件设置
 */
function setTjsz(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	
	var value;
	if (cellValue=="1") {
		value = "已设置";
	} else {
		value = "未设置";
	}
	value = setColor(value, cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\"" + xmdm + "\");'>" + value + "</a>";
	return value;
}


function query() {
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["xn"] = jQuery.trim(jQuery("#xn").val());
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'axcsWpsz.do?method=updateWp&xmdm=' + rows[0]["xmdm"];
		var title = "物品修改";
		showDialog(title, 600, 300, url);
	}
}
function wpCk(xmdm){
	showDialog("物品查询",600,300,"axcsWpsz.do?method=ckWp&xmdm="+xmdm);
}

function add() {
	var url = "axcsWpsz.do?method=addWp";
	var title = "增加专项爱心物品";
	showDialog(title, 600, 300, url);
}
function showZpZjDiv() {
	tipsWindown("系统提示", "id:addPic", "380", "130", "true", "", "true", "id");
}

// 照片上传
function uploadWpPic() {

	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xmdm = jQuery("#xmdm").val();

	jQuery.ajaxFileUpload( {
		url : 'axcs_wpsz_ajax.do?method=uploadWpPic&xmdm=' + xmdm,
		secureuri : false,
		fileElementId : 'xmtp',
		success : function(data, type) {
			if (type == 'success') {
				jQuery("#axwptp").attr(
						"src",
						"axcsWpsz.do?method=showPhoto&xmdm=" + xmdm + "&tt="
								+ new Date());
				// jQuery('#zpsfcz').attr("value", "true");
				alertInfo(data);
			}
		}
	});
}

/* 条件设置 */
function tjsz(xmdm) {
	if (xmdm == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'axcsWpsz.do?method=wpTjsz';
	url += "&xmdm=" + xmdm;
	var title = "条件设置";
	tjszDialog = showDialog(title, 750, 340, url, {
		close : function() {
			query();
		}
	});
}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？", {
			"okFun" : function() {
				var url = "axcsWpsz.do?method=delWp";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					if (data["success"] == false) {
						showAlertDivLayer(data["message"]);
					} else {
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function(tag) {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}
				}, 'json');
			}
		});
	}
}

/* 项目复制 */
function wpfz() {
	var url = 'axcsWpsz.do?method=wpFz';
	var title = "物品复制";
	showDialog(title, 390, 150, url);
}

function saveWpFz() {
	var wpfzxn = jQuery("#xn").val();
	if(wpfzxn == null || wpfzxn == ""){
		showAlert("请选择复制来源学年！");
		return false;
	}
	
	var url = 'axcsWpsz.do?method=wpFz&type=save';
	ajaxSubFormWithFun("WpszForm", url, function(data) {
		if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});


}
