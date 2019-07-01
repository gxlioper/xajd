/**
 * 咨询师职工号链接
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zghLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('查看咨询师信息' , 750 , 420 , 'xljk_zxsgl.do?method=viewZxsxx&zgh="
			+ rowObject['zgh'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}
/**
 * 咨询师姓名链接
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxsxmLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('查看咨询师信息' , 750 , 420 , 'xljk_zxsgl.do?method=viewZxsxx&zgh="
			+ rowObject['zxs'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + (cellValue==null?" ":cellValue)
			+ "</a>";

	return el;
}
/**
 * 咨询师链接
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxsLink(zgh) {
	showDialog('查看咨询师信息' , 750 , 420 , "xljk_zxsgl.do?method=viewZxsxx&zgh="+zgh);
}
/**
 * 学号链接（显示预约咨询申请信息）
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	
	var onclickfn = "onclick=\""
		+ "showDialog('查看心理咨询预约信息' , 750 , 360 , 'xljk_xsyyzx.do?method=viewXsyysq";
	
	if(rowObject['sqid']!=null){
		onclickfn += "&sqid=" + rowObject['sqid']+ "')" + "\"";
	}else {
		onclickfn += "&zxid=" + rowObject['zxid']+ "')" + "\"";;
	}

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * 学号链接（显示预约咨询申请信息及安排咨询信息及咨询师反馈信息）
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForZxgl(cellValue, rowObject) {
	var onclickfn = "";
	
	if(rowObject['zxid']==null){
		onclickfn = "onclick=\""
			+ "showDialog('查看心理咨询' , 750 , 400 , 'xljk_yyzxfk.do?method=viewXlzxYyfk&sqid="
			+ rowObject['sqid'] + "')" + "\"";
	}else{
		onclickfn = "onclick=\""
			+ "showDialog('查看心理咨询' , 750 , 400 , 'xljk_xlzxcl.do?method=viewXlzxcl&zxid="
			+ rowObject['zxid'] + "')" + "\"";
	}
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * 预约状态
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function yyztChange(cellValue, rowObject) {
	var returnText;
	switch (cellValue) {
		case "1":
			returnText="预约中";
			break;
		case "2":
			returnText="<font color='blue'>预约成功</font>";
			break;
		case "3":
			returnText="预约中<br/>(学生取消)";
			break;
		case "4":
			returnText="预约成功<br/>(学生取消)";
			break;
		case "5":
			returnText="预约失败";
			break;
		default:
			returnText="";
			break;
	}
	return returnText;
}
/**
 * 反馈状态
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function yyfkztChange(cellValue, rowObject) {
	var returnText;
	var yyzt = rowObject['yyzt'];
	if(yyzt=='2' || yyzt == '4' || yyzt == '5'){
		returnText = "已反馈";
	}else if(yyzt=='1'){
		returnText = "<font color='red'>未反馈</font>";
	}else{
		returnText = "";
	}
	return returnText;
}
/**
 * 学生咨询评价状态
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function zxztChange(cellValue, rowObject) {
	var returnText;
	switch (cellValue) {
		case "0":
			returnText="待咨询";
			break;
		case "1":
			returnText="已咨询";
			break;
		case "2":
			returnText="未咨询";
			break;
		default:
			returnText="";
			break;
	}
	return returnText;
}

/**
 * 展开关闭显示tbody
 */
function zkandbxqwdview(node,showid){
	if(jQuery(node).attr("class")=="down"){
		jQuery(node).attr("class","up");
		jQuery(node).html("&nbsp;&nbsp;收起");
	}else{
		jQuery(node).attr("class","down");
		jQuery(node).html("&nbsp;&nbsp;展开");
	}
	jQuery("#"+showid).toggle();
}