function searchRs() {
	var map = getSuperSearch();
	map["xydm"]=jQuery("#xydm").val();
	map["js"]=jQuery("#js").val();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 检查项Link
 * @return
 */
function jcxLink(cellValue, rowObject){
	var jcxmc = "";
	if(rowObject['wsjc'] == "1"){
		jcxmc +="卫生检查、";
	}
	if(rowObject['aqjc'] == "2"){
		jcxmc +="安全检查、";
	}
	if(rowObject['jljc'] == "3"){
		jcxmc +="纪律检查、";
	}
	return "<font color='blue'>"+jcxmc.substring(0,jcxmc.length-1)+"</font>";
}

/**
 * 增加
 * @return
 */
function add(){
	var url = "gyjc_jcrc.do?method=add";
	showDialog("增加检查日程", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();	
	}})
}

/**
 * 修改
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "gyjc_jcrc.do?method=update&guid="+rows[0]["guid"];
	showDialog("修改检查日程", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();	
	}});
}

/**
 * 计算上限百分比
 * @param obj
 * @return
 */
function calBfbOver(obj){
	var inputBfb = parseFloat(jQuery(obj).val());
	var ztxbl = parseFloat(jQuery(obj).parent().find("[name='ztxbl']").val());
	var isUpate = jQuery("#guid").length == 1 ? true :false;
	if(isUpate){
		var bcjcbl = parseFloat(jQuery(obj).parent().find("[name='bcjcbl']").val());
		ztxbl = parseFloat(ztxbl)-parseFloat(bcjcbl);
	}
	var sxBfb =parseFloat((parseFloat(100) - parseFloat(ztxbl)));
	if(!isUpate && ztxbl == 100){
		sxBfb = 100;
	}
	if((sxBfb < parseFloat(inputBfb) && (ztxbl != parseFloat(100))) || inputBfb > 100){
		showAlert("超过可输入百分比上限"+sxBfb+"%",{},{"clkFun":function(){
				obj.focus();
		}});
	}else{
		return;
	}
}

/**
 * 保存检查日程
 * @return
 */
function saveJcrc(){
	var ids = "ccrq-jzrq";
	if(!checkNotNull(ids)){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	if(jQuery("input[type='checkbox']:checked").length == 0){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	var flag = false;
	jQuery("[name='jcbl']").each(function(){
		if(jQuery.trim(this.value) != ""){
			flag = true;
			return;
		}
	})
	if(!flag){
		return showAlert("请至少填写一条抽查记录！");
	}
	var url = "gyjc_jcrc.do?method=saveJcRc";
	ajaxSubFormWithFun("JcrcForm", url, function(data) {
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

/**
 * 只修改日期
 * @return
 */
function saveJcrcUpdate(){
	var url = "gyjc_jcrc.do?method=saveJcrcUpdate";
	ajaxSubFormWithFun("JcrcForm", url, function(data) {
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

/**
 * 删除
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gyjc_jcrc.do?method=delJcrc",{rcid:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//提交记录数查看
function tjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"ytjscx"+ "\",\""+"1"+ "\");'>" + cellValue
			+ "</a>";
}

//未提交记录数查看
function wtjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"wtjscx"+ "\",\""+"0"+ "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(rcid,flag,tjzt) {
	showDialog("查看", 900, 450, "gyjc_jcjglr.do?method=getJcjgLrcxList&rcid="+rcid+"&flag="+flag+"&tjzt="+tjzt);
}

/**
 * 选楼栋
 * @param obj
 * @return
 */
function selLd(obj){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var guid = jQuery("#guid").val();
	if(!guid){
		guid = "";
	}
	var xydm = jQuery(obj).parent().parent().parent().find("[name='xydm']").val();
	var lddm = new Array();
	var lddmInputObj = jQuery(obj).parent().parent().parent().find("[name='lddm']");
	if(lddmInputObj != null && lddmInputObj.length >0){
		for ( var j = 0; j < lddmInputObj.length; j++) {
			lddm.push(lddmInputObj[j].value);
		}
	}
	var url = "gyjc_jcrc.do?method=getSelLdCx";
	url += "&xn="+xn;
	url += "&xq="+xq;
	url += "&guid="+guid;
	url += "&xydm="+xydm;
	url += "&lddms="+lddm;
	var title = "楼栋选择";
	showDialog(title, 300, 200, url);
	
}