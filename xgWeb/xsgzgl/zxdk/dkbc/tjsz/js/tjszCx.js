var dataObj;
var tbodyTr = "#dataTable>tbody>tr";//tbody下所有行
var xnTip = "点击选择...";
jQuery(function() {
	onShow();
});

function onShow(){
	var url = "dkbc_tjsz.do?method=xmwhTjszSy";
	url += "&xmdm=" + jQuery("#xmdm").val();
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		dataObj = data;
		setInit();//设置初值
	}, 'json');
	
	//setSpzt();//根据审核状态进行设置
}

//设置初值
function setInit(){
	var tjszList = dataObj['tjszList'];
	var tjgxList = dataObj['tjgxList'];//条件关系对照
	var xqList = dataObj['xqList'];
	var zhcpTjxmList = dataObj['zhcpTjxmList'];//综测条件项目

	if(tjszList == null || tjszList.length == 0){
		var sTr = "<tr id='nodata' align='center'>";
		sTr += "<td colSpan='7' >无显示数据</td>";
		sTr += "</tr>";
		jQuery("#dataTable>tbody").html(sTr);
	}	
	
	if(tjszList != null){
		for ( var i = 0; i < tjszList.length; i++) {
			var o = tjszList[i];
			createRow(dataObj);
			jQuery(jQuery(tbodyTr)[i]).find("select[name=tjdm]").val(o.tjdm);
			jQuery(jQuery(tbodyTr)[i]).find("select[name=tjdm]").change();
			jQuery(jQuery(tbodyTr)[i]).find("select[name=gxdm]").val(o.tjgx);
			jQuery(jQuery(tbodyTr)[i]).find("select[name=gxdm]").change();
			
			//条件值赋值
			for ( var j = 0; j < tjgxList.length; j++) {
				var tjgxObj = tjgxList[j];
				if(o.tjdm == tjgxObj.tjdm && o.tjgx == tjgxObj.gxdm){
					var gxz = tjgxObj.gxz;
					if(tjgxObj.gxlx == '1' || tjgxObj.gxlx == '2' || tjgxObj.gxlx == '4'){//数字文本框，下拉框，小数文本框
						jQuery(jQuery(tbodyTr)[i]).find("[name=tjz]").val(o.tjz);
					}else if(tjgxObj.gxlx == '3'){//checkbox，tjz保存格式为逗号分割
						var arr = o.tjz.split(",");
						jQuery.each(arr,function(index,value){
							jQuery(jQuery(tbodyTr)[i]).find("[name=tjz][value='"+value+"']").attr("checked","checked");
						});
					} else if (tjgxObj.gxlx == '5') {// checkbox，tjz保存格式为逗号分割,弹层复选框
						jQuery(jQuery(tbodyTr)[i]).find("[name=tjz]").val(o.tjz);
						var gxzs = "";
						if (gxz != null) {
							gxzs = gxz.split("|");
						}
						var tjzView = "";
						for ( var k = 0; k < gxzs.length; k = k + 2) {
							var dm = gxzs[k];
							var mc = "";
							if(o.tjz.indexOf(dm) > -1){
								if (gxzs[k + 1] != undefined) {
									mc = gxzs[k + 1];
								}else{
									mc = dm;
								}
								if(tjzView != ""){
									tjzView += ",";
								}
								tjzView += mc;
							}
						}
						jQuery(jQuery(tbodyTr)[i]).find("[name='tjzView']").val(tjzView);
					}
					break;
				}
			}
			
			var tjList = dataObj['tjList'];
			var zqlx = "";//周期类型		
			for ( var j = 0; j < tjList.length; j++) {
				var tjObj = tjList[j];
				if(tjObj.tjdm == o.tjdm){
					zqlx = tjObj.zqlx;
					break;
				}
			}			
			var xn = o.xn;
			var xnView = xn;
			
			
			if(zqlx == "3"){//综测条件项目
				for ( var j = 0; j < zhcpTjxmList.length; j++) {
					var zhcpTjxmObj = zhcpTjxmList[j];
					if(zhcpTjxmObj.dm == xn){
						xnView = zhcpTjxmObj.mc;
						break;
					}
				}
			}else if(zqlx == '4' || zqlx == '5'){//学年单选、多选
				var reg = new RegExp(";","g");
				if(xnView != null && xnView != ""){
					xnView = xnView.replace(reg,"");
				}
			}else{
				for ( var j = 0; j < xqList.length; j++) {
					var xqObj = xqList[j];
					var reg = new RegExp(";" + xqObj.xqdm,"g");
					if(xnView != null && xnView != ""){
						xnView = xnView.replace(reg," " + xqObj.xqmc);
					}
				}
			}

			jQuery(jQuery(tbodyTr)[i]).find("[name=xn]").val(xn);
			if(xnView != null && xnView != ""){
				jQuery(jQuery(tbodyTr)[i]).find("[name=xnView]").val(xnView);
			}
			
			//应用范围
			var yyfw = o.bjdl;
			jQuery(jQuery(tbodyTr)[i]).find("[name=yyfw]").val(yyfw);
		}
	}
}

