
/**
 * 学号链接（显示预约咨询申请信息及安排咨询信息）
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForYyfk(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('查看心理咨询' , 750 , 360 , 'xljk_yyzxfk.do?method=viewXlzxYyfk&sqid="
			+ rowObject['sqid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * 预约反馈
 */
function xlzxyyfk(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要预约反馈的记录！");
		return false;
	} else{
		var zxzt = rows[0]['zxzt'];
		var yyzt = rows[0]['yyzt'];
		if(yyzt == '3'){
			showAlertDivLayer("您不能对预约中（学生取消）的数据进行操作！");
			return false;
		}else if(yyzt == '4'){
			showAlertDivLayer("您不能对预约成功（学生取消）的数据进行操作！");
			return false;
		}

		if(zxzt == '1' || zxzt == '2'){
			showAlertDivLayer("您只能对未反馈或者已反馈待咨询的数据进行预约反馈！");
			return false;
		}
		showDialog('预约反馈',750,440,'xljk_yyzxfk.do?method=xlzxYyfk&sqid=' + rows[0]['sqid'] + '&yyzt=' + rows[0]['yyzt']);
	}
}

/**
 * 预约反馈保存操作
 */
function opYyzxfkAction(){
	var yyzt = jQuery("input[name='yyzt']:checked").val();
	var zxs = jQuery("input[name='zxs']:checked");
	if(yyzt == '2'){
		if(zxs.length!=1){
			showAlert("请选择一名咨询师！");
			return false;
		}
		if(!checkNotNull("zzaprq")){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var bz = jQuery('#bz').val();
		if(bz.length > 500){
			showAlertDivLayer("其他备注最大字数不超过"+500+",请确认！");
			return false;
		}
	}else if(yyzt == '5'){
		if(!checkNotNull("yysbyy")){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var yysbyy = jQuery('#yysbyy').val();
		if(yysbyy.length > 500){
			showAlertDivLayer("预约失败原因最大字数不超过"+500+",请确认！");
			return false;
		}
	}
	var url = "xljk_yyzxfk.do?method=xlzxYyfkAction";
	ajaxSubFormWithFun("yyzxfkForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentYyzxfk();
			}
		}});
	});
}

function checkZxs(node){
	var lxdh = jQuery(node).parent().find(".lxdh").html();
	var address = jQuery(node).parent().find(".address").html();
	jQuery("#zxslxdh").val(lxdh=="null"?"":lxdh);
	jQuery("#zxdz").val(address=="null"?"":address);
}

/**
 * 在弹出心理咨询处理信息添加/修改窗口中刷新父页面，并关闭窗口
 */
function refershParentYyzxfk(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadYyzxfkDataTable();
		iFClose();
	} 
}