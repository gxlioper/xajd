var action = "jtpjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["jgid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(jgid) {
	var url = action + "?method=showView&jgid=" + jgid;
	showDialog("奖项信息", 800, 500, url);
}

function jxpdzq(cellValue, rowObject) {
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
function sqzq(cellValue, rowObject) {
	return rowObject["sqxn"] + rowObject["sqxqmc"];
}
function sjly(cellValue, rowObject) {
	if(rowObject["sjly"]=="1"){
		return "是";
	}
	return "否";
}
// 申请
function add() {
	var url = action + "?method=add";
	showDialog("集体评奖增加", 800, 500, url);
}
/**
 * 加载奖项信息
 * 
 * @return
 */
function loadJxxx() {
	var jxid = jQuery("#jxid").val();
	// 奖项不为空
	if(jxid !=null && jxid != ""){
		jQuery.post("jtpjsz.do?method=loadJxxx", {
			jxid : jxid
		}, function(data) {
			var pdzq="";
			if(data.pdxn){
				pdzq=data.pdxn + " " + data.pdxqmc;
			}
			jQuery("#pdzq").text(pdzq);
			// 隐藏
			jQuery("#pdxn").val(data.pdxn);
			jQuery("#pdxq").val(data.pdxq);
			jQuery("#jxsm").text(data.jxsm);			
			if (data.jtpjdw == "bj") {
				loadBjpjxx();
			} else if (data.jtpjdw == "xy") {
				loadXypjxx();
			} else if (data.jtpjdw == "qs") {
				loadQspjxx();
			}else {
				loadOther();
			}
			jQuery("#jtpjxx").show();
		}, 'json');
	}else{
		jQuery("#pdzq").text("");
		jQuery("#jxsm").text("");
		jQuery("#jtpjxx").hide();		
	}
	checkJx(jxid);
}
function loadUpdate() {
	jQuery.post("jtpjjg.do?method=loadUpdate", {
		jgid : jQuery("#jgid").val()
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
	var jgid = jQuery("#jgid").val();
	if (!bjdm) {
		bjdm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadBjpjxx&bjdm=" + bjdm + "&type=" + type
					+ "&jgid=" + jgid,function(){
				fomartForm();
			});
	setQsxxShow(bjdm);
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
}
/**
 * 设置寝室信息显示
 * 
 * @return
 */
function setQsxxShow(bjdm){
	var iswzdx=jQuery("#iswzdx").val();
	//如果是温州大学
	if(iswzdx=="1"){
		jQuery("#qswmxx").load("jtpjsq.do?method=qsxxlist&bjdm=" + bjdm);
	}
}

/**
 * 通过申请学年学期取得集体评奖列表
 * @return
 */
function loadJtpjList(){	
	var sqxn = jQuery("#sqxn").val();
	var sqxq = jQuery("#sqxq").val();
	var jxid = jQuery("#jxid").val();
	if(sqxn !=null && sqxn !=""  && sqxq !=null && sqxq !="" ){
		// 调用方法返回
		jQuery.post("jtpjsz.do?method=loadJtpjList",{sqxn:sqxn,sqxq:sqxq},function(data){
			jQuery("#jxid").empty();
			jQuery("#jxid").append("<option value=''>&nbsp;</option>");
			jQuery(data["jtpjList"]).each(function(){
				if(jxid == this.jxid){
					jQuery("#jxid").append(jQuery("<option/>").text(this.jxmc).attr("value",this.jxid).attr("selected","true"));
				}else{
					jQuery("#jxid").append(jQuery("<option/>").text(this.jxmc).attr("value",this.jxid));
				}
			});
		},'json');
	}else{
		jQuery("#jxid").empty();
	}
	loadJxxx();
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
	var jgid = jQuery("#jgid").val();
	if (!xydm) {
		xydm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadXypjxx&xydm=" + xydm + "&type=" + type
					+ "&jgid=" + jgid,function(){
				fomartForm();
			});
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
}


function loadOther() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type = jQuery("#type").val();
	var jgid = jQuery("#jgid").val();
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadOtherPjxx&type=" + type + "&jgid=" + jgid,function(){
				fomartForm();
			});
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
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
		var sjly = rows[0]["sjly"];
		if ("1" == sjly) {
			showAlertDivLayer("不能修改流程数据！");
			return false;
		}
		var url = action + '?method=update&jgid=' + rows[0]["jgid"];
		var title = "修改集体评奖申请";
		showDialog(title, 800, 500, url);
	}
}
/**
 * 设置集体评奖名称
 */
