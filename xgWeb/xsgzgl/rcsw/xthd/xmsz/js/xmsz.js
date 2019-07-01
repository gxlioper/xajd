var tjszDialog;
var gridSetting = {
	caption : "项目列表",
	pager : "pager",
	url : "rcsw_txhd_xmszCx.do?method=xmszCx&type=query",
	colList : [ {
		label : '学年',
		name : 'xn',
		index : 'xn',
		width : '12%'
	}, {
		label : '学期',
		name : 'xqmc',
		index : 'xq',
		width : '5%'
	}, {
		label : '项目代码',
		name : 'xmdm',
		index : 'xmdm',
		key : true,
		hidden : true
	}, {
		label : '项目名称',
		name : 'xmmc',
		index : 'xmmc',
		width : '23%',
		formatter:show
	}, {
		label : '活动时间',
		name : 'hdsj',
		index : 'hdsj',
		width : '25%'
	}, {
		label : '活动地点',
		name : 'hddd',
		index : 'hddd',
		width : '19%'
	}, {
		label : '活动类别',
		name : 'lbmc',
		index : 'lbmc',
		width : '10%'
	}, {
		label : '申请开关',
		name : 'sqkg',
		index : 'sqkg',
		width : '8%',
		formatter : setSqkg
	}, 
	{label:'流程数据',name:'sjly', index: 'sjly',hidden:true},
	{
		label : '审核开关',
		name : 'shkg',
		index : 'shkg',
		width : '8%',
		formatter : setShkg
	} ],
	sortname : "xmmc",
	sortorder : "asc"
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function show(cellValue, rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["xmdm"]+ "')\" class='name'>" + cellValue + "</a>";
}
function ckxx(xmdm){
	var url ="rcsw_txhd_xmszCx.do?method=showView&xmdm=" + xmdm;
	showDialog("查看活动项目", 790,470, url);
}
// 由于外层样式影响，颜色必须写在元素上
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
 * 申请开关
 */
function setSqkg(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "未开启";
	var status = '0';
	var color;
	if (cellValue == '1') {
		var currDate = jQuery("#currDate").val();
		if (rowObject.sqkssj != null && currDate < rowObject.sqkssj
				|| rowObject.sqjssj != null && currDate > rowObject.sqjssj) {
		} else {
			value = "已开启";
			status = '1';
		}
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

/*
 * 审核开关
 */
function setShkg(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "未开启";
	var status = '0';
	var color;
	if (cellValue == '1') {
		var currDate = jQuery("#currDate").val();
		if (rowObject.shkssj != null && currDate < rowObject.shkssj
				|| rowObject.shjssj != null && currDate > rowObject.shjssj) {
		} else {
			value = "已开启";
			status = '1';
		}
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

/*
 * 时间开关
 */
function sjkg(xmdm) {
	if (xmdm == null) {// 点击按钮
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条您要设置的记录！");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'rcsw_txhd_xmszCx.do?method=xmszSjkg&xmdm=' + xmdm;
	var title = "项目时间控制";
	showDialog(title, 600, 445, url);
}

function onShow() {
	jQuery("[name=shlc]").change(function() {// 人数控制级别，兼得控制级别，根据审核流程进行显示
				setKzlc(jQuery(this).val());
			});
	defaultShlc = jQuery("[name=shlc]:checked").val();
	jQuery("[name=shlc]").change();

}


/**
 * 取自评奖的方法，应该改成通用的
 * @param value
 * @return
 */
function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		return;
	}
	var url = "xpj_xmwh.do?method=xmwhShfw";
	jQuery
			.post(
					url,
					{
						value : value
					},
					function(data) {
						var sHtml = "";
						var radio1 = "";
						if (data != null) {
							for ( var i = 0; i < data.length; i++) {
								var o = data[i];
								radio1 += "<label><input type='radio' name='rskzjbView' value='"
										+ o.spgwdm + "'/>";
								radio1 += o.spgwmc;
								radio1 += "</label>";
								if (i != data.length - 1) {
									radio1 += "<br/>";
								}
							}
						}
						sHtml += radio1;
						jQuery("#rskzjbTd").html(sHtml);
						// 人数控制级别
						jQuery(
								"input:radio[name=rskzjbView][value="
										+ jQuery("#rskzjb").val() + "]").attr(
								"checked", "checked");
						if (defaultShlc == value) {

						}
						setSpzt();//设置审批状态
					}, 'json');
}

/**
 * 新增
 * 
 * @return
 */
function add() {
	var url = "rcsw_txhd_xmszCx.do?method=xmszZj";
	var title = "增加活动项目";
	showDialog(title, 790,476, url);
}

/**
 * 保存
 * 
 * @param method
 *            ：方法名
 * @param type
 *            ：类型
 * @return
 */
function save(method, type) {
	if (yanzheng()) {
		var url = "rcsw_txhd_xmszCx.do?method=" + method + "&type=" + type;
		ajaxSubFormWithFun("demoForm", url, function(data) {
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
}

/**
 * 验证
 */
function yanzheng() {
	var xmmc = jQuery("#xmmc").val();
	var hdkssj = jQuery("#hdkssj").val();
	var hdjssj = jQuery("#hdjssj").val();
	var lbdm = jQuery("#lbdm").val();
	var hddd = jQuery("#hddd").val();

	if (xmmc == "") {
		showAlert("项目名称不能为空！");
	} else if (hdkssj == "" || hdjssj == "") {
		showAlert("请输入活动起止时间");
	} else if (lbdm == "") {
		showAlert("活动类别不能为空！");
	} else if (hddd == "") {
		showAlert("活动地点不能为空！");
	} else if(jQuery("#hdmdyy").val().length > 200){
		showAlert("活动目的及意义不能超过200字！");
	} else if(jQuery("#hdfa").val().length > 500){
		showAlert("活动方案不能超过500字！");
	} else if(jQuery("#hdsm").val().length > 1000){
		showAlert("活动说明不能超过1000字！");
	} else {
		return true;
	}

	return false;
}

/**
 * 修改
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else if(sjly == '1'){
		showAlertDivLayer("流程数据不能修改！");
	} else {
		var url = 'rcsw_txhd_xmszCx.do?method=xmszXg&xmdm=' + rows[0]["xmdm"];
		var title = "修改活动项目";
		showDialog(title, 790,480, url);
	}
}

/**
 * 删除
 * 
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
			"okFun" : function() {
				var url = "rcsw_txhd_xmszCx.do?method=xmszSc";
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


/**
 * 时间开关保存设置
 * @return
 */
function saveForm() {
	var shlc = jQuery("#shlc").val();

	// 人数开关级别设置值，以便提交用
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	if (shlc == "") {
		showAlert("审核流程不能为空！");
		return false;
	}
	if (rskzjb == "" || rskzjb == null) {
		showAlert("人数控制级别不能为空！");
		return false;
	}
	if(jQuery("#kgbz").val().length > 100){
		showAlert("申请开关备注不能超过100字！");
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=xmszSjkg&type=update";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun":  function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});
}


/*
 * 根据审核状态，设置部分项
 * 
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery(".prompt").css("display","");
		jQuery(".promptYsq").css("display","");
		jQuery("table input,select").not(jQuery("#sqrssx,#shrssx")).attr(
				"disabled", "disabled");
	}
}

var DCCLBH = "rcsw_txhd_xmsz.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号，执行导出的函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_txhd_xmszCx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//复制
function copystxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一个社团项目！");
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=copeOfXmxx&xmmc="+rows[0]['xmmc']+"&xmdm="+rows[0]['xmdm'];
	var title = "社团结果增加";
	showDialog(title, 400, 180, url);
}


//保存项目结果
function saveCopyXmjg(){
	if(jQuery("#xmmc").val()=="" || jQuery("#xn").val()=="" || jQuery("#xq").val()==""){
		showAlert("项目名称,学年,学期不能为空！")
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=saveCopeXmxx";
		ajaxSubFormWithFun("TxhdXmszForm", url, function(data) {
			 if(data["message"]=="保存成功！"){
		   		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		   	 }else{
		   		 showAlert(data["message"]);
		   	 }
		});
	
}
