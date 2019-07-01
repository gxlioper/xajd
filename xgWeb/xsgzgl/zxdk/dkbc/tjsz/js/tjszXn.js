
var dataObj;
var colNum = 2;//列数

jQuery(function() {
	onShow();
});

function onShow() {	
	var url = "dkbc_tjsz.do?method=xmwhTjszXn&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		dataObj = data;
		var zqlx = jQuery("#zqlx").val();
		//困难生申请条件按照系统配置参数显示，modified by xiaxia 2015-03-17
		if("ZZTJ_5"==jQuery("#tjdm").val()){
			if("xn"==jQuery("#knszq").val()){
				createKnsHtml() ;
			}else{
				createHtml();
			}
		}else{
		if(zqlx == "3"){//综测条件项目
			createHtmlForZhcp() ;
		}else if(zqlx == "4" || zqlx == "5"){//学年单选、多选
			createHtmlForXn() ;
		}else{
			createHtml() ;
		}
		}
		setInit();//设置初值
	}, 'json');
}

//初始化数据
function setInit(){
	var xnVal = jQuery("#xnVal").val();
	var arr = xnVal.split(",");

	jQuery.each(arr,function(index,value){
		jQuery("input[name=xnView][value='"+value+"']").attr("checked","checked");
	});
	var zqlx = jQuery("#zqlx").val();
	if(zqlx == "2" || zqlx == "5"){//学年学期多选、学年多选
		jQuery("#qxDiv").show();
		//全选按钮
		jQuery("input:checkbox[name=grid_selectAll]").change(function(){
			jQuery("#xmList input:checkbox").attr("checked",jQuery(this).prop("checked"));
		});
		if(jQuery("#xmList  input:checkbox:not(:checked)").length == 0){
			jQuery("input:checkbox[name=grid_selectAll]").attr("checked","checked");
		}
	}
}

function createHtml() {
	var xnList = dataObj['xnList'];
	var xqList = dataObj['xqList'];
	var xqL =  xqList.length;

	var html = "";
	var m = 0;
	for ( var i = 0; i < xnList.length; i++) {
		var o = xnList[i];
		if(o.xn == ""){
			m++;
			continue;
		}
		if((i - m)%2 == 0){
			html += "<tr>";
		}
		for ( var j = 0; j < xqList.length; j++) {
			var xqObj = xqList[j];
			var value = o.xn+ ";" + xqObj.xqdm;
			var text = o.xn+ " " + xqObj.xqmc;
			html += "<td>";
			html += "<label>";
			if(jQuery("#zqlx").val() == "1"){//学年学期单选
				html += "<input type='radio' value='" + value + "' name='xnView'/>";
			}else{
				html += "<input type='checkbox' value='" + value+ "' name='xnView'/>";
			}
			html += text;
			html += "</label>";
			html += "</td>";
		}
		if(((i - m)+1)%2 == 0){
			html += "</tr>";
		}
	}
	
	var k = xnList.length - m;
	if(k %2 > 0){
		while(k % 2 != 0){
			html += "<td>&nbsp;</td>";
			html += "<td>&nbsp;</td>";
			k++;
		}
		html += "</tr>";
	}
	jQuery("#xmList").html(html);

}
//困难生申请周期为学年
function createKnsHtml() {
	var xnList = dataObj['xnList'];

	var html = "";
	var m = 0;
	for ( var i = 0; i < xnList.length; i++) {
		var o = xnList[i];
		if(o.xn == ""){
			m++;
			continue;
		}
		if((i - m)%4 == 0){
			html += "<tr>";
		}
		
		var value = o.xn;
		var text = o.xn;
		html += "<td>";
		html += "<label>";
		if(jQuery("#zqlx").val() === "1"){//学年多选
			html += "<input type='radio' value='" + value + "' name='xnView'/>";
		}else{
			html += "<input type='checkbox' value='" + value+ "' name='xnView'/>";
		}
		html += text;
		html += "</label>";
		html += "</td>";
		
		if(((i - m)+1)%4 == 0){
			html += "</tr>";
		}
	}
	
	var k = 4-xnList.length%4+1;
	for(i=0;i<k;i++){
		html += "<td>&nbsp;</td>";
	}
	html += "</tr>";
	
	jQuery("#xmList").html(html);


}
function createHtmlForXn() {
	var xnList = dataObj['xnList'];
	
	var html = "";
	var m = 0;
	for ( var i = 0; i < xnList.length; i++) {
		var o = xnList[i];
		if(o.xn == ""){
			m++;
			continue;
		}
		if((i - m)%2 == 0){
			html += "<tr>";
		}
		var value = o.xn;
		var text = o.xn;
		html += "<td>";
		html += "<label>";
		if(jQuery("#zqlx").val() == "4"){//学年单选
			html += "<input type='radio' value='" + value + "' name='xnView'/>";
		}else{
			html += "<input type='checkbox' value='" + value+ "' name='xnView'/>";
		}
		html += text;
		html += "</label>";
		html += "</td>";
		if(((i - m)+1)%2 == 0){
			html += "</tr>";
		}
	}
	
	var k = xnList.length - m;
	if(k %2 > 0){
		while(k % 2 != 0){
			html += "<td>&nbsp;</td>";
			html += "<td>&nbsp;</td>";
			k++;
		}
		html += "</tr>";
	}
	jQuery("#xmList").html(html);
	
}