function setJtpjmc() {
	//if (!jQuery("#pjjtmc").val()) {
		var pjjtmc = jQuery("#pjjtid>option:selected").text();
		if(pjjtmc){
			jQuery("#pjjtmc").val(pjjtmc);
		}
	//}
}
/**
 * 保存
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId) {
	fomartForm();
	setJtpjmc();	
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "保存成功！"){
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
	if(html){
		jQuery("#jtpjxx").html(html);
	}
}
/**
 * 验证是否存在空项
 * 
 * @param ids
 *            要验证的控件id "-"分隔
 * @return
 */
function check(ids) {
	var id = ids.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			alert(id[i]);
			return false;
		}
	}
	return true;
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {

  var rows = jQuery("#dataTable").getSeletRow(); 
  for ( var i = 0; i <ids.length; i++) { 
	  if (rows[i]['sjly'] == '1') {
		  showAlertDivLayer("只能删除非流程数据！");
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
// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport('jtpjjgbase.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();// 设置高级查询条件
	var url = "jtpjjg.do?method=exportData&dcclbh=" + dcclbh;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function drxx(){
	toImportData("IMPORT_JTPJ_JTPJJG");
	return false;
}
//山东畜牧兽医职业学院先进班集体推荐表打印
function getXjbjt_12947(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要下载的记录！");
	 } else {
		var flag = false;
		 for(var i = 0; i < rows.length; i++){
			 if(rows[i]["jtpjdw"] != 'bj'){
				 flag = true;
			 }
		 }
		 if(flag){
			 showAlertDivLayer("请选择以班级为单位的记录！");
			 return false;
		 }
		var ids = jQuery("#dataTable").getSeletIds();
		var url="jtpjjg.do?method=getSdxmWord_xjbjt&value="+ids;
		window.open(url);
	 } 
}

function getWordXjbjt(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("请选择您要下载的记录！");
	 } else if (rows.length > 1){
		var flag = false;
		 for(var i = 0; i < rows.length; i++){
			 if(rows[i]["jtpjdw"] != 'bj'){
				 flag = true;
			 }
		 }
		 if(flag){
			 showAlertDivLayer("请选择以班级为单位的记录！");
			 return false;
		 }
		var ids = jQuery("#dataTable").getSeletIds();
		var url="jtpjjg.do?method=getDjbZip_12947&value="+ids;
		window.open(url);
	 } else {
		 if(rows[0]["jtpjdw"] != 'bj'){
			 showAlertDivLayer("请选择以班级为单位的记录！");
			 return false;
		 }
		var url="jtpjjg.do?method=getDjbWord_12947&jgid="+rows[0]["jgid"];
		window.open(url);
    }
}

//验证奖项（西安科技大学）
function checkJx(jxiddm) {
	if(jQuery("#xxdm").val() == '10704'){
		jQuery.ajaxSetup({async:false});
			jQuery.getJSON('jtpjjg.do?method=isBjjx',{jxid:jxiddm},function(data){
				if(data){
					jQuery("#rdfsLocation").empty();
					html = '<tr>';
					html+= '<th width="20%" style="border-top: 0px;">评定分数</th>';
					html+= '<td colspan="3" style="border-top: 0px;"><input type="text" id="rdfs" name="rdfs" value="" onblur="checkZs(this)"/></td>';
					html+= '</tr>'
					jQuery("#rdfsLocation").html(html);					
				}else{
					jQuery("#rdfsLocation").empty();
				}
			});
		jQuery.ajaxSetup({async:true});
		return;
	}else{
		return;
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
	var jgid = jQuery("#jgid").val();
	jQuery("#jtpjxx").load("jtpjjg.do?method=loadld&type=" + type+"&jgid=" + jgid,function(){
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
	jQuery("#rowConut").val(jQuery("#rowConut").text());
	jQuery("#first").val("0");
	jQuery.ajaxSetup( {
		async : true
	});
}