//条件，显示关系
function tjSet(tjObj){
	var tjList = dataObj['tjList'];
	var tjdm = jQuery(tjObj).val();
	var gxdmHtml = createGx(tjdm);
	jQuery(tjObj).parent().parent().find("td[name=gxdmTd]").html(gxdmHtml);
	
	//条件说明
	var tjsmHtml = "";
	var zqlx = "";
	for ( var i = 0; i < tjList.length; i++) {
		var o = tjList[i];
		if(tjdm == o.tjdm){
			tjsmHtml = o.tjsm;
			zqlx = o.zqlx;
			break;
		}
	}
	if(zqlx != "0" && zqlx != "1" && zqlx != "2" && zqlx != "3" && zqlx != "4" && zqlx != "5"){
		zqlx = "2";
	}
	jQuery(tjObj).parent().parent().find("td[name=tjsmTd]").html(tjsmHtml);
	//依赖周期联动
	jQuery(tjObj).parent().parent().find("td[name=ylzqTd]").html(createXn(zqlx));
	
	jQuery(tjObj).change(function(){
		gxSet(jQuery(this).parent().parent().find("select[name=gxdm]"));//显示关系
	});
}

//关系联动，显示条件值
function gxSet(gxObj){
	var gxdm = jQuery(gxObj).val();
	var tjdm = jQuery(gxObj).parent().parent().find("select[name=tjdm]").val();
	var tjzHtml11 = createTjz(tjdm,gxdm);
	jQuery(gxObj).parent().parent().find("td[name=tjzTd]").html(tjzHtml11);
	
	jQuery(gxObj).change(function(){
		var tjzHtml = createTjz(tjdm,jQuery(this).val());
		jQuery(this).parent().parent().find("td[name=tjzTd]").html(tjzHtml);
	});
}

/*
 *生成一行记录 
 * @return
 */
function createRow(dataObj){
	var html = "";
	var tjList = dataObj['tjList'];
	var gxList = dataObj['gxList'];
	var tjgxList = dataObj['tjgxList'];
	var tjszList = dataObj['tjszList'];
	var xnList = dataObj['xnList'];
	var xqList = dataObj['xqList'];
	var bjdlList = dataObj['bjdlList'];
	var sfqyList = dataObj['sfqyList'];
	
	var tjObj = tjList[0];
	html += "<tr>";
	//复选框
	html += "<td>";
	html += "<input type='checkbox' name='grid_key'>";
	html += "</td>";
	//条件
	html += "<td>";
	html += createTj();
	html += "</td>";
	//关系
	html += "<td name='gxdmTd'>";
	html += "";
	html += "</td>";
	//条件值
	html += "<td name='tjzTd'>";
	html += "";
	html += "</td>";
	//应用学年学期
	html += "<td name='ylzqTd'>";
	html += "";
	html += "</td>";
	//应用范围
	html += "<td>";
	html += createYyfw();
	html += "</td>";
	//条件说明
	html += "<td name='tjsmTd'>";
	html += "";
	html += "</td>";
	
	html += "</tr>";
	
	jQuery("#nodata").remove();
	jQuery("#dataTable>tbody").append(html);
	
	var length = jQuery(tbodyTr).length;
	var tjObj = jQuery(jQuery(tbodyTr)[length - 1]).find("select[name=tjdm]");
	
	jQuery(tjObj).change(function(){
		tjSet(jQuery(this));//显示关系
	});
	
	tjSet(tjObj);//显示关系
	
	var gxObj = jQuery(jQuery(tbodyTr)[length - 1]).find("select[name=gxdm]");

	gxSet(gxObj);//显示条件值
}

