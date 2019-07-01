var tsHtml="请输入工作摘要，字数在50字以上。";
jQuery(function(){
	initParam();
		});
//填充类别编号
function getLbdm(obj){
	jQuery("#lbbh").val(obj.value);
}

function initParam(){
	if(""==jQuery("#gzzy").val()||null==jQuery("#gzzy").val()){
		jQuery("#gzzy").val(tsHtml);
		jQuery("#gzzy").css("color", "#7D7D7D");	
	}
	
	jQuery("#gzzy").focus(function() {
		if (tsHtml== jQuery("#gzzy").val()) {
			jQuery("#gzzy").val("");
			jQuery("#gzzy").css("color", "");
		}
	});
	jQuery("#gzzy").blur(function() {
		if ("" == jQuery("#gzzy").val()) {
			jQuery("#gzzy").val(tsHtml);
			jQuery("#gzzy").css("color", "#7D7D7D");
		}
	});
}

/**
 * 参数验证
 * @return
 */
function checkZdz(){
	var zgh = jQuery("#zgh").val();
	var flag=true;
	var zgh = jQuery("#zgh").val();
	if (jQuery("#gzsj").val() == ""||jQuery("#gzsj").val() == null||jQuery("#gzzy").val() == ""||jQuery("#gzzy").val() == null||
			jQuery("#lbdm").val() == "" ||jQuery("#lbdm").val() == null || zgh == "") {
		showAlert("请将必填项填写完整！");
		flag= false;
		return flag;
	}
	if (jQuery("#gzzy").val().length<50) {
		showAlert("工作摘要至少输入50字！");
		flag= false;
		return flag;
	}
	if (jQuery("#gzzy").val().length>1000) {
		showAlert("工作摘要最多输入1000字！");
		flag= false;
		return flag;
	}
	if(jQuery("#xxdm").val() == '11842'){
		var lks = jQuery("#lks").val();
		if(lks == null || lks == "") {
			showAlertDivLayer("请将必填项填写完整！");
			flag = false;
			return flag;
		}
		var objArr = "";
		jQuery.each(jQuery("#tbody_dhryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			objArr += xh + ",";			
		});
		jQuery("#objStr").val(objArr);
	}
	return flag;
}

//增加谈话对象
function addThxs() {
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_dhryxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'gzjlsq.do?method=getStu&&xhArr='+xhArr;
	showDialog('请选择学生', 800, 550, url);
	return false;	
}

//删除谈话对象
function delThxs() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("请选择需要删除学生！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

function thxsZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请至少选择一个学生！");
		return false;
	}
	setQqxs(rows);	
}

function setQqxs(rows) {

	var html = "";
	/*经测试，下面这段代码无用 by yxy,注销掉*/
	//var qqxsTrLen = jQuery("#tbody_qqryxx tr").length;
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	for ( var int = 0; int < rows.length; int++) {
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td>";
		html += "<td name='xb'>" + rows[int]["xb"] + "</td>";
		html += "<td name='xymc'>" + rows[int]["xymc"] + "</td>"; 
		html += "<td name='zymc'>" + rows[int]["zymc"] + "</td>"; 
		html += "<td name='bjmc'>" + rows[int]["bjmc"] + "</td>"; 
		/*经测试，下面这段代码无用 by yxy,注销掉*/
		//jQuery("#tbody_qqryxx").append(html);
	}
	
	W.thxszj(html);

	iFClose();
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}