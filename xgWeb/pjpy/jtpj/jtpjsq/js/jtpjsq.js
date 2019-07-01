var action = "jtpjsq.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["sqid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("奖项信息", 800, 500, url);
}
/**
 * 设置寝室信息显示
 * 
 * @return
 */
function setQsxxShow(bjdm) {
	var iswzdx = jQuery("#iswzdx").val();
	// 如果是温州大学
	if (iswzdx == "1") {
		jQuery("#qswmxx").load("jtpjsq.do?method=qsxxlist&bjdm=" + bjdm);
	}
}
function jxpdzq(cellValue, rowObject) {
	var pdxq = rowObject["pdxqmc"];
	if (!pdxq) {
		pdxq = "";
	}
	return rowObject["pdxn"] + pdxq;
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "通过";
		break;
	case "2":
		shztmc = "不通过";
		break;
	case "3":
		shztmc = "已退回";
		break;
	case "5":
		shztmc = "审核中";
		break;
	default:
		shztmc = "未提交";
		break;
	}
	return shztmc;
}
// 申请
function add() {
	var url = action + "?method=add";
	showDialog("集体评奖申请", 800, 500, url);
}
/**
 * 加载奖项信息
 * 
 * @return
 */
function loadJxxx() {
	var jxid = jQuery("#jxid").val();
	jQuery.post("jtpjsz.do?method=loadJxxx", {
		jxid : jxid
	}, function(data) {
		/*
		 * jQuery("#sqxn").text(data.sqxn); jQuery("#sqxq").text(data.sqxqmc);
		 */
		var pdzq="";
		if(data.pdxn){
			pdzq=data.pdxn + " " + data.pdxqmc;
		}
		jQuery("#pdzq").text(pdzq);
		jQuery("#jxsm").text(data.jxsm);
		jQuery("#splcid").val(data.splcid);
		if (data.jtpjdw == "bj") {
			loadBjpjxx();
		} else if (data.jtpjdw == "xy") {
			loadXypjxx();
		} else if (data.jtpjdw == "qs") {
			loadQspjxx();
		} else {
			loadOther();
		}

		// 退回后奖项不可调整
		if(jQuery("#shzt").val()=='3'){
			jQuery("#jxid").attr("disabled","disabled");
		}
	}, 'json');
}
function loadUpdate() {
	jQuery.post("jtpjsq.do?method=loadUpdate"+"&tt="+new Date().getTime(), {
		sqid : jQuery("#sqid").val()
	}, function(data) {
		if (data) {
			autoSetParam(data, false);
		}
	}, 'json');
}
/**
 * 加载班级评奖信息
 * 
 * @return
 */
function loadBjpjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var bjdm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	if (!bjdm) {
		bjdm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadBjpjxx&bjdm=" + bjdm + "&type=" + type
					+ "&sqid=" + sqid + "&tt=" + new Date().getTime(),
			function() {
				 fomartForm();
				// 加载寝室信息
				setQsxxShow(jQuery("#pjjtid").val());
				loadUpdate();
			});
	 pjjtidchange();
	jQuery.ajaxSetup( {
		async : true
	});
	
}
/**
 * 加载学院评奖信息
 * 
 * @return
 */
function loadXypjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var xydm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	if (!xydm) {
		xydm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadXypjxx&xydm=" + xydm + "&type=" + type
					+ "&sqid=" + sqid + "&tt=" + new Date().getTime(),
			function() {
				fomartForm();
				loadUpdate();
			});
	jQuery.ajaxSetup( {
		async : true
	});
}
function loadOther() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadOtherPjxx&type=" + type + "&sqid=" + sqid
					+ "&tt=" + new Date().getTime(), function() {
				fomartForm();
				loadUpdate();
			});
	jQuery.ajaxSetup( {
		async : true
	});
}
/**
 * 修改
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt && "3" != shzt) {
			showAlertDivLayer("只能修改未提交或者退回的记录！");
			return false;
		}
		var url = action + '?method=update&sqid=' + rows[0]["sqid"];
		var title = "修改集体评奖申请";
		showDialog(title, 800, 560, url);
	}
}
/**
 * 设置集体评奖名称
 */
