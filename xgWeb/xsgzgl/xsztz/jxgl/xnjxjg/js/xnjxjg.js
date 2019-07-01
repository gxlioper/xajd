/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "jxgl_xnjxjg.do?method=xnjxjgAdd";
	var title = "申请";
    showDialog(title, 770, 520, url);
	
}

//增加结果保存
function save(type){
	var ids = "xh-xmmc-jxid";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "jxgl_xnjxjg.do?method=savejg&type=" + type;
	ajaxSubFormWithFun("XnjxjgForm", url, function(data) {
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

function saveUpdate(){
	if(null==jQuery("#jxid").val()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "jxgl_xnjxjg.do?method=savejg&type=update";
	ajaxSubFormWithFun("XnjxjgForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(sqid, xh) {
	showDialog("查看", 770, 450, "xmsqgl_xmsq.do?method=XmjgView&sqid="
			+ sqid + "&xh=" + xh);
}

function jxLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["sjly1"] == "1") {
		showAlertDivLayer("不能删除来自数据流记录！");
		return false;
	} else {
		var xmdms = new Array();
		var xhs = new Array();
		var xns = new Array();
		var xqs = new Array();
		jQuery.each(rows, function(i, row) {
			xmdms.push(row["xmdm"]);
			xhs.push(row["xh"]);
			xns.push(row["xn"]);
			xqs.push(row["xq"]);
		});
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jxgl_xnjxjg.do?method=deljg",{xmdms:xmdms,xhss:xhs,xns:xns,xqs:xqs},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if (rows[0]["sjly1"] == "1") {
			showAlertDivLayer("数据流来源的数据不能修改！");
			return false;
		}
		var url = 'jxgl_xnjxjg.do?method=editjg&jgid=' + rows[0]["jgid"];
		var title = "奖项结果修改";
		showDialog(title, 770, 500, url);
	}
}

var DCCLBH = "sztz_jxgl_xnjxjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xnjxjgExportData);
}

//导出方法
function xnjxjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "jxgl_xnjxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jxgl_xnjxsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
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
				jQuery.post("jxgl_xnjxsq.do?method=cancelXmsq", {
					values : ids.toString(),
					splcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("校内奖项申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['shlc']);
	}
}


/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var kcyrs = parseInt(jQuery.trim(jQuery(this).find("td[name=kcyrs]").text()));
		var tgrs  = parseInt(jQuery.trim(jQuery(this).find("td[name=syme]").text()));
		if(kcyrs-tgrs == 0){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}

/**
 * 选择项目页，切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").last().css("display","none");
	} else {
		jQuery("#titleTr td").last().css("display","");
	}
}

/**
 * 确定选择项目
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("input[name='checkbox']:checked");
	var test = api.get('parentDialog');
	if(selectBox.length != 1){
		showAlert("请选择一项活动，进行选择！");
		return false;
	}
		var xmdm = test.jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmdm']").val();
	  	var xmmc = test.jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmmc']").val();
		test.jQuery("#xmjbmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmjbmc']").val());
		test.jQuery("#sbbmmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sbbmmc']").val());
		test.jQuery("#lxdh").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'lxdh']").val());
		test.jQuery("#sskmmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sskmmc']").val());
		test.jQuery("#jcxf").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'jcxf']").val());
		test.jQuery("#kcyrs").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'kcyrs']").val());
		test.jQuery("#sbr").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sbrxm']").val());
		test.jQuery("#xmkssj").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xmkssj'] ").val());
		test.jQuery("#sfsljxmc").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'sfsljxmc'] ").val());
		test.jQuery("input[name='xmdm']").val(jQuery(selectBox).parent().parent().find("td:eq(0) input").val());
		test.jQuery("#xmmc").val(xmmc);
		test.jQuery("#jgid").val(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'jgid']").val());
		test.jQuery("#xn").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xn']").val());
		test.jQuery("#xq").text(jQuery(selectBox).parent().parent().find("td[name=syme] input[name = 'xqmc']").val());
		var jxhtml = "";
    	var jxxhtml = "";
		jQuery.ajaxSetup({async:false});
		jQuery.post("jxgl_xnjxsq.do?method=getjxxx", {
			xmdm:xmdm
		}, function(data) {
			dataObj = data;
			
        		
            	for(var i=0;i<data.length;i++){
            		
            		jxhtml+="<tr><input type='hidden' name='jxId' value='"+data[i].jgid+"'/><td>"+data[i].jxmc+"</td><td>"+data[i].fjxf+"</td><td>"+data[i].xssx+"</td><tr>";
            		test.jQuery("#tbody_xmjxxx").html(jxhtml);
            		jxxhtml+="<option value='"+data[i].jgid+"'>"+data[i].jxmc+"</option>";
            		
            	}	
			}, 'json');
		test.jQuery("#jxid").html(jxxhtml);
		jQuery.ajaxSetup({async:true});
		
	    iFClose();
	    test.jifen();
}

function jxLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function jxView(id) {
	showDialog("奖项申请查看", 800, 500, "jxgl_xnjxjg.do?method=viewJx&jgid="+id);
}

function jifen(){
	var fen = jQuery("#jcxf").html();
	var jichu = jQuery("#jxid").val();
	jQuery("input[name='jxId']").each(function(i,n){
		if(jQuery(n).val()==jichu){
			jQuery("#zxf").html(Number(fen)+Number((jQuery(n).parent().find("td").eq(1).html())));
			return false;
		}
	})
	return false;
}

function importXnjxjg(){
	toImportDataNew("IMPORT_XNJXJG");
	return false;
}
