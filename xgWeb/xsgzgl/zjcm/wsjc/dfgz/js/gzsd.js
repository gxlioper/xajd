var dataObj;
var tbodyTr = "#dataTable>tbody>tr";// tbody下所有行

jQuery(function() {
	onShow();
});

function onShow() {
	var url = "cjWsfDfgz.do?method=gzsz";
	jQuery.post(url, {
		xn : jQuery("#xn").val(),
		xq : jQuery("#xq").val(),
		ccny : jQuery("#ccny").val()
	}, function(data) {
		dataObj = data;
		setInit();// 设置初值
		}, 'json');

	// setSpzt();//根据审核状态进行设置
}



//设置初值
function setInit() {
	var pfzszList = dataObj['pfzszList'];
	if (pfzszList == null || pfzszList.length == 0) {
		var sTr = "<tr id='nodata' align='center'>";
		sTr += "<td colSpan='4'>无显示数据</td>";
		sTr += "</tr>";
		jQuery("#dataTable>tbody").html(sTr);
	}

	if (pfzszList == null) {
		return;
	}
	var qxCheck = true; //全选
	for ( var i = 0; i < pfzszList.length; i++) {
		var o = pfzszList[i];
		createRow(dataObj);
		jQuery(jQuery(tbodyTr)[i]).find("select[name=pfzid]").val(o.pfzid);
		jQuery(jQuery(tbodyTr)[i]).find("select[name=pfzid]").change();
		
		// 条件值赋值
		
		// 抽查比例
		jQuery(jQuery(tbodyTr)[i]).find("[name=ccbl]").val(o.ccbl);		
		
		// 是否包含毕业班
		var bhbyb = o.bhbyb;
		if("1"==bhbyb){
			jQuery(jQuery(tbodyTr)[i]).find("[name=bhbyb]").attr("checked","checked");
		}
		
		if(false==qxCheck||"0"==bhbyb){
			qxCheck = false;
		}
	}
	
	if(qxCheck){
		jQuery("[name = pl_bhbyb]:checkbox").attr("checked", true);
	}else{
		jQuery("[name = pl_bhbyb]:checkbox").attr("checked", false);
	}
}


/*
 * 生成一行记录 @return
 */
function createRow() {
	var html = "";
	var pfzList = dataObj['pfzList'];
	var pfzszList = dataObj['pfzszList'];

	var tjObj = pfzList[0];
	html += "<tr>";
	// 复选框
	html += "<td>";
	html += "<input type='checkbox' name='grid_key'>";
	html += "</td>";
	// 评分组
	html += "<td>";
	html += createPfz();
	html += "</td>";
	// 抽查比例
	html += "<td name='ccblTd'>";
	html += "<input type='text' name='ccbl' style='width:30px;' maxlength='3' value='' onblur=\"checkInputNum(this);\" onkeyup='checkInputNum(this);' >";
	html += "%</td>";
	// 是否包含毕业班
	html += "<td name='sfbhbybTd'>";
	html += "<input type='checkbox' name='bhbyb'>";
	html += "</td>";

	html += "</tr>";

	jQuery("#nodata").remove();
	jQuery("#dataTable>tbody").append(html);

}

//生成评分组cell
function createPfz() {
	var pfzList = dataObj['pfzList'];
	var html = "";
	html += "<select style='width:200px' name='pfzid'>";
	for ( var i = 0; i < pfzList.length; i++) {
		var o = pfzList[i];
		var name = o.pfzmc;
		var value = o.pfzid;
		html += "<option value='" + value + "'>" + name + "</option>";
	}
	html += "</select>";
	return html;
}


function del() {
	var length = jQuery("#dataTable input:checkbox[name=grid_key]:checked").length;
	if (length == 0) {
		showAlert("请选择您要删除的记录！");
		return false;
	}
	jQuery("#dataTable input[name=grid_key]:checked").each(
			function(index) {
				jQuery(this).parent().parent().remove();
			});
}




//评分对象设置保存
function update() {
	
	var length = jQuery(tbodyTr).not("#nodata").length;
	if (length == 0) {
		showAlert("没有需要保存的记录！");
		return false;
	}
	
	var flg=true;
	var nullsFlag=true;
	var pfzJson= [];
	var samePfz="";
	var pfzmc="";
	
	jQuery.each(jQuery(tbodyTr).not("#nodata"),function(i,n){
		var obj = {};
			var pfzid = jQuery(n).find("select[name=pfzid] option:selected").val();
			  pfzmc = jQuery(n).find("select[name=pfzid] option:selected").text();
			 var ccbl = jQuery(n).find("input[name=ccbl]").val();
			 var bhbyb = 0 ;
			 if(jQuery(n).find("input[name=bhbyb]").is(":checked")){
				 bhbyb= 1;
			 }
			
			var checkMsg=pfzid;
			//验证评分组是否重复添加
			if(samePfz.indexOf(checkMsg) > -1){
				flg=false;
				return false;
			}else{
				samePfz+="&"+pfzid;
			}
			//验证必填项
			if(""==ccbl||""==pfzid){
				nullsFlag=false;
				return false;
			}
			obj.pfzid=pfzid;
			obj.ccbl=ccbl;
			obj.bhbyb=bhbyb;
			pfzJson.push(obj);
	});
	if(!flg){
		showAlertDivLayer("评分组【<font class='red'>"+pfzmc+"</font>】已存在，请勿重复添加！");
		return false;
	}
	if(!nullsFlag){
		showAlertDivLayer("请将<span class='red'>*</span>必填项填写完整！");
		return false;
	}
	var pfzJson = JSON.stringify(pfzJson);
	jQuery("#pfzJson").val(pfzJson);
	var map = {};
	map["xn"]=jQuery("#xn").val();
	map["xq"]=jQuery("#xq").val();
	map["ccny"]=jQuery("#ccny").val();
	map["dfszid"]=jQuery("#dfszid").val();
	var url = "cjWsfDfgz.do?method=dfgzXg&type=save";
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("cjWsfDfgz.do?method=dfgzXg",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("当前月份已有抽查数据，是否要覆盖？",{"okFun":function(){
					
					ajaxSubFormWithFun("DfgzForm", url, function(data) {
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
				}});
			} else {
				ajaxSubFormWithFun("DfgzForm", url, function(data) {
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
		});
	jQuery.ajaxSetup({async:true});
	
}

function pcCheck(obj){
	
	if(obj.checked){
		jQuery("[name = bhbyb]:checkbox").attr("checked", true);
	}else{
		jQuery("[name = bhbyb]:checkbox").attr("checked", false);
	}
}