function setJtpjmc() {
	var pjjtmc = jQuery("#pjjtid>option:selected").text();
	if(pjjtmc){
		jQuery("#pjjtmc").val(pjjtmc);
	}
}
/**
 * 保存
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url, checkId) {
	setJtpjmc();
	if (!checkNull(checkId)) {
		return false;
	}
	if (!checkother()) {
		return false;
	}
	if(!bjyzts()){
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		 if(data["message"] == "提交成功！"){
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
		 }else{
			 showAlert(data["message"]);
		 }
		
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}
/**
 * 去掉内嵌的form
 * 
 * @return
 */
function fomartForm() {
	var html = jQuery("#innerForm").html();
	if (html) {
		jQuery("#jtpjxx").html(html);
	}
}
function checkother() {
	return true;
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '0') {
				showAlertDivLayer("只能删除未提交的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes = "成功删除了<font color='green'>&nbsp;"
							+ data["successDel"] + "&nbsp;</font>条数据";
					mes += "</br>";
					showAlertDivLayer(mes, {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});

	}
}
/**
 * 提交
 * 
 * @return
 */
function tj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '0' && rows[i]['shzt'] != '3') {
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		var shzt = rows[0]["shzt"];
		
		
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				if(shzt!="3"){
					// 验证是否可提交
					jQuery.post(action + "?method=checkSfktj", {
						jxid:rows[0]["jxid"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("您申请的奖项已关闭申请，无法提交，详情请联系管理员。");
							return false;
						}else{
							// 提交
							jQuery.post(action + "?method=subBusi", {
								values : ids.toString(),
								sqid : rows[0]['sqid']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
					
				}else{
	
					// 提交
					jQuery.post(action + "?method=subBusi", {
						values : ids.toString(),
						sqid : rows[0]['sqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}			

			}
		});
	}
}
/**
 * 撤销
 * 
 * @return
 */
function cancle() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action + "?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else if (rows[0]["shzt"] == "0") {
		showAlertDivLayer("尚未提交，无相关流程信息！");
	} else {
		showDialog("请假审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}

/**
 * 载入寝室评奖信息
 */
function loadQspjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type=jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	jQuery("#jtpjxx").load("jtpjsq.do?method=loadld&type=" + type+"&sqid=" + sqid,function(){
			fomartForm();
		});
	loadLdxx();
	jQuery.ajaxSetup({async : true});
	loadUpdate();
}

function loadLdxx(){
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#lddm').val(pjjtids[0]);
	}
	jQuery('#ldxb').empty();
	jQuery('#ch').empty();
	jQuery('#qsh').empty();
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx', { lddm : jQuery('#lddm').val() }, function(data) {
		var qsch = parseInt(data.qsch); //起始层号
		var ldcs = parseInt(data.ldcs); //楼栋层数
		var count = 0;
		jQuery('#ldxb').text(data.ldxb);
		var option = "<option value=''></option>"
		for(var i=qsch;i<=ldcs;i++){
			option += "<option value='" +i+"'>"+i+"</option>";
		}
		jQuery('#ch').append(option);
	}, 'json');
	loadChxx();
}


function loadChxx() {
	jQuery('#qsh').empty();
	var lddm=jQuery('#lddm').val();
	if(!(lddm==null||lddm=="")){
		jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm', {
			lddm : lddm, ch : jQuery('#ch').val() },function(data) {
					var option = "<option value=''></option>";
					if (data != null && data.length != 0) {
						for ( var i = 0; i < data.length; i++) {
							option +="<option value=\""+data[i].qsh+"\">"	+data[i].qsh+"</option>";
						}
					}
					jQuery('#qsh').append(option);
				});
	} else {
		var option = "<option value=''></option>";
		jQuery('#qsh').append(option);
	}
	loadQsxx();
}

function loadQsxx(){
	jQuery.ajaxSetup( {
		async : false
	});
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#qsh').val(pjjtids[1]);
	}
	jQuery("#qsxsxx").load("jtpjsq.do?method=qsxsxxList");
	var lddm=jQuery('#lddm').val();
	var qsh=jQuery('#qsh').val();
	if(qsh==null||qsh==""){
		jQuery("#pjjtid").val("");
		jQuery("#pjjtmc").val("");
	}else{
		jQuery("#pjjtid").val(jQuery('#lddm').val()+"@@"+jQuery('#qsh').val());
		jQuery("#pjjtmc").val(jQuery("#lddm option:selected").text()+"-"+jQuery('#qsh').val());
	}
	pjjtidchange();
	jQuery("#rowConut").val(jQuery("#rowConut").text());
	jQuery("#first").val("0");
	jQuery.ajaxSetup( {
		async : true
	});
}