function searchRs() {
	var map = getSuperSearch();
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
 * 检查
 * @return
 */
function jc(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("请选择一条记录！");
	}
	if(rows[0]["jzrq"] < jQuery("#today").val() || rows[0]['ccrq'] > jQuery("#today").val()){
		return showAlertDivLayer("不在检查日程维护时间区间内！");
	}
	document.location.href = "gyjc_jcjglr.do?method=getJcjgLrcxList&rcid="+rows[0]['guid']+"&flag=lr&js="+rows[0]['js'];
}

/**
 * 保存录入结果
 * @return
 */
function saveLrjg(type){
	var url = "gyjc_jcjglr.do?method=saveLrjg&tjzt=" + type;
	ajaxSubFormWithFun("JcjglrForm", url, function(data) {
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

//提交记录数查看
function tjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"ytjscxsub"+ "\",\""+"1"+ "\");'>" + cellValue
			+ "</a>";
}

//未提交记录数查看
function wtjLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["guid"] + "\",\""+"wtjscxsub"+ "\",\""+"0"+ "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function View(rcid,flag,tjzt) {
	showDialog("查看", 900, 550, "gyjc_jcjglr.do?method=getJcjgLrcxList&username=isnotnull&rcid="+rcid+"&flag="+flag+"&tjzt="+tjzt);
}