//生成条件cell
function createTj(){
	var tjList = dataObj['tjList'];
	var html = "";
	html += "<select name='tjdm' class='tjdm'>";
	for ( var i = 0; i < tjList.length; i++) {
		var o = tjList[i];
		var name = o.tjmc;
		var value = o.tjdm;
		html += "<option value='"+value+"'>"+name+"</option>";
	}
	html += "</select>";
	return html;
}

//生成关系cell
function createGx(tjdm){
	var gxList = dataObj['gxList'];
	var tjgxList = dataObj['tjgxList'];
	
	var html = "";
	html += "<select name='gxdm' class='gxdm'>";
	for ( var i = 0; i < tjgxList.length; i++) {
		var name = "";
		var value = "";
		var o = tjgxList[i];
		if(o.tjdm == tjdm){
			value = o.gxdm;
			for ( var j = 0; j < gxList.length; j++) {
				var gxObj = gxList[j];
				if(gxObj.gxdm == o.gxdm){
					name = gxObj.gxmc;
					break;
				}
			}
			html += "<option value='"+value+"'>"+name+"</option>";
		}
	}	
	html += "</select>";
	return html;
}


//生成条件值
function createTjz(tjdm,gxdm){
	var tjgxList = dataObj['tjgxList'];
	var html = "";
	for ( var i = 0; i < tjgxList.length; i++) {
		var name = "";
		var value = "";
		var o = tjgxList[i];
		if(o.tjdm == tjdm && o.gxdm == gxdm){
			html += "<input type='hidden' name='gxlx' value='"+o.gxlx+"'>";//关系类型
			if(o.gxlx == "1"){//数字文本框类型
				html += createTjzLx1();
			}else if(o.gxlx == "2"){//下拉列表
				html += createTjzLx2(o.gxz);
			}else if(o.gxlx == "3"){//checkbox多选框
				html += createTjzLx3(o.gxz);
			}else if(o.gxlx == "4"){//小数文本框
				html += createTjzLx4(o.gxz);
			} else if (o.gxlx == "5") {// checkbox多选框-弹层选择
				html += createTjzLx5(o.gxz);
			}
		}
	}
	return html;
}

//生成条件值，类型1，数字类型
function createTjzLx1(){
	var html = "";
	html += "<input type='text' name='tjz' class='tjz1' maxlength='10'>";
	return html;
}


//生成条件值，类型1，小数类型
function createTjzLx4(){
	var html = "";
	html += "<input type='text' name='tjz' class='tjz1' maxlength='10'>";
	return html;
}

//生成条件值，类型2，下拉列表
function createTjzLx2(gxz){
	var html = "";
	var gxzs = "";
	if(gxz != null){
		gxzs = gxz.split("|");
	}
	html += "<select name='tjz' class='tjz2'>";
	for ( var i = 0; i < gxzs.length; i=i+2) {
		var name = gxzs[i];
		if(gxzs[i + 1] != undefined){
			name = gxzs[i + 1];
		}
		html += "<option value='"+gxzs[i]+"'>"+name+"</option>";
	}
	html += "</select>";
	return html;
}

//生成条件值，类型3，checkbox
function createTjzLx3(gxz){
	var html = "";
	var gxzs = "";
	if(gxz != null){
		gxzs = gxz.split("|");
	}
	for ( var i = 0; i < gxzs.length; i=i+2) {
		var name = gxzs[i];
		if(gxzs[i + 1] != undefined){
			name = gxzs[i + 1];
		}
		html += "<label>";
		html += "<input type='checkbox' name='tjz' value='"+gxzs[i]+"' >";
		html += name + "</label><br/>";
	}
	return html;
}

//生成条件值，类型5，checkbox多选框-弹层选择
function createTjzLx5(gxz) {
	var html = "";
	html += "<input type='hidden' name='tjzCur' value='0'/>";
	html += "<input type='hidden' name='tjz' value=''/>";
	html += "<input type='text' name='tjzView' class='tjzView' onfocus='tjzSelect(this,\""
			+ gxz + "\");' value='"+xnTip+"'/>";
	return html;
}

