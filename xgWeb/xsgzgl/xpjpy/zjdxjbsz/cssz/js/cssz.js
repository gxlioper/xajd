//查询
function searchRs(){
	var map = {};
	jQuery("#dataTable").reloadGrid(map);
}

/*全选*/
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

/*点击评奖开关按钮，起止时间和评奖周期连动*/
function displayQzsj(){
	var pjkg = jQuery(":radio:checked").val();
	if (pjkg == 1){
		//开启
		jQuery("#qzsjTr").show();
		jQuery(".toolbox").css("display","block");
	} else {
		//关闭
		jQuery("#qzsjTr").hide();
		jQuery(".toolbox").hide();
	}
}

jQuery(function(){
	displayQzsj();
	jQuery(":radio").bind("click",function(){
		saveCssz("pjkg",jQuery(this).val());
	});
	
	jQuery("#xn").bind("change",function(){
		jQuery.ajaxSetup({async:false});
		if (jQuery(this).val() == ""){
			return ;
		}
		saveCssz("xn",jQuery(this).val());
		refreshForm("xpjpy_jbsz_cssz.do");
	});
});

/*保存参数设置*/
function saveCssz(zdKey,zdValue){
	if (zdValue != null){
		jQuery.post("xpjpy_cssz.do?method=saveCssz",{"zdKey":zdKey,"zdValue":zdValue},function(data){
			if(data["message"] != null){
				alert(data["message"]);
			}
		});
	}
}

/*参数设置增加综测项目*/
function add(){
	var url = "xpjpy_cssz.do?method=addZcxm";
	var title = "增加综测项目";
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("综测项目已被使用无法增加！");
		return false;
	}
	showDialog(title,440,360,url);
}

/*项目类型radio点击事件*/
function changeZcxm(){
	var xmlx = jQuery("input[name='xmlx']:checked").val();
	if (xmlx == "0"){
		//等级
		jQuery("#zxfzTr").hide();//隐藏最小分值TR
		jQuery("#zxfz").val('');//最小分值置空
		jQuery("#zdfzTr").hide();//隐藏最大分值TR
		jQuery("#zdfz").val('');//最大分值置空
		jQuery("#pxTr").hide();//隐藏排序TR
		jQuery("#px").val('');//排序置空
		jQuery("#xmdjTable").show();
	}else{
		//分值
		jQuery("#zxfzTr").show();
		jQuery("#zdfzTr").show();
		jQuery("#pxTr").show();
		jQuery("#xmdjTable").hide();
		jQuery("#tbody_zcxmLx").html(jQuery("#tbody_zcxmLx").html());
	}
}

/*综测项目增加_项目类型_等级，增加行*/
function addRow() {
	var html = "";
	var qqxsTrLen = jQuery("#tbody_zcxmLx tr").length;
	html += "<tr><td><input type='checkbox' id='checkbox_" + qqxsTrLen
			+ "'/></td>";
	html += "<td name='mc'><input type='text' maxlength='10' name='mc' id='mc'/></td>";
	html += "<td><input type='text' name='px' id='px' maxlength='3' onkeyup=\"value=value.replace(/[^\\d]/g,\'\');\"/></td></tr>";
	jQuery("#tbody_zcxmLx").append(html);
}

/*综测项目增加_项目类型_等级，删除行*/
function delRow() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlert("请选择需要删除的数据！");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}

/*增加保存*/
function saveForAdd(){
	var zxfz = jQuery("#zxfz").val();
	var zdfz = jQuery("#zdfz").val();
	if (check()){
		djToJson();// 封装奖项信息
		if(parseInt(zxfz)> parseInt(zdfz)){
			showAlert("最小分值不能大于最大分值！");
			return false;
		}
		var url = "xpjpy_cssz.do?method=saveForAdd";
		ajaxSubFormWithFun("zjdxCsszForm", url, function(data) {
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
}

/*保存时检验*/
function check() {
	var flag=true;
	var checkId = 'xmmc-fjdm';
	var id = checkId.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			flag= false;
		}
	}
	var xmlx=jQuery("input[name='xmlx']:checked").val();
	if(xmlx == ''||xmlx == null){
		flag=false;
	}
	if("0" == xmlx){
		jQuery.each(jQuery("#tbody_zcxmLx tr"), function(i, n) {
			var mc = jQuery(n).find("input[name=mc]").val();
			if(mc==null||mc==""){
				flag=false;
			}
		});
	}
	if("1" == xmlx){
		if(jQuery("#zxfz").val()==''){
			flag=false;
		}
		if( jQuery("#zdfz").val()==''){
			flag=false;
		}
	}
	if(!flag){
		showAlert("请将带*项填写完整！");
		flag= false;
		return flag;
	}
	return flag;
}


//封装点击等级事件值
function djToJson() {
	var xmlx = jQuery("input[name='xmlx']:checked").val();
	if ("0" == xmlx) {
		var objArr = [];
		jQuery.each(jQuery("#tbody_zcxmLx tr"), function(i, n) {
			var obj = {};
			var mc = jQuery(n).find("input[name=mc]").val();
			var px = jQuery(n).find("input[name=px]").val();
			obj.mc = mc;
			obj.px = px;
			objArr.push(obj);
		});
		var objStr = JSON.stringify(objArr);
		jQuery("#objStr").val(objStr);
	}
}

function del(){
//	var len = jQuery("input:[name='nextOne']:checked").length;//选中CheckBox的个数
//	var trLen = jQuery("input:[name='nextOne']").length;//所有对象为nextOne的总个数
//	var tr = document.getElementsByName("nextOne");//选择所有name="'nextOne'"的对象，返回数组
//	var ids = "";
//	if(len == 0 ){
//		showAlertDivLayer("请选择您要删除的记录！");
//		return false;
//	}
//	for(i=0;i<trLen;i++){
//		if(tr[i].checked == true){
//			ids+=tr[i].value+",";
//		 }
//	}
//	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
//		jQuery.post("xpjpy_cssz.do?method=delZcxm",{values:ids.toString()},function(data){
//			showAlertDivLayer(data["message"]);
//			refreshForm("xpjpy_jbsz_cssz.do");
//		},'json');
//	}});
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("综测项目已被使用无法删除！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xpjpy_cssz.do?method=delZcxm",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

/*
 *修改方法
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var zcxmCount = jQuery("#zcxmCount").val();
	if(zcxmCount > 0){
		showAlertDivLayer("综测项目已被使用无法修改！");
		return false;
	}
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}else{
		var url = 'xpjpy_cssz.do?method=updateZcxm&xmdm='+rows[0]["xmdm"];
		var title = "修改综测项目";
		showDialog(title,440,360,url);
	}
}

/*修改保存*/
function saveForUpdate(){
	var zxfz = jQuery("#zxfz").val();
	var zdfz = jQuery("#zdfz").val();
	if (check()){
		djToJson();// 封装奖项信息
		if(parseInt(zxfz)> parseInt(zdfz)){
			showAlert("最小分值不能大于最大分值！");
			return false;
		}
		var url = "xpjpy_cssz.do?method=saveForUpdate";
		ajaxSubFormWithFun("zjdxCsszForm", url, function(data) {
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
}