function createHtmlForZhcp() {
	var zhcpTjxmList = dataObj['zhcpTjxmList'];
	var html = "";
	var m = 0;
	for ( var i = 0; i < zhcpTjxmList.length; i++) {
		var o = zhcpTjxmList[i];
		if(o.dm == ""){
			m++;
			continue;
		}
		if((i - m)%2 == 0){
			html += "<tr>";
		}
		
		var value = o.dm;
		var text = o.mc;
		html += "<td>";
		html += "<label>";
		html += "<input type='radio' value='" + value + "' name='xnView'/>";
		html += text;
		html += "</label>";
		html += "</td>";
		if(((i - m)+1)%2 == 0){
			html += "</tr>";
		}
	}	
	var k = zhcpTjxmList.length - m;
	if(k %2 > 0){
		while(k % 2 != 0){
			html += "<td>&nbsp;</td>";
			k++;
		}
		html += "</tr>";
	}
	jQuery("#xmList").html(html);
}

/*保存*/
function saveForm() {
	var xqList = dataObj['xqList'];

	var xn = "";
	var xnView = "";
	var flag = false;
	jQuery("input[name=xnView]:checked").each(function(index){
		if(flag){
			xn += ",";
		}else{
			flag = true;
		}
		xn += jQuery(this).val();
	});
	
	xnView = xn;
	var zqlx = jQuery("#zqlx").val();
	if(zqlx == "3"){//综测条件项目
		xn = jQuery("input[name=xnView]:checked").val();
		if(xn == null || xn == ""){
			showAlert("请勾选记录！");
			return false;
		}
		var zhcpTjxmList = dataObj['zhcpTjxmList'];
		for ( var i = 0; i < zhcpTjxmList.length; i++) {
			var o = zhcpTjxmList[i];
			if(o.dm == xn){
				xnView = o.mc;
				break;
			}			
		}		
	}else if(zqlx == '4' || zqlx == '5'){//学年单选、多选
		var reg = new RegExp(";","g");
		xnView = xnView.replace(reg,"");
	}else{
		for ( var i = 0; i < xqList.length; i++) {
			var xqObj = xqList[i];
			var reg = new RegExp(";" + xqObj.xqdm,"g");
			xnView = xnView.replace(reg,"" + xqObj.xqmc);
		}
	}
	var api = frameElement.api,W = api.opener;
	var parentTd = jQuery(W.tjszDialog.content.document).find("[name=xnCur][value=1]").parent();
	parentTd.find("[name=xn]").val(xn);
	parentTd.find("[name=xnView]").val(xnView);
//	W.tjszDialog.focus();
	iFClose();	
}