//条件值，类型5，checkbox多选框-弹层
function tjzSelect(obj, gxz) {
	var tjz = jQuery(obj).parent().find("[name=tjz]").val();
	jQuery("[name=tjzCur]").val("0");
	jQuery(obj).parent().find("[name=tjzCur]").val("1");
	jQuery("[name=curGxz]").remove();
	var sHtml = "<input type='hidden' name='curGxz' value='" + gxz + "'/>";
	jQuery(obj).parent().append(sHtml);
	var xmdm = jQuery("#xmdm").val();
	var url = 'dkbc_tjsz.do?method=xmwhTjszTjzDiv';
	url += "&xmdm=" + xmdm;
	url += "&tjzVal=" + tjz;
	var title = "条件值选择";
	showDialog(title, 600, 400, url);
}


//生成学年cell
function createXn(zqlx){
	var xnList = dataObj['xnList'];
	var xqList = dataObj['xqList'];
	var html = "";
	html += "<input type='hidden' name='xnCur' value='0'/>";
	html += "<input type='hidden' name='xn' value=''/>";
	if(zqlx == '1'){//学年学期单选
		html += "<input type='text' name='xnView' class='xnView' onfocus='xnSelect(this,"+zqlx+");' value='"+xnTip+"'/>";
	}else if(zqlx == '2'){//学年学期多选
		html += "<input type='text' name='xnView' class='xnView' onfocus='xnSelect(this,"+zqlx+");' value='"+xnTip+"'/>";
	}else if(zqlx == '3'){//
		html += "<input type='text' name='xnView' class='xnView' onfocus='xnSelect(this,"+zqlx+");' value='"+xnTip+"'/>";
	}else if(zqlx == '4' || zqlx == '5'){//学年单选、多选
		html += "<input type='text' name='xnView' class='xnView' onfocus='xnSelect(this,"+zqlx+");' value='"+xnTip+"'/>";
	}else{//无
		html += "无";
	}	
	return html;
	
}

//学年弹层
function xnSelect(obj,zqlx){
	obj.blur();
	var tjdm = jQuery(obj).parent().parent().find(".tjdm option:selected").val();
	var xn = jQuery(obj).parent().find("[name=xn]").val();
	jQuery("[name=xnCur]").val("0");
	jQuery(obj).parent().find("[name=xnCur]").val("1");
	var xmdm = jQuery("#xmdm").val();
	var url = 'dkbc_tjsz.do?method=xmwhTjszXn';
	url += "&xmdm=" + xmdm;
	url += "&xnVal=" + xn;
	url += "&zqlx=" + zqlx;
	url += "&tjdm=" + tjdm;
	var title = "依赖学年学期";
	showDialog(title, 600, 400, url);
}

/*保存*/
function update() {
	var length = jQuery(tbodyTr).not("#nodata").length;
	if(length == 0 ){
		showAlert("没有需要保存的记录！");
		return false;
	}
	
	var json = "{data:[";
	var flag = false;
	var result = false;//jQuery跳出循环
	var message = "";
	var tjdms = "";
	jQuery(tbodyTr).each(function(index){//
		var tjdm = jQuery(this).find("select[name=tjdm]").val();
		var gxdm = jQuery(this).find("select[name=gxdm]").val();
		var tjz = jQuery.trim(jQuery(this).find("[name=tjz]").val());
		var xn = jQuery(this).find("[name=xn]").val();
		var yyfw = jQuery(this).find("select[name=yyfw]").val();
		var sfqy = jQuery(this).find("input:radio[name=sfqy]:checked").val();
		var gxlx = jQuery(this).find("input[name=gxlx]").val();
		if(tjdm == ""){
			message = "第"+(index + 1)+"行记录[条件]不允许为空！";
			result = true;
			return false;
		}
		if(tjdms.indexOf(gxdm + ":" + tjdm + ":" + xn) > -1){
			message = "第"+(index + 1)+"行记录[条件]重复！";
			result = true;
			return false;
		}
		tjdms += gxdm + ":" + tjdm  + ":" + xn + ",";		
		
		if(gxlx == "1"){//数字类型
			if(tjz == ""){
				message = "第"+(index + 1)+"行记录[条件值]不允许为空！";
				result = true;
				return false;
			}
			var reg = /^\d{0,10}$/;
			if(!tjz.match(reg) ){
				message = "第"+(index + 1)+"行记录[条件值]格式不符，请输入数字格式！";
				result = true;
				return false;
			}
		}else if(gxlx == "2" || gxlx == "5"){//下拉框,弹层复选框
			if(tjz == ""){
				message = "第"+(index + 1)+"行记录[条件值]不允许为空！";
				result = true;
				return false;
			}
		}else if(gxlx == "3"){//checkbox
			tjz = "";				
			var flag1 = false;
			jQuery(this).find("input:checkbox[name=tjz]:checked").each(function(index){
				if(flag1){
					tjz += ",";
				}else{
					flag1 = true;
				}
				tjz += jQuery(this).val();
			});
			if(tjz == ""){
				message = "第"+(index + 1)+"行记录[条件值]不允许为空！";
				result = true;
				return false;
			}
		}else if(gxlx == "4"){//小数类型
			if(tjz == ""){
				message = "第"+(index + 1)+"行记录[条件值]不允许为空！";
				result = true;
				return false;
			}
			var reg = /^\d+\.?\d*$/;
			if(!tjz.match(reg) ){
				message = "第"+(index + 1)+"行记录[条件值]格式不符，请输入整数或小数格式！";
				result = true;
				return false;
			}
		}
		
		var tjList = dataObj['tjList'];
		var zqlx = "";
		for ( var i = 0; i < tjList.length; i++) {
			var o = tjList[i];
			if(tjdm == o.tjdm){
				if((o.zqlx == "3" || tjdm == 'ZZTJ_11318') && (xn == null || xn == "")){
					message = "第"+(index + 1)+"行记录[依赖学年学期]不允许为空！";
					result = true;
					return false;
				}
			}
		}		
		
		if(flag){
			json += ",";
		}else{
			flag = true;
		}
		
		if(sfqy == undefined){
			sfqy = "1";
		}
		sfqy = "1";
		bjdl = "all";

		json += "{";
		json += "tjdm:'" + tjdm + "'";
		json += ",tjgx:'" + gxdm + "'";
		json += ",tjz:'" + tjz + "'";
		json += ",xn:'" + xn + "'";
		json += ",xq:'01'";
		json += ",yyfw:'" + yyfw + "'";
		json += ",sfqy:'" + sfqy + "'";
		json += "}";
	});
	json += "]}";
	
	if(result){//验证失败，重新输入
		showAlert(message);
		 return false;		
	}

	var url = "dkbc_tjsz.do?method=xmwhTjszXg&type=save";
	jQuery.post(url, {
		xmdm:jQuery("#xmdm").val(),
		json:json
	}, function(data) {
		if(data["success"] == false){
			showConfirm(data["message"]);
		}else{
			showAlert(data["message"],{},{"clkFun": function(tag) {
		//		refershParent();
			}});
		}
	}, 'json');
}

function add(){	
	createRow(dataObj);
}

function del(){
	var length = jQuery("#dataTable input:checkbox[name=grid_key]:checked").length;
	if(length == 0){
		showAlert("请选择您要删除的记录！");
		return false;
	}
	showConfirmDivLayer("是否确定删除勾选的记录？",{"okFun":function(){
		jQuery("#dataTable input[name=grid_key]:checked").each(function(index){
			jQuery(this).parent().parent().remove();
			var tjdm = jQuery(this).parent().parent().find("[name=tjdm]").val();
			var url = "dkbc_tjsz.do?method=xmwhTjszSc";
			jQuery.post(url, {
				xmdm : jQuery("#xmdm").val(),
				tjdm : tjdm
			}, function(data) {
			}, 'json');
		});	
	}});
}
//应用范围cell
function createYyfw(){
	var yyfwList = dataObj['bjdlList'];
	var html = "";
	html += "<select name='yyfw' class='yyfw'>";
	html += "<option value='all'>所有</option>";
	for ( var i = 0; i < yyfwList.length; i++) {
		var o = yyfwList[i];
		html += "<option value='"+o.lxdm+"'>"+o.lxmc+"</option>";
	}
	html += "</select>";
	return html;
}